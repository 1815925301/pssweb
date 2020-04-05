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
import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.extend.ExtendResourceModule;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ResourceModuleQuery;
import com.sinosoft.security.service.ResourceModuleService;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: resourceModuleController
 * @Description: 模块管理  控制层
 * @author zhaomin
 * @Version V1.0
 * @date 2013-10-16 上午01:42:28
 */
@Controller
public class ResourceModuleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceModuleController.class);

	@Resource
	private ResourceModuleService resourceModuleService;
	
	@Resource
	private SystemConstant systemConstant;
	
	/**
	 * 模块管理页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/resourceModule.do")
	public String resourceModuleManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问模块管理页面", method);
		model.addAttribute("pageHanName", "模块"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		resourceModuleService.getModuleInfoForInitPage(model, method, request);
		return "manage/" + model.get("pageName");
	}
	
	/**
	 * 根据父id获取模块列表
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-15 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getModuleList.do")
	private @ResponseBody String getModuleList(
			@RequestParam(value = "pid", required = true)String pid,
			ResourceModuleQuery moduleQuery, 
			HttpServletRequest request, HttpServletResponse response){
		LOGGER.debug("获取模块列表数据，");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		
		RowSet rowSet = resourceModuleService.getModuleList(moduleQuery, modelMap, pid);
		
		LOGGER.info("返回的角色列表数据："+JSONObject.fromObject(rowSet).toString());
		return JSONObject.fromObject(rowSet).toString();
	}

	
	/**
	 * 根据模块id获取模块信息
	 * @param moduleId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 上午09:39:41
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showModuleInfo.do")
	public @ResponseBody Map<String, Object> getModuleInfo(
			@RequestParam(value = "moduleId", required = true)Long moduleId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定id的模块信息，moduleId={}", moduleId);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (moduleId == null) {
				LOGGER.error("获取给定id的模块信息失败，模块id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该模块不存在，请刷新页面重新请求!");
			} else {
				ExtendResourceModule extModule = resourceModuleService.getExtendModuleInfoByModuleId(moduleId);
				if (extModule != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", extModule);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该模块不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的模块信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 获取模块树
	 * @param moduleId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午19:39:41
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showModuleInfoTree.do")
	public @ResponseBody Map<String, Object> getModuleInfoTree(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("加载模块树");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			List<ResourceModule> list = resourceModuleService.getModuleInfoTree();
			if(list.size() > 0){
				modelMap.put("status", "success");
				modelMap.put("data", list);
			}else {
				modelMap.put("status", "failure");
				modelMap.put("data", "模块树未找到相应数据!");
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的模块信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}


	
	/**
	 * 保存新的模块信息
	 * @param resourceModule
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 上午01:24:05
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewModuleInfo.do")
	public @ResponseBody Map<String, Object> addNewresourceModule(ResourceModule resourceModule, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("保存新的模块信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || resourceModule == null) {
				modelMap.put("status", "exception");
			} else {
				if (resourceModuleService.addNewResourceModule(resourceModule, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "保存新的模块信息成功！");
					systemConstant.setResourceModuleList();
					systemConstant.setRoleResourceList();
				}
			}
		} catch(Exception e) {
			LOGGER.error("保存新的模块信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		LOGGER.info("保存新的模块信息返回结果" + modelMap.getModelMap());
		return modelMap.getModelMap();
	}
	
	/**
	 * 修改模块信息
	 * @param resourceModule
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 下午03:41:42
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeModuleInfo.do")
	public @ResponseBody Map<String, Object> updateresourceModule(ResourceModule resourceModule, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新模块信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || resourceModule == null) {
				modelMap.put("status", "exception");
			} else {
				if (resourceModuleService.updateResourceModule(resourceModule, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新模块信息成功！");
					systemConstant.setResourceModuleList();
					systemConstant.setRoleResourceList();
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新模块信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/removeModuleInfo.do")
	public @ResponseBody Map<String, Object> removeresourceModule(
			@RequestParam(value = "moduleId", required = true)Long moduleId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除模块信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || moduleId == null) {
				modelMap.put("status", "exception");
			} else {
				if (resourceModuleService.removeResourceModule(moduleId, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "删除模块信息成功！");
					systemConstant.setResourceModuleList();
					systemConstant.setRoleResourceList();
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除模块信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}

}
