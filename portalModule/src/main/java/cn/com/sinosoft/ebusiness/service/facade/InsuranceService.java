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

	// ����
	private TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();

	protected String process(InsuranceVerifiable ins) {
		
		savaInfoBeforeRequest(ins);
		
		InsuranceVerifiable insConvertFromReturn = handleRequest(ins);
		
		//�ٱ��鷵�ظ��ͻ���
		String responseXmlToClient = createRequestOrResponseXml(insConvertFromReturn,MessageType.RESPONSE.getDataENName());
        
		//�洢�ڲ�ϵͳ��Ӧ�ͻ��˵ı���
		saveXmlMessage(ins, responseXmlToClient, MessageType.FRONTRESPONSE,getRequestProcessStatus(insConvertFromReturn));
		
		//��Ӧ�ͻ���֮ǰ�����ݿ�Ĳ���δʵ��
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
	
	// �ڷ���������֮ǰ�洢��InsuranceVerifiableʵ���������õ���xml���ģ����洢�������ĵı���
	@Async
	protected void saveXmlMessage(InsuranceVerifiable ins, String requestXml,
			MessageType messageType, RequestProcessStatus status) {
		
		TXInsurance txIns = (TXInsurance) ins;
		// ��ѯ�洢���ĵ�����
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

		// �洢�ڲ�ϵͳ�������ĵı���
		saveXmlMessage(txIns, requestXmlToCore, MessageType.REQUEST,
				RequestProcessStatus.OK);
		
		// ���ͱ��ĵ�����
		String responseXmlFromCore = "";
		try {
			responseXmlFromCore = interfaceTransportService.sendServletRequestXML(requestXmlToCore,txIns.getTransType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// �����飬�Ӻ�����Ӧ���ķ�����
		InsuranceVerifiable insConvertFromReturn = (TXInsurance) txFactory
		.getConvertMessageService().unMarshall(responseXmlFromCore,txIns.getTransType(),MessageType.RESPONSE.getDataENName());
		
		//�������Ӧ����
		saveXmlMessage(ins, responseXmlFromCore, MessageType.RESPONSE,getRequestProcessStatus(insConvertFromReturn));
		
		return insConvertFromReturn;
	}
	
	//�ж��Ƿ��׳ɹ�
		protected RequestProcessStatus getRequestProcessStatus(InsuranceVerifiable ins){
			
			TXInsurance txIns = (TXInsurance) ins;
			TranResponse tranRes = (TranResponse) txIns.getBusinessDatum().get(0);
			if("1".equals(tranRes.getFlag())){
				return RequestProcessStatus.SUCCESS;
			}
			return RequestProcessStatus.FAIL;
		}
}
