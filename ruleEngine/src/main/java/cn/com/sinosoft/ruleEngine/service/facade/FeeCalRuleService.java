package cn.com.sinosoft.ruleEngine.service.facade;

import java.util.List;

import cn.com.sinosoft.ruleEngine.domain.FeeCalInputBOM;
import cn.com.sinosoft.ruleEngine.domain.FeeCalResultBOM;

/**
 *  
 *
 */
public interface FeeCalRuleService {
	/**
	 * �����뱣����ص�����
	 * @param input
	 * @return
	 */
	public List<FeeCalResultBOM> getFeeCalResultInfo(List<FeeCalInputBOM> feeCalInputList);
}
