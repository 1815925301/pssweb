package com.sinosoft.business.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;



public interface PersonalCenterService {

	/***
	 * 获得（已完成订单）（处理中订单）（待处理订单）（已取消订单）（失败订单）各个的数量
	 */
	public void getOrdersCounts(ModelMap model, HttpServletRequest request);

	/***
	 * 采集单
	 * @param model
	 * @param request
	 */
	public void getCollectionOrders(ModelMap model, HttpServletRequest request);
	/***
	 * 定制单
	 * @param model
	 * @param request
	 */
	public void getProductOrders(ModelMap model, HttpServletRequest request);
	/***
	 * 订购单
	 * @param model
	 * @param request
	 */
	public void getOrders(ModelMap model, HttpServletRequest request);
}
