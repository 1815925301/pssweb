/*
 * Powered By J.C
 * Since 2015 - 2016-53-18
 */

package com.sinosoft.business.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssPromptDao;
import com.sinosoft.business.po.PssPrompt;
import com.sinosoft.business.po.query.PssPromptQuery;


@Repository(value="PssPromptDao")
public class PssPromptDaoImpl extends BaseDao implements PssPromptDao{
	
	@Override
	public int insertPssPrompt(PssPrompt pssPrompt){
		return insert("PssPromptDao.insertPssPrompt",pssPrompt);
	}
	
	@Override
	public int deletePssPrompt(Long id){
		return delete("PssPssPromptDao.deletePssPrompt",id);
	}
	
	@Override
	public  int updatePssPrompt(PssPrompt pssPrompt){
		return update("PssPromptDao.updatePssPrompt",pssPrompt);
	}
	
	@Override
	public PssPrompt selectPssPromptById(Long id){
		return getReadSqlSession().selectOne("PssPromptDao.getPssPromptById",id);
	}
	
	@Override
	public Integer getCountByQuery(PssPromptQuery pssPromptQuery){
		return getReadSqlSession().selectOne("PssPromptDao.getCountByQuery",pssPromptQuery);
	}
	
	
	
	@Override
	public List<PssPrompt> getPssPromptListByQuery(PssPromptQuery pssPromptQuery){
		return getReadSqlSession().selectList("PssPromptDao.getPssPromptListByQuery",pssPromptQuery);
	}

	@Override
	public List<PssPrompt> getPssPromptByQuery(PssPromptQuery pssPromptQuery) {
		// @Override
			return getReadSqlSession().selectList("PssPromptDao.getPssPromptByQuery",pssPromptQuery);
	}


}
