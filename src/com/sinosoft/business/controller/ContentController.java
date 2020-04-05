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

import com.sinosoft.business.po.Content;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.service.ContentManager;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;

@Controller
public class ContentController {
	@Resource
	private ContentManager contmgr;
	/***
	 * 新闻管理初始化页面
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ContentController.class);
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssContentZhCnmanage.do")
	public String selectContent(ModelMap model, HttpServletRequest request) {
		String method = request.getMethod();
	model.addAttribute("pageHanName", "pssContentZhCnmanage"); //页面名称
	ActivityWebUtils.WrapperModle(request, model);	
	contmgr.ContentPageInit(request,model,method);
	return  "manage/" + model.get("pageName");
	}
	
	
	/***
	 * 新增加保存
	 * @param content
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewContent.do")
	public @ResponseBody Map<String, Object> addsystem(Content content, 
			HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
				if (contmgr.saveContent(content, request, modelMap)) {
					//此时更新成功，将改后的用户信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", "用户信息增加成功！");
				}
		} catch(Exception e) {
			LOGGER.error("修改当前新闻管理的基本信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	
	/***
	 * 删除数据
	 * @param dataId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/deletecontentbyid.do")
	public @ResponseBody Map<String, Object> removedatabyid(
			@RequestParam(value = "dataId", required = true)Long dataId, 
			HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
		if (contmgr.removeContent(dataId,modelMap, request)) {
			modelMap.put("status", "success");
			modelMap.put("data", "删除用户信息成功！");
		}
		}catch(Exception e){
			LOGGER.error("删除用户信息的操作出现异常：{}", e);
			modelMap.put("status", "error");
			modelMap.put("data", "系统中存在该用户的操作数据，如上报活动等！<BR/>无法删除！");
		}
		return modelMap.getModelMap();
	}
	
	
	/***
	 * 通过id查询数据
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/selectcontentbyid.do")
	public @ResponseBody Map<String, Object> selectdatabyid(ModelMap model, HttpServletRequest request) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		contmgr.getContentById(modelMap, request);
		return modelMap.getModelMap();
	}

	
	/***
	 * 修改数据
	 * @param content
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/modifycontent.do")
	public @ResponseBody Map<String, Object> modifycontent(Content content, 
			HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			String id = request.getParameter("id");
			Content contentnew = new Content();
			contentnew = contmgr.updateContent(content, request, modelMap,id);
		} catch(Exception e) {
			LOGGER.error("修改新闻内容的基本信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/***
	 * 修改页面的确认
	 * @param content
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveContentConfirm.do")
	public @ResponseBody Map<String, Object> modifysystemconfirm(Content content, 
			HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
				if (contmgr.updateContentfirm(content, request, modelMap)) {
					//此时更新成功，将改后的用户信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", "内容信息更新成功！");
				}
		} catch(Exception e) {
			LOGGER.error("修改内容的基本信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
}
