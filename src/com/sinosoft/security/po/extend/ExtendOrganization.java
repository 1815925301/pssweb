package com.sinosoft.security.po.extend;

import java.io.Serializable;

import com.sinosoft.security.po.Organization;

/**
 * @Package com.sinosoft.security.po.extend
 * @ClassName: ExtendsOrganization
 * @Description: 系统机构信息 扩展实体类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-18 上午02:24:40
 */
public class ExtendOrganization extends Organization implements Serializable, Cloneable {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 1L;
	
	public ExtendOrganization(){
		super();
	}

	private String orgType;// 机构类型名称
	private Long id; 
   private Long pid; //父节点
	
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
