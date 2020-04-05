package com.sinosoft.security.dao;

import java.util.List;

import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.query.OrganizationQuery;

/**
 * @Package com.sinosoft.security.dao
 * @ClassName: OrganizationDao
 * @Description: 机构信息的DAO 接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-9 上午02:23:30
 */
public interface OrganizationDao {
	
	/**
	 * 根据机构id获取机构信息
	 * @param orgId
	 * @return OrganizationDao
	 * @throws
	 * @author mrajian
	 * @date 2013-10-9 上午02:27:21
	 * @version V1.0
	 */
	public Organization getOrgInfoByOrgId(Long orgId);
	
	/**
	 * 根据机构名称查询机构信息
	 * @param orgName
	 * @return Organization
	 * @throws
	 * @author mrajian
	 * @date 2013-10-17 下午06:47:40
	 * @version V1.0
	 */
	public Organization getOrgInfoByOrgName(String orgName);
	
	/**
	 * 根据机构名称与ID精确查找符合条件的机构数量
	 * @param orgQuery
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 下午10:50:03
	 * @version V1.0
	 */
	public Integer getOrgCountByOrgName(OrganizationQuery orgQuery);
	
	/**
	 * 根据检索条件获取机构的数量
	 * @param orgQuery
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-16 下午11:36:03
	 * @version V1.0
	 */
	public Integer getOrgCountByQuery(OrganizationQuery orgQuery);
	
	/**
	 * 根据检索条件获取机构的记录信息
	 * @param orgQuery
	 * @return List<Organization>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-16 下午11:35:36
	 * @version V1.0
	 */
	public List<Organization> getOrgInfoByQuery(OrganizationQuery orgQuery);
	
	/**
	 * 新增机构信息
	 * @param organization
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-17 下午06:46:07
	 * @version V1.0
	 */
	public Integer addNewOrganization(Organization organization);
	
	/**
	 * 根据机构id更新机构信息
	 * @param organization
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-17 下午06:54:06
	 * @version V1.0
	 */
	public Integer updateOrgInfoByOrgId(Organization organization);
	
	/**
	 * 根据机构id删除机构信息 物理删除
	 * @param orgId
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-17 下午06:56:39
	 * @version V1.0
	 */
	public Integer deleteOrgInfoByOrgId(Long orgId);
	
}
