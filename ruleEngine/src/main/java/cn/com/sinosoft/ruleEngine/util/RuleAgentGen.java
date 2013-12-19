package cn.com.sinosoft.ruleEngine.util;

import java.util.HashMap;

import org.drools.agent.RuleAgent;

public class RuleAgentGen {
	private static RuleAgentGen INSTANCE;
	private static final HashMap<String,RuleAgent> ruleAgentMap = (HashMap<String,RuleAgent>)new HashMap<String,RuleAgent>();
	private RuleAgentGen(){
	}
	public static  RuleAgentGen getRuleAgentGenInstance(){
		if(INSTANCE==null){
			INSTANCE=new RuleAgentGen();
		}
		return INSTANCE;
	}
	public static synchronized RuleAgent getRuleAgent(String propertiesFilePath){
		RuleAgent ruleAgent = null;
		if (ruleAgentMap.containsKey(propertiesFilePath)) {
			ruleAgent = ruleAgentMap.get(propertiesFilePath);
		} else {
			System.out.println("╪сть  --   "+propertiesFilePath);
			ruleAgent = RuleAgent.newRuleAgent(propertiesFilePath);
			ruleAgentMap.put(propertiesFilePath, ruleAgent);
		}
		return ruleAgent;
	}
}
