package com.sinosoft.security.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.OrganizationDao;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.query.OrganizationQuery;

/**
 * @Package com.sinosoft.security.dao.impl
 * @ClassName: OrganizationDaoImpl
 * @Description: 机构信息的DAO 实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-9 上午02:24:17
 */
@Repository(value="organizationDao")
public class OrganizationDaoImpl extends BaseDao implements OrganizationDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationDaoImpl.class);
	
	@Override
	public Organization getOrgInfoByOrgId(Long orgId) {
		LOGGER.debug("Dao层：根据机构id获取机构信息");
		return (Organization)getReadSqlSession().selectOne("organizationDao.selectOrgInfoByOrgId", orgId);
	}
	
	@Override
	public Organization getOrgInfoByOrgName(String orgName) {
		LOGGER.debug("Dao层：根据机构名称查询机构信息");
		return (Organization)getReadSqlSession().selectOne("organizationDao.selectOrgInfoByOrgName", orgName);
	}
	
	@Override
	public Integer getOrgCountByOrgName(OrganizationQuery orgQuery) {
		LOGGER.debug("Dao层：根据机构名称与ID精确查找符合条件的机构数量");
		return (Integer)getReadSqlSession().selectOne("organizationDao.selectOrgCountByOrgName", orgQuery);
	}
	
	@Override
	public Integer getOrgCountByQuery(OrganizationQuery orgQuery) {
		LOGGER.debug("Dao层：根据检索条件获取机构的数量");
		return (Integer)getReadSqlSession().selectOne("organizationDao.selectOrgCountByQuery", orgQuery);
	}
	
	@Override
	public List<Organization> getOrgInfoByQuery(OrganizationQuery orgQuery) {
		LOGGER.debug("Dao层：根据检索条件获取机构的记录信息");
		return getReadSqlSession().selectList("organizationDao.selectOrgInfoByQuery", orgQuery);
	}
	
	@Override
	public Integer addNewOrganization(Organization organization) {
		LOGGER.debug("Dao层：新增机构信息");
		return getReadSqlSession().insert("organizationDao.insertNewOrganization", organization);
	}
	
	@Override
	public Integer updateOrgInfoByOrgId(Organization organization) {
		LOGGER.debug("Dao层：根据机构id更新机构信息");
		return getReadSqlSession().update("organizationDao.updateOrgInfoByOrgId", organization);
	}
	
	@Override
	public Integer deleteOrgInfoByOrgId(Long orgId) {
		LOGGER.debug("Dao层：根据机构id更新机构信息");
		return getReadSqlSession().delete("organizationDao.deleteOrgInfoByOrgId", orgId);
	}

}
