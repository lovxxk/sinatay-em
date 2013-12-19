package cn.com.sinosoft.ebusiness.sale.service.spring;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDaoHibernate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteInsureInformBook;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteLiability;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteRole;
import cn.com.sinosoft.ebusiness.sale.service.facade.QuoteLiabilityService;

public class QuoteLiabilityServiceSpringImpl extends GenericDaoHibernate<QuoteLiability, String> implements QuoteLiabilityService {

	/**
	 * ����������ѯ���㵥-���� 
	 * @param pk
	 * @return
	 */
	public QuoteLiability findQuoteLiabilityByPk(String pk){
		return super.findUnique("serialNo", pk);
	}
	
	/**
	 * ��ѯ���е����㵥-���� 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteLiability> findAllQuoteLiability(QueryRule queryRule){
		return super.find(queryRule);
	}
	
	/**
	 * ��ҳ��ѯ���㵥-���� 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteLiabilityByPage(QueryRule queryRule,int pageNo,int pageSize){
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/**
	 * ������ɾ�����㵥-���� 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteLiability(String pk){
		try {
			super.deleteByPK(pk);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * �޸����㵥-���� 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteLiability(QuoteLiability quoteLiability){
		boolean flag = false;
		try {
			QuoteLiability update = super.findUnique("serialNo", quoteLiability.getSerialNo());
			BeanUtils.copyProperties(quoteLiability, update, new String[] { "serialNo" });
			super.getHibernateTemplate().merge(update);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
