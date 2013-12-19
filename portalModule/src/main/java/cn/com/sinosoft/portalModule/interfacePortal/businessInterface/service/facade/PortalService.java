package cn.com.sinosoft.portalModule.interfacePortal.businessInterface.service.facade;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis2.client.Stub;

import cn.com.sinosoft.portalModule.interfacePortal.domain.InterfaceInfo;

public interface PortalService {

	Map<String, InterfaceInfo> interfaceInfoMap = new HashMap<String, InterfaceInfo>();

	Map<String, Stub> stubClassMap = new HashMap<String, Stub>();

	@SuppressWarnings("rawtypes")
	Map<String, Class> insuranceReqClassMap = new HashMap<String, Class>();

	@SuppressWarnings("rawtypes")
	Map<String, Class> insReqExtsClassMap = new HashMap<String, Class>();

	@SuppressWarnings("rawtypes")
	Map<String, Class> stubSendClassMap = new HashMap<String, Class>();
	
	Map<String, Class> pageClassMap = new HashMap<String, Class>();

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

}
