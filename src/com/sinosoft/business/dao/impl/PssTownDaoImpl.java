/*
 * Powered By 尹力
 * Since 2015 - 2016-58-26
 */

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssTownDao;
import com.sinosoft.business.po.PssTown;
import com.sinosoft.business.po.query.PssTownQuery;



@Repository(value="PssTownDao")
public class PssTownDaoImpl extends BaseDao implements PssTownDao{
	
	@Override
	public int insertPssTown(PssTown pssTown){
		return insert("PssTownDao.insertPssTown",pssTown);
	}
	
	@Override
	public int deletePssTown(Long id){
		return delete("PssTownDao.deletePssTown",id);
	}
	
	@Override
	public  int updatePssTown(PssTown pssTown){
		return update("PssTownDao.updatePssTown",pssTown);
	}
	// 根据城市获得县列表
	@Override
	public List<PssTown> selectPssTownByCity(Long cityCode){
		return getReadSqlSession().selectList("PssTownDao.getPssTownByCity",cityCode);
	}
	
	@Override
	public Integer getCountByQuery(PssTownQuery pssTownQuery){
		return getReadSqlSession().selectOne("PssTownDao.getCountByQuery",pssTownQuery);
	}
	
	@Override
	public List<PssTown> getPssTownInfoByQuery(PssTownQuery pssTownQuery){
		return getReadSqlSession().selectList("PssTownDao.getPssTownInfoByQuery",pssTownQuery);
	}
	
	@Override
	public List<PssTown> getPssTownListByQuery(PssTownQuery pssTownQuery){
		return getReadSqlSession().selectList("PssTownDao.getPssTownListByQuery",pssTownQuery);
	}
}
