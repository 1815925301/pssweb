/*
 * Powered By 尹力
 * Since 2015 - 2016-01-10
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssMemberprice;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssMemberpriceService {
	
	/**
	 * 页面初始化
	 */
	public void PssMemberpricePageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssMemberprice
	 **/
	public Boolean save(HttpServletRequest request,PssMemberprice pssMemberprice,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssMemberprice
	 **/	
    public Boolean update(HttpServletRequest request,PssMemberprice pssMemberprice,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssMemberprice
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssMemberprice
	 **/    
    public PssMemberprice getPssMemberprice(HttpServletRequest request,ActivityModelMap modelMap);

	public com.sinosoft.business.po.PssMemberprice getPssMemberprice(Long id);
    
}
