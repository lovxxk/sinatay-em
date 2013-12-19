package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;

// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO类GePayment
 */
@Entity
@Table(name = "GE_PAYMENT", uniqueConstraints = @UniqueConstraint(columnNames = "PAYMENTCODE"))
public class GePayment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性id */
	private String paymentId;

	/** 属性支付方式代码 */
	private String paymentCode;

	/** 属性支付方式名称 */
	private String paymentName;

	/** 属性支付方式logo图片路径 */
	private String logoImg;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性修改时间 */
	private Date updateTime;

	/** 属性备注 */
	private String remark;

	/** 属性网关号 */
	private String gateId;

	/** 属性支付方式与投保机构关联集合 */
	private List<GePaymentCity> gePaymentCities = new ArrayList<GePaymentCity>(
			0);

	/**
	 * 类GePayment的默认构造方法
	 */
	public GePayment() {
	}

	
	

	

	/**
	 * 属性支付方式代码的getter方法
	 */

	@Column(name = "PAYMENTCODE")
	public String getPaymentCode() {
		return this.paymentCode;
	}

	@Id
	@Column(name = "PAYMENTID", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	/**
	 * 属性支付方式代码的setter方法
	 */
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	/**
	 * 属性支付方式名称的getter方法
	 */

	@Column(name = "PAYMENTNAME")
	public String getPaymentName() {
		return this.paymentName;
	}

	/**
	 * 属性支付方式名称的setter方法
	 */
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	/**
	 * 属性支付方式logo图片路径 的getter方法
	 */

	@Column(name = "LOGOIMG")
	public String getLogoImg() {
		return this.logoImg;
	}

	/**
	 * 属性支付方式logo图片路径 的setter方法
	 */
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
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
	 * 属性修改时间的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 属性修改时间的setter方法
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

	/**
	 * 属性网关号的getter方法
	 */

	@Column(name = "GATEID")
	public String getGateId() {
		return this.gateId;
	}

	/**
	 * 属性网关号的setter方法
	 */
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	/**
	 * 属性gePaymentCities的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePayment")
	public List<GePaymentCity> getGePaymentCities() {
		return this.gePaymentCities;
	}

	/**
	 * 属性gePaymentCities的setter方法
	 */
	public void setGePaymentCities(List<GePaymentCity> gePaymentCities) {
		this.gePaymentCities = gePaymentCities;
	}

}
