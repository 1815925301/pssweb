package com.sinosoft.security.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.extend.ExtendOrganization;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.extend.TreeNode;
import com.sinosoft.security.service.OrganizationService;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: OrganizationController
 * @Description: 机构管理  控制层
 */
@Controller
public class OrganizationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

	@Resource
	private OrganizationService organizationService;
	
	/**
	 * 工作提醒页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author mrajian
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/organization.do")
	public String organizationManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问机构管理页面", method);
		model.addAttribute("pageHanName", "机构"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		//organizationService.getOrgInfoForInitPage(model, method, request);
		return "manage/" + model.get("pageName");
	}
	/**
	 * 
	 * @author 张永斌
	 *获取机构树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getOrganizationTree.do")
	public @ResponseBody Map<String, Object> getTree(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取机构信息");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		List<TreeNode> list =organizationService.getOrganizationTree(modelMap, request);
		if(list!=null&&list.size()>0){
			modelMap.put("status", "success");
			modelMap.put("data", list);
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 
	 * @author 张永斌
	 *根据机构id获取子机构
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getOrganization.do")
	public @ResponseBody String getInfo(
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取机构信息");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		List<Organization> list = organizationService.getOrganizationList(modelMap, request);
		try {
			if(list!=null&&list.size()>0){
				modelMap.put("status", "success");
				modelMap.put("data",list);
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的机构信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return JSONArray.fromObject(list).toString();
	}
	
	/**
	 * 根据机构id获取机构信息
	 * @param orgId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午09:39:41
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showOrgInfo.do")
	public @ResponseBody Map<String, Object> getOrgInfo(
			@RequestParam(value = "orgId", required = true)Long orgId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定id的机构信息，orgId={}", orgId);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (orgId == null) {
				LOGGER.error("获取给定id的机构信息失败，机构id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该机构不存在，请刷新页面重新请求!");
			} else {
				ExtendOrganization extOrg = organizationService.getExtendOrgInfoByOrgId(orgId);
				if (extOrg != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", extOrg);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该机构不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的机构信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存新的机构信息
	 * @param organization
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午01:24:05
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewOrgInfo.do")
	public @ResponseBody Map<String, Object> addNewOrganization(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("保存新的机构信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null ) {
				modelMap.put("status", "exception");
			} else {
				if (organizationService.addNewOrganization(request, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "保存新的机构信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("保存新的机构信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 修改机构信息
	 * @param organization
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 下午03:41:42
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeOrgInfo.do")
	public @ResponseBody Map<String, Object> updateOrganization(	HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新机构信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null ) {
				modelMap.put("status", "exception");
			} else {
				if (organizationService.updateOrganization(request, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新机构信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新机构信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/removeOrgInfo.do")
	public @ResponseBody Map<String, Object> removeOrganization(
			@RequestParam(value = "orgId", required = true)Long orgId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除机构信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || orgId == null) {
				modelMap.put("status", "exception");
			} else {
				if (organizationService.removeOrganization(orgId, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "删除机构信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除机构信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}

}
