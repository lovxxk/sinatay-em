package cn.com.sinosoft.ebusiness.sale.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteBeneficiary;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsured;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteRole;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteInsuredService;

public class QuoteInsuredServiceSpringImpl extends GenericDaoHibernate<QuoteInsured, String> implements QuoteInsuredService {

	@Override
	public QuoteInsured findQuoteInsuredByPk(String pk) {
		return super.findUnique("serialNo", pk);
	}

	@Override
	public List<QuoteInsured> findAllQuoteInsured(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public Page findAllQuoteInsuredByPage(QueryRule queryRule, int pageNo,
			int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	@Override
	public boolean deleteQuoteInsured(String pk) {
		try {
			super.deleteByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateQuoteInsured(QuoteInsured quoteInsured) {
		boolean flag = false;
		try {
			QuoteInsured update = super.findUnique("serialNo", quoteInsured.getSerialNo());
			BeanUtils.copyProperties(quoteInsured, update, new String[] { "serialNo" });
			super.getHibernateTemplate().merge(update);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	

}
