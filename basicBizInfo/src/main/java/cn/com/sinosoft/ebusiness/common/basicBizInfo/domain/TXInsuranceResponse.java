package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

/**
 * 响应报文域
 *
 */
public class TXInsuranceResponse extends TXInsurance{
	
	//交易结果域
	private TransResult transResult;
	
	//业务扩展域
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
