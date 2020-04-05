package com.sinosoft.security.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.sinosoft.common.web.ActivityModelMap;
import com.sinosoft.dblog.po.Syslog;
import com.sinosoft.dblog.service.MonitorReceiverLogService;
import com.sinosoft.security.dao.UserRoleDao;
import com.sinosoft.security.po.UserRole;
import com.sinosoft.security.po.extend.ExtendUsers;
import com.sinosoft.security.po.query.UserRoleQuery;
import com.sinosoft.security.service.UserRoleService;

/**
 * @Package com.sinosoft.security.service.impl
 * @ClassName: UserRoleServiceImpl
 * @Description: 系统用户与角色关联信息 服务层实现类
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-4 下午08:33:52
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleServiceImpl.class);
	
	@Resource
	private UserRoleDao userRoleDao;
	@Resource
	private MonitorReceiverLogService syslog;
	
	@Override
	public List<UserRole> getRoleIdListByUserId(Long userId) {
		if(userId == null) {
			LOGGER.error("Service层：根据用户id获取用户的角色信息，用户ID为空：{}", userId);
			return null;
		}
		LOGGER.debug("Service层：根据用户id获取用户的角色信息");
		UserRoleQuery userRoleQuery = new UserRoleQuery();
		userRoleQuery.setUserId(userId);
		userRoleQuery.setPageSize(-1);
		return userRoleDao.getUserRoleByQuery(userRoleQuery);
	}
	
	@Override
	public Integer getUserRoleCountByRoleId(Long roleId) {
		if(roleId == null) {
			LOGGER.error("Service层：根据检索条件获取符合条件的用户角色关联信息的数量，角色ID为空：{}", roleId);
			return null;
		}
		LOGGER.debug("Service层：根据检索条件获取符合条件的用户角色关联信息的数量");
		UserRoleQuery userRoleQuery = new UserRoleQuery();
		userRoleQuery.setUserId(roleId);
		return userRoleDao.getUserRoleCountByQuery(userRoleQuery);
	}
	
	@Override
	public Integer deleteUserRoleByUserId(Long userId) {
		LOGGER.debug("Service层：删除给定用户的用户角色关联关系: userId={}", userId);
		return userRoleDao.deleteUserRoleByUserId(userId);
	}
	
	@Override
	public Integer addNewUserRole(UserRole userRole) {
		LOGGER.debug("Service层：保存新的用户角色关系信息");
		return userRoleDao.addNewUserRole(userRole);
	}

	@Override
	public void updateBeforeUserRoleInfo(ActivityModelMap model, String method,
			HttpServletRequest request) {
		ExtendUsers eUser = (ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		
		String roleId = request.getParameter("roleName");
		String dredgeType = request.getParameter("dredgeType");
		String price = request.getParameter("price");
		String userId = String.valueOf(eUser.getId());
		String userName = String.valueOf(eUser.getUserName());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("roleId", roleId);
		map.put("dredgeType", dredgeType);
		map.put("price", price);
		map.put("userId", userId);
		
		int num = userRoleDao.updateUserRoleInfo(map);
		Syslog log = new Syslog();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(num>=1){
			Date date = new Date();
		    String	date1 = sdf.format(date);
			log.setCreatetime(date1);
			log.setLogtype("info");
			log.setDescription(userName+"申请会员信息提交成功");
			log.setLoglevel("2");
			syslog.saveSystemLog(log);
		}else{
			Date date = new Date();
		    String	date1 = sdf.format(date);
			log.setCreatetime(date1);
			log.setLogtype("error");
			log.setDescription(userName+"申请会员信息提交失败");
			log.setLoglevel("2");
			syslog.saveSystemLog(log);
		}
		model.put("num", num);
	}

	@Override
	public int getRolePayCountByUserId(String userid) {
		Map<String, String> map = new HashMap<String, String>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("userid", userid);
		int num = userRoleDao.getRolePayCountByUserId(map1);
		return num;
	}
}
