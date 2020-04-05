package com.sinosoft.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.UserRole;

/**
 * @Package com.sinosoft.security.service
 * @ClassName: UserRoleService
 * @Description: 系统用户与角色关联信息 服务层接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-4 下午08:32:36
 */
public interface UserRoleService {
	
	/**
	 * 根据用户id获取用户的角色信息
	 * @param userId
	 * @return List<UserRole>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-4 下午10:23:14
	 * @version V1.0
	 */
	public List<UserRole> getRoleIdListByUserId(Long userId);
	
	/**
	 * 根据检索条件获取符合条件的用户角色关联信息的数量
	 * @param roleId
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午03:25:50
	 * @version V1.0
	 */
	public Integer getUserRoleCountByRoleId(Long roleId);

	/**
	 * 删除给定用户的用户角色关联关系
	 * @param userId
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午08:20:23
	 * @version V1.0
	 */
	public Integer deleteUserRoleByUserId(Long userId);
	
	/**
	 * 保存新的用户角色关系信息
	 * @param userRole
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午08:21:42
	 * @version V1.0
	 */
	public Integer addNewUserRole(UserRole userRole);
	
	/**
	 * 修改支付前的用户角色信息
	 * @param modelMap
	 * @param method
	 * @param request
	 */
	public void updateBeforeUserRoleInfo(ActivityModelMap modelMap, String method, HttpServletRequest request);
	
	public int getRolePayCountByUserId(String userid);
}
