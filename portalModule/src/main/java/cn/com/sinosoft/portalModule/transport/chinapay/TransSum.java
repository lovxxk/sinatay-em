package cn.com.sinosoft.portalModule.transport.chinapay;

public class TransSum implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/** ���� ҵ�����  */
	private String businessCode;

	/** ���� �̻����� */
	private String merchantId;
	
	/** ���� �ύʱ�� */
	private String submitTime;
	
	/** ���� �ܼ�¼��  */
	private String totalItem;

	/** ���� �ܽ��*/
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
