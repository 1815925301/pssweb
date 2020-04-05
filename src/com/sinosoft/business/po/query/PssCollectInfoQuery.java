/*
 * Powered By 尹力
 * Since 2015 - 2016-53-18
 */

package com.sinosoft.business.po.query;


import com.sinosoft.po.query.BasePaginationQuery;


public class PssCollectInfoQuery  extends BasePaginationQuery {
	
	
	 //id       数据库字段: ID 
	private Double id;
	
	
	 //采集编号       数据库字段: TASKID 
	private String taskid;
	
	
	 //操作员名称       数据库字段: OPERATORNAME 
	private java.lang.String operatorname;
	
	
	 //任务优先级       数据库字段: PRIORITY 
	private Double priority;
	
	
	 //用户id       数据库字段: USERNAME 
	private java.lang.String username;
	
	
	 //卫星代号       数据库字段: SATELLITEID 
	private java.lang.String satelliteid;
	
	
	 //传感器标识       数据库字段: SENSORID 
	private java.lang.String sensorid;
	
	
	 //实施采集最早时间       数据库字段: BEGINTIME 
	private java.util.Date begintime;
	
	
	 //实施采集最晚时间       数据库字段: ENDTIME 
	private java.util.Date endtime;
	
	
	 //左上经度       数据库字段: UPPERLEFTLONG 
	private Double upperleftlong;
	
	
	 //左上纬度       数据库字段: UPPERLEFTLAT 
	private Double upperleftlat;
	
	
	 //右上经度       数据库字段: UPPERRIGHTLONG 
	private Double upperrightlong;
	
	
	 //右上纬度       数据库字段: UPPERRIGHTLAT 
	private Double upperrightlat;
	
	
	 //左下经度       数据库字段: LOWERLEFTLONG 
	private Double lowerleftlong;
	
	
	 //左下纬度       数据库字段: LOWERLEFTLAT 
	private Double lowerleftlat;
	
	
	 //右下经度       数据库字段: LOWERRIGHTLONG 
	private Double lowerrightlong;
	
	
	 //右下纬度       数据库字段: LOWERRIGHTLAT 
	private Double lowerrightlat;
	
	
	 //????       数据库字段: PRODUCTLEVEL 
	private java.lang.String productlevel;
	
	
	 //数据格式       数据库字段: POLARIZEALOGRITHM 
	private java.lang.String polarizealogrithm;
	
	
	 //质量评价结果       数据库字段: CLOUDCOVER 
	private Double cloudcover;
	
	
	 //价格       数据库字段: PRICE 
	private Double price;
	
	
	 //备注       数据库字段: REMARK 
	private java.lang.String remark;
	
	
	 //??????       数据库字段: FINISHDOWNLOADIF 
	private Double finishdownloadif;
	
	
	 //??????       数据库字段: FINISHDOWNLOADTIME 
	private java.util.Date finishdownloadtime;
	
	
	 //?????  ??????? 1,?? 2,?? 3,?? 4,??? 5???6       数据库字段: STATUS 
	private java.lang.String status;
	
	
	 //?????       数据库字段: STATIONID 
	private java.lang.String stationid;
	
	
	 //??????       数据库字段: REASON 
	private java.lang.String reason;
	
	
	 //????       数据库字段: SUBDATE 
	private java.util.Date subdate;
	
	
	 //???????????-??       数据库字段: AREACENTERLAT 
	private Double areacenterlat;
	
	
	 //???????????-??       数据库字段: AREACENTERLONG 
	private Double areacenterlong;
	
	
	 //????(0-9) default?6       数据库字段: AVERAGECLOUD 
	private Double averagecloud;
	
	
	 //?????       数据库字段: SIDEANGLE 
	private Double sideangle;
	
	
	 //????? (1-5)       数据库字段: USERTYPE 
	private Double usertype;
	
	
	 //????  ???0  ???1       数据库字段: URGENCYLEVEL 
	private Double urgencylevel;
	
	
	 //????       数据库字段: ISSEND 
	private Double issend;
	
	
	 //????       数据库字段: ISCANCEL 
	private Double iscancel;
	
	
	 //????       数据库字段: CREATETIME 
	private java.util.Date createtime;
	
	
	 //collectidstr       数据库字段: COLLECTIDSTR 
	private Double collectidstr;
	
	
	 //earthsurfacename       数据库字段: EARTHSURFACENAME 
	private java.lang.String earthsurfacename;
	
	
	 //surfacereflectivity       数据库字段: SURFACEREFLECTIVITY 
	private java.lang.String surfacereflectivity;
	
	
	 //(30-580)       数据库字段: OBSERVATIONINTERVAL 
	private java.lang.String observationinterval;
	
	
	 //gatherareaname       数据库字段: GATHERAREANAME 
	private java.lang.String gatherareaname;
	
	
	 //gatherareadescription       数据库字段: GATHERAREADESCRIPTION 
	private java.lang.String gatherareadescription;
	
	
	 //产品级别       数据库字段: INSTRUMENTMODE 
	private java.lang.String instrumentmode;
	
