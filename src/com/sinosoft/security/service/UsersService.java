package com.sinosoft.security.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.xml.rpc.ServiceException;

import org.springframework.ui.ModelMap;

import com.sinosoft.business.po.SysUserset;
import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.security.po.UserPassword;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.extend.TreeNode;
import com.sinosoft.security.po.query.UsersQuery;

/**
 * @Package com.sinosoft.security.service
 * @ClassName: UsersService
 * @Description: 系统用户信息 服务层接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-1 下午09:09:58
 */
public interface UsersService {
	
	/**
	 * 根据查询参数获取用户信息 用于用户管理页面
	 * @param model
	 * @param method
	 * @param request
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-20 下午09:55:39
	 * @version V1.0
	 */
	public void getUsersInfoForInitPage(ModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 根据用户id获取用户信息
	 * @param userId
	 * @return Users
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午10:42:46
	 * @version V1.0
	 */
	public Users getUserInfoByUserId(Long userId);
	
	/**
	 * 根据登录用户name获取用户信息
	 * @param userId
	 * @return Users
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午10:42:46
	 * @version V1.0
	 */
	public Users getAllUsersInfoByQuery(UsersQuery usersQuery);
	
	
	/**
	 * 根据用户id获取用户的扩展信息
	 * @param userId
	 * @return ExtendUsers
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午12:38:42
	 * @version V1.0
	 */
	public ExtendUsers getExtendUsersById(Long userId);
	
	/**
	 * 保存新的用户信息
	 * @param user
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午02:17:23
	 * @version V1.0
	 */
	public boolean addNewUser(Users user, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午05:19:46
	 * @version V1.0
	 */
	public boolean updateUserInfo (Users user, ExtendUsers eUser, ActivityModelMap modelMap);
	public boolean updateUserInfoById (Users user, ExtendUsers eUser, ActivityModelMap modelMap);
	/**
	 * 更新用户密码信息
	 * @param user
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午06:27:06
	 * @version V1.0
	 */
	public boolean updateUserPass(Users user, ExtendUsers eUser, ActivityModelMap modelMap);
	
	public boolean updateUserLock(Users user, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 删除用户信息记录
	 * @param userId
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午06:44:39
	 * @version V1.0
	 */
	public boolean removeUser(Long userId, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据用户id集合获取用户信息
	 * @param userIdList
	 * @return List<Users>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午10:52:51
	 * @version V1.0
	 */
	public List<Users> getUserInfoByUserIdList(List<Long> userIdList);
		
	/**
	 * 根据用户登录名查取用户信息
	 * @param userName
	 * @return Users
	 * @throws
	 * @author mrajian
	 * @date 2013-10-3 下午09:27:06
	 * @version V1.0
	 */
	public Users getUserInfoByUserName (String userName);
	
	/**
	 * 根据用户电子邮箱查取用户信息
	 * @param userEmail
	 * @return Users
	 * @throws
	 * @author mrajian
	 * @date 2013-10-11 上午09:35:16
	 * @version V1.0
	 */
	public Users getUserInfoByUserEmail (String userEmail);
	
	/**
	 * 根据用户所属的机构id查询用户的数量
	 * @param orgId
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-19 上午01:14:30
	 * @version V1.0
	 */
	public Integer getUsersCountByOrgId(Long orgId);
	
	/**
	 * 修改当前用户的基本信息
	 * @param eUser
	 * @param newUser
	 * @param modelMap
	 * @return boolean
	 * @return void
	 * @throws
	 * @author mrajian
	 * @date 2013-10-12 上午08:34:38
	 * @version V1.0
	 */
	public boolean updateCurrentUserBaseInfo(ExtendUsers eUser, Users newUser, ActivityModelMap modelMap);
	
	/**
	 * 修改当前用户的密码信息
	 * @param eUser
	 * @param password
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-13 下午08:45:21
	 * @version V1.0
	 */
	public boolean updateCurrentUserPassInfo(ExtendUsers eUser, UserPassword password, ActivityModelMap modelMap);
	
	/**
	 * 根据指定的用户id 获取用户角色信息
	 * @param userId
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午07:33:15
	 * @version V1.0
	 */
	public boolean getUserRoleInfoByUserId(Long userId, ActivityModelMap modelMap);
	
	/**
	 * 更新用户的角色信息
	 * @param userId
	 * @param roleId
	 * @param eUser
	 * @param modelMap
	 * @return boolean
	 * @throws
	 * @author mrajian
	 * @date 2013-10-27 下午07:57:06
	 * @version V1.0
	 */
	public boolean updateUserRole(Long userId, Long roleId, ExtendUsers eUser, ActivityModelMap modelMap);
	
	/**
	 * 根据检索条件获取用户信息
	 * @param usersQuery
	 * @return List<Users>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-24 下午09:32:46
	 * @version V1.0
	 */
	public List<Users> getUsersInfoByQuery(UsersQuery usersQuery);
	
	/**
	 * 
	 * @author 张永斌
	 *获取用户信息
	 * @param model
	 * @param method
	 * @param request
	 */
	public void getUserInfo(ActivityModelMap modelMap, HttpServletRequest request);
	
	
	
	/**
	 * 获取未分配组用户信息
	 */
	
	public  void getChooseUser(ModelMap model, String method, HttpServletRequest request);
	
    
    /**
     * 获取用户完整信息
     */
    public  ExtendUsers getUserInfoById(Integer id) throws Exception;
    public  ExtendUsers getUserInfoByNname(String name) throws Exception;
    
	
    
    /**
     * 邮箱激活
     */
	public void processActivate(String userEmail) throws Exception;
	 
    /**
     * 用户注册，新增一个用户
     */
	public boolean addUser(Users users, ModelMap model);
	
	//--------------用户分类查询开始------------------	
	public void getUserCount(HttpServletRequest request,ActivityModelMap model);
	public void getUserCountByDay(HttpServletRequest request,ActivityModelMap model,UsersQuery usersQuery);
	public void getUserCountByMonth(HttpServletRequest request,ActivityModelMap model,UsersQuery usersQuery);
	public void getUserCountByYear(HttpServletRequest request,ActivityModelMap model,UsersQuery usersQuery);
	//--------------用户分类查询结束------------------	
	
	public Integer getCountByusers(UsersQuery usersQuery);

	public void updatePassword(Users users);

	public Users selectMessageByName(HttpServletRequest request,
			ActivityModelMap modelMap, String userName);

	public SysUserset getUserset();

	/**
	 * 获取用户信息
	 * @param model
	 * @param method
	 * @param request
	 */
	public void getUsersInfo(ModelMap model, String method,
			HttpServletRequest request);
	
	/**
	 * 获取开通VIP信息
	 * @param model
	 * @param method
	 * @param request
	 */
	public void getDredgeVIP(ModelMap model, String method, HttpServletRequest request);
	public void getDredgeVIP(ActivityModelMap model, String method, HttpServletRequest request);
	
	/**
	 * 获取会员信息
	 * @param model
	 * @param method
	 * @param request
	 */
	public void getInfoOfVIP(ModelMap model, String method, HttpServletRequest request);
}
