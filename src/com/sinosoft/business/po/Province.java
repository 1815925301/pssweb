package com.sinosoft.business.po;

import java.io.Serializable;

/**
 * @Package com.sinosoft.business.po
 * @ClassName: Province
 * @Description: 省份信息 实体类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 上午11:18:08
 */
public class Province implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Long id; // 车型id
	private String province; // 车型名称
	private Long areaId; // 区域id 对应机构表中的id
	private String createTime;// 车型被创建的时间
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
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
	
	public void trim() {
		if (this.province != null)
			this.province = this.province.trim();
		if (this.note != null)
			this.note = this.note.trim();
	}

}
