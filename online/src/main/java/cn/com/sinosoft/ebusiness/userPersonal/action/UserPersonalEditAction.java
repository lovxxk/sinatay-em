package cn.com.sinosoft.ebusiness.userPersonal.action;

import ins.framework.web.Struts2Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bindPolicy.service.facade.BindPolicyService;
import cn.com.sinosoft.ebusiness.common.bizConfig.service.facade.GeCodeService;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.service.user.common.domain.LoginUserInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.MobilePhoneCode;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.util.encode.Md5;
import cn.com.sinosoft.util.security.springSecurity.SpringSecurityUtils;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.time.DateUtils;

public class UserPersonalEditAction extends Struts2Action {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(UserPersonalEditAction.class);

	public GeUserPersonal customer;

	public GeUserPersonalService geUserPersonalService;
	
	public GeCodeService geCodeService;
	
	public String identifyTypes;
	
	public BindPolicyService bindPolicyService;
	
	public String errorMessage;

	public String changeResult;
	
	public String newPassword;
	
	public String oldPassword;
	
	public boolean hasBind;
	
	@Autowired
	private SmsSendService smsSendService;
	
	public String editUserPersonal() {
		errorMessage = "";
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		customer = geUserPersonalService.getUserPersonalByUserId(user.getCustomer().getUserID());

		//获取证件类型
		identifyTypes = geUserPersonalService.getIdTypes();
		hasBind = bindPolicyService.hasPolicy(user.getCustomer().getUserID());
		return "edit";
	}

	public String update() {
		errorMessage = "";
		String hobby = customer.getHobby();
		if (StringUtils.isNotBlank(hobby) && hobby.length() >= 30) {
			hobby = hobby.substring(0, 30) + "....";
		}
		customer.setHobby(hobby);
		customer = geUserPersonalService.updateUserPersonal(customer);
		
//		//数据更新
		getSession().setAttribute("geUserPersonal", customer);
		
		return SUCCESS;
	}
	
	/**验证是否绑定过保单*/
	public String checkBindPolicy() {
		GeUserPersonal personal = new GeUserPersonal();
		try {
			String userId = getRequest().getParameter("userId");
			String userName = getRequest().getParameter("userName");
			String identifyType = getRequest().getParameter("identifyType");
			String identifyNumber = getRequest().getParameter("identifyNumber");
			String sex = getRequest().getParameter("sex");
			String birthday = getRequest().getParameter("birthday");
			
			personal.setUserID(userId);
			personal.setUserName(userName);
			personal.setIdentifyType(identifyType);
			personal.setIdentifyNumber(identifyNumber);
			personal.setSex(sex);
			personal.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (geUserPersonalService.checkChanged(personal)) {
			//校验
			if (bindPolicyService.hasPolicy(personal.getUserID())) {
				output("failed");
			} else {
				output("success");
			}
		} else {
			output("success");
		}
		
		return NONE;
	}
	
	/**
	 * 获取手机动态验证码
	 * 
	 * @param mobile
	 * @return
	 */
	public String getPhoneDynamicNumber() {
		// 获取手机号码
		String phoneNum = getRequest().getParameter("phoneNum");

		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			resultMap.put("resultFlag", "T");
			resultMap.put("phonePwd", "1122");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultMessage", "系统处理异常！");
			resultMap.put("resultFlag", "error");
		} finally {
			JSONObject jsonObject = JSONObject.fromObject(resultMap);
			renderText(jsonObject.toString());
		}

		return NONE;
	}

	public String updateSucces() {
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		if (user != null) {
			customer = geUserPersonalService.getUserPersonalByUserId(user.getCustomer().getUserID());
		}

		return SUCCESS;
	}
	
	public String supplyUserPersonal(){
		String param = getRequest().getParameter("param");

		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		if (user != null) {
			customer = geUserPersonalService.getUserPersonalByUserId(user.getCustomer().getUserID());
			if (param.indexOf("@") > 0) {//用户录入的是邮箱
				if(geUserPersonalService.checkIsCustomer(param) > 0){
					output("existsEmail");
					return NONE;
				}
				customer.setEmail(param);
			} else {//用户录入的是手机号
				//判断手机号是否已被注册
				if(geUserPersonalService.checkIsCustomer(param) > 0){
					output("existsmobile");
					return NONE;
				}
				customer.setMobilePhone(param);
			}
			customer = geUserPersonalService.updateUserPersonal(customer);
			getRequest().getSession().setAttribute("geUserPersonal", customer);
		}
		output("success");
		
		return NONE;
	}
	
