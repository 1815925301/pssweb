package com.sinosoft.business.dao;

import java.util.List;

import com.sinosoft.business.po.query.ContentQuery;

public interface FulltextSearchDao {

	
	List getDataByanything(ContentQuery contentquery);
}
