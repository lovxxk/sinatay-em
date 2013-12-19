package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsured;

/**
 *试算单-被保人 
 */
public interface QuoteInsuredService extends GenericDao<QuoteInsured, String> {
	
	/**
	 * 根据主键查询试算单-被保人 
	 * @param pk
	 * @return
	 */
	public QuoteInsured findQuoteInsuredByPk(String pk);
	
	/**
	 * 查询所有的试算单-被保人 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteInsured> findAllQuoteInsured(QueryRule queryRule);
	
	/**
	 * 分页查询试算单-被保人 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteInsuredByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除试算单-被保人 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteInsured(String pk);
	
	/**
	 * 修改试算单-被保人 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteInsured(QuoteInsured quoteInsured);

}
