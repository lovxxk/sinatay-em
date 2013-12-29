package cn.com.sinosoft.ebusiness.service.facade;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
import cn.com.sinosoft.portalModule.enumUtil.SaveMessageType;
import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;
import cn.com.sinosoft.portalModule.portalTransaction.service.fascade.PortalTransactionService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public abstract class InsuranceService {

	@Autowired
	protected PortalInterfaceService portalInterfaceService;

	@Autowired
	private PortalInterfaceRuleFactorService portalInterfaceRuleFactorService;
	@Autowired
	private PortalTransactionService portalTransactionService;

	// 编组
	private TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();

	public String process(InsuranceVerifiable ins) {
		
		savaInfoBeforeRequest(ins);
		
		String requestXmlToCore = createRequestOrResponseXml(ins,MessageType.REQUEST.getDataENName());

		// 存储内部系统发往核心的报文
		saveXmlMessage(ins, requestXmlToCore, MessageType.REQUEST,
				RequestProcessStatus.OK);

		// 发报文给核心或第三方
		String responseXmlFromCore = sendRequest(requestXmlToCore);

		// 反编组，从核心响应报文反编组
		InsuranceVerifiable insConvertFromReturn = (TXInsurance) txFactory
				.getConvertMessageService().unMarshall(responseXmlFromCore,
						((TXInsurance) ins).getTransType(),
						MessageType.RESPONSE.getDataENName());
		
		//响应客户端之前存数据库的操作未实现
		savaInfoAfterResponse(insConvertFromReturn);
		
		//再编组
		String requestXmlToClient = createRequestOrResponseXml(insConvertFromReturn,MessageType.REQUEST.getDataENName());

		return requestXmlToClient;
	}

	// 发送报文到核心
	protected String sendRequest(String xml) {
		// 发送报文到核心
		String responseXml = "";
		return responseXml;
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
	
	public abstract InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins);

	public abstract InsuranceVerifiable savaInfoAfterResponse(InsuranceVerifiable ins);

}
