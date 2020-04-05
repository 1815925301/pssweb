package com.sinosoft.common.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Package com.dangdang.LOGGER.mail
 * @ClassName: SimpleMailSender
 * @Description: 简单邮件（不带附件的邮件）发送器
 * @author zzq
 * @Version V1.0
 * @date 2013-3-1 下午02:38:55
 */
public class SimpleMailSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMailSender.class);

	/**
	 * 发送文本格式邮件
	 * @param SimpleMailSender
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-11-15 上午10:55:48
	 * @version V1.0
	 */
	public boolean sendTextMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			// 如果需要身份认证，则创建一个密码验证器
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 以HTML格式发送邮件
	 * @param mailInfo
	 * @return boolean
	 * @throws
	 * @author zzq
	 * @date 2013-11-15 上午10:57:34
	 * @version V1.0
	 */
	public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address[] tos = null;
			// 将要群发的邮箱地址存在了个字符串中 用 ；隔开
			String[] receivers = org.apache.commons.lang.StringUtils.split(mailInfo.getToAddress(), ";");
			if (receivers != null) {
				// 为每个邮件接收者创建一个地址
				tos = new InternetAddress[receivers.length];
				for (int i = 0; i < receivers.length; i++) {
					String s = receivers[i];
					tos[i] = new InternetAddress(s);
				}
			}
			
			// 创建邮件的抄送者地址，并设置到邮件消息中
			Address[] cos = null;
			// 将要群发的邮箱地址存在了个字符串中 用 ；隔开
			String[] cReceivers = org.apache.commons.lang.StringUtils.split(mailInfo.getCcAddress(), ";");
			if (cReceivers != null) {
				// 为每个邮件接收者创建一个地址
				cos = new InternetAddress[cReceivers.length];
				for (int i = 0; i < cReceivers.length; i++) {
					String s = cReceivers[i];
					cos[i] = new InternetAddress(s);
				}
			}
			// 将所有接收者地址都添加到邮件接收者属性中
			mailMessage.setRecipients(Message.RecipientType.TO, tos);
			// 将所有抄送者地址都添加到邮件抄送者属性中
			mailMessage.setRecipients(Message.RecipientType.CC, cos);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);

			MimeMultipart multipart = new MimeMultipart();
			BodyPart msgBodyPart = new MimeBodyPart();// 用来放置文本内容
			msgBodyPart.setContent(mailInfo.getContent(), "text/plain;charset=UTF-8");
			multipart.addBodyPart(msgBodyPart);
			setAttMultipart(multipart, mailInfo.getAttachFileNames());
			
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private static void setAttMultipart(Multipart multipart, String[] attachments) {
		try {
			BodyPart attBodyPart;
			DataSource ds;
			if (attachments != null && attachments.length != 0) {
				for (int i = 0; i < attachments.length; i++) {
					attBodyPart = new MimeBodyPart();// 用来放置附件
					ds = new FileDataSource(new File(attachments[i]));
					attBodyPart.setDataHandler(new DataHandler(ds));// 设置DataHandler
					attBodyPart.setFileName(MimeUtility.encodeText(ds.getName()));// 附件的显示名字
					multipart.addBodyPart(attBodyPart);
				}
			}
		} catch (MessagingException e) {
			LOGGER.error("添加附件出错！", e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}