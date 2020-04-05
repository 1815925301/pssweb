
package com.sinosoft.business.po;




public class PssTasMetadata {
	
	
    //产品序列号       数据库字段: PRODUCTID 
	private String productid;
	
	
    //产品名称       数据库字段: PRODUCTNAME 
	private String productname;
	
	
    //产品类型       数据库字段: PRODUCTTYPE 
	private String producttype;
	
	
    //卫星标识       数据库字段: SATELLITEID 
	private String satelliteid;
	
	
    //载荷标识       数据库字段: SENSORID 
	private String sensorid;
	
	
    //接收站标识       数据库字段: STATIONID 
	private String stationid;
	
	
    //像素值类型       数据库字段: PIXELTYPE 
	private String pixeltype;
	
	
    //影像的宽       数据库字段: IMAGEWIDTH 
	private Double imagewidth;
	
	
    //影像的高       数据库字段: IMAGEHEIGHT 
	private Double imageheight;
	
	
    //产品波段数目       数据库字段: IMAGEBANDNUM 
	private Double imagebandnum;
	
	
    //成像开始时间       数据库字段: IMAGINGSTARTTIME 
	private java.util.Date imagingstarttime;
	
	
    //成像结束时间       数据库字段: IMAGINGSTOPTIME 
	private java.util.Date imagingstoptime;
	
	
    //太阳方位角       数据库字段: SUNAZIMUTH 
	private Double sunazimuth;
	
	
    //太阳高度角       数据库字段: SUNELEVATION 
	private Double sunelevation;
	
	
    //地球模型       数据库字段: EARTHMODEL 
	private String earthmodel;
	
	
    //地图投影       数据库字段: MAPPROJECTION 
	private String mapprojection;
	
	
    //像素单位       数据库字段: UNIT 
	private String unit;
	
	
    //影像X方向分辨率       数据库字段: RESOLUTIONX 
	private Double resolutionx;
	
	
    //影像Y方向分辨率       数据库字段: RESOLUTIONY 
	private Double resolutiony;
	
	
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
	
	
    //图像右上角经度       数据库字段: DATAUPPERRIGHTLONG 
	private Double dataupperrightlong;
	
	
    //图像左下角纬度       数据库字段: DATALOWERLEFTLAT 
	private Double datalowerleftlat;
	
	
    //图像左下角经度       数据库字段: DATALOWERLEFTLONG 
	private Double datalowerleftlong;
	
	
    //图像右下角纬度       数据库字段: DATALOWERRIGHTLAT 
	private Double datalowerrightlat;
	
	
    //图像右下角经度       数据库字段: DATALOWERRIGHTLONG 
	private Double datalowerrightlong;
	
	
    //连续处理次数       数据库字段: PROCESSNUM 
	private Double processnum;
	
	
    //处理信息标签1,处理信息标签2与处理信息标签1相同，可追加项       数据库字段: PROCESSINFO 
	private String processinfo;
	
	
    //处理时间       数据库字段: PROCESSTIME 
	private java.util.Date processtime;
	
	
    //处理模块       数据库字段: PROCESSMODULE 
	private String processmodule;
	
	
    //处理输入数据名,多个输入数据文件名称使用“,”分隔       数据库字段: INPUTDATANAME 
	private String inputdataname;
	
	
    //产品浏览图文件名称       数据库字段: BROWSENAME 
	private String browsename;
	
	
    //产品拇指图文件名称       数据库字段: THUMBNAME 
	private String thumbname;
	
	private String metadataid;
	
	
	
	public String getMetadataid() {
		return metadataid;
	}

	public void setMetadataid(String metadataid) {
		this.metadataid = metadataid;
	}

	
	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public void setProductname(String productname) {
		this.productname = productname== null ? null : productname.trim();
	}
	
	public String getProductname() {
		return this.productname;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype== null ? null : producttype.trim();
	}
	
	public String getProducttype() {
		return this.producttype;
	}
	public void setSatelliteid(String satelliteid) {
		this.satelliteid = satelliteid== null ? null : satelliteid.trim();
	}
	
	public String getSatelliteid() {
		return this.satelliteid;
	}
	public void setSensorid(String sensorid) {
		this.sensorid = sensorid== null ? null : sensorid.trim();
	}
	
