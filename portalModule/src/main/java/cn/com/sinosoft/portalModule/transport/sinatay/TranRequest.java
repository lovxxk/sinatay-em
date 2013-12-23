package cn.com.sinosoft.portalModule.transport.sinatay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.sinosoft.portalModule.transport.dhf100.QueryData;

/**
 * TranRequest节点DTO
 * 
 */
public class TranRequest implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// 支付时查询通过tradeno查询支付结果
	private String tradeNo;

	private QueryData queryData;

	/**  */
	private LCCont lcCont;

	/**
	 * 投保单号
	 */
	private String proposalContNo;

	/**
	 * 影像文件数
	 */
	private Integer attachmentCount;

	/**
	 * 影像文件
	 */
	private List<Attachment> attachments = new ArrayList<Attachment>(0);

	/** 身份证校验类型 code表示位1: 号码姓名多项核查 2: 号码姓名核查比对 3: 号码姓名多项核查及同住址成员多项核查 */
	private String idCardCheckCode;

	/** 身份证校验 */
	private List<IdCardCheck> idCardChecks = new ArrayList<IdCardCheck>(0);

	/** 身份证校验 */
	private List<IdCardChecks> idCardCheckes = new ArrayList<IdCardChecks>(0);

	/** 客服系统通知服务数 */
	private Integer serviceInfoCount;

	/** 客服系统通知服务 */
	private List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>(0);

	/** 手机短信 */
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
	//分段开始日期
	private Date qStartDate;
	//分段截至日期
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
	 * 属性 lcCont 的 getter 方法
	 * 
	 * @return the lcCont
	 */
	public LCCont getLcCont() {
		return lcCont;
	}

	/**
	 * 属性 lcCont 的 setter 方法
	 * 
	 * @param lcCont
	 *            the lcCont to set
	 */
	public void setLcCont(LCCont lcCont) {
		this.lcCont = lcCont;
	}

	/**
	 * 属性 proposalContNo 的 getter 方法
	 * 
	 * @return the proposalContNo
	 */
	public String getProposalContNo() {
		return proposalContNo;
	}

	/**
	 * 属性 proposalContNo 的 setter 方法
	 * 
	 * @param proposalContNo
	 *            the proposalContNo to set
	 */
	public void setProposalContNo(String proposalContNo) {
		this.proposalContNo = proposalContNo;
	}

	/**
	 * 属性 attachmentCount 的 getter 方法
	 * 
	 * @return the attachmentCount
	 */
	public Integer getAttachmentCount() {
		return attachmentCount;
	}

	/**
	 * 属性 attachmentCount 的 setter 方法
	 * 
	 * @param attachmentCount
	 *            the attachmentCount to set
	 */
	public void setAttachmentCount(Integer attachmentCount) {
		this.attachmentCount = attachmentCount;
	}

	/**
	 * 属性 attachments 的 getter 方法
	 * 
	 * @return the attachments
	 */
	public List<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * 属性 attachments 的 setter 方法
	 * 
	 * @param attachments
	 *            the attachments to set
	 */
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * 属性 idCardCheckCode 的 getter 方法
	 * 
	 * @return the idCardCheckCode
	 */
	public String getIdCardCheckCode() {
		return idCardCheckCode;
	}

	/**
	 * 属性 idCardCheckCode 的 setter 方法
	 * 
	 * @param idCardCheckCode
	 *            the idCardCheckCode to set
	 */
	public void setIdCardCheckCode(String idCardCheckCode) {
		this.idCardCheckCode = idCardCheckCode;
	}

	/**
	 * 属性 idCardChecks 的 getter 方法
	 * 
	 * @return the idCardChecks
	 */
	public List<IdCardCheck> getIdCardChecks() {
		return idCardChecks;
	}

	/**
	 * 属性 idCardChecks 的 setter 方法
	 * 
	 * @param idCardChecks
	 *            the idCardChecks to set
	 */
	public void setIdCardChecks(List<IdCardCheck> idCardChecks) {
		this.idCardChecks = idCardChecks;
	}

	/**
	 * 属性 serviceInfoCount 的 getter 方法
	 * 
	 * @return the serviceInfoCount
	 */
	public Integer getServiceInfoCount() {
		return serviceInfoCount;
	}

	/**
	 * 属性 serviceInfoCount 的 setter 方法
	 * 
	 * @param serviceInfoCount
	 *            the serviceInfoCount to set
	 */
	public void setServiceInfoCount(Integer serviceInfoCount) {
		this.serviceInfoCount = serviceInfoCount;
	}

	/**
	 * 属性 serviceInfos 的 getter 方法
	 * 
	 * @return the serviceInfos
	 */
	public List<ServiceInfo> getServiceInfos() {
		return serviceInfos;
	}

	/**
	 * 属性 serviceInfos 的 setter 方法
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
