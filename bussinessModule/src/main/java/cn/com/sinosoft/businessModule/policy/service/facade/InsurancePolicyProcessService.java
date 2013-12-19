package cn.com.sinosoft.businessModule.policy.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyProcess;

public interface InsurancePolicyProcessService {

	/**
	 * ���Ͷ����
	 * @param insurancePolicyProcess
	 */
	public abstract String addInsurancePolicyProcess(
			InsurancePolicyProcess insurancePolicyProcess);

	/***
	 * ����������ѯ������Ϣ
	 * @param serialNo �߼��������
	 * @return
	 */
	public abstract InsurancePolicyProcess findInsurancePolicyProcessBySerialNo(
			String serialNo);

	/***
	 * ��ҳ��ѯͶ������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findInsurancePolicyProcessByQueryRule(
			QueryRule queryRule, int pageNo, int pageSize);

	/**
	 * ����Ͷ����
	 * @param InsurancePolicyProcess
	 */
	public abstract void updateInsurancePolicyProcess(
			InsurancePolicyProcess insurancePolicyProcess);

	public abstract List<InsurancePolicyProcess> findInsurancePolicyProcessByQueryRule(
			QueryRule queryRule);

	public abstract void updateInsurancePolicyProcessByMerchantOrderNumber(
			InsurancePolicyProcess insurancePolicyProcess);

	public abstract String addInsurancePolicyProcessByInsurancePolicy(InsurancePolicy insurancePolicy);
	
	public abstract InsurancePolicyProcess findInsurancePolicyProcessByQueryMap(Map propertyMap);
	
	public abstract InsurancePolicyProcess findInsurancePolicyProcessByMerchantOrderNumber(String merchantOrderNumber);
	
	public abstract void deleteInsurancePolicyProcess(InsurancePolicyProcess insurancePolicyProcess);
	
	public abstract void deleteInsurancePolicyProcessByMerchantOrderNumber(
			InsurancePolicyProcess insurancePolicyProcess);
}