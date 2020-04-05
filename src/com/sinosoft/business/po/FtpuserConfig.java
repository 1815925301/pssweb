

package com.sinosoft.business.po;


public class FtpuserConfig {
	
	
    //id       数据库字段: ID 
	private Double id;
	
	
    //单位名称       数据库字段: COMPANY 
	private java.lang.String company;
	
	
    //IP地址       数据库字段: IPADDRESS 
	private java.lang.String ipaddress;
	
	
    //端口      数据库字段: PORT 
	private java.lang.String port;
	
	
    //FTP用户名      数据库字段: CUSERNAME 
	private java.lang.String cusername;
	
	
    //FTP密码       数据库字段: CPASSWORD 
	private java.lang.String cpassword;
	
	
    //创建时间       数据库字段: CREATETIME 
	private String createtime;

    //主用户类型        数据库字段: TRANSTYPE 
	private java.lang.String transtype;
	
	
    //推送目录      数据库字段: TARGETLOCALPATH 
	private java.lang.String targetlocalpath;
	private String path1;
	private String path2;
	private String path3;
	private String path4;
	private String path5;
	
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

	public String getPath1() {
		return path1;
	}

	public void setPath1(String path1) {
		this.path1 = path1;
	}

	public String getPath2() {
		return path2;
	}

	public void setPath2(String path2) {
		this.path2 = path2;
	}

	public String getPath3() {
		return path3;
	}

	public void setPath3(String path3) {
		this.path3 = path3;
	}

	public String getPath4() {
		return path4;
	}

	public void setPath4(String path4) {
		this.path4 = path4;
	}

	public String getPath5() {
		return path5;
	}

	public void setPath5(String path5) {
		this.path5 = path5;
	}


}
