package test.subService;

import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfacePayment;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceSystem;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfacePaymentService;

/**
 * 接口支付信息测试类
 * @author lovxxk
 *
 */
public class PortalInterfacePaymentTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{
					"classpath:/testConfig/applicationContext.xml"
					,"classpath:/testConfig/applicationContext-dataAccess.xml"
					,"classpath:/testConfig/applicationContext-hibernate.xml"
					,"classpath:/spring/applicationContext-interfacePortal.xml"
				});
		ctx.refresh();
		PortalInterfacePaymentService portalInterfacePaymentService = ctx.getBean("portalInterfacePaymentService",PortalInterfacePaymentService.class);
		
		String payType = "2";
		String payName = "银联实时代收";
		String payDesc = "银联实时代收";
		
		PortalInterfacePayment pipay = new PortalInterfacePayment();
		pipay.setPayType(payType);
		pipay.setPayName(payName);
		pipay.setPayDesc(payDesc);
		pipay.setStatus("1");
		pipay.setOperatorID("admin");
		
		//添加支付信息
//		portalInterfacePaymentService.addPortalInterfacePayment(pipay);
		

		List<PortalInterfacePayment> pipays = new ArrayList<PortalInterfacePayment>();
		pipays = portalInterfacePaymentService.findPortalInterfacePaymentByQueryRule(QueryRule.getInstance());
		System.out.println("pipays.size(): "+pipays.size());
		
		String serialNo = "";
		if(pipays != null && !pipays.isEmpty())
			serialNo = pipays.get(0).getSerialNo();
		System.out.println("serialNo: "+serialNo);
		PortalInterfacePayment tempPipay = portalInterfacePaymentService.findPortalInterfacePaymentBySerialNo(serialNo);
		if(tempPipay != null){
			tempPipay.setPayDesc(payDesc+"EditTest");
//			portalInterfacePaymentService.updatePortalInterfacePayment(tempPipay);
//			portalInterfacePaymentService.deletePortalInterfacePayment(tempPipay);
		}
		
	}

}
