package cn.com.sinosoft.ebusiness.infomanage.domain;

import java.io.Serializable;

public class NoticeInfo implements Serializable,Comparable<NoticeInfo>{

	private static final long serialVersionUID = -3185848756407047438L;
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
	 * 通知书号 01 : 分红通知书 02 自动垫交通知书 03 增额红利领取通知书 04 万能险预中止通知书 05 万能险年度报告通知书 06
	 * 生存保险金领取通知书
	 */
	private String noticeNo;
	//保单号
	private String policyNo;
	
	// 通知书类型
	private String noticeType;
	// 通知书类型名称
	private String noticeTypeName;
	// 发送日期
	private String sendDate;
	// 险种名称
	private String riskName;

	public String getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	@Override
	public int compareTo(NoticeInfo o) {
		return o.getSendDate().compareTo(this.getSendDate());
	}

	public String getNoticeTypeName() {
		return noticeTypeName;
	}

	public void setNoticeTypeName(String noticeTypeName) {
		this.noticeTypeName = noticeTypeName;
	}
	

}
