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
	 * @param requestObject �������
	 * @param transCode ���״���
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
						stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(60000L);//���ó�ʱ��Ϊһ����
						return MethodUtils.invokeMethod(stub, interfaceInfo.getLocalPart(), sendObject);
					}
					
				} else {
					throw portalModuleException.newInstanceMsg("δ�ҵ� "+ transCode + "��Ӧ�Ĵ����࣡");
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
	 * ��ͳ����ר��
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
						stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(2*60000L);//���ó�ʱ��Ϊһ����
						return MethodUtils.invokeMethod(stub, interfaceInfo.getLocalPart(), sendObject);
					}
					
				} else {
					throw portalModuleException.newInstanceMsg("δ�ҵ� "+ transCode + "��Ӧ�Ĵ����࣡");
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
	 * @param requestObject �������
	 * @param transCode ���״���
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
						stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(60000L);//���ó�ʱʱ��Ϊһ����
						return MethodUtils.invokeMethod(stub, interfaceInfo.getLocalPart(), sendObject);
					}
					
				} else {
					throw portalModuleException.newInstanceMsg("δ�ҵ� "+ transCode + "��Ӧ�Ĵ����࣡");
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
	 * @param requestObject �������
	 * @param transCode ���״���
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
					throw portalModuleException.newInstanceMsg("δ�ҵ� "+ transCode + "��Ӧ�Ĵ����࣡");
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
	 * ws-01:�Ż�ͨ���ֵĳ����쳣,���쳣����
	 * ws-02:�Ż�ͨ���ֵĳ����쳣�Լ�esb�쳣�����ַ�����Ϣ
	 * ws-03:�ӿ�У�鲻ͨ��
	 * @param requestObject �������
	 * @param transCode ���״���
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
			// �ӿ�У��
			interfaceInfo = interfaceInfoService.findInterfaceInfoByTransCode(transCode);
			if (interfaceInfo != null) {
				// �жϽӿ�״̬�Ƿ����
				if (InterfaceStatus.ENABLED.equals(interfaceInfo.getStatus())) {
					ClientUser clientUser = interfaceInfo.getClientUser();
					if(clientUser == null){
						
					}
					if (clientUser != null) {
						// ����ӿڴ�������Ӧ���û���Ϣ���ж��û�״̬�Ƿ����
						if (!UserStatus.ENABLED.equals(clientUser.getStatus())) {
							
						}
					}
				} else {
					exceptionStr = "�ӿ�δ���ã�";
				}
			}else{
				exceptionStr = "�ӿڲ����ڣ�";
			}
			if(StringUtils.isNotBlank(exceptionStr)){
				wsReturnMap = new HashMap<String, Object>();
				wsReturnMap.put("ws-03", exceptionStr);
				wSResult.setResultFlag(wsReturnMap);
				return wSResult;
			}
			
			
			// ��ȡstub
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
				
				// ����esb����ͷ
				requestHeadStr = "<cl:HEADER xmlns:cl=\""+interfaceInfo.getNamespaceURL()+"\">" +
			    "<SVCNAME>"+interfaceInfo.getEsbSvcName()+"</SVCNAME>" + 
			    "<SVCCODE>"+interfaceInfo.getTransCode()+"</SVCCODE>" +
			    "<SVCVER>"+interfaceInfo.getEsbserviceversion() +"</SVCVER>" +
			    // ����ϵͳ�Ĵ��붨Ϊ��GROUP-EBP
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
				
				// ����
				Object[] prams = new Object[]{requestObject,transRefGUID};
				responseObj = MethodUtils.invokeMethod(stub, interfaceInfo.getLocalPart(), prams);
				// ��ȡӦ��ͷȫ����Ϣ
				Map<String,Object> responseHead = SoapHelper.getReturnHead(transRefGUID);
				if(responseHead!=null){
					responseHeadMap = responseHead.toString();
					
					String retCode  = (String)responseHead.get("RETCODE");
					String retInfo  = (String)responseHead.get("RETINFO");
			 
					if(StringUtils.isNotBlank(retCode)){
						// 0000��ʶ�˴�����ɹ���1001Ϊ�������ҵ���쳣
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
			
			// ws-01 Ϊ�Ż�ͨ���ֵĳ����쳣
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
	 * ֱ�ӵ��÷������ķ�����������
	 * @param transCode ���״���
	 * @param param �������
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
	 * XML��ʽ����
	 * 
	 * @param buinessData
	 *            ҵ������
	 * @param transCode
	 *            ���״���
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
	 * �����ҳ���ķ�ʽ
	 * 
	 * @param txXMLReq ������װ����
	 * 
	 * @param transCode ���״���
	 * 
	 * @return ������װӦ�����
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
	 * ����ǩ�±���������
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
					responseXML = "Ӧ���ĳ���Ϊ�㣡";
					throw portalModuleException.newInstanceMsg("���ף����״��룺"+ transCode + "�� ������ˮ�ţ�" +transRefGUID + "��Ӧ���ĳ���Ϊ�㣡");
				}
				
			} else {
				responseXML = "Ӧ���ĳ���Ϊ�㣡";
				throw portalModuleException.newInstanceMsg("���ף����״��룺"+ transCode + "�� ������ˮ�ţ�" +transRefGUID + "�������ĳ���Ϊ�㣡");
			}
			
			}
		} catch (AxisFault e) {
			responseXML = "���������쳣��" + e.getMessage();
			e.printStackTrace();
			throw portalModuleException.newInstanceMsg( "���ף����״��룺"+ transCode + "�� ������ˮ�ţ�" +transRefGUID + "�����������쳣��" + e.getMessage());
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
	 * Ӧ���ⲿϵͳ����
	 * @param tmResp Ӧ�����
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
			frontResponseXML += "��װӦ�����쳣��" + e.getMessage();
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
	 * �����ⲿϵͳ����
	 * @param frontRequestXML �ⲿϵͳ������
	 * @param transCode ������ˮ��
	 * @return �ⲿϵͳ�������
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
										throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "�ӿ�δ���ã�");
									}
								} else {
									throw portalModuleException.newInstanceMsg("δ�鵽��" + txReqExts.getOperator() + "���" + transCode + "�ӿ���Ϣ��");
								}
							} else {
								throw portalModuleException.newInstanceMsg(txReqExts.getOperator() + "�û�״̬δ���ã�");
							}

						} else {
							throw portalModuleException.newInstanceMsg(txReqExts.getOperator() + "���벻��ȷ��");
						}

					} else {
						throw portalModuleException.newInstanceMsg("δ�鵽" + txReqExts.getOperator() + "�����Ϣ��");
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/***
	 * ��������չ��Ϣ�ı���
	 * @param interfaceInfo �ӿ���Ϣ
	 * @param clientUser  �û���Ϣ
	 * @param buinessData ҵ������
	 * @param iInsuranceExtension ҵ��������չ��Ϣ
	 * @param transCode ���״���
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
					responseXML = "Ӧ���ĳ���Ϊ�㣡";
					throw portalModuleException.newInstanceMsg("���ף����״��룺"+ transCode + "�� ������ˮ�ţ�" +transRefGUID + "��Ӧ���ĳ���Ϊ�㣡");
				}
				
			} else {
				requestXML = "�����ĳ���Ϊ�㣡";
				throw portalModuleException.newInstanceMsg("���ף����״��룺"+ transCode + "�� ������ˮ�ţ�" +transRefGUID + "�������ĳ���Ϊ�㣡");
			}
		} catch (AxisFault e) {
			responseXML = "���������쳣��" + e.getMessage();
			e.printStackTrace();
			throw portalModuleException.newInstanceMsg("���ף����״��룺"+ transCode + "�� ������ˮ�ţ�" +transRefGUID + "�����������쳣��");
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
	 * ��ȡ�ӿ���Ϣ
	 * @param transCode ���״���
	 * @return �ӿ���Ϣ
	 * @throws portalModuleException
	 */
	@ImplTraced
	public InterfaceInfo findInterfaceByTransCode(String transCode) throws portalModuleException {
		InterfaceInfo interfaceInfo = interfaceInfoService.findInterfaceInfoByTransCode(transCode);
		if (interfaceInfo != null) {
			// �жϽӿ�״̬�Ƿ����
			if (InterfaceStatus.ENABLED.equals(interfaceInfo.getStatus())) {
				ClientUser clientUser = interfaceInfo.getClientUser();
				if (clientUser != null) {
					// ����ӿڴ�������Ӧ���û���Ϣ���ж��û�״̬�Ƿ����
					if (UserStatus.ENABLED.equals(clientUser.getStatus())) {
						return interfaceInfo;
					} else {
						throw portalModuleException.newInstanceMsg(clientUser.getLoginName()
								+ "�û�����Ӧ�Ľӿ�" + interfaceInfo.getTransName()
								+ "δ���ã�");
					}
				} else {
					return interfaceInfo;
				}
			} else {
				throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "�ӿ�δ���ã�");
			}

		} 
		return null;
	}
	
	/***
	 * RPC���ַ�����ʽ������������
	 * 
	 * @param interfaceInfo �ӿ���Ϣ
	 * @param requestXML ������Ϣ
	 * @return Ӧ����
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
	 *���Axis2��ʱ�ļ�
	 * 
	 */
	public void cleanAxis2TempFiles(){
		AxisClient axisClient = new AxisClient();
		axisClient.cleanAxis2TempFiles();
	}
	
	/**
	 * ����������Ӧ�������json�ĸ�ʽ�洢�����׼�¼��
	 * @param requestObj �������
	 * @param responseObj Ӧ�����
	 * @param transCode ���״���
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
					throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "�ӿ�δ���ã�");
				}
			} else {
				throw portalModuleException.newInstanceMsg("δ�鵽" + transCode + "�ӿ���Ϣ��");
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
					throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "�ӿ�δ���ã�");
				}
			} else {
				throw portalModuleException.newInstanceMsg("δ�鵽" + transCode + "�ӿ���Ϣ��");
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
					throw portalModuleException.newInstanceMsg(interfaceInfo.getTransName() + "�ӿ�δ���ã�");
				}
			} else {
				throw portalModuleException.newInstanceMsg("δ�鵽" + transCode + "�ӿ���Ϣ��");
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


	// ����esb��ˮ��
	public String getTranNo(Date date){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + validateCode(6);
	}
	
	/** 
     * ��������λ������� 
     * @param code_len(λ��) 
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
