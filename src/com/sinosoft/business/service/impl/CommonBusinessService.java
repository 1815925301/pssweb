package com.sinosoft.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sinosoft.business.po.City;
import com.sinosoft.business.po.Province;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.web.SinosoftModelMap;

/**
 * @Package com.sinosoft.business.service.impl
 * @ClassName: CommonBusinessService
 * @Description: 公用的服务类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-22 下午04:46:08
 */
@Service("commBusinessService")
public class CommonBusinessService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonBusinessService.class);
	
	@Resource
	private SystemConstant systemConstant;
	
	/**
	 * 根据区域id获取区域下的省份信息
	 * @param areaId
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午05:09:56
	 * @version V1.0
	 */
	public boolean getProvinceInfoByAreaId(Long areaId, SinosoftModelMap modelMap){
		LOGGER.debug("根据区域id获取区域下的省份信息：areaId={}", areaId);
		boolean result = false;
		//获取所有的省份信息
		List<Province> provinceList = systemConstant.getProvinceList();
		if (provinceList != null && provinceList.size() > 0) {
			if (areaId.compareTo(new Long(-1)) == 0) {
				//此时返回所有的省份信息
				modelMap.put("data", provinceList);
			} else {
				List<Province> provinceSubList = new ArrayList<Province>();
				for (Province province : provinceList) {
					if (province.getAreaId().compareTo(areaId) == 0) {
						provinceSubList.add(province);
					}
				}
				modelMap.put("data", provinceSubList);
			}
			result = true;
		} else {
			modelMap.put("status", "failure");
			modelMap.put("data", "数据异常：没有获取到省份信息！");
		}
		return result;
	}
	
	/**
	 * 根据省份id获取省份所属的区域信息
	 * @param provinceId
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午05:09:56
	 * @version V1.0
	 */
	public boolean getAreaInfoByProvinceId(Long provinceId, SinosoftModelMap modelMap){
		LOGGER.debug("根据省份id获取省份所属的区域信息：provinceId={}", provinceId);
		boolean result = false;
		//获取所有的省份信息
		List<Province> provinceList = systemConstant.getProvinceList();
		Long areaId = null;
		if (provinceList != null && provinceList.size() > 0) {
			for (Province province : provinceList) {
				if (province.getId().compareTo(provinceId) == 0) {
					areaId = province.getAreaId();
					break;
				}
			}
			if (areaId != null) {
				modelMap.put("data", areaId);
				result = true;
			}
		} 
		if (areaId == null) {
			modelMap.put("status", "failure");
			modelMap.put("data", "数据异常：没有获取到区域信息！");
		}
		return result;
	}
	
	/**
	 * 根据省份id获取省份下的城市信息
	 * @param provinceId
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-24 上午03:19:21
	 * @version V1.0
	 */
	public boolean getCityInfoByProvinceId(Long provinceId, SinosoftModelMap modelMap){
		LOGGER.debug("根据省份id获取省份下的城市信息：provinceId={}", provinceId);
		boolean result = false;
		//获取所有的省份信息
		List<City> cityList = systemConstant.getCityList();
		List<City> citySubList = new ArrayList<City>();
		if (cityList != null && cityList.size() > 0) {
			for (City city : cityList) {
				if (city.getProvinceId().compareTo(provinceId) == 0) {
					citySubList.add(city);
				}
			}
			if (citySubList != null && citySubList.size() > 0) {
				modelMap.put("data", citySubList);
				result = true;
			}
		} 
		if (citySubList == null || citySubList.size() == 0) {
			modelMap.put("status", "failure");
			modelMap.put("data", "数据异常：没有获取到城市信息！");
		}
		return result;
	}
	
	/**
	 * 获取给定省份id的城市信息与经销商信息 省份城市经销商联动功能调用
	 * @param CommonBusinessService
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-11-3 上午10:12:02
	 * @version V1.0
	 */
	public boolean getCityAndDistribInfoByProvinceId(Long provinceId, SinosoftModelMap modelMap){
		LOGGER.debug("获取给定省份id的城市信息与经销商信息：provinceId={}", provinceId);
		boolean result = false;
		//获取所有的省份信息
		List<City> cityList = systemConstant.getCityList();
		List<City> citySubList = new ArrayList<City>();
		if (cityList != null && cityList.size() > 0) {
			for (City city : cityList) {
				if (city.getProvinceId().compareTo(provinceId) == 0) {
					citySubList.add(city);
				}
			}
			if (citySubList != null && citySubList.size() > 0) {
				modelMap.put("data", citySubList);
				result = true;
			}
		} 
		if (citySubList == null || citySubList.size() == 0) {
			modelMap.put("status", "failure");
			modelMap.put("data", "数据异常：没有获取到城市信息！");
		}
		
		return result;
	}
	
	/**
	 * 获取给定区域id的所属省份信息 获取给定省份id的所属城市信息 实现区域、省份、城市三级联动
	 * @param areaId
	 * @param provinceId
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-24 上午05:44:59
	 * @version V1.0
	 */
	public boolean getProvinceInfoByAreaIdAndCityInfoByProvinceId(Long areaId, Long provinceId, SinosoftModelMap modelMap) {
		LOGGER.debug("根据省份id获取省份下的城市信息：provinceId={}", provinceId);
		boolean result = true;
		
		//获取所有的省份信息
		List<Province> provinceList = systemConstant.getProvinceList();
		if (provinceList != null && provinceList.size() > 0) {
			if (areaId.compareTo(new Long(-1)) == 0) {
				//此时返回所有的省份信息
				modelMap.put("data", provinceList);
			} else {
				List<Province> provinceSubList = new ArrayList<Province>();
				for (Province province : provinceList) {
					if (province.getAreaId().compareTo(areaId) == 0) {
						provinceSubList.add(province);
					}
				}
				modelMap.put("province", provinceSubList);
			}
		} else {
			modelMap.put("status", "failure");
			modelMap.put("data", "数据异常：没有获取到省份信息！");
			return false;
		}
		
		//获取所有的省份信息
		List<City> cityList = systemConstant.getCityList();
		List<City> citySubList = new ArrayList<City>();
		if (cityList != null && cityList.size() > 0) {
			for (City city : cityList) {
				if (city.getProvinceId().compareTo(provinceId) == 0) {
					citySubList.add(city);
				}
			}
			if (citySubList != null && citySubList.size() > 0) {
				modelMap.put("city", citySubList);
			}
		} 
		if (citySubList == null || citySubList.size() == 0) {
			modelMap.put("status", "failure");
			modelMap.put("data", "数据异常：没有获取到城市信息！");
			return false;
		}
		
		return result;
	}
	
}
