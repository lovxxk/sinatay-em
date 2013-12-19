package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

/**
 * 请求报文域
 *  
 *
 */
public class TXInsuranceRequestEhm extends TXInsurance{
	
	//业务扩展域
	private IInsuranceExtensionEhm iInsuranceExtensionEhm;

	public IInsuranceExtensionEhm getiInsuranceExtensionEhm() {
		return iInsuranceExtensionEhm;
	}

	public void setiInsuranceExtensionEhm(
			IInsuranceExtensionEhm iInsuranceExtensionEhm) {
		this.iInsuranceExtensionEhm = iInsuranceExtensionEhm;
	}
	
	



}
