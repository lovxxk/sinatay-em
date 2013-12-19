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

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类InsurancePolicy
 */
@Entity
@Table(name = "INSURANCEPOLICYCACHE")
public class InsurancePolicyCache implements java.io.Serializable {
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
	
	
	/** 属性请求时间 */
	private Date requestTime;
	
	/** 属性最近一次请求时间 */
	private Date lastRequestTime;
	
	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();
	
	/**
	 * 类InsurancePolicyProcess的默认构造方法
	 */
	public InsurancePolicyCache() {
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
	 * 属性请求时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQUESTTIME")
	public Date getRequestTime() {
		return requestTime;
	}
	
	/**
	 * 属性请求时间的setter方法
	 */
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
	/**
	 * 属性最后一次请求时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTREQUESTTIME")
	public Date getLastRequestTime() {
		return lastRequestTime;
	}
	
	/**
	 * 属性最后一次请求时间的setter方法
	 */
	public void setLastRequestTime(Date lastRequestTime) {
		this.lastRequestTime = lastRequestTime;
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
