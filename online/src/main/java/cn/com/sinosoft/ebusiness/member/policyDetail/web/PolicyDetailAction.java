package cn.com.sinosoft.ebusiness.member.policyDetail.web;

import ins.framework.web.Struts2Action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.infomanage.domain.Edor;
import cn.com.sinosoft.ebusiness.infomanage.domain.Policy;
import cn.com.sinosoft.ebusiness.infomanage.domain.Risk;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.EdorCreater;
import cn.com.sinosoft.ebusiness.xmltransfer.PolicyDetailCreater;
import cn.com.sinosoft.util.string.StringUtils;

public class PolicyDetailAction extends Struts2Action implements Serializable {
	/**
	 * @ProjectName:online
	 * @Package: cn.com.sinosoft.ebusiness.infomanage.action
	 * @ClassName: PolicyDetailAction
	 * @Description: ������ϸҳ����ʾaction
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-5
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	private static final long serialVersionUID = -6811177498600452905L;
	// ��־��Ϣ
	private static Logger log = LoggerFactory
			.getLogger(PolicyDetailAction.class);
	// ������
	private String policyNo;
	// ������ϸ��Ϣ
	private Policy policy;
	// ��ȫ�����Ϣ
	private List<Edor> edors = new ArrayList<Edor>();;
	// ҳ����ʾ���ֱ�ȫ��Ϣ
	// ������Ϣ�ַ���
	private String risksStr;
	// ����ҵ������
	@Autowired
	private PolicyDetailCreater policyDetailCreater;
	@Autowired
	private WSClientHelper wsclientHelper;
	@Autowired
	private EdorCreater edorCreater;
	private String flag;
	private String desc;
	private String userMobile;
	private GeUserPersonal customer;

	private boolean receiveFlag;

	/**
	 * �Ƿ���ʾ�����б�
	 */
	private String showSelect = "0";

	@SuppressWarnings("unchecked")
	public String viewMyPolicyDetail() throws Exception {
		if (StringUtils.isNotBlank(policyNo)) {

			// �����û��ƶ��绰
			customer = (GeUserPersonal) super.getSession().getAttribute(
					"geUserPersonal");
			log.info("�û�����" + customer.getUserName() + "�鿴��������,������Ϊ��"
					+ policyNo);
			userMobile = customer.getMobilePhone();

			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String, Object> result;
			// ���ձ�����Ϣ
			Map<String, Object> map = new HashMap<String, Object>();

			// ����������Ϣ
			map.put("policyNo", policyNo);
			// ��һ�ʱ�������

			docRequest = policyDetailCreater.createXml(map);
			log.info("���������һ��������docRequest......:" + docRequest);
			System.out.println("���������һ��������docRequest......:" + docRequest);

			sRequest = wsclientHelper.doc2String(docRequest);
			log.info("���������һ��������sRequest......:" + sRequest);
			System.out.println("���������һ��������sRequest......:" + sRequest);

			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);

			result = policyDetailCreater.Xml2Object(docResponse);
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");

			if (flag.equals("1")) {
				policy = (Policy) result.get("policy");

				// ������Чʱ����ж�
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				if (sdf.format(new Date()).compareTo(policy.getValiDate()) < 0) {
					receiveFlag = false;
				} else {
					receiveFlag = true;
				}

				// ��ȡ��ȫ�����¼
				// �ڶ��ʱ�������
				docRequest = edorCreater.createXml(map);
				log.info("��������ڶ���������docRequest......:" + docRequest);
				System.out.println("��������ڶ���������docRequest......:" + docRequest);

				sRequest = wsclientHelper.doc2String(docRequest);
				log.info("��������ڶ���������sRequest......:" + sRequest);
				System.out.println("��������ڶ���������sRequest......:" + sRequest);

				sResponse = wsclientHelper.submitGBK(sRequest);
				docResponse = wsclientHelper.string2Doc(sResponse);
				result = edorCreater.Xml2Object(docResponse);

				flag = (String) result.get("flag");
				desc = (String) result.get("desc");

				if (flag.equals("1")) {
					policy.setEdors((List<Edor>) result.get("edors"));
					// ��ñ�ȫ�����Ϣ
					edors = policy.getEdors();
					// ʵ������
					Collections.sort(edors);
					// ��������json ��Ҫ�û���������ʾ
					// �������ͬ���֣�����Ҫ���� ,ȡ���������ظ���ĳ���ֶ�Ԫ��,��ʾ��ֻ����Ч��
					List<Risk> selectRisk = removeListRepeatingObject(policy
							.getRisks());
					if (selectRisk.size() == 0 || selectRisk == null) {
						showSelect = "0";
					} else {
						showSelect = "1";
					}
					JSONArray jsonArray = JSONArray.fromObject(selectRisk);
					risksStr = jsonArray.toString();
				}
			}else{
				log.info("��PolicyDetailAction��viewMyPolicyDetail�����з�������:����ԭ��-->1,�����Ų���Ϊ�գ�2,ϵͳ�쳣��");
				throw new RuntimeException("������Ϊ�գ�");
			}
		}
		return SUCCESS;
	}

	/**
	 * �Ƴ����ּ������ظ�������
	 * 
	 * @param risks
	 * @return
	 */
	private List<Risk> removeListRepeatingObject(List<Risk> risks) {
		Map<String, Risk> risksMap = new HashMap<String, Risk>();
		List<Risk> riskList = new ArrayList<Risk>();
		for (Risk risk : risks) {
			if (risk.getState().equals("��Ч")) {
				risksMap.put(risk.getRiskCode(), risk);
			}
		}
		for (Entry<String, Risk> entry : risksMap.entrySet()) {
			riskList.add(entry.getValue());
		}
		return riskList;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public String getRisksStr() {
		return risksStr;
	}

	public void setRisksStr(String risksStr) {
		this.risksStr = risksStr;
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

	public List<Edor> getEdors() {
		return edors;
	}

	public void setEdors(List<Edor> edors) {
		this.edors = edors;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public String getShowSelect() {
		return showSelect;
	}

	public void setShowSelect(String showSelect) {
		this.showSelect = showSelect;
	}

	public boolean isReceiveFlag() {
		return receiveFlag;
	}

	public void setReceiveFlag(boolean receiveFlag) {
		this.receiveFlag = receiveFlag;
	}

}
