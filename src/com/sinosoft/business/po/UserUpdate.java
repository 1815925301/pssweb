/*
 * Powered By 尹力
 * Since 2015 - 2016-06-19
 */

package com.sinosoft.business.po;


public class UserUpdate {
	
	
    //id       数据库字段: ID 
	private Double id;
	
	
    //frequency       数据库字段: FREQUENCY 
	private Double frequency;
	
	
    //userid       数据库字段: USERID 
	private Double userid;
	
	
    //updatetime       数据库字段: UPDATETIME 
	private String updatetime;
	
	// username 
	private String username;
	
	
	// frequencyColumn  数据库字段FREQUENCYCOLUMN
	private String frequencyColumn;
	
	public void setId(Double id) {
		this.id = id;
	}
	
	public Double getId() {
		return this.id;
	}
	public void setFrequency(Double frequency) {
		this.frequency = frequency;
	}
	
	public Double getFrequency() {
		return this.frequency;
	}
	public void setUserid(Double userid) {
		this.userid = userid;
	}
	
	public Double getUserid() {
		return this.userid;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	public String getUpdatetime() {
		return this.updatetime;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFrequencyColumn() {
		return frequencyColumn;
	}

	public void setFrequencyColumn(String frequencyColumn) {
		this.frequencyColumn = frequencyColumn;
	}


}
