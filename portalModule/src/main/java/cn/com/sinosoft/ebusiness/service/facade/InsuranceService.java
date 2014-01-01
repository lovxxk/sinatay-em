package cn.com.sinosoft.ebusiness.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
import cn.com.sinosoft.portalModule.enumUtil.SaveMessageType;
import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;
import cn.com.sinosoft.portalModule.portalTransaction.service.fascade.PortalTransactionService;
import cn.com.sinosoft.portalModule.transport.service.InterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public abstract class InsuranceService {

	@Autowired
	protected PortalInterfaceService portalInterfaceService;

	@Autowired
	private PortalInterfaceRuleFactorService portalInterfaceRuleFactorService;
	
	@Autowired
	private PortalTransactionService portalTransactionService;
	
	@Autowired
	private InterfaceTransportService interfaceTransportService;

	// 编组
	private TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();

	protected String process(InsuranceVerifiable ins) {
		
		savaInfoBeforeRequest(ins);
		
		InsuranceVerifiable insConvertFromReturn = handleRequest(ins);
		
		//再编组返回给客户端
		String responseXmlToClient = createRequestOrResponseXml(insConvertFromReturn,MessageType.RESPONSE.getDataENName());
        
		//存储内部系统响应客户端的报文
		saveXmlMessage(ins, responseXmlToClient, MessageType.FRONTRESPONSE,getRequestProcessStatus(insConvertFromReturn));
		
		//响应客户端之前存数据库的操作未实现
		savaInfoAfterResponse(insConvertFromReturn);
		
		return responseXmlToClient;
	}

	protected String createRequestOrResponseXml(InsuranceVerifiable ins,
			String reqOrRes) {
		TXInsurance txIns = (TXInsurance) ins;
		PortalInterface portalInterface = portalInterfaceService
				.findPortalInterfaceByTransCode(txIns.getTransType());
		String reqOrResXml = txFactory.getConvertMessageService().marshaller(
				(TXInsurance) ins, txIns.getTransType(),
				portalInterface.getMessageEncoding(), reqOrRes);
		return reqOrResXml;
	}
	
	// 在发核心请求之前存储由InsuranceVerifiable实例对象编组得到的xml报文，即存储发往核心的报文
	@Async
	protected void saveXmlMessage(InsuranceVerifiable ins, String requestXml,
			MessageType messageType, RequestProcessStatus status) {
		
		TXInsurance txIns = (TXInsurance) ins;
		// 查询存储报文的类型
		SaveMessageType saveMsgType = portalInterfaceRuleFactorService
				.findPortalInterfaceRuleFactorSaveMessageType(
						txIns.getTransType(), txIns.getSource());

		if (saveMsgType == SaveMessageType.DATABASE) {
			portalTransactionService.insertAndUpdatePortalTransaction(
					txIns.getSource(), txIns.getTransType(), requestXml,
					txIns.getTransRefGUID(), null, null, MessageType.REQUEST,
					status);
		} else if (saveMsgType == SaveMessageType.FILESYSTEM) {

		} else {

		}

	}
	
	@Async
	protected abstract InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins);
	
	@Async
	protected abstract InsuranceVerifiable savaInfoAfterResponse(InsuranceVerifiable ins);
	
	protected InsuranceVerifiable handleRequest(InsuranceVerifiable ins){
		TXInsurance txIns = (TXInsurance) ins;
		String requestXmlToCore = createRequestOrResponseXml(ins,MessageType.REQUEST.getDataENName());

		// 存储内部系统发往核心的报文
		saveXmlMessage(txIns, requestXmlToCore, MessageType.REQUEST,
				RequestProcessStatus.OK);
		
		// 发送报文到核心
		String responseXmlFromCore = "";
		try {
			responseXmlFromCore = interfaceTransportService.sendServletRequestXML(requestXmlToCore,txIns.getTransType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 反编组，从核心响应报文反编组
		InsuranceVerifiable insConvertFromReturn = (TXInsurance) txFactory
		.getConvertMessageService().unMarshall(responseXmlFromCore,txIns.getTransType(),MessageType.RESPONSE.getDataENName());
		
		//存核心响应报文
		saveXmlMessage(ins, responseXmlFromCore, MessageType.RESPONSE,getRequestProcessStatus(insConvertFromReturn));
		
		return insConvertFromReturn;
	}
	
	//判断是否交易成功
		protected RequestProcessStatus getRequestProcessStatus(InsuranceVerifiable ins){
			
			TXInsurance txIns = (TXInsurance) ins;
			TranResponse tranRes = (TranResponse) txIns.getBusinessDatum().get(0);
			if("1".equals(tranRes.getFlag())){
				return RequestProcessStatus.SUCCESS;
			}
			return RequestProcessStatus.FAIL;
		}
}
