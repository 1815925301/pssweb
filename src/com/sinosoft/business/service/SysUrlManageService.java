/*
 * Powered By J.C
 * Since 2016-08-11
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.SysUrlManage;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface SysUrlManageService {
	
	/**
	 * 页面初始化
	 */
	public void SysUrlManagePageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建SysUrlManage
	 **/
	public Boolean save(HttpServletRequest request,SysUrlManage sysUrlManage,ActivityModelMap modelMap);
	
	/** 
	 * 更新SysUrlManage
	 **/	
    public Boolean update(HttpServletRequest request,SysUrlManage sysUrlManage,ActivityModelMap modelMap);
    
	/** 
	 * 删除SysUrlManage
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到SysUrlManage
	 **/    
    public SysUrlManage getSysUrlManage(HttpServletRequest request,ActivityModelMap modelMap);
    
}
