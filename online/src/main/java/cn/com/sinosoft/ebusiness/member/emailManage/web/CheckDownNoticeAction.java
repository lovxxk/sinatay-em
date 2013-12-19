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
	 * @Description: 请求是否提供给用户下载信息，不给下载提示失败信息。
	 * @Author: jack_xiao
	 * @CreateDate: 2013-9-16
	 * @UpdateUser:
	 * @UpdateDate:
	 * @UpdateRemark:
	 * @Version: v1.0
	 * 
	 */
	/**
	 * 日志信息
	 */
	private static Logger log = LoggerFactory
			.getLogger(CheckDownNoticeAction.class);
	/**
	 * 保单号
	 */
	private String policyNo = "";
	/**
	 * 通知书号
	 */
	private String noticeNo = "";
	/**
	 * 开始日期
	 */
	private String startDate = "";
	/**
	 * 结束日期
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
	 * 业务：校验报文信息 检测用户是否能够下载文件，不能下载，提示通知书不能 下载原因
	 * 
	 * @return
	 */

	public String checkDownNotice() throws Exception {
		
		log.info("系统调用CheckDownNoticeAction类中checkDownNotice验证用户是否能下载通知书！");
		
		Document docRequest;
		Document docResponse;
		String sRequest;
		String sResponse;
		Map<String, String> result;
		
		// 接收报文信息
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
		//获得文件名
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
