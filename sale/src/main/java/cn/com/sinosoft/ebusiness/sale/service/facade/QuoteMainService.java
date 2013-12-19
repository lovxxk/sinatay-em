package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;

/**
 *试算单 
 */
public interface QuoteMainService extends GenericDao<QuoteMain, String> {
	/**
	 * 保存试算单 
	 * @param geStationInfo
	 * @return
	 */
	public boolean saveQuoteMain(QuoteMain quoteMain);
	
	/**
	 * 根据主键查询试算单 
	 * @param pk
	 * @return
	 */
	public QuoteMain findQuoteMainByPk(String pk);
	
	/**
	 * 查询所有的试算单 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteMain> findAllQuoteMain(QueryRule queryRule);
	
	/**
	 * 分页查询试算单 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteMainByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除试算单 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteMain(String pk);
	
	/**
	 * 修改试算单 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteMain(QuoteMain quoteMain);

	/**
	 * 查询时间最新的两条试算单数据，展示在会员中心首页
	 * @param userId
	 * @return
	 */
	public List<QuoteMain> getTwoQuoteMains(String userId);

	public QuoteMain getQuoteMainByQuoteNo(String serialNo);

	
}
