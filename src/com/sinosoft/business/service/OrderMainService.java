package com.sinosoft.business.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.PssOrderInfo;

/**
 * 订单Service层接口
 * 
 * @author Dylan
 * @date 16-08-24 18:27:41
 */
public interface OrderMainService {

	/**
	 * 订单页面初始化
	 */
	public void initOrderPage(ModelMap model, String method,
			HttpServletRequest request);

	/**
	 * 根据条件查询订单信息
	 * 
	 * @param condMap
	 *            Map<String, Object> 主订单id和子订单id(支持多个及单个子订单id查询)
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> findOrderByCond(Map<String, Object> condMap);
	
	
	
	/**
	 * 获取导出shp文件所需数据
	 * 
	 * @param sonOrderIds
	 *            String 子订单id
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getShpDate(String sonOrderIds);

	public void update(OrderMain orderMain);

	public List<PssOrderInfo> findsonOrderByCond(Map<String, Object> condMap);

	public PssOrderInfo getOrderInfo(Long orderid);

	public OrderMain getorderMainInfo(Long orderMainId);

}
