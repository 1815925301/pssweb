package com.sinosoft.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @Package com.sinosoft.filter
 * @ClassName: ForceNoCacheFilter
 * @Description: 使浏览器不缓存页面的过滤器
 * @author mrajian
 * @Version V1.0
 * @date 2013-9-20 上午09:56:42
 */
public class ForceNoCacheFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		((HttpServletResponse) servletresponse).setHeader("Cache-Control", "no-cache");
	    ((HttpServletResponse) servletresponse).setHeader("Pragma", "no-cache");
	    ((HttpServletResponse) servletresponse).setDateHeader ("Expires", -1);
	    filterchain.doFilter(servletrequest, servletresponse);
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
