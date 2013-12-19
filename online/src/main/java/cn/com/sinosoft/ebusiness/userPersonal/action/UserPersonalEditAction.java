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

		//��ȡ֤������
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
		
//		//���ݸ���
		getSession().setAttribute("geUserPersonal", customer);
		
		return SUCCESS;
	}
	
	/**��֤�Ƿ�󶨹�����*/
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
			//У��
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
	 * ��ȡ�ֻ���̬��֤��
	 * 
	 * @param mobile
	 * @return
	 */
	public String getPhoneDynamicNumber() {
		// ��ȡ�ֻ�����
		String phoneNum = getRequest().getParameter("phoneNum");

		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			resultMap.put("resultFlag", "T");
			resultMap.put("phonePwd", "1122");
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("resultMessage", "ϵͳ�����쳣��");
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
			if (param.indexOf("@") > 0) {//�û�¼���������
				if(geUserPersonalService.checkIsCustomer(param) > 0){
					output("existsEmail");
					return NONE;
				}
				customer.setEmail(param);
			} else {//�û�¼������ֻ���
				//�ж��ֻ����Ƿ��ѱ�ע��
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
	 * ��ȡ�û�������ʱ��ϵͳ���͵���֤��
	 */
	public String sendCodeByEmail() {
		String email = getRequest().getParameter("email");
		String validateCode = geUserPersonalService.sendCodeByEmail(email);
		
		geUserPersonalService.saveMobilePhoneCode(email, validateCode);
		
		output("success");
		
		return NONE;
	}
	
	/**
	 * ��ȡ�û����ֻ���ʱ��ϵͳ���͵���֤��
	 */
	public String getUserPersonalPhonePwd() {
		// ��ȡ�ֻ�����
		String phoneNum = getRequest().getParameter("phoneNum");
		Integer count = geUserPersonalService.getTransmissionTimesByAccount(phoneNum);
		if (count > 50) {
			output("limit");
			return NONE;
		}
		String validateCode = getValidateCodeByPhone(phoneNum);
		if (StringUtils.isNotBlank(validateCode)) {
			////�����ֻ�������֤�������ݿ���
			geUserPersonalService.saveMobilePhoneCode(phoneNum, validateCode);
			//ͳ�Ƶ�ǰʱ����ֻ��ŷ�����֤��Ĵ���
			geUserPersonalService.saveTransmissionTimes(phoneNum, count + 1);
			output("success");
		} else {
			output("error");
		}

		return NONE;
	}
	

	/**
	 * �����ֻ��Ż�ȡ�ֻ���֤��
	 */
	public String getValidateCodeByPhone(String phoneNum) {
		//��λ����֤��
		String validateCode = org.apache.commons.lang.StringUtils.leftPad(new Random().nextInt(999999) + "", 6, '0');
		//���ŷ�����
		String sender = "9005";
		//��������
		String msgComment = "��л��ע��Ϊ��̩���յĻ�Ա��ע����֤��Ϊ:" + validateCode + "��";
		
		List<String> params = new ArrayList<String>();
		if(StringUtils.isNotBlank(phoneNum)){
			params.add(validateCode);
			smsSendService.smsSend(true, "1", params, "1", phoneNum, msgComment, sender, null);
		}
		return validateCode;
	}
	
	/***
	 * �ʺ���Ϣ,�ֻ��Ż������ַ��,��֤��֤���Ƿ�Ϸ�
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
		//��֤����Ч��Ϊ30���ӣ�30����֮��ʧЧ
		if (!DateUtils.addMinutes(mobilePhoneCode.getUpdateTime(), 30).after(new Date())) {
			output("invalid");
			return NONE;
		}
		
		output("success");
		return NONE;
	}
	
	/**�����˻���Ϣ*/
	public String accountInfo() {
		LoginUserInfo user = SpringSecurityUtils.getCurrentUser();
		if (user != null) {
			customer = geUserPersonalService.getUserPersonalByUserId(user.getCustomer().getUserID());
			
			//�Ƿ���а�ȫ��֤
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
				//ԭ����������ȷ�����ж������� ��ԭ�����Ƿ�һ��
				//�¾�������ͬ
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
				//ԭ�����������
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
	 * У��󶨵��ֻ��������Ƿ��Ѵ���
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
			logger.info("У��󶨵��ֻ��������Ƿ��Ѵ���ʧ�ܣ��쳣��Ϣ��"+StringUtils.exception2String(e));
		}
		super.render(jsonObject.toString(), "application/json;charset=GBK");
	}
	
	
	/***
	 * Ajax ӳ���ǰ��
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
