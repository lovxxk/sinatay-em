package cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping;

import ins.framework.utils.StringUtils;

import java.util.Properties;

import org.exolab.castor.mapping.ConfigurableFieldHandler;
import org.exolab.castor.mapping.GeneralizedFieldHandler;
import org.exolab.castor.mapping.ValidityException;

public class IntegerConverterString extends GeneralizedFieldHandler implements ConfigurableFieldHandler {

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
		
		String flagConfig = config.getProperty("flag");
		if (flagConfig != null) {
			flag = Boolean.parseBoolean(flagConfig);
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
				return value.toString();
			}
		} else {
			if (value == null) {
				return null;
			} else if (StringUtils.isBlank(value.toString())) {
				return null;
			} else {
				return value.toString();
			}
		} 
	}

	@Override
	public Object convertUponSet(Object value) {
		if (StringUtils.isBlank((String) value))
			return null;
		return Integer.valueOf((String)value);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getFieldType() {
		return Integer.class;
	}

}
