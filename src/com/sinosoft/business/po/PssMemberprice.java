/*
 * Powered By 尹力
 * Since 2015 - 2016-01-10
 */

package com.sinosoft.business.po;


public class PssMemberprice {
	
	
    //ID       数据库字段: ID 
	private Long id;
	
	
    //会员类型       数据库字段: MEMBERTYPE 
	private Long membertype;
	
	
    //一个月       数据库字段: ONEMONTH 
	private Long onemonth;
	
	
    //三个月       数据库字段: TREEMONTHS 
	private Long treemonths;
	
	
    //六个月       数据库字段: SIXMONTHS 
	private Long sixmonths;
	
	
    //一年       数据库字段: YEAR 
	private Long year;
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setMembertype(Long membertype) {
		this.membertype = membertype;
	}
	
	public Long getMembertype() {
		return this.membertype;
	}
	public void setOnemonth(Long onemonth) {
		this.onemonth = onemonth;
	}
	
	public Long getOnemonth() {
		return this.onemonth;
	}
	public void setTreemonths(Long treemonths) {
		this.treemonths = treemonths;
	}
	
	public Long getTreemonths() {
		return this.treemonths;
	}
	public void setSixmonths(Long sixmonths) {
		this.sixmonths = sixmonths;
	}
	
	public Long getSixmonths() {
		return this.sixmonths;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	
	public Long getYear() {
		return this.year;
	}


}
