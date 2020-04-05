package com.sinosoft.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sinosoft.security.po.UserRole;
import com.sinosoft.security.po.Users;
import com.sinosoft.security.service.UserRoleService;
import com.sinosoft.security.service.UsersService;

/**
 * @Package com.sinosoft.security
 * @ClassName: MyUserDetailServiceImpl
 * @Description: TODO
 * @author mrajian
 * @Version V1.0
 * @date 2013-10-1 下午08:11:41
 */
public class MyUserDetailServiceImpl implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailServiceImpl.class);
	
	@Resource
	private UsersService usersService;
	
	@Resource
	private UserRoleService userRoleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		LOGGER.debug("MyUserDetailServiceImpl:loadUserByUsername 正在加载用户名和密码，用户名为：{}", username);
		
		Users user = usersService.getUserInfoByUserName(username);
		if(user == null){
			LOGGER.error("用户名没有找到! {}", username);
			throw new UsernameNotFoundException("用户名不存在!");
		}
		
		boolean enabled = true;
        boolean accountNonExpired = user.getStatus().compareTo(new Integer(0)) == 0 ? true : false;        //是否过期
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = user.getIsLock().compareTo(new Integer(0)) == 0 ? true : false;  //是否可用 
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		
		//一个用户可能有多个角色
		List<UserRole> userRoleList = userRoleService.getRoleIdListByUserId(user.getId());
		for(UserRole userRole : userRoleList){
			GrantedAuthority gantedAuthority = new SimpleGrantedAuthority(userRole.getRoleId().toString());
			authorities.add(gantedAuthority);
		}
		User userdetail = new User(user.getUserName(), user.getPassword(), enabled, 
				accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return userdetail;
	}

}
