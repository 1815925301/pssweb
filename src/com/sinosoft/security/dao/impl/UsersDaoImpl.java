package com.sinosoft.security.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import com.sinosoft.base.dao.BaseDao;
import com.sinosoft.security.dao.UsersDao;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.po.query.UsersQuery;

/**
 * @Package com.sinosoft.security.dao.impl
 * @ClassName: UsersDaoImpl
 * @Description: 处理用户信息的DAO 实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-2 下午07:15:48
 */
@Repository(value = "usersDao")
public class UsersDaoImpl extends BaseDao implements UsersDao {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsersDaoImpl.class);
	
	@Override
	public Users getUserInfoByUserId(Long userId) {
		if (userId == null) {
			LOGGER.error("Dao层：根据用户id查取用户信息，用户id参数为空：{}", userId);
			return null;
		}
		LOGGER.debug("Dao层：根据用户id查取用户信息：{}", userId);
		return (Users)select("usersDao.selectUserInfoById", userId);
	}
	
	@Override
	public Integer getUserCountByName(UsersQuery usersQuery){
		LOGGER.debug("Dao层：根据用户名称、用户id，精确查找符合条件的用户数量");
		return (Integer)getReadSqlSession().selectOne("usersDao.selectUserCountByQueryForCheck", usersQuery);
	}
	
	@Override
	public Integer getUserCountByEmail(UsersQuery usersQuery){
		LOGGER.debug("Dao层：根据电子邮箱、用户id，精确查找符合条件的用户数量");
		return (Integer)getReadSqlSession().selectOne("usersDao.selectUserCountByQueryForCheck", usersQuery);
	}
	
	@Override
	public Integer getUsersCountByQuery(UsersQuery usersQuery) {
		LOGGER.debug("Dao层：根据检索条件获取用户数量");
		return (Integer)getReadSqlSession().selectOne("usersDao.selectUsersCountByQuery", usersQuery);
	}
	
	@Override
	public List<Users> getUsersInfoByQuery(UsersQuery usersQuery) {
		LOGGER.debug("Dao层：根据检索条件获取用户的记录信息");
		return getReadSqlSession().selectList("usersDao.selectUsersInfoByQuery", usersQuery);
	}
	
	@Override
	public List<Users> getUserInfoByUserIdList (List<Long> userIdList) {
		if (userIdList == null || userIdList.size() == 0) {
			LOGGER.error("Dao层：根据用户id集合查取用户信息，用户id集合参数为空：{}", userIdList);
			return null;
		}
		LOGGER.debug("Dao层：根据用户id查取用户信息：{}", userIdList);
		return getReadSqlSession().selectList("usersDao.selectUserInfoByIdList", userIdList);
	}
	
	@Override
	public Users getUserInfoByUserName(String userName){
		if (StringUtils.isBlank(userName)) {
			LOGGER.error("Dao层：根据用户登录名查取用户信息，用户名参数为空：{}", userName);
			return null;
		}
		LOGGER.debug("Dao层：根据用户登录名查取用户信息：{}", userName);
		return (Users)select("usersDao.selectUserInfoByUserName", userName);
	}
	
	@Override
	public Users getUserInfoByUserEmail (String userEmail) {
		if (StringUtils.isBlank(userEmail)) {
			LOGGER.error("Dao层：根据用户电子邮箱查取用户信息，用户名参数为空：{}", userEmail);
			return null;
		}
		LOGGER.debug("Dao层：根据用户电子邮箱查取用户信息：{}", userEmail);
		return (Users)select("usersDao.selectUserInfoByUserEmail", userEmail);
	}
	
	@Override
	public Integer getUsersCountByOrgId(Long orgId) {
		if (orgId == null) {
			LOGGER.error("Dao层：根据用户电子邮箱查取用户信息，机构id参数为空：{}", orgId);
			return null;
		}
		LOGGER.debug("Dao层：根据用户所属的机构id查询用户的数量：{}", orgId);
		return (Integer)select("usersDao.selectUsersCountByOrgId", orgId);
	}
	
	@Override
	public Integer updateUserInfoById (Users user) {
		LOGGER.debug("Dao层：根据用户id更新用户信息：{}", user.toString());
		return update("usersDao.updateUserInfoById",user);
	}
	
	@Override
	public Integer updateUserPasswordById (Users user) {
		LOGGER.debug("Dao层：根据用户id更新用户密码信息：{}", user.toString());
		return update("usersDao.updateUserPasswordById",user);
	}
	
	@Override
	public Integer deleteUserById (Long userId) {
		LOGGER.debug("Dao层：根据用户id删除用户信息：{}", userId);
		return update("usersDao.deleteUserById",userId);
	}
	
	@Override
	public Integer selectCountByUserInfo(Users user) {
		LOGGER.debug("Dao层：查取符合检索条件的用户数量，检索条件为: {}", user);
		return (Integer)select("usersDao.selectUsersCountByQuery",user);
	}
	
	@Override
	public Integer addNewUser(Users user) {
		LOGGER.debug("Dao层：增加信息的用户信息");
		return (Integer)insert("usersDao.insertNewUser",user);
	}

	@Override
	public List<Users> selectUsersListByQuery(UsersQuery usersQuery) {
		LOGGER.debug("Dao层：根据检索条件获取用户的记录信息");
		return getReadSqlSession().selectList("usersDao.selectUsersListByQuery", usersQuery);
	}



	@Override
	public void update(Users user) throws ParseException {
		
	}

	//----------用户分类统计开始-----------
	
	//----------用户分类统计结束-----------	

	public int selectUsersCountByTime(Users users) {
		LOGGER.debug("Dao层：默认查询当天注册用户的数量");
		return (Integer)select("usersDao.selectUsersCountByTime",users);
	}
	//按天开始
	public int selectUserCountByDay(UsersQuery usersQuery){
		LOGGER.debug("Dao层：按天查询注册用户的数量");
		return (Integer)select("usersDao.selectUserCountByDay",usersQuery);
	}
	public List<UsersQuery> selecByDayGroup(UsersQuery usersQuery){
		LOGGER.debug("Dao层：按天查询注册用户的数量");
		return getReadSqlSession().selectList("usersDao.selecByDayGroup",usersQuery);
	}
	//按月开始
	public int selectUserCountByMonth(UsersQuery usersQuery){
		LOGGER.debug("Dao层：按月查询注册用户的数量");
		return (Integer)select("usersDao.selectUserCountByMonth",usersQuery);
	}
	public List<UsersQuery> selecByMonthGroup(UsersQuery usersQuery){
		LOGGER.debug("Dao层：按天查询注册用户的数量");
		return getReadSqlSession().selectList("usersDao.selecByMonthGroup",usersQuery);
	}
	//按年开始
	public int selectUserCountByYear(UsersQuery usersQuery){
		LOGGER.debug("Dao层：按年查询注册用户的数量");
		return (Integer)select("usersDao.selectUserCountByYear",usersQuery);
	}
	public List<UsersQuery> selecByYearGroup(UsersQuery usersQuery){
		LOGGER.debug("Dao层：按天查询注册用户的数量");
		return getReadSqlSession().selectList("usersDao.selecByYearGroup",usersQuery);
	}

	@Override
	public Users getAllInfoByQuery(UsersQuery usersQuery) {
		LOGGER.debug("Dao层：根据登录用户名字查询用户详细信息");
		return getReadSqlSession().selectOne("usersDao.getAllInfoByQuery",usersQuery);
	}

	@Override
	public Users getuserTimebyname(String userName) {
		
		return getReadSqlSession().selectOne("usersDao.getuserTimeByname",userName);
	}
}
