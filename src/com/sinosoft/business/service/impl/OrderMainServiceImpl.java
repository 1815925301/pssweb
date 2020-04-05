package com.sinosoft.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.OrderMainDao;
import com.sinosoft.business.dao.ProductDao;
import com.sinosoft.business.dao.PssOrderInfoDao;
import com.sinosoft.business.dao.PssShopCarDao;
import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.PssProduct;
import com.sinosoft.business.po.query.PssOrderInfoQuery;
import com.sinosoft.business.po.query.PssProductQuery;
import com.sinosoft.business.service.OrderMainService;
import com.sinosoft.common.constant.Constant.ORDER_BY;
import com.sinosoft.common.util.SystemConfigUtil;

/**
 * 订单Service层实现
 * 
 * @author Dylan
 * @date 16-08-24 18:28:27
 */
@Service("orderMainService")
public class OrderMainServiceImpl implements OrderMainService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderMainServiceImpl.class);

	@Resource
	private OrderMainDao orderMainDao; // 主订单dao层对象
	@Resource
	private PssOrderInfoDao orderInfoDao; // 子订单dao层对象
	@Resource
	private PssShopCarDao pssShopCarDao;// 购物车Dao接口
	@Resource
	private ProductDao productDao;// 1级产品表dao层对象
	/**
	 * 订单页面初始化
	 */
	String orderstatenl;
	public void initOrderPage(ModelMap model, String method,
			HttpServletRequest request) {
		LOGGER.debug("OrderMainServiceImpl.class method = initOrderPage");
		PssOrderInfoQuery pssOrderInfoQuery = new PssOrderInfoQuery();// 创建子订单查询对象
		
		String sortby="";
		pssOrderInfoQuery.setSortBy("ordertime");// 以主订单“订购时间ORDERTIME”降序
		pssOrderInfoQuery.setSortType(ORDER_BY.DESC);// DESC降序
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");// 当前页码
																	// 默认为1(排序用)
			if (!StringUtils.isBlank(pageNum)) {
				pssOrderInfoQuery.setPage(Integer.parseInt(pageNum));
			}
			
			sortby=request.getParameter("SortBySearch");
			String satelliteId = request.getParameter("_satelliteId");// 卫星标识SATELLITEID
			if (!StringUtils.isBlank(satelliteId) && !"-1".equals(satelliteId)) {
				pssOrderInfoQuery.setSatelliteid(satelliteId);
				model.addAttribute("_satelliteId", satelliteId);
				String[] val = SystemConfigUtil.getSystemValue(satelliteId,
						request.getServletContext());
				model.addAttribute("sensorIdS", val);// 传感器
			}

			String sensorId = request.getParameter("_sensorId");// 传感器 SENSORID
			if (!StringUtils.isBlank(sensorId) && !"-1".equals(sensorId)) {
				pssOrderInfoQuery.setSensorid(sensorId);
				model.addAttribute("_sensorId", sensorId);
			}
			String taskType = request.getParameter("_taskType");// 订单类型
			if (!StringUtils.isBlank(taskType) && !"-1".equals(taskType)) {
				pssOrderInfoQuery.setTasktype(Long.valueOf(taskType));
				model.addAttribute("_taskType", taskType);
			}
			String productLevel = request.getParameter("_productLevel");// 产品级别
			if (!StringUtils.isBlank(productLevel)
					&& !"-1".equals(productLevel)) {
				pssOrderInfoQuery.setProductlevel(productLevel);
				model.addAttribute("_productLevel", productLevel);
			}
			String areadystate = request.getParameter("_areadystate");// 审核状态
			if (!StringUtils.isBlank(areadystate) && !"-1".equals(areadystate)) {
				pssOrderInfoQuery.setCheckstate(Long.parseLong(areadystate));
				model.addAttribute("_areadystate", areadystate);
			}
			String orderState = request.getParameter("_orderState");// 处理状态
			orderstatenl = orderState;
			if (!StringUtils.isBlank(orderState) && !orderState.equals("-1")) {
				pssOrderInfoQuery.setOrderstate(Long.valueOf(orderState));
				model.addAttribute("_orderState", orderState);
			}
			String orderId = request.getParameter("_orderId");// 订单号
			if (!StringUtils.isBlank(orderId)) {
				pssOrderInfoQuery.setOrderid(Long.valueOf(orderId));
				model.addAttribute("_orderId", orderId);
			}
			String sceneId = request.getParameter("_sceneId");// 景序列号
			if (!StringUtils.isBlank(sceneId)) {
				pssOrderInfoQuery.setSceneid(Double.valueOf(sceneId));
				model.addAttribute("_sceneId", sceneId);
			}

			String orderStartTime = request.getParameter("orderStartTime");// 订购时间(开始时间)
			if (!StringUtils.isBlank(orderStartTime)) {
				pssOrderInfoQuery.setOrderStartTime(orderStartTime);
				model.addAttribute("orderStartTime", orderStartTime);
			}
			String orderEndTime = request.getParameter("orderEndTime");// 订购时间(结束时间)
			if (!StringUtils.isBlank(orderEndTime)) {
				pssOrderInfoQuery.setOrderEndTime(orderEndTime);
				model.addAttribute("orderEndTime", orderEndTime);
			}
			String checkStartTime = request.getParameter("checkStartTime");// 审核时间(开始时间)
			if (!StringUtils.isBlank(checkStartTime)) {
				pssOrderInfoQuery.setCheckStartTime(checkStartTime);
				model.addAttribute("checkStartTime", checkStartTime);
			}
			String checkEndTime = request.getParameter("checkEndTime");// 审核时间(结束时间)
			if (!StringUtils.isBlank(checkEndTime)) {
				pssOrderInfoQuery.setCheckEndTime(checkEndTime);
				model.addAttribute("checkEndTime", checkEndTime);
			}
			String isfault=request.getParameter("_isfault");
			if(!StringUtils.isBlank(isfault)&& !isfault.equals("-1")){
				pssOrderInfoQuery.setIsfault(Integer.parseInt(isfault));
				model.addAttribute("isfaultsearch", isfault);
			}

		}

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();// 订购人
		pssOrderInfoQuery.setOrderer(username);

		Integer totalCount = orderMainDao.getCountByQuery(pssOrderInfoQuery);// 查询总记录条数

		TotalInfo totalInfo = new TotalInfo(totalCount,
				pssOrderInfoQuery.getPageSize(), pssOrderInfoQuery.getPage(),
				pssOrderInfoQuery.getStartNum());// 分页信息
		model.addAttribute("totalInfo", totalInfo);

		String orderMainId;// 主订单id
		List<OrderMain> orderMainList = orderMainDao
				.getOrderMainListByQuery(pssOrderInfoQuery);

		for (OrderMain orderMain : orderMainList) {
			orderMainId = String.valueOf(orderMain.getOrderMainId());// 获取主订单id
			orderMain.setOrderInfoList(getPssOrderInfoListByQuery(orderMainId,sortby,model));//主订单查子订单的方法
		}

		model.addAttribute("orderMainList", orderMainList);

		String[] valueArray = SystemConfigUtil.getSystemValue(
				"collectSatellite", request.getServletContext());
		model.put("collectSatellite", valueArray);// 卫星
		valueArray = SystemConfigUtil.getSystemValue("collectSatellite",
				request.getServletContext());
		model.put("collectSatellite", valueArray);// 产品级别
		valueArray = SystemConfigUtil.getSystemValue("productOrderProcedure",
				request.getServletContext());
		model.put("productOrderProcedure", valueArray);// 产品订单 步骤
		valueArray = SystemConfigUtil.getSystemValue(
				"productionOrderProcedure", request.getServletContext());
		model.put("productionOrderProcedure", valueArray);// 生产订单 步骤

	}

	@SuppressWarnings("null")
	public List<Map<String, Object>> findOrderByCond(Map<String, Object> condMap) {
		LOGGER.debug("OrderMainServiceImpl.class method = findOrderById");
		List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();

		String sonOrderIds = (String) condMap.get("sonOrderIds");// 子订单id,为多个时以“,”拼接
		if (null == sonOrderIds && "".equals(sonOrderIds.trim())) {// 当sonOrderIds为“”或null时返回null
			return null;
		}
		String flag = (String) condMap.get("flag");
		if ("1".equals(flag)) {// 1 订单 2 购物车
			orderList = orderMainDao.findOrderBySonOrderId(sonOrderIds);
			String orderMainId;// 主订单id
			for (Map<String, Object> orderMain : orderList) {
				orderMainId = String.valueOf(orderMain.get("ORDER_MAIN_ID"));// 获取主订单id
				condMap.put("orderMainId", orderMainId);
				orderMain.put(orderMainId,
						orderInfoDao.findSonOrderByCond(condMap));
			}
		} else if ("2".equals(flag)) {// 1 订单 2 购物车
			orderList = pssShopCarDao.findPssShopCarInfoById(sonOrderIds);
		}

		return orderList;
	}

	
	@Override
	public Map<String, Object> getShpDate(String sonOrderIds) {
		@SuppressWarnings("unchecked")
		StringBuffer orderInfoBur = new StringBuffer();// 创建StringBuffer记录所需子订单信息
		StringBuffer spaceInfoBur = new StringBuffer();// 创建StringBuffer记录所需空间信息
		Map<String, Object> dateMap = new HashedMap();// 返回值
		String productid;// 产品序列号
		PssProduct product;// 1级产品表实体类
		String level[]=sonOrderIds.split("_");
		for (int i = 0; i < level.length; i++) {
			String productlevel=level[1];
			String orderid=level[0];
	
		
		dateMap.put("sonOrderIds", orderid);
		List<Map<String, Object>> orderList = orderInfoDao
				.findSonOrderByCond(dateMap);// 根据子订单id查询子订单信息
		dateMap.clear();// 清空map
		
		String[] orderField = {"PRODUCTID", "SCENEID", "STATELLITEI",
				"SENSORID", "TASKTIME", "MAPPROJECT", "EARTHMODEL",
				"BROWSEFILE"};// 字段数组
	
		PssProductQuery productquery = new PssProductQuery();// 1级产品表查询类
		for (Map<String, Object> map : orderList) {
			for (String field : orderField) {
				if ("STATELLITEI".equals(field)) {// STATELLITEI找不到对应的值故先用“待定”代替显示，以便后续更改
					orderInfoBur.append("待定!");
				} else if ("MAPPROJECT".equals(field)) {// MAPPROJECT找不到对应的值故先用“待定”代替显示，以便后续更改
					orderInfoBur.append("待定!");
				} else if ("BROWSEFILE".equals(field)) {// BROWSEFILE找不到对应的值故先用“待定”代替显示，以便后续更改
					orderInfoBur.append("待定!");
				} else {
					orderInfoBur.append((null == map.get(field) ? " " : map
							.get(field)) + "!");// 当值为null时用" "代替
				}

				if ("PRODUCTID".equals(field)) {// 判断是否为1级产品表 id ，拼接空间信息
					productid = String.valueOf(map.get(field));
					productquery.setProductid(productid);
					if(productlevel!=null && !"".equals(productlevel)){
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
					
					product = productDao.selectProductById(productquery);
					
					spaceInfoBur.append(product.getDataupperleftlat() + ","
							+ product.getDataupperleftlong() + ","
							+ product.getDataupperrightlat() + ","
							+ product.getDatalowerghtlong() + ","
							+ product.getDatalowerrightlat() + ","
							+ product.getDatalowerrightlong() + ","
							+ product.getDatalowerleftlat() + ","
							+ product.getDatalowerleftlong() + ","
							+ product.getDataupperleftlat() + "," + // 再次追加左上角坐标信息，用于闭合空间
							product.getDataupperleftlong());
					}
				}
			}
			}
			
			orderInfoBur = new StringBuffer(orderInfoBur.substring(0,
					orderInfoBur.lastIndexOf("!")) + ";");// 去除最后一个“!”,同时追加一个“;”
			spaceInfoBur = new StringBuffer(spaceInfoBur.append(";"));// 追加一个“;”
		}
		
		orderInfoBur = new StringBuffer(orderInfoBur.substring(0,
				orderInfoBur.lastIndexOf(";")));// 去除最后一个“;”
		spaceInfoBur = new StringBuffer(spaceInfoBur.substring(0,
				spaceInfoBur.lastIndexOf(";")));// 去除最后一个“;”
		dateMap.put("spaceInfo", spaceInfoBur.toString());
		dateMap.put("orderInfo", orderInfoBur.toString());
		return dateMap;
	}

	/**
	 * 根据主订单id查询子订单信息
	 * 
	 * @param orderMainId
	 *            String 主订单id
	 * @return
	 */
	private List<PssOrderInfo> getPssOrderInfoListByQuery(String orderMainId,String sortby,ModelMap model) {
		PssOrderInfoQuery orderInfoQuery = new PssOrderInfoQuery();
		orderInfoQuery.setOrderMainId(orderMainId);// 主订单id
		// 分页查询
		if( sortby==null){
			orderInfoQuery.setSortBy("tasktime");// 以子订单“任务时间TASKTIME”排序
		}else{
			orderInfoQuery.setSortBy(sortby);// 以子订单“任务时间TASKTIME”排序
			model.addAttribute("sortBySearch", sortby);
		}
		
		orderInfoQuery.setSortType(ORDER_BY.DESC);// DESC降序
		orderInfoQuery.setPageSize(100000);// 调用分页查询，设置每页显示条数为十万，一般子订单条数达不到十万
		//这个是加入子订单的状态，获得主订单的id，根据主订单的id查询主订单下所有满足条件订单状态的自订单
	
		List<PssOrderInfo> orderList = orderInfoDao
				.getPssOrderInfoListByQuery(orderInfoQuery);
		return orderList;
	}

	@Override
	public void update(OrderMain orderMain) {
		orderMainDao.updateOrderMain(orderMain);
		
	}

	@Override
	public List<PssOrderInfo> findsonOrderByCond(Map<String, Object> condMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PssOrderInfo getOrderInfo(Long orderid) {
	
		return orderInfoDao.getorderInfoByid(orderid);
	}

	@Override
	public OrderMain getorderMainInfo(Long orderMainId) {
		// TODO Auto-generated method stub
		return orderMainDao.getOrderMainById(orderMainId);
	}


}
