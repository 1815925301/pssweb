package com.sinosoft.dblog.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.dblog.dao.MonitorSqlLogDao;
import com.sinosoft.dblog.po.MonitorSqlLog;
import com.sinosoft.dblog.po.query.MonitorSqlLogQuery;

/**
 * @Package com.sinosoft.dblog.dao.impl
 * @ClassName: MonitorSqlLogDaoImp
 * @Description: SQL执行监控日志信息的DAO 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 下午04:11:19
 */
@Repository(value="monitorSqlLogDao")
public class MonitorSqlLogDaoImp extends BaseDao implements MonitorSqlLogDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorSqlLogDaoImp.class);

	@Override
	public Integer saveMonitorSqlLog(MonitorSqlLog monitorSqlLog) {
		LOGGER.debug("Dao层：保存SQL执行的监控日志");
		return insert("monitorSqlLogDao.insertMonitorSqlLog", monitorSqlLog);
	}

	@Override
	public MonitorSqlLog getMonitorSqlLogById(Long id) {
		LOGGER.debug("Dao层：根据ID查询SQL执行的监控日志");
		return getReadSqlSession().selectOne("monitorSqlLogDao.selectMonitorSqlLogById",id);
	}

	@Override
	public List<MonitorSqlLog> getMonitorSqlLogByQuery(
			MonitorSqlLogQuery monitorSqlLogQuery) {
		LOGGER.debug("Dao层：根据条件查询SQL执行的监控日志");
		return getReadSqlSession().selectList("monitorSqlLogDao.selectMonitorSqlInfoByQuery",monitorSqlLogQuery);
	}

	@Override
	public Integer getMonitorSqlLogCountByQuery(
			MonitorSqlLogQuery monitorSqlLogQuery) {
		LOGGER.debug("Dao层：根据条件查询SQL执行的监控日志条数");
		return getReadSqlSession().selectOne("monitorSqlLogDao.selectMonitorSqlLongCountByQuery",monitorSqlLogQuery);
	}

}
