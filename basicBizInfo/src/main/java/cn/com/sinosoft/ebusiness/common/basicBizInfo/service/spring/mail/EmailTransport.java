package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring.mail;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import com.actuate.logging.Log;

import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;
import cn.com.sinosoft.util.mail.MailSender;
import cn.com.sinosoft.util.mail.MailSenderInfo;

public class EmailTransport {
	
	private static final Map<String, Template> velocityTemplateMap = new HashMap<String, Template>();
	

	/**
	 * 初始化配置文件
	 */
	static {
		PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/mailSenderInfo.properties");	
	}
	
	/**
	 * 发送邮件
	 * @param context 
	 * @param mailSenderInfo
	 * @return
	 */
	public static boolean sendEmail(VelocityContext context, MailSenderInfo mailSenderInfo){
		boolean emailSendFlag = false;
		try {
			mailSenderInfo.init(PropertyFileUtils.getProperties());
			Template template = getEmailTemplate(mailSenderInfo, mailSenderInfo.getTemplateName());
			StringWriter sw = new StringWriter();
			template.merge(context, sw);
			mailSenderInfo.setContent(sw.toString());
			emailSendFlag = MailSender.sentHtmlMail(mailSenderInfo);
		} catch (Exception e) {
			Log.error("send email failed.reason : " + e.getMessage());
			e.printStackTrace();
		} 
		return emailSendFlag;
	}
	
	/***
	 * 获取邮件模版
	 * @param mailSenderInfo
	 * @param templateName
	 * @return
	 */
	public static  Template getEmailTemplate(MailSenderInfo mailSenderInfo, String templateName) {
		Template template = velocityTemplateMap.get(templateName);
		if (template == null) {
			loadEmailTemplate(mailSenderInfo, templateName);
			template = velocityTemplateMap.get(templateName);
		}
		return template;  
		
	}
	
	/**
	 * 根据模版名称请求模版
	 * @param productCode 产品代码
	 */
	public static void clearRuleResource(String templateName) {
		velocityTemplateMap.remove(templateName);
	}
	
	/**
	 * 清除所有模版
	 * @param productCode 产品代码
	 */
	public static void clearAllVelocityTemplate() {
		velocityTemplateMap.clear();
	}
	
	public static void loadEmailTemplate(MailSenderInfo mailSenderInfo, String templateName) {
		Template template = null;
		try {
			Properties p = new Properties();  
			p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, mailSenderInfo.getVelocityResourceLoaderPath()); 
			p.setProperty(Velocity.ENCODING_DEFAULT, mailSenderInfo.getEmailEcoding());  
			p.setProperty(Velocity.INPUT_ENCODING, mailSenderInfo.getEmailEcoding());  
			p.setProperty(Velocity.OUTPUT_ENCODING, mailSenderInfo.getEmailEcoding());
			Velocity.init(p);
			template = Velocity.getTemplate(templateName);
			velocityTemplateMap.put(templateName, template);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
