package cn.com.sinosoft.ebusiness.userPersonal.action;

import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeFunctionSwitchService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.MobilePhoneCode;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.util.cookie.EncryptionUtil;
import cn.com.sinosoft.util.encode.Md5;
import cn.com.sinosoft.util.time.DateUtils;

public class ResetPasswordAction extends Struts2Action {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ResetPasswordAction.class);

	public GeUserPersonal userPersonal;

	public GeUserPersonalService geUserPersonalService;
	
	private SmsSendService smsSendService;
	
	/**功能开关*/
	@Autowired
	private GeFunctionSwitchService geFunctionSwitchService;
	
	/**错误提示信息*/
	public String validateMessage;
	
	/**取回密码类型，手机或邮箱*/
	public String resetPwdType;
	
	/**系统发送至手机或邮箱的验证码*/
//	public String validateCode;
	
	/**
	 * 验证用户输入的手机号或邮箱
	 * @return
	 */
	public String inputEmailOrPhone() {
		String emailOrPhone = getRequest().getParameter("emailOrPhone");
		userPersonal = geUserPersonalService.getUserPersonalByEmailOrPhone(emailOrPhone);
		if (userPersonal == null) {
			if (emailOrPhone.indexOf("@") > 0) {
				validateMessage = "您输入的邮箱有误，请重新输入";
			} else {
				validateMessage = "您输入的手机号有误，请重新输入";
			}
			return ERROR;
		}
		if (emailOrPhone.indexOf("@") > 0) {
			//发邮件至邮箱，需调用发送验证码接口，暂时返回固定值“1122”
			resetPwdType = "email";
		} else {
			//发验证码至客户手机
			resetPwdType = "phone";
		}
		getRequest().setAttribute("userAccount", EncryptionUtil.encrypt(emailOrPhone));
 		return SUCCESS;
	}
	
	public String resetPwdValidateCode() {
		String userAccount = getRequest().getParameter("userAccount");
		if (StringUtils.isBlank(userAccount)) {
			output("paramError");
			return NONE;
		}
		String validateCode = "";
		if (userAccount.indexOf("@") > 0) { 
			//发邮件至邮箱，需调用发送验证码接口，暂时返回固定值“1122”
			validateCode = geUserPersonalService.sendValidateCodeByEmail(userAccount);
			resetPwdType = "email";
		} else {
			//查询该手机号当天发送验证码的次数，如果
			Integer count = geUserPersonalService.getTransmissionTimesByAccount(userAccount);
			if (count > 50) {
				output("limit");
				return NONE;
			}
			
			//发验证 码至客户手机
			validateCode = getValidateCodeByPhone(userAccount);
			geUserPersonalService.saveTransmissionTimes(userAccount, count + 1);
			resetPwdType = "phone";
		}
		geUserPersonalService.saveMobilePhoneCode(userAccount, validateCode);
		
		output("success");
		
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
		String msgComment = "感谢您使用取回密码功能，取回密码验证码为:" + validateCode + "。";
		
		List<String> params = new ArrayList<String>();
		if(StringUtils.isNotBlank(phoneNum)){
			params.add(validateCode);
			smsSendService.smsSend(true, "3", params, "1", phoneNum, msgComment, sender, null);
		}
		
		return validateCode;
	}
	
	/**取回密码，校验用户录入的验证码是否合法*/
	public String checkValidateCode() {
		String userAccount = getRequest().getParameter("userAccount");
		String inputCode = getRequest().getParameter("inputCode");
		if (StringUtils.isBlank(userAccount) || StringUtils.isBlank(inputCode)) {
			output("paramError");
			return NONE;
		}
		userAccount = EncryptionUtil.decrypt(userAccount);
		MobilePhoneCode mobilePhoneCode = geUserPersonalService.findMobilePhoneCode(userAccount);
		if (!inputCode.equals(mobilePhoneCode.getCode())) {
			output("error");
			return null;
		}
		
		if (!new Date().before(DateUtils.addMinutes(mobilePhoneCode.getUpdateTime(), 30))) {
			output("timeout");
			return NONE;
		}
		
		return NONE;
	}
	
	public void findUserAccount() {
		String userAccount = getRequest().getParameter("userAccount");
		if (org.apache.commons.lang.StringUtils.isBlank(userAccount))
			return;
		
		userAccount = EncryptionUtil.decrypt(userAccount);
		
		try {
			PrintWriter out = getResponse().getWriter();
			out.write(userAccount);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public String inputNewPwd() {
		String userAccount = getRequest().getParameter("userAccount");
		getRequest().setAttribute("userAccount", userAccount);
		validateMessage = "";
		return SUCCESS;
	}
	
	/**
	 * 更新用户密码
	 * @return
	 */
	public String updatePwd() {
		String userAccount = getRequest().getParameter("userAccount");
		if (StringUtils.isNotBlank(userAccount)) {
			userAccount = EncryptionUtil.decrypt(userAccount);
		}
		String pwd = getRequest().getParameter("pwd");
		pwd = new Md5().toMD5(pwd);
		userPersonal = geUserPersonalService.getUserPersonalByEmailOrPhone(userAccount);
		if (userPersonal != null) {
			userPersonal.setPwd(pwd);
			geUserPersonalService.updateUserPersonal(userPersonal);
		} else {
			validateMessage = "账户错误，密码修改失败";
			return ERROR;
		}
		
		return SUCCESS;
	}

	public void setUserPersonal(GeUserPersonal userPersonal) {
		this.userPersonal = userPersonal;
	}

	public void setGeUserPersonalService(
			GeUserPersonalService geUserPersonalService) {
		this.geUserPersonalService = geUserPersonalService;
	}

	public void setValidateMessage(String validateMessage) {
		this.validateMessage = validateMessage;
	}

	public void setResetPwdType(String resetPwdType) {
		this.resetPwdType = resetPwdType;
	}

	public void setSmsSendService(SmsSendService smsSendService) {
		this.smsSendService = smsSendService;
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
