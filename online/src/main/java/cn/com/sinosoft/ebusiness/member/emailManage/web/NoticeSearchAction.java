package cn.com.sinosoft.ebusiness.member.emailManage.web;

import ins.framework.common.QueryRule;
import ins.framework.web.Struts2Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.businessModule.bindPolicy.domain.BindPolicy;
import cn.com.sinosoft.businessModule.bindPolicy.service.facade.BindPolicyService;
import cn.com.sinosoft.ebusiness.infomanage.domain.NoticeInfo;
import cn.com.sinosoft.ebusiness.service.user.personal.domain.GeUserPersonal;
import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.NoticeCreater;

public class NoticeSearchAction extends Struts2Action {
	private static final long serialVersionUID = -3114998543353935055L;
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description:
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-14
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */

	/**
	 * ֪ͨ��� 01 : �ֺ�֪ͨ�� 02 �Զ��潻֪ͨ�� 03 ���������ȡ֪ͨ�� 04 ������Ԥ��ֹ֪ͨ�� 05 ��������ȱ���֪ͨ�� 06
	 * 
	 * ���汣�ս���ȡ֪ͨ��
	 */
	private String noticeType;
	/**
	 * ��ʼ����
	 */
	private String startDate;
	/**
	 * ��������
	 */
	private String endDate;
	/**
	 * �û���Ϣ
	 */
	private GeUserPersonal customer;

	/**
	 * ��ð󶨱�����Ϣ
	 */
	@Autowired
	private BindPolicyService bindPolicyService;

	/**
	 * ��֪ͨ����Ϣ����
	 * */
	private List<NoticeInfo> noticeInfos = new ArrayList<NoticeInfo>();

	@Autowired
	private NoticeCreater noticeCreater;

	@Autowired
	private WSClientHelper wsclientHelper;
	private String flag;
	private String desc;
	private static Logger log = LoggerFactory
			.getLogger(NoticeSearchAction.class);
	@SuppressWarnings("unchecked")
	public String search() throws Exception {
		customer = (GeUserPersonal) super.getSession().getAttribute(
				"geUserPersonal");
		log.info("�û�����" +customer.getUserName()+"��ѯ���Ӻ���֪ͨ��......");
		QueryRule queryRule = QueryRule.getInstance();
		queryRule.addEqual("CUSTOMERID", customer.getUserID());
		List<BindPolicy> listBindPolicy = bindPolicyService
				.findPolicyByQueryRule(queryRule);
		Map<String, Object> params = new HashMap<String, Object>();

		List<String> contnosList = new ArrayList<String>();
		for (BindPolicy bindPolicy : listBindPolicy) {
			contnosList.add(bindPolicy.getPolicySerialNumber());
		}
		params.put("policyNos", contnosList);
		// ���汣������
		if (startDate != null || !startDate.endsWith("")) {
			params.put("startDate", startDate);
		}
		if (endDate != null || !endDate.endsWith("")) {
			params.put("endDate", endDate);
		}
		if (noticeType != null || !noticeType.endsWith("")) {
			params.put("noticeType", noticeType);
		}

		Document docRequest;
		Document docResponse;
		String sRequest;
		String sResponse;
		Map<String, Object> result;

		// �������������
		docRequest = noticeCreater.createXml(params);
		sRequest = wsclientHelper.doc2String(docRequest);
		sResponse = wsclientHelper.submitGBK(sRequest);
		docResponse = wsclientHelper.string2Doc(sResponse);
		result = noticeCreater.Xml2Object(docResponse);
		flag = (String) result.get("flag");
		desc = (String) result.get("desc");
		// ��ý������ĵ�֪ͨ��
	
		noticeInfos = (List<NoticeInfo>) result.get("noticeInfos");
		if(noticeInfos!=null){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public GeUserPersonal getCustomer() {
		return customer;
	}

	public void setCustomer(GeUserPersonal customer) {
		this.customer = customer;
	}

	public WSClientHelper getWsclientHelper() {
		return wsclientHelper;
	}

	public void setWsclientHelper(WSClientHelper wsclientHelper) {
		this.wsclientHelper = wsclientHelper;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<NoticeInfo> getNoticeInfos() {
		return noticeInfos;
	}

	public void setNoticeInfos(List<NoticeInfo> noticeInfos) {
		this.noticeInfos = noticeInfos;
	}

}
