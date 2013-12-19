package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;

/**
 * 返回报文域
 *
 */
public class ResponseBase implements Serializable{
	/**响应报文域*/
	private TXInsuranceResponse tXInsuranceResponse;
	/**响应报文扩展域*/
	private TXInsuranceResponseExtension tXInsuranceResponseExtension;
	public TXInsuranceResponse gettXInsuranceResponse() {
		return tXInsuranceResponse;
	}
	public void settXInsuranceResponse(TXInsuranceResponse tXInsuranceResponse) {
		this.tXInsuranceResponse = tXInsuranceResponse;
	}
	public TXInsuranceResponseExtension gettXInsuranceResponseExtension() {
		return tXInsuranceResponseExtension;
	}
	public void settXInsuranceResponseExtension(
			TXInsuranceResponseExtension tXInsuranceResponseExtension) {
		this.tXInsuranceResponseExtension = tXInsuranceResponseExtension;
	}
}
