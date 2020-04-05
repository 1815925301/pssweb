package com.sinosoft.common.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.sinosoft.business.dao.PubZoncodeDao;
import com.sinosoft.business.po.City;
import com.sinosoft.business.po.Province;
import com.sinosoft.business.po.PubZoncode;
import com.sinosoft.business.po.query.PubZoncodeQuery;
import com.sinosoft.business.service.CityService;
import com.sinosoft.business.service.ProvinceService;
import com.sinosoft.security.po.ConstantChild;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.ResourceModule;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.RoleResource;
import com.sinosoft.security.po.Roles;
import com.sinosoft.security.po.query.OrganizationQuery;
import com.sinosoft.security.service.ConstantChildServer;
import com.sinosoft.security.service.ConstantServer;
import com.sinosoft.security.service.OrganizationService;
import com.sinosoft.security.service.ResourceModuleService;
import com.sinosoft.security.service.RoleResourceService;
import com.sinosoft.security.service.RolesService;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: SystemConstant
 * @Description: 系统常量管理 从数据库中取出的常用数据 如区域信息 车型信息
 * @author zzq
 * @Version V1.0
 * @date 2013-10-21 下午10:02:15
 */
@Repository(value="systemConstant")
public class SystemConstant {
	
	@Resource
	private OrganizationService organizationService;
	
	@Resource 
	private RolesService rolesService;
	
	@Resource 
	private RoleResourceService roleResourceService;
	
	@Resource
	private ProvinceService provinceService;
	
	@Resource
	private CityService cityService;
	
	@Resource
	private ResourceModuleService resourceModuleService;
	
	@Resource
	private ConstantServer constantService;
	
	@Resource
	private ConstantChildServer constantChildService;
	@Resource
	private PubZoncodeDao pubZoncodeDao;
	/**
	 * @Fields ORGANIZATION_LIST : 存放机构信息的常量
	 */
	private static List<Organization> ORGANIZATION_LIST = null;
	
	/**
	 * @Fields areaList : 存放区域信息的常量
	 */
	private static List<Organization> AREA_LIST = null;
	
	/**
	 * @Fields ROLES_LIST : 角色常量
	 */
	private static List<Roles> ROLES_LIST = null;
	
	/**
	 * @Fields ROLE_RESOURCE_LIST : 角色资源常量
	 */
	private static List<RoleResource> ROLE_RESOURCE_LIST = null;
	
	/**
	 * @Fields PROVINCE_LIST : 存放省份信息的常量
	 */
	private static List<Province> PROVINCE_LIST = null;
	
	/**
	 * @Fields CITY_LIST : 存放城市信息的常量
	 */
	private static List<City> CITY_LIST = null;
	
	/**
	 * @Fields RESOURCEMODULE_LIST : 系统资源模块与模块下资源的信息
	 */
	private static List<ResourceModule> RESOURCEMODULE_LIST = null;
	
	
	/**
	 * @Fields MAX_IMPORT_FILE_SIZE : 上传附件的最大限制 单位M
	 */
	private static Integer MAX_IMPORT_FILE_SIZE = null;
	
	/**
	 * @Fields MAX_ACTIVITY_FILE_SIZE : 活动计划文件的最大限制 单位M
	 */
	private static Integer MAX_ACTIVITY_FILE_SIZE = null;
	
	
	/**
	 * @Fields CONSTANT_MAP ：存放key value 这种常量集合
	 */
	private static Map CONSTANT_MAP = null;
	
	/**
	 * @Fields CONSTANT_TABLE_MAP ：存放key value 这种常量集合
	 */
	
	/**
	 * @Fields PUBZONECODE_LIST_ALL ：存放省份数据
	 */
	private static List<PubZoncode> PUBZONECODE_LIST_ALL = null;
	
	private static Map<String, List<ConstantChild>> CONSTANT_TABLE_MAP = null;
	
	
	/**
	 * 获取 上传附件的最大限制
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午12:35:48
	 * @version V1.0
	 */
	public Integer getMaxImportFileSize() {
		if (MAX_IMPORT_FILE_SIZE == null) 
			this.setMaxImportFileSize();
		return MAX_IMPORT_FILE_SIZE;
	}
	
	/**
	 * 设置 上传附件的最大限制
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午12:35:51
	 * @version V1.0
	 */
	public void setMaxImportFileSize(){
		MAX_IMPORT_FILE_SIZE = 100;
	}
	
