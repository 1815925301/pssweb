/*
 * Powered By 尹力
 * Since 2015 - 2016-42-09
 */

package com.sinosoft.business.po;


public class PssVersionnum {
	
	
    //ID       数据库字段: ID 
	private Long id;
	
	
    //版本名称       数据库字段: VERSIONNUM 
	private java.lang.String versionnum;
	
	
    //是否审核通过{1:审核失败，2：审核成功，3待审核}       数据库字段: ISAUDIT 
	private Long isaudit;
	
	
    //是否生效{1：是，2：否}       数据库字段: ISORNOTEFFECT 
	private Long isornoteffect;
	
	
    //checktime       数据库字段: CHECKTIME 
	private String checktime;
	
	
    //checkname       数据库字段: CHECKNAME 
	private java.lang.String checkname;
	
	private String note;
	
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setVersionnum(java.lang.String versionnum) {
		this.versionnum = versionnum== null ? null : versionnum.trim();
	}
	
	public java.lang.String getVersionnum() {
		return this.versionnum;
	}
	public void setIsaudit(Long isaudit) {
		this.isaudit = isaudit;
	}
	
	public Long getIsaudit() {
		return this.isaudit;
	}
	public void setIsornoteffect(Long isornoteffect) {
		this.isornoteffect = isornoteffect;
	}
	
	public Long getIsornoteffect() {
		return this.isornoteffect;
	}
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	
	public String getChecktime() {
		return this.checktime;
	}
	public void setCheckname(java.lang.String checkname) {
		this.checkname = checkname== null ? null : checkname.trim();
	}
	
	public java.lang.String getCheckname() {
		return this.checkname;
	}


}
