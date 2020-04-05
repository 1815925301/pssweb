package com.sinosoft.po.query;

/**
 * @Package com.sinosoft.po.query
 * @ClassName: BasePaginationQuery
 * @Description: 查询时所用分页信息基类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-3 下午06:55:03
 */
public class BasePaginationQuery {

	private Integer page = 1; // 当前页码 默认为1

	private Integer pageSize = 8; // 每页显示记录数 默认为8

	private Integer startNum; // 查询的起始记录序号

	private String sortBy; // 排序SQL

	private String sortType = "2"; // 1升序 2降序

	public Integer getPage() {
		if (page <= 0) {
			page = 1;
		}
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		if (pageSize == null || pageSize == 0) {
			pageSize = 15;
		}
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStartNum() {
		startNum = 0;
		if (pageSize != null && page != null) {
			if (page <= 0) {
				page = 1;
			}
			startNum = (page - 1) * pageSize;
		}
		return startNum;
	}

	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

}
