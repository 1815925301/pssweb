package com.sinosoft.business.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.query.PssOrderInfoQuery;

/**
 * 主订单dao层接口
 * 
 * @author Dylan
 * @date 16-08-20 13:10:20
 */
public interface OrderMainDao {

	/**
	 * 新增主订单方法
	 */
	public int insertOrderMain(OrderMain orderMain);

	/**
	 * 修改主订单方法
	 */
	public int updateOrderMain(OrderMain orderMain);

	/**
	 * 根据id查询主订单
	 * 
	 * @param orderMainId
	 *            Long 主订单id
	 * @return
	 */
	public OrderMain getOrderMainById(Long orderMainId);

	/**
	 * 查询订单总条数(关联子订单)
	 * 
	 * @param orderInfoQuery
	 * @return
	 */
	public Integer getCountByQuery(PssOrderInfoQuery pssOrderInfoQuery);

	/**
	 * 按条件查询订单(关联子订单)
	 * 
	 * @param pssshopcarQuery
	 * @return
	 */
	public List<OrderMain> getOrderMainListByQuery(
			PssOrderInfoQuery pssOrderInfoQuery);

	/**
	 * 根据子订单id查询订单信息
	 * 
	 * @param orderIds
	 *            String 以“,”拼接的id字符串
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> findOrderBySonOrderId(String sonOrderIds);

	
	
	/**
	 * 获取主订单当前序列值
	 * 
	 * @return
	 */
	public Long getSequencesVal();
}
