/*
 * Powered By 尹力
 * Since 2015 - 2016-22-31
 */

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssVersionnumDao;
import com.sinosoft.business.po.PssVersionnum;
import com.sinosoft.business.po.query.PssVersionnumQuery;



@Repository(value="versionnumDao")
public class PssVersionnumDaoImpl extends BaseDao implements PssVersionnumDao{
	
	@Override
	public int insertPssVersionnum(PssVersionnum PSS_VERSIONNUM){
		return insert("PSS_VERSIONNUMDao.insertPssVersionnum",PSS_VERSIONNUM);
	}
	
	@Override
	public int deletePssVersionnum(Long id){
		return delete("PSS_VERSIONNUMDao.deletePssVersionnum",id);
	}
	
	@Override
	public  int updatePssVersionnum(PssVersionnum PSS_VERSIONNUM){
		return update("PSS_VERSIONNUMDao.updatePssVersionnum",PSS_VERSIONNUM);
	}
	
	@Override
	public PssVersionnum selectPssVersionnumById(Long id){
		return (PssVersionnum)getReadSqlSession().selectOne("PSS_VERSIONNUMDao.getPssVersionnumById",id);
	}
	
	@Override
	public Integer getCountByQuery(PssVersionnumQuery PSS_VERSIONNUMQuery){
		return getReadSqlSession().selectOne("PSS_VERSIONNUMDao.getCountByQuery",PSS_VERSIONNUMQuery);
	}
	
	@Override
	public List<PssVersionnum> getPssVersionnumInfoByQuery(PssVersionnumQuery PSS_VERSIONNUMQuery){
		return getReadSqlSession().selectList("PSS_VERSIONNUMDao.getPssVersionnumInfoByQuery",PSS_VERSIONNUMQuery);
	}
	
	@Override
	public List<PssVersionnum> getPssVersionnumListByQuery(PssVersionnumQuery PSS_VERSIONNUMQuery){
		return getReadSqlSession().selectList("PSS_VERSIONNUMDao.getPssVersionnumListByQuery",PSS_VERSIONNUMQuery);
	}
}
