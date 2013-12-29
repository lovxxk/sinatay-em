package test.subService;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.portalModule.enumUtil.EncapsulationType;
import cn.com.sinosoft.portalModule.enumUtil.ReturnType;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;

public class PortalInterfaceTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{
				"classpath:/testConfig/applicationContext.xml"
				,"classpath:/testConfig/applicationContext-dataAccess.xml"
				,"classpath:/testConfig/applicationContext-hibernate.xml"
				,"classpath:/spring/applicationContext-interfacePortal.xml"
			});
		ctx.refresh();
		PortalInterfaceService portalInterfaceService = ctx.getBean("portalInterfaceService", PortalInterfaceService.class);
		
		String transCode = "ST000090";
		String transName = "支付接口";
		String transPortType = "Servlet"; 
//		transPortType = "Axis2RPC";
		String requestURL = "http://10.20.3.123:7001/SIP/URLServer";
		String namespaceURI = "http://10.20.3.123:7001/SIP/URLServer";
		String localPart = "service";
		String messageEncoding = "GBK";
		
		PortalInterface portalInterface = new PortalInterface();
		portalInterface.setTransCode(transCode);
		portalInterface.setTransName(transName);
		portalInterface.setTransPortType(transPortType);
		portalInterface.setRequestURL(requestURL);
		portalInterface.setNamespaceURI(namespaceURI);
		portalInterface.setLocalPart(localPart);
		portalInterface.setEnumEncapsulationType(EncapsulationType.HEADER_ENCAPSULATION);
		portalInterface.setEnumReturnType(ReturnType.RETURN_BUSINESSDATUM);
		portalInterface.setMessageEncoding(messageEncoding);
		
		//添加接口信息
//		portalInterfaceService.addPortalInterface(portalInterface);

		requestURL = "http://10.1.217.7:7001/lis/services/EcommerceService";
		namespaceURI = "http://10.1.217.7:7001/lis/services/EcommerceService";
//		namespaceURI = requestURL;
		QueryRule queryRule = QueryRule.getInstance();
		Page page = portalInterfaceService.findPortalInterface(queryRule, 1, 100);
		List<PortalInterface> portalInterfaces = page.getResult();
		for (PortalInterface _portalInterface : portalInterfaces) {
			_portalInterface.setRequestURL(requestURL);
			_portalInterface.setNamespaceURI(namespaceURI);
//			portalInterfaceService.updatePortalInterface(_portalInterface);
		}
	}

}
