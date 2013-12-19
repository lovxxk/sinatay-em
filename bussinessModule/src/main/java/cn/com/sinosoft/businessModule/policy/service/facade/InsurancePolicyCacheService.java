package cn.com.sinosoft.businessModule.policy.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyCache;

public interface InsurancePolicyCacheService {

	/**
	 * ���Ͷ����
	 * @param insurancePolicyCache
	 */
	public abstract String addInsurancePolicyCache(
			InsurancePolicyCache insurancePolicyCache);

	/***
	 * ����������ѯ������Ϣ
	 * @param serialNo �߼��������
	 * @return
	 */
	public abstract InsurancePolicyCache findInsurancePolicyCacheBySerialNo(
			String serialNo);

	/***
	 * ��ҳ��ѯͶ������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findInsurancePolicyCacheByQueryRule(
			QueryRule queryRule, int pageNo, int pageSize);

	/**
	 * ����Ͷ����
	 * @param insurancePolicyCache
	 */
	public abstract void updateInsurancePolicyCache(
			InsurancePolicyCache insurancePolicyCache);

	public abstract List<InsurancePolicyCache> findInsurancePolicyCacheByQueryRule(
			QueryRule queryRule);

}