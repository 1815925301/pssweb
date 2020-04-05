/*
 * Powered By 尹力
 * Since 2015 - 2016-40-30
 */

package com.sinosoft.business.service;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.query.PssOrderInfoQuery;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * 
 * @author
 * @version 1.0
 * @since 1.0
 * */
public interface PssOrderInfoService {

	/**
	 * 页面初始化
	 */
	public void PssOrderInfoPageInit(ModelMap model, String method,
			HttpServletRequest request);

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
	public int andOrderTrackingById(String orderid, String orderTracking);

	/**
	 * 创建PssOrderInfo
	 **/
	public Boolean save(HttpServletRequest request, PssOrderInfo pssOrderInfo,
			ActivityModelMap modelMap);

	/**
	 * 更新PssOrderInfo
	 **/
	public Boolean update(HttpServletRequest request,
			PssOrderInfo pssOrderInfo, ActivityModelMap modelMap);

	/**
	 * 删除PssOrderInfo
	 **/
	public void remove(HttpServletRequest request, ActivityModelMap modelMap);

	/**
	 * 根据ID得到PssOrderInfo
	 **/
	public PssOrderInfo getPssOrderInfo(HttpServletRequest request,
			ActivityModelMap modelMap);

	public void getOrderForInitPage(ModelMap model, String method,
			HttpServletRequest request);

	/**
	 * 根据ID得到PssOrderInfo
	 **/
	public PssOrderInfo getPssOrderInfo(Long orderid);
	// ----------------订单下载量开始------------------
	// ------下载量分类统计------
	// 按产品级别查询
	public void getOrderByLevel(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery pssOrderInfoQuery);
	// 按卫星查询
	public void getOrderByState(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery pssOrderInfoQuery);
	// 按传感器
	public void getOrderBySensorid(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery pssOrderInfoQuery);
	// 按用户等级
	public void getOrderByUserLevel(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery pssOrderInfoQuery);
	// 按用户
	public void getOrderByUser(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery pssOrderInfoQuery);
	// 按应用领域
	public void getOrderByArea(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery pssOrderInfoQuery);
	// ------订单分类统计------
	// 按单位
	public void getOrderByUnit(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery);
	// 按接收站
	public void getOrderByReceive(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery);
	// 按订单状态
	public void getOrderByOrderState(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery,
			Locale locale);
	// ------付费订单统计
	public void getOrderByPayTheline(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery);
	// -----------------订单下载量结束------------------

	public boolean updateCheckstate(PssOrderInfo pssOrderInfo,
			ExtendUsers eUser, ActivityModelMap modelMap);

	public Integer getCountByQuery(PssOrderInfoQuery orderInfoQuery);
}
