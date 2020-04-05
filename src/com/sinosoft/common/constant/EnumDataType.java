package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumDataIsValid
 * @Description: 使用批量导入 时 被导入的信息的类型 两种：计划 与 总结
 * @author zzq
 * @Version V1.0
 * @date 2014-01-14 上午03:21:53
 */
public enum EnumDataType {
	
	// 信息是有效的
	DATA_TYPE_IS_PLAN(1),
	
	// 信息存在错误
	DATA_TYPE_IS_SUMUP(2);

	private Integer status;

	private EnumDataType(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
