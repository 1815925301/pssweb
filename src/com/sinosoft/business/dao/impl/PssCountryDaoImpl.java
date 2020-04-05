
package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssCountryDao;
import com.sinosoft.business.po.PssCountry;
import com.sinosoft.business.po.query.PssCountryQuery;
/**
 * @Package com.sinosoft.business.dao.impl
 * @ClassName: CountryDaoImpl
 * @Description: 处理国家信息的DAO 实现类
 * @author hao
 * @date 2013-10-2 下午07:15:48
 */

@Repository(value="PssCountryDao")
public class PssCountryDaoImpl extends BaseDao implements PssCountryDao{
	
	@Override
	public int insertPssCountry(PssCountry pssCountry){
		return insert("PssCountryDao.insertPssCountry",pssCountry);
	}
	
	@Override
	public int deletePssCountry(Long id){
		return delete("PssCountryDao.deletePssCountry",id);
	}
	
	@Override
	public  int updatePssCountry(PssCountry pssCountry){
		return update("PssCountryDao.updatePssCountry",pssCountry);
	}
	
	@Override
	public PssCountry selectPssCountryById(Long id){
		return (PssCountry)getReadSqlSession().selectOne("PssCountryDao.getPssCountryById",id);
	}
	
	@Override
	public Integer getCountByQuery(PssCountryQuery pssCountryQuery){
		return getReadSqlSession().selectOne("PssCountryDao.getCountByQuery",pssCountryQuery);
	}
	
	@Override
	public List<PssCountry> getPssCountryInfoByQuery(PssCountryQuery pssCountryQuery){
		return getReadSqlSession().selectList("PssCountryDao.getPssCountryInfoByQuery",pssCountryQuery);
	}
	
	@Override
	public List<PssCountry> getPssCountryListByQuery(PssCountryQuery pssCountryQuery){
		return getReadSqlSession().selectList("PssCountryDao.getPssCountryListByQuery",pssCountryQuery);
		
	}
	
}
