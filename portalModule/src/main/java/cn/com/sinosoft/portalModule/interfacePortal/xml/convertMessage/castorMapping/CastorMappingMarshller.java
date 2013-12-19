package cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping;

import java.io.IOException;
import java.io.StringWriter;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;

public class CastorMappingMarshller {
	
	private static final String MAPPINGENCODINGTYPE = "GBK";
	
	/**
	 * 组装报文
	 * @param object 对象
	 * @param encoding 编码集
	 * @param filePath 映射配置文件
	 * @return
	 */
	public static String marshaller(Object object, String encoding, String filePath) {
		Mapping mapping = new Mapping();
		StringWriter sw = new StringWriter();
		try {
			mapping.loadMapping(filePath);
			Marshaller fundMarshaller = new Marshaller(sw);
			fundMarshaller.setMapping(mapping);
			fundMarshaller.setEncoding(encoding);
			fundMarshaller.marshal(object);
			return sw.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} 
		return null;
		
	}
	
	/**
	 * 组装报文
	 * @param object 对象
	 * @param filePath 映射配置文件
	 * @return
	 */
	public static String marshaller(Object object, String filePath) {
		return marshaller(object, MAPPINGENCODINGTYPE, filePath);
	}
	
	/**
	 * 组装报文
	 * @param object 对象
	 * @param encoding 编码集
	 * @param mapping 映射对象
	 * @return
	 */
	public static String marshaller(Object object, String encoding, Mapping mapping) {
		try {
			StringWriter sw = new StringWriter();
			Marshaller fundMarshaller = new Marshaller(sw);
			fundMarshaller.setEncoding(encoding);
			fundMarshaller.setMapping(mapping);
			fundMarshaller.marshal(object);
			return sw.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		} 
		return null;
		
	}
	
	/**
	 * 组装报文
	 * @param object 对象
	 * @param mapping 映射对象
	 * @return
	 */
	public static String marshaller(Object object, Mapping mapping) {
		return marshaller(object, MAPPINGENCODINGTYPE, mapping);
		
	}
}
