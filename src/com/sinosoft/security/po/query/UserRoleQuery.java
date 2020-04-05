package com.sinosoft.security.po.query;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.security.po.query
 * @ClassName: UserRoleQuery
 * @Description: 查询用户角色关联信息 Query类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-27 下午03:08:04
 */
public class UserRoleQuery extends BasePaginationQuery {

	private Long id;
	private Long userId; // 用户id
	private Long roleId; // 角色id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