	public String getSensorid() {
		return this.sensorid;
	}
	public void setStationid(String stationid) {
		this.stationid = stationid== null ? null : stationid.trim();
	}
	
	public String getStationid() {
		return this.stationid;
	}
	
	public String getPixeltype() {
		return pixeltype;
	}

	public void setPixeltype(String pixeltype) {
		this.pixeltype = pixeltype;
	}

	public void setImagewidth(Double imagewidth) {
		this.imagewidth = imagewidth;
	}
	
	public Double getImagewidth() {
		return this.imagewidth;
	}
	public void setImageheight(Double imageheight) {
		this.imageheight = imageheight;
	}
	
	public Double getImageheight() {
		return this.imageheight;
	}
	public void setImagebandnum(Double imagebandnum) {
		this.imagebandnum = imagebandnum;
	}
	
	public Double getImagebandnum() {
		return this.imagebandnum;
	}
	
	public void setImagingstarttime(java.util.Date imagingstarttime) {
		this.imagingstarttime = imagingstarttime;
	}
	
	public java.util.Date getImagingstarttime() {
		return this.imagingstarttime;
	}
	
	public void setImagingstoptime(java.util.Date imagingstoptime) {
		this.imagingstoptime = imagingstoptime;
	}
	
	public java.util.Date getImagingstoptime() {
		return this.imagingstoptime;
	}
	public void setSunazimuth(Double sunazimuth) {
		this.sunazimuth = sunazimuth;
	}
	
	public Double getSunazimuth() {
		return this.sunazimuth;
	}
	public void setSunelevation(Double sunelevation) {
		this.sunelevation = sunelevation;
	}
	
	public Double getSunelevation() {
		return this.sunelevation;
	}
	public void setEarthmodel(String earthmodel) {
		this.earthmodel = earthmodel== null ? null : earthmodel.trim();
	}
	
	public String getEarthmodel() {
		return this.earthmodel;
	}
	public void setMapprojection(String mapprojection) {
		this.mapprojection = mapprojection== null ? null : mapprojection.trim();
	}
	
	public String getMapprojection() {
		return this.mapprojection;
	}
	public void setUnit(String unit) {
		this.unit = unit== null ? null : unit.trim();
	}
	
	public String getUnit() {
		return this.unit;
	}
	public void setResolutionx(Double resolutionx) {
		this.resolutionx = resolutionx;
	}
	
	public Double getResolutionx() {
		return this.resolutionx;
	}
	public void setResolutiony(Double resolutiony) {
		this.resolutiony = resolutiony;
	}
	
	public Double getResolutiony() {
		return this.resolutiony;
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
	public void setDataupperrightlong(Double dataupperrightlong) {
		this.dataupperrightlong = dataupperrightlong;
	}
	
	public Double getDataupperrightlong() {
		return this.dataupperrightlong;
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
	public void setProcessnum(Double processnum) {
		this.processnum = processnum;
	}
	
	public Double getProcessnum() {
		return this.processnum;
	}
	public void setProcessinfo(String processinfo) {
		this.processinfo = processinfo== null ? null : processinfo.trim();
	}
	
	public String getProcessinfo() {
		return this.processinfo;
	}
	
	public void setProcesstime(java.util.Date processtime) {
		this.processtime = processtime;
	}
	
	public java.util.Date getProcesstime() {
		return this.processtime;
	}
	public void setProcessmodule(String processmodule) {
		this.processmodule = processmodule== null ? null : processmodule.trim();
	}
	
	public String getProcessmodule() {
		return this.processmodule;
	}
	public void setInputdataname(String inputdataname) {
		this.inputdataname = inputdataname== null ? null : inputdataname.trim();
	}
	
	public String getInputdataname() {
		return this.inputdataname;
	}
	public void setBrowsename(String browsename) {
		this.browsename = browsename== null ? null : browsename.trim();
	}
	
	public String getBrowsename() {
		return this.browsename;
	}
	public void setThumbname(String thumbname) {
		this.thumbname = thumbname== null ? null : thumbname.trim();
	}
	
	public String getThumbname() {
		return this.thumbname;
	}


}
