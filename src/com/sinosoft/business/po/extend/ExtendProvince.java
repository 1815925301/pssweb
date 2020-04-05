package com.sinosoft.business.po.extend;

import java.io.Serializable;

import com.sinosoft.business.po.Province;

/**
 * @Package com.sinosoft.business.po.extend
 * @ClassName: ExtendProvince
 * @Description: 省份信息 扩展实体类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 上午11:21:27
 */
public class ExtendProvince extends Province implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public ExtendProvince() {
		super();
	}

	private String areaName; // 区域名称
	private String createUserName; // 创建者的用户名称
	private String updateUserName; // 修改者的用户名称

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
