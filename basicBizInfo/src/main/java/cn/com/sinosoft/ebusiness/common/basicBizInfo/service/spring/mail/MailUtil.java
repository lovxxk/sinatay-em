package cn.com.sinosoft.ebusiness.common.basicBizInfo.service.spring.mail;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class MailUtil {
	/**
	 * ��ȡ�ʼ������ļ�����ȡ���������Ϣ��from��subject��velocityPath��

	 * @param tagName
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static String getConfigInfo(String tagName) throws ParserConfigurationException, SAXException, IOException{
		String tagValue = "";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(MailUtil.class.getResource("resource.xml").getPath());
			Element element = (Element)doc.getElementsByTagName(tagName).item(0);
			tagValue = element.getFirstChild().getNodeValue();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw e;
		} catch (SAXException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return tagValue;
	}
	
	/**
	 * �����ַ�ɿ����ж�

	 * @param addr �ʼ���ַ
	 * @return
	 */
	public static boolean isAddressValidate(String addr){
		/* Ϊ�˱�֤B2Cǰ��̨У��һ����Ҫ��java��У��ȥ��
		if(addr == null || addr.trim().length() < 1){
			return false;
		}
		
		Pattern addrPattern = Pattern.compile("^\\.|^\\@");
		Matcher matcher = addrPattern.matcher(addr);
		if(matcher.find()){
			// System.out.println("EMAIL��ַ������'.'��'@'��Ϊ��ʼ�ַ�");
			return false;
		}
		
		addrPattern = Pattern.compile("^www\\.");
		matcher = addrPattern.matcher(addr);
		if(matcher.find()){
			// System.out.println("EMAIL��ַ������'www.'��ʼ");
			return false;			
		}
		
		addrPattern = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
		matcher = addrPattern.matcher(addr);
		if(matcher.find()){
			// System.out.println("EMAIL��ַ�����Ƿ��ַ�������ð�š������Լ�����ĸΪ���ֵ�");
			return false;			
		}*/

		return true;
	}
}

