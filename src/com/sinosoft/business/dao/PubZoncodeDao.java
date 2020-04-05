package com.sinosoft.business.dao;

import java.util.List;

import com.sinosoft.business.po.PubZoncode;
import com.sinosoft.business.po.query.PubZoncodeQuery;

public interface PubZoncodeDao {
	
    int deleteByPrimaryKey(String zonecode);

    int insertSelective(PubZoncode record);

    PubZoncode selectByPrimaryKey(String zonecode);

    int updateByPrimaryKeySelective(PubZoncode record);

	List<PubZoncode> selectByPrimaryQuery(PubZoncodeQuery record);
}