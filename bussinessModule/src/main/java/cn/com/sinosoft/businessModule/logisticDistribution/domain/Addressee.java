package cn.com.sinosoft.businessModule.logisticDistribution.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类Addressee
 */
@Entity
@Table(name = "ADDRESSEE", uniqueConstraints = @UniqueConstraint(columnNames = "POLICYSERIALNO"))
public class Addressee implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性保单 */
	private InsurancePolicy insurancePolicy;

	/** 属性收件人姓名 */
	private String consigneeName;

	/** 属性联系电话 */
	private String telephone;

	/** 属性固定电话 */
	private String fixedPhone;

	/** 属性省 */
	private String province;

	/** 属性市 */
	private String city;

	/** 属性区/县 */
	private String county;

	/** 属性收件地址 */
	private String consigneeAddress;

	/** 属性邮政编码 */
	private String zipCode;

	/** 属性电子邮箱  */
	private String email;

	/** 属性创建时间 */
	private Date createTime = new Date();

	/** 属性更新时间 */
	private Date updateTime = new Date();

	/** 属性备注 */
	private String remark;

	/**
	 * 类Addressee的默认构造方法
	 */
	public Addressee() {
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
	 * 属性保单的getter方法
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POLICYSERIALNO", unique = true, nullable = false)
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
	 * 属性添加保单时同时将物流配送信息赋值给保单对象
	 */
	public void addInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
		if (getInsurancePolicy() != null && getInsurancePolicy().getAddressee() == null) {
			getInsurancePolicy().setAddressee(this);
		}
	}
	/**
	 * 属性收件人姓名的getter方法
	 */

	@Column(name = "CONSIGNEENAME")
	public String getConsigneeName() {
		return this.consigneeName;
	}

	/**
	 * 属性收件人姓名的setter方法
	 */
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	/**
	 * 属性联系电话的getter方法
	 */

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * 属性联系电话的setter方法
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 属性固定电话的getter方法
	 */

	@Column(name = "FIXEDPHONE")
	public String getFixedPhone() {
		return this.fixedPhone;
	}

	/**
	 * 属性固定电话的setter方法
	 */
	public void setFixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	/**
	 * 属性省的getter方法
	 */

	@Column(name = "PROVINCE")
	public String getProvince() {
		return this.province;
	}

	/**
	 * 属性省的setter方法
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 属性市的getter方法
	 */

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	/**
	 * 属性市的setter方法
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 属性区/县的getter方法
	 */

	@Column(name = "COUNTY")
	public String getCounty() {
		return this.county;
	}

	/**
	 * 属性区/县的setter方法
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * 属性收件地址的getter方法
	 */

	@Column(name = "CONSIGNEEADDRESS")
	public String getConsigneeAddress() {
		return this.consigneeAddress;
	}

	/**
	 * 属性收件地址的setter方法
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	/**
	 * 属性邮政编码的getter方法
	 */

	@Column(name = "ZIPCODE")
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * 属性邮政编码的setter方法
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 属性电子邮箱 的getter方法
	 */

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	/**
	 * 属性电子邮箱 的setter方法
	 */
	public void setEmail(String email) {
		this.email = email;
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

	/**
	 * 属性备注的getter方法
	 */

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 属性备注的setter方法
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
