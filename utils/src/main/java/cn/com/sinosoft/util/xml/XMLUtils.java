package cn.com.sinosoft.util.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtils {
	
	private static final String xmlEncoding = "GBK";
	
	/**
	 * ��ʽ��XML�ַ��� Ĭ�ϱ��ı��뼯GBK
	 * @param xmlStr
	 * @return
	 */
	public static String formatXML(String xmlStr){
        return formatXML(xmlStr, xmlEncoding);  
	}
	
	/**
	 * ��ʽ��XML�ַ���
	 * @param xmlStr XML�ַ���
	 * @param xmlEncoding XML���뼯
	 * @return
	 */
	public static String formatXML(String xmlStr, String xmlEncoding){
		StringWriter out = null;  
        try{  
        	SAXReader reader = new SAXReader();
            StringReader in = new StringReader(xmlStr);
            Document doc = reader.read(in);
            OutputFormat format = OutputFormat.createPrettyPrint();  
            format.setEncoding(xmlEncoding);
            out = new StringWriter();  
            XMLWriter writer = new XMLWriter(out, format);  
            writer.write(doc);  
        } catch (IOException e){  
           e.printStackTrace();  
        } catch (DocumentException e) {
			e.printStackTrace();
		} finally{  
            try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}   
        }  
        return out.toString();  
	}
	
	/**
	 * ��XML�ַ���д���ļ�Ĭ�ϱ��뼯GBK
	 * @param xmlStr XML�ַ���
	 * @param fileName �ļ���
	 * @throws IOException 
	 */
	public static void writeFormatXMLToFile(String xmlStr, String fileName) throws IOException {
		writeFormatXMLToFile(xmlStr, fileName, xmlEncoding);
	}
	
	/**
	 * ��XML�ַ���д���ļ� 
	 * @param xmlStr XML�ַ���
	 * @param fileName �ļ���
	 * @param xmlEncoding XML���뼯GBK
	 * @throws IOException
	 */
	public static void writeFormatXMLToFile(String xmlStr, String fileName, String xmlEncoding) throws IOException {
		FileUtils.writeStringToFile(new File(fileName), formatXML(xmlStr, xmlEncoding));
	}
	
	/**
	 * ��XML�ַ���д���ļ�  
	 * @param xmlStr XML�ַ���
	 * @param fileName �ļ���
	 * @throws IOException
	 */
	public static void writeXMLToFile(String xmlStr, String fileName) throws IOException {
		FileUtils.writeStringToFile(new File(fileName), xmlStr);
	}
	
	public static void main(String[] args) throws IOException {
		writeFormatXMLToFile(FileUtils.readFileToString(new File("C:/Users/zhaozhentao/Desktop/1111.xml")), "C:/Users/zhaozhentao/Desktop/teddst.xml");
	}
}
