package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.ebusiness.infomanage.domain.EmailInfo;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

public class EmailCreater extends XmlCreater implements
		Creater<Map<String, Object>> {
	private final static String sTranRequest = "/TranData/TranRequest";
	private final static String sTranResponse = "/TranData/TranResponse";
	private static Logger log = LoggerFactory.getLogger(EmailCreater.class);
	// 交易码设置
	private final static String functionFlag = "ST000049";

	@SuppressWarnings("unchecked")
	@Override
	public Document createXml(Map<String, Object> map) {
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element) doc.selectSingleNode(sTranRequest);
		Element tQueryData = tTranRequest.addElement("QueryData");
		Element tParams = tQueryData.addElement("Params");
		Element tParamCount = tParams.addElement("ParamCount");
		tParamCount.addText(map.size() + "");
		// 多个绑定的保单号
		List<BindPolicy> policyNos = (List<BindPolicy>) map.get("policys");
		for (BindPolicy policyNo : policyNos) {
			Element tParam = tParams.addElement("Param");
			Element key = tParam.addElement("Key");
			key.setText("ContNo");
			Element value = tParam.addElement("Value");
			value.setText(policyNo.getPolicySerialNumber());

		}
		return doc;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> Xml2Object(Document doc) {

		log.info("----保单详细页面报文转化开始-------");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("flag", doc.selectSingleNode(sTranResponse + "/Flag")
				.getText());
		Element desc = (Element) doc.selectSingleNode(sTranResponse + "/Desc");
		result.put("desc", desc.getText());
		List<EmailInfo> emailInfos = new ArrayList<EmailInfo>();
		if (doc.selectSingleNode(sTranResponse + "/Flag").getText().equals("1")) {
			Element tLCConts = (Element) doc.selectSingleNode(sTranResponse
					+ "/LCConts");
			// 与保全信息
			List<Element> tConts = tLCConts.elements("LCCont");
			for (Element tCont : tConts) {
				EmailInfo emailInfo = new EmailInfo();
				emailInfo.setPolicyNo(tCont.element("ContNo").getText());
				emailInfo.setRiskName(tCont.element("MainPolName").getText());
				emailInfo.setEmail(tCont.element("Email").getText());
				emailInfo.setSubType(tCont.element("NoticeSendType").getText());
				emailInfos.add(emailInfo);
			}
		}
		result.put("emailInfos", emailInfos);

		return result;

	}
}
