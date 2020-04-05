package com.sinosoft.business.po.extend;

import java.io.Serializable;

import com.sinosoft.business.po.City;

/**
 * @Package com.sinosoft.business.po.extend
 * @ClassName: ExtendCity
 * @Description: 城市信息 扩展实体类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 上午11:22:13
 */
public class ExtendCity extends City implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public ExtendCity() {
		super();
	}

	private String province; // 省份名称
	private Long areaId; // 区域id
	private String areaName; // 区域名称
	private String createUserName; // 创建者的用户名称
	private String updateUserName; // 修改者的用户名称

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
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
