package com.sinosoft.security.dao;

import java.util.List;

import com.sinosoft.security.po.Department;
import com.sinosoft.security.po.query.DepartmentQuery;


/**
 * @Package com.sinosoft.security.dao
 * @ClassName: DepartmentDao
 * @Description: 部门信息的DAO 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-9 上午02:23:30
 */
public interface DepartmentDao {
	
	/**
	 * 根据部门id获取部门信息
	 * @param detpId
	 * @return DepartmentDao
	 * @throws
	 * @author zzq
	 * @date 2013-10-9 上午02:27:21
	 * @version V1.0
	 */
	public Department getDeptInfoByDeptId(Long detpId);
	
	/**
	 * 获取部门树
	 * @param 
	 * @return List<Department>
	 * @throws
	 * @author zhaomin
	 * @date 2014-5-12 上午22:27:21
	 * @version V1.0
	 */
	public List<Department> getDeptInfoTree();

	
	/**
	 * 根据部门名称查询部门信息
	 * @param detpName
	 * @return Department
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:47:40
	 * @version V1.0
	 */
	public Department getDeptInfoByDeptName(String detpName);
	
	/**
	 * 根据部门名称与ID精确查找符合条件的部门数量
	 * @param detpQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-18 下午10:50:03
	 * @version V1.0
	 */
	public Integer getDeptCountByDeptName(DepartmentQuery detpQuery);
	
	/**
	 * 根据检索条件获取部门的数量
	 * @param detpQuery
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-16 下午11:36:03
	 * @version V1.0
	 */
	public Integer getDeptCountByQuery(DepartmentQuery detpQuery);
	
	/**
	 * 根据检索条件获取部门的记录信息
	 * @param detpQuery
	 * @return List<Department>
	 * @throws
	 * @author zzq
	 * @date 2013-10-16 下午11:35:36
	 * @version V1.0
	 */
	public List<Department> getDeptInfoByQuery(DepartmentQuery detpQuery);
	
	/**
	 * 新增部门信息
	 * @param department
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:46:07
	 * @version V1.0
	 */
	public Integer addNewDepartment(Department department);
	
	/**
	 * 根据部门id更新部门信息
	 * @param department
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:54:06
	 * @version V1.0
	 */
	public Integer updateDeptInfoByDeptId(Department department);
	
	/**
	 * 根据部门id删除部门信息 物理删除
	 * @param detpId
	 * @return Integer
	 * @throws
	 * @author zzq
	 * @date 2013-10-17 下午06:56:39
	 * @version V1.0
	 */
	public Integer deleteDeptInfoByDeptId(Long detpId);

}
