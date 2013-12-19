package cn.com.sinosoft.ebusiness.weibo4j;

import cn.com.sinosoft.ebusiness.weibo4j.model.Comment;
import cn.com.sinosoft.ebusiness.weibo4j.model.CommentWapper;
import cn.com.sinosoft.ebusiness.weibo4j.model.Paging;
import cn.com.sinosoft.ebusiness.weibo4j.model.PostParameter;
import cn.com.sinosoft.ebusiness.weibo4j.model.WeiboException;
import cn.com.sinosoft.ebusiness.weibo4j.org.json.JSONArray;
import cn.com.sinosoft.ebusiness.weibo4j.util.WeiboConfig;

public class Comments extends Weibo{

	private static final long serialVersionUID = 3321231200237418256L;

	/**
	 * ����΢��ID����ĳ��΢���������б�
	 * 
	 * @param id
	 *            ��Ҫ��ѯ��΢��ID
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/show">comments/show</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentById(String id) throws WeiboException {
		return Comment.constructWapperComments(client.get(
				WeiboConfig.getValue("baseURL") + "comments/show.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * ����΢��ID����ĳ��΢���������б�
	 * 
	 * @param id
	 *            ��Ҫ��ѯ��΢��ID
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ50��
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1��
	 * @param filter_by_author
	 *            ����ɸѡ���ͣ�0��ȫ����1���ҹ�ע���ˡ�2��İ���ˣ�Ĭ��Ϊ0��
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/show">comments/show</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentById(String id, Paging page,
			Integer filter_by_author) throws WeiboException {
		return Comment.constructWapperComments(client.get(
				WeiboConfig.getValue("baseURL") + "comments/show.json",
				new PostParameter[] {
						new PostParameter("id", id),
						new PostParameter("filter_by_author", filter_by_author.toString()) }, page));
	}

	/**
	 * ��ȡ��ǰ��¼�û��������������б�
	 * 
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/by_me">comments/by_me</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentByMe() throws WeiboException {
		return Comment.constructWapperComments(client.get(WeiboConfig
				.getValue("baseURL") + "comments/by_me.json"));
	}

	/**
	 * ��ȡ��ǰ��¼�û��������������б�
	 * 
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ50
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1
	 * @param filter_by_source
	 *            ��Դɸѡ���ͣ�0��ȫ����1������΢�������ۡ�2������΢Ⱥ�����ۣ�Ĭ��Ϊ0
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/by_me">comments/by_me</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentByMe(Paging page, Integer filter_by_source)
			throws WeiboException {
		return Comment.constructWapperComments(client.get(
				WeiboConfig.getValue("baseURL") + "comments/by_me.json",
				new PostParameter[] { new PostParameter("filter_by_author",
						filter_by_source.toString()) }, page));
	}

	/**
	 * ��ȡ��ǰ��¼�û������յ��������б�
	 * 
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/to_me">comments/to_me</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentToMe() throws WeiboException {
		return Comment.constructWapperComments(client.get(WeiboConfig
				.getValue("baseURL") + "comments/to_me.json"));
	}

	/**
	 * ��ȡ��ǰ��¼�û������յ��������б�
	 * 
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ50��
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1��
	 * @param filter_by_author
	 *            ����ɸѡ���ͣ�0��ȫ����1���ҹ�ע���ˡ�2��İ���ˣ�Ĭ��Ϊ0��
	 * @param filter_by_source
	 *            ��Դɸѡ���ͣ�0��ȫ����1������΢�������ۡ�2������΢Ⱥ�����ۣ�Ĭ��Ϊ0��
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/to_me">comments/to_me</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentToMe(Paging page, Integer filter_by_source,
			Integer filter_by_author) throws WeiboException {
		return Comment.constructWapperComments(client.get(
				WeiboConfig.getValue("baseURL") + "comments/to_me.json",
				new PostParameter[] {
						new PostParameter("filter_by_source", filter_by_source
								.toString()),
						new PostParameter("filter_by_author", filter_by_author
								.toString()) }, page));
	}

	/**
	 * ��ȡ��ǰ��¼�û����������۰������յ����뷢����
	 * 
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/timeline">comments/timeline</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentTimeline() throws WeiboException {
		return Comment.constructWapperComments(client.get(WeiboConfig
				.getValue("baseURL") + "comments/timeline.json"));
	}

	/**
	 * ��ȡ��ǰ��¼�û����������۰������յ����뷢����
	 * 
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ50��
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1��
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/timeline">comments/timeline</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentTimeline(Paging page) throws WeiboException {
		return Comment.constructWapperComments(client.get(
				WeiboConfig.getValue("baseURL") + "comments/timeline.json",
				null, page));
	}

	/**
	 * ��ȡ���µ��ᵽ��ǰ��¼�û������ۣ���@�ҵ�����
	 * 
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/mentions">comments/mentions</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentMentions() throws WeiboException {
		return Comment.constructWapperComments(client.get(WeiboConfig
				.getValue("baseURL") + "comments/mentions.json"));
	}

	/**
	 * ��ȡ���µ��ᵽ��ǰ��¼�û������ۣ���@�ҵ�����
	 * 
	 * @param count
	 *            ��ҳ���صļ�¼������Ĭ��Ϊ50��
	 * @param page
	 *            ���ؽ����ҳ�룬Ĭ��Ϊ1��
	 * @param filter_by_author
	 *            ����ɸѡ���ͣ�0��ȫ����1���ҹ�ע���ˡ�2��İ���ˣ�Ĭ��Ϊ0��
	 * @param filter_by_source
	 *            ��Դɸѡ���ͣ�0��ȫ����1������΢�������ۡ�2������΢Ⱥ�����ۣ�Ĭ��Ϊ0��
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/mentions">comments/mentions</a>
	 * @since JDK 1.5
	 */
	public CommentWapper getCommentMentions(Paging page,
			Integer filter_by_source, Integer filter_by_author)
			throws WeiboException {
		return Comment.constructWapperComments(client.get(
				WeiboConfig.getValue("baseURL") + "comments/mentions.json",
				new PostParameter[] {
						new PostParameter("filter_by_source", filter_by_source
								.toString()),
						new PostParameter("filter_by_author", filter_by_author
								.toString()) }, page));
	}

