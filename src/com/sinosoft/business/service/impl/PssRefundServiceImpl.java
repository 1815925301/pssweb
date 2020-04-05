/*
 * Powered By 尹力
 * Since 2015 - 2017-40-17
 */

package com.sinosoft.business.service.impl;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.business.dao.PssRefundDao;
import com.sinosoft.business.po.PssRefund;
import com.sinosoft.business.service.PssRefundService;
import com.sinosoft.common.web.ActivityModelMap;

@Service("refundService")
public class PssRefundServiceImpl implements PssRefundService {
	@Resource
	private PssRefundDao refundDao;
	/**
	 * 页面初始化
	 */
	@Override
	public void PssRefundPageInit(HttpServletRequest request,ModelMap model,String method){
		
	}

	/** 
	 * 创建PssRefund
	 **/
	@Override
	public Boolean save(HttpServletRequest request,PssRefund pssRefund,ActivityModelMap modelMap){
		
		return false;
	}
	
	/** 
	 * 更新PssRefund
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,PssRefund pssRefund,ActivityModelMap modelMap){
		
		return false;
	}
    
	/** 
	 * 删除PssRefund
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    


	@Override
	public PssRefund getPssRefund(Long orderMainid) {
		// TODO Auto-generated method stub
		return refundDao.selectPssRefundById(orderMainid);
	}

	@Override
	public PssRefund getPssRefundBycollectid(Long collectid) {
		// TODO Auto-generated method stub
		return refundDao.selectPssRefundByColl(collectid);
	}
}
