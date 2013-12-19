package cn.com.sinosoft.portalModule.transport.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import cn.com.sinosoft.ebusiness.log.ImplTraced;
import cn.com.sinosoft.portalModule.enumUtil.EncapsulationType;
import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.ReturnType;
import cn.com.sinosoft.portalModule.interfacePortal.client.servlet.ServletClient;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceHandlerMethodParameter;
import cn.com.sinosoft.portalModule.transport.abstractService.AbstractInterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.axis.AxisClient;
import cn.com.sinosoft.portalModule.transport.message.PortalMessageUtils;
import cn.com.sinosoft.portalModule.transport.transactionObject.BusinessResultDatum;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.portalModule.transport.transactionObject.TransMessage;
import cn.com.sinosoft.util.string.StringUtils;

public class InterfaceTransportServiceImpl extends AbstractInterfaceTransportService implements InterfaceTransportService {
	
	/**
	 * Axis2 RPC 报文请求
	 * @param bussinessObject 交易对象
	 * @param transCode 交易代码
	 * @return
	 * @throws Exception 
	 */
	public Object sendRPCRequestXML(Object bussinessObject, String transCode) throws Exception {
		return sendRPCRequestXML(bussinessObject, null, transCode);
	}
	
	/**
	 * Axis2 RPC 报文请求 
	 * @param bussinessObject 业务对象
	 * @param transMessage 交易对象
	 * @param transCode 交易代码
	 * @return
	 * @throws Exception
	 */
	public Object sendRPCRequestXML(Object bussinessObject, TransMessage transMessage, String transCode) throws Exception {
		Date transDate = new Date();
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		String requestXml = "";
		if (transMessage == null) {
			transMessage = new TransMessage();
		}
		if (EncapsulationType.NOT_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType())) {
			requestXml = txFactory.getConvertMessageService().marshaller(bussinessObject, transCode, portalInterface.getMessageEncoding(), MessageType.REQUEST.getDataENName());
		} else {
			super.marshallObject(portalInterface, bussinessObject, transMessage, transDate);
			requestXml = txFactory.getConvertMessageService().marshaller(transMessage, transCode, portalInterface.getMessageEncoding(), MessageType.REQUEST.getDataENName());
		}
		PortalMessageUtils.recordMessageToFile(requestXml, transMessage, MessageType.REQUEST);
		PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter = new PortalInterfaceHandlerMethodParameter();
		portalInterfaceHandlerMethodParameter.setParameterClassName(String.class.getName());
		portalInterfaceHandlerMethodParameter.setInputObjectValue(requestXml);
		String responseXML = (String)AxisClient.sendRPCRequestMethodSingleReturn(portalInterface, portalInterfaceHandlerMethodParameter);
		if (ReturnType.RETURN_NONE.getValue().equals(portalInterface.getReturnType())) {
			return null;
		} else if (ReturnType.RETURN_RESPONSE_XML.getValue().equals(portalInterface.getReturnType())) {
			return responseXML;
		}
		TransMessage returnTransMessage = (TransMessage) txFactory.getConvertMessageService().unMarshall(responseXML, transCode, MessageType.RESPONSE.getDataENName());
		PortalMessageUtils.recordMessageToFile(responseXML, returnTransMessage, MessageType.RESPONSE);
		if (ReturnType.RETURN_TRANSOBJ.getValue().equals(portalInterface.getReturnType())) {
			return returnTransMessage;
		} else if (ReturnType.RETURN_BUSINESSDATUM.getValue().equals(portalInterface.getReturnType())) {
			return returnTransMessage.getInsurance().getBusinessResultDatum().get(0);
		}
		return null;
	}
	
	/**
	 * Axis2 RPC 报文请求 
	 * @param bussinessObject 业务对象
	 * @param transMessage 交易对象
	 * @param transCode 交易代码
	 * @return
	 * @throws Exception
	 */
	public Object sendRPCRequestXML(Object bussinessObject, TXInsurance txInsurance, String transCode) throws Exception {
		Date transDate = new Date();
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		String requestXml = "";
		if (txInsurance == null) {
			txInsurance = new TXInsurance();
		}
		if (EncapsulationType.NOT_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType())) {
			requestXml = txFactory.getConvertMessageService().marshaller(bussinessObject, transCode, portalInterface.getMessageEncoding(), MessageType.REQUEST.getDataENName());
		} else {
			super.marshallObject(portalInterface, bussinessObject, txInsurance, transDate);
			requestXml = txFactory.getConvertMessageService().marshaller(txInsurance, transCode, portalInterface.getMessageEncoding(), MessageType.REQUEST.getDataENName());
		}
		PortalMessageUtils.recordMessageToFile(requestXml, txInsurance, MessageType.REQUEST);
		PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter = new PortalInterfaceHandlerMethodParameter();
		portalInterfaceHandlerMethodParameter.setParameterClassName(String.class.getName());
		portalInterfaceHandlerMethodParameter.setInputObjectValue(requestXml);
		String responseXML = (String)AxisClient.sendRPCRequestMethodSingleReturn(portalInterface, portalInterfaceHandlerMethodParameter);
		if (ReturnType.RETURN_NONE.getValue().equals(portalInterface.getReturnType())) {
			return null;
		} else if (ReturnType.RETURN_RESPONSE_XML.getValue().equals(portalInterface.getReturnType())) {
			return responseXML;
		}
		TXInsurance returnTXInsurance = (TXInsurance) txFactory.getConvertMessageService().unMarshall(responseXML, transCode, MessageType.RESPONSE.getDataENName());
		PortalMessageUtils.recordMessageToFile(responseXML, returnTXInsurance, MessageType.RESPONSE);
		if (ReturnType.RETURN_TRANSOBJ.getValue().equals(portalInterface.getReturnType())) {
			return returnTXInsurance;
		} else if (ReturnType.RETURN_BUSINESSDATUM.getValue().equals(portalInterface.getReturnType())) {
			return returnTXInsurance.getBusinessResultDatum().get(0);
		}
		return null;
	}
	
	/**
	 * Axis2 RPC 报文请求 
	 * @param bussinessObject 业务对象
	 * @param transMessage 交易对象
	 * @param transCode 交易代码
	 * @return
	 * @throws Exception
	 */
	public Object sendServletRequestXML(Object bussinessObject, TXInsurance txInsurance, String transCode) throws Exception {
		Date transDate = new Date();
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		String requestXml = "";
		if (txInsurance == null) {
			txInsurance = new TXInsurance();
		}
		if (EncapsulationType.NOT_ENCAPSULATION.getValue().equals(portalInterface.getEncapsulationType())) {
			requestXml = txFactory.getConvertMessageService().marshaller(bussinessObject, transCode, portalInterface.getMessageEncoding(), MessageType.REQUEST.getDataENName());
		} else {
			super.marshallObject(portalInterface, bussinessObject, txInsurance, transDate);
			requestXml = txFactory.getConvertMessageService().marshaller(txInsurance, transCode, portalInterface.getMessageEncoding(), MessageType.REQUEST.getDataENName());
		}
		PortalMessageUtils.recordMessageToFile(requestXml, txInsurance, MessageType.REQUEST);
		PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter = new PortalInterfaceHandlerMethodParameter();
		portalInterfaceHandlerMethodParameter.setParameterClassName(String.class.getName());
		portalInterfaceHandlerMethodParameter.setInputObjectValue(requestXml);
		String requestAddress = portalInterface.getRequestURL();
		String transPortType = portalInterface.getTransPortType();
		String RequestEcoding = portalInterface.getRequestEcoding();
		String responseXML = "";
		if(StringUtils.isNotBlank(transPortType) &&
				StringUtils.isNotBlank(requestAddress) &&
				StringUtils.isNotBlank(RequestEcoding) &&
				transPortType.equals("Axis2RPC")){
			responseXML = (String)AxisClient.sendRPCRequestMethodSingleReturn(portalInterface, portalInterfaceHandlerMethodParameter);
		}else if(StringUtils.isNotBlank(transPortType) &&
				StringUtils.isNotBlank(requestAddress) &&
				StringUtils.isNotBlank(RequestEcoding) &&
				transPortType.equals("Servlet")){
			responseXML = (String)ServletClient.sendRequestXML(requestAddress, requestXml, RequestEcoding);
		}
		if (ReturnType.RETURN_NONE.getValue().equals(portalInterface.getReturnType())) {
			return null;
		} else if (ReturnType.RETURN_RESPONSE_XML.getValue().equals(portalInterface.getReturnType())) {
			return responseXML;
		}
		if(StringUtils.isNotBlank(responseXML)){
			TXInsurance returnTXInsurance = (TXInsurance) txFactory.getConvertMessageService().unMarshall(responseXML, transCode, MessageType.RESPONSE.getDataENName());
			PortalMessageUtils.recordMessageToFile(responseXML, returnTXInsurance, MessageType.RESPONSE);
			if (ReturnType.RETURN_TRANSOBJ.getValue().equals(portalInterface.getReturnType())) {
				return returnTXInsurance;
			} else if (ReturnType.RETURN_BUSINESSDATUM.getValue().equals(portalInterface.getReturnType())) {
				return returnTXInsurance.getBusinessDatum().get(0);
			}
		}
		return null;
	}
	
	/**
	 * Axis2 RPC 报文请求
	 * @param bussinessObject
	 * @param transCode
	 * @param requestURL
	 * @return
	 * @throws Exception 
	 */
	public List<BusinessResultDatum> sendRPCRequestXML(List<Object> businessDatum, String transCode) throws Exception {
		return sendRPCRequestXML(businessDatum, null, transCode);
	}
	
	/**
	 * Axis2 RPC 
	 * @param businessDatum 批量处理业务对象
	 * @param txInsuranceExtension 交易扩展信息
	 * @param transCode 交易代码
	 * @return
	 * @throws Exception
	 */
	public List<BusinessResultDatum> sendRPCRequestXML(List<Object> businessDatum, TransMessage transMessage, String transCode) throws Exception {
		System.out.println("### sendRPCRequestXML()... transCode: "+transCode);
		Date transDate = new Date();
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		if (transMessage == null) {
			transMessage = new TransMessage();
		}
		super.marshallObject(portalInterface, businessDatum, transMessage, transDate);
		String requestXml = txFactory.getConvertMessageService().marshaller(transMessage, transCode, portalInterface.getMessageEncoding(), MessageType.REQUEST.getDataENName());
		PortalMessageUtils.recordMessageToFile(requestXml, transMessage, MessageType.REQUEST);
		System.out.println("transCode: "+transCode+", requestXml: "+requestXml);
		PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter = new PortalInterfaceHandlerMethodParameter();
		portalInterfaceHandlerMethodParameter.setParameterClassName(String.class.getName());
		portalInterfaceHandlerMethodParameter.setInputObjectValue(requestXml);
		String responseXML = (String)AxisClient.sendRPCRequestMethodSingleReturn(portalInterface, portalInterfaceHandlerMethodParameter);
		System.out.println("transCode: "+transCode+", responseXML: "+responseXML);
		if (ReturnType.RETURN_NONE.getValue().equals(portalInterface.getReturnType())) {
			return null;
		}
		TransMessage returnTransMessage = (TransMessage) txFactory.getConvertMessageService().unMarshall(responseXML, transCode, MessageType.RESPONSE.getDataENName());
		PortalMessageUtils.recordMessageToFile(responseXML, returnTransMessage, MessageType.RESPONSE);
		return returnTransMessage.getInsurance().getBusinessResultDatum();
	}
	
	/**
	 * 获取请求对象
	 * @param requestXML 请求报文
	 * @param transCode 交易代码
	 * @param messageType 交易信息类型
	 * @return
	 * @throws IOException 
	 */
	public Object unMarshall(String requestXML, String transCode, MessageType messageType) throws IOException {
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		TransMessage transMessage = (TransMessage) txFactory.getConvertMessageService().unMarshall(requestXML, transCode, messageType.getDataENName());
		PortalMessageUtils.recordMessageToFile(requestXML, transMessage, messageType);
		if (MessageType.REQUEST.getValue().equals(messageType.getValue()) || MessageType.FRONTREQUEST.getValue().equals(messageType.getValue())) {
			if (portalInterface != null) {
				if (ReturnType.RETURN_TRANSOBJ.getValue().equals(portalInterface.getReturnType())) {
					return transMessage;
				} else if (ReturnType.RETURN_BUSINESSDATUM.getValue().equals(portalInterface.getReturnType())) {
					return transMessage.getInsurance().getBusinessDatum();
				}
			} else {
				return transMessage.getInsurance().getBusinessDatum();
			}
		} else  {
			if (portalInterface != null) {
				if (ReturnType.RETURN_TRANSOBJ.getValue().equals(portalInterface.getReturnType())) {
					return transMessage;
				} else if (ReturnType.RETURN_BUSINESSDATUM.getValue().equals(portalInterface.getReturnType())) {
					return transMessage.getInsurance().getBusinessResultDatum();
				}
			}
		}
		
		return transMessage;
	}
	
	
	/**
	 * 获取请求对象
	 * @param requestXML
	 * @param transCode
	 * @param portalInterface
	 * @param messageType
	 * @return
	 * @throws IOException 
	 */
	public Object unMarshall(String requestXML, String transCode, PortalInterface portalInterface, MessageType messageType) throws IOException {
		TransMessage transMessage = (TransMessage) txFactory.getConvertMessageService().unMarshall(requestXML, transCode, messageType.getDataENName());
		PortalMessageUtils.recordMessageToFile(requestXML, transMessage, messageType);
		if (MessageType.REQUEST.getValue().equals(messageType.getValue()) || MessageType.FRONTREQUEST.getValue().equals(messageType.getValue())) {
			if (portalInterface != null) {
				if (ReturnType.RETURN_TRANSOBJ.getValue().equals(portalInterface.getReturnType())) {
					return transMessage;
				} else if (ReturnType.RETURN_BUSINESSDATUM.getValue().equals(portalInterface.getReturnType())) {
					return transMessage.getInsurance().getBusinessDatum();
				}
			} else {
				return transMessage.getInsurance().getBusinessDatum();
			}
		} else  {
			if (portalInterface != null) {
				if (ReturnType.RETURN_TRANSOBJ.getValue().equals(portalInterface.getReturnType())) {
					return transMessage;
				} else if (ReturnType.RETURN_BUSINESSDATUM.getValue().equals(portalInterface.getReturnType())) {
					return transMessage.getInsurance().getBusinessResultDatum();
				}
			}
		}
		
		return transMessage;
	}
	
	/**
	 * 组装报文
	 * @param businessDatum 业务对象
	 * @param transMessage 交易信息对象
	 * @param transCode 交易代码
	 * @param messageType 交易类型
	 * @return
	 * @throws IOException 
	 */
	public String marshaller(List<Object> businessDatum, TransMessage transMessage, String transCode, MessageType messageType) throws IOException {
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		Date transDate = new Date();
		if (portalInterface == null) {
			portalInterface = new PortalInterface();
			portalInterface.setTransCode(transCode);
		}
		super.marshallObject(portalInterface, businessDatum, transMessage, transDate);
		String xmlMessage = txFactory.getConvertMessageService().marshaller(transMessage, transCode, messageType.getDataENName());
		PortalMessageUtils.recordMessageToFile(xmlMessage, transMessage, messageType);
		return xmlMessage;
	}
	
	/**
	 * 组装报文
	 * @param businessDatum 业务对象
	 * @param PortalInterface 接口信息
	 * @param transMessage 交易信息对象
	 * @param messageType 交易类型
	 * @return
	 * @throws IOException 
	 */
	public String marshaller(List<Object> businessDatum, TransMessage transMessage, PortalInterface portalInterface, MessageType messageType) throws IOException {
		Date transDate = new Date();
		super.marshallObject(portalInterface, businessDatum, transMessage, transDate, messageType);
		String xmlMessage = txFactory.getConvertMessageService().marshaller(transMessage, portalInterface.getTransCode(), messageType.getDataENName());
		PortalMessageUtils.recordMessageToFile(xmlMessage, transMessage, messageType);
		return xmlMessage;
	}
	
	/***
	 * RPC以字符串形式发送请求数据
	 * @param transCode 交易代码
	 * @param requestXML 请求信息
	 * @return 应答报文
	 * @throws Exception 
	 */
	@ImplTraced
	public String sendRPCXML(String transCode, String requestXML) throws Exception {
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter = new PortalInterfaceHandlerMethodParameter();
		portalInterfaceHandlerMethodParameter.setParameterClassName(String.class.getName());
		portalInterfaceHandlerMethodParameter.setInputObjectValue(requestXML);
		String responseXML = (String)AxisClient.sendRPCRequestMethodSingleReturn(portalInterface, portalInterfaceHandlerMethodParameter);
		PortalMessageUtils.recordMessageToFile(responseXML, transCode);
		return responseXML;
	}

	@Override
	public String sendServletRequestXML(String requestXml, String transCode) throws Exception {
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter = new PortalInterfaceHandlerMethodParameter();
		portalInterfaceHandlerMethodParameter.setParameterClassName(String.class.getName());
		portalInterfaceHandlerMethodParameter.setInputObjectValue(requestXml);
		String requestAddress = portalInterface.getRequestURL();
		String transPortType = portalInterface.getTransPortType();
		String RequestEcoding = portalInterface.getRequestEcoding();
		String responseXML = "";
		if(StringUtils.isNotBlank(transPortType) &&
				StringUtils.isNotBlank(requestAddress) &&
				StringUtils.isNotBlank(RequestEcoding) &&
				transPortType.equals("Axis2RPC")){
			responseXML = (String)AxisClient.sendRPCRequestMethodSingleReturn(portalInterface, portalInterfaceHandlerMethodParameter);
		}else if(StringUtils.isNotBlank(transPortType) &&
				StringUtils.isNotBlank(requestAddress) &&
				StringUtils.isNotBlank(RequestEcoding) &&
				transPortType.equals("Servlet")){
			responseXML = (String)ServletClient.sendRequestXML(requestAddress, requestXml, RequestEcoding);
		}
		return responseXML;
	}
	
}
