package com.sinosoft.business.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.ProvinceDao;
import com.sinosoft.business.po.Province;
import com.sinosoft.business.po.query.ProvinceQuery;

/**
 * @Package com.sinosoft.business.dao.impl
 * @ClassName: ProvinceDaoImpl
 * @Description: 省份信息的DAO 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 下午10:13:24
 */
@Repository(value="provinceDao")
public class ProvinceDaoImpl extends BaseDao implements ProvinceDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceDaoImpl.class);
	
	@Override
	public Province getProvinceById(Long id) {
		LOGGER.debug("Dao层：根据省份id获取省份信息 : {}", id);
		return getReadSqlSession().selectOne("provinceDao.selectProvinceById", id);
	}

	@Override
	public Province getProvinceByName(String provinceName) {
		LOGGER.debug("Dao层：根据省份id获取省份信息 : {}", provinceName);
		return getReadSqlSession().selectOne("provinceDao.selectProvinceByName", provinceName);
	}
	
	@Override
	public Integer getProvinceCountByQuery(ProvinceQuery provinceQuery) {
		LOGGER.debug("Dao层：根据检索条件获取省份的数量");
		return (Integer)getReadSqlSession().selectOne("provinceDao.selectProvinceCountByQuery", provinceQuery);
	}

	@Override
	public List<Province> getProvinceInfoByQuery(ProvinceQuery provinceQuery) {
		LOGGER.debug("Dao层：根据检索条件获取省份的记录信息");
		return getReadSqlSession().selectList("provinceDao.selectProvinceInfoByQuery", provinceQuery);
	}
	
	@Override
	public Integer addNewProvince(Province province) {
		LOGGER.debug("Dao层：新增省份信息");
		return (Integer)insert("provinceDao.insertNewProvince", province);
	}
	
	@Override
	public Integer getProvinceCountByName(ProvinceQuery provinceQuery) {
		LOGGER.debug("Dao层：根据省份id和省份名称精确查找符合条件的省份信息");
		return getReadSqlSession().selectOne("provinceDao.selectProvinceCountByName", provinceQuery);
	}
	
	@Override
	public Integer updateProvinceById(Province province) {
		LOGGER.debug("Dao层：根据省份id更新省份信息");
		return getReadSqlSession().update("provinceDao.updateProvinceById", province);
	}
	
	@Override
	public Integer deleteProvinceById(Long provinceId) {
		LOGGER.debug("Dao层：根据省份id删除省份信息");
		return getReadSqlSession().delete("provinceDao.deleteProvinceById", provinceId);
	}
	
	@Override
	public List<Province> getAllProvince() {
		LOGGER.debug("Dao层：获取全部的省份信息");
		return getReadSqlSession().selectList("provinceDao.selectAllProvince");
	}

}
