package cn.com.sinosoft.ebusiness.weibo4j;

import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONObject;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class ShortUrl extends Weibo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**  
	 * ������תΪ������
	 *
	 *
	 */
	public JSONObject longToShortUrl (String url_long) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/shorten.json",new PostParameter[] {
			new PostParameter("url_long",url_long)
		}).asJSONObject();
	}
	
	/**
	 * ������תΪ������
	 * 
	 */
	public JSONObject shortToLongUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/expand.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * ��ȡ�����ӵ��ܵ����
	 * 
	 * 
	 */
	public JSONObject clicksOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/clicks.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * ��ȡһ�������ӵ����referer��Դ������ 
	 * 
	 * 
	 */
	public JSONObject referersOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/referers.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * 
	 * ��ȡһ�������ӵ���ĵ�����Դ������ 
	 * 
	 */
	public JSONObject locationsOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/locations.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	
	/**
	 * ��ȡ��������΢���ϵ�΢�������� 
	 * 
	 * 
	 * 
	 */
	public JSONObject shareCountsOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/share/counts.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	/**
	 * ��ȡ����ָ�����������ӵ�����΢������ 
	 * 
	 * 
	 */
	public JSONObject statusesContentUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/share/statuses.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	/**
	 * ��ȡ��������΢���ϵ�΢�������� 
	 * 
	 * 
	 */
	public JSONObject commentCountOfUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/comment/counts.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
	/**
	 * ��ȡ����ָ�����������ӵ�����΢������ 
	 * 
	 */
	public JSONObject commentsContentUrl (String url_short) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "short_url/comment/comments.json",new PostParameter[] {
			new PostParameter("url_short",url_short)
		}).asJSONObject();
	}
}
