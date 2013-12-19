package cn.com.sinosoft.ruleEngine.service.facade;

import java.util.List;

import cn.com.sinosoft.ruleEngine.domain.UndwrtInputBOM;
import cn.com.sinosoft.ruleEngine.domain.UndwrtResultBOM;

public interface UndwrtRuleService {
	/**
	 * ������˱���ص�����
	 * 
	 * @param input
	 * @return
	 */
	public List<UndwrtResultBOM> getUndwrtResultInfo(List<UndwrtInputBOM> undwrtInputList);
}
