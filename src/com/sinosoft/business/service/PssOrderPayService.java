/*
 * Powered By 尹力
 * Since 2015 - 2016-16-29
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.PssOrderPay;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssOrderPayService {
	
	/**
	 * 页面初始化
	 */
	public void PssOrderPayPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssOrderPay
	 **/
	public Boolean save(HttpServletRequest request,PssOrderPay pssOrderPay,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssOrderPay
	 **/	
    public Boolean update(HttpServletRequest request,PssOrderPay pssOrderPay,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssOrderPay
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssOrderPay
	 **/    
    public PssOrderPay getPssOrderPay(HttpServletRequest request,ActivityModelMap modelMap);

	public PssOrderPay getPayinfoByMainid(Long orderMainId);

	public PssOrderPay getPayinfoByCollid(Long collectid);
    
}
