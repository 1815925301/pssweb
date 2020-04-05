package com.sinosoft.business.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.City;
import com.sinosoft.business.po.extend.ExtendCity;
import com.sinosoft.business.po.query.CityQuery;
import com.sinosoft.common.web.SinosoftModelMap;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.business.service
 * @ClassName: CityService
 * @Description: 城市信息 服务层接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 下午03:43:21
 */
public interface CityService {
	
	/**
	 * 根据城市id获取城市信息
	 * @param id
	 * @return City
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午01:55:20
	 * @version V1.0
	 */
	public City getCityById(Long id);
	
	/**
	 * 检索符合条件的城市数量
	 * @param cityQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:27:36
	 * @version V1.0
	 */
	public Integer getCityCountByQuery(CityQuery cityQuery);
	
	/**
	 * 根据查询参数获取城市信息 用于城市管理页面
	 * @param model
	 * @param method
	 * @param request
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-20 下午09:55:39
	 * @version V1.0
	 */
	public void getCityInfoForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 获取全部的城市信息
	 * @return List<City>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午11:39:58
	 * @version V1.0
	 */
	public List<City> getAllCity();
	
	/**
	 * 根据城市id获取城市的扩展信息
	 * @param CityId
	 * @return ExtendCity
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午01:50:40
	 * @version V1.0
	 */
	public ExtendCity getExtendCityById(Long cityId);
	
	/**
	 * 保存新的城市信息
	 * @param City
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:18:07
	 * @version V1.0
	 */
	public boolean addNewCity(City city, ExtendUsers eUser, SinosoftModelMap modelMap);
	
	/**
	 * 更新城市信息
	 * @param City
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:42:57
	 * @version V1.0
	 */
	public boolean updateCity(City city, ExtendUsers eUser, SinosoftModelMap modelMap);
	
	/**
	 * 根据id删除城市信息
	 * @param CityService
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午10:57:56
	 * @version V1.0
	 */
	public boolean removeCity(Long cityId, ExtendUsers eUser, SinosoftModelMap modelMap);
	
}
