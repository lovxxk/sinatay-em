package cn.com.sinosoft.portalModule.interfacePortal.xml.domain;

/**
 * 返回业务扩展域
 *  
 *
 */
public class OInsuranceExtensionEhm {

	//返回数据总条数
	private String maxRecords;
	
	/**
	 * 发起数据类型
	 */
	private String dataType;
	
	public String getMaxRecords() {
		return maxRecords;
	}

	public void setMaxRecords(String maxRecords) {
		this.maxRecords = maxRecords;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	
}
