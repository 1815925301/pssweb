package com.sinosoft.filter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.spring.SpringContextUtils;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.GenerateSequenceUtils;
import com.sinosoft.common.util.ObjectToString;
import com.sinosoft.common.util.ThreadContainer;
import com.sinosoft.dblog.DBLog;
import com.sinosoft.dblog.DBLogContext;
import com.sinosoft.dblog.po.MonitorReceiverLog;
import com.sinosoft.filter.wrapper.ActivityRequestWrapper;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.filter
 * @ClassName: LogDealFilter
 * @Description: LogDealFilter用于前端调用本系统的接口的详细信息进行拦截和执行时间处理
 * @author mrajian
 * @Version V1.0
 * @date 2013-9-16 下午11:45:22
 */
public class LogDealFilter implements Filter {

	private static final Logger loggerApi = LoggerFactory.getLogger(LogDealFilter.class);

	private static final Logger logger = LoggerFactory.getLogger(Filter.class);

	/** 日志记录 */
	private DBLog dblog;

	/** dblog是否初始化过 */
	private boolean isdblogInit = false;

	/** 重入锁 */
	private final Lock lock = new ReentrantLock();

	/** 是否开启数据库DB */
	private boolean logdb = true;

	/** 是否记录请求参数详细信息 */
	private boolean logParam = false;

