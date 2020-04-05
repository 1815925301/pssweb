package com.sinosoft.business.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.po.PssCountry;
import com.sinosoft.business.po.query.PssCountryQuery;
import com.sinosoft.business.service.PssCityService;
import com.sinosoft.business.service.PssCountryService;
import com.sinosoft.business.service.PssProvinceService;
import com.sinosoft.business.service.PssTownService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: UserMessageController
 * @Description: 个人中心用户信息展示
 * @author hao
 * @date 2016-8-24
 */
@Controller
public class UserMessageController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserMessageController.class);

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

	/**
	 * 初始化用户信息
	 **/
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET }, value = "/UserMessage.do")
	public String usersManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问用户信息页面", method);
		ActivityWebUtils.WrapperModle(request, model);
		usersService.getUsersInfoForInitPage(model, method, request);
		PssCountryQuery pssCountryQuery = new PssCountryQuery();	
		// 得到所有国家
		List<PssCountry> countryList = pssCountryService
				.getCountry(pssCountryQuery);

		model.addAttribute("countryList", countryList);
		return "manage/userMessage";
	}

	/**
	 * 获取省份信息
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/getProvince.do")
	public @ResponseBody
	Map<String, Object> provinceMessage(HttpServletRequest request,
			HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		// 根据国家获得省份
		pssProvinceService.getPssProvinceByCountry(request, modelMap);
		return modelMap.getModelMap();
	}

	/**
	 * 获取市信息
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/getCity.do")
	public @ResponseBody
	Map<String, Object> cityMessage(HttpServletRequest request,
			HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		// 根据省份获得市
		pssCityService.getPssCityByProvince(request, modelMap);
		return modelMap.getModelMap();
	}

	/**
	 * 获取县信息
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/getTown.do")
	public @ResponseBody
	Map<String, Object> townMessage(HttpServletRequest request,
			HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		// 根据市获得县
		pssTownService.getPssTownByCity(request, modelMap);

		return modelMap.getModelMap();
	}

	/**
	 * 修改保存用户信息
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/saveUserMessage.do")
	public @ResponseBody
	Map<String, Object> saveUserMessage(Users user, HttpServletRequest request,
			HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		// 获得当前登录用户的信息
		ExtendUsers eUser = (ExtendUsers) request.getSession().getAttribute(
				"CURRENT_USER_INFO");
		// 根据用户的Id更新用户信息
		if (usersService.updateUserInfoById(user, eUser, modelMap)) {
			modelMap.put("status", "success");
			modelMap.put("data", PropertiesUtils.key("updatesuccess", locale));
		}
		return modelMap.getModelMap();
	}

}
