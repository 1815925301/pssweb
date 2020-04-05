package com.sinosoft.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.po.RowSet;
import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.extend.ExtendResourceModule;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ResourceModuleQuery;

/**
 * @Package com.sinosoft.security.service
 * @ClassName: ResourceModuleService
 * @Description: 系统资源模块信息 服务层接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-14 上午10:02:52
 */
public interface ResourceModuleService {
	
	/**
	 * 根据模块id集合获取资源模块信息
	 * @param idList
	 * @return List<ResourceModule>
	 * @throws
	 * @author zzq
	 * @date 2013-10-14 上午10:03:52
	 * @version V1.0
	 */
	public List<ResourceModule> getResourceModuleInfoByIdList(List<Long> idList);
	
	/**
	 * 系统资源模块与模块下资源的信息
	 * @return List<ResourceModule>
	 * @throws
	 * @author zzq
	 * @date 2013-10-27 上午11:07:53
	 * @version V1.0
	 */
	public List<ResourceModule> getResourceModuleInfoWithResources();
	
	/**
	 * 获取功能模块
	 * @param modelMap
	 * @return ExtendResources
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午19:42:54
	 * @version V1.0
	 */
	public List<ResourceModule> getResourceModuleInfo();
	
	/**
	 * 根据模块id获取模块信息
	 * @param moduleId
	 * @return ResourceModule
	 * @throws
	 * @author zzq
	 * @date 2013-10-9 上午02:37:34
	 * @version V1.0
	 */
	public ResourceModule getModuleInfoByModuleId(Long moduleId);
	
	/**
	 * 根据模块id获取模块信息 扩展后的信息
	 * @param moduleId
	 * @param modelMap
	 * @return ExtendResourceModule
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 上午09:42:54
	 * @version V1.0
	 */
	public ExtendResourceModule getExtendModuleInfoByModuleId(Long moduleId);
	
	/**
	 * 获取模块树
	 * @param modelMap
	 * @return ExtendResourceModule
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午19:42:54
	 * @version V1.0
	 */
	public List<ResourceModule> getModuleInfoTree();
	
	/**
	 * 根据模块名称取模块信息
	 * @param moduleName
	 * @return ResourceModule
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 上午01:54:05
	 * @version V1.0
	 */
	public ResourceModule getModuleInfoByModuleName(String moduleName);
	
	/**
	 * 根据模块id获取模块名称
	 * @param moduleId
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-10-9 上午02:50:14
	 * @version V1.0
	 */
	public String getModuleNameByModuleId(Long moduleId);
	
	/**
	 * 根据检索条件获取模块的数量
	 * @param moduleQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-16 下午11:36:03
	 * @version V1.0
	 */
	public Integer getModuleCountByQuery(ResourceModuleQuery moduleQuery);
	
	/**
	 * 根据检索条件获取模块的记录信息
	 * @param moduleQuery
	 * @return List<ResourceModule>
	 * @throws
	 * @author zzq
	 * @date 2013-10-16 下午11:35:36
	 * @version V1.0
	 */
	public List<ResourceModule> getModuleInfoByQuery(ResourceModuleQuery moduleQuery);
	
	/**
	 * 根据查询参数获取模块信息 用于模块管理页面
	 * @param model
	 * @param method
	 * @param request
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 上午12:12:59
	 * @version V1.0
	 */
	public void getModuleInfoForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 新增模块信息
	 * @param ResourceModule
	 * @param eUser
	 * @param modelMap
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 上午01:26:32
	 * @version V1.0
	 */
	public boolean addNewResourceModule(ResourceModule resourceModule, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据模块id修改模块信息
	 * @param ResourceModule
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午03:43:52
	 * @version V1.0
	 */
	public boolean updateResourceModule(ResourceModule resourceModule, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据模块ID删除模块信息
	 * @param moduleId
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-19 上午01:05:47
	 * @version V1.0
	 */
	public boolean removeResourceModule(Long moduleId, ExtendUsers eUser, ActivityModelMap modelMap);
	
	public RowSet getModuleList(ResourceModuleQuery moduleQuery, ActivityModelMap modelMap, String pid);



}
