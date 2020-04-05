package com.sinosoft.common.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sinosoft.common.constant.MappingConstantConfig;
import com.sinosoft.common.util.DESPlus;

/**
 * @Package com.sinosoft.common.mail
 * @ClassName: SendMailService
 * @Description: 发送邮件的入口方法
 * @author zzq
 * @Version V1.0
 * @date 2013-11-15 上午10:54:54
 */
@Service("sendMailService")
public class SendMailService {
	
	private static final Logger LOGGER  = LoggerFactory.getLogger(SendMailService.class.getName());
	
	/**
	 * 发送邮件的主入口方法
	 * @param attachFiles : 邮件附件路径 多个附件以逗号分隔
	 * @param mailSubject : 邮件主题
	 * @param mailContent : 邮件内容
	 * @param toUserAddress
	 * @return void
	 * @throws
	 * @author zzq
	 * @date 2013-3-4 下午12:15:57
	 * @version V1.0
	 */
	public void sendMailService(String attachFiles, String mailSubject, String mailContent, 
			String toUserAddress, String ccUserAddress) throws Exception {
		LOGGER.debug("邮件主题：{}", mailSubject);
		LOGGER.debug("邮件内容：{}", mailContent);
		LOGGER.debug("邮件附件：{}", attachFiles);
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost(MappingConstantConfig.getValue("MAIL_SERVER_HOST"));
		mailInfo.setMailServerPort(MappingConstantConfig.getValue("MAIL_SERVER_PORT"));
		mailInfo.setValidate(true);
		mailInfo.setUserName(MappingConstantConfig.getValue("MAIL_USER_NAMR"));
		mailInfo.setPassword(DESPlus.desPlusDecrypt(MappingConstantConfig.getValue("FROM_USER_PASS")));// 您的邮箱密码
		mailInfo.setFromAddress(MappingConstantConfig.getValue("FROM_USER_ADDRESS"));
		mailInfo.setToAddress(toUserAddress);
		mailInfo.setCcAddress(ccUserAddress);
		mailInfo.setSubject(mailSubject);
		mailInfo.setContent(mailContent);
		if (attachFiles != null && !attachFiles.equals("")) {
			String[] attachFileArray = attachFiles.split(",");
			mailInfo.setAttachFileNames(attachFileArray);
		}
		SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式
	}
	
	

}