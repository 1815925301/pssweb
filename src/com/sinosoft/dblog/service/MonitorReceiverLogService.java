package com.sinosoft.dblog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.dblog.po.MonitorReceiverLog;
import com.sinosoft.dblog.po.Syslog;
import com.sinosoft.dblog.po.extend.ExtendMonitorReceiverLog;
import com.sinosoft.dblog.po.query.MonitorReceiverLogQuery;
import com.sinosoft.dblog.po.query.SyslogQuery;

// TODO: Auto-generated Javadoc
/**
 * The Interface MonitorReceiverLogService.
 *
 * @Package com.sinosoft.dblog.service
 * @ClassName: MonitorReceiverLogService
 * @Description: 系统服务监控类 服务层 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 上午01:42:16
 */
public interface MonitorReceiverLogService {
	
	public void selectlogdata(ModelMap model, String method, HttpServletRequest request,SyslogQuery syslogQuery);
	
	/**
	 * 保存访问服务的监控日志.
	 *
	 * @param monitorReceiverLog the monitor receiver log
	 * @return boolean
	 * @author zzq
	 * @date 2013-10-20 上午03:24:02
	 * @version V1.0
	 */
	public boolean saveSystemLog(Syslog syslog);
	
	/**
	 * 保存日志文件
	 *
	 * @param monitorReceiverLog the monitor receiver log
	 * @return boolean
	 * @author zzq
	 * @date 2013-10-20 上午03:24:02
	 * @version V1.0
	 */
	public boolean saveMonitorReceiverLog(MonitorReceiverLog monitorReceiverLog);

	/**
	 * 方法的作用: 获得  monitor sql log by query.
	 *
	 * @param monitorReceiverLogQuery the monitor receiver log query
	 * @return MonitorReceiverLogService - monitor sql log by query
	 * @author 刘金成
	 * @version 2014-5-6-16:08:34
	 */
	public List<MonitorReceiverLog> getMonitorSqlLogByQuery(MonitorReceiverLogQuery monitorReceiverLogQuery);
	
	/**
	 * 方法的作用: 获得  monitor sql log count by query.
	 *
	 * @param monitorReceiverLogQuery the monitor receiver log query
	 * @return MonitorReceiverLogService - monitor sql log count by query
	 * @author 刘金成
	 * @version 2014-5-6-16:57:03
	 */
	public Integer getMonitorSqlLogCountByQuery(MonitorReceiverLogQuery monitorReceiverLogQuery);
	
	/**
	 * 方法的作用: 获得  monitor sql log for init page.
	 *
	 * @param model the model
	 * @param method the method
	 * @param request the request
	 * @return MonitorReceiverLogService - monitor sql log for init page
	 * @author 刘金成
	 * @version 2014-5-6-16:47:27
	 */
	public void getMonitorSqlLogForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 方法的作用: 获得  monitor sql log by id.
	 *
	 * @param id the id
	 * @return MonitorReceiverLogService - monitor sql log by id
	 * @author 刘金成
	 * @version 2014-5-7-10:52:39
	 */
	public MonitorReceiverLog getMonitorReceiverLogById(Long id);
	
	
	/**
	 * 方法的作用: 获得  extend monitor receiver log by id.
	 *
	 * @param id the id
	 * @return MonitorReceiverLogService - extend monitor receiver log by id
	 * @author 刘金成
	 * @version 2014-5-7-20:23:09
	 */
	public ExtendMonitorReceiverLog getExtendMonitorReceiverLogById(Long id);
}
