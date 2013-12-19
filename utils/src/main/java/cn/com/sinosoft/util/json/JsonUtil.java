package cn.com.sinosoft.util.json;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class JsonUtil {
	private static String[] configFilter = new String[] { "comments",
			"relDocTrees" };

	public static JsonConfig getFilter(final String[] s) {
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {

			public boolean apply(Object source, String name, Object value) {
				if (juge(s, name)) {
					return true;
				} else {
					return false;
				}
			}

			public boolean juge(String[] s, String s2) {
				boolean b = false;
				for (String sl : s) {
					if (s2.equals(sl)) {
						b = true;
					}
				}
				return b;
			}
		});
		return config;
	}

	// ת����ʱ����ù�����
	/**
	 * @param entityList
	 * @param cfg
	 *            �Զ����������
	 * @return ��������
	 */
	public static Object getJsonObject(List entityList, String[] cfgFilter) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(cfgFilter);
		JSONArray jSONArray = JSONArray.fromObject(entityList, config);
		return jSONArray.toArray();
	}

	/**
	 * @param entityList
	 * @param cfg
	 *            �Զ����������
	 * @return ��������
	 */
	public static Object getJsonArrayObject(Map entityList, String[] cfgFilter) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(cfgFilter);
		JSONArray jSONArray = JSONArray.fromObject(entityList, config);
		return jSONArray.toArray();
	}

	/**
	 * 
	 * @param entityList
	 * @return ��������
	 */
	public static Object getJsonArrayObject(List entityList) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(configFilter);
		JSONArray jSONArray = JSONArray.fromObject(entityList, config);
		return jSONArray.toArray();
	}

	public static Object getJsonArrayObject(Map entityList) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(configFilter);
		// JsonConfig config = getFilter(configFilter);//String�����д洢����Ҫ���˵�����
		JSONArray jSONArray = JSONArray.fromObject(entityList, config);
		return jSONArray.toArray();
	}

	/**
	 * 
	 * @param jsonStr
	 *            json����
	 * @param entityClass
	 *            ʵ���� xxx.class
	 * @return Object ����
	 */
	public static Object JsonToEntity(String jsonStr, Class entityClass) {
		JSONObject jsonObject = JSONObject.fromObject(jsonStr);
		return JSONObject.toBean(jsonObject, entityClass);
	}
	
	/**
	 * 
	 * @param obj
	 *  ʵ�������
	 * @return json����
	 */
	public static String entityToJson(Object obj) {
		JSONObject jsonObject = JSONObject.fromObject(obj);
		return jsonObject.toString();
	}
	
	
	public static final String SYSMBOL_QUOTE = "\"";
	public static final String SYSMBOL_COLON = ":";
	public static final String SYSMBOL_COMMA = ",";
	public static final String SYSMBOL_BRACES = "}";
	
	/**
	 * ����json�ַ�����ȡ�ؼ��ֵ�ֵ,
	 *  ����: {"Flag": 1, "Password": 471103,"Desc": ""}
	 * @param jsonStr
	 * @param key
	 * @param fromIndex
	 * @return �ؼ���ֵ
	 */
	public static String getJsonValue0(String jsonStr, String key, int fromIndex){//Flag
		key = SYSMBOL_QUOTE + key + SYSMBOL_QUOTE;
		fromIndex = jsonStr.indexOf(key, fromIndex);
		// ����Ҳ����ؼ���,������
		if (fromIndex < 0)
			return null;
		fromIndex = jsonStr.indexOf(SYSMBOL_COLON, fromIndex);
		// ����Ҳ����ؼ���֮���":"����,������
		if (fromIndex < 0)
			throw new JSONException("json�ַ�����ʽ����ȷ.");
		int index_comma = jsonStr.indexOf(SYSMBOL_COMMA, fromIndex);
		int index_braces = jsonStr.indexOf(SYSMBOL_BRACES, fromIndex);
		// ����Ҳ���":"����֮���","��"}" , ������
		if (index_comma < 0 && index_braces < 0)
			throw new JSONException("json�ַ�����ʽ����ȷ.");
		int toIndex = index_comma < index_braces ? index_comma : index_braces;
		String value = jsonStr.substring(++fromIndex, toIndex).trim();
		if(value.charAt(0) == '\"' && value.charAt(value.length()-1)=='\"')
			value = value.substring(1,value.length()-1);
		return value;
	}
	
	
	/**
	 * ����json�ַ�����ȡ�ؼ��ֵ�ֵ,
	 *  ����: {"Flag": 1, "Password": 471103,"Desc": ""}
	 * @param jsonStr
	 * @param key
	 * @return �ؼ���ֵ
	 */
	public static String getJsonValue(String jsonStr, String key){
		return getJsonValue0(jsonStr,key,0);
	}
	
	
	/**
	 * ����json�ַ�����ȡ�ؼ��ֵ�ֵ,
	 *  ����: {"Flag": 1, "Password": 471103,Flag": 1, "Password": 571103,"Desc": ""}, "Password"
	 *  ���� ["471103", "571103"]
	 * @param jsonStr
	 * @param key
	 * @return �ؼ���ֵ������
	 */
	public static Object[] getJsonValues(String jsonStr, String key){
		String key_quote = SYSMBOL_QUOTE + key + SYSMBOL_QUOTE;
		List<String> values = new ArrayList<String>();
		int index = 0;
		while((index = jsonStr.indexOf(key_quote,index)) >0){
			values.add(getJsonValue0(jsonStr, key,index++));
		}
		System.out.println(values);
		return values.toArray();
	}
	
}
