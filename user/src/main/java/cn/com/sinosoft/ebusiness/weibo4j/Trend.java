package cn.com.sinosoft.ebusiness.weibo4j;

import java.util.List;

import cn.com.sinosoft.ebusiness.weibo4j.model.Paging;
import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.Trends;
import cn.com.sinosoft.ebusiness.weibo4j.model.UserTrend;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONObject;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class Trend extends Weibo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 903299515334415487L;

	/*----------------------------����ӿ�----------------------------------------*/
	/**
	 * ��ȡĳ�˵Ļ����б�
	 * 
	 * @param uid
	 *            ��Ҫ��ȡ������û���UID
	 * @return list of the userTrend
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/trends">trends</a>
	 * @since JDK 1.5
	 */
	public List<UserTrend> getTrends(String uid) throws WeiboException {
		return UserTrend
				.constructTrendList(client.get(
						WeiboConfig.getValue("baseURL") + "trends.json",
						new PostParameter[] { new PostParameter("uid", uid) }));
	}

	/**
	 * ��ȡĳ�˵Ļ����б�
	 * 
	 * @param uid
	 *            ��Ҫ��ȡ������û���UID
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1
	 * @return list of the userTrend
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/trends">trends</a>
	 * @since JDK 1.5
	 */
	public List<UserTrend> getTrends(String uid, Paging page)
			throws WeiboException {
		return UserTrend
				.constructTrendList(client.get(
						WeiboConfig.getValue("baseURL") + "trends.json",
						new PostParameter[] { new PostParameter("uid", uid) }, page));
	}

	/**
	 * �жϵ�ǰ�û��Ƿ��עĳ����
	 * 
	 * @param trend_name
	 *            ����ؼ��֣�������URLencode
	 * @return jsonobject
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/is_follow">trends/is_follow</a>
	 * @since JDK 1.5
	 */
	public JSONObject isFollow(String trend_name) throws WeiboException {
			return client.get(WeiboConfig.getValue("baseURL")+ "trends/is_follow.json",
							new PostParameter[] { 
				new PostParameter("trend_name", trend_name) }).asJSONObject();
	}

	/**
	 * �������һСʱ�ڵ����Ż���
	 * 
	 * @param base_app
	 *            �Ƿ�ֻ��ȡ��ǰӦ�õ����ݡ�0Ϊ���������ݣ���1Ϊ�ǣ�����ǰӦ�ã���Ĭ��Ϊ0
	 * @return list of trends
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/hourly">trends/hourly</a>
	 * @since JDK 1.5
	 */
	public List<Trends> getTrendsHourly() throws WeiboException {
		return Trends.constructTrendsList(client.get(
				WeiboConfig.getValue("baseURL") + "trends/hourly.json"));
	}
	
	public List<Trends> getTrendsHourly(Integer base_app) throws WeiboException {
		return Trends.constructTrendsList(client.get(
				WeiboConfig.getValue("baseURL") + "trends/hourly.json",
				new PostParameter[] { new PostParameter("base_app", base_app.toString()) }));
	}

	/**
	 * �������һ���ڵ����Ż���
	 * 
	 * @param base_app
	 *            �Ƿ�ֻ��ȡ��ǰӦ�õ����ݡ�0Ϊ���������ݣ���1Ϊ�ǣ�����ǰӦ�ã���Ĭ��Ϊ0
	 * @return list of trends
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws JSONException
	 * @see <a href="http://open.weibo.com/wiki/2/trends/daily">trends/daily</a>
	 * @since JDK 1.5
	 */
	public List<Trends> getTrendsDaily() throws WeiboException {
		return Trends.constructTrendsList(client.get(
				WeiboConfig.getValue("baseURL") + "trends/daily.json"));
	}
	
	public List<Trends> getTrendsDaily(Integer base_app) throws WeiboException {
		return Trends.constructTrendsList(client.get(
				WeiboConfig.getValue("baseURL") + "trends/daily.json",
				new PostParameter[] { new PostParameter("base_app", base_app
						.toString()) }));
	}

	/**
	 * �������һ���ڵ����Ż���
	 * 
	 * @param base_app
	 *            �Ƿ�ֻ��ȡ��ǰӦ�õ����ݡ�0Ϊ���������ݣ���1Ϊ�ǣ�����ǰӦ�ã���Ĭ��Ϊ0
	 * @return list of trends
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/weekly">trends/weekly</a>
	 * @since JDK 1.5
	 */
	public List<Trends> getTrendsWeekly() throws WeiboException {
		return Trends.constructTrendsList(client.get(
				WeiboConfig.getValue("baseURL") + "trends/weekly.json"));
	}
	
	public List<Trends> getTrendsWeekly(Integer base_app) throws WeiboException {
		return Trends.constructTrendsList(client.get(
				WeiboConfig.getValue("baseURL") + "trends/weekly.json",
				new PostParameter[] { new PostParameter("base_app", base_app.toString()) }));
	}

	/**
	 * ��עĳ����
	 * 
	 * @param trend_name
	 *            Ҫ��ע�Ļ���ؼ��ʡ�
	 * @return UserTrend
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/follow">trends/follow</a>
	 * @since JDK 1.5
	 */
	public UserTrend trendsFollow(String trend_name) throws WeiboException {
		return new UserTrend(client.post(WeiboConfig.getValue("baseURL")
				+ "trends/follow.json",
				new PostParameter[] { new PostParameter("trend_name",
						trend_name) }));
	}

	/**
	 * ȡ����ĳ����Ĺ�ע
	 * 
	 * @param trend_id
	 *            Ҫȡ����ע�Ļ���ID
	 * @return jsonobject
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @throws JSONException
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/trends/destroy">trends/destroy</a>
	 * @since JDK 1.5
	 */
	public JSONObject trendsDestroy(Integer trend_id) throws WeiboException {
			return client.post(WeiboConfig.getValue("baseURL")
							+ "trends/destroy.json",
							new PostParameter[] { new PostParameter("trend_id",trend_id.toString()) }).asJSONObject();
	}

}
