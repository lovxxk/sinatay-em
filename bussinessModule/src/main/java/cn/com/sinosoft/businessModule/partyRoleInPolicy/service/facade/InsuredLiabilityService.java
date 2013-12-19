package cn.com.sinosoft.businessModule.partyRoleInPolicy.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuredLiability;

public interface InsuredLiabilityService {

	/***
	 *  ��ӱ������˱���/����
	 * @param insuredLiability
	 */
	public abstract void addInsuredLiability(InsuredLiability insuredLiability);

	/**
	 * ������б������˱���/����
	 * @param insuredLiabilityList
	 */
	public abstract void addAllInsuredLiability(
			List<InsuredLiability> insuredLiabilityList);

	/***
	 * �������Բ�ѯ�������˱���/����
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public abstract InsuredLiability findInsuredLiabilityByQueryMap(
			Map propertyMap);

	/**
	 * ���� ������ѯ�������˱���/������Ϣ
	 * @param serialNo
	 * @return
	 */
	public abstract InsuredLiability findInsuredLiabilityBySerialNo(
			String serialNo);

	/***
	 * ��ҳ��ѯ�������˱���/������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findInsuredLiabilityByQueryRule(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * ����������ѯ�������˱���/����
	 * @param queryRule
	 * @return
	 */
	public abstract List<InsuredLiability> findAllInsuredLiabilityByQueryRule(
			QueryRule queryRule);

	/**
	 * ���±������˱���/����
	 * @param insuredLiability
	 */
	public abstract void updateInsuredLiability(
			InsuredLiability insuredLiability);

	/**
	 * ɾ���������˱���/����
	 * @param insuredLiabilityList
	 * @return
	 */
	public abstract boolean deleteAllInsuredLiability(
			List<InsuredLiability> insuredLiabilityList);

}