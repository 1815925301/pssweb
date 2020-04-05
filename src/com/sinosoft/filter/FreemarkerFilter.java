package com.sinosoft.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewRendererServlet;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import com.sinosoft.common.constant.MappingConstantConfig;

/**
 * @Package com.sinosoft.filter
 * @ClassName: FreemarkerFilter
 * @Description: TODO
 * @author mrajian
 * @Version V1.0
 * @date 2013-9-29 上午09:44:51
 */
public class FreemarkerFilter implements Filter{
	
	private Locale locale;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String localeStr = filterConfig.getInitParameter("locale");
        if(StringUtils.isNotBlank(localeStr)){
        	locale = Locale.getDefault();
        }else {
            locale = Locale.getDefault();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse res = (HttpServletResponse)response;
            
            StringBuffer reqUrl = req.getRequestURL();
            String outInterface = MappingConstantConfig.getValue("OUR_INTERFACE");
    		if(!reqUrl.toString().contains(outInterface)){
    			String name = req.getRequestURI();
                if (name.contains("main.ftl")) {
                	name= name.substring(1, name.lastIndexOf(".ftl"));
                	ApplicationContext ac = new ClassPathXmlApplicationContext("annomvc-servlet.xml"); 
                	FreeMarkerViewResolver viewResolver = (FreeMarkerViewResolver)ac.getBean("viewResolver");
//                	FreeMarkerViewResolver viewResolver = 
//                		(FreeMarkerViewResolver)SpringContextUtils.getCurrentContext().getBean(FreeMarkerViewResolver.class);
                    View view = viewResolver.resolveViewName("main", locale);
                    @SuppressWarnings("unchecked")
                    Map<String, Object> model = (Map<String, Object>) request.getAttribute(ViewRendererServlet.MODEL_ATTRIBUTE);
                    view.render(model, req, res);
                }
                chain.doFilter(request, response);
    		}
            
            
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
        
    }

}
