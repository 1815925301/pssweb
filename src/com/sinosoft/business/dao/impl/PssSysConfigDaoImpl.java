package com.sinosoft.business.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.SystemConfigDao;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.po.query.SystemConfigQuery;


@Repository(value="SystemConfigDao")
public class PssSysConfigDaoImpl extends BaseDao implements SystemConfigDao{
	/**
     *查询全部数据
     */
	@Override
	public List<SystemConfig> getSystemConfig(){
		return getReadSqlSession().selectList("SystemConfigDao.getSystemConfig");
	}
	
 	/**
	 * 增加某一条记录
	 */
	@Override
	public Integer saveSystemConfig(SystemConfig sysConfig) {
		return insert("SystemConfigDao.insertSystemConfig", sysConfig);
	}

    /**
	 * 删除某一条记录
	 */
	@Override
	public Integer removeSystemConfig(Long id){
		return delete("SystemConfigDao.deletePssSysConfig",id);
	}
	/**
	 * 修改方法
	 */
	@Override
	public  Integer updateSystemConfig(SystemConfig systemConfig){
		
		return update("SystemConfigDao.updatePssSysConfig", systemConfig);
	} 
	/**
	 * 通过一条id查询到详情
	 */
	@Override
	public  SystemConfig getSysConfigById(Long id){
		return getReadSqlSession().selectOne("SystemConfigDao.selectDataById",id);
	}
	
	/**
     *根据分页查询到一页显示的数据
     */
	@Override
	public List<SystemConfig> getSystemConfigList(int pageFirst, int pageSize,SystemConfig systemConfig){
		return getReadSqlSession().selectList("SystemConfigDao.selectSystemConfigQuery");
	}
	/**
     *新的框架 含有(条件查询)到一页显示的符合条件的数据
     */
	@Override
	public List<SystemConfig> getSystemConfigList(SystemConfigQuery systemConfigQuery){
		return getReadSqlSession().selectList("SystemConfigDao.getPssSysConfigListByQuery",systemConfigQuery);
	}
	
	/**
     *查询一共有多少条数据
     */
	@Override
	public Integer getPageSize(String sql){
		return getReadSqlSession().selectOne("SystemConfigDao.count",sql);
	}

	@Override
	public Integer getCountByQuery(SystemConfigQuery systemConfigQuery) {
		
		return getReadSqlSession().selectOne("SystemConfigDao.getPssSysConfigListByCondition",systemConfigQuery);
	}

	@Override
	public SystemConfig getSysConfigByName(String name) {
		return getReadSqlSession().selectOne("SystemConfigDao.getPssSysConfigListByname",name);
	}
	@Override
	public String getConfigValueByName(String configKey) {
		SystemConfig systemConfig = getReadSqlSession().selectOne("SystemConfigDao.getPssSysConfigListByname",configKey);
		String configValue = "";
		if(null != systemConfig){
			configValue = systemConfig.getConfigvalue();
		}
		return null != configValue ? configValue : "";
	}

	@Override
	public List getsystemtypeList() {
		return getReadSqlSession().selectList("SystemConfigDao.getsystemtypeList");
	}

	@Override
	public SystemConfig getAllDataByKeyDesc(SystemConfig systemConfig) {
		return getReadSqlSession().selectOne("SystemConfigDao.getAllDataByKeyDesc",systemConfig);
	}
	
}
