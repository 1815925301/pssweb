package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumSecondAudit
 * @Description: 活动二审的标识符 枚举类型   若一级审批者不是总部，此时是否需要总部二级审批
 * @author zzq
 * @Version V1.0
 * @date 2013-11-22 上午10:18:40
 */
public enum EnumSecondAudit {
	
	// 不需要二级审批
	AUDIT_NOT_NEED(0),
	
	// 机构类型是区域
	AUDIT_NEED(1),
	
	// 在活动起始时间或者场地修改后需要总部二级审批
	AUDIT_INFO_CHANGE_NEED(2);
	
	private Integer status;

	private EnumSecondAudit(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
