package com.sinosoft.dblog.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.dblog.dao.MonitorReceiverLogDao;
import com.sinosoft.dblog.po.MonitorReceiverLog;
import com.sinosoft.dblog.po.MonitorSqlLog;
import com.sinosoft.dblog.po.Syslog;
import com.sinosoft.dblog.po.query.MonitorReceiverLogQuery;
import com.sinosoft.dblog.po.query.SyslogQuery;

/**
 * @Package com.sinosoft.dblog.dao.impl
 * @ClassName: MonitorReceiverLogDaoImpl
 * @Description: 访问服务的监控日志信息的DAO 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 上午03:47:26
 */
@Repository(value="MonitorReceiverLogDao")
public class MonitorReceiverLogDaoImpl extends BaseDao implements MonitorReceiverLogDao {
			
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorReceiverLogDaoImpl.class);
	private static final Object MonitorReceiverLog = null;
	
	@Override
	public Integer saveMonitorReceiverLog(MonitorReceiverLog monitorReceiverLog) {
		LOGGER.debug("Dao层：保存访问服务的监控日志");
		return getReadSqlSession().insert("monitorReceiverLogDao.insertMonitorReceiverLog", monitorReceiverLog);
	}

	@Override
	public List<MonitorReceiverLog> getMonitorReceiverLogByQuery(
			MonitorReceiverLogQuery monitorReceiverLogQuery) {
		LOGGER.debug("Dao层：浏览访问服务的监控日志");
		return getReadSqlSession().selectList("monitorReceiverLogDao.selectReceiverLogInfoByQuery", monitorReceiverLogQuery);
		
	}

	@Override
	public Integer getMonitorReceiverLogCountByQuery(
			MonitorReceiverLogQuery monitorReceiverLogQuery) {
		LOGGER.debug("Dao层：浏览访问服务的监控日志条数");
		return (Integer)getReadSqlSession().selectOne("monitorReceiverLogDao.selectReceiverLogCountByQuery", monitorReceiverLogQuery);
	}

	@Override
	public MonitorReceiverLog getMonitorReceiverLogById(Long id) {
		LOGGER.debug("Dao层：根据ID访问服务的监控日志信息");
		return getReadSqlSession().selectOne("monitorReceiverLogDao.selectMonitorReceiverLogById",id);
	}

	@Override
	public List<Syslog> getLogByQuery(SyslogQuery syslogQuery) {
		return getReadSqlSession().selectList("SysLogDao.selectlogdata",syslogQuery);
	}

	@Override
	public List getLogType() {
		return getReadSqlSession().selectList("SysLogDao.getLogType");
	}
	@Override
	public Integer getLogCountByQuery(SyslogQuery syslogQuery) {
		return getReadSqlSession().selectOne("SysLogDao.selectlogdatacount",syslogQuery);
	}

	/***
	 * 保存日志
	 */
	@Override
	public Integer saveSystemLog(Syslog syslog) {
		return getReadSqlSession().insert("SysLogDao.insertSystemLog", syslog);
	}

}
