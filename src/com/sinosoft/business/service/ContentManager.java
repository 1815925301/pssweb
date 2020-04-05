package com.sinosoft.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.Content;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.common.web.ActivityModelMap;

public interface ContentManager {
	/***
	 * 页面初始化
	 * @param request
	 * @param model
	 * @param method
	 */
	public void ContentPageInit(HttpServletRequest request,ModelMap model,String method);

	/**
	 * 查询全部数据
	 * @param configId
	 * @return
	 */

	List<Content> getContent();
	/**
	 * 根据条件查询数据
	 * @param configId
	 * @return
	 */
	Content getSearchContentDate(ModelMap model, 
			HttpServletRequest request,Content content);
	/**
	 * 通过一条id查询到详情
	 * @param configId
	 * @return
	 */
	public void getContentById(ActivityModelMap modelMap, HttpServletRequest request);

	/**
	 * 增加某一条记录
	 * @param configId
	 * @return
	 */
	
	public boolean saveContent(Content content,HttpServletRequest requestSystem,ActivityModelMap modelMap);
	/**
	 * 删除某一条记录
	 * @param configId
	 * @return
	 */
	
	public boolean removeContent(Long id,ActivityModelMap modelMap, HttpServletRequest request);
	
	/**
	 * 修改某一条记录的查询显示
	 * @param configId
	 * @return
	 */
	public Content  updateContent(Content content,HttpServletRequest requestSystem,ActivityModelMap modelMap,String id);
	
	/**
	 * 修改某一条记录确认方法
	 * @param configId
	 * @return
	 */
	public boolean  updateContentfirm(Content content,HttpServletRequest requestSystem,ActivityModelMap modelMap);

}
