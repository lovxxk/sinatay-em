package cn.com.sinosoft.portalModule.interfacePortal.webService.service;

import java.util.List;

import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.portalModuleException;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TXInsuranceXMLRequest;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TXInsuranceXMLResponse;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransMessageRequest;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransMessageResponse;
import cn.com.sinosoft.portalModule.opendata.service.spring.WSResult;

public interface WebService {
	
	public WSResult sendADBObjectToEsb(Object requestObject,String transCode);

	public abstract List sendRPCRequestXML(Object buinessData, String transCode) throws portalModuleException;

	public TXInsuranceXMLResponse sendRPCRequestXML(TXInsuranceXMLRequest txXMLReq, String transCode) throws portalModuleException;

	public abstract Object sendRPCRequestXMLForEcSeal(Object buinessData, String transCode) throws portalModuleException;

	public abstract TransMessageRequest getFrontRequestXML(String frontRequestXML, String transCode) throws portalModuleException;
	
	/**
	 * 把请求对象以json的格式存储到交易记录中
	 * @param requestObj 请求对象
	 * @param transCode 交易代码
	 * return transRefGuid 交易流水号--交易记录的主键
	 */
	public abstract String writeRequestMessage(Object requestObj, String transCode);
	
	/**
	 * 把应答对象以json的格式存储到交易记录中
	 * @param responseObj 应答对象
	 * @param transCode 交易代码
	 * @param transRefGuid 交易流水号
	 */
	public abstract void writeResponseMessage(Object responseObj, String transCode, String transRefGuid);
	
	/**
	 * 把请求对象和应答对象以json的格式存储到交易记录中
	 * @param requestObj 请求对象
	 * @param responseObj 应答对象
	 * @param transCode 交易代码
	 */
	public abstract void writeMessage(Object requestObj, Object responseObj, String transCode);

	public abstract String sendRPCFrontResponseXML(TransMessageResponse tmResp) throws portalModuleException;

	public abstract Object sendADBObject(Object requestObject, String setRequestObjectMethodName, String transCode) throws portalModuleException;
	public abstract Object sendADBObjectEhm(Object requestObject, String setRequestObjectMethodName, String transCode) throws portalModuleException; 

	public abstract Object[] sendRPCRequestMethod(String transCode, List param);

	public abstract void cleanAxis2TempFiles();

	public abstract Object sendADBObject(Object requestObject, String transCode);

	public abstract Object sendADBObject(Object requestObject, String reqURL,
			String setRequestObjectMethodName, String transCode) throws portalModuleException;
	
}
