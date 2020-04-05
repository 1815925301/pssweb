

package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;





public class PssProductpriceQuery  extends BasePaginationQuery {
	
	
	 //价格ID       数据库字段: ID 
	private Long id;
	
	
	 //产品分类（1:数据产品；2:专题产品）       数据库字段: PRODUCTCATE 
	private String producttype;
	
	
	 //产品级别       数据库字段: PRODUCTLEVEL 
	private java.lang.String productlevel;
	
	
	 //面积       数据库字段: PRODUCTAREA 
	private Long productarea;
	
	
	 //价格       数据库字段: PRICE 
	private Long price;
	
	
	 //版本号       数据库字段: VERSIONNUM 
	private java.lang.String versionnum;
	private String unit;
	
	
	private String sqlWhere;
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public void setProductlevel(java.lang.String productlevel) {
		this.productlevel = productlevel== null ? null : productlevel.trim();
	}
	
	public java.lang.String getProductlevel() {
		return this.productlevel;
	}
	public void setProductarea(Long productarea) {
		this.productarea = productarea;
	}
	
	public Long getProductarea() {
		return this.productarea;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Long getPrice() {
		return this.price;
	}
	public void setVersionnum(java.lang.String versionnum) {
		this.versionnum = versionnum== null ? null : versionnum.trim();
	}
	
	public java.lang.String getVersionnum() {
		return this.versionnum;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	

}
