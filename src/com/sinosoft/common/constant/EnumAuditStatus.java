package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumAuditStatus
 * @Description: 活动审批的状态 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-11-17 上午02:51:35
 */
public enum EnumAuditStatus {
	
	// 待审批 0
	AUDIT_WATING(0),
	
	// 已审批 1
	AUDIT_COMPLETE(1),
	
	// 无需审批，作废 2
	AUDIT_NOT_NEED(2);

	private Integer status;

	private EnumAuditStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
