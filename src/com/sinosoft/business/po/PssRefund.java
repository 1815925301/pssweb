/*
 * Powered By 尹力
 * Since 2015 - 2017-40-17
 */

package com.sinosoft.business.po;


public class PssRefund {
	
	
    //id       数据库字段: ID 
	private Long id;
	
	
    //TICKETID       数据库字段: TICKETID 
	private Long ticketid;
	
	
    //退款总价       数据库字段: AMOUNT 
	private Long amount;
	
	
    //退款日期       数据库字段: TRANDATE 
	private String trandate;
	
	
    //图片路径       数据库字段: IMAGEADDRESS 
	private java.lang.String imageaddress;
	
	
    //订单ID       数据库字段: ORDERMAINID 
	private Long ordermainid;
	
	
    //管理员姓名       数据库字段: SYSNAME 
	private java.lang.String sysname;
	
	//单位
	private String unit;
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setTicketid(Long ticketid) {
		this.ticketid = ticketid;
	}
	
	public Long getTicketid() {
		return this.ticketid;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	
	public Long getAmount() {
		return this.amount;
	}
	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
	
	public String getTrandate() {
		return this.trandate;
	}
	public void setImageaddress(java.lang.String imageaddress) {
		this.imageaddress = imageaddress== null ? null : imageaddress.trim();
	}
	
	public java.lang.String getImageaddress() {
		return this.imageaddress;
	}
	public void setOrdermainid(Long ordermainid) {
		this.ordermainid = ordermainid;
	}
	
	public Long getOrdermainid() {
		return this.ordermainid;
	}
	public void setSysname(java.lang.String sysname) {
		this.sysname = sysname== null ? null : sysname.trim();
	}
	
	public java.lang.String getSysname() {
		return this.sysname;
	}


}
