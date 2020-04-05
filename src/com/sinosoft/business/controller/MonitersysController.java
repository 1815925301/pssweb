/*
 *  2016-08-19
 */

package com.sinosoft.business.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;
@Controller
public class MonitersysController{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitersysController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/monitersys.do")
	public String PssUsermessageUpdateManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问Monitersys管理页面", method);
		model.addAttribute("pageHanName", "Monitersys"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 查看对象
	 **/
	
	/** 
	 * 进入新增页面
	 **/
	
	/** 
	 * 保存新增对象
	 **/

	
	/**
	 * 保存更新对象
	 **/
	
	/**
	 *删除对象
	 **/
}

