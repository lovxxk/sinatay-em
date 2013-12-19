package cn.com.sinosoft.businessModule.policy.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.businessModule.enums.dictionary.LockStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.ProcessStatus;
import cn.com.sinosoft.enums.EnumDataUtils;

/**
 * POJO类InsurancePolicy
 */
@Entity
@Table(name = "INSURANCEPOLICYPROCESS")
public class InsurancePolicyProcess implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;
	
	/** 属性合作机构订单号（淘宝订单号） */
	private String merchantOrderNumber;

	/** 属性保单号 */
	private String policySerialNumber;

	/** 属性合作机构代码 */
	private String institutionCode;

	/** 属性合作机构名称 */
	private String institutionName;
	
	/** 属性合作机构名称 */
	private String serviceName;
	
	/** 属性加锁状态 */
	private Integer lockStatus;
	
	/** 属性加锁原因 */
	private String lockedReason;
	
	/** 属性处理状态 */
	private Integer processStatus;
	
	/** 属性处理开始时间 */
	private Date processStartTime;
	
	/** 属性处理结束时间 */
	private Date processEndTime;
	
	/** 属性处理状态描述 */
	private String processStatusDesc;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * 类InsurancePolicyProcess的默认构造方法
	 */
	public InsurancePolicyProcess() {
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * 属性序号的setter方法
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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
	 * 属性合作机构代码的getter方法 
	 */
	@Column(name = "INSTITUTIONCODE")
	public String getInstitutionCode() {
		return institutionCode;
	}
	
	/** 
	 * 属性合作机构代码的setter方法 
	 */
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	/** 
	 * 属性合作机构名称的getter方法 
	 */
	@Column(name = "INSTITUTIONNAME")
	public String getInstitutionName() {
		return institutionName;
	}
	
	/** 
	 * 属性合作机构名称的setter方法 
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
	/**
	 * 属性服务名称的getter方法
	 */
	@Column(name = "SERVICENAME")
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * 属性服务名称的setter方法
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/** 
	 * 属性加锁状态的getter方法 
	 */
	@Column(name = "LOCKSTATUS")
	public Integer getLockStatus() {
		return lockStatus;
	}
	
	/**
	 * 属性处理状态枚举类的getter方法
	 */
	@Transient
	public LockStatus getEnumLockStatus() {
		if (getLockStatus() == null) {
			return null;
		}
		LockStatus  lockStatus = (LockStatus) EnumDataUtils.getEnumDictionaryByValue(LockStatus.class, getLockStatus());
		return lockStatus;
	}
	
	/** 
	 * 属性加锁状态的getter方法 
	 */
	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	/**
	 * 属性加锁状态赋值
	 */
	public void setEnumLockStatus(LockStatus  lockStatus) {
		if (lockStatus != null) {
			this.processStatus = lockStatus.getValue();
		}
	}
	
	/** 
	 * 属性加锁原因的getter方法 
	 */
	@Column(name = "LOCKEDREASON")
	public String getLockedReason() {
		return lockedReason;
	}
	
	/** 
	 * 属性加锁原因的setter方法 
	 */
	public void setLockedReason(String lockedReason) {
		this.lockedReason = lockedReason;
	}

	/** 
	 * 属性处理状态的getter方法 
	 */
	@Column(name = "PROCESSSTATUS")
	public Integer getProcessStatus() {
		return processStatus;
	}

	/**
	 * 属性处理状态枚举类的getter方法
	 */
	@Transient
	public ProcessStatus getEnumProcessStatus() {
		if (getProcessStatus() == null) {
			return null;
		}
		ProcessStatus  processStatus = (ProcessStatus) EnumDataUtils.getEnumDictionaryByValue(ProcessStatus.class, getProcessStatus());
		return processStatus;
	}
	
	/** 
	 * 属性处理状态的setter方法 
	 */
	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}

	/**
	 * 属性处理状态赋值
	 */
	public void setEnumProcessStatus(ProcessStatus  processStatus) {
		if (processStatus != null) {
			this.processStatus = processStatus.getValue();
		}
	}
	
	/** 
	 * 属性处理开始时间的getter方法 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROCESSSTARTTIME")
	public Date getProcessStartTime() {
		return processStartTime;
	}

	/** 
	 * 属性处理开始时间的setter方法 
	 */
	public void setProcessStartTime(Date processStartTime) {
		this.processStartTime = processStartTime;
	}
	
	/**
	 * 属性处理结束时间的getter方法 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROCESSENDTIME")
	public Date getProcessEndTime() {
		return processEndTime;
	}

	/**
	 * 属性处理结束时间的setter方法 
	 */
	public void setProcessEndTime(Date processEndTime) {
		this.processEndTime = processEndTime;
	}

	/** 
	 * 属性处理描述的getter方法 
	 */
	@Column(name = "PROCESSSTATUSDESC")
	public String getProcessStatusDesc() {
		return processStatusDesc;
	}
	
	/** 
	 * 属性处理描述的setter方法 
	 */
	public void setProcessStatusDesc(String processStatusDesc) {
		this.processStatusDesc = processStatusDesc;
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
