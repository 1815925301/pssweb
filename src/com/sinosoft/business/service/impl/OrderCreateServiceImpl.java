package com.sinosoft.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.OrderMainDao;
import com.sinosoft.business.dao.PssOrderInfoDao;
import com.sinosoft.business.dao.PssShopCarDao;
import com.sinosoft.business.dao.PssTaskDao;
import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.po.PssTask;
import com.sinosoft.business.po.query.PssOrderInfoQuery;
import com.sinosoft.business.po.query.PssShopCarQuery;
import com.sinosoft.business.service.OrderCreateService;
import com.sinosoft.business.service.PssTaskService;
import com.sinosoft.business.uilt.GetTaskId;
import com.sinosoft.business.uilt.SetSystemProperty;
import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.util.DateTimeUtils;
import com.sinosoft.common.util.SystemConfigUtil;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.Roles;
import com.sinosoft.security.po.UserRole;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.service.UserRoleService;

/**
 * 订单生成页面service层实现
 * 
 * @author Dylan
 * @date 16-08-20 14:13:55
 */
@Service("orderCreateService")
public class OrderCreateServiceImpl implements OrderCreateService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PssOrderInfoServiceImpl.class);
	@Resource
	private PssShopCarDao pssshopcarDao; // 购物车dao
	@Resource
	private OrderMainDao orderInfoMainDao; // 主订单dao
	@Resource
	private PssOrderInfoDao pssOrderInfoDao; // 子订单dao
	@Resource
	private SystemConstant systemConstant;
	@Resource
	private UserRoleService userRoleService;
	@Resource
	private PssTaskDao pssTaskDao;
	@Override
	public void initOrderCreatePage(ModelMap model, String method,
			HttpServletRequest request) {
		LOGGER.debug("Service层:查询订单");
		PssShopCarQuery pssshopcarQuery = new PssShopCarQuery();
		// 以id升序排序
		pssshopcarQuery.setSortBy("orderid");
		pssshopcarQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (!StringUtils.isBlank(pageNum)) {
				pssshopcarQuery.setPage(Integer.parseInt(pageNum));
			}
			String satelliteSearch = request.getParameter("satelliteSearch");
			if (!StringUtils.isBlank(satelliteSearch)
					&& !satelliteSearch.equals("-1")) {
				pssshopcarQuery.setSatelliteid(satelliteSearch);
				model.addAttribute("satelliteSearch", satelliteSearch);
			}
			String sensoridSearch = request.getParameter("sensoridSearch");
			if (!StringUtils.isBlank(sensoridSearch)
					&& !sensoridSearch.equals("-1")) {
				pssshopcarQuery.setSensorid(sensoridSearch);
				model.addAttribute("sensoridSearch", sensoridSearch);
			}
			String sceneidInput = request.getParameter("sceneidInput");
			if (!StringUtils.isBlank(sceneidInput)) {
				pssshopcarQuery.setSceneid(new Double(sceneidInput));
				model.addAttribute("sceneidInput", sceneidInput);
			}
			String productlevelSearch = request
					.getParameter("productlevelSearch");
			if (!StringUtils.isBlank(productlevelSearch)
					&& !productlevelSearch.equals("-1")) {
				pssshopcarQuery.setProductlevel(productlevelSearch);
				model.addAttribute("productlevelSearch", productlevelSearch);
			}
			String orderidInput = request.getParameter("orderidInput");
			if (!StringUtils.isBlank(orderidInput)) {
				pssshopcarQuery.setOrderid(new Long(orderidInput));
				model.addAttribute("orderidInput", orderidInput);
			}
			String usernameInput = request.getParameter("usernameInput");
			if (!StringUtils.isBlank(usernameInput)) {
				pssshopcarQuery.setUsername(usernameInput);
				model.addAttribute("usernameInput", usernameInput);
			}
			String tasktypeSearch = request.getParameter("tasktypeSearch");
			if (!StringUtils.isBlank(tasktypeSearch)
					&& !tasktypeSearch.equals("-1")) {
				pssshopcarQuery.setTasktype(new Double(tasktypeSearch));
				model.addAttribute("tasktypeSearch", tasktypeSearch);
			}
			String orderIds = request.getParameter("orderIds");
			if (!StringUtils.isBlank(orderIds)) {
				pssshopcarQuery.setOrderids(orderIds);
				model.addAttribute("orderIds", orderIds);
			}

		}
		String allprices = request.getParameter("allprices");
		if(null!=allprices){
			model.addAttribute("allprices", allprices);
		}
		// 获得当前登录用户的信息
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String usernameString = eUser.getUserName();
		pssshopcarQuery.setUsername(usernameString);
		Integer totalCount = pssshopcarDao.getCountByQuery(pssshopcarQuery);

		TotalInfo totalInfo = new TotalInfo(totalCount,
				pssshopcarQuery.getPageSize(), pssshopcarQuery.getPage(),
				pssshopcarQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);

		List<PssShopCar> orderList = pssshopcarDao
				.getPssShopCarListByQuery(pssshopcarQuery);
		model.addAttribute("orderList", orderList);

	}

	@Override
	public Boolean addOrderCreate(HttpServletRequest request,
			ActivityModelMap modelMap, String orderIds, String orderName,
			String remark) {
		boolean flay = true;
		List<PssShopCar> pssShopList;
		PssShopCarQuery pssShopCarQuery = new PssShopCarQuery();
		pssShopCarQuery.setOrderids(orderIds);// 设置购物车订单id
		pssShopList = pssshopcarDao.getPssShopCarListByQuery(pssShopCarQuery);// 根据id查询购物车订单信息
		OrderMain orderMain = new OrderMain();
		long orderMainId = orderInfoMainDao.getSequencesVal();// 生成id
		orderMain.setOrderMainId(orderMainId);// 设置id
		orderMain.setOrderName(orderName);// 设置主订单名称
		orderMain.setRemark(remark);// 设置备注
		orderMain.setOrderTime(new Date());// 设置订单生成时间
		orderMain.setOrderState("0");// 订单状态0未完成，1完成
		orderMain.setIspay(0);
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		orderMain.setOrderer(username);// 订购人
		orderInfoMainDao.insertOrderMain(orderMain);// 添加主订单数据
		SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtils.YMDHMS);
		PssOrderInfo orderInfo; // 声明订单实体类
		for (PssShopCar pssShopCar : pssShopList) {
			orderInfo = new PssOrderInfo(pssShopCar);
			orderInfo.setCheckstate(Long.parseLong("1"));
			
			
			orderInfo.setUsername(username);// 当前用户名称
			orderInfo.setOrderMainId(String.valueOf(orderMainId));// 主订单id
			orderInfo.setTasktime(sdf.format(new Date()));// 插入当前日期
			orderInfo.setIsfault(1);
			//-------------------yinli add-----------------
			orderInfo.setOrderstate(Long.valueOf("4"));
			orderInfo.setAreadystate(Double.valueOf("4"));
			orderInfo.setTasktype(pssShopCar.getOrdertype());
			orderInfo.setProducttype(pssShopCar.getProducttype());
			orderInfo.setProductcate(pssShopCar.getProductcate());//数据产品/专题产品
			orderInfo.setPriority(1);//用户优先级
			orderInfo.setUrgerncylevel(0);//紧急程度
			orderInfo.setIspay(Long.parseLong("0"));//未支付状态
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			orderInfo.setTasktime(df.format(new Date()));
			//数据产品定制
			if(pssShopCar.getProductcate()==1&&pssShopCar.getOrdertype()==1){
				orderInfo.setStationid(pssShopCar.getStationid());//接收站标识
				orderInfo.setSceneid(pssShopCar.getSceneid());
				orderInfo.setBands(pssShopCar.getBands());
				orderInfo.setL0dataid(pssShopCar.getL0dataid());
				orderInfo.setOrbitid(pssShopCar.getOrbitid());
				orderInfo.setScenepathbias(pssShopCar.getScenepathbias());
				orderInfo.setScenerowbias(pssShopCar.getScenerowbias());
				orderInfo.setScenepath(pssShopCar.getScenepath());
				orderInfo.setScenerow(pssShopCar.getScenerow());
				orderInfo.setEarthmodel(pssShopCar.getEarthmodel());
				orderInfo.setMapprojection(pssShopCar.getMapprojection());
				orderInfo.setResampletechnique(pssShopCar.getResampletechnique());
				
				orderInfo.setScenecount(pssShopCar.getScenecount());
				orderInfo.setSceneshift(pssShopCar.getSceneshift());
				orderInfo.setDataformatdes(pssShopCar.getPgproductformat());
			}
			//专题产品的定制
			if(pssShopCar.getProductcate()==2 && pssShopCar.getOrdertype()==1){
				orderInfo.setDataupperleftlat(pssShopCar.getDataupperleftlat());
				orderInfo.setDataupperleftlong(pssShopCar.getDataupperleftlong());
				orderInfo.setDataupperrightlat(pssShopCar.getDataupperrightlat());
				orderInfo.setDataupperrightupperlong(pssShopCar.getDataupperrightupperlong());
				orderInfo.setDatalowerleftlat(pssShopCar.getDatalowerleftlat());
				orderInfo.setDatalowerleftlong(pssShopCar.getDatalowerleftlong());
				orderInfo.setDatalowerrightlat(pssShopCar.getDatalowerrightlat());
				orderInfo.setDatalowerrightlong(pssShopCar.getDatalowerrightlong());
				orderInfo.setEarthmodel(pssShopCar.getEarthmodel());
				orderInfo.setMapprojection(pssShopCar.getMapprojection());
				orderInfo.setImageingstarttime(pssShopCar.getImagedatebegin());
				orderInfo.setImageimgstoptime(pssShopCar.getImagedateend());
				orderInfo.setSpatialmax(pssShopCar.getSpatialmax());
				orderInfo.setSpatialmin(pssShopCar.getSpatialmin());
				orderInfo.setNote(pssShopCar.getRemark());
			}
			if(pssShopCar.getGcp()!=null && !pssShopCar.equals("")){
				orderInfo.setGcp(pssShopCar.getGcp());
			}
			//------------------------------------
		int ordertype = pssShopCar.getOrdertype();
			Long orderid = (long) 0;
			if(ordertype==1){
				orderid = getCustomId();
			}else if(ordertype==2){
				orderid = getOrderId();
			}
			
			orderInfo.setOrderid(orderid);
			pssOrderInfoDao.insertPssOrderInfo(orderInfo);// 插入订单
			//免费订单自动审核
			String satelliteid=pssShopCar.getSatelliteid();
			if(satelliteid != null && (satelliteid.equals("VRSS-1") || satelliteid.equals("VRSS-2") )){
				int num2=findUserfreecont(request);//不超过可免费下载景数就自动审核成功
				if(num2==1){
					//更改子订单的价格为0
					pssOrderInfoDao.upOrderChectstate(orderid);
				}
				//如果审核通过且没有查到相同订单，在任务表里插入数据			
				int num = filterOrderInfo(orderInfo);
				if(num==0 && num2==1){
					PssTask pssTask=new PssTask();
					pssTask.setOrderid(String.valueOf(orderInfo.getOrderid()));
					
					pssTask.setCreatetime(df.format(new Date()));
					pssTask.setOrdertype(String.valueOf(pssShopCar.getOrdertype()));
					pssTask.setStatus(Long.parseLong("0"));
					pssTask.setSendsys("pss");
					if(pssShopCar.getProductcate()==1 && pssShopCar.getOrdertype()==2){//数据产品订购
						pssTask.setReceivesys("dms");
					}
					if(pssShopCar.getProductcate()==1 && pssShopCar.getOrdertype()==1){//数据产品定制
						pssTask.setReceivesys("ips");
					}
					
					pssTaskDao.insertPssTask(pssTask);
					
					
				}
			}
			
			
			
		}
		//计算订单总价（所以子订单价格相加）
		List<PssOrderInfo> prilist=pssOrderInfoDao.getPriceByOrderMainid(orderMainId);
		Integer price=0;
		for (int i = 0; i < prilist.size(); i++) {
			price+=prilist.get(i).getPrice();
		}
		OrderMain ordermain2=new OrderMain();
		ordermain2.setOrderMainId(orderMainId);
		ordermain2.setPrice(price);
		ordermain2.setUuit(prilist.get(0).getUuit());
		orderInfoMainDao.updateOrderMain(ordermain2);
		pssshopcarDao.deletePssShopCarByIdS(orderIds);// 删除购物车订单记录
		return flay;
	}

	// /**
	// * id生成，由3位随机数加13位当前时间组成的16位正整数
	// *
	// * @return long 由3位随机数加13位当前时间组成的16位正整数
	// */
	// public long getUUID() {
	// int random = (int) ((Math.random() * 9 + 1) * 100);// 生成3位随机数
	// Calendar calendar = Calendar.getInstance();
	// long id = calendar.getTime().getTime();
	// return Long.valueOf((random + "" + id));
	// }
	/**
	 * 当前订单是否为免费下载景数
	 * @return
	 */
	@Override
	public Integer findUserfreecont(HttpServletRequest request){
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		//获取角色信息
				List<Roles> rolesList = systemConstant.getRolesList();
				eUser.setUserRoleList(userRoleService.getRoleIdListByUserId(eUser.getId()));
				String roleName = "";
				if (eUser.getUserRoleList() != null && eUser.getUserRoleList().size() > 0) {
					
					for (UserRole userRole : eUser.getUserRoleList()) {
						for (Roles roles : rolesList) {
							if(userRole.getRoleId().compareTo(roles.getId()) == 0) {
								roleName += roles.getRoleName()+" , " ;
								break;
							}
						}
					}
					if (roleName.length() > 0) 
						eUser.setRoleName(roleName.substring(0,roleName.length()-3));
				}
				roleName=eUser.getRoleName();
				
				Integer freeCount=0;
				String[] frees=SystemConfigUtil.getSystemValue("FreeOrders", request.getServletContext());
				
					if(roleName.equals("Ordinary")){//普通用户
						freeCount=Integer.parseInt(frees[0]);
					}
					if(roleName.equals("SilverMember")){//白银会员
						freeCount=Integer.parseInt(frees[1]);
					}
					if(roleName.equals("GoldMember")){//黄金会员
						freeCount=Integer.parseInt(frees[2]);
					}
					if(roleName.equals("DiamondMember")){//钻石会员
						freeCount=Integer.parseInt(frees[3]);
					}
					if(roleName.equals("System")){//管理员
						freeCount=Integer.parseInt(frees[4]);
					}
			PssOrderInfoQuery orderInfoQuery=new PssOrderInfoQuery();
			orderInfoQuery.setUsername(eUser.getUserName());
			orderInfoQuery.setIspay(Long.parseLong("2"));
			Integer ordercount=pssOrderInfoDao.getCountByQuery(orderInfoQuery);
			Integer num2=0;
			if(ordercount<=freeCount){//可免费
				num2=1;
			}else{//已超出，不免费
				num2=2;
			}
		return num2;	
	}
	
	@Override
	public Integer filterOrderInfo(PssOrderInfo orderInfo) {
		
		String satelliteid =  orderInfo.getSatelliteid();
		String sensorid = orderInfo.getSensorid();
		String productle = orderInfo.getProductlevel();
		String orderId = String.valueOf(orderInfo.getOrderid());
		
		String satelliteidSensoridProductle = satelliteid+"_"+sensorid+"_"+productle;
		
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> pmap = new HashMap<String, String>();
		map = pssOrderInfoDao.filterOrderInfo(satelliteidSensoridProductle);
		String productuploaddir = "";
		String ftpurl = "";
		if(map!=null){
			productuploaddir = map.get("PRODUCTUPLOADDIR");
			ftpurl = map.get("FTPURL");
		}
		int num = 0;
		//如果表中已有数据就更新orderInfo
		if(!productuploaddir.equals("")&&!ftpurl.equals("")&&!productuploaddir.equals("null")&&!ftpurl.equals("null")){
			num = 1;
			pmap.put("productuploaddir", productuploaddir);
			pmap.put("ftpurl", ftpurl);
			pmap.put("orderId", orderId);
			int a = pssOrderInfoDao.updateOrderInfoFtp(pmap);
		}else{
			num = 0;
		}
		
		return num;
	}

	@Override
	public Long getOrderId() {
//		定义一个调用生成的订购单编号
		Long CJDHNum=(long) 0L;
//		定义最终获得的订购单编号
		Long orderid1=(long) 0L;
//		文件路径
		String profilepath = System.getProperty("user.dir").replace("bin", "webapps")+"\\pssweb\\WEB-INF\\classes\\Taskid.properties";
//		System.out.println(path);
//		获得当前日期并转换成Integer
		String sdate=new java.sql.Date(new java.util.Date().getTime()).toString().replace("-", "");
		Integer sysdate=Integer.valueOf(sdate).intValue();
//		获取properties中的日期
		String pdate=SetSystemProperty.getKeyValue("orderid").substring(0,8);
		Integer prodate=Integer.valueOf(pdate).intValue();
//		获得properties中的Value
		CJDHNum=Long.parseLong(SetSystemProperty.getKeyValue("orderid"));
//		当前日期与properties文件日期进行比较
		if (sysdate>prodate) {
//			如果是新的日期则调用生成单号的方法
			GetTaskId taskid=new GetTaskId();
//			Constant.CAINUM订购单标号的常量：05
			String LSH=taskid.getNum(Constant.ORDER);
//			把生成的订购单填入properties文件中
			SetSystemProperty.writeProperties("orderid", LSH.toString());
			orderid1 = Long.parseLong(LSH);
		}else{
//			如果是该日期的时间就在properties文件的值的基础上加1
			orderid1=CJDHNum+1L;
//			生成的订购单编号存到数据库字段中
			SetSystemProperty.writeProperties("orderid", orderid1.toString());
		}
		return orderid1;
	}
	
	
	
	@Override
	public Long getCustomId() {
//		定义一个调用生成的订购单编号
		Long CJDHNum=(long) 0L;
//		定义最终获得的订购单编号
		Long orderid1=(long) 0L;
//		文件路径
		String profilepath = System.getProperty("user.dir").replace("bin", "webapps")+"\\pssweb\\WEB-INF\\classes\\Taskid.properties";
//		System.out.println(path);
//		获得当前日期并转换成Integer
		String sdate=new java.sql.Date(new java.util.Date().getTime()).toString().replace("-", "");
		Integer sysdate=Integer.valueOf(sdate).intValue();
//		获取properties中的日期
		String pdate=SetSystemProperty.getKeyValue("customid").substring(0,8);
		Integer prodate=Integer.valueOf(pdate).intValue();
//		获得properties中的Value
		CJDHNum=Long.parseLong(SetSystemProperty.getKeyValue("customid"));
//		当前日期与properties文件日期进行比较
		if (sysdate>prodate) {
//			如果是新的日期则调用生成单号的方法
			GetTaskId taskid=new GetTaskId();
//			Constant.CAINUM订购单标号的常量：05
			String LSH=taskid.getNum(Constant.CUSTON);
//			把生成的订购单填入properties文件中
			SetSystemProperty.writeProperties("customid", LSH.toString());
			orderid1 = Long.parseLong(LSH);
		}else{
//			如果是该日期的时间就在properties文件的值的基础上加1
			orderid1=CJDHNum+1L;
//			生成的订购单编号存到数据库字段中
			SetSystemProperty.writeProperties("customid", orderid1.toString());
		}
		return orderid1;
	}

}
