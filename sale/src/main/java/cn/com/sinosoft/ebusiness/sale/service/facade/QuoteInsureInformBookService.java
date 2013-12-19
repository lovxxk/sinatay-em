package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsureInformBook;

/**
 *���㵥-��֪ 
 */
public interface QuoteInsureInformBookService extends GenericDao<QuoteInsureInformBook, String> {
	
	/**
	 * ����������ѯ���㵥-��֪ 
	 * @param pk
	 * @return
	 */
	public QuoteInsureInformBook findQuoteInsureInformBookByPk(String pk);
	
	/**
	 * ��ѯ���е����㵥-��֪ 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteInsureInformBook> findAllQuoteInsureInformBook(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ���㵥-��֪ 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteInsureInformBookByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ�����㵥-��֪ 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteInsureInformBook(String pk);
	
	/**
	 * �޸����㵥-��֪ 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteInsureInformBook(QuoteInsureInformBook quoteInsureInformBook);

}
