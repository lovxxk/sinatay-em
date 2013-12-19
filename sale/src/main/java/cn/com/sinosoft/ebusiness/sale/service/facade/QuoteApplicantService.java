package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteApplicant;

/**
 *试算单-投保人 
 */
public interface QuoteApplicantService extends GenericDao<QuoteApplicant, String> {
	
	/**
	 * 根据主键查询试算单-投保人 
	 * @param pk
	 * @return
	 */
	public QuoteApplicant findQuoteApplicantByPk(String pk);
	
	/**
	 * 查询所有的试算单-投保人 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteApplicant> findAllQuoteApplicant(QueryRule queryRule);
	
	/**
	 * 分页查询试算单-投保人 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteApplicantByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除试算单-投保人 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteApplicant(String pk);
	
	/**
	 * 修改试算单-投保人 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteApplicant(QuoteApplicant quoteApplicant);

}
