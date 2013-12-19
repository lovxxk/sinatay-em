package cn.com.sinosoft.ebusiness.member.emailManage.web;

import ins.framework.web.Struts2Action;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.sinosoft.ebusiness.tools.WSClientHelper;
import cn.com.sinosoft.ebusiness.xmltransfer.DownloadNoticeCreater;

public class CheckDownNoticeAction extends Struts2Action {
	private static final long serialVersionUID = 5090311441473538274L;
	/**
	 * @ProjectName:
	 * @Package:
	 * @ClassName:
	 * @Description: �����Ƿ��ṩ���û�������Ϣ������������ʾʧ����Ϣ��
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-16
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	/**
	 * ��־��Ϣ
	 */
	private static Logger log = LoggerFactory
			.getLogger(CheckDownNoticeAction.class);
	/**
	 * ������
	 */
	private String policyNo = "";
	/**
	 * ֪ͨ���
	 */
	private String noticeNo = "";
	/**
	 * ��ʼ����
	 */
	private String startDate = "";
	/**
	 * ��������
	 */
	private String endDate = "";
	@Autowired
	private DownloadNoticeCreater downloadNoticeCreater;
	@Autowired
	private WSClientHelper wsclientHelper;
	private String flag;
	private String desc;
	private String url;

	/**
	 * ҵ��У�鱨����Ϣ ����û��Ƿ��ܹ������ļ����������أ���ʾ֪ͨ�鲻�� ����ԭ��
	 * 
	 * @return
	 */

	public String checkDownNotice() throws Exception {
		
		log.info("ϵͳ����CheckDownNoticeAction����checkDownNotice��֤�û��Ƿ�������֪ͨ�飡");
		
		Document docRequest;
		Document docResponse;
		String sRequest;
		String sResponse;
		Map<String, String> result;
		
		// ���ձ�����Ϣ
		Map<String, Object> map = new HashMap<String, Object>();
		if (noticeNo != null || !noticeNo.equals("")) {
			map.put("noticeNo", noticeNo);
		}
		if (policyNo != null || !policyNo.equals("")) {
			map.put("policyNo", policyNo);
		}
		if (startDate != null || !startDate.equals("")) {
			map.put("startDate", startDate);

		}
		if (endDate != null || !endDate.equals("")) {
			map.put("endDate", endDate);

		}

		docRequest = downloadNoticeCreater.createXml(map);
		sRequest = wsclientHelper.doc2String(docRequest);
		sResponse = wsclientHelper.submitGBK(sRequest);
		docResponse = wsclientHelper.string2Doc(sResponse);
		result = downloadNoticeCreater.Xml2Object(docResponse);

		flag = result.get("flag");
		desc = result.get("desc");
		//����ļ���
		if (flag.equals("1")) {
			url = result.get("url");
			int index = url.lastIndexOf("/");
			if (index > 0) {
				url = url.substring(index + 1);
			}
		}

		return SUCCESS;

	}

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
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

}
