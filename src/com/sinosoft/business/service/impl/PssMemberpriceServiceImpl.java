/*
 * Powered By 尹力
 * Since 2015 - 2016-01-10
 */

package com.sinosoft.business.service.impl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.business.dao.PssMemberpriceDao;
import com.sinosoft.business.po.PssMemberprice;
import com.sinosoft.business.service.PssMemberpriceService;
import com.sinosoft.common.web.ActivityModelMap;



@Service("PSS_MEMBERPRICEService")
public class PssMemberpriceServiceImpl implements PssMemberpriceService {
	@Resource
	private  PssMemberpriceDao memberpriceDao;

	/**
	 * 页面初始化
	 */
	@Override
	public void PssMemberpricePageInit(HttpServletRequest request,ModelMap model,String method){
		
		List<PssMemberprice> mlist=memberpriceDao.getPssMemberpriceInfo();
		model.addAttribute("mlist", mlist);
	}

	/** 
	 * 创建PssMemberprice
	 **/
	@Override
	public Boolean save(HttpServletRequest request,PssMemberprice pssMemberprice,ActivityModelMap modelMap){
		Boolean result=false;
		Integer resultNumber=memberpriceDao.insertPssMemberprice(pssMemberprice);
		if(resultNumber!=null){
			result =true;
		}else{
			result =false;
		}
		return result;
	}
	
	/** 
	 * 更新PssMemberprice
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,PssMemberprice pssMemberprice,ActivityModelMap modelMap){
		Boolean result=false;
		Integer resultNumber=memberpriceDao.updatePssMemberprice(pssMemberprice);
		if(resultNumber!=null){
			result =true;
		}else{
			result =false;
		}
		return result;
	}
    
	/** 
	 * 删除PssMemberprice
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据ID得到PssMemberprice
	 **/
	@Override   
    public PssMemberprice getPssMemberprice(HttpServletRequest request,ActivityModelMap modelMap){
    	
    	return null;
    }

	@Override
	public PssMemberprice getPssMemberprice(Long id) {
		
		return memberpriceDao.selectPssMemberpriceById(id);
	}
}
