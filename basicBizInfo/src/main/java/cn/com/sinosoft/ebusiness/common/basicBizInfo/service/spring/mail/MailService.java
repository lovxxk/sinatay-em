package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring.mail;

import ins.framework.utils.FileUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.exolab.castor.mapping.Mapping;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.Attachment;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.InlineFile;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.domain.MailConfig;
import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.CastorMappingUnMarshall;
import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.LoadMappingFiles;


/**
 * 
 * 发送邮件类
 *
 */
public class MailService  implements MimeMessagePreparator{
	
	private String configPath;
	
	
	// 发件人

	private String from;
	
	private String senderName;
	
	// 收件人

	private String[] to;
	// 抄送人
	private String[] cc;
	// 密件抄送人
	private String[] bcc;
	// 邮件主题
	private String subject="你好!";
	// 邮件正文（仅在简单文本邮件发送时使用，稍微复杂格式的邮件内容，建议使用velocity模板配置）

    
	private String text;
	// 附件信息Map，包含附件文件名称和附件文件组成的key-value对


	private List attachment;
	// 内嵌文件信息Map，包含由内嵌文件名称和内嵌文件组成的key-value对


	private List inlineFile;
	// velocity模板文件路径，为velocity的class的相对路径


	private String velocityFilePath ="/com/chinalife/ebusiness/common/basicBizInfo/service/spring/mail/vm/mail.vm";
	// velocity模板中定义变量的集合，第一个参数为velocity模板中的变量名，第二个参数为其值


	private Map  model;
	// VelocityEngine
	private VelocityEngine velocityEngine;
	// JavaMailSender
	private JavaMailSenderImpl sender;
	
	
	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	// 更新邮件标志
	private boolean flag = false;

	public void setFlag(boolean flag){
		this.flag = flag;
	}
	
	public void setVelocityFilePath(String path){
		this.velocityFilePath = path;
	}
	public void setSubject(String subject){
		this.subject = subject;
	}
	/**
	 * 简单格式邮件发送功能


	 * @param to 收件人地址，可以包含多个收件人
	 * @param text 邮件文本内容
	 * @throws CheckedMailException  包含邮件发送失败原因的异常
	 */
	public void sendMail(String[] to, 
			String text) throws CheckedMailException{
		try {
//			this.init();
			this.sendMail(this.from, to, null, null, null, null, this.subject, text, null, null);
		} catch (CheckedMailException cme) {
			throw cme;
		}
	}
	/**
	 * 简单格式邮件发送
	 * @param to 收件人地址，可以包含多个收件人
	 * @param bcc 密送人，可以包含多个收件人
	 * @param title 标题
	 * @param text 内容
	 * @throws CheckedMailException
	 */
	public void sendMailAndBcc(String[] to, String[]bcc,String title,
			String text) throws CheckedMailException{
		try {
//			this.init();
			this.sendMail(this.from, to, null, bcc, null, null, title, text, null, null);
		} catch (CheckedMailException cme) {
			throw cme;
		}
	}
	
