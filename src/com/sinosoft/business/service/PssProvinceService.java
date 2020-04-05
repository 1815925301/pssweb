

package com.sinosoft.business.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.business.po.PssProvince;
import com.sinosoft.common.web.ActivityModelMap;

/**
 * @Package com.sinosoft.business.service
 * @ClassName: PssProvinceService
 * @Description: 省份信息 服务层接口类
 * @author hao
 * @date 2016-8-24 
 */
public interface PssProvinceService {
	
	/**
	 * 页面初始化
	 */
	public void PssProvincePageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssProvince
	 **/
	public Boolean save(HttpServletRequest request,PssProvince pssProvince,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssProvince
	 **/	
    public Boolean update(HttpServletRequest request,PssProvince pssProvince,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssProvince
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssProvince
	 **/    
    public PssProvince getPssProvince(HttpServletRequest request,ActivityModelMap modelMap);
    
    /** 
	 * 根据incountryCode得到PssProvince
	 **/    
    public void getPssProvinceByCountry(HttpServletRequest request,ActivityModelMap modelMap);
    
}
