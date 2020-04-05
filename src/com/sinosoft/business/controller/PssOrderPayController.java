/*
 * Powered By 尹力
 * Since 2015 - 2016-16-29
 */

package com.sinosoft.business.controller;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.business.po.PssOrderPay;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.service.OrderMainService;
import com.sinosoft.business.service.PssCollectInfoService;
import com.sinosoft.business.service.PssOrderPayService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;



@Controller
public class PssOrderPayController {
	
	@Resource
	private PssOrderPayService PSS_ORDER_PAYService;
	@Resource
	private OrderMainService orderMainService;
	@Resource
	private PssCollectInfoService collectInfoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PssOrderPayController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssOrderPaymanage.do")
	public String PssOrderPayManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssOrderPay管理页面", method);
		model.addAttribute("pageHanName", "PssOrderPay"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		PSS_ORDER_PAYService.PssOrderPayPageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssOrderPayshow.do")
	public @ResponseBody Map<String, Object>  showPssOrderPay(
			HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	

	@RequestMapping(method = RequestMethod.POST, value = "/saveOrderpay.do")
	public String  addsystem(@RequestParam(value="file",required=false) MultipartFile files,PssOrderPay orderPay, 
			HttpServletRequest request) throws IOException {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		ModelMap model = new ModelMap();
		String box="";
		ActivityWebUtils.WrapperModle(request, model);
		String fileName= files.getOriginalFilename(); 
		File file2 = new File( request.getSession().getServletContext().getRealPath("/")+"upload"); 
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+"upload";
		if(!file2.exists()){
			file2.mkdirs();
		}
		File uploadFile = new File(file2+"/"+fileName);
		FileCopyUtils.copy(files.getBytes(), uploadFile);
		orderPay.setImageaddress(basePath+"/"+fileName);
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		String abaebank=PropertiesUtils.key("ABAEBANK",locale);
		String abank[]=abaebank.split(",");
		//判断是否国内银行
		for (int i = 0; i < abank.length; i++) {
			if(orderPay.getPssuserbank().equals(abank[i])){
				orderPay.setBanktype(0);
			}else{
				orderPay.setBanktype(1);
			}
		}
		
		PSS_ORDER_PAYService.save(request, orderPay, modelMap);
		if(orderPay.getOrderMainId()!=null && !orderPay.getOrderMainId().equals("")){
			OrderMain orderMain=new OrderMain();
			orderMain.setOrderMainId(orderPay.getOrderMainId());
			orderMain.setIspay(1);
			orderMainService.update(orderMain);
			box="redirect:/orderMain.do";
		}if(orderPay.getCollectid()!=null && !orderPay.getCollectid().equals("")){
			PssCollectInfo collectInfo=new PssCollectInfo();
			collectInfo.setIspay(1);
			collectInfo.setTaskid(orderPay.getCollectid());
			collectInfoService.updateCollectInfoInfo(request, collectInfo, modelMap);
			box="redirect:/pssCollectInfomanage.do";
		}
		
		
		 return box;
	}
	
	/**
	 * 保存更新对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssOrderPayupdate.do")
	public @ResponseBody Map<String, Object>  update(
			HttpServletRequest request,HttpServletResponse response ,PssOrderPay pssOrderPay) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/pssOrderPaydelete.do")
	public @ResponseBody Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
}

