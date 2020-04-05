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

import com.sinosoft.business.service.UserUpdateService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.business.po.UserUpdate;;
@Controller
public class userUpdateController {
	
	@Resource
	private UserUpdateService userUpdateService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(userUpdateController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/userUpdate.do")
	public String PssUsermessageUpdateManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问userUpdate管理页面", method);
		model.addAttribute("pageHanName", "userUpdate"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		userUpdateService.UserUpdatePageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssUsermessageUpdateshow.do")
	public @ResponseBody Map<String, Object>  showPssUsermessageUpdate(
			HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/** 
	 * 进入新增页面
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssUsermessageUpdate.do")
	public String PssUsermessageUpdate(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		model.addAttribute("pageHanName", "PssUsermessageUpdate"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		/*
		 * 逻辑处理
		 */
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 保存新增对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/saveUserMessage1.do")
	public @ResponseBody Map<String, Object>  saveNew(
			HttpServletRequest request,HttpServletResponse response,UserUpdate pssUsermessageUpdate){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存更新对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/saveUserUpdate.do")
	public @ResponseBody Map<String, Object>  update(
			HttpServletRequest request,HttpServletResponse response ,UserUpdate userUpdate) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		userUpdateService.update(request, userUpdate, modelMap);
		return modelMap.getModelMap();
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssUsermessageUpdatedelete.do")
	public @ResponseBody Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
}

