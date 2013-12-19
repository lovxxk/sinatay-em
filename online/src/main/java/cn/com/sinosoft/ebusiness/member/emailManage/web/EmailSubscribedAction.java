package cn.com.sinosoft.ebusiness.member.emailManage.web;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.jfree.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.businessModule.bindPolicy.service.facade.BindPolicyService;
import cn.com.sinosoft.ebusiness.infomanage.domain.EmailInfo;
import cn.com.sinosoft.ebusiness.sale.service.facade.SmsSendService;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.MobilePhoneCode;
import cn.com.sinosoft.ebusiness.service.user.personal.service.facade.GeUserPersonalService;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.EmailCreater;
import cn.com.sinosoft.ebusiness.xmltransfer.SubscribedCreater;

public class EmailSubscribedAction extends Struts2Action {
	private static final long serialVersionUID = 2393916264264268659L;
	/**
	 * @ProjectName:online
	 * @Package:
	 * @ClassName:
	 * @Description: ���䶩�Ĺ���
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-11
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	// �û���Ϣ
	private GeUserPersonal customer;
	// ���ж��ĵ��Ӻ�������
	private List<EmailInfo> emailInfos = new ArrayList<EmailInfo>();
	// �Ѷ��ĵ��Ӻ�����Ϣ
	private List<EmailInfo> subEmailInfos = new ArrayList<EmailInfo>();
	// ��δ���ĵ��Ӻ�����Ϣ
	private List<EmailInfo> notSubEmailInfos = new ArrayList<EmailInfo>();
	// �ȶ��ĵ����붩��ֽ��
	private List<EmailInfo> paperAndEmailInfos = new ArrayList<EmailInfo>();
	// δ���ĵ��Ӻ�����Ϣ����
	private int notSubCount = 0;
	// �Ѷ��ĵ��Ӻ�����Ϣ����
	private int subCount = 0;
	private String subType;
	// �Ƿ���
	private boolean isSubscribed = false;
	// �����а󶨵ı�����
	private boolean isBindPolicy = false;
	// �Ƿ���Ϣ����
	private boolean isInfoComplete = false;
	// ���ı���json�ַ���
	private String subJsonStr = "";
	// ȡ�����ı���json�ַ���
	private String cancleSubJsonStr = "";
	// �����޸�json�ַ���
	private String changeEmailJsonStr;
	// �ֻ���֤��
	private String checkNo = "";
	@Autowired
	public GeUserPersonalService geUserPersonalService;
	@Autowired
	private SubscribedCreater subscribedCreater;
	@Autowired
	private WSClientHelper wsclientHelper;
	private String flag;
	private String desc;
	// У���ֻ��Ƿ�ɹ� Y�ɹ� Nʧ��
	private String checkPhone;
	// ��ð󶨱�����Ϣ
	@Autowired
	private BindPolicyService bindPolicyService;
	// ���Ӻ���������
	@Autowired
	private EmailCreater emailCreater;
	@Autowired
	private SmsSendService smsSendService;
	private static Logger log = LoggerFactory
			.getLogger(EmailSubscribedAction.class);

	/**
	 * ��ѯ�Ƿ��ж�����Ϣ: 1 �� ��δ�а󶨵ı������б� ��ʾ��ͨ���Ӻ�������ҳ�� ����1����ͨȷ���û���Ϣ���� ��2�� ��ת���û��󶨱���ҳ�� 2
	 * �� ���󶨵ı�������δ���ģ���ʾΪ����ҳ�棬����֮��ˢ��δ����ҳ�� ����ʾ���ı����������� 3 �� ���а󶨵ı����ж�����Ϣ������ʾ������Ϣ��
	 * ����ʾδ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String subscribedEmail() throws Exception {
		customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		log.info("�û�����" +customer.getUserName()+"�鿴���Ӻ���������Ϣ......");
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService
				.findPolicyByQueryRule(queryRule);

		// δ�󶨱��� ��ʾ��ͨ�������
		if (listBindPolicy.size() == 0) {
			isBindPolicy = false;
			return "open";
		} else {
			// ���а󶨱������Ӻ�����Ϣ
			// �����ģ���ѯ��ǰ�û����ж��ġ�δ������Ϣ
			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String, Object> result;
			// ���ձ�����Ϣ
			Map<String, Object> map = new HashMap<String, Object>();
			// ����󶨵ı�������
			map.put("policys", listBindPolicy);

			// �������������
			docRequest = emailCreater.createXml(map);
			log.info("���䶩��������docRequest......:"+docRequest);
			System.out.println("���䶩��������docRequest......:"+docRequest);
			sRequest = wsclientHelper.doc2String(docRequest);
			log.info("���䶩��������sRequest......:"+sRequest);
			System.out.println("���䶩��������sRequest......:"+sRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
			result = emailCreater.Xml2Object(docResponse);
			// ���ؽ����
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");
			
			if (flag.equals("1")) {
				emailInfos = (List<EmailInfo>) result.get("emailInfos");
				// ������ʾ���Ļ���δ����ҳ�� δ����ҳ�棺û�ж���
				for (EmailInfo emailInfo : emailInfos) {
					if (emailInfo.getSubType().equals("01")
							|| emailInfo.getSubType().equals("03")) {
						// �Ѷ���
						subEmailInfos.add(emailInfo);
					} else {// δ����
						notSubEmailInfos.add(emailInfo);
					}
				}
				// ������ʾ���� 1:���Ľ��� ��ʾΪ������Ŀ 0 ����ʾ������Ϣ
				if (subEmailInfos.size() == 0) {
					// δ���� notSubscribed ����Ҫ��ʾ���� ��ʾ������Ŀ
					System.out.println(notSubEmailInfos.size());
					notSubCount = notSubEmailInfos.size();
					return "notSubscribed";
				} else {
					// ��ʾ������Ϣ ��ʾ�û�δ��������
					notSubCount = notSubEmailInfos.size();
					return "subscribed";
				}
			} else {
				log.info("ϵͳ����.....:�������ĳ���");
				throw new Exception("ϵͳ����.....:�������ĳ���");
			}

		}
	}

	@SuppressWarnings("unchecked")
	public String notSubscribe() {

		try {
			customer = (GeUserPersonal) super.getSession().getAttribute(
					"geUserPersonal");

			// ��ø��û��°󶨵ı���
			QueryRule queryRule = QueryRule.getInstance();
			queryRule.addEqual("CUSTOMERID", customer.getUserID());
			List<BindPolicy> listBindPolicy = bindPolicyService
					.findPolicyByQueryRule(queryRule);
			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String, Object> result;
			// ���ձ�����Ϣ
			Map<String, Object> map = new HashMap<String, Object>();
			// ����󶨵ı�������
			map.put("policys", listBindPolicy);
			docRequest = emailCreater.createXml(map);
			sRequest = wsclientHelper.doc2String(docRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);

			docResponse = wsclientHelper.string2Doc(sResponse);
			result = emailCreater.Xml2Object(docResponse);
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");
			emailInfos = (List<EmailInfo>) result.get("emailInfos");
			if (emailInfos.size() != 0) {
				// ������ʾ���Ļ���δ����ҳ�� δ����ҳ�棺û�ж���
				for (EmailInfo emailInfo : emailInfos) {
					if (emailInfo.getSubType().equals("01")
							|| emailInfo.getSubType().equals("03")) {
						// �Ѷ���
						subEmailInfos.add(emailInfo);
					} else {// δ����
						notSubEmailInfos.add(emailInfo);
					}
				}
				// ��ʾδ������Ϣ ��ʾ�û���������
				notSubCount = notSubEmailInfos.size();
				subCount = subEmailInfos.size();

				return SUCCESS;
			} else {
				throw new Exception("ϵͳ����.....:���û����а󶨵ı����޶�����Ϣ");
			}
		} catch (Exception e) {
			Log.info("ϵͳ����......��" + e.getMessage());
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * �û���Ϣ�Ƿ����� 1,���� �Ա� �������� ֤������ ֤������ ��֤5Ҫ��
	 * 
	 */
	public String checkUserInfoComplete() {

		customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		if (customer.getBirthday() == null || customer.getBirthday().equals("")
				|| customer.getSex() == null || customer.getSex().equals("")
				|| customer.getUserName() == null
				|| customer.getUserName().equals("")
				|| customer.getIdentifyType() == null
				|| customer.getIdentifyType().equals("")
				|| customer.getIdentifyNumber() == null
				|| customer.getIdentifyNumber().equals("")) {
			isInfoComplete = false;

			return SUCCESS;

		} else {
			isInfoComplete = true;

			return SUCCESS;
		}
	}

