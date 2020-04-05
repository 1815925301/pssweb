/*
 * Powered By 张永斌
 * Since 2015 - 2016-52-17
 */

package com.sinosoft.business.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;


import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.business.dao.StudentDao;
import com.sinosoft.business.po.Student;
import com.sinosoft.business.po.query.StudentQuery;

@Repository(value="STUDENTDao")
public class StudentDaoImpl extends BaseDao implements StudentDao{
	
	@Override
	public int insertStudent(Student STUDENT){
		return insert("STUDENTDao.insertStudent",STUDENT);
	}
	
	@Override
	public int deleteStudent(Long id){
		return delete("STUDENTDao.deleteStudent",id);
	}
	
	@Override
	public  int updateStudent(Student STUDENT){
		return update("STUDENTDao.updateStudent",STUDENT);
	}
	@Override
	public Student selectStudentById(Long id){
		return (Student)getReadSqlSession().selectOne("STUDENTDao.getStudentById",id);
	}
	
	@Override
	public Integer getCountByQuery(StudentQuery STUDENTQuery){
		return getReadSqlSession().selectOne("STUDENTDao.getCountByQuery",STUDENTQuery);
	}
	
	@Override
	public List<Student> getStudentInfoByQuery(StudentQuery STUDENTQuery){
		return getReadSqlSession().selectList("STUDENTDao.getStudentInfoByQuery",STUDENTQuery);
	}
	
	@Override
	public List<Student> getStudentListByQuery(StudentQuery STUDENTQuery){
		return getReadSqlSession().selectList("STUDENTDao.getStudentListByQuery",STUDENTQuery);
	}
}
