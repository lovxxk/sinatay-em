package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

public class TransMessageResponse {
	
	protected TXInsuranceXMLResponse insuranceResponse;
	
	protected TXInsuranceResponseExtension insuranceResponseExtension;

	public TXInsuranceXMLResponse getInsuranceResponse() {
		return insuranceResponse;
	}

	public void setInsuranceResponse(TXInsuranceXMLResponse insuranceResponse) {
		this.insuranceResponse = insuranceResponse;
	}

	public TXInsuranceResponseExtension getInsuranceResponseExtension() {
		return insuranceResponseExtension;
	}

	public void setInsuranceResponseExtension(
			TXInsuranceResponseExtension insuranceResponseExtension) {
		this.insuranceResponseExtension = insuranceResponseExtension;
	}
	
	
}
