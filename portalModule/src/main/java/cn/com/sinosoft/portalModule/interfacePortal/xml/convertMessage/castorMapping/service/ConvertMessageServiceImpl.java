package cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.service;

import java.util.HashMap;
import java.util.Map;

import org.exolab.castor.mapping.Mapping;

import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.CastorMappingMarshller;
import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.CastorMappingUnMarshall;
import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.LoadMappingFiles;

public class ConvertMessageServiceImpl implements ConvertMessageService {
	
	private Map<String, Mapping> mappingFiles = new HashMap<String, Mapping>();

	/***
	 * @param transCode
	 * @param mappingFilePath
	 * @return
	 */
	public String marshaller(Object buinessData, String mappingFilePath) {

		return CastorMappingMarshller.marshaller(buinessData, mappingFilePath);

	}
	
	/**
	 * 
	 * @param buinessData
	 * @param encoding
	 * @param mappingFilePath
	 * @return
	 */
	public String marshallerEncoding(Object buinessData, String encoding, String mappingFilePath) {

		return CastorMappingMarshller.marshaller(buinessData, encoding, mappingFilePath);

	}
	
	/***
	 * 
	 * @param buinessData
	 * @param transCode
	 * @param requestMethod
	 * @return
	 */
	public String marshaller(Object buinessData, String transCode, String requestMethod) {
		Mapping mapping = getMapping(transCode, requestMethod);
		if (mapping != null) {
			return CastorMappingMarshller.marshaller(buinessData, mapping);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @param buinessData
	 * @param transCode
	 * @param encoding
	 * @param requestMethod
	 * @return
	 */
	public String marshaller(Object buinessData, String transCode, String encoding, String requestMethod) {
		Mapping mapping = getMapping(transCode, requestMethod);
		if (mapping != null) {
			return CastorMappingMarshller.marshaller(buinessData, encoding, mapping);
		} else {
			return null;
		}

	}
	
	/***
	 * 
	 * @param className
	 * @param buinessData
	 * @param transCode
	 * @param requestMethod
	 * @return
	 * 
	 */
	public Object unMarshall(String buinessData, String transCode, String requestMethod) {
		Mapping mapping = getMapping(transCode, requestMethod);
		if (mapping != null) {
			return CastorMappingUnMarshall.unmarshallMessage(buinessData, mapping);
		} else {
			return null;
		}
	}

	/***
	 * 
	 * @param className
	 * @param buinessData
	 * @param mappingFilePath
	 * @return
	 */
	public Object unMarshall(String buinessData, String mappingFilePath) {

		return CastorMappingUnMarshall.unmarshallMessage(buinessData, mappingFilePath);

	}
	
	/**
	 * getMapping by transCode And HandleMessageType
	 * @param transCode
	 * @param requestMethod
	 * @return
	 */
	public Mapping getMapping(String transCode, String messageType) {
		String key = transCode + "_" + messageType + "_" + "CastorMapping";
		Mapping mapping = mappingFiles.get(key);
		if (mapping == null) {
			LoadMappingFiles.loadMappingFiles(mappingFiles, transCode);
			mapping = mappingFiles.get(key);
			return mapping;
		} else {
			return mapping;
		}
	}
}
