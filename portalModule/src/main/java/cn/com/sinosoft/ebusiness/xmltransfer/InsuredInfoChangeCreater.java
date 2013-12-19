package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.ebusiness.infomanage.domain.PersonInfo;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

/**
 * 被保人基本信息变更
 * @author zhuxuyang
 * 2013.09.04
 */
public class InsuredInfoChangeCreater extends XmlCreater implements Creater<Map<String,String>>{
	private static String sTranRequest = "/TranData/TranRequest";
	private static String sTranResponse = "/TranData/TranResponse";
	
	//交易码设置
	private static String functionFlag = "ST000042";
	
	public InsuredInfoChangeCreater(){
		super();
	}
	
	@Override
	public Document createXml(Map<String,Object> map){
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element)doc.selectSingleNode(sTranRequest);
		Element tLCCont = tTranRequest.addElement("LCCont");
		Element tContNo = tLCCont.addElement("ContNo");
		Element tLCInsureds = tLCCont.addElement("LCInsureds");
		Element tLCInsuredCount = tLCInsureds.addElement("LCInsuredCount");
		Element tLCInsured = tLCInsureds.addElement("LCInsured");
		
		Element tName = tLCInsured.addElement("Name");
		Element tSex = tLCInsured.addElement("Sex");
		Element tBirthday = tLCInsured.addElement("Birthday");
		Element tIDType = tLCInsured.addElement("IDType");
		Element tIDNo = tLCInsured.addElement("IDNo");
		Element tMarriage = tLCInsured.addElement("Marriage");
		Element tNationality = tLCInsured.addElement("Nationality");
		Element tLicenseType = tLCInsured.addElement("LicenseType");
		Element tJobCode = tLCInsured.addElement("JobCode");
		Element tProvince = tLCInsured.addElement("Province");
		Element tCity = tLCInsured.addElement("City");
		Element tCounty = tLCInsured.addElement("County");
		Element tHomeAddress = tLCInsured.addElement("HomeAddress");
		Element tHomeZipCode = tLCInsured.addElement("HomeZipCode");
		Element tMobile = tLCInsured.addElement("Mobile");
		Element tOfficePhone = tLCInsured.addElement("OfficePhone");
		Element tHomePhone = tLCInsured.addElement("HomePhone");
		Element tHomeFax = tLCInsured.addElement("HomeFax");
		Element tEmail = tLCInsured.addElement("Email");
		Element tGrpName = tLCInsured.addElement("GrpName");

		PersonInfo insured = (PersonInfo)map.get("insured");
		String contNo = (String) map.get("ContNo");
		
		tContNo.addText(contNo);
		tLCInsuredCount.addText("1");
		tName.addText(insured.getName());
		tSex.addText(insured.getSex());
		tBirthday.addText(insured.getBirthDay());
		tIDType.addText(insured.getIdType());
		tIDNo.addText(insured.getIdNo());
		tMarriage.addText(insured.getMarriage());
		tNationality.addText(insured.getNationality());
		tLicenseType.addText(insured.getLicenseType());
		tJobCode.addText(insured.getJobCode());
		tProvince.addText(insured.getProvince());
		tCity.addText(insured.getCity());
		tCounty.addText(insured.getCounty());
		tHomeAddress.addText(insured.getHomeAddress());
		tHomeZipCode.addText(insured.getHomeZipCode());
		tMobile.addText(insured.getMobile());
		tOfficePhone.addText(insured.getOfficePhone());
		tHomePhone.addText(insured.getPhone());
		tHomeFax.addText(insured.getFax());
		tEmail.addText(insured.getEmail());
		tGrpName.addText(insured.getGrpName());
		
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
