
package com.sinosoft.business.po.query;

import java.util.List;

import com.sinosoft.po.query.BasePaginationQuery;



public class PssTasMetadataQuery  extends BasePaginationQuery {
	
	
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
	private String imagewidth;
	
	
	 //影像的高       数据库字段: IMAGEHEIGHT 
	private String imageheight;
	
	
	 //产品波段数目       数据库字段: IMAGEBANDNUM 
	private String imagebandnum;
	
	
	 //成像开始时间       数据库字段: IMAGINGSTARTTIME 
	private java.util.Date imagingstarttime;
	
	
	 //成像结束时间       数据库字段: IMAGINGSTOPTIME 
	private java.util.Date imagingstoptime;
	
	
	 //太阳方位角       数据库字段: SUNAZIMUTH 
	private String sunazimuth;
	
	
	 //太阳高度角       数据库字段: SUNELEVATION 
	private String sunelevation;
	
	
	 //地球模型       数据库字段: EARTHMODEL 
	private String earthmodel;
	
	
	 //地图投影       数据库字段: MAPPROJECTION 
	private String mapprojection;
	
	
	 //像素单位       数据库字段: UNIT 
	private String unit;
	
	
	 //影像X方向分辨率       数据库字段: RESOLUTIONX 
	private String resolutionx;
	
	
	 //影像Y方向分辨率       数据库字段: RESOLUTIONY 
	private String resolutiony;
	private String tablename;//根据级别查询不同的表的名字
	

	
	
	 public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	//景中心纬度       数据库字段: SCENECENTERLAT 
	 public String centerlat;
    
	
	
	//景中心经度       数据库字段: SCENECENTERLONG 
	 public String centerlong;
	//图像左上角纬度   
	public String topleftlatitude;
	
	//图像左上角经度
    public String topleftlongitude;
    //图像右上角纬度   
    public String toprightlatitude;
    //图像右上角经度   
    public String toprightlongitude;
    //图像右下角纬度
    public String bottomrightlatitude;
    //图像右下角经度
    public String bottomrightlongitude;
  //图像左下角纬度
    public String bottomleftlatitude;
  //图像左下角纬度
   public String bottomleftlongitude;
   
   public String sceneStarttime;//采集开始时间
   
public String sceneEndtime;//采集结束时间
   public String productStarttime;//生产开始时间
   public String productEndtime;//生产结束时间
   public List<String> sensorid_array;//sensorid数组
   public String difference_table;//查询表的不同
   public String shapename;//导入 shape名字
   public String country;//行政区划-国家
   public String states;//行政区划-州
   public List<String> producttypes_array;//sensorid数组
   
	public List<String> getProducttypes_array() {
	return producttypes_array;
}
public void setProducttypes_array(List<String> producttypes_array) {
	this.producttypes_array = producttypes_array;
}
	private String metadataid;
	
	
	
	public String getMetadataid() {
		return metadataid;
	}

	public void setMetadataid(String metadataid) {
		this.metadataid = metadataid;
	}
   
   /* //图像左上角纬度       数据库字段: DATAUPPERLEFTLAT 
	private String dataupperleftlat;
	 //图像左上角经度       数据库字段: DATAUPPERLEFTLONG 
	private String dataupperleftlong;
	
	
	 //图像右上角纬度       数据库字段: DATAUPPERRIGHTLAT 
	private String dataupperrightlat;
	
	
	 //图像右上角经度       数据库字段: DATAUPPERRIGHTLONG 
	private String dataupperrightlong;
	
	
	 //图像左下角纬度       数据库字段: DATALOWERLEFTLAT 
	private String datalowerleftlat;
	
	
	 //图像左下角经度       数据库字段: DATALOWERLEFTLONG 
	private String datalowerleftlong;
	
	
	 //图像右下角纬度       数据库字段: DATALOWERRIGHTLAT 
	private String datalowerrightlat;
	
	
	 //图像右下角经度       数据库字段: DATALOWERRIGHTLONG 
	private String datalowerrightlong;*/
	
	
	//连续处理次数       数据库字段: PROCESSNUM 
	private String processnum;
	
	
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
	
    public String shape;//传的条件shape，做空间查询
	private String sqlWhere;
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
	public void setProductid(String productid) {
		this.productid = productid;
	}
	
	public String getProductid() {
		return this.productid;
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
		this.sensorid = sensorid;
	}
	
	public String getSensorid() {
		return this.sensorid;
	}
	public void setStationid(String stationid) {
		this.stationid = stationid;
	}
	
