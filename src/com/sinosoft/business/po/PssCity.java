

package com.sinosoft.business.po;
/**
 * @Package com.sinosoft.business.po
 * @ClassName: PssCity
 * @Description: 城市信息 实体类
 * @author hao
 * @date 2016-8-24
 */

public class PssCity {
	
	
    //name       数据库字段: NAME 
	private String name;
	
	
    //code       数据库字段: CODE
	private Double code;
	
	//provinceCode       数据库字段: PROVINCECODE
		private Double provinceCode;
		
    //inprovince       数据库字段: INPROVINCE 
	private String inprovince;
	
	
    //gid       数据库字段: GID 
	private Double gid;
	
	//area       数据库字段: AREA
		private Double area;
	
	
	public void setName(String name) {
		this.name = name== null ? null : name.trim();
	}
	
	public String getName() {
		return this.name;
	}
	public void setInprovince(String inprovince) {
		this.inprovince = inprovince== null ? null : inprovince.trim();
	}
	
	public String getInprovince() {
		return this.inprovince;
	}
	public void setGid(Double gid) {
		this.gid = gid;
	}
	
	public Double getGid() {
		return this.gid;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Double provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Double getCode() {
		return code;
	}

	public void setCode(Double code) {
		this.code = code;
	}


}