	/**
	 * 获取 活动计划文件 的最大限制
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午12:35:48
	 * @version V1.0
	 */
	public Integer getMaxActivityFileSize() {
		if (MAX_ACTIVITY_FILE_SIZE == null) 
			this.setMaxActivityFileSize();
		return MAX_ACTIVITY_FILE_SIZE;
	}
	
	/**
	 * 设置 活动计划文件的最大限制
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-11-8 上午12:35:51
	 * @version V1.0
	 */
	public void setMaxActivityFileSize(){
		MAX_ACTIVITY_FILE_SIZE = 8;
	}
	
	/**
	 * 获取机构信息常量
	 * @return List<Organization>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 上午08:54:55
	 * @version V1.0
	 */
	public List<Organization> getOrganizationList() {
		if (ORGANIZATION_LIST == null || ORGANIZATION_LIST.size() == 0) {
			this.setOrganizationList();
		}
		return ORGANIZATION_LIST;
	}
	
	/**
	 * 生成机构信息常量
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 上午08:55:09
	 * @version V1.0
	 */
	public void setOrganizationList() {
		OrganizationQuery orgQuery = new OrganizationQuery();
		orgQuery.setPageSize(-1); //将分页设为-1 ，即意味不想要分页
		orgQuery.setSortType("1");
		List<Organization> organizationList = organizationService.getOrgInfoByQuery(orgQuery);
		ORGANIZATION_LIST = organizationList;
	}

	/**
	 * 获取区域信息的常量
	 * @return List<Organization>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 上午08:54:55
	 * @version V1.0
	 */
	public List<Organization> getAreaList() {
		if (AREA_LIST == null || AREA_LIST.size() == 0) {
			this.setAreaList();
		}
		return AREA_LIST;
	}
	
	/**
	 * 生成区域信息常量
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 上午08:55:09
	 * @version V1.0
	 */
	public void setAreaList() {
		List<Organization> subAreaList = new ArrayList<Organization>();
		List<Organization> organizationList = this.getOrganizationList();
		for (Organization org : organizationList) {
			if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_AREA.getStatus()) == 0) {
				subAreaList.add(org);
			}
		}
