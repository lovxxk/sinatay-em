package cn.com.sinosoft.portalModule.portalInterface.service.fascade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;

import java.util.List;

import cn.com.sinosoft.portalModule.portalInterface.domain.PortalInterfacePayment;

@SuppressWarnings("rawtypes")
public interface PortalInterfacePaymentService {

	/***
	 * 添加接口交互支付信息
	 * 
	 * @param PortalInterfacePayment
	 *  接口交互支付信息和接口关系对象
	 * @return
	 */
	public abstract void addPortalInterfacePayment(PortalInterfacePayment portalInterfacePayment);

	/**
	 * 根据查询对象获取Page对象的接口交互支付信息和接口关系列表
	 * 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return 包含接口交互系统信息和接口关系列表的Page对象
	 */
	public abstract Page findPortalInterfacePayment(QueryRule queryRule, int pageNo, int pageSize);

	public abstract PortalInterfacePayment findPortalInterfacePaymentBySerialNo(String serialNo);
	
	public abstract List<PortalInterfacePayment> findPortalInterfacePaymentByQueryRule(QueryRule queryRule);

	public abstract void deletePortalInterfacePayment(PortalInterfacePayment portalInterfacePayment);

	/***
	 * 更新接口交互支付信息和接口关系信息
	 * 
	 * @param 更新接口交互支付信息和接口关系信息
	 */
	public abstract void updatePortalInterfacePayment(PortalInterfacePayment portalInterfacePayment);

}
