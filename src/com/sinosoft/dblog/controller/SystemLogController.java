package com.sinosoft.dblog.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.SinosoftModelMap;

import com.sinosoft.dblog.po.extend.ExtendMonitorReceiverLog;
import com.sinosoft.dblog.po.query.SyslogQuery;
import com.sinosoft.dblog.service.MonitorReceiverLogService;
import com.sinosoft.dblog.service.MonitorSqlLogService;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemLogController.
 *
 * @Package com.sinosoft.business.controller
 * @ClassName: SystemLogController
 * @Description: 系统日志  MVC控制层web入口
 * @author zzq
 * @Version V1.0
 * @date 2013-10-26 上午06:58:50
 */
@Controller
public class SystemLogController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemLogController.class);
	
	/** The monitor receiver log service. */
	@Resource
	 private MonitorReceiverLogService monitorReceiverLogService;
	@Resource
	 private MonitorSqlLogService monitorSqlLogService;
	
	/**
	 * 工作提醒页面.
	 *
	 * @param request the request
	 * @param model the model
	 * @return String
	 * @author zzq
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/syslog.do")
	public String organizationManage(HttpServletRequest request, ModelMap model ,SyslogQuery syslogQuery) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问日志浏览页面", method);
		model.addAttribute("pageHanName", "syslog"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
//		monitorReceiverLogService.getMonitorSqlLogForInitPage(model, method, request);
		monitorReceiverLogService.selectlogdata(model,method,request,syslogQuery);
		return "manage/" + model.get("pageName");
	}

	/**
	 * 方法的作用: 获得  user info.
	 *
	 * @param userId the user id
	 * @param request the request
	 * @param response the response
	 * @return SystemLogController - user info
	 * @author 刘金成
	 * @version 2014-5-7-11:10:51
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showLog.do")
	public @ResponseBody Map<String, Object> getUserInfo(
			@RequestParam(value = "logId", required = true)Long logId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定id的日志信息，logId={}", logId);
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			if (logId == null) {
				LOGGER.error("获取给定id的日志信息失败，日志id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该日志不存在，请刷新页面重新请求!");
			} else {
				ExtendMonitorReceiverLog eLog = monitorReceiverLogService.getExtendMonitorReceiverLogById(logId);
				if (eLog != null) {
					modelMap.put("status", "success");
					modelMap.put("data", eLog);
				} else {
					modelMap.put("status", "failure");
					modelMap.put("data", "该日志不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的日志信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	
	/**
	 * Sql日志页面.
	 *
	 * @param request the request
	 * @param model the model
	 * @return String
	 * @author zzq
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/sqllog.do")
	public String sqlLogManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问日志浏览页面", method);
		model.addAttribute("pageHanName", "SQL日志"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		monitorSqlLogService.getMonitorSqlLogForInitPage(model, method, request);
		return "log/" + model.get("pageName");
	}

}
