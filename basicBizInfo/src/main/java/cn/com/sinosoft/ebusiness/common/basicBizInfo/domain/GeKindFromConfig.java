package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.util.List;

public class GeKindFromConfig {
	
	private List<GeRiskClassCodeAndRiskCode> synchCoreRiskCodes;//Ҫͬ�����ĵ�����
	
	private List<RiskAndKind> geKinds ;//�����ڵ�������̶��������ձ�

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
