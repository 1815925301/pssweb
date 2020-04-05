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
public class ResourcesQuery extends BasePaginationQuery {

	private Long id;// 用户ID
	private Long pId; // 父Id
	private String resourceName;// 资源名称
	private String resourceUrl;// 资源URL
	private Long moduleId; // 模块id
	private Integer isRestricted; // 用户访问是否需判断用户所属角色拥有访问此资源的权限
	private Integer isMenu; // 是否用于生成菜单
	

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

}
