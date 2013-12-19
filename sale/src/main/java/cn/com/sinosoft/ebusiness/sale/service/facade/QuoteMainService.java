package cn.com.sinosoft.ebusiness.sale.service.facade;

import ins.framework.common.Page;
import ins.framework.common.QueryRule;
import ins.framework.dao.GenericDao;

import java.util.List;

import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;

/**
 *���㵥 
 */
public interface QuoteMainService extends GenericDao<QuoteMain, String> {
	/**
	 * �������㵥 
	 * @param geStationInfo
	 * @return
	 */
	public boolean saveQuoteMain(QuoteMain quoteMain);
	
	/**
	 * ����������ѯ���㵥 
	 * @param pk
	 * @return
	 */
	public QuoteMain findQuoteMainByPk(String pk);
	
	/**
	 * ��ѯ���е����㵥 
	 * @param queryRule
	 * @return
	 */
	public List<QuoteMain> findAllQuoteMain(QueryRule queryRule);
	
	/**
	 * ��ҳ��ѯ���㵥 
	 * @param queryRule
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findAllQuoteMainByPage(QueryRule queryRule,int pageNo,int pageSize);
	
	/**
	 * ������ɾ�����㵥 
	 * @param pk
	 * @return
	 */
	public boolean deleteQuoteMain(String pk);
	
	/**
	 * �޸����㵥 
	 * @param geStationInfo
	 * @return
	 */
	public boolean updateQuoteMain(QuoteMain quoteMain);

	/**
	 * ��ѯʱ�����µ��������㵥���ݣ�չʾ�ڻ�Ա������ҳ
	 * @param userId
	 * @return
	 */
	public List<QuoteMain> getTwoQuoteMains(String userId);

	public QuoteMain getQuoteMainByQuoteNo(String serialNo);

	
}
