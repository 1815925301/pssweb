/*
 * Powered By 尹力
 * Since 2015 - 2016-40-30
 */

package com.sinosoft.business.po.query;

import java.util.Date;

import com.sinosoft.po.query.BasePaginationQuery;

public class PssOrderInfoQuery  extends BasePaginationQuery {
	
	private String starttime;//前台开始时间
	private String endtime;//前台结束时间
	private String amount;//数量
	private String timeType;//时间类型(年,月,日)
	private String reportUnit;//报表单位（产品级别，应用领域，卫星，传感器）
	private String statisticType;//需要统计的类型，对于同一订单表的查询，判断处理
	
	 //订单号       数据库字段: ORDERID 
	private Long orderid;
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getReportUnit() {
		return reportUnit;
	}
	public void setReportUnit(String reportUnit) {
		this.reportUnit = reportUnit;
	}
	public String getStatisticType() {
		return statisticType;
	}
	public void setStatisticType(String statisticType) {
		this.statisticType = statisticType;
	}
	private String orderMainId;//主订单id ORDERMAINID
	private String orderer; // 订购人(查询用)ORDERER
	private String orderStartTime; //订购时间(开始时间)
	private String orderEndTime;//订购时间(结束时间)
	private String checkStartTime;//审核时间(开始时间)
	private String checkEndTime;//审核时间(结束时间)
	
	
	//审核状态
	private Long checkstate;
	public Long getCheckstate() {
		return checkstate;
	}
	public void setCheckstate(Long checkstate) {
		this.checkstate = checkstate;
	}
	//支付行
	private String paytheline;
	public String getPaytheline() {		
		return paytheline;
		}
	public void setPaytheline(String paytheline) {
		this.paytheline = paytheline;
		}
		//是否支付
	private Long ispay;
	

	
	
