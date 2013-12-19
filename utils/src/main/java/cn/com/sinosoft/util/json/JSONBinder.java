package cn.com.sinosoft.util.json;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import cn.com.sinosoft.util.string.StringUtils;

/**
 * Jackson�ļ򵥷�װ.
 * 
 */
public class JSONBinder {


	private ObjectMapper mapper;

	public JSONBinder(Inclusion inclusion) {
		mapper = new ObjectMapper();
		//�����������������
		mapper.getSerializationConfig().setSerializationInclusion(inclusion);
		//��������ʱ����JSON�ַ����д��ڶ�Java����ʵ��û�е�����
		mapper.getDeserializationConfig().set(
				org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	/**
	 * �������ȫ�����Ե�Json�ַ�����Binder.
	 */
	public static JSONBinder buildNormalBinder() {
		return new JSONBinder(Inclusion.ALWAYS);
	}

	/**
	 * ����ֻ����ǿ����Ե�Json�ַ�����Binder.
	 */
	public static JSONBinder buildNonNullBinder() {
		return new JSONBinder(Inclusion.NON_NULL);
	}

	/**
	 * ����ֻ�����ʼֵ���ı�����Ե�Json�ַ�����Binder.
	 */
	public static JSONBinder buildNonDefaultBinder() {
		return new JSONBinder(Inclusion.NON_DEFAULT);
	}

	/**
	 * ���JSON�ַ���ΪNull��"null"�ַ���,����Null.
	 * ���JSON�ַ���Ϊ"[]",���ؿռ���.
	 * 
	 * �����ȡ������List/Map,�Ҳ���List<String>���ּ�����ʱʹ���������:
	 * List<MyBean> beanList = binder.getMapper().readValue(listString, new TypeReference<List<MyBean>>() {});
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * �������ΪNull,����"null".
	 * �������Ϊ�ռ���,����"[]".
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * ����ת���������͵�format pattern,���������Ĭ�ϴ�ӡTimestamp������.
	 */
	public void setDateFormat(String pattern) {
		if (StringUtils.isNotBlank(pattern)) {
			DateFormat df = new SimpleDateFormat(pattern);
			mapper.getSerializationConfig().setDateFormat(df);
			mapper.getDeserializationConfig().setDateFormat(df);
		}
	}

	/**
	 * ȡ��Mapper����һ�������û�ʹ���������л�API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}
