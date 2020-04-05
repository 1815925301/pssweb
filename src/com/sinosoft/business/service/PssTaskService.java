/*
 * Powered By 尹力
 * Since 2015 - 2016-45-13
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssTask;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssTaskService {
	
	/**
	 * 页面初始化
	 */
	public void PssTaskPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssTask
	 **/
	public Boolean save(HttpServletRequest request,PssTask pssTask,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssTask
	 **/	
    public Boolean update(HttpServletRequest request,PssTask pssTask,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssTask
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssTask
	 **/    
    public PssTask getPssTask(HttpServletRequest request,ActivityModelMap modelMap);
    
}
