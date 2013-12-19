package cn.com.sinosoft.ebusiness.sale.service.facade;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.ebusiness.sale.domain.InsureFlowDto;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.SaleException;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;

/**
 * ��������ӿ�
 *
 */
public interface SaleService {
	/**
	 * �������㵥��Ϣ
	 * @param geNoCarTemporary
	 * @param step(1-����)
	 * @return
	 */
	public boolean saveQuote(InsureFlowDto insureFlowDto, String step, String quoteNo, String userId, String save_topInsured);
	
	/**
	 * �������㵥��Ϣ
	 * @param geNoCarTemporary
	 * @param step(1-����)
	 * @return
	 */
	public QuoteMain updateQuote(InsureFlowDto insureFlowDto, String step, String save_topInsured);
	
	/**
	 * �������㵥��Ϣ
	 * @param geNoCarTemporary
	 * @param step(1-����)
	 * @return
	 */
	public QuoteMain saveQuote(InsureFlowDto insureFlowDto, String step, String userId, String quoteNo);
	
	/**
	 * �������㵥����Ͷ����
	 * @param insureFlowDto
	 * @return
	 */
	public InsurancePolicy createInsurancePolicy(InsureFlowDto insureFlowDto);
	
	/**
	 * �Ѻ˱�������Ϣ���µ����㵥��Ͷ������
	 * @param uw
	 * @return map--key--quoteMain;
	 * @throws SaleException
	 */
	public boolean saveQuote(LCCont lcCont, InsureFlowDto insureFlowDto, InsurancePolicy insurancePolicy) throws SaleException;
	
	/**
	 * �������㵥�Ų�ѯ�ݴ浥��ɼ���Ͷ��
	 * @param quoteNo
	 * @return
	 */
	public InsureFlowDto getInsureFlowDtoForConInsu(String quoteNo);
	
	/**
	 * �жϸ��û��Ƿ�����޸Ŀͻ���Ҫ��
	 */
	public boolean updateInforOrNot(String userid);
	
	/**
	 * �ǳ��ռ�����д����ɾ��
	 */
	public boolean deleteMyQuoteMain(String quoteNo);
	
}
