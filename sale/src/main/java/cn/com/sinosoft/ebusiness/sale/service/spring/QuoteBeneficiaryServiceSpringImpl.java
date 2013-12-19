package cn.com.sinosoft.ebusiness.sale.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteApplicant;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteBeneficiary;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteRole;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteBeneficiaryService;

public class QuoteBeneficiaryServiceSpringImpl extends GenericDaoHibernate<QuoteBeneficiary, String> implements
		QuoteBeneficiaryService {

	@Override
	public QuoteBeneficiary findQuoteBeneficiaryByPk(String pk) {
		return super.findUnique("serialNo", pk);
	}

	@Override
	public List<QuoteBeneficiary> findAllQuoteBeneficiary(QueryRule queryRule) {
		return super.find(queryRule);
	}

	@Override
	public Page findAllQuoteBeneficiaryByPage(QueryRule queryRule, int pageNo,
			int pageSize) {
		return super.find(queryRule, pageNo, pageSize);
	}

	@Override
	public boolean deleteQuoteBeneficiary(String pk) {
		try {
			super.deleteByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateQuoteBeneficiary(QuoteBeneficiary quoteBeneficiary) {
		boolean flag = false;
		try {
			QuoteBeneficiary update = super.findUnique("serialNo", quoteBeneficiary.getSerialNo());
			BeanUtils.copyProperties(quoteBeneficiary, update, new String[] { "serialNo" });
			super.getHibernateTemplate().merge(update);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	

}
