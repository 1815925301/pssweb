package com.sinosoft.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.constant.MappingConstantConfig;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: FileUtils
 * @Description: 文件处理工具类
 * @author zzq
 * @Version V1.0
 * @date 2013-11-3 下午05:55:37
 */
public class FileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    
    /**
     * <P>根据文件绝对路径获取文件名称</P>
     * @param filePhysicalPath
     * @return String
     * @throws
     * @author zzq
     * @date 2013-11-3 下午05:58:00
     * @version V1.0
     */
    public static String getFileName(String filePhysicalPath) {
    	if(filePhysicalPath.equals(""))
    	    return "";
    	int pos = filePhysicalPath.lastIndexOf(Constant.SEPARATOR);
    	if(pos < 0)
    	    return "";
    	return filePhysicalPath.substring(pos+1);
        
    }
    
    /**
     * 删除单个文件
     * @param sPat 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     * @throws
     * @author zzq
     * @date 2014-2-26 上午01:24:16
     * @version V1.0
     */
    public static void deleteFile(String sPath) {
    	File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
        }
    }
    
    /**
     * <P>根据文件名或文件路径获取文件的后缀名</P>
     * @param filePath
     * @return String
     * @throws
     * @author zzq
     * @date 2013-11-3 下午05:58:33
     * @version V1.0
     */
    public static String getFileExtName(String filePath, String suffix) {
    	File file = new File(filePath);
    	String filename = file.getName();
    	String extName = filename.substring(filename.lastIndexOf(".") + 1);
    	if(extName.indexOf("?")>0) {
    	    extName = extName.substring(0, extName.indexOf("?")).toLowerCase();
    	}
    	if (StringUtils.isBlank(suffix)) {
    		return extName;
    	} else {
    		String extNames = MappingConstantConfig.getValue(suffix);
        	if (extNames.indexOf(extName) > 0) {
        	    return extName;
        	} else {
        	    LOGGER.error("不在允许范围内的文件后缀名，请检查！filePath：" + filePath);
        	    return "";
        	}	
    	}
    	
    }
    
    /**
     * <P>判断指定的文件是否存在，指定文件路径必须是绝对路径</P>
     * @param filePhysicalPath
     * @return boolean
     * @throws
     * @author zzq
     * @date 2013-11-3 下午06:01:14
     * @version V1.0
     */
    public static boolean isFileExists(String filePhysicalPath) {
    	boolean isExists = false;
    	try {
    	    File file = new File(filePhysicalPath);
    	    isExists = file.exists();
    	} catch(Exception e) {
    	    e.printStackTrace();
    	    LOGGER.error("判断指定文件是否存在的操作出现错误！filePhysicalPath=" + filePhysicalPath);
    	}
    	return isExists;
    }
    
    /**
     * <P>用于将源文件的内容拷贝到目标文件中</P>
     * @param srcFile
     * @param targetFile
     * @return void
     * @throws
     * @author zzq
     * @date 2013-11-3 下午06:02:45
     * @version V1.0
     */
    public static void copyFile(String srcFile, String targetFile) {
    	if(!(isFileExists(srcFile))) {
    	    LOGGER.error("用于将源文件的内容拷贝到目标文件中的操作失败！失败原因：源文件不存在！srcFile：" + srcFile); 
    	    return;
    	}    
    	FolderUtils.createFolder(FolderUtils.getFolderPhysicalPath(targetFile));
    	try {
    	    FileInputStream input = new FileInputStream(srcFile); 
    	    FileOutputStream output = new FileOutputStream(targetFile);
    	    byte[] b = new byte[1024 * 5];
    	    int len;
    	    try {
	    		while((len = input.read(b)) != -1) {
	    		    output.write(b, 0, len);
	    		}
	    		output.flush();
    	    } catch(IOException e) {
    		e.printStackTrace();
    		LOGGER.error("将源文件的内容拷贝到目标文件中的操作失败，失败发生在拷贝的过程中！源文件：" + srcFile + "，目标文件：" + targetFile);
    	    } finally {
	    		output.close();
	    		input.close();
    	    }
    	} catch(FileNotFoundException e) {
    	    e.printStackTrace();
    	    LOGGER.error("将源文件的内容拷贝到目标文件中的操作失败，原因是找不到指定的文件！源文件：" + srcFile + "，目标文件：" + targetFile);
    	} catch(Exception e) {
    	    e.printStackTrace();
    	    LOGGER.error("将源文件的内容拷贝到目标文件中的操作失败！源文件：" + srcFile + "，目标文件：" + targetFile);
    	}
    	LOGGER.debug("文件拷贝成功！源文件：" + srcFile + "，目标文件：" + targetFile);
    }
    
    /**
     * <P>遍历文件夹下的文件，并根据规则修改文件名称</P>
     * @param: String directoryPath 文件夹绝对路径
     * @param FileUtil
     * @return void
     * @throws
     * @author zzq
     * @date 2013-11-3 下午06:03:56
     * @version V1.0
     */
    public static void reFileName(String directoryPath) {
    	File dir = new File(directoryPath);
    	File[] files = dir.listFiles();
    	if(files == null) {
    	    LOGGER.error("遍历文件夹下的文件，并根据规则修改文件名称的操作失败！原因是提供的文件夹不存在！directoryPath：" + directoryPath);
    	    return;
    	} else {
    	    for(int i = 0; i < files.length; i++) {
	    		if(files[i].isDirectory()) {
	    		    continue;
	    		} else {
	    		    String fileNum;
	    		    //if (files[i].getName().indexOf("_") > 0)
	    			//fileNum = files[i].getName().substring(0, files[i].getName().indexOf("_"));
	    		    //else
	    			//fileNum = files[i].getName().substring(0, files[i].getName().indexOf("."));
	    		    fileNum = (i + 1) + "";
	    		    StringBuffer newName = new StringBuffer();
	    		    newName.append(directoryPath);
	    		    if(fileNum.length() == 2) newName.append("0");
	    		    if(fileNum.length() == 1) newName.append("00");			
	    		    newName.append(fileNum).append(".XML");
	    		    File dest = new File(newName.toString());
	    		    files[i].renameTo(dest);
	    		    newName = null;
	    		    fileNum = null;
	    		}
    	    }
    	}
    }
    
    /**
     * <P>读取文件中的内容，将内容赋予一个字符串中</P>
     * @param: String filePhysicalPath 文件的绝对路径
     * @return String
     * @throws
     * @author zzq
     * @date 2013-11-3 下午06:04:46
     * @version V1.0
     */
    public static String readFileContent(String filePhysicalPath, String charset) {

    	String content = "";
    	StringBuffer sbf = new StringBuffer();
    	File file = new File(filePhysicalPath);	
    	if(file.exists()) {
    	    if(file.isFile()) {
    		FileInputStream fis = null;
    		InputStreamReader isr = null;
    		BufferedReader br = null;
    		try {
    		    fis = new FileInputStream(filePhysicalPath);
    		    isr = new InputStreamReader(fis, charset);
    		    br = new BufferedReader(isr);
    		    String line;
    		    while ((line = br.readLine()) != null) {
    			sbf.append(line);
    			sbf.append("\r\n");
    		    }
    		} catch(Exception e) {
    		    e.printStackTrace();
    		    LOGGER.error("读取文件中的内容，将内容赋予一个字符串中的操作出错！filePhysicalPath：" + filePhysicalPath);
    		} finally {
    		    try {
    			br.close();
    			isr.close();
    			fis.close();
    		    } catch (Exception e) {
    			e.printStackTrace();
    			LOGGER.error("读取文件中的内容，将内容赋予一个字符串中的操作在关闭时文件读取类时出错！");
    		    }	    
    		}
    		content = sbf.toString();
    		//去掉最后一行多余的"\r\n"
    		if(content.length() > 2)content = content.substring(0, content.length()-2);
    	    } else {
    		LOGGER.error("读取文件中的内容，将内容赋予一个字符串中的操作出错！出错原因：提供的文件不是文件类型：filePhysicalPath：" + filePhysicalPath);
    	    }
    	} else {
    	    LOGGER.error("读取文件中的内容，将内容赋予一个字符串中的操作出错！出错原因：不存在提供的文件：filePhysicalPath：" + filePhysicalPath);
    	}
    	return content;
    }
    
}
