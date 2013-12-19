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
	 * ���Ͷ����
	 * @param insurancePolicy
	 */
	public abstract String addInsurancePolicy(InsurancePolicy insurancePolicy);
	
	/**
	 * ����Ͷ����
	 * @param insurancePolicy
	 */
	public InsurancePolicy createInsurancePolicy(InsurancePolicy insurancePolicy);
	
	/***
	 * �������Բ�ѯͶ����
	 * @param propertyMap
	 * @return
	 */
	public abstract InsurancePolicy findInsurancePolicyByQueryMap(
			Map propertyMap);

	/***
	 * ����������ѯ������Ϣ
	 * @param serialNo �߼��������
	 * @return
	 */
	public abstract InsurancePolicy findInsurancePolicyBySerialNo(
			String serialNo);

	/***
	 * ���ݱ����Ų�ѯ������Ϣ
	 * @param serialNo �߼��������
	 * @return
	 */
	public abstract InsurancePolicy findInsurancePolicyByPolicySerialNumber(
			String policySerialNumber);

	/***
	 * ��ҳ��ѯͶ������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findInsurancePolicyByQueryRule(QueryRule queryRule,
			int pageNo, int pageSize);

	/**
	 * ����Ͷ����
	 * @param insurancePolicy
	 */
	public abstract void updateInsurancePolicy(InsurancePolicy insurancePolicy);

	/**
	 * ��������ͬ����ʼ����ͬ��״̬
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public abstract void updateAllGenerationElectronic(
			final List insurancePolicyAll,
			final ElectronicPolicyStatus electronicPolicyStatus);

	/**
	 * �����������Ѵ���
	 * @param insurancePolicyAll
	 */
	public abstract void updateAllRemindCount(final List insurancePolicyAll) throws Exception;

	/**
	 * ��������ͬ����ʼ����ͬ��״̬
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public abstract void updateAllSyncStartInsurancePolicy(
			final List insurancePolicyAll, final SyncStatus syncStatus);
	
	/**
	 * ���ݱ����Ÿ���ͬ������״̬
	 * @param insurancePolicyAll
	 * @param syncStatus
	 */
	public abstract void updateSyncEndByPolicySerialNumber(InsurancePolicy insurancePolicy);
	
	/**
	 * ����Ͷ����
	 * @param insurancePolicy
	 */

	public abstract void updateInsurancePolicyByPolicySerialNo(
			InsurancePolicy insurancePolicy, List<String> copyProperties);

	public abstract List<InsurancePolicy> findInsurancePolicyByQueryRule(
			QueryRule queryRule);

	/**
	 * �����ۼƱ���
	 * @param insurancePolicy
	 * @return
	 */
	public abstract BigDecimal findCurrentDayTotalPreminum(
			InsurancePolicy insurancePolicy);

	/**
	 * ���ɱ�����
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
	 * ɾ��Ͷ����
	 * @param insurancePolicy
	 */
	public abstract void deleteInsurancePolicy(InsurancePolicy insurancePolicy);

	/** ���ݶ�Ͷ������Ų�ѯͶ����
	 * @param proposalSIDs Ͷ���������','����
	 * @return
	 */
	public List<InsurancePolicy> findInsurancePoliciesByIds(String proposalSIDs);
	
	/**
	 * ���±�����
	 * @param insurancePolicy
	 */
	public void updatePolicySerialNumber(final InsurancePolicy insurancePolicy);

	public abstract InsurancePolicy getInsurancePolicyByQuoteNo(String quoteNo); 
}