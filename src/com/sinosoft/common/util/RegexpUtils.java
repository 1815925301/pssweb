package com.sinosoft.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: RegexpUtils
 * @Description: 正则表达式校验工具类
 * @author zzq
 * @Version V1.0
 * @date 2013-9-8 下午01:46:13
 */
public class RegexpUtils {
	
	// 通用表达式
	public static final class COMMON_REGEX {
		
		// 页数
		public static final String PAGE = "^0|[1-9][0-9]*$";
		
		// 每页数量
		public static final String PAGE_SIZE = "^0|[1-9][0-9]*$";
		
		// 0 1标记
		public static final String YN_MARK = "^0|1$";
		
		// 包含中文
		public static final String HAS_CHINESE = "[\\u4e00-\\u9fa5]";
		
		// 非零数据
		public static final String PRODUCT_ID = "^[1-9][0-9]*$";
		
		// 纯数字
		public static final String NUMBER = "^\\d+$";
		
		//分号分隔的字符
		public static final String STR_SPLITBY_FH = "^([^;]+;)*[^;]+$" ;
		
		//分号分隔的数字或者字母
		public static final String WORD_SPLITBY_FH = "^(\\w+;)*\\w+$" ;
		
		// 商品状态
		public static final String STATUS = "^-1|[0-6]$";
		
		// 商品审核状态
		public static final String CHECK_STATUS = "^[1-6]$";
		
		// 价格
		public static final String PRICE = "^[0-9]{1,10}(\\.[0-9]{1,2})?$";
		
		//商家编码：不能有&<>
		public static final String OUTER_ITEM_ID =  "[^&<>]*";
		
	}

	public static boolean validate(String regex, String input) {
		return Pattern.matches(regex, input);
	}
	
	public static boolean validateHas(String regex, String input) {
		Pattern pat = Pattern.compile(regex);
		Matcher matcher = pat.matcher(input);   
		return matcher.find();
	}
	
	public static void main(String args[]){
		System.out.println(validateHas(COMMON_REGEX.HAS_CHINESE, "212542,.jpd"));
	}

}
