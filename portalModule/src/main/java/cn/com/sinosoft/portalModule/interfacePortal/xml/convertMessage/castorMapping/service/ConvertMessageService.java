package cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.service;

public interface ConvertMessageService {

	/***
	 * 
	 * @param transCode
	 * @param mappingFilePath
	 * @return
	 */
	public abstract String marshaller(Object buinessData, String mappingFilePath);

	/***
	 * 
	 * @param buinessData
	 * @param transCode
	 * @param requestMethod
	 * @return
	 */
	public abstract String marshaller(Object buinessData, String transCode,
			String requestMethod);

	/***
	 * 
	 * @param className
	 * @param buinessData
	 * @param transCode
	 * @param requestMethod
	 * @return
	 */
	public abstract Object unMarshall(String buinessData,
			String transCode, String requestMethod);

	/***
	 * 
	 * @param className
	 * @param buinessData
	 * @param mappingFilePath
	 * @return
	 */
	public abstract Object unMarshall(String buinessData,
			String mappingFilePath);

	public abstract String marshaller(Object buinessData, String transCode, String encoding,
			String requestMethod);

	public abstract String marshallerEncoding(Object buinessData, String encoding,
			String mappingFilePath);

}