	//下单时间的起始时间	
		private java.lang.String createStarttime;
		//审核状态
		private Integer checkstate;
		//审核备注
		private String note;
		//是否故障订单
		private Integer isfault;
		//友好提示
		private String friendlyprompt;
		
		public Integer getCheckstate() {
			return checkstate;
		}
		public void setCheckstate(Integer checkstate) {
			this.checkstate = checkstate;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
		public Integer getIsfault() {
			return isfault;
		}
		public void setIsfault(Integer isfault) {
			this.isfault = isfault;
		}
		public String getFriendlyprompt() {
			return friendlyprompt;
		}
		public void setFriendlyprompt(String friendlyprompt) {
			this.friendlyprompt = friendlyprompt;
		}
		public java.lang.String getCreateStarttime() {
			return createStarttime;
		}

		public void setCreateStarttime(java.lang.String createStarttime) {
			this.createStarttime = createStarttime;
		}

		public java.lang.String getCreateEndtime() {
			return createEndtime;
		}

		public void setCreateEndtime(java.lang.String createEndtime) {
			this.createEndtime = createEndtime;
		}

		public java.lang.String getBeginStarttime() {
			return beginStarttime;
		}

		public void setBeginStarttime(java.lang.String beginStarttime) {
			this.beginStarttime = beginStarttime;
		}

		public java.lang.String getBeginEndtime() {
			return beginEndtime;
		}

		public void setBeginEndtime(java.lang.String beginEndtime) {
			this.beginEndtime = beginEndtime;
		}

		public java.lang.String getEndStarttime() {
			return endStarttime;
		}

		public void setEndStarttime(java.lang.String endStarttime) {
			this.endStarttime = endStarttime;
		}

		//下单时间的结束时间
		private java.lang.String createEndtime;
		//实施最早时间的起始时间
		private java.lang.String beginStarttime;
		//实施最早时间的结束时间	
		private java.lang.String beginEndtime;
		//实施最晚时间的起始时间	
		private java.lang.String endStarttime;
		//实施最晚时间的结束时间	

		private java.lang.String endEndtime;
	
	
	public java.lang.String getEndEndtime() {
			return endEndtime;
		}

		public void setEndEndtime(java.lang.String endEndtime) {
			this.endEndtime = endEndtime;
		}

	private String sqlWhere;
	
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
	public void setId(Double id) {
		this.id = id;
	}
	
	public Double getId() {
		return this.id;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	
	public String getTaskid() {
		return this.taskid;
	}
	public void setOperatorname(java.lang.String operatorname) {
		this.operatorname = operatorname== null ? null : operatorname.trim();
	}
	
	public java.lang.String getOperatorname() {
		return this.operatorname;
	}
	public void setPriority(Double priority) {
		this.priority = priority;
	}
	
	public Double getPriority() {
		return this.priority;
	}
	public void setUsername(java.lang.String username) {
		this.username = username== null ? null : username.trim();
	}
	
	public java.lang.String getUsername() {
		return this.username;
	}
	public void setSatelliteid(java.lang.String satelliteid) {
		this.satelliteid = satelliteid== null ? null : satelliteid.trim();
	}
	
	public java.lang.String getSatelliteid() {
		return this.satelliteid;
	}
	public void setSensorid(java.lang.String sensorid) {
		this.sensorid = sensorid== null ? null : sensorid.trim();
	}
	
	public java.lang.String getSensorid() {
		return this.sensorid;
	}

	
	public void setBegintime(java.util.Date begintime) {
		this.begintime = begintime;
	}
	
	public java.util.Date getBegintime() {
		return this.begintime;
	}

	
	public void setEndtime(java.util.Date endtime) {
		this.endtime = endtime;
	}
	
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	public void setUpperleftlong(Double upperleftlong) {
		this.upperleftlong = upperleftlong;
	}
	
	public Double getUpperleftlong() {
		return this.upperleftlong;
	}
	public void setUpperleftlat(Double upperleftlat) {
		this.upperleftlat = upperleftlat;
	}
	
	public Double getUpperleftlat() {
		return this.upperleftlat;
	}
	public void setUpperrightlong(Double upperrightlong) {
		this.upperrightlong = upperrightlong;
	}
	
	public Double getUpperrightlong() {
		return this.upperrightlong;
	}
	public void setUpperrightlat(Double upperrightlat) {
		this.upperrightlat = upperrightlat;
	}
	
	public Double getUpperrightlat() {
		return this.upperrightlat;
	}
	public void setLowerleftlong(Double lowerleftlong) {
		this.lowerleftlong = lowerleftlong;
	}
	
	public Double getLowerleftlong() {
		return this.lowerleftlong;
	}
	public void setLowerleftlat(Double lowerleftlat) {
		this.lowerleftlat = lowerleftlat;
	}
	
	public Double getLowerleftlat() {
		return this.lowerleftlat;
	}
	public void setLowerrightlong(Double lowerrightlong) {
		this.lowerrightlong = lowerrightlong;
	}
	
	public Double getLowerrightlong() {
		return this.lowerrightlong;
	}
	public void setLowerrightlat(Double lowerrightlat) {
		this.lowerrightlat = lowerrightlat;
	}
	
	public Double getLowerrightlat() {
		return this.lowerrightlat;
	}
	public void setProductlevel(java.lang.String productlevel) {
		this.productlevel = productlevel== null ? null : productlevel.trim();
	}
	
	public java.lang.String getProductlevel() {
		return this.productlevel;
	}
	public void setPolarizealogrithm(java.lang.String polarizealogrithm) {
		this.polarizealogrithm = polarizealogrithm== null ? null : polarizealogrithm.trim();
	}
	
	public java.lang.String getPolarizealogrithm() {
		return this.polarizealogrithm;
	}
	public void setCloudcover(Double cloudcover) {
		this.cloudcover = cloudcover;
	}
	
	public Double getCloudcover() {
		return this.cloudcover;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return this.price;
	}
	public void setRemark(java.lang.String remark) {
		this.remark = remark== null ? null : remark.trim();
	}
	
	public java.lang.String getRemark() {
		return this.remark;
	}
	public void setFinishdownloadif(Double finishdownloadif) {
		this.finishdownloadif = finishdownloadif;
	}
	
	public Double getFinishdownloadif() {
		return this.finishdownloadif;
	}

	public void setFinishdownloadtime(java.util.Date finishdownloadtime) {
		this.finishdownloadtime = finishdownloadtime;
	}
	
	public java.util.Date getFinishdownloadtime() {
		return this.finishdownloadtime;
	}
	public void setStatus(java.lang.String status) {
		this.status = status== null ? null : status.trim();
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setStationid(java.lang.String stationid) {
		this.stationid = stationid== null ? null : stationid.trim();
	}
	
	public java.lang.String getStationid() {
		return this.stationid;
	}
	public void setReason(java.lang.String reason) {
		this.reason = reason== null ? null : reason.trim();
	}
	
	public java.lang.String getReason() {
		return this.reason;
	}

	public void setSubdate(java.util.Date subdate) {
		this.subdate = subdate;
	}
	
	public java.util.Date getSubdate() {
		return this.subdate;
	}
	public void setAreacenterlat(Double areacenterlat) {
		this.areacenterlat = areacenterlat;
	}
	
	public Double getAreacenterlat() {
		return this.areacenterlat;
	}
	public void setAreacenterlong(Double areacenterlong) {
		this.areacenterlong = areacenterlong;
	}
	
	public Double getAreacenterlong() {
		return this.areacenterlong;
	}
	public void setAveragecloud(Double averagecloud) {
		this.averagecloud = averagecloud;
	}
	
	public Double getAveragecloud() {
		return this.averagecloud;
	}
	public void setSideangle(Double sideangle) {
		this.sideangle = sideangle;
	}
	
	public Double getSideangle() {
		return this.sideangle;
	}
	public void setUsertype(Double usertype) {
		this.usertype = usertype;
	}
	
	public Double getUsertype() {
		return this.usertype;
	}
	public void setUrgencylevel(Double urgencylevel) {
		this.urgencylevel = urgencylevel;
	}
	
	public Double getUrgencylevel() {
		return this.urgencylevel;
	}
	public void setIssend(Double issend) {
		this.issend = issend;
	}
	
	public Double getIssend() {
		return this.issend;
	}
	public void setIscancel(Double iscancel) {
		this.iscancel = iscancel;
	}
	
	public Double getIscancel() {
		return this.iscancel;
	}
	
	public void setCreatetime(java.util.Date createtime) {
		this.createtime = createtime;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	public void setCollectidstr(Double collectidstr) {
		this.collectidstr = collectidstr;
	}
	
	public Double getCollectidstr() {
		return this.collectidstr;
	}
	public void setEarthsurfacename(java.lang.String earthsurfacename) {
		this.earthsurfacename = earthsurfacename== null ? null : earthsurfacename.trim();
	}
	
	public java.lang.String getEarthsurfacename() {
		return this.earthsurfacename;
	}
	public void setSurfacereflectivity(java.lang.String surfacereflectivity) {
		this.surfacereflectivity = surfacereflectivity== null ? null : surfacereflectivity.trim();
	}
	
	public java.lang.String getSurfacereflectivity() {
		return this.surfacereflectivity;
	}
	public void setObservationinterval(java.lang.String observationinterval) {
		this.observationinterval = observationinterval== null ? null : observationinterval.trim();
	}
	
	public java.lang.String getObservationinterval() {
		return this.observationinterval;
	}
	public void setGatherareaname(java.lang.String gatherareaname) {
		this.gatherareaname = gatherareaname== null ? null : gatherareaname.trim();
	}
	
	public java.lang.String getGatherareaname() {
		return this.gatherareaname;
	}
	public void setGatherareadescription(java.lang.String gatherareadescription) {
		this.gatherareadescription = gatherareadescription== null ? null : gatherareadescription.trim();
	}
	
	public java.lang.String getGatherareadescription() {
		return this.gatherareadescription;
	}
	public void setInstrumentmode(java.lang.String instrumentmode) {
		this.instrumentmode = instrumentmode== null ? null : instrumentmode.trim();
	}
	
	public java.lang.String getInstrumentmode() {
		return this.instrumentmode;
	}


}
