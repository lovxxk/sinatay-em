package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

/**
 * ��Ӧ������
 *
 */
public class TXInsuranceResponse extends TXInsurance{
	
	//���׽����
	private TransResult transResult;
	
	//ҵ����չ��
	private OInsuranceExtension oInsuranceExtension;

	public OInsuranceExtension getoInsuranceExtension() {
		return oInsuranceExtension;
	}

	public void setoInsuranceExtension(OInsuranceExtension oInsuranceExtension) {
		this.oInsuranceExtension = oInsuranceExtension;
	}
	
	public TransResult getTransResult() {
		return transResult;
	}

	public void setTransResult(TransResult transResult) {
		this.transResult = transResult;
	}

}
