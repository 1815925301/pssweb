package com.sinosoft.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: CreateGUID
 * @Description: 生成GUID(Globally Unique Identifier 全球唯一标识符)
 * @author zzq
 * @Version V1.0
 * @date 2013-11-3 下午05:40:29
 */
public class CreateGUID {

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateGUID.class);
	
	private String valueBeforeMD5;
	private String valueAfterMD5;
	private static Random myRandom;
	private static SecureRandom mySecureRandom;
	private static String s_id;

	static {
		mySecureRandom = new SecureRandom();
		long secureInitializer = mySecureRandom.nextLong();
		myRandom = new Random(secureInitializer);
		try {
			s_id = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			LOGGER.error("GetGUID不可获知主机错误！");
		}
	}
	
	/**
	 * 类的构造方法
	 */
	public CreateGUID() {
		valueBeforeMD5 = "";
		valueAfterMD5 = "";
		getRandmoGUID(false);
	}

	/**
	 * 类的构造方法
	 */
	public CreateGUID(boolean secure) {
		valueBeforeMD5 = "";
		valueAfterMD5 = "";
		getRandmoGUID(secure);
	}
	
	/**
	 * 生成MD5摘要
	 * @param secure
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-3-16 下午12:46:53
	 */
	private void getRandmoGUID(boolean secure) {
		MessageDigest md5 = null;
		StringBuffer sbfValueBeforeMD5 = new StringBuffer();
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			LOGGER.error("getRandmoGUID操作失败！失败原因：NoSuchAlgorithmException，没有MessageDigest.getInstance(\"MD5\")此算法！");
		}
		try {
			long time = System.currentTimeMillis();
			long rand = 0L;
			if (secure)
				rand = mySecureRandom.nextLong();
			else
				rand = myRandom.nextLong();
			sbfValueBeforeMD5.append(s_id);
			sbfValueBeforeMD5.append(":");
			sbfValueBeforeMD5.append(Long.toString(time));
			sbfValueBeforeMD5.append(":");
			sbfValueBeforeMD5.append(Long.toString(rand));
			valueBeforeMD5 = sbfValueBeforeMD5.toString();
			md5.update(valueBeforeMD5.getBytes());
			byte array[] = md5.digest();
			StringBuffer sbf = new StringBuffer();
			for (int j = 0; j < array.length; j++) {
				int b = array[j] & 0xff;
				if (b < 16)
					sbf.append('0');
				sbf.append(Integer.toHexString(b));
			}
			valueAfterMD5 = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("getRandmoGUID操作失败！失败原因：调用MD5算法计算过程有误！");
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * TODO：将生成的MD5摘要转化为大写格式，GetGUID类的调用接口函数
	 */
	public String toString() {
		String raw = valueAfterMD5.toUpperCase();
		return raw;
	}
	
	/**
	 * 获取GUID
	 * @param CreateGUID
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-11-3 下午06:21:31
	 * @version V1.0
	 */
	public static String getGUID() {
		CreateGUID getGuid = new CreateGUID(true);
		return getGuid.toString();
	}

	/**
	 * 测试专用
	 * @param args
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-3-16 下午12:47:57
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(i + ": " + getGUID());
		}
	}

}
