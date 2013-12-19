package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade;

import java.util.Map;

public interface GePortalInterfaceExtraService {

	Map<String,String>  findParamFromType(String transType);
	
	String findParamValueFromId(String transType, String paramName);
	
}
