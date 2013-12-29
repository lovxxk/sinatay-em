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

	// ����
	private TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();

	public String process(InsuranceVerifiable ins) {
		
		savaInfoBeforeRequest(ins);
		
		String requestXmlToCore = createRequestOrResponseXml(ins,MessageType.REQUEST.getDataENName());

		// �洢�ڲ�ϵͳ�������ĵı���
		saveXmlMessage(ins, requestXmlToCore, MessageType.REQUEST,
				RequestProcessStatus.OK);

		// �����ĸ����Ļ������
		String responseXmlFromCore = sendRequest(requestXmlToCore);

		// �����飬�Ӻ�����Ӧ���ķ�����
		InsuranceVerifiable insConvertFromReturn = (TXInsurance) txFactory
				.getConvertMessageService().unMarshall(responseXmlFromCore,
						((TXInsurance) ins).getTransType(),
						MessageType.RESPONSE.getDataENName());
		
		//��Ӧ�ͻ���֮ǰ�����ݿ�Ĳ���δʵ��
		savaInfoAfterResponse(insConvertFromReturn);
		
		//�ٱ���
		String requestXmlToClient = createRequestOrResponseXml(insConvertFromReturn,MessageType.REQUEST.getDataENName());

		return requestXmlToClient;
	}

	// ���ͱ��ĵ�����
	protected String sendRequest(String xml) {
		// ���ͱ��ĵ�����
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

	// �ڷ���������֮ǰ�洢��InsuranceVerifiableʵ���������õ���xml���ģ����洢�������ĵı���
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
	
	public abstract InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins);

	public abstract InsuranceVerifiable savaInfoAfterResponse(InsuranceVerifiable ins);

}
