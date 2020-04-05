/*
 * Powered By 尹力
 * Since 2015 - 2016-16-29
 */

package com.sinosoft.business.service.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.business.dao.PssOrderPayDao;
import com.sinosoft.business.po.PssOrderPay;
import com.sinosoft.business.service.PssOrderPayService;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.dblog.po.Syslog;
import com.sinosoft.dblog.service.MonitorReceiverLogService;
import com.sinosoft.security.po.extend.ExtendUsers;




@Service("PSS_ORDER_PAYService")
public class PssOrderPayServiceImpl implements PssOrderPayService {
	@Resource
	private PssOrderPayDao orderPayDao;
	@Resource
	private MonitorReceiverLogService syslog;
	/**
	 * 页面初始化
	 */
	@Override
	public void PssOrderPayPageInit(HttpServletRequest request,ModelMap model,String method){
		
	}

	/** 
	 * 创建PssOrderPay
	 **/
	@Override
	public Boolean save(HttpServletRequest request,PssOrderPay pssOrderPay,ActivityModelMap modelMap){
		Boolean resultNum=false;
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String userName= String.valueOf(eUser.getUserName());
		Integer result=orderPayDao.insertPssOrderPay(pssOrderPay);
		Syslog log = new Syslog();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(result!=null && result >=1){
			resultNum=true;
			Date date = new Date();
		    String	date1 = sdf.format(date);
			log.setCreatetime(date1);
			log.setLogtype("info");
			log.setDescription(userName+"提交支付信息成功");
			log.setLoglevel("2");
			syslog.saveSystemLog(log);
		}else{
			resultNum=false;
			Date date = new Date();
		    String	date1 = sdf.format(date);
			log.setCreatetime(date1);
			log.setLogtype("info");
			log.setDescription(userName+"提交支付信息失败");
			log.setLoglevel("2");
			syslog.saveSystemLog(log);
		}
		
		return resultNum;
	}
	
	/** 
	 * 更新PssOrderPay
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,PssOrderPay pssOrderPay,ActivityModelMap modelMap){
		
		return false;
	}
    
	/** 
	 * 删除PssOrderPay
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据ID得到PssOrderPay
	 **/
	@Override   
    public PssOrderPay getPssOrderPay(HttpServletRequest request,ActivityModelMap modelMap){
    	
    	return null;
    }

	@Override
	public PssOrderPay getPayinfoByMainid(Long orderMainId) {
		PssOrderPay pssOrderPay=orderPayDao.selectPssOrderPayById(orderMainId);
		return pssOrderPay;
	}

	@Override
	public PssOrderPay getPayinfoByCollid(Long collectid) {
		PssOrderPay pssOrderPay=orderPayDao.selectPssOrderPayByCollId(collectid);
		return pssOrderPay;
	}
}
