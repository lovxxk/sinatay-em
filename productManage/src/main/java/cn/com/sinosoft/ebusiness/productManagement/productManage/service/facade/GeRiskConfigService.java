package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeRiskConfig;

public interface GeRiskConfigService {

	/**
	 * �������
	 * @param geRiskConfig
	 */
	public abstract void addGeRiskConfig(GeRiskConfig geRiskConfig);

	/**
	 * ����������ѯ����
	 * @param queryRule
	 * @return
	 */
	public abstract List<GeRiskConfig> findGeRiskConfigByQueryRule(
			QueryRule queryRule);

	/**
	 * ��ҳ��ʽ��ѯ����
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findGeRiskConfig(QueryRule queryRule, int pageNo,
			int pageSize);
	
	/**
	 * �������ִ����ѯ����
	 * @param riskCode
	 * @return
	 */
	public abstract GeRiskConfig findGeRiskConfigByRiskCode(String riskCode);
	
	/**
	 * ��������
	 * @param geRiskConfig
	 */
	public abstract void updateGeRiskConfig(GeRiskConfig geRiskConfig);

}
