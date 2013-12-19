package cn.com.sinosoft.portalModule.transport.service;

import java.io.IOException;
import java.util.List;

import cn.com.sinosoft.ebusiness.log.ImplTraced;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.portalModuleException;
import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.transport.transactionObject.BusinessResultDatum;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.portalModule.transport.transactionObject.TransMessage;

public interface InterfaceTransportService {

	/**
	 * Axis2 RPC ��������
	 * @param bussinessObject
	 * @param transCode
	 * @param requestURL
	 * @return
	 * @throws Exception 
	 */
	public abstract Object sendRPCRequestXML(Object bussinessObject, String transCode) throws Exception;
	
	/**
	 * Axis2 RPC �������� 
	 * @param bussinessObject ҵ�����
	 * @param transMessage ���׶���
	 * @param transCode ���״���
	 * @return
	 * @throws Exception
	 */
	public abstract Object sendRPCRequestXML(Object bussinessObject, TransMessage transMessage, String transCode) throws Exception;
	
	/**
	 * Axis2 RPC �������� 
	 * @param bussinessObject ҵ�����
	 * @param transMessage ���׶���
	 * @param transCode ���״���
	 * @return
	 * @throws Exception
	 */
	public abstract Object sendRPCRequestXML(Object bussinessObject, TXInsurance txInsurance, String transCode) throws Exception;
	
	/**
	 * Servlet �������� 
	 * @param bussinessObject ҵ�����
	 * @param transMessage ���׶���
	 * @param transCode ���״���
	 * @return
	 * @throws Exception
	 */
	public abstract Object sendServletRequestXML(Object bussinessObject, TXInsurance txInsurance, String transCode) throws Exception;
	
	/**
	 * Axis2 RPC ��������
	 * @param bussinessObject
	 * @param transCode
	 * @param requestURL
	 * @return
	 * @throws Exception 
	 */
	public abstract List<BusinessResultDatum> sendRPCRequestXML(List<Object> businessDatum, String transCode)
			throws Exception;
	
	/**
	 * Axis2 RPC 
	 * @param businessDatum ��������ҵ�����
	 * @param txInsuranceExtension ������չ��Ϣ
	 * @param transCode ���״���
	 * @return
	 * @throws Exception
	 */
	public List<BusinessResultDatum> sendRPCRequestXML(List<Object> businessDatum, TransMessage transMessage, String transCode) throws Exception; 
	
	/***
	 * RPC���ַ�����ʽ������������
	 * @param interfaceInfo �ӿ���Ϣ
	 * @param requestXML ������Ϣ
	 * @return Ӧ����
	 * @throws Exception 
	 */
	@ImplTraced
	public abstract String sendRPCXML(String transCode, String requestXML)
			throws Exception;

	/***
	 * 
	 * ��ȡ�ӿ���Ϣ
	 * @param transCode ���״���
	 * @return �ӿ���Ϣ
	 * @throws portalModuleException
	 */
	@ImplTraced
	public abstract PortalInterface findPortalInterfaceByTransCode(
			String transCode) throws portalModuleException;

	/**
	 * ��ȡ�ӿ���Ϣ
	 * @param transCode ���״���
	 * @param loginName �˺���
	 * @return
	 * @throws portalModuleException
	 */
	@ImplTraced
	public abstract PortalInterface findPortalInterface(String transCode,
			String loginName) throws portalModuleException;

	/**
	 * ��ȡ�������
	 * @param requestXML ������
	 * @param transCode ���״���
	 * @param messageType ������Ϣ����
	 * @return
	 */
	public abstract Object unMarshall(String requestXML, String transCode,
			MessageType messageType) throws IOException;

	/**
	 * ��װ����
	 * @param businessDatum ҵ�����
	 * @param transMessage ������Ϣ����
	 * @param transCode ���״���
	 * @param messageType ��������
	 * @return
	 * @throws IOException 
	 */
	public abstract String marshaller(List<Object> businessDatum, TransMessage transMessage, String transCode, MessageType messageType) throws IOException;
	
	/**
	 * ��װ����
	 * @param businessDatum ҵ�����
	 * @param PortalInterface �ӿ���Ϣ
	 * @param transMessage ������Ϣ����
	 * @param messageType ��������
	 * @return
	 * @throws IOException 
	 */
	public abstract String marshaller(List<Object> businessDatum, TransMessage transMessage,PortalInterface portalInterface, 
			MessageType messageType) throws IOException; 

	/**
	 * ��ȡ�������
	 * @param requestXML
	 * @param transCode
	 * @param portalInterface
	 * @param messageType
	 * @return
	 * @throws IOException 
	 */
	public abstract Object unMarshall(String requestXML, String transCode, PortalInterface portalInterface, MessageType messageType) throws IOException;

	public abstract String sendServletRequestXML(String frontRequestXML,
			String transCode) throws Exception; 
}