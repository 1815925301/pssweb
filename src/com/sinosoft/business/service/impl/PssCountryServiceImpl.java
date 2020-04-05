
package com.sinosoft.business.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;






import com.sinosoft.business.dao.PersonalCenterDao;
import com.sinosoft.business.dao.PssCountryDao;
import com.sinosoft.business.po.PssCountry;
import com.sinosoft.business.po.query.PssCountryQuery;
import com.sinosoft.business.service.PssCountryService;
import com.sinosoft.common.web.ActivityModelMap;

/**
 * @Package com.sinosoft.business.service.impl
 * @ClassName: PssCountryServiceImpl
 * @Description: 国家信息 服务层实现类
 * @author hao
 * @date 2016-8-24 
 */


@Service("PssCountryService")
public class PssCountryServiceImpl implements PssCountryService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PssCountryServiceImpl.class);
	@Resource
	private PssCountryDao dao;
	/**
	 * 页面初始化
	 */
	@Override
	public void PssCountryPageInit(HttpServletRequest request,ModelMap model,String method){
		
	}

	/** 
	 * 创建PssCountry
	 **/
	@Override
	public Boolean save(HttpServletRequest request,PssCountry pssCountry,ActivityModelMap modelMap){
		
		return false;
	}
	
	/** 
	 * 更新PssCountry
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,PssCountry pssCountry,ActivityModelMap modelMap){
		
		return false;
	}
    
	/** 
	 * 删除PssCountry
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据ID得到PssCountry
	 **/
	@Override   
    public PssCountry getPssCountry(HttpServletRequest request,ActivityModelMap modelMap){
    	
    	return null;
    }
	/** 
	 * 得到PssCountry
	 **/
	@Override 
	public List<PssCountry> getCountry(PssCountryQuery pssCountryQuery){
		return dao.getPssCountryListByQuery(pssCountryQuery);
		
	}
	
}
