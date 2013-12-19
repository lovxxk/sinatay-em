package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis2.client.Stub;

import cn.com.sinosoft.portalModule.interfacePortal.xml.factory.TXInsuranceFactory;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
@SuppressWarnings("rawtypes")
public interface PortalService {
	
	TXInsuranceFactory txFactory = TXInsuranceFactory.getInstance();
	
	Map<String, PortalInterface> portalInterfaceMap = new HashMap<String, PortalInterface>();

	Map<String, Stub> stubClassMap = new HashMap<String, Stub>();
	
	Map<String, Class> insuranceReqClassMap = new HashMap<String, Class>();

	Map<String, Class> insReqExtsClassMap = new HashMap<String, Class>();

	Map<String, Class> stubSendClassMap = new HashMap<String, Class>();
	
	Map<String, Class> pageClassMap = new HashMap<String, Class>();

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
   
	SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	SimpleDateFormat fileDate = new SimpleDateFormat("yyyy_MM_dd");
	
	SimpleDateFormat fileDateTime = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_ssss");
	
	static final int timeOutMilliSeconds = 1 * 60 * 1000;
	
	String xmlEncoding = "GBK";
}
