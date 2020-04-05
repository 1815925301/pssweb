/*
 * Powered By 张永斌
 * Since 2015 - 2016-52-17
 */

package com.sinosoft.business.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinosoft.business.service.StudentService;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.business.po.Student;


@Controller
public class StudentController {
	
	@Resource
	private StudentService STUDENTService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	/**
	 * 管理页面
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/studentmanage.do")
	public String StudentManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问Student管理页面", method);
		model.addAttribute("pageHanName", "Student"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		STUDENTService.StudentPageInit( request, model, method);
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 查看对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/studentshow.do")
	public @ResponseBody Map<String, Object>  showStudent(
			HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/** 
	 * 进入新增页面
	 **/
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/student.do")
	public String Student(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		model.addAttribute("pageHanName", "Student"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		/*
		 * 逻辑处理
		 */
		return "manage/" + model.get("pageName");
	}
	
	/** 
	 * 保存新增对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/studentnew.do")
	public @ResponseBody Map<String, Object>  saveNew(
			HttpServletRequest request,HttpServletResponse response,Student student){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存更新对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/studentupdate.do")
	public @ResponseBody Map<String, Object>  update(
			HttpServletRequest request,HttpServletResponse response ,Student student) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
	/**
	 *删除对象
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/studentdelete.do")
	public @ResponseBody Map<String, Object> delete(HttpServletRequest request,HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		/*
		 * 逻辑处理
		 */
		return modelMap.getModelMap();
	}
	
}

