package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;

// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GePayment
 */
@Entity
@Table(name = "GE_PAYMENT", uniqueConstraints = @UniqueConstraint(columnNames = "PAYMENTCODE"))
public class GePayment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ����id */
	private String paymentId;

	/** ����֧����ʽ���� */
	private String paymentCode;

	/** ����֧����ʽ���� */
	private String paymentName;

	/** ����֧����ʽlogoͼƬ·�� */
	private String logoImg;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** �����޸�ʱ�� */
	private Date updateTime;

	/** ���Ա�ע */
	private String remark;

	/** �������غ� */
	private String gateId;

	/** ����֧����ʽ��Ͷ�������������� */
	private List<GePaymentCity> gePaymentCities = new ArrayList<GePaymentCity>(
			0);

	/**
	 * ��GePayment��Ĭ�Ϲ��췽��
	 */
	public GePayment() {
	}

	
	

	

	/**
	 * ����֧����ʽ�����getter����
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
	 * ����֧����ʽ�����setter����
	 */
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	/**
	 * ����֧����ʽ���Ƶ�getter����
	 */

	@Column(name = "PAYMENTNAME")
	public String getPaymentName() {
		return this.paymentName;
	}

	/**
	 * ����֧����ʽ���Ƶ�setter����
	 */
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	/**
	 * ����֧����ʽlogoͼƬ·�� ��getter����
	 */

	@Column(name = "LOGOIMG")
	public String getLogoImg() {
		return this.logoImg;
	}

	/**
	 * ����֧����ʽlogoͼƬ·�� ��setter����
	 */
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
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
	 * �����޸�ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME")
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * �����޸�ʱ���setter����
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

	/**
	 * �������غŵ�getter����
	 */

	@Column(name = "GATEID")
	public String getGateId() {
		return this.gateId;
	}

	/**
	 * �������غŵ�setter����
	 */
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	/**
	 * ����gePaymentCities��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gePayment")
	public List<GePaymentCity> getGePaymentCities() {
		return this.gePaymentCities;
	}

	/**
	 * ����gePaymentCities��setter����
	 */
	public void setGePaymentCities(List<GePaymentCity> gePaymentCities) {
		this.gePaymentCities = gePaymentCities;
	}

}
