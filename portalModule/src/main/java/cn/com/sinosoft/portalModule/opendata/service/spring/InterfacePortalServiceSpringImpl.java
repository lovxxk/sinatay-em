package cn.com.sinosoft.portalModule.opendata.service.spring;


import cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade.InterfaceInfoService;
import cn.com.sinosoft.portalModule.opendata.service.facade.InterfacePortalService;

public class InterfacePortalServiceSpringImpl implements InterfacePortalService {
	private InterfaceInfoService interfaceInfoService;
	public String clearInterface() {
		// TODO Auto-generated method stub
		interfaceInfoService.clearAllCache();//Çå³ýËùÓÐ»º´æ
		return null;
	}
}
