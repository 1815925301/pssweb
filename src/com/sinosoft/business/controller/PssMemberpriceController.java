/*
 * Powered By 尹力
 * Since 2015 - 2016-01-10
 */

package com.sinosoft.business.controller;

import java.util.Locale;
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

import com.sinosoft.business.po.PssMemberprice;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.service.PssMemberpriceService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;


@Controller
public class PssMemberpriceController {
	
	@Resource
	private PssMemberpriceService PSS_MEMBERPRICEService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PssMemberpriceController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/memberprice.do")
	public String PssMemberpriceManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssMemberprice管理页面", method);
		model.addAttribute("pageHanName", "memberprice"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		PSS_MEMBERPRICEService.PssMemberpricePageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/selectmemberprice.do")
	public @ResponseBody Map<String, Object>  showPssMemberprice(
			HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		
		Long id=Long.parseLong(request.getParameter("id"));
		
			PssMemberprice memberprice=PSS_MEMBERPRICEService.getPssMemberprice(id);
			
			model.addAttribute("memberprice", memberprice);
			return model;
	}
	
	/** 
	 * 进入新增页面
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssMemberprice.do")
	public String PssMemberprice(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		model.addAttribute("pageHanName", "PssMemberprice"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		/*
		 * 逻辑处理
		 */
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 保存新增对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/savememberprice.do")
	public @ResponseBody Map<String, Object>  saveNew(
			HttpServletRequest request,HttpServletResponse response,PssMemberprice pssMemberprice){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		try {
			PSS_MEMBERPRICEService.save(request, pssMemberprice, modelMap);
			modelMap.put("status", "success");
			modelMap.put("data", PropertiesUtils.key("savesuccess", locale));
			
		} catch (Exception e) {
			LOGGER.error("更新信息的操作出现异常：{}", e);
			modelMap.put("error", "异常");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存更新对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/updatememberprice.do")
	public @ResponseBody Map<String, Object>  update(
			HttpServletRequest request,HttpServletResponse response ,PssMemberprice pssMemberprice) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		try {
			PSS_MEMBERPRICEService.update(request, pssMemberprice, modelMap);
			modelMap.put("status", "success");
			modelMap.put("data", PropertiesUtils.key("updatesuccess", locale));
			
		} catch (Exception e) {
			LOGGER.error("更新信息的操作出现异常：{}", e);
			modelMap.put("error", e);
		}
		return modelMap.getModelMap();
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssMemberpricedelete.do")
	public @ResponseBody Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
}

