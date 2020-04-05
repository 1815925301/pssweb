/*
 * Powered By 尹力
 * Since 2015 - 2016-40-30
 */

package com.sinosoft.business.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.po.query.PssOrderInfoQuery;
import com.sinosoft.business.service.OrderMainService;
import com.sinosoft.business.service.PssOrderInfoService;
import com.sinosoft.business.service.PssShopCarService;
import com.sinosoft.business.uilt.ShopCarUilt;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.mail.MailSenderInfo;
import com.sinosoft.common.mail.SimpleMailSender;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.util.SystemConfigUtil;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.Roles;
import com.sinosoft.security.po.UserRole;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.service.UserRoleService;
import com.sinosoft.security.service.UsersService;

@Controller
public class PssOrderInfoController {

	@Resource
	private PssOrderInfoService orderInfoService;
	@Resource
	private UsersService usersService;
	@Resource
	private OrderMainService orderMainService;// 订单Service层接口
	@Resource
	private UserRoleService userRoleService;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PssOrderInfoController.class);
	@Resource
	private SystemConstant systemConstant;
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssorderinfo.do")
	public String PssOrderInfoManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		model.addAttribute("pageHanName", "底层子任务"); // 页面名称
		ActivityWebUtils.WrapperModle(request, model);
		orderInfoService.PssOrderInfoPageInit(model, method, request);
		return "manage/" + model.get("pageName");
	}

	/**
	 * 追加"订单跟踪"信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return
	 * 
	 * @date 2016-10-11 10:03:35
	 * @author Dylan
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/addOrderTracking.do")
	public Map<String, Object> addOrderTracking(HttpServletRequest request,
			String orderid, String orderTracking) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// String orderid = request.getParameter("orderid");
		// String orderTracking = request.getParameter("orderTracking");
		try {
			if (orderid != null && !"".equals(orderid.trim())) {
				orderInfoService.andOrderTrackingById(orderid, orderTracking);
				modelMap.put("status", "success");
			}
		} catch (Exception e) {
			LOGGER.error(
					"PssOrderInfoController.class method = addOrderTracking throws Exception",
					e);
		}
		return modelMap;
	}

	/**
	 * 根据id查询子订单信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param orderid
	 *            String 子订单id
	 * @return	Map<String, Object>
	 * @date 2016-10-26 10:50:03
	 * @author Dylan
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getOrderinfoByid.do")
	public Map<String, Object> getOrderinfoByid(HttpServletRequest request,
			String orderid) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		try {
			if (orderid != null && !"".equals(orderid.trim())) {
				PssOrderInfo order = orderInfoService.getPssOrderInfo(Long
						.valueOf(orderid));
				modelMap.put("order", order);
			}
		} catch (Exception e) {
			LOGGER.error(
					"PssOrderInfoController.class method = getOrderinfoByid throws Exception",
					e);
		}
		return modelMap;
	}

	/**
	 * 根据id查看订制单信息
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/showOrderinfoByid.do")
	public String showOrderinfoByid(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		Long orderid = null;
		LOGGER.debug("以 {} 方式访问管理页面", method);
		String id = request.getParameter("orderid");
		if (id != null) {
			orderid = Long.parseLong(id);
		}
		ActivityWebUtils.WrapperModle(request, model);
		PssOrderInfo order = orderInfoService.getPssOrderInfo(orderid);
		model.addAttribute("order", order);
		return "manage/orderManage";
	}

	/**
	 * 根据id查看订购单信息个人信息
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/BuyOrderinfoByid.do")
	public @ResponseBody
	Map<String, Object> buyOrderinfoByid(HttpServletRequest request,
			ModelMap model) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		String method = request.getMethod();
		Long orderid = null;
		LOGGER.debug("以 {} 方式访问管理页面", method);
		String id = request.getParameter("orderid");
		if (id != null) {
			orderid = Long.parseLong(id);
		}
		// 这个是个人中心加的查询详情
		String ids = request.getParameter("id");
		if (ids != null) {
			orderid = Long.parseLong(ids);
		}
		ActivityWebUtils.WrapperModle(request, model);
		PssOrderInfo order = orderInfoService.getPssOrderInfo(orderid);
		model.addAttribute("orders", order);
		modelMap.put("orders", order);
		return modelMap.getModelMap();
	}

	/**
	 * 根据id查看订单信息个人订单
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/OrderinfoByid.do")
	public @ResponseBody
	Map<String, Object> showOrderinfo(HttpServletRequest request, ModelMap model) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		String method = request.getMethod();
		Long orderid = null;
		LOGGER.debug("以 {} 方式访问管理页面", method);
		String id = request.getParameter("orderid");
		if (id != null) {
			orderid = Long.parseLong(id);
		}
		// 这个是个人中心加的查询详情
		String ids = request.getParameter("id");
		if (ids != null) {
			orderid = Long.parseLong(ids);
		}
		ActivityWebUtils.WrapperModle(request, model);
		PssOrderInfo order = orderInfoService.getPssOrderInfo(orderid);
		model.addAttribute("orders", order);
		modelMap.put("orders", order);
		return modelMap.getModelMap();
	}
	/**
	 * 覆盖显示订单信息
	 **/

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/coverageOrderInfo.do")
	public String coverageOrderInfo(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		ActivityWebUtils.WrapperModle(request, model);
		
		String orderids = request.getParameter("orderid");// 获取选择的订单id
		Map<String, Object> dateMap = orderMainService.getShpDate(orderids);
		String attrPoints = (String) dateMap.get("spaceInfo");
		model.addAttribute("points", attrPoints);
		return "manage/coverage";
	}
	/**
	 * 点击景序列号查看订单信息
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/showOrderinfoBysceneid.do")
	public String showOrderinfoBysceneid(HttpServletRequest request,
			ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		String id = request.getParameter("orderid");
		Long orderid = Long.parseLong(id);
		PssOrderInfo orderManage = orderInfoService.getPssOrderInfo(orderid);
		model.addAttribute("orderManage", orderManage);
		return "manage/orderManageinfo";
	}
	/**
	 * 查看订单信息
	 **/
	@RequestMapping(method = {RequestMethod.POST}, value = "/showPssOrderInfo.do")
	public @ResponseBody
	Map<String, Object> showTaskSubTable(HttpServletRequest request,
			ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		String id = request.getParameter("orderid");
		Long orderid = Long.parseLong(id);
		PssOrderInfo orderInfo = orderInfoService.getPssOrderInfo(orderid);
		model.addAttribute("orderInfo", orderInfo);
		return model;
	}

	/**
	 * 订单审核邮件通知
	 * 
	 * @param request
	 * @param model
	 * @return PssOrderInfo
	 * @throws
	 * @author wlg
	 * @date 2016-07-20
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/updateOrderState.do")
	public @ResponseBody
	Map<String, Object> update(HttpServletRequest request,
			HttpServletResponse response, PssOrderInfo pssOrderInfo)
			throws Exception {
		LOGGER.debug("更新订单信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		Locale locale = PropertiesUtils.getLocale(request);// 得到当前业务中的国际化语言(locale)
		String ordermath = pssOrderInfo.getOrdermath();// 别名接收
		String splita[] = ordermath.split(",");
		// 拆分封装成Map
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<String, String> m = new HashMap<String, String>();
		// 遍历splita[]数组
		for (int i = 0; i < splita.length; i++) {
			String str[] = splita[i].split("-");
			// 把数组第一个值赋给Map m的key
			m.put(str[0], str[0]);
		}
		// 遍历Map m
		for (String key : m.keySet()) {
			List<String> list1 = new ArrayList<String>();
			for (int i = 0; i < splita.length; i++) {
				String str[] = splita[i].split("-");
				if (key.equals(str[0])) {
					String a = str[1];
					list1.add(a);
				}
			}
			map.put(key, list1);
		}
		// 遍历Map map
		for (String key : map.keySet()) {
			List<String> list2 = map.get(key);
			Users u = usersService.getUserInfoByNname(key);
			for (int i = 0; i < list2.size(); i++) {
				long aid = Long.parseLong(list2.get(i));
				pssOrderInfo.setOrderid(aid);
				try {
					ExtendUsers eUser = (ExtendUsers) request.getSession()
							.getAttribute("CURRENT_USER_INFO");
					if (eUser == null || pssOrderInfo == null) {
						modelMap.put("status", "error");
					} else {
						if (orderInfoService.updateCheckstate(pssOrderInfo,
								eUser, modelMap)) {
							modelMap.put("status", "success");
							modelMap.put("data",
									PropertiesUtils.key("savesuccess", locale));
							MailSenderInfo mailInfo = new MailSenderInfo();
							mailInfo.setMailServerHost("smtp.163.com");
							mailInfo.setMailServerPort("25");
							mailInfo.setValidate(true);
							mailInfo.setUserName("zkr_admin@163.com");
							// mailInfo.setPassword(DESPlus.desPlusDecrypt("18a437fc6e0b9784def947d249cd58b4"));//
							// 您的邮箱密码
							mailInfo.setPassword("a123456");
							mailInfo.setFromAddress("zkr_admin@163.com");
							mailInfo.setToAddress(u.getUserEmail());
							mailInfo.setSubject("这是测试");
							SimpleMailSender sms = new SimpleMailSender();
							if (pssOrderInfo.getCheckstate() == 2) {
								if (u.getUserName().equals(key)) {
									mailInfo.setContent("恭喜你！审核成功！希望对您有所帮助,谢谢您的支持,订单号"
											+ map.get(key));
								}
							} else {
								mailInfo.setContent("审核失败，原因:"
										+ pssOrderInfo.getNote());
							}
							sms.sendTextMail(mailInfo);// 发送文体格式
						}
					}
				} catch (Exception e) {
					LOGGER.error("更新用户信息的操作出现异常：{}", e);
					modelMap.put("status", "error");
				}
			}
		}
		return modelMap.getModelMap();
	}

	/**
	 * 保存新增对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/savepssOrderInfo.do")
	public @ResponseBody
	Map<String, Object> saveNew(HttpServletRequest request,
			HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		Long orderid = Long.parseLong(request.getParameter("orderid"));
		PssOrderInfo info = orderInfoService.getPssOrderInfo(orderid);
		info.setOrderstate(new Long(1));
		info.setCheckstate(new Long(1));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		info.setTasktime(df.format(new Date()));
		info.setIspay(new Long(1));
		try {
			if (orderInfoService.save(request, info, modelMap)) {
				modelMap.put("status", "success");
				modelMap.put("data", "增加成功！");
			}
		} catch (Exception e) {
			LOGGER.error("出现异常：{}", e);
			modelMap.put("status", "exception");
		}

		return modelMap.getModelMap();
	}
	@Resource
	private PssShopCarService pssshopcarservice;

	@RequestMapping(method = RequestMethod.POST, value = "savePssShop.do")
	public @ResponseBody
	Map<String, Object> saveNewShop(HttpServletRequest request,
			HttpServletResponse response) {
		Locale locale = PropertiesUtils.getLocale(request);// 得到当前业务中的国际化语言(locale)
		ActivityModelMap modelMap = new ActivityModelMap(request);
		Long orderid = Long.parseLong(request.getParameter("orderid"));
		PssOrderInfo info = orderInfoService.getPssOrderInfo(orderid);
		ShopCarUilt shop = new ShopCarUilt();
		PssShopCar pssshopcar = shop.getPssOrderInfoToPssShopCar(info);
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String username= eUser.getUserName();
		pssshopcar.setUsername(username);
		pssshopcar.setBrowsefilelocation("imgnew/img03.gif");
		Date day=new Date();   
	    pssshopcar.setOrderdate(day);
		Boolean str = pssshopcarservice.save(request, pssshopcar, modelMap);
		try {
			if (str) {
				modelMap.put("status", "success");
				modelMap.put("data", PropertiesUtils.key("savesuccess", locale));
			}
		} catch (Exception e) {
			LOGGER.error("出现异常：{}", e);
			modelMap.put("status", "exception");
		}

		return modelMap.getModelMap();
	}

	/**
	 * 取消订单
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/cancleOrder.do")
	public @ResponseBody
	Map<String, Object> cancleOrder(HttpServletRequest request,
			HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		Long orderid=Long.parseLong(request.getParameter("orderid"));
		Long orderstate=Long.parseLong(request.getParameter("orderstate"));
		
		PssOrderInfo pssOrderInfo=new PssOrderInfo();
		pssOrderInfo.setOrderid(orderid);
		pssOrderInfo.setOrderstate(orderstate);
		if(orderstate==4){
			pssOrderInfo.setCheckstate(Long.parseLong("1"));
		}
		Boolean state=orderInfoService.update(request, pssOrderInfo, modelMap);
		if(state && orderstate==3){
			modelMap.put("status", "success");
			modelMap.put("title", "取消成功");
		}if(state && orderstate==4){
			modelMap.put("status", "success");
			modelMap.put("title", "恢复成功");
		}
		
	
		return modelMap.getModelMap();
	}
	/**
	 * 查询当前用户会员每天下了多少景
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param orderid
	 *            String 子订单id
	 * @return	Map<String, Object>
	 * @date 2016-10-26 10:50:03
	 * @author Dylan
	 */
	@ResponseBody
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/getOrderCount.do")
	public Map<String, Object> getOrderCount(HttpServletRequest request,
			String orderid,ModelMap model) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
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
				Integer ordercount=0;
				String[] downs=SystemConfigUtil.getSystemValue("membertotal", request.getServletContext());
				
					if(roleName.equals("Ordinary")){//普通用户
						ordercount=Integer.parseInt(downs[0]);
					}
					if(roleName.equals("SilverMember")){//白银会员
						ordercount=Integer.parseInt(downs[1]);
					}
					if(roleName.equals("GoldMember")){//黄金会员
						ordercount=Integer.parseInt(downs[2]);
					}
					if(roleName.equals("DiamondMember")){//钻石会员
						ordercount=Integer.parseInt(downs[3]);
					}
					if(roleName.equals("System")){//系统管理员
						ordercount=Integer.parseInt(downs[4]);
					}
					PssOrderInfoQuery orderInfoQuery=new PssOrderInfoQuery();
					orderInfoQuery.setUsername(eUser.getUserName());
					SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd");
					orderInfoQuery.setTasktime(smf.format(new Date()));
					//登录用户当天已下单量
					Integer count=orderInfoService.getCountByQuery(orderInfoQuery);
					Integer souns=ordercount-count;//用户当天还可以下多少单
					model.addAttribute("souns", souns);
		return model;
	}

}