	public void sendMail(String to, List inlineFile,
			String text) throws CheckedMailException{
		try {
//			this.init();
			String toS[] = {to};
			this.sendMail(this.from, toS, null, null, inlineFile, null, this.subject, text, null, null);
		} catch (CheckedMailException cme) {
			throw cme;
		}
	}
	public void sendMail(String[] to, List inlineFile,
			String text) throws CheckedMailException{
		try {
//			this.init();
			String toS[] = to;
			this.sendMail(this.from, toS, null, null, inlineFile, null, this.subject,text, null, null);
		} catch (CheckedMailException cme) {
			throw cme;
		}
	}
	/**
	 * 使用velocity模板发送邮件


	 * @param to 收件人地址，可以包含多个收件人
	 * @param model 邮件正文velocity模板中使用的变量Map，key为velocity模板中引用的变量，value为其对应的值


	 * @throws CheckedMailException  包含邮件发送失败原因的异常
	 */
	public void sendMail(String[] to, 
			Map model) throws CheckedMailException{
		try {
//			this.init();
			this.sendMail(this.from, to, null, null, null, null, this.subject, null, this.velocityFilePath, model);
		} catch (CheckedMailException cme) {
			throw cme;
		}
	}
	/**
	 * 使用velocity模板发送邮件  
	 * @param to 收件人地址，可以包含多个收件人
	 * @param model 邮件正文velocity模板中使用的变量Map，key为velocity模板中引用的变量，value为其对应的值


	 * @param title 标题自定义


	 * @throws CheckedMailException  包含邮件发送失败原因的异常
	 */
	public void sendMail(String[] to, 
			Map model,String title) throws CheckedMailException{
		try {
			this.subject = title;
//			this.init();
			this.sendMail(this.from, to, null, null, null, null, this.subject, null, this.velocityFilePath, model);
		} catch (CheckedMailException cme) {
			throw cme;
		}
	}
	/**
	 * 使用velocity模板发送邮件，同时包含附件和内嵌文件功能


	 * @param to 收件人地址，可以包含多个收件人
	 * @param attachment 附件对象，参见Attachment类，包含附件名称及其对应的附件文件（java.io.File）


	 * @param inlineFile 内嵌对象，参见InlineFile类，包含附件名称及其对应的附件文件（java.io.File）


	 * @param model 邮件正文velocity模板中使用的变量Map，key为velocity模板中引用的变量，value为其对应的值


	 * @throws CheckedMailException  包含邮件发送失败原因的异常
	 */
	public void sendMail(String[] to, 
			List attachment, 
			List inlineFile, 
			Map model,String title) throws CheckedMailException{
		try {
//			this.init();
			this.subject = title;
			this.sendMail(this.from, to, null, null, attachment, inlineFile, this.subject, null, this.velocityFilePath, model);
		} catch (CheckedMailException cme) {
			throw cme;
		}
	}
	
	/**
	 * 使用velocity模板发送邮件，同时包含抄送、密件抄送、附件、内嵌文件功能


	 * @param to 收件人地址，可以包含多个收件人
	 * @param cc 抄送人地址，可以包含多个抄送人
	 * @param bcc 密件抄送地址，可以包含多个密件抄送


	 * @param attachment 附件对象，参见Attachment类，包含附件名称及其对应的附件文件（java.io.File）


	 * @param inlineFile 内嵌对象，参见InlineFile类，包含附件名称及其对应的附件文件（java.io.File）


	 * @param model 邮件正文velocity模板中使用的变量Map，key为velocity模板中引用的变量，value为其对应的值


	 * @throws CheckedMailException  包含邮件发送失败原因的异常
	 */
	public void sendMail(String[] to, 
			String[] cc, 
			String[] bcc, 
			List attachment, 
			List inlineFile, 
			Map model) throws CheckedMailException{
		try {
//			this.init();
			this.sendMail(this.from, to, cc, bcc, attachment, inlineFile, this.subject, null, this.velocityFilePath, model);
		} catch (CheckedMailException cme) {
			throw cme;
		}
	}
	
	/**
	 * 发送邮件主功能，主要用于其他sendMail方法重载
	 * @param from 发件人地址
	 * @param to 收件人地址，可以包含多个收件人
	 * @param cc 抄送人地址，可以包含多个抄送人
	 * @param bcc 密件抄送地址，可以包含多个密件抄送


	 * @param attachment 附件对象，参见Attachment类，包含附件名称及其对应的附件文件（java.io.File）


	 * @param inlineFile 内嵌对象，参见InlineFile类，包含附件名称及其对应的附件文件（java.io.File）


	 * @param subject 邮件主题
	 * @param text 邮件正文（文本，仅用于简单邮件发送）
	 * @param velocityFilePath 邮件正文使用的velocity模板
	 * @param model 邮件正文velocity模板中使用的变量Map，key为velocity模板中引用的变量，value为其对应的值


	 * @throws CheckedMailException  包含邮件发送失败原因的异常
	 */
	private void sendMail(String from, 
			String[] to, 
			String[] cc, 
			String[] bcc, 
			List attachment, 
			List inlineFile, 
			String subject,
			String text,
			String velocityFilePath, 
			Map model) throws CheckedMailException{
		
		// 收集邮件信息
		this.setProperties(from, to, cc, bcc, attachment, inlineFile, subject, text, velocityFilePath, model);
		
		// 准备邮件信息
		try {
			this.prepare(this.sender.createMimeMessage());
		} catch (CheckedMailException cme) {
			throw cme;
		}
		// 发送邮件


		try{
			this.sender.send(this);
			flag = true;
		}catch(MailException me){
			me.printStackTrace();
			flag = false;
			CheckedMailException cme = new CheckedMailException("mail send failure！");
//			throw cme;
		}catch(Exception e){
		   throw new RuntimeException(e.getCause());
		}
		finally{
			
		}
	}
	
