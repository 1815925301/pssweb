/*
 * Powered By 尹力
 * Since 2015 - 2016-56-25
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

import com.sinosoft.business.po.PssMetadata0scene;
import com.sinosoft.business.po.query.PssMetadata0sceneQuery;
import com.sinosoft.business.service.PssMetadata0sceneService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;


@Controller
public class PssMetadata0sceneController {
	
	@Resource
	private PssMetadata0sceneService pssMetadata0sceneService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PssMetadata0sceneController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssMetadata0scenemanage.do")
	public String PssMetadata0sceneManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssMetadata0scene管理页面", method);
		model.addAttribute("pageHanName", "PssMetadata0Scene"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		pssMetadata0sceneService.PssMetadata0scenePageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = { RequestMethod.POST,RequestMethod.GET}, value = "/showPssMetadata0scene.do")
	public String  showPssMetadata0scene(HttpServletRequest request,ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityWebUtils.WrapperModle(request, model);
		PssMetadata0scene product = pssMetadata0sceneService.getPssMetadata0scene(request, model,"");
		model.addAttribute("product", product);
		return "manage/pssMetadata0Scene";
	}
	
}

