package com.sinosoft.business.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.ContentDao;
import com.sinosoft.business.dao.SystemConfigDao;
import com.sinosoft.business.po.Content;
import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.business.po.query.ContentQuery;
import com.sinosoft.business.po.query.SystemConfigQuery;
import com.sinosoft.business.service.ContentManager;
import com.sinosoft.common.web.ActivityModelMap;
@Service("ContentManager")
public class ContentManagerImpl implements ContentManager{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContentManagerImpl.class);
	@Resource
	private ContentDao dao;

	@Override
	public void ContentPageInit(HttpServletRequest request, ModelMap model,
			String method) {
		LOGGER.debug("Service层：根据查询参数获取");
		ContentQuery contentquery = new ContentQuery();
		//设置排序方式
		contentquery.setSortBy("contentid");
		contentquery.setSortType("1");
		if(method.equals("POST")){
			String Starttime = request.getParameter("starttime");
			String enttime = request.getParameter("endtime");
			contentquery.setBtime(Starttime);
			contentquery.setEtime(enttime);
			//进行首页回显
			model.addAttribute("Starttime", Starttime);
			model.addAttribute("enttime", enttime);
			String pageNum = request.getParameter("pageNumInput");
			if (!StringUtils.isBlank(pageNum)) {
				contentquery.setPage(Integer.parseInt(pageNum));
			}
			String name = request.getParameter("ContentSearch");
			//获取首页的搜索栏里的
			if (!StringUtils.isBlank(name)) {
				contentquery.setContenttitle(name);
				model.addAttribute("name", name);
			}
			String contentType =  request.getParameter("contentTypeSearch");
			Long rontentt = Long.parseLong(contentType);
			if (!StringUtils.isBlank(contentType)) {
				contentquery.setContenttype(rontentt);
				model.addAttribute("contentTypee", contentType);
			}
			if(request.getParameter("contentTypeSearch") == null ||"".equals(request.getParameter("contentTypeSearch"))){
				contentquery.setContenttype(null);
			}
			String getisrelease = request.getParameter("contentisreleaseSearch");
			Long getisreleasee = Long.parseLong(getisrelease);
			if (!StringUtils.isBlank(getisrelease)) {
				contentquery.setIsrelease(getisreleasee);
				model.addAttribute("getisreleaselist", getisrelease);
			}
		}
		
		//获取到新闻管理数据总量
		Integer totalCount = dao.getCountByQuery(contentquery);
		TotalInfo totalInfo = new TotalInfo(totalCount,
				contentquery.getPageSize(), contentquery.getPage(),
				contentquery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		//获得新闻管理显示列表
		List<Content> contentList = dao
				.getContentList(contentquery);
		model.addAttribute("contentList", contentList);
		//获取新闻管理内容类型
		List getcontentType = dao.getContentType();
		model.addAttribute("getcontentType", getcontentType);
		//是否发布
		List getisrelease = dao.getisrelease();
		model.addAttribute("getisreleaseget", getisrelease);
	}

	@Override
	public List<Content> getContent() {
		return null;
	}

	@Override
	public Content getSearchContentDate(ModelMap model,
			HttpServletRequest request, Content content) {
		if (request.getParameter("ContentSearch") != null) {
			String name = request.getParameter("ContentSearch");
			ContentQuery sys = new ContentQuery();
			Content contents = dao.getContentByName(name);
			model.put("contents", contents);
		}
		return content;
	}

	@Override
	public void getContentById(ActivityModelMap modelMap,
			HttpServletRequest request) {
		if (request.getParameter("id") != null) {
			Long contentid = Long.parseLong(request.getParameter("id"));
			ContentQuery sys = new ContentQuery();
			Content content = dao.getContentById(contentid);
			modelMap.put("content", content);
		}
		
	}

	@Override
	public boolean saveContent(Content content,
			HttpServletRequest requestSystem, ActivityModelMap modelMap) {
		boolean result = false;
		if (content.getCreatetime().equals("")
				|| content.getCreatetime() == null) {
			content.setCreatetime(null);
		}
		Integer resultNum = dao.saveContent(content);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}

	@Override
	public boolean removeContent(Long id, ActivityModelMap modelMap,
			HttpServletRequest request) {
		boolean result = false;
		Integer resultNum = dao.removeContent(new Long(id));
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return true;
	}

	@Override
	public Content updateContent(Content content,
			HttpServletRequest requestSystem, ActivityModelMap modelMap,
			String id) {
		Long contentid = Long.parseLong(id);
		Content contents = dao.getContentById(contentid);
		modelMap.put("contents", contents);
		return contents;
	}

	@Override
	public boolean updateContentfirm(Content content,
			HttpServletRequest requestSystem, ActivityModelMap modelMap) {
		boolean result = false;
		if (content.getImage().length==0) {
			content.setImage(null);
		}
		Integer resultNum = dao.updateContent(content);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}
	
}
