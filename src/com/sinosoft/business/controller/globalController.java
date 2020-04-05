package com.sinosoft.business.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.util.log.Log;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.sinosoft.common.util.ActivityWebUtils;
import com.sinosoft.common.util.PropertiesUtils;
import com.sinosoft.common.web.ActivityModelMap;

@Controller
public class globalController {
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/changeLanguage.do")
	public void changeLanguage(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		//String returnPage  = (String) request.getSession().getAttribute("returnPage");
		//ActivityWebUtils.WrapperModle(request, model);
		HttpSession session=request.getSession(true);//获取session
		String result="true";//定义一个返回类型
		String langType = request.getParameter("locale"); //页面传递的变量名称 
		Locale locale=null;
		if (langType != null) {
			int indexOfUnderscore = langType.indexOf('_');
			if (indexOfUnderscore != -1) {
				String language = langType.substring(0, indexOfUnderscore);
				String country = langType.substring(indexOfUnderscore + 1);
				locale = new Locale(language, country);
				session.setAttribute("language_js", language);
			} else {
				locale = new Locale(langType);
			}
		}
		 session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);//语言生效  
      /*  if(langType.equals("zh_CN")){  
             locale = new Locale("zh", "CN");//中文   
             session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);   
        }  
        else if(langType.equals("en_US")){  
             locale = new Locale("en", "US"); //英文  
             session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);  
        }  
        else if(langType.equals("ru_RU")){  
        	locale = new Locale("ru", "RU"); //俄文
        	session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);  
        } */ 
        session.setAttribute("locale", locale);//定义变量，用于切换语言后，后台对于接收数据的判断
		/*Object objectUrl = request.getSession().getAttribute("returnUrl");
		if (objectUrl != null) {
			return "redirect:/" + objectUrl.toString();
		} else {
			return "/index";
		}*/
      //  return "manage/" + returnPage;
        response.getWriter().print(result);
	}
	@RequestMapping(method = {RequestMethod.POST}, value = "/getLanguage.do")
	public @ResponseBody Map<String, Object> getLanguage(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		HttpSession session=request.getSession(true);//获取session
		ActivityModelMap modelMap = new ActivityModelMap(request);
		String language_js = (String) session.getAttribute("language_js");
		modelMap.put("language_js", language_js);
		return modelMap.getModelMap();
	}
}
