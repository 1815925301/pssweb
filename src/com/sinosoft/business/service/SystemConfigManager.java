package com.sinosoft.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.common.web.ActivityModelMap;

public interface SystemConfigManager {
	
	/**
	 * 页面初始化
	 */
	public void SystemConfigPageInit(HttpServletRequest request,ModelMap model,String method);
	
	/**
	 * 查询全部数据
	 * @param configId
	 * @return
	 */
	
	List<SystemConfig> getSystemConfig();

	/**
	 * 根据条件查询数据
	 * @param configId
	 * @return
	 */
	SystemConfig getSearchSystemConfigDate(ModelMap model, 
			HttpServletRequest request,SystemConfig systemconfig);

	/**
	 * 通过一条id查询到详情
	 * @param configId
	 * @return
	 */
	public void getSysConfigById(ActivityModelMap modelMap, HttpServletRequest request);

	/**
	 * 增加某一条记录
	 * @param configId
	 * @return
	 */
	
	public boolean saveSystemConfig(SystemConfig systemConfig,HttpServletRequest requestSystem,ActivityModelMap modelMap);

	/**
	 * 删除某一条记录
	 * @param configId
	 * @return
	 */
	
	public boolean removeSystemConfig(Long id,ActivityModelMap modelMap, HttpServletRequest request);
	
	/**
	 * 修改某一条记录
	 * @param configId
	 * @return
	 */
	public SystemConfig  updateSystemConfig(SystemConfig systemConfig,HttpServletRequest requestSystem,ActivityModelMap modelMap,String id);
	
	/**
	 * 修改某一条记录确认方法
	 * @param configId
	 * @return
	 */
	public boolean  updateSystemConfigconfirm(SystemConfig systemConfig,HttpServletRequest requestSystem,ActivityModelMap modelMap);
	
	/**
	 * 通过传递两个参数，配置管理的key值和配置描述来获得整个list包含的列表
	 * @param configId
	 * @return
	 */
	public SystemConfig getAllDataByKeyDes(String key,String des);
}
