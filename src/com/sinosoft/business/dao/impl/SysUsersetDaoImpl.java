/*
 * Powered By 尹力
 * Since 2015 - 2016-13-25
 */

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.SysUsersetDao;
import com.sinosoft.business.po.SysUserset;
import com.sinosoft.business.po.query.SysUsersetQuery;

@Repository(value="SYS_USERSETDao")
public class SysUsersetDaoImpl extends BaseDao implements SysUsersetDao{
	
	@Override
	public int insertSysUserset(SysUserset SYS_USERSET){
		return insert("SYS_USERSETDao.insertSysUserset",SYS_USERSET);
	}
	
	@Override
	public int deleteSysUserset(Long id){
		return delete("SYS_USERSETDao.deleteSysUserset",id);
	}
	
	@Override
	public  int updateSysUserset(SysUserset SYS_USERSET){
		return update("SYS_USERSETDao.updateSysUserset",SYS_USERSET);
	}
	
	@Override
	public SysUserset selectSysUsersetById(Long id){
		return (SysUserset)getReadSqlSession().selectOne("SYS_USERSETDao.getSysUsersetById",id);
	}
	
	@Override
	public Integer getCountByQuery(SysUsersetQuery SYS_USERSETQuery){
		return getReadSqlSession().selectOne("SYS_USERSETDao.getCountByQuery",SYS_USERSETQuery);
	}
	
	@Override
	public List<SysUserset> getSysUsersetInfoByQuery(SysUsersetQuery SYS_USERSETQuery){
		return getReadSqlSession().selectList("SYS_USERSETDao.getSysUsersetInfoByQuery",SYS_USERSETQuery);
	}
	
	@Override
	public List<SysUserset> getSysUsersetListByQuery(SysUsersetQuery SYS_USERSETQuery){
		return getReadSqlSession().selectList("SYS_USERSETDao.getSysUsersetListByQuery",SYS_USERSETQuery);
	}

	@Override
	public List<SysUserset> selectallname() {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList("SYS_USERSETDao.selectAllname");
	}

	@Override
	public SysUserset getUserset() {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectOne("SYS_USERSETDao.getuserset");
	}
}
