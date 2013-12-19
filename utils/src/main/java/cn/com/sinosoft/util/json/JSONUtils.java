package cn.com.sinosoft.util.json;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.PropertyFilter;

public class JSONUtils {
	
	public static JsonConfig jsonConfig;
	
	/**
	 * json∏Ò ΩªØ≈‰÷√
	 */
	public static JsonConfig getJsonConfig(final JSONConfig config) {
		
		if(jsonConfig == null && config != null) {
			jsonConfig = new JsonConfig();
			jsonConfig.setJsonPropertyFilter(new PropertyFilter(){
				public boolean apply(Object source, String name, Object value) {
					if (value != null) {
						if (config.getFilterField() != null) {
							if (config.isIgnore()) {
								if (config.getFilterField().contains(name)) {
									return false;
								} else {
									return true;
								}
							} else {
								if (config.getFilterField().contains(name)) {
									return true;
								} else {
									return false;
								}
							}
						} else {
							return false;
						}
					} else {
						return true;
					}
				}
				
			});
			if (config.isHandlerDateFormat()) {
				jsonConfig.registerJsonValueProcessor(Date.class, new JSONValueProcessorImpl(config));
				jsonConfig.registerJsonBeanProcessor(Date.class, new JsDateJsonBeanProcessor());
			}
		}
		return jsonConfig;
	}
}
