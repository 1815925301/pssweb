

package com.sinosoft.business.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;

import com.sinosoft.business.dao.PssShopCarDao;
import com.sinosoft.business.dao.PssTasMetadataDao;

import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.po.PssTasMetadata;

import com.sinosoft.business.po.query.PssTasMetadataQuery;
import com.sinosoft.business.service.PssTasMetadataService;
import com.sinosoft.common.util.ActivityStringUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;



@Service("PssTasMetadataService")
public class PssTasMetadataServiceImpl implements PssTasMetadataService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PssTasMetadataServiceImpl.class);
	@Resource
	private PssTasMetadataDao pssTasMetadataDao;
	@Resource
	private PssShopCarDao pssshopcarDao;
	// 初始化专题产品查询页面
	@Override
	public void getTasDataForInitPage(ActivityModelMap modelMap, String method,
			HttpServletRequest request) { 
		LOGGER.debug("Service层：根据查询参数获取信息 用于显示专题产品查询页面");
		PssTasMetadataQuery pssTasMetadataquery = new PssTasMetadataQuery();
		pssTasMetadataquery.setSortType("1");
		String sensorid = request.getParameter("sensorid");//传感器
		String producttype = request.getParameter("producttype");//产品类型
		
		//行政区划
		String country = request.getParameter("country");//国家
		String states = request.getParameter("states");//州
		
		//shape文件
		String shpName = request.getParameter("shpName");//shape文件
		
		String geometryQuery = request.getParameter("geometryQuery");
		
		if(null!=sensorid && !"".equals(sensorid)){
			String[] aa = sensorid.split(",");
			List<String> sensorid_array  = new ArrayList<String>();
			for(int i=0;i<sensorid.split(",").length;i++){
				sensorid_array.add(aa[i]);
			}
			pssTasMetadataquery.setSensorid_array(sensorid_array);
		}
		if(null!=producttype && !"".equals(producttype)){
			String[] aa = producttype.split(",");
			List<String> producttype_array  = new ArrayList<String>();
			for(int i=0;i<aa.length;i++){
				producttype_array.add(aa[i]);
			}
			pssTasMetadataquery.setProducttypes_array(producttype_array);
			
		}
		if(null!=geometryQuery && !"".equals(geometryQuery)){
			String str_shape = ActivityStringUtils.replaceComn2Space(geometryQuery);
			String shape = "polygon(("+str_shape+"))";
			pssTasMetadataquery.setShape(shape);
			putModelMap2(modelMap,pssTasMetadataquery,"basic");//查询存值
		}else if(null!=shpName && !"".equals(shpName)){
			pssTasMetadataquery.setShapename(shpName);
			putModelMap2(modelMap,pssTasMetadataquery,"shapename");//查询存值
		}else if(null!=states && !"".equals(states)){
			pssTasMetadataquery.setStates(states);
			putModelMap2(modelMap,pssTasMetadataquery,"world");//查询存值
		}else if(null!=country && !"".equals(country)){
			pssTasMetadataquery.setCountry(country);
			putModelMap2(modelMap,pssTasMetadataquery,"world");//查询存值
		}else{
			putModelMap2(modelMap,pssTasMetadataquery,"basic");//查询存值
		}
	}
	
	/**
	 * 抽取共用的代码，方便调用
	 * @param modelMap
	 * @param productquery
	 */
	public void putModelMap2(ActivityModelMap modelMap,PssTasMetadataQuery pssTasMetadataquery,String difference){
		if(difference.equals("basic")){
			pssTasMetadataquery.setDifference_table("basic");
		}
		if(difference.equals("shapename")){
			pssTasMetadataquery.setDifference_table("shapename");
		}
		if(difference.equals("world")){
			pssTasMetadataquery.setDifference_table("world");
		}
		List<PssTasMetadataQuery> productList = pssTasMetadataDao.getPssTasMetadataByQuery(pssTasMetadataquery);
		modelMap.put("test1", "1");
		modelMap.put("productList", productList);
	}

	@Override
	public void getTopproduct(HttpServletRequest request, ModelMap model,
			String productid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTopicproduct(HttpServletRequest request, ModelMap model,
			String productid) {
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		PssTasMetadataQuery metadataQuery=new PssTasMetadataQuery();
		if(productid!=null&&!"".equals(productid)){
			String[] str=productid.split(",");
			for(int i = 0; i < str.length; i++){
				metadataQuery.setMetadataid(str[i]);
				PssTasMetadata tasmetadata =pssTasMetadataDao.getTasMetadataByid(metadataQuery);
				
				PssShopCar pssShopCar=new PssShopCar();
				pssShopCar.setSatelliteid(tasmetadata.getSatelliteid());
				pssShopCar.setSensorid(tasmetadata.getSensorid());
				pssShopCar.setOrdertype(2);
				pssShopCar.setProducttype(tasmetadata.getProducttype());
				pssShopCar.setProductid(tasmetadata.getProductid());
				pssShopCar.setUsername(username);
				pssShopCar.setDataupperleftlat(tasmetadata.getDataupperleftlat());
				pssShopCar.setDataupperleftlong(tasmetadata.getDataupperleftlong());
				pssShopCar.setDataupperrightlat(tasmetadata.getDataupperrightlat());
				pssShopCar.setDataupperrightupperlong(tasmetadata.getDataupperrightlong());
				pssShopCar.setDatalowerleftlat(tasmetadata.getDatalowerleftlat());
				pssShopCar.setDatalowerleftlong(tasmetadata.getDatalowerleftlong());
				pssShopCar.setDatalowerrightlat(tasmetadata.getDatalowerrightlat());
				pssShopCar.setDatalowerrightlong(tasmetadata.getDatalowerrightlong());
				pssShopCar.setBrowsefilelocation("imgnew/img03.gif");
				pssShopCar.setProductcate(2);
				Date day=new Date();   
			    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
			    String date_car = df.format(day);
				try {
					pssShopCar.setOrderdate(df.parse(date_car));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				pssshopcarDao.insertPssShopCar(pssShopCar);
				
			}
		}
		
	}
	/**
	 * 专题产品定制放入购物车
	 */
	@Override
	public void saveshopCar(String method, ModelMap model,
			HttpServletRequest request) {
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		
		String userPhone = eUser.getUserPhone();
		String userEmail = eUser.getUserEmail();
		String companyAddress = eUser.getCompanyAddress();
		String earthmodel = "WGS_84";	//地球模型
		String mapprojection = "UTM";
		
		Double leftuplong = Double.parseDouble(request.getParameter("leftuplong"));
		Double leftuplat = Double.parseDouble(request.getParameter("leftuplat"));
		Double rightdownlong = Double.parseDouble(request.getParameter("rightdownlong"));
		Double rightdownlat = Double.parseDouble(request.getParameter("rightdownlat"));
		String mingsd = request.getParameter("mingsd");
		String maxgsd = request.getParameter("maxgsd");
		String imagingstarttime = request.getParameter("imagingstarttime");
		String imagingstoptime = request.getParameter("imagingstoptime");
		String remark = request.getParameter("remark");

		String producttype=request.getParameter("producttype");
		String sateSen=request.getParameter("sensorid");
		String sen[]=sateSen.split("_");
		String satelliteid=sen[0];
		String sensorid=sen[1];
		PssShopCar shopCar=new PssShopCar();
		
		shopCar.setTelmobile(userPhone);
		shopCar.setEmail(userEmail);
		shopCar.setDepartment(companyAddress);
		shopCar.setSpatialmax(maxgsd);
		shopCar.setSpatialmin(mingsd);
		shopCar.setImagedatebegin(imagingstarttime);
		shopCar.setImagedateend(imagingstoptime);
		shopCar.setRemark(remark);
		shopCar.setEarthmodel(earthmodel);
		shopCar.setMapprojection(mapprojection);
		
		shopCar.setDataupperleftlong(leftuplong);
		shopCar.setDatalowerleftlong(leftuplong);
		
		shopCar.setDataupperleftlat(leftuplat);
		shopCar.setDataupperrightlat(leftuplat);
		
		shopCar.setDataupperrightupperlong(rightdownlong);
		shopCar.setDatalowerrightlong(rightdownlong);
		
		shopCar.setDatalowerrightlat(rightdownlat);
		shopCar.setDatalowerleftlat(rightdownlat);
		
		shopCar.setOrdertype(1);
		shopCar.setProducttype(producttype);
		shopCar.setSatelliteid(satelliteid);
		shopCar.setSensorid(sensorid);
		shopCar.setUsername(username);
		shopCar.setProductcate(2);
		shopCar.setBrowsefilelocation("imgnew/img03.gif");
		Date day=new Date();   
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
	    String date_car = df.format(day);
		try {
			shopCar.setOrderdate(df.parse(date_car));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pssshopcarDao.insertPssShopCar(shopCar);
		
	}

	@Override
	public PssTasMetadata getPssTasMetadata(HttpServletRequest request,
			ModelMap model, String string) {
		PssTasMetadata pssTasMetadata = new PssTasMetadata();
		//点击拇指图查看Level0Scene详情
		String productid=request.getParameter("productid");
		pssTasMetadata.setProductid(productid);
		pssTasMetadata = pssTasMetadataDao.selectTasPssMetadataById(pssTasMetadata);
		return pssTasMetadata;
	}	
	
	
	/***
	 * 导出excel（产品）
	 */
	@SuppressWarnings("null")
	public List<Map<String, Object>> findProductByCond(Map<String, Object> condMap) {
		LOGGER.debug("OrderMainServiceImpl.class method = findProductByCond");
		List<Map<String, Object>> orderList1 = new ArrayList<Map<String, Object>>();

		String sonOrderIds = (String) condMap.get("productids");// 获取产品的id
		if (null == sonOrderIds && "".equals(sonOrderIds.trim())) {// 当sonOrderIds为“”或null时返回null
			return null;
		}
//		List<PssTasMetadataQuery> orderList;
//		PssTasMetadata pssTasMetadata = new PssTasMetadata();
//		pssTasMetadata.setProductid(sonOrderIds);
		Long isdon= Long.parseLong(sonOrderIds);
		String flag = (String) condMap.get("flag");
		if ("1".equals(flag)) {// 
			orderList1 = pssTasMetadataDao.findSonProductByCond(sonOrderIds);
//			String orderMainId;// 主订单id
//			for (Map<String, Object> orderMain : orderList) {
//				orderMainId = String.valueOf(orderMain.get("ORDER_MAIN_ID"));// 获取主订单id
//				condMap.put("orderMainId", orderMainId);
//				orderMain.put(orderMainId,
//						orderInfoDao.findSonOrderByCond(condMap));
//			}
		} else if ("2".equals(flag)) {// 1 订单 2 购物车
//			orderList = pssShopCarDao.findPssShopCarInfoById(sonOrderIds);
		}

		return orderList1;
	}
	
}
