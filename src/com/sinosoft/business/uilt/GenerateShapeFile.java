package com.sinosoft.business.uilt;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.geotools.data.DataStoreFactorySpi;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FeatureStore;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.factory.FactoryRegistryException;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureCollections;

import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;


/**************************************************************
 * @version 1.0
 * @日期：2012-04-26
 * @描述：该类负责将经纬度信息和属性信息写入生成标准的shape文件
 * @author wangwei
 **************************************************************
 */
public class GenerateShapeFile {
	
	 private static final Log log = LogFactory.getLog(GenerateShapeFile.class);
	 private static SimpleFeatureType simpleFeatureType;
	/**
	 * 此方法负责根据给定的data数据生成相应的shape文件
	 * @param filePath 生成shape文件的路径
	 * @param date 生成shape文件所需要的数据
	 * @param fileName 生成shape文件的文件名
	 */
	@SuppressWarnings("unchecked")
	public static void createShape(String filePath, String fileName, String point, String attributes) {
		//首先对经纬度中是否有存在包含关系进行检查，如果有包含关系则进行拆分处理
		//point = splitPoint(point);
		FeatureCollection featureCollection = null;
		// 获取要写入的数据源
		FeatureStore featureStore = getFeatureStore(filePath, fileName);
		
		DefaultTransaction transaction = new DefaultTransaction();
		try {
			featureStore.setTransaction(transaction);
			featureCollection = FeatureCollections.newCollection();
			GeometryFactory geomFac = JTSFactoryFinder.getGeometryFactory(null);
			SimpleFeatureBuilder simpleFeatureBuilder = new SimpleFeatureBuilder(simpleFeatureType);
			SimpleFeature feature = null;
			//申明面字符串数组
			String pointArr[]= point.split(";");
			String attrArr[] = attributes.split(";");
			//申明一个面对象数组
			Polygon polygons[] = new Polygon[pointArr.length];
			//第一次循环，遍历有多少个面
			for(int i=0;i<pointArr.length;i++){
				
				//申明一个点对象
				String latLongArr[] = pointArr[i].split(",");
				//申明点集合
				Coordinate[] coordinate = new Coordinate[latLongArr.length/2];
				//第二次循环，遍历每个面里面有对少组点
				for(int j = 0;j<latLongArr.length/2;j++){
				 	 coordinate[j]=new Coordinate(Double.parseDouble(latLongArr[j*2]), Double.parseDouble(latLongArr[j*2+1]));
					}
				
				

				//先判断是否有挖洞部分，如果有则初始化挖洞个数
				int holesCount = 0;
				Coordinate coordinateH1 =coordinate[0] ;
				for(int n = 0;n<coordinate.length;n++){
					if(coordinateH1.equals2D(coordinate[n+1])){
						if(n+2<coordinate.length){
							coordinateH1 =coordinate[n+2];
						}
						n=n+1;
						holesCount++;
						}
					}
				//构建封闭的线
				LinearRing linearRing = null;
				//构建洞对象数组
				LinearRing linearRings[] = new LinearRing[holesCount-1];
				Coordinate coordinateH =coordinate[0] ;
				int countLinearRing = 0;
				int countCoordinate = 0;
				for(int h = 0;h<coordinate.length;h++){
					if(coordinateH.equals2D(coordinate[h+1])){ 
						//初始化coordinatesH对象
						if(h+2<coordinate.length){
							coordinateH =coordinate[h+2];
						}
						Coordinate coordinatesH[] = new Coordinate[h+2-countCoordinate];
						for(int m = 0;m<h+2-countCoordinate;m++ ){
							coordinatesH[m]=coordinate[m+countCoordinate];
						}
						countCoordinate = h+2;
						h=h+1;
						if(countLinearRing==0){
							linearRing = geomFac.createLinearRing(coordinatesH);
						}else{
							linearRings[countLinearRing-1]= geomFac.createLinearRing(coordinatesH);
						}
						countLinearRing++;
					}
				}
				
				
				//构建封闭的线
				//LinearRing linearRing = geomFac.createLinearRing(coordinate);
				//构建面对象
				polygons[i] = geomFac.createPolygon(linearRing, linearRings);
				simpleFeatureBuilder.add(polygons[i]);
				
				feature = simpleFeatureBuilder.buildFeature(null);
				String attArr[] = attrArr[i].split("!");
				//设置变量中的值
				feature.setAttribute("PRODUCTID", attArr[0]);
				feature.setAttribute("SCENEID", attArr[1]);
				feature.setAttribute("STATELLITEI", attArr[2]);
				feature.setAttribute("SENSORID", attArr[3]);
				feature.setAttribute("TASKTIME", attArr[4]);
				feature.setAttribute("MAPPROJECT", attArr[5]);
				feature.setAttribute("EARTHMODEL", attArr[6]);
				feature.setAttribute("BROWSEFILE", attArr[7]);
				
				//SimpleFeature simpleFeatureObject = getSimpleFeature(point);
				if (!featureCollection.add(feature)) {
					log.error("数据集添加出错");
				}
			}
			featureStore.addFeatures(featureCollection);
			transaction.commit();
			log.info("**********生成shape文件结束*********");
			featureCollection.clear();
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			try {
				transaction.rollback();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			transaction.close();
		}
	}
	/**
	 * 将原字符串中有包含关系的面进行拆分，加上“;”号处理
	 * @param 原始串
	 * @return  修改后返回串
	 */
	private static String splitPoint(String point) {
		// TODO Auto-generated method stub
		
		//获取第一组经纬度点
		String pattren = point.substring(0,point.indexOf(",", point.indexOf(",")+1)+1);
		//找到重复的并截取重复串的后面部分进行继续
		String subPoint = point.substring(pattren.length());
		while (true) {
			if (subPoint.indexOf(pattren) == -1) {
				break;
		    //如果有重复的串,则进入		
			} else  {
				int indexOf = point.indexOf(pattren,point.indexOf(pattren)+1)+pattren.length();
				//满足条件则插入“;”号
				point = insertSemicolon(point,";",indexOf);
				//找到重复的并截取重复串的后面部分进行继续
				subPoint = subPoint.substring(subPoint.indexOf(pattren)+pattren.length());
				//加入分号后截取分号下一组继续判断是否有重复
				pattren = subPoint.substring(0,subPoint.indexOf(",", subPoint.indexOf(",")+1)+1);
				//再次截取重复部分
				subPoint = subPoint.substring(subPoint.indexOf(pattren)+pattren.length());
			}
		}
		return point.replaceAll(",;", ";");
	}
	/**
	 *  
	 * @param 原始串
	 * @param 要插入的值
	 * @param 插入到第几个位置
	 * @return插入之后的串
	 */
	private static String insertSemicolon(String point, String string,
			int indexOf) {
		// TODO Auto-generated method stub
		StringBuilder sb=new StringBuilder();
		 sb.append(point).insert(indexOf, string); 
		 return sb.toString();

		
	}
	/**
	 * 根据创建shape文件的路径和shape文件的文件名对其进行初始化
	 * @param filePath 生成shape文件的文件路径
	 * @param fileName 生成shape 文件的文件名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static FeatureStore getFeatureStore(String filePath, String fileName) {
	    
	    FeatureStore featureStore = null;
		// 判断生成shape文件的路径是否存在，如果不存在则按照filePath创建新文件路径
		File newFile = new File(filePath);
	    if (!newFile.exists()) {
	      newFile.mkdirs();
	    }
	    //生成绝对文件路径，包括文件名
	    File fileAbsoName = new File(filePath + "/" + fileName+".shp");

	    Map createMap = new HashMap();
	    try {
	    	createMap.put("url", fileAbsoName.toURI().toURL());
	    	createMap.put("create spatial index", Boolean.TRUE);

	      DataStoreFactorySpi dataStoreFactory = new ShapefileDataStoreFactory();
	      ShapefileDataStore newDataStore = (ShapefileDataStore)dataStoreFactory.createNewDataStore(createMap);
	      SimpleFeatureTypeBuilder builder = new SimpleFeatureTypeBuilder();

	      builder.setName(fileName);
	      //设置shape文件坐标系
	      builder.setCRS(DefaultGeographicCRS.WGS84);
	      //设置shape文件空间信息
	      builder.add("geom_type", MultiPolygon.class);
	      
	      builder.add("PRODUCTID",String.class);
	      builder.add("SCENEID",String.class);
	      builder.add("STATELLITEI",String.class);
	      builder.add("SENSORID",Double.class);
	      builder.add("TASKTIME",String.class);
	      builder.add("MAPPROJECT",String.class);
	      builder.add("EARTHMODEL",String.class);
	      builder.add("BROWSEFILE",String.class);
	      //设置shape文件属性信息
	      simpleFeatureType = builder.buildFeatureType();
	      //设置shape文件编码方式
	      newDataStore.setStringCharset(Charset.forName("GBK"));
	      newDataStore.createSchema(simpleFeatureType);

	      featureStore = (FeatureStore)newDataStore.getFeatureSource(fileName);
	    }
	    catch (MalformedURLException e) {
	      e.printStackTrace();
	    }
	    catch (FactoryRegistryException e) {
	      e.printStackTrace();
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	    return featureStore;
	}
	/**
	 * 将查询出的数据构造成一个SimpleFeature对象
	 * @param data 数据对象集合
	 * @return
	 */
	public static SimpleFeature getSimpleFeature(String point) {
		GeometryFactory geomFac = JTSFactoryFinder.getGeometryFactory(null);
		SimpleFeatureBuilder simpleFeatureBuilder = new SimpleFeatureBuilder(simpleFeatureType);
		SimpleFeature feature = null;
		//申明面字符串数组
		String pointArr[]= point.split(";");
		//申明一个面对象数组
		Polygon polygons[] = new Polygon[pointArr.length];
		//第一次循环，遍历有多少个面
		for(int i=0;i<pointArr.length;i++){
			//申明一个点对象
			String latLongArr[] = pointArr[i].split(",");
			//申明点集合
			Coordinate[] coordinate = new Coordinate[latLongArr.length/2];
			//第二次循环，遍历每个面里面有对少组点
			for(int j = 0;j<latLongArr.length/2;j++){
			 	 coordinate[j]=new Coordinate(Double.parseDouble(latLongArr[j*2]), Double.parseDouble(latLongArr[j*2+1]));
				}
			//构建封闭的线
			LinearRing linearRing = geomFac.createLinearRing(coordinate);
			//构建面对象
			polygons[i] = geomFac.createPolygon(linearRing, null);
			simpleFeatureBuilder.add(polygons[i]);
			feature = simpleFeatureBuilder.buildFeature(null);
			//设置变量中的值
			feature.setAttribute("qqq", "w");
			}
		//
//		MultiPolygon ms = geomFac.createMultiPolygon(polygons);
	
//		simpleFeatureBuilder.add(polygons);
//		feature = simpleFeatureBuilder.buildFeature(null);
		//设置变量中的值
//		feature.setAttribute("", "");
		return feature;
	}
}
