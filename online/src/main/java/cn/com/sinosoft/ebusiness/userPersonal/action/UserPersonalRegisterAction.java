package cn.com.sinosoft.ebusiness.userPersonal.action;

import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.MobilePhoneCode;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.util.encode.Md5;
import cn.com.sinosoft.util.time.DateUtils;

public class UserPersonalRegisterAction extends Struts2Action {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(UserPersonalRegisterAction.class);

	public GeUserPersonal customer;

	public GeUserPersonalService geUserPersonalService;
	
	public String errorMessage;
	
	public SmsSendService smsSendService;
	
	/**功能开关*/
	@Autowired
	private GeFunctionSwitchService geFunctionSwitchService;

	public String userPersonalRegister() {
		customer = null;

		return SUCCESS;
	}

	/**
	 * 保存客户注册时录入的基本信息
	 * 
	 * @return
	 */
	public String saveUserPersonal() {
		try {
			geUserPersonalService.saveUserPersonal(customer, getRequest());
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
		// 当用户是通过邮箱进行注册时，发邮件，激活帐号
		if (StringUtils.isNotBlank(customer.getEmail())) {
			geUserPersonalService.activationAccount(customer);
			return "email";
		}
		//发送注册成功短信
		List<String> params = new ArrayList<String>();
		String phoneNum = customer.getMobilePhone();
		if(StringUtils.isNotBlank(phoneNum)) {
			String tagString = "";
			if(StringUtils.isNotBlank(customer.getUserName())){
				tagString = "尊敬的"+customer.getUserName()+"先生/女士，";
			}
			params.add(tagString);
			params.add(DateUtils.getCurZhCNDate());
			params.add(customer.getUserAccount());
			smsSendService.smsSend(true, "2", params, "1", phoneNum, "", "9005", null);
		}
		
		return "phone";
	}

	public String registerSuccess() {
		customer = geUserPersonalService.getUserPersonalByUserId(getRequest().getParameter("id"));
		return SUCCESS;
	}
	
	public String mobileRegisterSuccess() {
		customer = geUserPersonalService.getUserPersonalByUserId(getRequest().getParameter("id"));
		return SUCCESS;
	}

	/**
	 * test
	 */
	public String userPersonalShow() {

		return SUCCESS;
	}

	/**
	 * 校验验证码
	 * 
	 * @return
	 */
	public String checkAjax() {
		String imageCode = (String) getSession().getAttribute("registerImageCode");
		String inputCode = super.getRequest().getParameter("checkCode") + "";
		
		if (inputCode.equalsIgnoreCase(imageCode)) {
			output("success");
		} else {
			output("error");
		}
		return NONE;
	}

	/**
	 * 校验验证码是否正确
	 * 
	 * @return
	 */
	public void registerCheckPwd() {
		String phonePwd = getRequest().getParameter("phonePwd");
		String customerName = getRequest().getParameter("customerName");
		if (StringUtils.isBlank(phonePwd) || StringUtils.isBlank(customerName)) {
			output("error");
			return;
		}
		MobilePhoneCode mobilePhoneCode = geUserPersonalService.findMobilePhoneCode(customerName);
		if (mobilePhoneCode == null) {
			output("error");
			return;
		}
		if (!phonePwd.equals(mobilePhoneCode.getCode())) {
			output("error");
			return;
		}
		//验证码有效期为30分钟，30分钟之后失效
		if (!new Date().before(DateUtils.addMinutes(mobilePhoneCode.getUpdateTime(), 30))) {
			output("invalid");
			return;
		}
		
		output("success");
	}
	
	/**
	 * 获取用户注册时系统发送的手机验证码
	 */
	public String getUserPersonalPhonePwd() {

		// 获取手机号码
		String phoneNum = getRequest().getParameter("phoneNum");
		Integer count = geUserPersonalService.getTransmissionTimesByAccount(phoneNum);
		if (count > 50) {
			output("limit");
			return NONE;
		}
		
		String validateCode = getValidateCodeByPhone(phoneNum);
		if (StringUtils.isNotBlank(validateCode)) {
			//保存手机号与验证码至数据库中
			geUserPersonalService.saveMobilePhoneCode(phoneNum, validateCode);
			//统计当前时间该手机号发送验证码的次数
			geUserPersonalService.saveTransmissionTimes(phoneNum, count + 1);
			output("success");
		} else {
			output("error");
		}

		return NONE;
	}
	
	public String editUserPersonal() {
		String userId = getRequest().getParameter("id");

		customer = geUserPersonalService.getUserPersonalByUserId(userId);

		return SUCCESS;
	}

	/**
	 * 用户通过邮箱注册后需通过激活连接激活帐号
	 * 
	 * @return
	 */
	public String userPersonalAcivate() {
		try {
			String id = getRequest().getParameter("id");
			String date = new String(Base64.decodeBase64(getRequest().getParameter("date").getBytes()));
			
			Date activeDate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				activeDate = format.parse(date);
			} catch (ParseException e) {
				log.error(e.getCause().getMessage());
				e.printStackTrace();
				return ERROR;
			}
			GeUserPersonal cus = null;
			//正常的激活日期加一天后，大于当前日期，失效
			if (new Date().before(activeDate)) {
				cus = geUserPersonalService.getUserPersonalByUserId(id);
				if (cus == null)
					return ERROR;
				
				cus.setActive(true);
				geUserPersonalService.updateUserPersonal(cus);
			} else {
				errorMessage = "激活连接无效";
				return ERROR;
			}
		} catch (Exception e) {
			errorMessage = "激活连接无效";
			log.error(e.getMessage());
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/** 重新发送激活邮件*/
	public void resetSendEmail() {
		String account = getRequest().getParameter("userAccount");
		GeUserPersonal user = geUserPersonalService.getUserPersonalByEmailOrPhone(account);

		if (user == null) {
			output("error");
			return;
		}
		geUserPersonalService.activationAccount(user);
		output("success");
	}
	
	
	/** 登录失败，立即激活*/
	public String sendEmailToActive() {
		String account = getRequest().getParameter("userAccount");
		GeUserPersonal user = geUserPersonalService.getUserPersonalByEmailOrPhone(account);
		if (user != null && StringUtils.isNotBlank(user.getEmail()))
			geUserPersonalService.activationAccount(user);
		return SUCCESS;
	}
	
	public String getPwdCode() {
		String phonePwd = getRequest().getParameter("phonePwd");
		try {
			if (org.apache.commons.lang.StringUtils.isNotBlank(phonePwd)) {
				if (phonePwd.indexOf("'") >= 0) {
					phonePwd = phonePwd.replaceAll("'", "");
				}
				PrintWriter writer = getResponse().getWriter();
				writer.write(new Md5().toMD5(phonePwd));
				writer.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	

	/**
	 * 根据手机号获取手机验证码
	 */
	public String getValidateCodeByPhone(String phoneNum) {
		//四位数验证码
		String validateCode = org.apache.commons.lang.StringUtils.leftPad(new Random().nextInt(999999) + "", 6, '0');
		//短信发送人
		String sender = "9005";
		//短信内容
		String msgComment = "感谢您注册为信泰保险的会员，注册验证码为:" + validateCode + "。";
		
		List<String> params = new ArrayList<String>();
		if(StringUtils.isNotBlank(phoneNum)){
			params.add(validateCode);
			smsSendService.smsSend(true, "1", params, "1", phoneNum, msgComment, sender, null);
		}
		return validateCode;
	}
	
	public void setCustomer(GeUserPersonal customer) {
		this.customer = customer;
	}

	public void setGeUserPersonalService(
			GeUserPersonalService geUserPersonalService) {
		this.geUserPersonalService = geUserPersonalService;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setSmsSendService(SmsSendService smsSendService) {
		this.smsSendService = smsSendService;
	}
	
	/**
	 * 校验绑定的手机、邮箱是否已存在
	 */
	public void existCustomerByUserAccount(){
		JSONObject jsonObject = new JSONObject();
		try {
			int count = 0;
			String value = super.getRequest().getParameter("value");
			if(StringUtils.isNotBlank(value) && StringUtils.isNotBlank(value)){
				count = geUserPersonalService.checkIsCustomer(value);
				System.out.println("count: "+count);
			}
			jsonObject.put("count", count+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	/***
	 * Ajax 映射给前端
	 * @param parm
	 */
	public void output(String parm) {
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			out.write(parm);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
