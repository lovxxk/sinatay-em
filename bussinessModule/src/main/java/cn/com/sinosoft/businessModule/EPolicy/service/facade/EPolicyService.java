package cn.com.sinosoft.businessModule.EPolicy.service.facade;

import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.businessModule.EPolicy.domain.EPolicy;

public interface EPolicyService {

	/**
	 * ��ӵ��ӱ���
	 * @param ePolicy
	 */
	public abstract void addEPolicy(EPolicy ePolicy);

	/**
	 * ���ĵ��ӱ���
	 * @param ePolicy
	 */
	public abstract void updateEPolicy(EPolicy ePolicy);

	/**
	 * ����QueryRule��ѯ��ѯ���ӱ���
	 * @param queryRule
	 */
	public abstract List<EPolicy> findEPolicyByQueryRule(QueryRule queryRule);

	/**
	 * ��������SerialNo ��ѯ���ӱ�����Ϣ
	 * @param serialNo
	 * @return
	 */
	public abstract EPolicy findEPolicyBySerialNo(String serialNo);

	/**
	 * ���ݱ����Ų�ѯ���ӱ�����Ϣ
	 * @param policyNo
	 * @return
	 */
	public abstract EPolicy findEPolicyByPolicyNo(String policyNo);

	/**
	 * ���ݶ����Ų�ѯ���ӱ�����Ϣ
	 * @param orderNo
	 * @return
	 */
	public abstract EPolicy findEPolicyByOrderNo(String orderNo);

	/**
	 * ɾ�����ӱ���
	 * @param queryRule
	 */
	public abstract void deleteQueryRuleBySerialNo(String serialNo);

	public abstract EPolicy findLatelyEPolicyByOrderNo(String orderNo);

}