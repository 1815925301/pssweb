package com.sinosoft.business.po.query;
import java.math.BigDecimal;
import java.util.Date;
import com.sinosoft.po.query.BasePaginationQuery;

public class SystemConfigQuery extends BasePaginationQuery{
	private Long configid;
	private String configkey;
	private String configvalue;
	private String configdes;
	private String createtime;

	private String btime;
	private String etime;
	private String key;
	private BigDecimal configtype;
	
	
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public BigDecimal getConfigtype() {
		return configtype;
	}
	public void setConfigtype(BigDecimal configtype) {
		this.configtype = configtype;
	}
	
}
