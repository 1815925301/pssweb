package com.sinosoft.security.controller;


import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;












import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sinosoft.business.po.OrderMain;
import com.sinosoft.business.po.PssCollectInfo;
import com.sinosoft.business.po.PssCountry;
import com.sinosoft.business.po.PssOrderPay;
import com.sinosoft.business.po.SysUserset;
import com.sinosoft.business.po.UserUpdate;
import com.sinosoft.business.po.query.PssCountryQuery;
import com.sinosoft.business.service.PssCountryService;
import com.sinosoft.business.service.PssOrderPayService;
import com.sinosoft.business.service.UserUpdateService;
import com.sinosoft.common.constant.MappingConstantConfig;
import com.sinosoft.common.mail.MailSenderInfo;
import com.sinosoft.common.mail.SimpleMailSender;
import com.sinosoft.common.util.ActivityStringUtils;
import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.UserPassword;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.UsersQuery;
import com.sinosoft.security.service.UserRoleService;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.security.controller
 * @ClassName: UsersController
 * @Description: 用户信息管理 控制器
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-7 下午02:58:23
 */
@Controller
public class UsersController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);
	
	@Resource
	private UsersService usersService;
	@Resource
	private UserRoleService usersRoleService;
	@Resource
	private UserUpdateService messageService;
	@Resource
	private PssCountryService pssCountryService;
	@Resource
	private PssOrderPayService PSS_ORDER_PAYService;
	/**
	 * 用户管理页面
	 * @param request
	 * @param model
	 * @return String
	 * @throws
	 * @author mrajian
	 * @date 2013-10-7 下午02:55:55
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/users.do")
	public String usersManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问用户管理页面", method);
		model.addAttribute("pageHanName", "用户"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		usersService.getUsersInfoForInitPage(model, method, request);
		return "manage/users" ;
	}
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/home.do")
	public String home(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问用户管理页面", method);
		model.addAttribute("pageHanName", "用户"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		model.addAttribute("bathPath", MappingConstantConfig.getValue("DEPLOY_ADDRESS")); 
		return "manage/home" ;
	}
	
	/**
	 * 登录首页
	 * @author leeyin
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/pssindex.html")
	public String pssindex(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问用户管理页面", method);
		model.addAttribute("pageHanName", "用户"); //页面名称
		model.addAttribute("ctx", request.getContextPath()); //应用名称
		if(request.getSession().getAttribute("CURRENT_USER_INFO")!=null && !request.getSession().getAttribute("CURRENT_USER_INFO").equals("")){
			model.addAttribute("CURRENT_USER_INFO", request.getSession().getAttribute("CURRENT_USER_INFO"));
		}else{
			model.addAttribute("CURRENT_USER_INFO", new ExtendUsers());//创建一个新实体
		}
		model.addAttribute("bathPath", MappingConstantConfig.getValue("DEPLOY_ADDRESS")); 
		return "manage/home" ;
	}
	
	
	/**
	 * 注册跳转页面
	 * @param user
	 * @param request
	 * @return Map<String,Object>
	 * @throws
	 * @author wlg
	 * @date 2016-07-18 
	 * @version V1.0
	 */
	@RequestMapping(value = "/register.html")
	public String register(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		PssCountryQuery pssCountryQuery = new PssCountryQuery();	
		// 得到所有国家
		List<PssCountry> countryList = pssCountryService
				.getCountry(pssCountryQuery);
		model.addAttribute("ctx", request.getContextPath());
		model.addAttribute("countryList", countryList);
		return "manage/register" ;
	}
	/**
	 * 注册用户
	 * @param user
	 * @param request
	 * @return Map<String,Object>
	 * @throws
	 * @author wlg
	 * @date 2016-07-18 
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/registerUser.html")
	public ModelAndView registerUser(Users users,HttpServletRequest request, ModelMap model) throws Exception{
	
		ModelAndView mav=new ModelAndView();
		String action =request.getParameter("action");
		if("register".equals(action)){
		//1.先把注册用户的信息入库
		users.setRoleId(61);//注册用户默认角色
		users.setIsLock(1);
		users.setDownloads(1400);
		usersService.addUser(users, model);
		mav.addObject("text", "注册成功");
		mav.setViewName("manage/register_success");
		
		}else if("activate".equals(action)){
			String userEmail=request.getParameter("userEmail");
			
				usersService.processActivate(userEmail);//激活方法
				mav.setViewName("manage/activate_success");
		}

		return mav;
		
		}     
	  
	/**
	 * 审核用户信息
	 * @param userName
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author wlg
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/checkrUserIslock.do")
	public@ResponseBody Map<String, Object> checkrUserIslock(Users users,HttpServletRequest request, ModelMap model){
		LOGGER.debug("更新用户信息！");
		String userName=users.getUserName();
		//拆分前台传来的字符串，构成一个数组
		String str[]=userName.split(",");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		for (int i = 0; i < str.length; i++) {
		Users u=usersService.getUserInfoByUserName(str[i]);
		users.setId(u.getId());
		users.setRemark(users.getRemark());
		users.setPassword(u.getPassword());
		users.setTrueName(u.getTrueName());
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || users == null) {
				modelMap.put("status", "exception");
			} else {
				//修改用户是否锁定状态
				if (usersService.updateUserLock(users, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新用户信息成功！");
					//发邮件
					MailSenderInfo mailInfo = new MailSenderInfo();
					mailInfo.setMailServerHost("smtp.163.com");
					mailInfo.setMailServerPort("25");
					mailInfo.setValidate(true);
					mailInfo.setUserName("zkr_admin@163.com");
//					mailInfo.setPassword(DESPlus.desPlusDecrypt("18a437fc6e0b9784def947d249cd58b4"));// 您的邮箱密码
					mailInfo.setPassword("a123456");
					mailInfo.setFromAddress("zkr_admin@163.com");
					mailInfo.setToAddress(u.getUserEmail());
					mailInfo.setSubject("这是测试");
				if(users.getIsLock()==0){
					String path = request.getContextPath();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
					mailInfo.setContent("恭喜你！注册成功！请尽快点击链接进行激活,激活成功即可登录!<a href="+basePath+"/registerUser.html?action=activate&userEmail="+u.getUserEmail()+">"+basePath+"/registerUser.html?action=activate&userEmail="+u.getUserEmail()+"</a>");
					
				}else{
					mailInfo.setContent("审核失败，原因:"+users.getRemark());
						
				}
					
					SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新用户信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		
		}
		}
		return modelMap.getModelMap();
	}
	/**
	 * 忘记密码跳转页面
	 * @param user
	 * @param request
	 * @return Map<String,Object>
	 * @throws
	 * @author wlg
	 * @date 2016-07-20
	 * @version V1.0
	 */
	@RequestMapping(value = "/forgotPassword.html")
	public String forgotPassword(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		Integer identifier=1;
		model.addAttribute("identifier", identifier);
		return "manage/forgotPassword" ;
	
	}
	/**
	 * 忘记用户名跳转页面
	 * @param user
	 * @param request
	 * @return Map<String,Object>
	 * @throws
	 * @author wlg
	 * @date 2016-07-20
	 * @version V1.0
	 */
	@RequestMapping(value = "/forgotuserName.html")
	public String forgotuserName(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("以 {} 方式访问管理页面", method);
		Integer identifier=2;
		model.addAttribute("identifier", identifier);
		return "manage/forgotPassword" ;
	
	}
	/**
	 * 忘记密码信息验证
	 * @param user
	 * @param request
	 * @return Map<String,Object>
	 * @throws
	 * @author wlg
	 * @date 2016-07-18 
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/retrievePassword.html")
	public ModelAndView retrievePassword(Users users,HttpServletRequest request, ModelMap model){
		LOGGER.debug("用户找回密码");
		ModelAndView ma=new ModelAndView();
		
		Users user=usersService.getUserInfoByUserEmail(users.getUserEmail());
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		if(user != null){
			try {
				//发送邮件
				MailSenderInfo mailInfo = new MailSenderInfo();
				mailInfo.setMailServerHost(PropertiesUtils.key("smtp.163.com", locale));
				mailInfo.setMailServerPort(PropertiesUtils.key("25", locale));
				mailInfo.setValidate(true);
				mailInfo.setUserName(PropertiesUtils.key("adminemail", locale));
//				mailInfo.setPassword(DESPlus.desPlusDecrypt("18a437fc6e0b9784def947d249cd58b4"));// 您的邮箱密码
				mailInfo.setPassword(PropertiesUtils.key("emailpassword", locale));
				mailInfo.setFromAddress(PropertiesUtils.key("adminemail", locale));
				mailInfo.setToAddress(users.getUserEmail());
				if(users.getIdentifier()==1){
					mailInfo.setSubject(PropertiesUtils.key("emailtitle", locale));
					String path = request.getContextPath();
					String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
					mailInfo.setContent(PropertiesUtils.key("forgotpswd", locale)+"<a href="+basePath+"updatetops.html?userEmail="+users.getUserEmail()+">"+basePath+"updatetops.html?userEmail="+users.getUserEmail()+"</a>");
				}
				if(users.getIdentifier()==2){
					mailInfo.setSubject(PropertiesUtils.key("emailtitle", locale));
					mailInfo.setContent(PropertiesUtils.key("forgotusernameemail", locale)+user.getUserName());
				}
				
				SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式
			} catch (Exception e) {
				//异常页面
				ma.setViewName("manage/exceptions");
			}
			//邮箱发送成功页面
			ma.setViewName("manage/retrievePassword_desc");
			return ma;
		}else{
			//验证不通过页面
			ma.setViewName("manage/retrievePassword_error");
			return ma;
		}
	}
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/updatetops.html")
	public String updateto(HttpServletRequest request, ModelMap model){
		LOGGER.debug("更新用户密码！");
		String userEmail=request.getParameter("userEmail");
		//根据邮箱查询用户信息
		Users u=usersService.getUserInfoByUserEmail(userEmail);
		model.addAttribute("u", u);
		
		return "manage/retrievePassword_success";
		
	}
	/**
	 * 修改密码
	 * @param user
	 * @param request
	 * @return Map<String,Object>
	 * @throws
	 * @author wlg
	 * @date 2016-07-18 
	 * @version V1.0
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},value = "/updatePassword.html")
	public String updatePassword(Users users,HttpServletRequest request, ModelMap model){
		LOGGER.debug("更新用户密码！");
		users.setPassword(ActivityStringUtils.createMd5Str(users.getPassword(), null));
		usersService.updatePassword(users);
		
		return "manage/retrievePassword_end";
		
	}
	/**
	 * 根据指定的用户id 获取用户信息
	 * @param userId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午12:35:17
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/showUsers.do")
	public @ResponseBody Map<String, Object>  getUserInfo(HttpServletRequest request, HttpServletResponse response) {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		usersService.getUserInfo(modelMap, request);
		return modelMap.getModelMap();
	}
	
	/**
	 * 保存新的用户信息
	 * @param user
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午02:14:37
	 * @version V1.0
	 */
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveNewUser.do")
	public @ResponseBody Map<String, Object> addNewUser(Users user, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("保存新的用户信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || user == null) {
				modelMap.put("data", "保存新的用户信息的操作出现异常");
			} else {
				if (usersService.addNewUser(user, eUser, modelMap)) {
						modelMap.put("data", "保存新的用户信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("保存新的用户信息的操作出现异常：{}", e);
			modelMap.put("data", "保存新的用户信息的操作出现异常");
		}
		return modelMap.getModelMap();
	}

	
	/**
	 * 更新用户信息
	 * @param user
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午05:19:11
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeUser.do")
	public @ResponseBody Map<String, Object> udpateUser(Users user, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新用户信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || user == null) {
				modelMap.put("status", "exception");
			} else {
				if (usersService.updateUserInfo(user, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新用户信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新用户信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/changeUserPass.do")
	public @ResponseBody Map<String, Object> changeUserPass(Users user, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新用户信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || user == null) {
				modelMap.put("status", "exception");
			} else {
				if (usersService.updateUserPass(user, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新用户信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新用户信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 获取当前用户的具体信息
	 * @param request
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-7 下午07:54:46
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/currentUserInfo.do")
	public @ResponseBody Map<String, Object> getCurrentUserInfo(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取当前用户的具体信息！");
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		if (eUser.getOrgName() == null || eUser.getOrgName().equals("")) {
			LOGGER.debug("获取当前用户所属的机构名称");
			request.getSession().setAttribute("CURRENT_USER_INFO", eUser);
		}
		ActivityModelMap modelMap = new ActivityModelMap(request);
		modelMap.put("total", 1);
		modelMap.put("data", eUser);
		modelMap.put("status", "success");
		return modelMap.getModelMap();
	}
	
	/**
	 * 删除用户信息
	 * @param userId
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-19 下午09:16:27
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/removeUser.do")
	public @ResponseBody Map<String, Object> removeUserInfo(
			@RequestParam(value = "userId", required = true)Long userId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("删除用户信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || userId == null) { 
				modelMap.put("status", "exception");
			} else {
				if (usersService.removeUser(userId, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "删除用户信息成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("删除用户信息的操作出现异常：{}", e);
			modelMap.put("status", "error");
			modelMap.put("data", "系统中存在该用户的操作数据，如上报活动等！<BR/>无法删除！");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 修改当前用户的基本信息
	 * @param newUser
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-10 上午08:57:18
	 * @version V1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/setCurrentUserInfo.do")
	public @ResponseBody Map<String, Object> setCurrentUserInfo(Users newUser, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("修改当前用户的基本信息");
		LOGGER.debug("接收到的用户信息：{}" , newUser.toString());
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || newUser == null) {
				modelMap.put("status", "exception");
			} else {
				if (usersService.updateCurrentUserBaseInfo(eUser, newUser, modelMap)) {
					//此时更新成功，将改后的用户信息更新到Session中
					request.getSession().setAttribute("CURRENT_USER_INFO", eUser);
					modelMap.put("status", "success");
					modelMap.put("data", "用户信息更新成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("修改当前用户的基本信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 设置当前用户的密码信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/setCurrentUserPassInfo.do")
	public @ResponseBody Map<String, Object> setCurrentUserPassInfo(UserPassword password, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("修改当前用户的密码信息");
		LOGGER.debug("接收到的密码信息 :{}" , password.toString());
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || password == null) {
				modelMap.put("status", "exception");
			} else {
				if (usersService.updateCurrentUserPassInfo(eUser, password, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "密码信息更新成功！");
				}
			}
		} catch(Exception e) {
			LOGGER.error("修改当前用户的密码信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 根据指定的用户id 获取用户角色信息
	 * @param userId
	 * @param request
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getUserRole.do")
	public @ResponseBody Map<String, Object> getUserRoleInfo(
			@RequestParam(value = "userId", required = true)Long userId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("获取给定用户id的用户角色信息，userId={}", userId);
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			if (userId == null) {
				LOGGER.error("获取给定用户id的用户角色信息失败，用户id为空");
				modelMap.put("status", "error");
				modelMap.put("data", "该用户不存在，请刷新页面重新请求!");
			} else {
				usersService.getUserRoleInfoByUserId(userId, modelMap);
			}
		} catch(Exception e) {
			LOGGER.error("获取给定用户id的用户角色信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 更新用户的角色信息
	 * @param userId
	 * @param roleId
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/changeUserRole.do")
	public @ResponseBody Map<String, Object> setUserRole(
			@RequestParam(value = "userId", required = true)Long userId, 
			@RequestParam(value = "roleId", required = true)Long roleId, 
			HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("更新用户角色信息！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		try {
			ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			if (eUser == null || userId == null || roleId == null) {
				modelMap.put("status", "exception");
			} else {
				if (usersService.updateUserRole(userId, roleId, eUser, modelMap)) {
					modelMap.put("status", "success");
					modelMap.put("data", "更新用户角色信息成功！");
				} else {
					
				}
			}
		} catch(Exception e) {
			LOGGER.error("更新用户角色信息的操作出现异常：{}", e);
			modelMap.put("status", "exception");
		}
		return modelMap.getModelMap();
	}
	
	/**
	 * 获取用户j进行分配组
	 */
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/userList.do")
	public String getChooseUser(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("获取用户j进行分配组！");
		model.addAttribute("pageHanName", "用户"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		usersService.getChooseUser(model, method, request);
		return "manage/usergroups/userList" ;
	}
	
	/**
	 * 获取用户权限树
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/showACL.do")
	public @ResponseBody String showACL(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug(" 获取用户权限树！");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		return  "";
	}
	
	/** 
	 * 用户更新提示 
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/updateTip.do")
	public @ResponseBody Map<String, Object> getUpdateMessage(HttpServletRequest request, HttpServletResponse response) {
		// 获得当前登录用户的信息
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		ActivityModelMap modelMap = new ActivityModelMap(request);
		
		// 根据用户id获取用户更新信息
		UserUpdate messageUpdate = messageService.selectMessageById(request, modelMap, eUser.getId());
		
		modelMap.put("eUser", eUser);
		modelMap.put("messageUpdate", messageUpdate);
		return modelMap.getModelMap();
	}
	/** 
	 * 用户更新提示 
	 * @throws ParseException 
	 **/
	@RequestMapping(method = RequestMethod.POST, value = "/queryuserday.do")
	public @ResponseBody Map<String, Object> queryuserday(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		// 获得当前登录用户的信息
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		if(eUser!=null){
		ActivityModelMap modelMap = new ActivityModelMap(request);
		// 根据用户名获取用户更新信息
		Users userupdate = usersService.selectMessageByName(request, modelMap, eUser.getUserName());
		//查询用户更新设置的天数
		SysUserset userset=usersService.getUserset();
		if(userupdate.getUpdateTime()!=null && !userupdate.getUpdateTime().equals("")){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1=sdf.parse(userupdate.getUpdateTime());

			Calendar c = Calendar.getInstance();  

			c.setTime(d1);  

			c.add(Calendar.DATE, Integer.parseInt(userset.getFrequency()==null?"0":userset.getFrequency().toString()));  

			Date d3=c.getTime(); 
			
			Date d2=new Date();
			long daysBetween=(d3.getTime()-d2.getTime()+1000000)/(3600*24*1000);
			modelMap.put("daysBetween", daysBetween);
		}else{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1=sdf.parse(userupdate.getRegisterTime());
			Calendar c = Calendar.getInstance();  
			c.setTime(d1); 
			c.add(Calendar.DATE, Integer.parseInt(userset.getFrequency()==null?"0":userset.getFrequency().toString())); 
			Date d3=c.getTime(); 
			Date d2=new Date();
			long daysBetween=(d3.getTime()-d2.getTime()+1000000)/(3600*24*1000);
			modelMap.put("daysBetween", daysBetween);
		}
		modelMap.put("eUser", eUser);
		
		return modelMap.getModelMap();
		}else{
			return null;
		}
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/dredgeVIP.do")
	public String rolesManage(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("跳转到会员开通页面", method);
		model.addAttribute("pageHanName", "开通会员"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		usersService.getDredgeVIP(model, method, request);
		return "manage/" + model.get("pageName");
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/dredgeVIP2.do")
	public @ResponseBody Map<String, Object> dredgeVIP2(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		// 获得当前登录用户的信息
		String method = request.getMethod();
		ActivityModelMap modelMap = new ActivityModelMap(request);
		usersService.getDredgeVIP(modelMap, method, request);
		return modelMap.getModelMap();
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateBeforeUserRoleInfo.do")
	public @ResponseBody Map<String, Object> updateBeforeUserRoleInfo(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		// 获得当前登录用户的信息
		String method = request.getMethod();
		ActivityModelMap modelMap = new ActivityModelMap(request);
		usersRoleService.updateBeforeUserRoleInfo(modelMap, method, request);
		return modelMap.getModelMap();
	}
	
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/InfoOfVIP.do")
	public String InfoOfVIP(HttpServletRequest request, ModelMap model) {
		String method = request.getMethod();
		LOGGER.debug("跳转到会员信息页面", method);
		model.addAttribute("pageHanName", "会员信息"); //页面名称
		ActivityWebUtils.WrapperModle(request, model);
		usersService.getInfoOfVIP(model, method, request);
		return "manage/" + model.get("pageName");
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/rolepay.do")
	public String rolepay(HttpServletRequest request, ModelMap model) {
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		model.addAttribute("usercountry", eUser.getCountry());
		Locale locale =  PropertiesUtils.getLocale(request);//得到当前业务中的国际化语言(locale)
		String userbank=PropertiesUtils.key("pssuserbank",locale);
		String ubank[]=userbank.split(",");
		model.addAttribute("userbank", ubank);//用户银行
		String abaebank=PropertiesUtils.key("ABAEBANK",locale);
		String abank[]=abaebank.split(",");
		model.addAttribute("abaebank", abank);//ABAE银行
		return "manage/rolepay";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveRolePay.do")
	public String  addsystem(@RequestParam(value="file",required=false) MultipartFile files,PssOrderPay orderPay, 
			HttpServletRequest request) throws IOException {
		ActivityModelMap modelMap = new ActivityModelMap(request);
		ModelMap model = new ModelMap();
		ActivityWebUtils.WrapperModle(request, model);
		String fileName= files.getOriginalFilename(); 
		File file2 = new File( request.getSession().getServletContext().getRealPath("/")+"upload"); 
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+"upload";
		if(!file2.exists()){
			file2.mkdirs();
		}
		File uploadFile = new File(file2+"/"+fileName);
		FileCopyUtils.copy(files.getBytes(), uploadFile);
		orderPay.setImageaddress(basePath+"/"+fileName);
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		String userid = String.valueOf(eUser.getId());
		String userName= String.valueOf(eUser.getUserName());
		orderPay.setUserid(userid);
		int num = usersRoleService.getRolePayCountByUserId(userid);
		if(num==0){
			PSS_ORDER_PAYService.save(request, orderPay, modelMap);
		}
		
		 return "redirect:/PersonalCenter.do";
	}
	
	
	
}