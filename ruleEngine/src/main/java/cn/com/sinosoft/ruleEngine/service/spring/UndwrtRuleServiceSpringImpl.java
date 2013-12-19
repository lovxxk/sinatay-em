package cn.com.sinosoft.ruleEngine.service.spring;

import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.ruleEngine.domain.UndwrtInputBOM;
import cn.com.sinosoft.ruleEngine.domain.UndwrtResultBOM;
import cn.com.sinosoft.ruleEngine.service.facade.UndwrtRuleService;

public class UndwrtRuleServiceSpringImpl implements UndwrtRuleService {

	@Override
	public List<UndwrtResultBOM> getUndwrtResultInfo(List<UndwrtInputBOM> undwrtInputList) {
		List<UndwrtResultBOM> undwrtResultList = new ArrayList<UndwrtResultBOM>();
		DroolsRuleServiceSpringImpl droolsRuleServiceSpringImpl = new DroolsRuleServiceSpringImpl();
		try{
			if (undwrtInputList.size() > 0) {
				for (int i = 0; i < undwrtInputList.size(); i++) {
					UndwrtResultBOM resultXOM = new UndwrtResultBOM();
					UndwrtInputBOM inputBOM = undwrtInputList.get(i);
					// Ö´ÐÐ¹æÔò
					UndwrtResultBOM returnBOM=(UndwrtResultBOM)droolsRuleServiceSpringImpl.executeRules("cn.com.sinosoft.ebusiness.sys.ruleEngine.domain.UndwrtRuleFlow",inputBOM,resultXOM,"/drools_undwrt.properties");
					undwrtResultList.add(returnBOM);
				}
			}
			return undwrtResultList;
		}catch(Exception e){
			return null;
		}
	}
}
