package com.sinosoft.business.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinosoft.common.util.FTPClientHelper;


@Controller
public class AjaxFtpDownFile {
   
	
	 /** 
     * 根据路径下载Ftp中的文件 
     * @param remoteFile 
     * @param fileName 
     * @param request 
     * @param response 
     * @throws IOException 
     * 
     */
	@RequestMapping(method = { RequestMethod.POST,RequestMethod.GET}, value = "/ftpdown.do")
    public void downFtpFile(String remoteFile,String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException{  
        // 以流的形式下载文件。  
        FTPClientHelper ftpClient=new FTPClientHelper("/order");  
        byte[] buffer=null;  
        fileName=request.getParameter("filename");
        try {  
            buffer = ftpClient.downFileByte(fileName);//根据文件名下载FTP服务器上的文件  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        // 清空response  
        response.reset();  
        response.setContentType("text/html;charset=UTF-8");  
        // 设置response的Header  
        //        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"),"ISO-8859-1"));  
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(),"ISO-8859-1"));  
        //response.addHeader("Content-Length", "" + fis.length());  
        OutputStream toClient = new BufferedOutputStream(response.getOutputStream());  
        response.setContentType("application/octet-stream");  
        toClient.write(buffer);  
        toClient.flush();  
        toClient.close();  
        
    }  
}
