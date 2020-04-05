package com.sinosoft.business.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.ProductDao;
import com.sinosoft.business.dao.PssMetadata0sceneDao;
import com.sinosoft.business.dao.PssShopCarDao;
import com.sinosoft.business.po.PssMetadata0scene;
import com.sinosoft.business.po.PssProduct;
import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.po.query.PssProductQuery;
import com.sinosoft.business.service.ProductService;
import com.sinosoft.business.uilt.ShopCarUilt;
import com.sinosoft.common.util.ActivityStringUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;




@Service("productService")
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Resource
	private ProductDao productDao;
	@Resource
	private PssShopCarDao pssshopcarDao;
	@Resource
	private PssMetadata0sceneDao metadata0sceneDao;


	@Override
	public void ProductPageInit(ModelMap model, String method,
			HttpServletRequest request) { }
 
	/**
	 * 添加到购物车
	 */
	@Override
	public void getProduct(HttpServletRequest request,
			ModelMap model,String stats) { 
		String method = request.getMethod();
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		PssProductQuery productquery=new PssProductQuery();
		PssProduct product=new PssProduct();
		if(stats.equals("add")){
		String pid=request.getParameter("productid");
		String[] productpro=pid.split(",");
		String productlevel="";
		for (int i = 0; i < productpro.length; i++) {
			String[] pro=productpro[i].split("@");
		    	ShopCarUilt shop=new ShopCarUilt();
		    			productquery.setMetadataid(pro[0]);
		    			if( i==0){
		    				productlevel=pro[1];
		    			}
		    			
		    			
		    			if(productlevel.equals("LEVEL1")){
		    				
		    				productquery.setTablename("PSS_METADATA_1");
		    			}
		    			
		    			if(productlevel.equals("LEVEL0")){
		    				productquery.setTablename("PSS_METADATA_0");
		    			}
		    			if(productlevel.equals("LEVEL2A")){
		    				productquery.setTablename("PSS_METADATA_2A");
		    			}
		    			if(productlevel.equals("LEVEL2B")){
		    				productquery.setTablename("PSS_METADATA_2B");
		    			}
		    			if(productlevel.equals("LEVEL3A")){
		    				productquery.setTablename("PSS_METADATA_3A");
		    			}
		    			if(productlevel.equals("LEVEL3B")){
		    				productquery.setTablename("PSS_METADATA_3B");
		    			}
		    			if(productlevel.equals("LEVEL4")){
		    				productquery.setTablename("PSS_METADATA_4");
		    			}
		    			
		    			product=productDao.selectProductById(productquery);
					    Date day=new Date();   
					    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
					    String date_car = df.format(day);
					    product.setAddshopcardate(date_car);
					    model.addAttribute("product", product);
				    	PssShopCar pssshopcar=shop.getPssShopCar(product);
				    	pssshopcar.setUsername(username);
				    	pssshopcar.setBrowsefilelocation("imgnew/img03.gif");
				    	pssshopcarDao.insertPssShopCar(pssshopcar);
					
		}
				
		}else{
			//点击拇指图查看详情
			String productid=request.getParameter("productid");
			String productlevel=request.getParameter("productlevel");
			productquery.setProductid(productid);
			if(productlevel.equals("LEVEL1")){
				
				productquery.setTablename("PSS_METADATA_1");
			}
			if(productlevel.equals("LEVEL0")){
				productquery.setTablename("PSS_METADATA_0");
			}
			if(productlevel.equals("LEVEL2A")){
				productquery.setTablename("PSS_METADATA_2A");
			}
			if(productlevel.equals("LEVEL2B")){
				productquery.setTablename("PSS_METADATA_2B");
			}
			if(productlevel.equals("LEVEL3A")){
				productquery.setTablename("PSS_METADATA_3A");
			}
			if(productlevel.equals("LEVEL3B")){
				productquery.setTablename("PSS_METADATA_3B");
			}
			if(productlevel.equals("LEVEL4")){
				productquery.setTablename("PSS_METADATA_4");
			}
			
			product=productDao.selectProductById(productquery);
			model.addAttribute("product", product);
		}
		
	}
	

	public void getProductForInitPage(ActivityModelMap modelMap, String method,
			HttpServletRequest request) { 
		LOGGER.debug("Service层：根据查询参数获取信息 用于采集单管理页面");
		PssProductQuery productquery = new PssProductQuery();
		productquery.setSortType("1");
			//先判断产品级别,不同级别对应不同的表名
			String productLevel = request.getParameter("productlevel");//产品级别
			if(null!=productLevel && !"".equals(productLevel)){
				if(productLevel.equals("LEVEL1")){
					productquery.setTablename("PSS_METADATA_1");
				}
				if(productLevel.equals("LEVEL2A")){
					productquery.setTablename("PSS_METADATA_2A");
				}
				if(productLevel.equals("LEVEL2B")){
					productquery.setTablename("PSS_METADATA_2B");
				}
				if(productLevel.equals("LEVEL3A")){
					productquery.setTablename("PSS_METADATA_3A");
				}
				if(productLevel.equals("LEVEL3B")){
					productquery.setTablename("PSS_METADATA_3B");
				}
				if(productLevel.equals("LEVEL4")){
					productquery.setTablename("PSS_METADATA_4");
				}
				if(productLevel.equals("LEVEL0")){
					productquery.setTablename("PSS_METADATA_0");
				}
				if(productLevel.equals("LEVEL0_SCENE")){
					productquery.setTablename("PSS_METADATA_0_SCENE");
				}
			}
			//产品号-景号-轨道号
			String productStrs = request.getParameter("productids");//产品号
			String sceneidStrs = request.getParameter("sceneids");//景号
			String orbitid = request.getParameter("orbitids");//轨道号
			
			// path/row
			String minPath = request.getParameter("minPath");//最小path
			String maxPath = request.getParameter("maxPath");//最大path
			String minRow = request.getParameter("minRow");//最小row
			String maxRow = request.getParameter("maxRow");//最大row
			
			//基本属性
			String quality = request.getParameter("quality");//质量
			String mingsd = request.getParameter("mingsd");//最小分辨率
			String maxgsd = request.getParameter("maxgsd");//最大分辨率
			String sceneStarttime = request.getParameter("sceneStarttime");//采集开始时间
			String sceneEndtime = request.getParameter("sceneEndtime");//采集结束时间
			String mincloudCoverage = request.getParameter("mincloudCoverage");//最小云盖
			String maxcloudCoverage = request.getParameter("maxcloudCoverage");//最大云盖
			String sensorid = request.getParameter("sensorid");//传感器
			String scenepath = request.getParameter("scenepath");//子path
			String scenerow = request.getParameter("scenerow");//子row
			String productStarttime = request.getParameter("productStarttime");//生产开始时间
			String productEndtime = request.getParameter("productEndtime");//生产结束时间 
			
			String geometryQuery = request.getParameter("geometryQuery");
		/*	//经纬度
			String leftuplong = request.getParameter("leftuplong");//右上经度
			String leftuplat = request.getParameter("leftuplat");//右上纬度
			String rightdownlong =  request.getParameter("rightdownlong");//右下经度
			String rightdownlat =  request.getParameter("rightdownlat");//右下纬度
*/			
			//行政区划
			String country = request.getParameter("country");//国家
			String states = request.getParameter("states");//州
			
			//shape文件
			String shpName = request.getParameter("shpName");//shape文件
			
			//先总体判断
			if((null!=productStrs && !"".equals(productStrs)) || (null!=sceneidStrs && !"".equals(sceneidStrs)) || (null!=orbitid && !"".equals(orbitid))){
				if(null!=productStrs && !"".equals(productStrs)){
					String[] aa = productStrs.split(",");
					List<String> productid_array  = new ArrayList<String>();
					for(int i=0;i<productStrs.split(",").length;i++){
						productid_array.add(aa[i]);
					}
					productquery.setProductid_array(productid_array);
					putModelMap(modelMap,productquery,"basic");//查询存值
				}else if(null!=sceneidStrs && !"".equals(sceneidStrs)){
					String[] aa = sceneidStrs.split(",");
					List<String> sceneid_array  = new ArrayList<String>();
					for(int i=0;i<sceneidStrs.split(",").length;i++){
						sceneid_array.add(aa[i]);
					}
					productquery.setSceneid_array(sceneid_array);
					putModelMap(modelMap,productquery,"basic");//查询存值
				}else if(null!=orbitid && !"".equals(orbitid)){
					String [] aa = orbitid.split(",");
					List<Integer> orbitid_array  = new ArrayList<Integer>();
					for(int i=0;i<orbitid.split(",").length;i++){
						orbitid_array.add(Integer.valueOf(aa[i]));
					}
					productquery.setOrbitid_array(orbitid_array);
					putModelMap(modelMap,productquery,"basic");//查询存值
				}
			}else{
				//先判断基本属性条件
				if(null!=quality && !"".equals(quality)){
					productquery.setQuality(Integer.valueOf(quality));
				}
				if(null!=mingsd && !"".equals(mingsd)){
					productquery.setMingsd(Integer.parseInt(mingsd));
				}
				if(null!=maxgsd && !"".equals(maxgsd)){
					productquery.setMaxgsd(Integer.parseInt(maxgsd));
				}
				if(null!=sceneStarttime && !"".equals(sceneStarttime)){
					productquery.setSceneStarttime(sceneStarttime);
				}
				if (null!=sceneEndtime && !"".equals(sceneEndtime)){
					
					productquery.setSceneEndtime(sceneEndtime);
				}
				if(null!=mincloudCoverage && !"".equals(mincloudCoverage)){
					productquery.setMincloudCoverage(Integer.valueOf(mincloudCoverage));
				}
				if(null!=maxcloudCoverage && !"".equals(maxcloudCoverage)){
					
					productquery.setMaxcloudCoverage(Integer.valueOf(maxcloudCoverage));
				}
				if(null!=sensorid && !"".equals(sensorid)){
					String[] aa = sensorid.split(",");
					List<String> sensorid_array  = new ArrayList<String>();
					for(int i=0;i<sensorid.split(",").length;i++){
						sensorid_array.add(aa[i]);
					}
					productquery.setSensorid_array(sensorid_array);
				}
				if(null!=scenepath && !"".equals(scenepath)){
					productquery.setScenepath(scenepath);
				}
				if(null!=scenerow && !"".equals(scenerow)){
					productquery.setScenepath(scenerow);
				}
				if(null!=productStarttime && !"".equals(productStarttime)){
					productquery.setProductStarttime(productStarttime);
				}
				if(null!=productEndtime && !"".equals(productEndtime)){
					
					productquery.setProductEndtime(productEndtime);
				}
				
				//分开判断：1.path/row
				if((null!=minPath && !"".equals(minPath)) || (null!=maxPath && !"".equals(maxPath)) || (null!=minRow && !"".equals(minRow)) || (null!=maxRow && !"".equals(maxRow))){
					if(null!=minPath && !"".equals(minPath)){
						productquery.setMinPath(Integer.valueOf(minPath));
					}
					if(null!=maxPath && !"".equals(maxPath)){
						productquery.setMaxPath(Integer.valueOf(maxPath));
					}
					if(null!=minRow && !"".equals(minRow)){
						productquery.setMinRow(Integer.valueOf(minRow));
					}
					if(null!=maxRow && !"".equals(maxRow)){
						productquery.setMaxRow(Integer.valueOf(maxRow));
					}
					putModelMap(modelMap,productquery,"basic");//查询存值
				}else if(null!=geometryQuery && !"".equals(geometryQuery)){
					String str_shape = ActivityStringUtils.replaceComn2Space(geometryQuery);
					String shape = "polygon(("+str_shape+"))";
					productquery.setShape(shape);
					putModelMap(modelMap,productquery,"basic");//查询存值
				}else if(null!=shpName && !"".equals(shpName)){
					productquery.setShapename(shpName);
					putModelMap(modelMap,productquery,"shapename");//查询存值
				}else if(null!=states && !"".equals(states)){
					productquery.setStates(states);
					putModelMap(modelMap,productquery,"world");//查询存值
				}else if(null!=country && !"".equals(country)){
					productquery.setCountry(country);
					putModelMap(modelMap,productquery,"world");//查询存值
				}else{
					putModelMap(modelMap,productquery,"basic");//查询存值
				}
					
			}		
			
			}
	/**
	 * 抽取共用的代码，方便调用
	 * @param modelMap
	 * @param productquery
	 */
	public void putModelMap(ActivityModelMap modelMap,PssProductQuery productquery,String difference){
		if(difference.equals("basic")){
			productquery.setDifference_table("basic");
		}
		if(difference.equals("shapename")){
			productquery.setDifference_table("shapename");
		}
		if(difference.equals("world")){
			productquery.setDifference_table("world");
		}
		List<PssProductQuery> productList = productDao.getProductByQuery(productquery);
		modelMap.put("test1", "1");
		modelMap.put("productList", productList);
	}
	/**
	 * 通过ID查询详情
	 */
	@Override
	public void getProductinfo(HttpServletRequest request, ModelMap model) {
		String productid=request.getParameter("productid");
		PssProductQuery productquery = new PssProductQuery();
		productquery.setProductid(productid);
		PssProduct product=productDao.selectProductById(productquery);
		model.addAttribute("product", product);
		
	}
	/**
	 *0级景定制
	 */
	@Override
	public void addSceneshopcar(HttpServletRequest request, ModelMap model) {
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		String gcp=request.getParameter("gcp");
		
		String productlevel=request.getParameter("productlevel");
		String id=request.getParameter("id");
		
		PssProductQuery productQuery=new PssProductQuery();
		PssMetadata0scene metadata0scene=new PssMetadata0scene();
		PssShopCar shopCar=new PssShopCar();
		String str[] = id.split(",");
		for (int i = 0; i < str.length; i++) {
			
			metadata0scene=metadata0sceneDao.getScensByqueryById(str[i]);
			Date day=new Date();   
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		    String date_car = df.format(day);
		   
		   
			if(gcp!=null && !gcp.equals("")){
				shopCar.setGcp(Integer.parseInt(gcp));
			}
			shopCar.setStationid(metadata0scene.getRecstation());//接收站
			shopCar.setBands(metadata0scene.getBands());//定制波段数
			
			shopCar.setL0dataid(metadata0scene.getL0dataid());//0级条带数据
			shopCar.setOrbitid(metadata0scene.getOrbitid());//成像圈号
			shopCar.setScenepath(metadata0scene.getScenepath());//景Path
			shopCar.setScenerow(metadata0scene.getScenerow());//景row
			
			shopCar.setScenepathbias(metadata0scene.getScenepathbias());//景子path
			shopCar.setScenerowbias(metadata0scene.getScenerowbias());//景子row
			shopCar.setEarthmodel("WGS_84");//地球模型
			shopCar.setMapprojection("UTM");//地图投影
			shopCar.setResampletechnique("NN");//重采样方式
			shopCar.setScenecount(1);//景数目
			shopCar.setSceneshift(0);//景漂移
			shopCar.setPgproductformat("GEOTIFF");
			shopCar.setSatelliteid(metadata0scene.getSatelliteid());
			shopCar.setSensorid(metadata0scene.getSensorid());
			shopCar.setProductlevel(productlevel);
			shopCar.setProducttype("Standard");
			shopCar.setOrdertype(1);
			shopCar.setProductid(metadata0scene.getProductid());
			shopCar.setSceneid(metadata0scene.getSceneid());
			shopCar.setBrowsefilelocation("imgnew/img03.gif");
			shopCar.setDataupperleftlat(Double.parseDouble(metadata0scene.getUpperleftlat()));
			shopCar.setDataupperleftlong(Double.parseDouble(metadata0scene.getUpperleftlong()));
			shopCar.setDataupperrightlat(Double.parseDouble(metadata0scene.getUpperrightlat()));
			shopCar.setDataupperrightupperlong(Double.parseDouble(metadata0scene.getUpperrightlong()));
			shopCar.setDatalowerleftlat(Double.parseDouble(metadata0scene.getLowerleftlat()));
			shopCar.setDatalowerleftlong(Double.parseDouble(metadata0scene.getLowerleftlong()));
			shopCar.setDatalowerrightlat(Double.parseDouble(metadata0scene.getLowerrightlat()));
			shopCar.setDatalowerrightlong(Double.parseDouble(metadata0scene.getLowerrightlong()));
			shopCar.setProductcate(1);
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
			try {
				shopCar.setOrderdate(sim.parse(date_car));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shopCar.setUsername(username);
			pssshopcarDao.insertPssShopCar(shopCar);
		}
		
	}
	/**
	 * 对比详情
	 */
	@Override
	public void contProduct(HttpServletRequest request, ModelMap model) {
		PssProduct pre=null;
		List<PssProduct> plist=new ArrayList<PssProduct>();
		String pid=request.getParameter("pid");
		String str[]=pid.split(",");
		String productlevel="";
		PssProductQuery productQuery=new PssProductQuery();
		for(int i=0;i<str.length;i++){
			String product[]=str[i].split("@");
			String mateid=product[0];
			productlevel=product[1];
			productQuery.setMetadataid(mateid);
			if(productlevel.equals("LEVEL1")){
				productQuery.setTablename("PSS_METADATA_1");
			}
			if(productlevel.equals("LEVEL2A")){
				productQuery.setTablename("PSS_METADATA_2A");
			}
			if(productlevel.equals("LEVEL2B")){
				productQuery.setTablename("PSS_METADATA_2B");
			}
			if(productlevel.equals("LEVEL3A")){
				productQuery.setTablename("PSS_METADATA_3A");
			}
			if(productlevel.equals("LEVEL3B")){
				productQuery.setTablename("PSS_METADATA_3B");
			}
			if(productlevel.equals("LEVEL4")){
				productQuery.setTablename("PSS_METADATA_4");
			}
			if(productlevel.equals("LEVEL0")){
				productQuery.setTablename("PSS_METADATA_0");
			}
			pre= productDao.selectProductById(productQuery);
			plist.add(pre);
		}
		
		
		model.addAttribute("plist", plist);
		
		
	}	
}
