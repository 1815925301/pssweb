package com.sinosoft.filter;

import java.io.IOException;
import java.util.Enumeration;

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

import com.sinosoft.common.constant.MappingConstantConfig;

/**
 * @Package com.sinosoft.filter
 * @ClassName: AjaxSessionTimeoutFilter
 * @Description: AJAX请求过滤器 指引session过期的Ajax请求返回超时的提示
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-10 下午10:23:17
 */
public class AjaxSessionTimeoutFilter implements Filter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxSessionTimeoutFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	private StringBuilder getParamers(ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		Enumeration<?> enumeration = request.getParameterNames();
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			sb.append(key + "=" + request.getParameter(key) + ",");
		}

		return sb;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String outInterface = MappingConstantConfig.getValue("OUR_INTERFACE");
		StringBuffer reqUrl = req.getRequestURL();
		// 判断session里是否有用户信息
		if (req.getSession().getAttribute("SPRING_SECURITY_CONTEXT") == null) {
			if (reqUrl.toString().contains(outInterface) || reqUrl.toString().contains("/mobile/")) {
				if (reqUrl.toString().contains("/mobile/")) {
					System.out.println("手机的请求：" + reqUrl + "\r\n请求数据:" + getParamers(request));
				}
				chain.doFilter(req, res);
			} else {
				LOGGER.info("此时用户的Session中没有信息，判定为没有登录或者session过期");
				// 如果是ajax请求响应头会有，x-requested-with；
				if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
					LOGGER.info("此时访问类型为Ajax，直接返回session超时编码");
					res.setStatus(911);// 表示session timeout
				} else {
					chain.doFilter(req, res);
				}
			}
		} else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
