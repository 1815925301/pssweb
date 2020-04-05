package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumAuditType
 * @Description: 活动审批类型 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-11-17 上午02:43:25
 */
public enum EnumAuditType {
	
	// 新活动审批 1
	AUDIT_NEW_ACTIVITY(1),
	
	// 修改活动信息需求审批 2
	AUDIT_CHANGE_ACTIVITY(2),
	
	// 活动总结审批 3
	AUDIT_SUMUP_ACTIVITY(3),
	
	// 修改活动信息后的审批4
	AUDIT_OLD_ACTIVITY(4),
	
	//删除活动信息的审批
	AUDIT_DELETE_ACTIVITY(5);

	private Integer status;

	private EnumAuditType(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
