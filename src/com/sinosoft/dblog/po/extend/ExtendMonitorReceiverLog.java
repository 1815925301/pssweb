package com.sinosoft.dblog.po.extend;

import java.io.Serializable;

import com.sinosoft.dblog.po.MonitorReceiverLog;

public class ExtendMonitorReceiverLog extends MonitorReceiverLog implements Serializable, Cloneable{
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public ExtendMonitorReceiverLog(){
		super();
	}
	private String orgName;
//	private String invokeTimeString;
//	private String responseTimeString;
//	private String createTiemString;
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
