package cn.com.sinosoft.businessModule.universalRate.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.businessModule.hospitalManage.domain.HospitalManage;
import cn.com.sinosoft.businessModule.universalRate.domain.UniversalRate;

public interface UniversalRateService {
	public abstract String addUniversal(UniversalRate universalRate);
	public abstract List<UniversalRate> findUniversalByQueryRule(QueryRule queryRule);
	public abstract void updateUniversal(UniversalRate universalRate);
	public abstract void deleteUniversal(UniversalRate universalRate);
	public abstract Page getUniversalPage(UniversalRate universalRate, int pageNo, int pageSize);
	public abstract List<String> findUniqueRiskName();
	public abstract List<UniversalRate> findNewUniversal();
	public abstract UniversalRate findUniqNewUniversal();
}