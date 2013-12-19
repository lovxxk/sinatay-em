package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;

import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.facade.SendEmailService;
import cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring.mail.EmailTransport;
import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;
import cn.com.sinosoft.util.mail.MailSenderInfo;
import cn.com.sinosoft.util.time.DateUtils;

public class SendEmailServiceSpringImpl implements SendEmailService {

	/**
	 * 注册激活邮件
	 * @param map
	 * @return
	 */
	public boolean sendActivateRegistEmail(String title,Map<String, String> map) throws Exception {
		if(StringUtils.isBlank(title)) title = "信泰保险提醒：您正在注册成为网站会员，请您完成账号激活";
		MailSenderInfo mailSenderInfo = new MailSenderInfo();
		mailSenderInfo.setSubject(title);
		mailSenderInfo.setValidate(true);
		mailSenderInfo.setMailReceiverAddress(map.get("email"));
		mailSenderInfo.setTemplateName("activateRegistrationMail.vm");
        VelocityContext context = getVelocityContext();
        context.put("activateUrl", map.get("activateUrl"));
		
		return EmailTransport.sendEmail(context, mailSenderInfo);
	}

	/**
	 * 密码重置邮件
	 * @param map
	 * @return
	 */
	public boolean sendResetPasswordEmail(String title,Map<String, String> map) throws Exception{
		if(StringUtils.isBlank(title)) title = "信泰保险提醒：您正在进行取回密码操作，请您完成验证";
		MailSenderInfo mailSenderInfo = new MailSenderInfo();
		mailSenderInfo.setSubject(title);
		mailSenderInfo.setValidate(true);
		mailSenderInfo.setMailReceiverAddress(map.get("email"));
		mailSenderInfo.setTemplateName("resetPasswordMail.vm");
		VelocityContext context = getVelocityContext();
		context.put("date", DateUtils.formatDate(new Date(),DateUtils.ZHCN_DATATIMEF_STR_YYYYMMDDHHMM));
		context.put("validateCode", map.get("validateCode"));
		return EmailTransport.sendEmail(context, mailSenderInfo);
	}

	/**
	 * 承保成功邮件
	 * @param map
	 * @return
	 */
	public boolean sendInsureSuccessEmail(String title,Map<String, String> map) {
		MailSenderInfo mailSenderInfo = new MailSenderInfo();
		mailSenderInfo.setSubject("信泰保险提醒：您已经成功投保 "+title+" 产品，请查看保单信息");
		mailSenderInfo.setValidate(true);
		mailSenderInfo.setMailReceiverAddress(map.get("email"));
		mailSenderInfo.setTemplateName("insureSuccessMail.vm");
		VelocityContext context = getVelocityContext();
		context.put("email", map.get("email"));
		context.put("fullName", map.get("fullName"));
		context.put("genderTag", map.get("genderTag"));
		context.put("date", map.get("date"));
		context.put("insFullName", map.get("insFullName"));
		context.put("productName", map.get("productName"));
		context.put("policyNo", map.get("policyNo"));
		context.put("inceptionDate", map.get("inceptionDate"));
		context.put("count", map.get("count"));
		context.put("premium", map.get("premium"));
		context.put("grossPremium", map.get("grossPremium"));
		context.put("memberCenter", map.get("memberCenter"));
		context.put("EPolicy", map.get("EPolicy"));
		
		return EmailTransport.sendEmail(context, mailSenderInfo);
	}

	/**
	 * 订单支付邮件
	 * @param map
	 * @return
	 */
	public boolean sendOrderPayEmail(String title,Map<String, String> map) throws Exception{
		if(StringUtils.isBlank(title)) title = "信泰保险提醒：您的订单已提交成功";
		MailSenderInfo mailSenderInfo = new MailSenderInfo();
		mailSenderInfo.setSubject(title);
		mailSenderInfo.setValidate(true);
		mailSenderInfo.setMailReceiverAddress(map.get("email"));
		mailSenderInfo.setTemplateName("ordersPayMail.vm");
		VelocityContext context = getVelocityContext();
		context.put("email", map.get("email"));
		context.put("fullName", map.get("fullName"));
		context.put("genderTag", map.get("genderTag"));
		context.put("date", map.get("date"));
		context.put("insFullName", map.get("insFullName"));
		context.put("productName", map.get("productName"));
		context.put("policyNo", map.get("policyNo"));
		context.put("count", map.get("count"));
		context.put("premium", map.get("premium"));
		context.put("grossPremium", map.get("grossPremium"));
		context.put("orderPay", map.get("orderPay"));
		
		return EmailTransport.sendEmail(context, mailSenderInfo);
	}

