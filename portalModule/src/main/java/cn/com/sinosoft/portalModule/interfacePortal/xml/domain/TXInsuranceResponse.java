package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

/**
 * 响应报文域
 *  
 *
 */
public class TXInsuranceResponse extends TXInsurance{
	
	//交易结果域
	private TransResult transResult;
	
	//业务扩展域
	private OInsuranceExtension oInsuranceExtension;

	public OInsuranceExtension getOInsuranceExtension() {
		return oInsuranceExtension;
	}

	public void setOInsuranceExtension(OInsuranceExtension oInsuranceExtension) {
		this.oInsuranceExtension = oInsuranceExtension;
	}
	
	public TransResult getTransResult() {
		return transResult;
	}

	public void setTransResult(TransResult transResult) {
		this.transResult = transResult;
	}

}
