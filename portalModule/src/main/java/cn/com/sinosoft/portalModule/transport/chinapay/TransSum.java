package cn.com.sinosoft.portalModule.transport.chinapay;

public class TransSum implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 属性 业务代码  */
	private String businessCode;

	/** 属性 商户代码 */
	private String merchantId;
	
	/** 属性 提交时间 */
	private String submitTime;
	
	/** 属性 总记录数  */
	private String totalItem;

	/** 属性 总金额*/
	private String totalSum;

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(String totalItem) {
		this.totalItem = totalItem;
	}

	public String getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(String totalSum) {
		this.totalSum = totalSum;
	}
}
