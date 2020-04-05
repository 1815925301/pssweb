/*
 * Powered By 尹力
 * Since 2015 - 2016-34-20
 */

package com.sinosoft.business.service.impl;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssUserstatic;
import com.sinosoft.business.service.PssUserstaticService;
import com.sinosoft.common.web.ActivityModelMap;




@Service("PSS_USERSTATICService")
public class PssUserstaticServiceImpl implements PssUserstaticService {

	/**
	 * 页面初始化
	 */
	@Override
	public void PssUserstaticPageInit(HttpServletRequest request,ModelMap model,String method){
		
	}

	/** 
	 * 创建PssUserstatic
	 **/
	@Override
	public Boolean save(HttpServletRequest request,PssUserstatic pssUserstatic,ActivityModelMap modelMap){
		
		return false;
	}
	
	/** 
	 * 更新PssUserstatic
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,PssUserstatic pssUserstatic,ActivityModelMap modelMap){
		
		return false;
	}
    
	/** 
	 * 删除PssUserstatic
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据ID得到PssUserstatic
	 **/
	@Override   
    public PssUserstatic getPssUserstatic(HttpServletRequest request,ActivityModelMap modelMap){
    	
    	return null;
    }
}
