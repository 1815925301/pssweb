

package com.sinosoft.business.service.impl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;


import com.sinosoft.business.dao.PssCityDao;

import com.sinosoft.business.po.PssCity;

import com.sinosoft.business.service.PssCityService;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.business.service.impl
 * @ClassName: PssCityServiceImpl
 * @Description: 城市信息 服务层实现类
 * @author hao
 * @date 2016-8-24 
 */


@Service("PssCityService")
public class PssCityServiceImpl implements PssCityService {

	
	@Resource
	private PssCityDao dao;

	/**
	 * 页面初始化
	 */
	@Override
	public void PssCityPageInit(HttpServletRequest request,ModelMap model,String method){
		
	}

	/** 
	 * 创建pssCity
	 **/
	@Override
	public Boolean save(HttpServletRequest request,PssCity pssCity,ActivityModelMap modelMap){
		
		return false;
	}
	
	/** 
	 * 更新pssCity
	 **/
	@Override
    public Boolean update(HttpServletRequest request,PssCity pssCity,ActivityModelMap modelMap){
		
		return false;
	}
    
	/** 
	 * 删除pssCity
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据ID得到pssCity
	 **/
	@Override   
	 public PssCity getPssCity(HttpServletRequest request,ActivityModelMap modelMap){
		return null;
		
	}

	/** 
	 * 根据省份名得到市
	 **/ 
	@Override 
	public void getPssCityByProvince(HttpServletRequest request,ActivityModelMap modelMap){
		
		// 获得当前登录用户的信息
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		if(eUser != null){
			modelMap.put("user", eUser);
		}
		if (request.getParameter("provinceCode") != null) {
			// 根据省份得到市级列表
			List<PssCity> pssCityList = dao.selectPssCityByProvince(Long.parseLong(request.getParameter("provinceCode")));
			modelMap.put("pssCityList", pssCityList);
		}
		
	}
}
