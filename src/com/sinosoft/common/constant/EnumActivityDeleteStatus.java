package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnmuActivityDeleteStatus
 * @Description: 活动信息删除状态  枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2014-1-5 下午06:30:36
 */
public enum EnumActivityDeleteStatus {
	// 活动已删除
	ACTIVITY_DELETE_STATUS(1),
	
	// 活动未删除
	ACTIVITY_NOT_DELETE_STATUS(0);

	private Integer status;

	private EnumActivityDeleteStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
}
