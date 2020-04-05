package com.sinosoft.common.util;

import java.security.Key;
import javax.crypto.Cipher;
import java.security.Security;

import javax.crypto.spec.SecretKeySpec;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: DESPlus
 * @Description: DES算法 对称加密算法
 * @author zzq
 * @Version V1.0
 * @date 2013-11-15 上午11:15:41
 */
public class DESPlus {

	private static String strDefaultKey = "national";
	private Cipher encryptCipher;
	private Cipher decryptCipher;

	/**
	 * 方法描述
	 * 
	 * @param arrB
	 * @return
	 * @throws Exception
	 * @Exception
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-3-6 上午10:50:23
	 */
	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;

		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; ++i) {
			int intTmp = arrB[i];

			while (intTmp < 0) {
				intTmp += 256;
			}

			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	/**
	 * 方法描述
	 * 
	 * @param strIn
	 * @return
	 * @throws Exception
	 * @Exception
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-3-6 上午10:51:25
	 */
	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i += 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[(i / 2)] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * 类的构造方法
	 * 
	 * @throws Exception
	 */
	public DESPlus() throws Exception {
		this(strDefaultKey);
	}

	/**
	 * 类的构造方法
	 * 
	 * @param strKey
	 * @throws Exception
	 */
	public DESPlus(String strKey) throws Exception {
		this.encryptCipher = null;
		this.decryptCipher = null;

//		Security.addProvider(new SunJCE());
		Key key = getKey(strKey.getBytes());

		this.encryptCipher = Cipher.getInstance("DES");
		this.encryptCipher.init(1, key);

		this.decryptCipher = Cipher.getInstance("DES");
		this.decryptCipher.init(2, key);
	}

	public byte[] encrypt(byte[] arrB) throws Exception {
		return this.encryptCipher.doFinal(arrB);
	}

	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	public byte[] decrypt(byte[] arrB) throws Exception {
		return this.decryptCipher.doFinal(arrB);
	}

	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	private Key getKey(byte[] arrBTmp) throws Exception {
		byte[] arrB = new byte[8];
		for (int i = 0; (i < arrBTmp.length) && (i < arrB.length); ++i) {
			arrB[i] = arrBTmp[i];
		}
		Key key = new SecretKeySpec(arrB, "DES");
		return key;
	}
	
	public static String desPlusDecrypt(String ciphertext) throws Exception {
		DESPlus dESPlus = new DESPlus("ACTIVITY");
		return dESPlus.decrypt(ciphertext);
	}

	public static String desPlusEncrypt(String pword) throws Exception {
		DESPlus dESPlus = new DESPlus("ACTIVITY");
		return dESPlus.encrypt(pword);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(DESPlus.desPlusEncrypt("zhaomin319266"));
		System.out.println(DESPlus.desPlusDecrypt("18a437fc6e0b9784def947d249cd58b4"));
	}

}