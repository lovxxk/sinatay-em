package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteBeneficiary;

/**
 *���㵥-������ 
 */
public interface QuoteBeneficiaryService extends GenericDao<QuoteBeneficiary, String> {
	
	/**
	 * ����������ѯ���㵥-������ 
	 * @param pk
	 * @return
	 */
	public QuoteBeneficiary findQuoteBeneficiaryByPk(String pk);
	
	/**
	 * ��ѯ���е����㵥-������ 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteBeneficiary> findAllQuoteBeneficiary(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ���㵥-������ 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteBeneficiaryByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ�����㵥-������ 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteBeneficiary(String pk);
	
	/**
	 * �޸����㵥-������ 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteBeneficiary(QuoteBeneficiary quoteBeneficiary);

}
