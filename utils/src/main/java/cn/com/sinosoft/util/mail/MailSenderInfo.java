package cn.com.sinosoft.util.mail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import cn.com.sinosoft.lang.BooleanUtils;
import cn.com.sinosoft.util.string.StringUtils;

public class MailSenderInfo {
	
	/**
	 * �����ʼ��ķ�����IP
	 */
	private String mailServerHost;
	
	/**
	 * �����ʼ��ķ������˿ں�
	 */
	private String mailServerPort = "25";
	
	/**
	 * �ʼ������ߵĵ�ַ
	 */
	private String mailSenderAddress;
	
	/**
	 * �ʼ������ߵĵ�ַ
	 */
	private String mailReceiverAddress;
	
	/**
	 * ����ʼ������ߵĵ�ַ
	 */
	private List<String> mailReceiversAddress = new ArrayList<String>();
	
	/**
	 * �Ƿ���Ҫ�����֤
	 */
	private boolean validate = false;
	
	/**
	 * ��¼�����ʼ����������û���
	 */
	private String userName;
	
	/**
	 * ��¼�����ʼ�������������
	 */
	private String password;
	
	/**
	 * �ʼ�����
	 */
	private String subject;
	
	/**
	 * �ʼ�����
	 */
	private String content;
	
	/**
	 * �ʼ����뼯
	 */
	private String emailEcoding = "GBK";
	
	/***
	 * ��������
	 */
	private String contentType;
	
	/**
	 * ��������
	 */
	private List<String> attachFileNames = new ArrayList<String>();
	
	/**
	 * �����ļ�
	 */
	private List<InlineFile> inlineFiles = new ArrayList<InlineFile>();
	
	/**
	 * ģ����ؾ���·��
	 */
	private String  velocityResourceLoaderPath;
	
	/***
	 * ģ������
	 */
	private String templateName;
	
	/** ��ʾ���� */
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
	 * �������ļ������ʼ�������Ϣ
	 * @param properties
	 */
	public MailSenderInfo(Properties properties) {
		init(properties);
		
	}

	/**
	 * �������ļ������ʼ�������Ϣ
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
	 * ���ʼ��������а���semicolon����������ô���ʼ������߽��зָ�Ϊ������յ�ַ
	 * @param mailReceiverAddress �ʼ������ߵ�ַ
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
	 * ���ʼ��������Էָ������зָ�Ϊ��������ߵĵ�ַ
	 * @param mailReceiverAddress �ʼ������ߵ�ַ
	 * @param delimiter �ָ��
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
