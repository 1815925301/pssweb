package com.sinosoft.business.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.json.JSONObject;

import org.apache.commons.collections.map.HashedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sinosoft.base.po.ComboTree;
import com.sinosoft.business.po.PssTasMetadata;
import com.sinosoft.business.po.query.PssTasMetadataQuery;
import com.sinosoft.business.service.ProductService;
import com.sinosoft.business.service.PssTasMetadataService;
import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.DateTimeUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.common.util.ExportExcelForProduct;
@Controller
public class topicProductController {
	@Resource
	private PssTasMetadataService pssTasMetadataService;
	@Resource
	private ExportExcelForProduct ExportExcelForProduct;//
	
	private static final Logger LOGGER = LoggerFactory.getLogger(topicProductController.class);
	// 初始化专题产品查询页面
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/topicProductSearch.do")
	public String ProductManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssCollectInfo管理页面", method);
		model.addAttribute("pageHanName", "topicProductSearch"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
	}
	
	/*
	 * 查询专题产品
	 */
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = "/pssTasMetadataSearch.do")
	public @ResponseBody Map<String, Object> productSearch(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问Pss查询页面", method);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		pssTasMetadataService.getTasDataForInitPage(modelMap, method, request);
		return modelMap.getModelMap();
	}
	/** 
	 * 加入购物车
	 **/
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET}, value = "/addtopicProduct.do")
	public @ResponseBody Map<String, Object> addProduct(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		String productid=request.getParameter("productid");
		pssTasMetadataService.addTopicproduct(request, model,productid);
		return modelMap.getModelMap();
	}
	/**
	 * 专题产品定制跳转页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET}, value = "/topicAdd.do")
	public String topicAdd(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssCollectInfo管理页面", method);
		model.addAttribute("pageHanName", "topicAdd"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		return "manage/" + model.get("pageName");
		
	}
	/**
	 * 专题产品定制保存到购物车
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET}, value = "/saveTopicProduct.do")
	public @ResponseBody Map<String, Object> saveTopicProduct(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		pssTasMetadataService.saveshopCar(method,model,request);
		return modelMap.getModelMap();
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = { RequestMethod.POST,RequestMethod.GET}, value = "/showPssTasMetadata.do")
	public String showPssTasMetadata(HttpServletRequest request,ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityWebUtils.WrapperModle(request, model);
		PssTasMetadata product = pssTasMetadataService.getPssTasMetadata(request, model,"");
		model.addAttribute("product", product);
		return "manage/pssTasMetadata";
	}

	
	/**
	 * 导出excel（产品）
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/exporXlsproduct.do")
	public void exporXlsproduct(HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.debug("Access management page{OrderMainController, exporXls}");
		@SuppressWarnings("unused")
		ActivityModelMap modelMap = new ActivityModelMap(request);

		try {
			String sonOrderIds = request.getParameter("sonOrderIds");// 获取选择的订单id
			String flag = request.getParameter("flag");// 获取标识
			if (null != sonOrderIds && !"".equals(flag)) {// 判断id是否为null
				LOGGER.error(sonOrderIds);
				OutputStream out = response.getOutputStream();// 打开输出流
				response.setContentType("octets/stream");
				response.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ DateTimeUtils.getNowStrTimeStemp() + ".xls");
				@SuppressWarnings("unchecked")
				Map<String, Object> condMap = new HashedMap();
				condMap.put("productids", sonOrderIds);
				List<Map<String, Object>> orderList;
				List<PssTasMetadataQuery> orderList2;
				boolean flagB = true;
				if ("1".equals(flag)) {// 1 订单 2 购物车
					flagB = true;
				} else if ("2".equals(flag)) {// 1 订单 2 购物车
					flagB = false;
				}
				condMap.put("flag", flag);
				orderList = pssTasMetadataService.findProductByCond(condMap);
				ExportExcelForProduct.exporsOrderXls("productOrder", orderList, out,
						DateTimeUtils.YMDHMS, flagB);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			LOGGER.error(
					"OrderMainController.class method = exporXlsproduct throws Exception",
					e);
		}

	}
	/**
	 * 查询环境对应的下拉框的值
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	/*@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/selectbytype.do")
	public void  show(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String producttype=request.getParameter("type");
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)		
		String topictype=PropertiesUtils.key(producttype,locale);
		List<ComboTree> clist=new ArrayList<ComboTree>();
		//ComboTree combo=null;
		String[] ptypes=topictype.split(",");
		ComboTree combo = new ComboTree(); //ComboTree
		combo.setId(i);
		combo.setText(ptypes[i]);
		for (int i = 0; i < ptypes.length; i++) {
			ComboTree combo1= new ComboTree(); //ComboTree
			combo1.setId(i);
			combo1.setText(ptypes[i]);
			clist.add(combo);
			combo.setCombotreelist(clist);
		}
	    request.getSession().setAttribute(Constant.COMBOTREE, clist);
	    request.get
//		JSON.toJSON(clist);
//		jsonObject.putAll((Map) clist);  
		
	}*/
	/**下拉多选框
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getcombotree.do")
	@ResponseBody
	public JSON getCombotree(HttpServletRequest request,HttpServletResponse response){
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		String topictype=PropertiesUtils.key("bordheading",locale);
		String type[]=topictype.split(",");
		List<ComboTree> combotree1=new ArrayList<ComboTree>();//一级下拉集合
		//ComboTree	combo1=null;
		for (int i = 0; i < type.length; i++) {
			
			String ctype[]=type[i].split("#");
			String a1=ctype[0];
			String a2=ctype[1];
			ComboTree combo = new ComboTree(); //一级下拉内容
			combo.setId(a2);
			combo.setText(a2);
			String zitype=PropertiesUtils.key(a1,locale);
			String ptypes[]=zitype.split(",");
			List<ComboTree> combotree=new ArrayList<ComboTree>();//二级下拉集合
			 //ComboTree
			
			for (int j = 0; j < ptypes.length; j++) {
				String ztype[]=ptypes[j].split("#");
				ComboTree combo1=new ComboTree();//二级下拉内容
				combo1.setId(ztype[0]);
				combo1.setText(ztype[1]);
				combotree.add(combo1);//二级下拉内容加入到集合
				combo.setChildren(combotree);//递归形式把二级放到一级
			}	
			
			combotree1.add(combo);//一级内容加入集合
			
		}
		return (JSON) JSON.toJSON(combotree1);//转成JSON
		
		
		
	}
	 public static void writeFile(String filePath, String sets)  
	            throws IOException {  
	        FileWriter fw = new FileWriter(filePath);  
	        PrintWriter out = new PrintWriter(fw);  
	        out.write(sets);  
	        out.println();  
	        fw.close();  
	        out.close();  
	    }  
}
