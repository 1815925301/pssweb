/*
 * Powered By 尹力
 * Since 2015 - 2016-53-18
 */

package com.sinosoft.business.po;

import com.sinosoft.business.uilt.GetTaskId;


public class PssCollectInfo {
    //id       数据库字段: ID 
	private Long id;
    //采集编号       数据库字段: TASKID 
	private Long taskid;
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
	private java.lang.String begintime;
    //实施采集最晚时间       数据库字段: ENDTIME 
	private java.lang.String endtime;
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
	private Integer price;
    //备注       数据库字段: REMARK 
	private java.lang.String remark;
    //??????       数据库字段: FINISHDOWNLOADIF 
	private Double finishdownloadif;
    //??????       数据库字段: FINISHDOWNLOADTIME 
	private java.lang.String finishdownloadtime;
    //?????  ??????? 1,?? 2,?? 3,?? 4,??? 5???6       数据库字段: STATUS 
	private java.lang.String status;
    //?????       数据库字段: STATIONID 
	private java.lang.String stationid;
    //??????       数据库字段: REASON 
	private java.lang.String reason;
    //????       数据库字段: SUBDATE 
	private java.lang.String subdate;
    //???????????-??       数据库字段: AREACENTERLAT 
	private Double areacenterlat;
    //???????????-??       数据库字段: AREACENTERLONG 
	private Double areacenterlong;
    //????(0-9) default?6       数据库字段: AVERAGECLOUD 
	private Integer averagecloud;
    //?????       数据库字段: SIDEANGLE 
	private Integer sideangle;
    //????? (1-5)       数据库字段: USERTYPE 
	private Integer usertype;
    //????  ???0  ???1       数据库字段: URGENCYLEVEL 
	private Integer urgencylevel;
    //????       数据库字段: ISSEND 
	private Double issend;
    //????       数据库字段: ISCANCEL 
	private Double iscancel;
    //下单时间       数据库字段: CREATETIME 
	private java.lang.String createtime;
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
	//审核状态
	private Integer checkstate;
	//审核备注
	private String note;
	//是否故障订单
	private Integer isfault;
	//友好提示
	private String friendlyprompt;
	//面积
	private double area;
	private String unit;
	//是否填写付费信息
	private Integer ispay;
	private Integer checkispay;
	
