package cn.com.sinosoft.ebusiness.service.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.scheduling.annotation.Async;

import cn.com.sinosoft.ebusiness.service.spring.SaveMsgService;
import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;
import cn.com.sinosoft.portalModule.transport.service.InterfaceTransportService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.sinatay.TranResponse;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;

public abstract class InsuranceService {

	@Autowired
	protected PortalInterfaceService portalInterfaceService;

	@Autowired
	private InterfaceTransportService interfaceTransportService;
	
	@Autowired
	private SaveMsgService saveMsgService;

	// ����
	private TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();

	public String process(InsuranceVerifiable ins) {
		
		savaInfoBeforeRequest(ins);
		
		InsuranceVerifiable insConvertFromReturn = handleRequest(ins);
		
		//�ٱ��鷵�ظ��ͻ���
		String responseXmlToClient = createRequestOrResponseXml(insConvertFromReturn,MessageType.FRONTRESPONSE.getDataENName());
        
		//�洢�ڲ�ϵͳ��Ӧ�ͻ��˵ı���
		saveXmlMessage(ins, responseXmlToClient, MessageType.FRONTRESPONSE,getRequestProcessStatus(insConvertFromReturn));
		return responseXmlToClient;
	}

	protected String createRequestOrResponseXml(InsuranceVerifiable ins,
			String reqOrRes) {
		if (portalInterfaceService == null) {
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load(new String[]{
					"classpath:/spring/applicationContext.xml"
					,"classpath:/spring/applicationContext-dataAccess.xml"
					,"classpath:/spring/applicationContext-hibernate.xml"
					,"classpath:/spring/applicationContext-interfacePortal.xml"
				});
			ctx.refresh();
			portalInterfaceService = ctx.getBean("portalInterfaceService", PortalInterfaceService.class);
		}
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
		if (saveMsgService == null) {
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load(new String[]{
					"classpath:/spring/applicationContext.xml"
					,"classpath:/spring/applicationContext-dataAccess.xml"
					,"classpath:/spring/applicationContext-hibernate.xml"
					,"classpath:/spring/applicationContext-interfacePortal.xml"
				});
			ctx.refresh();
			saveMsgService = ctx.getBean("saveMsgService", SaveMsgService.class);
		}
		saveMsgService.saveXmlMessage(requestXml, messageType, status);
	}
	
	protected InsuranceVerifiable handleRequest(InsuranceVerifiable ins){
		if (interfaceTransportService == null) {
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load(new String[]{
					"classpath:/spring/applicationContext.xml"
					,"classpath:/spring/applicationContext-dataAccess.xml"
					,"classpath:/spring/applicationContext-hibernate.xml"
					,"classpath:/spring/applicationContext-interfacePortal.xml"
				});
			ctx.refresh();
			interfaceTransportService = ctx.getBean("interfaceTransportService", InterfaceTransportService.class);
		}
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
		
		//��Ӧ�ͻ���֮ǰ�����ݿ�Ĳ���δʵ��
		savaInfoBeforeResponse(insConvertFromReturn);
		
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
	
	@Async
	protected abstract InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins);
	
	@Async
	protected abstract InsuranceVerifiable savaInfoBeforeResponse(InsuranceVerifiable ins);
		
}
