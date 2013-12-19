package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeDutyConfig;

public interface GeDutyConfigService {

	public abstract void addDutyKindConfig(GeDutyConfig dutyKindConfig);

	public abstract void updateDutyConfig(GeDutyConfig geDutyConfig);

	public abstract List<GeDutyConfig> findDutyKindConfig(QueryRule queryRule);

	public abstract Page findDutyKindConfig(QueryRule queryRule, int pageNo,
			int pageSize);

	public abstract GeDutyConfig findDutyConfigByDutyCodeAndRiskCode(String dutyCode,
			String riskCode);

	public abstract void deleteDutyConfigByRiskCode(String riskCode);

	public abstract void delete(GeDutyConfig geDutyConfig);

}
