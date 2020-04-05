package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumInfoModule
 * @Description: 活动信息模块代号 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-11-23 下午08:29:14
 */
public enum EnumActivityInfoModule {

	// 活动基本信息模块
	ACTIVITY_BASE_INFO(1),
	
	// 活动场地信息模块
	ACTIVITY_PLACE_INFO(2),
	
	// 活动KPI信息模块
	ACTIVITY_KPI_INFO(3),
	
	// 活动附件信息模块
	ACTIVITY_ATTACHMENT_INFO(4),

	// 活动审批信息模块
	ACTIVITY_AUDIT_INFO(5);

	private Integer status;

	private EnumActivityInfoModule(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
