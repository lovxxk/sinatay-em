package cn.com.sinosoft.businessModule.EPolicy.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.businessModule.EPolicy.domain.EPolicy;

public interface EPolicyService {

	/**
	 * 添加电子保单
	 * @param ePolicy
	 */
	public abstract void addEPolicy(EPolicy ePolicy);

	/**
	 * 更改电子保单
	 * @param ePolicy
	 */
	public abstract void updateEPolicy(EPolicy ePolicy);

	/**
	 * 根据QueryRule查询查询电子保单
	 * @param queryRule
	 */
	public abstract List<EPolicy> findEPolicyByQueryRule(QueryRule queryRule);

	/**
	 * 根据主键SerialNo 查询电子保单信息
	 * @param serialNo
	 * @return
	 */
	public abstract EPolicy findEPolicyBySerialNo(String serialNo);

	/**
	 * 根据保单号查询电子保单信息
	 * @param policyNo
	 * @return
	 */
	public abstract EPolicy findEPolicyByPolicyNo(String policyNo);

	/**
	 * 根据订单号查询电子保单信息
	 * @param orderNo
	 * @return
	 */
	public abstract EPolicy findEPolicyByOrderNo(String orderNo);

	/**
	 * 删除电子保单
	 * @param queryRule
	 */
	public abstract void deleteQueryRuleBySerialNo(String serialNo);

	public abstract EPolicy findLatelyEPolicyByOrderNo(String orderNo);

}