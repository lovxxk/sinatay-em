package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;

/**
 * ��������
 *
 */
public class RequestBase implements Serializable{
	
	/**��������  1-����*/
	private String requestType; 
	/** ��������*/
	private TXInsuranceRequest tXInsuranceRequest;
	/** ��������չ��*/
	private TXInsuranceRequestExtension tXInsuranceRequestExtension;
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public TXInsuranceRequest gettXInsuranceRequest() {
		return tXInsuranceRequest;
	}
	public void settXInsuranceRequest(TXInsuranceRequest tXInsuranceRequest) {
		this.tXInsuranceRequest = tXInsuranceRequest;
	}
	public TXInsuranceRequestExtension gettXInsuranceRequestExtension() {
		return tXInsuranceRequestExtension;
	}
	public void settXInsuranceRequestExtension(
			TXInsuranceRequestExtension tXInsuranceRequestExtension) {
		this.tXInsuranceRequestExtension = tXInsuranceRequestExtension;
	}
	
}
