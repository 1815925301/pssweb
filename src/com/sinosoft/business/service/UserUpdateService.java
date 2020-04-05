
package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.business.po.UserUpdate;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.po.query.SystemConfigQuery;
import com.sinosoft.common.web.ActivityModelMap;
/**
 *
 * @author  
 * @version 1.0
 * @since 1.0
 * */
public interface UserUpdateService {
	
	/**
	 * 页面初始化
	 */
	public void UserUpdatePageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建PssUsermessageUpdate
	 **/
	public Boolean save(HttpServletRequest request,UserUpdate pssUsermessageUpdate,ActivityModelMap modelMap);
	
	/** 
	 * 更新PssUsermessageUpdate
	 **/	
    public Boolean update(HttpServletRequest request,UserUpdate pssUsermessageUpdate,ActivityModelMap modelMap);
    
	/** 
	 * 删除PssUsermessageUpdate
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到PssUsermessageUpdate
	 **/    
    public UserUpdate selectMessageById(HttpServletRequest request,ActivityModelMap modelMap,Long id);

    
}
