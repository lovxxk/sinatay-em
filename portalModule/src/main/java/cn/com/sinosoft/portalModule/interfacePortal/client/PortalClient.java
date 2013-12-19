package cn.com.sinosoft.portalModule.interfacePortal.client;

import java.text.SimpleDateFormat;

public interface PortalClient {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
	
}
