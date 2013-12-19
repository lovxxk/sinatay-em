package cn.com.sinosoft.ebusiness.productManagement.saleProduct.domain;
// ���ù��� Hibernate Tools 3.2.4.GA (sinosoft version) ���ɣ������ֹ��޸ġ�

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
 * POJO��GeSaleProduct
 */
@Entity
@Table(name = "GE_SALE_PRODUCT")
public class GeSaleProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	
	// �Ƿ�����
	private String isOnline;
	
	private String runintTaskId; // �������еĽڵ�id
	
	private String workFlowId;//������id
	
	/** ���Բ�Ʒ���� */
	private String coreProductCode;

	/** ���Բ�Ʒ���� */
	private String productName;

	/** ���Բ�Ʒ��� */
	private String coreProductSimpleName;

	/** ����ҵ������ */
	private String businessArea;

	/** ������С�����˸��� */
	private String minInsuredNum;

	/** ������󱻱��˸��� */
	private String maxInsuredNum;

	/** ������С�����˸��� */
	private String minBeneficiaryNum;

	/** ������������˸��� */
	private String maxBeneficiaryNum;

	/** �������������˱�־ */
	private String isSupportPIns;

	/** ������С���������˸��� */
	private String minPInsuredNum;

	/** ����������������˸��� */
	private String maxPInsuredNum;

	/** ����ֽ�ʱ��������� */
	private String isPaper;

	/** ���Է��������˱�� */
	private String isSupportBeneficiary;

	/** ���Խ�����ϵ�˱�� */
	private String isSupportEmergency;

	/** ���Ա�ĸ��� */
	private String isSupportTarget;

	/** ������Ч�������� */
	private String effectDateType;

	/** ������Ч��ʼ���� */
	private Date specifyStartDate;

	/** ������Ч��ֹ����  */
	private Date specifyEndDate;

	/** ������Ч�ӳ����� */
	private String delayDays;

	/** ������С�������� */
	private String minEffectDateLimited;

	/** ���������������  */
	private String maxEffectDateLimited;

	/** ���Բ�Ʒ״̬ */
	private String productStatus;

	/** ���Բ�Ʒ�������� */
	private String productFlow;

	/** ���Դ������� */
	private Date createDate;

	/** ��������������� */
	private Date updateDate;

	/** �����Ƿ��޶�ͬҵ���� */
	private String limitSameIndInsuredAmount;

	/** ����ͬҵ�������ֵ */
	private BigDecimal sameIndInsuredAmountMax;

	/** �����ر�Լ�� */
	private String specialAgreement;

	/** ���Բ���Ա��� */
	private String operatorID;

	/** ���Ա�ע */
	private String remark;

	/** �����Ƿ�֧�ַ�Ʊ */
	private String isInvoice;

	/** ����geSaleProExtraInfos */
	private List<GeSaleProExtraInfo> geSaleProExtraInfos = new ArrayList<GeSaleProExtraInfo>(
			0);

	/** ����geSaleProWebFlows */
	private List<GeSaleProWebFlow> geSaleProWebFlows = new ArrayList<GeSaleProWebFlow>(
			0);

	/** ����geSaleProInformbooks */
	private List<GeSaleProInformbook> geSaleProInformbooks = new ArrayList<GeSaleProInformbook>(
			0);

	/** ����geSaleProInsuredConfigs */
	private List<GeSaleProInsuredConfig> geSaleProInsuredConfigs = new ArrayList<GeSaleProInsuredConfig>(
			0);

	/** ����geSaleProProDepts */
	private List<GeSaleProProDept> geSaleProProDepts = new ArrayList<GeSaleProProDept>(
			0);

	/** ����geSaleProWebFlowPages */
	private List<GeSaleProWebFlowPage> geSaleProWebFlowPages = new ArrayList<GeSaleProWebFlowPage>(
			0);

	/** ����geSaleProWebFlowPageElements */
	private List<GeSaleProWebFlowPageElement> geSaleProWebFlowPageElements = new ArrayList<GeSaleProWebFlowPageElement>(
			0);

	/** ����geSaleProductDuties */
	private List<GeSaleProductDuty> geSaleProductDuties = new ArrayList<GeSaleProductDuty>(
			0);

	/** ����geSaleProApplicantConfigs */
	private List<GeSaleProApplicantConfig> geSaleProApplicantConfigs = new ArrayList<GeSaleProApplicantConfig>(
			0);

	/** ����geSaleProAddresseeConfigs */
	private List<GeSaleProAddresseeConfig> geSaleProAddresseeConfigs = new ArrayList<GeSaleProAddresseeConfig>(
			0);

	/** ����geSaleProAttrAllowValueses */
	private List<GeSaleProAttrAllowValues> geSaleProAttrAllowValueses = new ArrayList<GeSaleProAttrAllowValues>(
			0);

	/** ����geSaleProductRisks */
	private List<GeSaleProductRisk> geSaleProductRisks = new ArrayList<GeSaleProductRisk>(
			0);

	/** ����geSaleProBeneficiaryConfigs */
	private List<GeSaleProBeneficiaryConfig> geSaleProBeneficiaryConfigs = new ArrayList<GeSaleProBeneficiaryConfig>(
			0);

	/** ����geSaleProEmergencyConfigs */
	private List<GeSaleProEmergencyConfig> geSaleProEmergencyConfigs = new ArrayList<GeSaleProEmergencyConfig>(
			0);


	/** ����geSaleProPolicyDisplayConfigs */
	private List<GeSaleProPolicyDisplayConfig> geSaleProPolicyDisConfigs = new ArrayList<GeSaleProPolicyDisplayConfig>(
			0);
	
	
	/**
	 * ��GeSaleProduct��Ĭ�Ϲ��췽��
	 */
	public GeSaleProduct() {
	}

	/**
	 * ���Բ�Ʒ�����getter����
	 */
	@Id
	@Column(name = "COREPRODUCTCODE")
	public String getCoreProductCode() {
		return this.coreProductCode;
	}
	/**
	 * ���Բ�Ʒ�����setter����
	 */
	public void setCoreProductCode(String coreProductCode) {
		this.coreProductCode = coreProductCode;
	}
	/**
	 * ���Բ�Ʒ���Ƶ�getter����
	 */

	@Column(name = "PRODUCTNAME")
	public String getProductName() {
		return this.productName;
	}
	/**
	 * ���Բ�Ʒ���Ƶ�setter����
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * ���Բ�Ʒ��Ƶ�getter����
	 */

	@Column(name = "COREPRODUCTSIMPLENAME")
	public String getCoreProductSimpleName() {
		return this.coreProductSimpleName;
	}
	/**
	 * ���Բ�Ʒ��Ƶ�setter����
	 */
	public void setCoreProductSimpleName(String coreProductSimpleName) {
		this.coreProductSimpleName = coreProductSimpleName;
	}
	/**
	 * ����ҵ�������getter����
	 */

	@Column(name = "BUSINESSAREA")
	public String getBusinessArea() {
		return this.businessArea;
	}
	/**
	 * ����ҵ�������setter����
	 */
	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}
	/**
	 * ������С�����˸�����getter����
	 */

	@Column(name = "MININSUREDNUM")
	public String getMinInsuredNum() {
		return this.minInsuredNum;
	}
	/**
	 * ������С�����˸�����setter����
	 */
	public void setMinInsuredNum(String minInsuredNum) {
		this.minInsuredNum = minInsuredNum;
	}
	/**
	 * ������󱻱��˸�����getter����
	 */

	@Column(name = "MAXINSUREDNUM")
	public String getMaxInsuredNum() {
		return this.maxInsuredNum;
	}
	/**
	 * ������󱻱��˸�����setter����
	 */
	public void setMaxInsuredNum(String maxInsuredNum) {
		this.maxInsuredNum = maxInsuredNum;
	}
	/**
	 * ������С�����˸�����getter����
	 */

	@Column(name = "MINBENEFICIARYNUM")
	public String getMinBeneficiaryNum() {
		return this.minBeneficiaryNum;
	}
	/**
	 * ������С�����˸�����setter����
	 */
	public void setMinBeneficiaryNum(String minBeneficiaryNum) {
		this.minBeneficiaryNum = minBeneficiaryNum;
	}
	/**
	 * ������������˸�����getter����
	 */

	@Column(name = "MAXBENEFICIARYNUM")
	public String getMaxBeneficiaryNum() {
		return this.maxBeneficiaryNum;
	}
	/**
	 * ������������˸�����setter����
	 */
	public void setMaxBeneficiaryNum(String maxBeneficiaryNum) {
		this.maxBeneficiaryNum = maxBeneficiaryNum;
	}
	/**
	 * �������������˱�־��getter����
	 */

	@Column(name = "ISSUPPORTPINS")
	public String getIsSupportPIns() {
		return this.isSupportPIns;
	}
	/**
	 * �������������˱�־��setter����
	 */
	public void setIsSupportPIns(String isSupportPIns) {
		this.isSupportPIns = isSupportPIns;
	}
	/**
	 * ������С���������˸�����getter����
	 */

	@Column(name = "MINPINSUREDNUM")
	public String getMinPInsuredNum() {
		return this.minPInsuredNum;
	}
	/**
	 * ������С���������˸�����setter����
	 */
	public void setMinPInsuredNum(String minPInsuredNum) {
		this.minPInsuredNum = minPInsuredNum;
	}
	/**
	 * ����������������˸�����getter����
	 */

	@Column(name = "MAXPINSUREDNUM")
	public String getMaxPInsuredNum() {
		return this.maxPInsuredNum;
	}
	/**
	 * ����������������˸�����setter����
	 */
	public void setMaxPInsuredNum(String maxPInsuredNum) {
		this.maxPInsuredNum = maxPInsuredNum;
	}
	/**
	 * ����ֽ�ʱ����������getter����
	 */

	@Column(name = "ISPAPER")
	public String getIsPaper() {
		return this.isPaper;
	}
	/**
	 * ����ֽ�ʱ����������setter����
	 */
	public void setIsPaper(String isPaper) {
		this.isPaper = isPaper;
	}
	/**
	 * ���Է��������˱�ǵ�getter����
	 */

	@Column(name = "ISSUPPORTBENEFICIARY")
	public String getIsSupportBeneficiary() {
		return this.isSupportBeneficiary;
	}
	/**
	 * ���Է��������˱�ǵ�setter����
	 */
	public void setIsSupportBeneficiary(String isSupportBeneficiary) {
		this.isSupportBeneficiary = isSupportBeneficiary;
	}
	/**
	 * ���Խ�����ϵ�˱�ǵ�getter����
	 */

	@Column(name = "ISSUPPORTEMERGENCY")
	public String getIsSupportEmergency() {
		return this.isSupportEmergency;
	}
	/**
	 * ���Խ�����ϵ�˱�ǵ�setter����
	 */
	public void setIsSupportEmergency(String isSupportEmergency) {
		this.isSupportEmergency = isSupportEmergency;
	}
	/**
	 * ���Ա�ĸ�����getter����
	 */

	@Column(name = "ISSUPPORTTARGET")
	public String getIsSupportTarget() {
		return this.isSupportTarget;
	}
	/**
	 * ���Ա�ĸ�����setter����
	 */
	public void setIsSupportTarget(String isSupportTarget) {
		this.isSupportTarget = isSupportTarget;
	}
	/**
	 * ������Ч�������͵�getter����
	 */

	@Column(name = "EFFECTDATETYPE")
	public String getEffectDateType() {
		return this.effectDateType;
	}
	/**
	 * ������Ч�������͵�setter����
	 */
	public void setEffectDateType(String effectDateType) {
		this.effectDateType = effectDateType;
	}
	/**
	 * ������Ч��ʼ���ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SPECIFYSTARTDATE")
	public Date getSpecifyStartDate() {
		return this.specifyStartDate;
	}
	/**
	 * ������Ч��ʼ���ڵ�setter����
	 */
	public void setSpecifyStartDate(Date specifyStartDate) {
		this.specifyStartDate = specifyStartDate;
	}
	/**
	 * ������Ч��ֹ���� ��getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SPECIFYENDDATE")
	public Date getSpecifyEndDate() {
		return this.specifyEndDate;
	}
	/**
	 * ������Ч��ֹ���� ��setter����
	 */
	public void setSpecifyEndDate(Date specifyEndDate) {
		this.specifyEndDate = specifyEndDate;
	}
	/**
	 * ������Ч�ӳ�������getter����
	 */

	@Column(name = "DELAYDAYS")
	public String getDelayDays() {
		return this.delayDays;
	}
	/**
	 * ������Ч�ӳ�������setter����
	 */
	public void setDelayDays(String delayDays) {
		this.delayDays = delayDays;
	}
	/**
	 * ������С����������getter����
	 */

	@Column(name = "MINEFFECTDATELIMITED")
	public String getMinEffectDateLimited() {
		return this.minEffectDateLimited;
	}
	/**
	 * ������С����������setter����
	 */
	public void setMinEffectDateLimited(String minEffectDateLimited) {
		this.minEffectDateLimited = minEffectDateLimited;
	}
	/**
	 * ��������������� ��getter����
	 */

	@Column(name = "MAXEFFECTDATELIMITED")
	public String getMaxEffectDateLimited() {
		return this.maxEffectDateLimited;
	}
	/**
	 * ��������������� ��setter����
	 */
	public void setMaxEffectDateLimited(String maxEffectDateLimited) {
		this.maxEffectDateLimited = maxEffectDateLimited;
	}
	/**
	 * ���Բ�Ʒ״̬��getter����
	 */

	@Column(name = "PRODUCTSTATUS")
	public String getProductStatus() {
		return this.productStatus;
	}
	/**
	 * ���Բ�Ʒ״̬��setter����
	 */
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	/**
	 * ���Բ�Ʒ�������̵�getter����
	 */

	@Column(name = "PRODUCTFLOW")
	public String getProductFlow() {
		return this.productFlow;
	}
	/**
	 * ���Բ�Ʒ�������̵�setter����
	 */
	public void setProductFlow(String productFlow) {
		this.productFlow = productFlow;
	}
	/**
	 * ���Դ������ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATEDATE")
	public Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * ���Դ������ڵ�setter����
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * ��������������ڵ�getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATEDATE")
	public Date getUpdateDate() {
		return this.updateDate;
	}
	/**
	 * ��������������ڵ�setter����
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * �����Ƿ��޶�ͬҵ�����getter����
	 */

	@Column(name = "LIMITSAMEINDINSUREDAMOUNT")
	public String getLimitSameIndInsuredAmount() {
		return this.limitSameIndInsuredAmount;
	}
	/**
	 * �����Ƿ��޶�ͬҵ�����setter����
	 */
	public void setLimitSameIndInsuredAmount(String limitSameIndInsuredAmount) {
		this.limitSameIndInsuredAmount = limitSameIndInsuredAmount;
	}
	/**
	 * ����ͬҵ�������ֵ��getter����
	 */

	@Column(name = "SAMEINDINSUREDAMOUNTMAX")
	public BigDecimal getSameIndInsuredAmountMax() {
		return this.sameIndInsuredAmountMax;
	}
	/**
	 * ����ͬҵ�������ֵ��setter����
	 */
	public void setSameIndInsuredAmountMax(BigDecimal sameIndInsuredAmountMax) {
		this.sameIndInsuredAmountMax = sameIndInsuredAmountMax;
	}
	/**
	 * �����ر�Լ����getter����
	 */

	@Column(name = "SPECIALAGREEMENT")
	public String getSpecialAgreement() {
		return this.specialAgreement;
	}
	/**
	 * �����ر�Լ����setter����
	 */
	public void setSpecialAgreement(String specialAgreement) {
		this.specialAgreement = specialAgreement;
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
	 * �����Ƿ�֧�ַ�Ʊ��getter����
	 */

	@Column(name = "ISINVOICE")
	public String getIsInvoice() {
		return this.isInvoice;
	}
	/**
	 * �����Ƿ�֧�ַ�Ʊ��setter����
	 */
	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}
	/**
	 * ����geSaleProExtraInfos��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProExtraInfo> getGeSaleProExtraInfos() {
		return this.geSaleProExtraInfos;
	}
	/**
	 * ����geSaleProExtraInfos��setter����
	 */
	public void setGeSaleProExtraInfos(
			List<GeSaleProExtraInfo> geSaleProExtraInfos) {
		this.geSaleProExtraInfos = geSaleProExtraInfos;
	}
	/**
	 * ����geSaleProWebFlows��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProWebFlow> getGeSaleProWebFlows() {
		return this.geSaleProWebFlows;
	}
	/**
	 * ����geSaleProWebFlows��setter����
	 */
	public void setGeSaleProWebFlows(List<GeSaleProWebFlow> geSaleProWebFlows) {
		this.geSaleProWebFlows = geSaleProWebFlows;
	}
	/**
	 * ����geSaleProInformbooks��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProInformbook> getGeSaleProInformbooks() {
		return this.geSaleProInformbooks;
	}
	/**
	 * ����geSaleProInformbooks��setter����
	 */
	public void setGeSaleProInformbooks(
			List<GeSaleProInformbook> geSaleProInformbooks) {
		this.geSaleProInformbooks = geSaleProInformbooks;
	}
	/**
	 * ����geSaleProInsuredConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProInsuredConfig> getGeSaleProInsuredConfigs() {
		return this.geSaleProInsuredConfigs;
	}
	/**
	 * ����geSaleProInsuredConfigs��setter����
	 */
	public void setGeSaleProInsuredConfigs(
			List<GeSaleProInsuredConfig> geSaleProInsuredConfigs) {
		this.geSaleProInsuredConfigs = geSaleProInsuredConfigs;
	}
	/**
	 * ����geSaleProProDepts��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProProDept> getGeSaleProProDepts() {
		return this.geSaleProProDepts;
	}
	/**
	 * ����geSaleProProDepts��setter����
	 */
	public void setGeSaleProProDepts(List<GeSaleProProDept> geSaleProProDepts) {
		this.geSaleProProDepts = geSaleProProDepts;
	}
	/**
	 * ����geSaleProWebFlowPages��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProWebFlowPage> getGeSaleProWebFlowPages() {
		return this.geSaleProWebFlowPages;
	}
	/**
	 * ����geSaleProWebFlowPages��setter����
	 */
	public void setGeSaleProWebFlowPages(
			List<GeSaleProWebFlowPage> geSaleProWebFlowPages) {
		this.geSaleProWebFlowPages = geSaleProWebFlowPages;
	}
	/**
	 * ����geSaleProWebFlowPageElements��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProWebFlowPageElement> getGeSaleProWebFlowPageElements() {
		return this.geSaleProWebFlowPageElements;
	}
	/**
	 * ����geSaleProWebFlowPageElements��setter����
	 */
	public void setGeSaleProWebFlowPageElements(
			List<GeSaleProWebFlowPageElement> geSaleProWebFlowPageElements) {
		this.geSaleProWebFlowPageElements = geSaleProWebFlowPageElements;
	}
	/**
	 * ����geSaleProductDuties��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
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
	/**
	 * ����geSaleProApplicantConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProApplicantConfig> getGeSaleProApplicantConfigs() {
		return this.geSaleProApplicantConfigs;
	}
	/**
	 * ����geSaleProApplicantConfigs��setter����
	 */
	public void setGeSaleProApplicantConfigs(
			List<GeSaleProApplicantConfig> geSaleProApplicantConfigs) {
		this.geSaleProApplicantConfigs = geSaleProApplicantConfigs;
	}
	/**
	 * ����geSaleProAddresseeConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProAddresseeConfig> getGeSaleProAddresseeConfigs() {
		return this.geSaleProAddresseeConfigs;
	}
	/**
	 * ����geSaleProAddresseeConfigs��setter����
	 */
	public void setGeSaleProAddresseeConfigs(
			List<GeSaleProAddresseeConfig> geSaleProAddresseeConfigs) {
		this.geSaleProAddresseeConfigs = geSaleProAddresseeConfigs;
	}
	/**
	 * ����geSaleProAttrAllowValueses��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProAttrAllowValues> getGeSaleProAttrAllowValueses() {
		return this.geSaleProAttrAllowValueses;
	}
	/**
	 * ����geSaleProAttrAllowValueses��setter����
	 */
	public void setGeSaleProAttrAllowValueses(
			List<GeSaleProAttrAllowValues> geSaleProAttrAllowValueses) {
		this.geSaleProAttrAllowValueses = geSaleProAttrAllowValueses;
	}
	/**
	 * ����geSaleProductRisks��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProductRisk> getGeSaleProductRisks() {
		return this.geSaleProductRisks;
	}
	/**
	 * ����geSaleProductRisks��setter����
	 */
	public void setGeSaleProductRisks(List<GeSaleProductRisk> geSaleProductRisks) {
		this.geSaleProductRisks = geSaleProductRisks;
	}
	/**
	 * ����geSaleProBeneficiaryConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProBeneficiaryConfig> getGeSaleProBeneficiaryConfigs() {
		return this.geSaleProBeneficiaryConfigs;
	}
	/**
	 * ����geSaleProBeneficiaryConfigs��setter����
	 */
	public void setGeSaleProBeneficiaryConfigs(
			List<GeSaleProBeneficiaryConfig> geSaleProBeneficiaryConfigs) {
		this.geSaleProBeneficiaryConfigs = geSaleProBeneficiaryConfigs;
	}
	/**
	 * ����geSaleProEmergencyConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geSaleProduct")
	public List<GeSaleProEmergencyConfig> getGeSaleProEmergencyConfigs() {
		return this.geSaleProEmergencyConfigs;
	}
	/**
	 * ����geSaleProEmergencyConfigs��setter����
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
