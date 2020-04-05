
package com.sinosoft.business.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.po.PssProductprice;
import com.sinosoft.business.po.PssVersionnum;
import com.sinosoft.business.service.PssProductpriceService;
import com.sinosoft.business.service.PssVersionnumService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.business.controller
 * @ClassName: PssOrderInfoController
 * @Description: 产品价格信息  MVC控制层web入口
 * @author wlg
 * @Version V1.0
 * @date 2016-08-05
 */
@Controller
public class PssProductpriceController {
	@Resource
	private PssVersionnumService versionnumService;
	@Resource
	private PssProductpriceService productpriceService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PssProductpriceController.class);
	
	/**
	 * 产品价格管理页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/PssProductprice.do")
	public String PssProductpriceManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssProductprice管理页面", method);
		model.addAttribute("pageHanName", "PssProductprice"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		versionnumService.PssVersionnumPageInit(request, model, method);
		return "manage/" + model.get("pageName");
	}
	
	/**
	 * 产品价格查看
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/selectproductprice.do")
	public Map<String, Object>  showPssProductprice(
			HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		String versionnum=request.getParameter("versionnum");
		ActivityWebUtils.WrapperModle(request, model);
		List<PssProductprice> productpriceList=productpriceService.getProductpriceByVersion(versionnum);
		modelMap.put("productpriceList", productpriceList);
		return modelMap.getModelMap();
	}
	
	@RequestMapping(method = { RequestMethod.POST,RequestMethod.GET}, value = "/addProductprice.do")
	public String addProductprice(HttpServletRequest request, ModelMap model){
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityWebUtils.WrapperModle(request, model);

		return "manage/addproductprice";
	}

	/**
	 * 产品价格保存新增
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewProductPrice.do")
	public  @ResponseBody Map<String, Object> saveNewProductPrice(
			HttpServletRequest request,HttpServletResponse response){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		PssProductprice productprice=new PssProductprice();
		PssVersionnum versionnum =new PssVersionnum();
		String values=request.getParameter("values");
		String[] str=values.split("@");
		try{
		for(int i=0;i<str.length;i++){
			String[] price=str[i].split("#");

			productprice.setProducttype(price[0]);

			productprice.setProducttype("standard");

			productprice.setProductlevel(price[1]);
			productprice.setProductarea(Long.parseLong(price[2]));
			productprice.setPrice(Long.parseLong(price[3]));
			productprice.setVersionnum(price[4]);
			versionnum.setVersionnum(price[4]);
			versionnum.setIsaudit(new Long(3));
			versionnum.setIsornoteffect(new Long(2));
			if(i==0){
				versionnumService.save(request, versionnum, modelMap);
			}
			productpriceService.save(request, productprice, modelMap);
			modelMap.put("status", "success");
			modelMap.put("data", "新增产品价格成功！");
		}
		}catch(Exception e) {
			LOGGER.error("出现异常：{}", e);
			modelMap.put("status", "error");
		}
		return modelMap.getModelMap();
	}
	/**
	 * 产品价格审核操作
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/updateisaudit.do")
	public  @ResponseBody Map<String, Object> updateisaudit(
			HttpServletRequest request,HttpServletResponse response,PssVersionnum pssVersionnum){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		
		try{
			pssVersionnum.setCheckname(eUser.getUserName());
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pssVersionnum.setChecktime(df.format(new Date()));
			versionnumService.update(request, pssVersionnum, modelMap);
			modelMap.put("status", "success");
			modelMap.put("data", "审核操作成功！");
		
		}catch(Exception e) {
			LOGGER.error("出现异常：{}", e);
			modelMap.put("status", "error");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 产品价格发布
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/Productpriceupdate.do")
	public @ResponseBody Map<String, Object>  update(
			HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			String versionnum=request.getParameter("versionnum");
			PssVersionnum pssversionnum=new PssVersionnum();
			pssversionnum.setIsornoteffect(new Long(1));
			pssversionnum.setVersionnum(versionnum);
			versionnumService.update(request,pssversionnum , modelMap);
			modelMap.put("status", "success");
			modelMap.put("data", "发布成功！");
		
		} catch(Exception e) {
			LOGGER.error("出现异常：{}", e);
			modelMap.put("status", "error");
		}
		return modelMap.getModelMap();
	}
	/**
	 * 产品价格取消发布
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author wlg
	 * @date 2016-08-05 
	 * @version V1.0
	 * @throws IOException 
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/cancelrelease.do")
	public @ResponseBody Map<String, Object>  cancelrelease(
			HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			String versionnum=request.getParameter("versionnum");
			PssVersionnum pssversionnum=new PssVersionnum();
			pssversionnum.setIsornoteffect(new Long(2));
			pssversionnum.setVersionnum(versionnum);
			versionnumService.update(request,pssversionnum , modelMap);
			modelMap.put("status", "success");
			modelMap.put("data", "发布成功！");
		
		} catch(Exception e) {
			LOGGER.error("出现异常：{}", e);
			modelMap.put("status", "error");
		}
		return modelMap.getModelMap();
	}
	


	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/selectvaluebyid.do")
	public void  show(HttpServletRequest request,HttpServletResponse response) throws IOException {
		JSONObject jsonObj = new JSONObject();
		ActivityModelMap modelMap = new ActivityModelMap(request);
		String selectid=request.getParameter("selectid");
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)		
		String producttype=PropertiesUtils.key(selectid,locale);
		Map<String,String> ll=new HashMap<String,String>();
		String[] ptypes=producttype.split(",");
		for (int i = 0; i < ptypes.length; i++) {
			String[] ss=ptypes[i].split("_");
			ll.put(ss[0], ss[1]);
		}
		jsonObj.putAll(ll);
		response.getWriter().write(jsonObj.toString());//json字符串
	}
	
}

