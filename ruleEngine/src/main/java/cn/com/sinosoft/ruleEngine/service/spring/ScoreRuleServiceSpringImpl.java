package cn.com.sinosoft.ruleEngine.service.spring;

import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.ruleEngine.domain.ScoreInputBOM;
import cn.com.sinosoft.ruleEngine.domain.ScoreResultBOM;
import cn.com.sinosoft.ruleEngine.service.facade.DroolsRuleService;
import cn.com.sinosoft.ruleEngine.service.facade.ScoreRuleService;

public class ScoreRuleServiceSpringImpl implements ScoreRuleService{

	@Override
	public List<ScoreResultBOM> getScoreInfo(List<ScoreInputBOM> scoreInfoList) {
		DroolsRuleService drs = new DroolsRuleServiceSpringImpl();
		List<ScoreResultBOM> scoreResultList = new ArrayList<ScoreResultBOM>();
		try{
			if (scoreInfoList.size() > 0){
				for (int i = 0; i < scoreInfoList.size(); i++){
					ScoreInputBOM inputBOM = scoreInfoList.get(i);
					ScoreResultBOM resultXOM = new ScoreResultBOM();
					ScoreResultBOM returnBOM = (ScoreResultBOM)drs
							.executeRules(
									"cn.com.sinosoft.ebusiness.ruleEngine.ScoreRuleFow", 
									inputBOM, resultXOM, 
									"/drools_score.properties");
					Float score = 0.f;
					if(returnBOM.getScore() != "0" && returnBOM.getScore() != null){
						score = Integer.parseInt(returnBOM.getScore())*(Float.parseFloat(returnBOM.getPresence()));
					}else{
						score = Integer.parseInt(inputBOM.getPayment())*(Float.parseFloat(returnBOM.getPresence()));
					}
					returnBOM.setScore(Float.toString(score));
					scoreResultList.add(returnBOM);
				}
			}
			return scoreResultList;
		}catch(Exception e){
			return null;
		}
	}

}
