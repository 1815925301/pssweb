package com.sinosoft.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sinosoft.business.po.PssOrderInfo;
import com.sun.tools.apt.Main;


@Service("csvutils")
public class CSVUtils {
	private static Logger logger=Logger.getLogger(CSVUtils.class);
	
	
	public static File createCSVFile(List exportDate,LinkedHashMap rowMapper,String outPutPath,String filename){
			File csvFile=null;
			BufferedWriter csvFileOutPutStream=null;
			try {
				csvFile =new File(outPutPath+filename+".csv");
				File parent=csvFile.getParentFile();
				if(parent != null && !parent.exists()){
					parent.mkdirs();
				}
				csvFile.createNewFile();
				csvFileOutPutStream=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile),"GB2312"),1024); 
				//写入文件头部
				
				for(Iterator propertyIterator=rowMapper.entrySet().iterator();propertyIterator.hasNext();){
					java.util.Map.Entry propertyEntry=(java.util.Map.Entry)propertyIterator.next();
					csvFileOutPutStream.write("\""+propertyEntry.getValue().toString()+"\"");
					if(propertyIterator.hasNext()){
						csvFileOutPutStream.write(",");
					}
				}
				csvFileOutPutStream.newLine();
				//写入文件内容
				int a = 0;
				for(Iterator iterator=exportDate.iterator();iterator.hasNext();){
					if(a==1){
						csvFileOutPutStream.newLine();//类似于换行,第一次进来不换行，接下来循环都要换行，不然都挤到一行了
					}
					Object row=(Object)iterator.next();
					for(Iterator propertyIterator=rowMapper.entrySet().iterator();propertyIterator.hasNext();){
						java.util.Map.Entry propertyEntry=(Entry) propertyIterator.next();
						csvFileOutPutStream.write("\""+BeanUtils.getProperty(row, propertyEntry.getKey().toString()).toString()+"\"");
						if(propertyIterator.hasNext()){
							csvFileOutPutStream.write(",");
						}
					}
					a=1;
					csvFileOutPutStream.flush();
				}
				
			} catch (Exception e) {
				logger.error(e);
			}finally{
				try {
					csvFileOutPutStream.close();
				} catch (IOException e2) {
					logger.error(e2);
				}
			}
		
		return csvFile;
	
	}
	
	
	public static void createCSV(List<PssOrderInfo> exportDate,String outPutPath,String filename){
		//1.需要对要展示的实体字段进行处理,要进行判断,没有赋值的字段要put("")。
		for(PssOrderInfo pssInfo : exportDate){
			//挨着获取生成cvs文件袋的字段进行判断----顺序一一对应
			if(pssInfo.getOrderid()==null){
				pssInfo.setOrderid(Long.valueOf(""));
			}
			if(pssInfo.getTasktype()==null){
				pssInfo.setTasktype(Integer.valueOf(""));
			}
			if(pssInfo.getUsername() ==null){
				pssInfo.setUsername("");
			}
			if(pssInfo.getProductlevel()==null){
				pssInfo.setProductlevel("");
			}
			if(pssInfo.getProductdate()==null){
				pssInfo.setProductdate("");
			}
		}
		
		//2.按顺序生成cvs,map里边存放的是表格字段
		LinkedHashMap<String,String> map=new LinkedHashMap<String,String>();
		map.put("orderid", "订单号");
		map.put("tasktype", "任务类型");
		map.put("username", "用户名");
		map.put("productlevel", "产品级别");
		map.put("productdate", "产品时间");
	
		CSVUtils.createCSVFile(exportDate, map, outPutPath,filename);
		
	}
	
	public static void main(String[] args) {
		List<PssOrderInfo> exportDate=new ArrayList<PssOrderInfo>();
		PssOrderInfo info = new PssOrderInfo();
		info.setUsername("yuan");//用户名
		info.setTasktype(2);//任务类型
		info.setProductid("123");//产品id
		//info.setProductlevel("");
		info.setChecktime("2016-01-09");
		exportDate.add(info);
		/*Map row1=new LinkedHashMap<String, String>();
		row1.put("1", "11");
		row1.put("2", "12");
		row1.put("3", "13");
		row1.put("4", "14");
		row1.put("5", "");
		exportDate.add(row1);*/
	/*	Map row2=new LinkedHashMap<String ,String>();
		row2.put("1", "21");
		row2.put("2", "22");
		row2.put("3", "23");
		row2.put("4", "24");
		exportDate.add(row2);*/
		LinkedHashMap<String,String> map=new LinkedHashMap<String,String>();
		map.put("username", "用户名");
		map.put("tasktype", "任务类型");
		map.put("productid", "产品id");
		map.put("productlevel", "产品级别");
		map.put("checktime", "审核时间");
		CSVUtils.createCSVFile(exportDate, map, "D:/","111目录");
	}
}
