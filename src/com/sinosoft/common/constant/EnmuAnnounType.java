package com.sinosoft.common.constant;

/**
 * 讨论
* @ClassName: EnmuAnnounType 
* @Description: TODO
* @author zzq
* @date 2014年12月2日 下午3:00:49 
*
 */
public enum EnmuAnnounType {
	
	// dashboard 合规
	ANNOUNTYPE_ONE(1),
	
	//dashboard 竞争力
	ANNOUNTYPE_TWO(2),
	
	// 在线报告 合规
	ANNOUNTYPE_THREE(3),
	
	// 在线报告 竞争力
	ANNOUNTYPE_FOUR(4);

	private Integer status;

	private EnmuAnnounType(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
