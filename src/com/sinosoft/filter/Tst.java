package com.sinosoft.filter;

import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Tst {

	public static void main(String[] args) {
		String path = "applicationContext.xml";    
        ApplicationContext context = new FileSystemXmlApplicationContext(path);    
        Object[] objs = new Object[] { "javacrazyer",    
                new Date().toLocaleString() };    
        // myName为资源文件的key值,objs为资源文件value值所需要的参数,Local.CHINA为国际化为什么语言    
        String str = context.getMessage("myName", objs, Locale.CHINA);    
        System.out.println(str);    
	}

}
