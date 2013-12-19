package test;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.impl.cookie.DateUtils;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.portalModule.interfacePortal.client.axis.AxisClient;
import cn.com.sinosoft.portalModule.interfacePortal.client.servlet.ServletClient;
import cn.com.sinosoft.portalModule.transport.dhf100.Data;
import cn.com.sinosoft.portalModule.transport.dhf100.Datas;
import cn.com.sinosoft.portalModule.transport.dhf100.Param;
import cn.com.sinosoft.portalModule.transport.dhf100.Params;
import cn.com.sinosoft.portalModule.transport.dhf100.QueryData;
import cn.com.sinosoft.portalModule.transport.sinatay.LCAppnt;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.LCInsured;
import cn.com.sinosoft.portalModule.transport.sinatay.Risk;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.encode.AES;
import cn.com.sinosoft.util.encode.DESPlus;

public class ST000052Test {

	/**
	 * @param args
	 * 2.2.	��Ʒ����߳б�(ST000052)����Ӧ��Test
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		invokeWebService();
//		String s = ST000052ReqMarshller();
//		System.out.println("��Ʒ����߳б�(ST000052)����...");
//		System.out.println(s);
//		String mappingFilePath = "D:/Workspaces/workspace_sinatay/portalModule/src/main/resources/castorMappingFile/ST000052_Req_CastorMapping.xml";
//		TXInsurance txi = (TXInsurance)ST000052UnMarshall(mappingFilePath,s);
//		systemObject(txi);
//
//		String requestURL = "http://10.20.3.147:9001/SIP/URLServer";
//		requestURL = "http://10.20.3.123:7001/SIP/URLServer";
//		String requestXML = "";
//		String charset = "GBK";
//		ServletClient sc = new ServletClient();
//		try {
//			System.out.println("*********");
//			System.out.println(sc.sendRequestXML(requestURL, s, charset));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println("*********");
//		String s1 = ST000052ResMarshller();
//		System.out.println("��Ʒ����߳б�(ST000052)Ӧ��...");
//		System.out.println(s1);
//		mappingFilePath = "D:/Workspaces/workspace_sinatay/portalModule/src/main/resources/castorMappingFile/ST000052_Res_CastorMapping.xml";
//		TXInsurance txi1 = (TXInsurance)ST000052UnMarshall(mappingFilePath,s1);
//		systemObject(txi1);
	}
	
	
	public static void invokeWebService() throws Exception{
		String requestXML = ST000052ReqMarshller();
		System.out.println("��Ʒ����߳б�(ST000052)����...");
		//requestXML= new DESPlus("ST000052").encrypt(requestXML);
		System.out.println(requestXML);
		requestXML= AES.Encrypt(requestXML,"ST000052ST000052");
		AxisClient axisClient = new AxisClient();
		axisClient.sendRPCRequestXML("http://127.0.0.1:8082/online/services/ST000052", "http://fascade.service.dhf.ebusiness.sinosoft.com.cn", "ST000052", requestXML);
	}
	
	public static void systemObject(TXInsurance txi){
		System.out.println("txi: "+txi);
	}

	public static String ST000052ReqMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumber();
		String mappingFilePath = "D:/Workspaces/workspace_sinatay/portalModule/src/main/resources/castorMappingFile/ST000052_Req_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("DHF20131118000000038");
			txInsurance.setTransType("ST000052");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HH:mm:ss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("DHF");
			
			TranRequest tranRequest = new TranRequest();
 
			LCCont lcCont = new LCCont();
			lcCont.setPolApplyDate(new Date());
			lcCont.setPrem(new BigDecimal(1000));
			lcCont.setAccBankCode("103");
			lcCont.setBankAccNo("1234567890");
			lcCont.setIsPaySucc("1");
			lcCont.setPayMessage("Succ");
			lcCont.setPaySerialNo("201208020009");
			lcCont.setOrderId("������");
			lcCont.setCarNo("201208020009");
			lcCont.setStartDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			lcCont.setStartTime(DateUtils.formatDate(transDate, "HH:mm:ss"));
			lcCont.setEndDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			lcCont.setEndTime(DateUtils.formatDate(transDate, "HH:mm:ss"));
			lcCont.setStartPlace("������");
			lcCont.setAcrossFlag("B");
			
			//Ͷ����
			LCAppnt lcAppnt = new LCAppnt();
			lcAppnt.setFullName("Ͷ�������� ");
			lcAppnt.setMobilePhoneNumber("13843838438");
			lcAppnt.setRelaToInsured("�뱻���˹�ϵ");
			
			lcCont.setLcAppnt(lcAppnt);
			
			lcCont.setLcInsuredCount(1);
			LCInsured lcInsured = new LCInsured();
			lcInsured.setFullName("����������");
			lcInsured.setMobilePhoneNumber("13843838438");
			lcInsured.setRiskCount(1);
			Risk risk = new Risk();
			risk.setRiskCode("���ִ���");
			risk.setMainRiskCode("�������ִ���");
			risk.setRiskName("��������");
			risk.setAmnt(new BigDecimal(1));
			risk.setPrem(new BigDecimal(1));
			
			List<Risk> risks = new ArrayList<Risk>();
			risks.add(risk);
			lcInsured.setRisks(risks);
			
			List<LCInsured> lcInsureds = new ArrayList<LCInsured>();
			lcInsureds.add(lcInsured);
			lcCont.setLcInsureds(lcInsureds);
			
			tranRequest.setLcCont(lcCont);
			
			List<Object> businessDatum = new ArrayList<Object>();
			businessDatum.add(tranRequest);
			txInsurance.setBusinessDatum(businessDatum);
			
			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingFilePath);
			Marshaller fundMarshaller = new Marshaller(sw);
			fundMarshaller.setMapping(mapping);
			fundMarshaller.setEncoding("GBK");
			fundMarshaller.marshal(txInsurance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	public static String ST000052ResMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String mappingFilePath = "D:/Workspaces/workspace_sinatay/portalModule/src/main/resources/castorMappingFile/ST000052_Res_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("201208220008");
			txInsurance.setTransType("ST000052");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("DHF");
			
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("1");
			tranResponse.setDesc("���׳ɹ�!");
			
			LCCont lcCont = new LCCont();
			lcCont.setContNo("201208140019");
			lcCont.setProposalContNo("201208140019");
			lcCont.setPrem(new BigDecimal(10000));
			
			tranResponse.setLcCont(lcCont);
			
			List<Object> businessDatum = new ArrayList<Object>();
			businessDatum.add(tranResponse);
			txInsurance.setBusinessDatum(businessDatum);
			
			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingFilePath);
			Marshaller marshaller = new Marshaller(sw);
			marshaller.setMapping(mapping);
			marshaller.setEncoding("GBK");
			marshaller.marshal(txInsurance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}
	
	public static Object ST000052UnMarshall(String mappingFilePath, String xmlString){
		Object object = new Object();
		try {
			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingFilePath);
			StringReader sr = new StringReader(xmlString);
			Unmarshaller unMarshaller = new Unmarshaller(mapping);
			object = (TXInsurance)unMarshaller.unmarshal(sr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
}
