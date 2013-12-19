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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeProductRisk
 */
@Entity
@Table(name = "GE_PRODUCTRISK")
public class GeProductRisk implements java.io.Serializable {
	// ����
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ���Ա������� */
	private GeRiskConfig geRiskConfig;

	/** ���Բ�Ʒ���ִ��� */
	private String productRiskCode;

	/** ���Բ�Ʒ�������� */
	private String productRiskName;

	/** ������ʾ˳�� */
	private Integer seqIndex;

	/** �����Ƿ���ʾ��Ʒ���� */
	private String isshowProductDuty;

	/** �������۱�ʾ */
	private String saleFlag;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ���Բ�Ʒ���� */
	private List<GeProductDuty> geProductDuties = new ArrayList<GeProductDuty>(
			0);

	/**
	 * ��GeProductRisk��Ĭ�Ϲ��췽��
	 */
	public GeProductRisk() {
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
	 * ���Բ�Ʒ��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE")
	public GeProductMain getGeProductMain() {
		return this.geProductMain;
	}
	/**
	 * ���Բ�Ʒ��setter����
	 */
	public void setGeProductMain(GeProductMain geProductMain) {
		this.geProductMain = geProductMain;
	}
	/**
	 * ���Ա������ֵ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RISKCODE")
	public GeRiskConfig getGeRiskConfig() {
		return this.geRiskConfig;
	}
	/**
	 * ���Ա������ֵ�setter����
	 */
	public void setGeRiskConfig(GeRiskConfig geRiskConfig) {
		this.geRiskConfig = geRiskConfig;
	}
	/**
	 * ���Բ�Ʒ���ִ����getter����
	 */

	@Column(name = "PRODUCTRISKCODE")
	public String getProductRiskCode() {
		return this.productRiskCode;
	}
	/**
	 * ���Բ�Ʒ���ִ����setter����
	 */
	public void setProductRiskCode(String productRiskCode) {
		this.productRiskCode = productRiskCode;
	}
	/**
	 * ���Բ�Ʒ�������Ƶ�getter����
	 */

	@Column(name = "PRODUCTRISKNAME")
	public String getProductRiskName() {
		return this.productRiskName;
	}
	/**
	 * ���Բ�Ʒ�������Ƶ�setter����
	 */
	public void setProductRiskName(String productRiskName) {
		this.productRiskName = productRiskName;
	}
	/**
	 * ������ʾ˳���getter����
	 */

	@Column(name = "SEQINDEX")
	public Integer getSeqIndex() {
		return this.seqIndex;
	}
	/**
	 * ������ʾ˳���setter����
	 */
	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
	}
	/**
	 * �����Ƿ���ʾ��Ʒ���ε�getter����
	 */

	@Column(name = "ISSHOWPRODUCTDUTY")
	public String getIsshowProductDuty() {
		return this.isshowProductDuty;
	}
	/**
	 * �����Ƿ���ʾ��Ʒ���ε�setter����
	 */
	public void setIsshowProductDuty(String isshowProductDuty) {
		this.isshowProductDuty = isshowProductDuty;
	}
	/**
	 * �������۱�ʾ��getter����
	 */

	@Column(name = "SALEFLAG")
	public String getSaleFlag() {
		return this.saleFlag;
	}
	/**
	 * �������۱�ʾ��setter����
	 */
	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
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
	 * ���Բ�Ʒ���ε�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductRisk")
	public List<GeProductDuty> getGeProductDuties() {
		return this.geProductDuties;
	}
	/**
	 * ���Բ�Ʒ���ε�setter����
	 */
	public void setGeProductDuties(List<GeProductDuty> geProductDuties) {
		this.geProductDuties = geProductDuties;
	}

}
