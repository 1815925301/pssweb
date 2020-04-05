package com.sinosoft.security.po;

import java.io.Serializable;

/**
 * @Package com.sinosoft.security.po
 * @ClassName: UserPassword
 * @Description: TODO
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-13 下午08:39:36
 */
public class UserPassword implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private String password; // 原密码
	private String newPassword; // 新密码
	private String confirmNewPassword; // 新密码确认

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	
	/**
	 * 去掉首尾空格
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-13 下午09:15:10
	 * @version V1.0
	 */
	public void trim() {
		if (this.password != null)
			this.password = this.password.trim();
		
		if (this.newPassword != null)
			this.newPassword = this.newPassword.trim();
		
		if (this.confirmNewPassword != null)
			this.confirmNewPassword = this.confirmNewPassword.trim();
	}

}
