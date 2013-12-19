package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteLiability;

/**
 *试算单-责任 
 */
public interface QuoteLiabilityService extends GenericDao<QuoteLiability, String> {
	
	/**
	 * 根据主键查询试算单-责任 
	 * @param pk
	 * @return
	 */
	public QuoteLiability findQuoteLiabilityByPk(String pk);
	
	/**
	 * 查询所有的试算单-责任 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteLiability> findAllQuoteLiability(QueryRule queryRule);
	
	/**
	 * 分页查询试算单-责任 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteLiabilityByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除试算单-责任 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteLiability(String pk);
	
	/**
	 * 修改试算单-责任 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteLiability(QuoteLiability quoteLiability);

}
