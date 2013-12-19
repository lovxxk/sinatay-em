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
	
	/**���ܿ���*/
	@Autowired
	private GeFunctionSwitchService geFunctionSwitchService;
	
	/**������ʾ��Ϣ*/
	public String validateMessage;
	
	/**ȡ���������ͣ��ֻ�������*/
	public String resetPwdType;
	
	/**ϵͳ�������ֻ����������֤��*/
//	public String validateCode;
	
	/**
	 * ��֤�û�������ֻ��Ż�����
	 * @return
	 */
	public String inputEmailOrPhone() {
		String emailOrPhone = getRequest().getParameter("emailOrPhone");
		userPersonal = geUserPersonalService.getUserPersonalByEmailOrPhone(emailOrPhone);
		if (userPersonal == null) {
			if (emailOrPhone.indexOf("@") > 0) {
				validateMessage = "�������������������������";
			} else {
				validateMessage = "��������ֻ�����������������";
			}
			return ERROR;
		}
		if (emailOrPhone.indexOf("@") > 0) {
			//���ʼ������䣬����÷�����֤��ӿڣ���ʱ���ع̶�ֵ��1122��
			resetPwdType = "email";
		} else {
			//����֤�����ͻ��ֻ�
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
			//���ʼ������䣬����÷�����֤��ӿڣ���ʱ���ع̶�ֵ��1122��
			validateCode = geUserPersonalService.sendValidateCodeByEmail(userAccount);
			resetPwdType = "email";
		} else {
			//��ѯ���ֻ��ŵ��췢����֤��Ĵ��������
			Integer count = geUserPersonalService.getTransmissionTimesByAccount(userAccount);
			if (count > 50) {
				output("limit");
				return NONE;
			}
			
			//����֤ �����ͻ��ֻ�
			validateCode = getValidateCodeByPhone(userAccount);
			geUserPersonalService.saveTransmissionTimes(userAccount, count + 1);
			resetPwdType = "phone";
		}
		geUserPersonalService.saveMobilePhoneCode(userAccount, validateCode);
		
		output("success");
		
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
		String msgComment = "��л��ʹ��ȡ�����빦�ܣ�ȡ��������֤��Ϊ:" + validateCode + "��";
		
		List<String> params = new ArrayList<String>();
		if(StringUtils.isNotBlank(phoneNum)){
			params.add(validateCode);
			smsSendService.smsSend(true, "3", params, "1", phoneNum, msgComment, sender, null);
		}
		
		return validateCode;
	}
	
	/**ȡ�����룬У���û�¼�����֤���Ƿ�Ϸ�*/
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
	 * �����û�����
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
			validateMessage = "�˻����������޸�ʧ��";
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
