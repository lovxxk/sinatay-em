package cn.com.sinosoft.ebusiness.weibo4j;

import cn.com.sinosoft.ebusiness.weibo4j.model.Paging;
import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.User;
import cn.com.sinosoft.ebusiness.weibo4j.model.UserWapper;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONArray;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class Friendships extends Weibo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3603512821159421447L;

	/*----------------------------��ϵ�ӿ�----------------------------------------*/
	/**
	 * ��ȡ�û��Ĺ�ע�б�
	 * 
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends">friendships/friends</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFriendsByID(String id) throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL") + "friendships/friends.json",
				new PostParameter[] { new PostParameter("uid", id) }));
	}

	/**
	 * ��ȡ�û��Ĺ�ע�б�
	 * 
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends">friendships/friends</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFriendsByScreenName(String screen_name)
			throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL") + "friendships/friends.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }));
	}

	/**
	 * ��ȡ�����û�֮��Ĺ�ͬ��ע���б�
	 * 
	 * @param uid
	 *            ��Ҫ��ȡ��ͬ��ע��ϵ���û�UID
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/in_common">friendships/friends/in_common</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFriendsInCommon(String uid) throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/in_common.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * ��ȡ�����û�֮��Ĺ�ͬ��ע���б�
	 * 
	 * @param uid
	 *            ��Ҫ��ȡ��ͬ��ע��ϵ���û�UID
	 * @param suid
	 *            ��Ҫ��ȡ��ͬ��ע��ϵ���û�UID��Ĭ��Ϊ��ǰ��¼�û�
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ50
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1
	 * @return list of the user's follow
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/in_common">friendships/friends/in_common</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFriendsInCommon(String uid, String suid, Paging page)
			throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL") + "friendships/friends/in_common.json",
				new PostParameter[] { 
					new PostParameter("uid", uid),
					new PostParameter("suid", suid)}, page));
	}

	/**
	 * ��ȡ�û���˫���ע�б��������б�
	 * 
	 * @param uid
	 *            ��Ҫ��ȡ˫���ע�б���û�UID
	 * @return list of the user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/bilateral">friendships/friends/bilateral</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFriendsBilateral(String uid) throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/bilateral.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * ��ȡ�û���˫���ע�б��������б�
	 * 
	 * @param uid
	 *            ��Ҫ��ȡ˫���ע�б���û�UID
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ50��
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1��
	 * @param sort
	 *            �������ͣ�0������עʱ���������Ĭ��Ϊ0��
	 * @return list of the user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @return 
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/bilateral">friendships/friends/bilateral</a>
	 * @since JDK 1.5
	 */
	public  UserWapper getFriendsBilateral(String uid, Integer sort, Paging page)
			throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/bilateral.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("sort", sort.toString()) }, page));
	}

	/**
	 * ��ȡ�û�˫���ע���û�ID�б�������UID�б�
	 * 
	 * @param uid
	 *            ��Ҫ��ȡ˫���ע�б���û�UID
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/bilateral/ids">friendships/friends/bilateral/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsBilateralIds(String uid) throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/bilateral/ids.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * ��ȡ�û�˫���ע���û�ID�б�������UID�б�
	 * 
	 * @param uid
	 *            ��Ҫ��ȡ˫���ע�б���û�UID
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ50��
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1��
	 * @param sort
	 *            �������ͣ�0������עʱ���������Ĭ��Ϊ0��
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/bilateral/ids">friendships/friends/bilateral/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsBilateralIds(String uid, Integer sort, Paging page)
			throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/bilateral/ids.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("sort", sort.toString()) }, page));
	}

	/**
	 * ��ȡ�û���ע���û�UID�б�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�UID
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/ids">friendships/friends/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsIdsByUid(String uid) throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/ids.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * ��ȡ�û���ע���û�UID�б�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�UID
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/ids">friendships/friends/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsIdsByName(String screen_name)
			throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/ids.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }));
	}

	/**
	 * ��ȡ�û���ע���û�UID�б�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�UID
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ500����󲻳���5000
	 * @param cursor
	 *            ���ؽ�����α꣬��һҳ�÷���ֵ���next_cursor����һҳ��previous_cursor��Ĭ��Ϊ0
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/ids">friendships/friends/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsIdsByUid(String uid, Integer count, Integer cursor)
			throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/ids.json", new PostParameter[] {
						new PostParameter("uid", uid),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * ��ȡ�û���ע���û�UID�б�
	 * 
	 * @param screen_name
	 *            ��Ҫ��ѯ���û��ǳ�
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ500����󲻳���5000
	 * @param cursor
	 *            ���ؽ�����α꣬��һҳ�÷���ֵ���next_cursor����һҳ��previous_cursor��Ĭ��Ϊ0
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/ids">friendships/friends/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFriendsIdsByName(String screen_name, Integer count,
			Integer cursor) throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/ids.json", new PostParameter[] {
						new PostParameter("screen_name", screen_name),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * ������ȡ��ǰ��¼�û��Ĺ�ע�˵ı�ע��Ϣ
	 * 
	 * @param uids
	 *            ��Ҫ��ȡ��ע���û�UID���ð�Ƕ��ŷָ�����಻����50��
	 * @return list of user's remark
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends/remark_batch">friendships/friends/remark_batch</a>
	 * @since JDK 1.5
	 */
	public JSONArray getRemark(String uids) throws WeiboException {
		return client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends/remark_batch.json",
				new PostParameter[] { new PostParameter("uids", uids) }).asJSONArray();	
	}

	/**
	 * ��ȡ�û��ķ�˿�б�
	 * 
	 * @param screen_name
	 *            ��Ҫ��ѯ���û��ǳ�
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers">friendships/followers</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFollowersByName(String screen_name)
			throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL") + "friendships/followers.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }));
	}

	/**
	 * ��ȡ�û��ķ�˿�б�
	 * 
	 * @param screen_name
	 *            ��Ҫ��ѯ���û��ǳ�
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ500����󲻳���5000
	 * @param cursor
	 *            ���ؽ�����α꣬��һҳ�÷���ֵ���next_cursor����һҳ��previous_cursor��Ĭ��Ϊ0
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers">friendships/followers</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFollowersByName(String screen_name, Integer count,
			Integer cursor) throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL") + "friendships/followers.json",
				new PostParameter[] {
						new PostParameter("screen_name", screen_name),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * ��ȡ�û��ķ�˿�б�
	 * 
	 * @param screen_name
	 *            ��Ҫ��ѯ���û��ǳ�
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers">friendships/followers</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFollowersById(String uid) throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL") + "friendships/followers.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * ��ȡ�û��ķ�˿�б�
	 * 
	 * @param screen_name
	 *            ��Ҫ��ѯ���û��ǳ�
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ500����󲻳���5000
	 * @param cursor
	 *            ���ؽ�����α꣬��һҳ�÷���ֵ���next_cursor����һҳ��previous_cursor��Ĭ��Ϊ0
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers">friendships/followers</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFollowersById(String uid, Integer count, Integer cursor)
			throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL") + "friendships/followers.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * ��ȡ�û���˿���û�UID�б�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�ID
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/ids">friendships/followers/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFollowersIdsById(String uid) throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/ids.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * ��ȡ�û���˿���û�UID�б�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�ID
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ500����󲻳���5000
	 * @param cursor
	 *            ���ؽ�����α꣬��һҳ�÷���ֵ���next_cursor����һҳ��previous_cursor��Ĭ��Ϊ0
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/ids">friendships/followers/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFollowersIdsById(String uid, Integer count,
			Integer cursor) throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/ids.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * ��ȡ�û���˿���û�UID�б�
	 * 
	 * @param screen_name
	 *            ��Ҫ��ѯ���û��ǳ�
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/ids">friendships/followers/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFollowersIdsByName(String screen_name)
			throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/ids.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }));
	}

	/**
	 * ��ȡ�û���˿���û�UID�б�
	 * 
	 * @param screen_name
	 *            ��Ҫ��ѯ���û�ID
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ500����󲻳���5000
	 * @param cursor
	 *            ���ؽ�����α꣬��һҳ�÷���ֵ���next_cursor����һҳ��previous_cursor��Ĭ��Ϊ0
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/ids">friendships/followers/ids</a>
	 * @since JDK 1.5
	 */
	public String[] getFollowersIdsByName(String screen_name, Integer count,
			Integer cursor) throws WeiboException {
		return User.constructIds(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/ids.json",
				new PostParameter[] {
						new PostParameter("screen_name", screen_name),
						new PostParameter("count", count.toString()),
						new PostParameter("cursor", cursor.toString()) }));
	}

	/**
	 * ��ȡ�û��Ļ�Ծ��˿�б�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�ID
	 * @return list of user's id
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/active">friendships/followers/active</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFollowersActive(String uid) throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/active.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * ��ȡ�û��Ļ�Ծ��˿�б�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�ID
	 * @param count
	 *            ���صļ�¼������Ĭ��Ϊ20����󲻳���200��
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/followers/active">friendships/followers/active</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFollowersActive(String uid, Integer count)
			throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/followers/active.json",
				new PostParameter[] { new PostParameter("uid", uid),
						new PostParameter("count", count.toString()) }));
	}

	/**
	 * ��ȡ��ǰ��¼�û��Ĺ�ע�����ֹ�ע��ָ���û����û��б�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�ID
	 * @return list of users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/friends_chain/followers">friendships/friends_chain/followers</a>
	 * @since JDK 1.5
	 */
	public UserWapper getFriendsChainFollowers(String uid)
			throws WeiboException {
		return User.constructWapperUsers(client.get(
				WeiboConfig.getValue("baseURL")
						+ "friendships/friends_chain/followers.json",
				new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * ��עһ���û�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�ID
	 * @return user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/create">friendships/create</a>
	 * @since JDK 1.5
	 */
	public User createFriendshipsById(String uid) throws WeiboException {
		return new User(client.post(
				WeiboConfig.getValue("baseURL") + "friendships/create.json",
				new PostParameter[] { new PostParameter("uid", uid) })
				.asJSONObject());
	}

	/**
	 * ��עһ���û�
	 * 
	 * @param screen_name
	 *            ��Ҫ��ѯ���û�screen_name
	 * @return user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/create">friendships/create</a>
	 * @since JDK 1.5
	 */
	public User createFriendshipsByName(String screen_name)
			throws WeiboException {
		return new User(client.post(
				WeiboConfig.getValue("baseURL") + "friendships/create.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }).asJSONObject());
	}

	/**
	 * ȡ����עһ���û�
	 * 
	 * @param uid
	 *            ��Ҫ��ѯ���û�ID
	 * @return user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/destroy">friendships/destroy</a>
	 * @since JDK 1.5
	 */
	public User destroyFriendshipsDestroyById(String uid) throws WeiboException {
		return new User(client.post(
				WeiboConfig.getValue("baseURL") + "friendships/destroy.json",
				new PostParameter[] { new PostParameter("uid", uid) })
				.asJSONObject());
	}

	/**
	 * ȡ����עһ���û�
	 * 
	 * @param screen_name
	 *            ��Ҫ��ѯ���û�screen_name
	 * @return user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/friendships/destroy">friendships/destroy</a>
	 * @since JDK 1.5
	 */
	public User destroyFriendshipsDestroyByName(String screen_name)
			throws WeiboException {
		return new User(client.post(
				WeiboConfig.getValue("baseURL") + "friendships/destroy.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }).asJSONObject());
	}
}
