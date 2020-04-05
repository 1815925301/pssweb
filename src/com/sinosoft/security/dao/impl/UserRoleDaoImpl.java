package com.sinosoft.security.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.UserRoleDao;
import com.sinosoft.security.po.UserRole;
import com.sinosoft.security.po.query.UserRoleQuery;

/**
 * @Package com.sinosoft.security.dao.impl
 * @ClassName: UserRoleDaoImpl
 * @Description: 系统用户与系统角色的关联信息 Dao 实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-4 下午08:04:18
 */
@Repository(value="userRoleDao")
public class UserRoleDaoImpl extends BaseDao implements UserRoleDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleDaoImpl.class);
	
	@Override
	public List<UserRole> getUserRoleByQuery(UserRoleQuery userRoleQuery) {
		LOGGER.debug("DAO层：根据检索条件获取符合条件的用户资源信息");
		return getReadSqlSession().selectList("userRoleDao.selectUserRoleByQuery", userRoleQuery);
	}
	
	@Override
	public Integer getUserRoleCountByQuery(UserRoleQuery userRoleQuery) {
		LOGGER.debug("DAO层：根据检索条件获取符合条件的用户角色关联信息的数量");
		return (Integer)getReadSqlSession().selectOne("userRoleDao.selectUserRoleCountByQuery", userRoleQuery);
	}

	public Integer deleteUserRoleByUserId(Long userId) {
		LOGGER.debug("DAO层：删除给定用户的用户角色关联关系: userId={}", userId);
		return (Integer)delete("userRoleDao.deleteUserRoleByUserId", userId);
	}
	
	@Override
	public Integer addNewUserRole(UserRole userRole) {
		LOGGER.debug("DAO层：保存新的用户角色关系信息");
		return (Integer)insert("userRoleDao.inserNewUserRole", userRole);
	}

	@Override
	public Integer updateUserRole(UserRole userRole) {
		LOGGER.debug("DAO层：更新用户角色关系信息");
		return (Integer)update("userRoleDao.updateUserRole", userRole);
	}

	@Override
	public Map selectVipInfoByUserId(Long userId) {
		LOGGER.debug("DAO层：根据用户ID查询会员信息");
		return getReadSqlSession().selectOne("userRoleDao.selectVipInfoByUserId", userId);
	}

	@Override
	public Map getDerdgeVIPinfoByRoleId(String roleId) {
		LOGGER.debug("DAO层：根据会员等级查询价格信息");
		return getReadSqlSession().selectOne("userRoleDao.getDerdgeVIPinfoByRoleId", roleId);
	}

	@Override
	public Integer updateUserRoleInfo(Map map) {
		LOGGER.debug("DAO层：修改支付前会员用户角色信息");
		return update("userRoleDao.updateBeforeUserRoleInfo", map);
	}

	@Override
	public Map getroleInfoByUserId(Long userId) {
		LOGGER.debug("DAO层：根据用户ID查询会员信息");
		return getReadSqlSession().selectOne("userRoleDao.getroleInfoByUserId", userId);
	}
	
	@Override
	public int getRolePayCountByUserId(Map map) {
		LOGGER.debug("DAO层：根据用户ID查询会员信息");
		return getReadSqlSession().selectOne("userRoleDao.getRolePayCountByUserId", map);
	}
}
