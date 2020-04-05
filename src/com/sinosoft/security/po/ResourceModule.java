package com.sinosoft.security.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.sinosoft.security.po
 * @ClassName: ResourceModule
 * @Description: 系统资源模块信息 实体类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-3 下午06:28:28
 */
public class ResourceModule implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id; // 资源模块id
	private String moduleName; // 模块名称
	private String name;     //资源名称
	private Integer sortNum; //展示顺序
	private Long pId; // 当前模块所属父模块的id
	private String createTime;// 用户被创建的时间
	private Long createUserId; // 创建者的用户ID
	private String updateTime; // 修改信息的时间
	private Long updateUserId; // 修改者的用户ID
	private String note;// 备注
	private String moduleStyle;// STYLE
	private String moduleUrl;// URL	

	private List<Resources> resourceList = new ArrayList<Resources>(); // 模块下的资源信息
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}


	public String getModuleStyle() {
		return moduleStyle;
	}

	public void setModuleStyle(String ModuleStyle) {
		this.moduleStyle = ModuleStyle;
	}
	
	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String ModuleUrl) {
		this.moduleUrl = ModuleUrl;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Resources> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resources> resourceList) {
		this.resourceList = resourceList;
	}
	
	public void addResource(Resources resources) {
		this.resourceList.add(resources);
	}

}
