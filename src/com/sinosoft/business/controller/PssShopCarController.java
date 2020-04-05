package com.sinosoft.business.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.service.OrderMainService;
import com.sinosoft.business.service.PssShopCarService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;




@Controller
public class PssShopCarController {
	
	@Resource
	private PssShopCarService PssShopCarService;
	
	@Resource
	private OrderMainService orderMainService;// 订单Service层接口
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PssShopCarController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssshopcar.do")
	public String PssShopManage(HttpServletRequest request, ModelMap model) {
		String method =request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		model.addAttribute("pageHanName", "底层子任务"); // 页面名称
		ActivityWebUtils.WrapperModle(request, model);
		PssShopCarService.PssShopCarPageInit(model, method, request);
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = { RequestMethod.POST}, value = "/showPssShopCar.do")
	public @ResponseBody Map<String, Object> showTaskSubTable(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		PssShopCarService.getPssShopCarForInitPage(model, method, request);
		return model;
	}
	
	@RequestMapping(method = { RequestMethod.POST}, value = "/queryshopcarcontol.do")
	public @ResponseBody Map<String, Object> queryShopcarcontol(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		Integer carcontol=PssShopCarService.getShopcarcontol(model, method, request);
		model.addAttribute("carcontol", carcontol);
		return model;
	}
	
