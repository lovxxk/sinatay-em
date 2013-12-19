package cn.com.sinosoft.portalModule.transport.sinatay;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import cn.com.sinosoft.portalModule.transport.transactionObject.TransMessage;

public class Test {
	public static void main(String[] args) {
		
		String xml = beanToXml();
		System.out.println(xml);

		String mappingFilePath = "F:/sinatayBusiness/portalModule/src/main/resources/castorMappingFile/phoneValidateCode_Res_CastorMapping.xml";
		StringBuffer result = new StringBuffer("<TranData><BaseInfo><TransrDate>20120817</TransrDate><TransrTime>13:45:58</TransrTime><TellerNo>0001</TellerNo><TransrNo>201208170023</TransrNo><SaleChannel>1</SaleChannel><SellType>01</SellType><FunctionFlag>ST000024</FunctionFlag><Source>PAD</Source></BaseInfo><TranResponse><Flag>1</Flag><Desc>交易成功!</Desc><LCCont><ProposalContNo>201208170024</ProposalContNo></LCCont></TranResponse></TranData>");
		xmlToBean(mappingFilePath, result.toString());
	}

	private static Object xmlToBean(String mappingFilePath, String xmlString) {
		Object object = new Object();
		try {
			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingFilePath);
			StringReader sr = new StringReader(xmlString);
			Unmarshaller unMarshaller = new Unmarshaller(mapping);
			TranData tran = (TranData) unMarshaller.unmarshal(sr);
			System.out.println(tran.getBaseInfo().getSaleChannel());
			System.out.println(tran.getBaseInfo().getSellType());
			System.out.println(tran.getBaseInfo().getSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public static String beanToXml() {
		StringWriter sw = new StringWriter();
		String mappingFilePath = "F:/sinatayBusiness/portalModule/src/main/resources/castorMappingFile/phoneValidateCode_Req_CastorMapping.xml";
		try {
			TranData tranData = new TranData();

			BaseInfo baseInfo = new BaseInfo();
			baseInfo.setFunctionFlag("123");
			baseInfo.setSaleChannel("渠道");
			baseInfo.setSellType("类型");
			baseInfo.setSource("source");
			baseInfo.setTellerNo("333333");
			baseInfo.setTransrDate(new Date());
			baseInfo.setTransrNo("transrNo");
			baseInfo.setTransrTime("12345678");

			tranData.setBaseInfo(baseInfo);

			TranRequest tranRequest = new TranRequest();
			LCCont lCCont = new LCCont();
			LCAppnt lCAppnt = new LCAppnt();
			lCAppnt.setMobilePhoneNumber("123123123");
			lCAppnt.setFullName("name");
			lCCont.setLcAppnt(lCAppnt);
			lCCont.setPassword("123");
			lCCont.setProposalContNo("123123");
			lCCont.setSmsMessage("测试短信");

			tranRequest.setLcCont(lCCont);
			tranData.setTranRequest(tranRequest);

			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingFilePath);
			Marshaller fundMarshaller = new Marshaller(sw);
			fundMarshaller.setMapping(mapping);
			fundMarshaller.setEncoding("GBK");

			fundMarshaller.marshal(tranData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

	public static Object INSURED001UnMarshall(String mappingFilePath,
			String xmlString) {
		Object object = new Object();
		try {
			Mapping mapping = new Mapping();
			mapping.loadMapping(mappingFilePath);
			StringReader sr = new StringReader(xmlString);
			Unmarshaller unMarshaller = new Unmarshaller(mapping);
			TransMessage transMessage = (TransMessage) unMarshaller
					.unmarshal(sr);
			object = transMessage.getInsurance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
}
