

package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;




public class FtpuserConfigQuery  extends BasePaginationQuery {
	
	
	 //id       数据库字段: ID 
	private Double id;
	
	
	 //company       数据库字段: COMPANY 
	private java.lang.String company;
	
	
	 //ipaddress       数据库字段: IPADDRESS 
	private java.lang.String ipaddress;
	
	
	 //port       数据库字段: PORT 
	private java.lang.String port;
	
	
	 //cusername       数据库字段: CUSERNAME 
	private java.lang.String cusername;
	
	
	 //cpassword       数据库字段: CPASSWORD 
	private java.lang.String cpassword;
	
	
	 //createtime       数据库字段: CREATETIME 
	private String createtime;
	
	 //transtype       数据库字段: TRANSTYPE 
	private java.lang.String transtype;
	
	
	 //targetlocalpath       数据库字段: TARGETLOCALPATH 
	private java.lang.String targetlocalpath;
	
	
	private String sqlWhere;
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
	public void setId(Double id) {
		this.id = id;
	}
	
	public Double getId() {
		return this.id;
	}
	public void setCompany(java.lang.String company) {
		this.company = company== null ? null : company.trim();
	}
	
	public java.lang.String getCompany() {
		return this.company;
	}
	public void setIpaddress(java.lang.String ipaddress) {
		this.ipaddress = ipaddress== null ? null : ipaddress.trim();
	}
	
	public java.lang.String getIpaddress() {
		return this.ipaddress;
	}
	public void setPort(java.lang.String port) {
		this.port = port== null ? null : port.trim();
	}
	
	public java.lang.String getPort() {
		return this.port;
	}
	public void setCusername(java.lang.String cusername) {
		this.cusername = cusername== null ? null : cusername.trim();
	}
	
	public java.lang.String getCusername() {
		return this.cusername;
	}
	public void setCpassword(java.lang.String cpassword) {
		this.cpassword = cpassword== null ? null : cpassword.trim();
	}
	
	public java.lang.String getCpassword() {
		return this.cpassword;
	}

	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getCreatetime() {
		return this.createtime;
	}

	public void setTranstype(java.lang.String transtype) {
		this.transtype = transtype== null ? null : transtype.trim();
	}
	
	public java.lang.String getTranstype() {
		return this.transtype;
	}
	public void setTargetlocalpath(java.lang.String targetlocalpath) {
		this.targetlocalpath = targetlocalpath== null ? null : targetlocalpath.trim();
	}
	
	public java.lang.String getTargetlocalpath() {
		return this.targetlocalpath;
	}


}
