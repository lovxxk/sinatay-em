package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

/**
 * 请求业务扩展域
 * 
 *  
 * 
 */
public class IInsuranceExtensionEhm {

	/**
	 * 分页标识 0-分页，1-不分页
	 */
	private String pageFlag;

	// 最大返回条数
	private String maxRecords;

	// 页数
	private String rowNumStart;

	// 每页行数
	private String pageRowNum;

	/**
	 * 排序标志 0-排序，1-不排序
	 */
	private String orderFlag;

	// 排序字段
	private String orderField;
	
	/**
	 * 发起数据类型
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
