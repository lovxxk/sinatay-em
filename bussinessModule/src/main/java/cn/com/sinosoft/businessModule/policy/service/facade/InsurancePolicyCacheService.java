package cn.com.sinosoft.businessModule.policy.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyCache;

public interface InsurancePolicyCacheService {

	/**
	 * 添加投保单
	 * @param insurancePolicyCache
	 */
	public abstract String addInsurancePolicyCache(
			InsurancePolicyCache insurancePolicyCache);

	/***
	 * 根据主键查询保单信息
	 * @param serialNo 逻辑主键序号
	 * @return
	 */
	public abstract InsurancePolicyCache findInsurancePolicyCacheBySerialNo(
			String serialNo);

	/***
	 * 分页查询投保单信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findInsurancePolicyCacheByQueryRule(
			QueryRule queryRule, int pageNo, int pageSize);

	/**
	 * 更新投保单
	 * @param insurancePolicyCache
	 */
	public abstract void updateInsurancePolicyCache(
			InsurancePolicyCache insurancePolicyCache);

	public abstract List<InsurancePolicyCache> findInsurancePolicyCacheByQueryRule(
			QueryRule queryRule);

}