package cn.com.sinosoft.businessModule.policy.domain;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;

import cn.com.sinosoft.businessModule.bankAccount.domain.FundsTransfer;
import cn.com.sinosoft.businessModule.bankAccount.domain.PaymentAccount;
import cn.com.sinosoft.businessModule.enums.dictionary.AnnuityPayoutDurationMode;
import cn.com.sinosoft.businessModule.enums.dictionary.BeneficiaryMode;
import cn.com.sinosoft.businessModule.enums.dictionary.BenefitMode;
import cn.com.sinosoft.businessModule.enums.dictionary.BenefitPeriodType;
import cn.com.sinosoft.businessModule.enums.dictionary.BooleanStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.DisputedFlag;
import cn.com.sinosoft.businessModule.enums.dictionary.DivType;
import cn.com.sinosoft.businessModule.enums.dictionary.ElectronicPolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.ExPayMode;
import cn.com.sinosoft.businessModule.enums.dictionary.FundTransferDateBasedOnType;
import cn.com.sinosoft.businessModule.enums.dictionary.GetPolMode;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentDurationMode;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentMethod;
import cn.com.sinosoft.businessModule.enums.dictionary.PaymentMode;
import cn.com.sinosoft.businessModule.enums.dictionary.PolicyStatus;
import cn.com.sinosoft.businessModule.enums.dictionary.SyncStatus;
import cn.com.sinosoft.businessModule.insureInformBook.domain.InsureInformBook;
import cn.com.sinosoft.businessModule.logisticDistribution.domain.Addressee;
import cn.com.sinosoft.businessModule.logisticDistribution.domain.Invoice;
import cn.com.sinosoft.businessModule.order.domain.OrderForm;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Beneficiary;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.InsuranceApplicant;
import cn.com.sinosoft.businessModule.partyRoleInPolicy.domain.Insured;
import cn.com.sinosoft.enums.EnumDataUtils;

/**
 * POJO��InsurancePolicy
 */
