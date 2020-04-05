

package com.sinosoft.business.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssTasMetadataDao;
import com.sinosoft.business.po.PssMetadata0scene;
import com.sinosoft.business.po.PssOrderInfo;
import com.sinosoft.business.po.PssTasMetadata;

import com.sinosoft.business.po.query.PssOrderInfoQuery;
import com.sinosoft.business.po.query.PssTasMetadataQuery;



@Repository(value="PssTasMetadataDao")
public class PssTasMetadataDaoImpl extends BaseDao implements PssTasMetadataDao{
	
	@Override
	//空间表的查询
	public List<PssTasMetadataQuery> getPssTasMetadataByQuery(PssTasMetadataQuery pssTasMetadataquery) {
		return  getSdeSqlSession().selectList("PssTasMetadataDao.getPssTasMetadataByQuery",pssTasMetadataquery);
	}

	@Override
	public PssTasMetadata getTasMetadataByid(PssTasMetadataQuery metadataQuery) {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectOne("PssTasMetadataDao.getTasMetadataById",metadataQuery);
	}

	@Override
	public PssTasMetadata selectTasPssMetadataById(PssTasMetadata pssTasMetadata) {
		return (PssTasMetadata)getReadSqlSession().selectOne("PssTasMetadataDao.getMetadataByproductId",pssTasMetadata);
	}
	
	@Override
	public List<Map<String, Object>> findSonProductByCond(
			String id) {
		return getReadSqlSession().selectList(
				"PssTasMetadataDao.findSonProductByCond", id);
	}
}
