package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteLiability;

/**
 *���㵥-���� 
 */
public interface QuoteLiabilityService extends GenericDao<QuoteLiability, String> {
	
	/**
	 * ����������ѯ���㵥-���� 
	 * @param pk
	 * @return
	 */
	public QuoteLiability findQuoteLiabilityByPk(String pk);
	
	/**
	 * ��ѯ���е����㵥-���� 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteLiability> findAllQuoteLiability(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ���㵥-���� 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteLiabilityByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ�����㵥-���� 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteLiability(String pk);
	
	/**
	 * �޸����㵥-���� 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteLiability(QuoteLiability quoteLiability);

}
