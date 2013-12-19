package cn.com.sinosoft.businessModule.partyRoleInPolicy.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;

public interface InsuranceApplicantService {

	public abstract void addInsuranceApplicant(InsuranceApplicant insuranceApplicant);
	
	public abstract void updateInsuranceApplicant(InsuranceApplicant insuranceApplicant);
	
	public abstract List<InsuranceApplicant> findInsuranceApplicant(QueryRule queryRule);
	
	public abstract void deleteInsuranceApplicantByKey(String id);
	
	/***
	 * 分页查询投保人信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findInsuranceApplicantByQueryRule(QueryRule queryRule, int pageNo, int pageSize);
	
	@SuppressWarnings("rawtypes")
	public abstract InsuranceApplicant findInsuranceApplicantByQueryMap(Map propertyMap);
}
