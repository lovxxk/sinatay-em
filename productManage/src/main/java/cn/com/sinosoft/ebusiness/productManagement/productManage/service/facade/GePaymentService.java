package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import java.util.List;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePayment;

public interface GePaymentService {
	/**
	 * ���ݳ��б���õ�id
	 * @param city
	 * @return
	 */
	List<String> getPayMentIdsByCity(String city);
	
	//List<String> getCityByIdAndCityCode(String city,String paymentId);
	
	void clearByPayMentId(String paymentId);
	
	void updatePayMent(GePayment gePayment);
	/**
	 * �½�֧����ʽ
	 * @param gePayment
	 */
	public void createPayment(GePayment gePayment);
	/**
	 * ��������Id��ѯ֧����ʽ
	 * @param id
	 * @return
	 */
	public GePayment findPaymentById(String paymentId);
	/**
	 * ��ѯ֧����ʽ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findPayment(QueryRule queryRule, int pageNo,
			int pageSize);
	
	/**
	 * ɾ��֧����ʽ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	
	public boolean delPaymentByPaymentId(String paymentId);
	/**
	 * ����֧����ʽ
	 * @param gePayment
	 */
	public void updatePayment(GePayment gePayment);
	
	/**
	 * ��ѯ֧����ʽ����
	 * @param queryRule
	 * @return
	 */
	public List<GePayment> findPayments(QueryRule queryRule);
	
	/**
	 * ����֧����ʽ��Ų�ѯ֧����ʽ
	 * @param paymentCode
	 * @return
	 */
	public int findCountByPaymentCode(String paymentCode);
	
	/**
	 * ����֧��������ѯ֧�ָõ���������֧����ʽ
	 * @param deptId
	 * @return
	 */
	public List<GePayment> findPaymentsByDeptId(String deptId);
	
	/**
	 * ���ݶ��֧��������ѯͬʱ֧����Щ����������֧����ʽ
	 * @param deptIds
	 * @return
	 */
	public List<GePayment> findPaymentsByDeptIds(List<String> deptIds);
}
