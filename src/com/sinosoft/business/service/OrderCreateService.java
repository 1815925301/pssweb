package com.sinosoft.business.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.common.web.ActivityModelMap;

/**
 * 订单生成页面service层接口
 * 
 * @author Dylan
 * @date 16-08-20 14:13:55
 */
public interface OrderCreateService {

	/**
	 * 订单生成页面初始化
	 * 
	 * @param model
	 *            ModelMap 返回值
	 * @param method
	 *            String 请求方式：POST/GET
	 * @param request
	 *            HttpServletRequest
	 */
	public void initOrderCreatePage(ModelMap model, String method,
			HttpServletRequest request);

	/**
	 * 订单生成（新增订单信息）
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param modelMap
	 *            ActivityModelMap 返回值
	 * @param orderIds
	 *            String 子订单id拼串
	 * @param orderName
	 *            String 主订单名称
	 * @param remark
	 *            String 主订单备注
	 * @return
	 */
	public Boolean addOrderCreate(HttpServletRequest request,
			ActivityModelMap modelMap, String orderIds, String orderName,
			String remark);

	/**
	 * 过滤订单(0未找到重复订单1找到重复订单)
	 * @param orderInfo
	 * @return
	 */
	public Integer filterOrderInfo(PssOrderInfo orderInfo);
	
	/**
	 * 从配置文件中得到orderId
	 * @return
	 */
	public Long getOrderId();
	
	/**
	 * 生成customId(定制单号)
	 * @return
	 */
	public Long getCustomId();

	Integer findUserfreecont(HttpServletRequest request);
	
}
