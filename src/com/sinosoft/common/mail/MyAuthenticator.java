package com.sinosoft.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import com.sinosoft.common.constant.Constant;
import com.sinosoft.common.util.DESPlus;

/**
 * @Package com.sinosoft.common.mail
 * @ClassName: MyAuthenticator
 * @Description: TODO
 * @author zzq
 * @Version V1.0
 * @date 2013-11-15 上午10:52:39
 */
public class MyAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public MyAuthenticator() {
	}

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
	
	public PasswordAuthentication performCheck(String user,String pass){
		userName = user;
		password = pass;
		return getPasswordAuthentication();
	}

	public static void main(String[] args) throws Exception {
		// 这个类主要是设置邮件
	
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("zkr_admin@163.com");
//		mailInfo.setPassword(DESPlus.desPlusDecrypt("18a437fc6e0b9784def947d249cd58b4"));// 您的邮箱密码
		mailInfo.setPassword("admin555");
		mailInfo.setFromAddress("zkr_admin@163.com");
		mailInfo.setToAddress("879608483@qq.com");
		mailInfo.setSubject("这是测试");
	
		mailInfo.setContent("<a href=http://localhost:8080/iot-web/user/register?action=activate&email>sadsadas</a>");
		//mailInfo.setAttachFileNames(new String[]{"D:/图片/1.jpg","D:/图片/DB2在Win7下安装及建库流程.doc"});
		// 这个类主要来发送邮件
//		SimpleMailSender sms = new SimpleMailSender();
//		sms.sendHtmlMail(mailInfo);
//		sms.sendTextMail(mailInfo);// 发送文体格式
		SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式
	}
  
}

