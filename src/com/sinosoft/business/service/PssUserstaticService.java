package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssUserstatic;
import com.sinosoft.common.web.ActivityModelMap;

/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssUserstaticService {
	
	/**
	 * 页面初始化
	 */
	public void PssUserstaticPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssUserstatic
	 **/
	public Boolean save(HttpServletRequest request,PssUserstatic pssUserstatic,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssUserstatic
	 **/	
    public Boolean update(HttpServletRequest request,PssUserstatic pssUserstatic,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssUserstatic
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssUserstatic
	 **/    
    public PssUserstatic getPssUserstatic(HttpServletRequest request,ActivityModelMap modelMap);
    
}
