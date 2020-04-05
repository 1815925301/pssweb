package com.sinosoft.business.service.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.SystemConfigDao;
import com.sinosoft.business.po.Student;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.po.query.SystemConfigQuery;
import com.sinosoft.business.service.SystemConfigManager;
import com.sinosoft.common.util.SystemLogUtil;
import com.sinosoft.common.web.ActivityModelMap;

@Service("SystemConfigManager")
public class SystemConfigManagerImpl implements SystemConfigManager {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SystemConfigManagerImpl.class);
	@Resource
	private SystemConfigDao dao;

	/**
	 * 查询全部数据
	 */
	@Override
	public List<SystemConfig> getSystemConfig() {
		// TODO Auto-generated method stub
		return dao.getSystemConfig();
	}
	/**
	 * 通过一条id查询到详情
	 */
	@Override
	public void getSysConfigById(ActivityModelMap modelMap,
			HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			Long systemconfigId = Long.parseLong(request.getParameter("id"));
			SystemConfigQuery sys = new SystemConfigQuery();
			SystemConfig SystemConfig = dao.getSysConfigById(systemconfigId);
			modelMap.put("systemconfig", SystemConfig);
		}
	}

	/**
	 * 修改一条数据
	 */
	@Override
	public SystemConfig updateSystemConfig(SystemConfig systemConfig,
			HttpServletRequest requestSystem, ActivityModelMap modelMap,
			String id) {
		Long systemconfigId = Long.parseLong(id);
		SystemConfig SystemConfig = dao.getSysConfigById(systemconfigId);
		modelMap.put("systemconfig", SystemConfig);
		return SystemConfig;
	}

	/***
	 * 修改一条记录保存
	 * 
	 * @param systemConfig
	 * @param requestSystem
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@Override
	public boolean updateSystemConfigconfirm(SystemConfig systemConfig,
			HttpServletRequest requestSystem, ActivityModelMap modelMap) {
		boolean result = false;
		Integer resultNum = dao.updateSystemConfig(systemConfig);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}

	/**
	 * 删除某一条记录
	 */
	@Override
	public boolean removeSystemConfig(Long configId, ActivityModelMap modelMap,
			HttpServletRequest request) {
		boolean result = false;
		Integer resultNum = dao.removeSystemConfig(new Long(configId));

		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return true;
	}

	/**
	 * 增加某一条记录
	 */
	@Override
	public boolean saveSystemConfig(SystemConfig systemConfig,
			HttpServletRequest requestSystem, ActivityModelMap modelMap) {
		boolean result = false;
		if (systemConfig.getCreatetime().equals("")
				|| systemConfig.getCreatetime() == null) {
			systemConfig.setCreatetime(null);
		}
		Integer resultNum = dao.saveSystemConfig(systemConfig);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
			modelMap.put("status", "success");
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}

	/**
	 * 根据条件查询数据
	 */
	@Override
	public SystemConfig getSearchSystemConfigDate(ModelMap model,
			HttpServletRequest request, SystemConfig systemconfig) {
		if (request.getParameter("userNameSearch") != null) {
			String name = request.getParameter("userNameSearch");
			SystemConfigQuery sys = new SystemConfigQuery();
			SystemConfig systemConfig = dao.getSysConfigByName(name);
			model.put("systemconfig", systemConfig);
		}
		return systemconfig;
	}

	/**
	 * 页面初始化
	 */
	@Override
	public void SystemConfigPageInit(HttpServletRequest request,
			ModelMap model, String method) {
		LOGGER.debug("Service层：根据查询参数获取");
		SystemConfigQuery systemConfigQuery = new SystemConfigQuery();
		//设置排序
		systemConfigQuery.setSortBy("configId");
		systemConfigQuery.setSortType("1");
		if (method.equals("POST")) {
			String Starttime = request.getParameter("starttime");
			String enttime = request.getParameter("endtime");
			systemConfigQuery.setBtime(Starttime);
			systemConfigQuery.setEtime(enttime);
			//获取到时间放到model前台进行回显
			model.addAttribute("Starttime", Starttime);
			model.addAttribute("enttime", enttime);
			String pageNum = request.getParameter("pageNumInput");
			if (!StringUtils.isBlank(pageNum)) {
				systemConfigQuery.setPage(Integer.parseInt(pageNum));
			}
			String name = request.getParameter("SystemConfigNameSearch");
			if (!StringUtils.isBlank(name)) {
				systemConfigQuery.setConfigkey(name);
				model.addAttribute("name", name);
			}
			String type = request.getParameter("systemconfigtype");
			BigDecimal bd=new BigDecimal(type);
			if (!StringUtils.isBlank(type)) {
				systemConfigQuery.setConfigtype(bd);
				model.addAttribute("type", type);
			}
		}
		//查询配置信息一共有多少条数据
		Integer totalCount = dao.getCountByQuery(systemConfigQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount,
				systemConfigQuery.getPageSize(), systemConfigQuery.getPage(),
				systemConfigQuery.getStartNum());
		//放到model里前台做分页
		model.addAttribute("totalInfo", totalInfo);
		//获取所有数据页面进行展示数据
		List<SystemConfig> systemConfigList = dao
				.getSystemConfigList(systemConfigQuery);
		model.addAttribute("systemConfigList", systemConfigList);
		//查询有多少种配置类型
		List systemtypeList=dao.getsystemtypeList();
		model.addAttribute("systemtypeList", systemtypeList);
		
	}
	//此方法是通过配置信息里的key和描述信息查询所有相同的数据放到List集合里。
	@Override
	public SystemConfig getAllDataByKeyDes(String key, String des) {
		SystemConfig systemConfig = new SystemConfig();
		//设置传过来的key值和描述信息
		systemConfig.setConfigdes(des);
		systemConfig.setConfigkey(key);
		SystemConfig systemconfigList = dao.getAllDataByKeyDesc(systemConfig);
		List systemtypeList=dao.getsystemtypeList();
		
		return systemconfigList;
	}
}
