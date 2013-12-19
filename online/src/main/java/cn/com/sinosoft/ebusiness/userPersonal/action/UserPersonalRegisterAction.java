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
	
	/**���ܿ���*/
	@Autowired
	private GeFunctionSwitchService geFunctionSwitchService;

	public String userPersonalRegister() {
		customer = null;

		return SUCCESS;
	}

	/**
	 * ����ͻ�ע��ʱ¼��Ļ�����Ϣ
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
		
		// ���û���ͨ���������ע��ʱ�����ʼ��������ʺ�
		if (StringUtils.isNotBlank(customer.getEmail())) {
			geUserPersonalService.activationAccount(customer);
			return "email";
		}
		//����ע��ɹ�����
		List<String> params = new ArrayList<String>();
		String phoneNum = customer.getMobilePhone();
		if(StringUtils.isNotBlank(phoneNum)) {
			String tagString = "";
			if(StringUtils.isNotBlank(customer.getUserName())){
				tagString = "�𾴵�"+customer.getUserName()+"����/Ůʿ��";
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
	 * У����֤��
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
	 * У����֤���Ƿ���ȷ
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
		//��֤����Ч��Ϊ30���ӣ�30����֮��ʧЧ
		if (!new Date().before(DateUtils.addMinutes(mobilePhoneCode.getUpdateTime(), 30))) {
			output("invalid");
			return;
		}
		
		output("success");
	}
	
	/**
	 * ��ȡ�û�ע��ʱϵͳ���͵��ֻ���֤��
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
			//�����ֻ�������֤�������ݿ���
			geUserPersonalService.saveMobilePhoneCode(phoneNum, validateCode);
			//ͳ�Ƶ�ǰʱ����ֻ��ŷ�����֤��Ĵ���
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
	 * �û�ͨ������ע�����ͨ���������Ӽ����ʺ�
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
			//�����ļ������ڼ�һ��󣬴��ڵ�ǰ���ڣ�ʧЧ
			if (new Date().before(activeDate)) {
				cus = geUserPersonalService.getUserPersonalByUserId(id);
				if (cus == null)
					return ERROR;
				
				cus.setActive(true);
				geUserPersonalService.updateUserPersonal(cus);
			} else {
				errorMessage = "����������Ч";
				return ERROR;
			}
		} catch (Exception e) {
			errorMessage = "����������Ч";
			log.error(e.getMessage());
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/** ���·��ͼ����ʼ�*/
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
	
	
	/** ��¼ʧ�ܣ���������*/
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
	 * У��󶨵��ֻ��������Ƿ��Ѵ���
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
