package cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping;

import ins.framework.utils.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.exolab.castor.mapping.ConfigurableFieldHandler;
import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;

public class DateHandler extends GeneralizedFieldHandler implements ConfigurableFieldHandler {

	private DateFormat formatter;
	
	private boolean flag  = true;
	/**
	 * Sets the configuration for this field handler. The config object is
	 * supposed to have one property, named "date-format", with a date format
	 * pattern compatible with java.text.SimpleDateFormat.
	 * 
	 * @param config
	 *            the configuration object.
	 * @throws ValidityException
	 *             if config doesn't have the required parameter, or if the
	 *             parameter value is not a valid date pattern.
	 */
	public void setConfiguration(Properties config) throws ValidityException {
		String pattern = config.getProperty("date-format");
		
		String flagConfig = config.getProperty("flag");
		
		if (pattern == null) {
			throw new ValidityException(
					"Required parameter \"date-format\" is missing for CustomDateFieldHandler.");
		}
		
		if (flagConfig != null) {
			flag = Boolean.parseBoolean(flagConfig);
		}
		try {
			formatter = new SimpleDateFormat(pattern);
		} catch (IllegalArgumentException e) {
			throw new ValidityException("Pattern \"" + pattern
					+ "\" is not a valid date format.");
		}
	}

	@Override
	public Object convertUponGet(Object value) {
		if (flag) {
			if (value == null) {
				return "";
			} else if (StringUtils.isBlank(value.toString())) {
				return "";
			} else {
				return formatter.format(value);
			}
		} else {
			if (value == null) {
				return null;
			} else if (StringUtils.isBlank(value.toString())) {
				return null;
			} else {
				return formatter.format(value);
			}
		} 
	}

	@Override
	public Object convertUponSet(Object value) {
		if (StringUtils.isBlank((String) value))
			return null;
		Date date = null;
		try {
			
			date = formatter.parse((String) value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getFieldType() {
		return Date.class;
	}

}
