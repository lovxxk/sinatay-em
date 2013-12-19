package cn.com.sinosoft.portalModule.interfacePortal.webService.service;

import ins.framework.utils.StringUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.apache.axis2.AxisFault;
import org.apache.axis2.client.Stub;
import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.beanutils.MethodUtils;

import cn.com.sinosoft.portalModule.enumUtil.InterfaceStatus;
import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RecordMessageType;
import cn.com.sinosoft.portalModule.enumUtil.UserStatus;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.ExternalSystemsUserService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.InterfaceInfoService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.PortalService;
import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.util.GeneratorTransSerialNumber;
import cn.com.sinosoft.portalModule.interfacePortal.client.axis.AxisClient;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ClientUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSysUserInterfaceInfo;
import cn.com.sinosoft.portalModule.interfacePortal.domain.ExternalSystemsUser;
import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;
import cn.com.sinosoft.portalModule.interfacePortal.soap.util.SoapHelper;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.IInsuranceExtension;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TXInsuranceRequestExtension;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TXInsuranceXMLRequest;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TXInsuranceXMLResponse;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransEcSealMessageRequest;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransEcSealMessageResponse;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransEcSealRequestExtension;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransMessageRequest;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransMessageResponse;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransResult;
import cn.com.sinosoft.portalModule.interfacePortal.xml.domain.TransactionMessage;
import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.interfacePortal.xml.service.facade.TransactionMessageService;
import cn.com.sinosoft.portalModule.opendata.service.spring.WSResult;
import cn.com.sinosoft.util.encode.JsonBinder;
import cn.com.sinosoft.ebusiness.log.ImplTraced;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.portalModuleException;

/**
 * 
 *  
 * 
 */
public class WebServiceImpl implements WebService, PortalService {

	private static TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();

	private InterfaceInfoService interfaceInfoService;
	
	private TransactionMessageService transactionMessageService;

	private ExternalSystemsUserService externalSystemsUserService;

	public WebServiceImpl() {
	}

	public InterfaceInfoService getInterfaceInfoService() {
		return interfaceInfoService;
	}

	public void setInterfaceInfoService(InterfaceInfoService interfaceInfoService) {
		this.interfaceInfoService = interfaceInfoService;
	}

	public TransactionMessageService getTransactionMessageService() {
		return transactionMessageService;
	}

	public void setTransactionMessageService(TransactionMessageService transactionMessageService) {
		this.transactionMessageService = transactionMessageService;
	}

	public ExternalSystemsUserService getExternalSystemsUserService() {
		return externalSystemsUserService;
	}

