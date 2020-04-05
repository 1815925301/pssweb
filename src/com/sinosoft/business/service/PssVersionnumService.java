/*
 * Powered By 尹力
 * Since 2015 - 2016-54-31
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssVersionnum;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssVersionnumService {
	
	/**
	 * 页面初始化
	 */
	public void PssVersionnumPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssVersionnum
	 **/
	public Boolean save(HttpServletRequest request,PssVersionnum pssVersionnum,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssVersionnum
	 **/	
    public Boolean update(HttpServletRequest request,PssVersionnum pssVersionnum,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssVersionnum
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssVersionnum
	 **/    
    public PssVersionnum getPssVersionnum(HttpServletRequest request,ActivityModelMap modelMap);
    
	
    
}
