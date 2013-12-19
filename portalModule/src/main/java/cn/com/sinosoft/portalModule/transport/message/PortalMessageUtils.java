package cn.com.sinosoft.portalModule.transport.message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalService;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.portalModule.transport.transactionObject.TransMessage;
import cn.com.sinosoft.util.string.StringUtils;
import cn.com.sinosoft.util.xml.XMLUtils;

public class PortalMessageUtils implements PortalService {

	public static void recordMessageToFile(String xmlStr, TransMessage transMessage, MessageType messageType) throws IOException {
		StringBuilder s = new StringBuilder();
		s.append("\n--------------------------------------\n");
		s.append("\nMessageType: ");
		s.append(transMessage.getInsurance().getTransType());
		s.append("\nTransRefGUID: ");
		s.append(transMessage.getInsurance().getTransRefGUID());
		s.append("\nTransCode: ");
		s.append(messageType.getDataENName());
		s.append("\nXML: ");
		s.append(xmlStr);
		s.append("\n--------------------------------------\n");
		System.out.println(s.toString());
		
		PortalMessageUtils pmutil = new PortalMessageUtils();
		StringBuffer fileName = new StringBuffer();
		fileName.append(pmutil.getXmlFilePath());
		fileName.append(fileDate.format(new Date()));
		fileName.append("/");
		fileName.append(transMessage.getInsurance().getTransType());
		fileName.append("/");
		fileName.append(transMessage.getInsurance().getTransRefGUID() +  "_" + transMessage.getInsurance().getTransType() + "_" + messageType.getDataENName()+ ".xml");
		if (StringUtils.isNotBlank(xmlStr) && Pattern.compile("<?xml version=").matcher(xmlStr).find()) {
			XMLUtils.writeFormatXMLToFile(xmlStr, fileName.toString(), xmlEncoding);
		}
	}
	
	public static void recordMessageToFile(String xmlStr, TXInsurance txInsurance, MessageType messageType) throws IOException {
		StringBuilder s = new StringBuilder();
		s.append("\n--------------------------------------\n");
		s.append("\nMessageType: ");
		s.append(txInsurance.getTransType());
		s.append("\nTransRefGUID: ");
		s.append(txInsurance.getTransRefGUID());
		s.append("\nTransCode: ");
		s.append(messageType.getDataENName());
		s.append("\nXML: ");
		s.append(xmlStr);
		s.append("\n--------------------------------------\n");
		System.out.println(s.toString());

		PortalMessageUtils pmutil = new PortalMessageUtils();
		StringBuffer fileName = new StringBuffer();
		fileName.append(pmutil.getXmlFilePath());
		fileName.append(fileDate.format(new Date()));
		fileName.append("/");
		fileName.append(txInsurance.getTransType());
		fileName.append("/");
		fileName.append(txInsurance.getTransRefGUID() +  "_" + txInsurance.getTransType() + "_" + messageType.getDataENName()+ ".xml");
		if (StringUtils.isNotBlank(xmlStr) && Pattern.compile("<?xml version=").matcher(xmlStr).find()) {
			XMLUtils.writeFormatXMLToFile(xmlStr, fileName.toString(), xmlEncoding);
		}
	}

	public static void recordMessageToFile(String xmlStr, String transCode, String transRefGUID, MessageType messageType) throws IOException {
		StringBuilder s = new StringBuilder();
		s.append("\n--------------------------------------\n");
		s.append("\nMessageType: ");
		s.append(messageType.getDataName());
		s.append("\nTransRefGUID: ");
		s.append(transRefGUID);
		s.append("\nTransCode: ");
		s.append(transCode);
		s.append("\nXML: ");
		s.append(xmlStr);
		s.append("\n--------------------------------------\n");
		System.out.println(s.toString());

		PortalMessageUtils pmutil = new PortalMessageUtils();
		StringBuffer fileName = new StringBuffer();
		fileName.append(pmutil.getXmlFilePath());
		fileName.append(fileDate.format(new Date()));
		fileName.append("/");
		fileName.append(transCode);
		fileName.append("/");
		fileName.append(transRefGUID +  "_" + transCode + "_" + messageType.getDataENName() + ".xml");
		if (StringUtils.isNotBlank(xmlStr) && Pattern.compile("<?xml version=").matcher(xmlStr).find()) {
			XMLUtils.writeFormatXMLToFile(xmlStr, fileName.toString(), xmlEncoding);
		}
	}

