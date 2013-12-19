package test;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.portalModule.enumUtil.EncapsulationType;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;

public class PortalInterfaceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{
				"classpath:/spring/applicationContext.xml",
				"classpath:/spring/applicationContext-interfacePortal.xml",
				"classpath:/spring/applicationContext-dataAccess.xml",
				"classpath:/spring/applicationContext-hibernate.xml"});
		ctx.refresh();
		PortalInterfaceService portalInterfaceService = ctx.getBean("portalInterfaceService", PortalInterfaceService.class);
		PortalInterface portalInterface = new PortalInterface();
		portalInterface.setTransCode("ST020002");
		portalInterface.setTransName("发送手机短信接口");
		portalInterface.setTransPortType("Servlet");
		portalInterface.setReturnType(1);
		portalInterface.setRequestURL("http://10.20.3.147:9001/SIP/URLServer");
		portalInterface.setNamespaceURI("http://10.20.3.147:9001/SIP/URLServer");
		portalInterface.setLocalPart("service");
		portalInterface.setEnumEncapsulationType(EncapsulationType.HEADER_ENCAPSULATION);
		portalInterface.setMessageEncoding("GBK");
		portalInterfaceService.addPortalInterface(portalInterface);
		

		String requestURL = "http://10.1.217.7:7001/lis/services/EcommerceService";
		String namespaceURI = "http://10.1.217.7:7001/lis/services/EcommerceService";
		requestURL = "http://10.0.134.101:8080/ui/services/EcommerceService";
		namespaceURI = requestURL;
		QueryRule queryRule = QueryRule.getInstance();
		Page page = portalInterfaceService.findPortalInterface(queryRule, 1, 100);
		List<PortalInterface> portalInterfaces = page.getResult();
		for(PortalInterface _portalInterface : portalInterfaces){
			_portalInterface.setRequestURL(requestURL);
			_portalInterface.setNamespaceURI(namespaceURI);
//			portalInterfaceService.updatePortalInterface(_portalInterface);
		}
	}

}
