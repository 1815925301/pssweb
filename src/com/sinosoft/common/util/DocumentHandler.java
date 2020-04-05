package com.sinosoft.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import com.sinosoft.common.web.SinosoftModelMap;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocumentHandler {
	
	private Configuration configuration = null;  
	  
    public DocumentHandler() {  
        configuration = new Configuration();  
        configuration.setDefaultEncoding("utf-8");  
    }  
    
    public void createDoc(SinosoftModelMap modeMap,String fileName) throws UnsupportedEncodingException {  
        //dataMap 要填入模本的数据文件  
        //设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，  
        //这里我们的模板是放在template包下面  
        configuration.setClassForTemplateLoading(this.getClass(), "manage");  
        Template t=null;  
        try {  
            //test.ftl为要装载的模板  
            t = configuration.getTemplate("signPrintWord.ftl");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        //输出文档路径及名称  
        File outFile = new File(fileName);  
        Writer out = null;  
        FileOutputStream fos=null;  
        try {  
            fos = new FileOutputStream(outFile);  
            OutputStreamWriter oWriter = new OutputStreamWriter(fos,"UTF-8");  
            //这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。  
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));  
             out = new BufferedWriter(oWriter);   
        } catch (FileNotFoundException e1) {  
            e1.printStackTrace();  
        }  
           
        try {  
            t.process(modeMap, out);  
            out.close();  
            fos.close();  
        } catch (TemplateException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
        //System.out.println("---------------------------");  
    }  
/*    public static void main(String[] args) throws UnsupportedEncodingException {
    	 Map dataMap=new HashMap();  
		 dataMap.put("year", "2015");
		 dataMap.put("project_name", "test");
		 List<Registration> registrationList = new ArrayList<Registration>();
		for(int i=0;i<10;i++){
			Registration registration = new Registration();
			registration.setBidd_unitname("中科软科技");
			registration.setAuthorize_person("赵志强");
			registration.setTender_person("王国生");
			registration.setTender_personcall("13601250142");
			registrationList.add(registration);
		}
		dataMap.put("registrationList", registrationList);
		DocumentHandler mdoc = new DocumentHandler();
		mdoc.createDoc(dataMap, "d:/outFile.doc");  
		
		
    }*/
    
}
