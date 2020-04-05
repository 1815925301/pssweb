package com.sinosoft.business.dao;

/*
 * Powered By 张永斌
 * Since 2015 - 2016-52-17
 */


import java.util.List;

import com.sinosoft.business.po.Student;
import com.sinosoft.business.po.query.StudentQuery;



public interface StudentDao {
	
	/**
	 * 新增方法
	 */
	public int insertStudent(Student STUDENT);
    
    
	/**
	 * 删除方法
	 */
	public int deleteStudent(Long id);
    
    /**
	 * 修改方法
	 */
	public  int updateStudent(Student STUDENT);

	/**
	 * 查询方法
	 */
    public Student selectStudentById(Long id);
    
    /**
     * 查询数量
     */
     public Integer getCountByQuery(StudentQuery STUDENTQuery);
    
    /**
     *按条件查询
     */
    public List<Student> getStudentInfoByQuery(StudentQuery STUDENTQuery);
    
    /**
     *按条件查询 不带分页
     */
    public List<Student> getStudentListByQuery(StudentQuery STUDENTQuery);

}