/*
 * Powered By 尹力
 * Since 2015 - 2016-55-28
 */

package com.sinosoft.business.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssProductprice;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssProductpriceService {
	
	/**
	 * 页面初始化
	 */
	public void PssProductpricePageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssProductprice
	 **/
	public Boolean save(HttpServletRequest request,PssProductprice pssProductprice,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssProductprice
	 **/	
    public Boolean update(HttpServletRequest request,PssProductprice pssProductprice,ActivityModelMap modelMap);
     
 

	public List<PssProductprice> getProductpriceByVersion(String versionnum);
    
}
