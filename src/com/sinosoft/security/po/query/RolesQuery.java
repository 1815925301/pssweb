package com.sinosoft.security.po.query;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.security.po.query
 * @ClassName: RolesQuery
 * @Description: 查询角色信息 Query类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-26 下午02:56:43
 */
public class RolesQuery extends BasePaginationQuery {

	private Long id;// 用户ID
	private String roleName;// 角色名称
	private Long siteid;
	
	public Long getSiteid() {
		return siteid;
	}

	public void setSiteid(Long siteid) {
		this.siteid = siteid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
