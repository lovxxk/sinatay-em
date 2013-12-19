package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

/**
 * 请求报文域
 *  
 *
 */
public class TXInsuranceRequest extends TXInsurance{
	
	//业务扩展域
	private IInsuranceExtension iInsuranceExtension;
	
	
	public IInsuranceExtension getIInsuranceExtension() {
		return iInsuranceExtension;
	}

	public void setIInsuranceExtension(IInsuranceExtension iInsuranceExtension) {
		this.iInsuranceExtension = iInsuranceExtension;
	}



}
