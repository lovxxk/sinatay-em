package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsureInformBook;

/**
 *试算单-告知 
 */
public interface QuoteInsureInformBookService extends GenericDao<QuoteInsureInformBook, String> {
	
	/**
	 * 根据主键查询试算单-告知 
	 * @param pk
	 * @return
	 */
	public QuoteInsureInformBook findQuoteInsureInformBookByPk(String pk);
	
	/**
	 * 查询所有的试算单-告知 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteInsureInformBook> findAllQuoteInsureInformBook(QueryRule queryRule);
	
	/**
	 * 分页查询试算单-告知 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteInsureInformBookByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除试算单-告知 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteInsureInformBook(String pk);
	
	/**
	 * 修改试算单-告知 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteInsureInformBook(QuoteInsureInformBook quoteInsureInformBook);

}
