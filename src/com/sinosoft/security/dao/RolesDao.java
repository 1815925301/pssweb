package com.sinosoft.security.dao;

import java.util.List;

import com.sinosoft.security.po.Roles;
import com.sinosoft.security.po.query.RolesQuery;

/**
 * @Package com.sinosoft.security.dao
 * @ClassName: RolesDao
 * @Description: 系统角色信息的DAO 接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-3 下午06:43:17
 */
public interface RolesDao {
	
	/**
	 * 从数据库中获取所有的系统角色信息
	 * @return List<Roles>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-4 下午04:37:29
	 * @version V1.0
	 */
	public List<Roles> getAllRoles();
	
	/**
	 * 根据检索条件获取符合条件的角色数量
	 * @param rolesQuery
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午03:11:30
	 * @version V1.0
	 */
	public Integer getRolesCountByQuery(RolesQuery rolesQuery);
	
	/**
	 * 根据检索条件获取符合条件的角色信息
	 * @param rolesQuery
	 * @return List<Roles>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午03:12:20
	 * @version V1.0
	 */
	public List<Roles> getRolesInfoByQuery(RolesQuery rolesQuery);
	
	/**
	 * 根据角色id获取角色信息
	 * @param roleId
	 * @return Roles
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午04:36:59
	 * @version V1.0
	 */
	public Roles getRoleInfoById(Long roleId);
	
	/**
	 * 添加新角色
	 * @param role
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午04:59:53
	 * @version V1.0
	 */
	public Integer addNewRole(Roles role);

	/**
	 * 根据角色id修改角色信息
	 * @param role
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午05:01:45
	 * @version V1.0
	 */
	public Integer updateRoleInfo(Roles role);
	
	/**
	 * 根据角色名称和id精确查找符合条件的角色数量
	 * @param rolesQuery
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午05:43:51
	 * @version V1.0
	 */
	public Integer getRoleCountByName(RolesQuery rolesQuery);
	
	/**
	 * 根据角色id删除角色信息
	 * @param roleId
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午06:05:12
	 * @version V1.0
	 */
	public Integer deleteRoleById(Long roleId);
	
	/**
	 * 根据query查询list
	 * @author 张永斌
	 * @param rolesQuery
	 * @return
	 */
	public List<Roles> getRolesListByQuery(RolesQuery rolesQuery);

}
