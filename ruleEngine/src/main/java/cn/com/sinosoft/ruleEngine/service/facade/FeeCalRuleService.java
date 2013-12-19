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
	 * 返回与保费相关的数据
	 * @param input
	 * @return
	 */
	public List<FeeCalResultBOM> getFeeCalResultInfo(List<FeeCalInputBOM> feeCalInputList);
}
