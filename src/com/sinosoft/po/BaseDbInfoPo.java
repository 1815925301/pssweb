package com.sinosoft.po;

import java.io.Serializable;


/**
 * 实体类的父类
 * 包含用户所属库
* @ClassName: BasePo 
* @Description: TODO
* @author zzq
* @mail zzq1229@126.com 
* @date 2014年6月12日 下午3:15:06 
*
 */
public class BaseDbInfoPo implements Serializable, Cloneable {
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	/**
	 * 当前用户所选数据库
	 */
	private String dbInfo;

	public String getDbInfo() {
		return dbInfo;
	}

	public void setDbInfo(String dbInfo) {
		this.dbInfo = dbInfo;
	}
}
