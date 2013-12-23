package cn.com.sinosoft.portalModule.transport.sinatay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.sinosoft.portalModule.transport.dhf100.QueryData;

/**
 * TranRequest�ڵ�DTO
 * 
 */
public class TranRequest implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// ֧��ʱ��ѯͨ��tradeno��ѯ֧�����
	private String tradeNo;

	private QueryData queryData;

	/**  */
	private LCCont lcCont;

	/**
	 * Ͷ������
	 */
	private String proposalContNo;

	/**
	 * Ӱ���ļ���
	 */
	private Integer attachmentCount;

	/**
	 * Ӱ���ļ�
	 */
	private List<Attachment> attachments = new ArrayList<Attachment>(0);

	/** ���֤У������ code��ʾλ1: ������������˲� 2: ���������˲�ȶ� 3: ������������˲鼰ͬסַ��Ա����˲� */
	private String idCardCheckCode;

	/** ���֤У�� */
	private List<IdCardCheck> idCardChecks = new ArrayList<IdCardCheck>(0);

	/** ���֤У�� */
	private List<IdCardChecks> idCardCheckes = new ArrayList<IdCardChecks>(0);

	/** �ͷ�ϵͳ֪ͨ������ */
	private Integer serviceInfoCount;

	/** �ͷ�ϵͳ֪ͨ���� */
	private List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>(0);

	/** �ֻ����� */
	private List<SMS> messages = new ArrayList<SMS>(0);

	private String billDate;
	private int totalCount;
	private BigDecimal totalPrem;
	private List<ReconciliationDetail> detailList;
	private String orderId;
	
	private PayInfo payInfo;
	
	public PayInfo getPayInfo() {
		return payInfo;
	}
<<<<<<< HEAD
=======

	public void setPayInfo(PayInfo payInfo) {
		this.payInfo = payInfo;
	}
>>>>>>> branch 'master' of https://github.com/lovxxk/sinatay-em.git

	public void setPayInfo(PayInfo payInfo) {
		this.payInfo = payInfo;
	}
	//�ֶο�ʼ����
	private Date qStartDate;
	//�ֶν�������
	private Date qEndDate;
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getTotalPrem() {
		return totalPrem;
	}

	public void setTotalPrem(BigDecimal totalPrem) {
		this.totalPrem = totalPrem;
	}

	public List<ReconciliationDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ReconciliationDetail> detailList) {
		this.detailList = detailList;
	}

	public QueryData getQueryData() {
		return queryData;
	}

	public void setQueryData(QueryData queryData) {
		this.queryData = queryData;
	}

	public List<IdCardChecks> getIdCardCheckes() {
		return idCardCheckes;
	}

	public void setIdCardCheckes(List<IdCardChecks> idCardCheckes) {
		this.idCardCheckes = idCardCheckes;
	}

	/**
	 * ���� lcCont �� getter ����
	 * 
	 * @return the lcCont
	 */
	public LCCont getLcCont() {
		return lcCont;
	}

	/**
	 * ���� lcCont �� setter ����
	 * 
	 * @param lcCont
	 *            the lcCont to set
	 */
	public void setLcCont(LCCont lcCont) {
		this.lcCont = lcCont;
	}

	/**
	 * ���� proposalContNo �� getter ����
	 * 
	 * @return the proposalContNo
	 */
	public String getProposalContNo() {
		return proposalContNo;
	}

	/**
	 * ���� proposalContNo �� setter ����
	 * 
	 * @param proposalContNo
	 *            the proposalContNo to set
	 */
	public void setProposalContNo(String proposalContNo) {
		this.proposalContNo = proposalContNo;
	}

	/**
	 * ���� attachmentCount �� getter ����
	 * 
	 * @return the attachmentCount
	 */
	public Integer getAttachmentCount() {
		return attachmentCount;
	}

	/**
	 * ���� attachmentCount �� setter ����
	 * 
	 * @param attachmentCount
	 *            the attachmentCount to set
	 */
	public void setAttachmentCount(Integer attachmentCount) {
		this.attachmentCount = attachmentCount;
	}

	/**
	 * ���� attachments �� getter ����
	 * 
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * ���� attachments �� setter ����
	 * 
	 * @param attachments
	 *            the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * ���� idCardCheckCode �� getter ����
	 * 
	 * @return the idCardCheckCode
	 */
	public String getIdCardCheckCode() {
		return idCardCheckCode;
	}

	/**
	 * ���� idCardCheckCode �� setter ����
	 * 
	 * @param idCardCheckCode
	 *            the idCardCheckCode to set
	 */
	public void setIdCardCheckCode(String idCardCheckCode) {
		this.idCardCheckCode = idCardCheckCode;
	}

	/**
	 * ���� idCardChecks �� getter ����
	 * 
	 * @return the idCardChecks
	 */
	public List<IdCardCheck> getIdCardChecks() {
		return idCardChecks;
	}

	/**
	 * ���� idCardChecks �� setter ����
	 * 
	 * @param idCardChecks
	 *            the idCardChecks to set
	 */
	public void setIdCardChecks(List<IdCardCheck> idCardChecks) {
		this.idCardChecks = idCardChecks;
	}

	/**
	 * ���� serviceInfoCount �� getter ����
	 * 
	 * @return the serviceInfoCount
	 */
	public Integer getServiceInfoCount() {
		return serviceInfoCount;
	}

	/**
	 * ���� serviceInfoCount �� setter ����
	 * 
	 * @param serviceInfoCount
	 *            the serviceInfoCount to set
	 */
	public void setServiceInfoCount(Integer serviceInfoCount) {
		this.serviceInfoCount = serviceInfoCount;
	}

	/**
	 * ���� serviceInfos �� getter ����
	 * 
	 * @return the serviceInfos
	 */
	public List<ServiceInfo> getServiceInfos() {
		return serviceInfos;
	}

	/**
	 * ���� serviceInfos �� setter ����
	 * 
	 * @param serviceInfos
	 *            the serviceInfos to set
	 */
	public void setServiceInfos(List<ServiceInfo> serviceInfos) {
		this.serviceInfos = serviceInfos;
	}

	public List<SMS> getMessages() {
		return messages;
	}

	public void setMessages(List<SMS> messages) {
		this.messages = messages;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getqStartDate() {
		return qStartDate;
	}

	public void setqStartDate(Date qStartDate) {
		this.qStartDate = qStartDate;
	}

	public Date getqEndDate() {
		return qEndDate;
	}

	public void setqEndDate(Date qEndDate) {
		this.qEndDate = qEndDate;
	}

}
