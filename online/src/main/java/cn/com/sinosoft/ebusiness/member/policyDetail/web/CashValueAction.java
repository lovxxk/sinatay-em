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

import cn.com.sinosoft.ebusiness.infomanage.domain.CashValueInfo;
import cn.com.sinosoft.ebusiness.infomanage.domain.ValueInfo;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.CashValueCreater;
import cn.com.sinosoft.util.string.StringUtils;

public class CashValueAction extends Struts2Action {

	private static final long serialVersionUID = -4000863691461355866L;
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description:
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-17
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	// ��־��Ϣ
	private static Logger log = LoggerFactory.getLogger(CashValueAction.class);
	// ������
	private String policyNo;
	// �������ֺ�
	private String polNo;
	// �ñ������ּ�ֵ��Ϣ
	private CashValueInfo cashValueInfo;
	@Autowired
	private CashValueCreater cashValueCreater;
	@Autowired
	private WSClientHelper wsclientHelper;
	private String flag;
	private String desc;
	private String jsonCashValueInfoStr;

	/**
	 * ҵ�񣺻�ȡ�ֽ��ֵjson�ַ���ת��Ϊ�ֽ��ֵ����
	 * @return
	 * @throws Exception
	 */
	public String cashValue() throws Exception {
		// �ж��Ƿ�Ϊ��
		if (StringUtils.isNotBlank(jsonCashValueInfoStr)) {

			log.info("ϵͳ����CashValueAction����cashValue������ѯ�ֽ��ֵ");
			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
			classMap.put("valueInfos", ValueInfo.class);

			JSONObject jsonObject = JSONObject.fromObject(jsonCashValueInfoStr);
			cashValueInfo = (CashValueInfo) JSONObject.toBean(jsonObject,
					CashValueInfo.class, classMap);
			
		}else{
			
			log.info("��CashValueAction��inBnf�����з�������:����ԭ��-->1���ֽ��ֵ(jsonCashValueInfoStr)����Ϊ�գ�2��ϵͳ�쳣��");
			throw new RuntimeException("��������(bnfJsonStr)����Ϊ�գ�");
			
		}
		return SUCCESS;

	}

	/**
	 * ҵ��У���Ƿ��и������Ƿ��м�ֵ��Ϣ
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String check() throws Exception {
		log.info("ϵͳ����CashValueAction����check������֤�ñ������Ƿ����ֽ��ֵ��policyNo-"
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
			docRequest = cashValueCreater.createXml(map);
			sRequest = wsclientHelper.doc2String(docRequest);
			sResponse = wsclientHelper.submitGBK(sRequest);
			docResponse = wsclientHelper.string2Doc(sResponse);
			result = cashValueCreater.Xml2Object(docResponse);
			
			flag = (String) result.get("flag");
			desc = (String) result.get("desc");
			
			// ��ü�ֵ��Ϣ
			List<CashValueInfo> cashValueInfos = (List<CashValueInfo>) result
					.get("cashValueInfos");
			if (cashValueInfos.size() == 0 || cashValueInfos == null) {
				flag = "0";
				desc = "�޿ɲ鿴���ݣ�����μ����պ�ͬ ��";
			} else {
				// ȡ�������ֵļ�ֵ��Ϣ
				for (CashValueInfo cashInfo : cashValueInfos) {
					if (cashInfo.getPolNo().equals(polNo)) {
						cashValueInfo = cashInfo;
						cashValueInfo.setPolicyNo(policyNo);

						JSONObject jsonObject = JSONObject
								.fromObject(cashValueInfo);
						jsonCashValueInfoStr = jsonObject.toString();
					}
				}
				// ����ñ����������ڼ�ֵ��Ϣһ��û�У�����ʾ������Ϣ
				if (cashValueInfo == null) {
					flag = "0";
					desc = "�޿ɲ鿴���ݣ�����μ����պ�ͬ ��";
				}
			}
		} else {
			log.info("��CashValueAction��check�����з�������:����ԭ��-->1,�����Ų���Ϊ�գ�2,ϵͳ�쳣��");
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

	public CashValueInfo getCashValueInfo() {
		return cashValueInfo;
	}

	public void setCashValueInfo(CashValueInfo cashValueInfo) {
		this.cashValueInfo = cashValueInfo;
	}

	public String getJsonCashValueInfoStr() {
		return jsonCashValueInfoStr;
	}

	public void setJsonCashValueInfoStr(String jsonCashValueInfoStr) {
		this.jsonCashValueInfoStr = jsonCashValueInfoStr;
	}

}
