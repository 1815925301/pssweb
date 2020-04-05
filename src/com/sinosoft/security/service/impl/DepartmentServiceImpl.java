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
import com.sinosoft.security.dao.DepartmentDao;
import com.sinosoft.security.po.Department;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendDepartment;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.DepartmentQuery;
import com.sinosoft.security.service.DepartmentService;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.security.service.impl
 * @ClassName: DepartmentServiceImpl
 * @Description: 部门控制层web入口
 * @author zm
 * @Version V1.0
 * @date 2013-10-9 上午02:38:27
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	@Resource
	private DepartmentDao departmentDao;
	
	@Resource
	private UsersService usersService;
	
	@Resource
	private SystemConstant systemConstant;

	@Override
	public Department getDeptInfoByDeptId(Long deptId) {
		if (deptId == null) {
			LOGGER.error("参数错误：机构id为空！");
			return null;
		}
		LOGGER.debug("Service层：根据机构id获取机构信息");
		return departmentDao.getDeptInfoByDeptId(deptId);
	}
	
	@Override
	public Department getDeptInfoByDeptName(String deptName) {
		if (deptName == null) {
			LOGGER.error("参数错误：机构名称为空！");
			return null;
		}
		LOGGER.debug("Service层：根据机构名称取机构信息");
		return departmentDao.getDeptInfoByDeptName(deptName);
	}
	
	@Override
	public List<Department> getDeptInfoTree() {
		LOGGER.debug("Service层：获取机构树");
		return departmentDao.getDeptInfoTree();
	}

	
	@Override
	public String getDeptNameByDeptId(Long deptId) {
		LOGGER.debug("Service层：根据机构id获取机构名称");
		if (deptId == null) {
			LOGGER.error("参数错误：机构id为空！");
			return "";
		}
		String deptName = "";
//		List<Department> deptList = systemConstant.getDepartmentList();
//		if (deptList != null && deptList.size() > 0) {
//			for (Department dept : deptList) {
//				if (deptId.compareTo(dept.getId()) == 0) {
//					deptName = dept.getDeptName();
//					break;
//				}
//			}
//		}
		return deptName;
	}
	
	@Override
	public Integer getDeptCountByQuery(DepartmentQuery deptQuery) {
		LOGGER.debug("Service层：根据检索条件获取机构的数量");
		if (deptQuery == null) {
			LOGGER.error("参数错误：查询参数对象为空！");
			return null;
		}
		return departmentDao.getDeptCountByQuery(deptQuery);
	}
	
	@Override
	public List<Department> getDeptInfoByQuery(DepartmentQuery deptQuery) {
		LOGGER.debug("Service层：根据检索条件获取机构的记录信息");
		if (deptQuery == null) {
			LOGGER.error("参数错误：查询参数对象为空！");
			return null;
		}
		return departmentDao.getDeptInfoByQuery(deptQuery);
	}
	
	@Override
	public void getDeptInfoForInitPage(ModelMap model, String method, HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取机构信息 用于机构管理页面");
		DepartmentQuery deptQuery = new DepartmentQuery();
		//以id升序排序
		deptQuery.setSortBy("id");
		deptQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				deptQuery.setPage(Integer.parseInt(pageNum));
			}
			String deptName = request.getParameter("deptName");
			if (! StringUtils.isBlank(deptName)) {
				deptQuery.setDeptName(deptName);
				model.addAttribute("deptName", deptName);
			}
			String deptType = request.getParameter("deptType");
			if (! StringUtils.isBlank(deptType) && ! deptType.equals("-1")) {
				//deptQuery.setType(Integer.parseInt(deptType));
				model.addAttribute("deptType", deptType);
			}
		}
		Integer totalCount = departmentDao.getDeptCountByQuery(deptQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, deptQuery.getPageSize(), deptQuery.getPage(), deptQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<Department> deptInfoList = departmentDao.getDeptInfoByQuery(deptQuery);
		List<ExtendDepartment> eDeptInfoList = new ArrayList<ExtendDepartment>();
		if (deptInfoList != null && deptInfoList.size() > 0) {
			try {
				for (Department dept : deptInfoList) {
					ExtendDepartment eDept = new ExtendDepartment();
					BeanUtils.copyProperties(eDept, dept);
					//获取机构类型常量
//					if(dept.getType().compareTo(EnumDepartmentType.ORGANIZATION_IS_HEAD_QUARTERS.getStatus()) == 0)
//						eDept.setDeptType(MappingConstantConfig.getValue("ORGANIZATION_IS_HEAD_QUARTERS"));
//					else if (dept.getType().compareTo(EnumDepartmentType.ORGANIZATION_IS_AREA.getStatus()) == 0)
//						eDept.setDeptType(MappingConstantConfig.getValue("ORGANIZATION_IS_AREA"));
//					else if (dept.getType().compareTo(EnumDepartmentType.ORGANIZATION_IS_THIRD_PARTY.getStatus()) == 0)
//						eDept.setDeptType(MappingConstantConfig.getValue("ORGANIZATION_IS_THIRD_PARTY"));
//					else if (dept.getType().compareTo(EnumDepartmentType.ORGANIZATION_IS_OTHER.getStatus()) == 0)
//						eDept.setDeptType(MappingConstantConfig.getValue("ORGANIZATION_IS_OTHER"));
					eDeptInfoList.add(eDept);
				}
			} catch(Exception e) {
				LOGGER.error("将机构信息进行对象拷贝 对象属性值拷贝过程中出现异常：{}", e);
			}
		}
		model.addAttribute("deptInfoList", eDeptInfoList);
	}
	
	@Override
	public boolean addNewDepartment(Department department, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		department.trim();
		if (this.checkNewDeptInfo(department, modelMap)) {
			//通过校验，开始进行更新
			department.setCreateUserId(eUser.getId());
			Integer resultNum = departmentDao.addNewDepartment(department);
			if (resultNum.compareTo(new Integer(1)) == 0) {
//				if (EnumDepartmentType.ORGANIZATION_IS_AREA.getStatus().compareTo(department.getType()) == 0) {
//					//重新加载放在内存中的区域信息
//					systemConstant.setDepartmentList();
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
	public ExtendDepartment getExtendDeptInfoByDeptId(Long deptId) {
		LOGGER.debug("Service层：根据机构id获取机构扩展信息");
		Department dept = this.getDeptInfoByDeptId(deptId);
		if (dept == null || dept.getId() == null) {
			LOGGER.debug("根据机构id获取机构扩展信息，没有查取到机构信息：deptId {}", deptId);
			return null;
		}
		ExtendDepartment eDept = new ExtendDepartment();
		try {
			BeanUtils.copyProperties(eDept, dept);
			if (eDept.getUpdateUserId() != null && eDept.getUpdateUserId().compareTo(new Long(0)) == 0) {
				eDept.setUpdateUserId(null);
			}
		} catch(Exception e){
			LOGGER.error("根据机构id获取机构扩展信息 对象属性值拷贝过程中出现异常：{}", e);
		}
		//获取机构类型常量
//		if(dept.getType().compareTo(EnumDepartmentType.ORGANIZATION_IS_HEAD_QUARTERS.getStatus()) == 0)
//			eDept.setDeptType(MappingConstantConfig.getValue("ORGANIZATION_IS_HEAD_QUARTERS"));
//		else if (dept.getType().compareTo(EnumDepartmentType.ORGANIZATION_IS_AREA.getStatus()) == 0)
//			eDept.setDeptType(MappingConstantConfig.getValue("ORGANIZATION_IS_AREA"));
//		else if (dept.getType().compareTo(EnumDepartmentType.ORGANIZATION_IS_THIRD_PARTY.getStatus()) == 0)
//			eDept.setDeptType(MappingConstantConfig.getValue("ORGANIZATION_IS_THIRD_PARTY"));
//		else if (dept.getType().compareTo(EnumDepartmentType.ORGANIZATION_IS_OTHER.getStatus()) == 0)
//			eDept.setDeptType(MappingConstantConfig.getValue("ORGANIZATION_IS_OTHER"));
		
		//获取创建和修改机构信息的用户名称
		if (eDept.getCreateUserId() != null || eDept.getUpdateUserId() != null) {
			List<Long> userIdList = new ArrayList<Long>();
			if (eDept.getCreateUserId() != null) {
				userIdList.add(eDept.getCreateUserId());
			}
			if (eDept.getUpdateUserId() != null) {
				userIdList.add(eDept.getUpdateUserId());
			}
			List<Users> usersList = usersService.getUserInfoByUserIdList(userIdList);
			if (usersList != null && usersList.size() > 0) {
				for (Users user : usersList) {
					if (eDept.getCreateUserId() != null && user.getId().compareTo(eDept.getCreateUserId()) == 0) {
						eDept.setCreateUserName(user.getUserName());
					} 
					if (eDept.getUpdateUserId() != null && user.getId().compareTo(eDept.getUpdateUserId()) == 0) {
						eDept.setUpdateUserName(user.getUserName());
					}
				}
			}
		}
		return eDept;
	}
	
	
	@Override
	public boolean updateDepartment(Department department, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		department.trim();
		if (this.checkUpdateDeptInfo(department, modelMap)) {
			//通过校验，开始进行更新
			department.setUpdateUserId(eUser.getId());
			Integer resultNum = departmentDao.updateDeptInfoByDeptId(department);
			if (resultNum.compareTo(new Integer(1)) == 0) {
//				if (EnumDepartmentType.ORGANIZATION_IS_AREA.getStatus().compareTo(department.getType()) == 0) {
//					//重新加载放在内存中的区域信息
//					systemConstant.setDepartmentList();
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
	public boolean removeDepartment(Long deptId, ExtendUsers eUser, ActivityModelMap modelMap) {
		boolean result = false;
//		Integer userNum = usersService.getUsersCountByDeptId(deptId);
//		if (userNum != null && userNum.compareTo(new Integer(0)) == 0) {
//			List<Province> provinceList = systemConstant.getProvinceList();
//			boolean isHaveProvince = false;
//			if (provinceList != null && provinceList.size() > 0) {
//				for (Province province : provinceList) {
//					if (province.getAreaId().compareTo(deptId) == 0) {
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
				Integer resultNum = departmentDao.deleteDeptInfoByDeptId(deptId);
				if (resultNum.compareTo(new Integer(1)) == 0) {
					//重新加载放在内存中的区域信息
					//systemConstant.setDepartmentList();
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
	 * @param department
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 上午01:36:51
	 * @version V1.0
	 */
	private boolean checkNewDeptInfo(Department department, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：校验机构名称的长度，机构类型值是否在枚举常量限制中，校验新机构的名称是否已存在！");
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		
		this.checkDeptBaseInfo(department, errorInfoList);
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验新机构的名称是否已存在！");
		Department dept = this.getDeptInfoByDeptName(department.getDeptName());
		
		if (dept != null && dept.getDeptName().equals(department.getDeptName())) {
			LOGGER.info("机构名称已经存在");
			errorInfoList.add(new CheckErrorDto("dept_name", "机构名称已经存在"));
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
	 * @param department
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午10:39:19
	 * @version V1.0
	 */
	private boolean checkUpdateDeptInfo(Department department, ActivityModelMap modelMap) {
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		this.checkDeptBaseInfo(department, errorInfoList);
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验更新后机构的名称是否已存在！");
		DepartmentQuery deptQuery = new DepartmentQuery();
		deptQuery.setDeptName(department.getDeptName());
		deptQuery.setId(department.getId());
		
		Integer resultNum = departmentDao.getDeptCountByDeptName(deptQuery);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.info("机构名称已经存在");
			errorInfoList.add(new CheckErrorDto("change_dept_name", "机构名称已经存在"));
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
	 * @param department
	 * @param errorInfoList
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午10:35:52
	 * @version V1.0
	 */
	private void checkDeptBaseInfo(Department department, List<CheckErrorDto> errorInfoList) {
		LOGGER.debug("Service层：校验机构名称的长度，机构类型值是否在枚举常量限制中！");
		Integer length = null;
		String prefix = "";
		if (department.getId() != null)
			prefix = "change_";
		if (StringUtils.isBlank(department.getDeptName())) {
			LOGGER.info("请输入机构名称");
			errorInfoList.add(new CheckErrorDto(prefix + "dept_name", "请输入机构名称"));
		} else {
			length = new Integer(department.getDeptName().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("ORG_NAME_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "dept_name", "机构名称长度不能大于" 
						+ MappingInputLengthConfig.getValue("ORG_NAME_LENGTH") + "位"));
			}
		}
		
//		if (department.getType() == null) {
//			LOGGER.info("请选择机构类型");
//			errorInfoList.add(new CheckErrorDto(prefix + "dept_type", "机构类型选择有误"));
//		} else {
//			if (! (EnumDepartmentType.ORGANIZATION_IS_AREA.getStatus().compareTo(department.getType()) == 0
//					|| EnumDepartmentType.ORGANIZATION_IS_THIRD_PARTY.getStatus().compareTo(department.getType()) == 0
//					|| EnumDepartmentType.ORGANIZATION_IS_OTHER.getStatus().compareTo(department.getType()) == 0)) {
//				LOGGER.info("请选择机构类型");
//				errorInfoList.add(new CheckErrorDto(prefix + "dept_type", "机构类型选择有误"));
//			}
//		}
		
//		if (!StringUtils.isBlank(department.getNote())) {
//			length = new Integer(department.getNote().length());
//			if (length.compareTo(MappingInputLengthConfig.getValue("NOTE_LENGTH")) > 0) {
//				errorInfoList.add(new CheckErrorDto(prefix + "dept_note", "备注长度不能大于" 
//						+ MappingInputLengthConfig.getValue("NOTE_LENGTH") + "位"));
//			}
//		}
	}
	
	@Override
	public RowSet getDeptList(DepartmentQuery deptQuery, ActivityModelMap modelMap, String pid) {
		LOGGER.debug("Service层：根据查询参数获取机构信息，用于机构管理首页列表");
		//以id升序排序
		deptQuery.setSortBy("id");
		deptQuery.setSortType("1");
		deptQuery.setpId(Long.valueOf(pid)); 
		Integer totalCount = departmentDao.getDeptCountByQuery(deptQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, deptQuery.getPageSize(), 
				deptQuery.getPage(), deptQuery.getStartNum());
		List deptList = departmentDao.getDeptInfoByQuery(deptQuery);
		RowSet rowSet = new RowSet();
		rowSet.setRows(deptList);
		rowSet.setRecords(totalInfo.getTotalCount());
		rowSet.setPage(deptQuery.getPage());
		rowSet.setTotal(totalInfo.getPageTotal());
		return rowSet;
	}


}
