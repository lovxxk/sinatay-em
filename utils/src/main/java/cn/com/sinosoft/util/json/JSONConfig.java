package cn.com.sinosoft.util.json;

import java.util.List;

public class JSONConfig implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2209993993930396552L;
	
	/**
	 * 过滤字段
	 */
	private List<String> filterField;
	
	/**
	 * 是否忽略
	 */
	private boolean ignore = false;
	
	/***
	 * 忽略空值
	 */
	private boolean ignoreNullValue = false;
	
	/***
	 * 忽略空白字符
	 */
	private boolean ignoreBlankCharacter = false;
	
	/**
	 * 是否处理日期格式
	 */
	private boolean handlerDateFormat;
	
	/**
	 * 字段日期格式处理配置
	 */
	private List<FieldDateFormatConfig> fieldDateFormatConfig;
	
	/**
	 * 默认日期处理格式
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