//		OrganizationQuery orgQuery = new OrganizationQuery();
//		orgQuery.setType(EnumOrganizationType.ORGANIZATION_IS_AREA.getStatus());
//		orgQuery.setPageSize(-1); //将分页设为-1 ，即意味不想要分页
//		orgQuery.setSortType("1");
//		List<Organization> areaList = organizationService.getOrgInfoByQuery(orgQuery);
		AREA_LIST = subAreaList;
	}
	
	/**
	 * 获取省份信息的常量
	 * @return List<Province>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 上午09:37:30
	 * @version V1.0
	 */
	public List<Province> getProvinceList(){
		if (PROVINCE_LIST == null || PROVINCE_LIST.size() == 0)
			this.setProvinceList();
		return PROVINCE_LIST;
			
	}
	
	/**
	 * 生成省份信息常量
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 上午09:37:49
	 * @version V1.0
	 */
	public void setProvinceList() {
		List<Province> provinceList = provinceService.getAllProvince();
		PROVINCE_LIST = provinceList;
	}
	
	/**
	 * 获取城市信息的常量
	 * @return List<City>
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午11:27:47
	 * @version V1.0
	 */
	public List<City> getCityList(){
		if (CITY_LIST == null || CITY_LIST.size() == 0)
			this.setCityList();
		return CITY_LIST;
			
	}
	
	/**
	 * 生成城市信息常量
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-22 下午11:27:24
	 * @version V1.0
	 */
	public void setCityList() {
		List<City> cityList = cityService.getAllCity();
		CITY_LIST = cityList;
	}
	
	/**
	 * 获取角色信息集合
	 * @return List<Roles>
	 * @throws
	 * @author zzq
	 * @date 2013-10-26 下午02:46:53
	 * @version V1.0
	 */
	public List<Roles> getRolesList() {
		if (ROLES_LIST == null || ROLES_LIST.size() == 0)
			this.setRolesList();
		return ROLES_LIST;
	}
	
	/**
	 * 生成角色信息集合
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-26 下午02:44:37
	 * @version V1.0
	 */
	public void setRolesList() {
		List<Roles> rolesList = rolesService.getAllRoles();
		ROLES_LIST = rolesList;
	}
	
	/**
	 * 获取角色资源信息集合
	 * @return List<RoleResource>
	 * @throws
	 * @author zzq
	 * @date 2013-11-17 下午02:02:01
	 * @version V1.0
	 */
	public List<RoleResource> getRoleResourceList() {
		if (ROLE_RESOURCE_LIST == null || ROLE_RESOURCE_LIST.size() == 0)
			this.setRoleResourceList();
		return ROLE_RESOURCE_LIST;
	}
	
	/**
	 * 生成角色资源信息集合
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-11-17 下午02:01:37
	 * @version V1.0
	 */
	public void setRoleResourceList() {
		List<RoleResource> roleResourceList = roleResourceService.getAllRoleResource();
		ROLE_RESOURCE_LIST = roleResourceList;
	}
	
	/**
	 * 获取系统资源模块与模块下资源的信息
	 * @return List<ResourceModule>
	 * @throws
	 * @author zzq
	 * @date 2013-10-27 上午11:13:08
	 * @version V1.0
	 */
	public List<ResourceModule> getResourceModuleList() {
		if (RESOURCEMODULE_LIST == null || RESOURCEMODULE_LIST.size() == 0)
			this.setResourceModuleList();
		if (RESOURCEMODULE_LIST != null && RESOURCEMODULE_LIST.size() > 0) {
			for (ResourceModule module : RESOURCEMODULE_LIST) {
				if (module.getResourceList() != null && module.getResourceList().size() > 0) {
					for (Resources resources : module.getResourceList()) {
						resources.setHaveAuth(false);
					}
				}
			}
		}
		return RESOURCEMODULE_LIST;
	}
	
	/**
	 * 生成 系统资源模块与模块下资源的信息
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-27 上午11:13:18
	 * @version V1.0
	 */
	public void setResourceModuleList() {
		List<ResourceModule> resourceModuleList = resourceModuleService.getResourceModuleInfoWithResources();
		RESOURCEMODULE_LIST = resourceModuleList;
	}
	
	/**
	 * 获取系统资源信息
	 * @return List<Resources>
	 * @throws
	 * @author zzq
	 * @date 2013-11-17 下午02:17:18
	 * @version V1.0
	 */
	public List<Resources> getResourcesList() {
		List<Resources> resourcesList = new ArrayList<Resources>();
		List<ResourceModule> resourceModuleList = this.getResourceModuleList();
		for (ResourceModule resourceModule : resourceModuleList) {
			resourcesList.addAll(resourceModule.getResourceList());
		}
		return resourcesList;
	}
	
	public Map getConstantList(){
		if (CONSTANT_MAP == null || CONSTANT_MAP.size() == 0)
			this.setConstantList();
		return CONSTANT_MAP;
			
	}
	public void setConstantList() {
		Map constantMap = new HashMap();	
		List<com.sinosoft.security.po.Constant> constantList = constantService.getAllConstantList();
		for(com.sinosoft.security.po.Constant con:constantList){
			constantMap.put(con.getName(), con.getConstantChildList());
		}
		CONSTANT_MAP = constantMap;
	}
	
	public Map<String, List<ConstantChild>> getConstantListByTableName(){
		if (CONSTANT_TABLE_MAP == null || CONSTANT_TABLE_MAP.size() == 0)
			this.setConstantListByTableName();
		return CONSTANT_TABLE_MAP;
	}
	
	public List getConstantListByTableName(String tableName){
		if (CONSTANT_TABLE_MAP == null || CONSTANT_TABLE_MAP.size() == 0)
			this.setConstantListByTableName();
		return CONSTANT_TABLE_MAP.get(tableName);
	}
	
	public void setConstantListByTableName() {
		Map<String, List<ConstantChild>> contantTableMap = new HashMap<String, List<ConstantChild>>();
		contantTableMap = constantChildService.getAllConstValue();
		CONSTANT_TABLE_MAP = contantTableMap;
	}
	
	public static Map getCONSTANT_MAP() {
		
		return CONSTANT_MAP;
	}

	public static void setCONSTANT_MAP(Map cONSTANT_MAP) {
		CONSTANT_MAP = cONSTANT_MAP;
	}
	
	public List<PubZoncode> getPubZoneCodeList(){
		if (PUBZONECODE_LIST_ALL == null || PUBZONECODE_LIST_ALL.size() == 0)
			this.setPubZoneCodeList();
		return PUBZONECODE_LIST_ALL;
			
	}
	public void setPubZoneCodeList() {
		PubZoncodeQuery pzQuery = new PubZoncodeQuery();
		pzQuery.setPageSize(100);
		pzQuery.setZonelevel(new Short("1"));
		pzQuery.setSortBy("ZONECODE");
		pzQuery.setSortType("1");
		List<PubZoncode> pubzList = pubZoncodeDao.selectByPrimaryQuery(pzQuery);
		PUBZONECODE_LIST_ALL = pubzList;
	}
}
