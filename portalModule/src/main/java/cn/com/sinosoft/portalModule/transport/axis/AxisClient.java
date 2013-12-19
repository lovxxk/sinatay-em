package cn.com.sinosoft.portalModule.transport.axis;


import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.io.FileUtils;

import cn.com.sinosoft.portalModule.enumUtil.ElementType;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceElement;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceHandlerMethodParameter;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalService;
import cn.com.sinosoft.util.io.fileFilter.RegexFileFilter;
import cn.com.sinosoft.util.string.StringUtils;

public class AxisClient implements PortalService {
	
	/***
	 * 
	 * RPC以字符串形式发送请求数据
	 * @param requestURL 请求地址
	 * @param namespaceURL 命名空间
	 * @param localPart 本地方法
	 * @param requestXML 请求处理报文
	 * @return 应答报文
	 */
	@SuppressWarnings("rawtypes")
	public String sendRPCRequestXML(String requestURL, String namespaceURL, String localPart, String requestXML) throws  AxisFault{
		String responseXML = null;
		long startTime = System.currentTimeMillis();
		RPCServiceClient serviceClient = new RPCServiceClient();
		EndpointReference targetEPR = new EndpointReference(requestURL);
		QName method = new QName(namespaceURL, localPart);
		Options options = serviceClient.getOptions();
		options.setTimeOutInMilliSeconds(timeOutMilliSeconds);
		options.setTo(targetEPR);
		Object[] opAddEntryArgs = new Object[]{requestXML};
		Class[] classes = new Class[]{String.class};
		System.out.println("请求服务器开始时间===========" + dateTimeFormat.format(new Date()) );
		responseXML = (String) serviceClient.invokeBlocking(method, opAddEntryArgs, classes)[0];
		System.out.println("服务器处理返回时间===========" + dateTimeFormat.format(new Date()) );
		long endTime = System.currentTimeMillis();
		System.out.println("服务器处理时间（毫秒）：========================" + (endTime - startTime));
		serviceClient.cleanupTransport();
		serviceClient.cleanup();
		return responseXML;
	}
	
	/**
	 * Axis 数据发送
	 * @param axisTransferData
	 * @return
	 * @throws AxisFault
	 */
	public static Object sendRPCRequestMethodSingleReturn(PortalInterface portalInterface, PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter) throws Exception {
		return sendRPCRequestMethod(portalInterface, portalInterfaceHandlerMethodParameter)[0] ;
	}
	
	/**
	 * Axis 数据发送
	 * @param axisTransferData
	 * @return
	 * @throws AxisFault
	 */
	public static Object sendRPCRequestMethodSingleReturn(PortalInterface portalInterface, List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters) throws Exception {
		return sendRPCRequestMethod(portalInterface, portalInterfaceHandlerMethodParameters)[0] ;
	}
	
	/**
	 * Axis 数据发送
	 * @param axisTransferData
	 * @return
	 * @throws AxisFault
	 */
	public static Object[] sendRPCRequestMethod(PortalInterface portalInterface, PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter) throws Exception {
		List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters = new ArrayList<PortalInterfaceHandlerMethodParameter>();
		portalInterfaceHandlerMethodParameters.add(portalInterfaceHandlerMethodParameter);
		return sendRPCRequestMethod(portalInterface, portalInterfaceHandlerMethodParameters);
	}
	
