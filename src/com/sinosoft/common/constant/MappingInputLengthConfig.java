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
 * @ClassName: MappingInputLengthConfig
 * @Description: 将用户输入控件长度限制的配置文件读入，并转换为Map类型对象，放到内存中
 * @author zzq
 * @Version V1.0
 * @date 2013-10-13 上午11:06:29
 */
public class MappingInputLengthConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MappingInputLengthConfig.class);

	private static Map<String, Integer> map = new HashMap<String, Integer>();
	
	private static final String propertiesName = "sinosoft_input_config.properties";
	
	static{
		LOGGER.debug("读取用户输入控件长度限制的配置文件：{}", propertiesName);
		try {
			Map<String, String> rstMap = PropertiesUtils.prop2Map(propertiesName);
			Set<String> set = rstMap.keySet();
			Iterator<String> it = set.iterator();
			while(it.hasNext()){
				String key = it.next();
				map.put(key, new Integer(rstMap.get(key)));
			}
		} catch(Exception e) {
			LOGGER.error("读取用户输入控件长度限制的配置文件：{}，出现异常: {}", propertiesName, e);
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private MappingInputLengthConfig(){}
	
	public static Integer getValue(String key){
		return map.get(key);
	}
}
