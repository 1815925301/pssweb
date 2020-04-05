package com.sinosoft.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.extend.ExtendOrganization;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.OrganizationQuery;

/**
 * @Package com.sinosoft.security.service
 * @ClassName: OrganizationService
 * @Description: 机构信息 服务层接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-9 上午02:36:30
 */
public interface OrganizationService {
	
	/**
	 * 根据机构id获取机构信息
	 * @param orgId
	 * @return Organization
	 * @throws
	 * @author mrajian
	 * @date 2013-10-9 上午02:37:34
	 * @version V1.0
	 */
	public Organization getOrgInfoByOrgId(Long orgId);
	
	/**
	 * 根据机构id获取机构信息 扩展后的信息
	 * @param orgId
	 * @param modelMap
	 * @return ExtendOrganization
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午09:42:54
	 * @version V1.0
	 */
	public ExtendOrganization getExtendOrgInfoByOrgId(Long orgId);
	
	/**
	 * 根据机构名称取机构信息
	 * @param orgName
	 * @return Organization
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午01:54:05
	 * @version V1.0
	 */
	public Organization getOrgInfoByOrgName(String orgName);
	
	/**
	 * 根据机构id获取机构名称
	 * @param orgId
	 * @return String
	 * @throws
	 * @author mrajian
	 * @date 2013-10-9 上午02:50:14
	 * @version V1.0
	 */
	public String getOrgNameByOrgId(Long orgId);
	
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
	 * 根据查询参数获取机构信息 用于机构管理页面
	 * @param model
	 * @param method
	 * @param request
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-17 上午12:12:59
	 * @version V1.0
	 */
	public void getOrgInfoForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 新增机构信息
	 * @param organization
	 * @param eUser
	 * @param modelMap
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午01:26:32
	 * @version V1.0
	 */
	public boolean addNewOrganization(HttpServletRequest request, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据机构id修改机构信息
	 * @param organization
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 下午03:43:52
	 * @version V1.0
	 */
	public boolean updateOrganization(HttpServletRequest request,  ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据机构ID删除机构信息
	 * @param orgId
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-19 上午01:05:47
	 * @version V1.0
	 */
	public boolean removeOrganization(Long orgId, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 获取机构树
	 */
	public List getOrganizationTree(ActivityModelMap modelMap, HttpServletRequest request);	
	
	/**
	 * 获取子机构
	 */
	public List<Organization> getOrganizationList(ActivityModelMap modelMap, HttpServletRequest request);	
}
