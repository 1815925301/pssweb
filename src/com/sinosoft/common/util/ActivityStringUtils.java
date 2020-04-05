package com.sinosoft.common.util;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: ActivityStringUtils
 * @Description: 处理字符串的工具类
 * @author zzq
 * @Version V1.0
 * @date 2013-9-8 下午02:15:10
 */
public class ActivityStringUtils extends org.apache.commons.lang.StringUtils {
	
	private static final Md5PasswordEncoder MD5 = new Md5PasswordEncoder();
	
	/**
	 * 生成MD5加密后的字符串
	 * @param rawPass
	 * @param salt
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-10-6 下午09:32:52
	 * @version V1.0
	 */
	public static String createMd5Str(String rawPass, Object salt) {
		return MD5.encodePassword(rawPass, salt);
	}
	
	/**
	 * 删除第一个字符
	 * @param str
	 * @return
	 */
	public static String removeFirst(String str) {
		return isEmpty(str) ? "" : str.substring(1, str.length());
	}

	/**
	 * 删除最后一个字符
	 * @param str
	 * @return
	 */
	public static String removeLast(String str) {
		return isEmpty(str) ? "" : str.substring(0, str.length() - 1);
	}
	
	/**
	 * 将 str按照指定的 分隔符 生成一个 list，并过滤掉其中的空数据
	 * @param str
	 * @param split
	 * @return
	 */
	public static List<String> string2ListBySplit(String str,String split){
		if(isNotEmpty(str)){
			List<String> list = new ArrayList<String>();
			String[] sts = str.split(split);
			for(String s : sts){
				if(isNotEmpty(s)){
					list.add(s);
				}
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 对于 aab{1},{0},bb 的消息进行转换
	 * @param source
	 * @param objects
	 * @return
	 */
	public static String format(String source,Object... objects){
		 MessageFormat mf = new MessageFormat(source);
		 return mf.format(objects);
	}
	
	
	/**
	 * 对列表类转换成字符串，并增加序号
	 * @param Iterator
	 * @param separator
	 * @return
	 */
	public static String joinWithSeq(List<String> list, char separator){
		String totalError  = "";
		for (int i = 1; i < list.size()+1; i++) {
			totalError += i+"."+ list.get(i-1) +separator; 
		}
		return totalError;
	}
	
	//
	public static boolean isNumer(String str){
		if (ActivityStringUtils.isBlank(str))  {
			return  false;
		}
		String matchStr = "^-?\\d+$";
        Pattern pattern = Pattern.compile(matchStr);
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() )
        {
              return false;
        }
        return true;
	}
	public static boolean isPositiveNumer(String str){
		if (ActivityStringUtils.isBlank(str))  {
			return  false;
		}
		String matchStr = "^\\d+$";
		Pattern pattern = Pattern.compile(matchStr);
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 截取字符串的长度为所给的长度
	 * @param str
	 * @param maxLength
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-11-4 下午08:26:21
	 * @version V1.0
	 */
	public static String cutStr(String str, int maxLength) {
		if (str != null && str.length() > maxLength) {
			str = str.substring(0, maxLength);
		}
		return str;
	}
	
	/**
	 * 把字符串转换为集合
	 * @param str
	 * @param seprator
	 * @return List<Long>
	 * @throws
	 * @author zzq
	 * @date 2013-11-10 下午03:56:57
	 * @version V1.0
	 */
	public static List<Long> transStrToLongList(String str, String seprator){
		if (str != null && str.equals("")) {
			return null;
		}
		String[] array = str.split(seprator);
		List<Long> list = new ArrayList<Long>();
		for (String s : array) {
			list.add( (s == null || s.trim().equals("")) ? null : new Long(s.trim()));
		}
		return list;
	}
	
	public static List<Integer> transStrToIntegerList(String str, String seprator){
		if (str != null && str.equals("")) {
			return null;
		}
		String[] array = str.split(seprator);
		List<Integer> list = new ArrayList<Integer>();
		for (String s : array) {
			list.add( (s == null || s.trim().equals("")) ? null : new Integer(s.trim()));
		}
		return list;
	}
	
	/**
	 * 判断字符串是否为数字
	 * @param str
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-11-10 下午08:15:50
	 * @version V1.0
	 */
	public static boolean isNumeric(String str){
		for (int i = 0; i < str.length(); i++) 
			if (!Character.isDigit(str.charAt(i))) return false;
		return true;
	}
	
	/**
	 * 获取两个数值相除后的百分数 给定百分数的小数点后N位
	 * @param m
	 * @param n
	 * @param precision
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-11-21 下午11:14:14
	 * @version V1.0
	 */
	public static String getDecimal(BigDecimal m, BigDecimal n, int precision) {
		if (n.compareTo(new BigDecimal(0)) == 0) return "";
		if (m.compareTo(new BigDecimal(0)) == 0) return "0.00%";
		BigDecimal k = new BigDecimal(m.doubleValue() / n.doubleValue() * 100);
		String value = String.valueOf((k.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue()));
		if (value != null && !value.equals("") && value.indexOf(".") > 0) {
			if ((value.length() - value.indexOf(".")) > (precision - 1)) {
				String temp = "";
				for (int i = 0; i < (value.length() - value.indexOf(".")); i++) {
					temp = value + "0";
				}
				value = temp;
			}
			value += "%";
		}
		return value;
	}
	
	public static String getDecimalForInteger(Integer m, Integer n, int precision) {
		if (m != 0 && n == 0) return "--";
		if (m == 0) return "0.00%";
		BigDecimal k = new BigDecimal(m.doubleValue() / n.doubleValue() * 100);
		String value = String.valueOf((k.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue()));
		if (value != null && !value.equals("") && value.indexOf(".") > 0) {
			if ((value.length() - value.indexOf(".")) > (precision - 1)) {
				String temp = "";
				for (int i = 0; i < (value.length() - value.indexOf(".")); i++) {
					temp = value + "0";
				}
				value = temp;
			}
			if (value != null && value.indexOf(".") > 0 && value.substring(value.indexOf(".")).length() > precision) {
				value = value.substring(0, value.length() - (value.substring(value.indexOf(".")).length() - precision) + 1);
			}
			value += "%";
		}
		return value;
	}
	
	/**
	 * 如果字符串是null或"null"，则返回空值""
	 * @param String str : 需要替换的字符串
	 * @return String str ：空值或字符串str
	 * @Author zzq
	 * @Version V1.0
	 * @Create at 2012-3-27 下午05:38:54
	 */
	public static String replaceNullValue (String str) {
		if (str == null) return "";
		return str;
	}
	
	/**
	 * 转义双引号为HTML的代码
	 * @param ActivityStringUtils
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2014-3-27 下午08:37:47
	 * @version V1.0
	 */
	public static String replaceQuotationValue(String str) {
		if (str == null) return "";
		str = str.replaceAll("\"", "&quot;");
			
		return str;
	}
	
	/**
	 * 字符串奇数位置的逗号换成空格
	 * @param strs
	*/
	public static String replaceComn2Space(String strs){
//		String strs = "-68.70013,8.868977,-66.0678,9.90423,-64.5923,8.3679,-65.149,6.8910,-67.559,7.8890,-68.70013,8.868977";
		String ss = "";
//		System.out.println(strs.indexOf(","));
		String[] str = strs.split(",");
		for(int i=0;i<str.length-1;i++){
			if(i%2 == 0){ 
		    	ss = ss+str[i] + "  " ;
		    }else
		    	ss = ss+str[i]+", ";
		}
		ss = ss + str[str.length-1]; 
		System.out.println(ss);
		return ss;
	}
	
	public static void main(String[] args) {
		System.out.println(ActivityStringUtils.getDecimal(new BigDecimal(11), new BigDecimal(10), 2));
		System.out.println(ActivityStringUtils.getDecimalForInteger(new Integer(110), new Integer(100), 2));
		System.out.println(ActivityStringUtils.getDecimalForInteger(new Integer(190), new Integer(180), 2));
		System.out.println(isNumer(" "));
		
		System.out.println("1".split(",").length);
	}

}
