package cn.com.sinosoft.businessModule.partyRoleInPolicy.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuredLiability;

public interface InsuredLiabilityService {

	/***
	 *  添加被保险人保障/险种
	 * @param insuredLiability
	 */
	public abstract void addInsuredLiability(InsuredLiability insuredLiability);

	/**
	 * 添加所有被保险人保障/险种
	 * @param insuredLiabilityList
	 */
	public abstract void addAllInsuredLiability(
			List<InsuredLiability> insuredLiabilityList);

	/***
	 * 根据属性查询被保险人保障/险种
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public abstract InsuredLiability findInsuredLiabilityByQueryMap(
			Map propertyMap);

	/**
	 * 根据 主键查询被保险人保障/险种信息
	 * @param serialNo
	 * @return
	 */
	public abstract InsuredLiability findInsuredLiabilityBySerialNo(
			String serialNo);

	/***
	 * 分页查询被保险人保障/险种信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findInsuredLiabilityByQueryRule(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * 根据条件查询被保险人保障/险种
	 * @param queryRule
	 * @return
	 */
	public abstract List<InsuredLiability> findAllInsuredLiabilityByQueryRule(
			QueryRule queryRule);

	/**
	 * 更新被保险人保障/险种
	 * @param insuredLiability
	 */
	public abstract void updateInsuredLiability(
			InsuredLiability insuredLiability);

	/**
	 * 删除被保险人保障/险种
	 * @param insuredLiabilityList
	 * @return
	 */
	public abstract boolean deleteAllInsuredLiability(
			List<InsuredLiability> insuredLiabilityList);

}