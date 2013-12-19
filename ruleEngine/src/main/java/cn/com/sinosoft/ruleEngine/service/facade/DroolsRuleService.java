package cn.com.sinosoft.ruleEngine.service.facade;

import java.util.Properties;
import java.util.Set;

/**
 * 规则引擎接口.
 */
public interface DroolsRuleService {
	/**
	 * 规则执行
	 * 
	 * @param ruleFlowName
	 *            主规则流名称
	 * @param fact
	 *            返回对象
	 * @param ruleResourceSet
	 *            规则执行用到的所有规则文件
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object fact ,Set<String> ruleResourceSet);
	/**
	 * 规则执行
	 * 
	 * @param ruleFlowName
	 *            主规则流名称
	 * @param objectMap
	 *            返回与插入对象集合
	 * @param ruleResourceSet
	 *            规则执行用到的所有规则文件
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object returnObj,Set<Object> insertObjSet,Set<String> ruleResourceSet);
	
	/**
	 * 规则执行(BRMS)
	 * 
	 * @param ruleFlowName
	 *            主规则流名称
	 * @param fact
	 *            返回对象
	 * @param propertiesFilePath
	 *            drools配置文件
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object fact ,String propertiesFilePath);
	
	/**
	 * 规则执行(BRMS)
	 * 
	 * @param ruleFlowName
	 *            主规则流名称
	 * @param returnObj
	 *            返回的对象
	 * @param insertObjSet
	 *            插入的对象集合
	 * @param propertiesFilePath
	 *            drools配置文件
	 * @return
	 */
	public Object executeRules(String ruleFlowName, Object insertObjSet,Object returnObj, String propertiesFilePath);
	/**
	 * 规则执行(BRMS)
	 * 
	 * @param ruleFlowName
	 *            主规则流名称
	 * @param returnObj
	 *            返回的对象
	 * @param insertObjSet
	 *            插入的对象集合
	 * @param propertiesFilePath
	 *            drools配置文件
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object returnObj,Set<Object> insertObjSet,String propertiesFilePath);
	
	/**
	 *  
	 * 核损级别与规则中对应转换
	 * @param prpLdeflossMain
	 * @param tabelName
	 * @param nextTableName
	 * @param strings
	 */
	
	public String getVerifyLevel(String level);
	/**
	 * 规则执行(BRMS)
	 * 
	 * @param ruleFlowName
	 *            主规则流名称
	 * @param returnObj
	 *            返回的对象
	 * @param insertObjSet
	 *            插入的对象集合
	 * @param config
	 *            drools配置文件
	 * @return
	 */
	
	public Object executeRules(String ruleFlowName,Object returnObj,Object insertObjSet,Properties config);
}
