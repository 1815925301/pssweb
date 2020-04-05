package com.sinosoft.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.sinosoft.common.constant.Constant;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: ZipUtils
 * @Description: ZIP格式文件处理工具类
 * @author zzq
 * @Version V1.0
 * @date 2014-2-19 上午01:30:35
 */
public class ZipUtils {
	
	/**
	 * 压缩某个文件夹中所有文件
	 * @param zipFile 压缩文件后的文件名
	 * @param folderPath 待压缩文件夹的路径
	 * @param isRetainFolderName 压缩后的压缩包中是否含有被压缩文件夹的那一层目录
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-2-19 上午09:51:39
	 * @version V1.0
	 */
	public static void zipFolderFile(String zipFile, String folderPath, boolean isRetainFolderName) throws IOException {
		File file = new File(folderPath);
		if (!file.exists()) {
			throw new RuntimeException(folderPath + " not exist!");
		}
			
		FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
		CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
		ZipOutputStream out = new ZipOutputStream(cos);
		out.setEncoding("UTF-8");
		String basedir = "";
		compress(file, out, basedir, isRetainFolderName);
		out.flush();
		out.close();
	}
	
	/**
	 * 压缩文件为zip格式
	 * @param zipFile 压缩文件后的文件名
	 * @param srcPathName 待压缩文件
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2014-2-19 上午01:31:03
	 * @version V1.0
	 */
	public static void zipSingleFile(String zipFile, String srcPathName) throws IOException {
		File file = new File(srcPathName);
		if (!file.exists()) {
			throw new RuntimeException(srcPathName + " not exist!");
		}
			
		FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
		CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream, new CRC32());
		ZipOutputStream out = new ZipOutputStream(cos);
		out.setEncoding("UTF-8");
		String basedir = "";
		compress(file, out, basedir, false);
		out.flush();
		out.close();
	}

	private static void compress(File file, ZipOutputStream out, String basedir, boolean isRetainFolderName) throws IOException {
		if (file.isDirectory()) {
			compressDirectory(file, out, basedir, isRetainFolderName);
		} else {
			compressFile(file, out, basedir);
		}
	}

	private static void compressDirectory(File dir, ZipOutputStream out, String basedir, boolean isRetainFolderName) 
			throws IOException {
		if (!dir.exists())
			return;
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (isRetainFolderName) {
				compress(files[i], out, basedir + dir.getName() + Constant.SEPARATOR, isRetainFolderName);
			} else {
				compress(files[i], out, basedir, isRetainFolderName);
			}
		}
	}

	private static void compressFile(File file, ZipOutputStream out, String basedir) throws IOException {
		if (!file.exists()) {
			return;
		}
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		ZipEntry entry = new ZipEntry(basedir + file.getName());
		out.putNextEntry(entry);
		int len = 0;
		byte data[] = new byte[1024];
		while ((len = bis.read(data)) != -1) {
			out.write(data, 0, len);
		}
		bis.close();
	}

	private static void createDir(String path) {
		File dir = new File(path);
		if (dir.exists() == false)
			dir.mkdir();
	}

	private static String getSuffixName(String name) {
		return name.substring(0, name.lastIndexOf("."));
	}

	/**
	 * 解压缩文件
	 * @param zipFilePath 压缩文件路径
	 * @param unzipDirectory 要解压到的目录
	 * @return void
	 * @throws IOException
	 * @author zzq
	 * @date 2014-2-19 上午01:35:52
	 * @version V1.0
	 */
	public static void unZip(String zipFilePath, String unzipDirectory) throws IOException {
		File file = new File(zipFilePath);
		ZipFile zipFile = new ZipFile(file);
		File unzipFile = new File(unzipDirectory + Constant.SEPARATOR + getSuffixName(file.getName()));
		if (unzipFile.exists())
			unzipFile.delete();
		unzipFile.mkdirs();
		@SuppressWarnings("rawtypes")
		Enumeration zipEnum = zipFile.getEntries();
		InputStream input = null;
		OutputStream output = null;
		ZipEntry entry = null;
		while (zipEnum.hasMoreElements()) {
			entry = (ZipEntry) zipEnum.nextElement();
			String entryName = new String(entry.getName());
			String names[] = entryName.split("\\/");
			int length = names.length;
			String path = unzipFile.getAbsolutePath();
			for (int v = 0; v < length; v++) {
				if (v < length - 1) {
					path += Constant.SEPARATOR + names[v] + Constant.SEPARATOR;
					createDir(path);
				} else {
					if (entryName.endsWith(Constant.SEPARATOR))
						createDir(unzipFile.getAbsolutePath() + Constant.SEPARATOR + entryName);
					else {
						input = zipFile.getInputStream(entry);
						output = new FileOutputStream(new File(unzipFile.getAbsolutePath() + Constant.SEPARATOR + entryName));
						byte[] buffer = new byte[1024 * 8];
						int readLen = 0;
						while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1)
							output.write(buffer, 0, readLen);
						input.close();
						output.flush();
						output.close();
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
//		unZip("E:\\zipFile.zip", "E:\\95B41843B741DC8AF84CE80322F270F7.jpg");
		zipFolderFile("E:\\456.zip", "E:\\zipFile\\", false);
		zipSingleFile("E:\\123.zip", "E:\\95B41843B741DC8AF84CE80322F270F7.jpg");
	}
}