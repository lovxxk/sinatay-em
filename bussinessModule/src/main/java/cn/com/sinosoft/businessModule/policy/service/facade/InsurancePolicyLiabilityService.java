package cn.com.sinosoft.businessModule.policy.service.facade;

import java.util.List;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyLiability;

public interface InsurancePolicyLiabilityService {
	
	/**
	 * ����ɾ�������µı���/��������
	 * @param InsurancePolicyLiabilitys
	 */
	public abstract void deleteInsurancePolicyLiability(List<InsurancePolicyLiability> insurancePolicyLiabilitys);
	
}
