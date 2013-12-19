package cn.com.sinosoft.ebusiness.sale.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.BeanUtils;

import com.actuate.logging.Log;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteMainService;

public class QuoteMainServiceSpringImpl extends GenericDaoHibernate<QuoteMain, String> implements QuoteMainService {

	@Override
	public boolean saveQuoteMain(QuoteMain quoteMain) {
		try {
			super.save(quoteMain);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public QuoteMain findQuoteMainByPk(String pk) {
		return super.findUnique("quoteNo", pk);
	}

	@Override
	public List<QuoteMain> findAllQuoteMain(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public Page findAllQuoteMainByPage(QueryRule queryRule, int pageNo, int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	@Override
	public boolean deleteQuoteMain(String pk) {
		try {
			super.deleteByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateQuoteMain(QuoteMain quoteMain) {
		boolean flag = false;
		try {
			QuoteMain update = super.findUnique("quoteNo", quoteMain.getQuoteNo());
			BeanUtils.copyProperties(quoteMain, update, new String[] { "quoteNo" });
			super.getHibernateTemplate().merge(update);
			flag = true;
		} catch (Exception e) {
			Log.error("insured failed.because updateQuoteMain failed." + e.getMessage());
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<QuoteMain> getTwoQuoteMains(String userId) {
		Query query = getSession().createQuery("from " + QuoteMain.class.getName() + " where userId = ? order by updateTime desc");
		query.setMaxResults(2);
		query.setString(0, userId);
		
		return query.list();

	}

	@Override
	public QuoteMain getQuoteMainByQuoteNo(String serialNo) {
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("quoteNo", serialNo);
		
		List list = this.find(queryRule);
		
		return list.isEmpty() ? null : (QuoteMain)list.get(0);
	}

}