	/**
	 * 完善订单邮件
	 * @param map
	 * @return
	 */
	public boolean sendOrderCompleteEmail(String title,Map<String, String> map) throws Exception{
		MailSenderInfo mailSenderInfo = new MailSenderInfo();
		mailSenderInfo.setSubject("信泰保险提醒：您已选购 "+title+" 产品");
		mailSenderInfo.setValidate(true);
		mailSenderInfo.setMailReceiverAddress(map.get("email"));
		mailSenderInfo.setTemplateName("orderCompleteMail.vm");
		VelocityContext context = getVelocityContext();
		context.put("email", map.get("email"));
		context.put("fullName", map.get("fullName"));
		context.put("genderTag", map.get("genderTag"));
		context.put("date", map.get("date"));
		context.put("productName", map.get("productName"));
		context.put("continueToInsure", map.get("continueToInsure"));
		
		return EmailTransport.sendEmail(context, mailSenderInfo);
	}

	/**
	 * 保障到期邮件
	 * @param map
	 * @return
	 */
	public boolean sendRenewalEmail(String title,Map<String, String> map) throws Exception{
		boolean result = false;

		
		return result;
	}

	public VelocityContext getVelocityContext(){
		PropertyFileUtils.init(FilePathUtil.getClassBuildPath() + "config/sinatay_config.properties");
		Properties properties = PropertyFileUtils.getProperties();
		String imgPath = "/resources/image/email/";
		VelocityContext context = new VelocityContext();
		//邮件模板图片
		context.put("logo", properties.getProperty("sinatayUrl")+imgPath+"logo.png");
		context.put("activation_icon", properties.getProperty("sinatayUrl")+imgPath+"activation_icon.png");
		context.put("dimensionalCode_bg", properties.getProperty("sinatayUrl")+imgPath+"dimensionalCode_bg.png");
		context.put("dimensionalCode_mb", properties.getProperty("sinatayUrl")+imgPath+"dimensionalCode_mb.jpg");
		context.put("dimensionalCode_mc", properties.getProperty("sinatayUrl")+imgPath+"dimensionalCode_mc.jpg");
		context.put("productImage_1", properties.getProperty("sinatayUrl")+imgPath+"productImage_1.jpg");
		context.put("productImage_2", properties.getProperty("sinatayUrl")+imgPath+"productImage_2.jpg");
		context.put("productImage_3", properties.getProperty("sinatayUrl")+imgPath+"productImage_3.jpg");
		context.put("title", properties.getProperty("sinatayUrl")+imgPath+"title.png");
		//邮件模板参数
		context.put("unsubscribe", properties.getProperty("sinatayUrl")+properties.getProperty("unsubscribe"));
		context.put("index", properties.getProperty("sinatayUrl"));
		context.put("myPolicyBox", properties.getProperty("sinatayUrl")+properties.getProperty("myPolicyBox"));
		context.put("onlineService", properties.getProperty("sinatayUrl")+properties.getProperty("onlineService"));
		context.put("productUrl_1", properties.getProperty("sinatayUrl")+properties.getProperty("productUrl_1"));
		context.put("productUrl_2", properties.getProperty("sinatayUrl")+properties.getProperty("productUrl_2"));
		context.put("productUrl_3", properties.getProperty("sinatayUrl")+properties.getProperty("productUrl_3"));
		context.put("contactUrl", "http://www.sinatay.com/selfservice/contactus.jsp");
		context.put("hotProductList", properties.getProperty("sinatayUrl") + properties.getProperty("hotProductList"));
		context.put("serviceCenter", properties.getProperty("sinatayUrl")+properties.getProperty("serviceCenter"));
		context.put("loginPage", properties.getProperty("sinatayUrl")+ properties.getProperty("loginPage"));
		return context; 
	} 
}
