package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;

/**
 * ���ر�����
 *
 */
public class ResponseBase implements Serializable{
	/**��Ӧ������*/
	private TXInsuranceResponse tXInsuranceResponse;
	/**��Ӧ������չ��*/
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
