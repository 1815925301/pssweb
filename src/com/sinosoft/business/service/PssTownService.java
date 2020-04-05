/*
 * Powered By 尹力
 * Since 2015 - 2016-58-26
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.business.po.PssTown;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssTownService {
	
	/**
	 * 页面初始化
	 */
	public void PssTownPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssTown
	 **/
	public Boolean save(HttpServletRequest request,PssTown pssTown,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssTown
	 **/	
    public Boolean update(HttpServletRequest request,PssTown pssTown,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssTown
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
  
	/** 
	 * 根据城市得到县
	 **/    
    public void getPssTownByCity(HttpServletRequest request,ActivityModelMap modelMap);
    
}
