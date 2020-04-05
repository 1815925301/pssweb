package com.sinosoft.security.po;

import java.io.Serializable;

/**
 * @Package com.sinosoft.security.po
 * @ClassName: UserRole
 * @Description: 系统用户角色关联信息 实体类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-4 下午08:14:19
 */
public class UserRole implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id; // 主键
	private Long userId; // 用户id
	private Long roleId; // 角色id
	private String createTime;// 用户被创建的时间
	private Long createUserId; // 创建者的用户ID
	private String updateTime; // 修改信息的时间
	private Long updateUserId; // 修改者的用户ID
	private String note;// 备注

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
