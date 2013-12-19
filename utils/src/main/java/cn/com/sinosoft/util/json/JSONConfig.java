package cn.com.sinosoft.util.json;

import java.util.List;

public class JSONConfig implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209993993930396552L;
	
	/**
	 * �����ֶ�
	 */
	private List<String> filterField;
	
	/**
	 * �Ƿ����
	 */
	private boolean ignore = false;
	
	/***
	 * ���Կ�ֵ
	 */
	private boolean ignoreNullValue = false;
	
	/***
	 * ���Կհ��ַ�
	 */
	private boolean ignoreBlankCharacter = false;
	
	/**
	 * �Ƿ������ڸ�ʽ
	 */
	private boolean handlerDateFormat;
	
	/**
	 * �ֶ����ڸ�ʽ��������
	 */
	private List<FieldDateFormatConfig> fieldDateFormatConfig;
	
	/**
	 * Ĭ�����ڴ����ʽ
	 */
	private String defaultDateFormat;

	public List<String> getFilterField() {
		return filterField;
	}

	public void setFilterField(List<String> filterField) {
		this.filterField = filterField;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}
	
	
	public boolean isIgnoreNullValue() {
		return ignoreNullValue;
	}

	public void setIgnoreNullValue(boolean ignoreNullValue) {
		this.ignoreNullValue = ignoreNullValue;
	}

	public boolean isIgnoreBlankCharacter() {
		return ignoreBlankCharacter;
	}

	public void setIgnoreBlankCharacter(boolean ignoreBlankCharacter) {
		this.ignoreBlankCharacter = ignoreBlankCharacter;
	}

	public boolean isHandlerDateFormat() {
		return handlerDateFormat;
	}

	public void setHandlerDateFormat(boolean handlerDateFormat) {
		this.handlerDateFormat = handlerDateFormat;
	}

	public List<FieldDateFormatConfig> getFieldDateFormatConfig() {
		return fieldDateFormatConfig;
	}

	public void setFieldDateFormatConfig(
			List<FieldDateFormatConfig> fieldDateFormatConfig) {
		this.fieldDateFormatConfig = fieldDateFormatConfig;
	}

	public String getDefaultDateFormat() {
		return defaultDateFormat;
	}

	public void setDefaultDateFormat(String defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}
	
}