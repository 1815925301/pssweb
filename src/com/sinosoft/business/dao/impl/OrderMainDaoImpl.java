package com.sinosoft.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.OrderMainDao;
import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.query.PssOrderInfoQuery;

/**
 * 订单dao层实现类
 * 
 * @author Dylan
 * @date 16-08-20 13:10:20
 */
@Repository(value = "orderMainDao")
public class OrderMainDaoImpl extends BaseDao implements OrderMainDao {

	@Override
	public int insertOrderMain(OrderMain orderMain) {
		return insert("OrderMainDao.insertOrderMain", orderMain);
	}

	@Override
	public int updateOrderMain(OrderMain orderMain) {
		return update("OrderMainDao.updateOrderMain", orderMain);
	}

	@Override
	public OrderMain getOrderMainById(Long orderMainId) {
		return (OrderMain) getReadSqlSession().selectOne(
				"OrderMainDao.getOrderMainById", orderMainId);
	}

	@Override
	public Integer getCountByQuery(PssOrderInfoQuery pssOrderInfoQuery) {
		return getReadSqlSession().selectOne("OrderMainDao.getCountByQuery",
				pssOrderInfoQuery);
	}

	@Override
	public List<OrderMain> getOrderMainListByQuery(
			PssOrderInfoQuery pssOrderInfoQuery) {
		return getReadSqlSession().selectList(
				"OrderMainDao.getOrderMainListByQuery", pssOrderInfoQuery);
	}

	@Override
	public List<Map<String, Object>> findOrderBySonOrderId(String sonOrderIds) {
		return getReadSqlSession().selectList(
				"OrderMainDao.findOrderBySonOrderId", sonOrderIds);
	}

	@Override
	public Long getSequencesVal() {
		return getReadSqlSession().selectOne("OrderMainDao.getSequencesVal");
	}

}
