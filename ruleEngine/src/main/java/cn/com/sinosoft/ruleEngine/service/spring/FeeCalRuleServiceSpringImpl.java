package cn.com.sinosoft.ruleEngine.service.spring;

import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.ruleEngine.domain.FeeCalInputBOM;
import cn.com.sinosoft.ruleEngine.domain.FeeCalResultBOM;
import cn.com.sinosoft.ruleEngine.service.facade.FeeCalRuleService;

public class FeeCalRuleServiceSpringImpl implements FeeCalRuleService {
	@Override
	public List<FeeCalResultBOM> getFeeCalResultInfo(
			List<FeeCalInputBOM> feeCalInputList) {
		List<FeeCalResultBOM> feeCalResultList = new ArrayList<FeeCalResultBOM>();
		DroolsRuleServiceSpringImpl droolsRuleServiceSpringImpl = new DroolsRuleServiceSpringImpl();
		try{
			if (feeCalInputList.size() > 0) {
				for (int i = 0; i < feeCalInputList.size(); i++) {
					FeeCalResultBOM resultXOM = new FeeCalResultBOM();
					FeeCalInputBOM inputBOM = feeCalInputList.get(i);
					// Ö´ÐÐ¹æÔò
					FeeCalResultBOM returnBOM = (FeeCalResultBOM) droolsRuleServiceSpringImpl.executeRules("cn.com.sinosoft.ebusiness.ruleEngine.FeeCalRuleFow", inputBOM,
									resultXOM, "/drools_feecal.properties");
					feeCalResultList.add(returnBOM);
				}
			}
			return feeCalResultList;
		}catch(Exception e){
			return null;
		}
	}
}
