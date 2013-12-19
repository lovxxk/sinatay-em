package cn.com.sinosoft.ebusiness.edor.action;

import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.infomanage.domain.PersonInfo;
import cn.com.sinosoft.ebusiness.infomanage.domain.Policy;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.MobilePhoneCode;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.AppntInfoChangeCreater;

public class AppntBaseInfoChangeAction extends Struts2Action {
	private static final long serialVersionUID = 1L;

	@Autowired
	private WSClientHelper wsclientHelper;
	private String policySerialNumber;
	private Policy policy;
	
	@Autowired
	public GeUserPersonalService geUserPersonalService;
	@Autowired
	private AppntInfoChangeCreater appntInfoChangeCreater;
	@Autowired
	private SmsSendService smsSendService;
	
	private String mobile;
	private String checkNum;
	private String checkPhone;
	private String flag;
	private String desc;
	
	public String appntChange(){
		try {
			PersonInfo appnt = policy.getAppnt();
			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,String> result;
			
			map.put("ContNo", policySerialNumber);
			map.put("appnt", appnt);
			docRequest = appntInfoChangeCreater.createXml(map);
			sRequest = wsclientHelper.doc2String(docRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);

			result = appntInfoChangeCreater.Xml2Object(docResponse);

			flag = result.get("flag");
			desc = result.get("desc");
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
			desc = "变更失败，详询全国客服热线400-600-8890";
		}
		//flag = "0";
		
		return "infoChange";
	}
	
	public String sendCheckNum(){
		checkNum = sendPhoneCheckNo(mobile);
		return SUCCESS;
	}
	
	public String sendPhoneCheckNo(String phone) {
		String checkNo = createCheckNo();
		String msgComment = "尊敬的用户，您正在操作保全变更信息，您的手机验证码为：" + checkNo + "。";
		System.out.println(msgComment);
		smsSendService.smsSend(false, "", null, "123", phone, msgComment, "9005", "");
		return checkNo;
	}	
	
	private String createCheckNo(){
		String number = (""+(Math.random()*10000000)).substring(0, 6).replace(".", "8");
		return number;
	}
	
	/**
	 * 功能描述： 手机验证码获得
	 * 
	 * 
	 * @return
	 */
	public String sendPhoneDynamicNumber() throws Exception {
		GeUserPersonal customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		String phoneNum = customer.getMobilePhone();
		//查询当前手机发送验证码是否超过次数
		Integer count = geUserPersonalService.getTransmissionTimesByAccount(phoneNum);
		if (count > 50) {
				flag="1";
				desc="非常抱歉，今天您进行相关网站操作，验证码发送已累计超过50次，请您明天再试。";
			return SUCCESS;
		}		
		// 用户手机号
		String sender = "9005";
		String checkNo = createCheckNo();
		// 短信内容
		String msgComment = "尊敬的用户，您正在操作保全变更信息，您的手机验证码为：" + checkNo + "。该验证码有效期为30分钟，请及时输入该验证码进行保全变更信息。";
		//保存当前用户手机号码到数据库中
		geUserPersonalService.saveMobilePhoneCode(phoneNum, checkNo);
		//请求核心发送验证码
		smsSendService.smsSend(false, "", null, "123", phoneNum, msgComment, sender, "");
		//统计当前时间该手机号发送验证码的次数
		geUserPersonalService.saveTransmissionTimes(phoneNum, count + 1);
		return SUCCESS;
	}
	
	public String checkPhone(){
		//获得请求中的输入的校验码
		String validPhoneNo=getRequest().getParameter("validPhoneNo");
		//获得当前用户的手机号
		GeUserPersonal customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		String phoneNum = customer.getMobilePhone();
		
		
		MobilePhoneCode mobilePhoneCode = geUserPersonalService.findMobilePhoneCode(phoneNum);
		if (mobilePhoneCode == null) {
			checkPhone="error";
			 
		}
		else if (!validPhoneNo.equals(mobilePhoneCode.getCode())) {
			checkPhone="N";
		}else{
			checkPhone="Y";
		}
		
		return SUCCESS;
	}
	
	public String getCheckPhone() {
		return checkPhone;
	}

	public void setCheckPhone(String checkPhone) {
		this.checkPhone = checkPhone;
	}

	public String getPolicySerialNumber() {
		return policySerialNumber;
	}

	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
	}
}
