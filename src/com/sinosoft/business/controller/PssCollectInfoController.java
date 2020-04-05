/*
 * Powered By JC
 * Since 2016-08-01 - 2016-08-04
 */

package com.sinosoft.business.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.service.PssCollectInfoService;
import com.sinosoft.business.service.PssOrderPayService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.business.po.PssOrderPay;

@Controller
public class PssCollectInfoController {
	
	@Resource
	private PssCollectInfoService pssCollectInfoService;
	@Resource
	private PssOrderPayService orderPayService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PssCollectInfoController.class);
	
	/**
	 * 采集单管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssCollectInfomanage.do")
	public String pssCollectInfoManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssCollectInfo管理页面", method);
		model.addAttribute("pageHanName", "PssCollectInfo"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		pssCollectInfoService.pssCollectInfoPageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	/**
	 * 进入添加采集单页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssCollectInfomanageAdd.do")
	public String pssCollectInfoManageAdd(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssCollectInfo新增管理页面", method);
		model.addAttribute("pageHanName", "pssCollectInfomanageAdd"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		pssCollectInfoService.pssCollectInfoPageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	/**
	 * 进入采集单详情页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/showCollectInfo.do")
	public String pssCollectInfoShow(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		Long id=Long.parseLong(request.getParameter("id"));
		ActivityModelMap modelMap = new ActivityModelMap(request);
		LOGGER.debug("以 {} 方式访问PssCollectInfo查看详情页面", method);
		model.addAttribute("pageHanName", "showCollectInfo"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		PssCollectInfo pssCollectInfo =pssCollectInfoService.getCollectInfoById(id);
		model.addAttribute("pssCollectInfo", pssCollectInfo);
		return "manage/" + model.get("pageName");
	}
	/** 
	 * 保存新增采集单
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/saveCollectInfo.do")
	public @ResponseBody Map<String, Object>  saveNewCollectInfoInfo(
			HttpServletRequest request,HttpServletResponse response,PssCollectInfo pssCollectInfo){
		LOGGER.debug("保存新的采集单信息！");
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
				if (pssCollectInfoService.saveCollectInfoInfo(request, pssCollectInfo, modelMap)) {
					//此时更新成功，将改后的采集单信息更新到Session中
					modelMap.put("status", "success");
					modelMap.put("data", PropertiesUtils.key("savesuccess", locale));
				}
		} catch(Exception e) {
			LOGGER.error("修改当前采集单的基本信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	

	/**
	 * 根据指定的采集单id 获取采集单信息
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author JC
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/showCollectInfobyid.do")
	public @ResponseBody Map<String, Object>  getCollectInfoInfo(HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		pssCollectInfoService.getCollectInfoById(modelMap, request);
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存更新采集单
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/changeCollectInfo.do")
	public @ResponseBody Map<String, Object>  updateCollectInfoInfo(
			HttpServletRequest request,HttpServletResponse response ,PssCollectInfo pssCollectInfo) {
		LOGGER.debug("更新用户信息！");
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
				if (pssCollectInfoService.updateCollectInfoInfo(request, pssCollectInfo, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", PropertiesUtils.key("updatesuccess", locale));
				}
			
		} catch(Exception e) {
			LOGGER.error("更新采集单信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 *删除采集单
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/removeCollectInfo.do")
	public @ResponseBody Map<String, Object> deleteCollectInfoInfo(
			@RequestParam(value = "id", required = true)Long id,
			HttpServletRequest request,HttpServletResponse response) {
		LOGGER.debug("删除采集单信息！");
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (pssCollectInfoService.removeCollectInfoInfo(id, request, modelMap)) {
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
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/addcollectionIspay.do")
	public String addcollectionIspay(HttpServletRequest request, ModelMap model) {
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		model.addAttribute("usercountry", eUser.getCountry());
		Long taskid=Long.parseLong(request.getParameter("taskid"));
		model.addAttribute("collectid", taskid);
		PssCollectInfo collectInfo=pssCollectInfoService.getCollectInfoByTaskId(taskid);
		model.addAttribute("collectInfo", collectInfo);
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		String userbank=PropertiesUtils.key("pssuserbank",locale);
		String ubank[]=userbank.split(",");
		model.addAttribute("userbank", ubank);//用户银行
		String abaebank=PropertiesUtils.key("ABAEBANK",locale);
		String abank[]=abaebank.split(",");
		model.addAttribute("abaebank", abank);//ABAE银行
		return "manage/orderpay";
	}
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/querypayInfo.do")
	public String querypayInfo(HttpServletRequest request, ModelMap model) {
		//主订单号
		Long collectid=Long.parseLong(request.getParameter("taskid"));
		PssOrderPay pssOrderPay=orderPayService.getPayinfoByCollid(collectid);
		model.addAttribute("pssOrderPay", pssOrderPay);
		return "manage/orderpayinfo";
	}

}

