package cn.com.sinosoft.portalModule.interfacePortal.xml.factory;

import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.service.ConvertMessageService;
import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.service.ConvertMessageServiceImpl;


public class TXInsuranceFactoryImpl extends TXInsuranceFactory {

	private ConvertMessageService convertMessageService;

	public TXInsuranceFactoryImpl() {
		this.convertMessageService = new ConvertMessageServiceImpl();
	}

	@Override
	public ConvertMessageService getConvertMessageService() {

		return convertMessageService;

	}

}
