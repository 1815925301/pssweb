package com.sinosoft.common.util;
import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.util.ResourceBundle;  
  
import org.apache.commons.net.ftp.FTPClient;  
import org.apache.commons.net.ftp.FTPClientConfig;  
import org.apache.commons.net.ftp.FTPFile;  
import org.apache.commons.net.ftp.FTPReply;  
  
public class FTPClientHelper {  
      
        //FTP客户端  
        private FTPClient ftpClient;  
        /** 
         * 连接FTP服务器 
         * @param remotePath //设定当前需要操作的目录 
         */  
        public FTPClientHelper(String remotePath){  
              
            try{  
            ftpClient=new FTPClient();  
            ftpClient.connect("192.168.201.8", 21);//FTP服务器IP地址  
            ftpClient.login("123456", "123456");//FTP服务器用户名和密码  
            ftpClient.setDataTimeout(120000);  
              
            // 下面三行代码必须要，而且不能改变编码格式，否则不能正确下载中文文件            
            ftpClient.setControlEncoding("GBK");  
            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);               
            conf.setServerLanguageCode("zh");   
            System.out.println("Login Success!");  
            int reply=ftpClient.getReplyCode();  
            if(!FTPReply.isPositiveCompletion(reply)){  
                ftpClient.disconnect();  
                ftpClient=null;  
            }  
            //转移到设置的目录下  
            if(remotePath!=null && !remotePath.equals("")){  
                ftpClient.changeWorkingDirectory(remotePath);  
                System.out.println("file success");  
            }  
              
            }catch(Exception e){  
                e.printStackTrace();  
            }  
        }  
        
        /** 
         * 上传文件 
         * @param remoteFile 
         * @param localFile 
         */  
        public boolean upload( String localFile){  
            boolean bool=false;  
            if(ftpClient!=null){  
                try{  
                    File file=new File(localFile);  
                    String remoteFile=file.getName();  
                    System.out.println(remoteFile);  
                    InputStream is=new FileInputStream(file);  
                    ftpClient.storeFile(remoteFile, is);  
                    is.close();  
                    ftpClient.logout();  
                    bool=true;  
                }catch(Exception e){  
                    e.printStackTrace();  
                    System.out.println("上传文件失败！请检查系统FTP设置,并确认FTP服务启动");    
                }finally{  
                    closeConnect();  
                }  
            }  
              
            return bool;  
        }  
        /** 
             * 下载文件 
             * @param remoteFile 远程文件路径(服务器端) 
             * @param fileName 需要下载的文件名 
             * @param localFile 本地文件路径(客户端) 
             */  
          
            public boolean download(String fileName, String localPath) {  
                boolean bool=false;  
                if(ftpClient!=null){  
                try{  
                      //ftpClient.changeWorkingDirectory(remoteFile);//转移到FTP服务器目录    
                      FTPFile[] files= ftpClient.listFiles();  
                      for(FTPFile file:files){  
                          if(file.getName().equals(fileName)){  
                              System.out.println("---start---"+System.currentTimeMillis());  
                              String path=localPath+"\\"+file.getName();  
                              System.out.println(path);  
                              File localFile=new File(path);  
                              OutputStream ops=new FileOutputStream(localFile);  
                              ftpClient.retrieveFile(file.getName(), ops);  
                              ops.close();  
                              System.out.println("---ender---"+System.currentTimeMillis());  
                              bool=true;  
                              break;  
                          }  
                      }  
                }catch(Exception e){  
                    e.printStackTrace();  
                    System.out.println("下载文件失败！请检查系统FTP设置,并确认FTP服务启动");    
                }finally{  
                    closeConnect();  
                }  
                }  
                return bool;  
            }  
  
            // 删除文件至FTP通用方法    
  
            public  boolean deleteFileFtp(String fileName){    
                boolean bool=false;  
                if(ftpClient!=null){  
                   try {    
                       ftpClient.deleteFile(fileName);  
                       bool=true;  
                    } catch (Exception e) {    
                        System.out.println("删除文件失败！请确定文件是否存在");    
                   }finally{  
                       closeConnect();//关闭FTP连接  
                   }    
                }  
               return bool;  
            }  
             
        /** 
         * 下载文件 
         * 返回byte[] 
         * @param fileName 需要下载的文件名 
         * @return 
         * @throws Exception 
         */  
        public byte[] downFileByte(String fileName){  
            byte[] return_arraybyte=null;  
            if(ftpClient!=null){  
            try{  
          FTPFile[] files= ftpClient.listFiles();  
          for(FTPFile file:files){  
              if(file.getName().equals(fileName)){  
                InputStream ins=ftpClient.retrieveFileStream(file.getName());  
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();  
                byte[] buf = new byte[204800];  
                int bufsize = 0;  
                while ((bufsize = ins.read(buf, 0, buf.length)) != -1) {  
                    byteOut.write(buf, 0, bufsize);  
                }  
                return_arraybyte = byteOut.toByteArray();  
                byteOut.close();  
                ins.close();  
                break;  
              }  
          }  
            }catch(Exception e){  
                e.printStackTrace();  
            }finally{  
                closeConnect();  
            }  
            }  
        return return_arraybyte;  
    }  
        /** 
         * 关闭FTP连接 
         */  
        public void closeConnect(){  
            try{  
                ftpClient.disconnect();  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
        }  
          
  /*  public static void main(String[] args) {  
        FTPClientHelper test=new FTPClientHelper("/zhou");  
    }  */
  
      
  
}
