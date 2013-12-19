package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

/**
 * 响应报文域
 *  
 *
 */
public class TXInsuranceResponseEhm extends TXInsurance{
	
	//交易结果域
	private TransResultEhm transResultEhm;
	
	//业务扩展域
	private OInsuranceExtensionEhm oInsuranceExtensionEhm;

	public TransResultEhm getTransResultEhm() {
		return transResultEhm;
	}

	public void setTransResultEhm(TransResultEhm transResultEhm) {
		this.transResultEhm = transResultEhm;
	}

	public OInsuranceExtensionEhm getoInsuranceExtensionEhm() {
		return oInsuranceExtensionEhm;
	}

	public void setoInsuranceExtensionEhm(
			OInsuranceExtensionEhm oInsuranceExtensionEhm) {
		this.oInsuranceExtensionEhm = oInsuranceExtensionEhm;
	}

	

}
