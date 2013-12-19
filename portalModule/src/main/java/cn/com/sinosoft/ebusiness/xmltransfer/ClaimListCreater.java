package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.ebusiness.claim.domain.ClaimProcessList;
import cn.com.sinosoft.ebusiness.infomanage.domain.PersonInfo;
import cn.com.sinosoft.ebusiness.infomanage.domain.PolicyList;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

/**
 * 理赔列表
 * @author zhuxuyang
 * 2013.09.16
 */
public class ClaimListCreater extends XmlCreater implements Creater<List<ClaimProcessList>>{
	private static String sTranRequest = "/TranData/TranRequest";
	private static String sTranResponse = "/TranData/TranResponse";
	
	//交易码设置
	private static String functionFlag = "ST000025";
	
	public ClaimListCreater(){
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
		
		tQueryType.addText("14");
		tParamCount.addText(("" + conts.size()));
		
		for(int i = 0; i < conts.size(); i++){
			Element tParam = tParams.addElement("Param");
			Element tKey = tParam.addElement("Key");
			Element tValue = tParam.addElement("Value");
			
			tKey.addText("ContNo");
			tValue.addText(conts.get(i).getPolicySerialNumber());
		}
		
		return doc;
	}
	
	@Override
	public List<ClaimProcessList> Xml2Object(Document doc) {
		List<ClaimProcessList> listClaimProcessList = new ArrayList<ClaimProcessList>();
		List<Element> listData = doc.selectNodes(sTranResponse + "/QueryData/Datas/Data");
		for(int i = 0; i < listData.size(); i++){
			Element e = listData.get(i);
			ClaimProcessList cl = new ClaimProcessList();
			
			cl.setClaimNumber(e.selectSingleNode("Col01").getText());
			cl.setInsuredName(e.selectSingleNode("Col02").getText());
			cl.setAccDate(e.selectSingleNode("Col03").getText());
			cl.setClaimStatus(e.selectSingleNode("Col04").getText());
			
			listClaimProcessList.add(cl);
		}
		
		return listClaimProcessList;
	}
	
	public static void main(String[] args){
	}

}