	private ThreadLocal<String> serivalThreadLocal;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if (this.serivalThreadLocal == null)
			this.serivalThreadLocal = ThreadContainer.getMyThreadLocal(Constant.INNER_SERIAL_THREAD_LOCAL);
		if (this.serivalThreadLocal == null) {
			this.serivalThreadLocal = new ThreadLocal<String>();
			ThreadContainer.addMyThreadLocal(Constant.INNER_SERIAL_THREAD_LOCAL, this.serivalThreadLocal);
		}
		this.logdb = "false".equals(filterConfig.getInitParameter("logdb")) ? false : true;
		this.logParam = "true".equals(filterConfig.getInitParameter("logParam")) ? true : false;
		// 设置本系统名称
		logger.debug("LogDealFilter init!");
	}

	/**
	 * 初始化dblog
	 * 
	 * @param request
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-9-17 上午12:05:25
	 * @version V1.0
	 */
	public void initDblog(HttpServletRequest request) {
		if (!logdb)
			return;
		if (logdb && isdblogInit)
			return;
		lock.lock();
		try {
			ApplicationContext ctx = SpringContextUtils.getApplicationContext(request);
			if (dblog == null && ctx != null && ctx.getBean("dblog") != null)
				dblog = (DBLog) ctx.getBean("dblog");
			if (dblog == null)
				dblog = DBLogContext.getDBLog();
			isdblogInit = true;
			if (dblog == null && isdblogInit)
				logger.error("DBLog未实例化!无法记录数据库日志信息!");
		} finally {
			lock.unlock();
		}

	}

	@Override
	public void destroy() {
		ThreadContainer.destoryAllThreadLocal();
		logger.debug("LogDealFilter destory!");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		initDblog(request);
		HttpServletRequest activityRequest = null;

		// 如果是文件流
		if (isMultipartRequest(request)) {
			try {
				// 如果不是DefaultMultipartHttpServletRequest类型实例
				if (!(request instanceof DefaultMultipartHttpServletRequest)) {
					ApplicationContext ctx = SpringContextUtils.getApplicationContext(request);
					MultipartResolver resolver = (MultipartResolver) ctx.getBean(MultipartResolver.class);
					// 转换为 MultipartHttpServletRequest
					request = resolver.resolveMultipart(request);
				}
				if (request instanceof DefaultMultipartHttpServletRequest) {
					DefaultMultipartHttpServletRequest requestwrapper = (DefaultMultipartHttpServletRequest) request;
					Field paramField = DefaultMultipartHttpServletRequest.class.getDeclaredField("multipartParameters");
					paramField.setAccessible(true);

					@SuppressWarnings("unchecked")
					Map<String, String[]> map = (Map<String, String[]>) paramField.get(requestwrapper);
					if (map == null) {
						map = new HashMap<String, String[]>();
						paramField.set(requestwrapper, map);
					}
					map.put(Constant.INNER_SERIAL, new String[] { GenerateSequenceUtils.generateSequenceNo() });
					paramField.setAccessible(false);
					activityRequest = requestwrapper;
				}
			} catch (Exception e) {
				logger.error("过滤器中，对文件流类型的请求处理中出现异常：{}", e);
			}
		} else {
			// 包装request
			ActivityRequestWrapper requestwrapper = new ActivityRequestWrapper(request);
			requestwrapper.setParameter(Constant.INNER_SERIAL, GenerateSequenceUtils.generateSequenceNo());
			activityRequest = requestwrapper;
		}
		if (activityRequest == null)
			activityRequest = request;
		MonitorReceiverLog receiverLog = new MonitorReceiverLog();
		// 处理前调用方法
		if (!request.getRequestURL().toString().contains("/mobile/"))
			doBefore(activityRequest, receiverLog);
		try {
			filterChain.doFilter(activityRequest, response);
		} finally {
			ThreadContainer.cleanAllThreadLocal();
			// 处理后调用方法 暂时加上不对手机请求进行过滤处理
			if (!request.getRequestURL().toString().contains("/mobile/")) {
				doAfter(activityRequest, receiverLog);
			}
		}
	}

	/**
	 * 判断当前请求是否是文件流类型
	 * 
	 * @param request
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-9-19 下午01:32:36
	 * @version V1.0
	 */
	private boolean isMultipartRequest(HttpServletRequest request) {
		if (!"post".equals(request.getMethod().toLowerCase())) {
			return false;
		}
		String contentType = request.getContentType();
		return (contentType != null && contentType.toLowerCase().startsWith("multipart/"));
	}

	/**
	 * request response 等事前处理;记录事前logger
	 * 
	 * @param receiverLog
	 * @return void
	 * @throws IOException
	 * @throws ServletException
	 * @author mrajian
	 * @date 2013-9-17 上午09:48:34
	 * @version V1.0
	 */
	private void doBefore(HttpServletRequest request, MonitorReceiverLog receiverLog) throws IOException, ServletException {
		ExtendUsers user = (ExtendUsers) request.getSession().getAttribute("CURRENT_USER_INFO");
		if (user != null) {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String serviceName = request.getRequestURI().substring(1).replaceAll(request.getContextPath().substring(1), "");
			String requestUrl = request.getRequestURL().toString();
			String serviceSerial = request.getParameter(Constant.INNER_SERIAL);
			// 在这里为当前线程写入内部服务序列号
			this.serivalThreadLocal.set(serviceSerial);
			// 记录model
			receiverLog.setUserName(user.getUserName());
			receiverLog.setUserId(user.getId());
			receiverLog.setOrgId(user.getOrgId());
			receiverLog.setRoleIds(userDetails.getAuthorities().toString());
			receiverLog.setInnerSerialId(serviceSerial);
			receiverLog.setServiceName(serviceName);
			receiverLog.setFullUrl(requestUrl);
			receiverLog.setServerIp(InetAddress.getLocalHost().getHostAddress());
			receiverLog.setRemoteIp(ActivityWebUtils.getRemoteIp(request));
			Long currentMillsBegin = System.currentTimeMillis();
			Timestamp invokeDate = new Timestamp(currentMillsBegin);
		    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
			receiverLog.setInvokeTime(invokeDate);
			receiverLog.setTimeCost(currentMillsBegin.intValue());

			String beginPre = Constant.INNER_SERIAL + "->" + receiverLog.getInnerSerialId();
			if (logger.isDebugEnabled()) {
				logger.debug("{} 请求的地址信息->:{}", beginPre, receiverLog.getFullUrl());
				if (logParam) {
					logger.debug("{} 请求详细参数信息->:{}", beginPre, ObjectToString.convertToString(request.getParameterMap()));
				}
				logger.debug("{} 请求开始执行时间->:{}", beginPre, invokeDate.toString());
			}
		}
	}

	/**
	 * request response 等事后处理;记录事前logger
	 * 
	 * @param request
	 * @param receiverLog
	 * @return void
	 * @throws IOException
	 * @throws ServletException
	 * @author mrajian
	 * @date 2013-9-17 上午10:03:54
	 * @version V1.0
	 */
	private void doAfter(HttpServletRequest request, MonitorReceiverLog receiverLog) throws IOException, ServletException {
		String beginPre = Constant.INNER_SERIAL + "->" + receiverLog.getInnerSerialId();

		long currentMillsBegin = receiverLog.getTimeCost();
		long currentMillsEnd = System.currentTimeMillis();
		Timestamp responseTime = new Timestamp(currentMillsEnd);
		Long timeCost = currentMillsEnd - currentMillsBegin;
		receiverLog.setTimeCost(timeCost.intValue());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		receiverLog.setResponseTime(responseTime);
		Object code = request.getAttribute(Constant.RESULT_CODE);
		if (code != null) {
			receiverLog.setResultCode(code.toString());
		}
		Object resultMsg = request.getAttribute(Constant.RESULT_MSG);
		if (resultMsg != null) {
			receiverLog.setResultMsg(resultMsg.toString());
		}
		if (receiverLog.getResultCode() == null || "".equals(receiverLog.getResultCode().trim())) {
			receiverLog.setResultCode(Constant.COMMON_UNFOUND.UNFOUND_CODE + "");
			receiverLog.setResultMsg(Constant.COMMON_UNFOUND.UNFOUND_MSG);
		}
		if (logdb && dblog != null)
			dblog.monitorReceiverLog(receiverLog);
		if (logger.isDebugEnabled()) {
			logger.debug("{} 请求结束执行时间->:{}", beginPre, responseTime.toString());
			logger.debug("{} 请求响应时间->:{}ms", beginPre, receiverLog.getTimeCost());
		}
		loggerApi.info("{} 请求的详细信息->:{}", beginPre, receiverLog.toString());
	}

}
