package cn.com.sinosoft.ebusiness.weibo4j;

import java.util.List;

import cn.com.sinosoft.ebusiness.weibo4j.model.Paging;
import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.Tag;
import cn.com.sinosoft.ebusiness.weibo4j.model.TagWapper;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONArray;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONObject;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class Tags extends Weibo{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7047254100483792467L;

	/*----------------------------��ǩ�ӿ�----------------------------------------*/
	/**
	 * ����ָ���û��ı�ǩ�б�
	 * 
	 * @param uid
	 *            Ҫ��ȡ�ı�ǩ�б��������û�ID
	 * @return list of the tags
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/tags">tags</a>
	 * @since JDK 1.5
	 */
	public List<Tag> getTags(String uid) throws WeiboException {
		return Tag.constructTags(client.get(WeiboConfig.getValue("baseURL")
						+ "tags.json", new PostParameter[] { new PostParameter(
						"uid", uid) }));
	}

	/**
	 * ����ָ���û��ı�ǩ�б�
	 * 
	 * @param uid
	 *            Ҫ��ȡ�ı�ǩ�б��������û�ID
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1
	 * @return list of the tags
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/tags">tags</a>
	 * @since JDK 1.5
	 */
	public List<Tag> getTags(String uid, Paging page) throws WeiboException {
		return Tag
				.constructTags(client.get(WeiboConfig.getValue("baseURL")
						+ "tags.json", new PostParameter[] { 
					new PostParameter("uid", uid) }, page));
	}

	/**
	 * ������ȡ�û��ı�ǩ�б�
	 * 
	 * @param uids
	 *            Ҫ��ȡ��ǩ���û�ID�����20�����ŷָ�
	 * @return list of the tags
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/tags/tags_batch">tags/tags_batch</a>
	 * @since JDK 1.5
	 */
	public TagWapper getTagsBatch(String uids) throws WeiboException {
		return Tag.constructTagWapper(client.get(
				WeiboConfig.getValue("baseURL") + "tags/tags_batch.json",
				new PostParameter[] { new PostParameter("uids", uids) }));
	}

	/**
	 * ��ȡϵͳ�Ƽ��ı�ǩ�б�
	 * 
	 * @return list of the tags
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/tags/suggestions">tags/suggestions</a>
	 * @since JDK 1.5
	 */

	public List<Tag> getTagsSuggestions() throws WeiboException {
		return Tag.constructTags(client.get(WeiboConfig
				.getValue("baseURL") + "tags/suggestions.json"));
	}

	/**
	 * Ϊ��ǰ��¼�û�����µ��û���ǩ
	 * 
	 * @param tags
	 *            Ҫ������һ���ǩ���ð�Ƕ��Ÿ�����ÿ����ǩ�ĳ��Ȳ��ɳ���7�����֣�14������ַ�
	 * @return tag_id
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/tags/create">tags/create</a>
	 * @since JDK 1.5
	 */
	public JSONArray createTags(String tags) throws WeiboException {
		return client.post(WeiboConfig.getValue("baseURL") + "tags/create.json",
				new PostParameter[] { new PostParameter("tags", tags) }).asJSONArray();
	}

	/**
	 * ɾ��һ���û���ǩ
	 * 
	 * @param tag_id
	 *            Ҫɾ���ı�ǩID
	 * @return
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws JSONException
	 * @see <a href="http://open.weibo.com/wiki/2/tags/destroy">tags/destroy</a>
	 * @since JDK 1.5
	 */
	public JSONObject destoryTag(Integer tag_id) throws WeiboException {
			return client.post(WeiboConfig.getValue("baseURL") + "tags/destroy.json",
							new PostParameter[] { new PostParameter("tag_id",
									tag_id.toString()) }).asJSONObject();
	}

	/**
	 * ����ɾ��һ���ǩ
	 * 
	 * @param ids
	 *            Ҫɾ����һ���ǩID���԰�Ƕ��Ÿ�����һ������ύ10��ID
	 * @return tag_id
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/tags/destroy_batch">tags/destroy_batch</a>
	 * @since JDK 1.5
	 */
	public List<Tag> destroyTagsBatch(String ids) throws WeiboException {
		return Tag.constructTags(client.post(
				WeiboConfig.getValue("baseURL") + "tags/destroy_batch.json",
				new PostParameter[] { new PostParameter("ids", ids) }));
	}
}
