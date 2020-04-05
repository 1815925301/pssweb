/*
 * Powered By 尹力
 * Since 2015 - 2016-28-22
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;


import com.sinosoft.business.po.FtpuserConfig;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author wlg
 * @version 1.0
 * @since 1.0
 * */
public interface FtpuserConfigService {
	
	/**
	 * 页面初始化
	 */
	public void ftpuserConfigPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建FtpuserConfig
	 **/
	public Boolean save(HttpServletRequest request,FtpuserConfig ftpuserConfig,ActivityModelMap modelMap);
    
	/** 
	 * 删除FtpuserConfig
	 **/
    public boolean remove(Long id, HttpServletRequest request,
			ActivityModelMap modelMap);
    

    
}
