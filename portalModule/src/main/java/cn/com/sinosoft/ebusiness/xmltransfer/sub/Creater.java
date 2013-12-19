package cn.com.sinosoft.ebusiness.xmltransfer.sub;

import java.util.Map;

import org.dom4j.Document;

public interface Creater<T> {
	public Document createXml(Map<String,Object> map);
	public T Xml2Object(Document doc);
}
