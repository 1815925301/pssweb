package com.sinosoft.dblog.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.common.constant.EnumIsLock;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.dblog.dao.MonitorReceiverLogDao;
import com.sinosoft.dblog.po.MonitorReceiverLog;
import com.sinosoft.dblog.po.MonitorSqlLog;
import com.sinosoft.dblog.po.Syslog;
import com.sinosoft.dblog.po.extend.ExtendMonitorReceiverLog;
import com.sinosoft.dblog.po.extend.ExtendMonitorSqlLog;
import com.sinosoft.dblog.po.query.MonitorReceiverLogQuery;
import com.sinosoft.dblog.po.query.SyslogQuery;
import com.sinosoft.dblog.service.MonitorReceiverLogService;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.service.OrganizationService;

/**
 * @Package com.sinosoft.dblog.service.impl
 * @ClassName: MonitorReceiverLogServiceImpl
 * @Description: 系统服务监控类 服务层 实现类
 * @author zzq
 * @Version V1.0
 * @date 2013-10-20 上午03:40:12
 */
@Service("monitorReceiverLogService")
public class MonitorReceiverLogServiceImpl implements MonitorReceiverLogService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MonitorReceiverLogServiceImpl.class);
	
	@Resource
	private MonitorReceiverLogDao monitroReceiverLogDao;
	@Resource
	private SystemConstant systemConstant;
	@Resource
	private OrganizationService organizationService;
	@Override
	public boolean saveMonitorReceiverLog(MonitorReceiverLog monitorReceiverLog) {
		LOGGER.debug("Service层：保存访问服务的监控日志");
		Integer resultNum = monitroReceiverLogDao.saveMonitorReceiverLog(monitorReceiverLog);
		return resultNum == null ? false : resultNum.compareTo(new Integer(0)) != 0;
	}

	@Override
	public List<MonitorReceiverLog> getMonitorSqlLogByQuery(
			MonitorReceiverLogQuery monitorReceiverLogQuery) {
		LOGGER.debug("Service层：获取访问服务的监控日志列表");
		return monitroReceiverLogDao.getMonitorReceiverLogByQuery(monitorReceiverLogQuery);
	}

	@Override
	public void getMonitorSqlLogForInitPage(ModelMap model, String method,
			HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取日志列表页面");
		MonitorReceiverLogQuery logQuery = new MonitorReceiverLogQuery();
		//以id升序排序
		logQuery.setSortBy("id");
		logQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				logQuery.setPage(Integer.parseInt(pageNum));
			}
			String serviceName = request.getParameter("serviceName");
			if (! StringUtils.isBlank(serviceName)) {
				logQuery.setServiceName(serviceName);
				model.addAttribute("serviceName", serviceName);
			}
			String  userName = request.getParameter("userName");

			if (! StringUtils.isBlank(userName) && ! userName.equals("-1")) {
				logQuery.setUserName(userName);
				model.addAttribute("userName", userName);
			}
		}
		Integer totalCount = monitroReceiverLogDao.getMonitorReceiverLogCountByQuery(logQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, logQuery.getPageSize(), logQuery.getPage(), logQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<MonitorReceiverLog> logList = monitroReceiverLogDao.getMonitorReceiverLogByQuery(logQuery);
		List<ExtendMonitorReceiverLog> elogList = new ArrayList<ExtendMonitorReceiverLog>();
		if (logList != null && logList.size() > 0) {
			try {
				for (MonitorReceiverLog log : logList) {
					ExtendMonitorReceiverLog elog = new ExtendMonitorReceiverLog();
					BeanUtils.copyProperties(elog, log);
//					//获取机构类型常量
//					if(org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_HEAD_QUARTERS.getStatus()) == 0)
//						eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_HEAD_QUARTERS"));
//					else if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_AREA.getStatus()) == 0)
//						eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_AREA"));
//					else if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_THIRD_PARTY.getStatus()) == 0)
//						eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_THIRD_PARTY"));
//					else if (org.getType().compareTo(EnumOrganizationType.ORGANIZATION_IS_OTHER.getStatus()) == 0)
//						eOrg.setOrgType(MappingConstantConfig.getValue("ORGANIZATION_IS_OTHER"));
//					eOrgInfoList.add(eOrg);
					elogList.add(elog);
				}
			} catch(Exception e) {
				LOGGER.error("将日志信息进行对象拷贝 对象属性值拷贝过程中出现异常：{}", e);
			}
		}
//		model.addAttribute("MonitorSqlLogList", elogList);
		model.addAttribute("MonitorSqlLogList", logList);
		
	}

	@Override
	public Integer getMonitorSqlLogCountByQuery(
			MonitorReceiverLogQuery monitorReceiverLogQuery) {
		// TODO Auto-generated method stub
		return monitroReceiverLogDao.getMonitorReceiverLogCountByQuery(monitorReceiverLogQuery);
	}

	@Override
	public MonitorReceiverLog getMonitorReceiverLogById(Long id) {
		
		return 	 monitroReceiverLogDao.getMonitorReceiverLogById(id);
	}

	@Override
	public ExtendMonitorReceiverLog getExtendMonitorReceiverLogById(Long id) {
		LOGGER.debug("Service层：根据日志id获取日志扩展信息");
		MonitorReceiverLog monitorReceiverLog = this.getMonitorReceiverLogById(id);
		if (monitorReceiverLog == null || monitorReceiverLog.getId() == null) {
			LOGGER.debug("根据日志id获取日志扩展信息，没有查取到日志信息：Id {}", id);
			return null;
		}
		ExtendMonitorReceiverLog eLog = new ExtendMonitorReceiverLog();
	
		try {
			BeanUtils.copyProperties(eLog, monitorReceiverLog);
		} catch (Exception e) {
			LOGGER.error("根据日志id获取日志扩展信息，拷贝属性出错", id);
			e.printStackTrace();
		}
		
		for (Organization org : systemConstant.getOrganizationList()) {
			if (org.getId().compareTo(eLog.getOrgId()) == 0) {
				eLog.setOrgName(org.getOrgName());
				break;
			}
		}

		eLog.setOrgName(organizationService.getOrgNameByOrgId(eLog.getOrgId()));
		return eLog;
	}

	@Override
	public void selectlogdata(ModelMap model, String method,
			HttpServletRequest request,SyslogQuery syslogQuery) {
		//以id升序排序
		syslogQuery.setSortBy("logid");
		syslogQuery.setSortType("2");
		if (method.equals("POST")) {
			
			String systemconfigtype = request.getParameter("systemconfigtype");
			if (! StringUtils.isBlank(systemconfigtype) && !systemconfigtype.equals("-1")) {
				syslogQuery.setLogtype(systemconfigtype);
				model.addAttribute("systemconfigtype", systemconfigtype);
			}
			
			String  starttime = request.getParameter("starttime");
			if (! StringUtils.isBlank(starttime)) {
				syslogQuery.setStratime(starttime);
				model.addAttribute("starttime", starttime);
			}

			String  endtime = request.getParameter("endtime");
			if (! StringUtils.isBlank(endtime)) {
				syslogQuery.setEndtime(endtime);
				model.addAttribute("endtime", endtime);
			}
		}
		
		String fenye = request.getParameter("pageNumInput");
		if(fenye != null && fenye !=""){
			int num = Integer.parseInt(fenye);
			syslogQuery.setPage(num);
		}
		//分页用
		Integer totalCount = monitroReceiverLogDao.getLogCountByQuery(syslogQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, syslogQuery.getPageSize(), syslogQuery.getPage(), syslogQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		//获得数据
		List<Syslog> getloglist = monitroReceiverLogDao.getLogByQuery(syslogQuery);
		model.addAttribute("getloglist", getloglist);
		//获得类型
		List gettype= monitroReceiverLogDao.getLogType();
		model.addAttribute("gettype", gettype);
		
		//获得下拉列表的值
		String type =request.getParameter("systemconfigtype");
		model.addAttribute("type", type);
		
	}

	/***
	 * 新增系统日志
	 */
	@Override
	public boolean saveSystemLog(Syslog syslog) {
		LOGGER.debug("Service层：保存日志记录");
		Integer resultNum1 = monitroReceiverLogDao.saveSystemLog(syslog);
		return resultNum1 == null ? false : resultNum1.compareTo(new Integer(0)) != 0;
	}

}
