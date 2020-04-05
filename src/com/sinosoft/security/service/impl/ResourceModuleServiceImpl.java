package com.sinosoft.security.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.sinosoft.common.constant.MappingInputLengthConfig;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.po.RowSet;
import com.sinosoft.security.dao.ResourceModuleDao;
import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendResourceModule;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ResourceModuleQuery;
import com.sinosoft.security.service.ResourceModuleService;
import com.sinosoft.security.service.ResourcesService;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.security.service.impl
 * @ClassName: ResourceModuleServiceImpl
 * @Description: 系统资源模块信息 服务层实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-14 上午10:04:29
 */
@Service("resourceModuleService")
public class ResourceModuleServiceImpl implements ResourceModuleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceModuleServiceImpl.class);
	
	@Resource
	private ResourceModuleDao resourceModuleDao;
	
	@Resource
	private ResourcesService resourcesService;
	
	@Resource
	private UsersService usersService;

	@Override
	public List<ResourceModule> getResourceModuleInfoByIdList(List<Long> idList) {
		LOGGER.debug("Service层：根据模块id集合获取资源模块信息");
		return resourceModuleDao.getResourceModuleInfoByIdList(idList);
	}
	
	@Override
	public List<ResourceModule> getResourceModuleInfoWithResources(){
		List<Resources> resourcesList = resourcesService.getAllMenuResources();
		if (resourcesList == null || resourcesList.size() == 0){
			LOGGER.error("获取菜单资源信息出现错误！");
			return null;
		}
		
		Set<Long> moudleIdSet = new HashSet<Long>();
		for (Resources resources : resourcesList) {
			moudleIdSet.add(resources.getModuleId());
		}
		if (moudleIdSet == null || moudleIdSet.size() == 0) {
			LOGGER.error("获取菜单资源信息出现错误！");
			return null;
		} 
		//获取到资源模块的集合
		List<ResourceModule> moduleList = this.getResourceModuleInfoByIdList(new ArrayList<Long>(moudleIdSet));
		if (moduleList == null || moduleList.size() == 0) {
			LOGGER.error("获取菜单资源信息出现错误！");
			return null;
		} 
		
		for (ResourceModule module : moduleList) {
			for (Resources resources : resourcesList) {
				if (module.getId().compareTo(resources.getModuleId()) == 0)
					module.addResource(resources);
			}
		}
		return moduleList;
	}

	@Override
	public List<ResourceModule> getResourceModuleInfo() {
		LOGGER.debug("Service层：获取功能树");
		return resourceModuleDao.getResourceModuleInfo();
	}
	
	@Override
	public ResourceModule getModuleInfoByModuleId(Long moduleId) {
		if (moduleId == null) {
			LOGGER.error("参数错误：机构id为空！");
			return null;
		}
		LOGGER.debug("Service层：根据机构id获取机构信息");
		return resourceModuleDao.getModuleInfoByModuleId(moduleId);
	}
	
	@Override
	public ResourceModule getModuleInfoByModuleName(String moduleName) {
		if (moduleName == null) {
			LOGGER.error("参数错误：机构名称为空！");
			return null;
		}
		LOGGER.debug("Service层：根据机构名称取机构信息");
		return resourceModuleDao.getModuleInfoByModuleName(moduleName);
	}
	
	@Override
	public List<ResourceModule> getModuleInfoTree() {
		LOGGER.debug("Service层：获取机构树");
		return resourceModuleDao.getModuleInfoTree();
	}

	
	@Override
	public String getModuleNameByModuleId(Long moduleId) {
		LOGGER.debug("Service层：根据机构id获取机构名称");
		if (moduleId == null) {
			LOGGER.error("参数错误：机构id为空！");
			return "";
		}
		String moduleName = "";
//		List<ResourceModule> moduleList = systemConstant.getResourceModuleList();
//		if (moduleList != null && moduleList.size() > 0) {
//			for (ResourceModule module : moduleList) {
//				if (moduleId.compareTo(module.getId()) == 0) {
//					moduleName = module.getModuleName();
//					break;
//				}
//			}
//		}
		return moduleName;
	}
	
	@Override
	public Integer getModuleCountByQuery(ResourceModuleQuery moduleQuery) {
		LOGGER.debug("Service层：根据检索条件获取机构的数量");
		if (moduleQuery == null) {
			LOGGER.error("参数错误：查询参数对象为空！");
			return null;
		}
		return resourceModuleDao.getModuleCountByQuery(moduleQuery);
	}
	
	@Override
	public List<ResourceModule> getModuleInfoByQuery(ResourceModuleQuery moduleQuery) {
		LOGGER.debug("Service层：根据检索条件获取机构的记录信息");
		if (moduleQuery == null) {
			LOGGER.error("参数错误：查询参数对象为空！");
			return null;
		}
		return resourceModuleDao.getModuleInfoByQuery(moduleQuery);
	}
	
	@Override
	public void getModuleInfoForInitPage(ModelMap model, String method, HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取机构信息 用于机构管理页面");
		ResourceModuleQuery moduleQuery = new ResourceModuleQuery();
		//以id升序排序
		moduleQuery.setSortBy("id");
		moduleQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				moduleQuery.setPage(Integer.parseInt(pageNum));
			}
			String moduleName = request.getParameter("moduleName");
			if (! StringUtils.isBlank(moduleName)) {
				moduleQuery.setModuleName(moduleName);
				model.addAttribute("moduleName", moduleName);
			}
			String moduleType = request.getParameter("moduleType");
			if (! StringUtils.isBlank(moduleType) && ! moduleType.equals("-1")) {
				//moduleQuery.setType(Integer.parseInt(moduleType));
				model.addAttribute("moduleType", moduleType);
			}
		}
		Integer totalCount = resourceModuleDao.getModuleCountByQuery(moduleQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, moduleQuery.getPageSize(), moduleQuery.getPage(), moduleQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<ResourceModule> moduleInfoList = resourceModuleDao.getModuleInfoByQuery(moduleQuery);
		List<ExtendResourceModule> eModuleInfoList = new ArrayList<ExtendResourceModule>();
		if (moduleInfoList != null && moduleInfoList.size() > 0) {
			try {
				for (ResourceModule module : moduleInfoList) {
					ExtendResourceModule eModule = new ExtendResourceModule();
					BeanUtils.copyProperties(eModule, module);
					//获取机构类型常量
//					if(module.getType().compareTo(EnumResourceModuleType.ORGANIZATION_IS_HEAD_QUARTERS.getStatus()) == 0)
//						eModule.setModuleType(MappingConstantConfig.getValue("ORGANIZATION_IS_HEAD_QUARTERS"));
//					else if (module.getType().compareTo(EnumResourceModuleType.ORGANIZATION_IS_AREA.getStatus()) == 0)
//						eModule.setModuleType(MappingConstantConfig.getValue("ORGANIZATION_IS_AREA"));
//					else if (module.getType().compareTo(EnumResourceModuleType.ORGANIZATION_IS_THIRD_PARTY.getStatus()) == 0)
//						eModule.setModuleType(MappingConstantConfig.getValue("ORGANIZATION_IS_THIRD_PARTY"));
//					else if (module.getType().compareTo(EnumResourceModuleType.ORGANIZATION_IS_OTHER.getStatus()) == 0)
//						eModule.setModuleType(MappingConstantConfig.getValue("ORGANIZATION_IS_OTHER"));
					eModuleInfoList.add(eModule);
				}
			} catch(Exception e) {
				LOGGER.error("将机构信息进行对象拷贝 对象属性值拷贝过程中出现异常：{}", e);
			}
		}
		model.addAttribute("moduleInfoList", eModuleInfoList);
	}
	
	@Override
	public boolean addNewResourceModule(ResourceModule resourceModule, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		//resourceModule.trim();
		if (this.checkNewModuleInfo(resourceModule, modelMap)) {
			//通过校验，开始进行更新
			resourceModule.setCreateUserId(eUser.getId());
			Integer resultNum = resourceModuleDao.addNewResourceModule(resourceModule);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public ExtendResourceModule getExtendModuleInfoByModuleId(Long moduleId) {
		LOGGER.debug("Service层：根据机构id获取机构扩展信息");
		ResourceModule module = this.getModuleInfoByModuleId(moduleId);
		if (module == null || module.getId() == null) {
			LOGGER.debug("根据机构id获取机构扩展信息，没有查取到机构信息：moduleId {}", moduleId);
			return null;
		}
		ExtendResourceModule eModule = new ExtendResourceModule();
		try {
			BeanUtils.copyProperties(eModule, module);
			if (eModule.getUpdateUserId() != null && eModule.getUpdateUserId().compareTo(new Long(0)) == 0) {
				eModule.setUpdateUserId(null);
			}
		} catch(Exception e){
			LOGGER.error("根据机构id获取机构扩展信息 对象属性值拷贝过程中出现异常：{}", e);
		}
		//获取创建和修改机构信息的用户名称
		if (eModule.getCreateUserId() != null || eModule.getUpdateUserId() != null) {
			List<Long> userIdList = new ArrayList<Long>();
			if (eModule.getCreateUserId() != null) {
				userIdList.add(eModule.getCreateUserId());
			}
			if (eModule.getUpdateUserId() != null) {
				userIdList.add(eModule.getUpdateUserId());
			}
			List<Users> usersList = usersService.getUserInfoByUserIdList(userIdList);
			if (usersList != null && usersList.size() > 0) {
				for (Users user : usersList) {
					if (eModule.getCreateUserId() != null && user.getId().compareTo(eModule.getCreateUserId()) == 0) {
						eModule.setCreateUserName(user.getUserName());
					} 
					if (eModule.getUpdateUserId() != null && user.getId().compareTo(eModule.getUpdateUserId()) == 0) {
						eModule.setUpdateUserName(user.getUserName());
					}
				}
			}
		}
		return eModule;
	}
	
	
	@Override
	public boolean updateResourceModule(ResourceModule resourceModule, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		//resourceModule.trim();
		if (this.checkUpdateModuleInfo(resourceModule, modelMap)) {
			//通过校验，开始进行更新
			resourceModule.setUpdateUserId(eUser.getId());
			Integer resultNum = resourceModuleDao.updateModuleInfoByModuleId(resourceModule);
			if (resultNum.compareTo(new Integer(1)) == 0) {
//				if (EnumResourceModuleType.ORGANIZATION_IS_AREA.getStatus().compareTo(resourceModule.getType()) == 0) {
//					//重新加载放在内存中的区域信息
//					systemConstant.setResourceModuleList();
//					//systemConstant.setAreaList();
//				}
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public boolean removeResourceModule(Long moduleId, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
//		Integer userNum = usersService.getUsersCountByModuleId(moduleId);
//		if (userNum != null && userNum.compareTo(new Integer(0)) == 0) {
//			List<Province> provinceList = systemConstant.getProvinceList();
//			boolean isHaveProvince = false;
//			if (provinceList != null && provinceList.size() > 0) {
//				for (Province province : provinceList) {
//					if (province.getAreaId().compareTo(moduleId) == 0) {
//						isHaveProvince = true;
//						break;
//					}
//				}
//			}
//			if (isHaveProvince) {
//				modelMap.put("status", "error");
//				modelMap.put("data", "存在属于本机构的省份，删除失败！");
//			} else {
				//通过校验，开始进行删除
				Integer resultNum = resourceModuleDao.deleteModuleInfoByModuleId(moduleId);
				if (resultNum.compareTo(new Integer(1)) == 0) {
					//重新加载放在内存中的区域信息
					//systemConstant.setResourceModuleList();
					//systemConstant.setAreaList();
					result = true;
				} else {
					modelMap.put("status", "failure");
				}
//			}
//		} else {
//			modelMap.put("status", "error");
//			modelMap.put("data", "存在属于本机构的用户，删除失败！");
//		} 
		return result;
	}
	
	/**
	 * 校验新机构的信息
	 * @param resourceModule
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 上午01:36:51
	 * @version V1.0
	 */
	private boolean checkNewModuleInfo(ResourceModule resourceModule, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：校验机构名称的长度，机构类型值是否在枚举常量限制中，校验新机构的名称是否已存在！");
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		
		this.checkModuleBaseInfo(resourceModule, errorInfoList);
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验新机构的名称是否已存在！");
		ResourceModule module = this.getModuleInfoByModuleName(resourceModule.getModuleName());
		
		if (module != null && module.getModuleName().equals(resourceModule.getModuleName())) {
			LOGGER.info("机构名称已经存在");
			errorInfoList.add(new CheckErrorDto("module_name", "机构名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验更新后机构的信息
	 * @param resourceModule
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午10:39:19
	 * @version V1.0
	 */
	private boolean checkUpdateModuleInfo(ResourceModule resourceModule, ActivityModelMap modelMap) {
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		this.checkModuleBaseInfo(resourceModule, errorInfoList);
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验更新后机构的名称是否已存在！");
		ResourceModuleQuery moduleQuery = new ResourceModuleQuery();
		moduleQuery.setModuleName(resourceModule.getModuleName());
		moduleQuery.setId(resourceModule.getId());
		
		Integer resultNum = resourceModuleDao.getModuleCountByModuleName(moduleQuery);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.info("机构名称已经存在");
			errorInfoList.add(new CheckErrorDto("change_module_name", "机构名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验机构名称的长度，机构类型值是否在枚举常量限制中
	 * @param resourceModule
	 * @param errorInfoList
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午10:35:52
	 * @version V1.0
	 */
	private void checkModuleBaseInfo(ResourceModule resourceModule, List<CheckErrorDto> errorInfoList) {
		LOGGER.debug("Service层：校验机构名称的长度，机构类型值是否在枚举常量限制中！");
		Integer length = null;
		String prefix = "";
		if (resourceModule.getId() != null)
			prefix = "change_";
		if (StringUtils.isBlank(resourceModule.getModuleName())) {
			LOGGER.info("请输入机构名称");
			errorInfoList.add(new CheckErrorDto(prefix + "module_name", "请输入机构名称"));
		} else {
			length = new Integer(resourceModule.getModuleName().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("ORG_NAME_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "module_name", "机构名称长度不能大于" 
						+ MappingInputLengthConfig.getValue("ORG_NAME_LENGTH") + "位"));
			}
		}
		
//		if (resourceModule.getType() == null) {
//			LOGGER.info("请选择机构类型");
//			errorInfoList.add(new CheckErrorDto(prefix + "module_type", "机构类型选择有误"));
//		} else {
//			if (! (EnumResourceModuleType.ORGANIZATION_IS_AREA.getStatus().compareTo(resourceModule.getType()) == 0
//					|| EnumResourceModuleType.ORGANIZATION_IS_THIRD_PARTY.getStatus().compareTo(resourceModule.getType()) == 0
//					|| EnumResourceModuleType.ORGANIZATION_IS_OTHER.getStatus().compareTo(resourceModule.getType()) == 0)) {
//				LOGGER.info("请选择机构类型");
//				errorInfoList.add(new CheckErrorDto(prefix + "module_type", "机构类型选择有误"));
//			}
//		}
		
//		if (!StringUtils.isBlank(resourceModule.getNote())) {
//			length = new Integer(resourceModule.getNote().length());
//			if (length.compareTo(MappingInputLengthConfig.getValue("NOTE_LENGTH")) > 0) {
//				errorInfoList.add(new CheckErrorDto(prefix + "module_note", "备注长度不能大于" 
//						+ MappingInputLengthConfig.getValue("NOTE_LENGTH") + "位"));
//			}
//		}
	}
	
	@Override
	public RowSet getModuleList(ResourceModuleQuery moduleQuery, ActivityModelMap modelMap, String pid) {
		LOGGER.debug("Service层：根据查询参数获取机构信息，用于机构管理首页列表");
		//以id升序排序
		moduleQuery.setSortBy("sort_num");
		moduleQuery.setSortType("1");
		moduleQuery.setpId(Long.valueOf(pid)); 
		Integer totalCount = resourceModuleDao.getModuleCountByQuery(moduleQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, moduleQuery.getPageSize(), 
				moduleQuery.getPage(), moduleQuery.getStartNum());
		List moduleList = resourceModuleDao.getModuleInfoByQuery(moduleQuery);
		RowSet rowSet = new RowSet();
		rowSet.setRows(moduleList);
		rowSet.setRecords(totalInfo.getTotalCount());
		rowSet.setPage(moduleQuery.getPage());
		rowSet.setTotal(totalInfo.getPageTotal());
		return rowSet;
	}


}
