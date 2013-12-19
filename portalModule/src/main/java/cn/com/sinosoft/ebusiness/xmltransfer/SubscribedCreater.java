package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

public class SubscribedCreater extends XmlCreater implements
		Creater<Map<String, String>> {

	private final static String sTranRequest = "/TranData/TranRequest";
	private final static String sTranResponse = "/TranData/TranResponse";

	// Ωª“◊¬Î…Ë÷√
	private final static String functionFlag = "ST000044";

	public SubscribedCreater() {
		super();
	}

	@Override
	public Document createXml(Map<String, Object> map) {
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element) doc.selectSingleNode(sTranRequest);
			Element tLCCont = tTranRequest.addElement("LCCont");
			Element tContNo = tLCCont.addElement("ContNo");
			Element tEmail = tLCCont.addElement("Email");
			Element tSubscribeType = tLCCont.addElement("SubscribeType");

			tContNo.addText((String) map.get("contNo"));
			tEmail.addText((String) map.get("email"));
			tSubscribeType.addText((String) map.get("subType"));
		return doc;
	}

	@Override
	public Map<String, String> Xml2Object(Document doc) {

		Map<String, String> result = new HashMap<String, String>();
		result.put("flag", doc.selectSingleNode(sTranResponse+"/Flag").getText());
		result.put("desc", doc.selectSingleNode(sTranResponse+"/Desc").getText());

		return result;

	}

}
