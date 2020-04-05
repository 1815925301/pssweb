package com.sinosoft.base.po;

import java.io.Serializable;

/**
 * @Package com.sinosoft.base.po
 * @ClassName: CheckErrorDto
 * @Description: Ajax返回到前台的错误信息封装实体类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-11 上午10:16:10
 */
public class CheckErrorDto implements Serializable, Cloneable {
	
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	private String key;
	private String value;
	
	public CheckErrorDto (String key, String value){
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
