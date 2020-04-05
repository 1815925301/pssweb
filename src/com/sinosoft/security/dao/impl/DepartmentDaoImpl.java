package com.sinosoft.security.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.DepartmentDao;
import com.sinosoft.security.po.Department;
import com.sinosoft.security.po.query.DepartmentQuery;


/**
 * @Package com.sinosoft.security.dao.impl
 * @ClassName: DepartmentDaoImpl
 * @Description: 部门信息的DAO 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-9 上午02:24:17
 */
@Repository(value="departmentDao")
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoImpl.class);
	
	@Override
	public Department getDeptInfoByDeptId(Long deptId) {
		LOGGER.debug("Dao层：根据部门id获取部门信息");
		return (Department)getReadSqlSession().selectOne("departmentDao.selectDeptInfoByDeptId", deptId);
	}
	
	@Override
	public List<Department> getDeptInfoTree() {
		LOGGER.debug("Dao层：获取部门树");
		return getReadSqlSession().selectList("departmentDao.selectDeptInfoTree");
	}
	
	@Override
	public Department getDeptInfoByDeptName(String deptName) {
		LOGGER.debug("Dao层：根据部门名称查询部门信息");
		return (Department)getReadSqlSession().selectOne("departmentDao.selectDeptInfoByDeptName", deptName);
	}
	
	@Override
	public Integer getDeptCountByDeptName(DepartmentQuery deptQuery) {
		LOGGER.debug("Dao层：根据部门名称与ID精确查找符合条件的部门数量");
		return (Integer)getReadSqlSession().selectOne("departmentDao.selectDeptCountByDeptName", deptQuery);
	}
	
	@Override
	public Integer getDeptCountByQuery(DepartmentQuery deptQuery) {
		LOGGER.debug("Dao层：根据检索条件获取部门的数量");
		return (Integer)getReadSqlSession().selectOne("departmentDao.selectDeptCountByQuery", deptQuery);
	}
	
	@Override
	public List<Department> getDeptInfoByQuery(DepartmentQuery deptQuery) {
		LOGGER.debug("Dao层：根据检索条件获取部门的记录信息");
		return getReadSqlSession().selectList("departmentDao.selectDeptInfoByQuery", deptQuery);
	}
	
	@Override
	public Integer addNewDepartment(Department department) {
		LOGGER.debug("Dao层：新增部门信息");
		return (Integer)insert("departmentDao.insertNewDepartment", department);
	}
	
	@Override
	public Integer updateDeptInfoByDeptId(Department department) {
		LOGGER.debug("Dao层：根据部门id更新部门信息");
		return (Integer)update("departmentDao.updateDeptInfoByDeptId", department);
	}
	
	@Override
	public Integer deleteDeptInfoByDeptId(Long deptId) {
		LOGGER.debug("Dao层：根据部门id更新部门信息");
		return (Integer)delete("departmentDao.deleteDeptInfoByDeptId", deptId);
	}


}
