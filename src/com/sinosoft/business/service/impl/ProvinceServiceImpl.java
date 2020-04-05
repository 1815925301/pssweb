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
import com.sinosoft.business.dao.ProvinceDao;
import com.sinosoft.business.po.City;
import com.sinosoft.business.po.Province;
import com.sinosoft.business.po.extend.ExtendProvince;
import com.sinosoft.business.po.query.ProvinceQuery;
import com.sinosoft.business.service.ProvinceService;
import com.sinosoft.common.constant.MappingInputLengthConfig;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.web.SinosoftModelMap;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.business.service.impl
 * @ClassName: ProvinceServiceImpl
 * @Description: 省份信息 服务层实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 下午09:57:16
 */
@Service("provinceService")
public class ProvinceServiceImpl implements ProvinceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceServiceImpl.class);
	
	@Resource
	private ProvinceDao provinceDao;
	
	@Resource
	private UsersService usersService;
	
	@Resource
	private SystemConstant systemConstant;
	
	@Override
	public Province getProvinceById(Long id) {
		if (id == null) {
			LOGGER.error("Service层：根据省份id获取省份信息出现错误，参数省份id为null : {}", id);
			return null;
		}
		LOGGER.debug("Service层：根据省份id获取省份信息 : {}", id);
		return provinceDao.getProvinceById(id);
	}
	
	@Override
	public Province getProvinceByName(String provinceName) {
		if (StringUtils.isBlank(provinceName)) {
			LOGGER.error("Service层：根据省份名称获取省份信息出现错误，参数省份id为null : {}", provinceName);
			return null;
		}
		LOGGER.debug("Service层：根据省份名称获取省份信息 : {}", provinceName);
		return provinceDao.getProvinceByName(provinceName);
	}

	@Override
	public void getProvinceInfoForInitPage(ModelMap model, String method, HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取省份信息 用于省份管理页面");
		ProvinceQuery provinceQuery = new ProvinceQuery();
		//以id升序排序
		provinceQuery.setSortBy("id");
		provinceQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				provinceQuery.setPage(Integer.parseInt(pageNum));
			}
			String province = request.getParameter("provinceSearch");
			if (! StringUtils.isBlank(province)) {
				provinceQuery.setProvince(province);
				model.addAttribute("province", province);
			}
			String areaId = request.getParameter("areaSearch");
			if (! StringUtils.isBlank(areaId) && !areaId.equals("-1")) {
				provinceQuery.setAreaId(new Long(areaId));
				model.addAttribute("areaId", areaId);
			}
		}
		Integer totalCount = provinceDao.getProvinceCountByQuery(provinceQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, provinceQuery.getPageSize(), 
				provinceQuery.getPage(), provinceQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<Province> provinceList = provinceDao.getProvinceInfoByQuery(provinceQuery);
		
		//获取所有区域的信息，用于省份管理页面中的区域检索
		List<Organization> areaList = systemConstant.getAreaList();
		model.addAttribute("areaList", areaList);
		
		List<ExtendProvince> eProvinceList = new ArrayList<ExtendProvince>();
		//从区域信息中获取到省份所属区域
		if (areaList != null && areaList.size() > 0 && provinceList != null && provinceList.size() > 0) {
			for (Province province : provinceList) {
				ExtendProvince eProvince = new ExtendProvince();
				try {
					BeanUtils.copyProperties(eProvince, province);
					eProvinceList.add(eProvince);
				} catch(Exception e){
					LOGGER.error("根据查询参数获取省份信息 用于省份管理页面 对象属性值拷贝过程中出现异常：{}", e);
				}
				for (Organization org : areaList) {
					if (org.getId().compareTo(eProvince.getAreaId()) == 0) {
						eProvince.setAreaName(org.getOrgName());
						break;
					}
				}
			}
			
		}
		model.addAttribute("provinceList", eProvinceList);
	}
	
	@Override
	public ExtendProvince getExtendProvinceById(Long provinceId){
		LOGGER.debug("Service层：根据省份id获取省份的扩展信息");
		Province province = this.getProvinceById(provinceId);
		if (province == null || province.getId() == null) {
			LOGGER.debug("根据省份id获取省份的扩展信息，没有查取到省份信息：provinceId {}", provinceId);
			return null;
		}
		ExtendProvince eProvince = new ExtendProvince();
		try {
			BeanUtils.copyProperties(eProvince, province);
			if (eProvince.getUpdateUserId() != null && eProvince.getUpdateUserId().compareTo(new Long(0)) == 0) {
				eProvince.setUpdateUserId(null);
			}
		} catch(Exception e){
			LOGGER.error("根据省份id获取省份的扩展信息 用于省份管理页面 对象属性值拷贝过程中出现异常：{}", e);
		}
		
		List<Organization> areaList = systemConstant.getAreaList();
		if (areaList != null && areaList.size() > 0) {
			for (Organization org : areaList) {
				if (org.getId().compareTo(eProvince.getAreaId()) == 0) {
					eProvince.setAreaName(org.getOrgName());
					break;
				}
			}
		}
		//获取创建和修改省份信息的用户名称
		if (eProvince.getCreateUserId() != null || eProvince.getUpdateUserId() != null) {
			List<Long> userIdList = new ArrayList<Long>();
			if (eProvince.getCreateUserId() != null) {
				userIdList.add(eProvince.getCreateUserId());
			}
			if (eProvince.getUpdateUserId() != null) {
				userIdList.add(eProvince.getUpdateUserId());
			}
			List<Users> usersList = usersService.getUserInfoByUserIdList(userIdList);
			if (usersList != null && usersList.size() > 0) {
				for (Users user : usersList) {
					if (eProvince.getCreateUserId() != null && user.getId().compareTo(eProvince.getCreateUserId()) == 0) {
						eProvince.setCreateUserName(user.getUserName());
					} 
					if (eProvince.getUpdateUserId() != null && user.getId().compareTo(eProvince.getUpdateUserId()) == 0) {
						eProvince.setUpdateUserName(user.getUserName());
					}
				}
			}
		}
		return eProvince;
	}
	
	@Override
	public boolean addNewProvince(Province province, ExtendUsers eUser, SinosoftModelMap modelMap) {
		LOGGER.debug("Service层：新增省份信息");
		boolean result = false;
		//过滤掉省份信息中各个属性值的前后空格
		province.trim();
		if (this.checkNewProvince(province, modelMap)) {
			//通过校验，开始进行更新
			province.setCreateUserId(eUser.getId());
			Integer resultNum = provinceDao.addNewProvince(province);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				//将新的省份数据加载到内存中
				systemConstant.setProvinceList();
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public boolean updateProvince(Province province, ExtendUsers eUser, SinosoftModelMap modelMap) {
		LOGGER.debug("Service层：根据省份id更新省份信息");
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		province.trim();
		if (this.checkUpdateProvince(province, modelMap)) {
			//通过校验，开始进行更新
			province.setUpdateUserId(eUser.getId());
			Integer resultNum = provinceDao.updateProvinceById(province);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				//将新的省份数据加载到内存中
				systemConstant.setProvinceList();
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public boolean removeProvince(Long provinceId, ExtendUsers eUser, SinosoftModelMap modelMap) {
		LOGGER.debug("Service层：根据省份id删除省份信息 物理删除");
		boolean result = false;
		boolean isHaveCity = false;
		List<City> cityList = systemConstant.getCityList();
		if (cityList != null && cityList.size() > 0) {
			for (City city : cityList) {
				if (city.getProvinceId().compareTo(provinceId) == 0) {
					isHaveCity = true;
					break;
				}
			}
		}
		if (isHaveCity){
			modelMap.put("status", "error");
			modelMap.put("data", "存在属于本省份的城市，删除失败！");
		} else {
			Integer resultNum = provinceDao.deleteProvinceById(provinceId);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				//将新的省份数据加载到内存中
				systemConstant.setProvinceList();
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public List<Province> getAllProvince() {
		LOGGER.debug("Service层：获取全部的省份信息");
		return provinceDao.getAllProvince();
	}
	
	/**
	 * 校验更新后省份的信息
	 * @param carType
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-19 下午11:10:39
	 * @version V1.0
	 */
	private boolean checkUpdateProvince(Province province, SinosoftModelMap modelMap) {
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		this.checkProvinceBaseInfo(province, errorInfoList);
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验更新后省份的名称是否已存在！");
		ProvinceQuery provinceQuery = new ProvinceQuery();
		provinceQuery.setId(province.getId());
		provinceQuery.setProvince(province.getProvince());
		
		Integer resultNum = provinceDao.getProvinceCountByName(provinceQuery);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.info("省份名称已经存在");
			errorInfoList.add(new CheckErrorDto("change_province", "省份名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	
	/**
	 * 校验新的省份信息
	 * @param province
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:23:33
	 * @version V1.0
	 */
	private boolean checkNewProvince(Province province, SinosoftModelMap modelMap) {
		LOGGER.debug("Service层：校验省份名称的长度，备注的长度，校验新省份的名称是否已存在！");
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		
		this.checkProvinceBaseInfo(province, errorInfoList);
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验新省份的名称是否已存在！");
		Province existProvince = this.getProvinceByName(province.getProvince());
		
		if (existProvince != null && existProvince.getProvince().equals(province.getProvince())) {
			LOGGER.info("省份名称已经存在");
			errorInfoList.add(new CheckErrorDto("province", "省份名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验省份的基本信息
	 * @param province
	 * @param errorInfoList
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午02:30:08
	 * @version V1.0
	 */
	private void checkProvinceBaseInfo(Province province, List<CheckErrorDto> errorInfoList) {
		LOGGER.debug("Service层：校验省份名称的长度，备注的长度！");
		Integer length = null;
		String prefix = "";
		if (province.getId() != null)
			prefix = "change_";
		if (StringUtils.isBlank(province.getProvince())) {
			LOGGER.info("请输入省份名称");
			errorInfoList.add(new CheckErrorDto(prefix + "province", "请输入省份名称"));
		} else {
			length = new Integer(province.getProvince().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("PROVINCE_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "province", "省份名称长度不能大于" 
						+ MappingInputLengthConfig.getValue("PROVINCE_LENGTH") + "位"));
			}
		}
		
		if (!StringUtils.isBlank(province.getNote())) {
			length = new Integer(province.getNote().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("NOTE_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "province_note", "备注长度不能大于" 
						+ MappingInputLengthConfig.getValue("NOTE_LENGTH") + "位"));
			}
		}
	}

}
