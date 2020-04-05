package com.sinosoft.dblog.dao;

import java.util.List;

import com.sinosoft.dblog.po.MonitorReceiverLog;
import com.sinosoft.dblog.po.MonitorSqlLog;
import com.sinosoft.dblog.po.query.MonitorReceiverLogQuery;
import com.sinosoft.dblog.po.query.MonitorSqlLogQuery;

// TODO: Auto-generated Javadoc
/**
 * The Interface MonitorSqlLogDao.
 *
 * @Package com.sinosoft.dblog.dao
 * @ClassName: MonitorSqlLogDao
 * @Description: SQL执行监控日志信息的DAO 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 上午01:40:55
 */
public interface MonitorSqlLogDao {
	
	/**
	 * 保存SQL执行的监控日志.
	 *
	 * @param monitorSqlLog the monitor sql log
	 * @return Integer
	 * @author zzq
	 * @date 2013-10-20 上午03:42:29
	 * @version V1.0
	 */
	public Integer saveMonitorSqlLog(MonitorSqlLog monitorSqlLog);
	
	/**
	 * 方法的作用: 获得  monitor sql log by query.
	 *
	 * @param monitorSqlLogQuery the monitor sql log query
	 * @return MonitorSqlLogDao - monitor sql log by query
	 * @author 刘金成
	 * @version 2014-5-7-20:15:54
	 */
	public List<MonitorSqlLog> getMonitorSqlLogByQuery(MonitorSqlLogQuery monitorSqlLogQuery);
	
	/**
	 * 方法的作用: 获得  monitor sql log count by query.
	 *
	 * @param monitorSqlLogQuery the monitor sql log query
	 * @return MonitorSqlLogDao - monitor sql log count by query
	 * @author 刘金成
	 * @version 2014-5-7-20:15:54
	 */
	public Integer getMonitorSqlLogCountByQuery(MonitorSqlLogQuery monitorSqlLogQuery);
	
	/**
	 * 方法的作用: 获得  monitor sql log by id.
	 *
	 * @param id the id
	 * @return MonitorSqlLogDao - monitor sql log by id
	 * @author 刘金成
	 * @version 2014-5-7-20:15:54
	 */
	public MonitorSqlLog getMonitorSqlLogById(Long id);
}
