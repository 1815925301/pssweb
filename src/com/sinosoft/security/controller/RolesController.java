package com.sinosoft.security.controller;

import java.util.List;
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

import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.MySecurityMetadataSource;
import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.Roles;
import com.sinosoft.security.po.extend.ExtendRoles;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.extend.TreeNode;
import com.sinosoft.security.service.RolesService;
import com.sun.source.tree.Tree;

/**
 * @Package com.sinosoft.security.controller
 * @ClassName: RolesController
 * @Description: 角色管理  MVC控制层web入口
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-26 上午06:59:49
 */
@Controller
public class RolesController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(RolesController.class);
	
	@Resource
	private RolesService rolesService;
	
	@Resource
	private MySecurityMetadataSource mySecurityMetadataSource;
	
	@Resource
	private SystemConstant systemConstant;
	
	/**
	 * 角色管理页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author mrajian
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/roles.do")
	public String rolesManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问角色管理页面", method);
		model.addAttribute("pageHanName", "角色"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		rolesService.getRolesInfoForInitPage(model, method, request);
		return "manage/" + model.get("pageName");
	}
	
	
	/**
>>>>>>> .r181
	 * 根据指定的角色id 获取角色信息
	 * @param roleId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午04:21:56
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showRole.do")
	public @ResponseBody Map<String, Object> getRoleInfo(
			@RequestParam(value = "roleId", required = true)Long roleId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定id的角色信息，roleId={}", roleId);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (roleId == null) {
				LOGGER.error("获取给定id的角色信息失败，角色id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该角色不存在，请刷新页面重新请求!");
			} else {
				ExtendRoles eRoles = rolesService.getExtendRolesById(roleId);
				if (eRoles != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", eRoles);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该角色不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的角色信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存新的角色信息
	 * @param role
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午04:25:44
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewRole.do")
	public @ResponseBody Map<String, Object> addNewRole(Roles role, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("保存新的角色信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || role == null) {
				modelMap.put("status", "exception");
			} else {
				if (rolesService.addNewRole(role, eUser, modelMap)) {
					//此时更新成功，将改后的角色信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", "保存新的角色信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("保存新的角色信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 更新角色信息
	 * @param role
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午04:25:55
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeRole.do")
	public @ResponseBody Map<String, Object> udpateRole(Roles role, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新角色信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || role == null) {
				modelMap.put("status", "exception");
			} else {
				if (rolesService.updateRoleInfo(role, eUser, modelMap)) {
					//此时更新成功，将改后的角色信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", "更新角色信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新角色信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}

	/**
	 * 删除角色信息
	 * @param roleId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午06:07:22
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/removeRole.do")
	public @ResponseBody Map<String, Object> removeRoleInfo(
			@RequestParam(value = "roleId", required = true)Long roleId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除角色信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || roleId == null) {
				modelMap.put("status", "exception");
			} else {
				if (rolesService.removeRoleInfo(roleId, eUser, modelMap)) {
					//此时更新成功，将改后的角色信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", "删除角色信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除角色信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 获取角色权限的信息
	 * @param roleId
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 上午09:08:07
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showRoleAuth.do")
	public @ResponseBody Map<String, Object> getRoleInAuthInfo(
			@RequestParam(value = "roleId", required = true)Long roleId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取角色权限的信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || roleId == null) {
				modelMap.put("status", "exception");
			} else {
				List<Resources> resourcesList = rolesService.getAllResourcesByRole(roleId);
				if (resourcesList != null && resourcesList.size() > 0) {
					modelMap.put("status", "success");
					modelMap.put("data", resourcesList);
				} else {
					modelMap.put("status", "failure");
					modelMap.put("data", "获取角色的权限信息失败，请刷新页面后重试！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除角色信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 为角色分配权限
	 * @param roleId
	 * @param resourceIds
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 上午09:08:07
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/assignRoleAuth.do")
	public @ResponseBody Map<String, Object> assignAuthForRole(
			@RequestParam(value = "roleId", required = true)Long roleId, 
			@RequestParam(value = "resourceIds", required = true)String resourceIds, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新角色权限的信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || roleId == null || resourceIds == null) {
				modelMap.put("status", "exception");
			} else {
				if (rolesService.assignRoleAuth(roleId, eUser, resourceIds, modelMap)) {
					//更新权限成功，重新加载角色权限信息进入spring security框架
					systemConstant.setRoleResourceList();
					mySecurityMetadataSource.loadResourceDefine(true);
					modelMap.put("status", "success");
					modelMap.put("data", "权限分配成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新角色的权限信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	
}
