package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.ebusiness.infomanage.domain.PersonInfo;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

/**
 * 投保人基本信息变更
 * @author zhuxuyang
 * 2013.09.04
 */
public class AppntInfoChangeCreater extends XmlCreater implements Creater<Map<String,String>>{
	private static String sTranRequest = "/TranData/TranRequest";
	private static String sTranResponse = "/TranData/TranResponse";
	
	//交易码设置
	private static String functionFlag = "ST000041";
	
	public AppntInfoChangeCreater(){
		super();
	}
	
	@Override
	public Document createXml(Map<String,Object> map){
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element)doc.selectSingleNode(sTranRequest);
		Element tLCCont = tTranRequest.addElement("LCCont");
		Element tContNo = tLCCont.addElement("ContNo");
		Element tLCAppnt = tLCCont.addElement("LCAppnt");
		
		Element tAppntName = tLCAppnt.addElement("AppntName");
		Element tAppntSex = tLCAppnt.addElement("AppntSex");
		Element tAppntBirthday = tLCAppnt.addElement("AppntBirthday");
		Element tAppntIDType = tLCAppnt.addElement("AppntIDType");
		Element tAppntIDNo = tLCAppnt.addElement("AppntIDNo");
		Element tMarriage = tLCAppnt.addElement("Marriage");
		Element tNationality = tLCAppnt.addElement("Nationality");
		Element tLicenseType = tLCAppnt.addElement("LicenseType");
		Element tJobCode = tLCAppnt.addElement("JobCode");
		Element tProvince = tLCAppnt.addElement("Province");
		Element tCity = tLCAppnt.addElement("City");
		Element tCounty = tLCAppnt.addElement("County");
		Element tHomeAddress = tLCAppnt.addElement("HomeAddress");
		Element tHomeZipCode = tLCAppnt.addElement("HomeZipCode");
		Element tAppntMobile = tLCAppnt.addElement("AppntMobile");
		Element tAppntOfficePhone = tLCAppnt.addElement("AppntOfficePhone");
		Element tAppntPhone = tLCAppnt.addElement("AppntPhone");
		Element tAppntFax = tLCAppnt.addElement("AppntFax");
		Element tEmail = tLCAppnt.addElement("Email");
		Element tGrpName = tLCAppnt.addElement("GrpName");

		PersonInfo appnt = (PersonInfo)map.get("appnt");
		String contNo = (String) map.get("ContNo");
		
		tContNo.addText(contNo);
		tAppntName.addText(appnt.getName());
		tAppntSex.addText(appnt.getSex());
		tAppntBirthday.addText(appnt.getBirthDay());
		tAppntIDType.addText(appnt.getIdType());
		tAppntIDNo.addText(appnt.getIdNo());
		tMarriage.addText(appnt.getMarriage());
		tNationality.addText(appnt.getNationality());
		tLicenseType.addText(appnt.getLicenseType());
		tJobCode.addText(appnt.getJobCode());
		tProvince.addText(appnt.getProvince());
		tCity.addText(appnt.getCity());
		tCounty.addText(appnt.getCounty());
		tHomeAddress.addText(appnt.getHomeAddress());
		tHomeZipCode.addText(appnt.getHomeZipCode());
		tAppntMobile.addText(appnt.getMobile());
		tAppntOfficePhone.addText(appnt.getOfficePhone());
		tAppntPhone.addText(appnt.getPhone());
		tAppntFax.addText(appnt.getFax());
		tEmail.addText(appnt.getEmail());
		tGrpName.addText(appnt.getGrpName());
		
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
	}

}
