package cn.com.sinosoft.ruleEngine.service.spring;

import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.RuleBase;
import org.drools.StatefulSession;
import org.drools.agent.RuleAgent;
import org.drools.builder.DecisionTableConfiguration;
import org.drools.builder.DecisionTableInputType;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.runtime.StatefulKnowledgeSession;

import cn.com.sinosoft.ruleEngine.service.facade.DroolsRuleService;
import cn.com.sinosoft.ruleEngine.util.AgentRule;
import cn.com.sinosoft.ruleEngine.util.RuleAgentGen;

@SuppressWarnings("unused")
public class DroolsRuleServiceSpringImpl implements DroolsRuleService {
	private KnowledgeBase kBase = null;
	private KnowledgeRuntimeLogger logger = null;
	private StatefulKnowledgeSession ksession = null;

	public Object executeRules(String ruleFlowName, Object myfact,
			Set<String> ruleResourceSet) {
		kBase = this.readKnowledgeBase(ruleResourceSet);
		ksession = kBase.newStatefulKnowledgeSession();
		ksession.insert(myfact);
		ksession.startProcess(ruleFlowName);
		ksession.fireAllRules();
		return myfact;
	}

	public Object executeRules(String ruleFlowName, Object returnObj,
			Set<Object> insertObjSet, Set<String> ruleResourceSet) {
		kBase = this.readKnowledgeBase(ruleResourceSet);
		ksession = kBase.newStatefulKnowledgeSession();
		for (Object obj : insertObjSet) {
			ksession.insert(obj);
		}
		ksession.startProcess(ruleFlowName);
		ksession.fireAllRules();
		return returnObj;
	}
	
	public Object executeRules(String ruleFlowName, Object myfact, String propertiesFilePath) {
		if (null == propertiesFilePath || "".equals(propertiesFilePath)) {
			propertiesFilePath = "/drools.properties";
		}
		System.out.println(propertiesFilePath);
//		RuleAgent agent = RuleAgent.newRuleAgent(propertiesFilePath);
//		RuleBase rb = agent.getRuleBase();
//		StatefulSession session =rb.newStatefulSession();	
		
		RuleAgentGen ruleAgentGen = RuleAgentGen.getRuleAgentGenInstance();
		RuleAgent agent = ruleAgentGen.getRuleAgent(propertiesFilePath);
		RuleBase ruleBase=agent.getRuleBase();
		StatefulSession session = ruleBase.newStatefulSession();
		session.insert(myfact);
		session.startProcess(ruleFlowName);
		session.fireAllRules();
		session.dispose();		
		
		
		/*
		kBase = (KnowledgeBase) agent.getRuleBase();
		ksession = kBase.newStatefulKnowledgeSession();
		ksession.insert(myfact);
		ksession.startProcess(ruleFlowName);
		ksession.fireAllRules();
		*/
		return myfact;
	}
	
	public Object executeRules(String ruleFlowName, Object inputXOM,
			Object resultXOM, String propertiesFilePath) {
		if (null == propertiesFilePath || "".equals(propertiesFilePath)) {
			propertiesFilePath = "/drools.properties";
		}
		RuleAgentGen ruleAgentGen = RuleAgentGen.getRuleAgentGenInstance();
		RuleAgent agent = ruleAgentGen.getRuleAgent(propertiesFilePath);
		RuleBase ruleBase=agent.getRuleBase();
		StatefulSession session = ruleBase.newStatefulSession();
		
		session.setGlobal("resultXOM", resultXOM);
		session.insert(inputXOM);
		session.startProcess(ruleFlowName);
		session.fireAllRules();
		session.dispose();
		return resultXOM;
	}

	public Object executeRules(String ruleFlowName, Object returnObj,
			Set<Object> insertObjSet, String propertiesFilePath) {
		if (null == propertiesFilePath || "".equals(propertiesFilePath)) {
			propertiesFilePath = "/drools.properties";
		}
//		RuleAgent agent = RuleAgent.newRuleAgent(propertiesFilePath);
//		RuleBase rb = agent.getRuleBase();
//		StatefulSession session =rb.newStatefulSession();
		
		RuleAgentGen ruleAgentGen = RuleAgentGen.getRuleAgentGenInstance();
		RuleAgent agent = ruleAgentGen.getRuleAgent(propertiesFilePath);
		RuleBase ruleBase=agent.getRuleBase();
		StatefulSession session = ruleBase.newStatefulSession();
		
		session.setGlobal("message", returnObj);
		for (Object obj : insertObjSet) {
			session.insert(obj);
		}
		//session.startProcess(ruleFlowName);
		session.fireAllRules();
		session.dispose();
		return returnObj;
	}

	private KnowledgeBase readKnowledgeBase(Set<String> ruleResourceSet) {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory
				.newKnowledgeBuilder();
		Iterator<String> iterator = ruleResourceSet.iterator();
		String ruleResourceName;
		ResourceType resourceType;
		while (iterator.hasNext()) {
			ruleResourceName = iterator.next();
			resourceType = this.getResourceType(ruleResourceName);
			if (resourceType.equals(ResourceType.DTABLE)) {
				DecisionTableConfiguration config = KnowledgeBuilderFactory
						.newDecisionTableConfiguration();
				config.setInputType(DecisionTableInputType.XLS);
				kbuilder.add(ResourceFactory
						.newClassPathResource(ruleResourceName), resourceType,
						config);
			} else {
				kbuilder.add(ResourceFactory
						.newClassPathResource(ruleResourceName), resourceType);
			}
		}
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kbase;
	}

	private ResourceType getResourceType(String ruleName) {
		String temp[] = ruleName.split("\\.");
		String fileExtensions = temp[temp.length - 1].toLowerCase();
		ResourceType resourceType = null;
		if ("drl".equals(fileExtensions)) {
			resourceType = ResourceType.DRL;
		} else if ("xls".equals(fileExtensions)) {
			resourceType = ResourceType.DTABLE;
		} else if ("rf".equals(fileExtensions)) {
			resourceType = ResourceType.DRF;
		}
		return resourceType;
	}
	
	public String getVerifyLevel(String level){
		String ruleLevel = "1";
		//车辆定损
		if ("LossApprCarOne".equals(level)){
			ruleLevel = "1";
		} else if("LossApprCarTwo".equals(level)){
			ruleLevel = "2";
		} else if("LossApprCarThree".equals(level)){
			ruleLevel = "3";
		}
		//财产定损
		if ("LossApprPropOne".equals(level)){
			ruleLevel = "1";
		} else if("LossApprPropTwo".equals(level)){
			ruleLevel = "2";
		} else if("LossApprPropThree".equals(level)){
			ruleLevel = "3";
		}
		//人伤定损
		if ("MedicalAuditOne".equals(level)){
			ruleLevel = "1";
		} else if("MedicalAuditTwo".equals(level)){
			ruleLevel = "2";
		} else if("MedicalAuditThree".equals(level)){
			ruleLevel = "3";
		}
		return ruleLevel;
	}

	//改造后规则引擎调用的方法
	@Override
	public Object executeRules(String ruleFlowName, Object inputXOM,
			Object resultXOM,Properties config) {
		AgentRule agentRule = AgentRule.getRuleAgentGenInstance();
		RuleAgent agent = agentRule.getRuleAgent(config);
		RuleBase ruleBase=agent.getRuleBase();
		StatefulSession session = ruleBase.newStatefulSession();
		session.setGlobal("resultXOM", resultXOM);
		session.insert(inputXOM);
		session.startProcess(ruleFlowName);
		session.fireAllRules();
		session.dispose();
		return resultXOM;
	}
}
