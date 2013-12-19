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
			desc = "���ʧ�ܣ���ѯȫ���ͷ�����400-600-8890";
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
		String msgComment = "�𾴵��û��������ڲ�����ȫ�����Ϣ�������ֻ���֤��Ϊ��" + checkNo + "��";
		System.out.println(msgComment);
		smsSendService.smsSend(false, "", null, "123", phone, msgComment, "9005", "");
		return checkNo;
	}	
	
	private String createCheckNo(){
		String number = (""+(Math.random()*10000000)).substring(0, 6).replace(".", "8");
		return number;
	}
	
	/**
	 * ���������� �ֻ���֤����
	 * 
	 * 
	 * @return
	 */
	public String sendPhoneDynamicNumber() throws Exception {
		GeUserPersonal customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		String phoneNum = customer.getMobilePhone();
		//��ѯ��ǰ�ֻ�������֤���Ƿ񳬹�����
		Integer count = geUserPersonalService.getTransmissionTimesByAccount(phoneNum);
		if (count > 50) {
				flag="1";
				desc="�ǳ���Ǹ�����������������վ��������֤�뷢�����ۼƳ���50�Σ������������ԡ�";
			return SUCCESS;
		}		
		// �û��ֻ���
		String sender = "9005";
		String checkNo = createCheckNo();
		// ��������
		String msgComment = "�𾴵��û��������ڲ�����ȫ�����Ϣ�������ֻ���֤��Ϊ��" + checkNo + "������֤����Ч��Ϊ30���ӣ��뼰ʱ�������֤����б�ȫ�����Ϣ��";
		//���浱ǰ�û��ֻ����뵽���ݿ���
		geUserPersonalService.saveMobilePhoneCode(phoneNum, checkNo);
		//������ķ�����֤��
		smsSendService.smsSend(false, "", null, "123", phoneNum, msgComment, sender, "");
		//ͳ�Ƶ�ǰʱ����ֻ��ŷ�����֤��Ĵ���
		geUserPersonalService.saveTransmissionTimes(phoneNum, count + 1);
		return SUCCESS;
	}
	
	public String checkPhone(){
		//��������е������У����
		String validPhoneNo=getRequest().getParameter("validPhoneNo");
		//��õ�ǰ�û����ֻ���
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
