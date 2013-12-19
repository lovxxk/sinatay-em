package cn.com.sinosoft.ruleEngine.service.spring;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyService;
import cn.com.sinosoft.ruleEngine.service.facade.InsurancePolicyRuleService;
import cn.com.sinosoft.ruleEngine.util.CheckMessage;
import cn.com.sinosoft.ruleEngine.util.CheckResult;
import cn.com.sinosoft.util.io.FilePathUtil;
import cn.com.sinosoft.util.io.PropertyFileUtils;

public class InsurancePolicyRuleServiceSpringImpl implements InsurancePolicyRuleService {
	
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	
	private static final Map<String, Resource> droolsResourceMap = new HashMap<String,Resource>();
	
	private  static URL getUrl(String fileMame){
		return InsurancePolicyRuleServiceSpringImpl.class.getClassLoader().getResource("drools/" + fileMame);
	}
	
	@Override
	public void insuredRule(InsurancePolicy insurancePolicy){
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		Resource resource = getResource(insurancePolicy.getProductCode());
		if (resource == null) {
			return;
		}
		builder.add(resource, ResourceType.DRL);
		Collection<KnowledgePackage> knowledgePackages = builder.getKnowledgePackages();
		KnowledgeBase kb = KnowledgeBaseFactory.newKnowledgeBase();
		kb.addKnowledgePackages(knowledgePackages);
		BigDecimal currentDayTotalPreminum = insurancePolicyService.findCurrentDayTotalPreminum(insurancePolicy);
		if(currentDayTotalPreminum == null){
			currentDayTotalPreminum = new BigDecimal(0);
		}
		StatefulKnowledgeSession statefulSession = kb.newStatefulKnowledgeSession();
		CheckResult checkResult = new CheckResult();
		PropertyFileUtils.init(FilePathUtil.getResourceFilePath(InsurancePolicyRuleServiceSpringImpl.class, "/drools/config/drools.properties"));
		statefulSession.setGlobal("checkResult", checkResult);
		statefulSession.setGlobal("maxSinglePremium", new BigDecimal(PropertyFileUtils.getConfig("maxSinglePremium")));
		statefulSession.setGlobal("maxCurrentDayTotalPreminum", new BigDecimal(PropertyFileUtils.getConfig("maxCurrentDayTotalPreminum")));
		statefulSession.setGlobal("currentDayTotalPreminum", currentDayTotalPreminum.add(insurancePolicy.getGrossPremium()==null?new BigDecimal(0):insurancePolicy.getGrossPremium()));
		statefulSession.insert(insurancePolicy);
		statefulSession.fireAllRules();
		if (checkResult.hasCheckMessages()) {
			insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_FAIL.getValue());
			insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_FAIL.getDataName());
			StringBuffer checkMessage = new StringBuffer();
			Collection<CheckMessage> checkMessageList = checkResult.getCheckMessages();
			Iterator<CheckMessage> iterator = checkMessageList.iterator();
			while(iterator.hasNext()) {
				CheckMessage message = iterator.next();
				checkMessage.append(message.getMessage());
				if (iterator.hasNext()) {
					checkMessage.append(";");
				}
			}
			insurancePolicy.setPolicyStatusDesc(checkMessage.toString());
		} else {
			insurancePolicy.setPolicyStatus(PolicyStatus.PROPOSAL_SUCC.getValue());
			insurancePolicy.setPolicyStatusName(PolicyStatus.PROPOSAL_SUCC.getDataName());
			insurancePolicy.setPolicyStatusDesc(PolicyStatus.PROPOSAL_SUCC.getDataName());
		}
		
		statefulSession.dispose();
	}
	
	/**
	 * ���ݲ�Ʒ�����ȡ�ò�Ʒ�Ĺ���Դ
	 * @param productCode ��Ʒ����
	 * @return
	 */
	@Override
	public Resource getResource(String productCode) {
		Resource resource = droolsResourceMap.get(productCode);
		if (resource == null) {
			loadRuleResource(droolsResourceMap, productCode);
			resource = droolsResourceMap.get(productCode);
			return resource;
		} else {
			return resource;
		}
	}
	
	/**
	 * ���ݲ�Ʒ��������ò�Ʒ�Ĺ����ļ�
	 * @param productCode ��Ʒ����
	 */
	@Override
	public void clearRuleResource(String productCode) {
		droolsResourceMap.remove(productCode);
	}
	
	/**
	 * ����������й����ļ�
	 * @param productCode ��Ʒ����
	 */
	@Override
	public void clearAllRuleResource() {
		droolsResourceMap.clear();
	}
	
	/**
	 * 
	 * ���ݲ�Ʒ���뽫�����ļ��ŵ����������
	 * @param map ���򻺴�Map����
	 * @param productCode ��Ʒ����
	 * 
	 */
	public static void loadRuleResource(Map<String,Resource> map, String productCode){
		String filePath = productCode + "_rules.drl";
		URL resource = getUrl(filePath);
		if (resource != null) {
			map.put(productCode, ResourceFactory.newUrlResource(resource));	
		}
		 
	}
}
