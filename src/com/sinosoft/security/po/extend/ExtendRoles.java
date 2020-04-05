package com.sinosoft.security.po.extend;

import java.io.Serializable;

import com.sinosoft.security.po.Roles;

/**
 * @Package com.sinosoft.security.po.extend
 * @ClassName: ExtendRoles
 * @Description: 系统角色 扩展实体类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-26 下午02:56:27
 */
public class ExtendRoles extends Roles implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private String createUserName; // 创建者的用户名称
	private String updateUserName; // 修改者的用户名称
	private String siteNmae;
	
	public String getSiteNmae() {
		return siteNmae;
	}

	public void setSiteNmae(String siteNmae) {
		this.siteNmae = siteNmae;
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

}