	 public Long getIspay() {
		return ispay;
	}
	public void setIspay(Long ispay) {
		this.ispay = ispay;
	}
	//任务类型       数据库字段: TASKTYPE 
	private Long tasktype;
	
	
	 //任务时间       数据库字段: TASKTIME 
	private String tasktime;
	
	
	 //操作员姓名       数据库字段: OPERATORNAME 
	private java.lang.String operatorname;
	
	
	 //优先级       数据库字段: PRIORITY 
	private Double priority;
	
	
	 //用户名       数据库字段: USERNAME 
	private java.lang.String username;
	
	
	 //产品序列号       数据库字段: PRODUCTID 
	private Double productid;
	
	
	 //景序列号       数据库字段: SCENEID 
	private Double sceneid;
	
	
	 //生产日期       数据库字段: PRODUCTDATE 
	private java.util.Date productdate;
	
	
	 //产品级别       数据库字段: PRODUCTLEVEL 
	private java.lang.String productlevel;
	
	
	 //产品上传目录       数据库字段: PRODUCTUPLOADDIR 
	private java.lang.String productuploaddir;
	
	
	 //订单状态       数据库字段: ORDERSTATE 
	private Long orderstate;
	
	
	 //删除标识       数据库字段: DELFLAG 
	private Double delflag;
	
	
	 //完成状态       数据库字段: AREADYSTATE 
	private Double areadystate;
	
	
	 //文件大小       数据库字段: PRODUCTSIZE 
	private Double productsize;
	
	
	 //审核人       数据库字段: CHECKUSERNAME 
	private java.lang.String checkusername;
	
	
	 //审核时间       数据库字段: CHECKTIME 
	private String checktime;
	
	
	 //备注       数据库字段: NOTE 
	private java.lang.String note;
	
	
	 //价格       数据库字段: PRICE 
	private Double price;
	
	
	 //结算方式       数据库字段: CLEARFORM 
	private java.lang.String clearform;
	
	
	 //数据格式       数据库字段: DATAFORMATDES 
	private java.lang.String dataformatdes;
	
	
	 //质量评价结果       数据库字段: OVERALLQUALITY 
	private Double overallquality;
	
	
	 //生产类型（标准景、浮动景、条带景）       数据库字段: PRODUCTTYPE 
	private java.lang.String producttype;
	
	
	 //条带景数       数据库字段: SCENECOUNT 
	private Double scenecount;
	
	
	 //景漂移       数据库字段: SCENESHIFT 
	private Double sceneshift;
	
	
	 //增益       数据库字段: GAIN 
	private java.lang.String gain;
	
	
	 //积分级数       数据库字段: INTERGRALLEVEL 
	private Double intergrallevel;
	
	
	 //太阳高度角       数据库字段: SUNELEVATION 
	private Double sunelevation;
	
	
	 //太阳方位角       数据库字段: SUNAZIMUTH 
	private Double sunazimuth;
	
	
	 //分景时间       数据库字段: SCENEDATE 
	private String scenedate;
	
	
	 //成像开始时间       数据库字段: IMAGEINGSTARTTIME 
	private String imageingstarttime;
	
	
	 //成像结束时间       数据库字段: IMAGEIMGSTOPTIME 
	private String imageimgstoptime;
	
	
	 //星下点PATH       数据库字段: SATPATH 
	private Double satpath;
	
	
	 //星下点ROW       数据库字段: SATROW 
	private Double satrow;
	
	
	 //景PATH       数据库字段: SCENEPATH 
	private Double scenepath;
	
	
	 //景ROW       数据库字段: SCENEROW 
	private Double scenerow;
	
	
	 //景中心纬度       数据库字段: SCENECENTERLAT 
	private Double scenecenterlat;
	
	
	 //景中心经度       数据库字段: SCENECENTERLONG 
	private Double scenecenterlong;
	
	
	 //图像左上角纬度       数据库字段: DATAUPPERLEFTLAT 
	private Double dataupperleftlat;
	
	
	 //图像左上角经度       数据库字段: DATAUPPERLEFTLONG 
	private Double dataupperleftlong;
	
	
	 //图像右上角纬度       数据库字段: DATAUPPERRIGHTLAT 
	private Double dataupperrightlat;
	
	
	 //图像右上角经度       数据库字段: DATAUPPERRIGHTUPPERLONG 
	private Double dataupperrightupperlong;
	
	
	 //图像左下角纬度       数据库字段: DATALOWERLEFTLAT 
	private Double datalowerleftlat;
	
	
	 //图像左下角经度       数据库字段: DATALOWERLEFTLONG 
	private Double datalowerleftlong;
	
	
	 //图像右下角纬度       数据库字段: DATALOWERRIGHTLAT 
	private Double datalowerrightlat;
	
	
	 //图像右下角经度       数据库字段: DATALOWERRIGHTLONG 
	private Double datalowerrightlong;
	
	
	 //波段号列表（以逗号隔开）       数据库字段: BANDS 
	private java.lang.String bands;
	
	
	 //辐射校正方法       数据库字段: RADIOMETRICMETHOD 
	private java.lang.String radiometricmethod;
	
	
	 //绝对定标系数类型       数据库字段: ABCCALIBTYPE 
	private java.lang.String abccalibtype;
	
	
	 //MTF类型       数据库字段: MTFCPROMODE 
	private java.lang.String mtfcpromode;
	
	
	 //卫星标识       数据库字段: SATELLITEID 
	private java.lang.String satelliteid;
	
	
	 //空间分辨率       数据库字段: PIXELSPACING 
	private Double pixelspacing;
	
	
	 //星历数据使用       数据库字段: EPHEMERISDATA 
	private java.lang.String ephemerisdata;
	
	
	 //姿态数据使用       数据库字段: ATTITUDEDATA 
	private java.lang.String attitudedata;
	
	
	 //产品图像取向       数据库字段: PRODUCTORIENTATION 
	private java.lang.String productorientation;
	
	
	 //地球模型       数据库字段: EARTHMODEL 
	private java.lang.String earthmodel;
	
	
	 //地图投影       数据库字段: MAPPROJECTION 
	private java.lang.String mapprojection;
	
	
	 //重采样技术       数据库字段: RESAMPLETECHNIQUE 
	private java.lang.String resampletechnique;
	
	
	 //产品左上角纬度       数据库字段: PRODUCTUPPERLEFTLAT 
	private Double productupperleftlat;
	
	
	 //产品左上经度       数据库字段: PRODUCTUPPERLEFTLONG 
	private Double productupperleftlong;
	
	
	 //产品右上角纬度       数据库字段: PRODUCTUPPERRIGHTLAT 
	private Double productupperrightlat;
	
	
	 //产品右上角经度       数据库字段: PRODUCTUPPERRIGHTLONG 
	private Double productupperrightlong;
	
	
	 //产品左下角纬度       数据库字段: PRODUCTLOWERLEFTLAT 
	private Double productlowerleftlat;
	
	
	 //产品左下角经度       数据库字段: PRODUCTLOWERLEFTLONG 
	private Double productlowerleftlong;
	
	
	 //产品右下角纬度       数据库字段: PRODUCTLOWERRIGHTLAT 
	private Double productlowerrightlat;
	
	
	 //产品右下角经度       数据库字段: PRODUCTLOWERRIGHTLONG 
	private Double productlowerrightlong;
	
	
	 //图像左上角X坐标       数据库字段: DATAUPPERLEFTX 
	private Double dataupperleftx;
	
	
	 //图像左上角Y坐标       数据库字段: DATAUPPERLEFTY 
	private Double dataupperlefty;
	
	
	 //图像右上角X坐标       数据库字段: DATAUPPERRIGHTX 
	private Double dataupperrightx;
	
	
	 //图像右上角Y坐标       数据库字段: DATAUPPERRIGHTY 
	private Double dataupperrighty;
	
	
	 //图像左下角X坐标       数据库字段: DATALOWERLEFTX 
	private Double datalowerleftx;
	
	
	 //图像左下角Y坐标       数据库字段: DATALOWERLEFTY 
	private Double datalowerlefty;
	
	
	 //图像右下角X坐标       数据库字段: DATALOWERRIGHTX 
	private Double datalowerrightx;
	
	
	 //图像右下角Y坐标       数据库字段: DATALOWERRIGHTY 
	private Double datalowerrighty;
	
	
	 //产品左上角X坐标       数据库字段: PRODUCTUPPERLEFTX 
	private Double productupperleftx;
	
	
	 //产品左上角Y坐标       数据库字段: PRODUCTUPPERLEFTY 
	private Double productupperlefty;
	
	
	 //产品右上角X坐标       数据库字段: PRODUCTUPPERRIGHTX 
	private Double productupperrightx;
	
	
	 //产品右上角Y坐标       数据库字段: PRODUCTUPPERRIGHTY 
	private Double productupperrighty;
	
	
	 //产品左下角X坐标       数据库字段: PRODUCTLOWERLEFTX 
	private Double productlowerleftx;
	
	
	 //产品左下角Y坐标       数据库字段: PRODUCTLOWERLEFTY 
	private Double productlowerlefty;
	
	
	 //产品右下角X坐标       数据库字段: PRODUCTLOWERRIGHTX 
	private Double productlowerrightx;
	
	
	 //产品右下角Y坐标       数据库字段: PRODUCTLOWERRIGHTY 
	private Double productlowerrighty;
	
	
	 //几何精校正方法       数据库字段: GEODETICMETHOD 
	private java.lang.String geodeticmethod;
	
	
	 //所使用的DEM类型       数据库字段: DEMTYPE 
	private java.lang.String demtype;
	
	
	 //failreason       数据库字段: FAILREASON 
	private java.lang.String failreason;
	
	
	 //delstatus       数据库字段: DELSTATUS 
	private Double delstatus;
	
	
	 //sensorid       数据库字段: SENSORID 
	private java.lang.String sensorid;
	
	
	 //finishdownloadif       数据库字段: FINISHDOWNLOADIF 
	private Double finishdownloadif;
	
	
	 //finishdownloadtime       数据库字段: FINISHDOWNLOADTIME 
	private String finishdownloadtime;
	
	
	 //urgerncylevel       数据库字段: URGERNCYLEVEL 
	private Double urgerncylevel;
	
	
	 //stationid       数据库字段: STATIONID 
	private java.lang.String stationid;
	
	
	 //ordermediumid       数据库字段: ORDERMEDIUMID 
	private java.lang.String ordermediumid;
	
	
	 //issend       数据库字段: ISSEND 
	private Double issend;
	
	
	 //orderidstringrel       数据库字段: ORDERIDSTRINGREL 
	private java.lang.String orderidstringrel;
	
	
	 //cloudcover       数据库字段: CLOUDCOVER 
	private Double cloudcover;
	
	
	 //finishtime       数据库字段: FINISHTIME 
	private String finishtime;
	
	
	 //orderidstring       数据库字段: ORDERIDSTRING 
	private java.lang.String orderidstring;
	
	
	 //ftpurl       数据库字段: FTPURL 
	private java.lang.String ftpurl;
	
	
	 //describe       数据库字段: DESCRIBE 
	private java.lang.String describe;
	
	
	 //auditnote       数据库字段: AUDITNOTE 
	private java.lang.String auditnote;
	
	
	 //scenerowbias       数据库字段: SCENEROWBIAS 
	private java.lang.String scenerowbias;
	
	
	 //scenepathbias       数据库字段: SCENEPATHBIAS 
	private java.lang.String scenepathbias;
	//是否故障订单
	private Integer isfault;
	

