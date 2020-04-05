package com.sinosoft.security.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.security.po.UserRole;
import com.sinosoft.security.po.query.UserRoleQuery;

/**
 * @Package com.sinosoft.security.dao
 * @ClassName: UserRoleDao
 * @Description: 系统用户与系统角色的关联信息 Dao 接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-4 下午08:03:26
 */
public interface UserRoleDao {
	
	/**
	 * 根据检索条件获取符合条件的用户资源信息
	 * @param userRoleQuery
	 * @return List<UserRole>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-4 下午10:25:28
	 * @version V1.0
	 */
	public List<UserRole> getUserRoleByQuery(UserRoleQuery userRoleQuery);
	
	/**
	 * 根据检索条件获取符合条件的用户角色关联信息的数量
	 * @param userRoleQuery
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午03:23:00
	 * @version V1.0
	 */
	public Integer getUserRoleCountByQuery(UserRoleQuery userRoleQuery);
	
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
	 * 更新角色信息
	 * @author 张永斌
	 * @param userRole
	 * @return
	 */
	public Integer updateUserRole(UserRole userRole);
	
	/**
	 * 根据用户ID查询会员信息
	 * @param userId
	 * @return
	 */
	public Map selectVipInfoByUserId(Long userId);
	
	/**
	 * 根据会员等级查询价格信息
	 * @param roleId
	 * @return
	 */
	public Map getDerdgeVIPinfoByRoleId(String roleId);
	
	/**
	 * 修改支付前会员用户角色信息
	 * @param roleId
	 * @return
	 */
	public Integer updateUserRoleInfo(Map map);
	
	/**
	 * 根据用户ID查询会员信息
	 * @param userId
	 * @return
	 */
	public Map getroleInfoByUserId(Long userId);
	
	
	/**
	 * 根据用户ID查询会员支付信息条数
	 * @param userid
	 * @return
	 */
	public int getRolePayCountByUserId(Map map);
}
