package cn.com.sinosoft.businessModule.partyRoleInPolicy.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Insured;

public interface InsuredService {

	public abstract void addInsured(Insured insured);
	
	public abstract void addBatchInsured(List<Insured> insured);
	
	public abstract void deleteBatchInsured(List<Insured> insured);
	
	public abstract void updateInsured(Insured insured);
	
	public abstract List<Insured> findInsured(QueryRule queryRule);
	
	public abstract void deleteInsuredByKey(String id);
}
