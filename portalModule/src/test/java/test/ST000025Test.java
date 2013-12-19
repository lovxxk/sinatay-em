package test;

import java.io.StringReader;
import java.io.StringWriter;
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
import cn.com.sinosoft.portalModule.transport.sinatay.TranRequest;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.util.encode.AES;
import cn.com.sinosoft.util.encode.DESPlus;
import cn.com.sinosoft.util.encode.Md5;

public class ST000025Test {

	/**
	 * @param args
	 * 2.2.	保单状态查询(ST000025)请求、应答Test
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		invokeWebService();
//		String s = ST000025ReqMarshller();
//		System.out.println("保单状态查询(ST000025)请求...");
//		System.out.println(s);
//		String mappingFilePath = "D:/Workspaces/workspace_sinatay/portalModule/src/main/resources/castorMappingFile/ST000025_Req_CastorMapping.xml";
//		TXInsurance txi = (TXInsurance)ST000025UnMarshall(mappingFilePath,s);
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
//		String s1 = ST000025ResMarshller();
//		System.out.println("保单状态查询(ST000025)应答...");
//		System.out.println(s1);
//		mappingFilePath = "D:/Workspaces/workspace_sinatay/portalModule/src/main/resources/castorMappingFile/ST000025_Res_CastorMapping.xml";
//		TXInsurance txi1 = (TXInsurance)ST000025UnMarshall(mappingFilePath,s1);
//		systemObject(txi1);
	}
	
	public static void invokeWebService() throws Exception{
		String requestXML = ST000025ReqMarshller();
//		System.out.println(requestXML);
//		requestXML = "<?xml version=\"1.0\" encoding=\"GBK\"?><TranData><BaseInfo><TransrDate>20131213</TransrDate><TransrTime>18:56:53</TransrTime><TellerNo>0001</TellerNo><TransrNo>DHF20131213000000106</TransrNo><SaleChannel>W</SaleChannel><SellType>20</SellType><FunctionFlag>ST000025</FunctionFlag><Source>DHF</Source></BaseInfo><TranRequest><QueryData><PageNo>1</PageNo><PageRowNum>50</PageRowNum><QueryType>04</QueryType><Params><ParamCount>1</ParamCount><Param><Key>ProposalContNo</Key><Value>340110000000055</Value></Param></Params></QueryData></TranRequest></TranData>";
//	    System.out.println("大黄蜂请求...");
		System.out.println("大黄蜂保单状态查询(ST000025)请求...");
		System.out.println(requestXML);
		requestXML= AES.Encrypt(requestXML,"1234567812345678");
		AxisClient axisClient = new AxisClient();
		//http://10.20.3.21:7001/services/portal
		//http://127.0.0.1:8082/online/services/portal
		String url = "http://127.0.0.1:9001/online/services/portal";
		url = "http://xtmall-em-dat.sinatay.com/services/portal";
		String responseXML = axisClient.sendRPCRequestXML(url, "http://fascade.service.dhf.ebusiness.sinosoft.com.cn", "webService", requestXML);
		System.out.println("大黄蜂保单状态查询(ST000025)应答...");
		System.out.println(AES.Decrypt(responseXML,"1234567812345678"));
	}
	
	public static void systemObject(TXInsurance txi){
		System.out.println("txi: "+txi);
	}

	public static String ST000025ReqMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String serialNumber = GeneratorTransSerialNumber.generatorTransSerialNumber();
		String mappingFilePath = "E:/e_workdir/ClearCase/liuchen_em__dzsw/dzsw_pro/mall/portalModule/src/main/resources/castorMappingFile/ST000025_Req_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("DHF"+serialNumber);
			txInsurance.setTransType("ST000025");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("DHF");
			
			TranRequest tranRequest = new TranRequest();
 
			QueryData queryData = new QueryData();
			queryData.setPageNo(1);
			queryData.setPageRowNum(50);
			queryData.setQueryType("04");
			Params ps = new Params();
			ps.setParamCount(1);
			Param p = new Param();
			p.setKey("ProposalContNo");
			p.setValue("340110000000075");
			List<Param> listParams = new ArrayList<Param>();
			listParams.add(p);
			ps.setParam(listParams);
			queryData.setParams(ps);
			tranRequest.setQueryData(queryData);
			
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
	
	public static String ST000025ResMarshller(){
		StringWriter sw = new StringWriter();
		Date transDate = new Date();
		String mappingFilePath = "E:/e_workdir/ClearCase/liuchen_em__dzsw/dzsw_pro/mall/portalModule/src/main/resources/castorMappingFile/ST000025_Res_CastorMapping.xml";
		try {
			TXInsurance txInsurance = new TXInsurance();
			txInsurance.setTransRefGUID("201208220008");
			txInsurance.setTransType("ST000025");
			txInsurance.setTransExeDate(DateUtils.formatDate(transDate, "yyyyMMdd"));
			txInsurance.setTransExeTime(DateUtils.formatDate(transDate, "HHmmss"));
			txInsurance.setTellerNo("0001");
			txInsurance.setSaleChannel("W");
			txInsurance.setSellType("20");
			txInsurance.setSource("DHF");
			
			TranResponse tranResponse = new TranResponse();
			tranResponse.setFlag("1");
			tranResponse.setDesc("交易成功!");
			
			QueryData queryData = new QueryData();
			queryData.setPageNo(1);
			queryData.setPageRowNum(50);
			queryData.setPageTotal(4);
			queryData.setTotalRowNum(200);
			queryData.setQueryType("04");
			Datas ds = new Datas();
			ds.setDataCount(1);
			List<Data> listData = new ArrayList<Data>();
			Data d = new Data();
			d.setCol01("");
			d.setCol02("");
			listData.add(d);
			ds.setData(listData);
			queryData.setDatas(ds);
			
			tranResponse.setQueryData(queryData);
			
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
	
	public static Object ST000025UnMarshall(String mappingFilePath, String xmlString){
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
