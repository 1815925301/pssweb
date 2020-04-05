package com.sinosoft.security.service;

import java.util.List;

import com.sinosoft.security.po.RoleResource;

/**
 * @Package com.sinosoft.security.service
 * @ClassName: RoleResourceService
 * @Description: 系统角色与系统资源关联信息 服务层接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-4 下午08:27:18
 */
public interface RoleResourceService {
	
	/**
	 * 获取系统所有角色与资源关联信息
	 * @return List<RoleResource>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-4 下午08:31:52
	 * @version V1.0
	 */
	public List<RoleResource> getAllRoleResource();
	
	/**
	 * 根据角色id集合获取系统角色资源信息
	 * @param roleIdList
	 * @return List<RoleResource>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-14 上午09:26:29
	 * @version V1.0
	 */
	public List<RoleResource> getRoleResourceInfoByRoleIdList(List<Long> roleIdList);
	
	/**
	 * 根据角色id删除该角色对应的角色资源关系记录
	 * @param roleId
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午04:35:40
	 * @version V1.0
	 */
	public Integer deleteRoleResourceByRoleId(Long roleId);
	
	/**
	 * 添加新的角色资源关系记录
	 * @param roleResource
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午04:41:35
	 * @version V1.0
	 */
	public Integer addNewRoleResource(RoleResource roleResource);

}
