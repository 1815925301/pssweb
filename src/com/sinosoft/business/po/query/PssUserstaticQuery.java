/*
 * Powered By 尹力
 * Since 2015 - 2016-34-20
 */

package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;





public class PssUserstaticQuery  extends BasePaginationQuery {
	
	
	 //id       数据库字段: id 
	private Long id;
	
	
	 //username       数据库字段: USERNAME 
	private java.lang.String username;
	
	
	 //logintime       数据库字段: LOGINTIME 
	private String logintime;
	
	
	 //exittime       数据库字段: EXITTIME 
	private String exittime;
	
	
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
	public void setUsername(java.lang.String username) {
		this.username = username== null ? null : username.trim();
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	
	public String getLogintime() {
		return this.logintime;
	}
	public void setExittime(String exittime) {
		this.exittime = exittime;
	}
	
	public String getExittime() {
		return this.exittime;
	}


}
