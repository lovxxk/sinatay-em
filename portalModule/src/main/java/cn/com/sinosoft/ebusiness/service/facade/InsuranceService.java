package cn.com.sinosoft.ebusiness.service.facade;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.portalModule.enumUtil.MessageType;
import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;
import cn.com.sinosoft.portalModule.transport.sinatay.InsuranceVerifiable;
import cn.com.sinosoft.portalModule.transport.transactionObject.TXInsurance;
import cn.com.sinosoft.portalModule.transport.transactionObject.TransMessage;

public abstract class InsuranceService {
	
	@Autowired
	protected PortalInterfaceService portalInterfaceService;
	
	public String process(InsuranceVerifiable ins){
		//共用的发报文的方法,处理过后转成xml发送给核心。
		//此处若是大部分接口都不需要处理可直接发往核心，可以考虑多接受一个xml参数。或者在ValidationContext增加对应对象的xml报文让其实现InsuranceVerifiable接口，将 context传入此方法。
		beforeProcess(ins);
		
		TXInsurance txIns = (TXInsurance)ins;
		//编组
		TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(txIns.getTransType());
		String requestXml = txFactory.getConvertMessageService().marshaller((TXInsurance)ins, txIns.getTransType(), portalInterface.getMessageEncoding(), MessageType.REQUEST.getDataENName());
		
		//发送报文到核心
		
		String responseXml = "";
		
		//反编组
		InsuranceVerifiable  insConvertFromReturn = (TransMessage) txFactory.getConvertMessageService().unMarshall(responseXml, txIns.getTransType(), MessageType.RESPONSE.getDataENName());
		
		afterProcess(insConvertFromReturn);
		
		return responseXml;
	}
	
	public abstract InsuranceVerifiable beforeProcess(InsuranceVerifiable ins);
	
	public abstract InsuranceVerifiable afterProcess(InsuranceVerifiable ins);
}
