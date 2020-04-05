package com.sinosoft.security.service.impl;

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
import com.sinosoft.common.constant.MappingInputLengthConfig;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.po.RowSet;
import com.sinosoft.security.dao.ResourcesDao;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendResources;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.ResourcesQuery;
import com.sinosoft.security.service.ResourcesService;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.security.service.impl
 * @ClassName: ResourcesServiceImpl
 * @Description: 系统资源信息 服务层实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-3 下午09:22:31
 */
@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesServiceImpl.class);

	@Resource
	private ResourcesDao resourcesDao;
	
	@Resource
	private UsersService usersService;
	
	@Resource
	private SystemConstant systemConstant;
	
	@Override
	public List<Resources> getAllResources() {
		LOGGER.debug("Service层：获取系统所有资源信息");
		Resources r=new Resources();
		r.setId((long)0);
		r.setName("菜单");
		List<Resources> list=resourcesDao.getAllResources();
		list.add(r);
		return list;
	}
	
	@Override
	public List<Resources> getResourceInfoByIdList(List<Long> idList) {
		if (idList == null) {
			LOGGER.error("Service层：根据资源id集合获取系统资源信息，资源id集合参数为空");
			return null;
		}
		LOGGER.debug("Service层：根据资源id集合获取系统资源信息");
		return resourcesDao.getResourceInfoByIdList(idList);
	}
	
	@Override
	public List<Resources> getNonRestrictedResources() {
		LOGGER.debug("Service层：获取用户登录后默认可以访问的资源，不受角色限制");
		return resourcesDao.getNonRestrictedResources();
	}
	
	@Override
	public List<Resources> getAllMenuResources(){
		ResourcesQuery resourcesQuery = new ResourcesQuery();
		resourcesQuery.setIsMenu(new Integer(1)); //资源是菜单
		resourcesQuery.setIsRestricted(new Integer(1)); //资源的访问需要用户拥有权限
		resourcesQuery.setSortBy("module_id, sort_num");
		resourcesQuery.setSortType("1");
		resourcesQuery.setPageSize(-1); //将分页设为-1 ，即意味不想要分页
		return resourcesDao.getResourcesByQuery(resourcesQuery);
	}

	@Override
	public Resources getResourceInfoByResourceId(Long resourceId) {
		if (resourceId == null) {
			LOGGER.error("参数错误：功能id为空！");
			return null;
		}
		LOGGER.debug("Service层：根据功能id获取功能信息");
		return resourcesDao.getResourceInfoByResourceId(resourceId);
	}
	
	@Override
	public Resources getResourceInfoByResourceName(String resourceName) {
		if (resourceName == null) {
			LOGGER.error("参数错误：功能名称为空！");
			return null;
		}
		LOGGER.debug("Service层：根据功能名称取功能信息");
		return resourcesDao.getResourceInfoByResourceName(resourceName);
	}
	
	@Override
	public List<Resources> getResourceInfoTree(Long moduleId) {
		LOGGER.debug("Service层：获取功能树");
		return resourcesDao.getResourceInfoTree(moduleId);
	}
	
	@Override
	public RowSet getResourceListByModuleId(Long moduleId,ActivityModelMap modelMap) {
		
		LOGGER.debug("Service层：根据查询参数获取常量");
		//以id升序排序
		List resourceList = resourcesDao.getResourceInfoTree(moduleId);
		RowSet rowSet = new RowSet();
		rowSet.setRows(resourceList);
		rowSet.setRecords(resourceList.size());
		rowSet.setTotal(resourceList.size());
		
		return rowSet;
	}
	
	@Override
	public String getResourceNameByResourceId(Long resourceId) {
		LOGGER.debug("Service层：根据功能id获取功能名称");
		if (resourceId == null) {
			LOGGER.error("参数错误：功能id为空！");
			return "";
		}
		String resourceName = "";
//		List<Resources> resourceList = systemConstant.getResourcesList();
//		if (resourceList != null && resourceList.size() > 0) {
//			for (Resources resource : resourceList) {
//				if (resourceId.compareTo(resource.getId()) == 0) {
//					resourceName = resource.getResourceName();
//					break;
//				}
//			}
//		}
		return resourceName;
	}
	
	@Override
	public Integer getResourceCountByQuery(ResourcesQuery resourceQuery) {
		LOGGER.debug("Service层：根据检索条件获取功能的数量");
		if (resourceQuery == null) {
			LOGGER.error("参数错误：查询参数对象为空！");
			return null;
		}
		return resourcesDao.getResourceCountByQuery(resourceQuery);
	}
	
	@Override
	public List<Resources> getResourceInfoByQuery(ResourcesQuery resourceQuery) {
		LOGGER.debug("Service层：根据检索条件获取功能的记录信息");
		if (resourceQuery == null) {
			LOGGER.error("参数错误：查询参数对象为空！");
			return null;
		}
		return resourcesDao.getResourceInfoByQuery(resourceQuery);
	}
	
	@Override
	public void getResourceInfoForInitPage(ModelMap model, String method, HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取功能信息 用于功能管理页面");
		ResourcesQuery resourceQuery = new ResourcesQuery();
		//以id升序排序
		resourceQuery.setSortBy("id");
		resourceQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				resourceQuery.setPage(Integer.parseInt(pageNum));
			}
			String resourceName = request.getParameter("resourceName");
			if (! StringUtils.isBlank(resourceName)) {
				resourceQuery.setResourceName(resourceName);
				model.addAttribute("resourceName", resourceName);
			}
			String resourceType = request.getParameter("resourceType");
			if (! StringUtils.isBlank(resourceType) && ! resourceType.equals("-1")) {
				//resourceQuery.setType(Integer.parseInt(resourceType));
				model.addAttribute("resourceType", resourceType);
			}
		}
		Integer totalCount = resourcesDao.getResourceCountByQuery(resourceQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, resourceQuery.getPageSize(), resourceQuery.getPage(), resourceQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<Resources> resourceInfoList = resourcesDao.getResourceInfoByQuery(resourceQuery);
		List<ExtendResources> eResourceInfoList = new ArrayList<ExtendResources>();
		if (resourceInfoList != null && resourceInfoList.size() > 0) {
			try {
				for (Resources resource : resourceInfoList) {
					ExtendResources eResource = new ExtendResources();
					BeanUtils.copyProperties(eResource, resource);
					//获取功能类型常量
//					if(resource.getType().compareTo(EnumResourcesType.ORGANIZATION_IS_HEAD_QUARTERS.getStatus()) == 0)
//						eResource.setResourceType(MappingConstantConfig.getValue("ORGANIZATION_IS_HEAD_QUARTERS"));
//					else if (resource.getType().compareTo(EnumResourcesType.ORGANIZATION_IS_AREA.getStatus()) == 0)
//						eResource.setResourceType(MappingConstantConfig.getValue("ORGANIZATION_IS_AREA"));
//					else if (resource.getType().compareTo(EnumResourcesType.ORGANIZATION_IS_THIRD_PARTY.getStatus()) == 0)
//						eResource.setResourceType(MappingConstantConfig.getValue("ORGANIZATION_IS_THIRD_PARTY"));
//					else if (resource.getType().compareTo(EnumResourcesType.ORGANIZATION_IS_OTHER.getStatus()) == 0)
//						eResource.setResourceType(MappingConstantConfig.getValue("ORGANIZATION_IS_OTHER"));
					eResourceInfoList.add(eResource);
				}
			} catch(Exception e) {
				LOGGER.error("将功能信息进行对象拷贝 对象属性值拷贝过程中出现异常：{}", e);
			}
		}
		model.addAttribute("resourceInfoList", eResourceInfoList);
	}
	
	@Override
	public boolean addNewResources(Resources resources, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		if (this.checkNewResourceInfo(resources, modelMap)) {
			//通过校验，开始进行更新
			resources.setCreateUserId(eUser.getId());
			Integer resultNum = resourcesDao.addNewResources(resources);
			if (resultNum.compareTo(new Integer(1)) == 0) {
//				if (EnumResourcesType.ORGANIZATION_IS_AREA.getStatus().compareTo(resources.getType()) == 0) {
//					//重新加载放在内存中的区域信息
//					systemConstant.setResourcesList();
//					systemConstant.setAreaList();
//				}
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public ExtendResources getExtendResourceInfoByResourceId(Long resourceId) {
		LOGGER.debug("Service层：根据功能id获取功能扩展信息");
		Resources resource = this.getResourceInfoByResourceId(resourceId);
		if (resource == null || resource.getId() == null) {
			LOGGER.debug("根据功能id获取功能扩展信息，没有查取到功能信息：resourceId {}", resourceId);
			return null;
		}
		ExtendResources eResource = new ExtendResources();
		try {
			BeanUtils.copyProperties(eResource, resource);
			if (eResource.getUpdateUserId() != null && eResource.getUpdateUserId().compareTo(new Long(0)) == 0) {
				eResource.setUpdateUserId(null);
			}
		} catch(Exception e){
			LOGGER.error("根据功能id获取功能扩展信息 对象属性值拷贝过程中出现异常：{}", e);
		}
		//获取功能类型常量
//		if(resource.getType().compareTo(EnumResourcesType.ORGANIZATION_IS_HEAD_QUARTERS.getStatus()) == 0)
//			eResource.setResourceType(MappingConstantConfig.getValue("ORGANIZATION_IS_HEAD_QUARTERS"));
//		else if (resource.getType().compareTo(EnumResourcesType.ORGANIZATION_IS_AREA.getStatus()) == 0)
//			eResource.setResourceType(MappingConstantConfig.getValue("ORGANIZATION_IS_AREA"));
//		else if (resource.getType().compareTo(EnumResourcesType.ORGANIZATION_IS_THIRD_PARTY.getStatus()) == 0)
//			eResource.setResourceType(MappingConstantConfig.getValue("ORGANIZATION_IS_THIRD_PARTY"));
//		else if (resource.getType().compareTo(EnumResourcesType.ORGANIZATION_IS_OTHER.getStatus()) == 0)
//			eResource.setResourceType(MappingConstantConfig.getValue("ORGANIZATION_IS_OTHER"));
		
		//获取创建和修改功能信息的用户名称
		if (eResource.getCreateUserId() != null || eResource.getUpdateUserId() != null) {
			List<Long> userIdList = new ArrayList<Long>();
			if (eResource.getCreateUserId() != null) {
				userIdList.add(eResource.getCreateUserId());
			}
			if (eResource.getUpdateUserId() != null) {
				userIdList.add(eResource.getUpdateUserId());
			}
			List<Users> usersList = usersService.getUserInfoByUserIdList(userIdList);
			if (usersList != null && usersList.size() > 0) {
				for (Users user : usersList) {
					if (eResource.getCreateUserId() != null && user.getId().compareTo(eResource.getCreateUserId()) == 0) {
						eResource.setCreateUserName(user.getUserName());
					} 
					if (eResource.getUpdateUserId() != null && user.getId().compareTo(eResource.getUpdateUserId()) == 0) {
						eResource.setUpdateUserName(user.getUserName());
					}
				}
			}
		}
		return eResource;
	}
	
	
	@Override
	public boolean updateResources(Resources resources, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		if (this.checkUpdateResourceInfo(resources, modelMap)) {
			//通过校验，开始进行更新
			resources.setUpdateUserId(eUser.getId());
			Integer resultNum = resourcesDao.updateResourceInfoByResourceId(resources);
			if (resultNum.compareTo(new Integer(1)) == 0) {
//				if (EnumResourcesType.ORGANIZATION_IS_AREA.getStatus().compareTo(resources.getType()) == 0) {
//					//重新加载放在内存中的区域信息
//					systemConstant.setResourcesList();
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
	public boolean removeResources(Long resourceId, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
//		Integer userNum = usersService.getUsersCountByResourceId(resourceId);
//		if (userNum != null && userNum.compareTo(new Integer(0)) == 0) {
//			List<Province> provinceList = systemConstant.getProvinceList();
//			boolean isHaveProvince = false;
//			if (provinceList != null && provinceList.size() > 0) {
//				for (Province province : provinceList) {
//					if (province.getAreaId().compareTo(resourceId) == 0) {
//						isHaveProvince = true;
//						break;
//					}
//				}
//			}
//			if (isHaveProvince) {
//				modelMap.put("status", "error");
//				modelMap.put("data", "存在属于本功能的省份，删除失败！");
//			} else {
				//通过校验，开始进行删除
				Integer resultNum = resourcesDao.deleteResourceInfoByResourceId(resourceId);
				if (resultNum.compareTo(new Integer(1)) == 0) {
					//重新加载放在内存中的区域信息
					//systemConstant.setResourcesList();
					//systemConstant.setAreaList();
					result = true;
				} else {
					modelMap.put("status", "failure");
				}
//			}
//		} else {
//			modelMap.put("status", "error");
//			modelMap.put("data", "存在属于本功能的用户，删除失败！");
//		} 
		return result;
	}
	
	/**
	 * 校验新功能的信息
	 * @param resources
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 上午01:36:51
	 * @version V1.0
	 */
	private boolean checkNewResourceInfo(Resources resources, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：校验功能名称的长度，功能类型值是否在枚举常量限制中，校验新功能的名称是否已存在！");
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		
		this.checkResourceBaseInfo(resources, errorInfoList);
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验新功能的名称是否已存在！");
		Resources resource = this.getResourceInfoByResourceName(resources.getResourceName());
		
		if (resource != null && resource.getResourceName().equals(resources.getResourceName())) {
			LOGGER.info("功能名称已经存在");
			errorInfoList.add(new CheckErrorDto("resource_name", "功能名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验更新后功能的信息
	 * @param resources
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午10:39:19
	 * @version V1.0
	 */
	private boolean checkUpdateResourceInfo(Resources resources, ActivityModelMap modelMap) {
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		this.checkResourceBaseInfo(resources, errorInfoList);
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验更新后功能的名称是否已存在！");
		ResourcesQuery resourceQuery = new ResourcesQuery();
		resourceQuery.setResourceName(resources.getResourceName());
		resourceQuery.setId(resources.getId());
		
		Integer resultNum = resourcesDao.getResourceCountByResourceName(resourceQuery);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.info("功能名称已经存在");
			errorInfoList.add(new CheckErrorDto("change_resource_name", "功能名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验功能名称的长度，功能类型值是否在枚举常量限制中
	 * @param resources
	 * @param errorInfoList
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午10:35:52
	 * @version V1.0
	 */
	private void checkResourceBaseInfo(Resources resources, List<CheckErrorDto> errorInfoList) {
		LOGGER.debug("Service层：校验功能名称的长度，功能类型值是否在枚举常量限制中！");
		Integer length = null;
		String prefix = "";
		if (resources.getId() != null)
			prefix = "change_";
		if (StringUtils.isBlank(resources.getResourceName())) {
			LOGGER.info("请输入功能名称");
			errorInfoList.add(new CheckErrorDto(prefix + "resource_name", "请输入功能名称"));
		} else {
			length = new Integer(resources.getResourceName().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("ORG_NAME_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "resource_name", "功能名称长度不能大于" 
						+ MappingInputLengthConfig.getValue("ORG_NAME_LENGTH") + "位"));
			}
		}
		
//		if (resources.getType() == null) {
//			LOGGER.info("请选择功能类型");
//			errorInfoList.add(new CheckErrorDto(prefix + "resource_type", "功能类型选择有误"));
//		} else {
//			if (! (EnumResourcesType.ORGANIZATION_IS_AREA.getStatus().compareTo(resources.getType()) == 0
//					|| EnumResourcesType.ORGANIZATION_IS_THIRD_PARTY.getStatus().compareTo(resources.getType()) == 0
//					|| EnumResourcesType.ORGANIZATION_IS_OTHER.getStatus().compareTo(resources.getType()) == 0)) {
//				LOGGER.info("请选择功能类型");
//				errorInfoList.add(new CheckErrorDto(prefix + "resource_type", "功能类型选择有误"));
//			}
//		}
		
//		if (!StringUtils.isBlank(resources.getNote())) {
//			length = new Integer(resources.getNote().length());
//			if (length.compareTo(MappingInputLengthConfig.getValue("NOTE_LENGTH")) > 0) {
//				errorInfoList.add(new CheckErrorDto(prefix + "resource_note", "备注长度不能大于" 
//						+ MappingInputLengthConfig.getValue("NOTE_LENGTH") + "位"));
//			}
//		}
	}
	
	@Override
	public RowSet getResourceList(ResourcesQuery resourceQuery, ActivityModelMap modelMap, Long pId, Long moduleId) {
		LOGGER.debug("Service层：根据查询参数获取功能信息，用于功能管理首页列表");
		//以id升序排序
		resourceQuery.setSortBy("id");
		resourceQuery.setSortType("1");
		resourceQuery.setpId(pId); 
		//resourceQuery.setModuleId(moduleId);
		Integer totalCount = resourcesDao.getResourceCountByQuery(resourceQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, resourceQuery.getPageSize(), 
				resourceQuery.getPage(), resourceQuery.getStartNum());
		List resourceList = resourcesDao.getResourceInfoByQuery(resourceQuery);
		RowSet rowSet = new RowSet();
		rowSet.setRows(resourceList);
		rowSet.setRecords(totalInfo.getTotalCount());
		rowSet.setPage(resourceQuery.getPage());
		rowSet.setTotal(totalInfo.getPageTotal());
		return rowSet;
	}

	
}
