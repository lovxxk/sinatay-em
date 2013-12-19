package cn.com.sinosoft.ebusiness.productManagement.productManage.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeDutyConfig;
import cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade.GeDutyConfigService;

public class GeDutyConfigServiceSpringImpl extends GenericDaoHibernate<GeDutyConfig, String> implements GeDutyConfigService {
	
	public void addDutyKindConfig(GeDutyConfig dutyKindConfig) {
		super.save(dutyKindConfig);
	}
	
	public GeDutyConfig findDutyConfigByDutyCodeAndRiskCode(String dutyCode,String riskCode) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("dutyCode", dutyCode);
		queryRule.addEqual("geRiskConfig.riskCode", riskCode);
		List<GeDutyConfig> geDutyConfigList = super.find(queryRule);
		if (geDutyConfigList.size() > 0) {
			return geDutyConfigList.get(0);
		} else {
			return null;
		}
		
	}
	
	public void deleteDutyConfigByRiskCode(String riskCode) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("geRiskConfig.riskCode", riskCode);
		List<GeDutyConfig> geDutyConfigList = super.find(queryRule);
		super.deleteAll(geDutyConfigList);
	}
	
	public void delete(GeDutyConfig geDutyConfig) {
		super.delete(geDutyConfig);
	}
	
	public void updateDutyConfig(GeDutyConfig geDutyConfig) {
		GeDutyConfig update = super.get(geDutyConfig.getSerialNo());
		BeanUtils.copyProperties(geDutyConfig, update,new String[]{"createTime"});
		super.update(update);
	}
	
	public List<GeDutyConfig> findDutyKindConfig(QueryRule queryRule) {
		try {
			return super.find(queryRule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Page findDutyKindConfig(QueryRule queryRule, int pageNo, int pageSize) {
		try {
			return super.find(queryRule, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
