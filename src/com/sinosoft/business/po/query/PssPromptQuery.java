/*
 * Powered By J.C
 * Since 2015 - 2016-42-23
 */

package com.sinosoft.business.po.query;


import com.sinosoft.po.query.BasePaginationQuery;


public class PssPromptQuery  extends BasePaginationQuery {
	
	
	 //id       数据库字段: ID 
	private Integer id;
	
	
	 //KEY       数据库字段: KEY 
	private java.lang.String key;
	
	
	 //VALUE       数据库字段: VALUE 
	private java.lang.String value;
	
	
	 //备注信息       数据库字段: DES 
	private java.lang.String des;
	
	
	 //创建时间       数据库字段: CREATETIME 
	private java.lang.String createtime;
	
	
	private String sqlWhere;
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setKey(java.lang.String key) {
		this.key = key== null ? null : key.trim();
	}
	
	public java.lang.String getKey() {
		return this.key;
	}
	public void setValue(java.lang.String value) {
		this.value = value== null ? null : value.trim();
	}
	
	public java.lang.String getValue() {
		return this.value;
	}
	public void setDes(java.lang.String des) {
		this.des = des== null ? null : des.trim();
	}
	
	public java.lang.String getDes() {
		return this.des;
	}

	public java.lang.String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(java.lang.String createtime) {
		this.createtime = createtime;
	}


}
