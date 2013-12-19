package cn.com.sinosoft.ebusiness.sale.service.facade;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.ebusiness.sale.domain.InsureFlowDto;
import cn.com.sinosoft.ebusiness.sale.domain.QuoteMain;
import cn.com.sinosoft.ebusiness.sys.moduleException.businessException.SaleException;
import cn.com.sinosoft.portalModule.transport.sinatay.LCCont;

/**
 * 网销服务接口
 *
 */
public interface SaleService {
	/**
	 * 保存试算单信息
	 * @param geNoCarTemporary
	 * @param step(1-试算)
	 * @return
	 */
	public boolean saveQuote(InsureFlowDto insureFlowDto, String step, String quoteNo, String userId, String save_topInsured);
	
	/**
	 * 更新试算单信息
	 * @param geNoCarTemporary
	 * @param step(1-试算)
	 * @return
	 */
	public QuoteMain updateQuote(InsureFlowDto insureFlowDto, String step, String save_topInsured);
	
	/**
	 * 保存试算单信息
	 * @param geNoCarTemporary
	 * @param step(1-试算)
	 * @return
	 */
	public QuoteMain saveQuote(InsureFlowDto insureFlowDto, String step, String userId, String quoteNo);
	
	/**
	 * 根据试算单创建投保单
	 * @param insureFlowDto
	 * @return
	 */
	public InsurancePolicy createInsurancePolicy(InsureFlowDto insureFlowDto);
	
	/**
	 * 把核保返回信息更新到试算单、投保单中
	 * @param uw
	 * @return map--key--quoteMain;
	 * @throws SaleException
	 */
	public boolean saveQuote(LCCont lcCont, InsureFlowDto insureFlowDto, InsurancePolicy insurancePolicy) throws SaleException;
	
	/**
	 * 根据试算单号查询暂存单完成继续投保
	 * @param quoteNo
	 * @return
	 */
	public InsureFlowDto getInsureFlowDtoForConInsu(String quoteNo);
	
	/**
	 * 判断该用户是否可以修改客户五要素
	 */
	public boolean updateInforOrNot(String userid);
	
	/**
	 * 非车险继续填写保单删除
	 */
	public boolean deleteMyQuoteMain(String quoteNo);
	
}
