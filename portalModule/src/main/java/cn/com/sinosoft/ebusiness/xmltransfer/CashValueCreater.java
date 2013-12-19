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

import cn.com.sinosoft.ebusiness.infomanage.domain.CashValueInfo;
import cn.com.sinosoft.ebusiness.infomanage.domain.ValueInfo;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

public class CashValueCreater extends XmlCreater implements
		Creater<Map<String, Object>> {
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description: �ֽ��ֵ
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-16
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	private static String sTranRequest = "/TranData/TranRequest";
	private static String sTranResponse = "/TranData/TranResponse";

	// ����������
	private static String functionFlag = "ST001001";

	@Override
	public Document createXml(Map<String, Object> map) {
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element) doc.selectSingleNode(sTranRequest);

		Element tServiceInfos = tTranRequest.addElement("ServiceInfos");
		Element tServiceCount = tServiceInfos.addElement("ServiceCount");
		tServiceCount.setText("1");
		Element tServiceInfo = tServiceInfos.addElement("ServiceInfo");
		tServiceInfo.addAttribute("id", "1");
		Element tType = tServiceInfo.addElement("Type");
		tType.setText("010160");
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

	// public static void main(String[] args) {
	// SAXReader reader = new SAXReader();
	// Document doc = null;
	//
	// try {
	// doc = reader
	// .read(new File(
	// "D:/workspace/sintay_shopping/online/src/main/java/cn/com/sinosoft/ebusiness/xmltransfer/1.xml"));
	//
	// } catch (DocumentException e) {
	// e.printStackTrace();
	// }
	// }

	/**
	 * xml ת��Ϊ����
	 * 
	 * */
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

		List<CashValueInfo> cashValueInfos = new ArrayList<CashValueInfo>();
		// node :polInfo
		for (Node tPolInfo : tPolInfos) {
			// ���һ���ֽ��ֵ��Ϣ
			Element ePolInfo = (Element) tPolInfo;
			// ��ֵ��Ϣ
			CashValueInfo cashValueInfo = new CashValueInfo();
			// �������ֺ�
			cashValueInfo.setPolNo(ePolInfo.element("PolNo").getText());
			// ��ֵtable��Ϣ
			Element tCashValueTable = ePolInfo.element("CashValueInfos");
			// ��ֵ����
			Element tCashValueCount = tCashValueTable.element("CashValueCount");
			String cashValueCount = tCashValueCount.getText();
			if (!cashValueCount.equals("0")) {

				// ����ͷ��Ϣ 1��������Ϣ �������ͷ
				Element tCashValueHeads = tCashValueTable
						.element("CashValueHeads");
				List<Node> tCashValueTableHead = tCashValueHeads
						.elements("CashValueHead");
				List<String> cashValueHead = new ArrayList<String>();
				for (Node tCashValueHead : tCashValueTableHead) {
					Element eCashValueHead = (Element) tCashValueHead;
					cashValueHead.add(eCashValueHead.getText());
				}
				cashValueInfo.setHeads(cashValueHead);

				// ����ֵ��Ϣ
				List<Node> tCashValueInfos = tCashValueTable
						.elements("CashValueInfo");
				List<ValueInfo> valueInfos = new ArrayList<ValueInfo>();
				for (Node tCashValueInfo : tCashValueInfos) {

					Element eCashValueInfo = (Element) tCashValueInfo;
					List<Node> tCashValues = eCashValueInfo
							.elements("CashValue");
					// ÿһ�е�ֵ
					List<String> values = new ArrayList<String>();
					for (Node tCashValue : tCashValues) {
						Element eCashValue = (Element) tCashValue;
						values.add(eCashValue.getText());
					}
					ValueInfo valueInfo = new ValueInfo();
					valueInfo.setValues(values);
					valueInfos.add(valueInfo);

				}

				cashValueInfo.setValueInfos(valueInfos);
				cashValueInfos.add(cashValueInfo);
			}
		}
		result.put("cashValueInfos", cashValueInfos);

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
