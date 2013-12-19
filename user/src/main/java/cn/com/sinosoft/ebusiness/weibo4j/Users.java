package cn.com.sinosoft.ebusiness.weibo4j;

import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.User;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONArray;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class Users extends Weibo{

	private static final long serialVersionUID = 4742830953302255953L;


	/*----------------------------�û��ӿ�----------------------------------------*/
	/**
	 * �����û�ID��ȡ�û���Ϣ
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�ID
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/users/show">users/show</a>
	 * @since JDK 1.5
	 */
	public User showUserById(String uid) throws WeiboException {
		return new User(client.get(
				WeiboConfig.getValue("baseURL") + "users/show.json",
				new PostParameter[] { new PostParameter("uid", uid) })
				.asJSONObject());
	}

	/**
	 * �����û�ID��ȡ�û���Ϣ
	 * 
	 * @param screen_name
	 *            �û��ǳ�
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/users/show">users/show</a>
	 * @since JDK 1.5
	 */
	public User showUserByScreenName(String screen_name) throws WeiboException {
		return new User(client.get(
				WeiboConfig.getValue("baseURL") + "users/show.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }).asJSONObject());
	}

	/**
	 * ͨ�����Ի�������ȡ�û������Լ��û����µ�һ��΢��
	 * 
	 * @param domain
	 *            ��Ҫ��ѯ�ĸ��Ի�������
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/users/domain_show">users/domain_show</a>
	 * @since JDK 1.5
	 */
	public User showUserByDomain(String domain) throws WeiboException {
		return new User(client.get(
				WeiboConfig.getValue("baseURL") + "users/domain_show.json",
				new PostParameter[] { new PostParameter("domain", domain) })
				.asJSONObject());
	}
	/**
	 * ������ȡ�û��ķ�˿������ע����΢����
	 * 
	 * @param uids
	 *            ��Ҫ��ȡ���ݵ��û�UID�����֮���ö��ŷָ�����಻����100��
	 * @return jsonobject
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/users/domain_show">users/domain_show</a>
	 * @since JDK 1.5
	 */
	public JSONArray getUserCount(String uids) throws WeiboException{
		return  client.get(WeiboConfig.getValue("baseURL") + "users/counts.json",
				new PostParameter[] { new PostParameter("uids", uids)}).asJSONArray();
	}
}
