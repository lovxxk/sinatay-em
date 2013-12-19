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
	 * ֪ͨ��� 01 : �ֺ�֪ͨ�� 02 �Զ��潻֪ͨ�� 03 ���������ȡ֪ͨ�� 04 ������Ԥ��ֹ֪ͨ�� 05 ��������ȱ���֪ͨ�� 06
	 * ���汣�ս���ȡ֪ͨ��
	 */
	private String noticeNo;
	//������
	private String policyNo;
	
	// ֪ͨ������
	private String noticeType;
	// ֪ͨ����������
	private String noticeTypeName;
	// ��������
	private String sendDate;
	// ��������
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
