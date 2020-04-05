package com.sinosoft.common.init;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sinosoft.business.service.SystemConfigManager;
import com.sinosoft.common.constant.Constant;
import com.sinosoft.dblog.service.MonitorReceiverLogService;

/**
 * <p>
 * StartupListener class used to initialize and database settings and populate
 * any application-wide drop-downs.
 * <p/>
 * <p>
 * Keep in mind that this listener is executed outside of
 * OpenSessionInViewFilter, so if you're using Hibernate you'll have to
 * explicitly initialize all loaded data at the GenericDao or service level to
 * avoid LazyInitializationException. Hibernate.initialize() works well for
 * doing this.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StartupListener implements ServletContextListener {
	private static final Log log = LogFactory.getLog(StartupListener.class);
	/**
	 * 此方法用于将数据放入ServletContext中
	 * 
	 * 参数是ServletContext
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void systemConfig(ServletContext servletcontext,
			ApplicationContext ctx) throws Exception {
		SystemConfigManager mgr = (SystemConfigManager) ctx
				.getBean("SystemConfigManager");
		servletcontext.setAttribute(Constant.SYSTEM_CONFIG, mgr
				.getSystemConfig());

		//把保存日志的方法所在的类的对象放在常量类里面，每次用时，直接调用常量类。
		MonitorReceiverLogService syslog = (MonitorReceiverLogService) ctx
				.getBean("monitorReceiverLogService");
		servletcontext.setAttribute(Constant.SYSTEM_LOG_MANAGER, syslog);
		/**
		 * * set url into center page--->start
		 */
//		SysUrlManageManager sysurlmanagemanager = (SysUrlManageManager) ctx
//				.getBean("SysUrlManageManager");
//		ArrayList<SysUrlManage> syslist = sysurlmanagemanager.findAll();
//		HashMap<String, SysUrlManage> SYSTEM_URL = new HashMap<String, SysUrlManage>();
//		for (SysUrlManage s : syslist) {
//			SYSTEM_URL.put(s.getUrl(), s);
//		}
//		servletcontext.setAttribute(Constants.SYSTEM_URL, SYSTEM_URL);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		Map<String, Object> config = (HashMap<String, Object>) context
				.getAttribute(Constant.SYSTEM_CONFIG);
		if (config == null) {
			config = new HashMap<String, Object>();
		}


		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);

		context.setAttribute(Constant.SYSTEM_CONFIG, config);

		// output the retrieved values for the Init and Context Parameters
		if (log.isDebugEnabled()) {
			log
					.debug("Remember Me Enabled? "
							+ config.get("rememberMeEnabled"));
			log.debug("Populating drop-downs...");
		}
		try {
			systemConfig(context, ctx);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
