package com.sinosoft.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.po.RowSet;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.extend.ExtendResources;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ResourcesQuery;

/**
 * @Package com.sinosoft.security.service
 * @ClassName: ResourcesService
 * @Description: 系统资源信息 服务层接口类
 * @author zhaomin
 * @Version V1.0
 * @date 2013-10-3 下午09:21:48
 */
public interface ResourcesService {
	
	/**
	 * 获取系统所有资源信息
	 * @param 
	 * @return List<Resources>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-3 下午09:49:18
	 * @version V1.0
	 */
	public List<Resources> getAllResources();
	
	/**
	 * 根据资源id集合获取系统资源信息
	 * @param id
	 * @return Resources
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-4 下午05:02:00
	 * @version V1.0
	 */
	public List<Resources> getResourceInfoByIdList(List<Long> idList);
	
	/**
	 * 获取用户登录后默认可以访问的资源，不受角色限制
	 * @return List<Resources>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-7 下午04:46:54
	 * @version V1.0
	 */
	public List<Resources> getNonRestrictedResources();
	
	/**
	 * 获取所有用于生成菜单的资源的信息
	 * @return List<Resources>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-26 下午08:53:31
	 * @version V1.0
	 */
	public List<Resources> getAllMenuResources();
	
	/**
	 * 根据功能id获取功能信息
	 * @param resourceId
	 * @return Resources
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-9 上午02:37:34
	 * @version V1.0
	 */
	public Resources getResourceInfoByResourceId(Long resourceId);
	
	/**
	 * 根据模块Id获取模块数据
	 * @param resourceId
	 * @return Resources
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-9 上午02:37:34
	 * @version V1.0
	 */
	public RowSet getResourceListByModuleId(Long moduleId,ActivityModelMap modelMap);
	
	/**
	 * 根据功能id获取功能信息 扩展后的信息
	 * @param resourceId
	 * @param modelMap
	 * @return ExtendResources
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 上午09:42:54
	 * @version V1.0
	 */
	public ExtendResources getExtendResourceInfoByResourceId(Long resourceId);
	
	/**
	 * 获取功能树
	 * @param modelMap
	 * @return ExtendResources
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午19:42:54
	 * @version V1.0
	 */
	public List<Resources> getResourceInfoTree(Long moduleId);
	
	/**
	 * 根据功能名称取功能信息
	 * @param resourceName
	 * @return Resources
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 上午01:54:05
	 * @version V1.0
	 */
	public Resources getResourceInfoByResourceName(String resourceName);
	
	/**
	 * 根据功能id获取功能名称
	 * @param resourceId
	 * @return String
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-9 上午02:50:14
	 * @version V1.0
	 */
	public String getResourceNameByResourceId(Long resourceId);
	
	/**
	 * 根据检索条件获取功能的数量
	 * @param resourceQuery
	 * @return Integer
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-16 下午11:36:03
	 * @version V1.0
	 */
	public Integer getResourceCountByQuery(ResourcesQuery resourceQuery);
	
	/**
	 * 根据检索条件获取功能的记录信息
	 * @param resourceQuery
	 * @return List<Resources>
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-16 下午11:35:36
	 * @version V1.0
	 */
	public List<Resources> getResourceInfoByQuery(ResourcesQuery resourceQuery);
	
	/**
	 * 根据查询参数获取功能信息 用于功能管理页面
	 * @param model
	 * @param method
	 * @param request
	 * @return void
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-17 上午12:12:59
	 * @version V1.0
	 */
	public void getResourceInfoForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 新增功能信息
	 * @param Resources
	 * @param eUser
	 * @param modelMap
	 * @return void
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 上午01:26:32
	 * @version V1.0
	 */
	public boolean addNewResources(Resources department, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据功能id修改功能信息
	 * @param Resources
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-18 下午03:43:52
	 * @version V1.0
	 */
	public boolean updateResources(Resources department, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据功能ID删除功能信息
	 * @param resourceId
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zhaomin
	 * @date 2013-10-19 上午01:05:47
	 * @version V1.0
	 */
	public boolean removeResources(Long resourceId, ExtendUsers eUser, ActivityModelMap modelMap);
	
	public RowSet getResourceList(ResourcesQuery resourceQuery, ActivityModelMap modelMap, Long pid, Long moduleId);


}
