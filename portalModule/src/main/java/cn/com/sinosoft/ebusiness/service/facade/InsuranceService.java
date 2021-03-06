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

	// 编组
	private TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();

	public String process(InsuranceVerifiable ins) {
		
		savaInfoBeforeRequest(ins);
		
		InsuranceVerifiable insConvertFromReturn = handleRequest(ins);
		
		//再编组返回给客户端
		String responseXmlToClient = createRequestOrResponseXml(insConvertFromReturn,MessageType.FRONTRESPONSE.getDataENName());
        
		//存储内部系统响应客户端的报文
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
	
	// 在发核心请求之前存储由InsuranceVerifiable实例对象编组得到的xml报文，即存储发往核心的报文
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
		
		//响应客户端之前存数据库的操作未实现
		savaInfoBeforeResponse(insConvertFromReturn);
		
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
	
	@Async
	protected abstract InsuranceVerifiable savaInfoBeforeRequest(InsuranceVerifiable ins);
	
	@Async
	protected abstract InsuranceVerifiable savaInfoBeforeResponse(InsuranceVerifiable ins);
		
}
