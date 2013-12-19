package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

public class DownloadNoticeCreater extends XmlCreater implements
		Creater<Map<String, String>> {
	private final static String sTranRequest = "/TranData/TranRequest";
	private final static String sTranResponse = "/TranData/TranResponse";

	// Ωª“◊¬Î…Ë÷√
	private final static String functionFlag = "ST000046";

	@Override
	public Document createXml(Map<String, Object> map) {
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element) doc.selectSingleNode(sTranRequest);
		Element tLCCont = tTranRequest.addElement("LCCont");
		Element tContNo = tLCCont.addElement("ContNo");
		tContNo.addText((String) map.get("policyNo"));
		Element tPrtSeq = tLCCont.addElement("PrtSeq");
		tPrtSeq.addText((String) map.get("noticeNo"));
		Element tStartDate = tLCCont.addElement("StartDate");
		tStartDate.addText((String) map.get("startDate"));
		Element tEndDate = tLCCont.addElement("EndDate");
		tEndDate.addText((String) map.get("endDate"));
		return doc;
	}

	@Override
	public Map<String, String> Xml2Object(Document doc) {
		Map<String, String> result = new HashMap<String, String>();
		result.put("flag", doc.selectSingleNode(sTranResponse+"/Flag").getText());
		result.put("desc", doc.selectSingleNode(sTranResponse+"/Desc").getText());
		result.put("url", doc.selectSingleNode(sTranResponse+"/Url").getText());

		return result;
	}

}
