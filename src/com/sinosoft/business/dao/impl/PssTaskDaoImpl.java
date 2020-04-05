/*
 * Powered By 尹力
 * Since 2015 - 2016-45-13
 */

package com.sinosoft.business.dao.impl;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssTaskDao;
import com.sinosoft.business.po.PssTask;



@Repository(value="pssTaskDao")
public class PssTaskDaoImpl extends BaseDao implements PssTaskDao{
	
	@Override
	public int insertPssTask(PssTask PSS_TASK){
		return insert("PSS_TASKDao.insertPssTask",PSS_TASK);
	}
	
	@Override
	public int deletePssTask(Long id){
		return delete("PSS_TASKDao.deletePssTask",id);
	}
	
	@Override
	public  int updatePssTask(PssTask PSS_TASK){
		return update("PSS_TASKDao.updatePssTask",PSS_TASK);
	}
	
	@Override
	public PssTask selectPssTaskById(Long id){
		return (PssTask)getReadSqlSession().selectOne("PSS_TASKDao.getPssTaskById",id);
	}
	
	
}
