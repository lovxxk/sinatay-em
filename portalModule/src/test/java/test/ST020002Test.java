package test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.impl.cookie.DateUtils;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.portalModule.interfacePortal.client.servlet.ServletClient;
import cn.com.sinosoft.portalModule.transport.sinatay.SMS;
import cn.com.sinosoft.portalModule.transport.sinatay.SMSResult;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public class ST020002Test {

	/**
	 * @param args
	 * 手机短信(ST020002)请求、应答Test
	 */
	public static void main(String[] args) {
		String s = ST020002ReqMarshller();
		System.out.println("手机短信(ST020002)请求...");
		System.out.println(s);
		String mappingFilePath = "E:/workdir/workspace_37_sinatay/portalModule/src/main/resources/castorMappingFile/ST020002_Req_CastorMapping.xml";
		TXInsurance txi = (TXInsurance)ST020002UnMarshall(mappingFilePath,s);
		systemObject(txi);

		String requestURL = "http://10.20.3.147:9001/SIP/URLServer";
		requestURL = "http://10.20.3.123:7001/SIP/URLServer";
		String requestXML = "";
		String charset = "GBK";
		ServletClient sc = new ServletClient();
		try {
			System.out.println("*********");
			System.out.println(sc.sendRequestXML(requestURL, s, charset));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("*********");
		String s1 = ST020002ResMarshller();
		System.out.println("手机短信(ST020002)应答...");
		System.out.println(s1);
		mappingFilePath = "E:/workdir/workspace_37_sinatay/portalModule/src/main/resources/castorMappingFile/ST020002_Res_CastorMapping.xml";
		TXInsurance txi1 = (TXInsurance)ST020002UnMarshall(mappingFilePath,s1);
		systemObject(txi1);
	}
	
	public static void systemObject(TXInsurance txi){
		System.out.println("txi: "+txi);
	}

	public static String ST020002ReqMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumber();
		String mappingFilePath = "E:/workdir/workspace_37_sinatay/portalModule/src/main/resources/castorMappingFile/ST020002_Req_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID(serialNumber);
			txInsurance.setTransType("ST020002");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo(serialNumber.substring(serialNumber.length()-6,serialNumber.length()));
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("WEB_PERSON");
			
			TranRequest tranRequest = new TranRequest();
			
			SMS sms = new SMS();
			sms.setId("1");
			sms.setPhoneNo("15800760896");
			sms.setMsgComment("测试短信");
			sms.setSender("9005");
			sms.setBusiSerialNo("15800760896");
			
			tranRequest.getMessages().add(sms);
			
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
	
	public static String ST020002ResMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String mappingFilePath = "E:/workdir/workspace_37_sinatay/portalModule/src/main/resources/castorMappingFile/ST020002_Res_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID(GeneratorTransSerialNumber.generatorTransSerialNumber());
			txInsurance.setTransType("ST020002");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo(GeneratorTransSerialNumber.generatorTransSerialNumber());
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("WEB_PERSON");
			
			TranRequest tranRequest = new TranRequest();
			TranResponse tranResponse = new TranResponse();
			
			SMS sms = new SMS();
			sms.setId("1");
			sms.setPhoneNo("15800760896");
			sms.setMsgComment("测试短信");
			sms.setSender("sinatay");
			sms.setBusiSerialNo("15800760896");
			
			SMSResult smsResult = new SMSResult();
			smsResult.setId("1");
			smsResult.setFlag("1");
			smsResult.setDesc("发送成功");
			
			tranRequest.getMessages().add(sms);
			
			tranResponse.setSmsResult(smsResult);
			
			txInsurance.setTranRequest(tranRequest);
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
	
	public static Object ST020002UnMarshall(String mappingFilePath, String xmlString){
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
