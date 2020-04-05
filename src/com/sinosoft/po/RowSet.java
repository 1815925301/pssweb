package com.sinosoft.po;

import java.util.ArrayList;
import java.util.List;

/**
 * 所有列表查询返回的数据对象，最终格式化成JSON
 * JSONObject.fromObject(rowSet).toString();
 */
public class RowSet {
	//private ArrayList<ColumnSet> rows = new ArrayList<ColumnSet>();
	/** 每行字段值*/
	private List<Object> rows = new ArrayList<Object>();

	/** 总记录数 */
	private int total;
	/** 当前页*/
	private int page;
	/** 查询出的记录数*/
	private int records;
	public List<Object> getRows() {
		return rows;
	}
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	
	
	
}