package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

public class TransMessageRequest {
	
	protected TXInsuranceXMLRequest insuranceRequest;
	
	protected TXInsuranceRequestExtension insuranceRequestExtension;

	public TXInsuranceXMLRequest getInsuranceRequest() {
		return insuranceRequest;
	}

	public void setInsuranceRequest(TXInsuranceXMLRequest insuranceRequest) {
		this.insuranceRequest = insuranceRequest;
	}

	public TXInsuranceRequestExtension getInsuranceRequestExtension() {
		return insuranceRequestExtension;
	}

	public void setInsuranceRequestExtension(
			TXInsuranceRequestExtension insuranceRequestExtension) {
		this.insuranceRequestExtension = insuranceRequestExtension;
	}

	
	
	
}
