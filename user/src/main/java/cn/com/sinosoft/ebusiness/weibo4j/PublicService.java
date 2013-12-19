package cn.com.sinosoft.ebusiness.weibo4j;

import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONArray;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONObject;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class PublicService extends Weibo{

	private static final long serialVersionUID = 1L;

	/**
	 * ͨ����ַ�����ȡ��ַ���� 
	 * 
	 * 
	 */
	public JSONArray getLocationByCode (String codes) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/code_to_location.json",new PostParameter[] {
			new PostParameter("codes", codes)
		}).asJSONArray();
	}
	/**
	 * ��ȡʡ���б� 
	 * 
	 * 
	 */
	public JSONArray provinceList (String country) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_province.json",new PostParameter[] {
			new PostParameter("country", country)
		}).asJSONArray();
	}
	
	public JSONArray provinceListOfCapital (String country,String capital) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_province.json",new PostParameter[] {
			new PostParameter("country", country),
			new PostParameter("capital", capital)
		}).asJSONArray();
	}
	
	public JSONArray provinceList (String country,String language) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_province.json",new PostParameter[] {
			new PostParameter("country", country),
			new PostParameter("language", language)
		}).asJSONArray();
	}
	
	public JSONArray provinceList (String country,String capital,String language) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_province.json",new PostParameter[] {
			new PostParameter("country", country),
			new PostParameter("capital", capital),
			new PostParameter("language", language)
		}).asJSONArray();
	}
	/**
	 * ��ȡ�����б� 
	 * 
	 * 
	 */
	public JSONArray cityList (String province) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_city.json",new PostParameter[] {
			new PostParameter("province", province)
		}).asJSONArray();
	}
	
	public JSONArray cityListOfCapital (String province,String capital) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_city.json",new PostParameter[] {
			new PostParameter("province", province),
			new PostParameter("capital", capital)
		}).asJSONArray();
	}
	
	public JSONArray cityList (String province,String language) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_city.json",new PostParameter[] {
			new PostParameter("province", province),
			new PostParameter("language", language)
		}).asJSONArray();
	}
	
	public JSONArray cityList (String province,String capital,String language) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_city.json",new PostParameter[] {
			new PostParameter("province", province),
			new PostParameter("capital", capital),
			new PostParameter("language", language)
		}).asJSONArray();
	}
	/**
	 * ��ȡ�����б� 
	 * 
	 * 
	 */
	public JSONArray countryList () throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_country.json").asJSONArray();
	}
	
	public JSONArray countryListOfCapital (String capital) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_country.json",new PostParameter[] {
			new PostParameter("capital", capital)
		}).asJSONArray();
	}
	
	public JSONArray countryList (String language) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_country.json",new PostParameter[] {
			new PostParameter("language", language)
		}).asJSONArray();
	}
	
	public JSONArray countryList (String capital,String language) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_country.json",new PostParameter[] {
			new PostParameter("capital", capital),
			new PostParameter("language", language)
		}).asJSONArray();
	}
	
	/**
	 * ��ȡʱ�����ñ� 
	 * 
	 */
	public JSONObject getTomeZone () throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "common/get_timezone.json").asJSONObject();
	}

}