	/**
	 * ���ܵ�������02��ֽ��֪ͨ�� 04���������� ��2�з�ʽδ���ĵ��Ӻ����ı������� 02->01 04->01
	 * 
	 * @author jack_xiao
	 * @return
	 * @throws Exception
	 */
	public String subscribed() throws Exception {
		// ���Ķ���
		EmailInfo emaillInfo = null;
		JSONArray jsonArray = new JSONArray(subJsonStr);
		int iSize = jsonArray.length();

		System.out.println("Size:" + iSize);
		for (int i = 0; i < iSize; i++) {
			emaillInfo = new EmailInfo();
			// ���һ��json����
			JSONObject jsonObj = jsonArray.getJSONObject(i);

			emaillInfo.setPolicyNo(jsonObj.getString("policyNo"));
			emaillInfo.setEmail(jsonObj.getString("email"));
			// ���ö��ķ�ʽ
			emaillInfo.setSubType("01");
			// ÿ���� ����һ��������
			subReqToResByPars(emaillInfo);

		}

		return SUCCESS;
	}

	/**
	 * ���������� ȡ������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String cancelEmail() throws Exception {
		// ���Ķ���
		EmailInfo emaillInfo = null;
		JSONObject jsonObj = new JSONObject(cancleSubJsonStr);
		emaillInfo = new EmailInfo();
		// ���һ��json����
		emaillInfo.setPolicyNo(jsonObj.getString("policyNo"));
		emaillInfo.setEmail(jsonObj.getString("email"));
		// ���ö��ķ�ʽ
		emaillInfo.setSubType("0" + jsonObj.getString("subType"));
		// ÿ���� ����һ��������
		subReqToResByPars(emaillInfo);

		return SUCCESS;

	}

	/**
	 * ���������� �����޸�
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	public String changeEmail() throws Exception {
		// ���Ķ���
		EmailInfo emaillInfo = null;
		JSONObject jsonObj = new JSONObject(changeEmailJsonStr);
		emaillInfo = new EmailInfo();
		// ���һ��json����
		emaillInfo.setPolicyNo(jsonObj.getString("policyNo"));
		emaillInfo.setEmail(jsonObj.getString("email"));
		// ���ö��ķ�ʽ
		emaillInfo.setSubType("0" + jsonObj.getString("subType"));
		// ÿ���� ����һ��������
		subReqToResByPars(emaillInfo);

		return SUCCESS;

	}

	/**
	 * ���������� �ֻ���֤����
	 * 
	 * 
	 * @return
	 */
	public String sendPhoneDynamicNumber() throws Exception {
		customer = (GeUserPersonal) super.getSession().getAttribute(
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
		checkNo = createCheckNo();
		// ��������
		String msgComment = "�𾴵��û����������޸������ַ����֤��Ϊ:" + checkNo + "������֤����Ч��Ϊ30���ӣ��뼰ʱ�������֤����������޸ġ�";
		//���浱ǰ�û��ֻ����뵽���ݿ���
		geUserPersonalService.saveMobilePhoneCode(phoneNum, checkNo);
		//������ķ�����֤��
		smsSendService.smsSend(false, "", null, "123", phoneNum, msgComment,
				sender, "");
		//ͳ�Ƶ�ǰʱ����ֻ��ŷ�����֤��Ĵ���
		geUserPersonalService.saveTransmissionTimes(phoneNum, count + 1);
		return SUCCESS;
	}
	/**
	 * ���ܣ�У���ֻ��Ƿ�������ȷ
	 * 
	 */
	public String checkPhone(){
		log.info("�ֻ���֤��У��....");
		//��������е������У����
		String validPhoneNo=getRequest().getParameter("validPhoneNo");
		//��õ�ǰ�û����ֻ���
		customer = (GeUserPersonal) super.getSession().getAttribute(
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
	/**
	 * ���������
	 * 
	 * @return
	 */
	private String createCheckNo() {
		String number = ("" + (Math.random() * 10000000)).substring(0, 6)
				.replace(".", "8");
		return number;
	}

	private void subReqToResByPars(EmailInfo emailInfo) throws Exception {
		Document docRequest;
		Document docResponse;
		String sRequest;
		String sResponse;
		Map<String, String> result;
		// ��������������Ӧ
		Map<String, Object> requestParms = new HashMap<String, Object>();

		requestParms.put("contNo", emailInfo.getPolicyNo());
		requestParms.put("email", emailInfo.getEmail());
		requestParms.put("subType", emailInfo.getSubType());

		// ����������Dom����
		docRequest = subscribedCreater.createXml(requestParms);
		sRequest = wsclientHelper.doc2String(docRequest);
		sResponse = wsclientHelper.submitGBK(sRequest);
		docResponse = wsclientHelper.string2Doc(sResponse);

		result = subscribedCreater.Xml2Object(docResponse);

		flag = result.get("flag");
		desc = result.get("desc");
	}

	public boolean getIsSubscribed() {
		return isSubscribed;
	}

	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public boolean getIsBindPolicy() {
		return isBindPolicy;
	}

	public void setBindPolicy(boolean isBindPolicy) {
		this.isBindPolicy = isBindPolicy;
	}

	public boolean getIsInfoComplete() {
		return isInfoComplete;
	}

	public void setInfoComplete(boolean isInfoComplete) {
		this.isInfoComplete = isInfoComplete;
	}



	public List<EmailInfo> getEmailInfos() {
		return emailInfos;
	}

	public void setEmailInfos(List<EmailInfo> emailInfos) {
		this.emailInfos = emailInfos;
	}

	public List<EmailInfo> getSubEmailInfos() {
		return subEmailInfos;
	}

	public void setSubEmailInfos(List<EmailInfo> subEmailInfos) {
		this.subEmailInfos = subEmailInfos;
	}

	public List<EmailInfo> getNotSubEmailInfos() {
		return notSubEmailInfos;
	}

	public void setNotSubEmailInfos(List<EmailInfo> notSubEmailInfos) {
		this.notSubEmailInfos = notSubEmailInfos;
	}

	public WSClientHelper getWsclientHelper() {
		return wsclientHelper;
	}

	public void setWsclientHelper(WSClientHelper wsclientHelper) {
		this.wsclientHelper = wsclientHelper;
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

	public int getNotSubCount() {
		return notSubCount;
	}

	public void setNotSubCount(int notSubCount) {
		this.notSubCount = notSubCount;
	}

	public String getCancleSubJsonStr() {
		return cancleSubJsonStr;
	}

	public void setCancleSubJsonStr(String cancleSubJsonStr) {
		this.cancleSubJsonStr = cancleSubJsonStr;
	}

	public String getSubJsonStr() {
		return subJsonStr;
	}

	public void setSubJsonStr(String subJsonStr) {
		this.subJsonStr = subJsonStr;
	}

	public int getSubCount() {
		return subCount;
	}

	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public List<EmailInfo> getPaperAndEmailInfos() {
		return paperAndEmailInfos;
	}

	public void setPaperAndEmailInfos(List<EmailInfo> paperAndEmailInfos) {
		this.paperAndEmailInfos = paperAndEmailInfos;
	}

	public String getChangeEmailJsonStr() {
		return changeEmailJsonStr;
	}

	public void setChangeEmailJsonStr(String changeEmailJsonStr) {
		this.changeEmailJsonStr = changeEmailJsonStr;
	}

	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}

	public String getCheckPhone() {
		return checkPhone;
	}

	public void setCheckPhone(String checkPhone) {
		this.checkPhone = checkPhone;
	}
    
}
