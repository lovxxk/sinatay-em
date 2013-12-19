package cn.com.sinosoft.portalModule.transport.sinatay;

import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.portalModule.transport.dhf100.QueryData;

/**
 * TranResponse�ڵ�DTO
 *
 */
public class TranResponse implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private QueryData queryData;
	
	/** ���ر�־ CD25 ���ر�־ 0��ʧ�� 1���ɹ� */
	private String flag;
	
	/** ������Ϣ FlagΪ0��������Ϣ */
	private String desc;
	
	private String detailArray;
	/**  */
	private LCCont lcCont;
	
	/** Ӱ���ļ��� */
	private Integer attachmentCount;
	
	/** Ӱ���ļ� */
	private List<Attachment> attachments = new ArrayList<Attachment>(0);

	/** ���֤У�� */
	private List<IdCardCheck> idCardChecks = new ArrayList<IdCardCheck>(0);

	private SMSResult smsResult = new SMSResult();
	
	private String outTradeNo;
	
	private String url;
	
	public QueryData getQueryData() {
		return queryData;
	}

	public void setQueryData(QueryData queryData) {
		this.queryData = queryData;
	}

	/**
	 * ���� flag �� getter ����
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * ���� flag �� setter ����
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * ���� desc �� getter ����
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * ���� desc �� setter ����
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDetailArray() {
		return detailArray;
	}

	public void setDetailArray(String detailArray) {
		this.detailArray = detailArray;
	}

	/**
	 * ���� lcCont �� getter ����
	 * @return the lcCont
	 */
	public LCCont getLcCont() {
		return lcCont;
	}

	/**
	 * ���� lcCont �� setter ����
	 * @param lcCont the lcCont to set
	 */
	public void setLcCont(LCCont lcCont) {
		this.lcCont = lcCont;
	}

	/**
	 * ���� attachmentCount �� getter ����
	 * @return the attachmentCount
	 */
	public Integer getAttachmentCount() {
		return attachmentCount;
	}

	/**
	 * ���� attachmentCount �� setter ����
	 * @param attachmentCount the attachmentCount to set
	 */
	public void setAttachmentCount(Integer attachmentCount) {
		this.attachmentCount = attachmentCount;
	}

	/**
	 * ���� attachments �� getter ����
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * ���� attachments �� setter ����
	 * @param attachments the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * ���� idCardChecks �� getter ����
	 * @return the idCardChecks
	 */
	public List<IdCardCheck> getIdCardChecks() {
		return idCardChecks;
	}

	/**
	 * ���� idCardChecks �� setter ����
	 * @param idCardChecks the idCardChecks to set
	 */
	public void setIdCardChecks(List<IdCardCheck> idCardChecks) {
		this.idCardChecks = idCardChecks;
	}

	public SMSResult getSmsResult() {
		return smsResult;
	}

	public void setSmsResult(SMSResult smsResult) {
		this.smsResult = smsResult;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
