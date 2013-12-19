package cn.com.sinosoft.ebusiness.productManagement.productManage.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import java.util.List;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GePayment;
import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductAttrAllowValues;

public interface GeProductAttrAllowValuesService {
	
	/**
	 * �½������ڼ�������Ϣ
	 * @param geProductAttrAllowValues
	 */
	public void createPAAValues(GeProductAttrAllowValues geProductAttrAllowValues);
	
	/**
	 * ��ҳ��ѯ�����ڼ�������Ϣ
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findPAAValues(QueryRule queryRule, int pageNo,
			int pageSize);

	/**
	 * ��������ѯ�����ڼ�������Ϣ
	 * @param serialNo
	 * @return
	 */
	public GeProductAttrAllowValues findPAAValuesBySerialNo(String serialNo);
	
	/**
	 * ��ѯ�����ڼ�������Ϣ����
	 * @param queryRule
	 * @return
	 */
	public List<GeProductAttrAllowValues> findPAAValuesList(QueryRule queryRule);
	
	/**
	 * ���汣���ڼ�������Ϣ
	 */
	public void savePAAValues(GeProductAttrAllowValues pAAValues);
	
	/**
	 * ���汣���ڼ�������Ϣ�б�
	 */
	public void savePAAValuesList(List<GeProductAttrAllowValues> pAAValuesList);
	
	/**
	 * ɾ�������ڼ�������Ϣ
	 * @param PAAValues
	 * @throws Exception
	 */
	public void deletePAAValues(GeProductAttrAllowValues PAAValues) throws Exception;

	/**
	 * ɾ�������ڼ�������Ϣ�б�
	 * @param PAAValues
	 * @throws Exception
	 */
	public void deletePAAValuesList(List<GeProductAttrAllowValues> PAAValuesList) throws Exception;
	
	/**
	 * ���汣���ڼ�������Ϣ�б��Զ��壩
	 * @param PAAValuesList
	 */
	public void savePAAValuesListAll(List<GeProductAttrAllowValues> PAAValuesList);

}
