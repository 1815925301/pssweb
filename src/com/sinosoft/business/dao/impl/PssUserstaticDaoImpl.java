

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssUserstaticDao;
import com.sinosoft.business.po.PssUserstatic;
import com.sinosoft.business.po.query.PssUserstaticQuery;



@Repository(value="PSS_USERSTATICDao")
public class PssUserstaticDaoImpl extends BaseDao implements PssUserstaticDao{
	
	@Override
	public int insertPssUserstatic(PssUserstatic PSS_USERSTATIC){
		return insert("PSS_USERSTATICDao.insertPssUserstatic",PSS_USERSTATIC);
	}
	
	@Override
	public int deletePssUserstatic(Long id){
		return delete("PSS_USERSTATICDao.deletePssUserstatic",id);
	}
	
	@Override
	public  int updatePssUserstatic(PssUserstatic PSS_USERSTATIC){
		return update("PSS_USERSTATICDao.updatePssUserstatic",PSS_USERSTATIC);
	}
	
	@Override
	public PssUserstatic selectPssUserstaticById(Long id){
		return (PssUserstatic)getReadSqlSession().selectOne("PSS_USERSTATICDao.getPssUserstaticById",id);
	}
	
	@Override
	public Integer getCountByQuery(PssUserstaticQuery PSS_USERSTATICQuery){
		return getReadSqlSession().selectOne("PSS_USERSTATICDao.getCountByQuery",PSS_USERSTATICQuery);
	}
	
	@Override
	public List<PssUserstatic> getPssUserstaticInfoByQuery(PssUserstaticQuery PSS_USERSTATICQuery){
		return getReadSqlSession().selectList("PSS_USERSTATICDao.getPssUserstaticInfoByQuery",PSS_USERSTATICQuery);
	}
	
	@Override
	public List<PssUserstatic> getPssUserstaticListByQuery(PssUserstaticQuery PSS_USERSTATICQuery){
		return getReadSqlSession().selectList("PSS_USERSTATICDao.getPssUserstaticListByQuery",PSS_USERSTATICQuery);
	}
}
