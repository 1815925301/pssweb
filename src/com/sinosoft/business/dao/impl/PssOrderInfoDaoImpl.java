/*
 * Powered By 尹力
 * Since 2015 - 2016-40-30
 */

package com.sinosoft.business.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssOrderInfoDao;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.query.PssOrderInfoQuery;

@Repository(value = "orderInfoDao")
public class PssOrderInfoDaoImpl extends BaseDao implements PssOrderInfoDao {

	@Override
	public int insertPssOrderInfo(PssOrderInfo PSS_ORDER_INFO) {
		return insert("PssOrderInfoDao.insertPssOrderInfo", PSS_ORDER_INFO);
	}

	@Override
	public int deletePssOrderInfo(Long id) {
		return delete("PssOrderInfoDao.deletePssOrderInfo", id);
	}

	@Override
	public int updatePssOrderInfo(PssOrderInfo PSS_ORDER_INFO) {
		return update("PssOrderInfoDao.updatePssOrderInfo", PSS_ORDER_INFO);
	}

	@Override
	public int updateOrderTrackingById(String orderid, String orderTracking) {
		Map<String, Object> conconditiond = new HashMap<String, Object>();
		conconditiond.put("orderid", orderid);
		conconditiond.put("orderTracking", orderTracking);
		return update("PssOrderInfoDao.updateOrderTrackingById", conconditiond);
	}

	@Override
	public PssOrderInfo selectPssOrderInfoById(Long id) {
		return (PssOrderInfo) getReadSqlSession().selectOne(
				"PssOrderInfoDao.getPssOrderInfoById", id);
	}

	@Override
	public Integer getCountByQuery(PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectOne("PssOrderInfoDao.getCountByQuery",
				orderInfoQuery);
	}

	@Override
	public List<PssOrderInfo> getPssOrderInfoInfoByQuery(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getPssOrderInfoInfoByQuery", orderInfoQuery);
	}

	@Override
	public List<PssOrderInfo> getPssOrderInfoListByQuery(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getPssOrderInfoListByQuery", orderInfoQuery);
	}

	@Override
	public List<PssOrderInfo> getPssOrderInfoList(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getPssOrderInfoList", orderInfoQuery);
	}

	@Override
	public void getPssOrderInfoInfoList() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PssOrderInfo> getPssOrderInfoList() {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getPssOrderInfoListByQuery");
	}

	@Override
	public List getSatelliteList() {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getSatelliteList");
	}

	@Override
	public List getSensoridList() {
		// TODO Auto-generated method stub
		return getReadSqlSession()
				.selectList("PssOrderInfoDao.getSensoridList");
	}

	@Override
	public List getProductlevelList() {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getProductlevelList");
	}

	@Override
	public List getTasktypeList() {
		// TODO Auto-generated method stub
		return getReadSqlSession()
				.selectList("PssOrderInfoDao.getTasktypeList");
	}

	@Override
	public List<Map<String, Object>> findSonOrderByCond(
			Map<String, Object> condMap) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.findSonOrderByCond", condMap);
	}
	// ----------------------订单统计开始--------------------------
	// 查询总记录数
	public int getOrderCount(PssOrderInfoQuery orderInfoQuery) {
		return (Integer) select("PssOrderInfoDao.getOrderCount", orderInfoQuery);
	}
	// 产品级别
	public List<PssOrderInfoQuery> getOrderByLevel(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getOrderByLevel", orderInfoQuery);
	}
	// 卫星
	public List<PssOrderInfoQuery> getOrderByState(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getOrderByState", orderInfoQuery);
	}
	// 传感器
	public List<PssOrderInfoQuery> getOrderBySensorid(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getOrderBySensorid", orderInfoQuery);
	}
	// 用户级别
	public List<PssOrderInfoQuery> getOrderByUserLevel(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getOrderByUserLevel", orderInfoQuery);
	}

	// 用户
	public List<PssOrderInfoQuery> getOrderByUser(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList("PssOrderInfoDao.getOrderByUser",
				orderInfoQuery);
	}

	// 应用领域
	public List<PssOrderInfoQuery> getOrderByArea(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList("PssOrderInfoDao.getOrderByArea",
				orderInfoQuery);
	}
	// 按单位
	public List<PssOrderInfoQuery> getOrderByUnit(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList("PssOrderInfoDao.getOrderByUnit",
				orderInfoQuery);
	}

	// 按接收站
	public List<PssOrderInfoQuery> getOrderByReceive(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getOrderByReceive", orderInfoQuery);
	}

	// 按订单状态
	public List<PssOrderInfoQuery> getOrderByOrderState(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getOrderByOrderState", orderInfoQuery);
	}

	// 按支付行
	public List<PssOrderInfoQuery> getOrderByPayTheline(
			PssOrderInfoQuery orderInfoQuery) {
		return getReadSqlSession().selectList(
				"PssOrderInfoDao.getOrderByPayTheline", orderInfoQuery);
	}
	//查询订单已下载成功的数量
	@Override
	public Integer getIsdownCount(PssOrderInfoQuery orderInfoQuery) {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectOne("PssOrderInfoDao.getIsdownCount",orderInfoQuery);
	}

	@Override
	public Map<String, String> filterOrderInfo(String satelliteidSensoridProductle) {
		
		return getReadSqlSession().selectOne("PssOrderInfoDao.selectOldInfo", satelliteidSensoridProductle);
	}

	@Override
	public Integer updateOrderInfoFtp(Map map) {
		// TODO Auto-generated method stub
		return update("PssOrderInfoDao.updateOrderInfoFtp", map);
	}

	@Override
	public Integer upOrderChectstate(Long orderid) {
		return getReadSqlSession().update("PssOrderInfoDao.updateCheckstate",orderid);
		
	}

	@Override
	public PssOrderInfo getorderInfoByid(Long orderid) {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectOne("PssOrderInfoDao.getorderinfoByid",orderid);
	}

	@Override
	public List<PssOrderInfo> getPriceByOrderMainid(long orderMainId) {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectList("PssOrderInfoDao.getorderPriceBymainid",orderMainId);
	}

}
