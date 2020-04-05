package com.sinosoft.security.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.RoleResourceDao;
import com.sinosoft.security.po.RoleResource;

/**
 * @Package com.sinosoft.security.dao.impl
 * @ClassName: RoleResourceDaoImpl
 * @Description: 系统角色与系统资源关联信息 Dao 实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-4 下午08:07:00
 */
@Repository(value="roleResourceDao")
public class RoleResourceDaoImpl extends BaseDao implements RoleResourceDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleResourceDaoImpl.class);
	
	@Override
	public List<RoleResource> getAllRoleResource() {
		LOGGER.debug("Dao层：获取系统所有角色与资源关联信息");
		return getReadSqlSession().selectList("roleResourceDao.selectAllRoleResource");
	}
	
	@Override
	public List<RoleResource> getRoleResourceInfoByRoleIdList(List<Long> roleIdList) {
		LOGGER.debug("Dao层：根据角色id集合获取系统角色资源信息");
		return getReadSqlSession().selectList("roleResourceDao.selectRoleResourceInfoByRoleIdList", roleIdList);
	}
	
	@Override
	public Integer deleteRoleResourceByRoleId(Long roleId) {
		LOGGER.debug("Dao层：根据角色id删除该角色对应的角色资源关系记录");
		return delete("roleResourceDao.deleteRoleResourceByRoleId", roleId);
	}
	
	@Override
	public Integer addNewRoleResource(RoleResource roleResource) {
		LOGGER.debug("Dao层：添加新的角色资源关系记录");
		return delete("roleResourceDao.insertNewRoleResource", roleResource);
	}

}
