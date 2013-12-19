package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.util.List;

public class GeKindFromConfig {
	
	private List<GeRiskClassCodeAndRiskCode> synchCoreRiskCodes;//要同步核心的险种
	
	private List<RiskAndKind> geKinds ;//配置在电子商务固定的险种险别

	public List<RiskAndKind> getGeKinds() {
		return geKinds;
	}

	public void setGeKinds(List<RiskAndKind> geKinds) {
		this.geKinds = geKinds;
	}
	
	public List<GeRiskClassCodeAndRiskCode> getSynchCoreRiskCodes() {
		return synchCoreRiskCodes;
	}

	public void setSynchCoreRiskCodes(
			List<GeRiskClassCodeAndRiskCode> synchCoreRiskCodes) {
		this.synchCoreRiskCodes = synchCoreRiskCodes;
	}
}
