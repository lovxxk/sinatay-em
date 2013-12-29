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
		//���õķ����ĵķ���,�������ת��xml���͸����ġ�
		//�˴����Ǵ󲿷ֽӿڶ�����Ҫ�����ֱ�ӷ������ģ����Կ��Ƕ����һ��xml������������ValidationContext���Ӷ�Ӧ�����xml��������ʵ��InsuranceVerifiable�ӿڣ��� context����˷�����
		beforeProcess(ins);
		
		TXInsurance txIns = (TXInsurance)ins;
		//����
		TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(txIns.getTransType());
		String requestXml = txFactory.getConvertMessageService().marshaller((TXInsurance)ins, txIns.getTransType(), portalInterface.getMessageEncoding(), MessageType.REQUEST.getDataENName());
		
		//���ͱ��ĵ�����
		
		String responseXml = "";
		
		//������
		InsuranceVerifiable  insConvertFromReturn = (TransMessage) txFactory.getConvertMessageService().unMarshall(responseXml, txIns.getTransType(), MessageType.RESPONSE.getDataENName());
		
		afterProcess(insConvertFromReturn);
		
		return responseXml;
	}
	
	public abstract InsuranceVerifiable beforeProcess(InsuranceVerifiable ins);
	
	public abstract InsuranceVerifiable afterProcess(InsuranceVerifiable ins);
}
