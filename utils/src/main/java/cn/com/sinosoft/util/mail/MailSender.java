package cn.com.sinosoft.util.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
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

import cn.com.sinosoft.util.string.StringUtils;

public class MailSender {
	
	private static Logger log = LoggerFactory.getLogger(MailSender.class);
	
	public static boolean sentTextMail(MailSenderInfo mailSenderInfo) {
		
		try {
			MailAuthenticator authenticator = null;
			
			//判断是否需要身份验证
			if (mailSenderInfo.isValidate()) {
				//如果需要身份验证，则创建一个密码验证器
				authenticator = new MailAuthenticator(mailSenderInfo.getUserName(), mailSenderInfo.getPassword());
			}
			
			Properties properties = mailSenderInfo.getProperties();
			
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session 
			Session sendMailSession = Session.getInstance(properties, authenticator);
			
			// 根据session创建一个邮件消息  
			Message mailMessage = new MimeMessage(sendMailSession);
			
			// 设置邮件消息的发送者  
			Address senderAddress = new InternetAddress(mailSenderInfo.getMailSenderAddress());
			mailMessage.setFrom(senderAddress);
			// 创建邮件的接收者地址，并设置到邮件消息中   
			List<Address> addressList = new ArrayList<Address>();
			
			if (mailSenderInfo.getMailReceiversAddress() != null) {
				for (String mailReceiverAddress:mailSenderInfo.getMailReceiversAddress()) {
					if (StringUtils.isNotBlank(mailReceiverAddress)) {
						Address receiverAddress = new InternetAddress(mailReceiverAddress);
						addressList.add(receiverAddress);
					}
				}
			}
			Address[] addresses = new Address[addressList.size()];
			for (int i = 0; i < addressList.size(); i++) {
				addresses[i] = addressList.get(i);
			}
			mailMessage.setRecipients(RecipientType.TO, addresses);
			// 设置邮件消息的主题   
			mailMessage.setSubject(mailSenderInfo.getSubject());   
			// 设置邮件消息发送的时间   
			mailMessage.setSentDate(new Date());   
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象   
	        Multipart mainPart = new MimeMultipart();   
			List<String> attachFileNames = mailSenderInfo.getAttachFileNames();
			for (int i = 0; i < attachFileNames.size(); i++) {
				FileDataSource source = new FileDataSource(attachFileNames.get(i));
				MimeBodyPart attachFilePart = new MimeBodyPart();
				attachFilePart.setDisposition(MimeBodyPart.ATTACHMENT);
				attachFilePart.setFileName(MimeUtility.encodeWord(source.getFile().getName()));
				attachFilePart.setDataHandler(new DataHandler(source));
				mainPart.addBodyPart(attachFilePart);
			}
			// 设置邮件消息的主要内容   
			String mailContent = mailSenderInfo.getContent();   
			mailMessage.setText(mailContent);
			mailMessage.saveChanges();
			// 发送邮件   
			Transport.send(mailMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
			return false; 
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    return true;  
	}
	
	public static boolean sentHtmlMail(MailSenderInfo mailSenderInfo) {
		
		try {
			MailAuthenticator authenticator = null;
			//判断是否需要身份验证
			System.out.println("是否需要验证密码：" + mailSenderInfo.isValidate());
			System.out.println("邮件服务器用户名：" + mailSenderInfo.getUserName());
			System.out.println("邮件服务器密码：" + mailSenderInfo.getPassword());
			if (mailSenderInfo.isValidate()) {
				//如果需要身份验证，则创建一个密码验证器
				authenticator = new MailAuthenticator(mailSenderInfo.getUserName(), mailSenderInfo.getPassword());
			}
			
			Properties properties = mailSenderInfo.getProperties();
			
			// 根据邮件会话属性和密码验证器构造一个发送邮件的session 
			Session sendMailSession = Session.getInstance(properties, authenticator);
			
			// 根据session创建一个邮件消息  
			Message mailMessage = new MimeMessage(sendMailSession);
			
			// 设置邮件消息的发送者  
			Address senderAddress = null;
			if(StringUtils.isNotBlank(mailSenderInfo.getPersonalName())){
				senderAddress = new InternetAddress(mailSenderInfo.getMailSenderAddress(), mailSenderInfo.getPersonalName());
			}else{
				senderAddress = new InternetAddress(mailSenderInfo.getMailSenderAddress());
			}
			mailMessage.setFrom(senderAddress);
			// 创建邮件的接收者地址，并设置到邮件消息中   
			List<Address> addressList = new ArrayList<Address>();
			
			if (mailSenderInfo.getMailReceiversAddress() != null) {
				for (String mailReceiverAddress:mailSenderInfo.getMailReceiversAddress()) {
					mailReceiverAddress = StringUtils.trim(mailReceiverAddress);
					if (StringUtils.isNotBlank(mailReceiverAddress)) {
						System.out.println("邮件接收者的地址：" + mailReceiverAddress);
						Address receiverAddress = new InternetAddress(mailReceiverAddress);
						addressList.add(receiverAddress);
					}
				}
			}
			Address[] addresses = new Address[addressList.size()];
			for (int i = 0; i < addressList.size(); i++) {
				addresses[i] = addressList.get(i);
			}
			mailMessage.setRecipients(RecipientType.TO, addresses);
			// 设置邮件消息的主题   
			mailMessage.setSubject(mailSenderInfo.getSubject());
			// 设置邮件消息发送的时间   
			mailMessage.setSentDate(new Date());   
			// 设置邮件消息的主要内容   
			String mailContent = mailSenderInfo.getContent();
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象   
	        Multipart mainPart = new MimeMultipart("related");   
	        // 创建一个包含HTML内容的MimeBodyPart   
	        MimeBodyPart html = new MimeBodyPart();
		    // 设置HTML内容   
        	html.setContent(mailContent, mailSenderInfo.getContentType());
        	mainPart.addBodyPart(html);
	        
 		    // 将MiniMultipart对象设置为邮件内容
	        List<String> attachFileNames = mailSenderInfo.getAttachFileNames();
	        for (int i = 0; i < attachFileNames.size(); i++) {
	        	String attachFileName = attachFileNames.get(i);
	        	if (StringUtils.isNotBlank(attachFileName)) {
	        		FileDataSource source = new FileDataSource(attachFileName);
		        	MimeBodyPart attachFilePart = new MimeBodyPart();
		    		attachFilePart.setDisposition(MimeBodyPart.ATTACHMENT);
		    		attachFilePart.setFileName(MimeUtility.encodeWord(source.getFile().getName()));
		    		attachFilePart.setDataHandler(new DataHandler(source));
		    		mainPart.addBodyPart(attachFilePart);
	        	}
	        	
	        }
	        List<InlineFile> inlineFiles = mailSenderInfo.getInlineFiles();
	        for (int i = 0; i < inlineFiles.size(); i++) {
	        	InlineFile inlineFile = inlineFiles.get(i);
	        	String contentId = inlineFile.getContentId();
	        	FileDataSource source = inlineFile.getSource();
	        	if (StringUtils.isNotBlank(contentId) && source != null) {
	        		MimeBodyPart mimeBodyPart = new MimeBodyPart();
	        		mimeBodyPart.setDisposition(MimeBodyPart.INLINE);
	        		mimeBodyPart.setHeader(MailSenderInfo.HEADER_CONTENT_ID, "<" + contentId + ">");
	        		mimeBodyPart.setDataHandler(new DataHandler(source));
	        		mainPart.addBodyPart(mimeBodyPart);
	        	}
	        }
		    
		    mailMessage.setContent(mainPart);   
		    // 发送邮件   
		    Transport.send(mailMessage);
		} catch (MessagingException e) {
			for (int i = 0; i < mailSenderInfo.getMailReceiversAddress().size(); i++) {
				log.error("send email failed，error message:" + e.getMessage()
						+ "; receivers address:" + mailSenderInfo.getMailReceiversAddress().get(i));
			}
			e.printStackTrace();
			return false; 
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    return true;  
	}
}
