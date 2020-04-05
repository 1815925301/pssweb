package com.sinosoft.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.exception.RarException;
import de.innosystec.unrar.rarfile.FileHeader;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: DeCompressUtils
 * @Description: 解压rar、zip文件 工具类
 * @author zzq
 * @Version V1.0
 * @date 2014-2-20 上午01:13:39
 */
public class DeCompressUtils {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeCompressUtils.class);
	private static final int BUFFER = 2048;
	
	private static String fileName = "";

	/**
	 * 测试解压归档tar文件
	 */
	public void testUnTar() {

		File srcTarGzFile = new File("d:/test.tar");// 要解压缩的tar文件对象

		String destDir = "d:/testtar";// 把解压的文件放置到c盘下的XZou目录下面
		System.out.println(srcTarGzFile.getName().substring(
				srcTarGzFile.getName().lastIndexOf(".",
						srcTarGzFile.getName().length())));
		boolean boo = false;// 是否压缩成功

		try {
			unTar(srcTarGzFile, destDir);
			boo = true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// 清理操作
			if (!boo)
				deleteDirectory(new File(destDir));// 目标文件夹 。清理

		}

	}

	/**
	 * 测试解压归档tar文件
	 */
	public void testUnTarGz() {

		File srcTarGzFile = new File("d:/test.tar.gz");// 要解压缩的tar.gz文件对象

		String destDir = "d:/testgz";// 把解压的文件放置到c盘下的XZou目录下面
		System.out.println(srcTarGzFile.getName().substring(
				srcTarGzFile.getName().lastIndexOf(".",
						srcTarGzFile.getName().length())));
		boolean boo = false;// 是否压缩成功

		try {
			unTarGz(srcTarGzFile, destDir);
			boo = true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			// 清理操作
			if (!boo)
				deleteDirectory(new File(destDir));// 目标文件夹 。清理

		}

	}

	public static void main(String[] args) throws Exception {


		String destDir = "d:/12345/";// 把解压的文件放置到c盘下的XZou目录下面

		DeCompressUtils.unrarFiles("d:/表格.rar", destDir);

//		srcTarGzFile = new File("d:/test.zip");// 要解压缩的tar.gz文件对象
//
//		destDir = "d:/testzip";// 把解压的文件放置到c盘下的XZou目录下面
//
//		DeCompressUtils.deCompress(srcTarGzFile, destDir);
//
//		srcTarGzFile = new File("d:/test.tar");// 要解压缩的tar文件对象
//
//		destDir = "d:/testtar";// 把解压的文件放置到c盘下的XZou目录下面
//
//		DeCompressUtils.deCompress(srcTarGzFile, destDir);

		// jtar.testUnTarGz();

		/*
		 * ActivityDeCompressUtils.unZip(new File("d:/default.zip"),
		 * "d:/defaultzip");
		 */

	}
	
	public static void deCompress(File file, String savePath) throws Exception {
		String filename = file.getName();
		int index = filename.lastIndexOf(".");
		if (index == -1) {
			throw new IllegalArgumentException("传入的文件无后缀名，无法确定解压缩格式");
		} else {
			String extName = filename.substring(index + 1);
			if (extName.toLowerCase().equals("zip")) {
				unZip(file, savePath);
			} else if (extName.toLowerCase().equals("tar")) {
				unTar(file, savePath);
			} else {
				LOGGER.info("只支持rar/tar/zip压缩格式，传入的文件格式为：{}", extName);
				throw new IllegalArgumentException("只支持rar/tar/zip压缩格式");
			}
		}
	}

	/**
	 * 解压tar File
	 * 
	 * @param file
	 *            要解压的tar文件对象
	 * @param outputDir
	 *            要解压到某个指定的目录下
	 * @throws IOException
	 */
	public static void unTar(File file, String outputDir) throws IOException {

		TarInputStream tarIn = null;

		try {

			tarIn = new TarInputStream(new FileInputStream(file), 1024 * 2);

			createDirectory(outputDir, null);// 创建输出目录

			TarEntry entry = null;

			while ((entry = tarIn.getNextEntry()) != null) {

				if (entry.isDirectory()) {// 是目录

					createDirectory(outputDir, entry.getName());// 创建空目录

				} else {// 是文件

					File tmpFile = new File(outputDir + File.separator
							+ entry.getName());

					createDirectory(tmpFile.getParent() + File.separator, null);// 创建输出目录

					OutputStream out = null;

					try {

						out = new FileOutputStream(tmpFile);

						int length = 0;

						byte[] b = new byte[BUFFER];

						while ((length = tarIn.read(b)) != -1) {
							out.write(b, 0, length);
						}

					} catch (IOException ex) {
						ex.printStackTrace();
						throw ex;
					} finally {

						if (out != null)
							out.close();
					}

				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IOException("解压归档文件出现异常", ex);
		} finally {
			try {
				if (tarIn != null) {
					tarIn.close();
				}
			} catch (IOException ex) {
				throw new IOException("关闭tarFile出现异常", ex);
			}
		}

	}

	/**
	 * 解压tar.gz 文件
	 * 
	 * @param file
	 *            要解压的tar.gz文件对象
	 * @param outputDir
	 *            要解压到某个指定的目录下
	 * @throws IOException
	 */
	public static void unTarGz(File file, String outputDir) throws IOException {

		TarInputStream tarIn = null;

		try {

			tarIn = new TarInputStream(new GZIPInputStream(
					new BufferedInputStream(new FileInputStream(file))),
					1024 * 2);

			createDirectory(outputDir, null);// 创建输出目录

			TarEntry entry = null;

			while ((entry = tarIn.getNextEntry()) != null) {

				if (entry.isDirectory()) {// 是目录

					createDirectory(outputDir, entry.getName());// 创建空目录

				} else {// 是文件

					File tmpFile = new File(outputDir + File.separator
							+ entry.getName());

					createDirectory(tmpFile.getParent() + File.separator, null);// 创建输出目录

					OutputStream out = null;

					try {

						out = new FileOutputStream(tmpFile);

						int length = 0;

						byte[] b = new byte[BUFFER];

						while ((length = tarIn.read(b)) != -1) {
							out.write(b, 0, length);
						}

					} catch (IOException ex) {
						ex.printStackTrace();
						throw ex;
					} finally {

						if (out != null)
							out.close();
					}

				}
			}

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IOException("解压归档文件出现异常", ex);
		} finally {
			try {
				if (tarIn != null) {
					tarIn.close();
				}
			} catch (IOException ex) {
				throw new IOException("关闭tarFile出现异常", ex);
			}
		}

	}

	/**
	 * 构建目录
	 * 
	 * @param outputDir
	 * @param subDir
	 */
	public static void createDirectory(String outputDir, String subDir) {

		File file = new File(outputDir);

		if (!(subDir == null || subDir.trim().equals(""))) {// 子目录不为空

			file = new File(outputDir + File.separator + subDir);
		}

		if (!file.exists()) {

			file.mkdirs();
		}

	}

	/**
	 * 清理文件(目录或文件)
	 * 
	 * @param file
	 */
	public void deleteDirectory(File file) {

		if (file.isFile()) {

			file.delete();// 清理文件
		} else {

			File list[] = file.listFiles();

			if (list != null) {

				for (File f : list) {
					deleteDirectory(f);
				}
				file.delete();// 清理目录
			}

		}
	}
	
	public static boolean existZH(String str) {  
	    String regEx = "[\\u4e00-\\u9fa5]";  
	    Pattern p = Pattern.compile(regEx);  
	    Matcher m = p.matcher(str);  
	    while (m.find()) {  
	        return true;  
	    }  
	    return false;  
	}

	 public static boolean unrarFiles(String rarFileName, String extPlace) {  
	        boolean flag = false;  
	        Archive archive = null;  
	        File out = null;  
	        File file = null;  
	        File dir = null;  
	        FileOutputStream os = null;  
	        FileHeader fh = null;  
	        String path, dirPath = "";  
	        try {  
	            file = new File(rarFileName);  
	            archive = new Archive(file);  
	        } catch (RarException e1) {  
	            e1.printStackTrace();  
	        } catch (IOException e1) {  
	            e1.printStackTrace();  
	        } finally {  
	            if (file != null) {  
	                file = null;  
	            }  
	        }  
	        if (archive != null) {  
	            try {  
	                fh = archive.nextFileHeader();  
	                while (fh != null) {  
	                    fileName = fh.getFileNameW().trim();  
	                    path = (extPlace + fileName).replaceAll("\\\\", "/");  
	                    int end = path.lastIndexOf("/");  
	                    if (end != -1) {  
	                        dirPath = path.substring(0, end);  
	                    }  
	                    try {  
	                        dir = new File(dirPath);  
	                        if (!dir.exists()) {  
	                            dir.mkdirs();  
	                        }  
	                    } catch (RuntimeException e1) {  
	                        e1.printStackTrace();  
	                    } finally {  
	                        if (dir != null) {  
	                            dir = null;  
	                        }  
	                    }  
	                    if (fh.isDirectory()) {  
	                        fh = archive.nextFileHeader();  
	                        continue;  
	                    }  
	                    out = new File(extPlace + fileName);  
	                    try {  
	                        os = new FileOutputStream(out);  
	                        archive.extractFile(fh, os);  
	                    } catch (FileNotFoundException e) {  
	                        e.printStackTrace();  
	                    } catch (RarException e) {  
	                        e.printStackTrace();  
	                    } finally {  
	                        if (os != null) {  
	                            try {  
	                                os.close();  
	                            } catch (IOException e) {  
	                                e.printStackTrace();  
	                            }  
	                        }  
	                        if (out != null) {  
	                            out = null;  
	                        }  
	                    }  
	                    fh = archive.nextFileHeader();  
	                }  
	            } catch (RuntimeException e) {  
	                e.printStackTrace();  
	            } finally {  
	                fh = null;  
	                if (archive != null) {  
	                    try {  
	                        archive.close();  
	                    } catch (IOException e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }  
	            flag = true;  
	        }  
	        return flag;  
	    }  


	public static void unZip(File file, String targetPath) throws Exception {
		InputStream ins = null;
		ZipFile zipFile = null;
		BufferedOutputStream bos = null;
		try {
			File targetFileDir = new File(targetPath);
			if (!new File(targetPath).exists()) {
				targetFileDir.mkdirs();
			}
			zipFile = new ZipFile(file, "UTF-8");
			Enumeration<ZipEntry> entries = zipFile.getEntries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				byte[] data = new byte[BUFFER];
				if (entry.isDirectory()) {
					File directoryFile = new File(targetPath + File.separator
							+ entry.getName());
					if (!directoryFile.exists()) {
						directoryFile.mkdir();
					}
					continue;
				}
				if (StringUtils.isEmpty(entry.getName())) {
					continue;
				}
				ins = zipFile.getInputStream(entry);
				bos = new BufferedOutputStream(new FileOutputStream(targetPath
						+ File.separator + entry.getName()));

				int count = 0;
				while ((count = ins.read(data, 0, BUFFER)) != -1) {
					bos.write(data, 0, count);
				}
				bos.flush();
				bos.close();
				bos = null;
			}
		} catch (Exception e) {
			LOGGER.error("解压ZIP文件失败：" + file.getPath() + "==" + file.getName()
					+ "!", e);
			throw e;
		} finally {
			if (ins != null) {
				try {
					ins.close();
					ins = null;
				} catch (IOException e) {
				}
			}
			if (bos != null) {
				try {
					bos.close();
					bos = null;
				} catch (IOException e) {
				}
			}
		}
	}

}
