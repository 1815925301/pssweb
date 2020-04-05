/*
 * Powered By JC
 * Since 2016-08-01 - 2016-08-04
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.po.PssPrompt;
import com.sinosoft.business.service.PssPromptService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

@Controller
public class PssPromptController {
	
	@Resource
	private PssPromptService pssPromptService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PssPromptController.class);
	
	/**
	 * 友好提示管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssPrompt.do")
	public String pssPromptManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssPrompt管理页面", method);
		model.addAttribute("pageHanName", "PssPrompt"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		pssPromptService.pssPromptPageInit(request, model, method);
		return "manage/" + model.get("pageName");
	}
	/** 
	 * 保存新增提示信息
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/savePssPrompt.do")
	public @ResponseBody Map<String, Object>  saveNewPrompt(
			HttpServletRequest request,HttpServletResponse response,PssPrompt pssPrompt){
		LOGGER.debug("保存新的用户信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
				if (pssPromptService.savePrompt(request, pssPrompt, modelMap)) {
					//此时更新成功，将改后的用户信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", "用户信息增加成功！");
				}
		} catch(Exception e) {
			LOGGER.error("修改当前用户的基本信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	

	/**
	 * 根据指定的友好提示id 获取友好提示信息
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author JC
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/showPromptbyid.do")
	public @ResponseBody Map<String, Object>  getPromptByID(HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		pssPromptService.getPromptById(modelMap, request);
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存更新提示信息
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/changePrompt.do")
	public @ResponseBody Map<String, Object>  updatePrompt(
			HttpServletRequest request,HttpServletResponse response ,PssPrompt pssPrompt) {
		LOGGER.debug("更新用户信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
				if (pssPromptService.updatePrompt(request, pssPrompt, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新采集单信息成功！");
				}
			
		} catch(Exception e) {
			LOGGER.error("更新采集单信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/removePrompt.do")
	public @ResponseBody Map<String, Object> delete(
			@RequestParam(value = "id", required = true)Long id,
			HttpServletRequest request,HttpServletResponse response) {
		LOGGER.debug("删除信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (pssPromptService.remove(id, request, modelMap)) {
				modelMap.put("status", "success");
				modelMap.put("data", "删除信息成功！");
			}
			
			}catch(Exception e){
				LOGGER.error("删除友好提示信息的操作出现异常：{}", e);
				modelMap.put("status", "error");
				modelMap.put("data", "系统中存在该用户的操作数据，如上报活动等！<BR/>无法删除！");
			}
			
			return modelMap.getModelMap();
	}
	
}

