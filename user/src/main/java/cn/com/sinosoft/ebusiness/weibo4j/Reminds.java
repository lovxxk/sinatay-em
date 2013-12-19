package cn.com.sinosoft.ebusiness.weibo4j;

import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONObject;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class Reminds extends Weibo{

	private static final long serialVersionUID = 1L;

	/**
	 * 获取某个用户的各种消息未读数 
	 * 
	 */
	public JSONObject getUnreadCountOfRemind () throws WeiboException {
		return client.get(WeiboConfig.getValue("rmURL") + "remind/unread_count.json").asJSONObject();
	}
	
	public JSONObject getUnreadCountOfRemind (String callback) throws WeiboException {
		return client.get(WeiboConfig.getValue("rmURL") + "remind/unread_count.json",new PostParameter[] {
			new PostParameter("callback", callback)
		}).asJSONObject();
	}
	
}
