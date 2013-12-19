package cn.com.sinosoft.businessModule.logisticDistribution.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import cn.com.sinosoft.businessModule.policy.domain.InsurancePolicy;

/**
 * POJO��Addressee
 */
@Entity
@Table(name = "ADDRESSEE", uniqueConstraints = @UniqueConstraint(columnNames = "POLICYSERIALNO"))
public class Addressee implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Ա��� */
	private InsurancePolicy insurancePolicy;

	/** �����ռ������� */
	private String consigneeName;

	/** ������ϵ�绰 */
	private String telephone;

	/** ���Թ̶��绰 */
	private String fixedPhone;

	/** ����ʡ */
	private String province;

	/** ������ */
	private String city;

	/** ������/�� */
	private String county;

	/** �����ռ���ַ */
	private String consigneeAddress;

	/** ������������ */
	private String zipCode;

	/** ���Ե�������  */
	private String email;

	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();

	/** ���Ա�ע */
	private String remark;

	/**
	 * ��Addressee��Ĭ�Ϲ��췽��
	 */
	public Addressee() {
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
	 * ���Ա�����getter����
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POLICYSERIALNO", unique = true, nullable = false)
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
	 * ������ӱ���ʱͬʱ������������Ϣ��ֵ����������
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && getInsurancePolicy().getAddressee() == null) {
			getInsurancePolicy().setAddressee(this);
		}
	}
	/**
	 * �����ռ���������getter����
	 */

	@Column(name = "CONSIGNEENAME")
	public String getConsigneeName() {
		return this.consigneeName;
	}

	/**
	 * �����ռ���������setter����
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	/**
	 * ������ϵ�绰��getter����
	 */

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * ������ϵ�绰��setter����
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * ���Թ̶��绰��getter����
	 */

	@Column(name = "FIXEDPHONE")
	public String getFixedPhone() {
		return this.fixedPhone;
	}

	/**
	 * ���Թ̶��绰��setter����
	 */
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	/**
	 * ����ʡ��getter����
	 */

	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}

	/**
	 * ����ʡ��setter����
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * �����е�getter����
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	/**
	 * �����е�setter����
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * ������/�ص�getter����
	 */

	@Column(name = "COUNTY")
	public String getCounty() {
		return this.county;
	}

	/**
	 * ������/�ص�setter����
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * �����ռ���ַ��getter����
	 */

	@Column(name = "CONSIGNEEADDRESS")
	public String getConsigneeAddress() {
		return this.consigneeAddress;
	}

	/**
	 * �����ռ���ַ��setter����
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	/**
	 * �������������getter����
	 */

	@Column(name = "ZIPCODE")
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * �������������setter����
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * ���Ե������� ��getter����
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * ���Ե������� ��setter����
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/**
	 * ���Ա�ע��getter����
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * ���Ա�ע��setter����
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
