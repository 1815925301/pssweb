package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumStatisModule
 * @Description: 统计模块常量
 * @author zzq
 * @Version V1.0
 * @date 2013-12-5 下午09:21:17
 */
public enum EnumStatisModule {

	// 总计概览
	OVERVIEW_STATIS("overviewStatis"),
	
	// 订单统计
	ORDER_STATIS("orderStatis"),
	
	// 信息留存统计
	INFO_RETAINED_STATIS("infoRetainedStatis"),
	
	// 完成率统计
	COMPLETE_RATE_STATIS("completeRateStatis"),
	
	// 成本分析
	COST_STATIS("costStatis"),
	
	// 广告统计
	ADS_STATIS("adsStatis"),
	
	// 媒体统计
	MEDIUM_STATIS("mediumStatis");

	private String moduleName;

	private EnumStatisModule(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleName() {
		return this.moduleName;
	}

}
