

package com.sinosoft.business.po;
/**
 * @Package com.sinosoft.business.po
 * @ClassName: PssCountry
 * @Description: 国家信息 实体类
 * @author hao
 * @date 2016-8-24
 */

public class PssCountry {
	
	
    //gid       数据库字段: GID 
	private Double gid;
	
	
    //name       数据库字段: NAME 
	private String name;
	
	
    //enName       数据库字段: EN_NAME 
	private String enName;
	
	
    //incountry       数据库字段: INCOUNTRY 
	private String incountry;
	
	
    //esName       数据库字段: ES_NAME 
	private String esName;
	
	
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
	public void setEnName(String enName) {
		this.enName = enName== null ? null : enName.trim();
	}
	
	public String getEnName() {
		return this.enName;
	}
	public void setIncountry(String incountry) {
		this.incountry = incountry== null ? null : incountry.trim();
	}
	
	public String getIncountry() {
		return this.incountry;
	}
	public void setEsName(String esName) {
		this.esName = esName== null ? null : esName.trim();
	}
	
	public String getEsName() {
		return this.esName;
	}


}
