package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.ebusiness.infomanage.domain.PolicyList;
import cn.com.sinosoft.ebusiness.tools.BT;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

/**
 * 客户有效保单
 * @author zhuxuyang
 * 2013.09.16
 */
public class CustomerPolicyCreater extends XmlCreater implements Creater<List<PolicyList>>{
	private static String sTranRequest = "/TranData/TranRequest";
	private static String sTranResponse = "/TranData/TranResponse";
	
	//交易码设置
	private static String functionFlag = "ST000025";
	
	public CustomerPolicyCreater(){
		super();
	}
	
	@Override
	public Document createXml(Map<String,Object> map){
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element)doc.selectSingleNode(sTranRequest);
		Element tQueryData = tTranRequest.addElement("QueryData");
		Element tPageNo = tQueryData.addElement("PageNo");
		Element tPageRowNum = tQueryData.addElement("PageRowNum");
		Element tQueryType = tQueryData.addElement("QueryType");
		Element tParams = tQueryData.addElement("Params");
		Element tParamCount = tParams.addElement("ParamCount");
		
		List<BindPolicy> conts = (List<BindPolicy>)map.get("listBindPolicy");
		
		tQueryType.addText("11");
		tParamCount.addText("5");
		
		Element tParam1 = tParams.addElement("Param");
		Element tKey1 = tParam1.addElement("Key");
		Element tValue1 = tParam1.addElement("Value");
		tKey1.addText("AppntName");
		
		tValue1.addText(BT.trunc((String)map.get("AppntName")));
		
		Element tParam2 = tParams.addElement("Param");
		Element tKey2 = tParam2.addElement("Key");
		Element tValue2 = tParam2.addElement("Value");
		tKey2.addText("AppntSex");
		tValue2.addText(BT.trunc((String)map.get("AppntSex")));
		
		Element tParam3 = tParams.addElement("Param");
		Element tKey3 = tParam3.addElement("Key");
		Element tValue3 = tParam3.addElement("Value");
		tKey3.addText("AppntBirthday");
		tValue3.addText(BT.trunc((String)map.get("AppntBirthday")));
		
		Element tParam4 = tParams.addElement("Param");
		Element tKey4 = tParam4.addElement("Key");
		Element tValue4 = tParam4.addElement("Value");
		tKey4.addText("AppntIDType");
		tValue4.addText(BT.trunc((String)map.get("AppntIDType")));
		
		Element tParam5 = tParams.addElement("Param");
		Element tKey5 = tParam5.addElement("Key");
		Element tValue5 = tParam5.addElement("Value");
		tKey5.addText("AppntIDNo");
		tValue5.addText(BT.trunc((String)map.get("AppntIDNo")));
		
		return doc;
	}
	
	@Override
	public List<PolicyList> Xml2Object(Document doc) {
		List<PolicyList> listPolicyList = new ArrayList<PolicyList>();
		List<Element> listData = doc.selectNodes(sTranResponse + "/QueryData/Datas/Data");
		for(int i = 0; i < listData.size(); i++){
			Element e = listData.get(i);
			PolicyList pl = new PolicyList();
			
			pl.setPolicySerialNumber(e.selectSingleNode("Col01").getText());
			pl.setMainRiskName(e.selectSingleNode("Col02").getText());
			pl.setInsuredName(e.selectSingleNode("Col03").getText());
			pl.setAmount(e.selectSingleNode("Col04").getText());
			pl.setInceptionDate(e.selectSingleNode("Col05").getText());
			pl.setPolicyStatus(e.selectSingleNode("Col06").getText());
			
			listPolicyList.add(pl);
		}
		
		return listPolicyList;
	}
	
	public static void main(String[] args){
	}

}
