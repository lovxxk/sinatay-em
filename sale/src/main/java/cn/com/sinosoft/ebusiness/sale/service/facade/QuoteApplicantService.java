package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteApplicant;

/**
 *���㵥-Ͷ���� 
 */
public interface QuoteApplicantService extends GenericDao<QuoteApplicant, String> {
	
	/**
	 * ����������ѯ���㵥-Ͷ���� 
	 * @param pk
	 * @return
	 */
	public QuoteApplicant findQuoteApplicantByPk(String pk);
	
	/**
	 * ��ѯ���е����㵥-Ͷ���� 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteApplicant> findAllQuoteApplicant(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ���㵥-Ͷ���� 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteApplicantByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ�����㵥-Ͷ���� 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteApplicant(String pk);
	
	/**
	 * �޸����㵥-Ͷ���� 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteApplicant(QuoteApplicant quoteApplicant);

}