	/**
	 * 初始化
	 * @throws Exception 
	 */
	public void iniConfig() {
		try{
			Mapping mailMapping = LoadMappingFiles.loadMappingFile("config", "mailMapping.xml");
			String configPathdir = getFilePath(configPath);
			CastorMappingUnMarshall cmu = new CastorMappingUnMarshall();
			String requestxml = FileUtils.read(configPathdir);
			
			MailConfig mailConfig = (MailConfig) cmu.unmarshallMessage(requestxml, mailMapping);
			this.senderName = mailConfig.getMailAddresssName();
			this.from = mailConfig.getMailAddress();
			this.sender.setHost(mailConfig.getMailHost());
			this.sender.setPort(25);
			this.sender.setUsername(mailConfig.getMailAddress().substring(0, mailConfig.getMailAddress().indexOf("@")));
			this.sender.setPassword(mailConfig.getUserPassword());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	private String getFilePath(String exceptionConfigPath)
		throws UnsupportedEncodingException {
	//String filePathTemp = this.getClass().getClassLoader().getResource(exceptionConfigPath).getPath();
	String filePathTemp = MailService.class.getClassLoader().getResource("config/" +exceptionConfigPath).getPath();
	filePathTemp = java.net.URLDecoder.decode(filePathTemp, "utf-8");
	return filePathTemp;
	}
	
	public void prepare(MimeMessage mimeMessage) throws CheckedMailException {
		try{
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
			boolean flag = false;
			
			// 设置发件人地址
			if(this.from != null && this.from.trim().length() > 0){
				if(MailUtil.isAddressValidate(this.from)){
					try {
						message.setFrom(from,new String(this.senderName.getBytes(),"GBK"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					CheckedMailException cme = new CheckedMailException("发件人地址有误，请检查！");
					throw cme;
				}
			}else{
				CheckedMailException cme = new CheckedMailException("发件人地址不能为空，请输入发件人地址！");
				throw cme;
			}
			// 设置收件人地址
			if(this.to != null && this.to.length > 0){
				InternetAddress[] address = new InternetAddress[this.to.length];
				for(int i = 0; i < this.to.length; i ++){
					if(MailUtil.isAddressValidate(this.to[i])){
						address[i] = new InternetAddress(this.to[i]);
					}else{
						CheckedMailException cme = new CheckedMailException("收件人地址有误，请检查！");
						throw cme;
					}
				}
				message.setTo(address);
			}else{
				CheckedMailException cme = new CheckedMailException("收件人地址不能为空，请输入收件人地址！");
				throw cme;
			}
			
			// 设置抄送人地址
			if(this.cc != null && this.cc.length > 0){
				InternetAddress[] ccAddress = new InternetAddress[this.cc.length];
				for(int i = 0; i < this.cc.length; i ++){
					if(MailUtil.isAddressValidate(this.cc[i])){
						ccAddress[i] = new InternetAddress(this.cc[i]);
					}else{
						flag = true;
					}
				}
				if(!flag){
					message.setCc(ccAddress);
					flag = false;
				}else{
					CheckedMailException cme = new CheckedMailException("抄送人地址有误，请检查！");
					throw cme;
				}
			}
			
			// 设置密件抄送人地址
			if(this.bcc != null && this.bcc.length > 0){
				InternetAddress[] bccAddress = new InternetAddress[this.bcc.length];
				for(int i = 0; i < this.bcc.length; i ++){
					if(MailUtil.isAddressValidate(this.bcc[i])){
						bccAddress[i] = new InternetAddress(this.bcc[i]);
					}else{
						flag = true;
					}
				}
				if(!flag){
					message.setBcc(bccAddress);
					flag = false;
				}else{
					CheckedMailException cme = new CheckedMailException("密件抄送人地址有误，请检查！");
					throw cme;
				}
			}
			
			// 设置邮件主题
			message.setSubject(this.subject);
			
			// 设置附件信息
			if(this.attachment != null && !this.attachment.isEmpty()){
				for(int i = 0; i < this.attachment.size(); i++){
					Attachment theAttachment = (Attachment)this.attachment.get(i);
					  try {    
						  message.addAttachment(MimeUtility.encodeWord(theAttachment.getAttachmentFileName()), theAttachment.getAttachmentFile());;    
				        } catch (UnsupportedEncodingException e) {              
				            e.printStackTrace();    
				        } 
				}
			}
			// 设置邮件正文
			if(this.text == null || text.trim().length() < 1){
				this.text = VelocityEngineUtils.mergeTemplateIntoString(
					this.velocityEngine, this.velocityFilePath, this.model);
				try {
					text = new String(this.text.getBytes("ISO-8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			message.setText(this.text, true);
			
			// 设置inline文件
			if(this.inlineFile != null && !this.inlineFile.isEmpty()){
				for(int i = 0; i < inlineFile.size(); i++){
					InlineFile theInlineFile = (InlineFile)this.inlineFile.get(i);
					message.addInline(theInlineFile.getInlineFileName(), theInlineFile.getInlineFile());
				}
			}
			
		}catch(MessagingException me){
			me.printStackTrace();
			CheckedMailException cme = new CheckedMailException("组装MimeMessage失败！");
			throw cme;
		}catch(VelocityException ve){
			ve.printStackTrace();
			CheckedMailException cme = new CheckedMailException("读取Velocity邮件模板失败！");
			throw cme;
		}
	}
	
	/**
	 * 设置邮件相关属性


	 * @param from
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param attachment
	 * @param inlineFile
	 * @param subject
	 * @param text
	 * @param velocityFilePath
	 * @param model
	 */
	private void setProperties(String from, 
			String[] to, 
			String[] cc, 
			String[] bcc, 
			List attachment, 
			List inlineFile, 
			String subject,
			String text,
			String velocityFilePath, 
			Map model){
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.attachment = attachment;
		this.inlineFile = inlineFile;
		this.subject = subject;
		this.text = text;
		this.velocityFilePath = velocityFilePath;
		this.model = model;
	}
	public void run(){
		try {
			this.sendMail(this.to, null, this.inlineFile, this.model,this.subject);
		} catch (CheckedMailException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 目前只用到了发送图片，没用到添加附件
	 * @param to
	 * @param inlineFile
	 * @param model
	 */
	public void setMailMessage(String[] to, 
			List inlineFile, 
			Map model){
		this.to = to;
		this.inlineFile = inlineFile;
		this.model = model;
	}
	
	/*private void init() throws CheckedMailException{
		this.from = "";//AppConfig.get("sysconst.MAIL_ADDRESS");
		this.senderName="";//AppConfig.get("sysconst.MAIL_ADDRESSNAME");
	}*/
	
	public static void main(String args[]) throws IOException{
	}
	
	public void setVelocityEngine(VelocityEngine velocityEngine){
		this.velocityEngine = velocityEngine;
	}
	
	public void setSender(JavaMailSenderImpl sender){
		this.sender = sender;
	}

	

	public String getVelocityFilePath() {
		return velocityFilePath;
	}

	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}
}
