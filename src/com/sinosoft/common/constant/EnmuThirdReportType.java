package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnmuThirdReportType
 * @Description: 第三方监测报告的类型 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2014-1-25 上午12:07:53
 */
public enum EnmuThirdReportType {
	
	// 周度分站报告 1
	WEEK_REPORT(1),
	
	// 月度分站报告 2
	MONTH_REPORT(2);

	private Integer status;

	private EnmuThirdReportType(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
