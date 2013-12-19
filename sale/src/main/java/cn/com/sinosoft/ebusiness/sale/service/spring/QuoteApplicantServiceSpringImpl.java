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
	 * ����������ѯ���㵥-Ͷ���� 
	 * @param pk
	 * @return
	 */
	public QuoteApplicant findQuoteApplicantByPk(String pk){
		return super.findUnique("serialNo", pk);
	}
	
	/**
	 * ��ѯ���е����㵥-Ͷ���� 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteApplicant> findAllQuoteApplicant(QueryRule queryRule){
		return super.find(queryRule);
	}
	
	/**
	 * ��ҳ��ѯ���㵥-Ͷ���� 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteApplicantByPage(QueryRule queryRule,int pageNo,int pageSize){
		return super.find(queryRule, pageNo, pageSize);
	}
	
	/**
	 * ������ɾ�����㵥-Ͷ���� 
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
	 * �޸����㵥-Ͷ���� 
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
