package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

/**
 * ��Ӧ������
 *  
 *
 */
public class TXInsuranceResponse extends TXInsurance{
	
	//���׽����
	private TransResult transResult;
	
	//ҵ����չ��
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
