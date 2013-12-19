package cn.com.sinosoft.ruleEngine.service.spring;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import cn.com.sinosoft.ruleEngine.domain.QuoteInputBOM;
import cn.com.sinosoft.ruleEngine.domain.QuoteResultBOM;
import cn.com.sinosoft.ruleEngine.service.facade.QuoteRuleService;

public class QuoteRuleServiceSpringImpl implements QuoteRuleService {
	@Override
	public List<QuoteResultBOM> getQuoteResultInfo(
			List<QuoteInputBOM> quoteInputBOM,String urlId) {
		DroolsRuleServiceSpringImpl droolsRuleServiceSpringImpl = new DroolsRuleServiceSpringImpl();
		List<QuoteResultBOM> activityResultList = new ArrayList<QuoteResultBOM>();
		Properties properties=new Properties();
		InputStream in = this.getClass().getResourceAsStream("/drools_quote.properties");
		try {
			properties.load(in);
			properties.setProperty("url",properties.getProperty("url").replace("#",urlId));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			if (quoteInputBOM.size() > 0) {
				for (int i = 0; i < quoteInputBOM.size(); i++) {
					QuoteInputBOM inputBOM = quoteInputBOM.get(i);
					QuoteResultBOM resultXOM = new QuoteResultBOM();
					QuoteResultBOM returnBOM = (QuoteResultBOM) droolsRuleServiceSpringImpl
							.executeRules(
									"cn.com.sinosoft.ebusiness.ruleEngine.QuoteRuleFlow",
									inputBOM, resultXOM,
									properties);
					activityResultList.add(returnBOM);
				}
			}
			return activityResultList;
		}catch(Exception e){
			return null;
		}
	}
}
