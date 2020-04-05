/*
 * Powered By 尹力
 * Since 2015 - 2017-40-17
 */

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssRefundDao;
import com.sinosoft.business.po.PssRefund;
import com.sinosoft.business.po.query.PssRefundQuery;

@Repository(value="refundDao")
public class PssRefundDaoImpl extends BaseDao implements PssRefundDao{
	
	@Override
	public int insertPssRefund(PssRefund PSS_REFUND){
		return insert("PSS_REFUNDDao.insertPssRefund",PSS_REFUND);
	}
	
	@Override
	public int deletePssRefund(Long id){
		return delete("PSS_REFUNDDao.deletePssRefund",id);
	}
	
	@Override
	public  int updatePssRefund(PssRefund PSS_REFUND){
		return update("PSS_REFUNDDao.updatePssRefund",PSS_REFUND);
	}
	
	@Override
	public PssRefund selectPssRefundById(Long orderMainid){
		return (PssRefund)getReadSqlSession().selectOne("PSS_REFUNDDao.getPssRefundById",orderMainid);
	}
	
	@Override
	public Integer getCountByQuery(PssRefundQuery PSS_REFUNDQuery){
		return getReadSqlSession().selectOne("PSS_REFUNDDao.getCountByQuery",PSS_REFUNDQuery);
	}
	
	@Override
	public List<PssRefund> getPssRefundInfoByQuery(PssRefundQuery PSS_REFUNDQuery){
		return getReadSqlSession().selectList("PSS_REFUNDDao.getPssRefundInfoByQuery",PSS_REFUNDQuery);
	}
	
	@Override
	public List<PssRefund> getPssRefundListByQuery(PssRefundQuery PSS_REFUNDQuery){
		return getReadSqlSession().selectList("PSS_REFUNDDao.getPssRefundListByQuery",PSS_REFUNDQuery);
	}

	@Override
	public PssRefund selectPssRefundByColl(Long collectid) {
		// TODO Auto-generated method stub
		return (PssRefund)getReadSqlSession().selectOne("PSS_REFUNDDao.getPssRefundBycollId",collectid);
	}
}
