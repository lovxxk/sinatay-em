package cn.com.sinosoft.portalModule.interfacePortal.xml.factory;

import cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping.service.ConvertMessageService;

public abstract class TXInsuranceFactory {

	public static TXInsuranceFactory instance = null;

	private static final Object key = new Object();

	static {

		instance = new TXInsuranceFactoryImpl();
	}

	public static TXInsuranceFactory getInstance() {

		if (instance == null) {

			synchronized (key) {

				if (instance == null) {
					instance = new TXInsuranceFactoryImpl();
				}

			}

		}

		return instance;
	}

	public abstract ConvertMessageService getConvertMessageService();

}
