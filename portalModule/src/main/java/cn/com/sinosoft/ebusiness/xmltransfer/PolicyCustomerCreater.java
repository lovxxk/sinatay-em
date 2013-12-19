package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

/**
 * 保单投保人五要素
 * @author zhuxuyang
 * 2013.09.16
 */
public class PolicyCustomerCreater extends XmlCreater implements Creater<Map<String,String>>{
	private static String sTranRequest = "/TranData/TranRequest";
	private static String sTranResponse = "/TranData/TranResponse";
	
	//交易码设置
	private static String functionFlag = "ST000025";
	
	public PolicyCustomerCreater(){
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
		
		tQueryType.addText("13");
		tParamCount.addText("1");
		
		Element tParam = tParams.addElement("Param");
		Element tKey = tParam.addElement("Key");
		Element tValue = tParam.addElement("Value");

		tKey.addText("ContNo");
		tValue.addText((String)map.get("ContNo"));

		return doc;
	}
	
	@Override
	public Map<String,String> Xml2Object(Document doc) {
		List<Element> listData = doc.selectNodes(sTranResponse + "/QueryData/Datas/Data");
		Map<String,String> map = new HashMap<String,String>();
		
		if(listData.size() == 0){
			map.put("hasCustomer", "N");
		}else{
			Element e = listData.get(0);
			map.put("AppntName", e.selectSingleNode("Col01").getText());
			map.put("AppntSex", e.selectSingleNode("Col02").getText());
			map.put("AppntBirth", e.selectSingleNode("Col03").getText());
			map.put("AppntIDType", e.selectSingleNode("Col04").getText());
			map.put("AppntID", e.selectSingleNode("Col05").getText());
			
			map.put("hasCustomer", "Y");
		}
		
		return map;
	}
	
	public static void main(String[] args){
	}

}
