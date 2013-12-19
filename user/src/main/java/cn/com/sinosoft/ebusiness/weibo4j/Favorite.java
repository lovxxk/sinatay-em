package cn.com.sinosoft.ebusiness.weibo4j;

import java.util.List;

import cn.com.sinosoft.ebusiness.weibo4j.model.Favorites;
import cn.com.sinosoft.ebusiness.weibo4j.model.FavoritesTag;
import cn.com.sinosoft.ebusiness.weibo4j.model.Paging;
import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.Tag;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONObject;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class Favorite extends Weibo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2298934944028795652L;

	/*----------------------------�ղؽӿ�----------------------------------------*/
	/**
	 * ��ȡ��ǰ��¼�û����ղ��б�
	 * 
	 * @return list of the Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/favorites">favorites</a>
	 * @since JDK 1.5
	 */
	public List<Favorites> getFavorites() throws WeiboException {
		return Favorites.constructFavorites(client.get(WeiboConfig
				.getValue("baseURL") + "favorites.json"));
	}

	/**
	 * ��ȡ��ǰ��¼�û����ղ��б�
	 * 
	 * @param page
	 *            ��count
	 * @return list of the Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/favorites">favorites</a>
	 * @since JDK 1.5
	 */
	public List<Favorites> getFavorites(Paging page) throws WeiboException {
		return Favorites.constructFavorites(client.get(
						WeiboConfig.getValue("baseURL") + "favorites.json",
						null, page));
	}
	/**
	 * ��ȡ��ǰ��¼�û����ղ��б�ID
	 * 
	 * @return list of the Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/favorites">favorites</a>
	 * @since JDK 1.5
	 */
	public JSONObject getFavoritesIds() throws WeiboException {
		return client.get(WeiboConfig
				.getValue("baseURL") + "favorites/ids.json").asJSONObject();
	}
	/**
	 * ��ȡ��ǰ��¼�û����ղ��б�ID
	 * 
	 * @return list of the Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/favorites">favorites</a>
	 * @since JDK 1.5
	 */
	public JSONObject getFavoritesIds(Paging page) throws WeiboException {
		return client.get(WeiboConfig
				.getValue("baseURL") + "favorites/ids.json",null,page).asJSONObject();
	}
	/**
	 * �����ղ�ID��ȡָ�����ղ���Ϣ
	 * 
	 * @param id
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/show">favorites/show</a>
	 * @since JDK 1.5
	 */
	public Favorites showFavorites(String id) throws WeiboException {
		return new Favorites(client.get(WeiboConfig.getValue("baseURL")
				+ "favorites/show.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * ���ݱ�ǩ��ȡ��ǰ��¼�û��ñ�ǩ�µ��ղ��б�
	 * 
	 * @param tid
	 * @return list of the favorite Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/by_tags">favorites/by_tags</a>
	 * @since JDK 1.5
	 */
	public List<Favorites> getFavoritesByTags(String tid) throws WeiboException {
		return Favorites.constructFavorites(client.get(
				WeiboConfig.getValue("baseURL") + "favorites/by_tags.json",
				new PostParameter[] { new PostParameter("tid", tid) }));
	}

	/**
	 * ���ݱ�ǩ��ȡ��ǰ��¼�û��ñ�ǩ�µ��ղ��б�
	 * 
	 * @param tid
	 * @param page
	 * @return list of the favorite Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/show">favorites/show</a>
	 * @since JDK 1.5
	 */
	public List<Favorites> getFavoritesByTags(String tid, Paging page)
			throws WeiboException {
		return Favorites.constructFavorites(client.get(
				WeiboConfig.getValue("baseURL") + "favorites/by_tags.json",
				new PostParameter[] { new PostParameter("tid", tid) }, page));
	}

	/**
	 * ��ȡ��ǰ��¼�û����ղر�ǩ�б�
	 * 
	 * @param page
	 * @return list of the favorite tags
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/tags">favorites/tags</a>
	 * @since JDK 1.5
	 */
	public List<FavoritesTag> getFavoritesTags() throws WeiboException {
		return Tag.constructTag(client.get(WeiboConfig
				.getValue("baseURL") + "favorites/tags.json"));

	}

	/**
	 * ���һ��΢�����ղ���
	 * 
	 * @param Ҫ�ղص�΢��ID
	 *            ��
	 * @return Favorites status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/create">favorites/create</a>
	 * @since JDK 1.5
	 */
	public Favorites createFavorites(String id) throws WeiboException {
		return new Favorites(client.post(WeiboConfig.getValue("baseURL")
				+ "favorites/create.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * ȡ���ղ�һ��΢��
	 * 
	 * @param Ҫȡ���ղص�΢��ID
	 *            ��
	 * @return Favorites status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/destroy">favorites/destroy</a>
	 * @since JDK 1.5
	 */
	public Favorites destroyFavorites(String id) throws WeiboException {
		return new Favorites(client.post(WeiboConfig.getValue("baseURL")
				+ "favorites/destroy.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * ����ɾ���ղ�
	 * 
	 * @param ids
	 *            Ҫȡ���ղص��ղ�ID���ð�Ƕ��ŷָ�����಻����10����
	 * @return destroy list of Favorites status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/destroy_batch">favorites/destroy_batch</a>
	 * @since JDK 1.5
	 */
	public Boolean destroyFavoritesBatch(String ids) throws WeiboException {
		try {
			return client
					.post(WeiboConfig.getValue("baseURL")
							+ "favorites/destroy_batch.json",
							new PostParameter[] { new PostParameter("ids", ids) })
					.asJSONObject().getBoolean("result");
		} catch (JSONException e) {
			throw new WeiboException(e);
		}
	}

	/**
	 * ����һ���ղص��ղر�ǩ
	 * 
	 * @param id
	 *            Ҫ��Ҫ���µ��ղ�ID
	 * @return update tag of Favorites status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/tags/update">favorites/tags/update</a>
	 * @since JDK 1.5
	 */
	public Favorites updateFavoritesTags(String id) throws WeiboException {
		return new Favorites(client.post(WeiboConfig.getValue("baseURL")
				+ "favorites/tags/update.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * ����һ���ղص��ղر�ǩ
	 * 
	 * @param id
	 *            Ҫ��Ҫ���µ��ղ�ID
	 * @param tags
	 *            ��Ҫ���µı�ǩ���ݣ�������URLencode���ð�Ƕ��ŷָ�����಻����2����
	 * @return update tag of Favorites status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/tags/update">favorites/tags/update</a>
	 * @since JDK 1.5
	 */
	public Favorites updateFavoritesTags(String id, String tags)
			throws WeiboException {
		return new Favorites(client.post(WeiboConfig.getValue("baseURL")
				+ "favorites/tags/update.json", new PostParameter[] {
				new PostParameter("id", id), new PostParameter("tags", tags) }));
	}

	/**
	 * ���µ�ǰ��¼�û������ղ��µ�ָ����ǩ
	 * 
	 * @param id
	 *            ��Ҫ���µı�ǩID��
	 * @return update tags of Favorites status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/tags/update_batch">favorites/tags/update_batch</a>
	 * @since JDK 1.5
	 */
	public JSONObject updateFavoritesTagsBatch(String tid, String tag)
			throws WeiboException {
		return client.post(
				WeiboConfig.getValue("baseURL")
						+ "favorites/tags/update_batch.json",
				new PostParameter[] { new PostParameter("tid", tid),
						new PostParameter("tag", tag) }).asJSONObject();
	}

	/**
	 * ɾ����ǰ��¼�û������ղ��µ�ָ����ǩ
	 * 
	 * @param id
	 *            ��Ҫɾ���ı�ǩID����
	 * @return destroy tags of Favorites status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/favorites/tags/destroy_batch">favorites/tags/destroy_batch</a>
	 * @since JDK 1.5
	 */
	public Boolean destroyFavoritesTagsBatch(String ids) throws WeiboException {
		try {
			return client
					.post(WeiboConfig.getValue("baseURL")
							+ "favorites/destroy_batch.json",
							new PostParameter[] { new PostParameter("ids", ids) })
					.asJSONObject().getBoolean("result");
		} catch (JSONException e) {
			throw new WeiboException(e);
		}
	}
}
