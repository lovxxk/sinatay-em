package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

/**
 * ��Ӧ������
 *  
 *
 */
public class TXInsuranceResponseEhm extends TXInsurance{
	
	//���׽����
	private TransResultEhm transResultEhm;
	
	//ҵ����չ��
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
