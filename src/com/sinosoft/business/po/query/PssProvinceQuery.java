
package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;
/**
 * @Package com.sinosoft.business.po.query
 * @ClassName: PssProvinceQuery
 * @Description: 查询省份信息 Query类
 * @author hao
 * @date 2016-8-24
 */

public class PssProvinceQuery  extends BasePaginationQuery {
	
	
	 //gid       数据库字段: GID 
	private Double gid;
	
	
	 //name       数据库字段: NAME 
	private String name;
	
	
	 //code      数据库字段: CODE 
		private Double code;
	    //incountryCodeCode       数据库字段: incountryCodeCODE 
		private Double incountryCode;
		
		
		//area      数据库字段: AREA 
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
	public void setName(String name) {
		this.name = name== null ? null : name.trim();
	}
	
	public String getName() {
		return this.name;
	}
	public void setincountryCode(Double incountryCode) {
		this.incountryCode = incountryCode== null ? null : incountryCode;
	}
	
	public Double getincountryCode() {
		return this.incountryCode;
	}

	public Double getCode() {
		return code;
	}

	public void setCode(Double code) {
		this.code = code;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}
	
	


}
