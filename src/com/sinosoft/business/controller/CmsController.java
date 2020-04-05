/*
 * Powered By 张永斌
 * Since 2015 - 2016-52-17
 */

package com.sinosoft.business.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinosoft.business.service.StudentService;
import com.sinosoft.common.constant.MappingConstantConfig;
import com.sinosoft.common.util.ActivityWebUtils;


@Controller
public class CmsController {
	
	@Resource
	private StudentService STUDENTService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CmsController.class);
	
	/**
	 * 管理页面
	 * @throws IOException 
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/content.do")
	public String content(HttpServletRequest request,HttpServletResponse response, ModelMap model,String path) throws IOException {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问Student管理页面", method);
		model.addAttribute("pageHanName", "Student"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		model.addAttribute("bathPath", MappingConstantConfig.getValue("DEPLOY_ADDRESS"));  
		if((path).indexOf("book")>0){
			path=MappingConstantConfig.getValue("DEPLOY_ADDRESS")+path;
			model.addAttribute("path",path);
			return "manage/book";
		}
		
		if(path.indexOf("/site1")>-1)
			path=path.replaceAll("/site1", "");
		if(path.indexOf("/site2")>-1)
			path=path.replaceAll("/site2", "");
		model.addAttribute("path",path);
		return "manage/" + model.get("pageName");
		
	}
	
	
	/**
	 * 管理页面
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/search.do")
	public String search(HttpServletRequest request, ModelMap model) throws UnsupportedEncodingException {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问Student管理页面", method);
		model.addAttribute("pageHanName", "Student"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		model.addAttribute("bathPath", MappingConstantConfig.getValue("DEPLOY_ADDRESS"));  
		String catalog=request.getParameter("catalog").trim();
		String qtype=request.getParameter("qtype");
		String hitsPerPage=request.getParameter("hitsPerPage");
		String date1=request.getParameter("date1");
		String showSummaries=request.getParameter("showSummaries");
		String qsort=request.getParameter("qsort");
		String query=request.getParameter("query");
		
		String str="catalog="+catalog+"&qtype="+qtype+"&hitsPerPage="+hitsPerPage+"&date1="+date1+
				"&showSummaries="+showSummaries+"&qsort="+qsort+"&query="+URLEncoder.encode(query,"UTF-8");
		
		String path="";
		if(catalog.equals("en"))
			path=MappingConstantConfig.getValue("DEPLOY_ADDRESS")+"/search/search_en.jsp?"+str;
		else
			path=MappingConstantConfig.getValue("DEPLOY_ADDRESS")+"/search/search.jsp?"+str;
		
		model.addAttribute("path",path);
		return "manage/" + model.get("pageName");
	}
	
}

