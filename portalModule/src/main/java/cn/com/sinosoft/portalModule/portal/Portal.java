package cn.com.sinosoft.portalModule.portal;

import java.text.SimpleDateFormat;

import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;

public interface Portal {
	
	TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	static final int timeOutMilliSeconds = 1 * 60 * 1000;
	
}
