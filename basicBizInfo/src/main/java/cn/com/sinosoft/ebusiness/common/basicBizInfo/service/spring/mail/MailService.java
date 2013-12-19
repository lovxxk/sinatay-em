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
 * �����ʼ���
 *
 */
public class MailService  implements MimeMessagePreparator{
	
	private String configPath;
	
	
	// ������

	private String from;
	
	private String senderName;
	
	// �ռ���

	private String[] to;
	// ������
	private String[] cc;
	// �ܼ�������
	private String[] bcc;
	// �ʼ�����
	private String subject="���!";
	// �ʼ����ģ����ڼ��ı��ʼ�����ʱʹ�ã���΢���Ӹ�ʽ���ʼ����ݣ�����ʹ��velocityģ�����ã�

    
	private String text;
	// ������ϢMap�����������ļ����ƺ͸����ļ���ɵ�key-value��


	private List attachment;
	// ��Ƕ�ļ���ϢMap����������Ƕ�ļ����ƺ���Ƕ�ļ���ɵ�key-value��


	private List inlineFile;
	// velocityģ���ļ�·����Ϊvelocity��class�����·��


	private String velocityFilePath ="/com/chinalife/ebusiness/common/basicBizInfo/service/spring/mail/vm/mail.vm";
	// velocityģ���ж�������ļ��ϣ���һ������Ϊvelocityģ���еı��������ڶ�������Ϊ��ֵ


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

	// �����ʼ���־
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
	 * �򵥸�ʽ�ʼ����͹���


	 * @param to �ռ��˵�ַ�����԰�������ռ���
	 * @param text �ʼ��ı�����
	 * @throws CheckedMailException  �����ʼ�����ʧ��ԭ����쳣
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
	 * �򵥸�ʽ�ʼ�����
	 * @param to �ռ��˵�ַ�����԰�������ռ���
	 * @param bcc �����ˣ����԰�������ռ���
	 * @param title ����
	 * @param text ����
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
	 * ʹ��velocityģ�巢���ʼ�


	 * @param to �ռ��˵�ַ�����԰�������ռ���
	 * @param model �ʼ�����velocityģ����ʹ�õı���Map��keyΪvelocityģ�������õı�����valueΪ���Ӧ��ֵ


	 * @throws CheckedMailException  �����ʼ�����ʧ��ԭ����쳣
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
	 * ʹ��velocityģ�巢���ʼ�  
	 * @param to �ռ��˵�ַ�����԰�������ռ���
	 * @param model �ʼ�����velocityģ����ʹ�õı���Map��keyΪvelocityģ�������õı�����valueΪ���Ӧ��ֵ


	 * @param title �����Զ���


	 * @throws CheckedMailException  �����ʼ�����ʧ��ԭ����쳣
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
	 * ʹ��velocityģ�巢���ʼ���ͬʱ������������Ƕ�ļ�����


	 * @param to �ռ��˵�ַ�����԰�������ռ���
	 * @param attachment �������󣬲μ�Attachment�࣬�����������Ƽ����Ӧ�ĸ����ļ���java.io.File��


	 * @param inlineFile ��Ƕ���󣬲μ�InlineFile�࣬�����������Ƽ����Ӧ�ĸ����ļ���java.io.File��


	 * @param model �ʼ�����velocityģ����ʹ�õı���Map��keyΪvelocityģ�������õı�����valueΪ���Ӧ��ֵ


	 * @throws CheckedMailException  �����ʼ�����ʧ��ԭ����쳣
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
	 * ʹ��velocityģ�巢���ʼ���ͬʱ�������͡��ܼ����͡���������Ƕ�ļ�����


	 * @param to �ռ��˵�ַ�����԰�������ռ���
	 * @param cc �����˵�ַ�����԰������������
	 * @param bcc �ܼ����͵�ַ�����԰�������ܼ�����


	 * @param attachment �������󣬲μ�Attachment�࣬�����������Ƽ����Ӧ�ĸ����ļ���java.io.File��


	 * @param inlineFile ��Ƕ���󣬲μ�InlineFile�࣬�����������Ƽ����Ӧ�ĸ����ļ���java.io.File��


	 * @param model �ʼ�����velocityģ����ʹ�õı���Map��keyΪvelocityģ�������õı�����valueΪ���Ӧ��ֵ


