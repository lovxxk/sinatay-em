package test.subService;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.axis2.AxisFault;
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
import cn.com.sinosoft.portalModule.transport.sinatay.Account;
import cn.com.sinosoft.portalModule.transport.sinatay.LCAppnt;
import cn.com.sinosoft.portalModule.transport.sinatay.LCBnf;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.LCInsured;
import cn.com.sinosoft.portalModule.transport.sinatay.Risk;
import cn.com.sinosoft.portalModule.transport.sinatay.TellInfo;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.encode.AES;
import cn.com.sinosoft.util.encode.DESPlus;
import cn.com.sinosoft.util.encode.Md5;

public class ST000029Test {

	/**
	 * @param args
	 * ������ϸ��ѯ(ST000029)����Ӧ��Test
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		invokeWebService();
		String s = ST000029ReqMarshller();
		System.out.println("������ϸ��ѯ(ST000029)����...");
		System.out.println(s);
		String mappingFilePath = "E:/e_workdir/git/sinatay-em/portalModule/src/main/resources/castorMappingFile/service/ST000029_Service_Default_Req_CastorMapping.xml";
		TXInsurance txi = (TXInsurance)ST000029UnMarshall(mappingFilePath,s);
		systemObject(txi);

		String requestURL = "http://10.20.3.147:9001/SIP/URLServer";
		requestURL = "http://10.20.3.123:7001/SIP/URLServer";
		String requestXML = "";
		String charset = "GBK";
		ServletClient sc = new ServletClient();
		try {
			System.out.println("*********");
//			System.out.println(sc.sendRequestXML(requestURL, s, charset));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("*********");
		String s1 = ST000029ResMarshller();
		System.out.println("������ϸ��ѯ(ST000029)Ӧ��...");
		System.out.println(s1);
		mappingFilePath = "E:/e_workdir/git/sinatay-em/portalModule/src/main/resources/castorMappingFile/service/ST000029_Service_Default_Res_CastorMapping.xml";
//		TXInsurance txi1 = (TXInsurance)ST000029UnMarshall(mappingFilePath,s1);
//		systemObject(txi1);
	}
	
	public static void invokeWebService() throws Exception{
		String requestXML = ST000029ReqMarshller();
//		System.out.println(requestXML);
//		requestXML = "<?xml version=\"1.0\" encoding=\"GBK\"?><TranData><BaseInfo><TransrDate>20131213</TransrDate><TransrTime>18:56:53</TransrTime><TellerNo>0001</TellerNo><TransrNo>DHF20131213000000106</TransrNo><SaleChannel>W</SaleChannel><SellType>20</SellType><FunctionFlag>ST000029</FunctionFlag><Source>DHF</Source></BaseInfo><TranRequest><QueryData><PageNo>1</PageNo><PageRowNum>50</PageRowNum><QueryType>04</QueryType><Params><ParamCount>1</ParamCount><Param><Key>ProposalContNo</Key><Value>340110000000055</Value></Param></Params></QueryData></TranRequest></TranData>";
//	    System.out.println("��Ʒ�����...");
		System.out.println("��Ʒ䱣����ϸ��ѯ(ST000029)����...");
		System.out.println(requestXML);
		requestXML= AES.Encrypt(requestXML,"1234567812345678");
		AxisClient axisClient = new AxisClient();
		//http://10.20.3.21:7001/services/portal
		//http://127.0.0.1:8082/online/services/portal
		String url = "http://127.0.0.1:9001/online/services/portal";
		url = "http://xtmall-em-dat.sinatay.com/services/portal";
		String responseXML = axisClient.sendRPCRequestXML(url, "http://fascade.service.dhf.ebusiness.sinosoft.com.cn", "webService", requestXML);
		System.out.println("��Ʒ䱣����ϸ��ѯ(ST000029)Ӧ��...");
		System.out.println(AES.Decrypt(responseXML,"1234567812345678"));
	}
	
	public static void systemObject(TXInsurance txi){
		System.out.println("txi: "+txi);
	}

	public static String ST000029ReqMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumberyyyyMMddHHmmss();
		String mappingFilePath = "E:/e_workdir/git/sinatay-em/portalModule/src/main/resources/castorMappingFile/service/ST000029_Service_Default_Req_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("TBJ"+serialNumber);
			txInsurance.setTransType("ST000029");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("TBJ");
			
			TranRequest tranRequest = new TranRequest();
			LCCont lcCont = new LCCont();
			
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
	
	public static String ST000029ResMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumberyyyyMMddHHmmss();
		String mappingFilePath = "E:/e_workdir/git/sinatay-em/portalModule/src/main/resources/castorMappingFile/service/ST000029_Service_Default_Res_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("TBJ"+serialNumber);
			txInsurance.setTransType("ST000029");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("TBJ");
			
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("1");
			tranResponse.setDesc("���׳ɹ�!");
			
			LCCont lcCont = new LCCont();
			lcCont.setTransDate(new Date());
//			lcCont.setPolApplyDate(new Date());
//			lcCont.setPrem(new BigDecimal(3130));
//			lcCont.setManageCom("86010000");
//			lcCont.setAgentCode("86010000");
//			lcCont.setDisputedFlag("1");
			
			//Ͷ����
			LCAppnt lcAppnt = new LCAppnt();
			lcAppnt.setFullName("����");
			lcAppnt.setIdType("0");
			lcAppnt.setIdNumber("370921198605231516");
			lcAppnt.setBirthDate(cn.com.sinosoft.util.time.DateUtils.parseDate("1986-05-23", new String[]{"yyyy-MM-dd"}));
			lcAppnt.setGender("0");
			lcAppnt.setMobilePhoneNumber("15800760896");
			lcAppnt.setEmail("lovxxk@163.com");
			lcAppnt.setProvince("440000");
			lcAppnt.setCity("440300");
			lcAppnt.setHomeAddress("�Ϻ���ĳĳ��1001��");
			lcAppnt.setHomeZipCode("271000");
			lcAppnt.setRelaToInsured("00");
			
			//������
			LCInsured lcInsured = new LCInsured();
			lcInsured.setRelaToMain("00");
			lcInsured.setRelaToAppnt("00");
			lcInsured.setFullName("����");
			lcInsured.setIdType("0");
			lcInsured.setIdNumber("370921198605231516");
			lcInsured.setBirthDate(cn.com.sinosoft.util.time.DateUtils.parseDate("1986-05-23", new String[]{"yyyy-MM-dd"}));
			lcInsured.setGender("0");
			lcInsured.setMobilePhoneNumber("15800760896");
			lcInsured.setEmail("lovxxk@163.com");
			lcInsured.setProvince("440000");
			lcInsured.setCity("440307");
			lcInsured.setHomeAddress("�Ϻ���ĳĳ��1001��");
			lcInsured.setHomeZipCode("271000");
			lcInsured.setJobCode("1050104");
			
			//����
			Risk risk = new Risk();
			risk.setMainRiskCode("00116600");
			risk.setRiskCode("00136600");
			risk.setAmnt(new BigDecimal(1000000));
//			risk.setPrem(new BigDecimal(750));
			risk.setMult(1);
			risk.setPayIntv("12");
			risk.setPayEndYearFlag("Y");
			risk.setPayEndYear("5");
			risk.setInsuYearFlag("Y");
			risk.setInsuYear("30");
			Risk risk1 = new Risk();
			risk1.setMainRiskCode("00116600");
			risk1.setRiskCode("00116600");
			risk1.setAmnt(new BigDecimal(100000));
//			risk1.setPrem(new BigDecimal(2380));
			risk1.setMult(1);
			risk1.setPayIntv("12");
			risk1.setPayEndYearFlag("Y");
			risk1.setPayEndYear("5");
			risk1.setInsuYearFlag("Y");
			risk1.setInsuYear("30");
			
			Risk risk2 = new Risk();
			risk2.setMainRiskCode("00116600");
			risk2.setRiskCode("00126600");
			risk2.setAmnt(new BigDecimal(100000));
//			risk2.setPrem(new BigDecimal(2380));
			risk2.setMult(1);
			risk2.setPayIntv("12");
			risk2.setPayEndYearFlag("Y");
			risk2.setPayEndYear("5");
			risk2.setInsuYearFlag("Y");
			risk2.setInsuYear("30");
			
			//������
			LCBnf lcBnf = new LCBnf();
			
			
			risk.getLcBnfs().add(lcBnf);
			risk.setLcBnfCount(risk.getLcBnfs().size());
			
//			risk1.getLcBnfs().add(lcBnf);
			risk1.setLcBnfCount(risk.getLcBnfs().size());
			
//			risk1.getAccounts().add(riskAccount);
			risk1.setAccountCount(risk1.getAccounts().size());
			
			risk2.getLcBnfs().add(lcBnf);
			risk2.setLcBnfCount(risk2.getLcBnfs().size());
			
//			risk2.getAccounts().add(riskAccount);
			risk2.setAccountCount(risk2.getAccounts().size());
			
			lcInsured.getRisks().add(risk);
//			lcInsured.getRisks().add(risk1);
//			lcInsured.getRisks().add(risk2);
			lcInsured.setRiskCount(lcInsured.getRisks().size());
			
			lcCont.setLcAppnt(lcAppnt);
			lcCont.getLcInsureds().add(lcInsured);
			lcCont.setLcInsuredCount(lcCont.getLcInsureds().size());
			
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
	
	public static Object ST000029UnMarshall(String mappingFilePath, String xmlString){
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
