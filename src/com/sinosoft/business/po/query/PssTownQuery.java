/*
 * Powered By 尹力
 * Since 2015 - 2016-58-26
 */

package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;





public class PssTownQuery  extends BasePaginationQuery {
	
	
	 //gid       数据库字段: GID 
	private Double gid;
	
	
	 //cntCntyC       数据库字段: CNT_CNTY_C 
	private Double cntCntyC;
	
	
	 //name       数据库字段: NAME 
	private String name;
	
	
	 //incity       数据库字段: INCITY 
	private String inCity;
	
	
	 //inProvince       数据库字段: INProvince 
	private String inProvince;
	
	
	 //code       数据库字段: CODE 
	private Double code;
	
	
	 //citycode       数据库字段: CITYCODE 
	private Double cityCode;
	
	
	 //Provincecode       数据库字段: ProvinceCODE 
	private Double provinceCode;
	
	
	 //area       数据库字段: AREA 
	private Double area;
	
	
	private String sqlWhere;
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
	public void setGid(Double gid) {
		this.gid = gid;
	}
	
	public Double getGid() {
		return this.gid;
	}
	public void setCntCntyC(Double cntCntyC) {
		this.cntCntyC = cntCntyC;
	}
	
	public Double getCntCntyC() {
		return this.cntCntyC;
	}
	public void setName(String name) {
		this.name = name== null ? null : name.trim();
	}
	
	public String getName() {
		return this.name;
	}
	public void setIncity(String incity) {
		this.inCity = incity== null ? null : incity.trim();
	}
	
	public String getIncity() {
		return this.inCity;
	}
	public void setInProvince(String inProvince) {
		this.inProvince = inProvince== null ? null : inProvince.trim();
	}
	
	public String getInProvince() {
		return this.inProvince;
	}
	public void setCode(Double code) {
		this.code = code;
	}
	
	public Double getCode() {
		return this.code;
	}
	public void setCityCode(Double cityCode) {
		this.cityCode = cityCode;
	}
	
	public Double getCitycode() {
		return this.cityCode;
	}
	public void setProvinceCode(Double provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public Double getProvincecode() {
		return this.provinceCode;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	
	public Double getArea() {
		return this.area;
	}


}
