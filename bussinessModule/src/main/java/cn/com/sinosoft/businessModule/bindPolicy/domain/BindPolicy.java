package cn.com.sinosoft.businessModule.bindPolicy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类BindPolicy
 */
@Entity
@Table(name = "BINDPOLICY")
public class BindPolicy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	private String serialNo;
	/** 客户号 */
	private String customerId;
	
	/** 保单号 */
	private String policySerialNumber;
	
	/**
	 * 类InsurancePolicy的默认构造方法
	 */
	public BindPolicy() {
	}

	/**
	 * 属性序号的getter方法
	 */
	@Id
	@Column(name = "SERIALNO", unique = false, nullable = false, length = 32)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	/**
	 * 属性客户号的getter方法
	 */
	@Column(name = "CUSTOMERID")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * 属性保单号的getter方法
	 */
	@Column(name = "POLICYSERIALNUMBER")
	public String getPolicySerialNumber() {
		return policySerialNumber;
	}

	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}
	

}
