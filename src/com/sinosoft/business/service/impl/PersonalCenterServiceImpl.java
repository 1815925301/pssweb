package com.sinosoft.business.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.FulltextSearchDao;
import com.sinosoft.business.dao.PersonalCenterDao;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.SelectCountsByOrderState;
import com.sinosoft.business.po.SelectOrderCount;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.po.query.PssCollectInfoQuery;
import com.sinosoft.business.po.query.PssOrderInfoQuery;
import com.sinosoft.business.po.query.SystemConfigQuery;
import com.sinosoft.business.service.PersonalCenterService;
import com.sinosoft.security.po.extend.ExtendUsers;


@Service("PersonalCenterService")
public class PersonalCenterServiceImpl implements PersonalCenterService{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PersonalCenterServiceImpl.class);
	@Resource
	private PersonalCenterDao dao;
	
	@Override
	public void getOrdersCounts(ModelMap model, HttpServletRequest request) {
		//获得登录名。
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		LOGGER.debug("Service层：根据查询参数获取");
		PssOrderInfoQuery pssOrderInfoQuery = new PssOrderInfoQuery();
		pssOrderInfoQuery.setUsername(username);
		//查询所有订单状态的数量
		SelectOrderCount getAllOrdersCounts = dao.getOrdersCounts(pssOrderInfoQuery);
		//第一个订单完成订单的数量
		if(getAllOrdersCounts != null){
		
		String num1 = getAllOrdersCounts.getCompleteorder();
		//第二个订单失败订单的数量
		String num2=getAllOrdersCounts.getFaielorder();
		//第三个订单取消订单的数量
		String num3=getAllOrdersCounts.getCancleorder();
		//第四个处理等待订单的数量
		String num4=getAllOrdersCounts.getWaitorder();
		//第五个订单失败订单的数量
		String num5=getAllOrdersCounts.getDealorder();
		model.addAttribute("num1", num1);
		model.addAttribute("num2", num2);
		model.addAttribute("num3", num3);
		model.addAttribute("num4", num4);
		model.addAttribute("num5", num5);
		
		}else{
			model.addAttribute("num1", 0);
			model.addAttribute("num2", 0);
			model.addAttribute("num3", 0);
			model.addAttribute("num4", 0);
			model.addAttribute("num5", 0);
		}
	}

	
	@Override
	public void getCollectionOrders(
			ModelMap model, HttpServletRequest request) {
		PssOrderInfoQuery pssOrderInfoQuery = new PssOrderInfoQuery();
		PssCollectInfoQuery pssCollectInfoQuery = new PssCollectInfoQuery();
		//获得登录名。
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		//设置用户名和状态，这个状态1代表的是待处理订单
//		pssOrderInfoQuery.setUsername(username);
//		pssOrderInfoQuery.setTasktype(new Long(1));
		pssCollectInfoQuery.setUsername(username);
		//获得采集单列表
		List<PssCollectInfoQuery> getSingleCollectionOrders=dao.getSingleCollectionOrders(pssCollectInfoQuery);
		Integer totalCount = dao.getCountByCollect(pssCollectInfoQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount,
				pssOrderInfoQuery.getPageSize(), pssOrderInfoQuery.getPage(),
				pssOrderInfoQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		model.addAttribute("getSingleCollectionOrders", getSingleCollectionOrders);
	}

	@Override
	public void getProductOrders(ModelMap model, HttpServletRequest request) {
		PssOrderInfoQuery pssOrderInfoQuery = new PssOrderInfoQuery();
		//获得登录名。
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		//设置用户名和状态，这个状态2代表的是处理中订单
		pssOrderInfoQuery.setUsername(username);
		pssOrderInfoQuery.setTasktype(new Long(2));
		Integer totalCount = dao.getCountByQuery(pssOrderInfoQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount,
				pssOrderInfoQuery.getPageSize(), pssOrderInfoQuery.getPage(),
				pssOrderInfoQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		//获得订单
		List<PssOrderInfoQuery> getCustomSingleOrder=dao.getCustomSingleOrder(pssOrderInfoQuery);		
		model.addAttribute("getCustomSingleOrder", getCustomSingleOrder);
	}

	@Override
	public void getOrders(ModelMap model, HttpServletRequest request) {
		PssOrderInfoQuery pssOrderInfoQuery = new PssOrderInfoQuery();
		//获得登录名
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		//设置用户名和状态，这个状态3代表的是成功订单
		pssOrderInfoQuery.setUsername(username);
		pssOrderInfoQuery.setTasktype(new Long(3));
		Integer totalCount = dao.getCountByQuery(pssOrderInfoQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount,
				pssOrderInfoQuery.getPageSize(), pssOrderInfoQuery.getPage(),
				pssOrderInfoQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<PssOrderInfoQuery> getBuyOrders=dao.getBuyOrders(pssOrderInfoQuery);
		model.addAttribute("getBuyOrders", getBuyOrders);
	}

}
