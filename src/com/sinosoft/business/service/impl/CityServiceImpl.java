package com.sinosoft.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.CheckErrorDto;
import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.CityDao;
import com.sinosoft.business.po.City;
import com.sinosoft.business.po.Province;
import com.sinosoft.business.po.extend.ExtendCity;
import com.sinosoft.business.po.query.CityQuery;
import com.sinosoft.business.service.CityService;
import com.sinosoft.common.constant.MappingInputLengthConfig;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.web.SinosoftModelMap;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.business.service.impl
 * @ClassName: CityServiceImpl
 * @Description: 城市信息 服务层实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-22 上午12:08:27
 */
@Service("cityService")
public class CityServiceImpl implements CityService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Resource
	private CityDao cityDao;
	
	@Resource
	private SystemConstant systemConstant;
	
	@Resource
	private UsersService usersService;
	
	@Override
	public List<City> getAllCity() {
		LOGGER.debug("Service层：获取所有的城市信息");
		CityQuery cityQuery = new CityQuery();
		//以id升序排序
		cityQuery.setSortBy("province_id, id");
		cityQuery.setSortType("1");
		cityQuery.setPageSize(-1); //将分页设为-1 ，即意味不想要分页
		return cityDao.getCityInfoByQuery(cityQuery);
	}

	@Override
	public City getCityById(Long id) {
		LOGGER.debug("Service层：根据城市id获取城市信息");
		return cityDao.getCityById(id);
	}

	@Override
	public Integer getCityCountByQuery(CityQuery cityQuery) {
		LOGGER.debug("Service层：根据检索条件获取城市信息");
		return cityDao.getCityCountByQuery(cityQuery);
	}

	@Override
	public void getCityInfoForInitPage(ModelMap model, String method, HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取城市信息 用于城市管理页面");
		CityQuery cityQuery = new CityQuery();
		//以id升序排序
		cityQuery.setSortBy("id");
		cityQuery.setSortType("1");
		//从内存中获取城市信息
		List<Province> provinceList = systemConstant.getProvinceList();
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				cityQuery.setPage(Integer.parseInt(pageNum));
			}
			String city = request.getParameter("citySearch");
			if (! StringUtils.isBlank(city)) {
				cityQuery.setCity(city);
				model.addAttribute("city", city);
			}
			
			String provinceId = request.getParameter("provinceSearch");
			if (! StringUtils.isBlank(provinceId) && !provinceId.equals("-1")) {
				cityQuery.setProvinceId(new Long(provinceId));
				model.addAttribute("provinceId", provinceId);
			}
			
			//属于所选中的区域的城市ID
			List<Long> provinceIdList = new ArrayList<Long>();
			
			//看是否选择了区域，如果选择了区域，则首先查出区域下的城市信息，从内存中查取城市即可
			String areaId = request.getParameter("areaSearch");
			if (! StringUtils.isBlank(areaId) && !areaId.equals("-1")) {
				List<Province> provinceSubList = new ArrayList<Province>();
				if (provinceList != null && provinceList.size() > 0) {
					for (Province province : provinceList) {
						if (province.getAreaId().compareTo(new Long(areaId)) == 0) {
							provinceIdList.add(province.getId());
							provinceSubList.add(province);
						}
					}
					if (provinceIdList.size() > 0 && cityQuery.getProvinceId() == null)
						cityQuery.setProvinceIdList(provinceIdList);
				}
				model.addAttribute("areaId", areaId);
				model.addAttribute("provinceList", provinceSubList);
			}
		}
		//表数据
		Integer totalCount = cityDao.getCityCountByQuery(cityQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, cityQuery.getPageSize(), cityQuery.getPage(), cityQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<City> cityList = cityDao.getCityInfoByQuery(cityQuery);
		
		//获取所有区域的信息，用于城市管理页面中的区域检索
		List<Organization> areaList = systemConstant.getAreaList();
		model.addAttribute("areaList", areaList);
		
		//将城市信息返回到页面
		if (model.get("provinceList") == null) {
			model.addAttribute("provinceList", provinceList);
		}
		
		List<ExtendCity> eCityList = new ArrayList<ExtendCity>();
		//从区域信息中获取到城市所属区域
		if (areaList != null && areaList.size() > 0 && cityList != null && cityList.size() > 0) {
			for (City city : cityList) {
				ExtendCity eCity = new ExtendCity();
				try {
					BeanUtils.copyProperties(eCity, city);
					eCityList.add(eCity);
				} catch(Exception e){
					LOGGER.error("根据查询参数获取城市信息 用于城市管理页面 对象属性值拷贝过程中出现异常：{}", e);
				}
				//获取城市名称
				for (Province province : provinceList) {
					if (province.getId().compareTo(eCity.getProvinceId()) == 0) {
						eCity.setProvince(province.getProvince());
						//获取区域名称
						for (Organization org : areaList) {
							if (province.getAreaId().compareTo(org.getId()) == 0) {
								eCity.setAreaName(org.getOrgName());
								break;
							}
						}
						break;
					}
				}
			}
			
		}
		model.addAttribute("cityList", eCityList);
	}

	@Override
	public ExtendCity getExtendCityById(Long cityId) {
		LOGGER.debug("Service层：根据城市id获取城市的扩展信息");
		City city = this.getCityById(cityId);
		if (city == null || city.getId() == null) {
			LOGGER.debug("根据城市id获取城市的扩展信息，没有查取到城市信息：CityId {}", cityId);
			return null;
		}
		ExtendCity eCity = new ExtendCity();
		try {
			BeanUtils.copyProperties(eCity, city);
			if (eCity.getUpdateUserId() != null && eCity.getUpdateUserId().compareTo(new Long(0)) == 0) {
				eCity.setUpdateUserId(null);
			}
		} catch(Exception e){
			LOGGER.error("根据城市id获取城市的扩展信息 用于城市管理页面 对象属性值拷贝过程中出现异常：{}", e);
		}
		//从内存中去除省份信息、区域信息
		List<Province> provinceList = systemConstant.getProvinceList();
		
		if (provinceList != null && provinceList.size() > 0) {
			List<Organization> areaList = systemConstant.getAreaList();
			for (Province province : provinceList) {
				if (eCity.getProvinceId().compareTo(province.getId()) == 0) {
					eCity.setProvince(province.getProvince());
					if (areaList != null && areaList.size() > 0) {
						for (Organization org : areaList) {
							if (org.getId().compareTo(province.getAreaId()) == 0) {
								eCity.setAreaId(org.getId());
								eCity.setAreaName(org.getOrgName());
								break;
							}
						}
					}
					break;
				}
			}
		}
		
		//获取创建和修改城市信息的用户名称
		if (eCity.getCreateUserId() != null || eCity.getUpdateUserId() != null) {
			List<Long> userIdList = new ArrayList<Long>();
			if (eCity.getCreateUserId() != null) {
				userIdList.add(eCity.getCreateUserId());
			}
			if (eCity.getUpdateUserId() != null) {
				userIdList.add(eCity.getUpdateUserId());
			}
			List<Users> usersList = usersService.getUserInfoByUserIdList(userIdList);
			if (usersList != null && usersList.size() > 0) {
				for (Users user : usersList) {
					if (eCity.getCreateUserId() != null && user.getId().compareTo(eCity.getCreateUserId()) == 0) {
						eCity.setCreateUserName(user.getUserName());
					} 
					if (eCity.getUpdateUserId() != null && user.getId().compareTo(eCity.getUpdateUserId()) == 0) {
						eCity.setUpdateUserName(user.getUserName());
					}
				}
			}
		}
		return eCity;
	}

	@Override
	public boolean addNewCity(City city, ExtendUsers eUser, SinosoftModelMap modelMap) {
		LOGGER.debug("Service层：新增城市信息");
		boolean result = false;
		//过滤掉城市信息中各个属性值的前后空格
		city.trim();
		if (this.checkNewCity(city, modelMap)) {
			//通过校验，开始进行更新
			city.setCreateUserId(eUser.getId());
			Integer resultNum = cityDao.addNewCity(city);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				//将新的城市数据加载到内存中
				systemConstant.setCityList();
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}

	@Override
	public boolean updateCity(City city, ExtendUsers eUser, SinosoftModelMap modelMap) {
		LOGGER.debug("Service层：根据城市id更新城市信息");
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		city.trim();
		if (this.checkUpdateCity(city, modelMap)) {
			//通过校验，开始进行更新
			city.setUpdateUserId(eUser.getId());
			Integer resultNum = cityDao.updateCityById(city);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				//将新的城市数据加载到内存中
				systemConstant.setCityList();
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}

	@Override
	public boolean removeCity(Long cityId, ExtendUsers eUser, SinosoftModelMap modelMap) {
		LOGGER.debug("Service层：根据城市id删除城市信息 物理删除");
		boolean result = false;
		Integer resultNum = cityDao.deleteCityById(cityId);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			//将新的城市数据加载到内存中
			systemConstant.setCityList();
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}
	
	/**
	 * 校验更新后城市的信息
	 * @param city
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-23 上午12:55:05
	 * @version V1.0
	 */
	private boolean checkUpdateCity(City city, SinosoftModelMap modelMap) {
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		this.checkCityBaseInfo(city, errorInfoList);
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验更新后城市的名称是否已存在！");
		CityQuery cityQuery = new CityQuery();
		cityQuery.setId(city.getId());
		cityQuery.setProvinceId(city.getProvinceId());
		cityQuery.setCity(city.getCity());
		
		Integer resultNum = cityDao.getCityCountByQueryForCheck(cityQuery);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.info("城市名称已经存在");
			errorInfoList.add(new CheckErrorDto("change_arae", "城市名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	
	}
	
	/**
	 * 校验新增的城市信息
	 * @param city
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午10:55:57
	 * @version V1.0
	 */
	private boolean checkNewCity(City city, SinosoftModelMap modelMap) {
		LOGGER.debug("Service层：校验城市名称的长度，备注的长度，校验新城市的名称是否已存在！");
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		
		this.checkCityBaseInfo(city, errorInfoList);
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验新城市的名称是否已存在！");
		CityQuery cityQuery = new CityQuery();
		cityQuery.setCity(city.getCity());
		cityQuery.setProvinceId(city.getProvinceId());
		Integer count = cityDao.getCityCountByQueryForCheck(cityQuery);
		
		if (count != null && count.compareTo(new Integer(0)) > 0) {
			LOGGER.info("城市名称已经存在");
			errorInfoList.add(new CheckErrorDto("city", "城市名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验城市的基本信息
	 * @param city
	 * @param errorInfoList
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午10:57:44
	 * @version V1.0
	 */
	private void checkCityBaseInfo(City city, List<CheckErrorDto> errorInfoList) {
		LOGGER.debug("Service层：校验城市名称的长度，备注的长度！");
		Integer length = null;
		String prefix = "";
		if (city.getId() != null)
			prefix = "change_";
		if (StringUtils.isBlank(city.getCity())) {
			LOGGER.info("请输入城市名称");
			errorInfoList.add(new CheckErrorDto(prefix + "city", "请输入城市名称"));
		} else {
			length = new Integer(city.getCity().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("CITY_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "city", "城市名称长度不能大于" 
						+ MappingInputLengthConfig.getValue("CITY_LENGTH") + "位"));
			}
		}
		
		if (!StringUtils.isBlank(city.getNote())) {
			length = new Integer(city.getNote().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("NOTE_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "city_note", "备注长度不能大于" 
						+ MappingInputLengthConfig.getValue("NOTE_LENGTH") + "位"));
			}
		}
	}

}
