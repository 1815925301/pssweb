/*
 * Powered By 尹力
 * Since 2015 - 2016-13-25
 */

package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;





public class SysUsersetQuery  extends BasePaginationQuery {
	
	
	 //id       数据库字段: ID 
	private Long id;
	
	
	 //updatename       数据库字段: UPDATENAME 
	private java.lang.String updatename;
	
	
	 //frequency       数据库字段: FREQUENCY 
	private Long frequency;
	
	
	 //whetheropen       数据库字段: WHETHEROPEN 
	private Long whetheropen;
	
	
	 //username       数据库字段: USERNAME 
	private java.lang.String username;
	
	
	 //settime       数据库字段: SETTIME 
	private String settime;
	
	
	private String sqlWhere;
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setUpdatename(java.lang.String updatename) {
		this.updatename = updatename== null ? null : updatename.trim();
	}
	
	public java.lang.String getUpdatename() {
		return this.updatename;
	}
	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}
	
	public Long getFrequency() {
		return this.frequency;
	}
	public void setWhetheropen(Long whetheropen) {
		this.whetheropen = whetheropen;
	}
	
	public Long getWhetheropen() {
		return this.whetheropen;
	}
	public void setUsername(java.lang.String username) {
		this.username = username== null ? null : username.trim();
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setSettime(String settime) {
		this.settime = settime;
	}
	
	public String getSettime() {
		return this.settime;
	}


}
