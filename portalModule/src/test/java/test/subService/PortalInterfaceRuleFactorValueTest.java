package test.subService;

import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.portalModule.enumUtil.FactorType;
import cn.com.sinosoft.portalModule.enumUtil.FactorValueType;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfacePayment;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactor;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactorValue;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfacePaymentService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorValueService;

/**
 * 接口规则具体值测试类
 * @author lovxxk
 *
 */
public class PortalInterfaceRuleFactorValueTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load(new String[]{
					"classpath:/testConfig/applicationContext.xml"
					,"classpath:/testConfig/applicationContext-dataAccess.xml"
					,"classpath:/testConfig/applicationContext-hibernate.xml"
					,"classpath:/spring/applicationContext-interfacePortal.xml"
				});
		ctx.refresh();
		
		PortalInterfaceRuleFactorService portalInterfaceRuleFactorService = ctx.getBean("portalInterfaceRuleFactorService",PortalInterfaceRuleFactorService.class);
		PortalInterfaceRuleFactorValueService portalInterfaceRuleFactorValueService = ctx.getBean("portalInterfaceRuleFactorValueService",PortalInterfaceRuleFactorValueService.class);
		PortalInterfacePaymentService portalInterfacePaymentService = ctx.getBean("portalInterfacePaymentService",PortalInterfacePaymentService.class);
		
		String valueCode = "IdentityValidator";
		String valueName = "身份证校验";
		Integer factorType = FactorType.INTERFACE_VERIFICATION.getValue();
		String cvalue = "cn.com.sinosoft.portalModule.transport.sinatay.insvalidator.IdentityValidator";
		String systemCode = "TBJ";
		String transCode = "ST000090";
		String factorCode = "ST000022IdentityValidator";
		valueCode = "payType";
		
		List<PortalInterfacePayment> pipays = portalInterfacePaymentService.findPortalInterfacePaymentByQueryRule(QueryRule.getInstance());
		if("payType".equals(valueCode) && pipays != null && !pipays.isEmpty()){
			PortalInterfacePayment pipay = pipays.get(0);
			valueName = pipay.getPayName();
			cvalue = pipay.getPayType();
		}
		
		PortalInterfaceRuleFactorValue pirfv = new PortalInterfaceRuleFactorValue();
		pirfv.setValueCode(valueCode);
		pirfv.setValueName(valueName);
		pirfv.setStatus("1");
		pirfv.setCvalue(cvalue);
		pirfv.setOperatorID("admin");
		//查找并关联需要校验的接口
		Map<String, Object> propertyMap = new HashMap<String, Object>();
		if(factorType == FactorType.INTERFACE_VERIFICATION.getValue()){
			pirfv.setFactorValueType(FactorValueType.INTERFACE_VERIFICATION_PROCESS.getValue());
			propertyMap.put("status", "1");
			propertyMap.put("factorType", factorType);
			propertyMap.put("factorCode", factorCode);
		}else{
			pirfv.setFactorValueType(FactorValueType.INTERFACE_PROCESS.getValue());
			propertyMap.put("status", "1");
			propertyMap.put("factorType", factorType);
			propertyMap.put("systemCode", systemCode);
			propertyMap.put("transCode", transCode);
		}
		PortalInterfaceRuleFactor oldPirf = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorByQueryMap(propertyMap);
		if(oldPirf != null){
			pirfv.setPortalInterfaceRuleFactor(oldPirf);

			//添加接口校验具体值
//			portalInterfaceRuleFactorValueService.addPortalInterfaceRuleFactorValue(pirfv);
		}
		
		List<PortalInterfaceRuleFactorValue> pirfcs = new ArrayList<PortalInterfaceRuleFactorValue>();
		pirfcs = portalInterfaceRuleFactorValueService.findPortalInterfaceRuleFactorValueByQueryRule(QueryRule.getInstance());
		System.out.println("pirfcs.size(): "+pirfcs.size());
		
		String serialNo = "";
		if(pirfcs != null && !pirfcs.isEmpty())
			serialNo = pirfcs.get(0).getSerialNo();
		System.out.println("serialNo: "+serialNo);
		PortalInterfaceRuleFactorValue tempPirfv = portalInterfaceRuleFactorValueService.findPortalInterfaceRuleFactorValueBySerialNo(serialNo);
		if(tempPirfv != null){
			tempPirfv.setValueName(valueName+"EditTest");
//			portalInterfaceRuleFactorValueService.updatePortalInterfaceRuleFactorValue(tempPirfv);
//			portalInterfaceRuleFactorValueService.deletePortalInterfaceRuleFactorValue(tempPirfv);
		}
		
		
		//测试查询接口规则处理类、校验器
		systemCode = "TBJ";
		transCode = "ST000022";
		List<String> lists = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorProcessClass(transCode, systemCode);
		System.out.println("lists.size(): "+lists.size());
		System.out.println("lists: : "+Arrays.asList(lists));
		lists = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorVerificationProcessClass(transCode, systemCode);
		System.out.println("lists.size(): "+lists.size());
		System.out.println("lists: : "+Arrays.asList(lists));
	}

}
