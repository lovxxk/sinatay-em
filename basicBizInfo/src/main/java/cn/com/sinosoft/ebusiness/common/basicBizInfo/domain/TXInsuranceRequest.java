package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

/**
 * ��������
 *
 */
public class TXInsuranceRequest extends TXInsurance{
	
	//ҵ����չ��
	private IInsuranceExtension iInsuranceExtension;
	
	
	public IInsuranceExtension getIInsuranceExtension() {
		return iInsuranceExtension;
	}

	public void setIInsuranceExtension(IInsuranceExtension iInsuranceExtension) {
		this.iInsuranceExtension = iInsuranceExtension;
	}



}
