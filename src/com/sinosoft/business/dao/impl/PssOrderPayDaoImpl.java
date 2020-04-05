/*
 * Powered By 尹力
 * Since 2015 - 2016-16-29
 */

package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssOrderPayDao;
import com.sinosoft.business.po.PssOrderPay;
import com.sinosoft.business.po.query.PssOrderPayQuery;



@Repository(value="PSS_ORDER_PAYDao")
public class PssOrderPayDaoImpl extends BaseDao implements PssOrderPayDao{
	
	@Override
	public int insertPssOrderPay(PssOrderPay PSS_ORDER_PAY){
		return insert("PSS_ORDER_PAYDao.insertPssOrderPay",PSS_ORDER_PAY);
	}
	
	@Override
	public int deletePssOrderPay(Long id){
		return delete("PSS_ORDER_PAYDao.deletePssOrderPay",id);
	}
	
	@Override
	public  int updatePssOrderPay(PssOrderPay PSS_ORDER_PAY){
		return update("PSS_ORDER_PAYDao.updatePssOrderPay",PSS_ORDER_PAY);
	}
	
	@Override
	public PssOrderPay selectPssOrderPayById(Long orderMainId){
		return (PssOrderPay)getReadSqlSession().selectOne("PSS_ORDER_PAYDao.getPssOrderPayById",orderMainId);
	}
	
	@Override
	public Integer getCountByQuery(PssOrderPayQuery PSS_ORDER_PAYQuery){
		return getReadSqlSession().selectOne("PSS_ORDER_PAYDao.getCountByQuery",PSS_ORDER_PAYQuery);
	}
	
	@Override
	public List<PssOrderPay> getPssOrderPayInfoByQuery(PssOrderPayQuery PSS_ORDER_PAYQuery){
		return getReadSqlSession().selectList("PSS_ORDER_PAYDao.getPssOrderPayInfoByQuery",PSS_ORDER_PAYQuery);
	}
	
	@Override
	public List<PssOrderPay> getPssOrderPayListByQuery(PssOrderPayQuery PSS_ORDER_PAYQuery){
		return getReadSqlSession().selectList("PSS_ORDER_PAYDao.getPssOrderPayListByQuery",PSS_ORDER_PAYQuery);
	}

	@Override
	public PssOrderPay selectPssOrderPayByCollId(Long collectid) {
		// TODO Auto-generated method stub
		return (PssOrderPay)getReadSqlSession().selectOne("PSS_ORDER_PAYDao.getPssOrderPayByCollId",collectid);
	}
}
