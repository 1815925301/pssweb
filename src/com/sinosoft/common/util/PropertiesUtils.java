package com.sinosoft.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinosoft.common.constant.Constant;
import com.sinosoft.security.po.query.UsersQuery;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: PropertiesUtils
 * @Description: 根据资源文件路径读取文件，转换为Map类型对象
 * @author zzq
 * @Version V1.0
 * @date 2013-10-13 上午11:08:10
 */
public class PropertiesUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);

	private PropertiesUtils() {}

	/**
	 * 根据资源文件路径读取
	 * @param path
	 * @return Map<String,String>
	 * @throws
	 * @author zzq
	 * @date 2013-10-13 上午11:08:46
	 * @version V1.0
	 */
	public static Map<String, String> prop2Map(String path) {
		LOGGER.debug("根据资源文件路径读取文件，转换为Map类型对象: {}", path);
		return prop2Map(path, Constant.DEFAULT_ENCODE.STRING_UTF8);
	}

	/**
	 * 根据资源文件路径读取
	 * @param path
	 * @param encode
	 * @return Map<String,String>
	 * @throws
	 * @author zzq
	 * @date 2013-10-13 上午11:08:49
	 * @version V1.0
	 */
	public static Map<String, String> prop2Map(String path, String encode) {
		Map<String, String> map = new HashMap<String, String>();
		Properties prop = new Properties();
		InputStream ins = PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
		Reader reader = null;
		try {
			reader = new InputStreamReader(ins, StringUtils.isEmpty(encode) ? Constant.DEFAULT_ENCODE.STRING_UTF8 : encode);
			prop.load(reader);
			Set<Entry<Object, Object>> set = prop.entrySet();
			Iterator<Entry<Object, Object>> it = set.iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				map.put((String) entry.getKey(), (String) entry.getValue());
			}
		} catch (Exception e) {
			LOGGER.error("根据资源文件路径读取文件，转换为Map类型对象：{}，在读取的过程中出现异常: {}", path, e);
		} finally {
			try {
				ins.close();
				reader.close();
			} catch (IOException e) {
				LOGGER.error("根据资源文件路径读取文件，转换为Map类型对象：{}，在关闭文件流对象时出现异常: {}", path, e);
			}
		}
		return map;
	}
	
	/**
	 * @see 根据输入的键获取其对应的值
	 * @param key输入键名称
	 * @return 返回键值
	 */
	public static String key(String key,Locale locale) {
		String num = "";
		try {
			ResourceBundle rsb = ResourceBundle.getBundle("messages", locale);
			num = rsb.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	/**
	 * 得到当前业务中的国际化语言(locale)。
	 * @param request
	 * @return 返回 locale
	 */
	public static Locale getLocale(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		Locale locale =  (Locale) session.getAttribute("locale");
		if(locale==null){
			locale = Locale.getDefault();
		}
		return locale;
	}

}
