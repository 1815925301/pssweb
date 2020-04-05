/*
 * Powered By 尹力
 * Since 2015 - 2017-40-17
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssRefund;
import com.sinosoft.common.web.ActivityModelMap;
/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssRefundService {
	
	/**
	 * 页面初始化
	 */
	public void PssRefundPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssRefund
	 **/
	public Boolean save(HttpServletRequest request,PssRefund pssRefund,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssRefund
	 **/	
    public Boolean update(HttpServletRequest request,PssRefund pssRefund,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssRefund
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssRefund
	 **/    
   

	public PssRefund getPssRefund(Long orderMainid);

	public PssRefund getPssRefundBycollectid(Long collectid);
    
}
