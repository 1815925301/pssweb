package com.sinosoft.security.po.query;

import java.util.Date;

import com.sinosoft.po.query.BasePaginationQuery;


public class MolecularSourceQuery extends BasePaginationQuery implements Cloneable {

	private static final long serialVersionUID = 1L;

	private String whereStr;
	private String whereStrz;//添加的 市或者 县的编码条件 
	private int zonelevel;

	public int getZonelevel() {
		return zonelevel;
	}

	public void setZonelevel(int zonelevel) {
		this.zonelevel = zonelevel;
	}

	public String getWhereStrz() {
		return whereStrz;
	}

	public void setWhereStrz(String whereStrz) {
		this.whereStrz = whereStrz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String groupByStr;

	private String columns;

	private String columnName;// patterncode的列名

	private String patternCode;

	private String sourceType;

	private String countBySingleDay;

	private String orgIds;

	private String date;// 查询时间,如果不为null,则从该时间起,往前推3个月

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	public String getCountBySingleDay() {
		return countBySingleDay;
	}

	public void setCountBySingleDay(String countBySingleDay) {
		this.countBySingleDay = countBySingleDay;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getPatternCode() {
		return patternCode;
	}

	public void setPatternCode(String patternCode) {
		this.patternCode = patternCode;
	}

	public String getWhereStr() {
		return whereStr;
	}

	public void setWhereStr(String whereStr) {
		this.whereStr = whereStr;
	}

	public String getGroupByStr() {
		return groupByStr;
	}

	public void setGroupByStr(String groupByStr) {
		this.groupByStr = groupByStr;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public MolecularSourceQuery clone() {
		try {
			return (MolecularSourceQuery) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
}
