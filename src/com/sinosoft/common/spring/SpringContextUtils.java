package com.sinosoft.common.spring;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @Package com.sinosoft.common.spring
 * @ClassName: SpringContextUtils
 * @Description: spring环境工具
 * 实现SpringContextUtils中context参数的初始化，供该类提供springContext的静态get方法使用 
 * @author zzq
 * @Version V1.0
 * @date 2013-9-14 下午08:19:18
 */
public class SpringContextUtils implements ApplicationContextAware {
	
	private SpringContextUtils() {}
	
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	/**
	 * 获取spring上下文
	 * @param 
	 * @return ApplicationContext
	 * @throws
	 * @author zzq
	 * @date 2013-9-14 下午08:21:31
	 * @version V1.0
	 */
	public static ApplicationContext getCurrentContext() {
		return context;
	}

	/**
	 * 根据web请求获取spring上下文
	 * @param SpringContextUtils
	 * @return ApplicationContext
	 * @throws
	 * @author zzq
	 * @date 2013-9-14 下午08:22:17
	 * @version V1.0
	 */
	public static ApplicationContext getApplicationContext(HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		return WebApplicationContextUtils.getRequiredWebApplicationContext(context);
	}

}
