package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePayment;

public interface GePaymentService {
	/**
	 * 根据城市编码得到id
	 * @param city
	 * @return
	 */
	List<String> getPayMentIdsByCity(String city);
	
	//List<String> getCityByIdAndCityCode(String city,String paymentId);
	
	void clearByPayMentId(String paymentId);
	
	void updatePayMent(GePayment gePayment);
	/**
	 * 新建支付方式
	 * @param gePayment
	 */
	public void createPayment(GePayment gePayment);
	/**
	 * 根据主键Id查询支付方式
	 * @param id
	 * @return
	 */
	public GePayment findPaymentById(String paymentId);
	/**
	 * 查询支付方式
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findPayment(QueryRule queryRule, int pageNo,
			int pageSize);
	
	/**
	 * 删除支付方式
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	
	public boolean delPaymentByPaymentId(String paymentId);
	/**
	 * 更新支付方式
	 * @param gePayment
	 */
	public void updatePayment(GePayment gePayment);
	
	/**
	 * 查询支付方式集合
	 * @param queryRule
	 * @return
	 */
	public List<GePayment> findPayments(QueryRule queryRule);
	
	/**
	 * 根据支付方式编号查询支付方式
	 * @param paymentCode
	 * @return
	 */
	public int findCountByPaymentCode(String paymentCode);
	
	/**
	 * 根据支付地区查询支持该地区的所有支付方式
	 * @param deptId
	 * @return
	 */
	public List<GePayment> findPaymentsByDeptId(String deptId);
	
	/**
	 * 根据多个支付地区查询同时支持这些地区的所有支付方式
	 * @param deptIds
	 * @return
	 */
	public List<GePayment> findPaymentsByDeptIds(List<String> deptIds);
}
