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
	 * ��װ����
	 * @param object ����
	 * @param encoding ���뼯
	 * @param filePath ӳ�������ļ�
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
	 * ��װ����
	 * @param object ����
	 * @param filePath ӳ�������ļ�
	 * @return
	 */
	public static String marshaller(Object object, String filePath) {
		return marshaller(object, MAPPINGENCODINGTYPE, filePath);
	}
	
	/**
	 * ��װ����
	 * @param object ����
	 * @param encoding ���뼯
	 * @param mapping ӳ�����
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
	 * ��װ����
	 * @param object ����
	 * @param mapping ӳ�����
	 * @return
	 */
	public static String marshaller(Object object, Mapping mapping) {
		return marshaller(object, MAPPINGENCODINGTYPE, mapping);
		
	}
}
