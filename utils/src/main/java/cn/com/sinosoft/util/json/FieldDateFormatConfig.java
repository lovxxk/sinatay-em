package cn.com.sinosoft.util.json;

public class FieldDateFormatConfig implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2850815412926987543L;

	private String fieldName;
	
	private String dateFormat;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	
}
