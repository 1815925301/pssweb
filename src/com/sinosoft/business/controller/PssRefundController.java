/*
 * Powered By 尹力
 * Since 2015 - 2017-40-17
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

import com.sinosoft.business.po.PssRefund;
import com.sinosoft.business.service.PssRefundService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;

@Controller
public class PssRefundController {
	
	@Resource
	private PssRefundService PSS_REFUNDService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PssRefundController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssRefundmanage.do")
	public String PssRefundManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssRefund管理页面", method);
		model.addAttribute("pageHanName", "PssRefund"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		PSS_REFUNDService.PssRefundPageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssRefundshow.do")
	public @ResponseBody Map<String, Object>  showPssRefund(
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
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssRefund.do")
	public String PssRefund(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		model.addAttribute("pageHanName", "PssRefund"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		/*
		 * 逻辑处理
		 */
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 保存新增对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssRefundnew.do")
	public @ResponseBody Map<String, Object>  saveNew(
			HttpServletRequest request,HttpServletResponse response,PssRefund pssRefund){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存更新对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssRefundupdate.do")
	public @ResponseBody Map<String, Object>  update(
			HttpServletRequest request,HttpServletResponse response ,PssRefund pssRefund) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssRefunddelete.do")
	public @ResponseBody Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
}

