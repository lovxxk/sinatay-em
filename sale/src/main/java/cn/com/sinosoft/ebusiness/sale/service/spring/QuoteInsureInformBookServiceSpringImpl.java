package cn.com.sinosoft.ebusiness.sale.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsureInformBook;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsured;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteRole;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteInsureInformBookService;

public class QuoteInsureInformBookServiceSpringImpl extends GenericDaoHibernate<QuoteInsureInformBook, String> implements
		QuoteInsureInformBookService {

	@Override
	public QuoteInsureInformBook findQuoteInsureInformBookByPk(String pk) {
		return super.findUnique("serialNo", pk);
	}

	@Override
	public List<QuoteInsureInformBook> findAllQuoteInsureInformBook(
			QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public Page findAllQuoteInsureInformBookByPage(QueryRule queryRule,
			int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	@Override
	public boolean deleteQuoteInsureInformBook(String pk) {
		try {
			super.deleteByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateQuoteInsureInformBook(
			QuoteInsureInformBook quoteInsureInformBook) {
		boolean flag = false;
		try {
			QuoteInsureInformBook update = super.findUnique("serialNo", quoteInsureInformBook.getSerialNo());
			BeanUtils.copyProperties(quoteInsureInformBook, update, new String[] { "serialNo" });
			super.getHibernateTemplate().merge(update);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	

}
