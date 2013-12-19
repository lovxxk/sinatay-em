package cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping;

import java.io.IOException;
import java.io.StringReader;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;

public class CastorMappingUnMarshall {

	/***
	 * 
	 * @param xmlString
	 * @param mappingFilePath
	 * @return
	 */
	public static  Object unmarshallMessage(String xmlString, String mappingFilePath) {
		Mapping mapping = new Mapping();
		try {
			mapping.loadMapping(mappingFilePath);
			StringReader sr = new StringReader(xmlString);
			Unmarshaller unMarshaller = new Unmarshaller(mapping);
			return unMarshaller.unmarshal(sr);
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

	/***
	 * 
	 * @param xmlString
	 * @param mapping
	 * @return
	 */
	public static Object unmarshallMessage(String xmlString, Mapping mapping) {
		try {
			StringReader sr = new StringReader(xmlString);
			Unmarshaller unMarshaller = new Unmarshaller(mapping);
			return unMarshaller.unmarshal(sr);
		} catch (MappingException e) {
			e.printStackTrace();
		} catch (MarshalException e) {
			e.printStackTrace();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		return null;

	}

}
