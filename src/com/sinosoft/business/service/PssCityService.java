package com.sinosoft.business.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.business.po.PssCity;
import com.sinosoft.common.web.ActivityModelMap;


/**
 * @Package com.sinosoft.business.service
 * @ClassName: PssCityService
 * @Description: 城市信息 服务层接口类
 * @author hao
 * @date 2016-8-24 
 */
public interface PssCityService {
	
	/**
	 * 页面初始化
	 */
	public void PssCityPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建pssCity
	 **/
	public Boolean save(HttpServletRequest request,PssCity pssCity,ActivityModelMap modelMap);
	
	/** 
	 * 更新pssCity
	 **/	
    public Boolean update(HttpServletRequest request,PssCity pssCity,ActivityModelMap modelMap);
    
	/** 
	 * 删除pssCity
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到pssCity
	 **/    
    public PssCity getPssCity(HttpServletRequest request,ActivityModelMap modelMap);
    
    /** 
	 * 根据省份名得到市
	 **/    
    public void getPssCityByProvince(HttpServletRequest request,ActivityModelMap modelMap);
    
}
