package com.sinosoft.dblog.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.dblog.po.MonitorReceiverLog;
import com.sinosoft.dblog.po.MonitorSqlLog;
import com.sinosoft.dblog.po.extend.ExtendMonitorSqlLog;
import com.sinosoft.dblog.po.query.MonitorSqlLogQuery;

/**
 * @Package com.sinosoft.dblog.service
 * @ClassName: MonitorSqlLogService
 * @Description: SQL执行监控 服务层 接口类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 下午04:46:44
 */
public interface MonitorSqlLogService {
	
	/**
	 * 保存SQL执行监控日志进入到数据库.
	 *
	 * @param monitorSqlLog the monitor sql log
	 * @return Integer
	 * @author zzq
	 * @date 2013-10-20 下午04:48:14
	 * @version V1.0
	 */
	public Integer saveMonitorSqlLog(MonitorSqlLog monitorSqlLog);
	
	/**
	 * 方法的作用: 获得  monitor sql log by query.
	 *
	 * @param monitorSqlLogQuery the monitor sql log query
	 * @return MonitorSqlLogService - monitor sql log by query
	 * @author 刘金成
	 * @version 2014-5-7-20:25:58
	 */
	public List<MonitorSqlLog> getMonitorSqlLogByQuery(MonitorSqlLogQuery monitorSqlLogQuery);
	
	/**
	 * 方法的作用: 获得  monitor sql log count by query.
	 *
	 * @param monitorSqlLogQuery the monitor sql log query
	 * @return MonitorSqlLogService - monitor sql log count by query
	 * @author 刘金成
	 * @version 2014-5-7-20:26:04
	 */
	public Integer getMonitorSqlLogCountByQuery(MonitorSqlLogQuery monitorSqlLogQuery);
	
	/**
	 * 方法的作用: 获得  monitor sql log for init page.
	 *
	 * @param model the model
	 * @param method the method
	 * @param request the request
	 * @return MonitorSqlLogService - monitor sql log for init page
	 * @author 刘金成
	 * @version 2014-5-7-20:25:46
	 */
	public void getMonitorSqlLogForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 方法的作用: 获得  monitor sql log by id.
	 *
	 * @param id the id
	 * @return MonitorSqlLogService - monitor sql log by id
	 * @author 刘金成
	 * @version 2014-5-7-20:25:43
	 */
	public MonitorSqlLog getMonitorSqlLogById(Long id);
	
	/**
	 * 方法的作用: 获得  extend monitor sql log by id.
	 *
	 * @param id the id
	 * @return MonitorSqlLogService - extend monitor sql log by id
	 * @author 刘金成
	 * @version 2014-5-7-20:26:09
	 */
	public ExtendMonitorSqlLog getExtendMonitorSqlLogById(Long id);
}
