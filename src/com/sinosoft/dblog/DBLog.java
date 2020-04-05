package com.sinosoft.dblog;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinosoft.dblog.po.MonitorReceiverLog;
import com.sinosoft.dblog.po.MonitorSqlLog;
import com.sinosoft.dblog.po.OperationLog;
import com.sinosoft.dblog.service.MonitorReceiverLogService;
import com.sinosoft.dblog.service.MonitorSqlLogService;

/**
 * @Package com.sinosoft.dblog
 * @ClassName: DBLog
 * @Description: 记录系统业务操作日志：包括系统操作日志 与 系统监控日志
 * @author zzq
 * @Version V1.0
 * @date 2013-9-14 下午10:22:46
 */
@Component("dblog")
public class DBLog {
	
	private Logger logger = LoggerFactory.getLogger(DBLog.class);
	
	@Resource
	private MonitorReceiverLogService monitorReveiverLogService;
	
	@Resource
	private MonitorSqlLogService monitorSqlLogService;
	
	/**
	 * @Fields monitorSqlTime : SQL响应阈值 如果SQL响应时间超过了该阈值 记录系统监控日志到数据库中 单位：秒
	 */
	@Value("${dblog.monitor.sql.time}")
	private Integer monitorSqlTime;

	public Integer getMonitorSqlTime() {
		return monitorSqlTime;
	}

	public void setMonitorSqlTime(Integer monitorSqlTime) {
		this.monitorSqlTime = monitorSqlTime;
	}

	/**
	 * 记录业务操作日志
	 * @param operationLog
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-9-15 下午06:07:20
	 * @version V1.0
	 */
	public void operationLog(OperationLog operationLog) {
	    if(logger.isDebugEnabled()){
	        logger.debug("开始记录系统操作日志->流水号:{},内容:{}", operationLog.getInnerSerialId(), operationLog.toString());
		}
		logger.debug(operationLog.toString());
		//TODO 补充记录日志到数据库中的逻辑
	}
	
	/**
	 * SQL执行检控日志 调用时间操作一个指定的值
	 * @param DBLog
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-9-15 下午06:08:17
	 * @version V1.0
	 */
	public void monitorSqlLog(MonitorSqlLog monitorSqlLog){
	    if(logger.isDebugEnabled()){
	        logger.debug("开始记录SQL执行日志->流水号:{},内容:{}", monitorSqlLog.getInnerSerialId(), monitorSqlLog.toString());
	    }
		//响应时间超过配置时间则记录日志
		if(monitorSqlLog.getTimeCost() != null && getMonitorSqlTime() * 1000 <= monitorSqlLog.getTimeCost()){
			monitorSqlLogService.saveMonitorSqlLog(monitorSqlLog);
		}
	}
	
	/**
	 * 服务调用监控日志
	 * @param monitorReceiverLog
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-9-17 下午09:04:30
	 * @version V1.0
	 */
	public void monitorReceiverLog(MonitorReceiverLog monitorReceiverLog){
	    if(logger.isDebugEnabled()){
	        logger.debug("开始记录SQL执行日志->流水号:{},内容:{}", monitorReceiverLog.getInnerSerialId(), monitorReceiverLog.toString());
	    }
		//将对系统服务的调用情况写入到日志表中
	    monitorReveiverLogService.saveMonitorReceiverLog(monitorReceiverLog);
	}
	
}
