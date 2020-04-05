/*
 * Powered By J.C
 * Since 2016 - 08 - 23
 */

package com.sinosoft.business.po;


public class PssPrompt {
	
	
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
	public String getCreatetime() {
		return this.createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
