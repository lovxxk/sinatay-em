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
	 * 读取邮件配置文件，获取相关配置信息（from、subject和velocityPath）

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
	 * 邮箱地址可靠性判断

	 * @param addr 邮件地址
	 * @return
	 */
	public static boolean isAddressValidate(String addr){
		/* 为了保证B2C前后台校验一致需要将java的校验去掉
		if(addr == null || addr.trim().length() < 1){
			return false;
		}
		
		Pattern addrPattern = Pattern.compile("^\\.|^\\@");
		Matcher matcher = addrPattern.matcher(addr);
		if(matcher.find()){
			// System.out.println("EMAIL地址不能以'.'或'@'作为起始字符");
			return false;
		}
		
		addrPattern = Pattern.compile("^www\\.");
		matcher = addrPattern.matcher(addr);
		if(matcher.find()){
			// System.out.println("EMAIL地址不能以'www.'起始");
			return false;			
		}
		
		addrPattern = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
		matcher = addrPattern.matcher(addr);
		if(matcher.find()){
			// System.out.println("EMAIL地址包含非法字符，比如冒号、逗号以及首字母为数字等");
			return false;			
		}*/

		return true;
	}
}