	/**
	 * ��������ID��������������Ϣ
	 * 
	 * @param cids
	 *            ��Ҫ��ѯ����������ID���ð�Ƕ��ŷָ������50
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/show_batch">comments/show_batch</a>
	 * @since JDK 1.5
	 */
	public JSONArray getCommentShowBatch(String cids) throws WeiboException {
		return client.get(
				WeiboConfig.getValue("baseURL") + "comments/show_batch.json",
				new PostParameter[] { new PostParameter("cids", cids) }).asJSONArray();
	}

	/**
	 * ��һ��΢����������
	 * 
	 * @param comment
	 *            �������ݣ�������URLencode�����ݲ�����140������
	 * @param id
	 *            ��Ҫ���۵�΢��ID
	 * @return Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/create">comments/create</a>
	 * @since JDK 1.5
	 */
	public Comment createComment(String comment, String id)
			throws WeiboException {
		return new Comment(client.post(WeiboConfig.getValue("baseURL")
				+ "comments/create.json", new PostParameter[] {
				new PostParameter("comment", comment),
				new PostParameter("id", id) }));
	}

	/**
	 * ��һ��΢����������
	 * 
	 * @param comment
	 *            �������ݣ�������URLencode�����ݲ�����140������
	 * @param id
	 *            ��Ҫ���۵�΢��ID
	 * @param comment_ori
	 *            ������ת��΢��ʱ���Ƿ����۸�ԭ΢����0����1���ǣ�Ĭ��Ϊ0��
	 * @return Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/create">comments/create</a>
	 * @since JDK 1.5
	 */
	public Comment createComment(String comment, String id, Integer comment_ori)
			throws WeiboException {
		return new Comment(client.post(WeiboConfig.getValue("baseURL")
				+ "comments/create.json", new PostParameter[] {
				new PostParameter("comment", comment),
				new PostParameter("id", id),
				new PostParameter("comment_ori", comment_ori.toString()) }));
	}

	/**
	 * �ظ�һ������ 
	 * @param comment �������ݣ�������URLencode�����ݲ�����140������
	 * 
	 * @param cid
	 *            ��Ҫ�ظ�������ID
	 * @param id
	 *            ��Ҫ���۵�΢��ID
	 * @return Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/reply">comments/reply</a>
	 * @since JDK 1.5
	 */
	public Comment replyComment(String cid, String id, String comment)
			throws WeiboException {
		return new Comment(client.post(WeiboConfig.getValue("baseURL")
				+ "comments/reply.json", new PostParameter[] {
				new PostParameter("cid", cid), 
				new PostParameter("id", id),
				new PostParameter("comment", comment) }));
	}

	/**
	 * �ظ�һ������
	 * 
	 * @param comment
	 *            �������ݣ�������URLencode�����ݲ�����140������
	 * @param cid
	 *            ��Ҫ�ظ�������ID
	 * @param id
	 *            ��Ҫ���۵�΢��ID
	 * @param without_mention
	 *            �ظ����Ƿ��Զ����롰�ظ�@�û�������0���ǡ�1����Ĭ��Ϊ0��
	 * @param comment_ori
	 *            ������ת��΢��ʱ���Ƿ����۸�ԭ΢����0����1���ǣ�Ĭ��Ϊ0��
	 * @return Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/reply">comments/reply</a>
	 * @since JDK 1.5
	 */
	public Comment replyComment(String cid, String id, String comment,
			Integer without_mention, Integer comment_ori) throws WeiboException {
		return new Comment(
				client.post(
						WeiboConfig.getValue("baseURL") + "comments/reply.json",
						new PostParameter[] {
								new PostParameter("comment", comment),
								new PostParameter("id", id),
								new PostParameter("cid", cid),
								new PostParameter("without_mention",without_mention.toString()),
								new PostParameter("comment_ori", comment_ori.toString()) }));
	}

	/**
	 * ɾ��һ������
	 * 
	 * @param cid
	 *            Ҫɾ��������ID��ֻ��ɾ����¼�û��Լ�����������
	 * @return Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/destroy">comments/destroy</a>
	 * @since JDK 1.5
	 */
	public Comment destroyComment(String cid) throws WeiboException {
		return new Comment(client.post(WeiboConfig.getValue("baseURL")
				+ "comments/destroy.json",
				new PostParameter[] { new PostParameter("cid", cid) }));
	}

	/**
	 * ��������ID����ɾ������
	 * 
	 * @param ids
	 *            ��Ҫɾ��������ID���ð�Ƕ��Ÿ��������20��
	 * @return list of Comment
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/comments/destroy_batch">comments/destroy_batch</a>
	 * @since JDK 1.5
	 */
	public JSONArray destoryCommentBatch(String cids) throws WeiboException {
		return client.post(
						WeiboConfig.getValue("baseURL")
								+ "comments/destroy_batch.json",
						new PostParameter[] { new PostParameter("cids", cids) }).asJSONArray();
	}
}
