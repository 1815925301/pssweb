package com.sinosoft.dblog.po.query;

import com.sinosoft.po.query.BasePaginationQuery;

/***
 *系统日志实体类
 * @author leo
 *
 */
public class SyslogQuery extends BasePaginationQuery{

	private Long logid;
	private String logtype;
	private String description;
	private String createtime;
	private String loglevel;
	
	private String stratime;
	private String endtime;
	
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
	public String getStratime() {
		return stratime;
	}
	public void setStratime(String stratime) {
		this.stratime = stratime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	
}
