package com.sinosoft.common.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import com.sinosoft.common.constant.Constant;
import com.sinosoft.dblog.po.Syslog;
import com.sinosoft.dblog.service.MonitorReceiverLogService;

/***
 * 通过调用saveErrorLog保存系统日志信息,只需要传三个参数就行.
 * @author leo
 *
 */
public class SystemLogUtil {
	public static boolean saveErrorLog(HttpServletRequest request,String type,String message) {
		Syslog syslog =new Syslog();
		
		//获得常量类里的保存日志方法 ---通过获得上下文Sping容器获得存在常量里的MonitorReceiverLogService，但是这个是获得service层对象，里面包含很多方法，
		MonitorReceiverLogService mgrs=(MonitorReceiverLogService)request.getServletContext().getAttribute(Constant.SYSTEM_LOG_MANAGER);
		syslog.setLogtype(type);
		syslog.setDescription(message);
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		syslog.setCreatetime(time);
		//这句话是获得对象里面的一个保存的方法。
		boolean savesuccessorfail = mgrs.saveSystemLog(syslog);
		 return savesuccessorfail;
	}
	
	public static void saveUserLog(String eventType,String eventContent) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date dd = Calendar.getInstance().getTime();
//		
//		sysuserlog.setEventtype(eventType);
//		sysuserlog.setEventcontent(eventContent);
//		sysuserlog.setEventtime(DateTimeUtils.StringToDate(sdf.format(dd),
//				"yyyy-MM-dd HH:mm:ss"));
//
//		baseform.getSysUserLogMgr().saveSystemUserLog(sysuserlog);

	}
	
	

}
