package com.sinosoft.business.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import  java.util.List;


public interface FulltextSearchManager {
/***
 * 通过一个输入字符串查询到库里含有this字段的内容
 * @param model
 * @param request
 * @return
 */
	List getFullTextSearch(ModelMap model, HttpServletRequest request);
	
}
