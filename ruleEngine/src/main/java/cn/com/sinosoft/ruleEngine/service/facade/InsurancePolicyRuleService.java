package cn.com.sinosoft.ruleEngine.service.facade;

import org.drools.io.Resource;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;

public interface InsurancePolicyRuleService {

	public abstract void insuredRule(InsurancePolicy insurancePolicy);

	/**
	 * ���ݲ�Ʒ�����ȡ�ò�Ʒ�Ĺ���Դ
	 * @param productCode ��Ʒ����
	 * @return
	 */
	public abstract Resource getResource(String productCode);
	
	/**
	 * ���ݲ�Ʒ��������ò�Ʒ�Ĺ����ļ�
	 * @param productCode ��Ʒ����
	 */
	public abstract void clearRuleResource(String productCode);
	
	/**
	 * ����������й����ļ�
	 * @param productCode ��Ʒ����
	 */
	public abstract void clearAllRuleResource();
	
}