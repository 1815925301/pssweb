package com.sinosoft.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;




public class ZIP {

	public static void unZip(File zip, String root) throws Exception {
		try {
			ZipFile zipFile = new ZipFile(zip, "GBK");
			Enumeration e = zipFile.getEntries();
			byte ch[] = new byte[256];
			while (e.hasMoreElements()) {
				ZipArchiveEntry zipEntry = (ZipArchiveEntry) e.nextElement();
				String temp = zipEntry.getName();
				System.out.println("unziping " + zipEntry.getName());
				File zfile = new File(root + File.separator + temp);
				File fpath = new File(zfile.getParentFile().getPath());
				if (zipEntry.isDirectory()) {
					if (!zfile.exists())
						zfile.mkdirs();
					/*zfile.setExecutable(true);//设置可执行权限  
					zfile.setReadable(true);//设置可读权限  
					zfile.setWritable(true);//设置可写权限  
*/				} else {
					if (!fpath.exists())
						fpath.mkdirs();
			/*		fpath.setExecutable(true);//设置可执行权限  
					fpath.setReadable(true);//设置可读权限  
					fpath.setWritable(true);//设置可写权限  
*/					FileOutputStream fouts = new FileOutputStream(zfile);
					InputStream in = zipFile.getInputStream(zipEntry);
					int i;
					while ((i = in.read(ch)) != -1)
						fouts.write(ch, 0, i);
					fouts.flush();

					fouts.close();
					in.close();
				}

			}
		} catch (Exception e) {
			System.err.println("Exception from ZipUtil -> unZip() : "
					+ e.getMessage());
			e.printStackTrace(System.err);
			throw e;
		}
	}


	public static String updatefile(String filepath, long currentfile)
			throws FileNotFoundException, IOException {
		String pathshp = "";
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {

			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + File.separator + filelist[i]);
					if (!readfile.isDirectory()) {
						String path = readfile.getName();
						if (path.endsWith(".shp")) {
							pathshp = path.replace("\\", "/");
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return pathshp;
	}
	
	public static String updatefileByType(String filepath, long currentfile,String type)
			throws FileNotFoundException, IOException {
		String pathshp = "";
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {

			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + File.separator + filelist[i]);
					if (!readfile.isDirectory()) {
						String path = readfile.getName();
						if (path.endsWith("."+type.toLowerCase())) {
							pathshp = path.replace("\\", "/");
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return pathshp;
	}
}
