package test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.InternetAddress;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;
import cn.com.sinosoft.util.mail.InlineFile;
import cn.com.sinosoft.util.mail.MailSender;
import cn.com.sinosoft.util.mail.MailSenderInfo;
import cn.com.sinosoft.util.time.DateUtils;

public class sendMailTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    try {
	    	String mailReceiverAddress = "wangye7657@gmail.com";
//	    	String mailReceiverAddress = "always_wy@163.com";
//	    	String mailReceiverAddress = "memorise@126.com";
	    	String mailReceiverAddress_1 = "";//"memorise@126.com";//"wangchaolongsh884@sinosoft.com.cn";
	    	String mailReceiverAddress_2 = "";//"doukunping@sinatay.com";
	    	PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/mailSenderInfo.properties");
			MailSenderInfo mailSenderInfo = new MailSenderInfo(PropertyFileUtils.getProperties());
	    	mailSenderInfo.setSubject("邮件测试 "+DateUtils.getCurZhCNDateTime());
//		mailSenderInfo.setMailReceiverAddress(mailReceiverAddress);
	    	mailSenderInfo.setMailReceiverAddress(mailReceiverAddress+";"+mailReceiverAddress_1+";"+mailReceiverAddress_2, ";");
	    	//附件
	    	String mailPath = "D:/dev/workspace_sinatay/online/src/main/resources/mail/";
	    	String filePath = "";
	    	List<String> attachFileName = new ArrayList<String>();
			attachFileName.add(filePath);
//			mailSenderInfo.setAttachFileNames(attachFileName);
			List<InlineFile> inlineFileList = new ArrayList<InlineFile>();
			inlineFileList.add(new InlineFile(mailPath+"logo.png"));
			inlineFileList.add(new InlineFile(mailPath+"dimensionalCode_bg.png"));
			inlineFileList.add(new InlineFile(mailPath+"dimensionalCode_mb.jpg"));
			inlineFileList.add(new InlineFile(mailPath+"dimensionalCode_mc.jpg"));
			inlineFileList.add(new InlineFile(mailPath+"productImage_1.png"));
			inlineFileList.add(new InlineFile(mailPath+"productImage_2.png"));
			inlineFileList.add(new InlineFile(mailPath+"productImage_3.png"));
			inlineFileList.add(new InlineFile(mailPath+"title.png"));
			inlineFileList.add(new InlineFile(mailPath+"activation_icon.png"));
			mailSenderInfo.setInlineFiles(inlineFileList);
			
	    	Properties p = new Properties();  
	    	p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "D:/dev/workspace_sinatay/online/src/main/resources/mail"); 
	    	System.out.println(mailSenderInfo.getEmailEcoding());
	    	p.setProperty(Velocity.ENCODING_DEFAULT, mailSenderInfo.getEmailEcoding());  
	    	p.setProperty(Velocity.INPUT_ENCODING, mailSenderInfo.getEmailEcoding());  
	    	p.setProperty(Velocity.OUTPUT_ENCODING, mailSenderInfo.getEmailEcoding());
			Velocity.init(p);
//			Template template = getEmailTemplate(mailSenderInfo, "activateRegistrationMail.vm");  
//			Template template = getEmailTemplate(mailSenderInfo, "insureSuccessMail.vm");
//			Template template = getEmailTemplate(mailSenderInfo, "orderCompleteMail.vm");
//			Template template = getEmailTemplate(mailSenderInfo, "ordersPayMail.vm");
//			Template template = getEmailTemplate(mailSenderInfo, "renewalMail.vm");
			Template template = getEmailTemplate(mailSenderInfo, "resetPasswordMail.vm");
	        VelocityContext context = new VelocityContext();
	        context.put("unsubscribe", "#");
	        context.put("index", "http://www.sinatay.com/");
	        context.put("myPolicyBox", "#");
	        context.put("onlineService", "#");
	        context.put("fullName", "测试用户");
	        context.put("productName", "测试产品");
	        context.put("policyNo", GeneratorTransSerialNumber.generatorTransSerialNumber());
	        context.put("prem", "130.0");
	        context.put("contact", "http://www.sinatay.com/selfservice/contactus.jsp");
	        context.put("time", DateUtils.getCurZhCNDateTime());
	        StringWriter sw = new StringWriter();
//	        sw.append("邮件测试 --> "+DateUtils.getCurZhCNDateTime());
	        template.merge(context, sw);
	        mailSenderInfo.setContent(sw.toString());
	        
	        if(sendEmail(mailSenderInfo)){
				System.out.println("邮件发送成功！");
			}else{
				System.out.println("邮件发送失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static boolean sendEmail(MailSenderInfo mailSenderInfo){
		boolean emailSendFlag = false;
		try {
//			mailSenderInfo.init(PropertyFileUtils.getProperties());
			emailSendFlag = MailSender.sentHtmlMail(mailSenderInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return emailSendFlag;
	}
	
	public static Template getEmailTemplate(MailSenderInfo mailSenderInfo, String templateName) {
		Template template = null;
		try {
			Properties p = new Properties();  
			p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, mailSenderInfo.getVelocityResourceLoaderPath()); 
			p.setProperty(Velocity.ENCODING_DEFAULT, mailSenderInfo.getEmailEcoding());  
			p.setProperty(Velocity.INPUT_ENCODING, mailSenderInfo.getEmailEcoding());  
			p.setProperty(Velocity.OUTPUT_ENCODING, mailSenderInfo.getEmailEcoding());
			Velocity.init(p);
			template = Velocity.getTemplate(templateName);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		} catch (ParseErrorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return template;  
		
	}
}
