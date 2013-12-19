package cn.com.sinosoft.ebusiness.weibo4j;

import cn.com.sinosoft.ebusiness.weibo4j.model.Paging;
import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.Status;
import cn.com.sinosoft.ebusiness.weibo4j.model.StatusWapper;
import cn.com.sinosoft.ebusiness.weibo4j.model.User;
import cn.com.sinosoft.ebusiness.weibo4j.model.UserWapper;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONArray;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class Suggestion extends Weibo{
/**
	 * 
	 */
	private static final long serialVersionUID = 1861364044145921824L;
	//---------------------------------�Ƽ��ӿ�---------------------------------------------------
	/**
	 * ����ϵͳ�Ƽ��������û��б�
	 * 
	 * @return list of the users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/users/hot">suggestions/users/hot</a>
	 * @since JDK 1.5
	 */
	
	public JSONArray suggestionsUsersHot() throws WeiboException{
		return client.get(WeiboConfig.getValue("baseURL")+"suggestions/users/hot.json").asJSONArray();
	}
	
	public JSONArray suggestionsUsersHot(String category) throws WeiboException{
		return client.get(WeiboConfig.getValue("baseURL")+"suggestions/users/hot.json",new PostParameter[]{
			new PostParameter("category", category)
		}).asJSONArray();
	}
	/**
	 * ��ȡ�û����ܸ���Ȥ���� 
	 * 
	 * @return list of the user's id
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/users/may_interested">suggestions/users/may_interested</a>
	 * @since JDK 1.5
	 */
	public JSONArray suggestionsUsersMayInterested() throws WeiboException{
		return client.get(WeiboConfig.getValue("baseURL")+"suggestions/users/may_interested.json").asJSONArray();
	}
	public JSONArray suggestionsUsersMayInterested(int count,int page) throws WeiboException{
		return client.get(WeiboConfig.getValue("baseURL")+"suggestions/users/may_interested.json",new PostParameter[]{
			new PostParameter("count", count),
			new PostParameter("page", page)
		}).asJSONArray();
	}
	/**
	 * ����һ��΢�������Ƽ����΢���û�
	 * 
	 * @return list of the users
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/users/by_status">suggestions/users/by_status</a>
	 * @since JDK 1.5
	 */
	public UserWapper suggestionsUsersByStatus(String content) throws WeiboException{
		return User.constructWapperUsers(client.get(WeiboConfig.getValue("baseURL")+"suggestions/users/by_status.json",new PostParameter[]{
			new PostParameter("content", content)
		}));
	}
	public UserWapper suggestionsUsersByStatus(String content,int num) throws WeiboException{
		return User.constructWapperUsers(client.get(WeiboConfig.getValue("baseURL")+"suggestions/users/by_status.json",new PostParameter[]{
			new PostParameter("content", content),
			new PostParameter("num", num)
		}));
	}
	/**
	 * ��ȡ΢����ѡ�Ƽ�
	 * 
	 * @return list of the status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/statuses/hot">suggestions/statuses/hot</a>
	 * @since JDK 1.5
	 */
	public StatusWapper suggestionsStatusesHot(int type,int isPic) throws WeiboException{
		return Status.constructWapperStatus(client.get(WeiboConfig.getValue("baseURL")+"suggestions/statuses/hot.json",new PostParameter[]{
			new PostParameter("type", type),
			new PostParameter("is_pic", isPic)
		}));
	}
	public StatusWapper suggestionsStatusesHot(int type,int isPic,Paging page) throws WeiboException{
		return Status.constructWapperStatus(client.get(WeiboConfig.getValue("baseURL")+"suggestions/statuses/hot.json",new PostParameter[]{
			new PostParameter("type", type),
			new PostParameter("is_pic", isPic)
		},page));
	}
	/**
	 * ����ϵͳ�Ƽ��������ղ� 
	 * 
	 * @return list of the status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/favorites/hot">suggestions/favorites/hot</a>
	 * @since JDK 1.5
	 */
	public JSONArray suggestionsFavoritesHot() throws WeiboException{
		return client.get(WeiboConfig.getValue("baseURL")+"suggestions/favorites/hot.json").asJSONArray();
	}
	public JSONArray suggestionsFavoritesHot(int page,int count) throws WeiboException{
		return client.get(WeiboConfig.getValue("baseURL")+"suggestions/favorites/hot.json",new PostParameter[]{
			new PostParameter("page", page),
			new PostParameter("count", count)
		}).asJSONArray();
	}
	/**
	 * ��ĳ�˱�ʶΪ������Ȥ����  
	 * 
	 * @return user
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/suggestions/users/not_interested">suggestions/users/not_interested</a>
	 * @since JDK 1.5
	 */
	public User suggestionsUsersNot_interested(String uid) throws WeiboException{
		return new User(client.post(WeiboConfig.getValue("baseURL")+"suggestions/users/not_interested.json",new PostParameter[]{
			new PostParameter("uid", uid)
		}).asJSONObject());
	}
}
