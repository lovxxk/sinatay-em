package cn.com.sinosoft.ruleEngine.service.facade;

import java.util.List;

import cn.com.sinosoft.ruleEngine.domain.QuoteInputBOM;
import cn.com.sinosoft.ruleEngine.domain.QuoteResultBOM;


public interface QuoteRuleService {
	/**
	 * �����뱣����ص�����
	 * @param input
	 * @return
	 */
	public List<QuoteResultBOM> getQuoteResultInfo(List<QuoteInputBOM> quoteInputBOM,String productId);
}
