/*
 * Powered By 尹力
 * Since 2015 - 2016-16-29
 */

package com.sinosoft.business.po;


public class PssOrderPay {
	
	
    //id       数据库字段: ID 
	private Long id;
	
	
    //用户银行       数据库字段: PSSUSERBANK 
	private java.lang.String pssuserbank;
	
	
    //abae银行       数据库字段: ABAESBANK 
	private java.lang.String abaesbank;
	
	
    //abae银行卡号       数据库字段: ABAEACCOUNT 
	private java.lang.String abaeaccount;
	
	
    //ticktid       数据库字段: TICKETID 
	private Long ticketid;
	
	
    //支付方式       数据库字段: TRANTYPE 
	private java.lang.String trantype;
	
	
    //支付时间       数据库字段: TRANDATE 
	private String trandate;
	
	
    //总价       数据库字段: AMOUNT 
	private java.lang.String amount;
	
	
    //公司地址       数据库字段: COMPANYADDRESS 
	private java.lang.String companyaddress;
	
	
    //上传图片地址       数据库字段: IMAGEADDRESS 
	private java.lang.String imageaddress;
	//主订单号
	
	private Long orderMainId;
	//单位
	private String unit;

	//用户ID（用来存储开通会员信息）
	private String userid;
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	//采集单号
	private Long collectid;
	
	//是否国内银行
	private Integer banktype;
	
	
	public Long getCollectid() {
		return collectid;
	}
	public void setCollectid(Long collectid) {
		this.collectid = collectid;
	}
	public Integer getBanktype() {
		return banktype;
	}
	public void setBanktype(Integer banktype) {
		this.banktype = banktype;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Long getOrderMainId() {
		return orderMainId;
	}
	public void setOrderMainId(Long orderMainId) {
		this.orderMainId = orderMainId;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setPssuserbank(java.lang.String pssuserbank) {
		this.pssuserbank = pssuserbank== null ? null : pssuserbank.trim();
	}
	
	public java.lang.String getPssuserbank() {
		return this.pssuserbank;
	}
	public void setAbaesbank(java.lang.String abaesbank) {
		this.abaesbank = abaesbank== null ? null : abaesbank.trim();
	}
	
	public java.lang.String getAbaesbank() {
		return this.abaesbank;
	}
	public void setAbaeaccount(java.lang.String abaeaccount) {
		this.abaeaccount = abaeaccount== null ? null : abaeaccount.trim();
	}
	
	public java.lang.String getAbaeaccount() {
		return this.abaeaccount;
	}
	public void setTicketid(Long ticketid) {
		this.ticketid = ticketid;
	}
	
	public Long getTicketid() {
		return this.ticketid;
	}
	public void setTrantype(java.lang.String trantype) {
		this.trantype = trantype== null ? null : trantype.trim();
	}
	
	public java.lang.String getTrantype() {
		return this.trantype;
	}
	public void setTrandate(String trandate) {
		this.trandate = trandate;
	}
	
	public String getTrandate() {
		return this.trandate;
	}
	public void setAmount(java.lang.String amount) {
		this.amount = amount== null ? null : amount.trim();
	}
	
	public java.lang.String getAmount() {
		return this.amount;
	}
	public void setCompanyaddress(java.lang.String companyaddress) {
		this.companyaddress = companyaddress== null ? null : companyaddress.trim();
	}
	
	public java.lang.String getCompanyaddress() {
		return this.companyaddress;
	}
	public void setImageaddress(java.lang.String imageaddress) {
		this.imageaddress = imageaddress== null ? null : imageaddress.trim();
	}
	
	public java.lang.String getImageaddress() {
		return this.imageaddress;
	}


}
