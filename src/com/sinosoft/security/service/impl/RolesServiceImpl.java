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
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.CheckErrorDto;
import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.common.constant.EnumEditable;
import com.sinosoft.common.constant.MappingInputLengthConfig;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.dao.RolesDao;
import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.RoleResource;
import com.sinosoft.security.po.Roles;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendRoles;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.RolesQuery;
import com.sinosoft.security.service.ResourcesService;
import com.sinosoft.security.service.RoleResourceService;
import com.sinosoft.security.service.RolesService;
import com.sinosoft.security.service.UserRoleService;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.security.service.impl
 * @ClassName: RolesServiceImpl
 * @Description: 系统角色信息 服务层实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-3 下午09:23:50
 */
@Service("rolesService")
public class RolesServiceImpl implements RolesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RolesServiceImpl.class);
	
	@Resource
	private RolesDao rolesDao;
	
	@Resource
	private UsersService usersService;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private SystemConstant systemConstant;
	
	@Resource
	private RoleResourceService roleResourceService;
	@Resource
	private ResourcesService resourcesService;
	
	/*@Override
	public Vector<ExtendSite> getRoleTree(ActivityModelMap modelMap) {
		com.sinosoft.common.cache.CacheOperation objcache = com.sinosoft.common.cache.CacheOperation.getInstance();
		Vector<ExtendSite> roleSite = (Vector)objcache.getCacheData("LISTROLESITETREE",0,0);
		Vector<ExtendSite> treeSite=new Vector<>();
		if(roleSite==null){
			ExtendSite roleChild = null; 
			Vector<ExtendSite> vSite = (Vector)objcache.getCacheData("LISTALLSITE",0,0);
			if(vSite!=null){
				int sum=vSite.size();
				for(int i=0;i<sum;i++){
					ExtendSite site = vSite.get(i);
					treeSite.add(site);
					
					RolesQuery query = new RolesQuery();
					query.setSiteid(site.getId().longValue());
					List<Roles> roleList=rolesDao.getRolesListByQuery(query);
					if(roleList!=null&&roleList.size()>0){
						for(Roles role:roleList){
							roleChild = new ExtendSite(); 
							roleChild.setpId(role.getSiteid());
							roleChild.setName(role.getRoleName());
							roleChild.setId(role.getId().intValue());
							roleChild.setType(2);
							treeSite.addElement(roleChild);
						}
					}
				}
			}
			objcache.addCacheData("LISTROLESITETREE",treeSite);
			return vSite;
		}
		return null;
	}
	
	@Override
	public List<ExtendRoles> getRoleListInfo(Long siteid,ActivityModelMap modelMap) {
		RolesQuery query = new RolesQuery();
		query.setSiteid(siteid);
		List<Roles> roleList = rolesDao.getRolesListByQuery(query);
		List<ExtendRoles> eRoleList =new ArrayList<>();
		if(roleList!=null&&roleList.size()>0){
			for(Roles roles:roleList){
				ExtendRoles eRoles=new ExtendRoles();
				try {
					BeanUtils.copyProperties( eRoles,roles);
					
					Site  site = siteDao.selectSiteById(roles.getSiteid());
					Users user = usersService.getUserInfoByUserId(roles.getCreateUserId());
					if(site!=null){
						eRoles.setSiteNmae(site.getSitename());
					}
					if(user!=null){
						eRoles.setCreateUserName(user.getUserName());
					}
					eRoleList.add(eRoles);
				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("根据角色id获取角色扩展信息 对象属性值拷贝过程中出现异常：{}", e);
				} 
			}
		}
		return eRoleList;
	}*/
	
	@Override

	public List<Roles> getAllRoles() {
		LOGGER.debug("Service层：获取所有的系统角色");
		return rolesDao.getAllRoles();
	}
	
	@Override
	public void getRolesInfoForInitPage(ModelMap model, String method, HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取角色信息 用于角色管理页面");
		RolesQuery rolesQuery = new RolesQuery();
		//以id升序排序
		rolesQuery.setSortBy("id");
		rolesQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				rolesQuery.setPage(Integer.parseInt(pageNum));
			}
			String roleName = request.getParameter("roleNameSearch");
			if (! StringUtils.isBlank(roleName)) {
				rolesQuery.setRoleName(roleName);
				model.addAttribute("roleName", roleName);
			}
		}
		Integer totalCount = rolesDao.getRolesCountByQuery(rolesQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, rolesQuery.getPageSize(), 
				rolesQuery.getPage(), rolesQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<Roles> rolesList = rolesDao.getRolesInfoByQuery(rolesQuery);
		model.addAttribute("rolesList", rolesList);
	}
	
	@Override
	public ExtendRoles getExtendRolesById(Long roleId) {
		LOGGER.debug("Service层：根据角色id获取角色扩展信息");
		Roles roles = rolesDao.getRoleInfoById(roleId);
		if (roles == null || roles.getId() == null) {
			LOGGER.debug("根据角色id获取角色扩展信息，没有查取到角色信息：roleId {}", roleId);
			return null;
		}
		ExtendRoles eRoles = new ExtendRoles();
		try {
			BeanUtils.copyProperties(eRoles, roles);
			if (eRoles.getUpdateUserId() != null && eRoles.getUpdateUserId().compareTo(new Long(0)) == 0) {
				eRoles.setUpdateUserId(null);
			}
		} catch(Exception e){
			LOGGER.error("根据角色id获取角色扩展信息 对象属性值拷贝过程中出现异常：{}", e);
		}
		//获取创建和修改角色信息的角色名称
		if (eRoles.getCreateUserId() != null || eRoles.getUpdateUserId() != null) {
			List<Long> userIdList = new ArrayList<Long>();
			if (eRoles.getCreateUserId() != null) {
				userIdList.add(eRoles.getCreateUserId());
			}
			if (eRoles.getUpdateUserId() != null) {
				userIdList.add(eRoles.getUpdateUserId());
			}
			List<Users> usersList = usersService.getUserInfoByUserIdList(userIdList);
			if (usersList != null && usersList.size() > 0) {
				for (Users user : usersList) {
					if (eRoles.getCreateUserId() != null && user.getId().compareTo(eRoles.getCreateUserId()) == 0) {
						eRoles.setCreateUserName(user.getUserName());
					} 
					if (eRoles.getUpdateUserId() != null && user.getId().compareTo(eRoles.getCreateUserId()) == 0) {
						eRoles.setUpdateUserName(user.getUserName());
					}
				}
			}
		}
		return eRoles;
	}
	
	@Override
	public boolean addNewRole(Roles role, ExtendUsers eUser, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：新增角色信息");
		boolean result = false;
		//过滤掉角色信息中各个属性值的前后空格
		role.trim();
		if (this.checkNewRole(role, modelMap)) {
			//通过校验，开始进行更新
			role.setCreateUserId(eUser.getId());
			Integer resultNum = rolesDao.addNewRole(role);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				//将新的角色信息更新到内存中
				systemConstant.setRolesList();
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public boolean updateRoleInfo(Roles role, ExtendUsers eUser, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：根据角色id更新角色信息");
		boolean result = false;
		//过滤掉角色信息中各个属性值的前后空格
		role.trim();
		
		if (this.checkUpdateRole(role, modelMap)) {
			//通过校验，开始进行更新
			role.setUpdateUserId(eUser.getId());
			Integer resultNum = rolesDao.updateRoleInfo(role);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				//将新的角色信息更新到内存中
				systemConstant.setRolesList();
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public boolean removeRoleInfo(Long roleId, ExtendUsers eUser, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：根据角色id删除角色信息 物理删除");
		boolean result = false;
		if (this.checkRemoveRole(roleId, modelMap)) {
			//通过校验，开始进行删除
			Integer resultNum = rolesDao.deleteRoleById(roleId);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				//将新的角色信息更新到内存中
				systemConstant.setRolesList();
				//内存中重新加载角色资源信息
				systemConstant.setRoleResourceList();
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	@Transactional(value = "sinosoftWriteTxManager", isolation = Isolation.READ_COMMITTED, 
			propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean assignRoleAuth(Long roleId, ExtendUsers eUser, String resourceIds, ActivityModelMap modelMap){
		LOGGER.debug("Service层：根据角色id更新角色权限信息");
		boolean result = false;
		List<Long> resourceIdList = new ArrayList<Long>();
		if (checkAssignRoleAuth(roleId, resourceIds, modelMap, resourceIdList)) {
			//校验通过，可以更新角色的权限信息
			//先删除角色资源表中该角色现有的记录，然后再将新的资源记录插入 因此需要使用事务
			roleResourceService.deleteRoleResourceByRoleId(roleId);
			System.out.println("resourceIdList--------------"+resourceIdList);
			if (resourceIdList != null && resourceIdList.size() > 0) {
				RoleResource roleResource = null;
				for (Long resourceId : resourceIdList) {
					roleResource = new RoleResource();
					roleResource.setRoleId(roleId);
					roleResource.setResourceId(resourceId);
					roleResource.setCreateUserId(eUser.getId());
					roleResourceService.addNewRoleResource(roleResource);
				}
			}
			result = true;
			//内存中重新加载角色资源信息
		} 
		return result;
	}
	
	/**
	 * 校验为角色分配权限的信息
	 * @param roleId
	 * @param resourceIds
	 * @param modelMap
	 * @param resourceIdList
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午04:44:55
	 * @version V1.0
	 */
	private boolean checkAssignRoleAuth(Long roleId, String resourceIds, 
			ActivityModelMap modelMap, List<Long> resourceIdList) {
		//校验当前角色是否可编辑
		if (!checkRoleIsEditable(roleId)) {
			LOGGER.error("角色是系统默认角色，不可修改权限信息: roleId={}", roleId);
			modelMap.put("status", "failure");
			modelMap.put("data", "该角色是系统默认角色，不可修改权限信息！");
			modelMap.put("fresh", "false");
			return false;
		}
		
		//校验所传递来的资源id信息
		if (!resourceIds.equals("")) {
			String[] idArray = resourceIds.split(",");
			if (idArray != null && idArray.length > 0) {
				for (String id : idArray) {
					try {
						resourceIdList.add(new Long(id));
					} catch(Exception e) {
						modelMap.put("status", "error");
						modelMap.put("data", "资源参数有误，分配资源失败！");
						LOGGER.error("为角色分配资源的操作失败，参数资源id有误：{}", resourceIds);
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * 校验所给的角色是否可以被删除
	 * @param RolesServiceImpl
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午02:47:12
	 * @version V1.0
	 */
	private boolean checkRemoveRole(Long roleId, ActivityModelMap modelMap){
		//校验角色是否为可编辑
		if (!checkRoleIsEditable(roleId)) {
			LOGGER.error("当前角色是系统默认角色，不可删除: roleId={}", roleId);
			modelMap.put("status", "failure");
			modelMap.put("data", "该角色是系统默认角色，不可删除！");
			modelMap.put("fresh", "false");
			return false;
		}
		//校验角色是否被分配给了具体的用户
		Integer resultNum = userRoleService.getUserRoleCountByRoleId(roleId);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.error("该角色已分配给用户，不可删除: roleId={}", roleId);
			modelMap.put("status", "failure");
			modelMap.put("data", "该角色已分配给用户，不可删除！");
			modelMap.put("fresh", "false");
			return false;
		}
		return true;
	}
	
	/**
	 * 校验新的角色信息
	 * @param role
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午05:19:29
	 * @version V1.0
	 */
	private boolean checkNewRole(Roles role, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：校验角色名称的长度，备注的长度，校验新角色的名称是否已存在！");
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		
		this.checkRoleBaseInfo(role, errorInfoList);
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验新的角色的名称是否已存在！");
		RolesQuery rolesQuery = new RolesQuery();
		rolesQuery.setRoleName(role.getRoleName());
		
		Integer resultNum = rolesDao.getRoleCountByName(rolesQuery);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.info("角色名称已经存在");
			errorInfoList.add(new CheckErrorDto("role_name", "角色名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验更新后的角色信息
	 * @param role
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午05:18:17
	 * @version V1.0
	 */
	private boolean checkUpdateRole (Roles role, ActivityModelMap modelMap) {
		if (!checkRoleIsEditable(role.getId())) {
			LOGGER.error("该角色是系统默认角色，不可编辑: roleId={}", role.getId());
			modelMap.put("status", "failure");
			modelMap.put("data", "该角色是系统默认角色，不可编辑！");
			modelMap.put("fresh", "false");
			return false;
		}
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		this.checkRoleBaseInfo(role, errorInfoList);
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验更新后角色的名称是否已存在！");
		RolesQuery rolesQuery = new RolesQuery();
		rolesQuery.setRoleName(role.getRoleName());
		rolesQuery.setId(role.getId());
		
		Integer resultNum = rolesDao.getRoleCountByName(rolesQuery);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.info("角色名称已经存在");
			errorInfoList.add(new CheckErrorDto("change_role_name", "角色名称已经存在"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验角色的基本信息
	 * @param role
	 * @param errorInfoList
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 下午05:44:13
	 * @version V1.0
	 */
	private void checkRoleBaseInfo(Roles role, List<CheckErrorDto> errorInfoList) {
		Integer length = null;
		String prefix = "";
		if (role.getId() != null)
			prefix = "change_";
		//角色名称
		if (StringUtils.isBlank(role.getRoleName())) {
			LOGGER.info("请输入角色名");
			errorInfoList.add(new CheckErrorDto(prefix + "role_name", "请输入角色名"));
		} else {
			length = new Integer(role.getRoleName().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("ROLE_NAME_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "role_name", "角色名称长度不能大于" 
						+ MappingInputLengthConfig.getValue("ROLE_NAME_LENGTH") + "位"));
			}
		}
		
		//角色备注信息
		if (!StringUtils.isBlank(role.getNote())) {
			length = new Integer(role.getNote().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("NOTE_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "role_note", "备注长度不能大于" 
						+ MappingInputLengthConfig.getValue("NOTE_LENGTH") + "位"));
			}
		}
	}
	
	@Override
	public List<Resources> getAllResourcesByRole(Long roleId) {
		//首先，获取当前角色能够访问的资源信息
		List<Long> roleIdList = new ArrayList<Long>();
		roleIdList.add(roleId);
		List<RoleResource> roleResourceList = roleResourceService.getRoleResourceInfoByRoleIdList(roleIdList);
		List<Resources> resourcesMenuList = new ArrayList<Resources>();
		//获取到资源模块与模块下资源的集合
	/*	List<Resources> moduleList = systemConstant.getResourceModuleList();
		if (moduleList == null || moduleList.size() == 0) {
			LOGGER.error("获取菜单资源信息出现错误！");
			return null;
		} */
		/*if (roleResourceList != null && roleResourceList.size() > 0) {*/
			List<Long> resourceIdList = new ArrayList<Long>();
			for (RoleResource roleResource : roleResourceList) {
				resourceIdList.add(roleResource.getResourceId());
			}
			List<Resources> resourcesList = resourcesService.getAllMenuResources();
			//在系统所有资源的集合中，为每个元素打上标记 表明当前角色是否有权可以访问该资源
			if (resourcesList != null && resourcesList.size() > 0) {
				for (Resources resources : resourcesList) {
					for (RoleResource roleRes : roleResourceList) {
						if (roleRes.getResourceId().compareTo(resources.getId()) == 0) {
							resources.setHaveAuth(true);
							break;
						}
					}
				}
			resourcesMenuList=this.treeMenuList(resourcesList,(long)0);
			} else {
				LOGGER.error("没有获取到资源信息");
				return null;
			}
		/*} else {
			LOGGER.error("生成菜单的过程中，没有获取到当前用户所拥角色的资源id");
			return null;
		}*/
	
		//在系统所有资源的集合中，为每个元素打上标记 表明当前角色是否有权可以访问该资源
		/*		if (roleResourceList != null && roleResourceList.size() > 0) {
			for (ResourceModule module : moduleList) {
				if (module.getResourceList() != null && module.getResourceList().size() > 0) {
					for (Resources resources : module.getResourceList()) {
						for (RoleResource roleRes : roleResourceList) {
							if (roleRes.getResourceId().compareTo(resources.getId()) == 0) {
								resources.setHaveAuth(true);
								break;
							}
						}
					}
				}
			}
		}*/
		return resourcesMenuList;
	}
	     //0-1,1-16,1-19,1-20,1-47,1-144,1-146,1-147
	   //第一遍，parentid换成1
		 public List<Resources> treeMenuList(List<Resources> menuList, Long parentId) {  
 			 List<Resources> childMenu = new ArrayList<Resources>(); 
		       for (Resources r : menuList) {  
		           Long menuId = r.getId();  
		           Long pid = r.getpId();  
		           if (parentId == pid) {  
		        	   List<Resources> c_node = treeMenuList(menuList, menuId);  
		               r.setResourceList(c_node); 
		               childMenu.add(r);
		           }  
		       }  
		       return childMenu;  
		   }  
	/**
	 * 校验所给角色是否能够被编辑
	 * @param RolesServiceImpl
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午02:35:51
	 * @version V1.0
	 */
	private boolean checkRoleIsEditable(Long roleId){
		boolean isEditable = false;
		List<Roles> rolesList = systemConstant.getRolesList();
		if (rolesList != null && rolesList.size() > 0) {
			for (Roles role : rolesList) {
				if(role.getId().compareTo(roleId) == 0){
					if (role.getEditable().compareTo(EnumEditable.EDITABLE_YES.getStatus()) == 0)
						isEditable = true;
					break;
				}
			}
		}
		return isEditable;
	}
}
