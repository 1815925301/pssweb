package com.sinosoft.security.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.ResourcesDao;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.query.ResourcesQuery;

/**
 * @Package com.sinosoft.security.dao.impl
 * @ClassName: ResourcesDaoImpl
 * @Description: 系统资源信息的DAO 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-3 下午09:15:52
 */
@Repository(value="resourcesDao")
public class ResourcesDaoImpl extends BaseDao implements ResourcesDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesDaoImpl.class);
	
	@Override
	public List<Resources> getAllResources() {
		LOGGER.debug("Dao层：获取系统所有资源信息");
		return getReadSqlSession().selectList("resourcesDao.selectAllResources");
	}
	
	@Override
	public List<Resources> getResourceInfoByIdList(List<Long> idList) {
		if (idList == null) {
			LOGGER.error("Dao层：根据资源id集合获取系统资源信息，资源id集合参数为空");
			return null;
		}
		LOGGER.debug("Dao层：根据资源id集合获取系统资源信息");
		return getReadSqlSession().selectList("resourcesDao.selectResourceInfoByIdList", idList);
	}
	
	@Override
	public List<Resources> getNonRestrictedResources() {
		LOGGER.debug("Dao层：获取用户登录后默认可以访问的资源，不受角色限制");
		return getReadSqlSession().selectList("resourcesDao.selectNonRestrictedResources");
	}
	
	@Override
	public List<Resources> getResourcesByQuery(ResourcesQuery resourcesQuery) {
		LOGGER.debug("Dao层：获取所有符合条件的系统资源信息");
		return getReadSqlSession().selectList("resourcesDao.selectResourcesByQuery", resourcesQuery);
	}
	
	@Override
	public Resources getResourceInfoByResourceId(Long resourceId) {
		LOGGER.debug("Dao层：根据功能菜单id获取功能菜单信息");
		return (Resources)getReadSqlSession().selectOne("resourcesDao.selectResourceInfoByResourceId", resourceId);
	}
	
	@Override
	public List<Resources> getResourceInfoTree(Long moduleId) {
		LOGGER.debug("Dao层：获取功能菜单树");
		return getReadSqlSession().selectList("resourcesDao.selectResourceInfoTree",moduleId);
	}
	
	@Override
	public Resources getResourceInfoByResourceName(String resourceName) {
		LOGGER.debug("Dao层：根据功能菜单名称查询功能菜单信息");
		return (Resources)getReadSqlSession().selectOne("resourcesDao.selectResourceInfoByResourceName", resourceName);
	}
	
	@Override
	public Integer getResourceCountByResourceName(ResourcesQuery resourceQuery) {
		LOGGER.debug("Dao层：根据功能菜单名称与ID精确查找符合条件的功能菜单数量");
		return (Integer)getReadSqlSession().selectOne("resourcesDao.selectResourceCountByResourceName", resourceQuery);
	}
	
	@Override
	public Integer getResourceCountByQuery(ResourcesQuery resourceQuery) {
		LOGGER.debug("Dao层：根据检索条件获取功能菜单的数量");
		return (Integer)getReadSqlSession().selectOne("resourcesDao.selectResourceCountByQuery", resourceQuery);
	}
	
	@Override
	public List<Resources> getResourceInfoByQuery(ResourcesQuery resourceQuery) {
		LOGGER.debug("Dao层：根据检索条件获取功能菜单的记录信息");
		return getReadSqlSession().selectList("resourcesDao.selectResourceInfoByQuery", resourceQuery);
	}
	
	@Override
	public Integer addNewResources(Resources resources) {
		LOGGER.debug("Dao层：新增功能菜单信息");
		return (Integer)insert("resourcesDao.insertNewResources", resources);
	}
	
	@Override
	public Integer updateResourceInfoByResourceId(Resources resources) {
		LOGGER.debug("Dao层：根据功能菜单id更新功能菜单信息");
		return (Integer)update("resourcesDao.updateResourceInfoByResourceId", resources);
	}
	
	@Override
	public Integer deleteResourceInfoByResourceId(Long resourceId) {
		LOGGER.debug("Dao层：根据功能菜单id更新功能菜单信息");
		return (Integer)delete("resourcesDao.deleteResourceInfoByResourceId", resourceId);
	}

	@Override
	public List<Resources> selectResourceInfoByRoleId(Long roldId) {
		LOGGER.debug("Dao层：根据角色id查询菜单信息");
		return getReadSqlSession().selectList("resourcesDao.selectResourceInfoByRoleId", roldId);
	}

	
}
