package cn.com.sinosoft.portalModule.portalTransaction.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.enums.EnumDataUtils;
import cn.com.sinosoft.portalModule.enumUtil.RequestProcessStatus;
@Entity
@Table(name = "PORTALTRANSACTION")
public class PortalTransaction implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5781018205265710345L;
	

	/** ������� */
	private String serialNo;

	/** ���Խ��״��� */
	private String transCode;
	
	/** ������������ID */
	private String batchID;

	/** ���Խ��ױ�ʾ */
	private String transIdentify;
	
	/** ���Խ�����ˮ�� */
	private String transSerialNumber;
	
	/** �����̼ҽ�����ˮ�� */
	private String merchantTransSerialNumber;
	
	/** ���Զ����� */
	private String orderSerialNumber;
	
	/** ���Ժ������������ţ��Ա������ţ� */
	private String merchantOrderNumber;
	
	/** �������������ַ */
	private String requestURL;
	
	/** ���Իص���ַ */
	private String callbackURL;
	
	/** ��������ʱ�� */
	private Date requestTime;
	
	/** ����Ӧ��ʱ�� */
	private Date responseTime;
	
	/** ����������״̬ */
	private Integer requestProcessStatus;
	
	/** ����������״̬���� */
	private String requestProcessStatusName;
	
	/** ����������״̬���� */
	private String requestProcessStatusDesc;
	
	/** ���Խ��ױ��� */
	private List<PortalTransactionMessage> portalTransactionMessages = new ArrayList<PortalTransactionMessage>(0);
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/**
	 * ������Ϣ
	 */
	public PortalTransaction() {
		
	}
	
	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * ���Խ��״����getter����
	 */
	@Column(name = "TRANSCODE")
	public String getTransCode() {
		return this.transCode;
	}
	
	/**
	 * ���Խ��״����setter����
	 */
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	
	/**
	 * ������������ID��getter����
	 */
	@Column(name = "BATCHID")
	public String getBatchID() {
		return batchID;
	}
	
	/**
	 * ������������ID��setter����
	 */
	public void setBatchID(String batchID) {
		this.batchID = batchID;
	}
	
	/**
	 * ���Խ��ױ�ʶ���getter����
	 */
	@Column(name = "TRANSIDENTIFY")
	public String getTransIdentify() {
		return transIdentify;
	}

	/**
	 * ���Խ��ױ�ʶ���getter����
	 */
	public void setTransIdentify(String transIdentify) {
		this.transIdentify = transIdentify;
	}

	/**
	 * ���Խ�����ˮ�ŵ�getter����
	 */
	@Column(name = "TRANSSERIALNUMBER")
	public String getTransSerialNumber() {
		return this.transSerialNumber;
	}

	/**
	 * ���Խ�����ˮ�ŵ�setter����
	 */
	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}
	
	/**
	 * �����̼ҽ�����ˮ�ŵ�getter����
	 */
	@Column(name = "MERCHANTTRANSSERIALNUMBER")
	public String getMerchantTransSerialNumber() {
		return merchantTransSerialNumber;
	}
	
	/**
	 * �����̼ҽ�����ˮ�ŵ�setter����
	 */
	public void setMerchantTransSerialNumber(String merchantTransSerialNumber) {
		this.merchantTransSerialNumber = merchantTransSerialNumber;
	}

	/**
	 * ���Զ����ŵ�getter����
	 */

	@Column(name = "ORDERSERIALNUMBER")
	public String getOrderSerialNumber() {
		return this.orderSerialNumber;
	}

	/**
	 * ���Զ����ŵ�setter����
	 */
	public void setOrderSerialNumber(String orderSerialNumber) {
		this.orderSerialNumber = orderSerialNumber;
	}
	
	/**
	 * ���Ժ������������ŵ�getter����
	 */
	@Column(name = "MERCHANTORDERNUMBER")
	public String getMerchantOrderNumber() {
		return merchantOrderNumber;
	}
	
	/**
	 * ���Ժ������������ŵ�setter����
	 */
	public void setMerchantOrderNumber(String merchantOrderNumber) {
		this.merchantOrderNumber = merchantOrderNumber;
	}
	
	/**
	 * ���������ַ��getter����
	 */
	@Column(name = "REQUESTURL")
	public String getRequestURL() {
		return requestURL;
	}
	
	/**
	 * ���������ַ��setter����
	 */
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	/**
	 * ���Իص���ַ��getter����
	 */
	@Column(name = "CALLBACKURL")
	public String getCallbackURL() {
		return callbackURL;
	}
	
	/**
	 * ���Իص���ַ��setter����
	 */
	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}
	
	/**
	 * ��������ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQUESTTIME")
	public Date getRequestTime() {
		return requestTime;
	}
	
	/**
	 * ��������ʱ���setter����
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
	/**
	 * ����Ӧ��ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESPONSETIME")
	public Date getResponseTime() {
		return responseTime;
	}

	/**
	 * ����Ӧ��ʱ���setter����
	 */
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	
	/**
	 * ����������״̬��getter����
	 */

	@Column(name = "REQUESTPROCESSSTATUS")
	public Integer getRequestProcessStatus() {
		return this.requestProcessStatus;
	}

	/**
	 * ����������״̬ö�����getter����
	 */
	@Transient
	public RequestProcessStatus getEnumRequestProcessStatus() {
		if (getRequestProcessStatus() == null) {
			return null;
		}
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByValue(RequestProcessStatus.class, getRequestProcessStatus());
		return requestProcessStatus;
	}

	/**
	 * ����������״̬����ֵ��getter����
	 */
	@Transient
	public String getRequestProcessStatusByCoreValue() {
		if (getRequestProcessStatus() == null) {
			return "";
		}
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByValue(RequestProcessStatus.class, getRequestProcessStatus());
		return requestProcessStatus.getCoreValue();
	}

	/**
	 * ����������״̬�̼�ֵ��getter����
	 */
	@Transient
	public String getRequestProcessStatusByMerchantValue() {
		if (getRequestProcessStatus() == null) {
			return "";
		}
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByValue(RequestProcessStatus.class, getRequestProcessStatus());
		return requestProcessStatus.getMerchantValue();
	}

	/**
	 * ����������״̬��setter����
	 */
	public void setRequestProcessStatus(Integer requestProcessStatus) {
		this.requestProcessStatus = requestProcessStatus;
	}

	/**
	 * ����������״̬��ֵ
	 */
	public void setEnumRequestProcessStatus(RequestProcessStatus requestProcessStatus) {
		if (requestProcessStatus != null) {
			this.requestProcessStatus = requestProcessStatus.getValue();
		}
	}

	/**
	 * ���Լ������͸�ֵ
	 */
	public void setRequestProcessStatusByCoreValue(String coreValue) {
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByCoreValue(RequestProcessStatus.class, coreValue);
		if (requestProcessStatus != null) {
			this.requestProcessStatus = requestProcessStatus.getValue();
		}
	}

	/**
	 * �����̼�������״̬��ֵ
	 */
	public void setRequestProcessStatusByMerchantValue(String merchantValue) {
		RequestProcessStatus requestProcessStatus = (RequestProcessStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(RequestProcessStatus.class, merchantValue);
		if (requestProcessStatus != null) {
			this.requestProcessStatus = requestProcessStatus.getValue();
		}
	}

	/**
	 * ����������״̬���Ƶ�getter����
	 */
	@Column(name = "REQUESTPROCESSSTATUSNAME")
	public String getRequestProcessStatusName() {
		return requestProcessStatusName;
	}

	/**
	 * ����������״̬���Ƶ�setter����
	 */
	public void setRequestProcessStatusName(String requestProcessStatusName) {
		this.requestProcessStatusName = requestProcessStatusName;
	}
	
	/**
	 * ����������״̬������getter����
	 */
	@Column(name = "REQUESTPROCESSSTATUSDESC")
	public String getRequestProcessStatusDesc() {
		return requestProcessStatusDesc;
	}

	/**
	 * ����������״̬������setter����
	 */
	public void setRequestProcessStatusDesc(String requestProcessStatusDesc) {
		this.requestProcessStatusDesc = requestProcessStatusDesc;
	}

	/**
	 * ���Խ��ױ��ĵ�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "portalTransaction")
	public List<PortalTransactionMessage> getPortalTransactionMessages() {
		return portalTransactionMessages;
	}
	
	/**
	 * ���Խ��ױ��ĵ�setter����
	 */
	public void setPortalTransactionMessages(
			List<PortalTransactionMessage> portalTransactionMessages) {
		this.portalTransactionMessages = portalTransactionMessages;
	}

	/**
	 * ����������н��ױ���
	 */
	public void addPortalTransactionMessages(List<PortalTransactionMessage> portalTransactionMessages) {
		
		for (PortalTransactionMessage portalTransactionMessage:portalTransactionMessages) {
			if (!getPortalTransactionMessages().contains(portalTransactionMessage)) {
				getPortalTransactionMessages().add(portalTransactionMessage);
			}
		}
		
		for (PortalTransactionMessage portalTransactionMessage:getPortalTransactionMessages()) {
			if (portalTransactionMessage.getTransactionMessage() == null) {
				portalTransactionMessage.setPortalTransaction(this);
			}
			
		}
	}
	
	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * ���Դ���ʱ���setter����
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * ���Ը���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * ���Ը���ʱ���setter����
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
