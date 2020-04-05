package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumKpiMonthStatus
 * @Description: TODO
 * @author zzq
 * @Version V1.0
 * @date 2013-4-1 上午09:56:38
 */
public enum EnumKpiMonthStatus {
	
	// 已提交 待总结
	KPI_WATING_SUMUP(1),

	// 结束
	KPI_COMPLETE(2);

	private Integer status;

	private EnumKpiMonthStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
