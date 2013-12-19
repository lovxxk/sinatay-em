package cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.riskAndKindManage.web;


import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonValueProcessorImpl implements JsonValueProcessor {
	private static final String format = "yyyy-MM-dd";
	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	/** 日期转换格式*/
	private String datePattern ;
	/**
	 * 日期转换对象
	 */
	private SimpleDateFormat sdf ;
	
	/**
	 * 日期转换对象
	 */
	private SimpleDateFormat sdfTime ;
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
	public JsonValueProcessorImpl() {
	}
	/**构造器
	 * 
	 * @param datePattern 日期格式
	 */
	public JsonValueProcessorImpl(String datePattern) {
		this.datePattern = datePattern;
	}
	/**
	 * 构造器
	 * @param sdf 日期转换器
	 */
	public JsonValueProcessorImpl(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	/**
	 *  完整构造器
	 * @param datePattern 日期格式
	 * @param sdf 日期转换器
	 */
	public JsonValueProcessorImpl(String datePattern, SimpleDateFormat sdf) {
		this.datePattern = datePattern;
		this.sdf = sdf;
	}
	
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return processValue(arg0);
	}

	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		if(arg0.equals("inputDate")){
			return getSdfTime().format((java.util.Date)arg1);
		} else {
			return processValue(arg1);			
		}
	}
	
	public Object processValue(Object arg0){
		return getSdf().format((java.util.Date)arg0);
	}

}
