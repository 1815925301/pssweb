package com.sinosoft.business.po.query;

import java.util.List;

import com.sinosoft.po.query.BasePaginationQuery;

public class PssProductQuery extends BasePaginationQuery{
	       public String productid;
	       public String sceneid;
	       public String satelliteid;
	       public String sensorid;
	       public String productdate;
	       public String productlevel;
	       public String bands;
	       public String dataformatdes;
	       public int cloudcoverage;//云盖
	       public String producttype;
	       public String scenecount;
	       public String sceneshift;
	       public String gain;
	       public String intergrallevel;
	       public String sunelevation;
	       public String sunazimuth;
	       public String scenedate;
	       public String imageingstarttime;
	       public String imageingstoptime;
	       public int satpath;
	       public int satrow;
	       public String centerlat;
	       public String centerlong;
	       
		   public String topleftlatitude;
	       public String topleftlongitude;
	       public String toprightlatitude;
	       public String toprightlongitude;
	       public String bottomrightlatitude;
	       public String bottomrightlongitude;
	       public String bottomleftlatitude;
	       public String bottomleftlongitude;
	       
	       public String mtfcpromode;
	       public String imagename;
	       public String browsename;
	       public String thumbname;
	       public String metadataid;
	       public String isspatial;
	       public int orbitid;
	       public int quality;
	       public String recstationid;
	       public String scenerowbias;
	       public String scenepathbias;
	       public String shape;//传的条件shape，做空间查询
	       public String tablename;//根据级别查询不同的表的名字
	       public int minPath;//最小path
	       public int maxPath;//最大path
	       public int minRow;//最小row
	       public int maxRow;//最大row
	       public int mingsd;//最小分辨率
	       public int maxgsd;//最大分辨率
	       public String sceneStarttime;//采集开始时间
	       public String sceneEndtime;//采集结束时间
	       public int mincloudCoverage;//最小云盖
	       public int maxcloudCoverage;//最大云盖
	       public String scenepath;//子path
	       public String  scenerow;//子row
	       public String  productStarttime;//生产开始时间
	       public String productEndtime;//生产结束时间
	       public String difference_table;//查询表的不同
	       public String shapename;//导入 shape名字
	       public String country;//行政区划-国家
	       public String states;//行政区划-州
	       public List<String> sensorid_array;//sensorid数组
	       public List<String> productid_array;//产品id数组
	       public List<String> sceneid_array;//景号id数组
	       public List<Integer> orbitid_array;//轨道号id数组
	       
