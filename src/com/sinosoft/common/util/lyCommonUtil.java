package com.sinosoft.common.util;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class lyCommonUtil {
	/**
	 * 判断字符串是否有效
	 * 
	 * @param Str
	 * @return
	 * @author liuying
	 * @date 2014年8月7日上午10:36:13
	 */
	public static boolean isNullOrEmpty(String Str) {
		if (Str != null && Str.trim().length() > 0)
			return false;
		else
			return true;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param arr
	 * @return
	 * @author liuying
	 * @date 2014年11月29日上午1:19:31
	 */
	public static boolean isNullOrEmpty(Object[] arr) {
		if (arr != null && arr.length > 0)
			return false;
		else
			return true;
	}

	public static boolean isNullOrEmpty(List list) {
		if (list != null && !list.isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * 判断数组中是否包含某个字符串
	 * 
	 * @param array
	 * @param Str
	 * @param CaseSensitive
	 * @return
	 * @author liuying
	 * @date 2014年8月7日上午10:35:56
	 */
	public static boolean isArrayContainString(String[] array, String Str, Boolean CaseSensitive) {
		if (array == null || array.length == 0 || isNullOrEmpty(Str))
			return false;
		boolean flag = false;
		if (CaseSensitive) {
			// 区分大小写
			for (String tmp : array) {
				if (tmp.equals(Str)) {
					flag = true;
					break;
				}
			}
		} else {
			// 不区分大小写
			for (String tmp : array) {
				if (tmp.trim().equalsIgnoreCase(Str.trim())) {
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 把字符串转换成数组
	 * 
	 * @param Str
	 * @param splitStr
	 * @return
	 * @author liuying
	 * @date 2014年8月7日上午10:43:34
	 */
	public static String[] StringToArray(String Str, String splitStr) {
		if (isNullOrEmpty(Str))
			return null;
		else
			return Str.trim().split(splitStr);
	}

	/**
	 * 将tomcat默认的ISO88591编码转为utf8(get)传参时用,不用再使用url编码
	 * 
	 * @param str
	 * @return
	 * @author liuying
	 * @date 2014年8月7日上午10:39:00
	 */
	public static String changeCode(String str) {
		try {
			return new String(str.getBytes("iso-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 把传入的StringBuffer长度减n
	 * 
	 * @param n
	 * @param sbs
	 * @author liuying
	 * @date 2014年8月7日上午10:41:28
	 */
	public static void StringBuffersLengthReduce(int n, StringBuffer... sbs) {
		if (sbs != null && sbs.length >= n) {
			for (StringBuffer sb : sbs) {
				if (sb.length() > 0)
					sb.setLength(sb.length() - n);
			}
		}
	}

	/**
	 * 获取数组长度
	 * 
	 * @param arr
	 * @return
	 * @author liuying
	 * @date 2014年12月1日下午5:10:38
	 */
	public static int getArrayLength(Object[] arr) {
		if (isNullOrEmpty(arr))
			return 0;
		else
			return arr.length;
	}

	/**
	 * 获取非空
	 * 
	 * @param arr
	 * @return
	 * @author liuying
	 * @date 2014年11月29日上午1:23:00
	 */
	public static int getStringArrayAvailableLength(String[] arr) {
		if (isNullOrEmpty(arr))
			return 0;
		else {
			int n = 0;
			for (int i = 0; i < arr.length; i++) {
				if (!isNullOrEmpty(arr[i]))
					n++;
			}
			return n;
		}
	}

	/**
	 * 检查所有数组的某一个元素是否都为空
	 * 
	 * @param index
	 * @param arrs
	 * @return
	 * @author liuying
	 * @date 2014年12月1日下午5:28:16
	 */
	public static boolean isAllNullOrEmpty(int index, String[]... arrs) {
		int maxlength = 0;
		boolean allArraysIsNull = true;
		for (String[] arr : arrs) {
			if (!isNullOrEmpty(arr)) {
				allArraysIsNull = false;
				maxlength = arr.length > maxlength ? arr.length : maxlength;
			}
		}
		if (allArraysIsNull)
			return true;

		for (String[] arr1 : arrs) {
			if (arr1 != null && arr1.length > index && !isNullOrEmpty(arr1[index]))
			return false;
		}
		return true;
	}
	
	/**
	 * 判断字符串是否能转换成整型
	 * @param str
	 * @return
	 * @author liuying
	 * @date 2014年12月18日下午3:49:32
	 */
	public static boolean canConvertToInt(String str){
		if(str==null || str.trim().length()==0)
			return false;
		try {
			new Integer(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
