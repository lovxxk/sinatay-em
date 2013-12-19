package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteBeneficiary;

/**
 *试算单-受益人 
 */
public interface QuoteBeneficiaryService extends GenericDao<QuoteBeneficiary, String> {
	
	/**
	 * 根据主键查询试算单-受益人 
	 * @param pk
	 * @return
	 */
	public QuoteBeneficiary findQuoteBeneficiaryByPk(String pk);
	
	/**
	 * 查询所有的试算单-受益人 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteBeneficiary> findAllQuoteBeneficiary(QueryRule queryRule);
	
	/**
	 * 分页查询试算单-受益人 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteBeneficiaryByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * 按主键删除试算单-受益人 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteBeneficiary(String pk);
	
	/**
	 * 修改试算单-受益人 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteBeneficiary(QuoteBeneficiary quoteBeneficiary);

}
