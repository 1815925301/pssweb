package com.sinosoft.security.po.extend;

import java.io.Serializable;

import com.sinosoft.security.po.Resources;

/**
 * @Package com.sinosoft.security.po.extend
 * @ClassName: ExtendsOrganization
 * @Description: 系统部门信息 扩展实体类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-18 上午02:24:40
 */
public class ExtendResources extends Resources implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public ExtendResources(){
		super();
	}

	private String createUserName; // 创建者的用户名称
	private String updateUserName; // 修改者的用户名称

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
