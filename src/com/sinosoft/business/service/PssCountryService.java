
package com.sinosoft.business.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.Province;
import com.sinosoft.business.po.PssCountry;
import com.sinosoft.business.po.query.PssCountryQuery;
import com.sinosoft.common.web.ActivityModelMap;


/**
 * @Package com.sinosoft.business.service
 * @ClassName: PssCountryService
 * @Description: 国家信息 服务层接口类
 * @author hao
 * @date 2016-8-24 
 */
public interface PssCountryService {
	
	/**
	 * 页面初始化
	 */
	public void PssCountryPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssCountry
	 **/
	public Boolean save(HttpServletRequest request,PssCountry pssCountry,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssCountry
	 **/	
    public Boolean update(HttpServletRequest request,PssCountry pssCountry,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssCountry
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssCountry
	 **/    
    public PssCountry getPssCountry(HttpServletRequest request,ActivityModelMap modelMap);
    
    public List<PssCountry> getCountry(PssCountryQuery pssCountryQuery);
    
}
