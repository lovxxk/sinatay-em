package cn.com.sinosoft.ebusiness.productManagement.productManage.domain;
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
import javax.persistence.Transient;


/**
 * POJO类GeProductMain
 */
@Entity
@Table(name = "GE_PRODUCT_MAIN")
public class GeProductMain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// 是否上线（根据产品目录的上架下架同步过来）
	private String isOnline;

	private String runintTaskId; // 正在运行的节点id
	
	private String workFlowId;//工作流id
	
	/** 属性geWorkflowProducts */
	private List<GeWorkflowProduct> geWorkflowProducts = new ArrayList<GeWorkflowProduct>(
			0);
	
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

	/** 属性产品状态 （01-已创建，02-已定制流程，03-已详细定义，04-已审核，05-已发布,06-详细定义中）*/
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
	
	//业务上使用的字段
	private String eid;

	/** 属性geProductConfigMains */
	private List<GeProductConfigMain> geProductConfigMains = new ArrayList<GeProductConfigMain>(
			0);

	/** 属性geProductApplicantConfigs */
	private List<GeProductApplicantConfig> geProductApplicantConfigs = new ArrayList<GeProductApplicantConfig>(
			0);

	/** 属性geProductEmergencyConfigs */
	private List<GeProductEmergencyConfig> geProductEmergencyConfigs = new ArrayList<GeProductEmergencyConfig>(
			0);

	/** 属性geWebFlowPages */
	private List<GeWebFlowPage> geWebFlowPages = new ArrayList<GeWebFlowPage>(0);

	/** 属性geProductAttrAllowValueses */
	private List<GeProductAttrAllowValues> geProductAttrAllowValueses = new ArrayList<GeProductAttrAllowValues>(
			0);

	/** 属性geProductMainProcesses */
	private List<GeProductMainProcess> geProductMainProcesses = new ArrayList<GeProductMainProcess>(
			0);

	/** 属性geProductProDepts */
	private List<GeProductProDept> geProductProDepts = new ArrayList<GeProductProDept>(
			0);

	/** 属性geProductRisks */
	private List<GeProductRisk> geProductRisks = new ArrayList<GeProductRisk>(0);

	/** 属性geProductExtraInfos */
	private List<GeProductExtraInfo> geProductExtraInfos = new ArrayList<GeProductExtraInfo>(
			0);

	/** 属性geProductInformBooks */
	private List<GeProductInformBook> geProductInformBooks = new ArrayList<GeProductInformBook>(
			0);

	/** 属性geAddresseeConfigs */
	private List<GeAddresseeConfig> geAddresseeConfigs = new ArrayList<GeAddresseeConfig>(
			0);

	/** 属性geWebFlowPageElements */
	private List<GeWebFlowPageElement> geWebFlowPageElements = new ArrayList<GeWebFlowPageElement>(
			0);

	/** 属性geWebFlows */
	private List<GeWebFlow> geWebFlows = new ArrayList<GeWebFlow>(0);

	/** 属性geProductBeneficiaryConfigs */
	private List<GeProductBeneficiaryConfig> geProductBeneficiaryConfigs = new ArrayList<GeProductBeneficiaryConfig>(
			0);

	/** 属性产品责任 */
	private List<GeProductDuty> geProductDuties = new ArrayList<GeProductDuty>(
			0);

	/** 属性geProductInsuredConfigs */
	private List<GeProductInsuredConfig> geProductInsuredConfigs = new ArrayList<GeProductInsuredConfig>(
			0);

	/** 属性geProductPolicyDisplayConfigs */
	private List<GeProductPolicyDisplayConfig> geProductPolicyDisplayConfigs = new ArrayList<GeProductPolicyDisplayConfig>(
			0);
	
	
	/**
	 * 类GeProductMain的默认构造方法
	 */
	public GeProductMain() {
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
	 * 属性geProductConfigMains的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductConfigMain> getGeProductConfigMains() {
		return this.geProductConfigMains;
	}
	/**
	 * 属性geProductConfigMains的setter方法
	 */
	public void setGeProductConfigMains(
			List<GeProductConfigMain> geProductConfigMains) {
		this.geProductConfigMains = geProductConfigMains;
	}
	/**
	 * 属性geProductApplicantConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductApplicantConfig> getGeProductApplicantConfigs() {
		return this.geProductApplicantConfigs;
	}
	/**
	 * 属性geProductApplicantConfigs的setter方法
	 */
	public void setGeProductApplicantConfigs(
			List<GeProductApplicantConfig> geProductApplicantConfigs) {
		this.geProductApplicantConfigs = geProductApplicantConfigs;
	}
	/**
	 * 属性geProductEmergencyConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductEmergencyConfig> getGeProductEmergencyConfigs() {
		return this.geProductEmergencyConfigs;
	}
	/**
	 * 属性geProductEmergencyConfigs的setter方法
	 */
	public void setGeProductEmergencyConfigs(
			List<GeProductEmergencyConfig> geProductEmergencyConfigs) {
		this.geProductEmergencyConfigs = geProductEmergencyConfigs;
	}
	/**
	 * 属性geWebFlowPages的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeWebFlowPage> getGeWebFlowPages() {
		return this.geWebFlowPages;
	}
	/**
	 * 属性geWebFlowPages的setter方法
	 */
	public void setGeWebFlowPages(List<GeWebFlowPage> geWebFlowPages) {
		this.geWebFlowPages = geWebFlowPages;
	}
	/**
	 * 属性geProductAttrAllowValueses的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductAttrAllowValues> getGeProductAttrAllowValueses() {
		return this.geProductAttrAllowValueses;
	}
	/**
	 * 属性geProductAttrAllowValueses的setter方法
	 */
	public void setGeProductAttrAllowValueses(
			List<GeProductAttrAllowValues> geProductAttrAllowValueses) {
		this.geProductAttrAllowValueses = geProductAttrAllowValueses;
	}
	/**
	 * 属性geProductMainProcesses的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductMainProcess> getGeProductMainProcesses() {
		return this.geProductMainProcesses;
	}
	/**
	 * 属性geProductMainProcesses的setter方法
	 */
	public void setGeProductMainProcesses(
			List<GeProductMainProcess> geProductMainProcesses) {
		this.geProductMainProcesses = geProductMainProcesses;
	}
	/**
	 * 属性geProductProDepts的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductProDept> getGeProductProDepts() {
		return this.geProductProDepts;
	}
	/**
	 * 属性geProductProDepts的setter方法
	 */
	public void setGeProductProDepts(List<GeProductProDept> geProductProDepts) {
		this.geProductProDepts = geProductProDepts;
	}
	/**
	 * 属性geProductRisks的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductRisk> getGeProductRisks() {
		return this.geProductRisks;
	}
	/**
	 * 属性geProductRisks的setter方法
	 */
	public void setGeProductRisks(List<GeProductRisk> geProductRisks) {
		this.geProductRisks = geProductRisks;
	}
	/**
	 * 属性geProductExtraInfos的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductExtraInfo> getGeProductExtraInfos() {
		return this.geProductExtraInfos;
	}
	/**
	 * 属性geProductExtraInfos的setter方法
	 */
	public void setGeProductExtraInfos(
			List<GeProductExtraInfo> geProductExtraInfos) {
		this.geProductExtraInfos = geProductExtraInfos;
	}
	/**
	 * 属性geProductInformBooks的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductInformBook> getGeProductInformBooks() {
		return this.geProductInformBooks;
	}
	/**
	 * 属性geProductInformBooks的setter方法
	 */
	public void setGeProductInformBooks(
			List<GeProductInformBook> geProductInformBooks) {
		this.geProductInformBooks = geProductInformBooks;
	}
	/**
	 * 属性geAddresseeConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeAddresseeConfig> getGeAddresseeConfigs() {
		return this.geAddresseeConfigs;
	}
	/**
	 * 属性geAddresseeConfigs的setter方法
	 */
	public void setGeAddresseeConfigs(List<GeAddresseeConfig> geAddresseeConfigs) {
		this.geAddresseeConfigs = geAddresseeConfigs;
	}
	/**
	 * 属性geWebFlowPageElements的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeWebFlowPageElement> getGeWebFlowPageElements() {
		return this.geWebFlowPageElements;
	}
	/**
	 * 属性geWebFlowPageElements的setter方法
	 */
	public void setGeWebFlowPageElements(
			List<GeWebFlowPageElement> geWebFlowPageElements) {
		this.geWebFlowPageElements = geWebFlowPageElements;
	}
	/**
	 * 属性geWebFlows的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeWebFlow> getGeWebFlows() {
		return this.geWebFlows;
	}
	/**
	 * 属性geWebFlows的setter方法
	 */
	public void setGeWebFlows(List<GeWebFlow> geWebFlows) {
		this.geWebFlows = geWebFlows;
	}
	/**
	 * 属性geProductBeneficiaryConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductBeneficiaryConfig> getGeProductBeneficiaryConfigs() {
		return this.geProductBeneficiaryConfigs;
	}
	/**
	 * 属性geProductBeneficiaryConfigs的setter方法
	 */
	public void setGeProductBeneficiaryConfigs(
			List<GeProductBeneficiaryConfig> geProductBeneficiaryConfigs) {
		this.geProductBeneficiaryConfigs = geProductBeneficiaryConfigs;
	}
	/**
	 * 属性产品责任的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductDuty> getGeProductDuties() {
		return this.geProductDuties;
	}
	/**
	 * 属性产品责任的setter方法
	 */
	public void setGeProductDuties(List<GeProductDuty> geProductDuties) {
		this.geProductDuties = geProductDuties;
	}
	/**
	 * 属性geProductInsuredConfigs的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductInsuredConfig> getGeProductInsuredConfigs() {
		return this.geProductInsuredConfigs;
	}
	/**
	 * 属性geProductInsuredConfigs的setter方法
	 */
	public void setGeProductInsuredConfigs(
			List<GeProductInsuredConfig> geProductInsuredConfigs) {
		this.geProductInsuredConfigs = geProductInsuredConfigs;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductPolicyDisplayConfig> getGeProductPolicyDisplayConfigs() {
		return geProductPolicyDisplayConfigs;
	}

	public void setGeProductPolicyDisplayConfigs(
			List<GeProductPolicyDisplayConfig> geProductPolicyDisplayConfigs) {
		this.geProductPolicyDisplayConfigs = geProductPolicyDisplayConfigs;
	}

	@Transient
	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	@Column(name = "ISONLINE")
	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	/**
	 * 属性geWorkflowProducts的getter方法
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeWorkflowProduct> getGeWorkflowProducts() {
		return this.geWorkflowProducts;
	}

	/**
	 * 属性geWorkflowProducts的setter方法
	 */
	public void setGeWorkflowProducts(List<GeWorkflowProduct> geWorkflowProducts) {
		this.geWorkflowProducts = geWorkflowProducts;
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
