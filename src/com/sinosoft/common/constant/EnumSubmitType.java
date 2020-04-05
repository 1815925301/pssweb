package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumSubmitType
 * @Description: 活动信息提交类型 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-11-10 下午07:19:34
 */
public enum EnumSubmitType {
	
	//活动计划提交到审核
	ACTIVITY_SUBMIT(1),
	
	//活动计划保存 但不提交审核
	ACTIVITY_SAVE(2);

	private Integer status;

	private EnumSubmitType(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
