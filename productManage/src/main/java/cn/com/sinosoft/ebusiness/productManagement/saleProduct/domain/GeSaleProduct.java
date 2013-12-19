package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// 采用工具 Hibernate Tools 3.2.4.GA (sinosoft version) 生成，请勿手工修改。

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.com.sinosoft.ebusiness.productManagement.productManage.domain.GeProductPolicyDisplayConfig;

/**
 * POJO类GeSaleProduct
 */
@Entity
@Table(name = "GE_SALE_PRODUCT")
public class GeSaleProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	
	// 是否上线
	private String isOnline;
	
	private String runintTaskId; // 正在运行的节点id
	
	private String workFlowId;//工作流id
	
	/** 属性产品代码 */
	private String coreProductCode;

	/** 属性产品名称 */
	private String productName;

	/** 属性产品简称 */
	private String coreProductSimpleName;

	/** 属性业务领域 */
	private String businessArea;

	/** 属性最小被保人个数 */
	private String minInsuredNum;

	/** 属性最大被保人个数 */
	private String maxInsuredNum;

	/** 属性最小受益人个数 */
	private String minBeneficiaryNum;

	/** 属性最大受益人个数 */
	private String maxBeneficiaryNum;

	/** 属性连带被保人标志 */
	private String isSupportPIns;

	/** 属性最小连带被保人个数 */
	private String minPInsuredNum;

	/** 属性最大连带被保人个数 */
	private String maxPInsuredNum;

	/** 属性纸质保单配置项 */
	private String isPaper;

	/** 属性法定受益人标记 */
	private String isSupportBeneficiary;

	/** 属性紧急联系人标记 */
	private String isSupportEmergency;

	/** 属性标的个数 */
	private String isSupportTarget;

	/** 属性生效日期类型 */
	private String effectDateType;

	/** 属性生效起始日期 */
	private Date specifyStartDate;

	/** 属性生效终止日期  */
	private Date specifyEndDate;

	/** 属性生效延迟天数 */
	private String delayDays;

	/** 属性最小限制天数 */
	private String minEffectDateLimited;

	/** 属性最大限制天数  */
	private String maxEffectDateLimited;

	/** 属性产品状态 */
	private String productStatus;

	/** 属性产品销售流程 */
	private String productFlow;

	/** 属性创建日期 */
	private Date createDate;

	/** 属性最近更新日期 */
	private Date updateDate;

	/** 属性是否限定同业保额 */
	private String limitSameIndInsuredAmount;

	/** 属性同业保额最大值 */
	private BigDecimal sameIndInsuredAmountMax;

	/** 属性特别约定 */
	private String specialAgreement;

	/** 属性操作员编号 */
	private String operatorID;

	/** 属性备注 */
	private String remark;

	/** 属性是否支持发票 */
	private String isInvoice;

	/** 属性geSaleProExtraInfos */
	private List<GeSaleProExtraInfo> geSaleProExtraInfos = new ArrayList<GeSaleProExtraInfo>(
			0);

	/** 属性geSaleProWebFlows */
	private List<GeSaleProWebFlow> geSaleProWebFlows = new ArrayList<GeSaleProWebFlow>(
			0);

	/** 属性geSaleProInformbooks */
	private List<GeSaleProInformbook> geSaleProInformbooks = new ArrayList<GeSaleProInformbook>(
			0);

	/** 属性geSaleProInsuredConfigs */
	private List<GeSaleProInsuredConfig> geSaleProInsuredConfigs = new ArrayList<GeSaleProInsuredConfig>(
			0);

	/** 属性geSaleProProDepts */
	private List<GeSaleProProDept> geSaleProProDepts = new ArrayList<GeSaleProProDept>(
			0);

	/** 属性geSaleProWebFlowPages */
	private List<GeSaleProWebFlowPage> geSaleProWebFlowPages = new ArrayList<GeSaleProWebFlowPage>(
			0);

	/** 属性geSaleProWebFlowPageElements */
	private List<GeSaleProWebFlowPageElement> geSaleProWebFlowPageElements = new ArrayList<GeSaleProWebFlowPageElement>(
			0);

	/** 属性geSaleProductDuties */
	private List<GeSaleProductDuty> geSaleProductDuties = new ArrayList<GeSaleProductDuty>(
			0);

	/** 属性geSaleProApplicantConfigs */
	private List<GeSaleProApplicantConfig> geSaleProApplicantConfigs = new ArrayList<GeSaleProApplicantConfig>(
			0);

	/** 属性geSaleProAddresseeConfigs */
	private List<GeSaleProAddresseeConfig> geSaleProAddresseeConfigs = new ArrayList<GeSaleProAddresseeConfig>(
			0);

	/** 属性geSaleProAttrAllowValueses */
	private List<GeSaleProAttrAllowValues> geSaleProAttrAllowValueses = new ArrayList<GeSaleProAttrAllowValues>(
			0);

	/** 属性geSaleProductRisks */
	private List<GeSaleProductRisk> geSaleProductRisks = new ArrayList<GeSaleProductRisk>(
			0);

	/** 属性geSaleProBeneficiaryConfigs */
	private List<GeSaleProBeneficiaryConfig> geSaleProBeneficiaryConfigs = new ArrayList<GeSaleProBeneficiaryConfig>(
			0);

	/** 属性geSaleProEmergencyConfigs */
	private List<GeSaleProEmergencyConfig> geSaleProEmergencyConfigs = new ArrayList<GeSaleProEmergencyConfig>(
			0);


	/** 属性geSaleProPolicyDisplayConfigs */
	private List<GeSaleProPolicyDisplayConfig> geSaleProPolicyDisConfigs = new ArrayList<GeSaleProPolicyDisplayConfig>(
			0);
	
	
	/**
	 * 类GeSaleProduct的默认构造方法
	 */
	public GeSaleProduct() {
	}

	/**
	 * 属性产品代码的getter方法
	 */
	@Id
	@Column(name = "COREPRODUCTCODE")
	public String getCoreProductCode() {
		return this.coreProductCode;
	}
	/**
	 * 属性产品代码的setter方法
	 */
	public void setCoreProductCode(String coreProductCode) {
		this.coreProductCode = coreProductCode;
	}
	/**
	 * 属性产品名称的getter方法
	 */

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return this.productName;
	}
	/**
	 * 属性产品名称的setter方法
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 属性产品简称的getter方法
	 */

	@Column(name = "COREPRODUCTSIMPLENAME")
	public String getCoreProductSimpleName() {
		return this.coreProductSimpleName;
	}
	/**
	 * 属性产品简称的setter方法
	 */
	public void setCoreProductSimpleName(String coreProductSimpleName) {
		this.coreProductSimpleName = coreProductSimpleName;
	}
	/**
	 * 属性业务领域的getter方法
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}
	/**
	 * 属性业务领域的setter方法
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	/**
	 * 属性最小被保人个数的getter方法
	 */

	@Column(name = "MININSUREDNUM")
	public String getMinInsuredNum() {
		return this.minInsuredNum;
	}
	/**
	 * 属性最小被保人个数的setter方法
	 */
	public void setMinInsuredNum(String minInsuredNum) {
		this.minInsuredNum = minInsuredNum;
	}
	/**
	 * 属性最大被保人个数的getter方法
	 */

	@Column(name = "MAXINSUREDNUM")
	public String getMaxInsuredNum() {
		return this.maxInsuredNum;
	}
	/**
	 * 属性最大被保人个数的setter方法
	 */
	public void setMaxInsuredNum(String maxInsuredNum) {
		this.maxInsuredNum = maxInsuredNum;
	}
	/**
	 * 属性最小受益人个数的getter方法
	 */

	@Column(name = "MINBENEFICIARYNUM")
	public String getMinBeneficiaryNum() {
		return this.minBeneficiaryNum;
	}
	/**
	 * 属性最小受益人个数的setter方法
	 */
	public void setMinBeneficiaryNum(String minBeneficiaryNum) {
		this.minBeneficiaryNum = minBeneficiaryNum;
	}
	/**
	 * 属性最大受益人个数的getter方法
	 */

	@Column(name = "MAXBENEFICIARYNUM")
	public String getMaxBeneficiaryNum() {
		return this.maxBeneficiaryNum;
	}
	/**
	 * 属性最大受益人个数的setter方法
	 */
	public void setMaxBeneficiaryNum(String maxBeneficiaryNum) {
		this.maxBeneficiaryNum = maxBeneficiaryNum;
	}
	/**
	 * 属性连带被保人标志的getter方法
	 */

	@Column(name = "ISSUPPORTPINS")
	public String getIsSupportPIns() {
		return this.isSupportPIns;
	}
	/**
	 * 属性连带被保人标志的setter方法
	 */
	public void setIsSupportPIns(String isSupportPIns) {
		this.isSupportPIns = isSupportPIns;
	}
	/**
	 * 属性最小连带被保人个数的getter方法
	 */

	@Column(name = "MINPINSUREDNUM")
	public String getMinPInsuredNum() {
		return this.minPInsuredNum;
	}
	/**
	 * 属性最小连带被保人个数的setter方法
	 */
	public void setMinPInsuredNum(String minPInsuredNum) {
		this.minPInsuredNum = minPInsuredNum;
	}
	/**
	 * 属性最大连带被保人个数的getter方法
	 */

	@Column(name = "MAXPINSUREDNUM")
	public String getMaxPInsuredNum() {
		return this.maxPInsuredNum;
	}
	/**
	 * 属性最大连带被保人个数的setter方法
	 */
	public void setMaxPInsuredNum(String maxPInsuredNum) {
		this.maxPInsuredNum = maxPInsuredNum;
	}
	/**
	 * 属性纸质保单配置项的getter方法
	 */

	@Column(name = "ISPAPER")
	public String getIsPaper() {
		return this.isPaper;
	}
	/**
	 * 属性纸质保单配置项的setter方法
	 */
	public void setIsPaper(String isPaper) {
		this.isPaper = isPaper;
	}
	/**
	 * 属性法定受益人标记的getter方法
	 */

	@Column(name = "ISSUPPORTBENEFICIARY")
	public String getIsSupportBeneficiary() {
		return this.isSupportBeneficiary;
	}
	/**
	 * 属性法定受益人标记的setter方法
	 */
	public void setIsSupportBeneficiary(String isSupportBeneficiary) {
		this.isSupportBeneficiary = isSupportBeneficiary;
	}
	/**
	 * 属性紧急联系人标记的getter方法
	 */

	@Column(name = "ISSUPPORTEMERGENCY")
	public String getIsSupportEmergency() {
		return this.isSupportEmergency;
	}
	/**
	 * 属性紧急联系人标记的setter方法
	 */
	public void setIsSupportEmergency(String isSupportEmergency) {
		this.isSupportEmergency = isSupportEmergency;
	}
	/**
	 * 属性标的个数的getter方法
	 */

	@Column(name = "ISSUPPORTTARGET")
	public String getIsSupportTarget() {
		return this.isSupportTarget;
	}
	/**
	 * 属性标的个数的setter方法
	 */
	public void setIsSupportTarget(String isSupportTarget) {
		this.isSupportTarget = isSupportTarget;
	}
	/**
	 * 属性生效日期类型的getter方法
	 */

	@Column(name = "EFFECTDATETYPE")
	public String getEffectDateType() {
		return this.effectDateType;
	}
	/**
	 * 属性生效日期类型的setter方法
	 */
	public void setEffectDateType(String effectDateType) {
		this.effectDateType = effectDateType;
	}
	/**
	 * 属性生效起始日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SPECIFYSTARTDATE")
	public Date getSpecifyStartDate() {
		return this.specifyStartDate;
	}
	/**
	 * 属性生效起始日期的setter方法
	 */
	public void setSpecifyStartDate(Date specifyStartDate) {
		this.specifyStartDate = specifyStartDate;
	}
	/**
	 * 属性生效终止日期 的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SPECIFYENDDATE")
	public Date getSpecifyEndDate() {
		return this.specifyEndDate;
	}
	/**
	 * 属性生效终止日期 的setter方法
	 */
	public void setSpecifyEndDate(Date specifyEndDate) {
		this.specifyEndDate = specifyEndDate;
	}
	/**
	 * 属性生效延迟天数的getter方法
	 */

	@Column(name = "DELAYDAYS")
	public String getDelayDays() {
		return this.delayDays;
	}
	/**
	 * 属性生效延迟天数的setter方法
	 */
	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}
	/**
	 * 属性最小限制天数的getter方法
	 */

	@Column(name = "MINEFFECTDATELIMITED")
	public String getMinEffectDateLimited() {
		return this.minEffectDateLimited;
	}
	/**
	 * 属性最小限制天数的setter方法
	 */
	public void setMinEffectDateLimited(String minEffectDateLimited) {
		this.minEffectDateLimited = minEffectDateLimited;
	}
	/**
	 * 属性最大限制天数 的getter方法
	 */

	@Column(name = "MAXEFFECTDATELIMITED")
	public String getMaxEffectDateLimited() {
		return this.maxEffectDateLimited;
	}
	/**
	 * 属性最大限制天数 的setter方法
	 */
	public void setMaxEffectDateLimited(String maxEffectDateLimited) {
		this.maxEffectDateLimited = maxEffectDateLimited;
	}
	/**
	 * 属性产品状态的getter方法
	 */

	@Column(name = "PRODUCTSTATUS")
	public String getProductStatus() {
		return this.productStatus;
	}
	/**
	 * 属性产品状态的setter方法
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	/**
	 * 属性产品销售流程的getter方法
	 */

	@Column(name = "PRODUCTFLOW")
	public String getProductFlow() {
		return this.productFlow;
	}
	/**
	 * 属性产品销售流程的setter方法
	 */
	public void setProductFlow(String productFlow) {
		this.productFlow = productFlow;
	}
	/**
	 * 属性创建日期的getter方法
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * 属性创建日期的setter方法
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 属性最近更新日期的getter方法
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}
	/**
	 * 属性最近更新日期的setter方法
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 属性是否限定同业保额的getter方法
	 */

	@Column(name = "LIMITSAMEINDINSUREDAMOUNT")
	public String getLimitSameIndInsuredAmount() {
		return this.limitSameIndInsuredAmount;
	}
	/**
	 * 属性是否限定同业保额的setter方法
	 */
	public void setLimitSameIndInsuredAmount(String limitSameIndInsuredAmount) {
		this.limitSameIndInsuredAmount = limitSameIndInsuredAmount;
	}
	/**
	 * 属性同业保额最大值的getter方法
	 */

	@Column(name = "SAMEINDINSUREDAMOUNTMAX")
	public BigDecimal getSameIndInsuredAmountMax() {
		return this.sameIndInsuredAmountMax;
	}
	/**
	 * 属性同业保额最大值的setter方法
	 */
	public void setSameIndInsuredAmountMax(BigDecimal sameIndInsuredAmountMax) {
		this.sameIndInsuredAmountMax = sameIndInsuredAmountMax;
	}
	/**
	 * 属性特别约定的getter方法
	 */

	@Column(name = "SPECIALAGREEMENT")
	public String getSpecialAgreement() {
		return this.specialAgreement;
	}
	/**
	 * 属性特别约定的setter方法
	 */
	public void setSpecialAgreement(String specialAgreement) {
		this.specialAgreement = specialAgreement;
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
	 * 属性是否支持发票的getter方法
	 */

	@Column(name = "ISINVOICE")
	public String getIsInvoice() {
		return this.isInvoice;
	}
	/**
	 * 属性是否支持发票的setter方法
	 */
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}
	/**
	 * 属性geSaleProExtraInfos的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProExtraInfo> getGeSaleProExtraInfos() {
		return this.geSaleProExtraInfos;
	}
	/**
	 * 属性geSaleProExtraInfos的setter方法
	 */
	public void setGeSaleProExtraInfos(
			List<GeSaleProExtraInfo> geSaleProExtraInfos) {
		this.geSaleProExtraInfos = geSaleProExtraInfos;
	}
	/**
	 * 属性geSaleProWebFlows的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProWebFlow> getGeSaleProWebFlows() {
		return this.geSaleProWebFlows;
	}
	/**
	 * 属性geSaleProWebFlows的setter方法
	 */
	public void setGeSaleProWebFlows(List<GeSaleProWebFlow> geSaleProWebFlows) {
		this.geSaleProWebFlows = geSaleProWebFlows;
	}
	/**
	 * 属性geSaleProInformbooks的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProInformbook> getGeSaleProInformbooks() {
		return this.geSaleProInformbooks;
	}
	/**
	 * 属性geSaleProInformbooks的setter方法
	 */
	public void setGeSaleProInformbooks(
			List<GeSaleProInformbook> geSaleProInformbooks) {
		this.geSaleProInformbooks = geSaleProInformbooks;
	}
	/**
	 * 属性geSaleProInsuredConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProInsuredConfig> getGeSaleProInsuredConfigs() {
		return this.geSaleProInsuredConfigs;
	}
	/**
	 * 属性geSaleProInsuredConfigs的setter方法
	 */
	public void setGeSaleProInsuredConfigs(
			List<GeSaleProInsuredConfig> geSaleProInsuredConfigs) {
		this.geSaleProInsuredConfigs = geSaleProInsuredConfigs;
	}
	/**
	 * 属性geSaleProProDepts的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProProDept> getGeSaleProProDepts() {
		return this.geSaleProProDepts;
	}
	/**
	 * 属性geSaleProProDepts的setter方法
	 */
	public void setGeSaleProProDepts(List<GeSaleProProDept> geSaleProProDepts) {
		this.geSaleProProDepts = geSaleProProDepts;
	}
	/**
	 * 属性geSaleProWebFlowPages的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProWebFlowPage> getGeSaleProWebFlowPages() {
		return this.geSaleProWebFlowPages;
	}
	/**
	 * 属性geSaleProWebFlowPages的setter方法
	 */
	public void setGeSaleProWebFlowPages(
			List<GeSaleProWebFlowPage> geSaleProWebFlowPages) {
		this.geSaleProWebFlowPages = geSaleProWebFlowPages;
	}
	/**
	 * 属性geSaleProWebFlowPageElements的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProWebFlowPageElement> getGeSaleProWebFlowPageElements() {
		return this.geSaleProWebFlowPageElements;
	}
	/**
	 * 属性geSaleProWebFlowPageElements的setter方法
	 */
	public void setGeSaleProWebFlowPageElements(
			List<GeSaleProWebFlowPageElement> geSaleProWebFlowPageElements) {
		this.geSaleProWebFlowPageElements = geSaleProWebFlowPageElements;
	}
	/**
	 * 属性geSaleProductDuties的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProductDuty> getGeSaleProductDuties() {
		return this.geSaleProductDuties;
	}
	/**
	 * 属性geSaleProductDuties的setter方法
	 */
	public void setGeSaleProductDuties(
			List<GeSaleProductDuty> geSaleProductDuties) {
		this.geSaleProductDuties = geSaleProductDuties;
	}
	/**
	 * 属性geSaleProApplicantConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProApplicantConfig> getGeSaleProApplicantConfigs() {
		return this.geSaleProApplicantConfigs;
	}
	/**
	 * 属性geSaleProApplicantConfigs的setter方法
	 */
	public void setGeSaleProApplicantConfigs(
			List<GeSaleProApplicantConfig> geSaleProApplicantConfigs) {
		this.geSaleProApplicantConfigs = geSaleProApplicantConfigs;
	}
	/**
	 * 属性geSaleProAddresseeConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProAddresseeConfig> getGeSaleProAddresseeConfigs() {
		return this.geSaleProAddresseeConfigs;
	}
	/**
	 * 属性geSaleProAddresseeConfigs的setter方法
	 */
	public void setGeSaleProAddresseeConfigs(
			List<GeSaleProAddresseeConfig> geSaleProAddresseeConfigs) {
		this.geSaleProAddresseeConfigs = geSaleProAddresseeConfigs;
	}
	/**
	 * 属性geSaleProAttrAllowValueses的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProAttrAllowValues> getGeSaleProAttrAllowValueses() {
		return this.geSaleProAttrAllowValueses;
	}
	/**
	 * 属性geSaleProAttrAllowValueses的setter方法
	 */
	public void setGeSaleProAttrAllowValueses(
			List<GeSaleProAttrAllowValues> geSaleProAttrAllowValueses) {
		this.geSaleProAttrAllowValueses = geSaleProAttrAllowValueses;
	}
	/**
	 * 属性geSaleProductRisks的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProductRisk> getGeSaleProductRisks() {
		return this.geSaleProductRisks;
	}
	/**
	 * 属性geSaleProductRisks的setter方法
	 */
	public void setGeSaleProductRisks(List<GeSaleProductRisk> geSaleProductRisks) {
		this.geSaleProductRisks = geSaleProductRisks;
	}
	/**
	 * 属性geSaleProBeneficiaryConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProBeneficiaryConfig> getGeSaleProBeneficiaryConfigs() {
		return this.geSaleProBeneficiaryConfigs;
	}
	/**
	 * 属性geSaleProBeneficiaryConfigs的setter方法
	 */
	public void setGeSaleProBeneficiaryConfigs(
			List<GeSaleProBeneficiaryConfig> geSaleProBeneficiaryConfigs) {
		this.geSaleProBeneficiaryConfigs = geSaleProBeneficiaryConfigs;
	}
	/**
	 * 属性geSaleProEmergencyConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProEmergencyConfig> getGeSaleProEmergencyConfigs() {
		return this.geSaleProEmergencyConfigs;
	}
	/**
	 * 属性geSaleProEmergencyConfigs的setter方法
	 */
	public void setGeSaleProEmergencyConfigs(
			List<GeSaleProEmergencyConfig> geSaleProEmergencyConfigs) {
		this.geSaleProEmergencyConfigs = geSaleProEmergencyConfigs;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProPolicyDisplayConfig> getGeSaleProPolicyDisConfigs() {
		return geSaleProPolicyDisConfigs;
	}

	public void setGeSaleProPolicyDisConfigs(
			List<GeSaleProPolicyDisplayConfig> geSaleProPolicyDisConfigs) {
		this.geSaleProPolicyDisConfigs = geSaleProPolicyDisConfigs;
	}

	@Column(name = "ISONLINE")
	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	
	@Column(name = "RUNINGTISKID")
	public String getRunintTaskId() {
		return runintTaskId;
	}

	public void setRunintTaskId(String runintTaskId) {
		this.runintTaskId = runintTaskId;
	}
	
	@Column(name = "WORKFLOWID")
	public String getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
}
