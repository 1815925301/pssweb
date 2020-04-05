package com.sinosoft.security.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.constant.MappingConstantConfig;
import com.sinosoft.security.po.extend.ExtendUsers;


/**
 * @Package com.sinosoft.security.controller
 * @ClassName: LoginLogoutController
 * @Description: 用户登录与退出系统 控制层
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-6 下午09:27:14
 */
@Controller
public class LoginLogoutController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginLogoutController.class);
	
	//session 注册器
	@Autowired
	SessionRegistry sessionRegistry;
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String root(String msg, HttpServletRequest request, ModelMap model) {
		model.addAttribute("ctx", request.getContextPath()); //应用名称
		LOGGER.debug("进入到用户登录控制器处理逻辑！");
		if (!StringUtils.isBlank(msg)) {
			if (msg.equals("error")) {
				String errorInfo = "";
				if (request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null) {
					
					String exceptionInfo = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION").toString();
					if (exceptionInfo != null) {
						for (Map.Entry<String, String> entry : Constant.LONGIN_EXCEPTION.LONGIN_EXCEPTION_MAP.entrySet()) {
							if (exceptionInfo.contains(entry.getKey())) 
								errorInfo = entry.getValue() + " ";
						}
					}
					LOGGER.info("用户登录遇到错误：{}" , errorInfo);
					errorInfo += "登录失败，请重新登录！";
					model.addAttribute("message", errorInfo);
					if (request.getSession().getAttribute("LOGIN_USER_NAME") != null) {
						model.addAttribute("username", request.getSession().getAttribute("LOGIN_USER_NAME"));
					}
				} else if (request.getAttribute("USER_MENU_ERROR") != null) {
					errorInfo = "系统异常，加载菜单失败！";
					model.addAttribute("message", errorInfo);
				}
			}
			if (msg.equals("expired")) {
				LOGGER.info("认证已经失效，可能是账户在其他地方登录造成！");
				model.addAttribute("message", "认证已经失效，可能是您的账户在其他地方登录造成，请重新登录！");
			}
		}
		return "login"; // 设置返回页面，这里对应 / 目录下的 login.ftl 文件
	}
	
	/**
	 * 用户登录控制器处理逻辑
	 * @param msg
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author mrajian
	 * @date 2013-10-6 下午09:33:56
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(String msg, HttpServletRequest request, ModelMap model) {
		model.addAttribute("ctx", request.getContextPath()); //应用名称
		LOGGER.debug("进入到用户登录控制器处理逻辑！");
		ServletContext application = request.getSession().getServletContext();
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		if(eUser!=null){
		int errnum = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(eUser!=null){
		if(application.getAttribute(eUser.getUserName()+"_logerr")==null)
			errnum = 0;
		else
			errnum = Integer.parseInt(application.getAttribute(eUser.getUserName()+"_logerr").toString());
		
		}
		
		int maxtrynum = Integer.parseInt(MappingConstantConfig.getValue("MaxTryNum"));
		int logindelay = Integer.parseInt(MappingConstantConfig.getValue("PauseLogin"));
		//获取当前时间前15分钟
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastLogin =df1.format( new Date(new Date(System.currentTimeMillis()).getTime() - 60000*logindelay));
		String oldLogin = (String)application.getAttribute(eUser.getUserName()+"_lastlog");
		if(oldLogin==null) oldLogin =df.format(new Date(System.currentTimeMillis()));
		if(lastLogin.compareTo(oldLogin)>=0){
			//清除错误登录次数和上次错误登录的时间
			application.removeAttribute(eUser.getUserName()+"_logerr");
			application.removeAttribute(eUser.getUserName()+"_lastlog");
		}
		if(errnum>=maxtrynum && lastLogin.compareTo(oldLogin)<0){
			String loginmsg="对不起，登录尝试超过"+maxtrynum+"次，账号被锁定,请"+logindelay+"分钟后重试";
			model.addAttribute("message", loginmsg);
		}
		else if (!StringUtils.isBlank(msg)) {
			if (msg.equals("error")) {
				String errorInfo = "";
				if (request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION") != null) {
					
					String exceptionInfo = request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION").toString();
					if (exceptionInfo != null) {
						for (Map.Entry<String, String> entry : Constant.LONGIN_EXCEPTION.LONGIN_EXCEPTION_MAP.entrySet()) {
							if (exceptionInfo.contains(entry.getKey())) 
								errorInfo = entry.getValue() + " ";
						}
					}
					//登录失败记录
					if(eUser!=null){
					application.setAttribute(eUser.getUserName()+"_logerr",Integer.toString(++errnum));
					application.setAttribute(eUser.getUserName()+"_lastlog",df.format(new Date(System.currentTimeMillis())));	//记录登录出错的时间
						errorInfo += "登录失败，请重新登录！";
					LOGGER.info("用户登录遇到错误：{}" , errorInfo);
					model.addAttribute("message", errorInfo);
					}
					if (request.getSession().getAttribute("LOGIN_USER_NAME") != null) {
						model.addAttribute("username", request.getSession().getAttribute("LOGIN_USER_NAME"));
					}
				} else if (request.getAttribute("USER_MENU_ERROR") != null) {
					errorInfo = "系统异常，加载菜单失败！";
					model.addAttribute("message", errorInfo);
				}
			}
			
			if (msg.equals("expired")) {
				LOGGER.info("认证已经失效，可能是账户在其他地方登录造成！");
				model.addAttribute("message", "认证已经失效，可能是您的账户在其他地方登录造成，请重新登录！");
			}
			if(msg.equals("logout")){
				List<Object> slist = sessionRegistry.getAllPrincipals();
		        int totalCount = slist.size()-1; 
			}
		}
		}
		return "login"; // 设置返回页面，这里对应 / 目录下的 login.ftl 文件
	}
	

	/**
	 * 列出当前在线用户的信息
	 * SessionRegistry跟踪所有活跃用户session的信息。我们可以可以很容易地在一个页面中列出所有的用户活跃用户以及他们在站点中使用的名字。
	 * @param model
	 * @return String
	 * @throws
	 * @author mrajian
	 * @date 2013-10-6 下午09:37:35
	 * @version V1.0
	 * @throws IOException 
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/onlineUserList.do")
	public void onlineUserList(ModelMap model, HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		//getAllPrincipals：返回拥有活跃session的Principal对象（典型情况下为UserDetails对象）所组成的List
		List<Object> slist = sessionRegistry.getAllPrincipals();
        int totalCount = slist.size();  
      
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        List<Object> pageList = slist;  
        StringBuffer retVal = new StringBuffer("[");  
        int k = 0;  
        for(int i=0; i<pageList.size(); i++) {
        	// getAllSessions(principal, includeExpired)：得到指定Principal的SessionInformation组成的List，包含了每个session的信息。也能够包含过期的session
            List<SessionInformation> sessionList = sessionRegistry.getAllSessions(pageList.get(i), true);   
            User user = (User)pageList.get(i);  
            for(SessionInformation t : sessionList){
                if(k != 0){  
                    retVal.append(",");
                }  
                retVal.append("{\"id\":\""+k+"\",\"userName\":\""+user.getUsername()+"\",\"expired\":\""+t.isExpired()
                		+"\",\"sessionId\":\""+t.getSessionId()+"\",\"lastRequest\":\""+sdf.format(t.getLastRequest())+"\"}");  
                k = k+1;
            }  
        }  
        retVal.append("]");
		model.addAttribute("message", retVal);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userCount", totalCount);
		response.getWriter().write(jsonObj.toString());
	}
	
	/**
	 * 
	 * @param msg
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author mrajian
	 * @date 2013-10-6 下午09:39:55
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/shotOffOnlineUser.do")
	public String shotOffOnlineUser(String msg, HttpServletRequest request, ModelMap model) {
		//getAllPrincipals：返回拥有活跃session的Principal对象（典型情况下为UserDetails对象）所组成的List
		List<Object> slist = sessionRegistry.getAllPrincipals();
        for(int i=0; i < slist.size(); i++) {
        	// getAllSessions(principal, includeExpired)：得到指定Principal的SessionInformation组成的List，包含了每个session的信息。也能够包含过期的session
            List<SessionInformation> sessionList = sessionRegistry.getAllSessions(slist.get(i), true);   
            User user = (User)slist.get(i);
            for(SessionInformation t : sessionList) {
            	//只踢掉传递来的用户
            	if (!StringUtils.isBlank(msg) && !StringUtils.isBlank(msg.trim())) {
            		if (user.getUsername().equals(msg.trim())) {
            			if (t.isExpired())
                    		t.expireNow();
            			break;
            		} 
            	} else { //全部用户都踢掉
            		if (t.isExpired())
                		t.expireNow();
            	}
            }  
        }
        model.addAttribute("message", msg + "已经退出");
        return "main";
     }

}
