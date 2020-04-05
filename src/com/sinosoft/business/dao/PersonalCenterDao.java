package com.sinosoft.business.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.SelectCountsByOrderState;
import com.sinosoft.business.po.SelectOrderCount;
import com.sinosoft.business.po.query.PssCollectInfoQuery;
import com.sinosoft.business.po.query.PssOrderInfoQuery;

public interface PersonalCenterDao {
	

	/***
	 * 获得个人的采集单
	 * @param pssOrderInfoQuery
	 * @return
	 */
	public List<PssCollectInfoQuery> getSingleCollectionOrders(
			PssCollectInfoQuery pssCollectInfoQuery);

	
	/**
     * 查询数量
     */
     public Integer getCountByQuery(PssOrderInfoQuery orderInfoQuery);
     
     /**
      * 查询定制单数量
      */
      public Integer getCountByCollect(PssCollectInfoQuery pssCollectInfoQuery);
	/***
	 * 获得个人的定制单
	 * @param pssOrderInfoQuery
	 * @return
	 */
	public List<PssOrderInfoQuery> getCustomSingleOrder(
			PssOrderInfoQuery pssOrderInfoQuery);

	/***
	 * 获得个人的订购单
	 * @param pssOrderInfoQuery
	 * @return
	 */
	public List<PssOrderInfoQuery> getBuyOrders(
			PssOrderInfoQuery pssOrderInfoQuery);


	/***
	 * 获得订单数量
	 * @param pssOrderInfoQuery
	 * @return
	 */
	public SelectOrderCount getOrdersCounts(PssOrderInfoQuery pssOrderInfoQuery);
}
