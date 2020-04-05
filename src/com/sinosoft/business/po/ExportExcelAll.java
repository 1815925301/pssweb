package com.sinosoft.business.po;

import java.util.List;

public class ExportExcelAll {
	/**
	 * 数据集合
	 */
	private List<String[]> statisticList; 
	/**
	 * 标题
	 */
	private List<String[]> excelFieldTitleList;
	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * SHEET名字
	 */
	private String name;
	/**
	 * 判断是否有数据
	 */
	private boolean isNum;
	public List<String[]> getStatisticList() {
		return statisticList;
	}
	public void setStatisticList(List<String[]> statisticList) {
		this.statisticList = statisticList;
	}
	public List<String[]> getExcelFieldTitleList() {
		return excelFieldTitleList;
	}
	public void setExcelFieldTitleList(List<String[]> excelFieldTitleList) {
		this.excelFieldTitleList = excelFieldTitleList;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isNum() {
		return isNum;
	}
	public void setNum(boolean isNum) {
		this.isNum = isNum;
	}
	
	
}
