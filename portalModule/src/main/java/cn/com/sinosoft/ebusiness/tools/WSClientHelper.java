package cn.com.sinosoft.ebusiness.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.transport.message.PortalMessageUtils;

/**
 * ������ü����÷�����װ
 * 
 * create by zhuxuyang.
 * 2013.08.27
 */
public class WSClientHelper {
	private String endPoint;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static Logger log = LoggerFactory.getLogger(WSClientHelper.class);

	public WSClientHelper(){
	}
	public WSClientHelper(String endPoint){
		this.endPoint = endPoint;
	}
	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * �����м�ӿ�ƽ̨�ӿ�
	 * 
	 * @return ���ر����ַ���
	 * @param xmlStr:�������ַ���
	 */
	public String submitGBK(String xmlStr){
		if(endPoint == null){
			String sql = "select codecorerelation from ge_code where codetype = 'EndPoint'";
			List<Object> objectList = new ArrayList<Object>();
			List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, objectList.toArray());
			if(list.size() != 0){
				endPoint = list.get(0).get("codecorerelation").toString();
			}else{
				endPoint = "";
			}
		}
		
		InputStream inputStream = null;
		String rString = null;
/*		try {*/
			Document doc = this.string2Doc(xmlStr);
			String transCode = doc.selectSingleNode("/TranData/BaseInfo/FunctionFlag").getText();
			String transRefGUID = doc.selectSingleNode("/TranData/BaseInfo/TransrNo").getText();
		try{
			PortalMessageUtils.recordMessageToFile(xmlStr, transCode, transRefGUID, MessageType.REQUEST);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			URL url = new URL(endPoint);
			URLConnection urlConn = url.openConnection();
			urlConn.setDoOutput(true);
			log.error("WSClientHelper��������ʼ��" + dateFormat(new Date()));
			OutputStream outputStream = urlConn.getOutputStream();
			log.error("WSClientHelper�������������" + dateFormat(new Date()));

			byte[] bytes = xmlStr.getBytes("GBK");
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
			inputStream = urlConn.getInputStream();
			byte[] rBytes = IOUtils.toByteArray(inputStream);
			rString = new String(rBytes, "GBK");
		}catch(Exception e){
			e.printStackTrace();
		}
			doc = this.string2Doc(rString);
		try{
			PortalMessageUtils.recordMessageToFile(rString, transCode, transRefGUID, MessageType.RESPONSE);
		}catch(Exception e){
			e.printStackTrace();
		}
			return rString;
/*		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}*/
	}

	/**
	 * ����Doc������
	 * 
	 * @return �Ƿ�ɹ�
	 * @param document:Doc����
	 * @param filename:�����ļ�Ŀ¼
	 */
	public boolean saveDoc(Document document, String filename) {
		boolean flag = true;
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("GBK");
			XMLWriter writer = new XMLWriter(
					new FileWriter(new File(filename)), format);
			writer.write(document);
			writer.close();
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	/**
	 * �ӱ��ض���Doc
	 * 
	 * @return Doc����
	 * @param filename:�����ļ�Ŀ¼
	 */
	public Document loadDoc(String filename) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			document = saxReader.read(new File(filename));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * ��DocumentתΪString
	 * 
	 * @return
	 * @param document:XMLDoc
	 */
	public String doc2String(Document document) {
		String s = "";
		try {
			// ʹ�������������ת��
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			OutputFormat format = new OutputFormat("  ", true, "GBK");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString("GBK");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}

	/**
	 * ��StringתΪDocument
	 * 
	 * @return
	 * @param s:xml��ʽ���ַ���
	 */
	public Document string2Doc(String s) {
		Document doc = null;
		try {
			doc = DocumentHelper.parseText(s);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return doc;
	}
	
	public void saveTransaction(Document doc, MessageType messageType){
		String transCode = doc.selectSingleNode("/TranData/BaseInfo/FunctionFlag").getText();
		String transRefGUID = doc.selectSingleNode("/TranData/BaseInfo/TransrNo").getText();
		
		try {
			PortalMessageUtils.recordMessageToFile(this.doc2String(doc), transCode, transRefGUID, messageType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String dateFormat(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}	
	
	/*public Document easySubmit(Document docRequest){
		try {
			this.saveTransaction(docRequest, MessageType.REQUEST);
			String sRequst = this.doc2String(docRequest);
			String sResponse = this.submitGBK(sRequst);
			Document docResponse = this.string2Doc(sResponse);
			this.saveTransaction(docResponse, MessageType.RESPONSE);
			return docResponse;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/
	
	public static void main(String[] args) throws DocumentException {
		Document doc = null;
		String s="<?xml version='1.0' encoding='GBK'?><TranData><TranResponse><Flag>1</Flag><Desc>���׳ɹ�</Desc></TranResponse></TranData>";
		doc=DocumentHelper.parseText(s);
		System.out.println(doc);
		Element tTranResponse = (Element) doc.selectSingleNode("/TranData/TranResponse");
		tTranResponse.selectSingleNode("/Flag").getText();
		System.out.println(tTranResponse.getText());

		/*
		
		
		String inFile = "D:\\testXML\\ebusiness\\010050.xml";
		inFile = "E:/workdir/workspace_37_sinatay/portalModule/src/test/java/test/1111.xml";
		String outFile = "D:\\testXML\\ebusiness\\010050_res.xml";
		WSClientHelper ws = new WSClientHelper();
		ws.setEndPoint("http://10.20.3.147:9001/SIP/URLServer");
		try {
			String rs = ws.submitGBK(ws.doc2String(ws.loadDoc(inFile)));
			System.out.println(rs);
//			ws.saveDoc(ws.string2Doc(rs), outFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/}
}
