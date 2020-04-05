
package com.sinosoft.business.po;

/**
 * @Package com.sinosoft.business.po
 * @ClassName: PssProvince
 * @Description: 省份信息 实体类
 * @author hao
 * @date 2016-8-24
 */

public class PssProvince {
	
	
    //gid       数据库字段: GID 
	private Double gid;
	
	
    //name       数据库字段: NAME 
	private String name;
	
	
	 //code      数据库字段: CODE 
	private Double code;
    //incountryCode       数据库字段: INCOUNTRYCODE 
	private Double incountryCode;
	
	
	//area      数据库字段: AREA 
	private Double area;
    
	
	public void setGid(Double gid) {
		this.gid = gid;
	}
	
	public Double getGid() {
		return this.gid;
	}
	public void setName(String name) {
		this.name = name== null ? null : name.trim();
	}
	
	public String getName() {
		return this.name;
	}

	public Double getCode() {
		return code;
	}

	public void setCode(Double code) {
		this.code = code;
	}

	public Double getIncountryCode() {
		return incountryCode;
	}

	public void setIncountryCode(Double incountryCode) {
		this.incountryCode = incountryCode;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}
}
