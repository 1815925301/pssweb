
package com.sinosoft.business.service.impl;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;




import com.sinosoft.business.dao.PssTownDao;

import com.sinosoft.business.po.PssTown;
import com.sinosoft.business.service.PssTownService;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;




@Service("PssTownService")
public class PssTownServiceImpl implements PssTownService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PssTownServiceImpl.class);
	@Resource
	private PssTownDao dao;
	/**
	 * 页面初始化
	 */
	@Override
	public void PssTownPageInit(HttpServletRequest request,ModelMap model,String method){
		
	}

	/** 
	 * 创建PssTown
	 **/
	@Override
	public Boolean save(HttpServletRequest request,PssTown PssTown,ActivityModelMap modelMap){
		
		return false;
	}
	
	/** 
	 * 更新PssTown
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,PssTown PssTown,ActivityModelMap modelMap){
		
		return false;
	}
    
	/** 
	 * 删除PssTown
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据城市得到县
	 **/
	@Override   
    public void getPssTownByCity(HttpServletRequest request,ActivityModelMap modelMap){
    	
		// 获得当前登录用户的信息
				ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
				if(eUser != null){
					modelMap.put("user", eUser);
				}
				if (request.getParameter("cityCode") != null) {
					// 根据市编码得到县级列表
					List<PssTown> pssTownList = dao.selectPssTownByCity(Long.parseLong(request.getParameter("cityCode")));
					modelMap.put("pssTownList", pssTownList);
				}
    }
}
