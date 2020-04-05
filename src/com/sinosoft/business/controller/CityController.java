package com.sinosoft.business.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.po.City;
import com.sinosoft.business.po.extend.ExtendCity;
import com.sinosoft.business.service.CityService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.SinosoftModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: CityController
 * @Description: 城市信息  MVC控制层web入口
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 下午09:51:35
 */
@Controller
public class CityController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);
	
	@Resource
	private CityService cityService;
	
	/**
	 * 城市管理页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/city.do")
	public String cityManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问城市管理页面", method);
		model.addAttribute("pageHanName", "城市"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		cityService.getCityInfoForInitPage(model, method, request);
		return "manage/" + model.get("pageName");
	}
	
	/**
	 * 根据指定的城市 获取城市信息
	 * @param cityId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:12:48
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showCity.do")
	public @ResponseBody Map<String, Object> getCityInfo(
			@RequestParam(value = "cityId", required = true)Long cityId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定id的城市新息，cityId={}", cityId);
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			if (cityId == null) {
				LOGGER.error("获取给定id的城市信息失败，机构id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该城市不存在，请刷新页面重新请求!");
			} else {
				ExtendCity eCity = cityService.getExtendCityById(cityId);
				if (eCity != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", eCity);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该城市不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的城市信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存新的城市信息
	 * @param carType
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午10:51:58
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewCity.do")
	public @ResponseBody Map<String, Object> addNewCity(City city, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("保存新的城市信息！");
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || city == null) {
				modelMap.put("status", "exception");
			} else {
				if (cityService.addNewCity(city, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "保存新的城市信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("保存新的城市信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 更新城市信息
	 * @param carType
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:41:25
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeCity.do")
	public @ResponseBody Map<String, Object> udpateCarType(City city, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新城市信息！");
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || city == null) {
				modelMap.put("status", "exception");
			} else {
				if (cityService.updateCity(city, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新城市信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新城市信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 删除城市信息
	 * @param cityId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午11:16:07
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/removeCity.do")
	public @ResponseBody Map<String, Object> removeCarTypeInfo(
			@RequestParam(value = "cityId", required = true)Long cityId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除城市信息！");
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || cityId == null) {
				modelMap.put("status", "exception");
			} else {
				if (cityService.removeCity(cityId, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "删除城市信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除城市信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}

}
