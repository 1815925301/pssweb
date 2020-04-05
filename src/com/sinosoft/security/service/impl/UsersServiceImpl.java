package com.sinosoft.security.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;
import javax.mail.Transport;
import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.jscape.inet.sftp.requests.Request;
import com.sinosoft.base.po.CheckErrorDto;
import com.sinosoft.base.po.TotalInfo;
import com.sinosoft.business.dao.PssOrderInfoDao;
import com.sinosoft.business.dao.SysUsersetDao;
import com.sinosoft.business.po.SysUserset;
import com.sinosoft.business.po.query.PssOrderInfoQuery;
import com.sinosoft.common.constant.EnumEditable;
import com.sinosoft.common.constant.EnumIsLock;
import com.sinosoft.common.constant.MappingInputLengthConfig;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.common.util.ActivityStringUtils;
import com.sinosoft.common.util.ChartModel;
import com.sinosoft.common.util.DateTimeUtils;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.dao.UserRoleDao;
import com.sinosoft.security.dao.UsersDao;
import com.sinosoft.security.dao.impl.UserRoleDaoImpl;
import com.sinosoft.security.po.Organization;
import com.sinosoft.security.po.Roles;
import com.sinosoft.security.po.UserPassword;
import com.sinosoft.security.po.UserRole;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.UsersQuery;
import com.sinosoft.security.service.OrganizationService;
import com.sinosoft.security.service.UserRoleService;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.security.service.impl
 * @ClassName: UsersServiceImpl
 * @Description: 系统用户信息 服务层实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-1 下午09:11:08
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {
	
	@Resource
	private UsersDao usersDao;
	
	@Resource
	private SystemConstant systemConstant;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private UserRoleDao userRoleDao;
	
	@Resource
	private OrganizationService organizationService;
	@Resource
	private SysUsersetDao usersetDao;
	@Resource
	private PssOrderInfoDao orderInfoDao;
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Override
	public void getUsersInfoForInitPage(ModelMap model, String method, HttpServletRequest request) {
		LOGGER.debug("Service层：根据查询参数获取用户信息 用于用户管理页面");
		UsersQuery usersQuery = new UsersQuery();
		
				
		//以id升序排序
		usersQuery.setSortBy("id");
		usersQuery.setSortType("1");
		if (method.equals("POST")) {
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				usersQuery.setPage(Integer.parseInt(pageNum));
			}
			String userName = request.getParameter("userNameSearch");
			if (! StringUtils.isBlank(userName)) {
				usersQuery.setUserName(userName);
				model.addAttribute("userName", userName);
			}
			String userEmail = request.getParameter("userEmailSearch");
			if (! StringUtils.isBlank(userEmail)) {
				usersQuery.setUserEmail(userEmail);
				model.addAttribute("userEmail", userEmail);
			}
			String orgId = request.getParameter("orgNameSearch");
			if (! StringUtils.isBlank(orgId) && !orgId.equals("-1")) {
				usersQuery.setOrgId(new Long(orgId));
				model.addAttribute("orgId", orgId);
			}
		}
		Integer totalCount = usersDao.getUsersCountByQuery(usersQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, usersQuery.getPageSize(), 
				usersQuery.getPage(), usersQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		// 获得当前登录用户的信息
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		
		String usernameString = eUser.getUserName();
		usersQuery.setUserName(usernameString);
		//通过登录名获取到详细信息
		Users usersinfo = this.getAllUsersInfoByQuery(usersQuery);
		List<Users> usersList = this.getUsersInfoByQuery(usersQuery);
		eUser.setPassword(usersinfo.getPassword());
		model.addAttribute("user",eUser);
		//获取所有区域的信息，用于用户管理页面中的区域检索
		List<Organization> orgList = systemConstant.getOrganizationList();
		model.addAttribute("orgList", orgList);
		//获取角色信息
		List<Roles> rolesList = systemConstant.getRolesList();
		model.addAttribute("rolesList", rolesList);
		
		List<ExtendUsers> eUsersList = new ArrayList<ExtendUsers>();
		if ( usersList != null && usersList.size() > 0) {
			for (Users users : usersList) {
				ExtendUsers eUsers = new ExtendUsers();
				try {
					BeanUtils.copyProperties(eUsers, users);
				} catch(Exception e){
					LOGGER.error("根据查询参数获取用户信息 用于用户管理页面 对象属性值拷贝过程中出现异常：{}", e);
				}
				if (eUsers.getIsLock().compareTo(EnumIsLock.IS_LOCK_YES.getStatus()) == 0) {
					eUsers.setLockStr("被锁定");
				} else if (eUsers.getIsLock().compareTo(EnumIsLock.IS_LOCK_NO.getStatus()) == 0) {
					eUsers.setLockStr("正常");
				}
				for (Organization org : orgList) {
					if (org.getId().compareTo(eUsers.getOrgId()) == 0) {
						eUsers.setOrgName(org.getOrgName());
						break;
					}
				}
				
				//获取用户角色名称
				eUsers.setUserRoleList(userRoleService.getRoleIdListByUserId(users.getId()));
				if (eUsers.getUserRoleList() != null && eUsers.getUserRoleList().size() > 0) {
					String roleName = "";
					for (UserRole userRole : eUsers.getUserRoleList()) {
						for (Roles roles : rolesList) {
							if(userRole.getRoleId().compareTo(roles.getId()) == 0) {
								roleName += ", " + roles.getRoleName();
								break;
							}
						}
					}
					if (roleName.length() > 0) 
						roleName = roleName.substring(2);
						eUsers.setRoleName(roleName);
				}
				eUsersList.add(eUsers);
			}
			
		}
		model.addAttribute("usersList", eUsersList);
	}
	
	@Override
	public void getUserInfo(ActivityModelMap modelMap, HttpServletRequest request) {
		LOGGER.debug("Service层：获取用户的记录信息");
		//如果接收到用户id，则返回用户信息
		if(request.getParameter("id")!=null){
			Long userId=Long.parseLong(request.getParameter("id"));
			ExtendUsers eUser=getExtendUsersById(userId);
			
			//获取角色信息
			List<Roles> rolesList = systemConstant.getRolesList();
			eUser.setUserRoleList(userRoleService.getRoleIdListByUserId(userId));
			if (eUser.getUserRoleList() != null && eUser.getUserRoleList().size() > 0) {
				String roleName = "";
				for (UserRole userRole : eUser.getUserRoleList()) {
					for (Roles roles : rolesList) {
						if(userRole.getRoleId().compareTo(roles.getId()) == 0) {
							roleName += roles.getRoleName()+" , " ;
							break;
						}
					}
				}
				if (roleName.length() > 0) 
					eUser.setRoleName(roleName.substring(0,roleName.length()-2));
			}
			modelMap.put("userInfo",eUser );
		}
	}
	
	@Override
	public List<Users> getUsersInfoByQuery(UsersQuery usersQuery) {
		LOGGER.debug("Service层：根据检索条件获取用户的记录信息");
		return usersDao.getUsersInfoByQuery(usersQuery);
	}
	
	@Override
	public Users getAllUsersInfoByQuery(UsersQuery usersQuery) {
		LOGGER.debug("Service层：根据登录用户名字获取用户的记录信息");
		return usersDao.getAllInfoByQuery(usersQuery);
	}
	
	/*@Override
	public Users getUsersInfoById(Long id) {
		LOGGER.debug("Service层：根据检索条件获取用户的记录信息");
		return usersDao.getUsersInfoByQuery(usersQuery);
	}*/
	
	@Override
	public ExtendUsers getExtendUsersById(Long userId) {
		LOGGER.debug("Service层：根据用户id获取用户扩展信息");
		Users users = this.getUserInfoByUserId(userId);
		if (users == null || users.getId() == null) {
			LOGGER.debug("根据用户id获取用户扩展信息，没有查取到用户信息：userId {}", userId);
			return null;
		}
		ExtendUsers eUsers = new ExtendUsers();
		try {
			BeanUtils.copyProperties(eUsers, users);
			if (eUsers.getUpdateUserId() != null && eUsers.getUpdateUserId().compareTo(new Long(0)) == 0) {
				eUsers.setUpdateUserId(null);
			}
		} catch(Exception e){
			LOGGER.error("根据用户id获取用户扩展信息 对象属性值拷贝过程中出现异常：{}", e);
		}
		//获取创建和修改用户信息的用户名称
		if (eUsers.getCreateUserId() != null || eUsers.getUpdateUserId() != null) {
			List<Long> userIdList = new ArrayList<Long>();
			if (eUsers.getCreateUserId() != null) {
				userIdList.add(eUsers.getCreateUserId());
			}
			if (eUsers.getUpdateUserId() != null) {
				userIdList.add(eUsers.getUpdateUserId());
			}
			List<Users> usersList = this.getUserInfoByUserIdList(userIdList);
			if (usersList != null && usersList.size() > 0) {
				for (Users user : usersList) {
					if (eUsers.getCreateUserId() != null && user.getId().compareTo(eUsers.getCreateUserId()) == 0) {
						eUsers.setCreateUserName(user.getUserName());
					} 
					if (eUsers.getUpdateUserId() != null && user.getId().compareTo(eUsers.getUpdateUserId()) == 0) {
						eUsers.setUpdateUserName(user.getUserName());
					}
				}
			}
		}
		
		for (Organization org : systemConstant.getOrganizationList()) {
			if (org.getId().compareTo(eUsers.getOrgId()) == 0) {
				eUsers.setOrgName(org.getOrgName());
				break;
			}
		}
		if (eUsers.getIsLock().compareTo(EnumIsLock.IS_LOCK_YES.getStatus()) == 0) {
			eUsers.setLockStr("被锁定");
		} else if (eUsers.getIsLock().compareTo(EnumIsLock.IS_LOCK_NO.getStatus()) == 0) {
			eUsers.setLockStr("正常");
		}
		eUsers.setOrgName(organizationService.getOrgNameByOrgId(eUsers.getOrgId()));
		return eUsers;
	}
	
	@Override
	public boolean addNewUser(Users user, ExtendUsers eUser, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：新增用户信息");
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		user.trim();
		if (this.checkNewUser(user, modelMap)) {
			//通过校验，开始进行更新
			user.setCreateUserId(eUser.getId());
			user.setPassword(ActivityStringUtils.createMd5Str(user.getPassword(), null));
			Integer resultNum = usersDao.addNewUser(user);
			Users newusers= usersDao.getUserInfoByUserName(user.getUserName());
			if(newusers!=null&&newusers.getId()!=null)
			{
				user.setId(newusers.getId());
			}
			//保存用户角色
			if(user.getRoleId()!=-1){
				UserRole userrole= new UserRole();
				userrole.setRoleId(user.getRoleId().longValue());
				userrole.setUserId(user.getId());
				userrole.setCreateUserId(eUser.getId());
				userrole.setUpdateUserId(eUser.getId());
				userrole.setUpdateTime(DateTimeUtils.getDateTime(DateTimeUtils.YMDHMS));
				userrole.setCreateTime(DateTimeUtils.getDateTime(DateTimeUtils.YMDHMS));
				userRoleService.addNewUserRole(userrole);
			}
			
			if (resultNum.compareTo(new Integer(1)) == 0) {
				result = true;
			} else {
				modelMap.put("status", "failure");
				modelMap.put("data", "保存新的用户信息的操作出现异常！");
				result=false;
			}
		}
		return result;
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
	@Override
	public boolean addUser(Users users, ModelMap model) {
		LOGGER.debug("Service层：新增用户信息");
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
			users.trim();
			users.setPassword(ActivityStringUtils.createMd5Str(users.getPassword(), null));
			
			users.setStatus(1);//注册用户默认状态
			
			Integer resultNum = usersDao.addNewUser(users);
			Users newusers= usersDao.getUserInfoByUserName(users.getUserName());
			if(newusers!=null&&newusers.getId()!=null)
			{
				users.setId(newusers.getId());
			}
			//保存用户角色
			if(users.getRoleId()!=-1){
				UserRole userrole= new UserRole();
				userrole.setRoleId(users.getRoleId().longValue());
				userrole.setUserId(users.getId());
				userrole.setCreateUserId(null);
				userrole.setUpdateUserId(null);
				userrole.setUpdateTime(DateTimeUtils.getDateTime(DateTimeUtils.YMDHMS));
				userrole.setCreateTime(DateTimeUtils.getDateTime(DateTimeUtils.YMDHMS));
				userRoleService.addNewUserRole(userrole);
			}
			if (resultNum.compareTo(new Integer(1)) == 0) {
				result = true;
			} else {
				model.put("status", "failure");
				model.put("data", "保存新的用户信息的操作出现异常！");
				result=false;
			}
		return result;
		
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @param request
	 * @return Map<String,Object>
	 * @throws
	 * @author wlg
	 * @date 2016-07-18 
	 * @version V1.0
	 */
	@Override
	public boolean updateUserInfo (Users user, ExtendUsers eUser, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：根据用户id更新用户信息");
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		user.trim();
		if (this.checkUpdateUser(user, modelMap)) {
			//通过校验，开始进行更新
			user.setUpdateUserId(eUser.getId());
			Integer resultNum = usersDao.updateUserInfoById(user);
			//保存用户角色
			if(user.getRoleId()!=-1){
				UserRole userrole= new UserRole();
				userrole.setRoleId(user.getRoleId().longValue());
				userrole.setUserId(user.getId());
				userrole.setUpdateUserId(eUser.getId());
				userrole.setUpdateTime(DateTimeUtils.getDateTime(DateTimeUtils.YMDHMS));
				Integer i=userRoleDao.updateUserRole(userrole);
				if(i<=0){
					userrole.setCreateTime(DateTimeUtils.getDateTime(DateTimeUtils.YMDHMS));
					userrole.setCreateUserId(eUser.getId());
					userRoleDao.addNewUserRole(userrole);
				}
			}
			
			if (resultNum.compareTo(new Integer(1)) == 0) {
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	@Override
	public boolean updateUserInfoById (Users user, ExtendUsers eUser, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：根据用户id更新用户信息");
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		user.trim();
		user.setId(eUser.getId());
		// 如果密码做了修改，要对密码进行加密
		if(!eUser.getPassword().equals(user.getPassword())){
			user.setPassword(ActivityStringUtils.createMd5Str(user.getPassword(),
					null));
		}
		Integer resultNum = usersDao.updateUserInfoById(user);
			
		// 判断是否查到数据
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}

		return result;
	}
	
	@Override
	public boolean removeUser(Long userId, ExtendUsers eUser, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：根据用户id删除用户信息 物理删除");
		boolean result = false;
		if (checkRemoveUser(userId, modelMap)) {
			// 通过了校验
			Integer resultNum = usersDao.deleteUserById(userId);
			if (resultNum.compareTo(new Integer(1)) == 0) {
				result = true;
			} else {
				modelMap.put("status", "failure");
			}
		}
		return result;
	}
	
	/**
	 * 校验所给的用户是否可以被删除
	 * @param userId
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午02:47:12
	 * @version V1.0
	 */
	private boolean checkRemoveUser(Long userId, ActivityModelMap modelMap){
		// 校验角色是否为可编辑
		if (!checkUserIsEditable(userId)) {
			LOGGER.error("该用户是系统默认用户，不可删除: userId={}", userId);
			modelMap.put("status", "failure");
			modelMap.put("data", "该用户是系统默认用户，不可删除！");
			modelMap.put("fresh", "false");
			return false;
		}
		return true;
	}
	/**
	 * 修改用户密码信息
	 * @param user
	 * @param request
	 * @return Map<String,Object>
	 * @throws
	 * @author wlg
	 * @date 2016-07-18 
	 * @version V1.0
	 */
	@Override
	public boolean updateUserPass(Users user, ExtendUsers eUser, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：根据用户id更新用户密码信息");
		boolean result = false;
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		if (StringUtils.isBlank(user.getPassword())) {
			LOGGER.info("请输入密码");
			errorInfoList.add(new CheckErrorDto("users_change_user_pass", "请输入密码"));
		} else {
			Integer length = new Integer(user.getPassword().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MIN_LENGTH")) < 0) {
				errorInfoList.add(new CheckErrorDto("users_change_user_pass", "密码长度不能小于" 
						+ MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
			} else if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("users_change_user_pass", "密码长度不能大于" + 
						MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
			}
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
	
		//通过校验，开始进行更新
		user.setUpdateUserId(eUser.getId());
		user.setPassword(ActivityStringUtils.createMd5Str(user.getPassword(), null));
		Integer resultNum = usersDao.updateUserInfoById(user);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}
	
	/**
	 * 校验改后用户的信息
	 * @param user
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午05:22:14
	 * @version V1.0
	 */
	private boolean checkUpdateUser(Users user, ActivityModelMap modelMap){
		if (!checkUserIsEditable(user.getId())) {
			LOGGER.error("该用户是系统默认用户，不可编辑: userId={}", user.getId());
			modelMap.put("status", "failure");
			modelMap.put("data", "该用户是系统默认用户，不可编辑！");
			modelMap.put("fresh", "false");
			return false;
		}
		
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		this.checkUserBaseInfo(user, errorInfoList);
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		LOGGER.debug("Service层：校验更新后用户的名称是否已存在！");
		UsersQuery userQuery = new UsersQuery();
		userQuery.setUserName(user.getUserName());
		userQuery.setId(user.getId());
		
		Integer resultNum = usersDao.getUserCountByName(userQuery);
		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
			LOGGER.info("用户名称已经存在");
			errorInfoList.add(new CheckErrorDto("users_change_user_name", "用户名称已经存在"));
		}
		
//		UsersQuery userEmailQuery = new UsersQuery();
//		userEmailQuery.setUserEmail(user.getUserEmail());
//		userEmailQuery.setId(user.getId());
//		
//		resultNum = usersDao.getUserCountByEmail(userEmailQuery);
//		if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
//			LOGGER.info("用户电子邮箱已经存在");
//			errorInfoList.add(new CheckErrorDto("users_change_user_email", "用户电子邮箱已经存在"));
//		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		return true;
	}
	
	/**
	 * 校验新用户的信息
	 * @param user
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午02:21:30
	 * @version V1.0
	 */
	private boolean checkNewUser(Users user, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：校验用户名称的长度，备注的长度，校验新用户的名称是否已存在！");
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		
		//this.checkUserBaseInfo(user, errorInfoList);
		
		//校验登录名是否唯一
		if (this.checkUserNameIsExist(user.getUserName())) {
			LOGGER.info("用户登录名已经存在");
			errorInfoList.add(new CheckErrorDto("user_name", "用户登录名已经存在"));
		}
		
		//校验改后的电子邮箱是否唯一
		if (this.checkUserEmailIsExist(user.getUserEmail())) {
			LOGGER.info("电子邮箱已经存在");
			errorInfoList.add(new CheckErrorDto("user_email", "电子邮箱已经存在"));
		}

		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		return true;
	}
	
	@Override
	public Users getUserInfoByUserId(Long userId) {
		if (userId == null) {
			LOGGER.error("Service层：根据用户id获取用户信息：用户id参数为空 {}", userId);
			return null;
		}
		LOGGER.debug("Service层：根据用户id获取用户信息");
		return usersDao.getUserInfoByUserId(userId);
	}
	
	@Override
	public List<Users> getUserInfoByUserIdList(List<Long> userIdList) {
		if (userIdList == null) {
			LOGGER.error("Service层：根据用户id集合获取用户信息：用户id集合参数为空 {}", userIdList);
			return null;
		}
		LOGGER.debug("Service层：根据用户id集合获取用户信息");
		return usersDao.getUserInfoByUserIdList(userIdList);
	}
	
	@Override
	public Users getUserInfoByUserName (String userName) {
		if (StringUtils.isBlank(userName)) {
			LOGGER.error("Service层：根据用户登录名查取用户信息：用户名称参数为空 {}", userName);
			return null;
		}
		LOGGER.debug("Service层：根据用户登录名查取用户信息");
		return usersDao.getUserInfoByUserName(userName);
	}
	
	@Override
	public Users getUserInfoByUserEmail (String userEmail) {
		if (StringUtils.isBlank(userEmail)) {
			LOGGER.error("Service层：根据用户电子邮箱地址查取用户信息：电子邮箱参数为空 {}", userEmail);
			return null;
		}
		LOGGER.debug("Service层：根据用户电子邮箱地址查取用户信息");
		return usersDao.getUserInfoByUserEmail(userEmail);
	}
	
	@Override
	public Integer getUsersCountByOrgId(Long orgId) {
		if (orgId == null) {
			LOGGER.error("Service层：根据用户所属的机构id查询用户的数量：机构id参数为空 {}", orgId);
			return null;
		}
		LOGGER.debug("Service层：根据用户所属的机构id查询用户的数量");
		return usersDao.getUsersCountByOrgId(orgId);
	}
	
	@Override
	public boolean updateCurrentUserBaseInfo(ExtendUsers eUser, Users newUser, ActivityModelMap modelMap) {
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		newUser.trim();
		if (this.checkUserChangeInfo(eUser, newUser, modelMap)) {
			if (modelMap.get("status") != null && modelMap.get("status").equals("same")) {
				LOGGER.debug("改前与改后的用户基本信息相同，无需更新数据库，直接返回更新成功");
				result = true;
			} else {
				//通过校验，开始进行更新
				newUser.setId(eUser.getId());
				newUser.setUpdateUserId(eUser.getId());
				Integer resultNum = usersDao.updateUserInfoById(newUser);
				if (resultNum.compareTo(new Integer(1)) == 0) {
					eUser.setUserName(newUser.getUserName());
					eUser.setUserEmail(newUser.getUserEmail());
					eUser.setTrueName(newUser.getTrueName());
					eUser.setUserPhone(newUser.getUserPhone());
					eUser.setUserMobile(newUser.getUserMobile());
					eUser.setNote(newUser.getNote());
					result = true;
				} else {
					modelMap.put("status", "failure");
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean updateCurrentUserPassInfo(ExtendUsers eUser, UserPassword password, ActivityModelMap modelMap) {
		boolean result = false;
		//过滤掉用户信息中各个属性值的前后空格
		password.trim();
		if (this.checkUserChangePassInfo(eUser, password, modelMap)) {
			if (modelMap.get("status") != null && modelMap.get("status").equals("same")) {
				LOGGER.debug("改前与改后的密码信息相同，无需更新数据库，直接返回更新成功");
				result = true;
			} else {
				//通过校验，开始进行更新
				Users user = new Users();
				user.setId(eUser.getId());
				user.setPassword(ActivityStringUtils.createMd5Str(password.getNewPassword(), null));
				user.setUpdateUserId(user.getId());
				Integer resultNum = usersDao.updateUserPasswordById(user);
				if (resultNum.compareTo(new Integer(1)) == 0) {
					result = true;
				} else {
					modelMap.put("status", "failure");
				}
			}
		}
		return result;
	}
	
	@Override
	public boolean getUserRoleInfoByUserId(Long userId, ActivityModelMap modelMap) {
		LOGGER.debug("根据指定的用户id 获取用户角色信息: userId={}", userId);
		//获取用户的角色信息
		List<UserRole> userRoleId = userRoleService.getRoleIdListByUserId(userId);
		modelMap.put("status", "success");
		if (userRoleId != null && userRoleId.size() > 0) {
			modelMap.put("data", userRoleId.get(0).getRoleId());
		} else {
			modelMap.put("data", "-1");
		}
		return true;
	}
	
	@Override
	@Transactional(value = "sinosoftWriteTxManager", isolation = Isolation.READ_COMMITTED, 
			propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public boolean updateUserRole(Long userId, Long roleId, ExtendUsers eUser, ActivityModelMap modelMap) {
		LOGGER.debug("更新用户的角色信息: userId={}, roleId={}", userId, roleId);
		boolean result = false;
		if (this.checkUpdateUserRole(userId, roleId, modelMap)) {
			//通过了校验
			//首先需要删除用户角色管理表中 用户与角色的关联记录
			//接着将新的用户角色关联信息插入到表中
			userRoleService.deleteUserRoleByUserId(userId);
			if (roleId.compareTo(new Long(-1)) != 0) {
				UserRole userRole = new UserRole();
				userRole.setRoleId(roleId);
				userRole.setUserId(userId);
				userRole.setCreateUserId(eUser.getId());
				Integer resultNum = userRoleService.addNewUserRole(userRole);
				if (resultNum != null && resultNum.compareTo(new Integer(0)) > 0) {
					result = true;
					LOGGER.debug("更新用户的角色信息成功！");
				} else {
					modelMap.put("status", "failure");
				}
			} else {
				result = true;
			}
			
		}
		return result;
	}
	
	private boolean checkUpdateUserRole(Long userId, Long roleId, ActivityModelMap modelMap) {
		//校验当前用户是否可以被编辑
		if (! this.checkUserIsEditable(userId)) {
			LOGGER.error("该用户是系统默认用户，不可改变角色: roleId={}", userId);
			modelMap.put("status", "failure");
			modelMap.put("data", "该用户是系统默认用户，不可改变角色！");
			modelMap.put("fresh", "false");
			return false;
		}
		
		if (roleId.compareTo(new Long(-1)) != 0) {
			boolean isHaveRole = false;
			List<Roles> rolesList = systemConstant.getRolesList();
			if (rolesList != null && rolesList.size() > 0) {
				for (Roles role : rolesList) {
					if(role.getId().compareTo(roleId) == 0){
						isHaveRole = true;
						break;
					}
				}
			}
			
			if (!isHaveRole) {
				LOGGER.error("更新用户角色失败，所传角色id参数有误，系统中无此角色id: roleId={}", roleId);
				modelMap.put("status", "failure");
				modelMap.put("data", "更新用户角色失败，参数有误！");
				return false;
			}
		}
		
		return true;
	}
	
	
	/**
	 * 校验用户自己所修改的密码信息
	 * 校验新密码是否与确认密码相同；校验原密码是否与信息密码相同；校验原密码是否与数据库中密码相同；
	 * @param UsersServiceImpl
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-13 下午08:56:20
	 * @version V1.0
	 */
	private boolean checkUserChangePassInfo(ExtendUsers eUser, UserPassword password, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：校验用户自己所修改的密码信息：校验新密码是否与确认密码相同；校验原密码是否与信息密码相同；校验原密码是否与数据库中密码相同");
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		Integer length = null;
		
		if (StringUtils.isBlank(password.getPassword())) {
			LOGGER.info("请输入原密码");
			errorInfoList.add(new CheckErrorDto("old_password", "请输入原密码"));
		} else {
			length = new Integer(password.getPassword().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MIN_LENGTH")) < 0) {
				errorInfoList.add(new CheckErrorDto("old_password", "密码长度不能小于" 
						+ MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
			} else if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("old_password", "密码长度不能大于" + 
						MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
			}
		}
		
		if (StringUtils.isBlank(password.getNewPassword())) {
			LOGGER.info("请输入新密码");
			errorInfoList.add(new CheckErrorDto("new_password", "请输入新密码"));
		} else {
			length = new Integer(password.getPassword().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MIN_LENGTH")) < 0) {
				errorInfoList.add(new CheckErrorDto("new_password", "密码长度不能小于" 
						+ MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
			} else if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("new_password", "密码长度不能大于" + 
						MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
			}
		}
		
		if (StringUtils.isBlank(password.getConfirmNewPassword())) {
			LOGGER.info("请输入确认密码");
			errorInfoList.add(new CheckErrorDto("confirm_new_password", "请输入确认密码"));
		} else {
			length = new Integer(password.getPassword().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MIN_LENGTH")) < 0) {
				errorInfoList.add(new CheckErrorDto("confirm_new_password", "密码长度不能小于" 
						+ MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
			} else if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("confirm_new_password", "密码长度不能大于" + 
						MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
			}
		}
		
		if (! password.getNewPassword().equals(password.getConfirmNewPassword())) {
			LOGGER.info("两次密码不一致，请确认");
			errorInfoList.add(new CheckErrorDto("confirm_new_password", "两次密码不一致,请确认"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		String encryptOldPass = ActivityStringUtils.createMd5Str(password.getPassword(), null);
		if (! this.checkPasswordIsCurrect(eUser.getId(), encryptOldPass)) {
			LOGGER.info("原密码错误，请重新输入");
			errorInfoList.add(new CheckErrorDto("old_password", "原密码错误,请重新输入"));
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		if (password.getPassword().equals(password.getNewPassword())) {
			modelMap.put("status", "same");
		}
		
		return true;
	}
	
	/**
	 * 校验用户自己所修改的用户信息
	 * 校验用户名、电子邮箱、真实姓名信息是否存在，其长度，校验改后登录名是否唯一，校验改后的电子邮箱是否唯一。如若登录名与电子邮箱没有发生变动，该校验自动认为成功
	 * @param user
	 * @param newUser
	 * @param modelMap
	 * @return String
	 * @throws
	 * @author mrajian
	 * @date 2013-10-11 上午08:51:01
	 * @version V1.0
	 */
	private boolean checkUserChangeInfo(ExtendUsers eUser, Users newUser, ActivityModelMap modelMap) {
		LOGGER.debug("Service层：校验用户自己所修改的用户信息：校验用户名、电子邮箱、真实姓名等信息是否存在，其长度，校验改后登录名是否唯一，校验改后的电子邮箱是否唯一。" +
				"如若登录名与电子邮箱没有发生变动，该校验自动认为成功！");
		List<CheckErrorDto> errorInfoList = new ArrayList<CheckErrorDto>();
		Integer length = null;
		if (StringUtils.isBlank(newUser.getUserName())) {
			LOGGER.info("请输入用户名");
			errorInfoList.add(new CheckErrorDto("change_user_name", "请输入用户名"));
		} else {
			length = new Integer(newUser.getUserName().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("USER_NAME_MIN_LENGTH")) < 0) {
				errorInfoList.add(new CheckErrorDto("change_user_name", "用户名长度不能小于" 
						+ MappingInputLengthConfig.getValue("USER_NAME_MIN_LENGTH") + "位"));
			} else if (length.compareTo(MappingInputLengthConfig.getValue("USER_NAME_MAX_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("change_user_name", "用户名长度不能大于" + 
						MappingInputLengthConfig.getValue("USER_NAME_MAX_LENGTH") + "位"));
			}
		}
		
		if (StringUtils.isBlank(newUser.getUserEmail())) {
			LOGGER.info("请输入电子邮箱地址");
			errorInfoList.add(new CheckErrorDto("change_user_email", "请输入电子邮箱地址"));
		} else {
			length = new Integer(newUser.getUserEmail().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("USER_EMAIL_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("change_user_email", "电子邮箱地址长度不能大于" 
						+ MappingInputLengthConfig.getValue("USER_EMAIL_LENGTH") + "位"));
			}
		}
		
		if (StringUtils.isBlank(newUser.getTrueName())) {
			LOGGER.info("请输入真实姓名");
			errorInfoList.add(new CheckErrorDto("change_true_name", "请输入真实姓名"));
		} else {
			length = new Integer(newUser.getTrueName().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("TRUE_NAME_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("change_true_name", "真实姓名长度不能大于" 
						+ MappingInputLengthConfig.getValue("TRUE_NAME_LENGTH") + "位"));
			}
		}
		
		if (!StringUtils.isBlank(newUser.getUserMobile())) {
			length = new Integer(newUser.getUserMobile().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("USER_MOBILE_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("change_user_mobile", "手机号码长度不能大于" 
						+ MappingInputLengthConfig.getValue("USER_MOBILE_LENGTH") + "位"));
			}
		}
		
		if (!StringUtils.isBlank(newUser.getUserPhone())) {
			length = new Integer(newUser.getUserPhone().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("USER_PHONE_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("change_user_phone", "座机号码长度不能大于" 
						+ MappingInputLengthConfig.getValue("USER_PHONE_LENGTH") + "位"));
			}
		}
		
		if (!StringUtils.isBlank(newUser.getNote())) {
			length = new Integer(newUser.getNote().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("NOTE_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto("change_note", "备注长度不能大于" 
						+ MappingInputLengthConfig.getValue("NOTE_LENGTH") + "位"));
			}
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
			
		
		//确认用户登录名已经发生了变化
		if(!(newUser.getUserName().equals(eUser.getUserName()))) {
			//校验改后登录名是否唯一
			if (this.checkUserNameIsExist(newUser.getUserName())) {
				LOGGER.info("用户登录名已经存在");
				errorInfoList.add(new CheckErrorDto("change_user_name", "用户登录名已经存在"));
			}
		}
		
		//确认电子邮箱已经发生了变化
		if(!(newUser.getUserEmail().equals(eUser.getUserEmail()))) {
			//校验改后的电子邮箱是否唯一
			if (this.checkUserEmailIsExist(newUser.getUserEmail())) {
				LOGGER.info("电子邮箱已经存在");
				errorInfoList.add(new CheckErrorDto("change_user_email", "电子邮箱已经存在"));
			}
		}
		
		if (errorInfoList.size() > 0) {
			modelMap.put("status", "error");
			modelMap.put("data", errorInfoList);
			return false;
		}
		
		if (newUser.getUserName().equals(eUser.getUserName()) && newUser.getUserEmail().equals(eUser.getUserEmail()) 
				&& newUser.getTrueName().equals(eUser.getTrueName()) && newUser.getUserMobile().equals(eUser.getUserMobile())
				&& newUser.getUserPhone().equals(eUser.getUserPhone()) && newUser.getNote().equals(eUser.getNote())) {
			modelMap.put("status", "allSame");
		}
		
		return true;
	}
	
	/**
	 * 校验登录名是否唯一
	 * @param newUserName
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-11 上午09:25:30
	 * @version V1.0
	 */
	private boolean checkUserNameIsExist(String newUserName) {
		Users user = this.getUserInfoByUserName(newUserName);
		if(user != null && user.getUserName().equals(newUserName)) {
			return true;
		} else 
			return false;
	}
	
	/**
	 * 校验用户电子邮箱是否唯一
	 * @param UsersServiceImpl
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-11 上午09:30:13
	 * @version V1.0
	 */
	private boolean checkUserEmailIsExist(String newUserEmail) {
		Users user = this.getUserInfoByUserEmail(newUserEmail);
		if(user != null && user.getUserEmail().equals(newUserEmail)) {
			return true;
		} else 
			return false;
	}
	
	/**
	 * 根据用户id，查询该用户的密码是否与给定的字符串相同
	 * @param userId
	 * @param password
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-13 下午09:36:22
	 * @version V1.0
	 */
	private boolean checkPasswordIsCurrect(Long userId, String password) {
		Users user = new Users(userId, password);
		Integer resultNum = usersDao.selectCountByUserInfo(user);
		return resultNum.compareTo(new Integer(1)) == 0;
	}
	
	
	/**
	 * 校验用户的基本信息
	 * @param user
	 * @param errorInfoList
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午02:22:58
	 * @version V1.0
	 */
	private void checkUserBaseInfo(Users user, List<CheckErrorDto> errorInfoList) {
		Integer length = null;
		String prefix = "";
		if (user.getId() != null)
			prefix = "users_change_";
		//用户名
		if (StringUtils.isBlank(user.getUserName())) {
			LOGGER.info("请输入用户名");
			errorInfoList.add(new CheckErrorDto(prefix + "user_name", "请输入用户名"));
		}
//		else {
//			length = new Integer(user.getUserName().length());
//			if (length.compareTo(MappingInputLengthConfig.getValue("USER_NAME_MIN_LENGTH")) < 0) {
//				errorInfoList.add(new CheckErrorDto(prefix + "user_name", "用户名长度不能小于" 
//						+ MappingInputLengthConfig.getValue("USER_NAME_MIN_LENGTH") + "位"));
//			} else if (length.compareTo(MappingInputLengthConfig.getValue("USER_NAME_MAX_LENGTH")) > 0) {
//				errorInfoList.add(new CheckErrorDto(prefix + "user_name", "用户名长度不能大于" + 
//						MappingInputLengthConfig.getValue("USER_NAME_MAX_LENGTH") + "位"));
//			}
//		}
		
		//登录密码
		if (user.getId() == null) {
			if (StringUtils.isBlank(user.getPassword())) {
				LOGGER.info("请输入密码");
				errorInfoList.add(new CheckErrorDto(prefix + "user_pass", "请输入密码"));
			} else {
				length = new Integer(user.getPassword().length());
				if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MIN_LENGTH")) < 0) {
					errorInfoList.add(new CheckErrorDto(prefix + "user_pass", "密码长度不能小于" 
							+ MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
				} else if (length.compareTo(MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH")) > 0) {
					errorInfoList.add(new CheckErrorDto(prefix + "user_pass", "密码长度不能大于" + 
							MappingInputLengthConfig.getValue("PASSWORD_MAX_LENGTH") + "位"));
				}
			}
		}
		
		
		//电子邮箱
//		if (StringUtils.isBlank(user.getUserEmail())) {
//			LOGGER.info("请输入电子邮箱地址");
//			errorInfoList.add(new CheckErrorDto(prefix + "user_email", "请输入电子邮箱地址"));
//		} else {
//			length = new Integer(user.getUserEmail().length());
//			if (length.compareTo(MappingInputLengthConfig.getValue("USER_EMAIL_LENGTH")) > 0) {
//				errorInfoList.add(new CheckErrorDto(prefix + "user_email", "电子邮箱地址长度不能大于" 
//						+ MappingInputLengthConfig.getValue("USER_EMAIL_LENGTH") + "位"));
//			}
//		}
		
		//真实姓名
		if (StringUtils.isBlank(user.getTrueName())) {
			LOGGER.info("请输入真实姓名");
			errorInfoList.add(new CheckErrorDto(prefix + "true_name", "请输入真实姓名"));
		} else {
			length = new Integer(user.getTrueName().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("TRUE_NAME_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "true_name", "真实姓名长度不能大于" 
						+ MappingInputLengthConfig.getValue("TRUE_NAME_LENGTH") + "位"));
			}
		}
		
		//用户职称
//		if (StringUtils.isBlank(user.getTrueName())) {
//			LOGGER.info("请输入用户职称");
//			errorInfoList.add(new CheckErrorDto(prefix + "user_title", "请输入用户职称"));
//		} else {
//			length = new Integer(user.getTrueName().length());
//			if (length.compareTo(MappingInputLengthConfig.getValue("USER_TITLE_LENGTH")) > 0) {
//				errorInfoList.add(new CheckErrorDto(prefix + "user_title", "用户职称长度不能大于" 
//						+ MappingInputLengthConfig.getValue("USER_TITLE_LENGTH") + "位"));
//			}
//		}
		
		//用户状态
		if (user.getIsLock() == null || user.getIsLock().compareTo(new Integer(-1)) == 0) {
			LOGGER.info("请选择用户状态");
			errorInfoList.add(new CheckErrorDto(prefix + "user_lock", "请选择用户状态"));
		} else {
			if (! (user.getIsLock().compareTo(EnumIsLock.IS_LOCK_NO.getStatus()) == 0 
					|| user.getIsLock().compareTo(EnumIsLock.IS_LOCK_YES.getStatus()) == 0)) {
				LOGGER.info("请选择用户状态");
				errorInfoList.add(new CheckErrorDto(prefix + "user_lock", "请选择用户状态"));
			}
		}
		
		//手机号
//		if (!StringUtils.isBlank(user.getUserMobile())) {
//			length = new Integer(user.getUserMobile().length());
//			if (length.compareTo(MappingInputLengthConfig.getValue("USER_MOBILE_LENGTH")) > 0) {
//				errorInfoList.add(new CheckErrorDto(prefix + "user_mobile", "手机号码长度不能大于" 
//						+ MappingInputLengthConfig.getValue("USER_MOBILE_LENGTH") + "位"));
//			}
//		}
		
		//座机号码
//		if (!StringUtils.isBlank(user.getUserPhone())) {
//			length = new Integer(user.getUserPhone().length());
//			if (length.compareTo(MappingInputLengthConfig.getValue("USER_PHONE_LENGTH")) > 0) {
//				errorInfoList.add(new CheckErrorDto(prefix + "user_phone", "座机号码长度不能大于" 
//						+ MappingInputLengthConfig.getValue("USER_PHONE_LENGTH") + "位"));
//			}
//		}
		
		//用户备注信息
		if (!StringUtils.isBlank(user.getNote())) {
			length = new Integer(user.getNote().length());
			if (length.compareTo(MappingInputLengthConfig.getValue("NOTE_LENGTH")) > 0) {
				errorInfoList.add(new CheckErrorDto(prefix + "user_note", "备注长度不能大于" 
						+ MappingInputLengthConfig.getValue("NOTE_LENGTH") + "位"));
			}
		}
	}
	
	/**
	 * 根据用户id获取用户是否可以被编辑
	 * @param userId
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午08:02:20
	 * @version V1.0
	 */
	private boolean checkUserIsEditable(Long userId) { 
		Users user = this.getUserInfoByUserId(userId);
		if (user.getEditable().compareTo(EnumEditable.EDITABLE_YES.getStatus()) == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void  getChooseUser(ModelMap model, String method, HttpServletRequest request) {
		UsersQuery usersQuery = new UsersQuery();
		//以id升序排序
		usersQuery.setSortBy("id");
		usersQuery.setSortType("1");
		if(method.equals("POST")){
			String pageNum = request.getParameter("pageNumInput");
			if (! StringUtils.isBlank(pageNum)) {
				usersQuery.setPage(Integer.parseInt(pageNum));
			}
			String userName = request.getParameter("userName");
			if (! StringUtils.isBlank(userName)) {
				usersQuery.setUserName(userName);
			}
		}
		Integer totalCount = usersDao.getUsersCountByQuery(usersQuery);
		TotalInfo totalInfo = new TotalInfo(totalCount, usersQuery.getPageSize(), usersQuery.getPage(), usersQuery.getStartNum());
		model.addAttribute("totalInfo", totalInfo);
		List<Users> usersList = this.getUsersInfoByQuery(usersQuery);
		
		//获取角色信息
		List<Roles> rolesList = systemConstant.getRolesList();
		List<ExtendUsers> eUsersList = new ArrayList<ExtendUsers>();
		if ( usersList != null && usersList.size() > 0) {
			for (Users users : usersList) {
				ExtendUsers eUsers = new ExtendUsers();
				try {
					BeanUtils.copyProperties(eUsers, users);
					eUsersList.add(eUsers);
				} catch(Exception e){
					LOGGER.error("根据查询参数获取用户信息 用于用户管理页面 对象属性值拷贝过程中出现异常：{}", e);
				}
				if (eUsers.getIsLock().compareTo(EnumIsLock.IS_LOCK_YES.getStatus()) == 0) {
					eUsers.setLockStr("被锁定");
				} else if (eUsers.getIsLock().compareTo(EnumIsLock.IS_LOCK_NO.getStatus()) == 0) {
					eUsers.setLockStr("正常");
				}
				
				List<Organization> orgList = systemConstant.getOrganizationList();
				for (Organization org : orgList) {
					if (org.getId().compareTo(eUsers.getOrgId()) == 0) {
						eUsers.setOrgName(org.getOrgName());
						break;
					}
				}
				
				//获取用户角色名称
				eUsers.setUserRoleList(userRoleService.getRoleIdListByUserId(users.getId()));
				if (eUsers.getUserRoleList() != null && eUsers.getUserRoleList().size() > 0) {
					String roleName = "";
					for (UserRole userRole : eUsers.getUserRoleList()) {
						for (Roles roles : rolesList) {
							if(userRole.getRoleId().compareTo(roles.getId()) == 0) {
								roleName += ", " + roles.getRoleName();
								break;
							}
						}
					}
					if (roleName.length() > 0) 
						roleName = roleName.substring(2);
					eUsers.setRoleName(roleName);
				}
			}
		}
		model.addAttribute("usersList", eUsersList);
	}


	@Override
	public com.sinosoft.security.po.extend.ExtendUsers getUserInfoById(Integer id) throws Exception {
		Users users = usersDao.getUserInfoByUserId(new Long(id));
		if(users==null){
			throw (new Exception("根据用户id未找到用户信息！"));
		}
		ExtendUsers eUsers = new ExtendUsers();
		try {
			BeanUtils.copyProperties(eUsers, users);
		} catch(Exception e){
			LOGGER.error("根据用户id获取用户扩展信息 对象属性值拷贝过程中出现异常：{}", e);
		}
		//获取其他信息
		
		return eUsers;
	}

	@Override
	public com.sinosoft.security.po.extend.ExtendUsers getUserInfoByNname(String name)  throws Exception{
		Users users = usersDao.getUserInfoByUserName(name);
		if(users==null){
			throw (new Exception("根据用户id未找到用户信息！"));
		}
		ExtendUsers eUsers = new ExtendUsers();
		try {
			BeanUtils.copyProperties(eUsers, users);
		} catch(Exception e){
			LOGGER.error("根据用户id获取用户扩展信息 对象属性值拷贝过程中出现异常：{}", e);
		}
		//获取其他信息
		
		return eUsers;
	}

    /**
     * 处理激活
     * @throws ParseException
     */
	public void processActivate(String userEmail) throws Exception{
		Users user=usersDao.getUserInfoByUserEmail(userEmail);
		if(user!=null){
			if(user.getStatus()==1){
						//激活成功
						user.setStatus(0);
						SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						user.setActivatetime(df.format(new Date()));
						usersDao.updateUserInfoById(user);
					} 
			} 
		}

	//----------用户分类统计开始-----------
	//----------用户分类统计开始-----------
		public void getUserCount(HttpServletRequest request, ActivityModelMap model) {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			String toadyTime = dateFormat.format(now);
			Users users = new Users();
			users.setRegisterTime(toadyTime);
			int count  = usersDao.selectUsersCountByTime(users);
			model.put("usersCount", count);
			LOGGER.debug("当前时间：根据查询参数获取用户信息 用于用户管理页面",toadyTime);
			model.put("usersCountTm", toadyTime);
		}

		//按天查询数量
		public void getUserCountByDay(HttpServletRequest request,
			ActivityModelMap model,UsersQuery usersQuery) {
			int counts = usersDao.selectUserCountByDay(usersQuery);//获取总记录数
			model.put("counts", counts);//前台js统一counts
			List<UsersQuery> userlist_day = usersDao.selecByDayGroup(usersQuery);
			//处理数据，返回封装好的集合数据
			List<ChartModel> chartList = getChartList(userlist_day);
			model.put("chartList", chartList);
		}

		//按月查询数量
		public void getUserCountByMonth(HttpServletRequest request,
			ActivityModelMap model,UsersQuery usersQuery) {
			int counts = usersDao.selectUserCountByMonth(usersQuery);//获取总记录数
			model.put("counts", counts);//前台js统一counts
			List<UsersQuery> userlist_month = usersDao.selecByMonthGroup(usersQuery);
			//处理数据，返回封装好的集合数据
			List<ChartModel> chartList = getChartList(userlist_month);
			model.put("chartList", chartList);
		}
		//按年查询数量
		public void getUserCountByYear(HttpServletRequest request,
			ActivityModelMap model,UsersQuery usersQuery) {
			int counts = usersDao.selectUserCountByYear(usersQuery);//获取总记录数
			model.put("counts", counts);//前台js统一counts
			List<UsersQuery> userlist_year = usersDao.selecByYearGroup(usersQuery);
			//处理数据，返回封装好的集合数据
			List<ChartModel> chartList = getChartList(userlist_year);
			model.put("chartList", chartList);
		}
		//----------用户分类统计结束----------
		
		/*
		 * 该方法的作用是为了处理查询到的数据，封装成前台js所需的list形式，方便取值。
		 */
		public List<ChartModel> getChartList(List<UsersQuery>  usersQueryList){
			List<ChartModel> chartList = new ArrayList<ChartModel>();
			for(int i=0;i<usersQueryList.size();i++){
				ChartModel chartModel = new ChartModel();//封装好的实体名称
				String name = usersQueryList.get(i).getRegisterTime();
				String value = usersQueryList.get(i).getAmount();
				chartModel.setX(name);//给X轴赋值
				chartModel.setY(value);//给Y轴赋值
				chartList.add(chartModel);//存放到集合里边
			}
			return chartList;
		}
		
	//----------用户分类统计结束----------

	@Override
	public boolean updateUserLock(Users user, ExtendUsers eUser,
			ActivityModelMap modelMap) {
		boolean result=false;
		Integer resultNum = usersDao.updateUserInfoById(user);
		if (resultNum.compareTo(new Integer(1)) == 0) {
			result = true;
		} else {
			modelMap.put("status", "failure");
		}
		return result;
	}

	@Override
	public Integer getCountByusers(UsersQuery usersQuery) {
		return usersDao.getUserCountByEmail(usersQuery);
	}

	@Override
	public void updatePassword(Users users) {
		usersDao.updateUserPasswordById(users);
		
	}

	@Override
	public Users selectMessageByName(HttpServletRequest request,
			ActivityModelMap modelMap, String userName) {
		return usersDao.getuserTimebyname(userName);
	}

	@Override
	public SysUserset getUserset() {
		return usersetDao.getUserset();
	}

	@Override
	public void getUsersInfo(ModelMap model, String method,
			HttpServletRequest request) {
		LOGGER.debug("Service层：获取用户的记录信息");
		//如果接收到用户id，则返回用户信息
		
		//会员开通天数
		int date = 0;
		//会员显示颜色
		String color = "black";
		
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
			//根据用户ID获取会员信息
		Long userId = eUser.getId();
		Map map = new HashMap<String, String>();
		map = userRoleDao.selectVipInfoByUserId(userId);
		String dredgeTime = "";
		String dredgeType = "";
		if(map!=null){
			dredgeTime = String.valueOf(map.get("DREDGE_TIME"));//开通时间
			dredgeType = String.valueOf(map.get("DREDGE_TYPE"));//开通方式（月、季度、年）
		}
		if(dredgeType.equals("null")||dredgeType.equals("")){
			 color = "#d4cece";
		}else{
			if(dredgeType.equals("1")){
				 date = 30;
			}else if(dredgeType.equals("2")){
				 date = 90;
			}else if(dredgeType.equals("3")){
				 date  = 365;
			}else{
				 color = "#d4cece";
			}
		}
		 DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 if(!dredgeTime.equals("null")&&!dredgeTime.equals("")){
			 Date dateNow = new Date();
			 try {
					Date dredgeTime2 = sdf.parse(dredgeTime);
					Long day = (dateNow.getTime()-dredgeTime2.getTime())/(1000*60*60);
					//将long转换为int
					int day2 = day.intValue();
					if(day2<date){
						 color = "black";
					}else{
						color = "#d4cece";
					}
					
				} catch (ParseException e) {
					e.printStackTrace();
				}
		 }
		model.addAttribute("color", color);
			//获取角色信息
			List<Roles> rolesList = systemConstant.getRolesList();
			eUser.setUserRoleList(userRoleService.getRoleIdListByUserId(eUser.getId()));
			if (eUser.getUserRoleList() != null && eUser.getUserRoleList().size() > 0) {
				String roleName = "";
				for (UserRole userRole : eUser.getUserRoleList()) {
					for (Roles roles : rolesList) {
						if(userRole.getRoleId().compareTo(roles.getId()) == 0) {
							roleName += roles.getRoleName()+" , " ;
							break;
						}
					}

				}
				if (roleName.length() > 0) 
					eUser.setRoleName(roleName.substring(0,roleName.length()-3));
			}
			model.addAttribute("user",eUser );
			//查询已下载完成的订单数（景数）
			PssOrderInfoQuery orderInfoQuery=new PssOrderInfoQuery();
			orderInfoQuery.setUsername(eUser.getUserName());
			Integer cont=orderInfoDao.getIsdownCount(orderInfoQuery);
			model.addAttribute("cont", cont);
		}

	@Override
	public void getDredgeVIP(ModelMap model, String method,
			HttpServletRequest request) {
		String roleId = request.getParameter("roleName");
		Map<String, String> map = new HashMap<String, String>();
		map = userRoleDao.getDerdgeVIPinfoByRoleId(roleId);
		String onemonth = String.valueOf(map.get("ONEMONTH"));
		String treemonths = String.valueOf(map.get("TREEMONTHS"));
		String year = String.valueOf(map.get("YEAR"));
		
		model.addAttribute("onemonth", onemonth);
		model.addAttribute("treemonths", treemonths);
		model.addAttribute("year", year);
	}
		
	
	@Override
	public void getDredgeVIP(ActivityModelMap model, String method,
			HttpServletRequest request) {
		String roleId = request.getParameter("roleName");
		Map<String, String> map = new HashMap<String, String>();
		map = userRoleDao.getDerdgeVIPinfoByRoleId(roleId);
		String onemonth = String.valueOf(map.get("ONEMONTH"));
		String treemonths = String.valueOf(map.get("TREEMONTHS"));
		String year = String.valueOf(map.get("YEAR"));
		
		model.put("onemonth", onemonth);
		model.put("treemonths", treemonths);
		model.put("year", year);
	}

	@Override
	public void getInfoOfVIP(ModelMap model, String method,
			HttpServletRequest request) {
	
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		//根据用户ID获取会员信息
		Long userId = eUser.getId();
		Map<String, String> map = new HashMap<String, String>();
		map = userRoleDao.getroleInfoByUserId(userId);
		
		String userName = String.valueOf(map.get("USER_NAME"));		//用户名称
		String orleNote = String.valueOf(map.get("NOTE"));			//会员等级
		String dredgeTime = String.valueOf(map.get("DREDGE_TIME"));	//开通时间
		String dredgeType = String.valueOf(map.get("DREDGE_TYPE"));	//开通方式
		String price = String.valueOf(map.get("VIP_PRICE"));		//总金额
		String unit = String.valueOf(map.get("UNIT"));			//价格单位
		
		Calendar c = Calendar.getInstance();		//获得一个日历的实例
		
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dredgeTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.setTime(date);
		
		if(dredgeType.equals("1")){	//	选择一个月
			c.add(Calendar.MONTH, +1);
		}else if(dredgeType.equals("2")){ //选择一个季度
			c.add(Calendar.MONTH, +3);
		}else if(dredgeType.equals("3")){//选择一年
			c.add(Calendar.YEAR, +1);
		}
		
		String endTime = sdf.format(c.getTime());
		
		String authority = "";
		
		if(orleNote.equals("白银会员")){
			authority = "白银权限";
		}else if(orleNote.equals("黄金会员")){
			authority = "黄金权限";
		}else if(orleNote.equals("钻石会员")){
			authority = "钻石权限";
		}
		
		model.addAttribute("userName", userName);
		model.addAttribute("orleNote", orleNote);
		model.addAttribute("dredgeTime", dredgeTime);
		model.addAttribute("dredgeType", dredgeType);
		model.addAttribute("price", price);
		model.addAttribute("endTime", endTime);
		model.addAttribute("authority", authority);
		model.addAttribute("unit", unit);
		
		
		
	}

	

	

}
