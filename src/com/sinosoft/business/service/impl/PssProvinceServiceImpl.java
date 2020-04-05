
package com.sinosoft.business.service.impl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;

import com.sinosoft.business.dao.PssProvinceDao;
import com.sinosoft.business.po.PssProvince;
import com.sinosoft.business.service.PssProvinceService;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.business.service.impl
 * @ClassName: PssProvinceServiceImpl
 * @Description: 省份信息 服务层实现类
 * @author hao
 * @date 2016-8-24 
 */
@Service("PssProvinceService")
public class PssProvinceServiceImpl implements PssProvinceService {
	@Resource
	private PssProvinceDao dao;

	@Override
	public void PssProvincePageInit(HttpServletRequest request, ModelMap model,
			String method) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean save(HttpServletRequest request, PssProvince pssProvince,
			ActivityModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(HttpServletRequest request, PssProvince pssProvince,
			ActivityModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(HttpServletRequest request, ActivityModelMap modelMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PssProvince getPssProvince(HttpServletRequest request,
			ActivityModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 
	 * 根据国家得到省份列表
	 **/ 
	@Override
    public void getPssProvinceByCountry(HttpServletRequest request,
			ActivityModelMap modelMap){
		
		// 获得当前登录用户的信息
				ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
						
				if(eUser != null){
					modelMap.put("user", eUser);
				}
		if (request.getParameter("countryCode") != null) {
			// 根据国家编码得到省份列表
			List<PssProvince> pssProvinceList = dao.selectPssProvinceByCountry(Long.parseLong(request.getParameter("countryCode")));
			modelMap.put("pssProvinceList", pssProvinceList);
		}
		
	}
	
	
}
