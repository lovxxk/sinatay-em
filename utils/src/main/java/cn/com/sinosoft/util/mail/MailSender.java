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
			
			//�ж��Ƿ���Ҫ�����֤
			if (mailSenderInfo.isValidate()) {
				//�����Ҫ�����֤���򴴽�һ��������֤��
				authenticator = new MailAuthenticator(mailSenderInfo.getUserName(), mailSenderInfo.getPassword());
			}
			
			Properties properties = mailSenderInfo.getProperties();
			
			// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session 
			Session sendMailSession = Session.getInstance(properties, authenticator);
			
			// ����session����һ���ʼ���Ϣ  
			Message mailMessage = new MimeMessage(sendMailSession);
			
			// �����ʼ���Ϣ�ķ�����  
			Address senderAddress = new InternetAddress(mailSenderInfo.getMailSenderAddress());
			mailMessage.setFrom(senderAddress);
			// �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��   
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
			// �����ʼ���Ϣ������   
			mailMessage.setSubject(mailSenderInfo.getSubject());   
			// �����ʼ���Ϣ���͵�ʱ��   
			mailMessage.setSentDate(new Date());   
			// MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���   
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
			// �����ʼ���Ϣ����Ҫ����   
			String mailContent = mailSenderInfo.getContent();   
			mailMessage.setText(mailContent);
			mailMessage.saveChanges();
			// �����ʼ�   
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
			//�ж��Ƿ���Ҫ�����֤
			System.out.println("�Ƿ���Ҫ��֤���룺" + mailSenderInfo.isValidate());
			System.out.println("�ʼ��������û�����" + mailSenderInfo.getUserName());
			System.out.println("�ʼ����������룺" + mailSenderInfo.getPassword());
			if (mailSenderInfo.isValidate()) {
				//�����Ҫ�����֤���򴴽�һ��������֤��
				authenticator = new MailAuthenticator(mailSenderInfo.getUserName(), mailSenderInfo.getPassword());
			}
			
			Properties properties = mailSenderInfo.getProperties();
			
			// �����ʼ��Ự���Ժ�������֤������һ�������ʼ���session 
			Session sendMailSession = Session.getInstance(properties, authenticator);
			
			// ����session����һ���ʼ���Ϣ  
			Message mailMessage = new MimeMessage(sendMailSession);
			
			// �����ʼ���Ϣ�ķ�����  
			Address senderAddress = null;
			if(StringUtils.isNotBlank(mailSenderInfo.getPersonalName())){
				senderAddress = new InternetAddress(mailSenderInfo.getMailSenderAddress(), mailSenderInfo.getPersonalName());
			}else{
				senderAddress = new InternetAddress(mailSenderInfo.getMailSenderAddress());
			}
			mailMessage.setFrom(senderAddress);
			// �����ʼ��Ľ����ߵ�ַ�������õ��ʼ���Ϣ��   
			List<Address> addressList = new ArrayList<Address>();
			
			if (mailSenderInfo.getMailReceiversAddress() != null) {
				for (String mailReceiverAddress:mailSenderInfo.getMailReceiversAddress()) {
					mailReceiverAddress = StringUtils.trim(mailReceiverAddress);
					if (StringUtils.isNotBlank(mailReceiverAddress)) {
						System.out.println("�ʼ������ߵĵ�ַ��" + mailReceiverAddress);
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
			// �����ʼ���Ϣ������   
			mailMessage.setSubject(mailSenderInfo.getSubject());
			// �����ʼ���Ϣ���͵�ʱ��   
			mailMessage.setSentDate(new Date());   
			// �����ʼ���Ϣ����Ҫ����   
			String mailContent = mailSenderInfo.getContent();
			// MiniMultipart����һ�������࣬����MimeBodyPart���͵Ķ���   
	        Multipart mainPart = new MimeMultipart("related");   
	        // ����һ������HTML���ݵ�MimeBodyPart   
	        MimeBodyPart html = new MimeBodyPart();
		    // ����HTML����   
        	html.setContent(mailContent, mailSenderInfo.getContentType());
        	mainPart.addBodyPart(html);
	        
 		    // ��MiniMultipart��������Ϊ�ʼ�����
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
		    // �����ʼ�   
		    Transport.send(mailMessage);
		} catch (MessagingException e) {
			for (int i = 0; i < mailSenderInfo.getMailReceiversAddress().size(); i++) {
				log.error("send email failed��error message:" + e.getMessage()
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
