package com.sinosoft.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinosoft.common.util.ActivityWebUtils;
@Controller
public class SensitiveareaController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/sensitivearea.do")
	public String ProductManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssCollectInfo管理页面", method);
		model.addAttribute("pageHanName", "sensitivearea"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
	}

}