	public String getStationid() {
		return this.stationid;
	}
	public void setPixeltype(String pixeltype) {
		this.pixeltype = pixeltype;
	}
	
	public String getPixeltype() {
		return this.pixeltype;
	}
	public void setImagewidth(String imagewidth) {
		this.imagewidth = imagewidth;
	}
	
	public String getImagewidth() {
		return this.imagewidth;
	}
	public void setImageheight(String imageheight) {
		this.imageheight = imageheight;
	}
	
	public String getImageheight() {
		return this.imageheight;
	}
	public void setImagebandnum(String imagebandnum) {
		this.imagebandnum = imagebandnum;
	}
	
	public String getImagebandnum() {
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
	public void setSunazimuth(String sunazimuth) {
		this.sunazimuth = sunazimuth;
	}
	
	public String getSunazimuth() {
		return this.sunazimuth;
	}
	public void setSunelevation(String sunelevation) {
		this.sunelevation = sunelevation;
	}
	
	public String getSunelevation() {
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
	public void setResolutionx(String resolutionx) {
		this.resolutionx = resolutionx;
	}
	
	public String getResolutionx() {
		return this.resolutionx;
	}
	public void setResolutiony(String resolutiony) {
		this.resolutiony = resolutiony;
	}
	
	public String getResolutiony() {
		return this.resolutiony;
	}
	
	
	
	public void setProcessnum(String processnum) {
		this.processnum = processnum;
	}
	
	public String getProcessnum() {
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
	public String getTopleftlatitude() {
		return topleftlatitude;
	}
	public void setTopleftlatitude(String topleftlatitude) {
		this.topleftlatitude = topleftlatitude;
	}
	public String getTopleftlongitude() {
		return topleftlongitude;
	}
	public void setTopleftlongitude(String topleftlongitude) {
		this.topleftlongitude = topleftlongitude;
	}
	public String getToprightlatitude() {
		return toprightlatitude;
	}
	public void setToprightlatitude(String toprightlatitude) {
		this.toprightlatitude = toprightlatitude;
	}
	public String getToprightlongitude() {
		return toprightlongitude;
	}
	public void setToprightlongitude(String toprightlongitude) {
		this.toprightlongitude = toprightlongitude;
	}
	public String getBottomrightlatitude() {
		return bottomrightlatitude;
	}
	public void setBottomrightlatitude(String bottomrightlatitude) {
		this.bottomrightlatitude = bottomrightlatitude;
	}
	public String getBottomrightlongitude() {
		return bottomrightlongitude;
	}
	public void setBottomrightlongitude(String bottomrightlongitude) {
		this.bottomrightlongitude = bottomrightlongitude;
	}
	public String getBottomleftlatitude() {
		return bottomleftlatitude;
	}
	public void setBottomleftlatitude(String bottomleftlatitude) {
		this.bottomleftlatitude = bottomleftlatitude;
	}
	public String getBottomleftlongitude() {
		return bottomleftlongitude;
	}
	public void setBottomleftlongitude(String bottomleftlongitude) {
		this.bottomleftlongitude = bottomleftlongitude;
	}
	 public String getCenterlat() {
			return centerlat;
		}
		public void setCenterlat(String centerlat) {
			this.centerlat = centerlat;
		}
		public String getCenterlong() {
			return centerlong;
		}
		public void setCenterlong(String centerlong) {
			this.centerlong = centerlong;
		}
		public String getSceneStarttime() {
			return sceneStarttime;
		}
		public void setSceneStarttime(String sceneStarttime) {
			this.sceneStarttime = sceneStarttime;
		}
		public String getSceneEndtime() {
			return sceneEndtime;
		}
		public void setSceneEndtime(String sceneEndtime) {
			this.sceneEndtime = sceneEndtime;
		}
		public String getProductStarttime() {
			return productStarttime;
		}
		public void setProductStarttime(String productStarttime) {
			this.productStarttime = productStarttime;
		}
		public String getProductEndtime() {
			return productEndtime;
		}
		public void setProductEndtime(String productEndtime) {
			this.productEndtime = productEndtime;
		}
		public List<String> getSensorid_array() {
			return sensorid_array;
		}
		public void setSensorid_array(List<String> sensorid_array) {
			this.sensorid_array = sensorid_array;
		}
		public String getDifference_table() {
			return difference_table;
		}
		public void setDifference_table(String difference_table) {
			this.difference_table = difference_table;
		}
		public String getShapename() {
			return shapename;
		}
		public void setShapename(String shapename) {
			this.shapename = shapename;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getStates() {
			return states;
		}
		public void setStates(String states) {
			this.states = states;
		}
}
