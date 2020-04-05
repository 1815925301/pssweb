package com.sinosoft.security.po;

import java.io.Serializable;

/**
 * @Package com.sinosoft.security.po
 * @ClassName: ResourcesSub
 * @Description: 系统子资源信息 实体类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-7 下午04:20:04
 */
public class ResourcesSub implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id; // 资源id
	private String subResourceName; // 子资源名称
	private String subResourceUrl; // 访问资源的路径
	private Long resourceId; // 资源所属模块ID
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

	public String getSubResourceName() {
		return subResourceName;
	}

	public void setSubResourceName(String subResourceName) {
		this.subResourceName = subResourceName;
	}

	public String getSubResourceUrl() {
		return subResourceUrl;
	}

	public void setSubResourceUrl(String subResourceUrl) {
		this.subResourceUrl = subResourceUrl;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
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
