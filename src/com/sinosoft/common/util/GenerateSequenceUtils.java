package com.sinosoft.common.util;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: GenerateSequenceUtils
 * @Description: 根据时间格式生成序列根据时间格式生成序列
 * @author zzq
 * @Version V1.0
 * @date 2013-9-19 上午09:40:43
 */
public class GenerateSequenceUtils {
	
	private GenerateSequenceUtils(){};

	private static final FieldPosition HELPER_POSITION = new FieldPosition(0);

	private static final Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmssS");

	private static final NumberFormat numberFormat = new DecimalFormat("0000");

	private static int seq = 0;

	private static final int MAX = 9999;

	/**
	 * 根据时间格式生成序列
	 * @param GenerateSequenceUtils
	 * @return String
	 * @throws
	 * @author zzq
	 * @date 2013-9-19 上午09:41:52
	 * @version V1.0
	 */
	public static synchronized String generateSequenceNo() {

		Calendar rightNow = Calendar.getInstance();

		StringBuffer sb = new StringBuffer();

		dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);

		numberFormat.format(seq, sb, HELPER_POSITION);

		if (seq == MAX) {
			seq = 0;
		} else {
			seq++;
		}
		return sb.toString();
	}
	
}
