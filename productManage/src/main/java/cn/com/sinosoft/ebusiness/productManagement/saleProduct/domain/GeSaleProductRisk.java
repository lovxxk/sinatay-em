package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeRiskConfig;
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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO��GeSaleProductRisk
 */
@Entity
@Table(name = "GE_SALE_PRODUCTRISK")
public class GeSaleProductRisk implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����geSaleProduct */
	private GeSaleProduct geSaleProduct;

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

	/** ����geSaleProductDuties */
	private List<GeSaleProductDuty> geSaleProductDuties = new ArrayList<GeSaleProductDuty>(
			0);

	/**
	 * ��GeSaleProductRisk��Ĭ�Ϲ��췽��
	 */
	public GeSaleProductRisk() {
	}

	/**
	 * ������ŵ�getter����
	 */
	@Id
	@Column(name = "SERIALNO", unique = true, nullable = false, length = 50)
	@GeneratedValue(generator = "UUIDGenerator")
	@GenericGenerator(name = "UUIDGenerator", strategy = "uuid.hex")
	@JsonIgnore
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
	 * ����geSaleProduct��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE")
	@JsonIgnore
	public GeSaleProduct getGeSaleProduct() {
		return this.geSaleProduct;
	}
	/**
	 * ����geSaleProduct��setter����
	 */
	public void setGeSaleProduct(GeSaleProduct geSaleProduct) {
		this.geSaleProduct = geSaleProduct;
	}
	/**
	 * ���Ա������ֵ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RISKCODE")
	@JsonIgnore
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
	@JsonIgnore
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
	@JsonIgnore
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
	@JsonIgnore
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
	 * ����geSaleProductDuties��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProductRisk")
	@JsonIgnore
	public List<GeSaleProductDuty> getGeSaleProductDuties() {
		return this.geSaleProductDuties;
	}
	/**
	 * ����geSaleProductDuties��setter����
	 */
	public void setGeSaleProductDuties(
			List<GeSaleProductDuty> geSaleProductDuties) {
		this.geSaleProductDuties = geSaleProductDuties;
	}

}
