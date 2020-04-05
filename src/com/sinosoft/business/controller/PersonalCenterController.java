package com.sinosoft.business.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinosoft.business.po.PssCountry;
import com.sinosoft.business.po.query.PssCountryQuery;
import com.sinosoft.business.service.PersonalCenterService;
import com.sinosoft.business.service.PssCityService;
import com.sinosoft.business.service.PssCountryService;
import com.sinosoft.business.service.PssProvinceService;
import com.sinosoft.business.service.PssTownService;
import com.sinosoft.business.service.SystemConfigManager;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.security.service.UsersService;

@Controller
public class PersonalCenterController {
	@Resource
	private PersonalCenterService mgr;
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonalCenterController.class);
	
	//个人信息里引入的方法接口
	@Resource
	private UsersService usersService;
	@Resource
	private PssCountryService pssCountryService;
	@Resource
	private PssProvinceService pssProvinceService;
	@Resource
	private PssCityService pssCityService;
	@Resource
	private PssTownService pssTownService;
	/***
	 * 查询订单数量
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/PersonalCenter.do")
	public String getOrdersCounts(ModelMap model, HttpServletRequest request) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问SystemConfig管理页面", method);
		model.addAttribute("pageHanName", "PersonalCenter"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		mgr.getOrdersCounts(model,request);
		//个人信息里的方法
		LOGGER.debug("以 {} 方式访问用户信息页面", method);
		ActivityWebUtils.WrapperModle(request, model);
		usersService.getUsersInfo(model, method, request);
		PssCountryQuery pssCountryQuery = new PssCountryQuery();	
		// 得到所有国家
		List<PssCountry> countryList = pssCountryService
				.getCountry(pssCountryQuery);
		model.addAttribute("countryList", countryList);
		
		
		return "manage/" + model.get("pageName");
	}

	
	
	/***
	 * 采集单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/SingleCollectionOrders.do")
	public String getProductOrders(ModelMap model, HttpServletRequest request) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问SystemConfig管理页面", method);
		model.addAttribute("pageHanName", "SingleCollectionOrders"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		mgr.getCollectionOrders(model,request);
		return "manage/" + model.get("pageName");
	}
	
	/***
	 * 定制单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/CustomSingleOrders.do")
	public String selectdd(ModelMap model, HttpServletRequest request) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问SystemConfig管理页面", method);
		model.addAttribute("pageHanName", "SingleCollectionOrders"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		mgr.getProductOrders(model,request);
		return "manage/" + model.get("pageName");
	}
	
	/***
	 * 订购单
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/BuyOrders.do")
	public String getOrders(ModelMap model, HttpServletRequest request) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问SystemConfig管理页面", method);
		model.addAttribute("pageHanName", "BuyOrders"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		mgr.getOrders(model,request);
		return "manage/" + model.get("pageName");
	}
}
