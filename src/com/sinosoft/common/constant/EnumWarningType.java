package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumWarningType
 * @Description: 提醒类型 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-11-27 上午02:30:30
 */
public enum EnumWarningType {
	
	//提醒待办工作
	WARNING_WATING_HANDLE(1),
	
	//提醒活动举办
	WARNING_ACTIVITY(2);

	private Integer status;

	private EnumWarningType(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
