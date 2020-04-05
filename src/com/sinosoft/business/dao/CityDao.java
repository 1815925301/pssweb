package com.sinosoft.business.dao;

import java.util.List;

import com.sinosoft.business.po.City;
import com.sinosoft.business.po.query.CityQuery;

/**
 * @Package com.sinosoft.business.dao
 * @ClassName: CityDao
 * @Description: 城市信息的DAO 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 下午03:05:28
 */
public interface CityDao {
	
	/**
	 * 根据城市id获取城市信息
	 * @param id
	 * @return City
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午01:56:12
	 * @version V1.0
	 */
	public City getCityById(Long id);
	
	/**
	 * 根据城市名称获取城市信息
	 * @param CityName
	 * @return City
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:28:40
	 * @version V1.0
	 */
	public City getCityByName(String cityName);
	
	/**
	 * 根据检索条件获取城市数量信息
	 * @param CityQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-20 下午10:04:54
	 * @version V1.0
	 */
	public Integer getCityCountByQuery(CityQuery cityQuery);
	
	/**
	 * 精确查找城市名称、省份名称相同，但是城市id不同的城市数量
	 * @param CityQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午11:15:26
	 * @version V1.0
	 */
	public Integer getCityCountByQueryForCheck(CityQuery cityQuery);
	
	/**
	 * 根据检索条件获取城市的记录信息
	 * @param CityQuery
	 * @return List<City>
	 * @throws
	 * @author zzq
	 * @date 2013-10-20 下午10:04:57
	 * @version V1.0
	 */
	public List<City> getCityInfoByQuery(CityQuery cityQuery);
	
	/**
	 * 新增城市信息
	 * @param City
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:20:30
	 * @version V1.0
	 */
	public Integer addNewCity(City city);
	
	
	/**
	 * 根据城市id和城市名称精确查找符合条件的城市信息
	 * @param CityQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:50:23
	 * @version V1.0
	 */
	public Integer getCityCountByName(CityQuery cityQuery);
	
	/**
	 * 根据城市id更新城市信息
	 * @param City
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:54:59
	 * @version V1.0
	 */
	public Integer updateCityById(City city);
	
	/**
	 * 根据城市id删除城市信息
	 * @param CityId
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午11:00:07
	 * @version V1.0
	 */
	public Integer deleteCityById(Long cityId);

}
