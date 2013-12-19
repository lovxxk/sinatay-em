package cn.com.sinosoft.portalModule.transport.sinatay;


public class TranData implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 基本信息
	 */
	private BaseInfo baseInfo;

	/**
	 * 交易请求信息
	 */
	private TranRequest tranRequest;

	/**
	 * 响应报文
	 */
	private TranResponse tranResponse;

	public BaseInfo getBaseInfo() {
		return baseInfo;
	}

	public void setBaseInfo(BaseInfo baseInfo) {
		this.baseInfo = baseInfo;
	}

	public TranRequest getTranRequest() {
		return tranRequest;
	}

	public void setTranRequest(TranRequest tranRequest) {
		this.tranRequest = tranRequest;
	}

	public TranResponse getTranResponse() {
		return tranResponse;
	}

	public void setTranResponse(TranResponse tranResponse) {
		this.tranResponse = tranResponse;
	}

}
