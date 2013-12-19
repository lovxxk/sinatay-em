package cn.com.sinosoft.businessModule.policy.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.enums.dictionary.ElectronicPolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.SyncStatus;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;

public interface InsurancePolicyService {

	/**
	 * 添加投保单
	 * @param insurancePolicy
	 */
	public abstract String addInsurancePolicy(InsurancePolicy insurancePolicy);
	
	/**
	 * 创建投保单
	 * @param insurancePolicy
	 */
	public InsurancePolicy createInsurancePolicy(InsurancePolicy insurancePolicy);
	
	/***
	 * 根据属性查询投保单
	 * @param propertyMap
	 * @return
	 */
	public abstract InsurancePolicy findInsurancePolicyByQueryMap(
			Map propertyMap);

	/***
	 * 根据主键查询保单信息
	 * @param serialNo 逻辑主键序号
	 * @return
	 */
	public abstract InsurancePolicy findInsurancePolicyBySerialNo(
			String serialNo);

	/***
	 * 根据保单号查询保单信息
	 * @param serialNo 逻辑主键序号
	 * @return
	 */
	public abstract InsurancePolicy findInsurancePolicyByPolicySerialNumber(
			String policySerialNumber);

	/***
	 * 分页查询投保单信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findInsurancePolicyByQueryRule(QueryRule queryRule,
			int pageNo, int pageSize);

	/**
	 * 更新投保单
	 * @param insurancePolicy
	 */
	public abstract void updateInsurancePolicy(InsurancePolicy insurancePolicy);

	/**
	 * 批量更新同步开始保单同步状态
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public abstract void updateAllGenerationElectronic(
			final List insurancePolicyAll,
			final ElectronicPolicyStatus electronicPolicyStatus);

	/**
	 * 批量跟新提醒次数
	 * @param insurancePolicyAll
	 */
	public abstract void updateAllRemindCount(final List insurancePolicyAll) throws Exception;

	/**
	 * 批量更新同步开始保单同步状态
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public abstract void updateAllSyncStartInsurancePolicy(
			final List insurancePolicyAll, final SyncStatus syncStatus);
	
	/**
	 * 根据保单号更新同步结束状态
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public abstract void updateSyncEndByPolicySerialNumber(InsurancePolicy insurancePolicy);
	
	/**
	 * 更新投保单
	 * @param insurancePolicy
	 */

	public abstract void updateInsurancePolicyByPolicySerialNo(
			InsurancePolicy insurancePolicy, List<String> copyProperties);

	public abstract List<InsurancePolicy> findInsurancePolicyByQueryRule(
			QueryRule queryRule);

	/**
	 * 当天累计保费
	 * @param insurancePolicy
	 * @return
	 */
	public abstract BigDecimal findCurrentDayTotalPreminum(
			InsurancePolicy insurancePolicy);

	/**
	 * 生成保单号
	 */
	public abstract String generatePolicySerialNumber();

	/**
	 * 
	 * @param sequenceName
	 * 
	 */
	public abstract Long getSequence(String sequenceName);

	/**
	 * 
	 * @param sequenceName
	 * @param tableName
	 * @return
	 * 
	 */
	public abstract Long getSequence(final String sequenceName,
			final String tableName);

	public abstract void updateInsurancePolicyByMerchantOrderNumber(
			InsurancePolicy insurancePolicy);

	public abstract void updateGenerationElectronic(final InsurancePolicy insurancePolicy);

	/**
	 * 删除投保单
	 * @param insurancePolicy
	 */
	public abstract void deleteInsurancePolicy(InsurancePolicy insurancePolicy);

	/** 根据多投保单序号查询投保单
	 * @param proposalSIDs 投保单序号由','连接
	 * @return
	 */
	public List<InsurancePolicy> findInsurancePoliciesByIds(String proposalSIDs);
	
	/**
	 * 更新保单号
	 * @param insurancePolicy
	 */
	public void updatePolicySerialNumber(final InsurancePolicy insurancePolicy);

	public abstract InsurancePolicy getInsurancePolicyByQuoteNo(String quoteNo); 
}