package com.sinosoft.common.constant;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: EnumIsLock
 * @Description: 系统用户是否被锁定 枚举类型
 * @author zzq
 * @Version V1.0
 * @date 2013-10-6 下午04:27:58
 */
public enum EnumIsLock {

	// 未锁定 0
	IS_LOCK_NO(0),

	// 已锁定 1
	IS_LOCK_YES(1);

	private Integer status;

	private EnumIsLock(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return this.status;
	}

}
