package com.sinosoft.business.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sinosoft.business.service.FulltextSearchManager;
import com.sinosoft.common.util.ActivityWebUtils;

@Controller
public class FulltextSearchController {
	@Resource
	private FulltextSearchManager fullsearch;
	private static final Logger LOGGER = LoggerFactory.getLogger(FulltextSearchController.class);
	/***
	 * 首页的搜索
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/Fulltextsearch.do")
	public String selected(ModelMap model, HttpServletRequest request) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问FulltextSearchController管理页面", method);
		model.addAttribute("pageHanName", "Fulltextsearch"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		List receivelistdata = fullsearch.getFullTextSearch(model,request);
		return "manage/" + model.get("pageName");
	}
	
}
