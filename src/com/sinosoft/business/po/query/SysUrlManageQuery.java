/*
 * Powered By 尹力
 * Since 2015 - 2016-18-08
 */

package com.sinosoft.business.po.query;


import com.sinosoft.po.query.BasePaginationQuery;


public class SysUrlManageQuery  extends BasePaginationQuery {
	
	
	 //id       数据库字段: ID 
	private Integer id;
	
	
	 //父id       数据库字段: F_ID 
	private Integer fid;
	
	
	 //中文描述       数据库字段: NAME_CN 
	private java.lang.String nameCn;
	
	
	 //英文描述       数据库字段: NAME_EN 
	private java.lang.String nameEn;
	
	
	 //url       数据库字段: URL 
	private java.lang.String url;
	
	
	 //备注       数据库字段: REMARK 
	private java.lang.String remark;
	
	
	private String sqlWhere;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getFid() {
		return fid;
	}


	public void setFid(Integer fid) {
		this.fid = fid;
	}


	public java.lang.String getNameCn() {
		return nameCn;
	}


	public void setNameCn(java.lang.String nameCn) {
		this.nameCn = nameCn;
	}


	public java.lang.String getNameEn() {
		return nameEn;
	}


	public void setNameEn(java.lang.String nameEn) {
		this.nameEn = nameEn;
	}


	public java.lang.String getUrl() {
		return url;
	}


	public void setUrl(java.lang.String url) {
		this.url = url;
	}


	public java.lang.String getRemark() {
		return remark;
	}


	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}


	public String getSqlWhere() {
		return sqlWhere;
	}


	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
}
