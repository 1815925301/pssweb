/*
 * Powered By 尹力
 * Since 2015 - 2016-45-13
 */

package com.sinosoft.business.po;


public class PssTask {
	
	
    //taskid       数据库字段: TASKID 
	private Long taskid;
	
	
    //tasklevel       数据库字段: TASKLEVEL 
	private Long tasklevel;
	
	
    //createtime       数据库字段: CREATETIME 
	private String createtime;
	
	
    //orderid       数据库字段: ORDERID 
	private java.lang.String orderid;
	
	
    //ordertype       数据库字段: ORDERTYPE 
	private java.lang.String ordertype;
	
	
    //sendsys       数据库字段: SENDSYS 
	private java.lang.String sendsys;
	
	
    //receivesys       数据库字段: RECEIVESYS 
	private java.lang.String receivesys;
	
	
    //0：未发送，1：发送       数据库字段: STATUS 
	private Long status;
	
	
    //failnum       数据库字段: FAILNUM 
	private Long failnum;
	
	
    //reason       数据库字段: REASON 
	private java.lang.String reason;
	
	
	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
	
	public Long getTaskid() {
		return this.taskid;
	}
	public void setTasklevel(Long tasklevel) {
		this.tasklevel = tasklevel;
	}
	
	public Long getTasklevel() {
		return this.tasklevel;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public String getCreatetime() {
		return this.createtime;
	}
	public void setOrderid(java.lang.String orderid) {
		this.orderid = orderid== null ? null : orderid.trim();
	}
	
	public java.lang.String getOrderid() {
		return this.orderid;
	}
	public void setOrdertype(java.lang.String ordertype) {
		this.ordertype = ordertype== null ? null : ordertype.trim();
	}
	
	public java.lang.String getOrdertype() {
		return this.ordertype;
	}
	public void setSendsys(java.lang.String sendsys) {
		this.sendsys = sendsys== null ? null : sendsys.trim();
	}
	
	public java.lang.String getSendsys() {
		return this.sendsys;
	}
	public void setReceivesys(java.lang.String receivesys) {
		this.receivesys = receivesys== null ? null : receivesys.trim();
	}
	
	public java.lang.String getReceivesys() {
		return this.receivesys;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
	public Long getStatus() {
		return this.status;
	}
	public void setFailnum(Long failnum) {
		this.failnum = failnum;
	}
	
	public Long getFailnum() {
		return this.failnum;
	}
	public void setReason(java.lang.String reason) {
		this.reason = reason== null ? null : reason.trim();
	}
	
	public java.lang.String getReason() {
		return this.reason;
	}


}