@Entity
@Table(name = "INSURANCEPOLICY")
public class InsurancePolicy implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/** ������� */
	private String serialNo;
	
	/** ���Խ��ױ�ʾ */
	private String transIdentify;
	
	/** ���Խ�����ˮ�� */
	private String transSerialNumber;
	
	/** �����̼ҽ�����ˮ�� */
	private String merchantTransSerialNumber;
	
	/** ���Զ����� */
	private String orderSerialNumber;
	
	/** ���Ժ������������ţ��Ա������ţ� */
	private String merchantOrderNumber;

	/** ���Ա����� */
	private String policySerialNumber;

	/** ����Ͷ������ */
	private String applicationNumber;

	/** ����Ͷ����ӡˢ�� */
	private String applicationSerialNumber;

	/** ���Ե��ӱ����� */
	private String electronicPolicyNumber;

	/** ���Ե���Ͷ������ */
	private String electronicApplicantNumber;

	/** ���Ա������� */
	private Integer policyType;
	
	/** ���Ա��յ�֤���� */
	private Integer documentType;
	
	/** �������ڽɸ�����ѡ��(���ѹ���δ��ѡ��) */
	private Integer overduePremiumPaymentOption;
	
	/** ���Ա���״̬ */
	private Integer policyStatus;

	/** ���Ա���״̬���� */
	private String policyStatusName;

	/** ���Ա���״̬���� */
	private String policyStatusDesc;
	
	/** �������ɱ���״̬*/
	private Integer generateElectronicPolicyStatus = ElectronicPolicyStatus.NOT_GENERATED.getValue();
	
	/** ���Ա��� */
	private BigDecimal insuredAmount;

	/** ���Ա��� */
	private BigDecimal premium;
	
	/** �����ۿ۱��� */
	private BigDecimal discountPremium;
	
	/** �����ܱ��� */
	private BigDecimal grossPremium = new BigDecimal(0);
	
	/** �����ܱ��� */
	private BigDecimal faceAmount;
	
	/** ���Բ�Ʒ���� */
	private String productCode;

	/** ���Բ�Ʒ���� */
	private String productName;
	
	/** ���Ժ���������Ʒ���� */
	private String merchantProductCode;
	
	/** ���Ժ���������Ʒ���� */
	private String merchantProductName;
	
	/** �������ڱ��� */
	private BigDecimal firstPremium;

	/** �����״νɷѽ�� */
	private BigDecimal initialPremAmt;

	/** ���Ա������� */
	private Integer benefitPeriod;

	/** ���Ա����������� */
	private Integer benefitPeriodType;

	/** ���Խɷѷ�ʽ */
	private Integer paymentMode;

	/** ���Խɷ����� */
	private Integer paymentDuration;

	/** ���Խɷ��������� */
	private Integer paymentDurationMode;

	/** ����֧����ʽ */
	private Integer paymentMethod;

	/** �����������ȡ��ʽ */
	private Integer benefitMode;

	/** ���Ժ�����ȡ��ʽ */
	private Integer divType;

	/** ���������ȡ���� */
	private String annuityPayoutDuration;

	/** ���������ȡ�������� */
	private Integer annuityPayoutDurationMode;

	/** ���������ȡ��ʼ���� */
	private String payoutStart;

	/** ���������ȡ��ֹ���� */
	private String payoutEnd;

	/** ���Ը��ӱ���/������� */
	private BigDecimal excessPremAmt;

	/** ����ֽ�ʱ�����ݷ� */
	private BigDecimal policyDeliveryFee;

	/** �����Զ��˱����� */
	private Integer policyStatusMessage;
	
	/** ����ǩԼ���� */
	private Date signedDate;

	/** ���Ա�����Ч�� */
	private Date inceptionDate;
	
	/** ���Ա���������*/
	private Date applicationDate;
	
	/** ���Ա���������*/
	private Date backtrackDate;
	
	/** ���Ա�������  */
	private Date insuranceStartPeriod;
	
	/** ���Ա���ֹ�� */
	private Date insuranceEndPeriod;
	
	/** �������淽ʽ  */
	private Integer beneficiaryMode;
	
	/** ��������Ŀ��  */
	private String travelDestination;
	
	/** ����Ͷ���������� */
	private Date submissionDate;

	/** ���Է��� */
	private Integer unitCount = 1;

	/** ����Ͷ�������� */
	private String formID;
	
	/** �����ر����� */
	private String specialStatement;
	
	/** �����Ķ�����������Ϣ */
	private String contractNames;
	
	/** ���Ա��ѽ���Ͷ���˻�����ѡ�� */
	private Integer fundTransferDateBasedOn;
	
	/** �������д��� */
	private String bankCode;
	
	/** �����Ƿ���ǩ֤ */
	private Integer requireVisa;
	
	/** �����Ƿ���ҪӢ��֤�� */
	private Integer certificationRequired;

	/** �����Ƿ�Ͷ�ݷ�Ʊ */
	private Integer deliveryInvoice;

	/** �����Ƿ�Ͷ��ֽ�ʱ��� */
	private Integer deliveryHardCopy;

	/** �����Ƿ��͵��ӱ��� */
	private Integer deliveryEPolicy;

	/** ���Ի���� */
	private String campaignCode;

	/** ���Ի���� */
	private String campaignName;

	/** �����ۿ۴��� */
	private String discountTypeCode;

	/** �����ۿ�ϵ�� */
	private BigDecimal discountRate;

	/** ���Է�����һ�����Զ����� */
	private Integer autoRenewable;

	/** �����ײʹ��� */
	private String comboCode;

	/** �����ײ����� */
	private String comboName;
	
	/** ���Ը��˿ͻ� */
	private String personalUserSerialNo;

	/** �����û��� */
	private String customerID;
	
	/** ���Դ����˴��� */
	private String agentCode;

	/** ���Դ�����Э��� */
	private String agreementNo;

	/** ���Դ��������� */
	private String agentName;

	/** ���Դ��������ڻ������� */
	private String departmentNo;

	/** ���Դ��������ڻ������� */
	private String departmentName;
	
	/** ������������ */
	private String intermediaryCode;
	
	/** ������������ */
	private String intermediaryName;
	
	/** ����������� */
	private String branchCode;
	
	/** ����������� */
	private String branchName;
	
	/** ���Ի������� */
	private String organizationCode;

	/** ���Ի������� */
	private String organizationName;
	
	/** ���Ժ����������� */
	private String institutionCode;

	/** ���Ժ����������� */
	private String institutionName;
	
	/** ����ͬ��״̬ */
	private Integer syncStatus = SyncStatus.NOTYET_SYNCHRONIZED.getValue();
	
	/** ����ͬ��״̬���� */
	private String syncStatusDesc;
	
	/** ����ͬ����ʼʱ�� */
	private Date syncStartTime;
	
	/** ����ͬ������ʱ�� */
	private Date syncEndTime;
	
	/** �����ֽ��ֵ */
	private BigDecimal cashValue;
	
	/** �����Ƿ�ɳ��� */
	private Integer reversalFlag;
	
	/** ����ȫ��������Ƿ�����˱� */
	private Integer mustCancelFlag;
	
	/** �����Ƿ�������֧ȡ */
	private Integer allowPartyWithdrowFlag;
	
	/** ���Ա�ȫ����ʱ��*/
	private Date preserveAcceptTime;
	
	/** ���Ա�ȫ��Чʱ��*/
	private Date preserveEffectiveTime;
	
	/** ���Գ���ʱ��*/
	private Date reFundPolicyTime;
	
	/** ���Գ����ɹ�ʱ��*/
	private Date reFundPolicySuccessTime;
	
	/** ���Ը����˻� */
	private PaymentAccount paymentAccount;

	/** ���Է�Ʊ */
	private Invoice invoice;

	/** �����ռ��� */
	private Addressee addressee;

	/** ���Զ��� */
	private OrderForm orderForm;

	/** ����Ͷ���� */
	private InsuranceApplicant insuranceApplicant;
	
	/** ���Ա������� */
	private Integer insuredNumber;
	
	/** ���Ա������� */
	private List<Insured> insureds = new ArrayList<Insured>(0);

	/** ������������ */
	private Integer beneficiaryNumber;
	
	/** ���������� */
	private List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>(0);
	
	/** ���Ա��������� */
	private Integer insurancePolicyLiabilityNumber;
	
	/** ���� �������� */
	private List<InsurancePolicyLiability> insurancePolicyLiabilities = new ArrayList<InsurancePolicyLiability>(0);
	
	/** ����Ͷ����֪ */
	private List<InsureInformBook> insureInformBooks = new ArrayList<InsureInformBook>(0);
	
	/** �����ʽ𻮲� */
	private List<FundsTransfer> fundsTransfers = new ArrayList<FundsTransfer>(0);
	
	/** ����Ͷ����֪�� */
	private Integer insureInformBookNumber;
	
	/** ���� ���ڽɷ���ʽ */
	private Integer exPayMode;

	/** ���� �������ͷ�ʽ */
	private Integer getPolMode;

	/** ���� �������� */
	private String password;

	/** ���� �ر�Լ�� */
	private String specContent;

	/** ���� ��Ʊӡˢ���� */
	private String tempFeeNo;

	/** ���� ��������� */
	private String agentGroup;

	/** ���� ��ͬ���鴦��ʽ */
	private Integer disputedFlag;

	/** ���� �ٲ�ίԱ������ */
	private String acName;

	/** ���� �Ƿ�ط� */
	private Integer isVisit;

	/** ���� �󶨱�־ */
	private Integer isBind;
	
	/** ���Բ���Ա */
	private String operatorID;
	
	/** ���Դ���ʱ�� */
	private Date createTime = new Date();

	/** ���Ը���ʱ�� */
	private Date updateTime = new Date();
	
	/** �������㵥�� */
	private String quoteNo;
	
	/** ����������־ */
	private String renewalFlag;
	
	/** ����ԭ������ */
	private String oldlPolicyNo;
	
	/** ���Աұ� */
	private String currency;
	
	/** ����¼��Сʱ */
	private String inputHour;
	
	/** ����ҵ������1���š�2���� 3���� 4������  9������ */
	private String businessArea;
	
	/** ����ԭ�� */
	private String reason;

	/** ���Ա�ʶλ */
	private String flag;
	
	/** �˱�ʱ�� */
	private String precheckDate;
	
	/** �ӹ������� */
	private String moreBuyNo;
	
	/**���Լ���ҵ����Դ��WEB��**/
	private String businessSource;
	
	/**���Լ����������루W����������**/
	private String groupChannel;
	
	/** ����sellType��20�� **/
	private String sellType;
	
	/**���Ѵ���*/
	private Integer remindCount = 0;
	
	
	/**
	 * ��InsurancePolicy��Ĭ�Ϲ��췽��
	 */
	public InsurancePolicy() {
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
	 * ���Խ��ױ�ʶ���getter����
	 */
	@Column(name = "TRANSIDENTIFY")
	@JSON(serialize=false)  
	public String getTransIdentify() {
		return transIdentify;
	}

	/**
	 * ���Խ��ױ�ʶ���getter����
	 */
	public void setTransIdentify(String transIdentify) {
		this.transIdentify = transIdentify;
	}

	/**
	 * ���Խ�����ˮ�ŵ�getter����
	 */
	@Column(name = "TRANSSERIALNUMBER")
	@JSON(serialize=false)  
	public String getTransSerialNumber() {
		return this.transSerialNumber;
	}

	/**
	 * ���Խ�����ˮ�ŵ�setter����
	 */
	public void setTransSerialNumber(String transSerialNumber) {
		this.transSerialNumber = transSerialNumber;
	}
	
	/**
	 * �����̼ҽ�����ˮ�ŵ�getter����
	 */
	@Column(name = "MERCHANTTRANSSERIALNUMBER")
	@JSON(serialize=false)  
	public String getMerchantTransSerialNumber() {
		return merchantTransSerialNumber;
	}
	
	/**
	 * �����̼ҽ�����ˮ�ŵ�setter����
	 */
	public void setMerchantTransSerialNumber(String merchantTransSerialNumber) {
		this.merchantTransSerialNumber = merchantTransSerialNumber;
	}

	/**
	 * ���Զ����ŵ�getter����
	 */

	@Column(name = "ORDERSERIALNUMBER")
	@JSON(serialize=false)  
	public String getOrderSerialNumber() {
		return this.orderSerialNumber;
	}

	/**
	 * ���Զ����ŵ�setter����
	 */
	public void setOrderSerialNumber(String orderSerialNumber) {
		this.orderSerialNumber = orderSerialNumber;
	}
	
	/**
	 * ���Ժ������������ŵ�getter����
	 */
	@Column(name = "MERCHANTORDERNUMBER")
	@JSON(serialize=false)  
	public String getMerchantOrderNumber() {
		return merchantOrderNumber;
	}
	
	/**
	 * ���Ժ������������ŵ�setter����
	 */
	public void setMerchantOrderNumber(String merchantOrderNumber) {
		this.merchantOrderNumber = merchantOrderNumber;
	}
	
	/**
	 * ���Ա����ŵ�getter����
	 */
	@Column(name = "POLICYSERIALNUMBER")
	@JSON(serialize=false)  
	public String getPolicySerialNumber() {
		return this.policySerialNumber;
	}

	/**
	 * ���Ա����ŵ�setter����
	 */
	public void setPolicySerialNumber(String policySerialNumber) {
		this.policySerialNumber = policySerialNumber;
	}

	/**
	 * ����Ͷ�����ŵ�getter����
	 */
	@Column(name = "APPLICATIONNUMBER")
	public String getApplicationNumber() {
		return this.applicationNumber;
	}

	/**
	 * ����Ͷ�����ŵ�setter����
	 */
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}

	/**
	 * ����Ͷ����ӡˢ�ŵ�getter����
	 */

	@Column(name = "APPLICATIONSERIALNUMBER")
	@JSON(serialize=false)  
	public String getApplicationSerialNumber() {
		return this.applicationSerialNumber;
	}

	/**
	 * ����Ͷ����ӡˢ�ŵ�setter����
	 */
	public void setApplicationSerialNumber(String applicationSerialNumber) {
		this.applicationSerialNumber = applicationSerialNumber;
	}

	/**
	 * ���Ե��ӱ����ŵ�getter����
	 */

	@Column(name = "ELECTRONICPOLICYNUMBER")
	@JSON(serialize=false)  
	public String getElectronicPolicyNumber() {
		return this.electronicPolicyNumber;
	}

	/**
	 * ���Ե��ӱ����ŵ�setter����
	 */
	public void setElectronicPolicyNumber(String electronicPolicyNumber) {
		this.electronicPolicyNumber = electronicPolicyNumber;
	}

	/**
	 * ���Ե���Ͷ�����ŵ�getter����
	 */

	@Column(name = "ELECTRONICAPPLICANTNUMBER")
	@JSON(serialize=false)  
	public String getElectronicApplicantNumber() {
		return this.electronicApplicantNumber;
	}

	/**
	 * ���Ե���Ͷ�����ŵ�setter����
	 */
	public void setElectronicApplicantNumber(String electronicApplicantNumber) {
		this.electronicApplicantNumber = electronicApplicantNumber;
	}

	/**
	 * ���Ա������͵�getter����
	 */
	@Column(name = "POLICYTYPE")
	@JSON(serialize=false)  
	public Integer getPolicyType() {
		return this.policyType;
	}

	/**
	 * ���Ա������͵�setter����
	 */
	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}
	
	/**
	 * ���Ա��յ�֤���͵�getter����
	 */
	@Column(name = "DOCUMENTTYPE")
	@JSON(serialize=false)  
	public Integer getDocumentType() {
		return documentType;
	}
	
	/**
	 * ���Ա��յ�֤���͵�getter����
	 */
	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}
	
	/**
	 * �������ڽɸ�����ѡ��(���ѹ���δ��ѡ��)��getter����
	 */
	@Column(name = "OVERDUEPREMIUMPAYMENTOPTION")
	@JSON(serialize=false)  
	public Integer getOverduePremiumPaymentOption() {
		return overduePremiumPaymentOption;
	}
	
	/**
	 * �������ڽɸ�����ѡ��(���ѹ���δ��ѡ��)��setter����
	 */
	public void setOverduePremiumPaymentOption(Integer overduePremiumPaymentOption) {
		this.overduePremiumPaymentOption = overduePremiumPaymentOption;
	}

	/**
	 * ���Ա���״̬��getter����
	 */

	@Column(name = "POLICYSTATUS")
	public Integer getPolicyStatus() {
		return this.policyStatus;
	}

	/**
	 * ���Ա���״̬ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public PolicyStatus getEnumPolicyStatus() {
		if (getPolicyStatus() == null) {
			return null;
		}
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByValue(PolicyStatus.class, getPolicyStatus());
		return policyStatus;
	}
	
	/**
	 * ���Ա���״̬����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPolicyStatusByCoreValue() {
		if (getPolicyStatus() == null) {
			return "";
		}
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByValue(PolicyStatus.class, getPolicyStatus());
		return policyStatus.getCoreValue();
	}
	
	/**
	 * ���Ա���״̬�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPolicyStatusByMerchantValue() {
		if (getPolicyStatus() == null) {
			return "";
		}
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByValue(PolicyStatus.class, getPolicyStatus());
		return policyStatus.getMerchantValue();
	}
	
	/**
	 * ���Ա���״̬��setter����
	 */
	public void setPolicyStatus(Integer policyStatus) {
		this.policyStatus = policyStatus;
	}
	
	/**
	 * ���Ա���״̬��ֵ
	 */
	public void setEnumPolicyStatus(PolicyStatus  policyStatus) {
		if (policyStatus != null) {
			this.policyStatus = policyStatus.getValue();
		}
	}
	
	/**
	 * ���Ժ��ı���״̬��ֵ
	 */
	public void setPolicyStatusByCoreValue(String coreValue) {
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByCoreValue(PolicyStatus.class, coreValue);
		if (policyStatus != null) {
			this.policyStatus = policyStatus.getValue();
		}
	}
	
	/**
	 * �����̼ұ���״̬��ֵ
	 */
	public void setPolicyStatusByMerchantValue(String merchantValue) {
		PolicyStatus  policyStatus = (PolicyStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(PolicyStatus.class, merchantValue);
		if (policyStatus != null) {
			this.policyStatus = policyStatus.getValue();
		}
	}
	
	/**
	 * ���Ա���״̬���Ƶ�getter����
	 */
	@Column(name = "POLICYSTATUSNAME")
	@JSON(serialize=false)  
	public String getPolicyStatusName() {
		return policyStatusName;
	}
	/**
	 * ���Ա���״̬���Ƶ�setter����
	 */
	public void setPolicyStatusName(String policyStatusName) {
		this.policyStatusName = policyStatusName;
	}
	
	/**
	 * ���Ա���״̬������getter����
	 */
	@Column(name = "POLICYSTATUSDESC")
	@JSON(serialize=false)  
	public String getPolicyStatusDesc() {
		return policyStatusDesc;
	}
	
	/**
	 * ���Ա���״̬������setter����
	 */
	public void setPolicyStatusDesc(String policyStatusDesc) {
		this.policyStatusDesc = policyStatusDesc;
	}
	

	/**
	 * �������ɵ��ӱ���״̬��getter����
	 */

	@Column(name = "GENERATEELECTRONICPOLICYSTATUS")
	@JSON(serialize=false)  
	public Integer getGenerateElectronicPolicyStatus() {
		return this.generateElectronicPolicyStatus;
	}
	
	/**
	 * �������ɵ��ӱ���״̬ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public ElectronicPolicyStatus getEnumGenerateElectronicPolicyStatus() {
		if (getGenerateElectronicPolicyStatus() == null) {
			return null;
		}
		ElectronicPolicyStatus  electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByValue(ElectronicPolicyStatus.class, getGenerateElectronicPolicyStatus());
		return electronicPolicyStatus;
	}
	
	/**
	 * �������ɵ��ӱ���״̬����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getGenerateElectronicPolicyStatusByCoreValue() {
		if (getGenerateElectronicPolicyStatus() == null) {
			return "";
		}
		ElectronicPolicyStatus  electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByValue(ElectronicPolicyStatus.class, getGenerateElectronicPolicyStatus());
		return electronicPolicyStatus.getCoreValue();
	}
	
	/**
	 * �������ɵ��ӱ���״̬�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getGenerateElectronicPolicyStatusByMerchantValue() {
		if (getGenerateElectronicPolicyStatus() == null) {
			return "";
		}
		ElectronicPolicyStatus  electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByValue(ElectronicPolicyStatus.class, getGenerateElectronicPolicyStatus());
		return electronicPolicyStatus.getMerchantValue();
	}
	
	/**
	 * �������ɵ��ӱ���״̬��setter����
	 */
	public void setGenerateElectronicPolicyStatus(Integer generateElectronicPolicyStatus) {
		this.generateElectronicPolicyStatus = generateElectronicPolicyStatus;
	}
	
	/**
	 * �������ɵ��ӱ���״̬��ֵ
	 */
	public void setEnumGenerateElectronicPolicyStatus(ElectronicPolicyStatus  electronicPolicyStatus) {
		if (electronicPolicyStatus != null) {
			this.generateElectronicPolicyStatus = electronicPolicyStatus.getValue();
		}
	}
	
	/**
	 * ���Ժ������ɵ��ӱ���״̬��ֵ
	 */
	public void setGenerateElectronicPolicyStatusByCoreValue(String coreValue) {
		ElectronicPolicyStatus  electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByCoreValue(ElectronicPolicyStatus.class, coreValue);
		if (electronicPolicyStatus != null) {
			this.generateElectronicPolicyStatus = electronicPolicyStatus.getValue();
		}
	}
	
	/**
	 * �����̼����ɵ��ӱ���״̬��ֵ
	 */
	public void setGenerateElectronicPolicyStatusByMerchantValue(String merchantValue) {
		ElectronicPolicyStatus electronicPolicyStatus = (ElectronicPolicyStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(ElectronicPolicyStatus.class, merchantValue);
		if (electronicPolicyStatus != null) {
			this.generateElectronicPolicyStatus = electronicPolicyStatus.getValue();
		}
	}
	
	
	/**
	 * ���Ա����getter����
	 */

	@Column(name = "INSUREDAMOUNT")
	@JSON(serialize=false)  
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
	 * ���Ա��ѵ�getter����
	 */

	@Column(name = "PREMIUM")
	@JSON(serialize=false)  
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
	 * �����ۿ۱��ѵ�getter����
	 */
	@Column(name = "DISCOUNTPREMIUM")
	@JSON(serialize=false)  
	public BigDecimal getDiscountPremium() {
		return discountPremium;
	}
	
	/**
	 * �����ۿ۱��ѵ�setter����
	 */
	public void setDiscountPremium(BigDecimal discountPremium) {
		this.discountPremium = discountPremium;
	}
	
	/**
	 * �����ܱ��ѵ�getter����
	 */

	@Column(name = "GROSSPREMIUM")
	@JSON(serialize=false)  
	public BigDecimal getGrossPremium() {
		return grossPremium;
	}
	
	/**
	 * �����ܱ��ѵ�setter����
	 */
	public void setGrossPremium(BigDecimal grossPremium) {
		this.grossPremium = grossPremium;
	}

	/**
	 * �����ܱ����getter����
	 */

	@Column(name = "FACEAMOUNT")
	@JSON(serialize=false)  
	public BigDecimal getFaceAmount() {
		return this.faceAmount;
	}

	/**
	 * �����ܱ����setter����
	 */
	public void setFaceAmount(BigDecimal faceAmount) {
		this.faceAmount = faceAmount;
	}

	/**
	 * �������ڱ��ѵ�getter����
	 */

	@Column(name = "FIRSTPREMIUM")
	@JSON(serialize=false)  
	public BigDecimal getFirstPremium() {
		return this.firstPremium;
	}

	/**
	 * �������ڱ��ѵ�setter����
	 */
	public void setFirstPremium(BigDecimal firstPremium) {
		this.firstPremium = firstPremium;
	}

	/**
	 * �����״νɷѽ���getter����
	 */

	@Column(name = "INITIALPREMAMT")
	@JSON(serialize=false)  
	public BigDecimal getInitialPremAmt() {
		return this.initialPremAmt;
	}

	/**
	 * �����״νɷѽ���setter����
	 */
	public void setInitialPremAmt(BigDecimal initialPremAmt) {
		this.initialPremAmt = initialPremAmt;
	}

	/**
	 * ���Ա������ڵ�getter����
	 */

	@Column(name = "BENEFITPERIOD")
	@JSON(serialize=false)  
	public Integer getBenefitPeriod() {
		return this.benefitPeriod;
	}

	/**
	 * ���Ա������ڵ�setter����
	 */
	public void setBenefitPeriod(Integer benefitPeriod) {
		this.benefitPeriod = benefitPeriod;
	}

	/**
	 * ���Ա����������͵�getter����
	 */
	@Column(name = "BENEFITPERIODTYPE")
	@JSON(serialize=false)  
	public Integer getBenefitPeriodType() {
		return this.benefitPeriodType;
	}
	
	/**
	 * ���Ա�����������ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BenefitPeriodType getEnumBenefitPeriodType() {
		if (getBenefitPeriodType() == null) {
			return null;
		}
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByValue(BenefitPeriodType.class, getBenefitPeriodType());
		return benefitPeriodType;
	}
	
	/**
	 * ���Ա����������ͺ���ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBenefitPeriodTypeByCoreValue() {
		if (getBenefitPeriodType() == null) {
			return null;
		}
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByValue(BenefitPeriodType.class, getBenefitPeriodType());
		return benefitPeriodType.getCoreValue();
	}
	
	/**
	 * ���Ա������������̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBenefitPeriodTypeByMerchantValue() {
		if (getBenefitPeriodType() == null) {
			return "";
		}
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByValue(BenefitPeriodType.class, getBenefitPeriodType());
		return benefitPeriodType.getMerchantValue();
	}
	
	
	/**
	 * ���Ա����������͵�setter����
	 */
	public void setBenefitPeriodType(Integer benefitPeriodType) {
		this.benefitPeriodType = benefitPeriodType;
	}
	
	/**
	 * ���Ա����������͸�ֵ
	 */
	public void setEnumBenefitPeriodType(BenefitPeriodType  benefitPeriodType) {
		if (benefitPeriodType != null) {
			this.benefitPeriodType = benefitPeriodType.getValue();
		}
	}
	
	/**
	 * ���Ժ��ı����������͸�ֵ
	 */
	public void setBenefitPeriodTypeByCoreValue(String coreValue) {
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByCoreValue(BenefitPeriodType.class, coreValue);
		if (benefitPeriodType != null) {
			this.benefitPeriodType = benefitPeriodType.getValue();
		}
	}
	
	/**
	 * �����̼ұ����������͸�ֵ
	 */
	public void setBenefitPeriodTypeByMerchantValue(String merchantValue) {
		BenefitPeriodType  benefitPeriodType = (BenefitPeriodType) EnumDataUtils.getEnumDictionaryByMerchantValue(BenefitPeriodType.class, merchantValue);
		if (benefitPeriodType != null) {
			this.benefitPeriodType = benefitPeriodType.getValue();
		}
	}
	
	/**
	 * ���Խɷѷ�ʽ��getter����
	 */

	@Column(name = "PAYMENTMODE")
	public Integer getPaymentMode() {
		return this.paymentMode;
	}

	/**
	 * ���Խɷѷ�ʽö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public PaymentMode getEnumPaymentMode() {
		if (getPaymentMode() == null) {
			return null;
		}
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByValue(PaymentMode.class, getPaymentMode());
		return paymentMode;
	}
	
	/**
	 * ���Խɷѷ�ʽ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentModeByCoreValue() {
		if (getPaymentMode() == null) {
			return "";
		}
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByValue(PaymentMode.class, getPaymentMode());
		return paymentMode.getCoreValue();
	}
	
	/**
	 * ���Խɷѷ�ʽ�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentModeByMerchantValue() {
		if (getPaymentMode() == null) {
			return "";
		}
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByValue(PaymentMode.class, getPaymentMode());
		return paymentMode.getMerchantValue();
	}
	
	
	/**
	 * ���Խɷѷ�ʽ��setter����
	 */
	public void setPaymentMode(Integer paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * ���Խɷѷ�ʽ��ֵ
	 */
	public void setEnumPaymentMode(PaymentMode  paymentMode) {
		if (paymentMode != null) {
			this.paymentMode = paymentMode.getValue();
		}
		
	}
	
	/**
	 * ���Ժ��Ľɷѷ�ʽ��ֵ
	 */
	public void setPaymentModeByCoreValue(String coreValue) {
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByCoreValue(PaymentMode.class, coreValue);
		if (paymentMode != null) {
			this.paymentMode = paymentMode.getValue();
		}
	}
	
	/**
	 * �����̼ҽɷѷ�ʽ��ֵ
	 */
	public void setPaymentModeByMerchantValue(String merchantValue) {
		PaymentMode  paymentMode = (PaymentMode) EnumDataUtils.getEnumDictionaryByMerchantValue(PaymentMode.class, merchantValue);
		if (paymentMode != null) {
			this.paymentMode = paymentMode.getValue();
		}
	}
	

	/**
	 * ���Խɷ����ڵ�getter����
	 */

	@Column(name = "PAYMENTDURATION")
	@JSON(serialize=false)  
	public Integer getPaymentDuration() {
		return this.paymentDuration;
	}

	/**
	 * ���Խɷ����ڵ�setter����
	 */
	public void setPaymentDuration(Integer paymentDuration) {
		this.paymentDuration = paymentDuration;
	}

	/**
	 * ���Խɷ��������͵�getter����
	 */

	@Column(name = "PAYMENTDURATIONMODE")
	@JSON(serialize=false)  
	public Integer getPaymentDurationMode() {
		return this.paymentDurationMode;
	}


	/**
	 * ���Խɷ���������ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public PaymentDurationMode getEnumPaymentDurationMode() {
		if (getPaymentDurationMode() == null) {
			return null;
		}
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByValue(PaymentDurationMode.class, getPaymentDurationMode());
		return paymentDurationMode;
	}
	
	/**
	 * ���Խɷ��������ͺ���ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentDurationModeByCoreValue() {
		if (getPaymentDurationMode() == null) {
			return "";
		}
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByValue(PaymentDurationMode.class, getPaymentDurationMode());
		return paymentDurationMode.getCoreValue();
	}
	
	/**
	 * ���Խɷ����������̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentDurationModeByMerchantValue() {
		if (getPaymentDurationMode() == null) {
			return "";
		}
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByValue(PaymentDurationMode.class, getPaymentDurationMode());
		return paymentDurationMode.getMerchantValue();
	}
	
	/**
	 * ���Խɷ��������͵�setter����
	 */
	public void setPaymentDurationMode(Integer paymentDurationMode) {
		this.paymentDurationMode = paymentDurationMode;
	}

	/**
	 * ���Խɷ��������͸�ֵ
	 */
	public void setEnumPaymentDurationMode(PaymentDurationMode  paymentDurationMode) {
		if (paymentDurationMode != null) {
			this.paymentDurationMode = paymentDurationMode.getValue();
		}
	}
	
	/**
	 * ���Ժ��Ľɷ��������͸�ֵ
	 */
	public void setPaymentDurationModeByCoreValue(String coreValue) {
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByCoreValue(PaymentDurationMode.class, coreValue);
		if (paymentDurationMode != null) {
			this.paymentDurationMode = paymentDurationMode.getValue();
		}
	}
	
	/**
	 * �����̼ҽɷ��������͸�ֵ
	 */
	public void setPaymentDurationModeByMerchantValue(String merchantValue) {
		PaymentDurationMode  paymentDurationMode = (PaymentDurationMode) EnumDataUtils.getEnumDictionaryByMerchantValue(PaymentDurationMode.class, merchantValue);
		if (paymentDurationMode != null) {
			this.paymentDurationMode = paymentDurationMode.getValue();
		}
	}
	
	/**
	 * ����֧����ʽ��getter����
	 */

	@Column(name = "PAYMENTMETHOD")
	@JSON(serialize=false)  
	public Integer getPaymentMethod() {
		return this.paymentMethod;
	}
	
	/**
	 * ����֧����ʽ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public PaymentMethod getEnumPaymentMethod() {
		if (getPaymentMethod() == null) {
			return null;
		}
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByValue(PaymentMethod.class, getPaymentMethod());
		return paymentMethod;
	}
	
	/**
	 * ����֧����ʽ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentMethodByCoreValue() {
		if (getPaymentMethod() == null) {
			return "";
		}
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByValue(PaymentMethod.class, getPaymentMethod());
		return paymentMethod.getCoreValue();
	}
	
	/**
	 * ����֧����ʽ�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPaymentMethodByMerchantValue() {
		if (getPaymentMethod() == null) {
			return "";
		}
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByValue(PaymentMethod.class, getPaymentMethod());
		return paymentMethod.getMerchantValue();
	}
	
	/**
	 * ����֧����ʽ��setter����
	 */
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * ����֧����ʽ��ֵ
	 */
	public void setEnumPaymentMethod(PaymentMethod  paymentMethod) {
		if (paymentMethod != null) {
			this.paymentMethod = paymentMethod.getValue();
		}
	}
	
	/**
	 * ���Ժ���֧����ʽ��ֵ
	 */
	public void setPaymentMethodByCoreValue(String coreValue) {
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByCoreValue(PaymentMethod.class, coreValue);
		if (paymentMethod != null) {
			this.paymentMethod = paymentMethod.getValue();
		}
	}
	
	/**
	 * �����̼�֧����ʽ��ֵ
	 */
	public void setPaymentMethodByMerchantValue(String merchantValue) {
		PaymentMethod  paymentMethod = (PaymentMethod) EnumDataUtils.getEnumDictionaryByMerchantValue(PaymentMethod.class, merchantValue);
		if (paymentMethod != null) {
			this.paymentMethod = paymentMethod.getValue();
		}
	}
	
	
	/**
	 * �����������ȡ��ʽ��getter����
	 */

	@Column(name = "BENEFITMODE")
	@JSON(serialize=false)  
	public Integer getBenefitMode() {
		return this.benefitMode;
	}
	
	/**
	 * �����������ȡ��ʽö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BenefitMode getEnumBenefitMode() {
		if (getBenefitMode() == null) {
			return null;
		}
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByValue(BenefitMode.class, getBenefitMode());
		return benefitMode;
	}
	
	/**
	 * �����������ȡ��ʽ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBenefitModeByCoreValue() {
		if (getBenefitMode() == null) {
			return "";
		}
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByValue(BenefitMode.class, getBenefitMode());
		return benefitMode.getCoreValue();
	}
	
	/**
	 * �����������ȡ��ʽ�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBenefitModeByMerchantValue() {
		if (getBenefitMode() == null) {
			return "";
		}
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByValue(BenefitMode.class, getBenefitMode());
		return benefitMode.getMerchantValue();
	}
	
	/**
	 * �����������ȡ��ʽ��setter����
	 */
	public void setBenefitMode(Integer benefitMode) {
		this.benefitMode = benefitMode;
	}
	
	/**
	 * �����������ȡ��ʽ��ֵ
	 */
	public void setEnumBenefitMode(BenefitMode  benefitMode) {
		if (benefitMode != null) {
			this.benefitMode = benefitMode.getValue();
		}
	}
	
	/**
	 * ���Ժ����������ȡ��ʽ��ֵ
	 */
	public void setBenefitModeByCoreValue(String coreValue) {
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByCoreValue(BenefitMode.class, coreValue);
		if (benefitMode != null) {
			this.benefitMode = benefitMode.getValue();
		}
	}
	
	/**
	 * �����̼��������ȡ��ʽ��ֵ
	 */
	public void setBenefitModeByMerchantValue(String merchantValue) {
		BenefitMode  benefitMode = (BenefitMode) EnumDataUtils.getEnumDictionaryByMerchantValue(BenefitMode.class, merchantValue);
		if (benefitMode != null) {
			this.benefitMode = benefitMode.getValue();
		}
	}
	
	
	/**
	 * ���Ժ�����ȡ��ʽ��getter����
	 */

	@Column(name = "DIVTYPE")
	@JSON(serialize=false)  
	public Integer getDivType() {
		return this.divType;
	}
	
	/**
	 * ���Ժ�����ȡ��ʽö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public DivType getEnumDivType() {
		if (getDivType() == null) {
			return null;
		}
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByValue(DivType.class, getDivType());
		return divType;
	}
	
	/**
	 * ���Ժ�����ȡ��ʽ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDivTypeByCoreValue() {
		if (getDivType() == null) {
			return "";
		}
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByValue(DivType.class, getDivType());
		return divType.getCoreValue();
	}
	
	/**
	 * ���Ժ�����ȡ��ʽ�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDivTypeByMerchantValue() {
		if (getDivType() == null) {
			return "";
		}
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByValue(DivType.class, getDivType());
		return divType.getMerchantValue();
	}
	
	
	/**
	 * ���Ժ�����ȡ��ʽ��setter����
	 */
	public void setDivType(Integer divType) {
		this.divType = divType;
	}

	/**
	 * ���Ժ�����ȡ��ʽȡ��ʽ��ֵ
	 */
	public void setEnumDivType(DivType  divType) {
		if (divType != null) {
			this.divType = divType.getValue();
		}
	}
	
	/**
	 * ���Ժ��ĺ�����ȡ��ʽȡ��ʽ��ֵ
	 */
	public void setDivTypeByCoreValue(String coreValue) {
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByCoreValue(DivType.class, coreValue);
		if (divType != null) {
			this.divType = divType.getValue();
		}
	}
	
	/**
	 * �����̼Һ�����ȡ��ʽȡ��ʽ��ֵ
	 */
	public void setDivTypeByMerchantValue(String merchantValue) {
		DivType  divType = (DivType) EnumDataUtils.getEnumDictionaryByMerchantValue(DivType.class, merchantValue);
		if (divType != null) {
			this.divType = divType.getValue();
		}
	}
	
	
	/**
	 * ���������ȡ���޵�getter����
	 */

	@Column(name = "ANNUITYPAYOUTDURATION")
	@JSON(serialize=false)  
	public String getAnnuityPayoutDuration() {
		return this.annuityPayoutDuration;
	}

	/**
	 * ���������ȡ���޵�setter����
	 */
	public void setAnnuityPayoutDuration(String annuityPayoutDuration) {
		this.annuityPayoutDuration = annuityPayoutDuration;
	}

	/**
	 * ���������ȡ�������͵�getter����
	 */

	@Column(name = "ANNUITYPAYOUTDURATIONMODE")
	@JSON(serialize=false)  
	public Integer getAnnuityPayoutDurationMode() {
		return this.annuityPayoutDurationMode;
	}
	/**
	 * ���������ȡ��������ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public AnnuityPayoutDurationMode getEnumAnnuityPayoutDurationMode() {
		if (getAnnuityPayoutDurationMode() == null) {
			return null;
		}
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByValue(AnnuityPayoutDurationMode.class, getAnnuityPayoutDurationMode());
		return annuityPayoutDurationMode;
	}
	
	/**
	 * ���������ȡ�������ͺ���ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAnnuityPayoutDurationModeByCoreValue() {
		if (getAnnuityPayoutDurationMode() == null) {
			return "";
		}
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByValue(AnnuityPayoutDurationMode.class, getAnnuityPayoutDurationMode());
		return annuityPayoutDurationMode.getCoreValue();
	}
	
	/**
	 * ���������ȡ���������̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAnnuityPayoutDurationModeByMerchantValue() {
		if (getAnnuityPayoutDurationMode() == null) {
			return "";
		}
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByValue(AnnuityPayoutDurationMode.class, getAnnuityPayoutDurationMode());
		return annuityPayoutDurationMode.getMerchantValue();
	}
	
	/**
	 * ���������ȡ�������͵�setter����
	 */
	public void setAnnuityPayoutDurationMode(Integer annuityPayoutDurationMode) {
		this.annuityPayoutDurationMode = annuityPayoutDurationMode;
	}
	
	/**
	 * ���������ȡ��������ȡ��ʽ��ֵ
	 */
	public void setEnumAnnuityPayoutDurationMode(AnnuityPayoutDurationMode  annuityPayoutDurationMode) {
		if (annuityPayoutDurationMode != null) {
			this.annuityPayoutDurationMode = annuityPayoutDurationMode.getValue();
		}
	}
	
	/**
	 * ���Ժ��������ȡ��������ȡ��ʽ��ֵ
	 */
	public void setAnnuityPayoutDurationModeByCoreValue(String coreValue) {
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByCoreValue(AnnuityPayoutDurationMode.class, coreValue);
		if (annuityPayoutDurationMode != null) {
			this.annuityPayoutDurationMode = annuityPayoutDurationMode.getValue();
		}
	}
	
	/**
	 * �����̼������ȡ��������ȡ��ʽ��ֵ
	 */
	public void setAnnuityPayoutDurationModeByMerchantValue(String merchantValue) {
		AnnuityPayoutDurationMode  annuityPayoutDurationMode = (AnnuityPayoutDurationMode) EnumDataUtils.getEnumDictionaryByMerchantValue(AnnuityPayoutDurationMode.class, merchantValue);
		if (annuityPayoutDurationMode != null) {
			this.annuityPayoutDurationMode = annuityPayoutDurationMode.getValue();
		}
	}
	
	/**
	 * ���������ȡ��ʼ�����getter����
	 */

	@Column(name = "PAYOUTSTART")
	@JSON(serialize=false)  
	public String getPayoutStart() {
		return this.payoutStart;
	}

	/**
	 * ���������ȡ��ʼ�����setter����
	 */
	public void setPayoutStart(String payoutStart) {
		this.payoutStart = payoutStart;
	}

	/**
	 * ���������ȡ��ֹ�����getter����
	 */

	@Column(name = "PAYOUTEND")
	@JSON(serialize=false)  
	public String getPayoutEnd() {
		return this.payoutEnd;
	}

	/**
	 * ���������ȡ��ֹ�����setter����
	 */
	public void setPayoutEnd(String payoutEnd) {
		this.payoutEnd = payoutEnd;
	}

	/**
	 * ���Ը��ӱ���/��������getter����
	 */

	@Column(name = "EXCESSPREMAMT")
	@JSON(serialize=false)  
	public BigDecimal getExcessPremAmt() {
		return this.excessPremAmt;
	}

	/**
	 * ���Ը��ӱ���/��������setter����
	 */
	public void setExcessPremAmt(BigDecimal excessPremAmt) {
		this.excessPremAmt = excessPremAmt;
	}

	/**
	 * ����ֽ�ʱ�����ݷѵ�getter����
	 */

	@Column(name = "POLICYDELIVERYFEE")
	@JSON(serialize=false)  
	public BigDecimal getPolicyDeliveryFee() {
		return this.policyDeliveryFee;
	}

	/**
	 * ����ֽ�ʱ�����ݷѵ�setter����
	 */
	public void setPolicyDeliveryFee(BigDecimal policyDeliveryFee) {
		this.policyDeliveryFee = policyDeliveryFee;
	}

	/**
	 * �����Զ��˱������getter����
	 */

	@Column(name = "POLICYSTATUSMESSAGE")
	@JSON(serialize=false)  
	public Integer getPolicyStatusMessage() {
		return this.policyStatusMessage;
	}
	
	/**
	 * �����Զ��˱�����ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumPolicyStatusMessage() {
		if (getPolicyStatusMessage() == null) {
			return null;
		}
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getPolicyStatusMessage());
		return policyStatusMessage;
	}
	
	/**
	 * �����Զ��˱��������ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPolicyStatusMessageByCoreValue() {
		if (getPolicyStatusMessage() == null) {
			return "";
		}
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getPolicyStatusMessage());
		return policyStatusMessage.getCoreValue();
	}
	
	/**
	 * �����Զ��˱������̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getPolicyStatusMessageByMerchantValue() {
		if (getPolicyStatusMessage() == null) {
			return "";
		}
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getPolicyStatusMessage());
		return policyStatusMessage.getMerchantValue();
	}
	
	/**
	 * �����Զ��˱������setter����
	 */
	public void setPolicyStatusMessage(Integer policyStatusMessage) {
		this.policyStatusMessage = policyStatusMessage;
	}
	
	/**
	 * �����Զ��˱����渳ֵ
	 */
	public void setEnumPolicyStatusMessage(BooleanStatus  policyStatusMessage) {
		if (policyStatusMessage != null) {
			this.policyStatusMessage = policyStatusMessage.getValue();
		}
	}
	
	/**
	 * ���Ժ����Զ��˱����渳ֵ
	 */
	public void setPolicyStatusMessageByCoreValue(String coreValue) {
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (policyStatusMessage != null) {
			this.policyStatusMessage = policyStatusMessage.getValue();
		}
	}
	
	/**
	 * �����̼��Զ��˱����渳ֵ
	 */
	public void setPolicyStatusMessageByMerchantValue(String merchantValue) {
		BooleanStatus  policyStatusMessage = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (policyStatusMessage != null) {
			this.policyStatusMessage = policyStatusMessage.getValue();
		}
	}
	
	/**
	 * ����ǩԼ���ڵ�getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SIGNEDDATE")
	@JSON(serialize=false)  
	public Date getSignedDate() {
		return this.signedDate;
	}

	/**
	 * ����ǩԼ���ڵ�setter����
	 */
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}

	/**
	 * ���Ա�����Ч�յ�getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INCEPTIONDATE")
	public Date getInceptionDate() {
		return this.inceptionDate;
	}

	/**
	 * ���Ա�����Ч�յ�setter����
	 */
	public void setInceptionDate(Date inceptionDate) {
		this.inceptionDate = inceptionDate;
	}
	
	/**
	 * ���Ա��������յ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "APPLICATIONDATE")
	@JSON(serialize=false)  
	public Date getApplicationDate() {
		return applicationDate;
	}
	
	/**
	 * ���Ա��������յ�setter����
	 */
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}
	
	/**
	 * ���Ա��������յ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "BACKTRACKDATE")
	@JSON(serialize=false)  
	public Date getBacktrackDate() {
		return backtrackDate;
	}
	
	/**
	 * ���Ա��������յ�setter����
	 */
	public void setBacktrackDate(Date backtrackDate) {
		this.backtrackDate = backtrackDate;
	}

	/**
	 * ���Ա������ڵ�getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSURANCESTARTPERIOD")
	public Date getInsuranceStartPeriod() {
		return insuranceStartPeriod;
	}
	
	/**
	 * ���Ա������ڵ�setter����
	 */
	public void setInsuranceStartPeriod(Date insuranceStartPeriod) {
		this.insuranceStartPeriod = insuranceStartPeriod;
	}
	
	/**
	 * ���Ա���ֹ�ڵ�getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSURANCEENDPERIOD")
	@JSON(serialize=false)  
	public Date getInsuranceEndPeriod() {
		return insuranceEndPeriod;
	}
	
	/**
	 * ���Ա���ֹ�ڵ�setter����
	 */
	public void setInsuranceEndPeriod(Date insuranceEndPeriod) {
		this.insuranceEndPeriod = insuranceEndPeriod;
	}
	
	/**
	 * �������淽ʽ��getter����
	 */
	@Column(name = "BENEFICIARYMODE")
	@JSON(serialize=false)  
	public Integer getBeneficiaryMode() {
		return beneficiaryMode;
	}
	
	/**
	 * �������淽ʽ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BeneficiaryMode getEnumBeneficiaryMode() {
		if (getBeneficiaryMode() == null) {
			return null;
		}
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByValue(BeneficiaryMode.class, getBeneficiaryMode());
		return beneficiaryMode;
	}
	
	/**
	 * �������淽ʽ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBeneficiaryModeByCoreValue() {
		if (getBeneficiaryMode() == null) {
			return "";
		}
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByValue(BeneficiaryMode.class, getBeneficiaryMode());
		return beneficiaryMode.getCoreValue();
	}
	
	/**
	 * �������淽ʽ�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getBeneficiaryModeByMerchantValue() {
		if (getBeneficiaryMode() == null) {
			return "";
		}
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByValue(BeneficiaryMode.class, getBeneficiaryMode());
		return beneficiaryMode.getMerchantValue();
	}
	
	/**
	 * �������淽ʽ��setter����
	 */
	public void setBeneficiaryMode(Integer beneficiaryMode) {
		this.beneficiaryMode = beneficiaryMode;
	}
	
	/**
	 * �������淽ʽ��ֵ
	 */
	public void setEnumBeneficiaryMode(BeneficiaryMode  beneficiaryMode) {
		if (beneficiaryMode != null) {
			this.beneficiaryMode = beneficiaryMode.getValue();
		}
	}
	
	/**
	 * ���Ժ������淽ʽ��ֵ
	 */
	public void setBeneficiaryModeByCoreValue(String coreValue) {
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByCoreValue(BeneficiaryMode.class, coreValue);
		if (beneficiaryMode != null) {
			this.beneficiaryMode = beneficiaryMode.getValue();
		}
	}
	
	/**
	 * �����̼����淽ʽ��ֵ
	 */
	public void setBeneficiaryModeByMerchantValue(String merchantValue) {
		BeneficiaryMode  beneficiaryMode = (BeneficiaryMode) EnumDataUtils.getEnumDictionaryByMerchantValue(BeneficiaryMode.class, merchantValue);
		if (beneficiaryMode != null) {
			this.beneficiaryMode = beneficiaryMode.getValue();
		}
	}
	
	/**
	 * ��������Ŀ�ĵص�getter����
	 */
	@Column(name = "TRAVELDESTINATION")
	@JSON(serialize=false)  
	public String getTravelDestination() {
		return travelDestination;
	}
	
	/**
	 * ��������Ŀ�ĵص�setter����
	 */
	public void setTravelDestination(String travelDestination) {
		this.travelDestination = travelDestination;
	}

	/**
	 * ����Ͷ���������ڵ�getter����
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "SUBMISSIONDATE")
	@JSON(serialize=false)  
	public Date getSubmissionDate() {
		return this.submissionDate;
	}

	/**
	 * ����Ͷ���������ڵ�setter����
	 */
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	/**
	 * ���Է�����getter����
	 */

	@Column(name = "UNITCOUNT")
	@JSON(serialize=false)  
	public Integer getUnitCount() {
		return this.unitCount;
	}

	/**
	 * ���Է�����setter����
	 */
	public void setUnitCount(Integer unitCount) {
		this.unitCount = unitCount;
	}

	/**
	 * ����Ͷ�������͵�getter����
	 */

	@Column(name = "FORMID")
	@JSON(serialize=false)  
	public String getFormID() {
		return this.formID;
	}

	/**
	 * ����Ͷ�������͵�setter����
	 */
	public void setFormID(String formID) {
		this.formID = formID;
	}
	
	/**
	 * �����Ķ�����������Ϣ��setter����
	 */
	@Column(name = "CONTRACTNAMES")
	@JSON(serialize=false)  
	public String getContractNames() {
		return contractNames;
	}
	
	/**
	 * �����Ķ�����������Ϣ��setter����
	 */
	public void setContractNames(String contractNames) {
		this.contractNames = contractNames;
	}
	
	/**
	 * �����ر�������setter����
	 */
	@Column(name = "SPECIALSTATEMENT")
	@JSON(serialize=false)  
	public String getSpecialStatement() {
		return specialStatement;
	}
	
	/**
	 * �����ر�������setter����
	 */
	public void setSpecialStatement(String specialStatement) {
		this.specialStatement = specialStatement;
	}

	/**
	 * ���Ա��ѽ���Ͷ���˻�����ѡ���getter����
	 */

	@Column(name = "FUNDTRANSFERDATEBASEDON")
	@JSON(serialize=false)  
	public Integer getFundTransferDateBasedOn() {
		return this.fundTransferDateBasedOn;
	}
	
	/**
	 * �����걣�ѽ���Ͷ���˻�����ѡ��ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public FundTransferDateBasedOnType getEnumFundTransferDateBasedOn() {
		if (getFundTransferDateBasedOn() == null) {
			return null;
		}
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByValue(FundTransferDateBasedOnType.class, getFundTransferDateBasedOn());
		return fundTransferDateBasedOn;
	}
	
	/**
	 * �����걣�ѽ���Ͷ���˻�����ѡ�����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getFundTransferDateBasedOnByCoreValue() {
		if (getFundTransferDateBasedOn() == null) {
			return "";
		}
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByValue(FundTransferDateBasedOnType.class, getFundTransferDateBasedOn());
		return fundTransferDateBasedOn.getCoreValue();
	}
	
	/**
	 * �����걣�ѽ���Ͷ���˻�����ѡ���̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getFundTransferDateBasedOnByMerchantValue() {
		if (getFundTransferDateBasedOn() == null) {
			return "";
		}
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByValue(FundTransferDateBasedOnType.class, getFundTransferDateBasedOn());
		return fundTransferDateBasedOn.getMerchantValue();
	}
	
	
	/**
	 * ���Ա��ѽ���Ͷ���˻�����ѡ���setter����
	 */
	public void setFundTransferDateBasedOn(Integer fundTransferDateBasedOn) {
		this.fundTransferDateBasedOn = fundTransferDateBasedOn;
	}
	
	/**
	 * ���Ա��ѽ���Ͷ���˻�����ѡ��ȡ��ʽ��ֵ
	 */
	public void setEnumFundTransferDateBasedOn(FundTransferDateBasedOnType  fundTransferDateBasedOn) {
		if (fundTransferDateBasedOn != null) {
			this.fundTransferDateBasedOn = fundTransferDateBasedOn.getValue();
		}
	}
	
	/**
	 * ���Ժ��ı��ѽ���Ͷ���˻�����ѡ��ȡ��ʽ��ֵ
	 */
	public void setFundTransferDateBasedOnByCoreValue(String coreValue) {
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByCoreValue(FundTransferDateBasedOnType.class, coreValue);
		if (fundTransferDateBasedOn != null) {
			this.fundTransferDateBasedOn = fundTransferDateBasedOn.getValue();
		}
	}
	
	/**
	 * �����̼ұ��ѽ���Ͷ���˻�����ѡ��ȡ��ʽ��ֵ
	 */
	public void setFundTransferDateBasedOnByMerchantValue(String merchantValue) {
		FundTransferDateBasedOnType  fundTransferDateBasedOn = (FundTransferDateBasedOnType) EnumDataUtils.getEnumDictionaryByMerchantValue(FundTransferDateBasedOnType.class, merchantValue);
		if (fundTransferDateBasedOn != null) {
			this.fundTransferDateBasedOn = fundTransferDateBasedOn.getValue();
		}
	}
	
	/**
	 * �������д����getter����
	 */

	@Column(name = "BANKCODE")
	@JSON(serialize=false)  
	public String getBankCode() {
		return this.bankCode;
	}

	/**
	 * �������д����setter����
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * �����Ƿ���ҪӢ��֤����getter����
	 */

	@Column(name = "CERTIFICATIONREQUIRED")
	@JSON(serialize=false)  
	public Integer getCertificationRequired() {
		return this.certificationRequired;
	}

	/**
	 * ���Է���ҪӢ��֤��ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumCertificationRequired() {
		if (getCertificationRequired() == null) {
			return null;
		}
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getCertificationRequired());
		return certificationRequired;
	}
	
	/**
	 * ���Է���ҪӢ��֤������ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getCertificationRequiredByCoreValue() {
		if (getCertificationRequired() == null) {
			return "";
		}
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getCertificationRequired());
		return certificationRequired.getCoreValue();
	}
	
	/**
	 * ���Է���ҪӢ��֤���̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getCertificationRequiredByMerchantValue() {
		if (getCertificationRequired() == null) {
			return "";
		}
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getCertificationRequired());
		return certificationRequired.getMerchantValue();
	}
	
	/**
	 * �����Ƿ���ҪӢ��֤����setter����
	 */
	public void setCertificationRequired(Integer certificationRequired) {
		this.certificationRequired = certificationRequired;
	}
	/**
	 * ���Է���ҪӢ��֤����ֵ
	 */
	public void setEnumCertificationRequired(BooleanStatus  certificationRequired) {
		if (certificationRequired != null) {
			this.certificationRequired = certificationRequired.getValue();
		}
	}
	
	/**
	 * ���Ժ��ķ���ҪӢ��֤����ֵ
	 */
	public void setCertificationRequiredByCoreValue(String coreValue) {
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (certificationRequired != null) {
			this.certificationRequired = certificationRequired.getValue();
		}
	}
	
	/**
	 * �����̼ҷ���ҪӢ��֤����ֵ
	 */
	public void setCertificationRequiredByMerchantValue(String merchantValue) {
		BooleanStatus  certificationRequired = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (certificationRequired != null) {
			this.certificationRequired = certificationRequired.getValue();
		}
	}
	
	/**
	 * �����Ƿ���Ҫǩ֤��getter����
	 */

	@Column(name = "REQUIREVISA")
	@JSON(serialize=false)  
	public Integer getRequireVisa() {
		return requireVisa;
	}
	
	/**
	 * ���Է���Ҫǩ֤ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumRequireVisa() {
		if (getRequireVisa() == null) {
			return null;
		}
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getRequireVisa());
		return requireVisa;
	}
	
	/**
	 * ���Է���Ҫǩ֤����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getRequireVisaByCoreValue() {
		if (getRequireVisa() == null) {
			return "";
		}
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getRequireVisa());
		return requireVisa.getCoreValue();
	}
	
	/**
	 * ���Է���Ҫǩ֤�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getRequireVisaByMerchantValue() {
		if (getRequireVisa() == null) {
			return "";
		}
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getRequireVisa());
		return requireVisa.getMerchantValue();
	}
	
	/**
	 * �����Ƿ���Ҫǩ֤��setter����
	 */
	public void setRequireVisa(Integer requireVisa) {
		this.requireVisa = requireVisa;
	}
	/**
	 * ���Է���Ҫǩ֤��ֵ
	 */
	public void setEnumRequireVisa(BooleanStatus  requireVisa) {
		if (requireVisa != null) {
			this.requireVisa = requireVisa.getValue();
		}
	}
	
	/**
	 * ���Ժ��ķ���Ҫǩ֤��ֵ
	 */
	public void setRequireVisaByCoreValue(String coreValue) {
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (requireVisa != null) {
			this.requireVisa = requireVisa.getValue();
		}
	}
	
	/**
	 * �����̼ҷ���Ҫǩ֤��ֵ
	 */
	public void setRequireVisaByMerchantValue(String merchantValue) {
		BooleanStatus  requireVisa = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (requireVisa != null) {
			this.requireVisa = requireVisa.getValue();
		}
	}
	
	/**
	 * �����Ƿ�Ͷ�ݷ�Ʊ��getter����
	 */

	@Column(name = "DELIVERYINVOICE")
	@JSON(serialize=false)  
	public Integer getDeliveryInvoice() {
		return this.deliveryInvoice;
	}
	
	/**
	 * �����Ƿ�Ͷ�ݷ�Ʊö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumDeliveryInvoice() {
		if (getDeliveryInvoice() == null) {
			return null;
		}
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryInvoice());
		return deliveryInvoice;
	}
	
	/**
	 * �����Ƿ�Ͷ�ݷ�Ʊ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryInvoiceByCoreValue() {
		if (getDeliveryInvoice() == null) {
			return "";
		}
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryInvoice());
		return deliveryInvoice.getCoreValue();
	}
	
	/**
	 * �����Ƿ�Ͷ�ݷ�Ʊ�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryInvoiceByMerchantValue() {
		if (getDeliveryInvoice() == null) {
			return "";
		}
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryInvoice());
		return deliveryInvoice.getMerchantValue();
	}
	
	
	/**
	 * �����Ƿ�Ͷ�ݷ�Ʊ��setter����
	 */
	public void setDeliveryInvoice(Integer deliveryInvoice) {
		this.deliveryInvoice = deliveryInvoice;
	}
	
	/**
	 * �����Ƿ�Ͷ�ݷ�Ʊ��ֵ
	 */
	public void setEnumDeliveryInvoice(BooleanStatus  deliveryInvoice) {
		if (deliveryInvoice != null) {
			this.deliveryInvoice = deliveryInvoice.getValue();
		}
	}
	
	/**
	 * ���Ժ����Ƿ�Ͷ�ݷ�Ʊ��ֵ
	 */
	public void setDeliveryInvoiceByCoreValue(String coreValue) {
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (deliveryInvoice != null) {
			this.deliveryInvoice = deliveryInvoice.getValue();
		}
	}
	
	/**
	 * �����̼��Ƿ�Ͷ�ݷ�Ʊ��ֵ
	 */
	public void setDeliveryInvoiceByMerchantValue(String merchantValue) {
		BooleanStatus  deliveryInvoice = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (deliveryInvoice != null) {
			this.deliveryInvoice = deliveryInvoice.getValue();
		}
	}
	
	
	/**
	 * �����Ƿ�Ͷ��ֽ�ʱ�����getter����
	 */

	@Column(name = "DELIVERYHARDCOPY")
	@JSON(serialize=false)  
	public Integer getDeliveryHardCopy() {
		return this.deliveryHardCopy;
	}
	
	/**
	 * �����Ƿ�Ͷ��ֽ�ʱ���ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumDeliveryHardCopy() {
		if (getDeliveryHardCopy() == null) {
			return null;
		}
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryHardCopy());
		return deliveryHardCopy;
	}
	
	/**
	 * �����Ƿ�Ͷ��ֽ�ʱ�������ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryHardCopyByCoreValue() {
		if (getDeliveryHardCopy() == null) {
			return "";
		}
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryHardCopy());
		return deliveryHardCopy.getCoreValue();
	}
	
	/**
	 * �����Ƿ�Ͷ��ֽ�ʱ����̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryHardCopyByMerchantValue() {
		if (getDeliveryHardCopy() == null) {
			return "";
		}
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryHardCopy());
		return deliveryHardCopy.getMerchantValue();
	}
	
	
	/**
	 * �����Ƿ�Ͷ��ֽ�ʱ�����setter����
	 */
	public void setDeliveryHardCopy(Integer deliveryHardCopy) {
		this.deliveryHardCopy = deliveryHardCopy;
	}
	/**
	 * �����Ƿ�Ͷ��ֽ�ʱ�����ֵ
	 */
	public void setEnumDeliveryHardCopy(BooleanStatus  deliveryHardCopy) {
		if (deliveryHardCopy != null) {
			this.deliveryHardCopy = deliveryHardCopy.getValue();
		}
	}
	
	/**
	 * ���Ժ����Ƿ�Ͷ��ֽ�ʱ�����ֵ
	 */
	public void setDeliveryHardCopyByCoreValue(String coreValue) {
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (deliveryHardCopy != null) {
			this.deliveryHardCopy = deliveryHardCopy.getValue();
		}
	}
	
	/**
	 * �����̼��Ƿ�Ͷ��ֽ�ʱ�����ֵ
	 */
	public void setDeliveryHardCopyByMerchantValue(String merchantValue) {
		BooleanStatus  deliveryHardCopy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (deliveryHardCopy != null) {
			this.deliveryHardCopy = deliveryHardCopy.getValue();
		}
	}
	
	/**
	 * �����Ƿ��͵��ӱ�����getter����
	 */

	@Column(name = "DELIVERYEPOLICY")
	@JSON(serialize=false)  
	public Integer getDeliveryEPolicy() {
		return this.deliveryEPolicy;
	}
	
	/**
	 * �����Ƿ��͵��ӱ���ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumDeliveryEPolicy() {
		if (getDeliveryEPolicy() == null) {
			return null;
		}
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryEPolicy());
		return deliveryEPolicy;
	}
	
	/**
	 * �����Ƿ��͵��ӱ�������ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryEPolicyByCoreValue() {
		if (getDeliveryEPolicy() == null) {
			return "";
		}
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryEPolicy());
		return deliveryEPolicy.getCoreValue();
	}
	
	/**
	 * �����Ƿ��͵��ӱ����̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDeliveryEPolicyByMerchantValue() {
		if (getDeliveryEPolicy() == null) {
			return "";
		}
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getDeliveryEPolicy());
		return deliveryEPolicy.getMerchantValue();
	}
	
	
	/**
	 * �����Ƿ��͵��ӱ�����setter����
	 */
	public void setDeliveryEPolicy(Integer deliveryEPolicy) {
		this.deliveryEPolicy = deliveryEPolicy;
	}
	
	/**
	 * �����Ƿ��͵��ӱ�����ֵ
	 */
	public void setEnumDeliveryEPolicy(BooleanStatus  deliveryEPolicy) {
		if (deliveryEPolicy != null) {
			this.deliveryEPolicy = deliveryEPolicy.getValue();
		}
	}
	
	/**
	 * ���Ժ����Ƿ��͵��ӱ�����ֵ
	 */
	public void setDeliveryEPolicyByCoreValue(String coreValue) {
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (deliveryEPolicy != null) {
			this.deliveryEPolicy = deliveryEPolicy.getValue();
		}
	}
	
	/**
	 * �����̼��Ƿ��͵��ӱ�����ֵ
	 */
	public void setDeliveryEPolicyByMerchantValue(String merchantValue) {
		BooleanStatus  deliveryEPolicy = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (deliveryEPolicy != null) {
			this.deliveryEPolicy = deliveryEPolicy.getValue();
		}
	}
	
	/**
	 * ���Ի�����getter����
	 */

	@Column(name = "CAMPAIGNCODE")
	@JSON(serialize=false)  
	public String getCampaignCode() {
		return this.campaignCode;
	}

	/**
	 * ���Ի�����setter����
	 */
	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	/**
	 * ���Ի���Ƶ�getter����
	 */

	@Column(name = "CAMPAIGNNAME")
	@JSON(serialize=false)  
	public String getCampaignName() {
		return this.campaignName;
	}

	/**
	 * ���Ի���Ƶ�setter����
	 */
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	/**
	 * �����ۿ۴����getter����
	 */

	@Column(name = "DISCOUNTTYPECODE")
	@JSON(serialize=false)  
	public String getDiscountTypeCode() {
		return this.discountTypeCode;
	}

	/**
	 * �����ۿ۴����setter����
	 */
	public void setDiscountTypeCode(String discountTypeCode) {
		this.discountTypeCode = discountTypeCode;
	}

	/**
	 * �����ۿ�ϵ����getter����
	 */

	@Column(name = "DISCOUNTRATE")
	@JSON(serialize=false)  
	public BigDecimal getDiscountRate() {
		return this.discountRate;
	}

	/**
	 * �����ۿ�ϵ����setter����
	 */
	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	/**
	 * �����Ƿ�����һ�����Զ�������getter����
	 */

	@Column(name = "AUTORENEWABLE")
	@JSON(serialize=false)  
	public Integer getAutoRenewable() {
		return this.autoRenewable;
	}
	
	/**
	 * �����Ƿ�����һ�����Զ�����ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumAutoRenewable() {
		if (getAutoRenewable() == null) {
			return null;
		}
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAutoRenewable());
		return autoRenewable;
	}
	
	/**
	 * �����Ƿ�����һ�����Զ���������ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAutoRenewableByCoreValue() {
		if (getAutoRenewable() == null) {
			return "";
		}
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAutoRenewable());
		return autoRenewable.getCoreValue();
	}
	
	/**
	 * �����Ƿ�����һ�����Զ������̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAutoRenewableByMerchantValue() {
		if (getAutoRenewable() == null) {
			return "";
		}
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAutoRenewable());
		return autoRenewable.getMerchantValue();
	}
	
	/**
	 * �����Ƿ�����һ�����Զ�������setter����
	 */
	public void setAutoRenewable(Integer autoRenewable) {
		this.autoRenewable = autoRenewable;
	}
	
	/**
	 * �����Ƿ�����һ�����Զ�������ֵ
	 */
	public void setEnumAutoRenewable(BooleanStatus  autoRenewable) {
		if (autoRenewable != null) {
			this.autoRenewable = autoRenewable.getValue();
		}
	}
	
	/**
	 * ���Ժ����Ƿ�����һ�����Զ�������ֵ
	 */
	public void setAutoRenewableByCoreValue(String coreValue) {
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (autoRenewable != null) {
			this.autoRenewable = autoRenewable.getValue();
		}
	}
	
	/**
	 * �����̼��Ƿ�����һ�����Զ�������ֵ
	 */
	public void setAutoRenewableByMerchantValue(String merchantValue) {
		BooleanStatus  autoRenewable = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (autoRenewable != null) {
			this.autoRenewable = autoRenewable.getValue();
		}
	}
	
	
	/**
	 * �����ײʹ����getter����
	 */

	@Column(name = "COMBOCODE")
	@JSON(serialize=false)  
	public String getComboCode() {
		return this.comboCode;
	}

	/**
	 * �����ײʹ����setter����
	 */
	public void setComboCode(String comboCode) {
		this.comboCode = comboCode;
	}
	
	/**
	 * �����ײ����͵�getter����
	 */
	@Column(name = "COMBONAME")
	@JSON(serialize=false)  
	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}
	
	/**
	 * ���Ը��˿ͻ���getter����
	 */

	@Column(name = "PERSONALUSERSERIALNO")
	@JSON(serialize=false)  
	public String getPersonalUserSerialNo() {
		return this.personalUserSerialNo;
	}

	/**
	 * ���Ը��˿ͻ���setter����
	 */
	public void setPersonalUserSerialNo(String personalUserSerialNo) {
		this.personalUserSerialNo = personalUserSerialNo;
	}

	/**
	 * �����û��ŵ�getter����
	 */
	@Column(name = "CUSTOMERID")
	@JSON(serialize=false)  
	public String getCustomerID() {
		return this.customerID;
	}

	/**
	 * �����û��ŵ�setter����
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
	/**
	 * ���Բ�Ʒ�����getter����
	 */

	@Column(name = "PRODUCTCODE")
	public String getProductCode() {
		return productCode;
	}
	
	/**
	 * ���Բ�Ʒ�����getter����
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	/**
	 * ���Բ�Ʒ���Ƶ�getter����
	 */
	@Column(name = "PRODUCTNAME")
	@JSON(serialize=false)  
	public String getProductName() {
		return productName;
	}
	
	/**
	 * ���Բ�Ʒ���Ƶ�setter����
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * �����̼Ҳ�Ʒ�����getter����
	 */
	@Column(name = "MERCHANTPRODUCTCODE")
	@JSON(serialize=false)  
	public String getMerchantProductCode() {
		return merchantProductCode;
	}

	/**
	 * �����̼Ҳ�Ʒ�����setter����
	 */
	public void setMerchantProductCode(String merchantProductCode) {
		this.merchantProductCode = merchantProductCode;
	}

	/**
	 * �����̼Ҳ�Ʒ���Ƶ�setter����
	 */
	@Column(name = "MERCHANTPRODUCTNAME")
	@JSON(serialize=false)  
	public String getMerchantProductName() {
		return merchantProductName;
	}

	/**
	 * �����̼Ҳ�Ʒ���Ƶ�setter����
	 */
	public void setMerchantProductName(String merchantProductName) {
		this.merchantProductName = merchantProductName;
	}

	/**
	 * ���Դ����˴����getter����
	 */
	@Column(name = "AGENTCODE")
	@JSON(serialize=false)  
	public String getAgentCode() {
		return agentCode;
	}

	/**
	 * ���Դ����˴����setter����
	 */
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	
	/** 
	 * ���Դ�����Э��ŵ�getter���� 
	 */
	@Column(name = "AGREEMENTNO")
	@JSON(serialize=false)  
	public String getAgreementNo() {
		return agreementNo;
	}
	
	/** 
	 * ���Դ�����Э��ŵ�setter���� 
	 */
	public void setAgreementNo(String agreementNo){
		this.agreementNo = agreementNo;
	}

	/** 
	 * ���Դ�����Э��ŵ�getter���� 
	 */
	@Column(name = "AGENTNAME")
	@JSON(serialize=false)  
	public String getAgentName() {
		return agentName;
	}
	
	/** 
	 * ���Դ�����Э��ŵ�setter���� 
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	/** 
	 * ���Դ������������ŵ�getter���� 
	 */
	@Column(name = "DEPARTMENTNO")
	@JSON(serialize=false)  
	public String getDepartmentNo() {
		return departmentNo;
	}
	
	/** 
	 * ���Դ������������ŵ�setter���� 
	 */
	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}
	
	/** 
	 * ���Դ����������������Ƶ�getter���� 
	 */
	@Column(name = "DEPARTMENTNAME")
	@JSON(serialize=false)  
	public String getDepartmentName() {
		return departmentName;
	}
	
	/** 
	 * ���Դ����������������Ƶ�setter���� 
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	/** 
	 * �������������getter���� 
	 */
	@Column(name = "INTERMEDIARYCODE")
	@JSON(serialize=false)  
	public String getIntermediaryCode() {
		return intermediaryCode;
	}
	
	/** 
	 * �������������setter���� 
	 */
	public void setIntermediaryCode(String intermediaryCode) {
		this.intermediaryCode = intermediaryCode;
	}
	
	/** 
	 * �����������Ƶ�getter���� 
	 */
	@Column(name = "INTERMEDIARYNAME")
	@JSON(serialize=false)  
	public String getIntermediaryName() {
		return intermediaryName;
	}
	
	/** 
	 * �����������Ƶ�setter���� 
	 */
	public void setIntermediaryName(String intermediaryName) {
		this.intermediaryName = intermediaryName;
	}
	/**
	 * ������������getter����
	 */

	@Column(name = "BRANCHCODE")
	@JSON(serialize=false)  
	public String getBranchCode() {
		return this.branchCode;
	}

	/**
	 * ������������setter����
	 */
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	/**
	 * �����������Ƶ�getter����
	 */
	@Column(name = "BRANCHNAME")
	@JSON(serialize=false)  
	public String getBranchName() {
		return branchName;
	}
	
	/**
	 * �����������Ƶ�setter����
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	/** 
	 * ���Ի��������getter���� 
	 */
	@Column(name = "ORGANIZATIONCODE")
	@JSON(serialize=false)  
	public String getOrganizationCode() {
		return organizationCode;
	}
	
	/** 
	 * ���Ի��������setter���� 
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	
	/** 
	 * ���Ի������Ƶ�getter���� 
	 */
	@Column(name = "ORGANIZATIONNAME")
	@JSON(serialize=false)  
	public String getOrganizationName() {
		return organizationName;
	}
	
	/** 
	 * ���Ի������Ƶ�setter���� 
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	
	/** 
	 * ���Ժ������������getter���� 
	 */
	@Column(name = "INSTITUTIONCODE")
	@JSON(serialize=false)  
	public String getInstitutionCode() {
		return institutionCode;
	}
	
	/** 
	 * ���Ժ������������setter���� 
	 */
	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	/** 
	 * ���Ժ����������Ƶ�getter���� 
	 */
	@Column(name = "INSTITUTIONNAME")
	@JSON(serialize=false)  
	public String getInstitutionName() {
		return institutionName;
	}
	
	/** 
	 * ���Ժ����������Ƶ�setter���� 
	 */
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
	
	/** 
	 * ����ͬ��״̬��getter���� 
	 */
	@Column(name = "SYNCSTATUS")
	@JSON(serialize=false)  
	public Integer getSyncStatus() {
		return syncStatus;
	}
	
	/**
	 * ����ͬ��״̬ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public SyncStatus getEnumSyncStatus() {
		if (getSyncStatus() == null) {
			return null;
		}
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByValue(SyncStatus.class, getSyncStatus());
		return syncStatus;
	}
	
	/**
	 * ����ͬ��״̬����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getSyncStatusByCoreValue() {
		if (getSyncStatus() == null) {
			return "";
		}
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByValue(SyncStatus.class, getSyncStatus());
		return syncStatus.getCoreValue();
	}
	
	/**
	 * ����ͬ��״̬�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getSyncStatusByMerchantValue() {
		if (getSyncStatus() == null) {
			return "";
		}
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByValue(SyncStatus.class, getSyncStatus());
		return syncStatus.getMerchantValue();
	}
	
	/** 
	 * ����ͬ��״̬��setter���� 
	 */
	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}
	
	/**
	 * ����ͬ��״̬��ֵ
	 */
	public void setEnumSyncStatus(SyncStatus  syncStatus) {
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/**
	 * ���Ժ���ͬ��״̬��ֵ
	 */
	public void setSyncStatusByCoreValue(String coreValue) {
		SyncStatus  syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByCoreValue(SyncStatus.class, coreValue);
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/**
	 * �����̼�ͬ��״̬��ֵ
	 */
	public void setSyncStatusByMerchantValue(String merchantValue) {
		SyncStatus syncStatus = (SyncStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(SyncStatus.class, merchantValue);
		if (syncStatus != null) {
			this.syncStatus = syncStatus.getValue();
		}
	}
	
	/** 
	 * ����ͬ��״̬������getter���� 
	 */
	@Column(name = "SYNCSTATUSDESC")
	@JSON(serialize=false)  
	public String getSyncStatusDesc() {
		return syncStatusDesc;
	}
	
	/** 
	 * ����ͬ��״̬������setter���� 
	 */
	public void setSyncStatusDesc(String syncStatusDesc) {
		this.syncStatusDesc = syncStatusDesc;
	}

	/** 
	 * ����ͬ����ʼʱ���getter���� 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNCSTARTTIME")
	@JSON(serialize=false)  
	public Date getSyncStartTime() {
		return syncStartTime;
	}
	
	/** 
	 * ����ͬ����ʼʱ���setter���� 
	 */
	public void setSyncStartTime(Date syncStartTime) {
		this.syncStartTime = syncStartTime;
	}
	
	/** 
	 * ����ͬ������ʱ���getter���� 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SYNCENDTIME")
	@JSON(serialize=false)  
	public Date getSyncEndTime() {
		return syncEndTime;
	}
	
	/** 
	 * ����ͬ������ʱ���setter���� 
	 */
	public void setSyncEndTime(Date syncEndTime) {
		this.syncEndTime = syncEndTime;
	}
	
	/** 
	 * �����ֽ��ֵ��getter���� 
	 */
	@Column(name = "CASHVALUE")
	@JSON(serialize=false)  
	public BigDecimal getCashValue() {
		return cashValue;
	}

	/** 
	 * �����ֽ��ֵ��setter���� 
	 */
	public void setCashValue(BigDecimal cashValue) {
		this.cashValue = cashValue;
	}
	
	/** 
	 * �����Ƿ�ɳ�����getter���� 
	 */
	@Column(name = "REVERSALFLAG")
	@JSON(serialize=false)  
	public Integer getReversalFlag() {
		return reversalFlag;
	}

	/**
	 * �����Ƿ�ɳ���ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumReversalFlag() {
		if (getReversalFlag() == null) {
			return null;
		}
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getReversalFlag());
		return reversalFlag;
	}
	
	/**
	 * �����Ƿ�ɳ�������ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getReversalFlagByCoreValue() {
		if (getReversalFlag() == null) {
			return "";
		}
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getReversalFlag());
		return reversalFlag.getCoreValue();
	}
	
	/**
	 * �����Ƿ�ɳ����̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getReversalFlagByMerchantValue() {
		if (getReversalFlag() == null) {
			return "";
		}
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getReversalFlag());
		return reversalFlag.getMerchantValue();
	}
	
	
	/** 
	 * �����Ƿ�ɳ�����setter���� 
	 */
	public void setReversalFlag(Integer reversalFlag) {
		this.reversalFlag = reversalFlag;
	}
	
	/**
	 * �����Ƿ�ɳ�����ֵ
	 */
	public void setEnumReversalFlag(BooleanStatus  reversalFlag) {
		if (reversalFlag != null) {
			this.reversalFlag = reversalFlag.getValue();
		}
	}
	
	/**
	 * ���Ժ����Ƿ�ɳ�����ֵ
	 */
	public void setReversalFlagByCoreValue(String coreValue) {
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (reversalFlag != null) {
			this.reversalFlag = reversalFlag.getValue();
		}
	}
	
	/**
	 * �����̼��Ƿ�ɳ�����ֵ
	 */
	public void setReversalFlagByMerchantValue(String merchantValue) {
		BooleanStatus  reversalFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (reversalFlag != null) {
			this.reversalFlag = reversalFlag.getValue();
		}
	}
	
	/** 
	 * ����ȫ����ȡ������Ƿ�����˱���getter���� 
	 */
	@Column(name = "MUSTCANCELFLAG")
	@JSON(serialize=false)  
	public Integer getMustCancelFlag() {
		return mustCancelFlag;
	}
	
	/**
	 * ����ȫ����ȡ������Ƿ�����˱�ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumMustCancelFlag() {
		if (getMustCancelFlag() == null) {
			return null;
		}
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getMustCancelFlag());
		return mustCancelFlag;
	}
	
	/**
	 * ����ȫ����ȡ������Ƿ�����˱�����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getMustCancelFlagByCoreValue() {
		if (getMustCancelFlag() == null) {
			return "";
		}
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getMustCancelFlag());
		return mustCancelFlag.getCoreValue();
	}
	
	/**
	 * ����ȫ����ȡ������Ƿ�����˱��̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getMustCancelFlagByMerchantValue() {
		if (getMustCancelFlag() == null) {
			return "";
		}
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getMustCancelFlag());
		return mustCancelFlag.getMerchantValue();
	}
	
	/** 
	 * ����ȫ����ȡ������Ƿ�����˱���setter���� 
	 */
	public void setMustCancelFlag(Integer mustCancelFlag) {
		this.mustCancelFlag = mustCancelFlag;
	}
	
	/**
	 * ���Զ���ȡ������Ƿ�����˱���ֵ
	 */
	public void setEnumMustCancelFlag(BooleanStatus  mustCancelFlag) {
		if (mustCancelFlag != null) {
			this.mustCancelFlag = mustCancelFlag.getValue();
		}
	}
	
	/**
	 * ���Ժ��Ķ���ȡ������Ƿ�����˱���ֵ
	 */
	public void setMustCancelFlagByCoreValue(String coreValue) {
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (mustCancelFlag != null) {
			this.mustCancelFlag = mustCancelFlag.getValue();
		}
	}
	
	/**
	 * �����̼Ҷ���ȡ������Ƿ�����˱���ֵ
	 */
	public void setMustCancelFlagByMerchantValue(String merchantValue) {
		BooleanStatus  mustCancelFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (mustCancelFlag != null) {
			this.mustCancelFlag = mustCancelFlag.getValue();
		}
	}
	
	/** 
	 * �����Ƿ�������֧ȡ��getter���� 
	 */
	@Column(name = "ALLOWPARTYWITHDROWFLAG")
	@JSON(serialize=false)  
	public Integer getAllowPartyWithdrowFlag() {
		return allowPartyWithdrowFlag;
	}
	
	/**
	 * �����Ƿ�������֧ȡö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public BooleanStatus getEnumAllowPartyWithdrowFlag() {
		if (getAllowPartyWithdrowFlag() == null) {
			return null;
		}
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAllowPartyWithdrowFlag());
		return allowPartyWithdrowFlag;
	}
	
	/**
	 * �����Ƿ�������֧ȡ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAllowPartyWithdrowFlagByCoreValue() {
		if (getAllowPartyWithdrowFlag() == null) {
			return "";
		}
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAllowPartyWithdrowFlag());
		return allowPartyWithdrowFlag.getCoreValue();
	}
	
	/**
	 * �����Ƿ�������֧ȡ�̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getAllowPartyWithdrowFlagByMerchantValue() {
		if (getAllowPartyWithdrowFlag() == null) {
			return "";
		}
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByValue(BooleanStatus.class, getAllowPartyWithdrowFlag());
		return allowPartyWithdrowFlag.getMerchantValue();
	}
	
	
	/** 
	 * �����Ƿ�������֧ȡ��setter���� 
	 */
	public void setAllowPartyWithdrowFlag(Integer allowPartyWithdrowFlag) {
		this.allowPartyWithdrowFlag = allowPartyWithdrowFlag;
	}
	
	/**
	 * �����Ƿ�������֧ȡ��ֵ
	 */
	public void setEnumAllowPartyWithdrowFlag(BooleanStatus  allowPartyWithdrowFlag) {
		if (allowPartyWithdrowFlag != null) {
			this.allowPartyWithdrowFlag = allowPartyWithdrowFlag.getValue();
		}
	}
	
	/**
	 * ���Ժ����Ƿ�������֧ȡ��ֵ
	 */
	public void setAllowPartyWithdrowFlagByCoreValue(String coreValue) {
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByCoreValue(BooleanStatus.class, coreValue);
		if (allowPartyWithdrowFlag != null) {
			this.allowPartyWithdrowFlag = allowPartyWithdrowFlag.getValue();
		}
	}
	
	/**
	 * �����̼��Ƿ�������֧ȡ��ֵ
	 */
	public void setAllowPartyWithdrowFlagByMerchantValue(String merchantValue) {
		BooleanStatus  allowPartyWithdrowFlag = (BooleanStatus) EnumDataUtils.getEnumDictionaryByMerchantValue(BooleanStatus.class, merchantValue);
		if (allowPartyWithdrowFlag != null) {
			this.allowPartyWithdrowFlag = allowPartyWithdrowFlag.getValue();
		}
	}
	
	/**
	 * ���Ա�ȫ����ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRESERVEACCEPTTIME")
	@JSON(serialize=false)  
	public Date getPreserveAcceptTime() {
		return preserveAcceptTime;
	}
	
	/**
	 * ���Ա�ȫ����ʱ���setter����
	 */
	public void setPreserveAcceptTime(Date preserveAcceptTime) {
		this.preserveAcceptTime = preserveAcceptTime;
	}
	
	/**
	 * ���Ա�ȫ��Чʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PRESERVEEFFECTIVETIME")
	@JSON(serialize=false)  
	public Date getPreserveEffectiveTime() {
		return preserveEffectiveTime;
	}
	
	/**
	 * ���Ա�ȫ��Чʱ���setter����
	 */
	public void setPreserveEffectiveTime(Date preserveEffectiveTime) {
		this.preserveEffectiveTime = preserveEffectiveTime;
	}
	
	/**
	 * ���Գ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REFUNDPOLICYTIME")
	@JSON(serialize=false)  
	public Date getReFundPolicyTime() {
		return reFundPolicyTime;
	}
	
	/**
	 * ���Գ���ʱ���setter����
	 */
	public void setReFundPolicyTime(Date reFundPolicyTime) {
		this.reFundPolicyTime = reFundPolicyTime;
	}

	/**
	 * ���Գ����ɹ�ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REFUNDPOLICYSUCCESSTIME")
	@JSON(serialize=false)  
	public Date getReFundPolicySuccessTime() {
		return reFundPolicySuccessTime;
	}
	
	/**
	 * ���Գ����ɹ�ʱ���setter����
	 */
	public void setReFundPolicySuccessTime(Date reFundPolicySuccessTime) {
		this.reFundPolicySuccessTime = reFundPolicySuccessTime;
	}

	
	/**
	 * ���Բ���Ա��getter����
	 */

	@Column(name = "OPERATORID")
	@JSON(serialize=false)  
	public String getOperatorID() {
		return this.operatorID;
	}

	/**
	 * ���Բ���Ա��setter����
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * ���Է�Ʊ��getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public Invoice getInvoice() {
		return this.invoice;
	}

	/**
	 * ���Է�Ʊ��setter����
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
		if (getInvoice() != null && getInvoice().getInsurancePolicy() == null) {
			getInvoice().setInsurancePolicy(this);
		}
	}

	/**
	 * ���Ը����˻���getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public PaymentAccount getPaymentAccount() {
		return this.paymentAccount;
	}

	/**
	 * ���Ը����˻���setter����
	 */
	public void setPaymentAccount(PaymentAccount paymentAccount) {
		this.paymentAccount = paymentAccount;
		if (getPaymentAccount() != null && getPaymentAccount().getInsurancePolicy() == null) {
			getPaymentAccount().setInsurancePolicy(this);
		}
	}

	/**
	 * �����ռ��˵�getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public Addressee getAddressee() {
		return this.addressee;
	}

	/**
	 * �����ռ��˵�setter����
	 */
	public void setAddressee(Addressee addressee) {
		this.addressee = addressee;
		if (getAddressee() != null && getAddressee().getInsurancePolicy() == null) {
			getAddressee().setInsurancePolicy(this);
		}
	}

	/**
	 * ���Զ�����getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public OrderForm getOrderForm() {
		return orderForm;
	}

	/**
	 * ���Զ�����setter����
	 */
	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
		if (getOrderForm() != null && getOrderForm().getInsurancePolicy() == null) {
			getOrderForm().setInsurancePolicy(this);
		}
	}

	/**
	 * ����Ͷ���˵�getter����
	 */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public InsuranceApplicant getInsuranceApplicant() {
		return this.insuranceApplicant;
	}

	/**
	 * ����Ͷ���˵�setter����
	 */
	public void setInsuranceApplicant(InsuranceApplicant insuranceApplicant) {
		this.insuranceApplicant = insuranceApplicant;
		if (getInsuranceApplicant()!= null && getInsuranceApplicant().getInsurancePolicy() == null) {
			getInsuranceApplicant().setInsurancePolicy(this);
		}
	}
	
	/**
	 * ���Ա������˵�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "insurancePolicy")
	@Where(clause="ROLEKIND = 'Insured'")
	@JSON(serialize=false)  
	public List<Insured> getInsureds() {
		return this.insureds;
	}
	
	/**
	 * ���Ա������˵�setter����
	 */
	public void setInsureds(List<Insured> insureds) {
		this.insureds = insureds;
	}

	/**
	 * ����������б���������Ϣ
	 */
	public void addAllInsureds(List<Insured> insureds) {
		
		for (Insured insured:insureds) {
			if (!getInsureds().contains(insured)) {
				getInsureds().add(insured);
			}
		}
		
		for (Insured insured:getInsureds()) {
			if (insured.getInsurancePolicy() == null) {
				insured.setInsurancePolicy(this);
			}
			
		}
	}
	
	/**
	 * ���Ա�������������getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public Integer getInsuredNumber() {
		insuredNumber = getInsureds().size();
		return insuredNumber;
	}
	
	/**
	 * ���Ա�������������setter����
	 */
	public void setInsuredNumber(Integer insuredNumber) {
		this.insuredNumber = insuredNumber;
	}

	/**
	 * ���������˵�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@Where(clause="ROLEKIND = 'Beneficiary'")
	@Fetch(FetchMode.SUBSELECT)
	@JSON(serialize=false)  
	public List<Beneficiary> getBeneficiaries() {
		return this.beneficiaries;
	}
	
	/**
	 * ���������˵�setter����
	 */
	public void setBeneficiaries(List<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}

	/**
	 * �����������������
	 */
	public void addAllBeneficiaries(List<Beneficiary> beneficiaries) {
		for (Beneficiary beneficiary:beneficiaries) {
			if (!getBeneficiaries().contains(beneficiary)) {
				getBeneficiaries().add(beneficiary);
			}
		}
		for (Beneficiary beneficiary:getBeneficiaries()) {
			if (beneficiary.getInsurancePolicy() == null) {
				beneficiary.setInsurancePolicy(this);
			}
		}
	}
	
	/**
	 * ����������������getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public Integer getBeneficiaryNumber() {
		beneficiaryNumber = getBeneficiaries().size();
		return beneficiaryNumber;
	}

	/**
	 * ����������������setter����
	 */
	public void setBeneficiaryNumber(Integer beneficiaryNumber) {
		this.beneficiaryNumber = beneficiaryNumber;
	}

	/**
	 * ���Ա������ε�getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@JSON(serialize=false)  
	public List<InsurancePolicyLiability> getInsurancePolicyLiabilities() {
		return insurancePolicyLiabilities;
	}
	
	/**
	 * ���Ա������ε�setter����
	 */
	public void setInsurancePolicyLiabilities(
			List<InsurancePolicyLiability> insurancePolicyLiabilities) {
		this.insurancePolicyLiabilities = insurancePolicyLiabilities;
	}

	/**
	 * ����������б�������
	 */
	public void addAllInsurancePolicyLiabilities(List<InsurancePolicyLiability> insurancePolicyLiabilities) {
		for (InsurancePolicyLiability insurancePolicyLiability:insurancePolicyLiabilities) {
			if (!getInsurancePolicyLiabilities().contains(insurancePolicyLiability)) {
				getInsurancePolicyLiabilities().add(insurancePolicyLiability);
			}
		}
		
		for (InsurancePolicyLiability insurancePolicyLiability:getInsurancePolicyLiabilities()) {
			if (insurancePolicyLiability.getInsurancePolicy() == null) {
				insurancePolicyLiability.setInsurancePolicy(this);
			}
		}
		
	}
	
	/**
	 * ���Ա�������������getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public Integer getInsurancePolicyLiabilityNumber() {
		insurancePolicyLiabilityNumber = getInsurancePolicyLiabilities().size();
		return insurancePolicyLiabilityNumber;
	}
	
	/**
	 * ���Ա�������������setter����
	 */
	public void setInsurancePolicyLiabilityNumber(
			Integer insurancePolicyLiabilityNumber) {
		this.insurancePolicyLiabilityNumber = insurancePolicyLiabilityNumber;
	}

	/**
	 * ����Ͷ����֪��getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@Fetch(FetchMode.SUBSELECT)
	@JSON(serialize=false)  
	public List<InsureInformBook> getInsureInformBooks() {
		return insureInformBooks;
	}
	
	/**
	 * ����Ͷ����֪��setter����
	 */
	public void setInsureInformBooks(List<InsureInformBook> insureInformBooks) {
		this.insureInformBooks = insureInformBooks;
	}

	/**
	 * �����������Ͷ����֪��Ϣ
	 */
	public void addAllInsureInformBooks(List<InsureInformBook> insureInformBooks) {
		
		for (InsureInformBook insureInformBook:insureInformBooks) {
			if (!getInsureInformBooks().contains(insureInformBook)) {
				getInsureInformBooks().add(insureInformBook);
			}
		}
		
		for (InsureInformBook insureInformBook:getInsureInformBooks()) {
			if (insureInformBook.getInsurancePolicy() == null) {
				insureInformBook.setInsurancePolicy(this);
			}
		}
		
	}
	
	/**
	 * �����ʽ𻮲���getter����
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "insurancePolicy")
	@Fetch(FetchMode.SUBSELECT)
	@JSON(serialize=false)  
	public List<FundsTransfer> getFundsTransfers() {
		return fundsTransfers;
	}
	
	/**
	 * �����ʽ𻮲���setter����
	 */
	public void setFundsTransfers(List<FundsTransfer> fundsTransfers) {
		this.fundsTransfers = fundsTransfers;
	}

	/**
	 * ������������ʽ𻮲���Ϣ
	 */
	public void addAllFundsTransfers(List<FundsTransfer> fundsTransfers) {
		for (FundsTransfer fundsTransfer:fundsTransfers) {
			if (!getFundsTransfers().contains(fundsTransfer)) {
				getFundsTransfers().add(fundsTransfer);
			}
		}
		for (FundsTransfer fundsTransfer:getFundsTransfers()) {
			if (fundsTransfer.getInsurancePolicy() == null) {
				fundsTransfer.setInsurancePolicy(this);
			}
		}
	}
	
	
	/**
	 * ���Դ���ʱ���getter����
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATETIME")
	@JSON(serialize=false)  
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
	@JSON(serialize=false)  
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
	 * ���� ���ڽɷ���ʽ ��getter����
	 */
	@Column(name = "EXPAYMODE")
	@JSON(serialize=false)  
	public Integer getExPayMode() {
		return exPayMode;
	}

	/**
	 * ���� ���ڽɷ���ʽ ��setter����
	 */
	public void setExPayMode(Integer exPayMode) {
		this.exPayMode = exPayMode;
	}

	/**
	 * ���� �������ͷ�ʽ ��getter����
	 */
	@Column(name = "GETPOLMODE")
	@JSON(serialize=false)  
	public Integer getGetPolMode() {
		return getPolMode;
	}

	/**
	 * ���� �������ͷ�ʽ ��setter����
	 */
	public void setGetPolMode(Integer getPolMode) {
		this.getPolMode = getPolMode;
	}

	/**
	 * ���� �������� ��getter����
	 */
	@Column(name = "PASSWORD")
	@JSON(serialize=false)  
	public String getPassword() {
		return password;
	}

	/**
	 * ���� �������� ��setter����
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ���� �ر�Լ�� ��getter����
	 */
	@Column(name = "SPECCONTENT")
	@JSON(serialize=false)  
	public String getSpecContent() {
		return specContent;
	}

	/**
	 * ���� �ر�Լ�� ��setter����
	 */
	public void setSpecContent(String specContent) {
		this.specContent = specContent;
	}

	/**
	 * ���� ��Ʊӡˢ���� ��getter����
	 */
	@Column(name = "TEMPFEENO")
	@JSON(serialize=false)  
	public String getTempFeeNo() {
		return tempFeeNo;
	}

	/**
	 * ���� ��Ʊӡˢ���� ��setter����
	 */
	public void setTempFeeNo(String tempFeeNo) {
		this.tempFeeNo = tempFeeNo;
	}

	/**
	 * ���� ��������� ��getter����
	 */
	@Column(name = "AGENTGROUP")
	@JSON(serialize=false)  
	public String getAgentGroup() {
		return agentGroup;
	}

	/**
	 * ���� ��������� ��setter����
	 */
	public void setAgentGroup(String agentGroup) {
		this.agentGroup = agentGroup;
	}

	/**
	 * ���� ��ͬ���鴦��ʽ ��getter����
	 */
	@Column(name = "DISPUTEDFLAG")
	@JSON(serialize=false)  
	public Integer getDisputedFlag() {
		return disputedFlag;
	}

	/**
	 * ���� ��ͬ���鴦��ʽ ��setter����
	 */
	public void setDisputedFlag(Integer disputedFlag) {
		this.disputedFlag = disputedFlag;
	}

	/**
	 * ���� �ٲ�ίԱ������ ��getter����
	 */
	@Column(name = "ACNAME")
	@JSON(serialize=false)  
	public String getAcName() {
		return acName;
	}

	/**
	 * ���� �ٲ�ίԱ������ ��setter����
	 */
	public void setAcName(String acName) {
		this.acName = acName;
	}

	/**
	 * ���� �طñ�־ ��getter����
	 */
	@Column(name = "ISVISIT")
	@JSON(serialize=false)  
	public Integer getIsVisit() {
		return isVisit;
	}

	/**
	 * ���� �طñ�־ ��setter����
	 */
	public void setIsVisit(Integer isVisit) {
		this.isVisit = isVisit;
	}

	/**
	 * ���� �󶨱�־ ��getter����
	 */
	@Column(name = "ISBIND")
	@JSON(serialize=false)  
	public Integer getIsBind() {
		return isBind;
	}

	/**
	 * ���� �󶨱�־ ��setter����
	 */
	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
	}

	/**
	 * ���� �󶨱�־ ��getter����
	 */
	@Column(name = "INSUREINFORMBOOKNUMBER")
	@JSON(serialize=false)  
	public Integer getInsureInformBookNumber() {
		return insureInformBookNumber;
	}

	/**
	 * ���� �󶨱�־ ��setter����
	 */
	public void setInsureInformBookNumber(Integer insureInformBookNumber) {
		this.insureInformBookNumber = insureInformBookNumber;
	}
	
	/**
	 * ���� ���ڽɷѷ�ʽ ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public ExPayMode getEnumExPayMode() {
		if (getExPayMode() == null) {
			return null;
		}
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByValue(ExPayMode.class, getExPayMode());
		return exPayMode;
	}
	
	/**
	 * ���� ���ڽɷѷ�ʽ ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getExPayModeByCoreValue() {
		if (getExPayMode() == null) {
			return "";
		}
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByValue(ExPayMode.class, getExPayMode());
		return exPayMode.getCoreValue();
	}
	
	/**
	 * ���� ���ڽɷѷ�ʽ �̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getExPayModeByMerchantValue() {
		if (getExPayMode() == null) {
			return "";
		}
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByValue(ExPayMode.class, getExPayMode());
		return exPayMode.getMerchantValue();
	}
	
	
	
	
	/**
	 * ���� ���ڽɷѷ�ʽ ��ֵ
	 */
	public void setEnumExPayMode(ExPayMode  exPayMode) {
		if (exPayMode != null) {
			this.exPayMode = exPayMode.getValue();
		}
		
	}
	
	/**
	 * ���Ժ��� ���ڽɷѷ�ʽ ��ֵ
	 */
	public void setExPayModeByCoreValue(String coreValue) {
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByCoreValue(ExPayMode.class, coreValue);
		if (exPayMode != null) {
			this.exPayMode = exPayMode.getValue();
		}
	}
	
	/**
	 * �����̼� ���ڽɷѷ�ʽ ��ֵ
	 */
	public void setExPayModeByMerchantValue(String merchantValue) {
		ExPayMode  exPayMode = (ExPayMode) EnumDataUtils.getEnumDictionaryByMerchantValue(ExPayMode.class, merchantValue);
		if (exPayMode != null) {
			this.exPayMode = exPayMode.getValue();
		}
	}
	
	/**
	 * ���� �������ͷ�ʽ ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public GetPolMode getEnumGetPolMode() {
		if (getGetPolMode() == null) {
			return null;
		}
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByValue(GetPolMode.class, getGetPolMode());
		return getPolMode;
	}
	
	/**
	 * ���� �������ͷ�ʽ ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getGetPolModeByCoreValue() {
		if (getGetPolMode() == null) {
			return "";
		}
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByValue(GetPolMode.class, getGetPolMode());
		return getPolMode.getCoreValue();
	}
	
	/**
	 * ���� �������ͷ�ʽ �̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getGetPolModeByMerchantValue() {
		if (getGetPolMode() == null) {
			return "";
		}
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByValue(GetPolMode.class, getGetPolMode());
		return getPolMode.getMerchantValue();
	}
	
	
	
	
	/**
	 * ���� �������ͷ�ʽ ��ֵ
	 */
	public void setEnumGetPolMode(GetPolMode  getPolMode) {
		if (getPolMode != null) {
			this.getPolMode = getPolMode.getValue();
		}
		
	}
	
	/**
	 * ���Ժ��� �������ͷ�ʽ ��ֵ
	 */
	public void setGetPolModeByCoreValue(String coreValue) {
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByCoreValue(GetPolMode.class, coreValue);
		if (getPolMode != null) {
			this.getPolMode = getPolMode.getValue();
		}
	}
	
	/**
	 * �����̼� �������ͷ�ʽ ��ֵ
	 */
	public void setGetPolModeByMerchantValue(String merchantValue) {
		GetPolMode  getPolMode = (GetPolMode) EnumDataUtils.getEnumDictionaryByMerchantValue(GetPolMode.class, merchantValue);
		if (getPolMode != null) {
			this.getPolMode = getPolMode.getValue();
		}
	}
	
	/**
	 * ���� ��ͬ���鴦��ʽ ö�����getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public DisputedFlag getEnumDisputedFlag() {
		if (getDisputedFlag() == null) {
			return null;
		}
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByValue(DisputedFlag.class, getDisputedFlag());
		return disputedFlag;
	}
	
	/**
	 * ���� ��ͬ���鴦��ʽ ����ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDisputedFlagByCoreValue() {
		if (getDisputedFlag() == null) {
			return "";
		}
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByValue(DisputedFlag.class, getDisputedFlag());
		return disputedFlag.getCoreValue();
	}
	
	/**
	 * ���� ��ͬ���鴦��ʽ �̼�ֵ��getter����
	 */
	@Transient
	@JSON(serialize=false)  
	public String getDisputedFlagByMerchantValue() {
		if (getDisputedFlag() == null) {
			return "";
		}
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByValue(DisputedFlag.class, getDisputedFlag());
		return disputedFlag.getMerchantValue();
	}
	
	
	
	
	/**
	 * ���� ��ͬ���鴦��ʽ ��ֵ
	 */
	public void setEnumDisputedFlag(DisputedFlag  disputedFlag) {
		if (disputedFlag != null) {
			this.disputedFlag = disputedFlag.getValue();
		}
		
	}
	
	/**
	 * ���Ժ��� ��ͬ���鴦��ʽ ��ֵ
	 */
	public void setDisputedFlagByCoreValue(String coreValue) {
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByCoreValue(DisputedFlag.class, coreValue);
		if (disputedFlag != null) {
			this.disputedFlag = disputedFlag.getValue();
		}
	}
	
	/**
	 * �����̼� ��ͬ���鴦��ʽ ��ֵ
	 */
	public void setDisputedFlagByMerchantValue(String merchantValue) {
		DisputedFlag  disputedFlag = (DisputedFlag) EnumDataUtils.getEnumDictionaryByMerchantValue(DisputedFlag.class, merchantValue);
		if (disputedFlag != null) {
			this.disputedFlag = disputedFlag.getValue();
		}
	}

	@Column(name = "QUOTENO")
	public String getQuoteNo() {
		return quoteNo;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	@Column(name = "RENEWALFLAG")
	@JSON(serialize=false)  
	public String getRenewalFlag() {
		return renewalFlag;
	}

	public void setRenewalFlag(String renewalFlag) {
		this.renewalFlag = renewalFlag;
	}

	@Column(name = "OLDLPOLICYNO")
	@JSON(serialize=false)  
	public String getOldlPolicyNo() {
		return oldlPolicyNo;
	}

	public void setOldlPolicyNo(String oldlPolicyNo) {
		this.oldlPolicyNo = oldlPolicyNo;
	}

	@Column(name = "CURRENCY")
	@JSON(serialize=false)  
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Column(name = "INPUTHOUR")
	@JSON(serialize=false)  
	public String getInputHour() {
		return inputHour;
	}

	public void setInputHour(String inputHour) {
		this.inputHour = inputHour;
	}

	@Column(name = "BUSINESSAREA")
	@JSON(serialize=false)  
	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	@Column(name = "REASON")
	@JSON(serialize=false)  
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "FLAG")
	@JSON(serialize=false)  
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "PRECHECKDATE")
	@JSON(serialize=false)  
	public String getPrecheckDate() {
		return precheckDate;
	}

	public void setPrecheckDate(String precheckDate) {
		this.precheckDate = precheckDate;
	}

	@Column(name = "MOREBUYNO")
	@JSON(serialize=false)  
	public String getMoreBuyNo() {
		return moreBuyNo;
	}

	public void setMoreBuyNo(String moreBuyNo) {
		this.moreBuyNo = moreBuyNo;
	}
	
	@Column(name = "BUSINESSSOURCE")
	@JSON(serialize=false)  
	public String getBusinessSource() {
		return businessSource;
	}

	public void setBusinessSource(String businessSource) {
		this.businessSource = businessSource;
	}

	@Column(name = "GROUPCHANNEL")
	@JSON(serialize=false)  
	public String getGroupChannel() {
		return groupChannel;
	}

	public void setGroupChannel(String groupChannel) {
		this.groupChannel = groupChannel;
	}

	@Column(name = "SELLTYPE")
	@JSON(serialize=false)  
	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	@JSON(serialize=false)  
	@Column(name = "REMINDCOUNT")
	public Integer getRemindCount() {
		return remindCount;
	}

	public void setRemindCount(Integer remindCount) {
		this.remindCount = remindCount;
	}
	
}
