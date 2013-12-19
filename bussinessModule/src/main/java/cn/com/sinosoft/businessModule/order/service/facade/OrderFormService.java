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
	 * 新增订单
	 * @param OrderForm
	 */
	public abstract String addOrderForm(OrderForm orderForm);
	
	/**
	 * 更新订单
	 * @param OrderForm
	 */
	public abstract void updateOrderForm(OrderForm orderForm);

	/***
	 * 根据属性查询订单
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public abstract OrderForm findOrderFormByQueryMap(Map propertyMap);

	/***
	 * 分页查询订单信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findOrderFormByQueryRule(QueryRule queryRule, int pageNo, int pageSize);
	
	/***
	 * 根据条件查询订单
	 * @param queryRule
	 * @return
	 */
	public abstract List<OrderForm> findAllOrderFormByPropertyMap(QueryRule queryRule);
	
	/***
	 * 根据条件查询订单立即加载保单基本信息
	 * @param queryRule
	 * @return
	 */
	public abstract List<OrderForm> findAllOrderFormJoinPolicyBasicInfoByPersonalUser(String personalUserSerialNo);
	
	/***
	 * 根据条件查询最老订单
	 * @param queryRule
	 * @return
	 */
	public abstract List<OrderForm> findOldOrderFormPolicyBasicInfoByPersonalUser(String personalUserSerialNo);

	/***
	 * 根据条件查询前几条数据
	 * @param propertyMap
	 * @param topNumber
	 * @return
	 */
	@SuppressWarnings({ "rawtypes"})
	public abstract List<OrderForm> findTopOrderFormByHQL(Map propertyMap, int topNumber);
	
	/**
	 * 查询当前用户的订单数
	 * @param personalUserSerialNo
	 * @return
	 */
	public abstract long findCurrentUserOrderFormNumber(String personalUserSerialNo);
	
	/**
	 * 删除订单
	 * @param OrderForms
	 * @return
	 */
	public abstract boolean deleteAllOrderForm(List<OrderForm> orderForms);
	
	/**
	 * 删除订单
	 * @param OrderForms
	 * @return
	 */
	public abstract boolean deleteByOrderFormSerialNumber(String orderSerialNumber);
	
	/***
	 * 根据订单号查询订单信息
	 * @param OrderFormSerialNumber
	 * @return
	 */
	public abstract OrderForm findOrderFormByOrderFormSerialNumber(String orderSerialNumber);
	
	/**
	 * 根据 主键查询订单信息
	 * @param serialNo
	 * @return
	 */
	public abstract OrderForm findOrderFormBySerialNo(String serialNo);
	
	public List<OrderForm> getOrderFormsByUserId(String personalUserSerialNo, String status);
	public Page getOrderFormsByUserId(String personalUserSerialNo, Integer status, String orderSerialNo, int pageNo, int pageSize);

	/**
	 * 查询相应状态对应的订单信息
	 * @param unpaid
	 * @return
	 */
	public abstract Integer getOrderFomrsByState(OrderStatus status, String userId);

	public abstract Integer getQuoteMainByState(String userId);

	public abstract List<OrderForm> getTwoOrderForms(String userId);

	/**
	 * 查询产品对应的销售记录
	 * @return
	 */
	public abstract List<OrderForm> getSaleRecords();

	/**
	 * 查询保单对象
	 * @param serialNo
	 * @return
	 */
	public abstract OrderForm getOrderFormBySerialNo(String serialNo);

	public abstract OrderForm getOrderFormForKey(String serialNo);

	public abstract boolean updateInsurancePolicyEffectDate(OrderForm orderForm, String effect_start);
	
	/**
	 * 批量跟新提醒次数
	 * @param orderFormAll
	 */
	public abstract void updateAllRemindCount(final List orderFormAll);
}
