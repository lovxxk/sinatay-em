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
import cn.com.sinosoft.portalModule.transport.sinatay.Attachment;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public class ST000026Test {

	/**
	 * @param args
	 * 影像通知请求、应答Test
	 */
	public static void main(String[] args) {
		String s = ST000026ReqMarshller();
		System.out.println("影像通知请求...");
		System.out.println(s);
		String mappingFilePath = "E:/workdir/workspace_37_sinatay/portalModule/src/main/resources/castorMappingFile/ST000026_Req_CastorMapping.xml";
		TXInsurance txi = (TXInsurance)ST000026UnMarshall(mappingFilePath,s);
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
		String s1 = ST000026ResMarshller();
		System.out.println("影像通知应答...");
		System.out.println(s1);
		mappingFilePath = "E:/workdir/workspace_37_sinatay/portalModule/src/main/resources/castorMappingFile/ST000026_Res_CastorMapping.xml";
		TXInsurance txi1 = (TXInsurance)ST000026UnMarshall(mappingFilePath,s1);
		systemObject(txi1);
	}
	
	public static void systemObject(TXInsurance txi){
		System.out.println("txi: "+txi);
	}

	public static String ST000026ReqMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumber();
		String mappingFilePath = "E:/workdir/workspace_37_sinatay/portalModule/src/main/resources/castorMappingFile/ST000026_Req_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID(serialNumber);
			txInsurance.setTransType("ST000026");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo(serialNumber.substring(serialNumber.length()-6,serialNumber.length()));
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("WEB_PERSON");
			
			TranRequest tranRequest = new TranRequest();
			tranRequest.setProposalContNo(serialNumber);
			
			Attachment attachment = new Attachment();
			attachment.setSubType("UA071");
			attachment.setPageCode("1");
			
			tranRequest.getAttachments().add(attachment);
			tranRequest.setAttachmentCount(tranRequest.getAttachments().size());
			
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
	
	public static String ST000026ResMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String mappingFilePath = "E:/workdir/workspace_37_sinatay/portalModule/src/main/resources/castorMappingFile/ST000026_Res_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID(GeneratorTransSerialNumber.generatorTransSerialNumber());
			txInsurance.setTransType("ST000026");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo(GeneratorTransSerialNumber.generatorTransSerialNumber());
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("WEB_PERSON");
			
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("1");
			tranResponse.setDesc("");
			
			Attachment attachment = new Attachment();
			
			tranResponse.getAttachments().add(attachment);
			tranResponse.setAttachmentCount(tranResponse.getAttachments().size());
			
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
	
	public static Object ST000026UnMarshall(String mappingFilePath, String xmlString){
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
