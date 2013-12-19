package cn.com.sinosoft.ebusiness.xmltransfer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.ebusiness.infomanage.domain.PolicyList;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.Creater;
import cn.com.sinosoft.ebusiness.xmltransfer.sub.XmlCreater;

/**
 * �����б�
 * @author zhuxuyang
 * 2013.09.16
 */
public class PolicyListCreater extends XmlCreater implements Creater<List<PolicyList>>{
	private static String sTranRequest = "/TranData/TranRequest";
	private static String sTranResponse = "/TranData/TranResponse";
	
	//����������
	private static String functionFlag = "ST000025";
	
	public PolicyListCreater(){
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
		
		tQueryType.addText("12");
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
	public List<PolicyList> Xml2Object(Document doc) {
		List<PolicyList> listPolicyList = new ArrayList<PolicyList>();
		List<Element> listData = doc.selectNodes(sTranResponse + "/QueryData/Datas/Data");
		for(int i = 0; i < listData.size(); i++){
			Element e = listData.get(i);
			PolicyList pl = new PolicyList();
			
			pl.setPolicySerialNumber(e.selectSingleNode("Col01").getText());
			String sMainRiskName = e.selectSingleNode("Col02").getText();
			if(sMainRiskName.length() > 9){
				sMainRiskName = sMainRiskName.substring(0, 9) + "...";
			}
			pl.setMainRiskName(sMainRiskName);
			pl.setInsuredName(e.selectSingleNode("Col03").getText());
			pl.setAmount(e.selectSingleNode("Col04").getText());
			pl.setInceptionDate(e.selectSingleNode("Col05").getText());
			//���⴦����δʧЧ���޸�Ϊ����Ч��
			if ("δʧЧ".equals(e.selectSingleNode("Col06").getText())) {
				pl.setPolicyStatus("��Ч");
			} else {				
				pl.setPolicyStatus(e.selectSingleNode("Col06").getText());
			}
			pl.setPrNumber(e.selectSingleNode("Col07").getText());
//			pl.setDownloadString(this.encryption(pl.getPrNumber()+"sinatay.pdf"));
			listPolicyList.add(pl);
		}
		
		return listPolicyList;
	}
	
	/**
	 * �����ַ���
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encryption(String string) {// ASCIIת��Ϊ�ַ���
		// ת���ת����ע��ת����������6���ַ�������ǰ��ӵ�̶����ַ�
		String s0 = "";
		try {
			s0 = URLEncoder.encode("�п�����̩��Ŀ��" + string, "GBK").replaceAll(
					"%", "`");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		// �������ַ����ĳ���
		int length = s0.length() / 4 + (s0.length() % 4 == 0 ? 0 : 1);
		// �ֽ��4�����ַ���
		String s1 = s0.substring(0, length);
		String s2 = s0.substring(length, length * 2);
		String s3 = s0.substring(length * 2, length * 3);
		String s4 = s0.substring(length * 3);
		// s4��~�ַ�
		switch (length - s4.length()) {
		case 1:
			s4 = s4 + "~";
			break;
		case 2:
			s4 = s4 + "~~";
			break;
		case 3:
			s4 = s4 + "~~~";
			break;
		default:
			break;
		}
		// �������г��µ��ַ���
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(s1.charAt(i));
			sb.append(s2.charAt(i));
			sb.append(s3.charAt(i));
			sb.append(s4.charAt(i));
		}
		return sb.toString();
	}

	/**
	 * �����ַ���
	 * 
	 * @param string
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decryption(String string)
			 {
		int length = string.length();
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		StringBuilder sb3 = new StringBuilder();
		StringBuilder sb4 = new StringBuilder();
		for (int i = 0; i < length;) {
			sb1.append(string.charAt(i));
			i++;
			sb2.append(string.charAt(i));
			i++;
			sb3.append(string.charAt(i));
			i++;
			sb4.append(string.charAt(i));
			i++;
		}
		String s0 = "" + sb1 + sb2 + sb3 + sb4;
		// ��������"~"�ַ�,�����3��������ִ��3��
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		if (s0.lastIndexOf("~") == s0.length() - 1) {
			s0 = s0.substring(0, s0.length() - 1);
		}
		// ת���ת��
		s0 = s0.replaceAll("`", "%");
		try {
			s0 = URLDecoder.decode(s0, "GBK");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		s0 = s0.replaceFirst("�п�����̩��Ŀ��", "");
		return s0;
	}
	
	
	public static void main(String[] args){
	}

}
