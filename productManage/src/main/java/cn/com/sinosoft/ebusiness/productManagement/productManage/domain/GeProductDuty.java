package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeProductDuty
 */
@Entity
@Table(name = "GE_PRODUCTDUTY")
public class GeProductDuty implements java.io.Serializable {
	//��Ʒ����
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;

	/** ���Բ�Ʒ */
	private GeProductMain geProductMain;

	/** ���Բ�Ʒ���� */
	private GeProductRisk geProductRisk;

	/** ���Ա������� */
	private GeDutyConfig geDutyConfig;

	/** ���Բ�Ʒ���δ��� */
	private String productDutyCode;

	/** ���Բ�Ʒ�������� */
	private String productDutyName;

	/** ���Ա����ڼ� */
	private Integer period;   //

	/** ���Ա����ڼ����� */
	private String periodType;//

	/**  ���Ա���*/
	private BigDecimal premium;//

	/** ���Ա��� */
	private BigDecimal insuredAmount;//

	/** ���������ձ��� */
	private BigDecimal maxRiskInsuredAmount;//

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

	/** ����geProDutyAttrAllowVals */
	private List<GeProDutyAttrAllowVal> geProDutyAttrAllowVals = new ArrayList<GeProDutyAttrAllowVal>(
			0);

	/**
	 * ��GeProductDuty��Ĭ�Ϲ��췽��
	 */
	public GeProductDuty() {
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
	 * ���Բ�Ʒ���ֵ�getter����
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTRISKSERIALNO")
	public GeProductRisk getGeProductRisk() {
		return this.geProductRisk;
	}
	/**
	 * ���Բ�Ʒ���ֵ�setter����
	 */
	public void setGeProductRisk(GeProductRisk geProductRisk) {
		this.geProductRisk = geProductRisk;
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
	 * ����geProDutyAttrAllowVals��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductDuty")
	public List<GeProDutyAttrAllowVal> getGeProDutyAttrAllowVals() {
		return this.geProDutyAttrAllowVals;
	}
	/**
	 * ����geProDutyAttrAllowVals��setter����
	 */
	public void setGeProDutyAttrAllowVals(
			List<GeProDutyAttrAllowVal> geProDutyAttrAllowVals) {
		this.geProDutyAttrAllowVals = geProDutyAttrAllowVals;
	}

}