	private String firstprinciple;//优先规划选项
	private Integer isrefund;//是否发生退款
	public Integer getIsrefund() {
		return isrefund;
	}
	public void setIsrefund(Integer isrefund) {
		this.isrefund = isrefund;
	}
	
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public Integer getUrgencylevel() {
		return urgencylevel;
	}
	public void setUrgencylevel(Integer urgencylevel) {
		this.urgencylevel = urgencylevel;
	}
	public String getFirstprinciple() {
		return firstprinciple;
	}
	public void setFirstprinciple(String firstprinciple) {
		this.firstprinciple = firstprinciple;
	}
	public Integer getCheckispay() {
		return checkispay;
	}
	public void setCheckispay(Integer checkispay) {
		this.checkispay = checkispay;
	}
	public Integer getIspay() {
		return ispay;
	}
	public void setIspay(Integer ispay) {
		this.ispay = ispay;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	
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
	//	CreateStarttime的get方法
	public java.lang.String getCreateStarttime() {
		return createStarttime;
	}
//  CreateStarttime的set方法
	public void setCreateStarttime(java.lang.String createStarttime) {
		this.createStarttime = createStarttime;
	}
//  CreateEndtime的get方法
	public java.lang.String getCreateEndtime() {
		return createEndtime;
	}
//	CreateEndtime的set方法
	public void setCreateEndtime(java.lang.String createEndtime) {
		this.createEndtime = createEndtime;
	}
//	BeginStarttime的get方法
	public java.lang.String getBeginStarttime() {
		return beginStarttime;
	}
//	BeginStarttime的set方法
	public void setBeginStarttime(java.lang.String beginStarttime) {
		this.beginStarttime = beginStarttime;
	}
//	BeginEndtime的get方法
	public java.lang.String getBeginEndtime() {
		return beginEndtime;
	}
//	BeginEndtime的set方法
	public void setBeginEndtime(java.lang.String beginEndtime) {
		this.beginEndtime = beginEndtime;
	}
//	EndStarttime的get方法
	public java.lang.String getEndStarttime() {
		return endStarttime;
	}
//	EndStarttime的set方法
	public void setEndStarttime(java.lang.String endStarttime) {
		this.endStarttime = endStarttime;
	}
//	EndEndtime的get方法
	public java.lang.String getEndEndtime() {
		return endEndtime;
	}
//	EndEndtime的set方法
	public void setEndEndtime(java.lang.String endEndtime) {
		this.endEndtime = endEndtime;
	}
//	id的set方法
	public void setId(Long id) {
		this.id = id;
	}
//	id的get方法
	public Long getId() {
		return this.id;
	}
//	taskid的set方法
	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
//	taskid的get方法
	public Long getTaskid() {
		return this.taskid;
	}
//	operatorname的set方法
	public void setOperatorname(java.lang.String operatorname) {
		this.operatorname = operatorname== null ? null : operatorname.trim();
	}
//	operatorname的get方法
	public java.lang.String getOperatorname() {
		return this.operatorname;
	}
//	priority的set方法
	public void setPriority(Double priority) {
		this.priority = priority;
	}
//	priority的set方法
	public Double getPriority() {
		return this.priority;
	}
//	username的set方法
	public void setUsername(java.lang.String username) {
		this.username = username== null ? null : username.trim();
	}
//	username的set方法
	public java.lang.String getUsername() {
		return this.username;
	}
//	satelliteid的set方法
	public void setSatelliteid(java.lang.String satelliteid) {
		this.satelliteid = satelliteid== null ? null : satelliteid.trim();
	}
//	satelliteid的set方法
	public java.lang.String getSatelliteid() {
		return this.satelliteid;
	}
//	sensorid的set方法
	public void setSensorid(java.lang.String sensorid) {
		this.sensorid = sensorid== null ? null : sensorid.trim();
	}
//	sensorid的set方法
	public java.lang.String getSensorid() {
		return this.sensorid;
	}
//	begintime的set方法
	public void setBegintime(java.lang.String begintime) {
		this.begintime = begintime;
	}
//	begintime的set方法	
	public java.lang.String getBegintime() {
		return this.begintime;
	}
//	endtime的set方法
	public void setEndtime(java.lang.String endtime) {
		this.endtime = endtime;
	}
//	endtime的set方法
	public java.lang.String getEndtime() {
		return this.endtime;
	}
//	upperleftlong的get方法
	public void setUpperleftlong(Double upperleftlong) {
		this.upperleftlong = upperleftlong;
	}
//	id的set方法
	public Double getUpperleftlong() {
		return this.upperleftlong;
	}
//	upperleftlat的set方法
	public void setUpperleftlat(Double upperleftlat) {
		this.upperleftlat = upperleftlat;
	}
//	upperleftlat的get方法
	public Double getUpperleftlat() {
		return this.upperleftlat;
	}
//	upperrightlong的set方法
	public void setUpperrightlong(Double upperrightlong) {
		this.upperrightlong = upperrightlong;
	}
//	upperrightlong的get方法
	public Double getUpperrightlong() {
		return this.upperrightlong;
	}
//	upperrightlat的set方法
	public void setUpperrightlat(Double upperrightlat) {
		this.upperrightlat = upperrightlat;
	}
//	upperrightlat的get方法
	public Double getUpperrightlat() {
		return this.upperrightlat;
	}
//	lowerleftlong的set方法
	public void setLowerleftlong(Double lowerleftlong) {
		this.lowerleftlong = lowerleftlong;
	}
//	lowerleftlong的get方法
	public Double getLowerleftlong() {
		return this.lowerleftlong;
	}
//	lowerleftlat的set方法
	public void setLowerleftlat(Double lowerleftlat) {
		this.lowerleftlat = lowerleftlat;
	}
//	lowerleftlat的get方法
	public Double getLowerleftlat() {
		return this.lowerleftlat;
	}
//	lowerrightlong的set方法
	public void setLowerrightlong(Double lowerrightlong) {
		this.lowerrightlong = lowerrightlong;
	}
//	lowerrightlong的get方法
	public Double getLowerrightlong() {
		return this.lowerrightlong;
	}
//	lowerrightlat的set方法
	public void setLowerrightlat(Double lowerrightlat) {
		this.lowerrightlat = lowerrightlat;
	}
//	lowerrightlat的get方法
	public Double getLowerrightlat() {
		return this.lowerrightlat;
	}
//	productlevel的set方法
	public void setProductlevel(java.lang.String productlevel) {
		this.productlevel = productlevel== null ? null : productlevel.trim();
	}
//	productlevel的get方法
	public java.lang.String getProductlevel() {
		return this.productlevel;
	}
//	polarizealogrithm的set方法
	public void setPolarizealogrithm(java.lang.String polarizealogrithm) {
		this.polarizealogrithm = polarizealogrithm== null ? null : polarizealogrithm.trim();
	}
//	polarizealogrithm的get方法
	public java.lang.String getPolarizealogrithm() {
		return this.polarizealogrithm;
	}
//	cloudcover的set方法
	public void setCloudcover(Double cloudcover) {
		this.cloudcover = cloudcover;
	}
//	cloudcover的get方法
	public Double getCloudcover() {
		return this.cloudcover;
	}
//	price的set方法
	public void setPrice(Integer price) {
		this.price = price;
	}
//	price的get方法
	public Integer getPrice() {
		return this.price;
	}
//	remark的set方法
	public void setRemark(java.lang.String remark) {
		this.remark = remark== null ? null : remark.trim();
	}
//	remark的get方法
	public java.lang.String getRemark() {
		return this.remark;
	}
//	finishdownloadif的set方法
	public void setFinishdownloadif(Double finishdownloadif) {
		this.finishdownloadif = finishdownloadif;
	}
//	finishdownloadif的get方法
	public Double getFinishdownloadif() {
		return this.finishdownloadif;
	}
//	finishdownloadtime的set方法
	public void setFinishdownloadtime(java.lang.String finishdownloadtime) {
		this.finishdownloadtime = finishdownloadtime;
	}
//	finishdownloadtime的get方法	
	public java.lang.String getFinishdownloadtime() {
		return this.finishdownloadtime;
	}
//	status的set方法
	public void setStatus(java.lang.String status) {
		this.status = status== null ? null : status.trim();
	}
//	status的get方法
	public java.lang.String getStatus() {
		return this.status;
	}
//	stationid的set方法
	public void setStationid(java.lang.String stationid) {
		this.stationid = stationid== null ? null : stationid.trim();
	}
//	stationid的get方法
	public java.lang.String getStationid() {
		return this.stationid;
	}
//	reason的set方法
	public void setReason(java.lang.String reason) {
		this.reason = reason== null ? null : reason.trim();
	}
//	reason的get方法
	public java.lang.String getReason() {
		return this.reason;
	}
//	subdate的set方法
	public void setSubdate(java.lang.String subdate) {
		this.subdate = subdate;
	}
//	subdate的get方法
	public java.lang.String getSubdate() {
		return this.subdate;
	}
//	areacenterlat的set方法
	public void setAreacenterlat(Double areacenterlat) {
		this.areacenterlat = areacenterlat;
	}
//	areacenterlat的get方法
	public Double getAreacenterlat() {
		return this.areacenterlat;
	}
//	areacenterlong的set方法
	public void setAreacenterlong(Double areacenterlong) {
		this.areacenterlong = areacenterlong;
	}
//	areacenterlong的get方法
	public Double getAreacenterlong() {
		return this.areacenterlong;
	}
public Integer getAveragecloud() {
		return averagecloud;
	}
	public void setAveragecloud(Integer averagecloud) {
		this.averagecloud = averagecloud;
	}
	
	public Integer getSideangle() {
		return sideangle;
	}
	public void setSideangle(Integer sideangle) {
		this.sideangle = sideangle;
	}
	//	issend的set方法
	public void setIssend(Double issend) {
		this.issend = issend;
	}
//	issend的get方法
	public Double getIssend() {
		return this.issend;
	}
//	iscancel的set方法
	public void setIscancel(Double iscancel) {
		this.iscancel = iscancel;
	}
//	iscancel的get方法
	public Double getIscancel() {
		return this.iscancel;
	}
//	createtime的set方法
	public void setCreatetime(java.lang.String createtime) {
		this.createtime = createtime;
	}
//	createtime的get方法
	public java.lang.String getCreatetime() {
		return this.createtime;
	}
//	collectidstr的set方法
	public void setCollectidstr(Double collectidstr) {
		this.collectidstr = collectidstr;
	}
//	collectidstr的get方法
	public Double getCollectidstr() {
		return this.collectidstr;
	}
//	earthsurfacename的set方法
	public void setEarthsurfacename(java.lang.String earthsurfacename) {
		this.earthsurfacename = earthsurfacename== null ? null : earthsurfacename.trim();
	}
//	earthsurfacename的get方法
	public java.lang.String getEarthsurfacename() {
		return this.earthsurfacename;
	}
//	surfacereflectivity的set方法
	public void setSurfacereflectivity(java.lang.String surfacereflectivity) {
		this.surfacereflectivity = surfacereflectivity== null ? null : surfacereflectivity.trim();
	}
//	surfacereflectivity的get方法
	public java.lang.String getSurfacereflectivity() {
		return this.surfacereflectivity;
	}
//	observationinterval的set方法
	public void setObservationinterval(java.lang.String observationinterval) {
		this.observationinterval = observationinterval== null ? null : observationinterval.trim();
	}
//	observationinterval的get方法
	public java.lang.String getObservationinterval() {
		return this.observationinterval;
	}
//	gatherareaname的set方法
	public void setGatherareaname(java.lang.String gatherareaname) {
		this.gatherareaname = gatherareaname== null ? null : gatherareaname.trim();
	}
//	gatherareaname的get方法
	public java.lang.String getGatherareaname() {
		return this.gatherareaname;
	}
//	getGatherareadescription的set方法
	public void setGatherareadescription(java.lang.String gatherareadescription) {
		this.gatherareadescription = gatherareadescription== null ? null : gatherareadescription.trim();
	}
//	Gatherareadescription的get方法
	public java.lang.String getGatherareadescription() {
		return this.gatherareadescription;
	}
//	Instrumentmode的set方法
	public void setInstrumentmode(java.lang.String instrumentmode) {
		this.instrumentmode = instrumentmode== null ? null : instrumentmode.trim();
	}
//	Instrumentmode的get方法
	public java.lang.String getInstrumentmode() {
		return this.instrumentmode;
	}
}
