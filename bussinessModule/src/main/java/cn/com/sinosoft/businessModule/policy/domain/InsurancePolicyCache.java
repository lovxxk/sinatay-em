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

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��InsurancePolicy
 */
@Entity
@Table(name = "INSURANCEPOLICYCACHE")
public class InsurancePolicyCache implements java.io.Serializable {
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
	
	
	/** ��������ʱ�� */
	private Date requestTime;
	
	/** �������һ������ʱ�� */
	private Date lastRequestTime;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/**
	 * ��InsurancePolicyProcess��Ĭ�Ϲ��췽��
	 */
	public InsurancePolicyCache() {
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
	 * �������һ������ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTREQUESTTIME")
	public Date getLastRequestTime() {
		return lastRequestTime;
	}
	
	/**
	 * �������һ������ʱ���setter����
	 */
	public void setLastRequestTime(Date lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
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
