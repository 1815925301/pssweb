package com.sinosoft.dblog.po;

import java.sql.Timestamp;

/**
 * @Package com.sinosoft.dblog.po
 * @ClassName: OperationLog
 * @Description: 操作日志记录实体类
 * @author zzq
 * @Version V1.0
 * @Time 2013-9-14 下午09:24:50
 */
public class OperationLog implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private Long id; // Pk

	private String innerSerialId; // 流水号，一般填写内部流水号

	private String userId; // 用户ID

	private String roleIds; // 用户所属角色ID

	private Integer orgId; // 用户所属机构ID

	private String operationType; // 操作类型，自己定义；可以填入本次操作执行业务的名称

	private String operationResult; // 操作结果

	private Timestamp operationTime; // 操作时间

	private Timestamp createTime; // 创建时间 ，数据库端加入，不用关心

	private String remark; // 描述

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInnerSerialId() {
		return innerSerialId;
	}

	public void setInnerSerialId(String innerSerialId) {
		this.innerSerialId = innerSerialId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationResult() {
		return operationResult;
	}

	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}

	public Timestamp getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Timestamp operationTime) {
		this.operationTime = operationTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "OperationLog [ innerSerialId=" + innerSerialId + ", userId=" + userId
				+ ", roleIds=" + roleIds + ", orgId=" + orgId
				+ ", operationType=" + operationType + ", operationResult="
				+ operationResult + ", operationTime=" + operationTime
				+ ", remark=" + remark + "]";
	}
}