	/** 
	 * 进入新增页面
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/addpssShopCar.do")
	public String PssShopCar(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		model.addAttribute("pageHanName", "PssShopCar"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		/*
		 * 逻辑处理
		 */
		return "manage/" + model.get("pageName");
	}
	/** 
	 * 根据日期获得日期下一共有多少id
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/selectIdOfDate.do")
	public @ResponseBody Map<String, Object>  selectIdOfDate(HttpServletRequest request, ModelMap model){
		ActivityModelMap actmap = new ActivityModelMap(request);
		String dateid = request.getParameter("selectidofdate");
		PssShopCarService.selevtIdOfDate( request,actmap,dateid);
		return actmap.getModelMap();
	}
	
	/** 
	 * 保存新增对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/savepssShopCar.do")
	public @ResponseBody Map<String, Object>  saveNew(
			HttpServletRequest request,HttpServletResponse response,PssShopCar pssshopcar){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存更新对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/updatepssShopCar.do")
	public @ResponseBody Map<String, Object>  update(
			HttpServletRequest request,HttpServletResponse response ,PssShopCar pssshopcar) {
		LOGGER.debug("更新用户信息！");
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
				if (PssShopCarService.update(request, pssshopcar, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", PropertiesUtils.key("order.orderstate", locale));
				}
			
		} catch(Exception e) {
			LOGGER.error("更新信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/removeShopCar.do")
	public @ResponseBody Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response) {
		LOGGER.debug("删除用户信息！");
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (PssShopCarService.remove(request, modelMap)) {
				modelMap.put("status", "success");
				modelMap.put("data", PropertiesUtils.key("deletesuccess", locale));
			}
			
			}catch(Exception e){
				LOGGER.error("删除采集单信息的操作出现异常：{}", e);
				modelMap.put("status", "error");
				modelMap.put("data", PropertiesUtils.key("deleteerror", locale));
			}
			
			return modelMap.getModelMap();
	}
	/** 
	 * 查看详情
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getPssShopCar.do")
	public @ResponseBody Map<String, Object> getPssShopCar(HttpServletRequest request, ModelMap model) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		ActivityWebUtils.WrapperModle(request, model);
		PssShopCarService.getPssShopCar(request, modelMap);
		return modelMap.getModelMap();
	}
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getPssShopCarinfo.do")
	public String getPssShopCarinfo(HttpServletRequest request, ModelMap model) {
		ActivityWebUtils.WrapperModle(request, model);
		PssShopCarService.getPssShopCarinfo(request, model);
		return "manage/shopcarManage";
	}
	/**
	 * 根据指定的采集单id 获取采集单信息
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/showPssshopcarbyid.do")
	public @ResponseBody Map<String, Object>  getUserInfo(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		PssShopCarService.getPssShopCar(request, modelMap);
		return modelMap.getModelMap();
	}
	
	
	
	/**
	 *通过选择多选框，增减总共价格
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},  value = "/selecShopCarOrder.do")
	public @ResponseBody Map<String, Object> selectShopCarOrder(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		LOGGER.debug("查询选择订单的价格总量！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
//		try {
			PssShopCarService.selectShopCarOrderPrice(request, modelMap);
//			if (PssShopCarService.remove(request, modelMap)) {
//				modelMap.put("status", "success");
//				modelMap.put("data", "删除用户信息成功！");
//			}
			
//			}catch(Exception e){
//				LOGGER.error("删除采集单信息的操作出现异常：{}", e);
//				modelMap.put("status", "error");
//				modelMap.put("data", "系统中存在该用户的操作数据，如上报活动等！<BR/>无法删除！");
//			}
			
			return modelMap.getModelMap();
	}
	
	/*
	 * 购物车覆盖显示
	 * 
	 */
	@RequestMapping(method = { RequestMethod.POST,RequestMethod.GET}, value = "/shopCarCoverage.do")
	public String shopCarCoverage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityWebUtils.WrapperModle(request, model);
		String orderids = request.getParameter("orderid");// 获取选择的订单id
		Map<String, Object> condMap = new HashedMap();
		condMap.put("sonOrderIds", orderids);
		List<Map<String, Object>> orderList;
		condMap.put("flag", "2");
		orderList = orderMainService.findOrderByCond(condMap);
		String strs = null;
		for (Map<String, Object> map : orderList) {
			strs = map.get("DATAUPPERLEFTLONG")+","+
					map.get("DATAUPPERLEFTLAT")+","+
					map.get("DATAUPPERRIGHTUPPERLONG")+","+
					map.get("DATAUPPERRIGHTLAT")+","+
					map.get("DATALOWERRIGHTLONG")+","+
					map.get("DATALOWERRIGHTLAT")+","+
					map.get("DATALOWERLEFTLONG")+","+
					map.get("DATALOWERLEFTLAT")+";";
		}
		String orderStrs = strs.substring(0,strs.lastIndexOf(";"));
		model.addAttribute("orderList", orderStrs);
		return "manage/shopcarCoverage";
	}
	
	
	/**
	 *清空购物车
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/removeAllShopCar.do")
	public @ResponseBody Map<String, Object> removeShopCar(HttpServletRequest request,HttpServletResponse response) {
		LOGGER.debug("清空购物车！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		String list = request.getParameter("sc");
		if("".equals(list)){
		}else if("1".equals(list)){
			List<PssShopCar> orderList;
//		String[] idlist = list.split(",");
			orderList = PssShopCarService.selectShopCar(request,
					modelMap);
		for(int i=0;i<orderList.size();i++){
			Long iod = orderList.get(i).getOrderid();
			String orderid = String.valueOf(iod);
			if (PssShopCarService.removed(request, modelMap,orderid)) {
				modelMap.put("status", "success");
				modelMap.put("data", "删除用户信息成功！");
			
			
			}else{
				LOGGER.error("删除采集单信息的操作出现异常：{}");
				modelMap.put("status", "error");
				modelMap.put("data", "系统中存在该用户的操作数据，如上报活动等！<BR/>无法删除！");
			}
		}
		}
			return modelMap.getModelMap();
	}
	
	
	/***
	 * 批量删除购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/deletemoreShopCar.do")
	public @ResponseBody Map<String, Object> deleteShopCarmore(HttpServletRequest request,HttpServletResponse response) {
		LOGGER.debug("批量删除购物车！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		String shopid = request.getParameter("shopid");
		List list = new ArrayList();
		
		String[] dfd = shopid.split(",");
		for(int ids=0;ids<dfd.length;ids++){
			if (PssShopCarService.removed(request, modelMap,dfd[ids])) {
				modelMap.put("status", "success");
				modelMap.put("data", "删除用户信息成功！");
			}else{
				LOGGER.error("删除采集单信息的操作出现异常：{}");
				modelMap.put("status", "error");
				modelMap.put("data", "系统中存在该用户的操作数据，如上报活动等！<BR/>无法删除！");
			}
		}
		return modelMap.getModelMap();
	}
	
	
}

