package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumActivityStatus
 * @Description: 活动状态 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-10-6 下午04:36:17
 */
public enum EnumActivityStatus {
	
	// 活动计划待提交 2
	ACTIVITY_WATING_COMMIT(2),
	
	// 活动计划待审批 3
	ACTIVITY_WATING_AUDIT(3),
	
	// 活动待总结 4
	ACTIVITY_WATING_SUMUP(4),
	
	// 活动待总结审批 5
	ACTIVITY_WATING_SUMUP_AUDIT(5),
	
	// 活动计划审批未通过后变为待提交6
	ACTIVITY_AUDIT_REJECT_WATING_COMMIT(6),
	
	//活动总结审批未通过后变为待总结 7
	ACTIVITY_AUDIT_REJECT_WATING_SUMUP(7),
	
	// 活动计划申请修改提交后变为待审批 8
	ACTIVITY_APPLY_CHANGE_WATING_AUDIT(8),
	
	// 活动计划申请修改成功后变为待提交 9
	ACTIVITY_APPLY_CHANGE_TO_WATING_COMMIT(9),
	
	// 活动删除待审批
	ACTIVITY_WATING_DELETE_AUDIT(10),
	
	// 活动已删除
	ACTIVITY_ALREDY_DELETE(11),
	
	// 活动已完成 1
	ACTIVITY_COMPLETE(1),
	
	// 全部活动状态 0
	ACTIVITY_ALL_STATUS(0);

	private Integer status;

	private EnumActivityStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}
	
	public static String getNameByStatus(Integer status) {
		String name = "";
		if (status.compareTo( new Integer(1)) == 0) {
			name = "活动已完成";
		} else if (status.compareTo( new Integer(2)) == 0) {
			name = "活动计划待提交";
		} else if (status.compareTo( new Integer(3)) == 0) {
			name = "活动计划待审批";
		} else if (status.compareTo( new Integer(4)) == 0) {
			name = "活动待总结";
		} else if (status.compareTo( new Integer(5)) == 0) {
			name = "活动待总结审批";
		} else if (status.compareTo( new Integer(6)) == 0) {
			name = "活动计划审批未通过后变为待提交";
		} else if (status.compareTo( new Integer(7)) == 0) {
			name = "活动总结审批未通过后变为待总结";
		} else if (status.compareTo( new Integer(8)) == 0) {
			name = "活动计划申请修改提交后变为待审批";
		} else if (status.compareTo( new Integer(9)) == 0) {
			name = "活动计划申请修改成功后变为待提交";
		} else if (status.compareTo( new Integer(10)) == 0) {
			name = "活动删除待审批";
		}
		
		return name;
	}

}
