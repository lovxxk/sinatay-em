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
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;
import cn.com.sinosoft.portalModule.transport.sinatay.ReconciliationDetail;
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.encode.AES;

public class ST000095Test {

	/**
	 * @param args
	 * 大黄蜂日终对账接口(交易码ST000095)请求、应答Test
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		invokeWebService();
//		String s = ST000095ReqMarshller();
//		System.out.println("大黄蜂日终对账接口(交易码ST000095)请求...");
//		System.out.println(s);
//		String mappingFilePath = "E:/e_workdir/ClearCase/liuchen_em__dzsw/dzsw_pro/mall/portalModuless/src/main/resources/castorMappingFile/ST000095_Req_CastorMapping.xml";
//		TXInsurance txi = (TXInsurance)ST000095UnMarshall(mappingFilePath,s);
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
//		String s1 = ST000095ResMarshller();
//		System.out.println("大黄蜂日终对账接口(交易码ST000095)应答...");
//		System.out.println(s1);
//		mappingFilePath = "E:/e_workdir/ClearCase/liuchen_em__dzsw/dzsw_pro/mall/portalModuless/src/main/resources/castorMappingFile/ST000095_Res_CastorMapping.xml";
//		TXInsurance txi1 = (TXInsurance)ST000095UnMarshall(mappingFilePath,s1);
//		systemObject(txi1);
	}
	

	public static void invokeWebService() throws Exception{
		String requestXML = ST000095ReqMarshller();
		System.out.println("大黄蜂日终对账(ST000095)请求...");
		System.out.println(requestXML);
		requestXML= AES.Encrypt(requestXML,"1234567812345678");
		AxisClient axisClient = new AxisClient();
		String url = "http://127.0.0.1:9001/online/services/portal";
		url = "http://xtmall-em-dat.sinatay.com/services/portal";
		String responseXML = axisClient.sendRPCRequestXML(url, "http://fascade.service.dhf.ebusiness.sinosoft.com.cn", "webService", requestXML);
		System.out.println("大黄蜂日终对账(ST000095)应答...");
		System.out.println(AES.Decrypt(responseXML,"1234567812345678"));
	}
	
	public static void systemObject(TXInsurance txi){
		System.out.println("txi: "+txi);
	}

	public static String ST000095ReqMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumber();
		String mappingFilePath = "E:/e_workdir/ClearCase/liuchen_em__dzsw/dzsw_pro/mall/portalModule/src/main/resources/castorMappingFile/ST000095_Req_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("DHF"+serialNumber);
			txInsurance.setTransType("ST000095");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HH:mm:ss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("DHF");
			
			TranRequest tranRequest = new TranRequest();
 
			tranRequest.setBillDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			tranRequest.setTotalCount(2);
			tranRequest.setTotalPrem(new BigDecimal(6000.50));
			
			List<ReconciliationDetail> detailList = new ArrayList<ReconciliationDetail>();
			ReconciliationDetail detail0 = new ReconciliationDetail();
			detail0.setTransDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			detail0.setPrtno("340110000000067");
			detail0.setPrem(new BigDecimal("1.6"));
			detail0.setAppFlag("1");
			detailList.add(detail0);
			ReconciliationDetail detail1 = new ReconciliationDetail();
			detail1.setTransDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			detail1.setPrtno("340110000000068");
			detail1.setPrem(new BigDecimal("1.6"));
			detail1.setAppFlag("1");
			detailList.add(detail1);
			tranRequest.setDetailList(detailList);
			
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
	
	public static String ST000095ResMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String mappingFilePath = "E:/e_workdir/ClearCase/liuchen_em__dzsw/dzsw_pro/mall/portalModule/src/main/resources/castorMappingFile/ST000095_Res_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("201208220008");
			txInsurance.setTransType("ST000095");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("DHF");
			
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("1");
			tranResponse.setDesc("交易成功!");
			
			LCCont lcCont = new LCCont();
			lcCont.setContNo("201208140019");
			lcCont.setProposalContNo("340110000000052");
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
	
	public static Object ST000095UnMarshall(String mappingFilePath, String xmlString){
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
