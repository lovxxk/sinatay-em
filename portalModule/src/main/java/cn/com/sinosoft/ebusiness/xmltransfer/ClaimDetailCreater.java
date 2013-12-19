package cn.com.sinosoft.ebusiness.xmltransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.ebusiness.claim.domain.ClaimDetail;
import cn.com.sinosoft.ebusiness.claim.domain.ClaimPayDetail;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

/**
 * 理赔详情
 * @author zhuxuyang
 * 2013.09.16
 */
public class ClaimDetailCreater extends XmlCreater implements Creater<ClaimDetail>{
	private static String sTranRequest = "/TranData/TranRequest";
	private static String sTranResponse = "/TranData/TranResponse";
	
	//交易码设置
	private static String functionFlag = "ST000043";
	
	public ClaimDetailCreater(){
		super();
	}
	
	@Override
	public Document createXml(Map<String,Object> map){
		Document doc = null;
		doc = super.createXml(functionFlag);
		Element tTranRequest = (Element)doc.selectSingleNode(sTranRequest);
		Element tCLMNo = tTranRequest.addElement("CLMNo");
		
		
		tCLMNo.addText((String)map.get("CLMNo"));
		
		return doc;
	}
	
	@Override
	public ClaimDetail Xml2Object(Document doc) {
		ClaimDetail claimDetail = new ClaimDetail();
		Element eClaimDetail = (Element) doc.selectSingleNode(sTranResponse + "/ClaimInfos/ClaimInfo");
		List<Element> listGetClaim = doc.selectNodes(sTranResponse + "/ClaimInfos/ClaimInfo/GetClaimInfos/GetClaimInfo");
		
		claimDetail.setClaimNumber(eClaimDetail.selectSingleNode("CaseNo").getText());
		claimDetail.setInsuredName(eClaimDetail.selectSingleNode("AccidentName").getText());
		claimDetail.setAccDate(eClaimDetail.selectSingleNode("AccidentDate").getText());
		claimDetail.setAccPlace(eClaimDetail.selectSingleNode("AccidentSite").getText());
		claimDetail.setAccReason(eClaimDetail.selectSingleNode("AccidentReason").getText());
		claimDetail.setApplyDate(eClaimDetail.selectSingleNode("RgtDate").getText());
		claimDetail.setCloseDate(eClaimDetail.selectSingleNode("EndCaseDate").getText());
		claimDetail.setClaimMoney(eClaimDetail.selectSingleNode("RealPay").getText());
		claimDetail.setClaimResult(eClaimDetail.selectSingleNode("GiveType").getText());
		claimDetail.setRefuseReason(eClaimDetail.selectSingleNode("GiveTypeDesc").getText());
		claimDetail.setClaimState(eClaimDetail.selectSingleNode("ClmState").getText());
		
		List<ClaimPayDetail> listClaimPayDetail = new ArrayList<ClaimPayDetail>();
		for(int i = 0; i < listGetClaim.size(); i++){
			Element e = listGetClaim.get(i);
			ClaimPayDetail cpd = new ClaimPayDetail();
			
			cpd.setPayType(listGetClaim.get(i).selectSingleNode("GetType").getText());
			cpd.setPayDate(listGetClaim.get(i).selectSingleNode("GetDate").getText());
			cpd.setReceiveMoney(listGetClaim.get(i).selectSingleNode("GetMoney").getText());
			cpd.setReceiverName(listGetClaim.get(i).selectSingleNode("GetName").getText());
			if("0".equals(listGetClaim.get(i).selectSingleNode("GetIDType").getText())){
				cpd.setReceiverIDType("身份证");
			}else if("1".equals(listGetClaim.get(i).selectSingleNode("GetIDType").getText())){
				cpd.setReceiverIDType("护照");
			}else if("2".equals(listGetClaim.get(i).selectSingleNode("GetIDType").getText())){
				cpd.setReceiverIDType("军官证");
			}else{
				cpd.setReceiverIDType("未知");
			}
			cpd.setReceiverID(listGetClaim.get(i).selectSingleNode("GetIDNo").getText());
			
			listClaimPayDetail.add(cpd);
		}
		claimDetail.setListClaimPayDetail(listClaimPayDetail);
		
		return claimDetail;
	}
	
	public static void main(String[] args){
	}

}
