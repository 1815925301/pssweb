package com.sinosoft.security.po.query;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.security.po.query
 * @ClassName: OrganizationQuery
 * @Description: 查询机构信息 Query类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-16 下午11:21:37
 */
public class OrganizationQuery extends BasePaginationQuery {

	private Long id; // 机构id
	private String orgName; // 机构名称
	private Integer type; // 机构类型
	private Integer pId;
	private Integer orgNum;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public Integer getOrgNum() {
		return orgNum;
	}
	public void setOrgNum(Integer orgNum) {
		this.orgNum = orgNum;
	}
	
	
}
