package com.sinosoft.business.po.query;

import java.util.List;

import com.sinosoft.po.query.BasePaginationQuery;

/**
 * @Package com.sinosoft.business.po.query
 * @ClassName: City
 * @Description: 城市信息 查询类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-19 上午11:22:43
 */
public class CityQuery extends BasePaginationQuery {

	private Long id; // 车型id
	private String city; // 车型名称
	private Long provinceId; // 省份id
	private List<Long> provinceIdList; // 省份id集合

	public List<Long> getProvinceIdList() {
		return provinceIdList;
	}

	public void setProvinceIdList(List<Long> provinceIdList) {
		this.provinceIdList = provinceIdList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

}
