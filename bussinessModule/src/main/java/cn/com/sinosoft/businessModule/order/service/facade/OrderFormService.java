package cn.com.sinosoft.businessModule.order.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.enums.dictionary.OrderStatus;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;

public interface OrderFormService extends GenericDao<OrderForm, String> {
	
	/**
	 * ��������
	 * @param OrderForm
	 */
	public abstract String addOrderForm(OrderForm orderForm);
	
	/**
	 * ���¶���
	 * @param OrderForm
	 */
	public abstract void updateOrderForm(OrderForm orderForm);

	/***
	 * �������Բ�ѯ����
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public abstract OrderForm findOrderFormByQueryMap(Map propertyMap);

	/***
	 * ��ҳ��ѯ������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findOrderFormByQueryRule(QueryRule queryRule, int pageNo, int pageSize);
	
	/***
	 * ����������ѯ����
	 * @param queryRule
	 * @return
	 */
	public abstract List<OrderForm> findAllOrderFormByPropertyMap(QueryRule queryRule);
	
	/***
	 * ����������ѯ�����������ر���������Ϣ
	 * @param queryRule
	 * @return
	 */
	public abstract List<OrderForm> findAllOrderFormJoinPolicyBasicInfoByPersonalUser(String personalUserSerialNo);
	
	/***
	 * ����������ѯ���϶���
	 * @param queryRule
	 * @return
	 */
	public abstract List<OrderForm> findOldOrderFormPolicyBasicInfoByPersonalUser(String personalUserSerialNo);

	/***
	 * ����������ѯǰ��������
	 * @param propertyMap
	 * @param topNumber
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public abstract List<OrderForm> findTopOrderFormByHQL(Map propertyMap, int topNumber);
	
	/**
	 * ��ѯ��ǰ�û��Ķ�����
	 * @param personalUserSerialNo
	 * @return
	 */
	public abstract long findCurrentUserOrderFormNumber(String personalUserSerialNo);
	
	/**
	 * ɾ������
	 * @param OrderForms
	 * @return
	 */
	public abstract boolean deleteAllOrderForm(List<OrderForm> orderForms);
	
	/**
	 * ɾ������
	 * @param OrderForms
	 * @return
	 */
	public abstract boolean deleteByOrderFormSerialNumber(String orderSerialNumber);
	
	/***
	 * ���ݶ����Ų�ѯ������Ϣ
	 * @param OrderFormSerialNumber
	 * @return
	 */
	public abstract OrderForm findOrderFormByOrderFormSerialNumber(String orderSerialNumber);
	
	/**
	 * ���� ������ѯ������Ϣ
	 * @param serialNo
	 * @return
	 */
	public abstract OrderForm findOrderFormBySerialNo(String serialNo);
	
	public List<OrderForm> getOrderFormsByUserId(String personalUserSerialNo, String status);
	public Page getOrderFormsByUserId(String personalUserSerialNo, Integer status, String orderSerialNo, int pageNo, int pageSize);

	/**
	 * ��ѯ��Ӧ״̬��Ӧ�Ķ�����Ϣ
	 * @param unpaid
	 * @return
	 */
	public abstract Integer getOrderFomrsByState(OrderStatus status, String userId);

	public abstract Integer getQuoteMainByState(String userId);

	public abstract List<OrderForm> getTwoOrderForms(String userId);

	/**
	 * ��ѯ��Ʒ��Ӧ�����ۼ�¼
	 * @return
	 */
	public abstract List<OrderForm> getSaleRecords();

	/**
	 * ��ѯ��������
	 * @param serialNo
	 * @return
	 */
	public abstract OrderForm getOrderFormBySerialNo(String serialNo);

	public abstract OrderForm getOrderFormForKey(String serialNo);

	public abstract boolean updateInsurancePolicyEffectDate(OrderForm orderForm, String effect_start);
	
	/**
	 * �����������Ѵ���
	 * @param orderFormAll
	 */
	public abstract void updateAllRemindCount(final List orderFormAll);
}
