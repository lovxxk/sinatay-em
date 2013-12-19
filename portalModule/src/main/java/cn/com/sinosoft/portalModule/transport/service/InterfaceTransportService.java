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
	 * Axis2 RPC 报文请求
	 * @param bussinessObject
	 * @param transCode
	 * @param requestURL
	 * @return
	 * @throws Exception 
	 */
	public abstract Object sendRPCRequestXML(Object bussinessObject, String transCode) throws Exception;
	
	/**
	 * Axis2 RPC 报文请求 
	 * @param bussinessObject 业务对象
	 * @param transMessage 交易对象
	 * @param transCode 交易代码
	 * @return
	 * @throws Exception
	 */
	public abstract Object sendRPCRequestXML(Object bussinessObject, TransMessage transMessage, String transCode) throws Exception;
	
	/**
	 * Axis2 RPC 报文请求 
	 * @param bussinessObject 业务对象
	 * @param transMessage 交易对象
	 * @param transCode 交易代码
	 * @return
	 * @throws Exception
	 */
	public abstract Object sendRPCRequestXML(Object bussinessObject, TXInsurance txInsurance, String transCode) throws Exception;
	
	/**
	 * Servlet 报文请求 
	 * @param bussinessObject 业务对象
	 * @param transMessage 交易对象
	 * @param transCode 交易代码
	 * @return
	 * @throws Exception
	 */
	public abstract Object sendServletRequestXML(Object bussinessObject, TXInsurance txInsurance, String transCode) throws Exception;
	
	/**
	 * Axis2 RPC 报文请求
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
	 * @param businessDatum 批量处理业务对象
	 * @param txInsuranceExtension 交易扩展信息
	 * @param transCode 交易代码
	 * @return
	 * @throws Exception
	 */
	public List<BusinessResultDatum> sendRPCRequestXML(List<Object> businessDatum, TransMessage transMessage, String transCode) throws Exception; 
	
	/***
	 * RPC以字符串形式发送请求数据
	 * @param interfaceInfo 接口信息
	 * @param requestXML 请求信息
	 * @return 应答报文
	 * @throws Exception 
	 */
	@ImplTraced
	public abstract String sendRPCXML(String transCode, String requestXML)
			throws Exception;

	/***
	 * 
	 * 获取接口信息
	 * @param transCode 交易代码
	 * @return 接口信息
	 * @throws portalModuleException
	 */
	@ImplTraced
	public abstract PortalInterface findPortalInterfaceByTransCode(
			String transCode) throws portalModuleException;

	/**
	 * 获取接口信息
	 * @param transCode 交易代码
	 * @param loginName 账号名
	 * @return
	 * @throws portalModuleException
	 */
	@ImplTraced
	public abstract PortalInterface findPortalInterface(String transCode,
			String loginName) throws portalModuleException;

	/**
	 * 获取请求对象
	 * @param requestXML 请求报文
	 * @param transCode 交易代码
	 * @param messageType 交易信息类型
	 * @return
	 */
	public abstract Object unMarshall(String requestXML, String transCode,
			MessageType messageType) throws IOException;

	/**
	 * 组装报文
	 * @param businessDatum 业务对象
	 * @param transMessage 交易信息对象
	 * @param transCode 交易代码
	 * @param messageType 交易类型
	 * @return
	 * @throws IOException 
	 */
	public abstract String marshaller(List<Object> businessDatum, TransMessage transMessage, String transCode, MessageType messageType) throws IOException;
	
	/**
	 * 组装报文
	 * @param businessDatum 业务对象
	 * @param PortalInterface 接口信息
	 * @param transMessage 交易信息对象
	 * @param messageType 交易类型
	 * @return
	 * @throws IOException 
	 */
	public abstract String marshaller(List<Object> businessDatum, TransMessage transMessage,PortalInterface portalInterface, 
			MessageType messageType) throws IOException; 

	/**
	 * 获取请求对象
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