	/**
	 * Axis 数据发送
	 * @param axisTransferData
	 * @return
	 * @throws AxisFault
	 */
	@SuppressWarnings("rawtypes")
	public static Object[] sendRPCRequestMethod(PortalInterface portalInterface , List<PortalInterfaceHandlerMethodParameter> portalInterfaceHandlerMethodParameters) throws Exception {
		Object[] response = null;
		String requestAddress = portalInterface.getRequestURL();
		String targetNamespace = portalInterface.getNamespaceURI();
		String prefix = portalInterface.getPrefix();
		String localPart = portalInterface.getLocalPart();
		String optionAction = portalInterface.getAction();
		List<PortalInterfaceElement> portalInterfaceElements = portalInterface.getPortalInterfaceElements();
		List<Class<?>> classList = new ArrayList<Class<?>>();
		List<Object> sendDataList = new ArrayList<Object>();
		if (portalInterfaceHandlerMethodParameters != null) {
			for (PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter:portalInterfaceHandlerMethodParameters) {
				classList.add(Class.forName(portalInterfaceHandlerMethodParameter.getParameterClassName()));
				sendDataList.add(portalInterfaceHandlerMethodParameter.getInputObjectValue());
			}	
		}
		
		for (PortalInterfaceHandlerMethodParameter portalInterfaceHandlerMethodParameter:portalInterface.getPortalInterfaceHandlerMethodParameters()) {
			classList.add(Class.forName(portalInterfaceHandlerMethodParameter.getParameterClassName()));
			sendDataList.add(portalInterfaceHandlerMethodParameter.getParameterValue());
		}
		long connectTimeOut = timeOutMilliSeconds;
		if (portalInterface.getConnectTimeOut() != null) {
			connectTimeOut = portalInterface.getConnectTimeOut();
		}
		if (StringUtils.isNotBlank(requestAddress) && 
			StringUtils.isNotBlank(targetNamespace) && 
			StringUtils.isNotBlank(localPart) && 
			sendDataList.size() > 0 && classList.size() > 0) {
			OMElement headerOMElement = null;
			for (int  i = 0; i < portalInterfaceElements.size(); i++) {
				PortalInterfaceElement portalInterfaceElement = portalInterfaceElements.get(i);
				if (ElementType.HEADER.getValue().equals(portalInterfaceElement.getElementType())) {
					OMFactory omFactory = OMAbstractFactory.getOMFactory();
					OMNamespace omNamespace =  omFactory.createOMNamespace(targetNamespace, prefix);
					headerOMElement = omFactory.createOMElement(portalInterfaceElement.getElementName(), omNamespace);
					List<PortalInterfaceElement> childPortalInterfaceElements = portalInterfaceElement.getPortalInterfaceElements();
					for (int  j = 0; j < portalInterfaceElements.size(); j++) {
						PortalInterfaceElement childPortalInterfaceElement = childPortalInterfaceElements.get(j);
						String elementName = childPortalInterfaceElement.getElementName();
						String elementValue = childPortalInterfaceElement.getElementValue();
						if (StringUtils.isNotBlank(elementName) && StringUtils.isNotBlank(elementValue)) {
							OMElement childElement = omFactory.createOMElement(elementName, omNamespace);
							childElement.setText(elementValue);
							headerOMElement.addChild(childElement);
						}
					}
				}
				
			}
			long startTime = System.currentTimeMillis();
			try {
				RPCServiceClient serviceClient = new RPCServiceClient();
				EndpointReference targetEPR = new EndpointReference(requestAddress);
				//添加报文头
				if (headerOMElement != null) {
					serviceClient.addHeader(headerOMElement);
				}
				
				QName method = new QName(targetNamespace, localPart);
				Options options = serviceClient.getOptions();
				
				options.setTimeOutInMilliSeconds(connectTimeOut);
				options.setTo(targetEPR);
				if (StringUtils.isNotBlank(optionAction)) {
					options.setAction(optionAction);
				}
				System.out.println("请求服务器开始时间===========" + dateTimeFormat.format(new Date()));
				Object[] sendObjectArray = new Object[sendDataList.size()]; 
				Class[] sendObjectClassArray = new Class[classList.size()]; 
				response = serviceClient.invokeBlocking(method, sendDataList.toArray(sendObjectArray), classList.toArray(sendObjectClassArray));
				System.out.println("服务器处理返回时间===========" + dateTimeFormat.format(new Date()) );
				long endTime = System.currentTimeMillis();
				System.out.println("服务器处理时间（毫秒）：========================" + (endTime - startTime));
				serviceClient.cleanupTransport();
				serviceClient.cleanup();
			} catch (AxisFault e) {
				System.out.println(e.getMessage());
			}
		} else {
			return null;
		}
		return response;
	}
	
	
	/**
	 * Axis 数据发送
	 * @param axisTransferData
	 * @return
	 * @throws AxisFault
	 */
	public Object[] sendRPCRequestMethod(AxisTransferData axisTransferData) throws Exception {
		Object[] response = null;
		AxisOMElement headerAxisOMElement = axisTransferData.getAxisHeaderOMElement();
		String requestAddress = axisTransferData.getRequestAddress();
		String targetNamespace = axisTransferData.getTargetNamespace();
		String prefix = axisTransferData.getPrefix();
		String localPart = axisTransferData.getLocalPart();
		String optionAction = axisTransferData.getOptionAction();
		Class<?>[] classes = axisTransferData.getClasses();
		Object[] sendData = axisTransferData.getSendObject();
		
		if (StringUtils.isNotBlank(requestAddress) && 
			StringUtils.isNotBlank(targetNamespace) && 
			StringUtils.isNotBlank(localPart) && 
			classes.length > 0 && sendData.length > 0) {
			OMElement headerOMElement = null;
			if (headerAxisOMElement != null) {
				String headerName = headerAxisOMElement.getElementName();
				OMFactory omFactory = OMAbstractFactory.getOMFactory();
				OMNamespace omNamespace =  omFactory.createOMNamespace(targetNamespace, prefix);
				headerOMElement = omFactory.createOMElement(headerName, omNamespace);
				List<AxisOMElement>  axisChildOMElementList= headerAxisOMElement.getAxisChildOMElement();
				for (AxisOMElement axisOMElement:axisChildOMElementList) {
					String elementName = axisOMElement.getElementName();
					String elementValue = axisOMElement.getElementValue();
					if (StringUtils.isNotBlank(elementName) && StringUtils.isNotBlank(elementValue)) {
						OMElement childElement = omFactory.createOMElement(elementName, omNamespace);
						childElement.setText(elementValue);
						headerOMElement.addChild(childElement);
					}
				}
			}
			long startTime = System.currentTimeMillis();
			try {
				RPCServiceClient serviceClient = new RPCServiceClient();
				EndpointReference targetEPR = new EndpointReference(requestAddress);
				
				//添加报文头
				if (headerOMElement != null) {
					serviceClient.addHeader(headerOMElement);
				}
				
				QName method = new QName(targetNamespace, localPart);
				Options options = serviceClient.getOptions();
				options.setTimeOutInMilliSeconds(timeOutMilliSeconds);
				options.setTo(targetEPR);
				if (StringUtils.isNotBlank(optionAction)) {
					options.setAction(optionAction);
				}
				System.out.println("请求服务器开始时间===========" + dateTimeFormat.format(new Date()) );
				response = serviceClient.invokeBlocking(method, sendData, classes);
				System.out.println("服务器处理返回时间===========" + dateTimeFormat.format(new Date()) );
				long endTime = System.currentTimeMillis();
				System.out.println("服务器处理时间（毫秒）：========================" + (endTime - startTime));
				serviceClient.cleanupTransport();
				serviceClient.cleanup();
			} catch (AxisFault e) {
				e.printStackTrace();
				throw new Exception(e);
			}
		} else {
			return null;
		}
		return response;
	}
	
	/**
	 * 
	 *清除Axis2临时文件
	 * 
	 */
	public void cleanAxis2TempFiles(){
		String tmpdir = System.getProperty("java.io.tmpdir");
		System.out.println("当前操作系统临时文件目录=================" + tmpdir);
		try {
			File dir = new File(tmpdir);
			FileFilter fileFilter = new RegexFileFilter("^axis2[-\\w]*.tmp[.lck]*$");
			File[] files = dir.listFiles(fileFilter);
			for (int i = 0; i < files.length; i++) {
				FileUtils.deleteQuietly(files[i]);
			}
		} catch (Exception e) {
			System.out.println("当前操作系统临时文件目录=================" + tmpdir);
			System.out.println("Axis2临时文件清除异常！");
			e.printStackTrace();
		}
	}

}
