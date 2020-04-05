package com.sinosoft.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.Roles;
import com.sinosoft.security.po.extend.ExtendRoles;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.extend.TreeNode;

/**
 * @Package com.sinosoft.security.service
 * @ClassName: RolesService
 * @Description: 系统角色信息 服务层接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-3 下午09:19:55
 */
public interface RolesService {
	
	/**
	 * 获取所有的系统角色信息
	 * @return List<Roles>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-4 下午04:37:29
	 * @version V1.0
	 */
	public List<Roles> getAllRoles();
	
	/**
	 * 根据查询参数获取角色信息 用于角色管理页面
	 * @param model
	 * @param method
	 * @param request
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午02:41:46
	 * @version V1.0
	 */
	public void getRolesInfoForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 根据角色id获取角色信息 扩展信息
	 * @param roleId
	 * @return ExtendRoles
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午04:26:34
	 * @version V1.0
	 */
	public ExtendRoles getExtendRolesById(Long roleId);
	
	/**
	 * 添加新角色
	 * @param role
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午04:59:53
	 * @version V1.0
	 */
	public boolean addNewRole(Roles role, ExtendUsers eUser, ActivityModelMap modelMap);

	/**
	 * 根据角色id修改角色信息
	 * @param role
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午05:01:45
	 * @version V1.0
	 */
	public boolean updateRoleInfo(Roles role, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据角色id删除角色信息
	 * @param roleId
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午06:02:47
	 * @version V1.0
	 */
	public boolean removeRoleInfo(Long roleId, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据角色id获取角色的权限信息
	 * @param roleId
	 * @return List<ResourceModule>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午09:06:15
	 * @version V1.0
	 */
	public List<Resources> getAllResourcesByRole(Long roleId);
	
	/**
	 * 为角色分配权限
	 * @param roleId
	 * @param eUser
	 * @param resourceIds
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午02:33:54
	 * @version V1.0
	 */
	public boolean assignRoleAuth(Long roleId, ExtendUsers eUser, String resourceIds, ActivityModelMap modelMap);
}
