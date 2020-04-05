package com.sinosoft.security.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.po.RowSet;
import com.sinosoft.security.po.Department;
import com.sinosoft.security.po.extend.ExtendDepartment;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.DepartmentQuery;

/**
 * @Package com.sinosoft.security.service
 * @ClassName: DepartmentService
 * @Description: 部门信息 服务层接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-9 上午02:36:30
 */
public interface DepartmentService {
	
	/**
	 * 根据部门id获取部门信息
	 * @param deptId
	 * @return Department
	 * @throws
	 * @author zzq
	 * @date 2013-10-9 上午02:37:34
	 * @version V1.0
	 */
	public Department getDeptInfoByDeptId(Long deptId);
	
	/**
	 * 根据部门id获取部门信息 扩展后的信息
	 * @param deptId
	 * @param modelMap
	 * @return ExtendDepartment
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 上午09:42:54
	 * @version V1.0
	 */
	public ExtendDepartment getExtendDeptInfoByDeptId(Long deptId);
	
	/**
	 * 获取部门树
	 * @param modelMap
	 * @return ExtendDepartment
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午19:42:54
	 * @version V1.0
	 */
	public List<Department> getDeptInfoTree();
	
	/**
	 * 根据部门名称取部门信息
	 * @param deptName
	 * @return Department
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 上午01:54:05
	 * @version V1.0
	 */
	public Department getDeptInfoByDeptName(String deptName);
	
	/**
	 * 根据部门id获取部门名称
	 * @param deptId
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-10-9 上午02:50:14
	 * @version V1.0
	 */
	public String getDeptNameByDeptId(Long deptId);
	
	/**
	 * 根据检索条件获取部门的数量
	 * @param deptQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-16 下午11:36:03
	 * @version V1.0
	 */
	public Integer getDeptCountByQuery(DepartmentQuery deptQuery);
	
	/**
	 * 根据检索条件获取部门的记录信息
	 * @param deptQuery
	 * @return List<Department>
	 * @throws
	 * @author zzq
	 * @date 2013-10-16 下午11:35:36
	 * @version V1.0
	 */
	public List<Department> getDeptInfoByQuery(DepartmentQuery deptQuery);
	
	/**
	 * 根据查询参数获取部门信息 用于部门管理页面
	 * @param model
	 * @param method
	 * @param request
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 上午12:12:59
	 * @version V1.0
	 */
	public void getDeptInfoForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 新增部门信息
	 * @param Department
	 * @param eUser
	 * @param modelMap
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 上午01:26:32
	 * @version V1.0
	 */
	public boolean addNewDepartment(Department department, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据部门id修改部门信息
	 * @param Department
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午03:43:52
	 * @version V1.0
	 */
	public boolean updateDepartment(Department department, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据部门ID删除部门信息
	 * @param deptId
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-10-19 上午01:05:47
	 * @version V1.0
	 */
	public boolean removeDepartment(Long deptId, ExtendUsers eUser, ActivityModelMap modelMap);
	
	public RowSet getDeptList(DepartmentQuery deptQuery, ActivityModelMap modelMap, String pid);
	
}
