

package com.sinosoft.business.service.impl;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.business.dao.UserUpdateDao;
import com.sinosoft.business.po.UserUpdate;
import com.sinosoft.business.po.query.UserUpdateQuery;
import com.sinosoft.business.service.UserUpdateService;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;


@Service("UserUpdateService")
public class UserUpdateServiceImpl implements UserUpdateService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserUpdateServiceImpl.class);
	@Resource
	private UserUpdateDao userUpdateDao;
	/**
	 * 页面初始化
	 */
	@Override
	public void UserUpdatePageInit(HttpServletRequest request,ModelMap model,String method){
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		model.addAttribute("user",eUser);
	}

	/** 
	 * 创建PssUsermessageUpdate
	 **/
	@Override
	public Boolean save(HttpServletRequest request,UserUpdate pssUsermessageUpdate,ActivityModelMap modelMap){
		
		return false;
	}
	
	/** 
	 * 更新PssUsermessageUpdate
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,UserUpdate userUpdate,ActivityModelMap modelMap){
		Boolean result = false;
		Integer resultNum = userUpdateDao.updateUser(userUpdate);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}
    
	/** 
	 * 删除PssUsermessageUpdate
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
	
	/** 
	 * 根据id得到记录
	 **/
	@Override
	public UserUpdate selectMessageById(HttpServletRequest request,ActivityModelMap modelMap,Long id){
		UserUpdateQuery messageQuery = new UserUpdateQuery();
		UserUpdate userUpdate = userUpdateDao.selectMessageById(id);
		modelMap.put("messageUpdate", userUpdate);
		return userUpdate;
	}
}
