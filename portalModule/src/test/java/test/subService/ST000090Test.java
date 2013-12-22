package test.subService;

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
import cn.com.sinosoft.portalModule.interfacePortal.client.axis.AxisClient;
import cn.com.sinosoft.portalModule.interfacePortal.client.servlet.ServletClient;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.PayInfo;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.encode.AES;

public class ST000090Test {

	/**
	 * @param args
	 * ֧��(ST000090)����Ӧ��Test
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		invokeWebService();
		String s = ST000090ReqMarshller();
		System.out.println("֧��(ST000090)����...");
		System.out.println(s);
		String mappingFilePath = "E:/e_workdir/git/sinatay-em/portalModule/src/main/resources/castorMappingFile/service/ST000090_Service_Default_Req_CastorMapping.xml";
		TXInsurance txi = (TXInsurance)ST000090UnMarshall(mappingFilePath,s);
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
		String s1 = ST000090ResMarshller();
		System.out.println("֧��(ST000090)Ӧ��...");
		System.out.println(s1);
		mappingFilePath = "E:/e_workdir/git/sinatay-em/portalModule/src/main/resources/castorMappingFile/service/ST000090_Service_Default_Res_CastorMapping.xml";
		TXInsurance txi1 = (TXInsurance)ST000090UnMarshall(mappingFilePath,s1);
		systemObject(txi1);
	}
	
	public static void invokeWebService() throws Exception{
		String requestXML = ST000090ReqMarshller();
//		System.out.println(requestXML);
//		requestXML = "<?xml version=\"1.0\" encoding=\"GBK\"?><TranData><BaseInfo><TransrDate>20131213</TransrDate><TransrTime>18:56:53</TransrTime><TellerNo>0001</TellerNo><TransrNo>DHF20131213000000106</TransrNo><SaleChannel>W</SaleChannel><SellType>20</SellType><FunctionFlag>ST000090</FunctionFlag><Source>DHF</Source></BaseInfo><TranRequest><QueryData><PageNo>1</PageNo><PageRowNum>50</PageRowNum><QueryType>04</QueryType><Params><ParamCount>1</ParamCount><Param><Key>ProposalContNo</Key><Value>340110000000055</Value></Param></Params></QueryData></TranRequest></TranData>";
//	    System.out.println("����...");
		System.out.println("֧��(ST000090)����...");
		System.out.println(requestXML);
		requestXML= AES.Encrypt(requestXML,"1234567812345678");
		AxisClient axisClient = new AxisClient();
		//http://10.20.3.21:7001/services/portal
		//http://127.0.0.1:8082/online/services/portal
		String url = "http://127.0.0.1:9001/online/services/portal";
		url = "http://xtmall-em-dat.sinatay.com/services/portal";
		String responseXML = axisClient.sendRPCRequestXML(url, "http://fascade.service.dhf.ebusiness.sinosoft.com.cn", "webService", requestXML);
		System.out.println("֧��(ST000090)Ӧ��...");
		System.out.println(AES.Decrypt(responseXML,"1234567812345678"));
	}
	
	public static void systemObject(TXInsurance txi){
		System.out.println("txi: "+txi);
	}

	public static String ST000090ReqMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumberyyyyMMddHHmmss();
		String mappingFilePath = "E:/e_workdir/git/sinatay-em/portalModule/src/main/resources/castorMappingFile/service/ST000090_Service_Default_Req_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("TBJ"+serialNumber);
			txInsurance.setTransType("ST000090");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("TBJ");
			
			TranRequest tranRequest = new TranRequest();
			
			PayInfo payInfo = new PayInfo();
			
			tranRequest.setPayInfo(payInfo);
			
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
	
	public static String ST000090ResMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumberyyyyMMddHHmmss();
		String mappingFilePath = "E:/e_workdir/git/sinatay-em/portalModule/src/main/resources/castorMappingFile/service/ST000090_Service_Default_Res_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("TBJ"+serialNumber);
			txInsurance.setTransType("ST000090");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("TBJ");
			
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("1");
			tranResponse.setDesc("���׳ɹ�!");
			
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
	
	public static Object ST000090UnMarshall(String mappingFilePath, String xmlString){
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
