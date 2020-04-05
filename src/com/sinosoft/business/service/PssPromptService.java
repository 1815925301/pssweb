/*
 * Powered By J.C
 * Since 2015 - 2016-53-18
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.business.po.PssPrompt;
import com.sinosoft.common.web.ActivityModelMap;


/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface PssPromptService {
	
	/**
	 * 页面初始化
	 */
	public void pssPromptPageInit(HttpServletRequest request, ModelMap model,
			String method);


	/** 
	 * 创建Prompt
	 **/
	public Boolean savePrompt(HttpServletRequest request,PssPrompt pssPrompt,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssPrompt
	 **/	
    public Boolean updatePrompt(HttpServletRequest request,PssPrompt pssPrompt,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssPrompt
	 * @return 
	 **/
    public boolean remove(Long id,HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssPrompt
	 **/    
	public void getPromptById(ActivityModelMap modelMap,
			HttpServletRequest request);

	
    
}
