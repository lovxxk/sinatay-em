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
	 * �����������json�ĸ�ʽ�洢�����׼�¼��
	 * @param requestObj �������
	 * @param transCode ���״���
	 * return transRefGuid ������ˮ��--���׼�¼������
	 */
	public abstract String writeRequestMessage(Object requestObj, String transCode);
	
	/**
	 * ��Ӧ�������json�ĸ�ʽ�洢�����׼�¼��
	 * @param responseObj Ӧ�����
	 * @param transCode ���״���
	 * @param transRefGuid ������ˮ��
	 */
	public abstract void writeResponseMessage(Object responseObj, String transCode, String transRefGuid);
	
	/**
	 * ����������Ӧ�������json�ĸ�ʽ�洢�����׼�¼��
	 * @param requestObj �������
	 * @param responseObj Ӧ�����
	 * @param transCode ���״���
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
