/*
 * Powered By 张永斌
 * Since 2015 - 2016-52-17
 */

package com.sinosoft.business.service;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.business.po.Student;
import com.sinosoft.common.web.ActivityModelMap;

/**
 *
 * @author 
 * @version 1.0
 * @since 1.0
 * */
public interface StudentService {
	
	/**
	 * 页面初始化
	 */
	public void StudentPageInit(HttpServletRequest request,ModelMap model,String method);

	/** 
	 * 创建Student
	 **/
	public Boolean save(HttpServletRequest request,Student student,ActivityModelMap modelMap);
	
	/** 
	 * 更新Student
	 **/	
    public Boolean update(HttpServletRequest request,Student student,ActivityModelMap modelMap);
    
	/** 
	 * 删除Student
	 **/
    public void remove(HttpServletRequest request,ActivityModelMap modelMap);
    
	/** 
	 * 根据ID得到Student
	 **/    
    public Student getStudent(HttpServletRequest request,ActivityModelMap modelMap);
    
}
