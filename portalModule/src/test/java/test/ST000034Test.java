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
import cn.com.sinosoft.portalModule.interfacePortal.client.servlet.ServletClient;
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

public class ST000034Test {

	/**
	 * @param args
	 * 核保-规则调用（ST000034）请求、应答Test
	 */
	public static void main(String[] args) {
		String s = ST000034ReqMarshller();
		System.out.println("核保-规则调用（ST000034）请求...");
		System.out.println(s);
		String mappingFilePath = "F:/sinatayBusiness/portalModule/src/main/resources/castorMappingFile/ST000034_Req_CastorMapping.xml";
		TXInsurance txi = (TXInsurance)ST000034UnMarshall(mappingFilePath,s);
		systemObject(txi);
		
		String requestURL = "http://10.20.3.123:7001/SIP/URLServer";
		String requestXML = "";
		String charset = "GBK";
		ServletClient sc = new ServletClient();
		try {
			System.out.println("*********");
			String result =  sc.sendRequestXML(requestURL, s, charset);
			System.out.println("result>" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("*********");
//		String s1 = ST000034ResMarshller();
//		System.out.println("核保-规则调用（ST000034）应答...");
//		System.out.println(s1);
//		mappingFilePath = "F:/sinatayBusiness/portalModule/src/main/resources/castorMappingFile/ST000034_Res_CastorMapping.xml";
//		TXInsurance txi1 = (TXInsurance)ST000034UnMarshall(mappingFilePath,s1);
//		systemObject(txi1);
	}
	
	public static void systemObject(TXInsurance txi){
		System.out.println("txi: "+txi);
	}

	public static String ST000034ReqMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumber();
		String mappingFilePath = "F:/sinatayBusiness/portalModule/src/main/resources/castorMappingFile/ST000034_Req_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID(serialNumber);
			txInsurance.setTransType("ST000034");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("86010000");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
//			txInsurance.setSource("WEB_PERSON");
			txInsurance.setSource("TAOBAO");
			
			TranRequest tranRequest = new TranRequest();
			LCCont lcCont = new LCCont();
			lcCont.setPolApplyDate(new Date());
//			lcCont.setPrem(new BigDecimal(3130));
//			lcCont.setManageCom("86010000");
//			lcCont.setAgentCode("86010000");
//			lcCont.setDisputedFlag("1");
			
			//告知
			TellInfo tellInfo = new TellInfo();
			tellInfo.setTellVersion("01");
			tellInfo.setTellCode("001");
			tellInfo.setTellRemark(", ,");
			
			//投保人
			LCAppnt lcAppnt = new LCAppnt();
			lcAppnt.setFullName("刘晨");
			lcAppnt.setIdType("0");
			lcAppnt.setIdNumber("370921198605231516");
			lcAppnt.setBirthDate(cn.com.sinosoft.util.time.DateUtils.parseDate("1986-05-23", new String[]{"yyyy-MM-dd"}));
			lcAppnt.setGender("0");
			lcAppnt.setMobilePhoneNumber("15800760896");
			lcAppnt.setEmail("lovxxk@163.com");
			lcAppnt.setProvince("440000");
			lcAppnt.setCity("440300");
			lcAppnt.setHomeAddress("上海市某某区1001号");
			lcAppnt.setHomeZipCode("271000");
			lcAppnt.setRelaToInsured("00");
			
			lcAppnt.getTellInfos().add(tellInfo);
			lcAppnt.setTellInfoCount(lcAppnt.getTellInfos().size());
			
			//被保人
			LCInsured lcInsured = new LCInsured();
			lcInsured.setRelaToMain("00");
			lcInsured.setRelaToAppnt("00");
			lcInsured.setFullName("刘晨");
			lcInsured.setIdType("0");
			lcInsured.setIdNumber("370921198605231516");
			lcInsured.setBirthDate(cn.com.sinosoft.util.time.DateUtils.parseDate("1986-05-23", new String[]{"yyyy-MM-dd"}));
			lcInsured.setGender("0");
			lcInsured.setMobilePhoneNumber("15800760896");
			lcInsured.setEmail("lovxxk@163.com");
			lcInsured.setProvince("440000");
			lcInsured.setCity("440307");
			lcInsured.setHomeAddress("上海市某某区1001号");
			lcInsured.setHomeZipCode("271000");
			lcInsured.setJobCode("1050104");
			
			lcInsured.getTellInfos().add(tellInfo);
			lcInsured.setTellInfoCount(lcInsured.getTellInfos().size());
			
			//险种
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
			risk2.setRiskCode("00116600");
			risk2.setAmnt(new BigDecimal(100000));
//			risk2.setPrem(new BigDecimal(2380));
			risk2.setMult(1);
			risk2.setPayIntv("12");
			risk2.setPayEndYearFlag("Y");
			risk2.setPayEndYear("5");
			risk2.setInsuYearFlag("Y");
			risk2.setInsuYear("30");
			
			//险种账户
			Account riskAccount = new Account();
			
			//受益人
			LCBnf lcBnf = new LCBnf();
			
			
//			risk.getLcBnfs().add(lcBnf);
//			risk.setLcBnfCount(risk.getLcBnfs().size());
			
//			risk.getAccounts().add(riskAccount);
//			risk.setAccountCount(risk.getAccounts().size());
			
//			risk1.getLcBnfs().add(lcBnf);
//			risk1.setLcBnfCount(risk.getLcBnfs().size());
			
//			risk1.getAccounts().add(riskAccount);
//			risk1.setAccountCount(risk1.getAccounts().size());
			
//			risk2.getLcBnfs().add(lcBnf);
//			risk2.setLcBnfCount(risk2.getLcBnfs().size());
			
//			risk2.getAccounts().add(riskAccount);
//			risk2.setAccountCount(risk2.getAccounts().size());
			
			lcInsured.getRisks().add(risk);
//			lcInsured.getRisks().add(risk1);
//			lcInsured.getRisks().add(risk2);
			lcInsured.setRiskCount(lcInsured.getRisks().size());
			
			lcCont.setLcAppnt(lcAppnt);
			lcCont.getLcInsureds().add(lcInsured);
			lcCont.setLcInsuredCount(lcCont.getLcInsureds().size());
			
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
	
	public static String ST000034ResMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String mappingFilePath = "F:/sinatayBusiness/portalModule/src/main/resources/castorMappingFile/ST000034_Res_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID(GeneratorTransSerialNumber.generatorTransSerialNumber());
			txInsurance.setTransType("ST000034");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo(GeneratorTransSerialNumber.generatorTransSerialNumber());
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("WEB_PERSON");
			
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("1");
			tranResponse.setDesc("");
			
			LCCont lcCont = new LCCont();
			
			//投保人
			LCAppnt lcAppnt = new LCAppnt();
			
			//被保人
			LCInsured lcInsured = new LCInsured();
			
			//险种
			Risk risk = new Risk();
			
			lcInsured.getRisks().add(risk);
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
	
	public static Object ST000034UnMarshall(String mappingFilePath, String xmlString){
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
