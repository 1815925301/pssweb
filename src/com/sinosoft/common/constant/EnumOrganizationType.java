package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumOrganizationType
 * @Description: 机构类型 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-10-16 下午11:07:43
 */
public enum EnumOrganizationType {
	
	// 机构类型是总部
	ORGANIZATION_IS_HEAD_QUARTERS(0),
	
	// 机构类型是区域
	ORGANIZATION_IS_AREA(1),
	
	// 机构类型是第三方
	ORGANIZATION_IS_THIRD_PARTY(2),
	
	// 机构类型是其他
	ORGANIZATION_IS_OTHER(3);

	private Integer status;

	private EnumOrganizationType(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
