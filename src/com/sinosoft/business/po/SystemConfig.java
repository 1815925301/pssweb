package com.sinosoft.business.po;

import java.io.Serializable;

public class SystemConfig implements Serializable, Cloneable {
	private Long configid;
	private String configkey;
	private String configvalue;
	private String configdes;
	private String createtime;
	private String imagelocation;
	
	private String btime;
	private String etime;
	private Long configtype;
	
	public Long getConfigid() {
		return configid;
	}
	public void setConfigid(Long configid) {
		this.configid = configid;
	}

	public String getConfigkey() {
		return configkey;
	}
	public void setConfigkey(String configkey) {
		this.configkey = configkey;
	}
	
	public String getConfigvalue() {
		return configvalue;
	}
	public void setConfigvalue(String configvalue) {
		this.configvalue = configvalue;
	}
	public String getConfigdes() {
		return configdes;
	}
	public void setConfigdes(String configdes) {
		this.configdes = configdes;
	}
	
	
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getBtime() {
		return btime;
	}
	public void setBtime(String btime) {
		this.btime = btime;
	}
	public String getEtime() {
		return etime;
	}
	public void setEtime(String etime) {
		this.etime = etime;
	}
	public Long getConfigtype() {
		return configtype;
	}
	public void setConfigtype(Long configtype) {
		this.configtype = configtype;
	}
	public String getImagelocation() {
		return imagelocation;
	}
	public void setImagelocation(String imagelocation) {
		this.imagelocation = imagelocation;
	}
	
}
