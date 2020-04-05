package com.sinosoft.common.util;
import java.util.List;

import javax.servlet.ServletContext;

import com.sinosoft.business.po.SystemConfig;
import com.sinosoft.common.constant.Constant;

public class SystemConfigUtil {

	/**
	 * 从数据字典中取得相关key的值 传入的参数String,ServletContext 用法如下:
	 * 在controll中用需要继承BaseFormController String[] str =
	 * SystemConfigUtil.getSystemValue(KEY,getServletContext());
	 * 
	 * @param key
	 *            String key key为null时返回长度为零的String数组
	 * @param servletContext
	 *            ServletContext servletContext
	 * @return String[] 如果未查询到数据则返回长度为零的String数组
	 */
	@SuppressWarnings("unchecked")
	public static String[] getSystemValue(String key,
			ServletContext servletContext) {
		if (null == key) {// key为null时返回长度为零的String数组
			return new String[0];
		}

		List systemConfig = (List) servletContext
				.getAttribute(Constant.SYSTEM_CONFIG);

		StringBuffer str = new StringBuffer();
		String[] value = null;

		for (int i = 0; i < systemConfig.size(); i++) {
			SystemConfig sysConfig = (SystemConfig) systemConfig.get(i);

			if (key.equalsIgnoreCase(sysConfig.getConfigkey())) {
				str.append(sysConfig.getConfigvalue());
			} else {
				str.append("");
			}
		}
		if (!"".equals(str)) {// 判断是否查询到数据
			value = str.toString().split(",");
		} else {
			value = new String[0];
		}
		return value;
	}
}
