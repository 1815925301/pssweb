package com.sinosoft.po;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sinosoft.base.po.CheckErrorDto;


/**
 * 通用数据对象
 */
@XmlRootElement(name ="Msg")
public class Msg {
	
	/** 数据对象id 对应表中主键*/
	private String objectID = "";
	
	/** 操作是否成功 {true false}*/
	private String success  = "";
	
	/** 成功或失败提示信息 */
	private String info = "";
	
	/** 错误的信息集合 */
	private List<CheckErrorDto> errorData;
	
	public List<CheckErrorDto> getErrorData() {
		return errorData;
	}


	public void setErrorData(List<CheckErrorDto> errorData) {
		this.errorData = errorData;
	}


	public String getObjectID() {
		return objectID;
	}
	

	public void setObjectID(String objectID) {
		this.objectID = objectID;
	}
	

	

	public String getSuccess() {
		return success;
	}


	public void setSuccess(String success) {
		this.success = success;
	}


	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
}
