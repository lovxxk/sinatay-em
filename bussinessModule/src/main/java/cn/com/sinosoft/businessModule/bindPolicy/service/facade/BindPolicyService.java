package cn.com.sinosoft.businessModule.bindPolicy.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;

public interface BindPolicyService {

	/**
	 * 添加客户绑定保单
	 * @param BindPolicy
	 */
	public abstract String addBindPolicy(BindPolicy bindPolicy);
	
	public abstract List<BindPolicy> findPolicyByQueryRule(
			QueryRule queryRule);
	
	public abstract void deleteBindPolicy(BindPolicy bindPolicy);
	
	public abstract boolean hasPolicy(String customerID);
}
