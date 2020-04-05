package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.FulltextSearchDao;
import com.sinosoft.business.po.query.ContentQuery;

@Repository(value="FulltextSearchDao")
public class FulltextSearchDaoImpl extends BaseDao implements FulltextSearchDao{

	@Override
	public List getDataByanything(ContentQuery contentQuery) {
		return  getReadSqlSession().selectList("FulltextSearchDao.getDataByAnything",contentQuery);
	}
}
