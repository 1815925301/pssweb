package com.sinosoft.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sinosoft.security.dao.RoleResourceDao;
import com.sinosoft.security.po.RoleResource;
import com.sinosoft.security.service.RoleResourceService;

/**
 * @Package com.sinosoft.security.service.impl
 * @ClassName: RoleResourceServiceImpl
 * @Description: 系统角色与系统资源关联信息 服务层实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-4 下午08:28:07
 */
@Service("roleResourceService")
public class RoleResourceServiceImpl implements RoleResourceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleResourceServiceImpl.class);
	
	@Resource
	private RoleResourceDao roleResourceDao;
	
	@Override
	public List<RoleResource> getAllRoleResource() {
		LOGGER.debug("Service层：获取系统所有角色与资源关联信息");
		return roleResourceDao.getAllRoleResource();
	}
	
	@Override
	public List<RoleResource> getRoleResourceInfoByRoleIdList(List<Long> roleIdList) {
		LOGGER.debug("Service层：根据角色id集合获取系统角色资源信息");
		return roleResourceDao.getRoleResourceInfoByRoleIdList(roleIdList);
	}
	
	@Override
	public Integer deleteRoleResourceByRoleId(Long roleId) {
		LOGGER.debug("Service层：根据角色id删除该角色对应的角色资源关系记录");
		return roleResourceDao.deleteRoleResourceByRoleId(roleId);
	}
	
	@Override
	public Integer addNewRoleResource(RoleResource roleResource) {
		LOGGER.debug("Service层：添加新的角色资源关系记录");
		return roleResourceDao.addNewRoleResource(roleResource);
	}

}
