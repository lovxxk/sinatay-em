package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

import java.io.Serializable;

/**
 * 请求报文域
 *
 */
public class RequestBase implements Serializable{
	
	/**请求类型  1-网销*/
	private String requestType; 
	/** 请求报文域*/
	private TXInsuranceRequest tXInsuranceRequest;
	/** 请求报文扩展域*/
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
