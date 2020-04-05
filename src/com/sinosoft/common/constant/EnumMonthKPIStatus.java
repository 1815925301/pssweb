package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnmuMonthKPIStatus
 * @Description: 月度KPI完成情况 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-10-6 下午04:53:40
 */
public enum EnumMonthKPIStatus {
	
	// 月度KPI待建立 0
	MONTH_KPI_WATING_BUILD(0),
	
	// 月度KPI待总结 2
	MONTH_KPI_WATING_SUMUP(2),

	// 月度KPI已完成 1
	MONTH_KPI_COMPLETE(1);

	private Integer status;

	private EnumMonthKPIStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
