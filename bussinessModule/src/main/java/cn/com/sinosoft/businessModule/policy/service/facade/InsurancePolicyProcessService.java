package cn.com.sinosoft.businessModule.policy.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyProcess;

public interface InsurancePolicyProcessService {

	/**
	 * 添加投保单
	 * @param insurancePolicyProcess
	 */
	public abstract String addInsurancePolicyProcess(
			InsurancePolicyProcess insurancePolicyProcess);

	/***
	 * 根据主键查询保单信息
	 * @param serialNo 逻辑主键序号
	 * @return
	 */
	public abstract InsurancePolicyProcess findInsurancePolicyProcessBySerialNo(
			String serialNo);

	/***
	 * 分页查询投保单信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findInsurancePolicyProcessByQueryRule(
			QueryRule queryRule, int pageNo, int pageSize);

	/**
	 * 更新投保单
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