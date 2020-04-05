

package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;
/**
 * @Package com.sinosoft.business.po.query
 * @ClassName: PssCityQuery
 * @Description: 查询城市信息 Query类
 * @author hao
 * @date 2016-8-24
 */




public class PssCityQuery  extends BasePaginationQuery {
	
	
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
	
	private String sqlWhere;
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
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

	public Double getCode() {
		return code;
	}

	public void setCode(Double code) {
		this.code = code;
	}

	public Double getProvinceCode() {
		return provinceCode;
	}

	public void setProcinceCode(Double provinceCode) {
		this.provinceCode = provinceCode;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}


}
