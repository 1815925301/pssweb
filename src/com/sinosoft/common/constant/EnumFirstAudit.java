package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumFirstAudit
 * @Description: 活动一审的标识符 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-11-22 上午10:28:52
 */
public enum EnumFirstAudit {
	
	// 总部执行一审
	AUDIT_BY_HEAD_QUARTERS(1),
	
	// 区域执行一审
	AUDIT_BY_AREA(2);
	
	private Integer status;

	private EnumFirstAudit(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
