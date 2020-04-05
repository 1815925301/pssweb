/*
 * Powered By 尹力
 * Since 2015 - 2016-53-18
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssCollectInfoService {
	
	/**
	 * 页面初始化
	 */
	public void pssCollectInfoPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssCollectInfo
	 **/
	public Boolean saveCollectInfoInfo(HttpServletRequest request,PssCollectInfo pssCollectInfo,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssCollectInfo
	 **/	
    public Boolean updateCollectInfoInfo(HttpServletRequest request,PssCollectInfo pssCollectInfo,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssCollectInfo
	 * @return 
	 **/
    public boolean removeCollectInfoInfo(Long id,HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssCollectInfo
	 **/    
	public void getCollectInfoById(ActivityModelMap modelMap,
			HttpServletRequest request);
	public PssCollectInfo getCollectInfoById(Long id);

	public PssCollectInfo getCollectInfoByTaskId(Long taskid);
    
}
