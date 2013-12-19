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
	 * 格式化XML字符串 默认报文编码集GBK
	 * @param xmlStr
	 * @return
	 */
	public static String formatXML(String xmlStr){
        return formatXML(xmlStr, xmlEncoding);  
	}
	
	/**
	 * 格式化XML字符串
	 * @param xmlStr XML字符串
	 * @param xmlEncoding XML编码集
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
	 * 将XML字符串写入文件默认编码集GBK
	 * @param xmlStr XML字符串
	 * @param fileName 文件名
	 * @throws IOException 
	 */
	public static void writeFormatXMLToFile(String xmlStr, String fileName) throws IOException {
		writeFormatXMLToFile(xmlStr, fileName, xmlEncoding);
	}
	
	/**
	 * 将XML字符串写入文件 
	 * @param xmlStr XML字符串
	 * @param fileName 文件名
	 * @param xmlEncoding XML编码集GBK
	 * @throws IOException
	 */
	public static void writeFormatXMLToFile(String xmlStr, String fileName, String xmlEncoding) throws IOException {
		FileUtils.writeStringToFile(new File(fileName), formatXML(xmlStr, xmlEncoding));
	}
	
	/**
	 * 将XML字符串写入文件  
	 * @param xmlStr XML字符串
	 * @param fileName 文件名
	 * @throws IOException
	 */
	public static void writeXMLToFile(String xmlStr, String fileName) throws IOException {
		FileUtils.writeStringToFile(new File(fileName), xmlStr);
	}
	
	public static void main(String[] args) throws IOException {
		writeFormatXMLToFile(FileUtils.readFileToString(new File("C:/Users/zhaozhentao/Desktop/1111.xml")), "C:/Users/zhaozhentao/Desktop/teddst.xml");
	}
}
