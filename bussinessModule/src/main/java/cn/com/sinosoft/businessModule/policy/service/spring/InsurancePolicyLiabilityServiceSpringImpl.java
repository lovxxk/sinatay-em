package cn.com.sinosoft.businessModule.policy.service.spring;

import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicyLiability;
import cn.com.sinosoft.businessModule.policy.service.facade.InsurancePolicyLiabilityService;

public class InsurancePolicyLiabilityServiceSpringImpl extends GenericDaoHibernate<InsurancePolicyLiability, String> implements InsurancePolicyLiabilityService {
	
	/**
	 * ����ɾ�������µı���/��������
	 * @param InsurancePolicyLiabilitys
	 */
	public void deleteInsurancePolicyLiability(List<InsurancePolicyLiability> insurancePolicyLiabilitys) {
		super.deleteAll(insurancePolicyLiabilitys);
	}

}
