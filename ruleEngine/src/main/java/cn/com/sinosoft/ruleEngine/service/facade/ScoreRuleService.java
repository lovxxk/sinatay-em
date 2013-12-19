package cn.com.sinosoft.ruleEngine.service.facade;

import java.util.List;

import cn.com.sinosoft.ruleEngine.domain.ScoreInputBOM;
import cn.com.sinosoft.ruleEngine.domain.ScoreResultBOM;

public interface ScoreRuleService {

	public List<ScoreResultBOM> getScoreInfo(List<ScoreInputBOM> scoreInfoList);
}
