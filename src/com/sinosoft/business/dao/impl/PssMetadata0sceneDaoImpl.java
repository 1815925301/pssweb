/*
 * Powered By 尹力
 * Since 2015 - 2016-56-25
 */

package com.sinosoft.business.dao.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssMetadata0sceneDao;
import com.sinosoft.business.po.PssMetadata0scene;
import com.sinosoft.business.po.PssProduct;
import com.sinosoft.business.po.query.PssMetadata0sceneQuery;


@Repository(value="PSS_METADATA_0_SCENEDao")
public class PssMetadata0sceneDaoImpl extends BaseDao implements PssMetadata0sceneDao{

	@Override
	public PssMetadata0scene selectPssMetadata0sceneQueryById(PssMetadata0scene pssMetadata0scene) {
		return (PssMetadata0scene)getReadSqlSession().selectOne("PSS_METADATA_0_SCENEDao.getPssMetadata0sceneById",pssMetadata0scene);
	}

	@Override
	public PssMetadata0scene getScensByqueryById(String metadataid) {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectOne("PSS_METADATA_0_SCENEDao.getMetadataById", metadataid);
	}
	
	
}
