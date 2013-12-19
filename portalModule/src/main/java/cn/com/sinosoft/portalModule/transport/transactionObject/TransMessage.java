package cn.com.sinosoft.portalModule.transport.transactionObject;


public class TransMessage extends TXInsurance {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1030182797112868325L;

	private TXInsurance insurance;
	
	//业务对象
	private Object businessData;
	
	private TXInsuranceExtension insuranceExtension;

	public TXInsurance getInsurance() {
		return insurance;
	}

	public void setInsurance(TXInsurance insurance) {
		this.insurance = insurance;
	}

	public Object getBusinessData() {
		return businessData;
	}

	public void setBusinessData(Object businessData) {
		this.businessData = businessData;
	}

	public TXInsuranceExtension getInsuranceExtension() {
		return insuranceExtension;
	}

	public void setInsuranceExtension(TXInsuranceExtension insuranceExtension) {
		this.insuranceExtension = insuranceExtension;
	}
	
	
}
