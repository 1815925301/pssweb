package com.sinosoft.business.controller;

import java.util.List;
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

import com.sinosoft.business.po.PubZoncode;
import com.sinosoft.business.po.query.PubZoncodeQuery;
import com.sinosoft.business.service.PubZoncodeServer;
import com.sinosoft.common.web.ActivityModelMap;

@Controller
public class PubZoncodeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PubZoncodeController.class);
	
	@Resource
	private PubZoncodeServer pubZoncodeServer;
	
	/**
	 * 根据省份id获取城市数据
	* @Title: showPubZoncodeCity 
	* @Description: TODO
	* @return Map<String,Object>    返回类型 
	* @param zonecode
	* @param request
	* @param response
	* @return
	* @throws 
	* @author zzq   
	* @date 2014年8月8日 下午12:59:16 
	* @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showPubZoncodeCity.do")
	public @ResponseBody Map<String, Object> showPubZoncodeCity(
			@RequestParam(value = "zonecode", required = true)String zonecode, 
			@RequestParam(value = "zonelevel", required = true)Short zonelevel,
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定id的省份新息，zonecode={}", zonecode);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (zonecode == null) {
				LOGGER.error("获取给定id的省份信息失败，机构id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该省份不存在，请刷新页面重新请求!");
			} else {
				PubZoncodeQuery pzq = new PubZoncodeQuery();
				if(zonecode.length()>5){
					pzq.setZonelevel(zonelevel);
					if(zonelevel.compareTo(new Short("2"))==0){//编码为省份
						pzq.setZonecode(zonecode.substring(0, 2)+"%");
					}else if(zonelevel.compareTo(new Short("3"))==0){//编码为市
						pzq.setZonecode(zonecode.substring(0, 4)+"%");
					}else if(zonelevel.compareTo(new Short("4"))==0){//编码为县区
						pzq.setZonecode(zonecode.substring(0, 6)+"%");
					}
					List<PubZoncode> eProvince = pubZoncodeServer.getPubZonCodeList(pzq);
					if (eProvince != null) {
						modelMap.put("status", "success");
						modelMap.put("data", eProvince);
					} else {
						modelMap.put("status", "failure");
						modelMap.put("data", "该数据不存在，请刷新页面重新请求!");
					}
				}
				
			}
		} catch(Exception e) {
			LOGGER.error("获取给定id的省份信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
}
