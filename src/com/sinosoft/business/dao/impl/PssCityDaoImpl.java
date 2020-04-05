
package com.sinosoft.business.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.PssCityDao;
import com.sinosoft.business.po.PssCity;
import com.sinosoft.business.po.query.PssCityQuery;

/**
 * @Package com.sinosoft.business.dao.impl
 * @ClassName: CityDaoImpl
 * @Description: 处理城市信息的DAO 实现类
 * @author hao
 * @date 2013-10-2 下午07:15:48
 */
@Repository(value="PssCityDao")
public class PssCityDaoImpl extends BaseDao implements PssCityDao{
	
	@Override
	public int insertPssCity(PssCity PSS_GEO_TOWN){
		return insert("PSS_GEO_TOWNDao.insertPssCity",PSS_GEO_TOWN);
	}
	
	@Override
	public int deletePssCity(Long id){
		return delete("PSS_GEO_TOWNDao.deletePssCity",id);
	}
	
	@Override
	public  int updatePssCity(PssCity PSS_GEO_TOWN){
		return update("PSS_GEO_TOWNDao.updatePssCity",PSS_GEO_TOWN);
	}
	
	/*@Override
	public PssCity selectPssCityById(Long id){
		return (PssCity)getReadSqlSession().selectOne("PssCityDao.getPssCityById",id);
	}
	
	@Override
	public Integer getCountByQuery(PssCityQuery pssCityQuery){
		return getReadSqlSession().selectOne("PssCityDao.getCountByQuery",pssCityQuery);
	}
	
	@Override
	public List<PssCity> getPssCityInfoByQuery(PssCityQuery pssCityQuery){
		return getReadSqlSession().selectList("PssCityDao.getPssCityInfoByQuery",pssCityQuery);
	}*/
	
	/*@Override
	public List<PssCity> getPssCityListByQuery(PssCityQuery pssCityQuery){
		return getReadSqlSession().selectList("PssCityDao.getPssCityByQuery",pssCityQuery);
	}*/
	
	/**
 	 * 查询方法,通过省份查询城市
 	 */
	@Override
    public List<PssCity> selectPssCityByProvince(Long provinceCode){
		return getReadSqlSession().selectList("PssCityDao.getPssCityByProvince",provinceCode);
		
	}
}
