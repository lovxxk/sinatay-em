package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import cn.com.sinosoft.ebusiness.infomanage.domain.InsuranceBenefitInfo;
import cn.com.sinosoft.ebusiness.infomanage.domain.ValueInfo;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

public class InsuranceBenefitCreater extends XmlCreater implements
		Creater<Map<String, Object>> {

	private final static String sTranRequest = "/TranData/TranRequest";
	private final static String sTranResponse = "/TranData/TranResponse";

	// ����������
	private final static String functionFlag = "ST001001";

	@Override
	public Document createXml(Map<String, Object> map) {

		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element) doc.selectSingleNode(sTranRequest);
		Element tServiceCount = tTranRequest.addElement("ServiceCount");
		tServiceCount.setText("1");

		Element tServiceInfos = tTranRequest.addElement("ServiceInfos");
		Element tServiceInfo = tServiceInfos.addElement("ServiceInfo");
		tServiceInfo.addAttribute("id", "1");
		Element tType = tServiceInfo.addElement("Type");
		tType.setText("010150");
		Element tContInfos = tServiceInfo.addElement("ContInfos");
		Element tContCount = tContInfos.addElement("ContCount");
		tContCount.setText("1");
		Element tContInfo = tContInfos.addElement("ContInfo");
		tContInfo.addAttribute("id", "1");
		Element tDate = tContInfo.addElement("Date");
		tDate.setText(getCurrentDateNoGap());
		Element tTime = tContInfo.addElement("Time");
		tTime.setText(XmlCreater.getCurrentTimeNoGap());

		Element tContNo = tContInfo.addElement("ContNo");
		tContNo.setText((String) map.get("policyNo"));

		return doc;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> Xml2Object(Document doc) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("flag", doc.selectSingleNode(sTranResponse + "/Flag")
				.getText());
		Element desc = (Element) doc.selectSingleNode(sTranResponse + "/Desc");
		result.put("desc", desc.getText());
		// ����ֽ��ֵ��Ϣ
		List<Node> tPolInfos = doc
				.selectNodes(sTranResponse
						+ "/Desc/ServiceInfos/ServiceInfo/ContInfos/ContInfo/Desc/PolInfos/PolInfo");
		// �ñ��������м�ֵ��Ϣ

		List<InsuranceBenefitInfo> insuranceBenefitInfos = new ArrayList<InsuranceBenefitInfo>();
		// node :polInfo
		for (Node tPolInfo : tPolInfos) {
			// ���һ���ֽ��ֵ��Ϣ
			Element ePolInfo = (Element) tPolInfo;
			// ��ֵ��Ϣ
			InsuranceBenefitInfo insuranceBenefitInfo = new InsuranceBenefitInfo();
			// �������ֺ�
			insuranceBenefitInfo.setPolNo(ePolInfo.element("PolNo").getText());
			// ��ֵtable��Ϣ
			Element tInBnfTable = ePolInfo.element("BenefitInfos");
			// ��ֵ����
			Element tInBnfCount = tInBnfTable.element("BenefitCount");
			String InBnfCount = tInBnfCount.getText();
			if (!InBnfCount.equals("0")) {
				// ����ͷ��Ϣ 1��������Ϣ �������ͷ
				Element tInBnfTableHeads = tInBnfTable.element("BenefitHeads");
				List<Node> tInBnfTableHead = tInBnfTableHeads
						.elements("BenefitHead");
				List<String> inBnfHead = new ArrayList<String>();
				for (Node nInBnfTableHead : tInBnfTableHead) {
					Element eInBnfTableHead = (Element) nInBnfTableHead;
					inBnfHead.add(eInBnfTableHead.getText());
				}
				insuranceBenefitInfo.setHeads(inBnfHead);

				// ����ֵ��Ϣ

				List<Node> tInBnfTableInfos = tInBnfTable
						.elements("BenefitInfo");
				List<ValueInfo> valueInfos = new ArrayList<ValueInfo>();
				for (Node tInBnfTableInfo : tInBnfTableInfos) {

					Element eInBnfTableInfo = (Element) tInBnfTableInfo;
					List<Node> tBenefitValues = eInBnfTableInfo
							.elements("BenefitValue");
					// ÿһ�е�ֵ
					List<String> values = new ArrayList<String>();
					for (Node tBenefitValue : tBenefitValues) {
						Element eBenefitValue = (Element) tBenefitValue;
						values.add(eBenefitValue.getText());
					}
					ValueInfo valueInfo = new ValueInfo();
					valueInfo.setValues(values);
					valueInfos.add(valueInfo);
				}

				insuranceBenefitInfo.setValueInfos(valueInfos);

				insuranceBenefitInfos.add(insuranceBenefitInfo);
			}
		}
		result.put("insuranceBenefitInfos", insuranceBenefitInfos);

		return result;
	}

	/**
	 * �õ���ǰϵͳ���� author: YT
	 * 
	 * @return ��ǰ���ڵĸ�ʽ�ַ���,���ڸ�ʽΪ"yyyyMMdd"
	 */
	private static String getCurrentDateNoGap() {
		GregorianCalendar tGCalendar = new GregorianCalendar();
		StringBuffer tStringBuffer = new StringBuffer(10);
		int sYears = tGCalendar.get(Calendar.YEAR);
		tStringBuffer.append(sYears + "-");
		int sMonths = tGCalendar.get(Calendar.MONTH) + 1;
		if (sMonths < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append(sMonths);
		int sDays = tGCalendar.get(Calendar.DAY_OF_MONTH);
		if (sDays < 10) {
			tStringBuffer.append('0');
		}
		tStringBuffer.append("-" + sDays);
		String tString = tStringBuffer.toString();
		return tString;

	}
}
