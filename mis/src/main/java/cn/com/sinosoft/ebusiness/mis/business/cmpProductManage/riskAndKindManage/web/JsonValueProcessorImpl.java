package cn.com.sinosoft.ebusiness.mis.business.cmpProductManage.riskAndKindManage.web;


import java.text.SimpleDateFormat;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonValueProcessorImpl implements JsonValueProcessor {
	private static final String format = "yyyy-MM-dd";
	private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
	
	/** ����ת����ʽ*/
	private String datePattern ;
	/**
	 * ����ת������
	 */
	private SimpleDateFormat sdf ;
	
	/**
	 * ����ת������
	 */
	private SimpleDateFormat sdfTime ;
	/**
	 * ����ת������
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
	 * ����ת������
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
	/** ����ת����ʽ*/
	public String getDatePattern() {
		return datePattern;
	}
	/** ����ת����ʽ*/
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	/**
	 * ����ת������
	 */
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
	/** Ĭ�Ϲ����� */
	public JsonValueProcessorImpl() {
	}
	/**������
	 * 
	 * @param datePattern ���ڸ�ʽ
	 */
	public JsonValueProcessorImpl(String datePattern) {
		this.datePattern = datePattern;
	}
	/**
	 * ������
	 * @param sdf ����ת����
	 */
	public JsonValueProcessorImpl(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	/**
	 *  ����������
	 * @param datePattern ���ڸ�ʽ
	 * @param sdf ����ת����
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
