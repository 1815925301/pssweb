package com.sinosoft.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.common.constant.Constant;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: ActivityWebUtils
 * @Description: web请求处理共用方法
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 下午05:13:05
 */
public class ActivityWebUtils {
	
	/**
	 * 封装返回给MVC的信息
	 * @param request
	 * @param model
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-19 下午05:19:27
	 * @version V1.0
	 */
	public static void WrapperModle (HttpServletRequest request, ModelMap model) {
		String pageName = request.getRequestURI().substring(1).split("/")[1].replaceAll(".do", "");
		model.addAttribute("currentPageUrl", "/" + pageName + ".do");
		model.addAttribute("pageName", pageName); //页面名称
		model.addAttribute("menuList", request.getSession().getAttribute("USER_MENU"));
		model.addAttribute("ctx", request.getContextPath()); //应用名称
		request.setAttribute(Constant.RESULT_CODE, Constant.COMMON_SUCCESS.SUCCESS_CODE);
		request.setAttribute(Constant.RESULT_MSG, Constant.COMMON_SUCCESS.SUCCESS_MSG);
	}
	
	
	/**
	 * 获取客户端ip地址
	 * @param request
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-10-20 下午03:25:53
	 * @version V1.0
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
}
