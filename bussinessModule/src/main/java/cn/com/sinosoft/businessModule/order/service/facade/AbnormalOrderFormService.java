package cn.com.sinosoft.businessModule.order.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;
import java.util.Map;

import cn.com.sinosoft.businessModule.order.domain.AbnormalOrderForm;

public interface AbnormalOrderFormService {

	/**
	 * 添加异常订单
	 * @param order
	 */
	public abstract void addAbnormalOrderForm(AbnormalOrderForm abnormalOrderForm);

	/***
	 * 根据属性查询异常订单
	 * @param propertyMap
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public abstract AbnormalOrderForm findAbnormalOrderFormByQueryMap(Map propertyMap);

	/**
	 * 根据 主键查询异常订单信息
	 * @param serialNo
	 * @return
	 */
	public abstract AbnormalOrderForm findAbnormalOrderFormBySerialNo(String serialNo);

	/***
	 * 根据异常订单号查询异常订单信息
	 * @param AbnormalOrderFormSerialNumber
	 * @return
	 */
	public abstract AbnormalOrderForm findAbnormalOrderFormByOrderSerialNumber(
			String orderSerialNumber);

	/***
	 * 分页查询异常订单信息
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract Page findAbnormalOrderFormByQueryRule(QueryRule queryRule,
			int pageNo, int pageSize);

	/***
	 * 根据条件查询异常订单
	 * @param queryRule
	 * @return
	 */
	public abstract List<AbnormalOrderForm> findAllAbnormalOrderFormByQueryRule(
			QueryRule queryRule);

	/**
	 * 更新异常订单
	 * @param AbnormalOrderForm
	 */
	public abstract void updateAbnormalOrderForm(AbnormalOrderForm abnormalOrderForm);

	/**
	 * 删除异常订单
	 * @param AbnormalOrderFormList
	 * @return
	 */
	public abstract boolean deleteAbnormalOrderForm(List<AbnormalOrderForm> abnormalOrderFormList);
	
	/**
	 * 根据订单号删除异常订单
	 * @param AbnormalOrderFormList
	 * @return
	 */
	public abstract boolean deleteAbnormalOrderForm(String orderSerialNumber);

}