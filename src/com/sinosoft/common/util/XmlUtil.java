package com.sinosoft.common.util;

import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.axis.wsdl.Java2WSDL;
import org.apache.log4j.Logger;

import org.dom4j.DocumentHelper;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.PssOrderInfo;

public class XmlUtil {
	
private static final  Logger log = Logger.getLogger(XmlUtil.class);

	
	public static void BuildXmlDoc(List<PssOrderInfo> list,String filepath)throws IOException,JDOMException{

		
		//创建根节点，并设置它的属性
		Element root =new Element("orderinfo");
		//将根节点添加到文档中
		 Document Doc=new Document(root);
		 
		 for(int i=0;i<list.size();i++){
		
			 //创建节点 
			 Element elements=new Element("order");
			 
			 //给order节点添加子节点并赋值
			 elements.addContent(new Element("orderid").setText(String.valueOf(list.get(i).getOrderid())));
			 elements.addContent(new Element("taskType").setText(String.valueOf(list.get(i).getTasktype())));
			 elements.addContent(new Element("tasKTime").setText(String.valueOf(list.get(i).getTasktime())));
			 elements.addContent(new Element("userName").setText(list.get(i).getUsername()));
			 elements.addContent(new Element("sceneid").setText(list.get(i).getSceneid()));
			 elements.addContent(new Element("productLevel").setText(list.get(i).getProductlevel()));
			 elements.addContent(new Element("note").setText(list.get(i).getNote()));
			 elements.addContent(new Element("price").setText(String.valueOf(list.get(i).getPrice())));
			 elements.addContent(new Element("producttype").setText(list.get(i).getProducttype()));
			 elements.addContent(new Element("imageingstarttime").setText(list.get(i).getImageingstarttime()));
			 elements.addContent(new Element("imageimgstoptime").setText(list.get(i).getImageimgstoptime()));
			 elements.addContent(new Element("scenepath").setText(String.valueOf(list.get(i).getScenepath())));
			 elements.addContent(new Element("scenerow").setText(String.valueOf(list.get(i).getScenerow())));
			 elements.addContent(new Element("dataupperleftlat").setText(String.valueOf(list.get(i).getDataupperleftlat())));
			 elements.addContent(new Element("dataupperleftlong").setText(String.valueOf(list.get(i).getDataupperleftlong())));
			 elements.addContent(new Element("dataupperrightlat").setText(String.valueOf(list.get(i).getDataupperrightlat())));
			 elements.addContent(new Element("dataupperrightupperlong").setText(String.valueOf(list.get(i).getDataupperrightupperlong())));
			 elements.addContent(new Element("datalowerleftlat").setText(String.valueOf(list.get(i).getDatalowerleftlat())));
			 elements.addContent(new Element("datalowerleftlong").setText(String.valueOf(list.get(i).getDatalowerleftlong())));
			 elements.addContent(new Element("datalowerrightlat").setText(String.valueOf(list.get(i).getDatalowerrightlat())));
			 elements.addContent(new Element("datalowerrightlong").setText(String.valueOf(list.get(i).getDatalowerrightlong())));
			 elements.addContent(new Element("bands").setText(String.valueOf(list.get(i).getBands())));
			 elements.addContent(new Element("satelliteid").setText(String.valueOf(list.get(i).getSatelliteid())));
			 elements.addContent(new Element("earthmodel").setText(String.valueOf(list.get(i).getEarthmodel())));
			 elements.addContent(new Element("sensorid").setText(String.valueOf(list.get(i).getSensorid())));
			 elements.addContent(new Element("friendlyprompt").setText(String.valueOf(list.get(i).getFriendlyprompt())));
			 root.addContent(elements);
		 }
		 //输出order.xml文件
		 Format format=Format.getPrettyFormat();
		 XMLOutputter XMLOut= new XMLOutputter(format);
		 XMLOut.output(Doc, new FileOutputStream(filepath));
		
		
	}
	

	
}
