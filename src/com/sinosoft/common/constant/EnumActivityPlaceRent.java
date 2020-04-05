package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumActivityPlaceRent
 * @Description: TODO
 * @author zzq
 * @Version V1.0
 * @date 2013-11-24 上午03:50:49
 */
public enum EnumActivityPlaceRent {
	
	// 没有选择
	PLACE_NOT_SURE(-1),
	
	// 没有租用场地
	PLACE_NOT_RENT(0),
	
	// 租用了场地
	PLACE_IS_RENT(1);
	
	private Integer status;

	private EnumActivityPlaceRent(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
