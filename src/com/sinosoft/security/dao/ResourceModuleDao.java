package com.sinosoft.security.dao;

import java.util.List;

import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.query.ResourceModuleQuery;

/**
 * @Package com.sinosoft.security.dao
 * @ClassName: ResourceModuleDao
 * @Description: 系统资源模块信息的DAO 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-14 上午09:41:14
 */
public interface ResourceModuleDao {
	
	/**
	 * 根据模块id集合获取资源模块信息
	 * @param idList
	 * @return List<ResourceModule>
	 * @throws
	 * @author zzq
	 * @date 2013-10-14 上午09:42:47
	 * @version V1.0
	 */
	public List<ResourceModule> getResourceModuleInfoByIdList(List<Long> idList);
	
	/**
	 * 获取功能模块
	 * @param 
	 * @return List<Resources>
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午22:27:21
	 * @version V1.0
	 */
	public List<ResourceModule> getResourceModuleInfo();
	
	/**
	 * 根据模块id获取模块信息
	 * @param moduleId
	 * @return ResourceModuleDao
	 * @throws
	 * @author zzq
	 * @date 2013-10-9 上午02:27:21
	 * @version V1.0
	 */
	public ResourceModule getModuleInfoByModuleId(Long moduleId);
	
	/**
	 * 获取模块树
	 * @param 
	 * @return List<ResourceModule>
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午22:27:21
	 * @version V1.0
	 */
	public List<ResourceModule> getModuleInfoTree();

	
	/**
	 * 根据模块名称查询模块信息
	 * @param moduleName
	 * @return ResourceModule
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:47:40
	 * @version V1.0
	 */
	public ResourceModule getModuleInfoByModuleName(String moduleName);
	
	/**
	 * 根据模块名称与ID精确查找符合条件的模块数量
	 * @param moduleQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午10:50:03
	 * @version V1.0
	 */
	public Integer getModuleCountByModuleName(ResourceModuleQuery moduleQuery);
	
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
	 * 新增模块信息
	 * @param resourceModule
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:46:07
	 * @version V1.0
	 */
	public Integer addNewResourceModule(ResourceModule resourceModule);
	
	/**
	 * 根据模块id更新模块信息
	 * @param resourceModule
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:54:06
	 * @version V1.0
	 */
	public Integer updateModuleInfoByModuleId(ResourceModule resourceModule);
	
	/**
	 * 根据模块id删除模块信息 物理删除
	 * @param moduleId
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:56:39
	 * @version V1.0
	 */
	public Integer deleteModuleInfoByModuleId(Long moduleId);


}
