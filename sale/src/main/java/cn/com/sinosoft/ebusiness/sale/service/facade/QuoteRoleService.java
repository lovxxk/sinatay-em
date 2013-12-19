package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteRole;

/**
 *���㵥-��ϵ�� 
 */
public interface QuoteRoleService extends GenericDao<QuoteRole, String> {
	
	/**
	 * ����������ѯ���㵥-��ϵ�� 
	 * @param pk
	 * @return
	 */
	public QuoteRole findQuoteRoleByPk(String pk);
	
	/**
	 * ��ѯ���е����㵥-��ϵ�� 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteRole> findAllQuoteRole(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ���㵥-��ϵ�� 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteRoleByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ�����㵥-��ϵ�� 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteRole(String pk);
	
	/**
	 * �޸����㵥-��ϵ�� 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteRole(QuoteRole quoteRole);

}
