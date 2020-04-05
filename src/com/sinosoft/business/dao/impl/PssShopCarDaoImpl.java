package com.sinosoft.business.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssShopCarDao;
import com.sinosoft.business.po.PssShopCar;
import com.sinosoft.business.po.query.PssShopCarQuery;

@Repository(value = "shopCarDao")
public class PssShopCarDaoImpl extends BaseDao implements PssShopCarDao {

	@Override
	public int insertPssShopCar(PssShopCar pssshopcar) {
		return insert("PssShopCarDao.insertPssShopCar", pssshopcar);
	}

	@Override
	public int deletePssShopCar(PssShopCar pssshopcar) {
		return delete("PssShopCarDao.deletePssShopCar", pssshopcar);
	}

	@Override
	public int deletePssShopCarByIdS(String orderIds) {
		return delete("PssShopCarDao.deletePssShopCarByIdS", orderIds);
	}

	@Override
	public int updatePssShopCar(PssShopCar pssshopcar) {
		return update("PssShopCarDao.updatePssShopCar", pssshopcar);
	}

	@Override
	public PssShopCar selectPssShopCarById(PssShopCar pssshopcar) {
		return (PssShopCar) getReadSqlSession().selectOne(
				"PssShopCarDao.selectPssShopCarById", pssshopcar);
	}

	@Override
	public Integer getCountByQuery(PssShopCarQuery pssshopcarQuery) {
		return getReadSqlSession().selectOne("PssShopCarDao.getCountByQuery",
				pssshopcarQuery);
	}

	@Override
	public List<Map<String, Object>> findPssShopCarInfoById(String orderIdS) {
		return getReadSqlSession().selectList(
				"PssShopCarDao.findPssShopCarInfoById", orderIdS);
	}

	@Override
	public List<PssShopCar> getPssShopCarInfoByQuery(
			PssShopCarQuery pssshopcarQuery) {
		return getReadSqlSession().selectList(
				"PssShopCarDao.getPssShopCarInfoByQuery", pssshopcarQuery);
	}

	@Override
	public List<PssShopCar> getPssShopCarListByQuery(
			PssShopCarQuery pssshopcarQuery) {
		return getReadSqlSession().selectList("PssShopCarDao.getPssShopCarListByQuery",pssshopcarQuery);
	}
	@Override
	public List<PssShopCar> getPssShopCarListall(
			PssShopCarQuery pssshopcarQuery) {
		return getReadSqlSession().selectList("PssShopCarDao.getPssShopCarListall",pssshopcarQuery);
	}
	
	@Override
	public Integer geCountcar() {
		// TODO Auto-generated method stub
		return getReadSqlSession().selectOne("PssShopCarDao.getcountcar");
	}

	@Override
	public List<Map<String, String>> getPssShopCarListByOrderTime(
			PssShopCarQuery pssshopcarQuery) {
		return getReadSqlSession().selectList("PssShopCarDao.getcountcar");
	}

	@Override
	public List getordertime(PssShopCarQuery pssshopcarQuery) {
		return getReadSqlSession().selectList("PssShopCarDao.getallordertime",pssshopcarQuery);
	}



}
