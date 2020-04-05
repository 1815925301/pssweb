package com.sinosoft.security.po;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package com.sinosoft.security.po
 * @ClassName: Resources
 * @Description: 系统资源信息 实体类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-3 下午06:23:46
 */
public class Resources implements Serializable, Cloneable, Comparable<Resources> {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;

	private Long id; // 资源id
	private Long pId; //机构父ID
	private String resourceName; // 资源名称
	private String name;     //资源名称--
	private String resourceUrl; // 访问资源的路径
	private Long moduleId; // 资源所属模块ID
	private Integer sortNum; // 展示顺序
	private Integer isRestricted; // 用户访问该页面是否受角色权限限制
	private Integer isMenu; // 是否能组装为页面中的菜单
	private String createTime;// 用户被创建的时间
	private Long createUserId; // 创建者的用户ID
	private String updateTime; // 修改信息的时间
	private Long updateUserId; // 修改者的用户ID
	private String note;// 备注
	private boolean isHaveAuth = false; //扩展字段，标识某角色是否有权访问,默认值为false--
	private String moduleUrl; // 扩展字段 访问资源所属模块的路径--
	private String moduleName;//模块名称--
	private List<Resources> resourceList = new ArrayList<Resources>(); // 资源信息
	private String icon;//图标样式
	
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<Resources> getResourceList() {
		return resourceList;
	}

	public void setResourceList(List<Resources> resourceList) {
		this.resourceList = resourceList;
	}

	public boolean isHaveAuth() {
		return isHaveAuth;
	}

	public void setHaveAuth(boolean isHaveAuth) {
		this.isHaveAuth = isHaveAuth;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
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

	public Integer getIsRestricted() {
		return isRestricted;
	}

	public void setIsRestricted(Integer isRestricted) {
		this.isRestricted = isRestricted;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
	@Override
	public int compareTo(Resources oterResources) {
		if (oterResources == null || oterResources.getSortNum() == null) {
			return 1;
		} 
		return this.sortNum.compareTo(oterResources.getSortNum());
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public void trim() {
//		if (this.deptName != null)
//			this.deptName = this.deptName.trim();
//		if (this.deptDuty != null)
//			this.deptDuty = this.deptDuty.trim();
	}


}
