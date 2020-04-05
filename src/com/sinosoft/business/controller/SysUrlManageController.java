/*
 * Powered By 尹力
 * Since 2015 - 2016-18-08
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

import com.sinosoft.business.po.SysUrlManage;
import com.sinosoft.business.service.SysUrlManageService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;

@Controller
public class SysUrlManageController {
	
	@Resource
	private SysUrlManageService SysUrlManageService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUrlManageController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/sysUrlManagemanage.do")
	public String SysUrlManageManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问SysUrlManage管理页面", method);
		model.addAttribute("pageHanName", "SysUrlManage"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		SysUrlManageService.SysUrlManagePageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/sysUrlManageshow.do")
	public @ResponseBody Map<String, Object>  showSysUrlManage(
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
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/sysUrlManage.do")
	public String SysUrlManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		model.addAttribute("pageHanName", "SysUrlManage"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		/*
		 * 逻辑处理
		 */
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 保存新增对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/sysUrlManagenew.do")
	public @ResponseBody Map<String, Object>  saveNew(
			HttpServletRequest request,HttpServletResponse response,SysUrlManage sysUrlManage){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存更新对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/sysUrlManageupdate.do")
	public @ResponseBody Map<String, Object>  update(
			HttpServletRequest request,HttpServletResponse response ,SysUrlManage sysUrlManage) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/sysUrlManagedelete.do")
	public @ResponseBody Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
}

