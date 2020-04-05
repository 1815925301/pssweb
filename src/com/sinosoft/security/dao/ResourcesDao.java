package com.sinosoft.security.dao;

import java.util.List;

import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.query.ResourcesQuery;

/**
 * @Package com.sinosoft.security.dao
 * @ClassName: ResourcesDao
 * @Description: 系统资源信息的DAO 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-3 下午06:44:09
 */
public interface ResourcesDao {
	
	/**
	 * 从数据库中系统所有资源信息
	 * @return List<Resources>
	 * @throws
	 * @author zzq
	 * @date 2013-10-3 下午09:51:17
	 * @version V1.0
	 */
	public List<Resources> getAllResources();
	
	/**
	 * 根据资源id集合从数据库中获取资源信息
	 * @param idList
	 * @return List<Resources>
	 * @throws
	 * @author zzq
	 * @date 2013-10-4 下午09:14:22
	 * @version V1.0
	 */
	public List<Resources> getResourceInfoByIdList(List<Long> idList);
	
	/**
	 * 获取用户登录后默认可以访问的资源，不受角色限制
	 * @return List<Resources>
	 * @throws
	 * @author zzq
	 * @date 2013-10-7 下午04:41:56
	 * @version V1.0
	 */
	public List<Resources> getNonRestrictedResources();
	
	/**
	 * 根据检索条件获取系统资源的信息 
	 * @param resourcesQuery
	 * @return List<Resources>
	 * @throws
	 * @author zzq
	 * @date 2013-10-26 下午08:51:49
	 * @version V1.0
	 */
	public List<Resources> getResourcesByQuery(ResourcesQuery resourcesQuery);
	
	/**
	 * 根据功能菜单id获取功能菜单信息
	 * @param detpId
	 * @return ResourcesDao
	 * @throws
	 * @author zzq
	 * @date 2013-10-9 上午02:27:21
	 * @version V1.0
	 */
	public Resources getResourceInfoByResourceId(Long detpId);
	
	/**
	 * 获取功能菜单树
	 * @param 
	 * @return List<Resources>
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午22:27:21
	 * @version V1.0
	 */
	public List<Resources> getResourceInfoTree(Long moduleId);

	
	/**
	 * 根据功能菜单名称查询功能菜单信息
	 * @param detpName
	 * @return Resources
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:47:40
	 * @version V1.0
	 */
	public Resources getResourceInfoByResourceName(String detpName);
	
	/**
	 * 根据功能菜单名称与ID精确查找符合条件的功能菜单数量
	 * @param detpQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午10:50:03
	 * @version V1.0
	 */
	public Integer getResourceCountByResourceName(ResourcesQuery detpQuery);
	
	/**
	 * 根据检索条件获取功能菜单的数量
	 * @param detpQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-16 下午11:36:03
	 * @version V1.0
	 */
	public Integer getResourceCountByQuery(ResourcesQuery detpQuery);
	
	/**
	 * 根据检索条件获取功能菜单的记录信息
	 * @param detpQuery
	 * @return List<Resources>
	 * @throws
	 * @author zzq
	 * @date 2013-10-16 下午11:35:36
	 * @version V1.0
	 */
	public List<Resources> getResourceInfoByQuery(ResourcesQuery detpQuery);
	
	/**
	 * 新增功能菜单信息
	 * @param resources
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:46:07
	 * @version V1.0
	 */
	public Integer addNewResources(Resources resources);
	
	/**
	 * 根据功能菜单id更新功能菜单信息
	 * @param resources
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:54:06
	 * @version V1.0
	 */
	public Integer updateResourceInfoByResourceId(Resources resources);
	
	/**
	 * 根据功能菜单id删除功能菜单信息 物理删除
	 * @param detpId
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:56:39
	 * @version V1.0
	 */
	public Integer deleteResourceInfoByResourceId(Long detpId);

	/**
	 * 根据角色查询菜单
	* @Title: selectResourceInfoByRoleId 
	* @Description: TODO 
	* @param @param roldId
	* @param @return    设定文件 
	* @return List<Resources>    返回类型 
	* @throws
	 */
	public List<Resources> selectResourceInfoByRoleId(Long roldId);
	
}
