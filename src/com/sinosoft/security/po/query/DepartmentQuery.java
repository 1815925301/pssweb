package com.sinosoft.security.po.query;

import com.sinosoft.po.query.BasePaginationQuery;


/**
 * @Package com.sinosoft.security.po.query
 * @ClassName: OrganizationQuery
 * @Description: 查询部门信息 Query类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-16 下午11:21:37
 */
public class DepartmentQuery extends BasePaginationQuery {

	private Long id; // 部门id
	private String deptName; // 部门名称
	private Long pId; //父ID
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}

	
}
