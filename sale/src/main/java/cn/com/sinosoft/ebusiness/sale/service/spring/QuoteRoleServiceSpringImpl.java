package cn.com.sinosoft.ebusiness.sale.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteRole;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteRoleService;

public class QuoteRoleServiceSpringImpl extends GenericDaoHibernate<QuoteRole, String> implements QuoteRoleService {

	@Override
	public QuoteRole findQuoteRoleByPk(String pk) {
		return super.findUnique("serialNo", pk);
	}

	@Override
	public List<QuoteRole> findAllQuoteRole(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public Page findAllQuoteRoleByPage(QueryRule queryRule, int pageNo,
			int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	@Override
	public boolean deleteQuoteRole(String pk) {
		try {
			super.deleteByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateQuoteRole(QuoteRole quoteRole) {
		boolean flag = false;
		try {
			QuoteRole update = super.findUnique("serialNo", quoteRole.getSerialNo());
			BeanUtils.copyProperties(quoteRole, update, new String[] { "serialNo" });
			super.getHibernateTemplate().merge(update);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


}
