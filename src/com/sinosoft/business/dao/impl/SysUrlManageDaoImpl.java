/*
 * Powered By J.C
 * Since  2016-08-11
 */

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.SysUrlManageDao;
import com.sinosoft.business.po.SysUrlManage;
import com.sinosoft.business.po.query.SysUrlManageQuery;



@Repository(value="SysUrlManageDao")
public class SysUrlManageDaoImpl extends BaseDao implements SysUrlManageDao{
	
	@Override
	public int insertSysUrlManage(SysUrlManage sysUrlManage){
		return insert("SysUrlManageDao.insertSysUrlManage",sysUrlManage);
	}
	
	@Override
	public int deleteSysUrlManage(Long id){
		return delete("SysUrlManageDao.deleteSysUrlManage",id);
	}
	
	@Override
	public  int updateSysUrlManage(SysUrlManage sysUrlManage){
		return update("SysUrlManageDao.updateSysUrlManage",sysUrlManage);
	}
	
	@Override
	public SysUrlManage selectSysUrlManageById(Long id){
		return (SysUrlManage)getReadSqlSession().selectOne("SysUrlManageDao.getSysUrlManageById",id);
	}
	
	@Override
	public Integer getCountByQuery(SysUrlManageQuery sysUrlManage){
		return getReadSqlSession().selectOne("SysUrlManageDao.getCountByQuery",sysUrlManage);
	}
	
	@Override
	public List<SysUrlManage> getSysUrlManageInfoByQuery(SysUrlManageQuery sysUrlManageQuery){
		return getReadSqlSession().selectList("SysUrlManageDao.getSysUrlManageInfoByQuery",sysUrlManageQuery);
	}
	
	@Override
	public List<SysUrlManage> getSysUrlManageListByQuery(SysUrlManageQuery sysUrlManageQuery){
		return getReadSqlSession().selectList("SysUrlManageDao.getSysUrlManageListByQuery",sysUrlManageQuery);
	}
}
