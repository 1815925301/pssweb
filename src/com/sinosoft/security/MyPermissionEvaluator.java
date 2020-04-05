package com.sinosoft.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.RoleResource;
import com.sinosoft.security.service.ResourcesService;
import com.sinosoft.security.service.RoleResourceService;

public class MyPermissionEvaluator implements PermissionEvaluator {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyPermissionEvaluator.class);
	
	@Resource
    private RoleResourceService roleResourceService;//角色和资源关联服务
	
	@Resource
	private ResourcesService resourcesService;//资源服务
	
	/**
	 *spring security hasPermission标签验证方法
	 */
	@Override
	public boolean hasPermission(Authentication authentication,Object domainObject,Object permission) {
		// TODO Auto-generated method stub
		 UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 Collection<? extends GrantedAuthority> authorities=userDetails.getAuthorities();
		 List<Long> roleIdList=new ArrayList<Long>();//封装当前用户的角色id
	     for (GrantedAuthority authority : authorities) {
	    	 roleIdList.add(Long.valueOf(authority.getAuthority()));
	     }
	     List<Long> resourceIdList = new ArrayList<Long>();//封装当前用户的资源id
	     List<RoleResource> roleResouceList = roleResourceService.getRoleResourceInfoByRoleIdList(roleIdList);//得到角色id与到资源id集合
	     for (RoleResource roleResource : roleResouceList) {
	    	 resourceIdList.add(roleResource.getResourceId());
		 }
	     List<Resources> resourcesListByLocalUser = resourcesService.getResourceInfoByIdList(resourceIdList);
	     for (Resources resources : resourcesListByLocalUser) {
			if(permission.equals(resources.getResourceName())){
				return true;
			}
		 }
	     
	     return false;
	}

	@Override
	public boolean hasPermission(Authentication arg0, Serializable arg1,
			String arg2, Object arg3) {
		// TODO Auto-generated method stub
		System.out.println("进入2----------------------");
		return false;
	}
	
	 

}
