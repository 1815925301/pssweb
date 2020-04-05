package com.sinosoft.business.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.service.impl.CommonBusinessService;
import com.sinosoft.common.web.SinosoftModelMap;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: CommonBusinessController
 * @Description: 公用的WEB服务 MVC控制层web入口
 * @author zzq
 * @Version V1.0
 * @date 2013-10-22 下午03:46:42
 */
@Controller
public class CommonBusinessController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonBusinessController.class);
	
	@Resource
	private CommonBusinessService commonBusinessService;
	
	/**
	 * 获取给定区域的省份信息 区域省份联动功能调用
	 * @param areaId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午04:36:34
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/areaChangeProvince.do")
	public @ResponseBody Map<String, Object> areaChangeProvince(
			@RequestParam(value = "areaId", required = true)Long areaId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定区域id的省份信息，areaId={}", areaId);
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			if (areaId == null) {
				LOGGER.error("获取给定id区域的省份信息失败，区域id为空");
				modelMap.put("status", "exception");
				modelMap.put("data", "该区域不存在，请刷新页面重新请求！");
			} else {
				if (commonBusinessService.getProvinceInfoByAreaId(areaId, modelMap)) {
					//此时获取成功
					modelMap.put("status", "success");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定区域的省份信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
			modelMap.put("data", "系统异常，请刷新页面重新请求！");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 获取给定省份的区域信息 省份区域联动功能调用
	 * @param provinceId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午04:36:34
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/provinceChangeArea.do")
	public @ResponseBody Map<String, Object> provinceChangeArea(
			@RequestParam(value = "provinceId", required = true)Long provinceId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定省份id的省份信息，provinceId={}", provinceId);
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			if (provinceId == null) {
				LOGGER.error("获取给定id省份的区域信息失败，省份id为空");
				modelMap.put("status", "exception");
				modelMap.put("data", "该省份不存在，请刷新页面重新请求！");
			} else {
				if (commonBusinessService.getAreaInfoByProvinceId(provinceId, modelMap)) {
					//此时获取成功
					modelMap.put("status", "success");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定省份的区域信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
			modelMap.put("data", "系统异常，请刷新页面重新请求！");
		}
		return modelMap.getModelMap();
	}
	
	
	/**
	 * 获取给定省份的城市信息 省份城市联动功能调用
	 * @param provinceId
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-24 上午03:18:14
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/provinceChangeCity.do")
	public @ResponseBody Map<String, Object> provinceChangeCity(
			@RequestParam(value = "provinceId", required = true)Long provinceId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定省份id的城市信息，provinceId={}", provinceId);
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			if (provinceId == null) {
				LOGGER.error("获取给定id省份的城市信息失败，省份id为空");
				modelMap.put("status", "exception");
				modelMap.put("data", "该省份不存在，请刷新页面重新请求！");
			} else {
				if (commonBusinessService.getCityInfoByProvinceId(provinceId, modelMap)) {
					//此时获取成功
					modelMap.put("status", "success");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定省份的区域信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
			modelMap.put("data", "系统异常，请刷新页面重新请求！");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 获取给定省份id的城市信息与经销商信息 省份城市经销商联动功能调用
	 * @param provinceId
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-24 上午03:18:14
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/provChangeCityWithDistrib.do")
	public @ResponseBody Map<String, Object> provChangeCityWithDistrib(
			@RequestParam(value = "provinceId", required = true)Long provinceId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定省份id的城市信息与经销商信息，provinceId={}", provinceId);
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			if (provinceId == null) {
				LOGGER.error("获取给定省份id的城市信息与经销商信息，省份id为空");
				modelMap.put("status", "exception");
				modelMap.put("data", "该省份不存在，请刷新页面重新请求！");
			} else {
				if (commonBusinessService.getCityAndDistribInfoByProvinceId(provinceId, modelMap)) {
					//此时获取成功
					modelMap.put("status", "success");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定省份id的城市信息与经销商信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
			modelMap.put("data", "系统异常，请刷新页面重新请求！");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 获取给定区域id的所属省份信息 获取给定省份id的所属城市信息 实现区域、省份、城市三级联动
	 * @param provinceId
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-24 上午05:41:30
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/areaChangeProvinceAndCity.do")
	public @ResponseBody Map<String, Object> areaChangeProvinceAndCity(
			@RequestParam(value = "areaId", required = true)Long areaId, 
			@RequestParam(value = "provinceId", required = true)Long provinceId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定区域id的所属省份信息 获取给定省份id的所属城市信息 实现区域，areaId={}，provinceId={}", areaId, provinceId);
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			if (areaId == null || provinceId == null) {
				LOGGER.error("获取给定区域id的身份信息、获取给定id省份的城市信息失败，区域id或省份id为空");
				modelMap.put("status", "exception");
				modelMap.put("data", "信息不存在，请刷新页面重新请求！");
			} else {
				if (commonBusinessService.getProvinceInfoByAreaIdAndCityInfoByProvinceId(areaId, provinceId, modelMap)) {
					//此时获取成功
					modelMap.put("status", "success");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定区域id的身份信息、获取给定id省份的城市信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
			modelMap.put("data", "系统异常，请刷新页面重新请求！");
		}
		return modelMap.getModelMap();
	}
	
}
