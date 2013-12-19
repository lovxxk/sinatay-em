package cn.com.sinosoft.ebusiness.common.basicBizInfo.domain;

/**
 * 交易结果代码
 *
 */
public class TransResult {
	
	/**
	 * 0 	交易成功	
	 * 1 	交易失败（异常） 	
	 * 2	发送报文编码有误	
	 * 3	报文格式有误	
	 */
	private String resultCode;

	
	private String resultInfoDesc;
	
	public String getResultInfoDesc() {
		return resultInfoDesc;
	}

	public void setResultInfoDesc(String resultInfoDesc) {
		this.resultInfoDesc = resultInfoDesc;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}


	
	
	
	
}
