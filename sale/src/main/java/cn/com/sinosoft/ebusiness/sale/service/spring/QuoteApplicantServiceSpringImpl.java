package cn.com.sinosoft.ebusiness.sale.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteApplicant;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteRole;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteApplicantService;

public class QuoteApplicantServiceSpringImpl extends GenericDaoHibernate<QuoteApplicant, String> implements QuoteApplicantService {

	/**
	 * 根据主键查询试算单-投保人 
	 * @param pk
	 * @return
	 */
	public QuoteApplicant findQuoteApplicantByPk(String pk){
		return super.findUnique("serialNo", pk);
	}
	
	/**
	 * 查询所有的试算单-投保人 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteApplicant> findAllQuoteApplicant(QueryRule queryRule){
		return super.find(queryRule);
	}
	
	/**
	 * 分页查询试算单-投保人 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteApplicantByPage(QueryRule queryRule,int pageNo,int pageSize){
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/**
	 * 按主键删除试算单-投保人 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteApplicant(String pk){
		try {
			super.deleteByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 修改试算单-投保人 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteApplicant(QuoteApplicant quoteApplicant){
		boolean flag = false;
		try {
			QuoteApplicant update = super.findUnique("serialNo", quoteApplicant.getSerialNo());
			BeanUtils.copyProperties(quoteApplicant, update, new String[] { "serialNo" });
			super.getHibernateTemplate().merge(update);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
