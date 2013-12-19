package cn.com.sinosoft.util.json;


import java.text.SimpleDateFormat;
import java.util.List;

import cn.com.sinosoft.util.string.StringUtils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
public class JSONValueProcessorImpl implements JsonValueProcessor {
	
	private static final String format = "yyyy-MM-dd";
	
	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 配置
	 */
	private JSONConfig config;
	
	/** 日期转换格式*/
	private String datePattern ;
	/**
	 * 日期转换对象
	 */
	private SimpleDateFormat sdf ;
	
	/**
	 * 日期转换对象
	 */
	private SimpleDateFormat sdfTime;
	
	/**
	 * 日期转换对象
	 */
	public SimpleDateFormat getSdf() {
		if(sdf == null){
			if(getDatePattern()==null||getDatePattern().equals("")){
				setDatePattern(format);
			}
			try {
				sdf = new SimpleDateFormat(getDatePattern());
			} catch (Exception e) {
				sdf = new SimpleDateFormat(format);
			}
		}
		return sdf;
	}
	
	/**
	 * 日期转换对象
	 */
	public SimpleDateFormat getSdfTime() {
		if(sdfTime == null){
			try {
				sdfTime = new SimpleDateFormat(dateTimeFormat);
			} catch (Exception e) {
				sdfTime = new SimpleDateFormat(dateTimeFormat);
			}
		}
		return sdfTime;
	}
	/** 日期转换格式*/
	public String getDatePattern() {
		return datePattern;
	}
	/** 日期转换格式*/
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	/**
	 * 日期转换对象
	 */
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
	/** 默认构造器 */
	public JSONValueProcessorImpl() {
		
	}
	
	public JSONValueProcessorImpl(JSONConfig config) {
		super();
		this.config = config;
	}

	/**构造器
	 * 
	 * @param datePattern 日期格式
	 */
	public JSONValueProcessorImpl(String datePattern) {
		this.datePattern = datePattern;
	}
	
	public JSONConfig getConfig() {
		return config;
	}

	public void setConfig(JSONConfig config) {
		this.config = config;
	}

	/**
	 * 构造器
	 * @param sdf 日期转换器
	 */
	public JSONValueProcessorImpl(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	/**
	 *  完整构造器
	 * @param datePattern 日期格式
	 * @param sdf 日期转换器
	 */
	public JSONValueProcessorImpl(String datePattern, SimpleDateFormat sdf) {
		this.datePattern = datePattern;
		this.sdf = sdf;
	}
	
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return processValue(arg0);
	}

	@SuppressWarnings("unused")
	public Object processObjectValue(String fieldName, Object fieldValue, JsonConfig arg2) {
		if (config != null && fieldValue != null) {
			List<FieldDateFormatConfig> fieldDateFormatConfigList = config.getFieldDateFormatConfig();
			if (fieldDateFormatConfigList != null) {
				for (FieldDateFormatConfig fieldDateFormatConfig:fieldDateFormatConfigList) {
					if (fieldName.equals(fieldDateFormatConfig.getFieldName())) {
						SimpleDateFormat  fieldDateFormat = new SimpleDateFormat(fieldDateFormatConfig.getDateFormat());
						return fieldDateFormat.format((java.util.Date)fieldValue);
					}
				}
			}
			if (StringUtils.isNotBlank(config.getDefaultDateFormat())) {
				SimpleDateFormat  fieldDateFormat = new SimpleDateFormat(config.getDefaultDateFormat());
				return fieldDateFormat.format((java.util.Date)fieldValue);
			} else {
				return processValue(fieldValue);
			}
		} else if (fieldValue != null) {
			if(fieldName.equals("requestDate")){
				return getSdfTime().format((java.util.Date)fieldValue);
			} else if (fieldValue != null){
				return processValue(fieldValue);			
			} 
		} else {
			return fieldValue;
		}
		return null;
	}
	
	public Object processValue(Object arg0){
		return getSdf().format((java.util.Date)arg0);
	}
}
