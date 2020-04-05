package com.sinosoft.security.po.query;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.security.po.query
 * @ClassName: ResourcesQuery
 * @Description: 查询资源信息 Query类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-26 下午08:42:12
 */
public class ResourceModuleQuery extends BasePaginationQuery {
	
	private Long id; // 资源模块id
	private String moduleName; // 模块名称
	private Long pId; // 当前模块所属父模块的id
	private Long sortNum; // 排序
	private String createTime;// 用户被创建的时间
	private Long createUserId; // 创建者的用户ID
	private String updateTime; // 修改信息的时间
	private Long updateUserId; // 修改者的用户ID
	private String note;// 备注
	private String moduleStyle;// style
	private String moduleUrl;// url
	
	public String getModuleStyle() {
		return moduleStyle;
	}
	public void setModuleStyle(String moduleStyle) {
		this.moduleStyle = moduleStyle;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public Long getpId() {
		return pId;
	}
	public void setpId(Long pId) {
		this.pId = pId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public Long getSortNum() {
		return sortNum;
	}
	public void setSortNum(Long sortNum) {
		this.sortNum = sortNum;
	}

}
