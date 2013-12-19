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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * POJO��GeProductMain
 */
@Entity
@Table(name = "GE_PRODUCT_MAIN")
public class GeProductMain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	// �Ƿ����ߣ����ݲ�ƷĿ¼���ϼ��¼�ͬ��������
	private String isOnline;

	private String runintTaskId; // �������еĽڵ�id
	
	private String workFlowId;//������id
	
	/** ����geWorkflowProducts */
	private List<GeWorkflowProduct> geWorkflowProducts = new ArrayList<GeWorkflowProduct>(
			0);
	
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

	/** ���Բ�Ʒ״̬ ��01-�Ѵ�����02-�Ѷ������̣�03-����ϸ���壬04-����ˣ�05-�ѷ���,06-��ϸ�����У�*/
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
	
	//ҵ����ʹ�õ��ֶ�
	private String eid;

	/** ����geProductConfigMains */
	private List<GeProductConfigMain> geProductConfigMains = new ArrayList<GeProductConfigMain>(
			0);

	/** ����geProductApplicantConfigs */
	private List<GeProductApplicantConfig> geProductApplicantConfigs = new ArrayList<GeProductApplicantConfig>(
			0);

	/** ����geProductEmergencyConfigs */
	private List<GeProductEmergencyConfig> geProductEmergencyConfigs = new ArrayList<GeProductEmergencyConfig>(
			0);

	/** ����geWebFlowPages */
	private List<GeWebFlowPage> geWebFlowPages = new ArrayList<GeWebFlowPage>(0);

	/** ����geProductAttrAllowValueses */
	private List<GeProductAttrAllowValues> geProductAttrAllowValueses = new ArrayList<GeProductAttrAllowValues>(
			0);

	/** ����geProductMainProcesses */
	private List<GeProductMainProcess> geProductMainProcesses = new ArrayList<GeProductMainProcess>(
			0);

	/** ����geProductProDepts */
	private List<GeProductProDept> geProductProDepts = new ArrayList<GeProductProDept>(
			0);

	/** ����geProductRisks */
	private List<GeProductRisk> geProductRisks = new ArrayList<GeProductRisk>(0);

	/** ����geProductExtraInfos */
	private List<GeProductExtraInfo> geProductExtraInfos = new ArrayList<GeProductExtraInfo>(
			0);

	/** ����geProductInformBooks */
	private List<GeProductInformBook> geProductInformBooks = new ArrayList<GeProductInformBook>(
			0);

	/** ����geAddresseeConfigs */
	private List<GeAddresseeConfig> geAddresseeConfigs = new ArrayList<GeAddresseeConfig>(
			0);

	/** ����geWebFlowPageElements */
	private List<GeWebFlowPageElement> geWebFlowPageElements = new ArrayList<GeWebFlowPageElement>(
			0);

	/** ����geWebFlows */
	private List<GeWebFlow> geWebFlows = new ArrayList<GeWebFlow>(0);

	/** ����geProductBeneficiaryConfigs */
	private List<GeProductBeneficiaryConfig> geProductBeneficiaryConfigs = new ArrayList<GeProductBeneficiaryConfig>(
			0);

	/** ���Բ�Ʒ���� */
	private List<GeProductDuty> geProductDuties = new ArrayList<GeProductDuty>(
			0);

	/** ����geProductInsuredConfigs */
	private List<GeProductInsuredConfig> geProductInsuredConfigs = new ArrayList<GeProductInsuredConfig>(
			0);

	/** ����geProductPolicyDisplayConfigs */
	private List<GeProductPolicyDisplayConfig> geProductPolicyDisplayConfigs = new ArrayList<GeProductPolicyDisplayConfig>(
			0);
	
	
	/**
	 * ��GeProductMain��Ĭ�Ϲ��췽��
	 */
	public GeProductMain() {
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
	 * ����geProductConfigMains��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductConfigMain> getGeProductConfigMains() {
		return this.geProductConfigMains;
	}
	/**
	 * ����geProductConfigMains��setter����
	 */
	public void setGeProductConfigMains(
			List<GeProductConfigMain> geProductConfigMains) {
		this.geProductConfigMains = geProductConfigMains;
	}
	/**
	 * ����geProductApplicantConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductApplicantConfig> getGeProductApplicantConfigs() {
		return this.geProductApplicantConfigs;
	}
	/**
	 * ����geProductApplicantConfigs��setter����
	 */
	public void setGeProductApplicantConfigs(
			List<GeProductApplicantConfig> geProductApplicantConfigs) {
		this.geProductApplicantConfigs = geProductApplicantConfigs;
	}
	/**
	 * ����geProductEmergencyConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductEmergencyConfig> getGeProductEmergencyConfigs() {
		return this.geProductEmergencyConfigs;
	}
	/**
	 * ����geProductEmergencyConfigs��setter����
	 */
	public void setGeProductEmergencyConfigs(
			List<GeProductEmergencyConfig> geProductEmergencyConfigs) {
		this.geProductEmergencyConfigs = geProductEmergencyConfigs;
	}
	/**
	 * ����geWebFlowPages��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeWebFlowPage> getGeWebFlowPages() {
		return this.geWebFlowPages;
	}
	/**
	 * ����geWebFlowPages��setter����
	 */
	public void setGeWebFlowPages(List<GeWebFlowPage> geWebFlowPages) {
		this.geWebFlowPages = geWebFlowPages;
	}
	/**
	 * ����geProductAttrAllowValueses��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductAttrAllowValues> getGeProductAttrAllowValueses() {
		return this.geProductAttrAllowValueses;
	}
	/**
	 * ����geProductAttrAllowValueses��setter����
	 */
	public void setGeProductAttrAllowValueses(
			List<GeProductAttrAllowValues> geProductAttrAllowValueses) {
		this.geProductAttrAllowValueses = geProductAttrAllowValueses;
	}
	/**
	 * ����geProductMainProcesses��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductMainProcess> getGeProductMainProcesses() {
		return this.geProductMainProcesses;
	}
	/**
	 * ����geProductMainProcesses��setter����
	 */
	public void setGeProductMainProcesses(
			List<GeProductMainProcess> geProductMainProcesses) {
		this.geProductMainProcesses = geProductMainProcesses;
	}
	/**
	 * ����geProductProDepts��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductProDept> getGeProductProDepts() {
		return this.geProductProDepts;
	}
	/**
	 * ����geProductProDepts��setter����
	 */
	public void setGeProductProDepts(List<GeProductProDept> geProductProDepts) {
		this.geProductProDepts = geProductProDepts;
	}
	/**
	 * ����geProductRisks��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductRisk> getGeProductRisks() {
		return this.geProductRisks;
	}
	/**
	 * ����geProductRisks��setter����
	 */
	public void setGeProductRisks(List<GeProductRisk> geProductRisks) {
		this.geProductRisks = geProductRisks;
	}
	/**
	 * ����geProductExtraInfos��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductExtraInfo> getGeProductExtraInfos() {
		return this.geProductExtraInfos;
	}
	/**
	 * ����geProductExtraInfos��setter����
	 */
	public void setGeProductExtraInfos(
			List<GeProductExtraInfo> geProductExtraInfos) {
		this.geProductExtraInfos = geProductExtraInfos;
	}
	/**
	 * ����geProductInformBooks��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductInformBook> getGeProductInformBooks() {
		return this.geProductInformBooks;
	}
	/**
	 * ����geProductInformBooks��setter����
	 */
	public void setGeProductInformBooks(
			List<GeProductInformBook> geProductInformBooks) {
		this.geProductInformBooks = geProductInformBooks;
	}
	/**
	 * ����geAddresseeConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeAddresseeConfig> getGeAddresseeConfigs() {
		return this.geAddresseeConfigs;
	}
	/**
	 * ����geAddresseeConfigs��setter����
	 */
	public void setGeAddresseeConfigs(List<GeAddresseeConfig> geAddresseeConfigs) {
		this.geAddresseeConfigs = geAddresseeConfigs;
	}
	/**
	 * ����geWebFlowPageElements��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeWebFlowPageElement> getGeWebFlowPageElements() {
		return this.geWebFlowPageElements;
	}
	/**
	 * ����geWebFlowPageElements��setter����
	 */
	public void setGeWebFlowPageElements(
			List<GeWebFlowPageElement> geWebFlowPageElements) {
		this.geWebFlowPageElements = geWebFlowPageElements;
	}
	/**
	 * ����geWebFlows��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeWebFlow> getGeWebFlows() {
		return this.geWebFlows;
	}
	/**
	 * ����geWebFlows��setter����
	 */
	public void setGeWebFlows(List<GeWebFlow> geWebFlows) {
		this.geWebFlows = geWebFlows;
	}
	/**
	 * ����geProductBeneficiaryConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductBeneficiaryConfig> getGeProductBeneficiaryConfigs() {
		return this.geProductBeneficiaryConfigs;
	}
	/**
	 * ����geProductBeneficiaryConfigs��setter����
	 */
	public void setGeProductBeneficiaryConfigs(
			List<GeProductBeneficiaryConfig> geProductBeneficiaryConfigs) {
		this.geProductBeneficiaryConfigs = geProductBeneficiaryConfigs;
	}
	/**
	 * ���Բ�Ʒ���ε�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductDuty> getGeProductDuties() {
		return this.geProductDuties;
	}
	/**
	 * ���Բ�Ʒ���ε�setter����
	 */
	public void setGeProductDuties(List<GeProductDuty> geProductDuties) {
		this.geProductDuties = geProductDuties;
	}
	/**
	 * ����geProductInsuredConfigs��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeProductInsuredConfig> getGeProductInsuredConfigs() {
		return this.geProductInsuredConfigs;
	}
	/**
	 * ����geProductInsuredConfigs��setter����
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
	 * ����geWorkflowProducts��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "geProductMain")
	public List<GeWorkflowProduct> getGeWorkflowProducts() {
		return this.geWorkflowProducts;
	}

	/**
	 * ����geWorkflowProducts��setter����
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
