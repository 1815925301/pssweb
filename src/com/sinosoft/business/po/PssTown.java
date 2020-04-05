

package com.sinosoft.business.po;


public class PssTown {
	
	
    //gid       数据库字段: GID 
	private Double gid;
	
	
    //cntCntyC       数据库字段: CNT_CNTY_C 
	private Double cntCntyC;
	
	
    //name       数据库字段: NAME 
	private String name;
	
	
    //inCity       数据库字段: INCity 
	private String inCity;
	
	
    //inProvince       数据库字段: INProvince 
	private String inProvince;
	
	
    //code       数据库字段: CODE 
	private Double code;
	
	
    //cityCode       数据库字段: cityCode 
	private Double cityCode;
	
	
    //provinceCode       数据库字段: provinceCode 
	private Double provinceCode;
	
	
    //area       数据库字段: AREA 
	private Double area;
	
	
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
	public void setInCity(String inCity) {
		this.inCity = inCity== null ? null : inCity.trim();
	}
	
	public String getInCity() {
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
	
	public Double getCityCode() {
		return this.cityCode;
	}
	public void setProvinceCode(Double provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public Double getProvinceCode() {
		return this.provinceCode;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	
	public Double getArea() {
		return this.area;
	}


}
