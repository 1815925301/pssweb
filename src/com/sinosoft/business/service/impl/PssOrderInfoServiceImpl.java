/*
 * Powered By 尹力
 * Since 2015 - 2016-40-30
 */

package com.sinosoft.business.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.PssOrderInfoDao;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.query.PssOrderInfoQuery;
import com.sinosoft.business.service.PssOrderInfoService;
import com.sinosoft.common.util.ChartModel;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

@Service("orderInfoService")
public class PssOrderInfoServiceImpl implements PssOrderInfoService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PssOrderInfoServiceImpl.class);
	@Resource
	private PssOrderInfoDao orderInfoDao;

	/**
	 * 页面初始化
	 */

	public void PssOrderInfoPageInit(ModelMap model, String method,
			HttpServletRequest request) {
		LOGGER.debug("Service层:查询订单");
		PssOrderInfoQuery orderInfoQuery = new PssOrderInfoQuery();
		// 以id升序排序
		orderInfoQuery.setSortBy("orderid");
		orderInfoQuery.setSortType("2");

		if (method.equals("POST")) {
			String Starttime = request.getParameter("starttime");
			String enttime = request.getParameter("endtime");
			orderInfoQuery.setStarttime(Starttime);
			orderInfoQuery.setEndtime(enttime);
			model.addAttribute("Starttime", Starttime);
			model.addAttribute("enttime", enttime);
			String pageNum = request.getParameter("pageNumInput");
			if (!StringUtils.isBlank(pageNum)) {
				orderInfoQuery.setPage(Integer.parseInt(pageNum));
			}
			String ispaySearch = request.getParameter("ispaySearch");
			if (!StringUtils.isBlank(ispaySearch) && !ispaySearch.equals("-1")) {
				orderInfoQuery.setIspay(new Long(ispaySearch));
				model.addAttribute("ispaySearch", ispaySearch);
			}
			String sensoridSearch = request.getParameter("sensoridSearch");
			if (!StringUtils.isBlank(sensoridSearch)
					&& !sensoridSearch.equals("-1")) {
				orderInfoQuery.setSensorid(sensoridSearch);
				model.addAttribute("sensoridSearch", sensoridSearch);
			}
			String sceneidInput = request.getParameter("sceneidInput");
			if (!StringUtils.isBlank(sceneidInput)) {
				orderInfoQuery.setSceneid(new Double(sceneidInput));
				model.addAttribute("sceneidInput", sceneidInput);
			}
			String productlevelSearch = request
					.getParameter("productlevelSearch");
			if (!StringUtils.isBlank(productlevelSearch)
					&& !productlevelSearch.equals("-1")) {
				orderInfoQuery.setProductlevel(productlevelSearch);
				model.addAttribute("productlevelSearch", productlevelSearch);
			}
			String orderidInput = request.getParameter("orderidInput");
			if (!StringUtils.isBlank(orderidInput)) {
				orderInfoQuery.setOrderid(new Long(orderidInput));
				model.addAttribute("orderidInput", orderidInput);
			}
			String usernameInput = request.getParameter("usernameInput");
			if (!StringUtils.isBlank(usernameInput)) {
				orderInfoQuery.setUsername(usernameInput);
				model.addAttribute("usernameInput", usernameInput);
			}
			String tasktypeSearch = request.getParameter("tasktypeSearch");
			if (!StringUtils.isBlank(tasktypeSearch)
					&& !tasktypeSearch.equals("-1")) {
				orderInfoQuery.setTasktype(new Long(tasktypeSearch));
				model.addAttribute("tasktypeSearch", tasktypeSearch);
			}
			String checkstateSearch = request.getParameter("checkstateSearch");
			if (!StringUtils.isBlank(checkstateSearch)
					&& !checkstateSearch.equals("-1")) {
				orderInfoQuery.setCheckstate(new Long(checkstateSearch));
				model.addAttribute("checkstateSearch", checkstateSearch);
			}

		}
		// 卫星
		List satelliteList = orderInfoDao.getSatelliteList();
		model.addAttribute("satelliteList", satelliteList);
		// 传感器
		List sensoridList = orderInfoDao.getSensoridList();
		model.addAttribute("sensoridList", sensoridList);
		// 产品级别
		List productlevelList = orderInfoDao.getProductlevelList();
		model.addAttribute("productlevelList", productlevelList);
		// 订单类型
		List tasktypeList = orderInfoDao.getTasktypeList();
		model.addAttribute("tasktypeList", tasktypeList);

		Integer totalCount = orderInfoDao.getCountByQuery(orderInfoQuery);

		TotalInfo totalInfo = new TotalInfo(totalCount,
				orderInfoQuery.getPageSize(), orderInfoQuery.getPage(),
				orderInfoQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);

		List<PssOrderInfo> orderList = orderInfoDao
				.getPssOrderInfoList(orderInfoQuery);
		model.addAttribute("orderList", orderList);

	}

	@Override
	public int andOrderTrackingById(String orderid, String orderTracking) {
		return orderInfoDao.updateOrderTrackingById(orderid, orderTracking);
	}

	/**
	 * 创建PssOrderInfo
	 **/

	public Boolean save(HttpServletRequest request, PssOrderInfo pssOrderInfo,
			ActivityModelMap modelMap) {
		boolean result = false;
		Integer resultNum = orderInfoDao.insertPssOrderInfo(pssOrderInfo);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}

		return result;
	}

	/**
	 * 更新PssOrderInfo
	 **/

	public Boolean update(HttpServletRequest request,
			PssOrderInfo pssOrderInfo, ActivityModelMap modelMap) {
		boolean result = false;
		Integer resultNum = orderInfoDao.updatePssOrderInfo(pssOrderInfo);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}

		return result;
	}

	/**
	 * 删除PssOrderInfo
	 **/

	public void remove(HttpServletRequest request, ActivityModelMap modelMap) {

	}

	/**
	 * 根据ID得到PssOrderInfo
	 **/

	public PssOrderInfo getPssOrderInfo(HttpServletRequest request,
			ActivityModelMap modelMap) {

		return null;
	}

	public void getOrderForInitPage(ModelMap model, String method,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		LOGGER.debug("Service层:查询订单");
		PssOrderInfoQuery orderInfoQuery = new PssOrderInfoQuery();
		// 以id升序排序
		orderInfoQuery.setSortBy("orderid");
		orderInfoQuery.setSortType("-1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");

			if (!StringUtil.isBlank(pageNum)) {
				orderInfoQuery.setPage(Integer.parseInt(pageNum));

			}

		}

		Integer totalCount = orderInfoDao.getCountByQuery(orderInfoQuery);
		System.out.println(totalCount);

		/*
		 * TotalInfo totalInfo = new TotalInfo(totalCount,
		 * taskSubQuery.getPageSize(),1, taskSubQuery.getStartNum());
		 */
		TotalInfo totalInfo = new TotalInfo(totalCount,
				orderInfoQuery.getPageSize(), orderInfoQuery.getPage(),
				orderInfoQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<PssOrderInfo> orderList = orderInfoDao
				.getPssOrderInfoListByQuery(orderInfoQuery);
		model.addAttribute("orderList", orderList);

	}
	/**
	 * 修改用户审核状态
	 * 
	 * @param request
	 * @param model
	 * @return PssOrderInfo
	 * @throws
	 * @author wlg
	 * @date 2016-07-20
	 * @version V1.0
	 */
	@Override
	public boolean updateCheckstate(PssOrderInfo pssOrderInfo,
			ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		Integer resultNumber = orderInfoDao.updatePssOrderInfo(pssOrderInfo);
		if (resultNumber != null) {
			result = true;
			LOGGER.debug("更新订单的审核状态成功！");
		} else {
			result = false;
		}
		return result;

	}

	@Override
	public PssOrderInfo getPssOrderInfo(Long orderid) {
		// TODO Auto-generated method stub
		return orderInfoDao.selectPssOrderInfoById(orderid);
	}
	// ------------------------------订单统计开始-----------------------------
	// 按产品级别
	public void getOrderByLevel(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderDownList = orderInfoDao
				.getOrderByLevel(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderDownList,
				"productLevel", request);
		model.put("chartList", chartList);
	}

	// 按卫星
	public void getOrderByState(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderDownList = orderInfoDao
				.getOrderByState(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderDownList, "state",
				request);
		model.put("chartList", chartList);

	}
	// 按传感器
	public void getOrderBySensorid(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderDownList = orderInfoDao
				.getOrderBySensorid(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderDownList, "sensorid",
				request);
		model.put("chartList", chartList);
	}

	// 按用户级别
	public void getOrderByUserLevel(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderDownList = orderInfoDao
				.getOrderByUserLevel(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderDownList, "priority",
				request);
		model.put("chartList", chartList);
	}

	// 按用户
	public void getOrderByUser(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderDownList = orderInfoDao
				.getOrderByUser(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderDownList, "user",
				request);
		model.put("chartList", chartList);
	}

	// 按应用领域
	public void getOrderByArea(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderDownList = orderInfoDao
				.getOrderByArea(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderDownList, "area",
				request);
		model.put("chartList", chartList);

	}
	// 按单位
	public void getOrderByUnit(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderList = orderInfoDao
				.getOrderByUnit(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderList, "unit", request);
		model.put("chartList", chartList);
	}

	// 按接收站
	public void getOrderByReceive(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderList = orderInfoDao
				.getOrderByReceive(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderList, "receive", request);
		model.put("chartList", chartList);

	}

	// 按订单状态
	public void getOrderByOrderState(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery,
			Locale locale) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderList = orderInfoDao
				.getOrderByOrderState(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderList, "orderState",
				request);
		model.put("chartList", chartList);

	}

	// 按支付行
	public void getOrderByPayTheline(HttpServletRequest request,
			ActivityModelMap model, PssOrderInfoQuery orderInfoQuery) {
		int counts = orderInfoDao.getOrderCount(orderInfoQuery);// 获取总记录数
		model.put("counts", counts);
		// 查询数据库，获取返回数据的集合
		List<PssOrderInfoQuery> orderList = orderInfoDao
				.getOrderByPayTheline(orderInfoQuery);
		// 处理数据，返回封装好的集合数据
		List<ChartModel> chartList = getChartList(orderList, "payTheline",
				request);
		model.put("chartList", chartList);
	}

	/*
	 * 该方法的作用是为了处理查询到的数据，封装成前台js所需的list形式，方便取值。
	 */
	public List<ChartModel> getChartList(List<PssOrderInfoQuery> orderDownList,
			String reportUnit, HttpServletRequest request) {
		List<ChartModel> chartList = new ArrayList<ChartModel>();
		// 产品级别
		if (reportUnit.equals("productLevel")) {
			for (int i = 0; i < orderDownList.size(); i++) {
				ChartModel chartModel = new ChartModel();// 封装好的实体名称
				String name = orderDownList.get(i).getProductlevel();// 这句话的取值不同
				String value = orderDownList.get(i).getAmount();
				// 判断name是否为空
				if (name != null && name != "") {
					chartModel.setX(name);// 给X轴赋值
					chartModel.setY(value);// 给Y轴赋值
					chartList.add(chartModel);// 存放到集合里边
				}
			}
		}
		// 卫星
		if (reportUnit.equals("state")) {
			for (int i = 0; i < orderDownList.size(); i++) {
				ChartModel chartModel = new ChartModel();// 封装好的实体名称
				String name = orderDownList.get(i).getSatelliteid();// 这句话的取值不同
				String value = orderDownList.get(i).getAmount();
				if (name != null && name != "") {
					chartModel.setX(name);// 给X轴赋值
					chartModel.setY(value);// 给Y轴赋值
					chartList.add(chartModel);// 存放到集合里边
				}
			}
		}
		// 传感器
		if (reportUnit.equals("sensorid")) {
			for (int i = 0; i < orderDownList.size(); i++) {
				ChartModel chartModel = new ChartModel();// 封装好的实体名称
				String name = orderDownList.get(i).getSensorid();// 这句话的取值不同
				String value = orderDownList.get(i).getAmount();
				if (name != null && name != "") {
					chartModel.setX(name);// 给X轴赋值
					chartModel.setY(value);// 给Y轴赋值
					chartList.add(chartModel);// 存放到集合里边
				}
			}
		}
		// 用户级别
		if (reportUnit.equals("priority")) {
			for (int i = 0; i < orderDownList.size(); i++) {
				ChartModel chartModel = new ChartModel();// 封装好的实体名称
				if (orderDownList.get(i).getPriority() != null) {
					Double name = orderDownList.get(i).getPriority();// 这句话的取值不同
					String value = orderDownList.get(i).getAmount();
					if (name != null) {
						String name1 = name.toString();// 转成string
						chartModel.setX(name1);// 给X轴赋值
						chartModel.setY(value);// 给Y轴赋值
						chartList.add(chartModel);// 存放到集合里边
					}
				}
			}
		}
		// 用户
		if (reportUnit.equals("user")) {
			for (int i = 0; i < orderDownList.size(); i++) {
				ChartModel chartModel = new ChartModel();// 封装好的实体名称
				String name = orderDownList.get(i).getUsername();// 这句话的取值不同
				String value = orderDownList.get(i).getAmount();
				if (name != null && name != "") {
					chartModel.setX(name);// 给X轴赋值
					chartModel.setY(value);// 给Y轴赋值
					chartList.add(chartModel);// 存放到集合里边
				}
			}
		}
		// 应用领域
		if (reportUnit.equals("area")) {

		}
		// 单位
		if (reportUnit.equals("unit")) {

		}
		// 接收站
		if (reportUnit.equals("receive")) {

		}
		// 订单状态
		if (reportUnit.equals("orderState")) {
			HttpSession session = request.getSession(true);
			Locale locale = (Locale) session.getAttribute("locale");
			if (locale == null) {
				locale = Locale.getDefault();
			}
			for (int i = 0; i < orderDownList.size(); i++) {
				ChartModel chartModel = new ChartModel();// 封装好的实体名称
				Long name = orderDownList.get(i).getOrderstate();// 这句话的取值不同
				// 要判断name值是不是为空，否则报错 PropertiesUtils.key("order.orderstate",
				// locale)
				if (name != null) {
					String name1 = name.toString();// 转成string
					String value = orderDownList.get(i).getAmount();
					if (name1.equals("1")) {
						name1 = PropertiesUtils.key(
								"reprot.order.orderstate.ok", locale);
					}
					if (name1.equals("2")) {
						name1 = PropertiesUtils.key(
								"reprot.order.orderstate.error", locale);
					}
					if (name1.equals("3")) {
						name1 = PropertiesUtils.key(
								"report.order.orderstate.esc", locale);
					}
					if (name1.equals("4")) {
						name1 = PropertiesUtils.key(
								"reprot.order.orderstate.loading", locale);
					}
					if (name1.equals("5")) {
						name1 = PropertiesUtils.key(
								"report.order.orderstate.chuling", locale);
					}
					chartModel.setX(name1);// 给X轴赋值
					chartModel.setY(value);// 给Y轴赋值
					chartList.add(chartModel);// 存放到集合里边
				}
			}
		}
		// 支付行
		if (reportUnit.equals("payTheline")) {
			for (int i = 0; i < orderDownList.size(); i++) {
				ChartModel chartModel = new ChartModel();// 封装好的实体名称
				String name = orderDownList.get(i).getPaytheline();// 这句话的取值不同
				String value = orderDownList.get(i).getAmount();
				if (name != null && name != "") {
					chartModel.setX(name);// 给X轴赋值
					chartModel.setY(value);// 给Y轴赋值
					chartList.add(chartModel);// 存放到集合里边
				}
			}
		}
		return chartList;
	}
	// ------------------------------订单统计结束-----------------------------

	@Override
	public Integer getCountByQuery(PssOrderInfoQuery orderInfoQuery) {
		// TODO Auto-generated method stub
		return orderInfoDao.getCountByQuery(orderInfoQuery);
	}

}
