package cn.com.sinosoft.businessModule.policy.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��InsurancePolicy
 */
@Entity
@Table(name = "INSURANCEPOLICYPROCESS")
public class InsurancePolicyProcess implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;
	
	/** ���Ժ������������ţ��Ա������ţ� */
	private String merchantOrderNumber;

	/** ���Ա����� */
	private String policySerialNumber;

	/** ���Ժ����������� */
	private String institutionCode;

	/** ���Ժ����������� */
	private String institutionName;
	
	/** ���Ժ����������� */
	private String serviceName;
	
	/** ���Լ���״̬ */
	private Integer lockStatus;
	
	/** ���Լ���ԭ�� */
	private String lockedReason;
	
	/** ���Դ���״̬ */
	private Integer processStatus;
	
	/** ���Դ���ʼʱ�� */
	private Date processStartTime;
	
	/** ���Դ������ʱ�� */
	private Date processEndTime;
	
	/** ���Դ���״̬���� */
	private String processStatusDesc;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/**
	 * ��InsurancePolicyProcess��Ĭ�Ϲ��췽��
	 */
	public InsurancePolicyProcess() {
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
	 * ���Ժ������������getter���� 
	 */
	@Column(name = "INSTITUTIONCODE")
	public String getInstitutionCode() {
		return institutionCode;
	}
	
	/** 
	 * ���Ժ������������setter���� 
	 */
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	/** 
	 * ���Ժ����������Ƶ�getter���� 
	 */
	@Column(name = "INSTITUTIONNAME")
	public String getInstitutionName() {
		return institutionName;
	}
	
	/** 
	 * ���Ժ����������Ƶ�setter���� 
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
	/**
	 * ���Է������Ƶ�getter����
	 */
	@Column(name = "SERVICENAME")
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * ���Է������Ƶ�setter����
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/** 
	 * ���Լ���״̬��getter���� 
	 */
	@Column(name = "LOCKSTATUS")
	public Integer getLockStatus() {
		return lockStatus;
	}
	
	/**
	 * ���Դ���״̬ö�����getter����
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
	 * ���Լ���״̬��getter���� 
	 */
	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	/**
	 * ���Լ���״̬��ֵ
	 */
	public void setEnumLockStatus(LockStatus  lockStatus) {
		if (lockStatus != null) {
			this.processStatus = lockStatus.getValue();
		}
	}
	
	/** 
	 * ���Լ���ԭ���getter���� 
	 */
	@Column(name = "LOCKEDREASON")
	public String getLockedReason() {
		return lockedReason;
	}
	
	/** 
	 * ���Լ���ԭ���setter���� 
	 */
	public void setLockedReason(String lockedReason) {
		this.lockedReason = lockedReason;
	}

	/** 
	 * ���Դ���״̬��getter���� 
	 */
	@Column(name = "PROCESSSTATUS")
	public Integer getProcessStatus() {
		return processStatus;
	}

	/**
	 * ���Դ���״̬ö�����getter����
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
	 * ���Դ���״̬��setter���� 
	 */
	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}

	/**
	 * ���Դ���״̬��ֵ
	 */
	public void setEnumProcessStatus(ProcessStatus  processStatus) {
		if (processStatus != null) {
			this.processStatus = processStatus.getValue();
		}
	}
	
	/** 
	 * ���Դ���ʼʱ���getter���� 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROCESSSTARTTIME")
	public Date getProcessStartTime() {
		return processStartTime;
	}

	/** 
	 * ���Դ���ʼʱ���setter���� 
	 */
	public void setProcessStartTime(Date processStartTime) {
		this.processStartTime = processStartTime;
	}
	
	/**
	 * ���Դ������ʱ���getter���� 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PROCESSENDTIME")
	public Date getProcessEndTime() {
		return processEndTime;
	}

	/**
	 * ���Դ������ʱ���setter���� 
	 */
	public void setProcessEndTime(Date processEndTime) {
		this.processEndTime = processEndTime;
	}

	/** 
	 * ���Դ���������getter���� 
	 */
	@Column(name = "PROCESSSTATUSDESC")
	public String getProcessStatusDesc() {
		return processStatusDesc;
	}
	
	/** 
	 * ���Դ���������setter���� 
	 */
	public void setProcessStatusDesc(String processStatusDesc) {
		this.processStatusDesc = processStatusDesc;
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
