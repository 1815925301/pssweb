/*
 * Powered By 尹力
 * Since 2015 - 2016-40-30
 */

package com.sinosoft.business.dao;
import java.util.List;
import java.util.Map;

import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.query.PssOrderInfoQuery;

public interface PssOrderInfoDao {

	/**
	 * 新增方法
	 */
	public int insertPssOrderInfo(PssOrderInfo orderInfo);

	/**
	 * 删除方法
	 */
	public int deletePssOrderInfo(Long id);

	/**
	 * 修改方法
	 */
	public int updatePssOrderInfo(PssOrderInfo orderInfo);

	/**
	 * 追加"订单跟踪"信息
	 * 
	 * @param orderid
	 *            String 订单id
	 * @param orderTracking
	 *            String "订单跟踪"信息
	 * @return
	 * @date 2016-10-11 09:58:15
	 * @author Dylan
	 */
	public int updateOrderTrackingById(String orderid, String orderTracking);

	/**
	 * 查询方法
	 */
	public PssOrderInfo selectPssOrderInfoById(Long id);

	/**
	 * 查询数量
	 */
	public Integer getCountByQuery(PssOrderInfoQuery orderInfoQuery);

	/**
	 * 按条件查询
	 */
	public List<PssOrderInfo> getPssOrderInfoInfoByQuery(
			PssOrderInfoQuery orderInfoQuery);

	/**
	 * 按条件查询 不带分页
	 */
	public List<PssOrderInfo> getPssOrderInfoListByQuery(
			PssOrderInfoQuery orderInfoQuery);

	public void getPssOrderInfoInfoList();

	public List<PssOrderInfo> getPssOrderInfoList();
	/**
	 * 查询卫星种类
	 */

	public List getSatelliteList();
	/**
	 * 查询传感器类型
	 * 
	 * @return
	 */

	public List getSensoridList();
	/**
	 * 查询产品级别
	 * 
	 * @return
	 */

	public List getProductlevelList();
	/**
	 * 查询订单类型
	 * 
	 * @return
	 */

	public List getTasktypeList();

	/**
	 * 根据条件查询子订单
	 * 
	 * @author Dylan
	 * @param condMap
	 *            Map<String, Object> 主订单id和子订单id(支持多个及单个子订单id查询)
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> findSonOrderByCond(
			Map<String, Object> condMap);

	// -----------------------------------订单统计查询开始--------------------------
	// 查询总记录数(类型通用)
	public int getOrderCount(PssOrderInfoQuery orderInfoQuery);
	// 按产品级别
	public List<PssOrderInfoQuery> getOrderByLevel(
			PssOrderInfoQuery orderInfoQuery);
	// 按卫星
	public List<PssOrderInfoQuery> getOrderByState(
			PssOrderInfoQuery orderInfoQuery);
	// 按传感器
	public List<PssOrderInfoQuery> getOrderBySensorid(
			PssOrderInfoQuery orderInfoQuery);
	// 按用户级别
	public List<PssOrderInfoQuery> getOrderByUserLevel(
			PssOrderInfoQuery orderInfoQuery);
	// 按用户
	public List<PssOrderInfoQuery> getOrderByUser(
			PssOrderInfoQuery orderInfoQuery);
	// 按应用领域
	public List<PssOrderInfoQuery> getOrderByArea(
			PssOrderInfoQuery orderInfoQuery);
	// ------订单分类统计------
	// 按单位
	public List<PssOrderInfoQuery> getOrderByUnit(
			PssOrderInfoQuery orderInfoQuery);
	// 按接收站
	public List<PssOrderInfoQuery> getOrderByReceive(
			PssOrderInfoQuery orderInfoQuery);
	// 按订单状态
	public List<PssOrderInfoQuery> getOrderByOrderState(
			PssOrderInfoQuery orderInfoQuery);
	// ------付费订单统计
	public List<PssOrderInfoQuery> getOrderByPayTheline(
			PssOrderInfoQuery orderInfoQuery);
	// -----------------------------------订单统计查询结束--------------------------

	public List<PssOrderInfo> getPssOrderInfoList(
			PssOrderInfoQuery orderInfoQuery);

	public Integer getIsdownCount(PssOrderInfoQuery orderInfoQuery);
	
	/**
	 * 过滤订单
	 * @param satelliteidSensoridProductle
	 * @return
	 */
	public Map<String,String> filterOrderInfo(String satelliteidSensoridProductle);

	/**
	 * 更新订单表下载路径
	 * @param map
	 * @return
	 */
	public Integer updateOrderInfoFtp(Map map);

	public Integer upOrderChectstate(Long orderid);

	public PssOrderInfo getorderInfoByid(Long orderid);

	public List<PssOrderInfo> getPriceByOrderMainid(long orderMainId);
}