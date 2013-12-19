package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteRole;

/**
 *试算单-关系人 
 */
public interface QuoteRoleService extends GenericDao<QuoteRole, String> {
	
	/**
	 * 根据主键查询试算单-关系人 
	 * @param pk
	 * @return
	 */
	public QuoteRole findQuoteRoleByPk(String pk);
	
	/**
	 * 查询所有的试算单-关系人 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteRole> findAllQuoteRole(QueryRule queryRule);
	
	/**
	 * 分页查询试算单-关系人 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteRoleByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除试算单-关系人 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteRole(String pk);
	
	/**
	 * 修改试算单-关系人 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteRole(QuoteRole quoteRole);

}
