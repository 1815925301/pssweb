package com.sinosoft.business.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.business.dao.FulltextSearchDao;
import com.sinosoft.business.po.query.ContentQuery;
import com.sinosoft.business.service.FulltextSearchManager;

@Service("FulltextSearch")
public class FulltextSearchManagerImpl implements FulltextSearchManager{
	@Resource
	private FulltextSearchDao dao;
	@Override
	public List getFullTextSearch(ModelMap model, HttpServletRequest request) {
		//搜索新闻管理的检索
		if (request.getParameter("FulltextSearch") != null) {
			String name = request.getParameter("FulltextSearch");
			ContentQuery contentquery = new ContentQuery();
			contentquery.setFulltext(name);
			List data=dao.getDataByanything(contentquery);
			model.addAttribute("data", data);
		}
		return null;
	}

}
