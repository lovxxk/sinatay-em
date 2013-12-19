package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeDutyConfig;
import java.math.BigDecimal;
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
 * POJO��GeSaleProductDuty
 */
@Entity
@Table(name = "GE_SALE_PRODUCTDUTY")
public class GeSaleProductDuty implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ����geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** ���� */
	private GeSaleProductRisk geSaleProductRisk;

	/** ���Ա������� */
	private GeDutyConfig geDutyConfig;

	/** ���Բ�Ʒ���δ��� */
	private String productDutyCode;

	/** ���Բ�Ʒ�������� */
	private String productDutyName;

	/** ���Ա����ڼ� */
	private Integer period;

	/** ���Ա����ڼ����� */
	private String periodType;

	/** ���Ա��� */
	private BigDecimal premium;

	/** ���Ա��� */
	private BigDecimal insuredAmount;

	/** ���������ձ��� */
	private BigDecimal maxRiskInsuredAmount;

	/** ������ʾ˳�� */
	private Integer seqIndex;

	/** �����Ƿ�����༭ */
	private String isEditable;

	/** �������۱�ʾ */
	private String saleFlag;

	/** ���Բ���Ա��� */
	private String operatorID;

	/** ���Դ���ʱ�� */
	private Date createTime;

	/** ���Ը���ʱ�� */
	private Date updateTime;

	/** ����geSaleProDutyAttrAllowVals */
	private List<GeSaleProDutyAttrAllowVal> geSaleProDutyAttrAllowVals = new ArrayList<GeSaleProDutyAttrAllowVal>(
			0);

	/**
	 * ��GeSaleProductDuty��Ĭ�Ϲ��췽��
	 */
	public GeSaleProductDuty() {
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
	 * ����geSaleProduct��getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE")
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
	 * ���Ե�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTRISKSERIALNO")
	public GeSaleProductRisk getGeSaleProductRisk() {
		return this.geSaleProductRisk;
	}
	/**
	 * ���Ե�setter����
	 */
	public void setGeSaleProductRisk(GeSaleProductRisk geSaleProductRisk) {
		this.geSaleProductRisk = geSaleProductRisk;
	}
	/**
	 * ���Ա������ε�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DUTYSERIALNO")
	public GeDutyConfig getGeDutyConfig() {
		return this.geDutyConfig;
	}
	/**
	 * ���Ա������ε�setter����
	 */
	public void setGeDutyConfig(GeDutyConfig geDutyConfig) {
		this.geDutyConfig = geDutyConfig;
	}
	/**
	 * ���Բ�Ʒ���δ����getter����
	 */

	@Column(name = "PRODUCTDUTYCODE")
	public String getProductDutyCode() {
		return this.productDutyCode;
	}
	/**
	 * ���Բ�Ʒ���δ����setter����
	 */
	public void setProductDutyCode(String productDutyCode) {
		this.productDutyCode = productDutyCode;
	}
	/**
	 * ���Բ�Ʒ�������Ƶ�getter����
	 */

	@Column(name = "PRODUCTDUTYNAME")
	public String getProductDutyName() {
		return this.productDutyName;
	}
	/**
	 * ���Բ�Ʒ�������Ƶ�setter����
	 */
	public void setProductDutyName(String productDutyName) {
		this.productDutyName = productDutyName;
	}
	/**
	 * ���Ա����ڼ��getter����
	 */

	@Column(name = "PERIOD")
	public Integer getPeriod() {
		return this.period;
	}
	/**
	 * ���Ա����ڼ��setter����
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	/**
	 * ���Ա����ڼ����͵�getter����
	 */

	@Column(name = "PERIODTYPE")
	public String getPeriodType() {
		return this.periodType;
	}
	/**
	 * ���Ա����ڼ����͵�setter����
	 */
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	/**
	 * ���Ա��ѵ�getter����
	 */

	@Column(name = "PREMIUM")
	public BigDecimal getPremium() {
		return this.premium;
	}
	/**
	 * ���Ա��ѵ�setter����
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	/**
	 * ���Ա����getter����
	 */

	@Column(name = "INSUREDAMOUNT")
	public BigDecimal getInsuredAmount() {
		return this.insuredAmount;
	}
	/**
	 * ���Ա����setter����
	 */
	public void setInsuredAmount(BigDecimal insuredAmount) {
		this.insuredAmount = insuredAmount;
	}
	/**
	 * ���������ձ����getter����
	 */

	@Column(name = "MAXRISKINSUREDAMOUNT")
	public BigDecimal getMaxRiskInsuredAmount() {
		return this.maxRiskInsuredAmount;
	}
	/**
	 * ���������ձ����setter����
	 */
	public void setMaxRiskInsuredAmount(BigDecimal maxRiskInsuredAmount) {
		this.maxRiskInsuredAmount = maxRiskInsuredAmount;
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
	 * �����Ƿ�����༭��getter����
	 */

	@Column(name = "ISEDITABLE")
	public String getIsEditable() {
		return this.isEditable;
	}
	/**
	 * �����Ƿ�����༭��setter����
	 */
	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
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
	 * ���Բ���Ա��ŵ�getter����
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}
	/**
	 * ���Բ���Ա��ŵ�setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
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
	 * ����geSaleProDutyAttrAllowVals��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProductDuty")
	public List<GeSaleProDutyAttrAllowVal> getGeSaleProDutyAttrAllowVals() {
		return this.geSaleProDutyAttrAllowVals;
	}
	/**
	 * ����geSaleProDutyAttrAllowVals��setter����
	 */
	public void setGeSaleProDutyAttrAllowVals(
			List<GeSaleProDutyAttrAllowVal> geSaleProDutyAttrAllowVals) {
		this.geSaleProDutyAttrAllowVals = geSaleProDutyAttrAllowVals;
	}

}
