package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

/**
 * ����ҵ����չ��
 * 
 *  
 * 
 */
public class IInsuranceExtensionEhm {

	/**
	 * ��ҳ��ʶ 0-��ҳ��1-����ҳ
	 */
	private String pageFlag;

	// ��󷵻�����
	private String maxRecords;

	// ҳ��
	private String rowNumStart;

	// ÿҳ����
	private String pageRowNum;

	/**
	 * �����־ 0-����1-������
	 */
	private String orderFlag;

	// �����ֶ�
	private String orderField;
	
	/**
	 * ������������
	 */
	private String dataType;
	
	public String getPageFlag() {
		return pageFlag;
	}

	public void setPageFlag(String pageFlag) {
		this.pageFlag = pageFlag;
	}

	public String getMaxRecords() {
		return maxRecords;
	}

	public void setMaxRecords(String maxRecords) {
		this.maxRecords = maxRecords;
	}

	public String getRowNumStart() {
		return rowNumStart;
	}

	public void setRowNumStart(String rowNumStart) {
		this.rowNumStart = rowNumStart;
	}

	public String getPageRowNum() {
		return pageRowNum;
	}

	public void setPageRowNum(String pageRowNum) {
		this.pageRowNum = pageRowNum;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
}
