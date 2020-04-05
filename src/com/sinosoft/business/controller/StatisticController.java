package com.sinosoft.business.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.alibaba.fastjson.JSON;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.query.PssOrderInfoQuery;
import com.sinosoft.business.service.PssOrderInfoService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.query.UsersQuery;
import com.sinosoft.security.service.UsersService;

@Controller
public class StatisticController {

	@Resource
	private UsersService usersService;
	
	@Resource
	private PssOrderInfoService orderInfoService;
	
	//------------------------用户分类统计开始--------------------
	/*
	 * 用户分类统计开始--统计页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/statisticAccess.do")
	public String rutuenview(HttpServletRequest request, ModelMap model){
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/accessCountByCondition.do")
	public @ResponseBody Map<String, Object> accessCountByCondition(
			@RequestParam(value = "condition", required = true)String condition, 
			HttpServletRequest request){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		//这段代码是返回对应的要查询的实体，需要引一个jar包(fastjson-1.1.31.jar)
		List<UsersQuery> userQuery = JSON.parseArray(condition,UsersQuery.class);
		String stime = userQuery.get(0).getStarttime();//开始时间
		String etime = userQuery.get(0).getEndtime();//结束时间
		String timeType = userQuery.get(0).getTimeType();//时间类型
		UsersQuery usersQuery = new UsersQuery();//定义参数实体
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		//判断时间类型，对应相应的配置文件
		if(timeType.equals(PropertiesUtils.key("day", locale))){
			usersQuery.setStarttime(stime);
			usersQuery.setEndtime(etime);
			usersService.getUserCountByDay(request, modelMap,usersQuery);
		}  
		if(timeType.equals(PropertiesUtils.key("month", locale))){
			String stime_new =stime.substring(0,7);
			String etime_new =etime.substring(0,7);
			usersQuery.setStarttime(stime_new);
			usersQuery.setEndtime(etime_new);
			usersService.getUserCountByMonth(request, modelMap, usersQuery);
		}  
		if(timeType.equals(PropertiesUtils.key("year", locale))){
			String stime_new =stime.substring(0,4);
			String etime_new =etime.substring(0,4);
			usersQuery.setStarttime(stime_new);
			usersQuery.setEndtime(etime_new);
			usersService.getUserCountByYear(request, modelMap,usersQuery);
		}
		return modelMap.getModelMap();
		
	}
	 //------------------------------用户分类统计结束------------------
	
	//-------------------------------订单分类统计开始-----------------
	/*
	 * 订单统计开始
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/statisticOrderInfo.do")
	public String rutuenview_order(HttpServletRequest request, ModelMap model){
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/orderInfoByCondition.do")
	public @ResponseBody Map<String, Object> getOrderInfoByCondition(
			@RequestParam(value = "condition", required = true)String condition, 
			HttpServletRequest request){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		//这段代码是返回对应的要查询的实体，需要引一个jar包
		List<PssOrderInfoQuery> order = JSON.parseArray(condition,PssOrderInfoQuery.class);
		String stime = order.get(0).getStarttime();//开始时间
		String etime = order.get(0).getEndtime();//结束时间
		String timeType = order.get(0).getTimeType();//时间类型
		String report_unit = order.get(0).getReportUnit();//前台获取的条件类型
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		PssOrderInfoQuery pssOrderInfoQuery = selectOrdLoadByTime(timeType,stime,etime,locale);
		pssOrderInfoQuery.setStatisticType("orderInfo");//给定义的共用变量赋值，用于sql判断区分
			//按订单状态
			if(report_unit.equals(PropertiesUtils.key("order.orderstate", locale))){
				orderInfoService.getOrderByOrderState(request, modelMap, pssOrderInfoQuery,locale);
			}//按卫星
			if(report_unit.equals(PropertiesUtils.key("order.satelliteid", locale))){
				orderInfoService.getOrderByState(request, modelMap, pssOrderInfoQuery);
			}//按传感器
			if(report_unit.equals(PropertiesUtils.key("order.sensor", locale))){
				orderInfoService.getOrderBySensorid(request, modelMap, pssOrderInfoQuery);
			}//按接收站
			if(report_unit.equals(PropertiesUtils.key("order.receivingstation", locale))){
				orderInfoService.getOrderByUserLevel(request, modelMap, pssOrderInfoQuery);
			}//按用户
			if(report_unit.equals(PropertiesUtils.key("user", locale))){
				orderInfoService.getOrderByUser(request, modelMap, pssOrderInfoQuery);
			}
			/*if(report_unit.indexOf("订单覆盖度")!=-1){
				orderInfoService.getOrderByArea(request, modelMap, pssOrderInfoQuery);
			}*/
		return modelMap.getModelMap();
	}
	//-------------------------------订单分类统计结束-----------------
	
	
	//------------------------------下载量统计分析开始-----------------
	/*
	 * 下载量统计开始
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/downloadreport.do")
	public String rutuenview_down(HttpServletRequest request, ModelMap model){
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
	}	
	
	@RequestMapping(method = RequestMethod.POST, value = "/downloadByCondition.do")
	public @ResponseBody Map<String, Object> getDownloadByCondition(
			@RequestParam(value = "condition", required = true)String condition, 
			HttpServletRequest request){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		//这段代码是返回对应的要查询的实体，需要引一个jar包
		List<PssOrderInfoQuery> order = JSON.parseArray(condition,PssOrderInfoQuery.class);
		String stime = order.get(0).getStarttime();//开始时间
		String etime = order.get(0).getEndtime();//结束时间
		String timeType = order.get(0).getTimeType();//时间类型
		String report_unit = order.get(0).getReportUnit();//前台获取的条件类型
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		PssOrderInfoQuery pssOrderInfoQuery = selectOrdLoadByTime(timeType,stime,etime, locale);//获取参数实体
		pssOrderInfoQuery.setStatisticType("download");//给定义的共用变量赋值，用于sql判断区分
			//按产品级别
			if(report_unit.equals(PropertiesUtils.key("order.productlevel", locale))){
				orderInfoService.getOrderByLevel(request, modelMap, pssOrderInfoQuery);
			}//按卫星
			if(report_unit.equals(PropertiesUtils.key("order.satelliteid", locale))){
				orderInfoService.getOrderByState(request, modelMap, pssOrderInfoQuery);
			}//按传感器
			if(report_unit.equals(PropertiesUtils.key("order.sensor", locale))){
				orderInfoService.getOrderBySensorid(request, modelMap, pssOrderInfoQuery);
			}//按用户级别
			if(report_unit.equals(PropertiesUtils.key("user.level", locale))){
				orderInfoService.getOrderByUserLevel(request, modelMap, pssOrderInfoQuery);
			}//按用户
			if(report_unit.equals(PropertiesUtils.key("user", locale))){
				orderInfoService.getOrderByUser(request, modelMap, pssOrderInfoQuery);
			}
			/*if(report_unit.indexOf("应用领域")!=-1){
				orderInfoService.getOrderByArea(request, modelMap, pssOrderInfoQuery);
			}*/
		return modelMap.getModelMap();
	}
	
	//------------------------------下载量统计分析结束-----------------
	
	//------------------------------订单付费统计开始----------------------
	/*
	 * 订单付费统计开始
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/statisticOrderPay.do")
	public String rutuenview_payOrder(HttpServletRequest request, ModelMap model){
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
	}
	@RequestMapping(method = RequestMethod.POST, value = "/payOrderByCondition.do")
	public @ResponseBody Map<String, Object> payOrderByCondition(
			@RequestParam(value = "condition", required = true)String condition, 
			HttpServletRequest request){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		//这段代码是返回对应的要查询的实体，需要引一个jar包
		List<PssOrderInfoQuery> order = JSON.parseArray(condition,PssOrderInfoQuery.class);
		String stime = order.get(0).getStarttime();//开始时间
		String etime = order.get(0).getEndtime();//结束时间
		String timeType = order.get(0).getTimeType();//时间类型
		String report_unit = order.get(0).getReportUnit();//前台获取的条件类型
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		PssOrderInfoQuery pssOrderInfoQuery = selectOrdLoadByTime(timeType,stime,etime, locale);//获取参数实体
		pssOrderInfoQuery.setStatisticType("payOrder");//给定义的共用变量赋值，用于sql判断区分
			//按支付行
			if(report_unit.equals(PropertiesUtils.key("order.paytheline", locale))){
				orderInfoService.getOrderByPayTheline(request, modelMap, pssOrderInfoQuery);
			}
			//按订单状态
			if(report_unit.equals(PropertiesUtils.key("order.orderstate", locale))){
				orderInfoService.getOrderByOrderState(request, modelMap, pssOrderInfoQuery,locale);
			}
		return modelMap.getModelMap();
	}
	//------------------------------订单付费统计结束----------------------
	
	/*
	 * 封装数据，返回参数实体，根据前台的时间类型，对得到的时间按年月日进行处理，在sql里边进行分类，获取对应时间条件的值
	 */
	public PssOrderInfoQuery selectOrdLoadByTime(String timeType,String stime,String etime,Locale locale){
		PssOrderInfoQuery pssordInfoQuery = new PssOrderInfoQuery();
		if(timeType.equals(PropertiesUtils.key("day", locale))){
			timeType="day";
			pssordInfoQuery.setStarttime(stime);
			pssordInfoQuery.setEndtime(etime);
			pssordInfoQuery.setTimeType(timeType);
		}else if(timeType.equals(PropertiesUtils.key("month", locale))){
			timeType="month";
			String stime_new =stime.substring(0,7);
			String etime_new =etime.substring(0,7);
			pssordInfoQuery.setStarttime(stime_new);
			pssordInfoQuery.setEndtime(etime_new);
			pssordInfoQuery.setTimeType(timeType);
		}else if(timeType.equals(PropertiesUtils.key("year", locale))){
			timeType="year";
			String stime_new =stime.substring(0,4);
			String etime_new =etime.substring(0,4);
			pssordInfoQuery.setStarttime(stime_new);
			pssordInfoQuery.setEndtime(etime_new);
			pssordInfoQuery.setTimeType(timeType);
		}
		return pssordInfoQuery;
	}
}