	public static void recordMessageToFileNotFormat(String xmlStr, String transCode, String transRefGUID, MessageType messageType) throws IOException {
		StringBuilder s = new StringBuilder();
		s.append("\n--------------------------------------\n");
		s.append("\nMessageType: ");
		s.append(messageType.getDataName());
		s.append("\nTransRefGUID: ");
		s.append(transRefGUID);
		s.append("\nTransCode: ");
		s.append(transCode);
		s.append("\nXML: ");
		s.append(xmlStr);
		s.append("\n--------------------------------------\n");
		System.out.println(s.toString());
		
		PortalMessageUtils pmutil = new PortalMessageUtils();
		StringBuffer fileName = new StringBuffer();
		fileName.append(pmutil.getXmlFilePath());
		fileName.append(fileDate.format(new Date()));
		fileName.append("/");
		fileName.append(transCode);
		fileName.append("/");
		fileName.append(transRefGUID + "_" + messageType.getDataENName() + ".xml");
		if (StringUtils.isNotBlank(xmlStr) && Pattern.compile("<?xml version=").matcher(xmlStr).find()) {
			XMLUtils.writeXMLToFile(xmlStr, fileName.toString());
		}
	}

	public static void recordMessageToFile(String xmlStr, String transCode) throws IOException {
		StringBuilder s = new StringBuilder();
		s.append("\n--------------------------------------\n");
		s.append("\nTransCode: ");
		s.append(transCode);
		s.append("\nXML: ");
		s.append(xmlStr);
		s.append("\n--------------------------------------\n");
		System.out.println(s.toString());

		PortalMessageUtils pmutil = new PortalMessageUtils();
		Date recordMessageFileDate = new Date();
		StringBuffer fileName = new StringBuffer();
		fileName.append(pmutil.getXmlFilePath());
		fileName.append(fileDate.format(recordMessageFileDate));
		fileName.append("/");
		fileName.append(transCode);
		fileName.append("/");
		fileName.append(fileDateTime.format(recordMessageFileDate) + "_" + transCode + ".xml");
		if (StringUtils.isNotBlank(xmlStr) && Pattern.compile("<?xml version=").matcher(xmlStr).find()) {
			XMLUtils.writeFormatXMLToFile(xmlStr, fileName.toString(), xmlEncoding);
		}
	}

	public static void recordMessageToFile(String xmlStr) throws IOException {
		StringBuilder s = new StringBuilder();
		s.append("\n--------------------------------------\n");
		s.append("\nXML: ");
		s.append(xmlStr);
		s.append("\n--------------------------------------\n");
		System.out.println(s.toString());

		PortalMessageUtils pmutil = new PortalMessageUtils();
		Date recordMessageFileDate = new Date();
		StringBuffer fileName = new StringBuffer();
		fileName.append(pmutil.getXmlFilePath());
		fileName.append(fileDate.format(recordMessageFileDate));
		fileName.append("/");
		fileName.append(fileDateTime.format(recordMessageFileDate) + ".xml");
		if (StringUtils.isNotBlank(xmlStr) && Pattern.compile("<?xml version=").matcher(xmlStr).find()) {
			XMLUtils.writeFormatXMLToFile(xmlStr, fileName.toString(), xmlEncoding);
		}
	}

	public String getXmlFilePath(){
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{"classpath:/spring/applicationContext-dataAccess.xml"});
		ctx.refresh();
		JdbcTemplate jdbcTemplate = ctx.getBean("jdbcTemplate", JdbcTemplate.class);
		
		String xmlFilePath = "/bea/share/xml/";
		List<Map<String, Object>> objectMaps = new ArrayList<Map<String, Object>>(0);
		List<Object> objectList = new ArrayList<Object>(0);
		StringBuffer sql = new StringBuffer("select ");
		sql.append("t.CODECORERELATION");
		sql.append(" from ");
//		sql.append(schema);
		sql.append("ge_code t where t.CODETYPE =");
		sql.append(" 'appConfig' ");
		sql.append("and t.CODECODE =");
		sql.append(" '1' ");
		objectMaps = jdbcTemplate.queryForList(sql.toString(), objectList.toArray());
		if(objectMaps != null && !objectMaps.isEmpty() && objectMaps.get(0) != null){
			xmlFilePath = (String) objectMaps.get(0).get("CODECORERELATION");
		}
		return xmlFilePath;
	}
}
