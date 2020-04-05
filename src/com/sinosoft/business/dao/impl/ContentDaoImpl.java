package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.ContentDao;
import com.sinosoft.business.po.Content;
import com.sinosoft.business.po.query.ContentQuery;

@Repository(value="ContentDao")
public class ContentDaoImpl extends BaseDao implements ContentDao{

	@Override
	public List<Content> getContent() {
		return null;
	}

	@Override
	public Integer saveContent(Content content) {
		return insert("ContentDao.insertContent", content);
	}

	@Override
	public Integer removeContent(Long id) {
		return delete("ContentDao.deleteContent",id);
	}
	
	@Override
	public List<Content> getContentList(ContentQuery contentQuery){
		return getReadSqlSession().selectList("ContentDao.getContentByQuery",contentQuery);
	}
	
	@Override
	public Integer updateContent(Content content) {
		return update("ContentDao.updateContent", content);
	}

	@Override
	public Content getContentById(Long id) {
		return getReadSqlSession().selectOne("ContentDao.selectcontentById",id);
	}

	@Override
	public Integer getCountByQuery(ContentQuery contentQuery) {
		return getReadSqlSession().selectOne("ContentDao.getCountByQuery",contentQuery);
	}

	@Override
	public Content getContentByName(String name) {
		return null;
	}
	
	@Override
	public List getContentType() {
		return getReadSqlSession().selectList("ContentDao.getContentType");
	}
	@Override
	public List getisrelease() {
		return getReadSqlSession().selectList("ContentDao.getisrelease");
	}
}
