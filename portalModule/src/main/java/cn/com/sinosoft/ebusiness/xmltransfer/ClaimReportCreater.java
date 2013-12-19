package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.ebusiness.infomanage.domain.PersonInfo;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

/**
 * 理赔报案
 * @author zhuxuyang
 * 2013.09.05
 */
public class ClaimReportCreater extends XmlCreater implements Creater<Map<String,String>>{
	private static String sTranRequest = "/TranData/TranRequest";
	private static String sTranResponse = "/TranData/TranResponse";
	
	//交易码设置
	private static String functionFlag = "ST000040";
	
	public ClaimReportCreater(){
		super();
	}
	
	@Override
	public Document createXml(Map<String,Object> map){
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element)doc.selectSingleNode(sTranRequest);
		Element tLLReport = tTranRequest.addElement("LLReport");
		Element tReportName = tLLReport.addElement("ReportName");
		Element tReportPhone = tLLReport.addElement("ReportPhone");
		Element tReportRelation = tLLReport.addElement("ReportRelation");
		Element tAccidentName = tLLReport.addElement("AccidentName");
		Element tAccidentSex = tLLReport.addElement("AccidentSex");
		Element tAccidentBirth = tLLReport.addElement("AccidentBirth");
		Element tAccidentIdType = tLLReport.addElement("AccidentIdType");
		Element tAccidentId = tLLReport.addElement("AccidentId");
		Element tAccidentDate = tLLReport.addElement("AccidentDate");
		Element tAccidentAddr = tLLReport.addElement("AccidentAddr");
		Element tProvince = tAccidentAddr.addElement("Province");
		Element tCity = tAccidentAddr.addElement("City");
		Element tCounty = tAccidentAddr.addElement("County");
		Element tOther = tAccidentAddr.addElement("Other");
		Element tAccidentReason = tLLReport.addElement("AccidentReason");
		Element tAccidentStatus = tLLReport.addElement("AccidentStatus");
		Element tAccidentDo = tLLReport.addElement("AccidentDo");
		Element tAccidentResult = tLLReport.addElement("AccidentResult");
		Element tHospital = tLLReport.addElement("Hospital");
		Element tDiagnose = tLLReport.addElement("Diagnose");
		Element tClaimType = tLLReport.addElement("ClaimType");

		PersonInfo appnt = (PersonInfo)map.get("appnt");
		String contNo = (String) map.get("ContNo");
		
		tReportName.addText((String)map.get("ReportName"));
		tReportPhone.addText((String)map.get("ReportPhone"));
		tReportRelation.addText((String)map.get("ReportRelation"));
		tAccidentName.addText((String)map.get("AccidentName"));
		tAccidentSex.addText((String)map.get("AccidentSex"));
		tAccidentBirth.addText((String)map.get("AccidentBirth"));
		tAccidentIdType.addText((String)map.get("AccidentIdType"));
		tAccidentId.addText((String)map.get("AccidentId"));
		tAccidentDate.addText((String)map.get("AccidentDate"));
		tProvince.addText((String)map.get("Province"));
		tCity.addText((String)map.get("City"));
		tCounty.addText((String)map.get("County"));
		tOther.addText((String)map.get("Other"));
		tAccidentReason.addText((String)map.get("AccidentReason"));
		tAccidentStatus.addText((String)map.get("AccidentStatus"));
		tAccidentDo.addText((String)map.get("AccidentDo"));
		tAccidentResult.addText((String)map.get("AccidentResult"));
		tHospital.addText((String)map.get("Hospital"));
		tDiagnose.addText((String)map.get("Diagnose"));
		tClaimType.addText((String)map.get("ClaimType"));
		
		return doc;
	}
	
	@Override
	public Map<String,String> Xml2Object(Document doc) {
		
		Map<String,String> result = new HashMap<String,String>();
		result.put("flag", doc.selectSingleNode(sTranResponse + "/Flag").getText());
		result.put("desc", doc.selectSingleNode(sTranResponse + "/Desc").getText());
		
		return result;
	}
	
	public static void main(String[] args){
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("ReportName", "asdf");
		map.put("ReportPhone", "asdf");
		map.put("AccidentName", "asdf");
		map.put("AccidentSex", "asdf");
		map.put("AccidentBirth", "asdf");
		map.put("AccidentIdType", "asdf");
		map.put("AccidentId", "asdf");
		map.put("AccidentDate", "asdf");
		map.put("Province", "asdf");
		map.put("City", "asdf");
		map.put("County", "asdf");
		map.put("Other", "asdf");
		map.put("AccidentReason", "asdf");
		map.put("AccidentStatus", "asdf");
		map.put("AccidentDo", "asdf");
		map.put("Hospital", "asdf");
		map.put("Diagnose", "asdf");
		map.put("ClaimType", "asdf");
		
		ClaimReportCreater creater = new ClaimReportCreater();
		WSClientHelper ws = new WSClientHelper();
		Document doc = creater.createXml(map);
		System.out.println(doc.asXML());
		ws.saveDoc(doc, "D:\\a.xml");
		
	}

}
