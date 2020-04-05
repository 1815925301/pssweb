/*
 * Powered By 尹力
 * Since 2015 - 2016-53-18
 */

package com.sinosoft.business.controller;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
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

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.business.service.ProductService;
import com.sinosoft.business.service.PssCollectInfoService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.ZIP;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.business.po.PssCollectInfo;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
//import com.sinosoft.common.util.shp2Sde;

@Controller
public class ProductController {
	@Resource
	private ProductService productService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	/**
	 * 管理页面
	 */
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/product.do")
	public String ProductManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问PssCollectInfo管理页面", method);
		model.addAttribute("pageHanName", "Product"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
//		productService.getProductForInitPage(model, method, request);
		return "manage/" + model.get("pageName");
	}
	
	/*
	 * 查询
	 */
	@RequestMapping(method = {RequestMethod.POST,RequestMethod.GET}, value = "/productSearch.do")
	public @ResponseBody Map<String, Object> productSearch(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问Pss查询页面", method);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		productService.getProductForInitPage(modelMap, method, request);
		return modelMap.getModelMap();
	}
	
	/** 
	 * 对比产品详情
	 **/
	@RequestMapping(method = { RequestMethod.POST,RequestMethod.GET}, value = "/contProduct.do")
	public String contProduct(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityWebUtils.WrapperModle(request, model);
		model.addAttribute("pageName", "contproduct"); //页面名称
		productService.contProduct(request, model);
		return "manage/contproduct";
	}

	 
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = { RequestMethod.POST,RequestMethod.GET}, value = "/showProduct.do")
	public String showTaskSubTable(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityWebUtils.WrapperModle(request, model);
		productService.getProduct(request, model,"");
		return "manage/productManage";
	}
	
	/** 
	 * 加入购物车
	 **/
	@RequestMapping(method = { RequestMethod.POST}, value = "/addProduct.do")
	public @ResponseBody Map<String, Object> addProduct(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		productService.getProduct(request, model,"add");
		return modelMap.getModelMap();
	}
	/**
	 * 0级景订制加入购物车
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = { RequestMethod.POST}, value = "/addProductScene.do")
	public @ResponseBody Map<String, Object> addProductScene(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		productService.addSceneshopcar(request,model);
		return modelMap.getModelMap();
	}
	
	/*
	 * 导入shp 文件
	*/ 
	@RequestMapping(method = {RequestMethod.POST}, value = "/importShape.do")
	@ResponseBody
	public Object addfile(@RequestParam(value="file",required=false) MultipartFile files, HttpServletRequest request,HttpServletResponse response) throws Exception {

		String path = "";
		String filePath = "";
		// 设置响应给前台的数据格式
		// 设置响应给前台内容的PrintWriter对象
		// 这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
		String realPath = request.getSession().getServletContext().getRealPath(File.separator+"upload");
		
		long currentfile = System.currentTimeMillis();
		realPath = realPath + File.separator + currentfile;
		// 上传前文件的名字
		String originalFilename = files.getOriginalFilename();
		String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
		File dirPath=new File(realPath);	
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		File uploadFile=new File(realPath+ File.separator +originalFilename);
		try {
			FileCopyUtils.copy(files.getBytes(), uploadFile);
			ZIP.unZip(uploadFile, realPath);
			filePath = realPath + File.separator + path + fileName;
			path = realPath + File.separator + path + fileName + File.separator + ZIP.updatefile(realPath + File.separator + fileName, currentfile);
//			shp2Sde.importShp(filePath,fileName);
		} catch (Exception e) {  
			e.printStackTrace();
			return null;
		}
		Map<String, String> map = getMap(path, request);
		//导入sde空间库
		return map;
	}
	
