package com.sinosoft.common.constant;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinosoft.common.util.PropertiesUtils;

/**
 * @Package com.sinosoft.common.constant
 * @ClassName: MappingConstantConfig
 * @Description: 将系统常量对应汉字名称的配置文件加读入，并转换为Map类型对象，放到内存中
 * @author zzq
 * @Version V1.0
 * @date 2013-10-18 上午10:07:13
 */
public class MappingConstantConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MappingInputLengthConfig.class);

	private static Map<String, String> map = new HashMap<String, String>();
	
	private static final String propertiesName = "sinosoft_constant_config.properties";
	
	static{
		LOGGER.debug("读取系统常量对应汉字名称的配置文件：{}", propertiesName);
		try {
			Map<String, String> rstMap = PropertiesUtils.prop2Map(propertiesName);
			Set<String> set = rstMap.keySet();
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String key = it.next();
				map.put(key, rstMap.get(key));
			}
		} catch(Exception e) {
			LOGGER.error("读取系统常量对应汉字名称的配置文件：{}，出现异常: {}", propertiesName, e);
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private MappingConstantConfig(){}
	
	public static String getValue(String key){
		return map.get(key);
	}
}
