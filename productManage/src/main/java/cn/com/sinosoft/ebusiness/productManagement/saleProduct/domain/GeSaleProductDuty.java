package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

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
 * POJO类GeSaleProductDuty
 */
@Entity
@Table(name = "GE_SALE_PRODUCTDUTY")
public class GeSaleProductDuty implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** 属性序号 */
	private String serialNo;

	/** 属性geSaleProduct */
	private GeSaleProduct geSaleProduct;

	/** 属性 */
	private GeSaleProductRisk geSaleProductRisk;

	/** 属性保险责任 */
	private GeDutyConfig geDutyConfig;

	/** 属性产品责任代码 */
	private String productDutyCode;

	/** 属性产品责任名称 */
	private String productDutyName;

	/** 属性保险期间 */
	private Integer period;

	/** 属性保险期间类型 */
	private String periodType;

	/** 属性保费 */
	private BigDecimal premium;

	/** 属性保额 */
	private BigDecimal insuredAmount;

	/** 属性最大风险保额 */
	private BigDecimal maxRiskInsuredAmount;

	/** 属性显示顺序 */
	private Integer seqIndex;

	/** 属性是否允许编辑 */
	private String isEditable;

	/** 属性销售标示 */
	private String saleFlag;

	/** 属性操作员编号 */
	private String operatorID;

	/** 属性创建时间 */
	private Date createTime;

	/** 属性更新时间 */
	private Date updateTime;

	/** 属性geSaleProDutyAttrAllowVals */
	private List<GeSaleProDutyAttrAllowVal> geSaleProDutyAttrAllowVals = new ArrayList<GeSaleProDutyAttrAllowVal>(
			0);

	/**
	 * 类GeSaleProductDuty的默认构造方法
	 */
	public GeSaleProductDuty() {
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
	 * 属性geSaleProduct的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COREPRODUCTCODE")
	public GeSaleProduct getGeSaleProduct() {
		return this.geSaleProduct;
	}
	/**
	 * 属性geSaleProduct的setter方法
	 */
	public void setGeSaleProduct(GeSaleProduct geSaleProduct) {
		this.geSaleProduct = geSaleProduct;
	}
	/**
	 * 属性的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTRISKSERIALNO")
	public GeSaleProductRisk getGeSaleProductRisk() {
		return this.geSaleProductRisk;
	}
	/**
	 * 属性的setter方法
	 */
	public void setGeSaleProductRisk(GeSaleProductRisk geSaleProductRisk) {
		this.geSaleProductRisk = geSaleProductRisk;
	}
	/**
	 * 属性保险责任的getter方法
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DUTYSERIALNO")
	public GeDutyConfig getGeDutyConfig() {
		return this.geDutyConfig;
	}
	/**
	 * 属性保险责任的setter方法
	 */
	public void setGeDutyConfig(GeDutyConfig geDutyConfig) {
		this.geDutyConfig = geDutyConfig;
	}
	/**
	 * 属性产品责任代码的getter方法
	 */

	@Column(name = "PRODUCTDUTYCODE")
	public String getProductDutyCode() {
		return this.productDutyCode;
	}
	/**
	 * 属性产品责任代码的setter方法
	 */
	public void setProductDutyCode(String productDutyCode) {
		this.productDutyCode = productDutyCode;
	}
	/**
	 * 属性产品责任名称的getter方法
	 */

	@Column(name = "PRODUCTDUTYNAME")
	public String getProductDutyName() {
		return this.productDutyName;
	}
	/**
	 * 属性产品责任名称的setter方法
	 */
	public void setProductDutyName(String productDutyName) {
		this.productDutyName = productDutyName;
	}
	/**
	 * 属性保险期间的getter方法
	 */

	@Column(name = "PERIOD")
	public Integer getPeriod() {
		return this.period;
	}
	/**
	 * 属性保险期间的setter方法
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	/**
	 * 属性保险期间类型的getter方法
	 */

	@Column(name = "PERIODTYPE")
	public String getPeriodType() {
		return this.periodType;
	}
	/**
	 * 属性保险期间类型的setter方法
	 */
	public void setPeriodType(String periodType) {
		this.periodType = periodType;
	}
	/**
	 * 属性保费的getter方法
	 */

	@Column(name = "PREMIUM")
	public BigDecimal getPremium() {
		return this.premium;
	}
	/**
	 * 属性保费的setter方法
	 */
	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}
	/**
	 * 属性保额的getter方法
	 */

	@Column(name = "INSUREDAMOUNT")
	public BigDecimal getInsuredAmount() {
		return this.insuredAmount;
	}
	/**
	 * 属性保额的setter方法
	 */
	public void setInsuredAmount(BigDecimal insuredAmount) {
		this.insuredAmount = insuredAmount;
	}
	/**
	 * 属性最大风险保额的getter方法
	 */

	@Column(name = "MAXRISKINSUREDAMOUNT")
	public BigDecimal getMaxRiskInsuredAmount() {
		return this.maxRiskInsuredAmount;
	}
	/**
	 * 属性最大风险保额的setter方法
	 */
	public void setMaxRiskInsuredAmount(BigDecimal maxRiskInsuredAmount) {
		this.maxRiskInsuredAmount = maxRiskInsuredAmount;
	}
	/**
	 * 属性显示顺序的getter方法
	 */

	@Column(name = "SEQINDEX")
	public Integer getSeqIndex() {
		return this.seqIndex;
	}
	/**
	 * 属性显示顺序的setter方法
	 */
	public void setSeqIndex(Integer seqIndex) {
		this.seqIndex = seqIndex;
	}
	/**
	 * 属性是否允许编辑的getter方法
	 */

	@Column(name = "ISEDITABLE")
	public String getIsEditable() {
		return this.isEditable;
	}
	/**
	 * 属性是否允许编辑的setter方法
	 */
	public void setIsEditable(String isEditable) {
		this.isEditable = isEditable;
	}
	/**
	 * 属性销售标示的getter方法
	 */

	@Column(name = "SALEFLAG")
	public String getSaleFlag() {
		return this.saleFlag;
	}
	/**
	 * 属性销售标示的setter方法
	 */
	public void setSaleFlag(String saleFlag) {
		this.saleFlag = saleFlag;
	}
	/**
	 * 属性操作员编号的getter方法
	 */

	@Column(name = "OPERATORID")
	public String getOperatorID() {
		return this.operatorID;
	}
	/**
	 * 属性操作员编号的setter方法
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
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
	 * 属性geSaleProDutyAttrAllowVals的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProductDuty")
	public List<GeSaleProDutyAttrAllowVal> getGeSaleProDutyAttrAllowVals() {
		return this.geSaleProDutyAttrAllowVals;
	}
	/**
	 * 属性geSaleProDutyAttrAllowVals的setter方法
	 */
	public void setGeSaleProDutyAttrAllowVals(
			List<GeSaleProDutyAttrAllowVal> geSaleProDutyAttrAllowVals) {
		this.geSaleProDutyAttrAllowVals = geSaleProDutyAttrAllowVals;
	}

}
