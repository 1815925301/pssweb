package com.sinosoft.business.po.query;

import com.sinosoft.po.query.BasePaginationQuery;

public class ContentQuery extends BasePaginationQuery{
	//内容编号
	private Long contentid;
	//文章标题
	private String contenttitle;
	//文章内容
	private String content;
	//是否发布
	private Long isrelease;
	//文章类型
	private Long contenttype;
	//创建时间
	private String createtime;
	//图片信息
	private String image;
	
	//全文检索字段】
	private String fulltext;
	private String btime;
	private String etime;
	public Long getContentid() {
		return contentid;
	}
	public void setContentid(Long contentid) {
		this.contentid = contentid;
	}
	public String getContenttitle() {
		return contenttitle;
	}
	public void setContenttitle(String contenttitle) {
		this.contenttitle = contenttitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getIsrelease() {
		return isrelease;
	}
	public void setIsrelease(Long isrelease) {
		this.isrelease = isrelease;
	}
	public Long getContenttype() {
		return contenttype;
	}
	public void setContenttype(Long contenttype) {
		this.contenttype = contenttype;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public String getFulltext() {
		return fulltext;
	}
	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}
	
	
}
