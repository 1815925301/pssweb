package com.sinosoft.security.po.extend;

import java.io.Serializable;
import java.util.List;

import com.sinosoft.security.po.UserRole;
import com.sinosoft.security.po.Users;

/**
 * @Package com.sinosoft.security.po.extend
 * @ClassName: ExtendUsers
 * @Description: 系统用户 扩展实体类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-9 上午01:45:46
 */
public class ExtendUsers extends Users implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private String roleName; //角色名称
	private List<UserRole> userRoleList; //用户角色
	private String lockStr; // 用户状态
	private String orgName;// 用户所属机构名称
	private String createUserName; // 创建者的用户名称
	private String updateUserName; // 修改者的用户名称

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		
		this.roleName = roleName;
	}

	public List<UserRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<UserRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public String getLockStr() {
		return lockStr;
	}

	public void setLockStr(String lockStr) {
		this.lockStr = lockStr;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sinosoft.security.po.Users#trim()
	 */
	public void trim() {
		super.trim();
		if (this.lockStr != null)
			this.lockStr = this.lockStr.trim();
		if (this.orgName != null)
			this.orgName = this.orgName.trim();
		if (this.createUserName != null)
			this.createUserName = this.createUserName.trim();
		if (this.updateUserName != null)
			this.updateUserName = this.updateUserName.trim();
	}

}
