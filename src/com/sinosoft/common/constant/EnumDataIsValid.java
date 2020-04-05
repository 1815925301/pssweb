package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumDataIsValid
 * @Description: 使用批量导入 时 被导入的信息是否有效
 * @author zzq
 * @Version V1.0
 * @date 2013-12-26 上午03:21:53
 */
public enum EnumDataIsValid {
	
	// 信息是有效的
	DATA_IS_VALID(1),
	
	// 信息存在错误
	DATA_IS_NOT_VALID(0);

	private Integer status;

	private EnumDataIsValid(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
