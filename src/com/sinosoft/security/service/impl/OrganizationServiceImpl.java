package com.sinosoft.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
import com.sinosoft.common.constant.EnumOrganizationType;
import com.sinosoft.common.constant.MappingConstantConfig;
import com.sinosoft.common.constant.MappingInputLengthConfig;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.dao.OrganizationDao;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendOrganization;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.extend.TreeNode;
import com.sinosoft.security.po.query.OrganizationQuery;
import com.sinosoft.security.service.OrganizationService;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.security.service.impl
 * @ClassName: OrganizationServiceImpl
 * @Description: 系统资源信息  MVC控制层web入口
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-9 上午02:38:27
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationServiceImpl.class);
	
	@Resource
	private OrganizationDao organizationDao;
	
	@Resource
	private UsersService usersService;
	
	@Resource
	private SystemConstant systemConstant;
	
	/**
	 * 获取机构树
	 */
	@Override
	public List getOrganizationTree(ActivityModelMap modelMap, HttpServletRequest request) {
		com.sinosoft.common.cache.CacheOperation objcache = com.sinosoft.common.cache.CacheOperation.getInstance();
		Vector<TreeNode> vOrganization = (Vector)objcache.getCacheData("ORGAANIZATION_INFO",0,0);
		if(vOrganization==null){
			vOrganization = new Vector();
			List<Organization> list  = organizationDao.getOrgInfoByQuery(new OrganizationQuery());
			if(list==null){
				LOGGER.error("找不到站点数据，请检查数据库！");
				modelMap.put("data", "找不到站点数据，请检查数据库！");
				return null;
			}
			List<TreeNode> nodeList = new ArrayList<TreeNode>();
			for(Organization data:list){
				TreeNode node= new TreeNode();
				node.setId(data.getId());
				node.setPid(data.getPid());
				node.setName(data.getOrgName());
				nodeList.add(node);
				vOrganization.add(node);
			}
			objcache.addCacheData("ORGAANIZATION_INFO",vOrganization);
			return nodeList;
		}
		return vOrganization;
	}
	
	/**
	 * 获取子机构
	 */
	@Override
	public List<Organization> getOrganizationList(ActivityModelMap modelMap,
			HttpServletRequest request) {
		String orgId = request.getParameter("orgId");
		List<Organization> eOrgInfoList = new ArrayList<Organization>();
		OrganizationQuery query = new  OrganizationQuery();
		if(orgId!=null){
			query.setpId(Integer.parseInt(orgId));
			eOrgInfoList = organizationDao.getOrgInfoByQuery(query);
		}else{
			eOrgInfoList = organizationDao.getOrgInfoByQuery(query);
		}
		return eOrgInfoList;
	}

	@Override
	public Organization getOrgInfoByOrgId(Long orgId) {
		if (orgId == null) {
			LOGGER.error("参数错误：机构id为空！");
			return null;
		}
		LOGGER.debug("Service层：根据机构id获取机构信息");
		return organizationDao.getOrgInfoByOrgId(orgId);
	}
	
	@Override
	public Organization getOrgInfoByOrgName(String orgName) {
		if (orgName == null) {
			LOGGER.error("参数错误：机构名称为空！");
			return null;
		}
		LOGGER.debug("Service层：根据机构名称取机构信息");
		return organizationDao.getOrgInfoByOrgName(orgName);
	}
	
	@Override
	public String getOrgNameByOrgId(Long orgId) {
		LOGGER.debug("Service层：根据机构id获取机构名称");
		if (orgId == null) {
			LOGGER.error("参数错误：机构id为空！");
			return "";
		}
		String orgName =organizationDao.getOrgInfoByOrgId(orgId).getOrgName();
		return orgName;
	}
	
	@Override
	public Integer getOrgCountByQuery(OrganizationQuery orgQuery) {
		LOGGER.debug("Service层：根据检索条件获取机构的数量");
		if (orgQuery == null) {
			LOGGER.error("参数错误：查询参数对象为空！");
			return null;
		}
		return organizationDao.getOrgCountByQuery(orgQuery);
	}
	
	@Override
	public List<Organization> getOrgInfoByQuery(OrganizationQuery orgQuery) {
		LOGGER.debug("Service层：根据检索条件获取机构的记录信息");
		if (orgQuery == null) {
			LOGGER.error("参数错误：查询参数对象为空！");
			return null;
		}
		return organizationDao.getOrgInfoByQuery(orgQuery);
	}
	
	@Override
	public void getOrgInfoForInitPage(ModelMap model, String method, HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取机构信息 用于机构管理页面");
		OrganizationQuery orgQuery = new OrganizationQuery();
		//以id升序排序
		orgQuery.setSortBy("id");
		orgQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				orgQuery.setPage(Integer.parseInt(pageNum));
			}
			String orgName = request.getParameter("orgName");
			if (! StringUtils.isBlank(orgName)) {
				orgQuery.setOrgName(orgName);
				model.addAttribute("orgName", orgName);
			}
			String orgType = request.getParameter("orgType");
			if (! StringUtils.isBlank(orgType) && ! orgType.equals("-1")) {
				orgQuery.setType(Integer.parseInt(orgType));
				model.addAttribute("orgType", orgType);
			}
		}
		List<Organization> orgInfoList = organizationDao.getOrgInfoByQuery(orgQuery);
		List<ExtendOrganization> eOrgInfoList = new ArrayList<ExtendOrganization>();
		if (orgInfoList != null && orgInfoList.size() > 0) {
			try {
				for (Organization org : orgInfoList) {
					ExtendOrganization eOrg = new ExtendOrganization();
					BeanUtils.copyProperties(eOrg, org);
					//获取机构类型常量
					if(org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_HEAD_QUARTERS.getStatus()) == 0)
						eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_HEAD_QUARTERS"));
					else if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_AREA.getStatus()) == 0)
						eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_AREA"));
					else if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_THIRD_PARTY.getStatus()) == 0)
						eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_THIRD_PARTY"));
					else if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_OTHER.getStatus()) == 0)
						eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_OTHER"));
					eOrgInfoList.add(eOrg);
				}
			} catch(Exception e) {
				LOGGER.error("将机构信息进行对象拷贝 对象属性值拷贝过程中出现异常：{}", e);
			}
		}
		model.addAttribute("orgInfoList", eOrgInfoList);
	}
	
	@Override
	public boolean addNewOrganization(HttpServletRequest request, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		String orgName=request.getParameter("orgName");
		String pid=request.getParameter("pid");
		String note=request.getParameter("note");
		
		Organization organization = new Organization();
		organization.setOrgName(orgName);
		organization.setPid(Long.parseLong(pid));
		if(!StringUtils.isBlank(note)){
			organization.setNote(note);
		}
		organization.setCreateUserId(eUser.getId());
		
		if(this.checkNewOrgInfo(organization, modelMap)){
			Integer resultNum = organizationDao.addNewOrganization(organization);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				//重新加载放在内存中的机构信息
				com.sinosoft.common.cache.CacheOperation objcache = com.sinosoft.common.cache.CacheOperation.getInstance();
				List<Organization> organizationList = organizationDao.getOrgInfoByQuery(new OrganizationQuery());
				Vector<TreeNode> vOrganization=new Vector<TreeNode>();
				for(Organization data:organizationList){
					TreeNode node= new TreeNode();
					node.setId(data.getId());
					node.setPid(data.getPid());
					node.setName(data.getOrgName());
					vOrganization.add(node);
				}
				objcache.addCacheData("ORGAANIZATION_INFO",vOrganization);
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public ExtendOrganization getExtendOrgInfoByOrgId(Long orgId) {
		LOGGER.debug("Service层：根据机构id获取机构扩展信息");
		Organization org = this.getOrgInfoByOrgId(orgId);
		if (org == null || org.getId() == null) {
			LOGGER.debug("根据机构id获取机构扩展信息，没有查取到机构信息：orgId {}", orgId);
			return null;
		}
		ExtendOrganization eOrg = new ExtendOrganization();
		try {
			BeanUtils.copyProperties(eOrg, org);
			if (eOrg.getUpdateUserId() != null && eOrg.getUpdateUserId().compareTo(new Long(0)) == 0) {
				eOrg.setUpdateUserId(null);
			}
		} catch(Exception e){
			LOGGER.error("根据机构id获取机构扩展信息 对象属性值拷贝过程中出现异常：{}", e);
		}
//		//获取机构类型常量
//		if(org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_HEAD_QUARTERS.getStatus()) == 0)
//			eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_HEAD_QUARTERS"));
//		else if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_AREA.getStatus()) == 0)
//			eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_AREA"));
//		else if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_THIRD_PARTY.getStatus()) == 0)
//			eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_THIRD_PARTY"));
//		else if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_OTHER.getStatus()) == 0)
//			eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_OTHER"));
		
//		//获取创建和修改机构信息的用户名称
//		if (eOrg.getCreateUserId() != null || eOrg.getUpdateUserId() != null) {
//			List<Long> userIdList = new ArrayList<Long>();
//			if (eOrg.getCreateUserId() != null) {
//				userIdList.add(eOrg.getCreateUserId());
//			}
//			if (eOrg.getUpdateUserId() != null) {
//				userIdList.add(eOrg.getUpdateUserId());
//			}
//			List<Users> usersList = usersService.getUserInfoByUserIdList(userIdList);
//			if (usersList != null && usersList.size() > 0) {
//				for (Users user : usersList) {
//					if (eOrg.getCreateUserId() != null && user.getId().compareTo(eOrg.getCreateUserId()) == 0) {
//						eOrg.setCreateUserName(user.getUserName());
//					} 
//					if (eOrg.getUpdateUserId() != null && user.getId().compareTo(eOrg.getUpdateUserId()) == 0) {
//						eOrg.setUpdateUserName(user.getUserName());
//					}
//				}
//			}
//		}
		return eOrg;
	}
	
	@Override
	public boolean updateOrganization(HttpServletRequest request, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		String orgName=request.getParameter("orgName");
		String id=request.getParameter("id");
		String note=request.getParameter("note");
		
		Organization organization = new Organization();
		organization.setOrgName(orgName);
		organization.setId(Long.parseLong(id));
		if(!StringUtils.isBlank(note)){
			organization.setNote(note);
		}
		
		if (this.checkNewOrgInfo(organization, modelMap)) {
			//通过校验，开始进行更新
			organization.setUpdateUserId(eUser.getId());
			Integer resultNum = organizationDao.updateOrgInfoByOrgId(organization);
			if (resultNum.compareTo(new Integer(1)) == 0) {
					//重新加载放在内存中的区域信息
				com.sinosoft.common.cache.CacheOperation objcache = com.sinosoft.common.cache.CacheOperation.getInstance();
				List<Organization> organizationList = organizationDao.getOrgInfoByQuery(new OrganizationQuery());
				Vector<TreeNode> vOrganization=new Vector<TreeNode>();
				for(Organization data:organizationList){
					TreeNode node= new TreeNode();
					node.setId(data.getId());
					node.setPid(data.getPid());
					node.setName(data.getOrgName());
					vOrganization.add(node);
				}
				objcache.addCacheData("ORGAANIZATION_INFO",vOrganization);
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public boolean removeOrganization(Long orgId, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		Integer userNum = usersService.getUsersCountByOrgId(orgId);
		if (userNum != null && userNum.compareTo(new Integer(0)) == 0) {
			boolean isHaveProvince = false;
			if (isHaveProvince) {
				modelMap.put("status", "error");
				modelMap.put("data", "存在属于本机构的省份，删除失败！");
			} else {
				//通过校验，开始进行删除
				Integer resultNum = organizationDao.deleteOrgInfoByOrgId(orgId);
				if (resultNum.compareTo(new Integer(1)) == 0) {
					//重新加载放在内存中的区域信息
					com.sinosoft.common.cache.CacheOperation objcache = com.sinosoft.common.cache.CacheOperation.getInstance();
					List<Organization> organizationList = organizationDao.getOrgInfoByQuery(new OrganizationQuery());
					Vector<TreeNode> vOrganization=new Vector<TreeNode >();
					for(Organization data:organizationList){
						TreeNode node= new TreeNode();
						node.setId(data.getId());
						node.setPid(data.getPid());
						node.setName(data.getOrgName());
						vOrganization.add(node);
					}
					objcache.addCacheData("ORGAANIZATION_INFO",vOrganization);
					result = true;
				} else {
					modelMap.put("status", "failure");
				}
			}
		} else {
			modelMap.put("status", "error");
			modelMap.put("data", "存在属于本机构的用户，删除失败！");
		} 
		return result;
	}
	
	/**
	 * 校验新机构的信息
	 * @param organization
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午01:36:51
	 * @version V1.0
	 */
	private boolean checkNewOrgInfo(Organization organization, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：校验机构名称的长度，机构类型值是否在枚举常量限制中，校验新机构的名称是否已存在！");
		
		LOGGER.debug("Service层：校验新机构的名称是否已存在！");
		Organization org = this.getOrgInfoByOrgName(organization.getOrgName());
		if (org != null && org.getOrgName().equals(organization.getOrgName())) {
			LOGGER.info("机构名称已经存在");
			modelMap.put("status", "error");
			modelMap.put("data", "机构名称 “"+organization.getOrgName()+"”已经存在");
			return false;
		}
		return true;
	}
	
	/**
	 * 校验更新后机构的信息
	 * @param organization
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 下午10:39:19
	 * @version V1.0
	 */
	private boolean checkUpdateOrgInfo(Organization organization, ActivityModelMap modelMap) {
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		this.checkOrgBaseInfo(organization, errorInfoList);
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验更新后机构的名称是否已存在！");
		OrganizationQuery orgQuery = new OrganizationQuery();
		orgQuery.setOrgName(organization.getOrgName());
		orgQuery.setId(organization.getId());
		
		Integer resultNum = organizationDao.getOrgCountByOrgName(orgQuery);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.info("机构名称已经存在");
			errorInfoList.add(new CheckErrorDto("change_org_name", "机构名称已经存在"));
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
	 * @param organization
	 * @param errorInfoList
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 下午10:35:52
	 * @version V1.0
	 */
	private void checkOrgBaseInfo(Organization organization, List<CheckErrorDto> errorInfoList) {
		LOGGER.debug("Service层：校验机构名称的长度，机构类型值是否在枚举常量限制中！");
		Integer length = null;
		String prefix = "";
		if (organization.getId() != null)
			prefix = "change_";
		if (StringUtils.isBlank(organization.getOrgName())) {
			LOGGER.info("请输入机构名称");
			errorInfoList.add(new CheckErrorDto(prefix + "org_name", "请输入机构名称"));
		} else {
			length = new Integer(organization.getOrgName().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("ORG_NAME_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "org_name", "机构名称长度不能大于" 
						+ MappingInputLengthConfig.getValue("ORG_NAME_LENGTH") + "位"));
			}
		}
		
		if (organization.getType() == null) {
			LOGGER.info("请选择机构类型");
			errorInfoList.add(new CheckErrorDto(prefix + "org_type", "机构类型选择有误"));
		} else {
			if (! (EnumOrganizationType.ORGANIZATION_IS_AREA.getStatus().compareTo(organization.getType()) == 0
					|| EnumOrganizationType.ORGANIZATION_IS_THIRD_PARTY.getStatus().compareTo(organization.getType()) == 0
					|| EnumOrganizationType.ORGANIZATION_IS_OTHER.getStatus().compareTo(organization.getType()) == 0)) {
				LOGGER.info("请选择机构类型");
				errorInfoList.add(new CheckErrorDto(prefix + "org_type", "机构类型选择有误"));
			}
		}
		
		if (!StringUtils.isBlank(organization.getNote())) {
			length = new Integer(organization.getNote().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("NOTE_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "org_note", "备注长度不能大于" 
						+ MappingInputLengthConfig.getValue("NOTE_LENGTH") + "位"));
			}
		}
	}

}
