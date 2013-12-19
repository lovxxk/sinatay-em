package cn.com.sinosoft.businessModule.bindPolicy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��BindPolicy
 */
@Entity
@Table(name = "BINDPOLICY")
public class BindPolicy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/** ���� */
	private String serialNo;
	/** �ͻ��� */
	private String customerId;
	
	/** ������ */
	private String policySerialNumber;
	
	/**
	 * ��InsurancePolicy��Ĭ�Ϲ��췽��
	 */
	public BindPolicy() {
	}

	/**
	 * ������ŵ�getter����
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
	 * ���Կͻ��ŵ�getter����
	 */
	@Column(name = "CUSTOMERID")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * ���Ա����ŵ�getter����
	 */
	@Column(name = "POLICYSERIALNUMBER")
	public String getPolicySerialNumber() {
		return policySerialNumber;
	}

	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}
	

}
