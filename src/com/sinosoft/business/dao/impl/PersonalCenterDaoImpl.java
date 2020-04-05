package com.sinosoft.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PersonalCenterDao;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.SelectCountsByOrderState;
import com.sinosoft.business.po.SelectOrderCount;
import com.sinosoft.business.po.query.PssCollectInfoQuery;
import com.sinosoft.business.po.query.PssOrderInfoQuery;
@Repository(value="PersonalCenterDao")
public class PersonalCenterDaoImpl extends BaseDao implements PersonalCenterDao{

	
	@Override
	public List<PssCollectInfoQuery> getSingleCollectionOrders(
			PssCollectInfoQuery pssCollectInfoQuery) {
		return getReadSqlSession().selectList("PssCollectInfoDao.getPssCollectInfoInfoByQuery",pssCollectInfoQuery);
	}
	
	
	
	@Override
	public Integer getCountByCollect(PssCollectInfoQuery pssCollectInfoQuery){
		return getReadSqlSession().selectOne("PssCollectInfoDao.getCountByQuery",pssCollectInfoQuery);
	}
	
	
	@Override
	public Integer getCountByQuery(PssOrderInfoQuery orderInfoQuery){
		return getReadSqlSession().selectOne("PssOrderInfoDao.getCountByQuery",orderInfoQuery);
	}
	
	@Override
	public List<PssOrderInfoQuery> getCustomSingleOrder(
			PssOrderInfoQuery pssOrderInfoQuery) {
		return getReadSqlSession().selectList("PssOrderInfoDao.getCustomSingleOrder",pssOrderInfoQuery);
		
	}

	@Override
	public List<PssOrderInfoQuery> getBuyOrders(
			PssOrderInfoQuery pssOrderInfoQuery) {
		return getReadSqlSession().selectList("PssOrderInfoDao.getBuyOrders",pssOrderInfoQuery);
	}
	@Override
	public SelectOrderCount getOrdersCounts(PssOrderInfoQuery pssOrderInfoQuery) {
		return getReadSqlSession().selectOne("PssOrderInfoDao.getOrdersCounts",pssOrderInfoQuery);
	}

}
