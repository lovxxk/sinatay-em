package cn.com.sinosoft.businessModule.bankAccount.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.businessModule.enums.dictionary.FundsTransferStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.SyncStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.TransferType;
import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;
import cn.com.sinosoft.enums.EnumDataUtils;

@Entity
@Table(name = "FUNDSTRANSFER")
public class FundsTransfer implements Serializable {

	/**
	 * �ʽ𻮲�
	 */
	private static final long serialVersionUID = 1L;
	
	/** ������� */
	private String serialNo;
	
	/** ���Ի�����ˮ�� (֧����������ˮ��)*/
	private String fundsTransferSerialNumber;
	
	/** ���Ժ������������ţ��Ա������ţ� */
	private String merchantOrderNumber;
	
	/** �����ʽ𻮲������� */
	private String fundsTransferOrderNumber;
	
	/** ���Ա����� */
	private String policySerialNumber;
	
	/** �����ʽ𻮲��˺� */
	private String accountNumber;
	
	/** �����ʺų�����Id */
	private String acctHolderId;
	
	/** �����ʺų��������� */
	private String acctHolderName;
	
	/**�����ֽ��ֵ*/
	private BigDecimal cashValue;

	/**����ʵ�ʿ�֧ȡ���*/
	private BigDecimal actualAvailableAmount;
	
	/**���Ի�������*/
	private Integer transferType;
	
	/**���Ի������*/
	private BigDecimal transferAmount;
	
	/** ���Ի���ʱ��*/
	private Date transferTime;
	
	/** ���Ի����ɹ�ʱ��*/
	private Date transferSuccessTime;
	
	/**�����ʽ𻮲�״̬*/
	private Integer fundsTransferStatus;
	
	/**�����ʽ𻮲�״̬����*/
	private String fundsTransferStatusDesc;
	
	/** ���Ա�ȫ����ʱ��*/
	private Date preserveAcceptTime;
	
	/** ���Ա�ȫ��Чʱ��*/
	private Date preserveEffectiveTime;
	
	/** ���Գ���ʱ��*/
	private Date reFundPolicyTime;
	
	/** ���Գ����ɹ�ʱ��*/
	private Date reFundPolicySuccessTime;
	
	/** ���Ա��� */
	private InsurancePolicy insurancePolicy;
	
	/** ���Բ���Ա */
	private String operatorID;
	
	/** ����ͬ��״̬ */
	private Integer syncStatus = SyncStatus.NOTYET_SYNCHRONIZED.getValue();
	
	/** ����ͬ��״̬���� */
	private String syncStatusDesc;
	
	/** ����ͬ����ʼʱ�� */
	private Date syncStartTime;
	
