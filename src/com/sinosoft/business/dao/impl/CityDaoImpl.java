package com.sinosoft.business.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.CityDao;
import com.sinosoft.business.po.City;
import com.sinosoft.business.po.query.CityQuery;

/**
 * @Package com.sinosoft.business.dao.impl
 * @ClassName: CityDaoImpl
 * @Description: 城市信息的DAO 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-22 上午12:07:36
 */
@Repository(value="cityDao")
public class CityDaoImpl extends BaseDao implements CityDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityDaoImpl.class);

	@Override
	public City getCityById(Long id) {
		LOGGER.debug("Dao层：根据城市id获取城市信息");
		return getReadSqlSession().selectOne("cityDao.selectCityById", id);
	}

	@Override
	public City getCityByName(String CityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCityCountByQuery(CityQuery cityQuery) {
		LOGGER.debug("Dao层：根据检索条件获取城市数量信息");
		return getReadSqlSession().selectOne("cityDao.selectCityCountByQuery", cityQuery);
	}

	@Override
	public List<City> getCityInfoByQuery(CityQuery cityQuery) {
		LOGGER.debug("Dao层：根据检索条件获取城市信息");
		return getReadSqlSession().selectList("cityDao.selectCityInfoByQuery", cityQuery);
	}
	
	@Override
	public Integer getCityCountByQueryForCheck(CityQuery cityQuery) {
		LOGGER.debug("Dao层：精确查找城市名称、省份名称相同，但是城市id不同的城市信息");
		return getReadSqlSession().selectOne("cityDao.selectCityCountByName", cityQuery);
	}

	@Override
	public Integer addNewCity(City city) {
		LOGGER.debug("Dao层：新增城市信息");
		return (Integer)insert("cityDao.insertNewCity", city);
	}

	@Override
	public Integer getCityCountByName(CityQuery cityQuery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateCityById(City city) {
		LOGGER.debug("Dao层：修改城市信息");
		return (Integer)update("cityDao.updateCityById", city);
	}

	@Override
	public Integer deleteCityById(Long cityId) {
		LOGGER.debug("Dao层：删除城市信息");
		return (Integer)delete("cityDao.deleteCityById", cityId);
	}

}
