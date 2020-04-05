/*
 * Powered By 尹力
 * Since 2015 - 2016-56-25
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.business.po.PssMetadata0scene;
import com.sinosoft.common.web.ActivityModelMap;
/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssMetadata0sceneService {
	
	/**
	 * 页面初始化
	 */
	public void PssMetadata0scenePageInit(HttpServletRequest request,ModelMap model,String method);

    
	/** 
	 * 根据ID得到PssMetadata0scene
	 * @return 
	 **/    
	PssMetadata0scene getPssMetadata0scene(HttpServletRequest request,ModelMap model, String string);
    
}
