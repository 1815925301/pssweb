package com.sinosoft.security.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;

import com.sinosoft.po.RowSet;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.extend.ExtendResources;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ResourcesQuery;
import com.sinosoft.security.service.ResourceModuleService;
import com.sinosoft.security.service.ResourcesService;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: resourcesController
 * @Description: 功能管理  控制层
 * @author zhaomin
 * @Version V1.0
 * @date 2013-10-16 上午01:42:28
 */
@Controller
public class ResourcesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesController.class);

	@Resource
	private ResourcesService resourcesService;
	
	@Resource
	private ResourceModuleService resourceModuleService;
	
	@Resource
	private SystemConstant systemConstant;
	
	/**
	 * 功能管理页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/resources.do")
	public String resourcesManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问功能管理页面", method);
		model.addAttribute("pageHanName", "菜单"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		resourcesService.getResourceInfoForInitPage(model, method, request);
		
		return "manage/" + model.get("pageName");
	}
	
	/**
	 * 根据父id获取功能列表
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-15 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getResourceList.do")
	private @ResponseBody String getResourceList(
			@RequestParam(value = "pId", required = true)Long pId,
			@RequestParam(value = "moduleId", required = true)Long moduleId,
			ResourcesQuery resourceQuery, 
			HttpServletRequest request, HttpServletResponse response){
		LOGGER.debug("获取功能列表数据，");
		System.out.println("获取功能列表数据，");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		
		RowSet rowSet = resourcesService.getResourceList(resourceQuery, modelMap, pId, moduleId);
		
		LOGGER.info("返回的角色列表数据："+JSONObject.fromObject(rowSet).toString());
		System.out.println("返回的角色列表数据："+JSONObject.fromObject(rowSet).toString());
		return JSONObject.fromObject(rowSet).toString();
	}

	
	/**
	 * 根据功能id获取功能信息
	 * @param resourceId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 上午09:39:41
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showResourceInfo.do")
	public @ResponseBody Map<String, Object> getResourceInfo(
			@RequestParam(value = "resourceId", required = true)Long resourceId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定id的功能信息，resourceId={}", resourceId);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (resourceId == null) {
				LOGGER.error("获取给定id的功能信息失败，功能id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该功能不存在，请刷新页面重新请求!");
			} else {
				ExtendResources extResource = resourcesService.getExtendResourceInfoByResourceId(resourceId);
				if (extResource != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", extResource);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该功能不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的功能信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 获取功能树
	 * @param moduleId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午19:39:41
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showResourceInfoTree.do")
	public @ResponseBody Map<String, Object> getResourceInfoTree(
			@RequestParam(value = "moduleId", required = true)Long moduleId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("加载功能树");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			List<Resources> list = resourcesService.getResourceInfoTree(moduleId);
			if(list.size() > 0){
				modelMap.put("status", "success");
				modelMap.put("data", list);
			}else {
				modelMap.put("status", "failure");
				modelMap.put("data", "功能树未找到相应数据!");
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的功能信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		} 
		return modelMap.getModelMap();
	}
	
    @RequestMapping(method = RequestMethod.POST, value = "/showResourceList.do")
	public @ResponseBody String getResourceList(@RequestParam(value = "moduleId", required = true)Long moduleId,
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("根据模块Id，获取模块下菜单列表！");
		LOGGER.debug("获取模块Id，moduleId={}", moduleId);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		RowSet rowSet = new RowSet();
		try {
			if (String.valueOf(moduleId) == null) {
				LOGGER.error("获取给定moduleId，模块Id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "模块获取失败，请刷新页面重新请求!");
			} else {
				rowSet = resourcesService.getResourceListByModuleId(moduleId, modelMap);
				if (rowSet != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", rowSet);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该常量不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的常量信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		LOGGER.info("返回常量数据模板："+JSONObject.fromObject(rowSet).toString());
		return JSONObject.fromObject(rowSet).toString();
	}


	/**
	 * 获取ResourceModule列表
	 * @param resourceId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午19:39:41
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showResourceTreeInfo.do")
	public @ResponseBody Map<String, Object> getResourceModuleInfo(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("加载功能树");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			List<Resources> list = resourcesService.getAllResources();
			if(list.size() > 0){ 
				modelMap.put("status", "success");
				modelMap.put("data", list);
			}else {
				modelMap.put("status", "failure");
				modelMap.put("data", "功能树未找到相应数据!");
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的功能信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}

	
	/**
	 * 保存新的功能信息
	 * @param resources
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 上午01:24:05
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewResourceInfo.do")
	public @ResponseBody Map<String, Object> addNewresources(Resources resources, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("保存新的功能信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || resources == null) {
				modelMap.put("status", "exception");
			} else {
				if (resourcesService.addNewResources(resources, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "保存新的功能信息成功！");
					systemConstant.setResourceModuleList();
					systemConstant.setRoleResourceList();
				}
			}
		} catch(Exception e) {
			LOGGER.error("保存新的功能信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 修改功能信息
	 * @param resources
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 下午03:41:42
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeResourceInfo.do")
	public @ResponseBody Map<String, Object> updateresources(Resources resources, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新功能信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || resources == null) {
				modelMap.put("status", "exception");
			} else {
				if (resourcesService.updateResources(resources, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新功能信息成功！");
					systemConstant.setResourceModuleList();
					systemConstant.setRoleResourceList();
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新功能信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/removeResourceInfo.do")
	public @ResponseBody Map<String, Object> removeresources(
			@RequestParam(value = "resourceId", required = true)Long resourceId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除功能信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || resourceId == null) {
				modelMap.put("status", "exception");
			} else {
				if (resourcesService.removeResources(resourceId, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "删除功能信息成功！");
					systemConstant.setResourceModuleList();
					systemConstant.setRoleResourceList();
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除功能信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}

}