	    public List<String> getSceneid_array() {
			return sceneid_array;
		}
		public void setSceneid_array(List<String> sceneid_array) {
			this.sceneid_array = sceneid_array;
		}
		public List<Integer> getOrbitid_array() {
			return orbitid_array;
		}
		public void setOrbitid_array(List<Integer> orbitid_array) {
			this.orbitid_array = orbitid_array;
		}
		public List<String> getProductid_array() {
			return productid_array;
		}
		public void setProductid_array(List<String> productid_array) {
			this.productid_array = productid_array;
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
	       
	       
	    public int getOrbitid() {
			return orbitid;
		}
		public void setOrbitid(int orbitid) {
			this.orbitid = orbitid;
		}
		public int getQuality() {
			return quality;
		}
		public void setQuality(int quality) {
			this.quality = quality;
		}
		public String getScenepath() {
			return scenepath;
		}
		public void setScenepath(String scenepath) {
			this.scenepath = scenepath;
		}
		public String getScenerow() {
			return scenerow;
		}
		public void setScenerow(String scenerow) {
			this.scenerow = scenerow;
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
		public String getShapename() {
			return shapename;
		}
		public void setShapename(String shapename) {
			this.shapename = shapename;
		}
		public String getDifference_table() {
			return difference_table;
		}
		public void setDifference_table(String difference_table) {
			this.difference_table = difference_table;
		}
	       
		public List<String> getSensorid_array() {
			return sensorid_array;
		}
		public void setSensorid_array(List<String> sensorid_array) {
			this.sensorid_array = sensorid_array;
		}
		public int getMingsd() {
			return mingsd;
		}
		public void setMingsd(int mingsd) {
			this.mingsd = mingsd;
		}
		public int getMaxgsd() {
			return maxgsd;
		}
		public void setMaxgsd(int maxgsd) {
			this.maxgsd = maxgsd;
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
		public int getMincloudCoverage() {
			return mincloudCoverage;
		}
		public void setMincloudCoverage(int mincloudCoverage) {
			this.mincloudCoverage = mincloudCoverage;
		}
		public int getMaxcloudCoverage() {
			return maxcloudCoverage;
		}
		public void setMaxcloudCoverage(int maxcloudCoverage) {
			this.maxcloudCoverage = maxcloudCoverage;
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
		public int getMinPath() {
			return minPath;
		}
		public void setMinPath(int minPath) {
			this.minPath = minPath;
		}
		public int getMaxPath() {
			return maxPath;
		}
		public void setMaxPath(int maxPath) {
			this.maxPath = maxPath;
		}
		public int getMinRow() {
			return minRow;
		}
		public void setMinRow(int minRow) {
			this.minRow = minRow;
		}
		public int getMaxRow() {
			return maxRow;
		}
		public void setMaxRow(int maxRow) {
			this.maxRow = maxRow;
		}
		public int getSatpath() {
			return satpath;
		}
		public void setSatpath(int satpath) {
			this.satpath = satpath;
		}
		public int getSatrow() {
			return satrow;
		}
		public void setSatrow(int satrow) {
			this.satrow = satrow;
		}
		public String getTablename() {
			return tablename;
		}
		public void setTablename(String tablename) {
			this.tablename = tablename;
		}
		public String getShape() {
			return shape;
		}
		public void setShape(String shape) {
			this.shape = shape;
		}
		/* public String createStarttime;
	       public String createEndtime;
	       public String gds0;
	       public String gds1;*/
		public String getProductid() {
			return productid;
		}
		public void setProductid(String productid) {
			this.productid = productid;
		}
		public String getSceneid() {
			return sceneid;
		}
		public void setSceneid(String sceneid) {
			this.sceneid = sceneid;
		}
		public String getSatelliteid() {
			return satelliteid;
		}
		public void setSatelliteid(String satelliteid) {
			this.satelliteid = satelliteid;
		}
		public String getSensorid() {
			return sensorid;
		}
		public void setSensorid(String sensorid) {
			this.sensorid = sensorid;
		}
		public String getProductdate() {
			return productdate;
		}
		public void setProductdate(String productdate) {
			this.productdate = productdate;
		}
		public String getProductlevel() {
			return productlevel;
		}
		public void setProductlevel(String productlevel) {
			this.productlevel = productlevel;
		}
		public String getBands() {
			return bands;
		}
		public void setBands(String bands) {
			this.bands = bands;
		}
		public String getDataformatdes() {
			return dataformatdes;
		}
		public void setDataformatdes(String dataformatdes) {
			this.dataformatdes = dataformatdes;
		}
		
		public int getCloudcoverage() {
			return cloudcoverage;
		}
		public void setCloudcoverage(int cloudcoverage) {
			this.cloudcoverage = cloudcoverage;
		}
		public String getProducttype() {
			return producttype;
		}
		public void setProducttype(String producttype) {
			this.producttype = producttype;
		}
		public String getScenecount() {
			return scenecount;
		}
		public void setScenecount(String scenecount) {
			this.scenecount = scenecount;
		}
		public String getSceneshift() {
			return sceneshift;
		}
		public void setSceneshift(String sceneshift) {
			this.sceneshift = sceneshift;
		}
		public String getGain() {
			return gain;
		}
		public void setGain(String gain) {
			this.gain = gain;
		}
		public String getIntergrallevel() {
			return intergrallevel;
		}
		public void setIntergrallevel(String intergrallevel) {
			this.intergrallevel = intergrallevel;
		}
		public String getSunelevation() {
			return sunelevation;
		}
		public void setSunelevation(String sunelevation) {
			this.sunelevation = sunelevation;
		}
		public String getSunazimuth() {
			return sunazimuth;
		}
		public void setSunazimuth(String sunazimuth) {
			this.sunazimuth = sunazimuth;
		}
		public String getScenedate() {
			return scenedate;
		}
		public void setScenedate(String scenedate) {
			this.scenedate = scenedate;
		}
		public String getImageingstarttime() {
			return imageingstarttime;
		}
		public void setImageingstarttime(String imageingstarttime) {
			this.imageingstarttime = imageingstarttime;
		}
		public String getImageingstoptime() {
			return imageingstoptime;
		}
		public void setImageingstoptime(String imageingstoptime) {
			this.imageingstoptime = imageingstoptime;
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

		
		public String getMtfcpromode() {
			return mtfcpromode;
		}
		public void setMtfcpromode(String mtfcpromode) {
			this.mtfcpromode = mtfcpromode;
		}
		public String getImagename() {
			return imagename;
		}
		public void setImagename(String imagename) {
			this.imagename = imagename;
		}
		public String getBrowsename() {
			return browsename;
		}
		public void setBrowsename(String browsename) {
			this.browsename = browsename;
		}
		public String getThumbname() {
			return thumbname;
		}
		public void setThumbname(String thumbname) {
			this.thumbname = thumbname;
		}
		public String getMetadataid() {
			return metadataid;
		}
		public void setMetadataid(String metadataid) {
			this.metadataid = metadataid;
		}
		public String getIsspatial() {
			return isspatial;
		}
		public void setIsspatial(String isspatial) {
			this.isspatial = isspatial;
		}
		
		public String getRecstationid() {
			return recstationid;
		}
		public void setRecstationid(String recstationid) {
			this.recstationid = recstationid;
		}
		public String getScenerowbias() {
			return scenerowbias;
		}
		public void setScenerowbias(String scenerowbias) {
			this.scenerowbias = scenerowbias;
		}
		public String getScenepathbias() {
			return scenepathbias;
		}
		public void setScenepathbias(String scenepathbias) {
			this.scenepathbias = scenepathbias;
		}
	/*	public String getCreateStarttime() {
			return createStarttime;
		}
		public void setCreateStarttime(String createStarttime) {
			this.createStarttime = createStarttime;
		}
		public String getCreateEndtime() {
			return createEndtime;
		}
		public void setCreateEndtime(String createEndtime) {
			this.createEndtime = createEndtime;
		}
		public String getGds0() {
			return gds0;
		}
		public void setGds0(String gds0) {
			this.gds0 = gds0;
		}
		public String getGds1() {
			return gds1;
		}
		public void setGds1(String gds1) {
			this.gds1 = gds1;
		}*/	
	       

}
