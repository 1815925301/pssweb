package com.sinosoft.security.po;

import java.io.Serializable;

/**
 * @Package com.sinosoft.security.po
 * @ClassName: Organization
 * @Description: 系统部门信息 实体类
 * @author 赵敏
 * @Version V1.0
 * @date 2013-10-3 下午06:26:40
 */
public class Department implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id; // 部门id
	private Long pId; //部门父ID
	private String deptName; // 部门名称
	private String name;     //机构名称
	private String deptDuty;   //部门职责
	private String createTime;// 用户被创建的时间
	private Long createUserId; // 创建者的用户ID
	private String updateTime; // 修改信息的时间
	private Long updateUserId; // 修改者的用户ID
	private Long orgId;//
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getpId() {
		return pId;
	}


	public void setpId(Long pId) {
		this.pId = pId;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDeptDuty() {
		return deptDuty;
	}


	public void setDeptDuty(String deptDuty) {
		this.deptDuty = deptDuty;
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


	public void trim() {
		if (this.deptName != null)
			this.deptName = this.deptName.trim();
		if (this.deptDuty != null)
			this.deptDuty = this.deptDuty.trim();
	}

}