	/** ����ͬ������ʱ�� */
	private Date syncEndTime;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}
	
	/**
	 * ������ŵ�setter����
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * �����ʽ𻮲���ˮ�ŵ�getter����
	 */
	@Column(name = "FUNDSTRANSFERSERIALNUMBER")
	public String getFundsTransferSerialNumber() {
		return fundsTransferSerialNumber;
	}
	
	/**
	 * �����ʽ𻮲���ˮ�ŵ�setter����
	 */
	public void setFundsTransferSerialNumber(String fundsTransferSerialNumber) {
		this.fundsTransferSerialNumber = fundsTransferSerialNumber;
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
	 * �����ʽ𻮲������ŵ�getter����
	 */
	@Column(name = "FUNDSTRANSFERORDERNUMBER")
	public String getFundsTransferOrderNumber() {
		return fundsTransferOrderNumber;
	}
	
	/**
	 * �����ʽ𻮲������ŵ�setter����
	 */
	public void setFundsTransferOrderNumber(
			String fundsTransferOrderNumber) {
		this.fundsTransferOrderNumber = fundsTransferOrderNumber;
	}

	/**
	 * ���Ա����ŵ�getter����
	 */
	@Column(name = "POLICYSERIALNUMBER")
	public String getPolicySerialNumber() {
		return this.policySerialNumber;
	}

	/**
	 * ���Ա����ŵ�setter����
	 */
	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}

	/**
	 * �����ʽ𻮲��˺ŵ�getter����
	 */
	@Column(name = "ACCOUNTNUMBER")
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * �����ʽ𻮲��˺ŵ�setter����
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	/**
	 * �����ʺų�����Id��getter����
	 */
	@Column(name = "ACCTHOLDERID")
	public String getAcctHolderId() {
		return acctHolderId;
	}
	
	/**
	 * �����ʺų�����Id��setter����
	 */
	public void setAcctHolderId(String acctHolderId) {
		this.acctHolderId = acctHolderId;
	}

	/**
	 * �����ʺų�����������getter����
	 */
	@Column(name = "ACCTHOLDERNAME")
	public String getAcctHolderName() {
		return this.acctHolderName;
	}

	/**
	 * �����ʺų�����������setter����
	 */
	public void setAcctHolderName(String acctHolderName) {
		this.acctHolderName = acctHolderName;
	}
	
	/**
	 * �����ֽ��ֵ��getter����
	 */
	@Column(name = "CASHVALUE")
	public BigDecimal getCashValue() {
		return cashValue;
	}

	/**
	 * �����ֽ��ֵ��setter����
	 */
	public void setCashValue(BigDecimal cashValue) {
		this.cashValue = cashValue;
	}
	
	/**
	 * ����ʵ�ʿ�֧ȡ����getter����
	 */
	@Column(name = "ACTUALAVAILABLEAMOUNT")
	public BigDecimal getActualAvailableAmount() {
		return actualAvailableAmount;
	}
	
	/**
	 * ����ʵ�ʿ�֧ȡ����setter����
	 */
	public void setActualAvailableAmount(BigDecimal actualAvailableAmount) {
		this.actualAvailableAmount = actualAvailableAmount;
	}
	
	/**
	 * ���Ի������͵�getter����
	 */
	@Column(name = "TRANSFERTYPE")
	public Integer getTransferType() {
		return transferType;
	}
	
	/**
	 * ���Ի�������ö�����getter����
	 */
	@Transient
	public TransferType getEnumTransferType() {
		if (getTransferType() == null) {
			return null;
		}
		TransferType  transferType = (TransferType) EnumDataUtils.getEnumDictionaryByValue(TransferType.class, getTransferType());
		return transferType;
	}
	
	/**
	 * ���Ի������ͺ���ֵ��getter����
	 */
	@Transient
	public String getTransferTypeByCoreValue() {
		if (getTransferType() == null) {
			return "";
		}
		TransferType  transferType = (TransferType) EnumDataUtils.getEnumDictionaryByValue(TransferType.class, getTransferType());
		return transferType.getCoreValue();
	}
	
	/**
	 * ���Ի��������̼�ֵ��getter����
	 */
	@Transient
	public String getTransferTypeByMerchantValue() {
		if (getTransferType() == null) {
			return "";
		}
		TransferType  transferType = (TransferType) EnumDataUtils.getEnumDictionaryByValue(TransferType.class, getTransferType());
		return transferType.getMerchantValue();
	}
	
	
	/**
	 * ���Ի������͵�setter����
	 */
	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}
	
	/**
	 * ���Ի������͸�ֵ
	 */
	public void setEnumTransferType(TransferType  transferType) {
		if (transferType != null) {
			this.transferType = transferType.getValue();
		}
	}
	
	/**
	 * ���Ժ��Ļ������͸�ֵ
	 */
	public void setTransferTypeByCoreValue(String coreValue) {
		TransferType  transferType = (TransferType) EnumDataUtils.getEnumDictionaryByCoreValue(TransferType.class, coreValue);
		if (transferType != null) {
			this.transferType = transferType.getValue();
		}
	}
	
	/**
	 * �����̼һ������͸�ֵ
	 */
	public void setTransferTypeByMerchantValue(String merchantValue) {
		TransferType  transferType = (TransferType) EnumDataUtils.getEnumDictionaryByMerchantValue(TransferType.class, merchantValue);
		if (transferType != null) {
			this.transferType = transferType.getValue();
		}
	}
	
	
	/**
	 * ���Ի�������getter����
	 */
	@Column(name = "TRANSFERAMOUNT")
	public BigDecimal getTransferAmount() {
		return transferAmount;
	}
	
	/**
	 * ���Ի�������setter����
	 */
	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}

	/**
	 * ���Ի���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSFERTIME")
	public Date getTransferTime() {
		return transferTime;
	}
	
	/**
	 * ���Ի���ʱ���setter����
	 */
	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}
	
	/**
	 * ���Ի����ɹ�ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSFERSUCCESSTIME")
	public Date getTransferSuccessTime() {
		return transferSuccessTime;
	}
	
	/**
	 * ���Ի����ɹ�ʱ���setter����
	 */
	public void setTransferSuccessTime(Date transferSuccessTime) {
		this.transferSuccessTime = transferSuccessTime;
	}

	
	/**
	 * �����ʽ𻮲�״̬��getter����
	 */
	@Column(name = "FUNDSTRANSFERSTATUS")
	public Integer getFundsTransferStatus() {
		return fundsTransferStatus;
	}
	
	/**
	 * �����ʽ𻮲�״̬ö�����getter����
	 */
	@Transient
	public FundsTransferStatus getEnumFundsTransferStatus() {
		if (getFundsTransferStatus() == null) {
			return null;
		}
		FundsTransferStatus  fundsTransferStatus = (FundsTransferStatus) EnumDataUtils.getEnumDictionaryByValue(FundsTransferStatus.class, getFundsTransferStatus());
		return fundsTransferStatus;
	}
	
	/**
	 * �����ʽ𻮲�״̬����ֵ��getter����
	 */
	@Transient
	public String getFundsTransferStatusByCoreValue() {
		if (getFundsTransferStatus() == null) {
			return "";
		}
		FundsTransferStatus  fundsTransferStatus = (FundsTransferStatus) EnumDataUtils.getEnumDictionaryByValue(FundsTransferStatus.class, getFundsTransferStatus());
		return fundsTransferStatus.getCoreValue();
	}
	
	/**
	 * �����ʽ𻮲�״̬�̼�ֵ��getter����
	 */
	@Transient
	public String getFundsTransferStatusByMerchantValue() {
		if (getFundsTransferStatus() == null) {
			return "";
		}
		FundsTransferStatus  fundsTransferStatus = (FundsTransferStatus) EnumDataUtils.getEnumDictionaryByValue(FundsTransferStatus.class, getFundsTransferStatus());
		return fundsTransferStatus.getMerchantValue();
	}
	
	/**
	 * �����ʽ𻮲�״̬��setter����
	 */
	public void setFundsTransferStatus(Integer fundsTransferStatus) {
		this.fundsTransferStatus = fundsTransferStatus;
	}
	
	/**
	 * �����ʽ𻮲�״̬��ֵ
	 */
	public void setEnumFundsTransferStatus(FundsTransferStatus  fundsTransferStatus) {
		if (fundsTransferStatus != null) {
			this.fundsTransferStatus = fundsTransferStatus.getValue();
		}
	}
	
	/**
	 * ���Ժ����ʽ𻮲�״̬��ֵ
	 */
	public void setFundsTransferStatusByCoreValue(String coreValue) {
		FundsTransferStatus  fundsTransferStatus = (FundsTransferStatus) EnumDataUtils.getEnumDictionaryByCoreValue(FundsTransferStatus.class, coreValue);
		if (fundsTransferStatus != null) {
			this.fundsTransferStatus = fundsTransferStatus.getValue();
		}
	}
	
	/**
	 * �����̼��ʽ𻮲�״̬��ֵ
	 */
	public void setFundsTransferStatusByMerchantValue(String merchantValue) {
		FundsTransferStatus  fundsTransferStatus = (FundsTransferStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(FundsTransferStatus.class, merchantValue);
		if (fundsTransferStatus != null) {
			this.fundsTransferStatus = fundsTransferStatus.getValue();
		}
	}
	
	
	/**
	 * �����ʽ𻮲�״̬������getter����
	 */
	@Column(name = "FUNDSTRANSFERSTATUSDESC")
	public String getFundsTransferStatusDesc() {
		return fundsTransferStatusDesc;
	}
	
	/**
	 * �����ʽ𻮲�״̬������setter����
	 */
	public void setFundsTransferStatusDesc(String fundsTransferStatusDesc) {
		this.fundsTransferStatusDesc = fundsTransferStatusDesc;
	}
	
	/**
	 * ���Ա�ȫ����ʱ���getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PRESERVEACCEPTTIME")
	public Date getPreserveAcceptTime() {
		return preserveAcceptTime;
	}
	
	/**
	 * ���Ա�ȫ����ʱ���setter����
	 */
	public void setPreserveAcceptTime(Date preserveAcceptTime) {
		this.preserveAcceptTime = preserveAcceptTime;
	}
	
	/**
	 * ���Ա�ȫ��Чʱ���getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PRESERVEEFFECTIVETIME")
	public Date getPreserveEffectiveTime() {
		return preserveEffectiveTime;
	}
	
	/**
	 * ���Ա�ȫ��Чʱ���setter����
	 */
	public void setPreserveEffectiveTime(Date preserveEffectiveTime) {
		this.preserveEffectiveTime = preserveEffectiveTime;
	}
	
	/**
	 * ���Գ���ʱ���getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "REFUNDPOLICYTIME")
	public Date getReFundPolicyTime() {
		return reFundPolicyTime;
	}
	
	/**
	 * ���Գ���ʱ���setter����
	 */
	public void setReFundPolicyTime(Date reFundPolicyTime) {
		this.reFundPolicyTime = reFundPolicyTime;
	}

	/**
	 * ���Գ����ɹ�ʱ���getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "REFUNDPOLICYSUCCESSTIME")
	public Date getReFundPolicySuccessTime() {
		return reFundPolicySuccessTime;
	}
	
	/**
	 * ���Գ����ɹ�ʱ���setter����
	 */
	public void setReFundPolicySuccessTime(Date reFundPolicySuccessTime) {
		this.reFundPolicySuccessTime = reFundPolicySuccessTime;
	}

	/**
	 * ���Բ���Ա��getter����
	 */
	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * ���Բ���Ա��setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	
	/**
	 * ���Ա�����getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "POLICYSERIALNO")
	public InsurancePolicy getInsurancePolicy() {
		return this.insurancePolicy;
	}

	/**
	 * ���Ա�����setter����
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}
	
	/**
	 * ������ӱ���ʱͬʱ���ʽ𻮲���Ϣ��ֵ����������
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (!getInsurancePolicy().getFundsTransfers().contains(this)) {
			getInsurancePolicy().getFundsTransfers().add(this);
		}
	}
	
	/** 
	 * ����ͬ��״̬��getter���� 
	 */
	@Column(name = "SYNCSTATUS")
	public Integer getSyncStatus() {
		return syncStatus;
	}
	
	/**
	 * ����ͬ��״̬ö�����getter����
	 */
	@Transient
	public SyncStatus getEnumSyncStatus() {
		if (getSyncStatus() == null) {
			return null;
		}
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByValue(SyncStatus.class, getSyncStatus());
		return syncStatus;
	}
	
	/**
	 * ����ͬ��״̬����ֵ��getter����
	 */
	@Transient
	public String getSyncStatusByCoreValue() {
		if (getSyncStatus() == null) {
			return "";
		}
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByValue(SyncStatus.class, getSyncStatus());
		return syncStatus.getCoreValue();
	}
	
	/**
	 * ����ͬ��״̬�̼�ֵ��getter����
	 */
	@Transient
	public String getSyncStatusByMerchantValue() {
		if (getSyncStatus() == null) {
			return "";
		}
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByValue(SyncStatus.class, getSyncStatus());
		return syncStatus.getMerchantValue();
	}
	
	/** 
	 * ����ͬ��״̬��setter���� 
	 */
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	
	/**
	 * ����ͬ��״̬��ֵ
	 */
	public void setEnumSyncStatus(SyncStatus  syncStatus) {
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/**
	 * ���Ժ���ͬ��״̬��ֵ
	 */
	public void setSyncStatusByCoreValue(String coreValue) {
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByCoreValue(SyncStatus.class, coreValue);
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/**
	 * �����̼�ͬ��״̬��ֵ
	 */
	public void setSyncStatusByMerchantValue(String merchantValue) {
		SyncStatus syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(SyncStatus.class, merchantValue);
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/** 
	 * ����ͬ��״̬������getter���� 
	 */
	@Column(name = "SYNCSTATUSDESC")
	public String getSyncStatusDesc() {
		return syncStatusDesc;
	}
	
	/** 
	 * ����ͬ��״̬������setter���� 
	 */
	public void setSyncStatusDesc(String syncStatusDesc) {
		this.syncStatusDesc = syncStatusDesc;
	}

	/** 
	 * ����ͬ����ʼʱ���getter���� 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNCSTARTTIME")
	public Date getSyncStartTime() {
		return syncStartTime;
	}
	
	/** 
	 * ����ͬ����ʼʱ���setter���� 
	 */
	public void setSyncStartTime(Date syncStartTime) {
		this.syncStartTime = syncStartTime;
	}
	
	/** 
	 * ����ͬ������ʱ���getter���� 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNCENDTIME")
	public Date getSyncEndTime() {
		return syncEndTime;
	}
	
	/** 
	 * ����ͬ������ʱ���setter���� 
	 */
	public void setSyncEndTime(Date syncEndTime) {
		this.syncEndTime = syncEndTime;
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
