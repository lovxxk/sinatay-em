package cn.com.sinosoft.ebusiness.weibo4j;

import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.Status;
import cn.com.sinosoft.ebusiness.weibo4j.model.StatusWapper;
import cn.com.sinosoft.ebusiness.weibo4j.model.User;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONObject;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class Place extends Weibo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****************��̬��ȡ************************/
	/**
	 * ע��û��д��
	 * 
	 * ��ȡ��ǰ��¼�û�������ѵ�λ�ö�̬ 
	 */
	public StatusWapper friendsTimeLine () throws WeiboException {
		return Status.constructWapperStatus(client.get(WeiboConfig.getValue("baseURL") + "place/friends_timeline.json"));
	}
	
	
	/****************�û���ȡ************************/
	/**
	 * ��ȡLBSλ�÷����ڵ��û���Ϣ 
	 * 
	 * 
	 */
	public JSONObject userInfoInLBS (String uid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/show.json",new PostParameter[] {
			new PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	public JSONObject userInfoInLBS (String uid,int base_app) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/show.json",new PostParameter[] {
			new PostParameter("uid", uid),
			new PostParameter("base_app", base_app)
		}).asJSONObject();
	}
	
	/**
	 * ��ȡ�û�ǩ�����ĵص��б�
	 * 
	 *   ע��û��д��
	 */
	public JSONObject checkinsList (String uid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/checkins.json",new PostParameter[] {
			new PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	/**
	 * ��ȡ�û�����Ƭ�б� 
	 * 
	 * ע��û��д��
	 */
	
	public JSONObject photoList (String uid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/photos.json",new PostParameter[] {
			new PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	/**
	 * ��ȡ�û��ĵ����б� 
	 * 
	 * ע��û��д��
	 */
	
	public JSONObject tipsList (String uid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/users/tips.json",new PostParameter[] {
			new PostParameter("uid", uid)
		}).asJSONObject();
	}
	
	/****************�ص��ȡ************************/
	/**
	 * ��ȡ�ص����� 
	 * 
	 * 
	 */
	public JSONObject poisShow (String poiid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/pois/show.json",new PostParameter[] {
			new PostParameter("poiid", poiid)
		}).asJSONObject();
	}
	
	public JSONObject poisShow (String poiid,int base_app) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/pois/show.json",new PostParameter[] {
			new PostParameter("poiid", poiid),
			new PostParameter("base_app",base_app)
		}).asJSONObject();
	}
	
	/**
	 * ��ȡ��ĳ���ص�ǩ�����˵��б� 
	 * 
	 * ע��ûд��
	 */
	public JSONObject poisUsersList (String poiid) throws WeiboException {
		return client.get(WeiboConfig.getValue("baseURL") + "place/pois/show.json",new PostParameter[] { 
			new PostParameter("poiid", poiid) }).asJSONObject();
	}
	
	/**
	 * ��ȡ��ĳ���ص�������б� 
	 * 
	 * ע��ûд��
	 */
	public User poisTipsList (String poiid) throws WeiboException {
		return new User(client.get(WeiboConfig.getValue("baseURL") + "place/pois/tips.json",new PostParameter[] { 
			new PostParameter("poiid", poiid) }).asJSONObject());
	}
	
}