//	@ResponseBody
//	@RequestMapping("getMap")
	public Map<String, String> getMap(String path,HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String filePath=request.getParameter("filePath");
		if(filePath!=null){
			path=filePath;
		}
		try {
			File targetFile = new File(path);
			ShapefileDataStore shpDataStore = null;
			shpDataStore = new ShapefileDataStore(targetFile.toURI().toURL());
			shpDataStore.setStringCharset(Charset.forName("GBK"));
			String typeName = shpDataStore.getTypeNames()[0];

			FeatureSource<SimpleFeatureType, SimpleFeature> featureSource = null;
			featureSource = (FeatureSource<SimpleFeatureType, SimpleFeature>) shpDataStore.getFeatureSource(typeName);
			FeatureCollection<SimpleFeatureType, SimpleFeature> result = featureSource.getFeatures();
			System.out.println(result.size());
			FeatureIterator<SimpleFeature> itertor = result.features();
			String lonlatstr = null;
			StringBuffer polySb = new StringBuffer();
			StringBuffer lineSb = new StringBuffer();
			StringBuffer pointSb = new StringBuffer();
			while (itertor != null && itertor.hasNext()) {
				SimpleFeature feature = itertor.next();
				Collection<Property> p = feature.getProperties();
				Iterator<Property> it = p.iterator();
				while (it.hasNext()) {
					Property pro = it.next();
					if (pro.getValue() instanceof MultiPolygon) {
						MultiPolygon shpStr = (MultiPolygon) pro.getValue();
						lonlatstr = shpStr.toString();
						int startIndex = lonlatstr.indexOf("(((") + 3;
						String lonlat = lonlatstr.substring(startIndex,	lonlatstr.length() - 3);
						polySb.append(lonlat + "@");
						break;
					}
					if (pro.getValue() instanceof MultiPoint) {
						MultiPoint shpStr = (MultiPoint) pro.getValue();
						lonlatstr = shpStr.toString();
						int startIndex = lonlatstr.indexOf("((") + 2;
						String lonlat = lonlatstr.substring(startIndex, lonlatstr.length() - 2);
						pointSb.append(lonlat + "@");
						break;
					}
					if (pro.getValue() instanceof MultiLineString) {
						MultiLineString shpStr = (MultiLineString) pro
								.getValue();
						lonlatstr = shpStr.toString();
						int startIndex = lonlatstr.indexOf("((") + 2;
						String lonlat = lonlatstr.substring(startIndex,	lonlatstr.length() - 2);
						lineSb.append(lonlat + "@");
						break;
					}
					if (pro.getValue() instanceof LinearRing) {

						LinearRing shpStr = (LinearRing) pro.getValue();
						lonlatstr = shpStr.toString();
						int startIndex = lonlatstr.indexOf("(") + 1;
						String lonlat = lonlatstr.substring(startIndex, lonlatstr.length() - 1);
						lineSb.append(lonlat + "@");
						break;
					}
					if (pro.getValue() instanceof Point) {
						Point shpStr = (Point) pro.getValue();
						lonlatstr = shpStr.toString();
						int startIndex = lonlatstr.indexOf("(") + 1;
						String lonlat = lonlatstr.substring(startIndex, lonlatstr.length() - 1);
						pointSb.append(lonlat + ",");
						break;
					}
				}
			}
			itertor.close();
			if (polySb.length() > 0) {
				polySb.deleteCharAt(polySb.length() - 1);
				map.put("poly", polySb.toString());
			}
			if (pointSb.length() > 0) {
				pointSb.deleteCharAt(pointSb.length() - 1);
				
				map.put("point", pointSb.toString());
			}
			if (lineSb.length() > 0) {
				lineSb.deleteCharAt(lineSb.length() - 1);
				map.put("line", lineSb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			map = null;
		}
		return map;
	}
	
	/** 
	 * 保存新增对象
	 **/
	

	/**
	 * 根据指定的采集单id 获取采集单信息
	 * @param Id
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 */
	
	
	/**
	 * 保存更新对象
	 **/
	
	
	/**
	 *删除对象
	 **/
	
	
}

