package com.sinosoft.dblog.dao;

import java.util.List;

import com.sinosoft.business.po.City;
import com.sinosoft.dblog.po.MonitorReceiverLog;
import com.sinosoft.dblog.po.MonitorSqlLog;
import com.sinosoft.dblog.po.Syslog;
import com.sinosoft.dblog.po.query.MonitorReceiverLogQuery;
import com.sinosoft.dblog.po.query.SyslogQuery;

// TODO: Auto-generated Javadoc
/**
 * The Interface MonitorReceiverLogDao.
 *
 * @Package com.sinosoft.dblog.dao
 * @ClassName: MonitorReceiverLogDao
 * @Description: 访问服务的监控日志信息的DAO 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 上午01:40:10
 */
public interface MonitorReceiverLogDao {
	
	/**
	 * 保存访问服务的监控日志.
	 *
	 * @param monitorReceiverLog the monitor receiver log
	 * @return Integer
	 * @author zzq
	 * @date 2013-10-20 上午03:42:29
	 * @version V1.0
	 */
	public Integer saveMonitorReceiverLog(MonitorReceiverLog monitorReceiverLog);



	/**
	 * 方法的作用: 获得  monitor receiver log by query.
	 *
	 * @param monitorReceiverLogQuery the monitor receiver log query
	 * @return MonitorReceiverLogDao - monitor receiver log by query
	 * @author 刘金成
	 * @version 2014-5-6-16:01:29
	 */
	public List<MonitorReceiverLog> getMonitorReceiverLogByQuery(MonitorReceiverLogQuery monitorReceiverLogQuery);
	
	/**
	 * 方法的作用: 获得  monitor receiver log count by query.
	 *
	 * @param monitorReceiverLogQuery the monitor receiver log query
	 * @return MonitorReceiverLogDao - monitor receiver log count by query
	 * @author 刘金成
	 * @version 2014-5-6-16:58:06
	 */
	public Integer getMonitorReceiverLogCountByQuery(MonitorReceiverLogQuery monitorReceiverLogQuery);
	
	/**
	 * 根据日志id获取日志信息
	 * @param id
	 * @return City
	 * @throws
	 * @author zzq
	 * @date 2013-10-21 下午01:56:12
	 * @version V1.0
	 */
	public MonitorReceiverLog getMonitorReceiverLogById(Long id);


	/***
	 * 查询系统日志
	 * @param syslogQuery
	 * @return
	 */
	public List<Syslog> getLogByQuery(SyslogQuery syslogQuery);
	
	
	
	/***
	 * 保存系统日志
	 * @param syslogQuery
	 * @return
	 */
	public Integer saveSystemLog(Syslog syslog);
	
	/***
	 * 获得有多少类型
	 * @param syslogQuery
	 * @return
	 */
	public List getLogType();



	/***
	 * 查询系统日志数量
	 * @param syslogQuery
	 * @return
	 */
	public Integer getLogCountByQuery(SyslogQuery syslogQuery);
}
