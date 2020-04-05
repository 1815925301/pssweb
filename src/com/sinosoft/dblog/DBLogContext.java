package com.sinosoft.dblog;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Package com.sinosoft.dblog
 * @ClassName: DBLogContext
 * @Description: 在Spring容器启动后自动获取Spring容器中的Bean
 * @author zzq
 * @Version V1.0
 * @date 2013-9-14 下午10:21:01
 */
public class DBLogContext implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	private static DBLog dbLog;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext; 
	}

	public static DBLog getDBLog(){
		if(dbLog==null){
			dbLog = context.getBean(DBLog.class);
		}
		return dbLog;
	}
}
