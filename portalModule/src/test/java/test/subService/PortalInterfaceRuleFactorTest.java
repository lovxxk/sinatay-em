package test.subService;

import ins.framework.common.QueryRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;

import cn.com.sinosoft.portalModule.enumUtil.AllowValuesType;
import cn.com.sinosoft.portalModule.enumUtil.FactorType;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterface;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceRuleFactor;
import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfaceSystem;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceRuleFactorService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceService;
import cn.com.sinosoft.portalModule.portalInterface.service.fascade.PortalInterfaceSystemService;

/**
 * �ӿڹ��������
 * @author lovxxk
 *
 */
public class PortalInterfaceRuleFactorTest {

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
		PortalInterfaceService portalInterfaceService = ctx.getBean("portalInterfaceService",PortalInterfaceService.class);
		PortalInterfaceSystemService portalInterfaceSystemService =  ctx.getBean("portalInterfaceSystemService",PortalInterfaceSystemService.class);
		
		//�ⲿϵͳ��Ӧ�ӿ�
		String factorCode = "TBJST000090";
		String factorName = "ͭ�������֧���ӿڹ���";
		String factorDesc = "ͭ�������֧���ӿڹ���";
		String transCode = "ST000090";
		String systemCode = "TBJ";
		
		PortalInterfaceRuleFactor pirf = new PortalInterfaceRuleFactor();
		pirf.setFactorCode(factorCode);
		pirf.setFactorName(factorName);
		pirf.setFactorDesc(factorDesc);
		pirf.setFactorType(FactorType.SYSTEM_INTERFACE.getValue());
		pirf.setFactorLevel(1);
		pirf.setStatus("1");
		//����ST000022�ӿڲ�����
		PortalInterface portalInterface = portalInterfaceService.findPortalInterfaceByTransCode(transCode);
		if(portalInterface != null){
			pirf.setPortalInterface(portalInterface);
			pirf.setTransCode(portalInterface.getTransCode());
		}
		//����ͭ���ϵͳ��Ϣ������
		PortalInterfaceSystem portalInterfaceSystem = portalInterfaceSystemService.findPortalInterfaceSystemBySystemCode(systemCode);
		if(portalInterfaceSystem != null){
			pirf.setPortalInterfaceSystem(portalInterfaceSystem);
			pirf.setSystemCode(portalInterfaceSystem.getSystemCode());
		}
		pirf.setReqCastorMappingPath("");
		pirf.setResCastorMappingPath("");
		
		//����ⲿϵͳ��Ӧ�ӿ�
//		portalInterfaceRuleFactorService.addPortalInterfaceRuleFactor(pirf);
		
		List<PortalInterfaceRuleFactor> pirfs = new ArrayList<PortalInterfaceRuleFactor>();
		pirfs = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorByQueryRule(QueryRule.getInstance());
		System.out.println("pirfs.size(): "+pirfs.size());
		
		String serialNo = "";
		if(pirfs != null && !pirfs.isEmpty())
			serialNo = pirfs.get(0).getSerialNo();
		System.out.println("serialNo: "+serialNo);
		PortalInterfaceRuleFactor tempPirf = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorBySerialNo(serialNo);
		if(tempPirf != null){
			tempPirf.setFactorDesc(factorDesc+"EditTest");
//			portalInterfaceRuleFactorService.updatePortalInterfaceRuleFactor(tempPirf);
//			portalInterfaceRuleFactorService.deletePortalInterfaceRuleFactor(tempPirf);
		}
		
		
		//�ӿ������֤
		factorCode = "ST000022IdentityValidator";
		factorName = "���ߺ˱��ӿ����֤У��";
		factorDesc = "ͭ������ߺ˱��ӿڹ���";
		
		PortalInterfaceRuleFactor pirfc = new PortalInterfaceRuleFactor();
		pirfc.setFactorCode(factorCode);
		pirfc.setFactorName(factorName);
		pirfc.setFactorDesc(factorDesc);
		pirfc.setFactorType(FactorType.INTERFACE_VERIFICATION.getValue());
		pirfc.setAllowValuesType(AllowValuesType.NOTLIMITED.getValue());
		pirfc.setFactorLevel(1);
		pirfc.setStatus("1");
		
		Map<String, String> propertyMap = new HashMap<String, String>();
		propertyMap.put("systemCode", "TBJ");
		propertyMap.put("transCode", "ST000022");
		PortalInterfaceRuleFactor oldPirf = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorByQueryMap(propertyMap);
		if(oldPirf != null)
			pirfc.setPortalInterfaceRuleFactor(oldPirf);
		
		//��ӽӿڹ���
//		portalInterfaceRuleFactorService.addPortalInterfaceRuleFactor(pirfc);
		
		List<PortalInterfaceRuleFactor> pirfcs = new ArrayList<PortalInterfaceRuleFactor>();
		pirfcs = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorByQueryRule(QueryRule.getInstance());
		System.out.println("pirfcs.size(): "+pirfcs.size());
		
		String serialNoc = "";
		if(pirfcs != null && !pirfcs.isEmpty())
			serialNoc = pirfcs.get(0).getSerialNo();
		System.out.println("serialNoc: "+serialNoc);
		PortalInterfaceRuleFactor tempPirfc = portalInterfaceRuleFactorService.findPortalInterfaceRuleFactorBySerialNo(serialNoc);
		if(tempPirfc != null){
			tempPirfc.setFactorDesc(factorDesc+"EditTest");
//			portalInterfaceRuleFactorService.updatePortalInterfaceRuleFactor(tempPirfc);
//			portalInterfaceRuleFactorService.deletePortalInterfaceRuleFactor(tempPirfc);
		}
		
		
		
	}

}
