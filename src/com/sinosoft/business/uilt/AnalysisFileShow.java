package com.sinosoft.business.uilt;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AnalysisFileShow {
	
	/*public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//String path="C:\\Users\\yhl\\Desktop\\TAS_TPPS_FSAP_201612211721.xml";
		//parse1(path);
	}*/
	public static String parseArea(String parse) throws ParserConfigurationException, SAXException, IOException{
					// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器） 
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
					// step 2:获得具体的dom解析器  
					DocumentBuilder db = dbf.newDocumentBuilder(); 
					// step3: 解析一个xml文档，获得Document对象（根结点）
					Document document = db.parse(new File(parse)); //nodeInfo
					String area = document.getElementsByTagName("Area").item(0).getFirstChild().getNodeValue();
					System.out.println(area);
					return area;
	}
	public static void parsePoints(String parse) throws ParserConfigurationException, SAXException, IOException{
		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器） 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		// step 2:获得具体的dom解析器  
		DocumentBuilder db = dbf.newDocumentBuilder(); 
		// step3: 解析一个xml文档，获得Document对象（根结点）
		Document document = db.parse(new File(parse)); //nodeInfo
		/*Element nodeElement = (Element) document.getElementsByTagName("ProcessInfo");
		for(int m=0;m<nodeElement.getChildNodes().getLength();m++){
			String name = nodeElement.getChildNodes().item(m).getNodeName();
			String value = nodeElement.getChildNodes().item(m).getTextContent();
			System.out.println(name+"=="+value);
		}*/
		NodeList nodeList_param = document.getElementsByTagName("Fire");
		Node node = nodeList_param.item(0);
		if (node.getNodeType() == Node.ELEMENT_NODE){
			Element nodeElement = (Element) node;
			for(int m=0;m<nodeElement.getChildNodes().getLength();m++){
				String name = nodeElement.getChildNodes().item(m).getNodeName();
				if(!name.equals("#text")){
				String value = nodeElement.getChildNodes().item(m).getTextContent();
				System.out.println(name+"=="+value);
			}
			}
		}
	}
	
	
}
