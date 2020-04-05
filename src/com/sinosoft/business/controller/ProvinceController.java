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

import com.sinosoft.business.po.Province;
import com.sinosoft.business.po.extend.ExtendProvince;
import com.sinosoft.business.service.ProvinceService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.SinosoftModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: ProvinceController
 * @Description: 省份信息  MVC控制层web入口
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 下午09:51:35
 */
@Controller
public class ProvinceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceController.class);
	
	@Resource
	private ProvinceService provinceService;
	
	/**
	 * 省份管理页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/province.do")
	public String provinceManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问省份管理页面", method);
		model.addAttribute("pageHanName", "省份"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		provinceService.getProvinceInfoForInitPage(model, method, request);
		return "manage/" + model.get("pageName");
	}
	
	/**
	 * 根据指定的省份 获取省份信息
	 * @param provinceId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:12:48
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showProvince.do")
	public @ResponseBody Map<String, Object> getProvinceInfo(
			@RequestParam(value = "provinceId", required = true)Long provinceId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定id的省份新息，provinceId={}", provinceId);
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			if (provinceId == null) {
				LOGGER.error("获取给定id的省份信息失败，机构id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该省份不存在，请刷新页面重新请求!");
			} else {
				ExtendProvince eProvince = provinceService.getExtendProvinceById(provinceId);
				if (eProvince != null) {
					modelMap.put("total", 1);
					modelMap.put("status", "success");
					modelMap.put("data", eProvince);
				} else {
					modelMap.put("total", 0);
					modelMap.put("status", "failure");
					modelMap.put("data", "该省份不存在，请刷新页面重新请求!");
				}
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的省份信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存新的省份信息
	 * @param carType
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:13:07
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewProvince.do")
	public @ResponseBody Map<String, Object> addNewProvince(Province province, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("保存新的省份信息！");
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || province == null) {
				modelMap.put("status", "exception");
			} else {
				if (provinceService.addNewProvince(province, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "保存新的省份信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("保存新的省份信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 更新省份信息
	 * @param carType
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:41:25
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeProvince.do")
	public @ResponseBody Map<String, Object> udpateCarType(Province province, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新省份信息！");
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || province == null) {
				modelMap.put("status", "exception");
			} else {
				if (provinceService.updateProvince(province, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新省份信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新省份信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 删除省份信息
	 * @param provinceId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午11:16:07
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/removeProvince.do")
	public @ResponseBody Map<String, Object> removeCarTypeInfo(
			@RequestParam(value = "provinceId", required = true)Long provinceId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除省份信息！");
		SinosoftModelMap modelMap = new SinosoftModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || provinceId == null) {
				modelMap.put("status", "exception");
			} else {
				if (provinceService.removeProvince(provinceId, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "删除省份信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除省份信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}

}
