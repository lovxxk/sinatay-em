package cn.com.sinosoft.util.mail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import cn.com.sinosoft.lang.BooleanUtils;
import cn.com.sinosoft.util.string.StringUtils;

public class MailSenderInfo {
	
	/**
	 * 发送邮件的服务器IP
	 */
	private String mailServerHost;
	
	/**
	 * 发送邮件的服务器端口号
	 */
	private String mailServerPort = "25";
	
	/**
	 * 邮件发送者的地址
	 */
	private String mailSenderAddress;
	
	/**
	 * 邮件接收者的地址
	 */
	private String mailReceiverAddress;
	
	/**
	 * 多个邮件接受者的地址
	 */
	private List<String> mailReceiversAddress = new ArrayList<String>();
	
	/**
	 * 是否需要身份验证
	 */
	private boolean validate = false;
	
	/**
	 * 登录发送邮件服务器的用户名
	 */
	private String userName;
	
	/**
	 * 登录发送邮件服务器的密码
	 */
	private String password;
	
	/**
	 * 邮件主题
	 */
	private String subject;
	
	/**
	 * 邮件内容
	 */
	private String content;
	
	/**
	 * 邮件编码集
	 */
	private String emailEcoding = "GBK";
	
	/***
	 * 内容类型
	 */
	private String contentType;
	
	/**
	 * 附件名称
	 */
	private List<String> attachFileNames = new ArrayList<String>();
	
	/**
	 * 内置文件
	 */
	private List<InlineFile> inlineFiles = new ArrayList<InlineFile>();
	
	/**
	 * 模版加载绝对路径
	 */
	private String  velocityResourceLoaderPath;
	
	/***
	 * 模版名称
	 */
	private String templateName;
	
	/** 显示名称 */
	private String personalName;
	
	public static final String MULTIPART_SUBTYPE_MIXED = "mixed";

	public static final String MULTIPART_SUBTYPE_RELATED = "related";

	public static final String MULTIPART_SUBTYPE_ALTERNATIVE = "alternative";

	public static final String CONTENT_TYPE_ALTERNATIVE = "text/alternative";

	public static final String CONTENT_TYPE_HTML = "text/html";

	public static final String CONTENT_TYPE_CHARSET_SUFFIX = ";charset=";

	public static final String HEADER_PRIORITY = "X-Priority";

	public static final String HEADER_CONTENT_ID = "Content-ID";
	
	public String getMailServerHost() {
		return mailServerHost;
	}

	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}

	public String getMailServerPort() {
		return mailServerPort;
	}

	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}

	public String getMailSenderAddress() {
		return mailSenderAddress;
	}

	public void setMailSenderAddress(String mailSenderAddress) {
		this.mailSenderAddress = mailSenderAddress;
	}

	public String getMailReceiverAddress() {
		return mailReceiverAddress;
	}
	
	
	public MailSenderInfo() {
		
	}

	/**
	 * 以配置文件配置邮件发送信息
	 * @param properties
	 */
	public MailSenderInfo(Properties properties) {
		init(properties);
		
	}

	/**
	 * 以配置文件配置邮件发送信息
	 * @param properties
	 * @return 
	 */
	public void init(Properties properties) {
		
		this.mailServerHost = properties.getProperty("mailServerHost");
		
		if (StringUtils.isNotBlank((String)properties.get("mailServerPort"))) {
			this.mailServerPort = (String)properties.get("mailServerPort");
		}
		
		this.mailSenderAddress = properties.getProperty("mailSenderAddress");
		
		this.setMailReceiverAddress(properties.getProperty("mailReceiverAddress"));
		
		if (StringUtils.isNotBlank((String)properties.get("validate"))) {
			this.validate = BooleanUtils.toBoolean((String)properties.get("validate"));
		}
		
		this.userName = properties.getProperty("userName");
		
		this.password = properties.getProperty("password");
		
		this.personalName = properties.getProperty("personalName");
		
		if (StringUtils.isNotBlank(properties.getProperty("emailEcoding"))) {
			this.emailEcoding = properties.getProperty("emailEcoding");
		}
		
		this.contentType = properties.getProperty("contentType");
		
		if (StringUtils.isBlank(this.velocityResourceLoaderPath)) {
			this.velocityResourceLoaderPath = properties.getProperty("velocityResourceLoaderPath");	
		}
		
		
		
		if (StringUtils.isBlank(this.templateName)) {
			this.templateName = properties.getProperty("templateName");	
		}
		
		if (StringUtils.isNotBlank(properties.getProperty("personalName"))) {
		}
		
	}
	/**
	 * 对邮件接收者中包含semicolon（；），那么对邮件接收者进行分割为多个接收地址
	 * @param mailReceiverAddress 邮件接收者地址
	 */
	public void setMailReceiverAddress(String mailReceiverAddress) {
		if (StringUtils.isBlank(mailReceiverAddress))
			return;
		if (mailReceiverAddress.indexOf(";") > -1) {
			mailReceiversAddress.addAll(Arrays.asList(mailReceiverAddress.split(";")));
		} else {
			mailReceiversAddress.add(mailReceiverAddress);
		}
	}
	
	/**
	 * 对邮件接收者以分隔符进行分割为多个接收者的地址
	 * @param mailReceiverAddress 邮件接收者地址
	 * @param delimiter 分割符
	 */
	public void setMailReceiverAddress(String mailReceiverAddress, String delimiter) {
		if (mailReceiverAddress.indexOf(delimiter) > -1) {
			mailReceiversAddress.addAll(Arrays.asList(mailReceiverAddress.split(delimiter)));
		} else {
			mailReceiversAddress.add(mailReceiverAddress);
		}
	}
	
	public List<String> getMailReceiversAddress() {
		return mailReceiversAddress;
	}

	public void setMailReceiversAddress(List<String> mailReceiversAddress) {
		this.mailReceiversAddress = mailReceiversAddress;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmailEcoding() {
		return emailEcoding;
	}

	public void setEmailEcoding(String emailEcoding) {
		this.emailEcoding = emailEcoding;
	}
	
	
	public String getContentType() {
		if (StringUtils.isNotBlank(contentType)) {
			return contentType;
		} else {
			return CONTENT_TYPE_HTML + CONTENT_TYPE_CHARSET_SUFFIX + emailEcoding;
		}
		
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public List<String> getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(List<String> attachFileNames) {
		this.attachFileNames = attachFileNames;
	}
	
	

	public List<InlineFile> getInlineFiles() {
		return inlineFiles;
	}

	public void setInlineFiles(List<InlineFile> inlineFiles) {
		this.inlineFiles = inlineFiles;
	}

	public String getVelocityResourceLoaderPath() {
		return velocityResourceLoaderPath;
	}

	public void setVelocityResourceLoaderPath(String velocityResourceLoaderPath) {
		this.velocityResourceLoaderPath = velocityResourceLoaderPath;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}


	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public Properties getProperties() {
		Properties msiProperty = new Properties();
		msiProperty.put("mail.smtp.host", this.mailServerHost);
		msiProperty.put("mail.smtp.port", this.mailServerPort);
		msiProperty.put("mail.smtp.auth", BooleanUtils.toStringTrueFalse(this.validate));
		return msiProperty;
		
	}
	
}
