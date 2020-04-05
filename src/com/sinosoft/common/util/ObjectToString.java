package com.sinosoft.common.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package com.sinosoft.common.util
 * @ClassName: ObjectToString
 * @Description: 将对象中的内容转换为JSON格式的字符串
 * @author zzq
 * @Version V1.0
 * @date 2013-9-14 下午09:51:26
 */
public class ObjectToString {

    private static ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(ObjectToString.class);

    static {
        DateFormat dateFormat = new SimpleDateFormat(DateTimeUtils.YMDHMSS);
        SerializationConfig serConfig = mapper.getSerializationConfig().withDateFormat(dateFormat);
        mapper.setSerializationConfig(serConfig);
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * 转换对象为String格式
     * @param ObjectToString
     * @return String
     * @throws
     * @author zzq
     * @date 2013-9-14 下午09:55:46
     * @version V1.0
     */
    public static String convertToString(Object obj) {
        if (obj == null)
            return null;
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            logger.error("{}转换为json出错,出错原因:{}", obj.toString(), e.getMessage());
        }
        return null;
    }

    public static void main(String[] agrs) throws Exception {
        System.out.println(convertToString(new java.sql.Date(System.currentTimeMillis())));
        System.out.println(convertToString(new Date()));
        System.out.println(convertToString(new Timestamp(System.currentTimeMillis())));
        System.out.println(convertToString(new BigDecimal(1212.2)));
    }

}
