package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.business.po.query
 * @ClassName: ProvinceQuery
 * @Description: 省份信息 查询类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 上午11:18:08
 */
public class ProvinceQuery extends BasePaginationQuery {

	private Long id; // 车型id
	private String province; // 车型名称
	private Long areaId; // 区域id 对应机构表中的id

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

}
