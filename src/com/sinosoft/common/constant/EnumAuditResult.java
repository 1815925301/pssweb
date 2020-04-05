package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumAuditResult
 * @Description: 活动审批的结果 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-11-18 上午01:38:59
 */
public enum EnumAuditResult {
	
	// 审批未通过 0
	AUDIT_NOT_PASS(0),
	
	// 审批通过 1
	AUDIT_PASS(1);
	
	private Integer status;

	private EnumAuditResult(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