	/**
	 * 获取用户绑定邮箱时，系统发送的验证码
	 */
	public String sendCodeByEmail() {
		String email = getRequest().getParameter("email");
		String validateCode = geUserPersonalService.sendCodeByEmail(email);
		
		geUserPersonalService.saveMobilePhoneCode(email, validateCode);
		
		output("success");
		
		return NONE;
	}
	
	/**
	 * 获取用户绑定手机号时，系统发送的验证码
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
			////保存手机号与验证码至数据库中
			geUserPersonalService.saveMobilePhoneCode(phoneNum, validateCode);
			//统计当前时间该手机号发送验证码的次数
			geUserPersonalService.saveTransmissionTimes(phoneNum, count + 1);
			output("success");
		} else {
			output("error");
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
	
	/***
	 * 帐号信息,手机号或邮箱地址绑定,验证验证码是否合法
	 * @return
	 */
	public String checkEmailValidateCode() {
		String pwd = getRequest().getParameter("pwd");
		String customerName = getRequest().getParameter("customerName");
		
		if (StringUtils.isBlank(pwd) || StringUtils.isBlank(customerName)) {
			output("paramError");
			return NONE;
		}
		
		MobilePhoneCode mobilePhoneCode = geUserPersonalService.findMobilePhoneCode(customerName);
		if (mobilePhoneCode == null) {
			output("error");
			return NONE;
		}
		
		if (!pwd.equals(mobilePhoneCode.getCode())) { 
			output("error");
			return NONE;
		}
		//验证码有效期为30分钟，30分钟之后失效
		if (!DateUtils.addMinutes(mobilePhoneCode.getUpdateTime(), 30).after(new Date())) {
			output("invalid");
			return NONE;
		}
		
		output("success");
		return NONE;
	}
	
	/**完善账户信息*/
	public String accountInfo() {
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		if (user != null) {
			customer = geUserPersonalService.getUserPersonalByUserId(user.getCustomer().getUserID());
			
			//是否进行安全验证
			boolean bindPolicy = geUserPersonalService.checkBindPolicy(user.getCustomer().getUserID());
			if (!bindPolicy) {
				bindPolicy = geUserPersonalService.findPolicyByOrder(user.getCustomer().getUserID());
			}
			getRequest().setAttribute("bindPolicy", bindPolicy);
		}
		
		return SUCCESS;
	}
	
	public String changePassword(){
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		if (user != null) {
			
			//customer = (GeUserPersonal) getSession().getAttribute("geUserPersonal");
			
			customer = geUserPersonalService.getUserPersonalByUserId(user.getCustomer().getUserID());
			if(new Md5().toMD5(oldPassword).equalsIgnoreCase(customer.getPwd())){
				//原密码输入正确，再判断新密码 与原密码是否一致
				//新旧密码相同
				if(newPassword.equals(oldPassword)){
					changeResult="N";
					return SUCCESS;
				}else{
					customer.setPwd(new Md5().toMD5(newPassword));
					customer.setPwd2(new Md5().toMD5(newPassword));
					geUserPersonalService.updateUserPersonal(customer);
					changeResult="Y";
					return SUCCESS;
				}
				
			}else{
				//原密码输入错误
				changeResult="E";
				return SUCCESS;
			}
		}
		return SUCCESS;
	}
	
	public void setCustomer(GeUserPersonal customer) {
		this.customer = customer;
	}

	public void setGeUserPersonalService(
			GeUserPersonalService geUserPersonalService) {
		this.geUserPersonalService = geUserPersonalService;
	}

	public void setGeCodeService(GeCodeService geCodeService) {
		this.geCodeService = geCodeService;
	}

	public void setIdentifyTypes(String identifyTypes) {
		this.identifyTypes = identifyTypes;
	}

	public void setBindPolicyService(BindPolicyService bindPolicyService) {
		this.bindPolicyService = bindPolicyService;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getChangeResult() {
		return changeResult;
	}

	public void setChangeResult(String changeResult) {
		this.changeResult = changeResult;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public boolean getHasBind() {
		return hasBind;
	}

	public void setHasBind(boolean hasBind) {
		this.hasBind = hasBind;
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
				GeUserPersonal user = geUserPersonalService.getUserPersonalByEmailOrPhone(value);
				if (user == null ) {
					count = 0;
				} else {
					count = 1;
				}
			}
			jsonObject.put("count", count+"");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("校验绑定的手机、邮箱是否已存在失败，异常信息："+StringUtils.exception2String(e));
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
