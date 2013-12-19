package cn.com.sinosoft.businessModule.order.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.order.domain.AbnormalOrderForm;

public interface AbnormalOrderFormService {

	/**
	 * ����쳣����
	 * @param order
	 */
	public abstract void addAbnormalOrderForm(AbnormalOrderForm abnormalOrderForm);

	/***
	 * �������Բ�ѯ�쳣����
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public abstract AbnormalOrderForm findAbnormalOrderFormByQueryMap(Map propertyMap);

	/**
	 * ���� ������ѯ�쳣������Ϣ
	 * @param serialNo
	 * @return
	 */
	public abstract AbnormalOrderForm findAbnormalOrderFormBySerialNo(String serialNo);

	/***
	 * �����쳣�����Ų�ѯ�쳣������Ϣ
	 * @param AbnormalOrderFormSerialNumber
	 * @return
	 */
	public abstract AbnormalOrderForm findAbnormalOrderFormByOrderSerialNumber(
			String orderSerialNumber);

	/***
	 * ��ҳ��ѯ�쳣������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findAbnormalOrderFormByQueryRule(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * ����������ѯ�쳣����
	 * @param queryRule
	 * @return
	 */
	public abstract List<AbnormalOrderForm> findAllAbnormalOrderFormByQueryRule(
			QueryRule queryRule);

	/**
	 * �����쳣����
	 * @param AbnormalOrderForm
	 */
	public abstract void updateAbnormalOrderForm(AbnormalOrderForm abnormalOrderForm);

	/**
	 * ɾ���쳣����
	 * @param AbnormalOrderFormList
	 * @return
	 */
	public abstract boolean deleteAbnormalOrderForm(List<AbnormalOrderForm> abnormalOrderFormList);
	
	/**
	 * ���ݶ�����ɾ���쳣����
	 * @param AbnormalOrderFormList
	 * @return
	 */
	public abstract boolean deleteAbnormalOrderForm(String orderSerialNumber);

}