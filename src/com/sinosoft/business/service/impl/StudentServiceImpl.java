/*
 * Powered By 张永斌
 * Since 2015 - 2016-52-17
 */

package com.sinosoft.business.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.StudentDao;
import com.sinosoft.business.po.Student;
import com.sinosoft.business.po.query.StudentQuery;
import com.sinosoft.business.service.StudentService;
import com.sinosoft.common.web.ActivityModelMap;



@Service("STUDENTService")
public class StudentServiceImpl implements StudentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	@Resource
	private  StudentDao studnetDao;

	/**
	 * 页面初始化
	 */
	@Override
	public void StudentPageInit(HttpServletRequest request,ModelMap model,String method){
		LOGGER.debug("Service层：根据查询参数获取省份信息 用于省份管理页面");
		StudentQuery studentQuery = new StudentQuery();
		//以id升序排序
		studentQuery.setSortBy("id");
		studentQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				studentQuery.setPage(Integer.parseInt(pageNum));
			}
			String name = request.getParameter("userNameSearch");
			if (! StringUtils.isBlank(name)) {
				studentQuery.setName(name);
				model.addAttribute("name", name);
			}
		}
		Integer totalCount = studnetDao.getCountByQuery(studentQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, studentQuery.getPageSize(), 
				studentQuery.getPage(), studentQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<Student> studentList = studnetDao.getStudentInfoByQuery(studentQuery);
		
		model.addAttribute("studentList", studentList);
	}

	/** 
	 * 创建Student
	 **/
	@Override
	public Boolean save(HttpServletRequest request,Student student,ActivityModelMap modelMap){
		
		return false;
	}
	
	/** 
	 * 更新Student
	 **/	
	@Override
    public Boolean update(HttpServletRequest request,Student student,ActivityModelMap modelMap){
		
		return false;
	}
    
	/** 
	 * 删除Student
	 **/
	@Override
    public void remove(HttpServletRequest request,ActivityModelMap modelMap){
		
	}
    
	/** 
	 * 根据ID得到Student
	 **/
	@Override   
    public Student getStudent(HttpServletRequest request,ActivityModelMap modelMap){
    	
    	return null;
    }
}
