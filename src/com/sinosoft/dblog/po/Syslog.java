package com.sinosoft.dblog.po;

/***
 *系统日志实体类
 * @author leo
 *
 */
public class Syslog {

	private Long logid;
	private String logtype;
	private String description;
	private String createtime;
	private String loglevel;
	public Long getLogid() {
		return logid;
	}
	public void setLogid(Long logid) {
		this.logid = logid;
	}
	public String getLogtype() {
		return logtype;
	}
	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getLoglevel() {
		return loglevel;
	}
	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}
	
	
}
