/*
 * Powered By 尹力
 * Since 2015 - 2016-53-18
 */

package com.sinosoft.business.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssCollectInfoDao;
import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.business.po.query.PssCollectInfoQuery;
import com.sinosoft.business.uilt.GetTaskId;


@Repository(value="PssCollectInfoDao")
public class PssCollectInfoDaoImpl extends BaseDao implements PssCollectInfoDao{
	
	@Override
	public int insertPssCollectInfo(PssCollectInfo pssCollectInfo){
		return insert("PssCollectInfoDao.insertPssCollectInfo",pssCollectInfo);
	}
	
	@Override
	public int deletePssCollectInfo(Long id){
		return delete("PssCollectInfoDao.deletePssCollectInfo",id);
	}
	
	@Override
	public  int updatePssCollectInfo(PssCollectInfo pssCollectInfo){
		return update("PssCollectInfoDao.updatePssCollectInfo",pssCollectInfo);
	}
	
	@Override
	public PssCollectInfo selectPssCollectInfoById(Long id){
		return getReadSqlSession().selectOne("PssCollectInfoDao.getPssCollectInfoById",id);
	}
	
	@Override
	public Integer getCountByQuery(PssCollectInfoQuery pssCollectInfoQuery){
		return getReadSqlSession().selectOne("PssCollectInfoDao.getCountByQuery",pssCollectInfoQuery);
	}
	
	@Override
	public List<PssCollectInfo> getPssCollectInfoInfoByQuery(PssCollectInfoQuery pssCollectInfoQuery){
		return getReadSqlSession().selectList("PssCollectInfoDao.getPssCollectInfoInfoByQuery",pssCollectInfoQuery);
	}
	
	@Override
	public List<PssCollectInfo> getPssCollectInfoListByQuery(PssCollectInfoQuery pssCollectInfoQuery){
		return getReadSqlSession().selectList("PssCollectInfoDao.getPssCollectInfoListByQuery",pssCollectInfoQuery);
	}

	@Override
	public List getSatelliteList() {
		// TODO Auto-generated method stub
		return  getReadSqlSession().selectList("PssCollectInfoDao.getSatelliteList");
	}

	@Override
	public List getStatusList() {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList("PssCollectInfoDao.getStatusList");
	}

	@Override
	public List getSensoridList() {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList("PssCollectInfoDao.getSensoridList");
	}

	@Override
	public PssCollectInfo getCollectInfoBytaskid(Long taskid) {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectOne("PssCollectInfoDao.getCollectByid",taskid);
	}
}
