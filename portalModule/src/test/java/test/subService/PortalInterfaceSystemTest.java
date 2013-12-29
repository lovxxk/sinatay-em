package test.subService;

import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.portalModule.enumUtil.SystemType;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceSystem;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceSystemService;

/**
 * 外部系统信息测试类 
 * @author lovxxk
 *
 */
public class PortalInterfaceSystemTest {
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{
					"classpath:/testConfig/applicationContext.xml"
					,"classpath:/testConfig/applicationContext-dataAccess.xml"
					,"classpath:/testConfig/applicationContext-hibernate.xml"
					,"classpath:/spring/applicationContext-interfacePortal.xml"
				});
		ctx.refresh();
		PortalInterfaceSystemService portalInterfaceSystemService = ctx.getBean("portalInterfaceSystemService",PortalInterfaceSystemService.class);
		
		String systemCode = "DHF";
		String systemName = "大黄蜂";
		String systemDesc = "大黄蜂";
		
		PortalInterfaceSystem pis = new PortalInterfaceSystem();
		pis.setSystemCode(systemCode);
		pis.setSystemName(systemName);
		pis.setSystemDesc(systemDesc);
		pis.setSystemSimpleName(systemName);
		pis.setSystemType(SystemType.EXTERNAL_SYSTEM.getValue());
		pis.setSystemSummary(systemDesc);
		pis.setOperatorID("admin");
		// 添加外部系统信息
//		portalInterfaceSystemService.addPortalInterfaceSystem(pis);
		
		
		List<PortalInterfaceSystem> piss = new ArrayList<PortalInterfaceSystem>();
		piss = portalInterfaceSystemService.findPortalInterfaceSystemByQueryRule(QueryRule.getInstance());
		System.out.println("piss.size(): "+piss.size());
		
		String serialNo = "";
		if(piss != null && !piss.isEmpty())
			serialNo = piss.get(0).getSerialNo();
		System.out.println("serialNo: "+serialNo);
		PortalInterfaceSystem tempPis = portalInterfaceSystemService.findPortalInterfaceSystemBySerialNo(serialNo);
		if(tempPis != null){
			tempPis.setSystemDesc(systemDesc+"EditTest");
//			portalInterfaceSystemService.updatePortalInterfaceSystem(tempPis);
//			portalInterfaceSystemService.deletePortalInterfaceSystem(tempPis);
		}
		
	}
}
