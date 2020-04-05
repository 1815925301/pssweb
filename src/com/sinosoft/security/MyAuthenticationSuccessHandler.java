package com.sinosoft.security;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.sinosoft.common.constant.MappingConstantConfig;
import com.sinosoft.common.constant.SystemConstant;
import com.sinosoft.security.dao.ResourcesDao;
import com.sinosoft.security.dao.RoleResourceDao;
import com.sinosoft.security.po.Resources;
import com.sinosoft.security.po.RoleResource;
import com.sinosoft.security.po.extend.ExtendUsers;

/**
 * @Package com.sinosoft.security
 * @ClassName: MyAuthenticationSuccessHandler
 * @Description: TODO
 * @author mrajian
 * @Version V1.0
 * @date 2014-3-10 上午01:42:08
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Resource
	private SystemConstant systemConstant;
	
	@Resource
	private ResourcesDao resourcesDao;
	
	@Override  
    public void onAuthenticationSuccess(HttpServletRequest request,  
            HttpServletResponse response, Authentication authentication)  
            throws IOException, ServletException {
		
		ServletContext application = request.getSession().getServletContext();
		ExtendUsers eUser=(ExtendUsers)request.getSession().getAttribute("CURRENT_USER_INFO");
		application.removeAttribute(eUser.getUserName()+"_logerr");
		application.removeAttribute(eUser.getUserName()+"_lastlog");
		StringBuffer reqUrl = request.getRequestURL();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//从内存中查出角色权限信息
		List<RoleResource> roleResourceList = systemConstant.getRoleResourceList();
		//从内存中查出系统资源信息
     	//	List<Resources> resourcesList = systemConstant.getResourcesList();
		boolean isFind = false;
		String url = "/main.do";
		if (userDetails.getAuthorities() != null && userDetails.getAuthorities().size() > 0) {
			for (Object userRole : userDetails.getAuthorities().toArray()) {
				String roleId = String.valueOf(userRole);
				if (roleId != null) {
					List<Resources>  userRoleresourcesList = resourcesDao.selectResourceInfoByRoleId(new Long(roleId));
					for(Resources resourcesinfo:userRoleresourcesList){
							if(resourcesinfo.getpId().compareTo(new Long(0))!=0){
								url = resourcesinfo.getResourceUrl();
								isFind = true; break;
							}
					}
//						for (RoleResource roleResource : roleResourceList) {
//							if (roleResource.getRoleId().compareTo(new Long(userRoleId)) == 0) {
//								//获取到资源id
//								for (Resources resources : resourcesList) {
//									if (resources.getId().compareTo(roleResource.getResourceId()) == 0) {
//										url = resources.getResourceUrl();
//										isFind = true; break;
//									}
//								}
//								if (isFind) break;
//							}
//							if (isFind) break;
//						}
				}
				if (isFind) break;
			}
		}
		
		if (!StringUtils.isBlank(url)) {
			response.sendRedirect(request.getContextPath() + "/home.do");
		} else {
			url = request.getContextPath() + "/login?msg=error";
			response.sendRedirect(url);
		}	
		
		
    }  

}
