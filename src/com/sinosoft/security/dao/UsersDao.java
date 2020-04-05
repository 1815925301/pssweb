package com.sinosoft.security.dao;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.query.UsersQuery;

/**
 * @Package com.sinosoft.security.dao
 * @ClassName: UsersDao
 * @Description: 系统用户信息的DAO 接口类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-2 下午07:15:27
 */
public interface UsersDao {
	
	/**
	 * 根据用户id获取用户信息
	 * @param userId
	 * @return Users
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午10:39:55
	 * @version V1.0
	 */
	public Users getUserInfoByUserId(Long userId);
	
	/**
	 * 根据用户名、用户id 精确查找符合条件的用户数量
	 * @param usersQuery
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午05:27:18
	 * @version V1.0
	 */
	public Integer getUserCountByName(UsersQuery usersQuery);
	
	/**
	 * 根据邮箱地址、用户id 精确查找符合条件的用户数量
	 * @param usersQuery
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午05:27:18
	 * @version V1.0
	 */
	public Integer getUserCountByEmail(UsersQuery usersQuery);
	
	/**
	 * 根据检索条件获取用户数量信息
	 * @param usersQuery
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-24 下午09:28:17
	 * @version V1.0
	 */
	public Integer getUsersCountByQuery(UsersQuery usersQuery);
	
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
	 * 根据登录用户名字获取用户信息
	 * @param usersQuery
	 * @return List<Users>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-24 下午09:32:46
	 * @version V1.0
	 */
	public Users getAllInfoByQuery(UsersQuery usersQuery);
	
	/**
	 * 根据用户id集合获取用户信息
	 * @param userIdList
	 * @return List<Users>
	 * @throws
	 * @author mrajian
	 * @date 2013-10-18 上午10:55:19
	 * @version V1.0
	 */
	public List<Users> getUserInfoByUserIdList (List<Long> userIdList);
	
	/**
	 * 根据用户登录名称查询用户信息 用户登录名称是唯一的，所以返回信息为单条记录或null
	 * @param userName
	 * @return User
	 * @throws
	 * @author mrajian
	 * @date 2013-10-2 下午08:32:41
	 * @version V1.0
	 */
	public Users getUserInfoByUserName(String userName);
	
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
	 * 根据用户ID修改用户信息
	 * @param user
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-12 上午08:56:30
	 * @version V1.0
	 */
	public Integer updateUserInfoById (Users user);
	
	/**
	 * 根据用户ID修改用户密码信息
	 * @param user
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-13 下午08:53:08
	 * @version V1.0
	 */
	public Integer updateUserPasswordById (Users user);
	
	/**
	 * 根据用户id删除用户信息
	 * @param UsersDao
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午06:48:00
	 * @version V1.0
	 */
	public Integer deleteUserById (Long userId);
	
	/**
	 * 检索符合给定条件的用户信息
	 * @param user
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-13 下午09:25:01
	 * @version V1.0
	 */
	public Integer selectCountByUserInfo(Users user);
	
	/**
	 * 添加新用户
	 * @param user
	 * @return Integer
	 * @throws
	 * @author mrajian
	 * @date 2013-10-26 上午03:25:02
	 * @version V1.0
	 */
	public Integer addNewUser(Users user);
	
	//查询用户
	public List<Users> selectUsersListByQuery(UsersQuery usersQuery);


	public void update(Users user)throws ParseException;

	//----------用户分类统计开始-----------
	public int selectUsersCountByTime(Users users);//初始化页面进行的查询
	//按日查询
	public int selectUserCountByDay(UsersQuery usersQuery);
	public List<UsersQuery> selecByDayGroup(UsersQuery usersQuery);
	//按月查询
	public int selectUserCountByMonth(UsersQuery usersQuery);
	public List<UsersQuery> selecByMonthGroup(UsersQuery usersQuery);
	//按年查询
	public int selectUserCountByYear(UsersQuery usersQuery);
	public List<UsersQuery> selecByYearGroup(UsersQuery usersQuery);
	//----------用户分类统计结束-----------	

	public Users getuserTimebyname(String userName);

}