	 * @throws CheckedMailException  �����ʼ�����ʧ��ԭ����쳣
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
	 * �����ʼ������ܣ���Ҫ��������sendMail��������
	 * @param from �����˵�ַ
	 * @param to �ռ��˵�ַ�����԰�������ռ���
	 * @param cc �����˵�ַ�����԰������������
	 * @param bcc �ܼ����͵�ַ�����԰�������ܼ�����


	 * @param attachment �������󣬲μ�Attachment�࣬�����������Ƽ����Ӧ�ĸ����ļ���java.io.File��


	 * @param inlineFile ��Ƕ���󣬲μ�InlineFile�࣬�����������Ƽ����Ӧ�ĸ����ļ���java.io.File��


	 * @param subject �ʼ�����
	 * @param text �ʼ����ģ��ı��������ڼ��ʼ����ͣ�
	 * @param velocityFilePath �ʼ�����ʹ�õ�velocityģ��
	 * @param model �ʼ�����velocityģ����ʹ�õı���Map��keyΪvelocityģ�������õı�����valueΪ���Ӧ��ֵ


	 * @throws CheckedMailException  �����ʼ�����ʧ��ԭ����쳣
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
		
		// �ռ��ʼ���Ϣ
		this.setProperties(from, to, cc, bcc, attachment, inlineFile, subject, text, velocityFilePath, model);
		
		// ׼���ʼ���Ϣ
		try {
			this.prepare(this.sender.createMimeMessage());
		} catch (CheckedMailException cme) {
			throw cme;
		}
		// �����ʼ�


		try{
			this.sender.send(this);
			flag = true;
		}catch(MailException me){
			me.printStackTrace();
			flag = false;
			CheckedMailException cme = new CheckedMailException("mail send failure��");
//			throw cme;
		}catch(Exception e){
		   throw new RuntimeException(e.getCause());
		}
		finally{
			
		}
	}
	
	/**
	 * ��ʼ��
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
			
			// ���÷����˵�ַ
			if(this.from != null && this.from.trim().length() > 0){
				if(MailUtil.isAddressValidate(this.from)){
					try {
						message.setFrom(from,new String(this.senderName.getBytes(),"GBK"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					CheckedMailException cme = new CheckedMailException("�����˵�ַ�������飡");
					throw cme;
				}
			}else{
				CheckedMailException cme = new CheckedMailException("�����˵�ַ����Ϊ�գ������뷢���˵�ַ��");
				throw cme;
			}
			// �����ռ��˵�ַ
			if(this.to != null && this.to.length > 0){
				InternetAddress[] address = new InternetAddress[this.to.length];
				for(int i = 0; i < this.to.length; i ++){
					if(MailUtil.isAddressValidate(this.to[i])){
						address[i] = new InternetAddress(this.to[i]);
					}else{
						CheckedMailException cme = new CheckedMailException("�ռ��˵�ַ�������飡");
						throw cme;
					}
				}
				message.setTo(address);
			}else{
				CheckedMailException cme = new CheckedMailException("�ռ��˵�ַ����Ϊ�գ��������ռ��˵�ַ��");
				throw cme;
			}
			
			// ���ó����˵�ַ
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
					CheckedMailException cme = new CheckedMailException("�����˵�ַ�������飡");
					throw cme;
				}
			}
			
			// �����ܼ������˵�ַ
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
					CheckedMailException cme = new CheckedMailException("�ܼ������˵�ַ�������飡");
					throw cme;
				}
			}
			
			// �����ʼ�����
			message.setSubject(this.subject);
			
			// ���ø�����Ϣ
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
			// �����ʼ�����
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
			
			// ����inline�ļ�
			if(this.inlineFile != null && !this.inlineFile.isEmpty()){
				for(int i = 0; i < inlineFile.size(); i++){
					InlineFile theInlineFile = (InlineFile)this.inlineFile.get(i);
					message.addInline(theInlineFile.getInlineFileName(), theInlineFile.getInlineFile());
				}
			}
			
		}catch(MessagingException me){
			me.printStackTrace();
			CheckedMailException cme = new CheckedMailException("��װMimeMessageʧ�ܣ�");
			throw cme;
		}catch(VelocityException ve){
			ve.printStackTrace();
			CheckedMailException cme = new CheckedMailException("��ȡVelocity�ʼ�ģ��ʧ�ܣ�");
			throw cme;
		}
	}
	
	/**
	 * �����ʼ��������


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
	 * Ŀǰֻ�õ��˷���ͼƬ��û�õ���Ӹ���
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
