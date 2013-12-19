package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsured;

/**
 *���㵥-������ 
 */
public interface QuoteInsuredService extends GenericDao<QuoteInsured, String> {
	
	/**
	 * ����������ѯ���㵥-������ 
	 * @param pk
	 * @return
	 */
	public QuoteInsured findQuoteInsuredByPk(String pk);
	
	/**
	 * ��ѯ���е����㵥-������ 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteInsured> findAllQuoteInsured(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ���㵥-������ 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteInsuredByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ�����㵥-������ 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteInsured(String pk);
	
	/**
	 * �޸����㵥-������ 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteInsured(QuoteInsured quoteInsured);

}
