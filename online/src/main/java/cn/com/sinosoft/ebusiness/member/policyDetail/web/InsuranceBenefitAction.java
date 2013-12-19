package cn.com.sinosoft.ebusiness.member.policyDetail.web;

import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.infomanage.domain.InsuranceBenefitInfo;
import cn.com.sinosoft.ebusiness.infomanage.domain.ValueInfo;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.InsuranceBenefitCreater;
import cn.com.sinosoft.util.string.StringUtils;

public class InsuranceBenefitAction extends Struts2Action {
	private static final long serialVersionUID = 9003091484838238174L;
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description: ��������
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-17
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	// ��־��Ϣ
	private static Logger log = LoggerFactory
			.getLogger(InsuranceBenefitAction.class);
	// ������
	private String policyNo;
	// �������ֺ�
	private String polNo;
	// �ñ���������Ϣ
	private InsuranceBenefitInfo insuranceBenefitInfo;
	// ������Ϣ���Ĵ�����
	@Autowired
	private InsuranceBenefitCreater insuranceBenefitCreater;
	// �ӿڸ�����
	@Autowired
	private WSClientHelper wsclientHelper;
	// ���ķ��سɹ���־
	private String flag;
	// ���ر�������
	private String desc;
	// ��������json�ַ���
	private String bnfJsonStr;

	// ���ر���������Ϣ
	public String inBnf() throws Exception {

		log.info("����inBnf������ѯ��������");
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("valueInfos", ValueInfo.class);

		// �ж��Ƿ�Ϊ��
		if (StringUtils.isNotBlank(bnfJsonStr)) {
			JSONObject jsonObject = JSONObject.fromObject(bnfJsonStr);

			insuranceBenefitInfo = (InsuranceBenefitInfo) JSONObject.toBean(
					jsonObject, InsuranceBenefitInfo.class, classMap);
		} else {
			log.info("��InsuranceBenefitAction��inBnf�����з�������:����ԭ��-->1����������(bnfJsonStr)����Ϊ�գ�2��ϵͳ�쳣��");
			throw new RuntimeException("��������(bnfJsonStr)����Ϊ�գ�");
		}
		return SUCCESS;
	}

	/**
	 * У���Ƿ��и������Ƿ��м�ֵ��Ϣ
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String check() throws Exception {
		log.info("ϵͳ����InsuranceBenefitAction����check������֤�ñ������Ƿ��б������棺policyNo-"
				+ policyNo);
		// �ж��Ƿ��б�����
		if (StringUtils.isNotBlank(policyNo)) {

			Document docRequest;
			Document docResponse;
			String sRequest;
			String sResponse;
			Map<String, Object> result;
			// ���ձ�����Ϣ
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("policyNo", policyNo);
			docRequest = insuranceBenefitCreater.createXml(map);
			sRequest = wsclientHelper.doc2String(docRequest);

			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
			result = insuranceBenefitCreater.Xml2Object(docResponse);
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");

			// ��ü�ֵ����
			List<InsuranceBenefitInfo> insuranceBenefitInfos = (List<InsuranceBenefitInfo>) result
					.get("insuranceBenefitInfos");
			if (insuranceBenefitInfos.size() == 0
					|| insuranceBenefitInfos == null) {
				flag = "0";
				desc = "�޿ɲ鿴���ݣ�����μ����պ�ͬ ��";
			} else {
				// ȡ�������ֵļ�ֵ��Ϣ
				for (InsuranceBenefitInfo tTnsuranceBenefitInfo : insuranceBenefitInfos) {
					if (tTnsuranceBenefitInfo.getPolNo().equals(polNo)) {

						insuranceBenefitInfo = tTnsuranceBenefitInfo;
						insuranceBenefitInfo.setPolicyNo(policyNo);

						JSONObject jsonObject = JSONObject
								.fromObject(insuranceBenefitInfo);
						bnfJsonStr = jsonObject.toString();
					}
				}

				// ����ñ����������ڼ�ֵ��Ϣһ��û�У�����ʾ������Ϣ
				if (insuranceBenefitInfo == null) {
					flag = "0";
					desc = "�޿ɲ鿴���ݣ�����μ����պ�ͬ ��";
				}
			}
		}else{
			log.info("��InsuranceBenefitAction��check�����з�������:����ԭ��-->1,�����Ų���Ϊ�գ�2,ϵͳ�쳣��");
			throw new RuntimeException("������Ϊ�գ�");
		}
		return SUCCESS;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPolNo() {
		return polNo;
	}

	public void setPolNo(String polNo) {
		this.polNo = polNo;
	}

	public InsuranceBenefitInfo getInsuranceBenefitInfo() {
		return insuranceBenefitInfo;
	}

	public void setInsuranceBenefitInfo(
			InsuranceBenefitInfo insuranceBenefitInfo) {
		this.insuranceBenefitInfo = insuranceBenefitInfo;
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

	public String getBnfJsonStr() {
		return bnfJsonStr;
	}

	public void setBnfJsonStr(String bnfJsonStr) {
		this.bnfJsonStr = bnfJsonStr;
	}

}
