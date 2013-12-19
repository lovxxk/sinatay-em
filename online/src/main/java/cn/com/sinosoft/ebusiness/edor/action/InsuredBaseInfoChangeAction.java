package cn.com.sinosoft.ebusiness.edor.action;

import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.infomanage.domain.PersonInfo;
import cn.com.sinosoft.ebusiness.infomanage.domain.Policy;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.InsuredInfoChangeCreater;

public class InsuredBaseInfoChangeAction extends Struts2Action {
	private static final long serialVersionUID = 1L;

	@Autowired
	private WSClientHelper wsclientHelper;
	@Autowired
	private InsuredInfoChangeCreater insuredInfoChangeCreater;
	@Autowired
	private SmsSendService smsSendService;
	private String policySerialNumber;
	private Policy policy;
	
	private String mobile;
	private String checkNum;
	private String flag;
	private String desc;
	public String insuredChange(){
		try {
			PersonInfo insured = policy.getInsured();
			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,String> result;
			
			map.put("ContNo", policySerialNumber);
			map.put("insured", insured);
			docRequest = insuredInfoChangeCreater.createXml(map);
			sRequest = wsclientHelper.doc2String(docRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);

			result = insuredInfoChangeCreater.Xml2Object(docResponse);

			flag = result.get("flag");
			desc = result.get("desc");
		} catch (Exception e) {
			e.printStackTrace();
			flag = "0";
			desc = "变更失败，详询全国客服热线400-600-8890";
		}
		//flag = "1";
		
		return "infoChange";
	}
	
	public String sendCheckNum(){
		checkNum = sendPhoneCheckNo(mobile);
		return SUCCESS;
	}
	
	public String sendPhoneCheckNo(String phone) {
		String checkNo = createCheckNo();
		String msgComment = "尊敬的用户，您正在操作保全变更信息，您的手机验证码为：" + checkNo + "。";
		smsSendService.smsSend(false, "", null, "123", phone, msgComment, "9005", "");
		return checkNo;
	}	
	
	private String createCheckNo(){
		String number = (""+(Math.random()*10000000)).substring(0, 6).replace(".", "8");
		return number;
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