	public void setExternalSystemsUserService(ExternalSystemsUserService externalSystemsUserService) {
		this.externalSystemsUserService = externalSystemsUserService;
	}
	
	
	/***
	 * @param requestObject 请求对象
	 * @param transCode 交易代码
	 */
	@SuppressWarnings("rawtypes")
	@ImplTraced
	public Object sendADBObject(Object requestObject, String setRequestObjectMethodName, String transCode) throws portalModuleException {
		Stub stub = null;
		InterfaceInfo interfaceInfo = null;
		String transRefGUID = null;
		Date transDate = new Date();
		Object requestObj = null;
		Object responseObj = null;
		
		try {
			interfaceInfo = this.findInterfaceByTransCode(transCode);
			if (interfaceInfo != null) {
				stub = stubClassMap.get(transCode);
				if (stub == null) {
					String stubClassName = interfaceInfo.getClassName();
					String requestURL = interfaceInfo.getRequestURL();
					if (stubClassName != null) {
						stub = (Stub) ConstructorUtils.invokeConstructor(Class.forName(stubClassName), requestURL);
						stubClassMap.put(transCode, stub);
					}
				}
				if (stub != null) {
					Class requestStubClass = insuranceReqClassMap.get(transCode);
					Class insReqExtsClass = insReqExtsClassMap.get(transCode);
					Class stubSendClass = stubSendClassMap.get(transCode);
					String stubSendClassName = StringUtils.upperCaseFirstChar(interfaceInfo.getLocalPart());
					if (requestStubClass == null || insReqExtsClass == null || stubSendClass == null) {
						Class[] stubDeclaredClasses = stub.getClass().getDeclaredClasses();
						for (Class stubDeclaredClass : stubDeclaredClasses) {
							if (stubDeclaredClass.getSimpleName().equals("TXInsuranceRequest")) {
								requestStubClass = stubDeclaredClass;
								insuranceReqClassMap.put(transCode, stubDeclaredClass);
								continue;
							}
							if (stubDeclaredClass.getSimpleName().equals("TXInsuranceRequestExtension")) {
								insReqExtsClass = stubDeclaredClass;
								insReqExtsClassMap.put(transCode, stubDeclaredClass);
								continue;
							}
							if (stubDeclaredClass.getSimpleName().equals(stubSendClassName)) {
								stubSendClass = stubDeclaredClass;
								stubSendClassMap.put(transCode, stubSendClass);
								continue;
							}
						}
					}
					
					if (stubSendClass != null) {
						Object sendObject = stubSendClass.newInstance();
						if (requestStubClass != null) {
							Object txRequest = null;
							Object txInsuranceRequest = MethodUtils.invokeExactMethod(requestObject , "getTXInsuranceRequest", null);
							if (txInsuranceRequest == null) {
								txRequest = requestStubClass.newInstance();
							} else {
								txRequest = txInsuranceRequest;
							}
							
							transRefGUID = GeneratorTransSerialNumber.generatorTransSerialNumber();
							MethodUtils.invokeMethod(txRequest, "setTransRefGUID", transRefGUID);
							MethodUtils.invokeMethod(txRequest, "setTransType", transCode);
							MethodUtils.invokeMethod(txRequest, "setTransExeDate", dateFormat.format(transDate));
							MethodUtils.invokeMethod(txRequest, "setTransExeTime", timeFormat.format(transDate));
							MethodUtils.invokeMethod(txRequest, "setTransExeTime", timeFormat.format(transDate));
							MethodUtils.invokeMethod(requestObject, "setTXInsuranceRequest", txRequest);
						}
						
						ClientUser clientUser = interfaceInfo.getClientUser();
						
						if (clientUser != null) {
							if (insReqExtsClass != null) {
								Object txRequestExt = insReqExtsClass.newInstance();
								MethodUtils.invokeMethod(txRequestExt, "setOperator", clientUser.getLoginName());
								MethodUtils.invokeMethod(txRequestExt, "setOperatorKey", clientUser.getPassword());
								MethodUtils.invokeMethod(requestObject, "setTXInsuranceRequestExtension", txRequestExt);
							}
						}

						requestObj = requestObject;
						responseObj =  MethodUtils.invokeMethod(sendObject, setRequestObjectMethodName, requestObject);
						stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(60000L);//设置超时间为一分钟
						return MethodUtils.invokeMethod(stub, interfaceInfo.getLocalPart(), sendObject);
					}
					
				} else {
					throw portalModuleException.newInstanceMsg("未找到 "+ transCode + "对应的处理类！");
				}
			}

		} catch (Exception e) {
			throw portalModuleException.newInstanceCode(transCode, e);
		} finally {
			if (interfaceInfo != null && StringUtils.isNotBlank(transRefGUID)) {
				if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
					TransactionMessage transMessage = new TransactionMessage();
					transMessage.setTransCode(transCode);
					transMessage.setTransRefGuid(transRefGUID);
					transMessage.setTransTime(transDate);
					transMessage.setRequestTime(transDate);
					transMessage.setResponseTime(new Date());
					
					JsonBinder jsonBuilder = JsonBinder.buildNonNullBinder();
					
					if(requestObj != null){
						transMessage.setRequestMessage(jsonBuilder.toJson(requestObj));
					}
					
					if(responseObj != null){
						transMessage.setResponseMessage(jsonBuilder.toJson(responseObj));
					}
					
					transactionMessageService.addTransactionMessage(transMessage);
				}
			}
		}
		return null;
	}
	/**
	 * 传统车险专用
	 * @param requestObject
	 * @param setRequestObjectMethodName
	 * @param transCode
	 * @return
	 * @throws portalModuleException
	 */
	@SuppressWarnings("rawtypes")
	@ImplTraced
	public Object sendADBObjectEhm(Object requestObject, String setRequestObjectMethodName, String transCode) throws portalModuleException {
		Stub stub = null;
		InterfaceInfo interfaceInfo = null;
		String transRefGUID = null;
		Date transDate = new Date();
		Object requestObj = null;
		Object responseObj = null;
		
		try {
			interfaceInfo = this.findInterfaceByTransCode(transCode);
			if (interfaceInfo != null) {
				stub = stubClassMap.get(transCode);
				if (stub == null) {
					String stubClassName = interfaceInfo.getClassName();
					String requestURL = interfaceInfo.getRequestURL();
					if (stubClassName != null) {
						stub = (Stub) ConstructorUtils.invokeConstructor(Class.forName(stubClassName), requestURL);
						stubClassMap.put(transCode, stub);
					}
				}
				if (stub != null) {
					Class requestStubClass = insuranceReqClassMap.get(transCode);
					Class insReqExtsClass = insReqExtsClassMap.get(transCode);
					Class stubSendClass = stubSendClassMap.get(transCode);
					Class pageClass = pageClassMap.get(transCode);
					String stubSendClassName = StringUtils.upperCaseFirstChar(interfaceInfo.getLocalPart());
					if (requestStubClass == null || insReqExtsClass == null || stubSendClass == null) {
						Class[] stubDeclaredClasses = stub.getClass().getDeclaredClasses();
						for (Class stubDeclaredClass : stubDeclaredClasses) {
							if (stubDeclaredClass.getSimpleName().equals("TXInsuranceRequestEhm")) {
								requestStubClass = stubDeclaredClass;
								insuranceReqClassMap.put(transCode, stubDeclaredClass);
								continue;
							}
							if (stubDeclaredClass.getSimpleName().equals("TXInsuranceRequestExtensionEhm")) {
								insReqExtsClass = stubDeclaredClass;
								insReqExtsClassMap.put(transCode, stubDeclaredClass);
								continue;
							}
							if (stubDeclaredClass.getSimpleName().equals("IInsuranceExtensionEhm")) {
								pageClass = stubDeclaredClass;
								pageClassMap.put(transCode, pageClass);
								continue;
							}
							if (stubDeclaredClass.getSimpleName().equals(stubSendClassName)) {
								stubSendClass = stubDeclaredClass;
								stubSendClassMap.put(transCode, stubSendClass);
								continue;
							}
						}
					}
					
					if (stubSendClass != null) {
						Object sendObject = stubSendClass.newInstance();
						if (requestStubClass != null) {
							Object txRequest = null;
							
							Object txInsuranceRequest = MethodUtils.invokeExactMethod(requestObject , "getTXInsuranceRequestEhm", null);
							if (txInsuranceRequest == null) {
								txRequest = requestStubClass.newInstance();
							} else {
								txRequest = txInsuranceRequest;
							}
							transRefGUID = GeneratorTransSerialNumber.generatorTransSerialNumber();
							MethodUtils.invokeMethod(txRequest, "setTransRefGUID", transRefGUID);
							MethodUtils.invokeMethod(txRequest, "setTransType", transCode);
							MethodUtils.invokeMethod(txRequest, "setTransExeDate", dateFormat.format(transDate));
							MethodUtils.invokeMethod(txRequest, "setTransExeTime", timeFormat.format(transDate));
							MethodUtils.invokeMethod(txRequest, "setTransExeTime", timeFormat.format(transDate));
							MethodUtils.invokeMethod(requestObject, "setTXInsuranceRequestEhm", txRequest);
//							MethodUtils.invokeMethod(requestObject, "setRequest", txRequest);
							
							Object pageRequest = MethodUtils.invokeExactMethod(txRequest , "getIInsuranceExtensionEhm", null);
							if (pageRequest == null) {
								pageRequest = pageClass.newInstance();
							}
							MethodUtils.invokeMethod(txRequest, "setIInsuranceExtensionEhm", pageRequest);

						}
						
						ClientUser clientUser = interfaceInfo.getClientUser();
						Object  txInsuranceRequestExtensionEhm = MethodUtils.invokeExactMethod(requestObject , "getTXInsuranceRequestExtensionEhm", null);
						if(txInsuranceRequestExtensionEhm == null){
							txInsuranceRequestExtensionEhm = insReqExtsClass.newInstance();
						}
						if (clientUser != null) {
							if (insReqExtsClass != null) {
								MethodUtils.invokeMethod(txInsuranceRequestExtensionEhm, "setOperator", clientUser.getLoginName());
								MethodUtils.invokeMethod(txInsuranceRequestExtensionEhm, "setOperatorKey", clientUser.getPassword());
								MethodUtils.invokeMethod(requestObject, "setTXInsuranceRequestExtensionEhm", txInsuranceRequestExtensionEhm);
							}
						}
						
						requestObj = requestObject;
						responseObj =  MethodUtils.invokeMethod(sendObject, setRequestObjectMethodName, requestObject);
						stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(2*60000L);//设置超时间为一分钟
						return MethodUtils.invokeMethod(stub, interfaceInfo.getLocalPart(), sendObject);
					}
					
				} else {
					throw portalModuleException.newInstanceMsg("未找到 "+ transCode + "对应的处理类！");
				}
			}

		} catch (Exception e) {
			throw portalModuleException.newInstanceCode(transCode, e);
		} finally {
			if (interfaceInfo != null && StringUtils.isNotBlank(transRefGUID)) {
				if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
					TransactionMessage transMessage = new TransactionMessage();
					transMessage.setTransCode(transCode);
					transMessage.setTransRefGuid(transRefGUID);
					transMessage.setTransTime(transDate);
					transMessage.setRequestTime(transDate);
					transMessage.setResponseTime(new Date());
					
					JsonBinder jsonBuilder = JsonBinder.buildNonNullBinder();
					
					if(requestObj != null){
						transMessage.setRequestMessage(jsonBuilder.toJson(requestObj));
					}
					
					if(responseObj != null){
						transMessage.setResponseMessage(jsonBuilder.toJson(responseObj));
					}
					
					transactionMessageService.addTransactionMessage(transMessage);
				}
			}
		}
		return null;
	}
	
	/***
	 * @param requestObject 请求对象
	 * @param transCode 交易代码
	 */
	@SuppressWarnings("rawtypes")
	@ImplTraced
	public Object sendADBObject(Object requestObject, String reqURL, String setRequestObjectMethodName, String transCode) throws portalModuleException {
		Stub stub = null;
		InterfaceInfo interfaceInfo = null;
		String transRefGUID = null;
		Date transDate = new Date();
		Object requestObj = null;
		Object responseObj = null;
		
		try {
			interfaceInfo = this.findInterfaceByTransCode(transCode);
			if (interfaceInfo != null) {
				stub = stubClassMap.get(transCode);
				if (stub == null) {
					String stubClassName = interfaceInfo.getClassName();
					String requestURL = interfaceInfo.getRequestURL();
					if (StringUtils.isNotBlank(reqURL)) {
						requestURL = reqURL;
					}
					if (stubClassName != null) {
						stub = (Stub) ConstructorUtils.invokeConstructor(Class.forName(stubClassName), requestURL);
						stubClassMap.put(transCode, stub);
					}
				}
				if (stub != null) {
					Class requestStubClass = insuranceReqClassMap.get(transCode);
					Class insReqExtsClass = insReqExtsClassMap.get(transCode);
					Class stubSendClass = stubSendClassMap.get(transCode);
					String stubSendClassName = StringUtils.upperCaseFirstChar(interfaceInfo.getLocalPart());
					if (requestStubClass == null || insReqExtsClass == null || stubSendClass == null) {
						Class[] stubDeclaredClasses = stub.getClass().getDeclaredClasses();
						for (Class stubDeclaredClass : stubDeclaredClasses) {
							if (stubDeclaredClass.getSimpleName().equals("TXInsuranceRequest")) {
								requestStubClass = stubDeclaredClass;
								insuranceReqClassMap.put(transCode, stubDeclaredClass);
								continue;
							}
							if (stubDeclaredClass.getSimpleName().equals("TXInsuranceRequestExtension")) {
								insReqExtsClass = stubDeclaredClass;
								insReqExtsClassMap.put(transCode, stubDeclaredClass);
								continue;
							}
							if (stubDeclaredClass.getSimpleName().equals(stubSendClassName)) {
								stubSendClass = stubDeclaredClass;
								stubSendClassMap.put(transCode, stubSendClass);
								continue;
							}
						}
					}
					
					if (stubSendClass != null) {
						Object sendObject = stubSendClass.newInstance();
						if (requestStubClass != null) {
							Object txRequest = null;
							Object txInsuranceRequest = MethodUtils.invokeExactMethod(requestObject , "getTXInsuranceRequest", null);
							if (txInsuranceRequest == null) {
								txRequest = requestStubClass.newInstance();
							} else {
								txRequest = txInsuranceRequest;
							}
							
							transRefGUID = GeneratorTransSerialNumber.generatorTransSerialNumber();
							MethodUtils.invokeMethod(txRequest, "setTransRefGUID", transRefGUID);
							MethodUtils.invokeMethod(txRequest, "setTransType", transCode);
							MethodUtils.invokeMethod(txRequest, "setTransExeDate", dateFormat.format(transDate));
							MethodUtils.invokeMethod(txRequest, "setTransExeTime", timeFormat.format(transDate));
							MethodUtils.invokeMethod(txRequest, "setTransExeTime", timeFormat.format(transDate));
							MethodUtils.invokeMethod(requestObject, "setTXInsuranceRequest", txRequest);
						}
						
						ClientUser clientUser = interfaceInfo.getClientUser();
						
						if (clientUser != null) {
							if (insReqExtsClass != null) {
								Object txRequestExt = insReqExtsClass.newInstance();
								MethodUtils.invokeMethod(txRequestExt, "setOperator", clientUser.getLoginName());
								MethodUtils.invokeMethod(txRequestExt, "setOperatorKey", clientUser.getPassword());
								MethodUtils.invokeMethod(requestObject, "setTXInsuranceRequestExtension", txRequestExt);
							}
						}

						requestObj = requestObject;
						responseObj = MethodUtils.invokeMethod(sendObject, setRequestObjectMethodName, requestObject);
						stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(60000L);//设置超时时间为一分钟
						return MethodUtils.invokeMethod(stub, interfaceInfo.getLocalPart(), sendObject);
					}
					
				} else {
					throw portalModuleException.newInstanceMsg("未找到 "+ transCode + "对应的处理类！");
				}
			}

		} catch (Exception e) {
			throw portalModuleException.newInstanceCode(transCode, e);
		}  finally {
			if (interfaceInfo != null && StringUtils.isNotBlank(transRefGUID)) {
				if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
					TransactionMessage transMessage = new TransactionMessage();
					transMessage.setTransCode(transCode);
					transMessage.setTransRefGuid(transRefGUID);
					transMessage.setTransTime(transDate);
					transMessage.setRequestTime(transDate);
					transMessage.setResponseTime(new Date());
					
					JsonBinder jsonBuilder = JsonBinder.buildNonNullBinder();
					
					if(requestObj != null){
						transMessage.setRequestMessage(jsonBuilder.toJson(requestObj));
					}
					
					if(responseObj != null){
						transMessage.setResponseMessage(jsonBuilder.toJson(responseObj));
					}
					
					transactionMessageService.addTransactionMessage(transMessage);
				}
			}
		}
		return null;
	}
	
	/***
	 * 
	 * @param requestObject 请求对象
	 * @param transCode 交易代码
	 * @return
	 */
	@ImplTraced
	public Object sendADBObject(Object requestObject,String transCode) {
		Stub stub = null;
		InterfaceInfo interfaceInfo = null;
		Date transDate = new Date();
		Object requestObj = null;
		Object responseObj = null;
		
		try {
			interfaceInfo = this.findInterfaceByTransCode(transCode);
			if (interfaceInfo != null) {
				stub = stubClassMap.get(transCode);
				if (stub == null) {
					String stubClassName = interfaceInfo.getClassName();
					String requestURL = interfaceInfo.getRequestURL();
					if (stubClassName != null) {
						stub = (Stub) ConstructorUtils.invokeConstructor(Class.forName(stubClassName), requestURL);
						stubClassMap.put(transCode, stub);
					}
				}
				
				if (stub != null) {
					requestObj = requestObject;
					responseObj = MethodUtils.invokeMethod(stub, interfaceInfo.getLocalPart(), requestObject);
					return responseObj;
				}
				} else {
					throw portalModuleException.newInstanceMsg("未找到 "+ transCode + "对应的处理类！");
				}

		}  catch (Exception e) {
			throw portalModuleException.newInstanceCode(transCode, e);
		} 	finally {
			if (interfaceInfo != null) {
				if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
					TransactionMessage transMessage = new TransactionMessage();
					transMessage.setTransCode(transCode);
					transMessage.setTransTime(transDate);
					transMessage.setRequestTime(transDate);
					transMessage.setResponseTime(new Date());
					transMessage.setTransRefGuid(UUID.randomUUID().toString());
					
					JsonBinder jsonBuilder = JsonBinder.buildNonNullBinder();
					
					if(requestObj != null){
						transMessage.setRequestMessage(jsonBuilder.toJson(requestObj));
					}
					
					if(responseObj != null){
						transMessage.setResponseMessage(jsonBuilder.toJson(responseObj));
					}
					
					transactionMessageService.addTransactionMessage(transMessage);
				}
			}
		}
		return null;
	}
	
	
	/***
	 * ws-01:门户通出现的程序异常,存异常对象
	 * ws-02:门户通出现的程序异常以及esb异常，存字符串信息
	 * ws-03:接口校验不通过
	 * @param requestObject 请求对象
	 * @param transCode 交易代码
	 * @return
	 */
	@ImplTraced
	public WSResult sendADBObjectToEsb(Object requestObject,String transCode){
		Map<String,Object> wsReturnMap = new HashMap<String, Object>();
		WSResult wSResult = new WSResult();
		Stub stub = null;
		InterfaceInfo interfaceInfo = null;
		Date transDate = new Date();
		Object requestObj = null;
		Object responseObj = null;
		String requestHeadStr = null;
		String responseHeadMap = null;
		String exceptionStr = null;
		String transRefGUID = getTranNo(transDate);
		
		try {
			// 接口校验
			interfaceInfo = interfaceInfoService.findInterfaceInfoByTransCode(transCode);
			if (interfaceInfo != null) {
				// 判断接口状态是否可用
				if (InterfaceStatus.ENABLED.equals(interfaceInfo.getStatus())) {
					ClientUser clientUser = interfaceInfo.getClientUser();
					if(clientUser == null){
						
					}
					if (clientUser != null) {
						// 如果接口存在所对应的用户信息，判断用户状态是否可用
						if (!UserStatus.ENABLED.equals(clientUser.getStatus())) {
							
						}
					}
				} else {
					exceptionStr = "接口未启用！";
				}
			}else{
				exceptionStr = "接口不存在！";
			}
			if(StringUtils.isNotBlank(exceptionStr)){
				wsReturnMap = new HashMap<String, Object>();
				wsReturnMap.put("ws-03", exceptionStr);
				wSResult.setResultFlag(wsReturnMap);
				return wSResult;
			}
			
			
			// 获取stub
			stub = stubClassMap.get(transCode);
			if (stub == null) {
				String stubClassName = interfaceInfo.getClassName();
				String requestURL = interfaceInfo.getRequestURL();
				if (stubClassName != null) {
					stub = (Stub) ConstructorUtils.invokeConstructor(Class.forName(stubClassName), requestURL);
				}
			}
			
			if (stub != null) {
				String esbauthbranchno = "";
				String loginName = "";
				String password = "";
				String esbsignature = "";
				String interface_Esbroutebranchno = "";
				String interface_Esbroutedestsys = "";
				
				if(interfaceInfo.getClientUser() != null){
					if(StringUtils.isNotBlank(interfaceInfo.getEsbroutebranchno()))
						interface_Esbroutebranchno = interfaceInfo.getEsbroutebranchno();
					if(StringUtils.isNotBlank(interfaceInfo.getEsbroutedestsys()))
						interface_Esbroutedestsys = interfaceInfo.getEsbroutedestsys();
					if(StringUtils.isNotBlank(interfaceInfo.getClientUser().getEsbauthbranchno()))
						esbauthbranchno = interfaceInfo.getClientUser().getEsbauthbranchno();
					if(StringUtils.isNotBlank(interfaceInfo.getClientUser().getLoginName()))
						loginName = interfaceInfo.getClientUser().getLoginName();
					if(StringUtils.isNotBlank(interfaceInfo.getClientUser().getPassword()))
						password = interfaceInfo.getClientUser().getPassword();
					if(StringUtils.isNotBlank(interfaceInfo.getClientUser().getEsbsignature()))
						esbsignature = interfaceInfo.getClientUser().getEsbsignature();
				}
				
				requestObj = requestObject;
				
				// 设置esb请求头
				requestHeadStr = "<cl:HEADER xmlns:cl=\""+interfaceInfo.getNamespaceURL()+"\">" +
			    "<SVCNAME>"+interfaceInfo.getEsbSvcName()+"</SVCNAME>" + 
			    "<SVCCODE>"+interfaceInfo.getTransCode()+"</SVCCODE>" +
			    "<SVCVER>"+interfaceInfo.getEsbserviceversion() +"</SVCVER>" +
			    // 集团系统的代码定为：GROUP-EBP
			    "<ORISYS>"+interfaceInfo.getEsborisys()+"</ORISYS>" + 
			    "<TRANTIME>"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transDate) +"</TRANTIME>"+
			    "<TRANNO>"+ transRefGUID +"</TRANNO>" +
			   
			    "<AUTH>" +
			    "<BRANCHNO>"+esbauthbranchno+"</BRANCHNO>" +
			    "<USERID>"+loginName+"</USERID>" +
			    "<TOKENID>"+password+"</TOKENID>" +
			    "</AUTH>" +
			    
			    "<SECURITY>" +
			    "<SIGNATURE>"+esbsignature+"</SIGNATURE>" +
			    "</SECURITY>" +
			    
			    "<ROUTE>" +
			    "<BRANCHNO>"+interface_Esbroutebranchno+"</BRANCHNO>" +
			    "<DESTSYS>"+interface_Esbroutedestsys+"</DESTSYS>" +
			    "</ROUTE>" +
			    
			    "</cl:HEADER>";
				
				SoapHelper.setSoapVersion(stub);
				SoapHelper.setSoapHeader(stub, requestHeadStr);
				
				// 调用
				Object[] prams = new Object[]{requestObject,transRefGUID};
				responseObj = MethodUtils.invokeMethod(stub, interfaceInfo.getLocalPart(), prams);
				// 获取应答头全部信息
				Map<String,Object> responseHead = SoapHelper.getReturnHead(transRefGUID);
				if(responseHead!=null){
					responseHeadMap = responseHead.toString();
					
					String retCode  = (String)responseHead.get("RETCODE");
					String retInfo  = (String)responseHead.get("RETINFO");
			 
					if(StringUtils.isNotBlank(retCode)){
						// 0000标识此次请求成功，1001为服务端有业务异常
						if("0000".equals(retCode) || "1001".equals(retCode)){
							wsReturnMap.put(retCode, retInfo);
						} else {
							wsReturnMap.put("ws-02", retInfo);
						}
					}
					
					wSResult.setResultFlag(wsReturnMap);
					wSResult.setObj(responseObj);
				}
				
				return wSResult;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			exceptionStr = getExceptionStr(e);
			
			// ws-01 为门户通出现的程序异常
			wsReturnMap = new HashMap<String, Object>();
			wsReturnMap.put("ws-01", e);
			wsReturnMap.put("ws-02", exceptionStr);
			wSResult.setResultFlag(wsReturnMap);
			return wSResult;
		} finally {
			if (interfaceInfo != null) {
				SoapHelper.clear(transRefGUID);
				if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
					TransactionMessage transMessage = new TransactionMessage();
					transMessage.setTransCode(transCode);
					transMessage.setTransTime(transDate);
					transMessage.setRequestTime(transDate);
					transMessage.setResponseTime(new Date());
					transMessage.setTransRefGuid(transRefGUID);
					
					JsonBinder jsonBuilder = JsonBinder.buildNormalBinder();
					
					if(requestObj != null){
						transMessage.setRequestMessage(jsonBuilder.toJson(requestObj));
					}
					
					if(responseObj != null){
						transMessage.setResponseMessage(jsonBuilder.toJson(responseObj));
					}
					
					if(StringUtils.isNotBlank(requestHeadStr)){
						transMessage.setEsbrequestheader(requestHeadStr);
					}
					if(StringUtils.isNotBlank(responseHeadMap)){
						transMessage.setEsbresponseheader(responseHeadMap);
					}
					
					if(StringUtils.isNotBlank(exceptionStr)){
						transMessage.setEsbException(exceptionStr);
					}
					transactionMessageService.addTransactionMessage(transMessage);
				}
			}
		}
		return null;
	}
	
	
	
	/***
	 * 
	 * 直接调用服务器的方法发送请求
	 * @param transCode 交易代码
	 * @param param 请求参数
	 * 
	 */
	@SuppressWarnings("rawtypes")
	@ImplTraced
	public Object[] sendRPCRequestMethod(String transCode, List param) {
		InterfaceInfo interfaceInfo = this.findInterfaceByTransCode(transCode);
		try {
			if (interfaceInfo != null) { 
				String requestURL = interfaceInfo.getRequestURL();
				String namespaceURL = interfaceInfo.getNamespaceURL();
				String localPart = interfaceInfo.getLocalPart(); 
				AxisClient axisClient = new AxisClient();
				return axisClient.sendRPCRequestMethod(requestURL, namespaceURL, localPart, param);
			}
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * XML格式报文
	 * 
	 * @param buinessData
	 *            业务数据
	 * @param transCode
	 *            交易代码
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	@ImplTraced
	public List sendRPCRequestXML(Object buinessData, String transCode) throws portalModuleException {
		InterfaceInfo interfaceInfo = this.findInterfaceByTransCode(transCode);
		if (interfaceInfo != null) {
			TXInsuranceXMLRequest txXMLReq = new TXInsuranceXMLRequest();
			IInsuranceExtension insuranceExtension = new IInsuranceExtension();
			List<Object> businessList = new ArrayList<Object>();
			businessList.add(buinessData);
			txXMLReq.setBusinessData(businessList);
			txXMLReq.setIInsuranceExtension(insuranceExtension);
			TXInsuranceXMLResponse txResp = sendXML(interfaceInfo, txXMLReq, transCode);
			TransResult result = txResp.getTransResult();
			if (!"0".equals(result.getResultCode())) {
				throw portalModuleException.newInstanceMsg(result.getResultCode() + "_" + result.getResultInfo());
			}
			return txResp.getBusinessData();
		}
		return null;
	}
	
	/***
	 * 处理分页报文方式
	 * 
	 * @param txXMLReq 报文组装对象
	 * 
	 * @param transCode 交易代码
	 * 
	 * @return 报文组装应答对象
	 */
	@ImplTraced
	public TXInsuranceXMLResponse sendRPCRequestXML(TXInsuranceXMLRequest txXMLReq, String transCode) throws portalModuleException {
		InterfaceInfo interfaceInfo = this.findInterfaceByTransCode(transCode);
		if (interfaceInfo != null) {
			return sendXML(interfaceInfo, txXMLReq, transCode);
		}
		return null;
	}

	/**
	 * 电子签章报文请求处理
	 * @param buinessData
	 * @param transCode
	 * @return
	 */
	@ImplTraced
	public Object sendRPCRequestXMLForEcSeal(Object buinessData, String transCode) throws portalModuleException {
		InterfaceInfo interfaceInfo = this.findInterfaceByTransCode(transCode);
		String transRefGUID = GeneratorTransSerialNumber.generatorTransSerialNumber();
		Date transDate = new Date();
		String requestXML = null;
		String responseXML = null;
		try {
			if (interfaceInfo != null) {
			TransEcSealMessageRequest tECReq = new TransEcSealMessageRequest();
			TXInsuranceXMLRequest txXMLReq = new TXInsuranceXMLRequest();
			TransEcSealRequestExtension ecSealRequestExtension = new TransEcSealRequestExtension();
			ClientUser clientUser = interfaceInfo.getClientUser();
			txXMLReq.setTransRefGUID(transRefGUID);
			txXMLReq.setTransType(transCode);
			txXMLReq.setTransExeDate(dateFormat.format(transDate));
			txXMLReq.setTransExeTime(timeFormat.format(transDate));
			tECReq.setInsuranceRequest(txXMLReq);
			tECReq.setExtensionInfo(buinessData);
			if (clientUser != null) {
				ecSealRequestExtension.setOperator(clientUser.getLoginName());
				ecSealRequestExtension.setOperatorKey(clientUser.getPassword());
				if (clientUser.getExternalSysInfo() != null) {
					ecSealRequestExtension.setSysId(clientUser.getExternalSysInfo().getExternalSysId());
				}
			}
			tECReq.setEcSealRequestExtension(ecSealRequestExtension);
			requestXML = txFactory.getConvertMessageService().marshaller(tECReq, interfaceInfo.getTransCode(), MessageType.REQUEST.getDataENName());
			if (StringUtils.isNotBlank(requestXML)) {
				responseXML = sendRPCXML(interfaceInfo, requestXML);
				if (StringUtils.isNotBlank(responseXML)) {
					TransEcSealMessageResponse tmRes = (TransEcSealMessageResponse) txFactory.getConvertMessageService().unMarshall(responseXML, transCode, MessageType.RESPONSE.getDataENName());
					return tmRes.getReturnExtensionInfo();
				} else {
					responseXML = "应答报文长度为零！";
					throw portalModuleException.newInstanceMsg("交易（交易代码："+ transCode + "， 交易流水号：" +transRefGUID + "）应答报文长度为零！");
				}
				
			} else {
				responseXML = "应答报文长度为零！";
				throw portalModuleException.newInstanceMsg("交易（交易代码："+ transCode + "， 交易流水号：" +transRefGUID + "）请求报文长度为零！");
			}
			
			}
		} catch (AxisFault e) {
			responseXML = "发送请求异常：" + e.getMessage();
			e.printStackTrace();
			throw portalModuleException.newInstanceMsg( "交易（交易代码："+ transCode + "， 交易流水号：" +transRefGUID + "）发送请求异常：" + e.getMessage());
		} finally {
			if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
				TransactionMessage transMessage = new TransactionMessage();
				transMessage.setTransCode(transCode);
				transMessage.setTransRefGuid(transRefGUID);
				transMessage.setTransTime(transDate);
				transMessage.setRequestTime(transDate);
				transMessage.setRequestMessage(requestXML);
				transMessage.setResponseTime(new Date());
				transMessage.setResponseMessage(responseXML);
				transactionMessageService.addTransactionMessage(transMessage);
			}
			
		}
		return null;
	}
	
	/***
	 * 应答外部系统请求
	 * @param tmResp 应答对象
	 * 
	 * 
	 */
	@ImplTraced
	public String sendRPCFrontResponseXML(TransMessageResponse tmResp) throws portalModuleException {
		InterfaceInfo interfaceInfo = null;
		String frontResponseXML = null;
		try {
			frontResponseXML = txFactory.getConvertMessageService().marshaller(tmResp, tmResp.getInsuranceResponse().getTransType(), MessageType.FRONTRESPONSE.getDataENName());
			interfaceInfo = this.findInterfaceByTransCode(tmResp.getInsuranceResponse().getTransType());
		} catch (Exception e) {
			e.printStackTrace();
			frontResponseXML += "组装应答报文异常：" + e.getMessage();
		} finally {
			if (interfaceInfo != null) {
				if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
					TransactionMessage transMessage = new TransactionMessage();
					transMessage.setTransCode(tmResp.getInsuranceResponse().getTransType());
					transMessage.setTransRefGuid(tmResp.getInsuranceResponse().getTransRefGUID());
					transMessage.setFrontResponeTime(new Date());
					transMessage.setFrontResponseMessage(frontResponseXML);
					transactionMessageService.updateTransactionMessage(transMessage);
				}
			}
		}
		return frontResponseXML;
	}

	/***
	 * 处理外部系统请求
	 * @param frontRequestXML 外部系统请求报文
	 * @param transCode 交易流水号
	 * @return 外部系统请求对象
	 */
	@ImplTraced
	public TransMessageRequest getFrontRequestXML(String frontRequestXML, String transCode) throws portalModuleException {
		TransMessageRequest tReq = (TransMessageRequest) txFactory.getConvertMessageService().unMarshall(frontRequestXML, transCode, MessageType.FRONTREQUEST.getDataENName());
		TXInsuranceRequestExtension txReqExts = tReq.getInsuranceRequestExtension();
		try {
			if (txReqExts != null) {
				String operator = txReqExts.getOperator();
				String operatorKey = txReqExts.getOperatorKey();
				if (operator != null && operatorKey != null) {
					ExternalSystemsUser extrlSysUser = externalSystemsUserService.findExternalSystemsUserByLoginName(operator);
					if (extrlSysUser != null) {
						if (operatorKey.equals(extrlSysUser.getPassword())) {
							if (UserStatus.ENABLED.equals(extrlSysUser.getStatus())) {
								List<ExternalSysUserInterfaceInfo> externalSysUserInterfaceInfoList = extrlSysUser.getExternalSysUserInterfaceInfos();
								InterfaceInfo interfaceInfo = null;
								for (ExternalSysUserInterfaceInfo externalSysUserInterfaceInfo:externalSysUserInterfaceInfoList) {
									if (transCode.equals(externalSysUserInterfaceInfo.getInterfaceInfo().getTransCode())) {
										interfaceInfo = externalSysUserInterfaceInfo.getInterfaceInfo();
										break;
									}
								}
								if (interfaceInfo != null) {
									if (InterfaceStatus.ENABLED.equals(interfaceInfo.getStatus())) {
										TXInsuranceXMLRequest txXMLReq = tReq.getInsuranceRequest();
										if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
											TransactionMessage transMessage = new TransactionMessage();
											transMessage.setTransCode(transCode);
											transMessage.setTransRefGuid(txXMLReq.getTransRefGUID());
											transMessage.setTransTime(new Date());
											transMessage.setFrontRequestTime(new Date());
											transMessage.setFrontRequestMessage(frontRequestXML);
											transactionMessageService.addTransactionMessage(transMessage);
										}
										return tReq;
									} else {
										throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "接口未启用！");
									}
								} else {
									throw portalModuleException.newInstanceMsg("未查到与" + txReqExts.getOperator() + "相关" + transCode + "接口信息！");
								}
							} else {
								throw portalModuleException.newInstanceMsg(txReqExts.getOperator() + "用户状态未启用！");
							}

						} else {
							throw portalModuleException.newInstanceMsg(txReqExts.getOperator() + "密码不正确！");
						}

					} else {
						throw portalModuleException.newInstanceMsg("未查到" + txReqExts.getOperator() + "相关信息！");
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/***
	 * 处理含有扩展信息的报文
	 * @param interfaceInfo 接口信息
	 * @param clientUser  用户信息
	 * @param buinessData 业务数据
	 * @param iInsuranceExtension 业务请求扩展信息
	 * @param transCode 交易代码
	 * @return TXInsuranceXMLResponse
	 * @throws portalModuleException 
	 */
	@ImplTraced
	public TXInsuranceXMLResponse sendXML(InterfaceInfo interfaceInfo, TXInsuranceXMLRequest txXMLReq, String transCode) throws portalModuleException {
		
		String transRefGUID = GeneratorTransSerialNumber.generatorTransSerialNumber();
		Date transDate = new Date();
		String requestXML = null;
		String responseXML = null;
		try {
			TransMessageRequest tReq = new TransMessageRequest();
			TXInsuranceRequestExtension txReqExtension = new TXInsuranceRequestExtension();
			ClientUser clientUser = interfaceInfo.getClientUser();
			txXMLReq.setTransRefGUID(transRefGUID);
			txXMLReq.setTransType(transCode);
			txXMLReq.setTransExeDate(dateFormat.format(transDate));
			txXMLReq.setTransExeTime(timeFormat.format(transDate));
			if (clientUser != null) {
				txReqExtension.setOperator(clientUser.getLoginName());
				txReqExtension.setOperatorKey(clientUser.getPassword());
			}
			tReq.setInsuranceRequestExtension(txReqExtension);
			tReq.setInsuranceRequest(txXMLReq);
			requestXML = txFactory.getConvertMessageService().marshaller(tReq, transCode, MessageType.REQUEST.getDataENName());
			if (StringUtils.isNotBlank(requestXML)) {
				responseXML = sendRPCXML(interfaceInfo, requestXML);
				if (StringUtils.isNotBlank(responseXML)) {
					TransMessageResponse tmResp = (TransMessageResponse) txFactory.getConvertMessageService().unMarshall(responseXML,transCode, MessageType.RESPONSE.getDataENName());
					TXInsuranceXMLResponse txResp = tmResp.getInsuranceResponse();
					return txResp;
				} else {
					responseXML = "应答报文长度为零！";
					throw portalModuleException.newInstanceMsg("交易（交易代码："+ transCode + "， 交易流水号：" +transRefGUID + "）应答报文长度为零！");
				}
				
			} else {
				requestXML = "请求报文长度为零！";
				throw portalModuleException.newInstanceMsg("交易（交易代码："+ transCode + "， 交易流水号：" +transRefGUID + "）请求报文长度为零！");
			}
		} catch (AxisFault e) {
			responseXML = "发送请求异常：" + e.getMessage();
			e.printStackTrace();
			throw portalModuleException.newInstanceMsg("交易（交易代码："+ transCode + "， 交易流水号：" +transRefGUID + "）发送请求异常！");
		} finally {
			if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
				TransactionMessage transMessage = new TransactionMessage();
				transMessage.setTransCode(transCode);
				transMessage.setTransRefGuid(transRefGUID);
				transMessage.setTransTime(transDate);
				transMessage.setRequestTime(transDate);
				transMessage.setRequestMessage(requestXML);
				transMessage.setResponseTime(new Date());
				transMessage.setResponseMessage(responseXML);
				transactionMessageService.addTransactionMessage(transMessage);
			}
		}
	}
	
	/***
	 * 
	 * 获取接口信息
	 * @param transCode 交易代码
	 * @return 接口信息
	 * @throws portalModuleException
	 */
	@ImplTraced
	public InterfaceInfo findInterfaceByTransCode(String transCode) throws portalModuleException {
		InterfaceInfo interfaceInfo = interfaceInfoService.findInterfaceInfoByTransCode(transCode);
		if (interfaceInfo != null) {
			// 判断接口状态是否可用
			if (InterfaceStatus.ENABLED.equals(interfaceInfo.getStatus())) {
				ClientUser clientUser = interfaceInfo.getClientUser();
				if (clientUser != null) {
					// 如果接口存在所对应的用户信息，判断用户状态是否可用
					if (UserStatus.ENABLED.equals(clientUser.getStatus())) {
						return interfaceInfo;
					} else {
						throw portalModuleException.newInstanceMsg(clientUser.getLoginName()
								+ "用户所对应的接口" + interfaceInfo.getTransName()
								+ "未启用！");
					}
				} else {
					return interfaceInfo;
				}
			} else {
				throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "接口未启用！");
			}

		} 
		return null;
	}
	
	/***
	 * RPC以字符串形式发送请求数据
	 * 
	 * @param interfaceInfo 接口信息
	 * @param requestXML 请求信息
	 * @return 应答报文
	 * @throws AxisFault 
	 */
	@ImplTraced
	public String sendRPCXML(InterfaceInfo interfaceInfo, String requestXML) throws AxisFault {
		AxisClient axisClient = new AxisClient();
		String responseXML = null;
		String requestURL = interfaceInfo.getRequestURL();
		String transCode = interfaceInfo.getTransCode();
		String namespaceURL = interfaceInfo.getNamespaceURL();
		String localPart = interfaceInfo.getLocalPart(); 
		int localPartParameterNumber = interfaceInfo.getLocalPartParameterNumber();
		if ( 1 == localPartParameterNumber) {
			responseXML = axisClient.sendRPCRequestXML(requestURL, namespaceURL, localPart, requestXML);
		} else {
			List<String> localPartParameterList = new ArrayList<String>();
			localPartParameterList.add(requestXML);
			localPartParameterList.add(transCode);
			responseXML = axisClient.sendRPCRequestXML(requestURL, namespaceURL, localPart, localPartParameterList);
		}
		return responseXML;
	}
	
	/**
	 * 
	 *清除Axis2临时文件
	 * 
	 */
	public void cleanAxis2TempFiles(){
		AxisClient axisClient = new AxisClient();
		axisClient.cleanAxis2TempFiles();
	}
	
	/**
	 * 把请求对象和应答对象以json的格式存储到交易记录中
	 * @param requestObj 请求对象
	 * @param responseObj 应答对象
	 * @param transCode 交易代码
	 */
	public void writeMessage(Object requestObj, Object responseObj,
			String transCode) {
		try {
			InterfaceInfo interfaceInfo = findInterfaceByTransCode(transCode);
			if (interfaceInfo != null) {
				if (InterfaceStatus.ENABLED.equals(interfaceInfo.getStatus())) {
					if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
						TransactionMessage transMessage = new TransactionMessage();
						JsonBinder jb = JsonBinder.buildNormalBinder();
						jb.setDateFormat("yyyy-MM-dd hh:mm:ss");
						transMessage.setTransCode(transCode);
						transMessage.setTransRefGuid(GeneratorTransSerialNumber.generatorTransSerialNumber());
						transMessage.setTransTime(new Date());
						transMessage.setFrontRequestTime(new Date());
						transMessage.setFrontRequestMessage(jb.toJson(requestObj));
						transMessage.setFrontResponeTime(new Date());
						transMessage.setFrontResponseMessage(jb.toJson(responseObj));
						transactionMessageService.addTransactionMessage(transMessage);
					}
				} else {
					throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "接口未启用！");
				}
			} else {
				throw portalModuleException.newInstanceMsg("未查到" + transCode + "接口信息！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	public String writeRequestMessage(Object requestObj, String transCode) {
		try {
			InterfaceInfo interfaceInfo = findInterfaceByTransCode(transCode);
			if (interfaceInfo != null) {
				if (InterfaceStatus.ENABLED.equals(interfaceInfo.getStatus())) {
					if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
						TransactionMessage transMessage = new TransactionMessage();
						JsonBinder jb = JsonBinder.buildNormalBinder();
						jb.setDateFormat("yyyy-MM-dd hh:mm:ss");
						transMessage.setTransCode(transCode);
						transMessage.setTransRefGuid(GeneratorTransSerialNumber.generatorTransSerialNumber());
						transMessage.setTransTime(new Date());
						transMessage.setFrontRequestTime(new Date());
						transMessage.setFrontRequestMessage(jb.toJson(requestObj));
						transactionMessageService.addTransactionMessage(transMessage);
						return  transMessage.getTransRefGuid();
					}
				} else {
					throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "接口未启用！");
				}
			} else {
				throw portalModuleException.newInstanceMsg("未查到" + transCode + "接口信息！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}

	public void writeResponseMessage(Object responseObj, String transCode, String transRefGuid) {
		try {
			InterfaceInfo interfaceInfo = findInterfaceByTransCode(transCode);
			if (interfaceInfo != null) {
				if (InterfaceStatus.ENABLED.equals(interfaceInfo.getStatus())) {
					if (RecordMessageType.NOT_RECORD.equals(interfaceInfo.getIsRecordMessage())) {
						TransactionMessage transMessage = new TransactionMessage();
						JsonBinder jb = JsonBinder.buildNormalBinder();
						jb.setDateFormat("yyyy-MM-dd hh:mm:ss");
						transMessage.setTransCode(transCode);
						transMessage.setTransRefGuid(transRefGuid);
						//transMessage.setTransTime(new Date());
						transMessage.setFrontResponeTime(new Date());
						transMessage.setFrontResponseMessage(jb.toJson(responseObj));
						transactionMessageService.updateTransactionMessage(transMessage);
					}
				} else {
					throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "接口未启用！");
				}
			} else {
				throw portalModuleException.newInstanceMsg("未查到" + transCode + "接口信息！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	public static String getExceptionStr(Throwable e){
		try {
			StringWriter writer = new StringWriter();
			PrintWriter printWriter  = new PrintWriter(writer);
			e.printStackTrace(printWriter);
			return writer.toString();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return null;
	}


	// 生成esb流水号
	public String getTranNo(Date date){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + validateCode(6);
	}
	
	/** 
     * 生成任意位数随机数 
     * @param code_len(位数) 
     * @return 
     */  
	public static String validateCode(int code_len) {  
	        int count = 0;  
	        char str[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
	        StringBuffer pwd = new StringBuffer("");  
	        Random r = new Random();  
	        while (count < code_len) {  
	            int i = Math.abs(r.nextInt(10));  
	            if (i >= 0 && i < str.length) {  
	                pwd.append(str[i]);  
	                count++;  
	            }  
	        }  
	        return pwd.toString();  
	 }


}
