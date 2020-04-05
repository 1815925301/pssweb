package com.sinosoft.dblog.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.dblog.dao.MonitorSqlLogDao;
import com.sinosoft.dblog.po.MonitorSqlLog;
import com.sinosoft.dblog.po.extend.ExtendMonitorSqlLog;
import com.sinosoft.dblog.po.query.MonitorSqlLogQuery;
import com.sinosoft.dblog.service.MonitorSqlLogService;
import com.sinosoft.security.service.OrganizationService;

/**
 * @Package com.sinosoft.dblog.service.impl
 * @ClassName: MonitorSqlLogServiceImpl
 * @Description: SQL执行监控 服务层 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 下午04:47:04
 */
@Service("monitorSqlLogService")
public class MonitorSqlLogServiceImpl implements MonitorSqlLogService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorSqlLogServiceImpl.class);
	
	@Resource
	private MonitorSqlLogDao monitorSqlLogDao;
	@Resource
	private SystemConstant systemConstant;
	@Resource
	private OrganizationService organizationService;
	@Override
	public Integer saveMonitorSqlLog(MonitorSqlLog monitorSqlLog) {
		LOGGER.debug("Service层：保存SQL执行的监控日志");
		return monitorSqlLogDao.saveMonitorSqlLog(monitorSqlLog);
	}

	@Override
	public ExtendMonitorSqlLog getExtendMonitorSqlLogById(Long id) {
		LOGGER.debug("Service层：根据日志id获取日志扩展信息");
		MonitorSqlLog monitorSqlLog = this.getMonitorSqlLogById(id);
		if (monitorSqlLog == null || monitorSqlLog.getId() == null) {
			LOGGER.debug("根据日志id获取日志扩展信息，没有查取到日志信息：Id {}", id);
			return null;
		}
		ExtendMonitorSqlLog eLog = new ExtendMonitorSqlLog();
	
		try {
			BeanUtils.copyProperties(eLog, monitorSqlLog);
		} catch (Exception e) {
			LOGGER.error("根据日志id获取日志扩展信息，拷贝属性出错", id);
			e.printStackTrace();
		}

		return eLog;
	}

	@Override
	public MonitorSqlLog getMonitorSqlLogById(Long id) {
		// TODO Auto-generated method stub
		return monitorSqlLogDao.getMonitorSqlLogById(id);
	}

	@Override
	public List<MonitorSqlLog> getMonitorSqlLogByQuery(
			MonitorSqlLogQuery monitorSqlLogQuery) {
		// TODO Auto-generated method stub
		return monitorSqlLogDao.getMonitorSqlLogByQuery(monitorSqlLogQuery);
	}

	@Override
	public Integer getMonitorSqlLogCountByQuery(
			MonitorSqlLogQuery monitorSqlLogQuery) {
		// TODO Auto-generated method stub
		return monitorSqlLogDao.getMonitorSqlLogCountByQuery(monitorSqlLogQuery);
	}

	@Override
	public void getMonitorSqlLogForInitPage(ModelMap model, String method,
			HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取日志列表页面");
		MonitorSqlLogQuery logQuery = new MonitorSqlLogQuery();
		//以id升序排序
		logQuery.setSortBy("id");
		logQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				logQuery.setPage(Integer.parseInt(pageNum));
			}
			String sqlcmd = request.getParameter("sqlcmd");
			if (! StringUtils.isBlank(sqlcmd)) {
				logQuery.setSqlCommandType(sqlcmd);
				model.addAttribute("sqlcmd", sqlcmd);
			}
			String  sqlstr = request.getParameter("sqlstr");

			if (! StringUtils.isBlank(sqlstr) && ! sqlstr.equals("-1")) {
				logQuery.setSqlStr(sqlstr);
				model.addAttribute("sqlstr", sqlstr);
			}
		}
		Integer totalCount = monitorSqlLogDao.getMonitorSqlLogCountByQuery(logQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, logQuery.getPageSize(), logQuery.getPage(), logQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<MonitorSqlLog> logList = monitorSqlLogDao.getMonitorSqlLogByQuery(logQuery);
		List<ExtendMonitorSqlLog> elogList = new ArrayList<ExtendMonitorSqlLog>();
		if (logList != null && logList.size() > 0) {
			try {
				for (MonitorSqlLog log : logList) {
					ExtendMonitorSqlLog elog = new ExtendMonitorSqlLog();
				//	BeanUtils.copyProperties(elog, log);
					if(log.getCreateTime()!=null)
					elog.setCreateTime(log.getCreateTime());
					elog.setSqlStr(log.getSqlStr());
					elog.setDataSource(log.getDataSource());
					elog.setFileResource(log.getFileResource());
					elog.setId(log.getId());
					elog.setParameters(log.getParameters());
					elog.setSqlCommandType(log.getSqlCommandType());
					if(log.getSqlEndTime()!=null)
					elog.setSqlEndTime(log.getSqlEndTime());
					if(log.getSqlStartTime()!=null)
					elog.setSqlStartTime(log.getSqlStartTime());
					elog.setTimeCost(log.getTimeCost());
					elogList.add(elog);
				}
			} catch(Exception e) {
				LOGGER.error("将日志信息进行对象拷贝 对象属性值拷贝过程中出现异常：{}", e);
			}
		}
		JSONArray jsonArray2 = JSONArray.fromObject( elogList ); 
		
		model.addAttribute("MonitorSqlLogList", elogList);
		model.addAttribute("SqlLogList", jsonArray2);
	}
	
}
