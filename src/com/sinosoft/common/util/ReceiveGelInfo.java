package com.sinosoft.common.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sinosoft.common.constant.MappingConstantConfig;


/**
 * 
* @ClassName: ReceiveGelInfo 
* @Description: TODO
* @author zzq
* @mail zzq1229@126.com 
* @date 2014年8月20日 下午1:59:10 
*
 */
public class ReceiveGelInfo extends HttpServlet{
	/** 
	* @Fields serialVersionUID : TODO
	*/ 
	private static final long serialVersionUID = 1L;
	
	private String path = MappingConstantConfig.getValue("APP_BASE_PATH")
			+ MappingConstantConfig.getValue("IMAGE_PATH");
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		fileControl(request, response);
	}
	 /**
      * 上传文件的处理
	 * @throws IOException 
      */
     private void fileControl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 

    	 boolean isMultipart=ServletFileUpload.isMultipartContent(request);
    	 if(!isMultipart){
    	     return;
    	 }

    	 DiskFileItemFactory factory=new DiskFileItemFactory();
    	 ServletFileUpload upload=new ServletFileUpload(factory);
    	 upload.setFileSizeMax(1024*1024);//the max size of the upload file
    	 List<FileItem> items=null;
    	 try
    	 {
    	     items=upload.parseRequest(request);
    	 }
    	 catch(FileUploadException e)
    	 {
    	     e.printStackTrace();
    	 }
    	 
    	 PrintWriter out = response.getWriter();
    	 for(FileItem item:items)
    	 {
    	     if (item.isFormField()) {
    	 		System.out.println("Field:" + item.getFieldName() + ",Value:" + item.getString("utf-8"));
    	     } else {
    	         System.out.println("Field:" + item.getFieldName() + ",File:" + item.getName());
    	 		if(item.getName().length() > 0){
    	 	    	File fullFile=new File(item.getName());
		        	String fileExtName = FileUtil.getFileExtName(item.getName(), "SUFFIXIMAGE_PATH");
		        	String fileName = CreateGUID.getGUID() + "." + fileExtName;
		        	 
    	 	    	File uploadFile=new File(path,fileName);
    	 	    	try{
    	 	    		item.write(uploadFile);
    	 	    		out.println(fileName);
    	 	    	}
    	 	    	catch(Exception e){
    	 	    		e.printStackTrace();
    	 	    	}
    	 		}

    	     }
    	 }
     }
	
}
