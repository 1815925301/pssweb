package com.sinosoft.business.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.sinosoft.business.po.FtpuserConfig;
import com.sinosoft.business.po.PssProductprice;
import com.sinosoft.business.service.FtpuserConfigService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;
/**
 * @Package com.sinosoft.business.controller
 * @ClassName: VipuserController
 * @Description: VIP用户管理  MVC控制层web入口
 * @author wlg
 * @Version V1.0
 * @date 2016-08-20
 */
@Controller
public class FtpuserConfigController {
	@Resource
	private FtpuserConfigService ftpuserConfigService;
	private static final Logger LOGGER = LoggerFactory.getLogger(FtpuserConfigController.class);
	/**
	 * VIP用户管理
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-8-20
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/vipuser.do")
	public String VipuserManage(HttpServletRequest request, ModelMap model) {
		String method =request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		model.addAttribute("pageHanName", "底层子任务"); // 页面名称
		ActivityWebUtils.WrapperModle(request, model);
		ftpuserConfigService.ftpuserConfigPageInit(request, model, method);
		return "manage/" + model.get("pageName");
	}
	/**
	 * VIP用户管理新增
	 * @param request
	 * @param FtpuserConfig
	 * @return Boolean
	 * @throws
	 * @author wlg
	 * @date 2016-8-23
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewvipuser.do")
	public @ResponseBody Map<String, Object>  saveNewvipuser(
			HttpServletRequest request,HttpServletResponse response,FtpuserConfig ftpuserConfig){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		String targetlocalpath="";
		if(!ftpuserConfig.getPath1().equals("")){
			targetlocalpath+= ","+ftpuserConfig.getPath1();
		}
		if(!ftpuserConfig.getPath2().equals("")){
			targetlocalpath+= ","+ftpuserConfig.getPath2();
		}
		if(!ftpuserConfig.getPath3().equals("")){
			targetlocalpath+= ","+ftpuserConfig.getPath3();
		}
		if(!ftpuserConfig.getPath4().equals("")){
			targetlocalpath+= ","+ftpuserConfig.getPath4();
		}
		if(!ftpuserConfig.getPath5().equals("")){
			targetlocalpath+= ","+ftpuserConfig.getPath5();
		}
		ftpuserConfig.setTargetlocalpath(targetlocalpath);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ftpuserConfig.setCreatetime(df.format(new Date()));
		try{
			ftpuserConfigService.save(request, ftpuserConfig, modelMap);
			modelMap.put("status", "success");
			modelMap.put("data", PropertiesUtils.key("savesuccess", locale));
		}catch(Exception e) {
			LOGGER.error("出现异常：{}", e);
			modelMap.put("status", "error");
		}
		return modelMap.getModelMap();
	}
	/**
	 * VIP用户管理删除
	 * @param request
	 * @param id
	 * @return boolean
	 * @throws
	 * @author wlg
	 * @date 2016-8-23
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/deleteVipuser.do")
	public @ResponseBody Map<String, Object> removeFtpuserbyid(
			@RequestParam(value = "id", required = true)Long id, 
			HttpServletRequest request, HttpServletResponse response) {
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
		if (ftpuserConfigService.remove(id,request, modelMap)) {
			modelMap.put("status", "success");
			modelMap.put("data", PropertiesUtils.key("deletesuccess", locale));
		}
		
		}catch(Exception e){
			LOGGER.error("删除操作出现异常：{}", e);
			modelMap.put("status", "error");
		}
		return modelMap.getModelMap();
	}

}
