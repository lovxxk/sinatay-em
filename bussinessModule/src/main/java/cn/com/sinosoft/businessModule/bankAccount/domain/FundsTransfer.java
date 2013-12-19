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
	 * 资金划拨
	 */
	private static final long serialVersionUID = 1L;
	
	/** 属性序号 */
	private String serialNo;
	
	/** 属性划拨流水号 (支付宝划拨流水号)*/
	private String fundsTransferSerialNumber;
	
	/** 属性合作机构订单号（淘宝订单号） */
	private String merchantOrderNumber;
	
	/** 属性资金划拨订单号 */
	private String fundsTransferOrderNumber;
	
	/** 属性保单号 */
	private String policySerialNumber;
	
	/** 属性资金划拨账号 */
	private String accountNumber;
	
	/** 属性帐号持有人Id */
	private String acctHolderId;
	
	/** 属性帐号持有人姓名 */
	private String acctHolderName;
	
	/**属性现金价值*/
	private BigDecimal cashValue;

	/**属性实际可支取金额*/
	private BigDecimal actualAvailableAmount;
	
	/**属性划拨类型*/
	private Integer transferType;
	
	/**属性划拨金额*/
	private BigDecimal transferAmount;
	
	/** 属性划拨时间*/
	private Date transferTime;
	
	/** 属性划拨成功时间*/
	private Date transferSuccessTime;
	
	/**属性资金划拨状态*/
	private Integer fundsTransferStatus;
	
	/**属性资金划拨状态描述*/
	private String fundsTransferStatusDesc;
	
	/** 属性保全受理时间*/
	private Date preserveAcceptTime;
	
	/** 属性保全生效时间*/
	private Date preserveEffectiveTime;
	
	/** 属性撤单时间*/
	private Date reFundPolicyTime;
	
	/** 属性撤单成功时间*/
	private Date reFundPolicySuccessTime;
	
	/** 属性保单 */
	private InsurancePolicy insurancePolicy;
	
	/** 属性操作员 */
	private String operatorID;
	
	/** 属性同步状态 */
	private Integer syncStatus = SyncStatus.NOTYET_SYNCHRONIZED.getValue();
	
	/** 属性同步状态描述 */
	private String syncStatusDesc;
	
	/** 属性同步开始时间 */
	private Date syncStartTime;
	
	/** 属性同步结束时间 */
	private Date syncEndTime;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}
	
	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	/**
	 * 属性资金划拨流水号的getter方法
	 */
	@Column(name = "FUNDSTRANSFERSERIALNUMBER")
	public String getFundsTransferSerialNumber() {
		return fundsTransferSerialNumber;
	}
	
	/**
	 * 属性资金划拨流水号的setter方法
	 */
	public void setFundsTransferSerialNumber(String fundsTransferSerialNumber) {
		this.fundsTransferSerialNumber = fundsTransferSerialNumber;
	}

	/**
	 * 属性合作机构订单号的getter方法
	 */
	@Column(name = "MERCHANTORDERNUMBER")
	public String getMerchantOrderNumber() {
		return merchantOrderNumber;
	}
	
	/**
	 * 属性合作机构订单号的setter方法
	 */
	public void setMerchantOrderNumber(String merchantOrderNumber) {
		this.merchantOrderNumber = merchantOrderNumber;
	}
	
	/**
	 * 属性资金划拨订单号的getter方法
	 */
	@Column(name = "FUNDSTRANSFERORDERNUMBER")
	public String getFundsTransferOrderNumber() {
		return fundsTransferOrderNumber;
	}
	
	/**
	 * 属性资金划拨订单号的setter方法
	 */
	public void setFundsTransferOrderNumber(
			String fundsTransferOrderNumber) {
		this.fundsTransferOrderNumber = fundsTransferOrderNumber;
	}

	/**
	 * 属性保单号的getter方法
	 */
	@Column(name = "POLICYSERIALNUMBER")
	public String getPolicySerialNumber() {
		return this.policySerialNumber;
	}

	/**
	 * 属性保单号的setter方法
	 */
	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}

	/**
	 * 属性资金划拨账号的getter方法
	 */
	@Column(name = "ACCOUNTNUMBER")
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/**
	 * 属性资金划拨账号的setter方法
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	/**
	 * 属性帐号持有人Id的getter方法
	 */
	@Column(name = "ACCTHOLDERID")
	public String getAcctHolderId() {
		return acctHolderId;
	}
	
	/**
	 * 属性帐号持有人Id的setter方法
	 */
	public void setAcctHolderId(String acctHolderId) {
		this.acctHolderId = acctHolderId;
	}

	/**
	 * 属性帐号持有人姓名的getter方法
	 */
	@Column(name = "ACCTHOLDERNAME")
	public String getAcctHolderName() {
		return this.acctHolderName;
	}

	/**
	 * 属性帐号持有人姓名的setter方法
	 */
	public void setAcctHolderName(String acctHolderName) {
		this.acctHolderName = acctHolderName;
	}
	
	/**
	 * 属性现金价值的getter方法
	 */
	@Column(name = "CASHVALUE")
	public BigDecimal getCashValue() {
		return cashValue;
	}

	/**
	 * 属性现金价值的setter方法
	 */
	public void setCashValue(BigDecimal cashValue) {
		this.cashValue = cashValue;
	}
	
	/**
	 * 属性实际可支取金额的getter方法
	 */
	@Column(name = "ACTUALAVAILABLEAMOUNT")
	public BigDecimal getActualAvailableAmount() {
		return actualAvailableAmount;
	}
	
	/**
	 * 属性实际可支取金额的setter方法
	 */
	public void setActualAvailableAmount(BigDecimal actualAvailableAmount) {
		this.actualAvailableAmount = actualAvailableAmount;
	}
	
	/**
	 * 属性划拨类型的getter方法
	 */
	@Column(name = "TRANSFERTYPE")
	public Integer getTransferType() {
		return transferType;
	}
	
	/**
	 * 属性划拨类型枚举类的getter方法
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
	 * 属性划拨类型核心值的getter方法
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
	 * 属性划拨类型商家值的getter方法
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
	 * 属性划拨类型的setter方法
	 */
	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}
	
	/**
	 * 属性划拨类型赋值
	 */
	public void setEnumTransferType(TransferType  transferType) {
		if (transferType != null) {
			this.transferType = transferType.getValue();
		}
	}
	
	/**
	 * 属性核心划拨类型赋值
	 */
	public void setTransferTypeByCoreValue(String coreValue) {
		TransferType  transferType = (TransferType) EnumDataUtils.getEnumDictionaryByCoreValue(TransferType.class, coreValue);
		if (transferType != null) {
			this.transferType = transferType.getValue();
		}
	}
	
	/**
	 * 属性商家划拨类型赋值
	 */
	public void setTransferTypeByMerchantValue(String merchantValue) {
		TransferType  transferType = (TransferType) EnumDataUtils.getEnumDictionaryByMerchantValue(TransferType.class, merchantValue);
		if (transferType != null) {
			this.transferType = transferType.getValue();
		}
	}
	
	
	/**
	 * 属性划拨金额的getter方法
	 */
	@Column(name = "TRANSFERAMOUNT")
	public BigDecimal getTransferAmount() {
		return transferAmount;
	}
	
	/**
	 * 属性划拨金额的setter方法
	 */
	public void setTransferAmount(BigDecimal transferAmount) {
		this.transferAmount = transferAmount;
	}

	/**
	 * 属性划拨时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSFERTIME")
	public Date getTransferTime() {
		return transferTime;
	}
	
	/**
	 * 属性划拨时间的setter方法
	 */
	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}
	
	/**
	 * 属性划拨成功时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSFERSUCCESSTIME")
	public Date getTransferSuccessTime() {
		return transferSuccessTime;
	}
	
	/**
	 * 属性划拨成功时间的setter方法
	 */
	public void setTransferSuccessTime(Date transferSuccessTime) {
		this.transferSuccessTime = transferSuccessTime;
	}

	
	/**
	 * 属性资金划拨状态的getter方法
	 */
	@Column(name = "FUNDSTRANSFERSTATUS")
	public Integer getFundsTransferStatus() {
		return fundsTransferStatus;
	}
	
	/**
	 * 属性资金划拨状态枚举类的getter方法
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
	 * 属性资金划拨状态核心值的getter方法
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
	 * 属性资金划拨状态商家值的getter方法
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
	 * 属性资金划拨状态的setter方法
	 */
	public void setFundsTransferStatus(Integer fundsTransferStatus) {
		this.fundsTransferStatus = fundsTransferStatus;
	}
	
	/**
	 * 属性资金划拨状态赋值
	 */
	public void setEnumFundsTransferStatus(FundsTransferStatus  fundsTransferStatus) {
		if (fundsTransferStatus != null) {
			this.fundsTransferStatus = fundsTransferStatus.getValue();
		}
	}
	
	/**
	 * 属性核心资金划拨状态赋值
	 */
	public void setFundsTransferStatusByCoreValue(String coreValue) {
		FundsTransferStatus  fundsTransferStatus = (FundsTransferStatus) EnumDataUtils.getEnumDictionaryByCoreValue(FundsTransferStatus.class, coreValue);
		if (fundsTransferStatus != null) {
			this.fundsTransferStatus = fundsTransferStatus.getValue();
		}
	}
	
	/**
	 * 属性商家资金划拨状态赋值
	 */
	public void setFundsTransferStatusByMerchantValue(String merchantValue) {
		FundsTransferStatus  fundsTransferStatus = (FundsTransferStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(FundsTransferStatus.class, merchantValue);
		if (fundsTransferStatus != null) {
			this.fundsTransferStatus = fundsTransferStatus.getValue();
		}
	}
	
	
	/**
	 * 属性资金划拨状态描述的getter方法
	 */
	@Column(name = "FUNDSTRANSFERSTATUSDESC")
	public String getFundsTransferStatusDesc() {
		return fundsTransferStatusDesc;
	}
	
	/**
	 * 属性资金划拨状态描述的setter方法
	 */
	public void setFundsTransferStatusDesc(String fundsTransferStatusDesc) {
		this.fundsTransferStatusDesc = fundsTransferStatusDesc;
	}
	
	/**
	 * 属性保全受理时间的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PRESERVEACCEPTTIME")
	public Date getPreserveAcceptTime() {
		return preserveAcceptTime;
	}
	
	/**
	 * 属性保全受理时间的setter方法
	 */
	public void setPreserveAcceptTime(Date preserveAcceptTime) {
		this.preserveAcceptTime = preserveAcceptTime;
	}
	
	/**
	 * 属性保全生效时间的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "PRESERVEEFFECTIVETIME")
	public Date getPreserveEffectiveTime() {
		return preserveEffectiveTime;
	}
	
	/**
	 * 属性保全生效时间的setter方法
	 */
	public void setPreserveEffectiveTime(Date preserveEffectiveTime) {
		this.preserveEffectiveTime = preserveEffectiveTime;
	}
	
	/**
	 * 属性撤单时间的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "REFUNDPOLICYTIME")
	public Date getReFundPolicyTime() {
		return reFundPolicyTime;
	}
	
	/**
	 * 属性撤单时间的setter方法
	 */
	public void setReFundPolicyTime(Date reFundPolicyTime) {
		this.reFundPolicyTime = reFundPolicyTime;
	}

	/**
	 * 属性撤单成功时间的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "REFUNDPOLICYSUCCESSTIME")
	public Date getReFundPolicySuccessTime() {
		return reFundPolicySuccessTime;
	}
	
	/**
	 * 属性撤单成功时间的setter方法
	 */
	public void setReFundPolicySuccessTime(Date reFundPolicySuccessTime) {
		this.reFundPolicySuccessTime = reFundPolicySuccessTime;
	}

	/**
	 * 属性操作员的getter方法
	 */
	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * 属性操作员的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	
	/**
	 * 属性保单的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "POLICYSERIALNO")
	public InsurancePolicy getInsurancePolicy() {
		return this.insurancePolicy;
	}

	/**
	 * 属性保单的setter方法
	 */
	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}
	
	/**
	 * 属性添加保单时同时将资金划拨信息赋值给保单对象
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (!getInsurancePolicy().getFundsTransfers().contains(this)) {
			getInsurancePolicy().getFundsTransfers().add(this);
		}
	}
	
	/** 
	 * 属性同步状态的getter方法 
	 */
	@Column(name = "SYNCSTATUS")
	public Integer getSyncStatus() {
		return syncStatus;
	}
	
	/**
	 * 属性同步状态枚举类的getter方法
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
	 * 属性同步状态核心值的getter方法
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
	 * 属性同步状态商家值的getter方法
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
	 * 属性同步状态的setter方法 
	 */
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	
	/**
	 * 属性同步状态赋值
	 */
	public void setEnumSyncStatus(SyncStatus  syncStatus) {
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/**
	 * 属性核心同步状态赋值
	 */
	public void setSyncStatusByCoreValue(String coreValue) {
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByCoreValue(SyncStatus.class, coreValue);
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/**
	 * 属性商家同步状态赋值
	 */
	public void setSyncStatusByMerchantValue(String merchantValue) {
		SyncStatus syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(SyncStatus.class, merchantValue);
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/** 
	 * 属性同步状态描述的getter方法 
	 */
	@Column(name = "SYNCSTATUSDESC")
	public String getSyncStatusDesc() {
		return syncStatusDesc;
	}
	
	/** 
	 * 属性同步状态描述的setter方法 
	 */
	public void setSyncStatusDesc(String syncStatusDesc) {
		this.syncStatusDesc = syncStatusDesc;
	}

	/** 
	 * 属性同步开始时间的getter方法 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNCSTARTTIME")
	public Date getSyncStartTime() {
		return syncStartTime;
	}
	
	/** 
	 * 属性同步开始时间的setter方法 
	 */
	public void setSyncStartTime(Date syncStartTime) {
		this.syncStartTime = syncStartTime;
	}
	
	/** 
	 * 属性同步结束时间的getter方法 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNCENDTIME")
	public Date getSyncEndTime() {
		return syncEndTime;
	}
	
	/** 
	 * 属性同步结束时间的setter方法 
	 */
	public void setSyncEndTime(Date syncEndTime) {
		this.syncEndTime = syncEndTime;
	}
	
	/**
	 * 属性创建时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 属性创建时间的setter方法
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 属性更新时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性更新时间的setter方法
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
