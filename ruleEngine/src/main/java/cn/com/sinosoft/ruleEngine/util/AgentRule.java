package cn.com.sinosoft.ruleEngine.util;

import java.util.HashMap;
import java.util.Properties;

import org.drools.agent.RuleAgent;

public class AgentRule {
	private static AgentRule INSTANCE;
	private static final HashMap<Properties,RuleAgent> ruleAgentMap = (HashMap<Properties,RuleAgent>)new HashMap<Properties,RuleAgent>();
	private AgentRule(){
	}
	public static  AgentRule getRuleAgentGenInstance(){
		if(INSTANCE==null){
			INSTANCE=new AgentRule();
		}
		return INSTANCE;
	}
	public static synchronized RuleAgent getRuleAgent(Properties config){
		RuleAgent ruleAgent = null;
		if(ruleAgentMap.containsKey(config)){
			ruleAgent = ruleAgentMap.get(config);
		}else{
			System.out.println("¼ÓÔØ  --   propertiesÎÄ¼þ");
			ruleAgent = RuleAgent.newRuleAgent(config);
			ruleAgentMap.put(config, ruleAgent);
		}
		return ruleAgent;
	}
}
