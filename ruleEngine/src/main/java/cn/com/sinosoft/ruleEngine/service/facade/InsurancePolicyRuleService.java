package cn.com.sinosoft.ruleEngine.service.facade;

import org.drools.io.Resource;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;

public interface InsurancePolicyRuleService {

	public abstract void insuredRule(InsurancePolicy insurancePolicy);

	/**
	 * 根据产品代码获取该产品的规资源
	 * @param productCode 产品代码
	 * @return
	 */
	public abstract Resource getResource(String productCode);
	
	/**
	 * 根据产品代码清除该产品的规则文件
	 * @param productCode 产品代码
	 */
	public abstract void clearRuleResource(String productCode);
	
	/**
	 * 根据清除所有规则文件
	 * @param productCode 产品代码
	 */
	public abstract void clearAllRuleResource();
	
}