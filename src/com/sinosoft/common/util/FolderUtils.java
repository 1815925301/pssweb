package com.sinosoft.common.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinosoft.common.constant.Constant;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: FolderUtils
 * @Description: 目录处理工具类
 * @author zzq
 * @Version V1.0
 * @date 2013-11-3 下午06:10:11
 */
public class FolderUtils {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    
    /**
     * <P>根据指定的路径创建目录，指定的路径必须是绝对路径</P>
     * @param folderPhysicalPath
     * @return void
     * @throws
     * @author zzq
     * @date 2013-11-3 下午06:10:44
     * @version V1.0
     */
    public static void createFolder(String folderPhysicalPath) {
    	File folder = new File(folderPhysicalPath);
    	try {
    	    if(!folder.exists()) {
    		if(folder.mkdirs())
    			LOGGER.debug("目录："+ folderPhysicalPath + "不存在，创建之！");
    		else
    			LOGGER.error("目录："+ folderPhysicalPath + "不存在，创建之！但创建出错！");
    	    } else {
    	    	LOGGER.debug("目录："+ folderPhysicalPath + "已存在，不必创建！");
    	    }		
    	} catch(Exception e) {
    	    e.printStackTrace();
    	    LOGGER.error("根据指定的路径创建目录的操作失败，需要创建的目录：" + folderPhysicalPath);
    	}
    }
    
    /**
     * 根据指定的文件路径获取到该文件所在的目录，指定的文件路径应该是绝对路径
     * @param filePhysicalPath
     * @return String
     * @throws
     * @author zzq
     * @date 2013-11-3 下午06:11:44
     * @version V1.0
     */
    public static String getFolderPhysicalPath(String filePhysicalPath) {
    	filePhysicalPath = filePhysicalPath.trim();
    	if(filePhysicalPath.equals(""))
    	    return "";
    	if(filePhysicalPath.lastIndexOf(Constant.SEPARATOR) < 0)
    	    return Constant.SEPARATOR;
    	String folderPhysicalPath = filePhysicalPath.substring(0,filePhysicalPath.lastIndexOf(Constant.SEPARATOR));
    	System.out.println("folderPhysicalPath:"+folderPhysicalPath);
    	if(folderPhysicalPath.equals(""))
    	    return Constant.SEPARATOR;
    	else
    	    return folderPhysicalPath;
    }
}
