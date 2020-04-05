package com.sinosoft.security;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sinosoft.common.constant.MappingConstantConfig;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.security
 * @ClassName: MyUsernamePasswordAuthenticationFilter
 * @Description: Spring Security的登陆验证
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-2 下午04:00:37
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyUsernamePasswordAuthenticationFilter.class);
	
	public static final String VALIDATE_CODE = "validateCode";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	
	@Resource
	private UsersService usersService;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("NOT_SUPPORTED_GET");
		}
		if(MappingConstantConfig.getValue("UseVerifyCode").equals("true"))//如果开启使用验证码则执行
		{checkValidateCode(request);}
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		if (StringUtils.isBlank(username)) {
			throw new AuthenticationServiceException("USERNAME_IS_NULL");
		}
		
		request.getSession().setAttribute("LOGIN_USER_NAME", username);
		
		if (StringUtils.isBlank(password)) {
			throw new AuthenticationServiceException("PASSWORD_IS_NULL");
		}
		
		//这里的作用就只是判断登录名是否存在于数据库中
		Users user = usersService.getUserInfoByUserName(username);
		
		if(user == null) {
			throw new AuthenticationServiceException("USERNAME_IS_NOT_EXIST"); 
		}
		
		//将用户对象中的密码置为空，然后将用户信息放入到session中
		user.setPassword("");
		ExtendUsers eUser = new ExtendUsers();
		try {
			BeanUtils.copyProperties(eUser, user);
		} catch(Exception e){
			LOGGER.error("获取当前用户的具体信息 对象属性值拷贝过程中出现异常：{}", e);
		}
		request.getSession().setAttribute("CURRENT_USER_INFO", eUser);
		
		
		//将菜单清空
		request.getSession().setAttribute("USER_MENU", null);
		
		ServletContext application = request.getSession().getServletContext();
		//验证登录错误次数
	/*	int errnum = 0;
		if(application.getAttribute(eUser.getUserName()+"_logerr")==null)
			errnum = 0;
		else
	 	errnum = Integer.parseInt(application.getAttribute(eUser.getUserName()+"_logerr").toString());
		int maxtrynum = Integer.parseInt(MappingConstantConfig.getValue("MaxTryNum"));
		int logindelay = Integer.parseInt(MappingConstantConfig.getValue("PauseLogin"));
		//获取当前时间前15分钟
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastLogin =df.format( new Date(new Date(System.currentTimeMillis()).getTime() - 60000*logindelay));
		String oldLogin = (String)application.getAttribute(eUser.getUserName()+"_lastlog");
		if(oldLogin==null) oldLogin =df.format(new Date(System.currentTimeMillis()));
		if(lastLogin.compareTo(oldLogin)>=0){
			//清除错误登录次数和上次错误登录的时间
			application.removeAttribute(eUser.getUserName()+"_logerr");
			application.removeAttribute(eUser.getUserName()+"_lastlog");
		}
		if(errnum>=maxtrynum && lastLogin.compareTo(oldLogin)<0){
			//String msg="对不起，登录尝试超过"+maxtrynum+"次，账号被锁定,请"+logindelay+"分钟后重试";
			throw new AuthenticationServiceException("TRY_TOOMANY");
	    }*/
		//UsernamePasswordAuthenticationToken实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		
		// 允许子类设置详细属性
        setDetails(request, authRequest);
		
        // 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	protected void checkValidateCode(HttpServletRequest request) { 
		HttpSession session = request.getSession();
		
	    String sessionValidateCode = obtainSessionValidateCode(session); 
	    //让上一次的验证码失效
	    session.setAttribute(VALIDATE_CODE, null);
	    String validateCodeParameter = obtainValidateCodeParameter(request);  
	    if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {  
	        throw new AuthenticationServiceException("validateCode.notEquals");  
	    }  
	}
	
	private String obtainValidateCodeParameter(HttpServletRequest request) {
		Object obj = request.getParameter(VALIDATE_CODE);
		return null == obj ? "" : obj.toString().trim();
	}

	protected String obtainSessionValidateCode(HttpSession session) {
		Object obj = session.getAttribute(VALIDATE_CODE);
		return null == obj ? "" : obj.toString().trim();
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		Object obj = request.getParameter(USERNAME);
		return null == obj ? "" : obj.toString().trim();
	}

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		Object obj = request.getParameter(PASSWORD);
		return null == obj ? "" : obj.toString().trim();
	}

}
