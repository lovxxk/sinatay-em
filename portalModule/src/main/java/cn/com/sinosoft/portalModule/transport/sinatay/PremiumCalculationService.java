package cn.com.sinosoft.portalModule.transport.sinatay;

public class PremiumCalculationService extends InsuranceService {

	@Override
	public void beforeProcess(Object reObjectObj) {
		//保费试算发报文之前处理

	}

	@Override
	public void afterProcess(Object reObjectObj) {
		//保费试算发报收到返回之后处理;
	}

}
