package cn.com.sinosoft.portalModule.interfacePortal.xml.convertMessage.castorMapping;

import ins.framework.utils.StringUtils;

import org.exolab.castor.mapping.ConfigurableFieldHandler;
import org.exolab.castor.mapping.GeneralizedFieldHandler;

public class StringFieldHandler extends GeneralizedFieldHandler implements ConfigurableFieldHandler{

	@Override
	public Object convertUponGet(Object value) {
		if (value == null)
			return "";
		else
			return StringUtils.strip(value.toString());
	}

	@Override
	public Object convertUponSet(Object value) {
		if (value == null)
			return "";
		else
			return StringUtils.strip(value.toString());
	}
	

	@Override
	public Class getFieldType() {
		return String.class;
	}

}
