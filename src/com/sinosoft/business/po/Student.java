/*
 * Powered By 张永斌
 * Since 2015 - 2016-52-17
 */

package com.sinosoft.business.po;


public class Student {
	
	
    //记录id       数据库字段: ID 
	private Double id;
	
	
    //学生姓名       数据库字段: NAME 
	private java.lang.String name;
	
	
    //学生年龄       数据库字段: AGE 
	private Double age;
	
	
	public void setId(Double id) {
		this.id = id;
	}
	
	public Double getId() {
		return this.id;
	}
	public void setName(java.lang.String name) {
		this.name = name== null ? null : name.trim();
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setAge(Double age) {
		this.age = age;
	}
	
	public Double getAge() {
		return this.age;
	}


}
