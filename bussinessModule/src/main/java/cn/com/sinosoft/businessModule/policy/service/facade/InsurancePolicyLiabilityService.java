package cn.com.sinosoft.businessModule.policy.service.facade;

import java.util.List;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyLiability;

public interface InsurancePolicyLiabilityService {
	
	/**
	 * 批量删除保单下的保障/险种责任
	 * @param InsurancePolicyLiabilitys
	 */
	public abstract void deleteInsurancePolicyLiability(List<InsurancePolicyLiability> insurancePolicyLiabilitys);
	
}
