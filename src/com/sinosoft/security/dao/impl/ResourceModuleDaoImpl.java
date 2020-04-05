package com.sinosoft.security.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.ResourceModuleDao;
import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.query.ResourceModuleQuery;

/**
 * @Package com.sinosoft.security.dao.impl
 * @ClassName: ResourceModuleDaoImpl
 * @Description: 系统资源模块信息的DAO 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-14 上午09:48:28
 */
@Repository(value="resourceModuleDao")
public class ResourceModuleDaoImpl extends BaseDao implements ResourceModuleDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceModuleDaoImpl.class);

	@Override
	public List<ResourceModule> getResourceModuleInfoByIdList(List<Long> idList) {
		if (idList == null) {
			LOGGER.error("Dao层：根据模块id集合获取资源模块信息，模块id集合参数为空");
			return null;
		}
		LOGGER.debug("DAO层：根据模块id集合获取资源模块信息");
		return getReadSqlSession().selectList("resourceModuleDao.selectResourceModuleInfoByIdList", idList);
	}

	@Override
	public List<ResourceModule> getResourceModuleInfo() {
		LOGGER.debug("Dao层：获取功能模块");
		return getReadSqlSession().selectList("resourceModuleDao.selectResourceModuleInfo");
	}
	
	@Override
	public ResourceModule getModuleInfoByModuleId(Long moduleId) {
		LOGGER.debug("Dao层：根据部门id获取部门信息");
		return (ResourceModule)getReadSqlSession().selectOne("resourceModuleDao.selectModuleInfoByModuleId", moduleId);
	}
	
	@Override
	public List<ResourceModule> getModuleInfoTree() {
		LOGGER.debug("Dao层：获取部门树");
		return getReadSqlSession().selectList("resourceModuleDao.selectModuleInfoTree");
	}
	
	@Override
	public ResourceModule getModuleInfoByModuleName(String moduleName) {
		LOGGER.debug("Dao层：根据部门名称查询部门信息");
		return (ResourceModule)getReadSqlSession().selectOne("resourceModuleDao.selectModuleInfoByModuleName", moduleName);
	}
	
	@Override
	public Integer getModuleCountByModuleName(ResourceModuleQuery moduleQuery) {
		LOGGER.debug("Dao层：根据部门名称与ID精确查找符合条件的部门数量");
		return (Integer)getReadSqlSession().selectOne("resourceModuleDao.selectModuleCountByModuleName", moduleQuery);
	}
	
	@Override
	public Integer getModuleCountByQuery(ResourceModuleQuery moduleQuery) {
		LOGGER.debug("Dao层：根据检索条件获取部门的数量");
		return (Integer)getReadSqlSession().selectOne("resourceModuleDao.selectModuleCountByQuery", moduleQuery);
	}
	
	@Override
	public List<ResourceModule> getModuleInfoByQuery(ResourceModuleQuery moduleQuery) {
		LOGGER.debug("Dao层：根据检索条件获取部门的记录信息");
		return getReadSqlSession().selectList("resourceModuleDao.selectModuleInfoByQuery", moduleQuery);
	}
	
	@Override
	public Integer addNewResourceModule(ResourceModule resourceModule) {
		LOGGER.debug("Dao层：新增部门信息");
		return (Integer)insert("resourceModuleDao.insertNewResourceModule", resourceModule);
	}
	
	@Override
	public Integer updateModuleInfoByModuleId(ResourceModule resourceModule) {
		LOGGER.debug("Dao层：根据部门id更新部门信息");
		return (Integer)update("resourceModuleDao.updateModuleInfoByModuleId", resourceModule);
	}
	
	@Override
	public Integer deleteModuleInfoByModuleId(Long moduleId) {
		LOGGER.debug("Dao层：根据部门id更新部门信息");
		return (Integer)delete("resourceModuleDao.deleteModuleInfoByModuleId", moduleId);
	}


}