	public Integer getIsfault() {
		return isfault;
	}
	public void setIsfault(Integer isfault) {
		this.isfault = isfault;
	}
	
	private String sqlWhere;
	
	public String getOrderMainId() {
		return orderMainId;
	}
	public void setOrderMainId(String orderMainId) {
		this.orderMainId = orderMainId;
	}
	
	public String getOrderer() {
		return orderer;
	}
	public void setOrderer(String orderer) {
		this.orderer = orderer;
	}
	
	public String getOrderStartTime() {
		return orderStartTime;
	}
	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}
	public String getOrderEndTime() {
		return orderEndTime;
	}
	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}
	public String getCheckStartTime() {
		return checkStartTime;
	}
	public void setCheckStartTime(String checkStartTime) {
		this.checkStartTime = checkStartTime;
	}
	public String getCheckEndTime() {
		return checkEndTime;
	}
	public void setCheckEndTime(String checkEndTime) {
		this.checkEndTime = checkEndTime;
	}
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}
	
	public Long getOrderid() {
		return this.orderid;
	}
	
	
	public Long getTasktype() {
		return tasktype;
	}
	public void setTasktype(Long tasktype) {
		this.tasktype = tasktype;
	}
	public void setTasktime(String tasktime) {
		this.tasktime = tasktime;
	}
	
	public String getTasktime() {
		return this.tasktime;
	}
	public void setOperatorname(String operatorname) {
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
	public void setProductid(Double productid) {
		this.productid = productid;
	}
	
	public Double getProductid() {
		return this.productid;
	}
	public void setSceneid(Double sceneid) {
		this.sceneid = sceneid;
	}
	
	public Double getSceneid() {
		return this.sceneid;
	}
	public void setProductdate(java.util.Date productdate) {
		this.productdate = productdate;
	}
	
	public java.util.Date getProductdate() {
		return this.productdate;
	}
	public void setProductlevel(java.lang.String productlevel) {
		this.productlevel = productlevel== null ? null : productlevel.trim();
	}
	
	public java.lang.String getProductlevel() {
		return this.productlevel;
	}
	public void setProductuploaddir(java.lang.String productuploaddir) {
		this.productuploaddir = productuploaddir== null ? null : productuploaddir.trim();
	}
	
	public java.lang.String getProductuploaddir() {
		return this.productuploaddir;
	}
	public void setOrderstate(Long orderstate) {
		this.orderstate = orderstate;
	}
	
	public Long getOrderstate() {
		return this.orderstate;
	}
	public void setDelflag(Double delflag) {
		this.delflag = delflag;
	}
	
	public Double getDelflag() {
		return this.delflag;
	}
	public void setAreadystate(Double areadystate) {
		this.areadystate = areadystate;
	}
	
	public Double getAreadystate() {
		return this.areadystate;
	}
	public void setProductsize(Double productsize) {
		this.productsize = productsize;
	}
	
	public Double getProductsize() {
		return this.productsize;
	}
	public void setCheckusername(java.lang.String checkusername) {
		this.checkusername = checkusername== null ? null : checkusername.trim();
	}
	
	public java.lang.String getCheckusername() {
		return this.checkusername;
	}

	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	
	public String getChecktime() {
		return this.checktime;
	}
	public void setNote(java.lang.String note) {
		this.note = note== null ? null : note.trim();
	}
	
	public java.lang.String getNote() {
		return this.note;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return this.price;
	}
	public void setClearform(java.lang.String clearform) {
		this.clearform = clearform== null ? null : clearform.trim();
	}
	
	public java.lang.String getClearform() {
		return this.clearform;
	}
	public void setDataformatdes(java.lang.String dataformatdes) {
		this.dataformatdes = dataformatdes== null ? null : dataformatdes.trim();
	}
	
	public java.lang.String getDataformatdes() {
		return this.dataformatdes;
	}
	public void setOverallquality(Double overallquality) {
		this.overallquality = overallquality;
	}
	
	public Double getOverallquality() {
		return this.overallquality;
	}
	public void setProducttype(java.lang.String producttype) {
		this.producttype = producttype== null ? null : producttype.trim();
	}
	
	public java.lang.String getProducttype() {
		return this.producttype;
	}
	public void setScenecount(Double scenecount) {
		this.scenecount = scenecount;
	}
	
	public Double getScenecount() {
		return this.scenecount;
	}
	public void setSceneshift(Double sceneshift) {
		this.sceneshift = sceneshift;
	}
	
	public Double getSceneshift() {
		return this.sceneshift;
	}
	public void setGain(java.lang.String gain) {
		this.gain = gain== null ? null : gain.trim();
	}
	
	public java.lang.String getGain() {
		return this.gain;
	}
	public void setIntergrallevel(Double intergrallevel) {
		this.intergrallevel = intergrallevel;
	}
	
	public Double getIntergrallevel() {
		return this.intergrallevel;
	}
	public void setSunelevation(Double sunelevation) {
		this.sunelevation = sunelevation;
	}
	
	public Double getSunelevation() {
		return this.sunelevation;
	}
	public void setSunazimuth(Double sunazimuth) {
		this.sunazimuth = sunazimuth;
	}
	
	public Double getSunazimuth() {
		return this.sunazimuth;
	}

	public void setScenedate(String scenedate) {
		this.scenedate = scenedate;
	}
	
	public String getScenedate() {
		return this.scenedate;
	}
	public void setImageingstarttime(String imageingstarttime) {
		this.imageingstarttime = imageingstarttime;
	}
	
	public String getImageingstarttime() {
		return this.imageingstarttime;
	}

	public void setImageimgstoptime(String imageimgstoptime) {
		this.imageimgstoptime = imageimgstoptime;
	}
	
	public String getImageimgstoptime() {
		return this.imageimgstoptime;
	}
	public void setSatpath(Double satpath) {
		this.satpath = satpath;
	}
	
	public Double getSatpath() {
		return this.satpath;
	}
	public void setSatrow(Double satrow) {
		this.satrow = satrow;
	}
	
	public Double getSatrow() {
		return this.satrow;
	}
	public void setScenepath(Double scenepath) {
		this.scenepath = scenepath;
	}
	
	public Double getScenepath() {
		return this.scenepath;
	}
	public void setScenerow(Double scenerow) {
		this.scenerow = scenerow;
	}
	
	public Double getScenerow() {
		return this.scenerow;
	}
	public void setScenecenterlat(Double scenecenterlat) {
		this.scenecenterlat = scenecenterlat;
	}
	
	public Double getScenecenterlat() {
		return this.scenecenterlat;
	}
	public void setScenecenterlong(Double scenecenterlong) {
		this.scenecenterlong = scenecenterlong;
	}
	
	public Double getScenecenterlong() {
		return this.scenecenterlong;
	}
	public void setDataupperleftlat(Double dataupperleftlat) {
		this.dataupperleftlat = dataupperleftlat;
	}
	
	public Double getDataupperleftlat() {
		return this.dataupperleftlat;
	}
	public void setDataupperleftlong(Double dataupperleftlong) {
		this.dataupperleftlong = dataupperleftlong;
	}
	
	public Double getDataupperleftlong() {
		return this.dataupperleftlong;
	}
	public void setDataupperrightlat(Double dataupperrightlat) {
		this.dataupperrightlat = dataupperrightlat;
	}
	
	public Double getDataupperrightlat() {
		return this.dataupperrightlat;
	}
	public void setDataupperrightupperlong(Double dataupperrightupperlong) {
		this.dataupperrightupperlong = dataupperrightupperlong;
	}
	
	public Double getDataupperrightupperlong() {
		return this.dataupperrightupperlong;
	}
	public void setDatalowerleftlat(Double datalowerleftlat) {
		this.datalowerleftlat = datalowerleftlat;
	}
	
	public Double getDatalowerleftlat() {
		return this.datalowerleftlat;
	}
	public void setDatalowerleftlong(Double datalowerleftlong) {
		this.datalowerleftlong = datalowerleftlong;
	}
	
	public Double getDatalowerleftlong() {
		return this.datalowerleftlong;
	}
	public void setDatalowerrightlat(Double datalowerrightlat) {
		this.datalowerrightlat = datalowerrightlat;
	}
	
	public Double getDatalowerrightlat() {
		return this.datalowerrightlat;
	}
	public void setDatalowerrightlong(Double datalowerrightlong) {
		this.datalowerrightlong = datalowerrightlong;
	}
	
	public Double getDatalowerrightlong() {
		return this.datalowerrightlong;
	}
	public void setBands(java.lang.String bands) {
		this.bands = bands== null ? null : bands.trim();
	}
	
	public java.lang.String getBands() {
		return this.bands;
	}
	public void setRadiometricmethod(java.lang.String radiometricmethod) {
		this.radiometricmethod = radiometricmethod== null ? null : radiometricmethod.trim();
	}
	
	public java.lang.String getRadiometricmethod() {
		return this.radiometricmethod;
	}
	public void setAbccalibtype(java.lang.String abccalibtype) {
		this.abccalibtype = abccalibtype== null ? null : abccalibtype.trim();
	}
	
	public java.lang.String getAbccalibtype() {
		return this.abccalibtype;
	}
	public void setMtfcpromode(java.lang.String mtfcpromode) {
		this.mtfcpromode = mtfcpromode== null ? null : mtfcpromode.trim();
	}
	
	public java.lang.String getMtfcpromode() {
		return this.mtfcpromode;
	}
	public void setSatelliteid(java.lang.String satelliteid) {
		this.satelliteid = satelliteid== null ? null : satelliteid.trim();
	}
	
	public java.lang.String getSatelliteid() {
		return this.satelliteid;
	}
	public void setPixelspacing(Double pixelspacing) {
		this.pixelspacing = pixelspacing;
	}
	
	public Double getPixelspacing() {
		return this.pixelspacing;
	}
	public void setEphemerisdata(java.lang.String ephemerisdata) {
		this.ephemerisdata = ephemerisdata== null ? null : ephemerisdata.trim();
	}
	
	public java.lang.String getEphemerisdata() {
		return this.ephemerisdata;
	}
	public void setAttitudedata(java.lang.String attitudedata) {
		this.attitudedata = attitudedata== null ? null : attitudedata.trim();
	}
	
	public java.lang.String getAttitudedata() {
		return this.attitudedata;
	}
	public void setProductorientation(java.lang.String productorientation) {
		this.productorientation = productorientation== null ? null : productorientation.trim();
	}
	
	public java.lang.String getProductorientation() {
		return this.productorientation;
	}
	public void setEarthmodel(java.lang.String earthmodel) {
		this.earthmodel = earthmodel== null ? null : earthmodel.trim();
	}
	
	public java.lang.String getEarthmodel() {
		return this.earthmodel;
	}
	public void setMapprojection(java.lang.String mapprojection) {
		this.mapprojection = mapprojection== null ? null : mapprojection.trim();
	}
	
	public java.lang.String getMapprojection() {
		return this.mapprojection;
	}
	public void setResampletechnique(java.lang.String resampletechnique) {
		this.resampletechnique = resampletechnique== null ? null : resampletechnique.trim();
	}
	
	public java.lang.String getResampletechnique() {
		return this.resampletechnique;
	}
	public void setProductupperleftlat(Double productupperleftlat) {
		this.productupperleftlat = productupperleftlat;
	}
	
	public Double getProductupperleftlat() {
		return this.productupperleftlat;
	}
	public void setProductupperleftlong(Double productupperleftlong) {
		this.productupperleftlong = productupperleftlong;
	}
	
	public Double getProductupperleftlong() {
		return this.productupperleftlong;
	}
	public void setProductupperrightlat(Double productupperrightlat) {
		this.productupperrightlat = productupperrightlat;
	}
	
	public Double getProductupperrightlat() {
		return this.productupperrightlat;
	}
	public void setProductupperrightlong(Double productupperrightlong) {
		this.productupperrightlong = productupperrightlong;
	}
	
	public Double getProductupperrightlong() {
		return this.productupperrightlong;
	}
	public void setProductlowerleftlat(Double productlowerleftlat) {
		this.productlowerleftlat = productlowerleftlat;
	}
	
	public Double getProductlowerleftlat() {
		return this.productlowerleftlat;
	}
	public void setProductlowerleftlong(Double productlowerleftlong) {
		this.productlowerleftlong = productlowerleftlong;
	}
	
	public Double getProductlowerleftlong() {
		return this.productlowerleftlong;
	}
	public void setProductlowerrightlat(Double productlowerrightlat) {
		this.productlowerrightlat = productlowerrightlat;
	}
	
	public Double getProductlowerrightlat() {
		return this.productlowerrightlat;
	}
	public void setProductlowerrightlong(Double productlowerrightlong) {
		this.productlowerrightlong = productlowerrightlong;
	}
	
	public Double getProductlowerrightlong() {
		return this.productlowerrightlong;
	}
	public void setDataupperleftx(Double dataupperleftx) {
		this.dataupperleftx = dataupperleftx;
	}
	
	public Double getDataupperleftx() {
		return this.dataupperleftx;
	}
	public void setDataupperlefty(Double dataupperlefty) {
		this.dataupperlefty = dataupperlefty;
	}
	
	public Double getDataupperlefty() {
		return this.dataupperlefty;
	}
	public void setDataupperrightx(Double dataupperrightx) {
		this.dataupperrightx = dataupperrightx;
	}
	
	public Double getDataupperrightx() {
		return this.dataupperrightx;
	}
	public void setDataupperrighty(Double dataupperrighty) {
		this.dataupperrighty = dataupperrighty;
	}
	
	public Double getDataupperrighty() {
		return this.dataupperrighty;
	}
	public void setDatalowerleftx(Double datalowerleftx) {
		this.datalowerleftx = datalowerleftx;
	}
	
	public Double getDatalowerleftx() {
		return this.datalowerleftx;
	}
	public void setDatalowerlefty(Double datalowerlefty) {
		this.datalowerlefty = datalowerlefty;
	}
	
	public Double getDatalowerlefty() {
		return this.datalowerlefty;
	}
	public void setDatalowerrightx(Double datalowerrightx) {
		this.datalowerrightx = datalowerrightx;
	}
	
	public Double getDatalowerrightx() {
		return this.datalowerrightx;
	}
	public void setDatalowerrighty(Double datalowerrighty) {
		this.datalowerrighty = datalowerrighty;
	}
	
	public Double getDatalowerrighty() {
		return this.datalowerrighty;
	}
	public void setProductupperleftx(Double productupperleftx) {
		this.productupperleftx = productupperleftx;
	}
	
	public Double getProductupperleftx() {
		return this.productupperleftx;
	}
	public void setProductupperlefty(Double productupperlefty) {
		this.productupperlefty = productupperlefty;
	}
	
	public Double getProductupperlefty() {
		return this.productupperlefty;
	}
	public void setProductupperrightx(Double productupperrightx) {
		this.productupperrightx = productupperrightx;
	}
	
	public Double getProductupperrightx() {
		return this.productupperrightx;
	}
	public void setProductupperrighty(Double productupperrighty) {
		this.productupperrighty = productupperrighty;
	}
	
	public Double getProductupperrighty() {
		return this.productupperrighty;
	}
	public void setProductlowerleftx(Double productlowerleftx) {
		this.productlowerleftx = productlowerleftx;
	}
	
	public Double getProductlowerleftx() {
		return this.productlowerleftx;
	}
	public void setProductlowerlefty(Double productlowerlefty) {
		this.productlowerlefty = productlowerlefty;
	}
	
	public Double getProductlowerlefty() {
		return this.productlowerlefty;
	}
	public void setProductlowerrightx(Double productlowerrightx) {
		this.productlowerrightx = productlowerrightx;
	}
	
	public Double getProductlowerrightx() {
		return this.productlowerrightx;
	}
	public void setProductlowerrighty(Double productlowerrighty) {
		this.productlowerrighty = productlowerrighty;
	}
	
	public Double getProductlowerrighty() {
		return this.productlowerrighty;
	}
	public void setGeodeticmethod(java.lang.String geodeticmethod) {
		this.geodeticmethod = geodeticmethod== null ? null : geodeticmethod.trim();
	}
	
	public java.lang.String getGeodeticmethod() {
		return this.geodeticmethod;
	}
	public void setDemtype(java.lang.String demtype) {
		this.demtype = demtype== null ? null : demtype.trim();
	}
	
	public java.lang.String getDemtype() {
		return this.demtype;
	}
	public void setFailreason(java.lang.String failreason) {
		this.failreason = failreason== null ? null : failreason.trim();
	}
	
	public java.lang.String getFailreason() {
		return this.failreason;
	}
	public void setDelstatus(Double delstatus) {
		this.delstatus = delstatus;
	}
	
	public Double getDelstatus() {
		return this.delstatus;
	}
	public void setSensorid(java.lang.String sensorid) {
		this.sensorid = sensorid== null ? null : sensorid.trim();
	}
	
	public java.lang.String getSensorid() {
		return this.sensorid;
	}
	public void setFinishdownloadif(Double finishdownloadif) {
		this.finishdownloadif = finishdownloadif;
	}
	
	public Double getFinishdownloadif() {
		return this.finishdownloadif;
	}
	public void setFinishdownloadtime(String finishdownloadtime) {
		this.finishdownloadtime = finishdownloadtime;
	}
	
	public String getFinishdownloadtime() {
		return this.finishdownloadtime;
	}
	public void setUrgerncylevel(Double urgerncylevel) {
		this.urgerncylevel = urgerncylevel;
	}
	
	public Double getUrgerncylevel() {
		return this.urgerncylevel;
	}
	public void setStationid(java.lang.String stationid) {
		this.stationid = stationid== null ? null : stationid.trim();
	}
	
	public java.lang.String getStationid() {
		return this.stationid;
	}
	public void setOrdermediumid(java.lang.String ordermediumid) {
		this.ordermediumid = ordermediumid== null ? null : ordermediumid.trim();
	}
	
	public java.lang.String getOrdermediumid() {
		return this.ordermediumid;
	}
	public void setIssend(Double issend) {
		this.issend = issend;
	}
	
	public Double getIssend() {
		return this.issend;
	}
	public void setOrderidstringrel(java.lang.String orderidstringrel) {
		this.orderidstringrel = orderidstringrel== null ? null : orderidstringrel.trim();
	}
	
	public java.lang.String getOrderidstringrel() {
		return this.orderidstringrel;
	}
	public void setCloudcover(Double cloudcover) {
		this.cloudcover = cloudcover;
	}
	
	public Double getCloudcover() {
		return this.cloudcover;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}
	
	public String getFinishtime() {
		return this.finishtime;
	}
	public void setOrderidstring(java.lang.String orderidstring) {
		this.orderidstring = orderidstring== null ? null : orderidstring.trim();
	}
	
	public java.lang.String getOrderidstring() {
		return this.orderidstring;
	}
	public void setFtpurl(java.lang.String ftpurl) {
		this.ftpurl = ftpurl== null ? null : ftpurl.trim();
	}
	
	public java.lang.String getFtpurl() {
		return this.ftpurl;
	}
	public void setDescribe(java.lang.String describe) {
		this.describe = describe== null ? null : describe.trim();
	}
	
	public java.lang.String getDescribe() {
		return this.describe;
	}
	public void setAuditnote(java.lang.String auditnote) {
		this.auditnote = auditnote== null ? null : auditnote.trim();
	}
	
	public java.lang.String getAuditnote() {
		return this.auditnote;
	}
	public void setScenerowbias(java.lang.String scenerowbias) {
		this.scenerowbias = scenerowbias== null ? null : scenerowbias.trim();
	}
	
	public java.lang.String getScenerowbias() {
		return this.scenerowbias;
	}
	public void setScenepathbias(java.lang.String scenepathbias) {
		this.scenepathbias = scenepathbias== null ? null : scenepathbias.trim();
	}
	
	public java.lang.String getScenepathbias() {
		return this.scenepathbias;
	}


}
